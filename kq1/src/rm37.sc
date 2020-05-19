;;; Sierra Script 1.0 - (do not remove this comment)
(script# 37)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use RFeature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm37 0
)

(local
	local0
)
(procedure (SnakeWatching)
	(Print 37 5)
)

(instance rm37 of Room
	(properties
		picture 37
		horizon 73
		north 44
		east 36
		south 28
		west 38
	)
	
	(method (init)
		(LoadMany VIEW 237 303)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(super init:)
		(switch prevRoomNum
			(north
				(if (> (ego x?) 160)
					(ego posn: (proc0_18 293 (ego x?) 215) (+ horizon 2))
				else
					(ego posn: (proc0_18 125 (ego x?) 89) (+ horizon 2))
				)
			)
			(west
				(ego posn: 3 (proc0_17 200 (ego y?) (+ horizon 2)))
			)
			(south (ego y: 188))
			(east
				(ego
					posn: 317 (proc0_17 185 (proc0_18 98 (ego y?) 85) 76)
				)
			)
			(else  (ego posn: 3 137))
		)
		(ego init:)
		(NormalEgo)
		(snakeBody priority: 12)
		(addToPics add: snakeBody eachElementDo: #init doit:)
		(snakeHead setPri: 12 init: stopUpd:)
		(snakeTongue setPri: 12 init: cel: 2 stopUpd:)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(lake1 init:)
		(lake2 init:)
		(= local0 2)
		(if (>= howFast 1)
			(wa1 setPri: 0 setCycle: Forward init: ignoreActors:)
			(wa2 setPri: 0 setCycle: Forward init: ignoreActors:)
			(wa3 setPri: 0 setCycle: Forward init: ignoreActors:)
		)
		(self setRegions: WATER)
		(if (and (not (Btst fGoatFollows)) (>= howFast 1))
			(self setLocales: BIRD)
		)
	)
	
	(method (doit &tmp temp0)
		(if
		(and (not (snakeHead script?)) (>= howFast 1))
			(cond 
				((and (< (ego x?) (snakeHead x?)) (!= local0 2))
					(snakeHead setScript: headLeft)
				)
				((and (>= (ego x?) (snakeHead x?)) (!= local0 3))
					(snakeHead setScript: headRight)
				)
				((and (> (ego distanceTo: snakeHead) 95) (!= (snakeTongue cycler?) 0))
					(snakeTongue setCycle: EndLoop)
				)
				((and (> (ego distanceTo: snakeHead) 95) (== (snakeTongue cycler?) 0))
					(if (< (Random 1 100) 10)
						(snakeTongue setCycle: EndLoop)
					)
				)
				((and (<= (ego distanceTo: snakeHead) 95) (== (snakeTongue cycler?) 0) (< (Random 1 100) 35))
					(snakeTongue setCycle: EndLoop)
				)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check>')
				(if (Said '[<at,around][/room]')
					(Print 37 0)
				)
			)
			((Said 'climb,scale/ceder')
				(Print 37 1)
			)
			((Said 'capture,kill,shoot,get,take,eat,consume/snake')
				(Print 37 2)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance snakeBody of RPicView
	(properties
		x 178
		y 48
		description {snake}
		view 303
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((or (Said 'look,check/snake') (MousedOn self event shiftDown))
				(event claimed: TRUE)
				(SnakeWatching)
			)
			((Said 'look,check/tongue')
				(Print 37 3)
			)
			((Said 'talk,speak,call/snake')
				(Print 37 4)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance snakeHead of Prop
	(properties
		x 178
		y 48
		description {snake}
		view 303
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(SnakeWatching)
			)
		)
	)
)

(instance snakeTongue of Prop
	(properties
		x 175
		y 59
		description {snake}
		view 303
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(SnakeWatching)
			)
		)
	)
)

(instance wa1 of Prop
	(properties
		x 158
		y 114
		description {ripple}
		sightAngle 180
		closeRangeDist 500
		longRangeDist 500
		shiftClick 0
		view 237
		loop 1
		cycleSpeed 2
	)
	
	(method (handleEvent)
	)
)

(instance wa2 of Prop
	(properties
		x 126
		y 107
		description {ripple}
		sightAngle 180
		closeRangeDist 500
		longRangeDist 500
		shiftClick 0
		view 237
		loop 1
		cel 1
		cycleSpeed 2
	)
	
	(method (handleEvent)
	)
)

(instance wa3 of Prop
	(properties
		x 145
		y 134
		description {ripple}
		sightAngle 180
		closeRangeDist 500
		longRangeDist 500
		shiftClick 0
		view 237
		loop 2
		cycleSpeed 2
	)
	
	(method (handleEvent)
	)
)

(instance headLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 2)
				(snakeTongue setCycle: EndLoop self)
			)
			(1
				(snakeHead setCycle: BegLoop self)
			)
			(2
				(snakeTongue loop: 2 posn: 175 59)
				(self cue:)
			)
			(3
				(self dispose:)
			)
		)
	)
)

(instance headRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 3)
				(snakeTongue setCycle: EndLoop self)
			)
			(1
				(snakeHead setCycle: EndLoop self)
			)
			(2
				(snakeTongue loop: 3 posn: 185 58)
				(self cue:)
			)
			(3
				(self dispose:)
			)
		)
	)
)

(instance lake1 of NewFeature
	(properties
		x 167
		y 110
		noun '/lake,lake,water,swamp'
		nsTop 101
		nsLeft 35
		nsBottom 120
		nsRight 299
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The murky water of this swampy lake looks very uninviting.}
	)
)

(instance lake2 of NewFeature
	(properties
		x 158
		y 138
		noun '/lake,lake,water,swamp'
		nsTop 121
		nsLeft 64
		nsBottom 156
		nsRight 252
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The murky water of this swampy lake looks very uninviting.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 255
		y 101
		noun '/ceder'
		nsTop 51
		nsLeft 222
		nsBottom 152
		nsRight 288
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Large, mossy trees grow around this small, swampy lake.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 160
		y 24
		noun '/ceder'
		nsBottom 50
		nsRight 320
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Large, mossy trees grow around this small, swampy lake.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 69
		y 101
		noun '/ceder'
		nsTop 51
		nsLeft 28
		nsBottom 151
		nsRight 111
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Large, mossy trees grow around this small, swampy lake.}
	)
)
