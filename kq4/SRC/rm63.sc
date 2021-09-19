;;; Sierra Script 1.0 - (do not remove this comment)
(script# 63)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room63 0
)

(local
	chestOpen
	chest
	ladder
	trapdoor
)
(instance Room63 of Room
	(properties
		picture 63
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load PICTURE 60)
		(Load VIEW 44)
		(Load VIEW 40)
		(Load VIEW 77)
		(Load VIEW 42)
		(Load VIEW 537)
		(Load VIEW 529)
		(Load VIEW 648)
		(Load SCRIPT JUMP)
		(Load SOUND 52)
		(self setRegions: HAUNTED_HOUSE)
		(super init:)
		((View new:)
			view: 648
			loop: 5
			cel: 0
			posn: 280 104
			setPri: 6
			addToPic:
		)
		((Prop new:)
			view: 536
			loop: 1
			cel: 0
			posn: 224 123
			init:
			setPri: 15
			setCycle: Forward
		)
		((View new:)
			view: 537
			loop: 3
			cel: 0
			posn: 167 125
			addToPic:
		)
		((= chest (Prop new:))
			view: 537
			loop: 0
			cel: 0
			posn: 167 115
			init:
			stopUpd:
		)
		((View new:)
			view: 537
			loop: 1
			cel: 0
			posn: 165 157
			setPri: 11
			ignoreActors: 1
			addToPic:
		)
		(if (== mansionPhase mansionBOY)
			(Load VIEW 209)
		)
		(ego setScript: climbIn)
		(if
			(and
				(< 0 mansionPhase)
				(< mansionPhase mansionFINAL)
				(== ghostRoomNum curRoomNum)
			)
			(NotifyScript HAUNTED_HOUSE -1)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (and (& (ego onControl: origin) cGREEN) (not (ego script?)))
			(ego setScript: fallDead)
		)
	)
	
	(method (dispose)
		(DisposeScript JUMP)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						((Said '/box')
							(Print 63 0)
						)
						((Said 'get,open,look/birdcage,birdcage')
							(Print 63 1)
						)
						((Said 'play/boy,ghost')
							(if (== curRoomNum ghostRoomNum)
								(Print 63 2)
							else
								(Print 63 3)
							)
						)
						((Said 'look>')
							(cond 
								((Said '<in/chest')
									(if (ego inRect: 141 120 193 129)
										(ego setMotion: 0)
										(cond 
											((not chestOpen)
												(if (== mansionPhase mansionBOY)
													(Print 63 4)
												else
													(Print 63 5)
												)
											)
											((== ((inventory at: iSheetMusic) owner?) 63)
												(Print 63 6)
												(ego get: iSheetMusic)
												(= gotItem TRUE)
												(theGame changeScore: 2)
											)
											(else
												(Print 63 7)
											)
										)
									else
										(Print 800 1)
									)
								)
								((Said '/chest')
									(if (== mansionPhase mansionBOY)
										(Print 63 4)
									else
										(Print 63 8)
									)
								)
								((Said '<in/trapdoor')
									(if (ego inRect: 135 138 182 148)
										(Print 63 9)
									else
										(Print 63 10)
									)
								)
								((or (Said '/trapdoor') (Said '<down'))
									(Print 63 11)
								)
								((Said '/ladder')
									(Print 63 12)
								)
								((Said '/window')
									(Print 63 13)
								)
								((Said '/junk')
									(Print 63 14)
								)
								((Said '/wall')
									(Print 63 15)
								)
								((Said '/dirt')
									(Print 63 16)
								)
								((Said '/ghost')
									(if (== ghostRoomNum curRoomNum)
										(Print 63 17)
									else
										(Print 63 18)
									)
								)
								((Said '[<around][/room]')
									(Print 63 19)
								)
							)
						)
						((Said 'climb[/ladder,down]')
							(if (ego inRect: 131 138 205 159)
								(ego setScript: climbDown)
							else
								(Print 800 1)
							)
						)
						((Said 'close/trapdoor')
							(Print 63 20)
						)
						((Said 'open/trapdoor')
							(Print 63 21)
						)
						((Said 'get/chest')
							(Print 63 22)
						)
						((Said 'get,move,detach/ghost')
							(if (== ghostRoomNum curRoomNum)
								(Print 63 23)
							else
								(Print 63 18)
							)
						)
						((Said 'close/chest')
							(if chestOpen
								(chest setCycle: BegLoop)
								(= chestOpen FALSE)
							else
								(Print 63 24)
							)
						)
						((Said 'open/chest')
							(cond 
								(chestOpen
									(Print 63 25)
								)
								((== mansionPhase mansionBOY)
									(Print 63 26)
								)
								((ego inRect: 141 120 193 129)
									(ego setMotion: 0)
									(chest setCycle: EndLoop)
									(= chestOpen TRUE)
								)
								(else
									(Print 800 1)
								)
							)
						)
					)
				)
			)
		)
	)
)

(instance climbDown of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 196 149 self)
			)
			(1
				(ego
					setMotion: 0
					view: 40
					loop: 1
					cel: 255
					setCycle: EndLoop self
				)
			)
			(2
				(ego
					view: 77
					loop: 2
					cel: 255
					setPri: 12
					setCycle: EndLoop self
				)
			)
			(3
				(= isHandsOff FALSE)
				(= inCutscene TRUE)
				(curRoom newRoom: 60)
			)
		)
	)
)

(instance climbIn of Script
	(method (init who)
		(super init: who)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					posn: 196 150
					view: 77
					loop: 2
					setStep: 4 3
					setPri: 12
					setCycle: BegLoop self
					init:
				)
			)
			(1
				(ego
					view: 40
					moveSpeed: 0
					cycleSpeed: 0
					loop: 1
					setStep: 4 2
					setPri: -1
					setCel: 255
					setCycle: BegLoop self
				)
			)
			(2
				(ego view: 4 setCycle: Walk illegalBits: cWHITE)
				(client setScript: 0)
				(HandsOn)
			)
		)
	)
)

(instance fallSound of Sound)

(instance fallDead of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(fallSound number: 52 play:)
				(ego
					view: 44
					illegalBits: 0
					ignoreActors: 1
					setPri: (if (>= (ego priority?) 12) 11 else (ego priority?))
					setCycle: Forward
					setStep: 5 5
					setLoop: (if (> (ego x?) 155) 1 else 0)
					setMotion: MoveTo 155 155 self
				)
			)
			(1
				(ego
					setLoop: (+ 2 (ego loop?))
					setMotion: MoveTo 155 222 self
				)
			)
			(2
				(addToPics eachElementDo: #dispose)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 60 IRISIN)
				((= ladder (View new:))
					view: 529
					loop: 1
					cel: 0
					posn: 161 137
					ignoreActors: 1
					addToPic:
				)
				((= trapdoor (View new:))
					view: 529
					loop: 0
					cel: 0
					posn: 161 33
					setPri: (- (ladder priority?) 1)
					addToPic:
				)
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
				(ego
					posn: 156 59
					setStep: 1 10
					show:
					setMotion: MoveTo 161 150 self
				)
			)
			(3
				(ego view: 40 setLoop: (- 2 (ego loop?)) setCel: 2)
				(= cycles 1)
			)
			(4
				(sounds eachElementDo: #stop 0)
				(ego view: 42)
				(RedrawCast)
				(ShakeScreen 10)
				(= seconds 5)
			)
			(5
				(= dead TRUE)
			)
		)
	)
)
