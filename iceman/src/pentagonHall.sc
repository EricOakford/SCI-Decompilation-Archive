;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include game.sh)
(use Main)
(use Intrface)
(use n316)
(use InitAllFeatures)
(use GoToSaid)
(use LoadMany)
(use Follow)
(use Chase)
(use Grooper)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	pentagonHall 0
)

(local
	elevator2
)
(instance pentagonHall of Room
	(properties
		picture 20
	)
	
	(method (init)
		(super init: &rest)
		(self setRegions: 302)
		(LoadMany VIEW 20 120)
		(addToPics add: chair ashTray doit:)
		(InitAllFeatures)
		(door init:)
		(elevator1 init:)
		(button1 init: stopUpd:)
		(button2 init: stopUpd:)
		((= elevator2 (Clone elevator1))
			signal: (| ignrAct stopUpdOn)
			posn: 31 108
			loop: 1
			init:
		)
		(guard
			init:
			setCycle: Walk
			setLoop: GradualLooper
			loop: 3
			heading: 0
			setScript: guardScript
		)
		(ego init:)
		(if (!= prevRoomNum 21)
			(ego z: 1000 setScript: leavingElevator)
		else
			(globalSound
				number: (SoundFX 67)
				priority: 1
				loop: -1
				play:
			)
			(ego posn: 309 83 setScript: exitBriefScript)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room]')
				(Print 20 0)
			)
			((Said 'read/order')
				(if (== prevRoomNum 21)
					(Print 20 1)
				else
					(event claimed: FALSE)
				)
			)
		)
	)
)

(instance exitBriefScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(door ignoreActors: TRUE setCycle: EndLoop self)
			)
			(1
				(ego posn: 309 83 setMotion: MoveTo 309 93 self)
			)
			(2
				(door ignoreActors: FALSE setCycle: BegLoop)
				(ego setMotion: MoveTo 309 109 self)
			)
			(3
				(ego ignoreActors: 0 illegalBits: -12288)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance elevator1 of Prop
	(properties
		y 96
		x 109
		view 20
		priority 5
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/elevator]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 20 2)
					)
					((Said 'open')
						(Print 20 3)
					)
				)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(self stopUpd:)
	)
)

(instance elevatorScript of Script
	
	(method (init)
		(= start FALSE)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 4)
			)
			(1
				(client setCycle: EndLoop self)
			)
			(2
				(ego illegalBits: cWHITE setAvoider: Avoider)
				(if register
					(ego setMotion: MoveTo 3 105 self)
				else
					(ego setMotion: MoveTo 118 93 self)
				)
			)
			(3
				(ego setAvoider: 0 heading: 180)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 5)
			)
			(4 (client setCycle: BegLoop self))
			(5 (curRoom newRoom: 19))
		)
	)
)

(instance guard of Actor
	(properties
		y 162
		x 112
		view 120
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(cond 
			((super handleEvent: event))
			((or (Said '/id,card>') (Said '//id,card>'))
				(cond 
					((TurnIfSaid self event 'examine,check,read,look[<at]/*'))
					((Said 'examine,check,read,look[<at]')
						(cond 
							((and (ego has: iIDCard) (== ((inventory at: iIDCard) cel?) 1))
								(Print 20 4)
							)
							((ego has: iIDCard)
								(Print 20 5)
							)
							((== ((inventory at: iIDCard) owner?) curRoomNum)
								(Print 20 6)
							)
						)
					)
					((GoToIfSaid self event self 20 0 20 7))
					((Said 'show,give')
						(cond 
							(
								(and
									(ego has: iIDCard)
									(== ((inventory at: iIDCard) cel?) 0)
									(== prevRoomNum 21)
								)
								(Print 20 8)
							)
							((and (ego has: iIDCard) (== ((inventory at: iIDCard) cel?) 0))
								(Print 20 9)
								(Print 20 10)
								(ego put: iIDCard curRoomNum)
								(theGame changeScore: 1)
								(guardScript cue:)
								(HandsOn)
							)
							((and (ego has: iIDCard) (== ((inventory at: iIDCard) cel?) 1))
								(Print 20 11)
								(Print 20 12)
								(Print 20 13)
								((inventory at: iIDCard) cel: 0)
								(theGame changeScore: 1)
							)
							((IsInvItemInRoom curRoomNum iIDCard)
								(Print 20 14)
							)
						)
					)
					(
						(or
							(Said 'get,get,return')
							(Said 'ask/guard')
							(Said 'ask/')
						)
						(cond 
							((and (ego has: iIDCard) (== ((inventory at: iIDCard) cel?) 0))
								(Print 20 15)
							)
							((and (ego has: iIDCard) (== ((inventory at: iIDCard) cel?) 1))
								(Print 20 16)
								(Print 20 12)
								(Print 20 17)
								((inventory at: iIDCard) cel: 0)
								(theGame changeScore: 1)
							)
							((and (not (ego has: iIDCard)) (== prevRoomNum 21))
								(Print 20 18)
								((inventory at: iIDCard) cel: 1)
								(ego get: iIDCard)
							)
							((not (ego has: iIDCard))
								(Print 20 19)
							)
						)
					)
				)
			)
			((Said '[/guard,man]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 20 20)
					)
					((GoToIfSaid self event self 20 0 20 7))
					((Said 'address')
						(cond 
							(
								(and
									(!= prevRoomNum 21)
									(not (== ((inventory at: iIDCard) owner?) curRoomNum))
								)
								(if (Random 0 1)
									(Print 20 21)
								else
									(Print 20 22)
								)
							)
							((== prevRoomNum 21)
								(Print 20 23)
							)
							(else
								(Print 20 24)
							)
						)
					)
				)
			)
		)
	)
)

(instance guardScript of Script
	
	(method (doit)
		(super doit: &rest)
		(if (== state 3)
			(guard
				heading: (GetAngle (guard x?) (guard y?) (ego x?) (ego y?))
			)
			((guard looper?) doit: guard (guard heading?))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(if (IsInvItemInRoom curRoomNum iIDCard)
					(HandsOn)
					(self dispose:)
				else
					(guard setMotion: Chase ego 25 self)
				)
			)
			(2
				(Print 20 25)
				(ego setMotion: Follow guard 15)
				(= cycles 5)
			)
			(3
				(HandsOn)
			)
			(4
				(guard setMotion: MoveTo 112 166 self)
			)
			(5
				(guard heading: 0)
				((guard looper?) doit: guard (guard heading?))
				(= cycles 5)
			)
			(6 (self dispose:))
		)
	)
)

(instance door of Prop
	(properties
		y 86
		x 305
		view 20
		loop 4
	)
	
	(method (init)
		(super init:)
		(self stopUpd:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 20 26)
					)
					((GoToIfSaid self event self 20 0 20 7))
					((Said 'knock')
						(if (== prevRoomNum 21)
							(Print 20 27)
						else
							(Print 20 28)
						)
					)
					((Said 'open')
						(cond 
							((== prevRoomNum 21)
								(Print 20 29)
							)
							((ego has: iIDCard)
								(Print 20 30)
								(guard loop: 0)
							)
							(else
								(HandsOff)
								(self setScript: openDoorScript)
							)
						)
					)
				)
			)
		)
	)
)

(instance openDoorScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(door setPri: 2 ignoreActors: TRUE setCycle: EndLoop self)
			)
			(1
				(ego
					illegalBits: 0
					ignoreActors: TRUE
					setMotion: MoveTo 310 86 self
				)
			)
			(2
				(curRoom newRoom: 21)
			)
		)
	)
)

(instance ashTray of PicView
	(properties
		y 105
		x 91
		view 20
		loop 3
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/ashtray]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 20 31)
					)
					((Said 'use')
						(Print 20 32)
					)
				)
			)
		)
	)
)

(instance chair of PicView
	(properties
		y 108
		x 73
		view 20
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/chair]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 20 33)
					)
					((Said 'sit')
						(Print 20 34)
					)
				)
			)
		)
	)
)

(instance button1 of View
	(properties
		y 78
		x 99
		z 1
		view 20
		loop 2
		cel 1
		priority 6
		signal fixPriOn
	)
	
	(method (init)
		(super init: &rest)
		(if (< numColors 16)
			(self cel: 2)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/button]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 20 35)
					)
					((GoToIfSaid self event self 30 'push,press' 20 7))
					((Said 'push,press')
						(Print 20 36)
						(elevator1 setScript: elevatorScript 0 0)
					)
				)
			)
		)
	)
)

(instance button2 of View
	(properties
		y 82
		x 43
		view 20
		loop 2
		cel 1
		priority 6
		signal fixPriOn
	)
	
	(method (init)
		(super init: &rest)
		(if (< numColors 16)
			(self cel: 2)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/button]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 20 35)
					)
					((GoToIfSaid self event self 30 'push,press' 20 7))
					((Said 'push,press')
						(Print 20 36)
						(elevator2 setScript: elevatorScript 0 1)
					)
				)
			)
		)
	)
)

(instance leavingElevator of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 5)
			)
			(1
				(if (> (ego x?) 159)
					(ego posn: 118 94 0)
					(elevator1 setCel: 0 setCycle: EndLoop self)
					(= register 1)
				else
					(ego posn: 13 104 0)
					(elevator2 setCel: 0 setCycle: EndLoop self)
					(= register 0)
				)
			)
			(2
				(elevator1 stopUpd:)
				(elevator2 stopUpd:)
				(ego
					setMotion: MoveTo (+ (ego x?) 4) (+ (ego y?) 24) self
				)
			)
			(3
				(ego observeControl: (| cYELLOW cLRED))
				(if register
					(elevator1 setCycle: BegLoop self)
				else
					(elevator2 setCycle: BegLoop self)
				)
			)
			(4
				(= seconds 1)
			)
			(5
				(guardScript cue:)
				(= cycles 10)
			)
			(6
				(if register
					(elevator1 stopUpd:)
				else
					(elevator2 stopUpd:)
				)
				(self dispose:)
			)
		)
	)
)
