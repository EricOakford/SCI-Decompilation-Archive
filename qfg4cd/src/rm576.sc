;;; Sierra Script 1.0 - (do not remove this comment)
(script# 576)
(include sci.sh)
(use Main)
(use GloryRm)
(use ForestView)
(use Polygon)

(public
	rm576 0
)

(instance rm576 of GloryRm
	(properties
		picture 430
		east 582
		south 577
	)
	
	(method (init)
		(if debugging (= Night 1))
		(if
			(or
				(and Night (not (Btst 63)))
				(and (Btst 63) (not (Btst 58)))
				(and (== global365 850) (== prevRoomNum 810))
			)
			(if (Btst 63) (Bset 66))
			(if (Btst 58) (Bset 65))
			(self setRegions: 53)
		)
		(self setRegions: 50)
		(super init: &rest)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						319
						144
						285
						144
						285
						134
						218
						134
						218
						116
						73
						116
						47
						132
						21
						132
						34
						156
						43
						156
						51
						172
						97
						172
						97
						189
						0
						189
						0
						0
						319
						0
					yourself:
				)
		)
		(curRoom
			addPoly:
				((Polygon new:)
					init: 319 98 319 189 74 189 171 94
					yourself:
				)
				60
		)
		(atp1 init:)
		(atp2 init:)
		(atp3 init:)
		(if Night
			(atp4 view: 438 loop: 0 cel: 2 x: 319 y: 48 setPri: 189)
		)
		(atp4 init:)
		(atp5 init:)
		(if (Btst 380) (theGame save: 1))
	)
)

(instance atp1 of ForestView
	(properties
		x 107
		y 71
		view 430
	)
)

(instance atp2 of ForestView
	(properties
		x 3
		y 104
		view 433
	)
)

(instance atp3 of ForestView
	(properties
		y 59
		view 434
		loop 2
		cel 1
	)
)

(instance atp4 of ForestView
	(properties
		x 290
		y 122
		view 434
		cel 2
	)
)

(instance atp5 of ForestView
	(properties
		x 117
		y 7
		view 430
		cel 1
	)
)
