;;; Sierra Script 1.0 - (do not remove this comment)
(script# 565)
(include sci.sh)
(use Main)
(use GloryRm)
(use ForestView)
(use Polygon)

(public
	rm565 0
)

(instance rm565 of GloryRm
	(properties
		picture 430
		style $0400
		horizon 83
		north 564
		east 568
		south 566
		west 559
		topX 136
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 0 116 0 116 84 85 90 0 90
					yourself:
				)
		)
		(if Night
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init: 319 0 319 119 263 119 263 105 207 105 156 92 156 0
						yourself:
					)
			)
			(atp0 init: setPri: 106)
			(atp1 init: setPri: 249)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init: 319 0 319 97 207 105 156 92 156 0
						yourself:
					)
			)
		)
		(self setRegions: 50)
		(super init: &rest)
		(curRoom
			addPoly:
				((Polygon new:)
					init: 177 63 183 90 77 107 86 63
					yourself:
				)
				130
				((Polygon new:)
					init:
						319
						100
						258
						108
						290
						117
						267
						121
						216
						111
						147
						127
						226
						176
						209
						189
						134
						189
						50
						138
						76
						111
						209
						91
						319
						92
					yourself:
				)
				60
		)
		(if (Btst 380) (theGame save: 1))
	)
)

(instance atp0 of ForestView
	(properties
		x 281
		y 117
		view 436
		loop 1
	)
)

(instance atp1 of ForestView
	(properties
		x 40
		y 189
		view 438
		cel 3
	)
)
