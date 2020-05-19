;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm20 0
)

(instance rm20 of Room
	(properties
		picture 20
		horizon 57
		north 29
		east 19
		south 13
		west 21
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
				(ego
					posn: (proc0_17 302 (proc0_18 276 (ego x?) 200) 0) (+ horizon 2)
				)
			)
			(south (ego y: 188))
			(west
				(ego
					posn: 3 (proc0_18 118 (proc0_18 98 (ego y?) 72) 112)
				)
			)
			(east
				(ego posn: 317 (proc0_17 189 (ego y?) 90))
			)
			(else  (ego posn: 3 137))
		)
		(ego init:)
		(NormalEgo)
		(self setRegions: WATER)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(tree4 init:)
		(tree5 init:)
		(tree6 init:)
		(tree7 init:)
		(bush1 init:)
		(bush2 init:)
		(bush3 init:)
		(bush4 init:)
		(bush5 init:)
		(lake1 init:)
		(lake2 init:)
		(lake3 init:)
		(lake4 init:)
		(if (>= howFast 1)
			(fish init:)
			(ripple init: cycleSpeed: howFast setCycle: Forward)
		)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			((and script (not egoInWater))
				(script doit:)
			)
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(self newRoom: nRoom)
			)
			((and script egoInWater)
				(script doit:)
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
			((Said '/beaver,animal')
				(Print 20 0)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room]')
						(Print 20 1)
					)
					((Said '/stump')
						(Print 20 2)
					)
				)
			)
		)
	)
)

(instance ripple of Prop
	(properties
		x 94
		y 104
		view 700
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
				(Print 20 3)
			)
		)
	)
)

(instance fishJump of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fish
					posn: (Random 85 145) (Random 120 135)
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
					(Print 20 4)
				else
					(Print 20 5)
				)
			)
			(verbGet
				(Print 20 6)
			)
		)
	)
)

(instance tree1 of NewFeature
	(properties
		x 211
		y 100
		noun '/ceder'
		nsTop 84
		nsLeft 181
		nsBottom 117
		nsRight 241
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Many evergreens surround this pretty lake.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 211
		y 65
		noun '/ceder'
		nsTop 46
		nsLeft 193
		nsBottom 84
		nsRight 229
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Many evergreens surround this pretty lake.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 259
		y 56
		noun '/ceder'
		nsTop 42
		nsLeft 238
		nsBottom 70
		nsRight 281
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Many evergreens surround this pretty lake.}
	)
)

(instance tree4 of NewFeature
	(properties
		x 256
		y 33
		noun '/ceder'
		nsTop 25
		nsLeft 243
		nsBottom 42
		nsRight 270
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Many evergreens surround this pretty lake.}
	)
)

(instance tree5 of NewFeature
	(properties
		x 258
		y 17
		noun '/ceder'
		nsTop 11
		nsLeft 251
		nsBottom 24
		nsRight 265
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Many evergreens surround this pretty lake.}
	)
)

(instance tree6 of NewFeature
	(properties
		x 209
		y 39
		noun '/ceder'
		nsTop 33
		nsLeft 202
		nsBottom 45
		nsRight 216
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Many evergreens surround this pretty lake.}
	)
)

(instance tree7 of NewFeature
	(properties
		x 223
		y 128
		noun '/ceder'
		nsTop 117
		nsLeft 217
		nsBottom 139
		nsRight 230
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Many evergreens surround this pretty lake.}
	)
)

(instance lake1 of NewFeature
	(properties
		x 124
		y 84
		noun '/lake,lake,water'
		nsTop 61
		nsLeft 68
		nsBottom 108
		nsRight 181
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The water of this crystal clear lake is especially cold and crisp. The Leprechauns use it to brew their dandelion wine.}
	)
)

(instance lake2 of NewFeature
	(properties
		x 51
		y 86
		noun '/lake,lake,water'
		nsTop 69
		nsLeft 36
		nsBottom 103
		nsRight 67
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The water of this crystal clear lake is especially cold and crisp. The Leprechauns use it to brew their dandelion wine.}
	)
)

(instance lake3 of NewFeature
	(properties
		x 108
		y 121
		noun '/lake,lake,water'
		nsTop 104
		nsLeft 47
		nsBottom 131
		nsRight 170
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The water of this crystal clear lake is especially cold and crisp. The Leprechauns use it to brew their dandelion wine.}
	)
)

(instance lake4 of NewFeature
	(properties
		x 90
		y 143
		noun '/lake,lake,water'
		nsTop 131
		nsLeft 45
		nsBottom 156
		nsRight 136
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The water of this crystal clear lake is especially cold and crisp. The Leprechauns use it to brew their dandelion wine.}
	)
)

(instance bush1 of NewFeature
	(properties
		x 8
		y 71
		noun '/bush'
		nsTop 25
		nsBottom 117
		nsRight 16
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Large, lush, colorful bushes grow all about this little lake.}
	)
)

(instance bush2 of NewFeature
	(properties
		x 24
		y 78
		noun '/bush'
		nsTop 37
		nsLeft 16
		nsBottom 119
		nsRight 32
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Large, lush, colorful bushes grow all about this little lake.}
	)
)

(instance bush3 of NewFeature
	(properties
		x 38
		y 67
		noun '/bush'
		nsTop 47
		nsLeft 31
		nsBottom 87
		nsRight 45
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Large, lush, colorful bushes grow all about this little lake.}
	)
)

(instance bush4 of NewFeature
	(properties
		x 54
		y 76
		noun '/bush'
		nsTop 66
		nsLeft 45
		nsBottom 86
		nsRight 63
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Large, lush, colorful bushes grow all about this little lake.}
	)
)

(instance bush5 of NewFeature
	(properties
		x 42
		y 109
		noun '/bush'
		nsTop 101
		nsLeft 31
		nsBottom 117
		nsRight 54
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Large, lush, colorful bushes grow all about this little lake.}
	)
)
