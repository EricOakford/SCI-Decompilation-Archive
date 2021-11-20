;;; Sierra Script 1.0 - (do not remove this comment)
(script# 564)
(include sci.sh)
(use Main)
(use GloryRm)
(use ForestView)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)

(public
	rm564 0
)

(instance rm564 of GloryRm
	(properties
		picture 410
		style $0400
		horizon 70
		north 563
		east 567
		south 565
		topX 140
	)
	
	(method (init)
		(streamFX play:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						0
						78
						0
						78
						81
						136
						81
						173
						115
						82
						128
						67
						128
						67
						142
						114
						142
						114
						168
						63
						189
						0
						189
						0
						127
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 204 117 164 85 164 80 192 72 192 0 319 0 319 132 205 132
					yourself:
				)
		)
		(self setRegions: 50)
		(super init: &rest)
		(curRoom
			addPoly:
				((Polygon new:)
					init: 319 189 283 189 277 148 300 143 282 133 319 122
					yourself:
				)
				130
				((Polygon new:)
					init: 143 189 205 181 188 165 217 157 266 189
					yourself:
				)
				130
				((Polygon new:)
					init: 169 170 122 159 154 117 155 150 206 139 229 150 172 152
					yourself:
				)
				130
				((Polygon new:)
					init: 184 136 166 123 193 112 144 83 191 79 226 125
					yourself:
				)
				60
		)
		(atp1 init:)
		(atp3 init: setPri: 106)
		(atp4 init: setPri: 116)
		(atp5 init: setPri: 169)
		(if Night
			(atp2Night init:)
		else
			(atp2Day init: setPri: 180)
		)
		(stream1 setPri: 74 setCycle: Fwd init:)
		(streamMat init:)
		(leftTree init:)
		(rightTree init:)
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
		x 262
		y 73
		view 414
		loop 1
		cel 2
	)
)

(instance atp3 of ForestView
	(properties
		x 165
		y 57
		view 411
		loop 1
		cel 1
	)
)

(instance atp4 of ForestView
	(properties
		x 28
		y 110
		view 411
		loop 1
		cel 2
	)
)

(instance atp5 of ForestView
	(properties
		x 109
		y 119
		view 414
		loop 1
		cel 6
	)
)

(instance atp2Day of ForestView
	(properties
		x 62
		y 157
		view 414
		loop 1
		cel 4
	)
)

(instance atp2Night of ForestView
	(properties
		x 87
		y 189
		view 418
		loop 1
		cel 2
	)
)

(instance stream1 of ForestView
	(properties
		x 263
		y 73
		view 414
		loop 3
		signal $4001
		detailLevel 2
	)
)

(instance streamFX of Sound
	(properties
		number 982
		loop -1
	)
)

(instance leftTree of Feature
	(properties
		noun 6
		modNum 50
		nsLeft 32
		nsRight 63
		nsBottom 136
		sightAngle 40
		approachX 47
		approachY 68
		x 47
		y 68
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if [egoStats 11]
					(ego trySkill: 11 300)
					(messager say: 12 6 7 0 0 50)
				else
					(messager say: 12 6 8 0 0 50)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rightTree of Feature
	(properties
		noun 6
		modNum 50
		nsLeft 230
		nsTop -2
		nsRight 249
		nsBottom 114
		sightAngle 40
		approachX 239
		approachY 56
		x 239
		y 110
	)
	
	(method (doVerb theVerb)
		(leftTree doVerb: theVerb &rest)
	)
)

(instance streamMat of Polygon
	(properties)
	
	(method (init)
		(super init: 206 115 156 108 132 83 172 88 206 115)
	)
)
