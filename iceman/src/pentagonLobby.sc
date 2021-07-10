;;; Sierra Script 1.0 - (do not remove this comment)
(script# 19)
(include sci.sh)
(use Main)
(use Intrface)
(use washington)
(use EgoDead)
(use InitAllFeatures)
(use GoToSaid)
(use LoadMany)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	pentagonLobby 0
)

(local
	local0
	local1 =  1
)
(instance pentagonLobby of Rm
	(properties
		picture 19
		south 18
	)
	
	(method (init)
		(super init: &rest)
		(self setRegions: 302)
		(LoadMany 128 19 19)
		(addToPics
			add: eagle stars button1 ((Clone button1) x: 81)
			doit:
		)
		(InitAllFeatures)
		(elevator1 init:)
		(elevator2 init:)
		(blip1 init: setPri: 8 setCycle: Fwd)
		(blip2 init: setPri: 8 setCycle: Fwd)
		(guard init: setPri: 7)
		(if (== prevRoomNum 20)
			(ego init: z: 1000 setScript: leavingElevatorScript)
			(= local0 1)
		else
			(ego init: posn: 155 180 heading: 0)
			(DirLoop ego 0)
			(ego
				observeControl: 16400
				setMotion: MoveTo 155 146
				setScript: noAdmitScript
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room]') (Print 19 1) (Print 19 2))
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (!= newRoomNumber 20) (Print 19 0))
		(super newRoom: newRoomNumber &rest)
	)
)

(instance noAdmitScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not local0)
				(or
					(< (ego distanceTo: elevator1) 40)
					(< (ego distanceTo: elevator2) 40)
				)
				(== state 0)
			)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(switch (Random 0 2)
					(0 (Print 19 3) (Print 19 4))
					(1 (Print 19 5) (Print 19 6))
					(2 (Print 19 7))
				)
				(ego setMotion: 0 setAvoider: 0)
				(= cycles 1)
			)
			(2
				(HandsOff)
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 10) self)
			)
			(3 (HandsOn) (self init:))
		)
	)
)

(instance guard of View
	(properties
		y 121
		x 155
		z 1
		view 19
		loop 5
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'thank') (Print 19 8))
			((Said '[/guard,man]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 19 9))
					((GoToIfSaid self event 156 128 'address' 19 10))
					((Said 'address')
						(cond 
							((== prevRoomNum 20) (Print 19 11))
							(local0 (Print 19 12))
							((Random 0 1) (Print 19 13))
							(else (Print 19 14))
						)
					)
				)
			)
			((Said '/room<briefing') (if local0 (Print 19 15) else (Print 19 16)))
			((Said '/id,card[<id]>')
				(cond 
					((GoToIfSaid self event 156 128 'show' 19 10))
					((Said 'show')
						(cond 
							((not (ego has: 2)) (EgoDead 918 0 0 19 17))
							(local0 (Print 19 18))
							((ego has: 2)
								(Print 19 19)
								(Print 19 20)
								(= local0 1)
								(theGame changeScore: 1)
								(ego setScript: 0)
							)
						)
					)
				)
			)
		)
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
			(1
				(if register
					(elevator1 setCycle: End self)
				else
					(elevator2 setCycle: End self)
				)
			)
			(2
				(ego
					ignoreControl: 16400
					setAvoider: Avoid
					setMotion: MoveTo (if register 51 else 259) 86 self
				)
			)
			(3
				(ego setAvoider: 0 heading: 180)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 5)
			)
			(4
				(if register
					(elevator1 setCycle: Beg self)
				else
					(elevator2 setCycle: Beg self)
				)
			)
			(5 (curRoom newRoom: 20))
		)
	)
)

(instance elevator1 of Prop
	(properties
		y 91
		x 71
		view 19
		priority 10
		signal $4001
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door,elevator]>')
				(cond 
					((TurnIfSaid self event 'look[<at]'))
					((Said 'look') (Print 19 21))
					((Said 'open') (Print 19 22))
				)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(self stopUpd:)
	)
)

(instance elevator2 of Prop
	(properties
		y 91
		x 239
		view 19
		loop 1
		priority 10
		signal $4001
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door,elevator]>')
				(cond 
					((TurnIfSaid self event 'look[<at]'))
					((Said 'look') (Print 19 21))
					((Said 'open') (Print 19 22))
				)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(self stopUpd:)
	)
)

(instance button1 of PV
	(properties
		y 94
		x 224
		z 21
		view 19
		loop 2
		cel 2
		priority 6
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/button]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 19 23))
					((and (not local0) (Said 'push,press')) (Print 19 7))
					(
					(GoToIfSaid self event self 20 'push,press' 19 10))
					((Said 'push,press')
						(if (washington beenBriefed?)
							(Print 19 24)
							(Print 19 25)
						else
							(if local1 (= local1 0) (Print 19 26))
							(curRoom setScript: elevatorScript 0 (!= self button1))
						)
					)
				)
			)
		)
	)
)

(instance eagle of PV
	(properties
		y 74
		x 155
		view 19
		loop 2
		priority 6
	)
)

(instance stars of PV
	(properties
		y 35
		x 154
		view 19
		loop 2
		cel 1
		priority 6
	)
)

(instance blip1 of Prop
	(properties
		y 97
		x 101
		view 19
		loop 3
	)
)

(instance blip2 of Prop
	(properties
		y 98
		x 208
		view 19
		loop 4
	)
)

(instance leavingElevatorScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 5))
			(1
				(if (> (ego x?) 60)
					(ego posn: 264 84 0)
					(elevator2 setCel: 0 setCycle: End elevator2)
					(= register 1)
				else
					(ego posn: 48 84 0)
					(elevator1 setCel: 0 setCycle: End elevator1)
					(= register 0)
				)
				(= seconds 1)
			)
			(2
				(ego setMotion: MoveTo (ego x?) 145 self)
			)
			(3
				(ego observeControl: 16400)
				(if register
					(elevator2 setCycle: Beg self)
				else
					(elevator1 setCycle: Beg self)
				)
			)
			(4
				(if register
					(elevator2 stopUpd:)
				else
					(elevator1 stopUpd:)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
