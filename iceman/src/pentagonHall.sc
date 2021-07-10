;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include sci.sh)
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
	local0
)
(instance pentagonHall of Rm
	(properties
		picture 20
	)
	
	(method (init)
		(super init: &rest)
		(self setRegions: 302)
		(LoadMany 128 20 120)
		(addToPics add: chair ashTray doit:)
		(InitAllFeatures)
		(door init:)
		(elevator1 init:)
		(button1 init: stopUpd:)
		(button2 init: stopUpd:)
		((= local0 (Clone elevator1))
			signal: 16385
			posn: 31 108
			loop: 1
			init:
		)
		(guard
			init:
			setCycle: Walk
			setLoop: Grooper
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
			((Said 'look[<at,around][/room]') (Print 20 0))
			((Said 'read/order')
				(if (== prevRoomNum 21)
					(Print 20 1)
				else
					(event claimed: 0)
				)
			)
		)
	)
)

(instance exitBriefScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(door ignoreActors: 1 setCycle: End self)
			)
			(1
				(ego posn: 309 83 setMotion: MoveTo 309 93 self)
			)
			(2
				(door ignoreActors: 0 setCycle: Beg)
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
		signal $4011
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/elevator]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 20 2))
					((Said 'open') (Print 20 3))
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
	(properties)
	
	(method (init)
		(= start 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 4))
			(1 (client setCycle: End self))
			(2
				(ego illegalBits: -32768 setAvoider: Avoid)
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
			(4 (client setCycle: Beg self))
			(5 (curRoom newRoom: 19))
		)
	)
)

(instance guard of Act
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
					(
					(TurnIfSaid self event 'examine,check,read,look[<at]/*'))
					((Said 'examine,check,read,look[<at]')
						(cond 
							(
							(and (ego has: 2) (== ((inventory at: 2) cel?) 1)) (Print 20 4))
							((ego has: 2) (Print 20 5))
							((== ((inventory at: 2) owner?) curRoomNum) (Print 20 6))
						)
					)
					((GoToIfSaid self event self 20 0 20 7))
					((Said 'show,give')
						(cond 
							(
								(and
									(ego has: 2)
									(== ((inventory at: 2) cel?) 0)
									(== prevRoomNum 21)
								)
								(Print 20 8)
							)
							(
							(and (ego has: 2) (== ((inventory at: 2) cel?) 0))
								(Print 20 9)
								(Print 20 10)
								(ego put: 2 curRoomNum)
								(theGame changeScore: 1)
								(guardScript cue:)
								(HandsOn)
							)
							(
							(and (ego has: 2) (== ((inventory at: 2) cel?) 1))
								(Print 20 11)
								(Print 20 12)
								(Print 20 13)
								((inventory at: 2) cel: 0)
								(theGame changeScore: 1)
							)
							((IsInvItemInRoom curRoomNum 2) (Print 20 14))
						)
					)
					(
						(or
							(Said 'get,get,return')
							(Said 'ask/guard')
							(Said 'ask/')
						)
						(cond 
							(
							(and (ego has: 2) (== ((inventory at: 2) cel?) 0)) (Print 20 15))
							(
							(and (ego has: 2) (== ((inventory at: 2) cel?) 1))
								(Print 20 16)
								(Print 20 12)
								(Print 20 17)
								((inventory at: 2) cel: 0)
								(theGame changeScore: 1)
							)
							((and (not (ego has: 2)) (== prevRoomNum 21)) (Print 20 18) ((inventory at: 2) cel: 1) (ego get: 2))
							((not (ego has: 2)) (Print 20 19))
						)
					)
				)
			)
			((Said '[/guard,man]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 20 20))
					((GoToIfSaid self event self 20 0 20 7))
					((Said 'address')
						(cond 
							(
								(and
									(!= prevRoomNum 21)
									(not (== ((inventory at: 2) owner?) curRoomNum))
								)
								(if (Random 0 1) (Print 20 21) else (Print 20 22))
							)
							((== prevRoomNum 21) (Print 20 23))
							(else (Print 20 24))
						)
					)
				)
			)
		)
	)
)

(instance guardScript of Script
	(properties)
	
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
				(if (IsInvItemInRoom curRoomNum 2)
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
			(3 (HandsOn))
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
					((Said 'look[<at]') (Print 20 26))
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
							((== prevRoomNum 21) (Print 20 29))
							((ego has: 2) (Print 20 30) (guard loop: 0))
							(else (HandsOff) (self setScript: openDoorScript))
						)
					)
				)
			)
		)
	)
)

(instance openDoorScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(door setPri: 2 ignoreActors: 1 setCycle: End self)
			)
			(1
				(ego
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo 310 86 self
				)
			)
			(2 (curRoom newRoom: 21))
		)
	)
)

(instance ashTray of PV
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
					((Said 'look[<at]') (Print 20 31))
					((Said 'use') (Print 20 32))
				)
			)
		)
	)
)

(instance chair of PV
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
					((Said 'look[<at]') (Print 20 33))
					((Said 'sit') (Print 20 34))
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
		signal $0010
	)
	
	(method (init)
		(super init: &rest)
		(if (< numColors 16) (self cel: 2))
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/button]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 20 35))
					(
					(GoToIfSaid self event self 30 'push,press' 20 7))
					((Said 'push,press') (Print 20 36) (elevator1 setScript: elevatorScript 0 0))
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
		signal $0010
	)
	
	(method (init)
		(super init: &rest)
		(if (< numColors 16) (self cel: 2))
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/button]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 20 35))
					(
					(GoToIfSaid self event self 30 'push,press' 20 7))
					((Said 'push,press') (Print 20 36) (local0 setScript: elevatorScript 0 1))
				)
			)
		)
	)
)

(instance leavingElevator of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 5))
			(1
				(if (> (ego x?) 159)
					(ego posn: 118 94 0)
					(elevator1 setCel: 0 setCycle: End self)
					(= register 1)
				else
					(ego posn: 13 104 0)
					(local0 setCel: 0 setCycle: End self)
					(= register 0)
				)
			)
			(2
				(elevator1 stopUpd:)
				(local0 stopUpd:)
				(ego
					setMotion: MoveTo (+ (ego x?) 4) (+ (ego y?) 24) self
				)
			)
			(3
				(ego observeControl: 20480)
				(if register
					(elevator1 setCycle: Beg self)
				else
					(local0 setCycle: Beg self)
				)
			)
			(4 (= seconds 1))
			(5
				(guardScript cue:)
				(= cycles 10)
			)
			(6
				(if register
					(elevator1 stopUpd:)
				else
					(local0 stopUpd:)
				)
				(self dispose:)
			)
		)
	)
)
