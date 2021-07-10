;;; Sierra Script 1.0 - (do not remove this comment)
(script# 76)
(include sci.sh)
(use Main)
(use Intrface)
(use tunisia)
(use CyclingProp)
(use InitAllFeatures)
(use SolvePuzzle)
(use GoToSaid)
(use LoadMany)
(use RFeature)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	oasisRm 0
	oasisFeat 1
)

(procedure (localproc_000e)
	(cond 
		((ego has: 5) (Print 76 0))
		((ego has: 2) (ego get: 5) ((inventory at: 5) loop: 1 cel: 0))
		(else
			(Print 76 1)
			(ego get: 5 6 2)
			((inventory at: 5) loop: 1 cel: 0)
			(theGame changeScore: 1)
			((inventory at: 2) view: 372 loop: 0 cel: 0)
			(Print 76 2)
			(Print 76 3)
			(Print 76 4)
		)
	)
)

(instance oasisRm of Rm
	(properties
		picture 76
		east 77
		picAngle 70
	)
	
	(method (init)
		(LoadMany 128 76 84 250 276 376 576)
		(Load rsSOUND 72)
		(HandsOn)
		(super init:)
		(globalSound
			number: 72
			owner: theGame
			priority: 1
			loop: -1
			play:
		)
		(water init:)
		(water4 init:)
		(water5 init:)
		(ego init:)
		(if (not (ego has: 2)) (agent init:))
		(if (> howFast 1) (flamingo init:))
		(addToPics
			add: palmTree treeTrunk rock1 rock2 rock3
			doit:
		)
		(InitAllFeatures)
		(if (== prevRoomNum 77)
			(ego init: x: 311)
		else
			(ego view: 84 init: posn: 311 100)
		)
		(self
			setRegions: 310 311
			setFeatures: building fallFeat pond oasisFeat
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room,scene,oasis]')
				(if (cast contains: agent)
					(Print 76 5)
					(Print 76 6)
					(if global142 (Print 76 7))
				else
					(Print 76 8)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(globalSound fade:)
		(super newRoom: newRoomNumber)
	)
	
	(method (notify)
		(agent setScript: agentLeaveScript)
	)
)

(instance agentScript of Script
	(properties
		seconds 5
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (client setCycle: End))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '/iceman,(man<ice)')
				(HandsOff)
				(InitArab ego)
				(client setScript: saidIceManScript)
				(self dispose:)
			)
			((Said 'look[<at][/babe,babe,agent]') (if global142 (Print 76 7) else (Print 76 9)))
			((Said '[/babe,babe,stacy,agent]')
				(InitArab ego)
				(Print 76 10 #at 100 40)
				(DisposeArab)
				(InitArab agent)
				(Print 76 11)
				(DisposeArab)
			)
		)
	)
)

(instance saidIceManScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(if (== (ego view?) 71)
					(Print 76 12 #at 100 40)
					(DisposeArab)
					(HandsOn)
					(client setScript: agentScript)
				else
					(= cycles 1)
				)
			)
			(2
				(DisposeArab)
				(HandsOn)
				(Print 76 13 #at 100 40)
				(if global142
					(Print 76 14 #at 100 40)
				else
					(Print 76 15 #at 100 40)
				)
				(Print 76 16 #at 100 40)
				(theGame changeScore: 1)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/babe,babe,stacy,agent]>')
				(cond 
					((TurnIfSaid client event 'look/*'))
					((Said 'look[<at]') (Print 76 17))
					((Said 'address') (Print 76 18))
					((Said 'kiss') (Print 76 19))
				)
			)
			(
				(GoToIfSaid
					self
					event
					(+ (agent x?) 25)
					(- (agent y?) 3)
					0
					76
					20
				)
			)
			((Said 'get/map') (localproc_000e))
			((Said 'drop,throw,give,return/map')
				(if (and (ego has: 5) ((inventory at: 5) loop?))
					(Print 76 21)
					(ego put: 5)
					(SolvePuzzle tunisia 413 2 1)
				else
					(Print 76 22)
				)
			)
		)
	)
)

(instance agentLeaveScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (agent loop?) 1)
					(agent loop: 0 setCel: 16 setCycle: Beg self)
				else
					(= cycles 1)
				)
			)
			(1
				(agent
					view: 250
					setAvoider: Avoid
					setStep: 3 2
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 332 143 self
				)
			)
			(2 (agent dispose:))
		)
	)
)

(instance flamingoDriver of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 15)))
			(1
				(flamingo
					setMotion: 0
					loop: (+ (flamingo loop?) 2)
					cel: 0
					setCycle: End
				)
				(= seconds (Random 3 7))
				(= register (Random 0 1))
			)
			(2
				(if register
					(flamingo
						loop: (+ (flamingo loop?) 2)
						cel: 0
						setCycle: End self
					)
				else
					(= cycles 1)
				)
			)
			(3
				(if register
					(flamingo loop: (+ (flamingo loop?) 2) setCycle: Fwd)
					(= seconds (Random 3 7))
				else
					(= cycles 1)
				)
			)
			(4
				(if register
					(flamingo
						loop: (- (flamingo loop?) 2)
						setCel: 16
						setCycle: Beg self
					)
				else
					(= cycles 1)
				)
			)
			(5
				(flamingo
					loop: (if register
						(- (flamingo loop?) 2)
					else
						(flamingo loop?)
					)
					setCel: 16
					setCycle: Beg self
				)
			)
			(6
				(flamingo loop: (- (flamingo loop?) 2) setCycle: Walk)
				(if (> (flamingo x?) 130)
					(flamingo setMotion: MoveTo 65 156)
				else
					(flamingo setMotion: MoveTo 150 156)
				)
				(self init:)
			)
		)
	)
)

(instance agent of Act
	(properties
		y 143
		x 207
		heading 180
		view 376
		loop 2
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self
			illegalBits: 0
			setCycle: Walk
			setScript: agentScript
			stopUpd:
		)
	)
)

(instance flamingo of Act
	(properties
		y 156
		x 45
		view 276
	)
	
	(method (init)
		(super init:)
		(self
			illegalBits: 0
			setCycle: Walk
			loop: 0
			cycleSpeed: 1
			moveSpeed: 1
			setScript: flamingoDriver
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/bird,flamingo]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]') (Print 76 23))
					((Said 'catch,scare,kill') (DontNeedTo))
				)
			)
		)
	)
)

(instance water of CyclingProp
	(properties
		y 145
		x 103
		view 76
		priority 10
	)
)

(instance water4 of CyclingProp
	(properties
		y 108
		x 29
		view 76
		loop 3
		priority 7
	)
)

(instance water5 of CyclingProp
	(properties
		y 173
		x 27
		view 76
		loop 4
		priority 12
	)
)

(instance palmTree of RPicView
	(properties
		y 33
		x 53
		view 576
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/palm<palm]>')
				(cond 
					((TurnIfSaid self event 'look[<at]'))
					((Said 'look[<at]') (Print 76 24))
				)
			)
		)
	)
)

(instance treeTrunk of RPicView
	(properties
		y 95
		x 42
		view 576
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/palm<palm]>')
				(cond 
					((TurnIfSaid self event 'look[<at]'))
					((Said 'look[<at]') (Print 76 24))
				)
			)
		)
	)
)

(instance rock1 of PV
	(properties
		y 99
		x 214
		view 576
		loop 2
		cel 1
	)
)

(instance rock2 of PV
	(properties
		y 84
		x 270
		view 576
		loop 2
		cel 2
	)
)

(instance rock3 of PV
	(properties
		y 96
		x 138
		view 576
		loop 2
		cel 2
	)
)

(instance fallFeat of RFeature
	(properties
		y 127
		x 100
		nsTop 113
		nsLeft 77
		nsBottom 141
		nsRight 123
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/waterfall]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 76 25))
				)
			)
		)
	)
)

(instance building of RFeature
	(properties
		y 38
		x 206
		nsTop 28
		nsLeft 173
		nsBottom 48
		nsRight 239
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/building]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 76 26))
				)
			)
		)
	)
)

(instance pond of RFeature
	(properties
		y 156
		x 144
		nsTop 143
		nsLeft 40
		nsBottom 169
		nsRight 248
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/water,pond]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 76 27))
					((Said 'get,swim,drink') (DontNeedTo))
				)
			)
		)
	)
)

(instance oasisFeat of RFeature
	(properties
		y 126
		x 331
		nsTop 125
		nsLeft 330
		nsBottom 127
		nsRight 332
	)
)
