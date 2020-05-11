;;; Sierra Script 1.0 - (do not remove this comment)
(script# 60)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room60 0
)
(synonyms
	(room bedroom)
)

(local
	trapdoor
	trapdoorOpen
	ladder
)
(instance Room60 of Room
	(properties
		picture 60
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 648)
		(Load VIEW 529)
		(self setRegions: HAUNTED_HOUSE)
		(super init:)
		(if isNightTime
			((View new:)
				view: 648
				loop: 1
				posn: 163 90
				setPri: 5
				addToPic:
			)
			((Prop new:)
				view: 536
				loop: 2
				posn: 267 75
				init:
				setPri: 13
				setCycle: Forward
			)
		)
		(if (> mansionPhase mansionLORD)
			(Load VIEW 529)
			(Load VIEW 77)
			((= ladder (View new:))
				view: 529
				loop: 1
				cel: 0
				posn: 161 137
				init:
				stopUpd:
			)
			((= trapdoor (View new:))
				view: 529
				loop: 0
				cel: 0
				posn: 161 33
				init:
				stopUpd:
				setPri: (- (ladder priority?) 1)
			)
			(= trapdoorOpen TRUE)
		)
		(if (== prevRoomNum 68)
			(ego posn: 68 159 view: 4 setStep: 4 2 init:)
		else
			(ego posn: 161 59 view: 77 setStep: 4 2 init:)
			(self setScript: climbDown)
		)
		(if
			(and
				(< 0 mansionPhase)
				(< mansionPhase 255)
				(== ghostRoomNum curRoomNum)
			)
			(NotifyScript HAUNTED_HOUSE -1)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: FALSE) $0040) (curRoom newRoom: 68))
	)
	
	(method (handleEvent event &tmp inventorySaidMe)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						(
							(or
								(Said 'look[<around][/!*]')
								(Said 'look[<around]/room')
							)
							(Print 60 0)
							(if (== mansionPhase mansionLADY) (Print 60 1))
						)
						((Said 'look>')
							(cond 
								((Said '<under/bed') (Print 60 2))
								((Said '/bed') (Print 60 3))
								((Said '/window')
									(if (ego inRect: 137 114 191 124)
										(Print 60 4)
									else
										(Print 800 1)
									)
								)
								((Said '/fireplace') (Print 60 5))
								((Said '/carpet,carpet') (Print 60 6))
								((Said '<in/chest,dresser,drawer') (Print 60 7))
								((Said '/chandelier') (Print 60 8))
								((Said '/chest,dresser') (Print 60 9))
								((Said '/wall') (Print 60 10))
								((or (Said '/dirt') (Said '<down')) (Print 60 11))
								((Said '/ceiling') (if trapdoorOpen (Print 60 12) else (Print 60 13)))
								((Said '<in,up/trapdoor') (if trapdoorOpen (Print 60 14) else (Print 60 15)))
								((Said '<up') (if trapdoorOpen (Print 60 16) else (SeeNothing)))
								((Said '/ladder') (if trapdoorOpen (Print 60 17) else (Print 60 18)))
								((Said '/trapdoor') (if trapdoorOpen (Print 60 19) else (Print 60 20)))
							)
						)
						((Said 'open/trapdoor') (if trapdoorOpen (Print 60 21) else (Print 60 22)))
						((Said 'close/trapdoor') (if trapdoorOpen (Print 60 23) else (Print 60 24)))
						((Said 'open/chest,dresser,drawer') (Print 60 7))
						(
							(or
								(Said 'lay,sleep[<in,down,on][/bed]')
								(Said 'get<in,in/bed')
							)
							(Print 60 25)
						)
						((Said 'climb[/ladder]')
							(if trapdoorOpen
								(if (< (ego distanceTo: ladder) 10)
									(if (>= (ego y?) (ladder y?))
										(ego setScript: climbLadder)
									else
										(Print 60 26)
									)
								else
									(Print 800 1)
								)
							else
								(Print 60 27)
							)
						)
						((Said 'climb') (Print 60 28))
						((Said 'move>') (Print 60 29))
					)
					(if
						(and
							(== mansionPhase mansionLADY)
							(== ghostHaunts TRUE)
							(== (event claimed?) FALSE)
						)
						(cond 
							((Said 'look/ghost,woman,woman,woman') (Print 60 1))
							((Said 'look/chair') (Print 60 30))
							(
								(or
									(Said 'converse/ghost,woman,woman,woman')
									(Said 'converse[/!*]')
								)
								(Print 60 31)
							)
							((Said 'get/ghost,woman,woman,woman') (Print 60 32))
							((Said 'get,capture/ghost,woman,woman,woman') (Print 60 33))
							((Said 'kill/ghost,woman,woman,woman') (Print 60 34))
							(
								(or
									(Said 'kiss/ghost,woman,woman,woman')
									(Said 'kiss[/!*]')
								)
								(Print 60 35)
							)
							((Said 'help/ghost,woman,woman,woman') (Print 60 36))
							((Said 'deliver>')
								(if (ego inRect: 60 118 105 152)
									(cond 
										(
											(and
												(= inventorySaidMe (inventory saidMe:))
												(ego has: (inventory indexOf: inventorySaidMe))
											)
											(if (== (inventory indexOf: inventorySaidMe) iLocket)
												(ego put: iLocket -1)
												(theGame changeScore: 2)
												(Print 60 37)
												(NotifyScript HAUNTED_HOUSE -1)
											else
												(Print 60 38)
											)
										)
										(inventorySaidMe (event claimed: FALSE))
									)
								else
									(Print 800 1)
									(event claimed: TRUE)
								)
							)
						)
					)
				)
			)
		)
	)
)

(instance climbLadder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					ignoreActors: 1
					illegalBits: 0
					setMotion: MoveTo 161 140 self
				)
			)
			(1
				(ego view: 77 loop: 0 setStep: 4 3 setCycle: EndLoop self)
			)
			(2
				(ego
					loop: 1
					cel: 0
					setPri: 12
					setCycle: Walk
					moveSpeed: 2
					cycleSpeed: 2
					setMotion: MoveTo 161 48 self
				)
			)
			(3
				(= isHandsOff FALSE)
				(= inCutscene TRUE)
				(client setScript: 0)
				(ego cycleSpeed: 0 moveSpeed: 0)
				(curRoom newRoom: 63)
			)
		)
	)
)

(instance climbDown of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 77
					loop: 1
					illegalBits: 0
					setStep: 3 3
					moveSpeed: 2
					cycleSpeed: 2
					setPri: 10
					setCycle: Reverse
					setMotion: MoveTo 161 140 self
				)
			)
			(1
				(ego
					view: 4
					loop: 3
					illegalBits: -32768
					setCycle: Walk
					cycleSpeed: 0
					moveSpeed: 0
					setPri: -1
					setStep: 4 2
				)
				(HandsOn)
				(= inCutscene FALSE)
			)
		)
	)
)
