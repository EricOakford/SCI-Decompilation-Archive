;;; Sierra Script 1.0 - (do not remove this comment)
(script# 83)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room83 0
)
(synonyms
	(room cell)
)

(local
	henchman
	window
	printObj
)
(instance Room83 of Room
	(properties
		picture 83
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 649)
		(Load VIEW 634)
		(Load VIEW 512)
		(self setRegions: LOLOTTE)
		(super init:)
		((View new:)
			view: 634
			loop: 1
			cel: 0
			posn: 55 78
			setPri: 4
			init:
			addToPic:
		)
		((View new:)
			view: 634
			loop: 1
			cel: 1
			posn: 267 78
			setPri: 4
			init:
			addToPic:
		)
		(if isNightTime
			((Prop new:)
				view: 512
				loop: 0
				posn: 57 66
				setPri: 3
				init:
				setCycle: Forward
			)
			((Prop new:)
				view: 512
				loop: 0
				posn: 267 66
				setPri: 3
				init:
				setCycle: Forward
			)
		)
		(= isIndoors TRUE)
		(if isNightTime
			((= window (View new:))
				view: 649
				loop: 0
				cel: 0
				posn: 162 64
				init:
				stopUpd:
			)
		)
		(if (or (== prevRoomNum 86) (== prevRoomNum 0))
			(ego
				posn: 156 159
				view: 4
				illegalBits: cWHITE
				loop: 3
				xStep: 4
				yStep: 2
				init:
			)
			(if (== gamePhase startingOut)
				(Load VIEW 141)
				(self setScript: takeBack)
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (and (== gamePhase endGame) (& (ego onControl: 0) cBROWN))
			(curRoom newRoom: 86)
		)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						(
							(or
								(Said 'look[<around][/noword]')
								(Said 'look/room,castle')
							)
							(Print 83 0)
						)
						((Said 'look>')
							(cond 
								((Said '/skeleton,bone')
									(Print 83 1)
								)
								((Said '/machine')
									(Print 83 2)
								)
								((Said '/whip')
									(Print 83 3)
								)
								((Said '/chain')
									(Print 83 4)
								)
								((Said '/window')
									(Print 83 5)
								)
								((Said '/wall')
									(Print 83 6)
								)
								((or (Said '/dirt') (Said '<down'))
									(Print 83 7)
								)
							)
						)
						((or (Said 'use,(turn<on)/machine') (Said 'turn/wheel'))
							(Print 83 8)
						)
						((Said 'get/whip')
							(Print 83 9)
						)
						((Said 'get/chain')
							(Print 83 4)
						)
						((Said 'open/window')
							(Print 83 10)
						)
						((Said 'break/window')
							(Print 83 11)
						)
						((Said 'open/door')
							(if (< gamePhase killedLolotte)
								(Print 83 12)
							else
								(Print 83 13)
							)
						)
						((Said 'unlatch/door')
							(if (< gamePhase killedLolotte)
								(Print 83 14)
							else
								(Print 83 15)
							)
						)
						((Said 'call/help,save')	;EO: fixed said spec
							(Print 83 16)
						)
					)
				)
			)
		)
	)
)

(instance takeBack of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 30)
			)
			(1
				(User canControl: FALSE canInput: FALSE)
				(ego setMotion: 0)
				(Print 83 17)
				(if (ego inRect: 123 142 193 180)
					(ego setMotion: MoveTo 150 130 self)
				else
					(self cue:)
				)
			)
			(2
				(ego loop: 2)
				((= henchman (Actor new:))
					view: 141
					posn: 150 194
					illegalBits: 0
					ignoreActors: TRUE
					init:
					setCycle: Walk
					setMotion: MoveTo 150 160 self
				)
			)
			(3
				(= printObj
					(Print 83 18
						#at -1 10
						#font smallFont
						#dispose
					)
				)
				(User canControl: FALSE canInput: FALSE)
				(ego illegalBits: 0 setMotion: MoveTo 160 (ego y?) self)
			)
			(4
				(ego illegalBits: 0 setLoop: 2 setMotion: Follow henchman 5)
				(self cue:)
			)
			(5
				(henchman setMotion: MoveTo 150 225 self)
			)
			(6
				(cls)
				(ego setLoop: -1 setAvoider: 0)
				(curRoom newRoom: 86)
			)
		)
	)
)
