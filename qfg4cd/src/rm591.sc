;;; Sierra Script 1.0 - (do not remove this comment)
(script# 591)
(include sci.sh)
(use Main)
(use GloryRm)
(use ForestView)
(use Polygon)

(public
	rm591 0
)

(instance rm591 of GloryRm
	(properties
		picture 430
		style $0400
		south 592
		west 587
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						-300
						-300
						619
						-300
						619
						489
						220
						489
						220
						178
						269
						171
						276
						159
						290
						154
						292
						143
						306
						138
						296
						132
						269
						132
						269
						124
						155
						92
						155
						80
						116
						76
						109
						87
						-300
						87
					yourself:
				)
		)
		(self setRegions: 50)
		(super init: &rest)
		(curRoom
			addPoly:
				((Polygon new:) init: 219 79 76 109 92 61 yourself:)
				130
				((Polygon new:)
					init:
						-300
						133
						220
						82
						231
						110
						156
						127
						178
						137
						241
						119
						267
						158
						197
						489
						-300
						489
					yourself:
				)
				60
		)
		(atp1 init:)
		(atp2 init:)
		(atp3 init:)
		(if (Btst 380) (theGame save: 1))
	)
)

(instance atp1 of ForestView
	(properties
		x 317
		y 77
		view 434
		loop 3
		cel 2
	)
)

(instance atp2 of ForestView
	(properties
		x 317
		y 104
		view 433
		loop 1
	)
)

(instance atp3 of ForestView
	(properties
		x 203
		y 7
		view 430
		loop 1
		cel 1
	)
)
