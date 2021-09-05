;;; Sierra Script 1.0 - (do not remove this comment)
(script# 500)
(include game.sh)
(use Main)
(use PolyPath)
(use Polygon)
(use Motion)
(use Game)
(use System)

(public
	rm500 0
)

(instance rm500 of Room
	(properties
		picture 500
		vanishingY -20
	)
	
	(method (init)
		(HandsOff)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						15
						58
						48
						58
						60
						57
						70
						52
						76
						49
						140
						49
						145
						51
						159
						50
						180
						58
						185
						59
						232
						59
						259
						77
						270
						79
						278
						86
						275
						114
						276
						123
						258
						133
						234
						138
						196
						146
						158
						149
						102
						160
						63
						160
						17
						114
						8
						91
					yourself:
				)
		)
		(= gGOwnerMoveSpeed (ego moveSpeed?))
		(ego
			changeGait: 1
			setScale: 500
			setSpeed: 6
			ignoreActors: 1
			init:
		)
		((ScriptID 39 1)
			setScale: 500
			origStep: 2053
			ignoreActors: 1
			init:
		)
		(cond 
			((== prevRoomNum 420) (curRoom setScript: eventOne))
			((== prevRoomNum 510) (curRoom setScript: eventTwo))
			((== prevRoomNum 520) (curRoom setScript: eventThree))
			((== prevRoomNum 530)
				(if (Btst 30)
					(curRoom setScript: eventFourFreed)
				else
					(curRoom setScript: eventFourTrap)
				)
			)
		)
		(super init:)
	)
	
	(method (dispose)
		(DisposeScript 39)
		(super dispose:)
	)
)

(instance eventFourTrap of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					x: 103
					y: 165
					setCycle: Walk
					setMotion: PolyPath 65 166 self
				)
				((ScriptID 39 1)
					view: 501
					setLoop: 0
					x: 142
					y: 161
					setCycle: Forward
				)
			)
			(1
				(ego setMotion: PolyPath 9 54)
				((ScriptID 39 1) setLoop: 1 setCycle: EndLoop self)
			)
			(2
				((ScriptID 39 1)
					cycleSpeed: 3
					moveSpeed: 3
					view: 983
					setCycle: Walk
					setMotion: PolyPath 6 88 self
				)
			)
			(3
				((ScriptID 39 1)
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: PolyPath 6 54 self
				)
			)
			(4
				(ego setSpeed: gGOwnerMoveSpeed setMotion: 0)
				(curRoom newRoom: 420)
			)
		)
	)
)

(instance eventFourFreed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					x: 142
					y: 161
					setCycle: Walk
					setMotion: PolyPath 10 74 self
				)
				((ScriptID 39 1)
					x: 142
					y: 165
					view: 983
					setCycle: Walk
					setMotion: PolyPath 5 74
				)
			)
			(1
				((ScriptID 39 1)
					cycleSpeed: 3
					moveSpeed: 3
					setMotion: PolyPath 5 54 self
				)
				(ego setMotion: PolyPath 10 54)
			)
			(2
				((ScriptID 39 1) cycleSpeed: 6 moveSpeed: 6)
				(ego setSpeed: gGOwnerMoveSpeed setMotion: 0)
				(curRoom newRoom: 420)
			)
		)
	)
)

(instance eventThree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					x: 255
					y: 137
					setCycle: Walk
					setMotion: PolyPath 204 153 self
				)
				((ScriptID 39 1)
					x: 255
					y: 141
					view: 983
					setCycle: Walk
					setMotion: PolyPath 204 158
				)
			)
			(1
				(ego setMotion: PolyPath 142 161)
				((ScriptID 39 1)
					cycleSpeed: 3
					moveSpeed: 3
					setMotion: PolyPath 142 164 self
				)
			)
			(2
				((ScriptID 39 1) cycleSpeed: 6 moveSpeed: 6)
				(ego setMotion: 0)
				(curRoom newRoom: 530)
			)
		)
	)
)

(instance eventTwo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 39 1)
					view: 983
					x: 228
					y: 57
					setCycle: Walk
					setMotion: PolyPath 288 87
				)
				(ego
					x: 224
					y: 54
					setCycle: Walk
					setMotion: PolyPath 283 87 self
				)
			)
			(1
				((ScriptID 39 1)
					cycleSpeed: 3
					moveSpeed: 3
					setMotion: PolyPath 288 123 self
				)
				(ego setMotion: PolyPath 283 123)
			)
			(2
				((ScriptID 39 1) cycleSpeed: 6 moveSpeed: 6)
				(ego setMotion: 0)
				(curRoom newRoom: 520)
			)
		)
	)
)

(instance eventOne of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					x: 13
					y: 57
					setCycle: Walk
					setMotion: PolyPath 140 57
				)
				((ScriptID 39 1)
					view: 983
					x: 15
					y: 55
					setCycle: Walk
					setMotion: PolyPath 140 51 self
				)
			)
			(1
				((ScriptID 39 1)
					cycleSpeed: 3
					moveSpeed: 3
					setMotion: PolyPath 170 49 self
				)
				(ego setMotion: PolyPath 170 51)
			)
			(2
				((ScriptID 39 1) cycleSpeed: 6 moveSpeed: 6)
				(ego setMotion: 0)
				(curRoom newRoom: 510)
			)
		)
	)
)
