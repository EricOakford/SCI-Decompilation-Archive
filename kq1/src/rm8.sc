;;; Sierra Script 1.0 - (do not remove this comment)
(script# 8)
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
	rm8 0
)

(instance rm8 of Room
	(properties
		picture 8
		horizon 54
		north 9
		east 7
		south 41
		west 83
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
				(ego posn: 204 (+ horizon 4))
			)
			(south
				(ego posn: (proc0_17 315 (ego x?) 33) 186)
			)
			(west
				(ego posn: 37 (proc0_17 195 (ego y?) 96))
			)
			(east
				(if (and (== egoInWater 0) (< (ego y?) 90))
					(ego y: 60)
				else
					(ego y: (proc0_17 198 (ego y?) 86))
				)
				(ego x: 317)
			)
			(else  (ego posn: 3 137))
		)
		(ego init:)
		(if (not egoInWater) (NormalEgo))
		(self setRegions: WATER)
		(bigtree1 init:)
		(bigtree2 init:)
		(smalltree1 init:)
		(lake1 init:)
		(lake2 init:)
		(lake3 init:)
		(underbrush init:)
		(bush init:)
		(rock1 init:)
		(rock2 init:)
		(rock3 init:)
		(rock4 init:)
		(rock5 init:)
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
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room]')
						(Print 8 0)
					)
					((Said '/waterfall')
						(Print 8 1)
					)
					((Said '/blossom')
						(Print 8 2)
					)
				)
			)
		)
	)
)

(instance ripple of Prop
	(properties
		x 185
		y 122
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
				(Print 8 3)
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
					posn: (Random 145 275) (Random 100 135)
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
					(Print 8 4)
				else
					(Print 8 5)
				)
			)
			(verbGet
				(Print 8 6)
			)
		)
	)
)

(instance bigtree1 of NewFeature
	(properties
		x 101
		y 14
		noun '/ceder[<big]'
		nsLeft 2
		nsBottom 29
		nsRight 170
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {Graceful willows adorn the lake.}
	)
)

(instance bigtree2 of NewFeature
	(properties
		x 110
		y 43
		noun '/ceder[<big]'
		nsTop 29
		nsLeft 33
		nsBottom 58
		nsRight 170
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {Graceful willows adorn the lake.}
	)
)

(instance smalltree1 of NewFeature
	(properties
		x 274
		y 31
		noun '/ceder<little'
		nsTop 1
		nsLeft 240
		nsBottom 55
		nsRight 289
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {A small, ordinary tree juts up from between the rocks.}
	)
)

(instance lake1 of NewFeature
	(properties
		x 222
		y 108
		noun '/lake,lake,water'
		nsTop 73
		nsLeft 125
		nsBottom 143
		nsRight 319
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a beautiful little lake.}
	)
)

(instance lake2 of NewFeature
	(properties
		x 109
		y 114
		noun '/lake,lake,water'
		nsTop 89
		nsLeft 94
		nsBottom 140
		nsRight 124
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a beautiful little lake.}
	)
)

(instance lake3 of NewFeature
	(properties
		x 86
		y 111
		noun '/lake,lake,water'
		nsTop 101
		nsLeft 79
		nsBottom 122
		nsRight 94
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a beautiful little lake.}
	)
)

(instance underbrush of NewFeature
	(properties
		x 88
		y 68
		noun '/underbrush,bury'
		nsTop 45
		nsBottom 91
		nsRight 176
		description {underbrush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The underbrush around the trees is home to many small furry creatures, none of which concern you at the moment.}
	)
)

(instance bush of NewFeature
	(properties
		x 106
		y 151
		noun '/bush'
		nsTop 142
		nsLeft 79
		nsBottom 161
		nsRight 134
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {Small bushes thrive by the clear little lake.}
	)
)

(instance rock1 of NewFeature
	(properties
		x 291
		y 79
		noun '/boulder'
		nsTop 76
		nsLeft 262
		nsBottom 83
		nsRight 320
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These rocks must lead to some sort of underground spring.__You remind yourself to explore their nooks and crannies sometime...when you don't have so much work to do!}
	)
)

(instance rock2 of NewFeature
	(properties
		x 277
		y 64
		noun '/boulder'
		nsTop 52
		nsLeft 234
		nsBottom 76
		nsRight 320
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These rocks must lead to some sort of underground spring.__You remind yourself to explore their nooks and crannies sometime...when you don't have so much work to do!}
	)
)

(instance rock3 of NewFeature
	(properties
		x 281
		y 47
		noun '/boulder'
		nsTop 42
		nsLeft 243
		nsBottom 52
		nsRight 319
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These rocks must lead to some sort of underground spring.__You remind yourself to explore their nooks and crannies sometime...when you don't have so much work to do!}
	)
)

(instance rock4 of NewFeature
	(properties
		x 311
		y 30
		noun '/boulder'
		nsTop 16
		nsLeft 303
		nsBottom 45
		nsRight 320
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These rocks must lead to some sort of underground spring.__You remind yourself to explore their nooks and crannies sometime...when you don't have so much work to do!}
	)
)

(instance rock5 of NewFeature
	(properties
		x 285
		y 36
		noun '/boulder'
		nsTop 30
		nsLeft 280
		nsBottom 42
		nsRight 291
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These rocks must lead to some sort of underground spring.__You remind yourself to explore their nooks and crannies sometime...when you don't have so much work to do!}
	)
)
