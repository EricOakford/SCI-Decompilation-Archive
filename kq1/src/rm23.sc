;;; Sierra Script 1.0 - (do not remove this comment)
(script# 23)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm23 0
)

(instance rm23 of Room
	(properties
		picture 23
		horizon 57
		north 26
		east 22
		south 10
		west 24
	)
	
	(method (init)
		(if (>= howFast 1)
			(Load VIEW 302)
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
				(ego posn: 188 (+ horizon 2))
			)
			(south
				(ego y: 188)
			)
			(west
				(ego posn: 3 (proc0_17 189 (ego y?) 95))
			)
			(east
				(ego posn: 317 (proc0_17 189 (ego y?) 76))
			)
			(else
				(ego posn: 3 137)
			)
		)
		(ego init:)
		(NormalEgo)
		(self setRegions: WATER)
		(flower1 init:)
		(flower2 init:)
		(flower3 init:)
		(flower4 init:)
		(flower5 init:)
		(bush init:)
		(rock1 init:)
		(rock2 init:)
		(rock3 init:)
		(water1 init:)
		(water2 init:)
		(water3 init:)
		(water4 init:)
		(tree init:)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(tree4 init:)
		(rock5 init:)
		(if (>= howFast 1) (fish init:))
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script (script doit:))
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
			((Said 'look,check[<at,around][/room]')
				(Print 23 0)
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
					posn: (Random 65 235) (Random 105 120)
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
					(Print 23 1)
				else
					(Print 23 2)
				)
			)
			(verbGet
				(Print 23 3)
			)
		)
	)
)

(instance water1 of NewFeature
	(properties
		x 194
		y 59
		noun '/water,lake,lake'
		nsTop 53
		nsLeft 177
		nsBottom 65
		nsRight 212
		description {water}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The surrounding boulders and trees are reflected on the small, mirror-like lake.}
	)
)

(instance water2 of NewFeature
	(properties
		x 182
		y 82
		noun '/water,lake,lake'
		nsTop 66
		nsLeft 98
		nsBottom 98
		nsRight 267
		description {water}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The surrounding boulders and trees are reflected on the small, mirror-like lake.}
	)
)

(instance water3 of NewFeature
	(properties
		x 279
		y 87
		noun '/water,lake,lake'
		nsTop 77
		nsLeft 266
		nsBottom 98
		nsRight 292
		description {water}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The surrounding boulders and trees are reflected on the small, mirror-like lake.}
	)
)

(instance water4 of NewFeature
	(properties
		x 163
		y 113
		noun '/water,lake,lake'
		nsTop 98
		nsLeft 42
		nsBottom 129
		nsRight 284
		description {water}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The surrounding boulders and trees are reflected on the small, mirror-like lake.}
	)
)

(instance rock1 of NewFeature
	(properties
		x 140
		y 150
		noun '/boulder,boulder'
		nsTop 133
		nsLeft 114
		nsBottom 167
		nsRight 167
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Two large boulders protect the delicate flowers by the lake.}
	)
)

(instance rock2 of NewFeature
	(properties
		x 214
		y 143
		noun '/boulder,boulder'
		nsTop 121
		nsLeft 184
		nsBottom 165
		nsRight 245
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Two large boulders protect the delicate flowers by the lake.}
	)
)

(instance rock3 of NewFeature
	(properties
		x 189
		y 153
		noun '/boulder,boulder'
		nsTop 142
		nsLeft 100
		nsBottom 165
		nsRight 278
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Two large boulders protect the delicate flowers by the lake.}
	)
)

(instance tree of NewFeature
	(properties
		x 159
		y 10
		noun '/ceder'
		nsTop -1
		nsBottom 22
		nsRight 319
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This peaceful little lake is ringed with lovely trees.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 19
		y 48
		noun '/ceder'
		nsTop 22
		nsLeft 4
		nsBottom 75
		nsRight 35
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This peaceful little lake is ringed with lovely trees.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 58
		y 34
		noun '/ceder'
		nsTop 22
		nsLeft 35
		nsBottom 47
		nsRight 82
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This peaceful little lake is ringed with lovely trees.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 137
		y 32
		noun '/ceder'
		nsTop 22
		nsLeft 114
		nsBottom 42
		nsRight 161
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This peaceful little lake is ringed with lovely trees.}
	)
)

(instance tree4 of NewFeature
	(properties
		x 236
		y 30
		noun '/ceder'
		nsTop 23
		nsLeft 218
		nsBottom 37
		nsRight 255
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This peaceful little lake is ringed with lovely trees.}
	)
)

(instance bush of NewFeature
	(properties
		x 236
		y 47
		noun '/bush'
		nsTop 37
		nsLeft 211
		nsBottom 57
		nsRight 261
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is another unremarkable specimen of the hundreds of bushes in Daventry's forests.}
	)
)

(instance rock5 of NewFeature
	(properties
		x 260
		y 44
		noun '/boulder'
		nsTop 22
		nsLeft 201
		nsBottom 67
		nsRight 320
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Small plants and bushes grow in the cracks and crevices of this boulder.}
	)
)

(instance flower1 of NewFeature
	(properties
		x 95
		y 154
		noun '/blossom'
		nsTop 149
		nsLeft 89
		nsBottom 160
		nsRight 101
		description {flower}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are some beautiful, fragile little flowers here by the rocks.}
	)
)

(instance flower2 of NewFeature
	(properties
		x 182
		y 159
		noun '/blossom'
		nsTop 154
		nsLeft 170
		nsBottom 164
		nsRight 195
		description {flower}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are some beautiful, fragile little flowers here by the rocks.}
	)
)

(instance flower3 of NewFeature
	(properties
		x 167
		y 163
		noun '/blossom'
		nsTop 160
		nsLeft 165
		nsBottom 166
		nsRight 170
		description {flower}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are some beautiful, fragile little flowers here by the rocks.}
	)
)

(instance flower4 of NewFeature
	(properties
		x 200
		y 163
		noun '/blossom'
		nsTop 159
		nsLeft 195
		nsBottom 167
		nsRight 206
		description {flower}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are some beautiful, fragile little flowers here by the rocks.}
	)
)

(instance flower5 of NewFeature
	(properties
		x 264
		y 164
		noun '/blossom'
		nsTop 160
		nsLeft 251
		nsBottom 168
		nsRight 277
		description {flower}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are some beautiful, fragile little flowers here by the rocks.}
	)
)
