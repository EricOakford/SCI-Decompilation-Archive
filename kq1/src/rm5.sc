;;; Sierra Script 1.0 - (do not remove this comment)
(script# 5)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use StopWalk)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm5 0
)

(instance rm5 of Room
	(properties
		picture 5
		north 12
		east 4
		south 44
		west 6
	)
	
	(method (init)
		(if (>= howFast 1)
			(LoadMany VIEW 302 700)
		)
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
				(ego posn: (ego x?) 82)
			)
			(south
				(ego posn: (proc0_17 310 (ego x?) 119) 186)
			)
			(west
				(ego
					posn: 4 (proc0_17 185 (proc0_18 121 (ego y?) 91) 73)
				)
			)
			(east
				(cond 
					(egoInWater (ego y: (proc0_17 153 (ego y?) 109)))
					((< (ego y?) 131) (ego y: 78))
					(else (ego y: 175))
				)
				(ego x: 317)
			)
			(else
				(ego posn: 4 137)
			)
		)
		(ego init:)
		(if (not egoInWater) (NormalEgo))
		(self setRegions: WATER)
		(lake1 init:)
		(lake2 init:)
		(lake3 init:)
		(lake4 init:)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(tree4 init:)
		(tree5 init:)
		(bigrock1 init:)
		(bigrock2 init:)
		(bigrock3 init:)
		(bigrock4 init:)
		(smallrock1 init:)
		(root1 init:)
		(root2 init:)
		(root3 init:)
		(if (>= howFast 1)
			(fish init:)
			(ripple init: cycleSpeed: howFast setCycle: Forward)
		)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(
				(and
					(OneOf (ego onControl: origin) cBLUE cGREEN cCYAN cRED)
					(!= (ego illegalBits?) -24576)
				)
				(ego illegalBits: -24576)
			)
			(
				(and
					(not (OneOf (ego onControl: origin) cBLUE cGREEN cCYAN cRED))
					(== (ego illegalBits?) -24576)
				)
				(ego illegalBits: cWHITE)
			)
		)
		(cond 
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(Bclr fClimbingHill)
				(ego illegalBits: cWHITE priority: -1)
				(self newRoom: nRoom)
			)
			((and script (not egoInWater)) (script doit:))
			(
				(and
					(not (ego script?))
					(== newRoomNum curRoomNum)
					(& (ego onControl: origin) cLMAGENTA)
				)
				(curRoom setScript: slipIntoWater)
			)
			((and script egoInWater)
				(script doit:)
			)
			((and (& (ego onControl: origin) cLBLUE) (!= (ego script?) climbHill))
				(ego setScript: climbHill)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,lake]')
						(Print 5 0)
					)
					((Said '/well')
						(Print 5 1)
					)
				)
			)
		)
	)
)

(instance ripple of Prop
	(properties
		x 249
		y 132
		view 700
		loop 1
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/ripple')
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 5 2)
			)
		)
	)
)

(instance fishJump of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fish
					posn: (Random 267 318) (Random 120 146)
					loop: (Random 0 1)
					setCycle: EndLoop self
				)
			)
			(1
				(fish stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance fish of Prop
	(properties
		view 302
	)
	
	(method (init)
		(self
			cycleSpeed: (if (>= howFast 1) 1 else 0)
			ignoreActors:
			stopUpd:
		)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(< (Random 1 100) 3)
				(not (fish script?))
				(not egoInWater)
			)
			(fish setScript: fishJump)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/fish')
				(self doVerb: verbLook)
			)
			((Said 'get,take,capture/fish')
				(self doVerb: verbGet)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (fish script?)
					(Print 5 3)
				else
					(Print 5 4)
				)
			)
			(verbGet
				(Print 5 5)
			)
		)
	)
)

(instance climbHill of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					setLoop: 3
					setPri: 1
					setMotion: MoveTo (ego x?) (+ (ego y?) 15) self
				)
			)
			(1
				(NormalEgo)
				(HandsOn)
				(curRoom newRoom: (curRoom north?))
				(self dispose:)
			)
		)
	)
)

(instance slipIntoWater of Script
	(method (doit)
		(super doit: &rest)
		(if (and (== state 0) (& (ego onControl: origin) cBLUE))
			(self changeState: 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setCycle: 0
					ignoreActors:
					illegalBits: 0
					setStep: 6 4
					setMotion: MoveTo 312 120
				)
				(if (Btst fInvisible)
					(Print 5 6)
					(theGame changeScore: -3)
				)
			)
			(1
				(ego
					posn: (ego x?) (+ (ego y?) 2)
					setCycle: StopWalk 2
					setStep: 3 2
					ignoreActors: 0
					setMotion: 0
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance lake1 of NewFeature
	(properties
		x 286
		y 131
		noun '/lake,lake,water'
		nsTop 104
		nsLeft 253
		nsBottom 159
		nsRight 319
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a serene mountain lake.}
	)
)

(instance lake2 of NewFeature
	(properties
		x 237
		y 134
		noun '/lake,lake,water'
		nsTop 116
		nsLeft 223
		nsBottom 153
		nsRight 252
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a serene mountain lake.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 43
		y 17
		noun '/ceder,oak'
		nsLeft 3
		nsBottom 35
		nsRight 84
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {An old, dignified oak tree rests near the shore of the lake.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 75
		y 69
		noun '/ceder,oak'
		nsTop 35
		nsLeft 60
		nsBottom 104
		nsRight 90
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {An old, dignified oak tree rests near the shore of the lake.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 129
		y 29
		noun '/ceder,oak'
		nsTop 16
		nsLeft 105
		nsBottom 42
		nsRight 153
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {An old, dignified oak tree rests near the shore of the lake.}
	)
)

(instance tree4 of NewFeature
	(properties
		x 110
		y 47
		noun '/ceder,oak'
		nsTop 42
		nsLeft 89
		nsBottom 53
		nsRight 131
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {An old, dignified oak tree rests near the shore of the lake.}
	)
)

(instance tree5 of NewFeature
	(properties
		x 110
		y 3
		noun '/ceder,oak'
		nsTop -1
		nsLeft 83
		nsBottom 7
		nsRight 138
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {An old, dignified oak tree rests near the shore of the lake.}
	)
)

(instance bigrock1 of NewFeature
	(properties
		x 42
		y 87
		noun '/boulder[<big]'
		nsTop 57
		nsLeft 25
		nsBottom 117
		nsRight 59
		description {big rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The roots of the oak are entwined around this craggy cluster of rocks.}
	)
)

(instance bigrock2 of NewFeature
	(properties
		x 18
		y 92
		noun '/boulder[<big]'
		nsTop 71
		nsLeft 12
		nsBottom 113
		nsRight 25
		description {big rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The roots of the oak are entwined around this craggy cluster of rocks.}
	)
)

(instance bigrock3 of NewFeature
	(properties
		x 107
		y 86
		noun '/boulder[<big]'
		nsTop 76
		nsLeft 90
		nsBottom 96
		nsRight 125
		description {big rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The roots of the oak are entwined around this craggy cluster of rocks.}
	)
)

(instance bigrock4 of NewFeature
	(properties
		x 81
		y 120
		noun '/boulder[<big]'
		nsTop 104
		nsLeft 59
		nsBottom 136
		nsRight 104
		description {big rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The roots of the oak are entwined around this craggy cluster of rocks.}
	)
)

(instance smallrock1 of NewFeature
	(properties
		x 232
		y 102
		noun '/boulder[<little]'
		nsTop 92
		nsLeft 214
		nsBottom 112
		nsRight 251
		description {small rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A boulder sits at the side of lake, the water lapping gently against its sides.}
	)
)

(instance lake3 of NewFeature
	(properties
		x 191
		y 114
		noun '/lake,water'
		nsTop 105
		nsLeft 173
		nsBottom 123
		nsRight 209
		description {lake2}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a serene mountain lake.}
	)
)

(instance lake4 of NewFeature
	(properties
		x 295
		y 98
		noun '/lake,water'
		nsTop 96
		nsLeft 271
		nsBottom 101
		nsRight 320
		description {lake3}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a serene mountain lake.}
	)
)

(instance root1 of NewFeature
	(properties
		x 121
		y 101
		noun '/root'
		nsTop 95
		nsLeft 99
		nsBottom 107
		nsRight 143
		description {root}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The roots of the oak spread out here to soak up water from the moist earth.}
	)
)

(instance root2 of NewFeature
	(properties
		x 134
		y 115
		noun '/root'
		nsTop 107
		nsLeft 110
		nsBottom 124
		nsRight 159
		description {root}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The roots of the oak spread out here to soak up water from the moist earth.}
	)
)

(instance root3 of NewFeature
	(properties
		x 123
		y 131
		noun '/root'
		nsTop 124
		nsLeft 113
		nsBottom 139
		nsRight 134
		description {root}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The roots of the oak spread out here to soak up water from the moist earth.}
	)
)
