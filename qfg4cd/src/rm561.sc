;;; Sierra Script 1.0 - (do not remove this comment)
(script# 561)
(include sci.sh)
(use Main)
(use GloryRm)
(use ForestView)
(use Polygon)
(use Motion)

(public
	rm561 0
)

(instance rm561 of GloryRm
	(properties
		picture 420
		style $0400
		horizon 67
		north 560
		south 562
		west 556
		topX 162
		leftY 150
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 0 145 0 145 79 151 101 176 101 176 115 103 115 0 133
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						619
						0
						619
						189
						265
						189
						265
						164
						221
						164
						221
						152
						261
						152
						261
						123
						298
						75
						281
						75
						275
						75
						254
						97
						184
						101
						154
						81
						178
						68
						178
						0
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 0 180 69 180 69 189 0 189
					yourself:
				)
		)
		(self setRegions: 50)
		(super init: &rest)
		(curRoom
			addPoly:
				((Polygon new:)
					init: 195 58 175 82 142 83 123 58
					yourself:
				)
				130
				((Polygon new:)
					init:
						0
						189
						0
						157
						29
						132
						61
						144
						196
						110
						240
						112
						261
						122
						266
						133
						244
						152
						302
						189
					yourself:
				)
				60
		)
		(atp1 init: setPri: 106)
		(atp3 init: setPri: 159)
		(if Night
			(atp2Night init: setPri: 189)
			(atp4Night init: setPri: 189)
		else
			(atp2Day init: setPri: 95)
			(atp4Day init: setPri: 189)
		)
		(stream1 setCycle: Fwd init: setPri: 53)
		(stream2 setCycle: Fwd init: setPri: 53)
		(stream3 setCycle: Fwd init: setPri: 53)
		(streamMat init:)
		(if (Btst 380) (theGame save: 1))
	)
	
	(method (dispose)
		(streamMat dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(11
				(if (streamMat onMe: (ego x?) (ego y?))
					(messager say: 12 6 10 0 0 50)
				else
					(messager say: 30 0 18 0 0 50)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance atp1 of ForestView
	(properties
		x 173
		y 5
		view 424
		loop 1
		cel 2
	)
)

(instance atp3 of ForestView
	(properties
		x 316
		y 58
		view 423
		loop 1
	)
)

(instance atp2Day of ForestView
	(properties
		x 78
		y 5
		view 424
		loop 3
	)
)

(instance atp4Day of ForestView
	(properties
		x 73
		y 174
		view 424
		loop 3
		cel 3
	)
)

(instance atp2Night of ForestView
	(properties
		x 319
		y 82
		view 427
		loop 1
	)
)

(instance atp4Night of ForestView
	(properties
		x 169
		y 45
		view 427
		cel 3
	)
)

(instance stream1 of ForestView
	(properties
		x 251
		y 73
		view 420
		loop 3
		signal $4001
		detailLevel 2
	)
)

(instance stream2 of ForestView
	(properties
		x 187
		y 102
		view 420
		loop 5
		signal $4001
		detailLevel 2
	)
)

(instance stream3 of ForestView
	(properties
		x 30
		y 126
		view 420
		loop 7
		signal $4001
		detailLevel 2
	)
)

(instance streamMat of Polygon
	(properties)
	
	(method (init)
		(super init: 186 100 147 101 145 93 176 92)
	)
)
