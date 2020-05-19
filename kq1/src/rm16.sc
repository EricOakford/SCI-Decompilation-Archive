;;; Sierra Script 1.0 - (do not remove this comment)
(script# 16)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Game)
(use User)

(public
	rm16 0
)

(define goatIllBits $4406) ;(| cYELLOW cLGREEN cBLUE cGREEN)

(instance rm16 of Room
	(properties
		picture 16
		horizon 51
		north 17
		east 9
		south 83
		west 15
	)
	
	(method (init)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west SCROLLLEFT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(super init:)
		(switch prevRoomNum
			(north
				(ego posn: (proc0_17 168 (ego x?) 93) (+ horizon 2))
			)
			(south (ego posn: 163 188))
			(west
				(ego posn: 3 (proc0_17 113 (ego y?) (+ 2 horizon)))
			)
			(else 
				(ego posn: 317 (proc0_17 152 (ego y?) (+ 2 horizon)))
			)
		)
		(tree1 init:)
		(tree init:)
		(tree2 init:)
		(tree3 init:)
		(flowers init:)
		(bush init:)
		(bush1 init:)
		(NormalEgo)
		(ego init: illegalBits: -31744)
		(if (Btst fGoatFollows) (theGoat illegalBits: goatIllBits))
		(self setRegions: GARDEN)
	)
	
	(method (doit &tmp nRoom)
		(if
			(= nRoom
				(switch ((User alterEgo?) edgeHit?)
					(NORTH north)
					(EAST east)
					(SOUTH south)
					(WEST west)
				)
			)
			(ego illegalBits: cWHITE)
			(if (Btst fGoatFollows)
				(theGoat illegalBits: cYELLOW)
			)
			(self newRoom: nRoom)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event))
			(
				(or
					(Said 'look,check/castle')
					(MouseClaimed event 0 171 318 188)
					(MouseClaimed event 284 154 316 170)
					(MouseClaimed event 206 155 239 170)
					(MouseClaimed event 126 156 160 171)
					(MouseClaimed event 53 156 84 171)
					(MouseClaimed event 0 157 15 172)
				)
				(Bset fLookedAtGarden)
				(event claimed: TRUE)
				(Print 16 0)
			)
		)
	)
)

(instance tree of NewFeature
	(properties
		x 63
		y 8
		noun '/ceder'
		nsTop -1
		nsBottom 18
		nsRight 127
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Lovely trees grow near the carrot patch.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 32
		y 27
		noun '/ceder'
		nsTop 18
		nsBottom 37
		nsRight 64
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Lovely trees grow near the carrot patch.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 222
		y 19
		noun '/ceder'
		nsTop -1
		nsLeft 171
		nsBottom 39
		nsRight 274
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Lovely trees grow near the carrot patch.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 289
		y 96
		noun '/ceder'
		nsTop 76
		nsLeft 261
		nsBottom 116
		nsRight 318
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Lovely trees grow near the carrot patch.}
	)
)

(instance flowers of NewFeature
	(properties
		x 228
		y 80
		noun '/blossom'
		nsTop 76
		nsLeft 217
		nsBottom 85
		nsRight 240
		description {flower}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A patch of pretty flowers has been planted near the garden, just for decoration.}
	)
)

(instance bush of NewFeature
	(properties
		x 39
		y 47
		noun '/bush'
		nsTop 37
		nsBottom 57
		nsRight 79
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A copse of low bushes grows beneath the shade trees.}
	)
)

(instance bush1 of NewFeature
	(properties
		x 240
		y 47
		noun '/bush'
		nsTop 39
		nsLeft 179
		nsBottom 55
		nsRight 301
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A copse of low bushes grows beneath the shade trees.}
	)
)
