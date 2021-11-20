;;; Sierra Script 1.0 - (do not remove this comment)
(script# 557)
(include sci.sh)
(use Main)
(use GloryRm)
(use ForestView)
(use PChase)
(use Polygon)
(use Motion)

(public
	rm557 0
)

(instance rm557 of GloryRm
	(properties
		picture 430
		style $0400
		east 563
		south 558
		west 552
		rightY 120
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 0 319 0 319 100 192 110 150 91 158 83 117 78 82 93 0 93
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 169 189 169 168 197 168 281 135 319 135 319 189
					yourself:
				)
		)
		(self setRegions: 50)
		(if (Btst 35)
			(self setRegions: 51)
			((ScriptID 51 1)
				init:
				posn: 0 110
				setHeading: 135
				setMotion: PChase ego 25
			)
			(= west 0)
		)
		(super init: &rest)
		(curRoom
			addPoly:
				((Polygon new:)
					init: 68 92 100 85 110 70 196 84 75 110
					yourself:
				)
				130
				((Polygon new:)
					init: 221 109 232 118 190 134 170 126 189 115
					yourself:
				)
				130
				((Polygon new:)
					init: 281 152 259 130 288 118 261 107 319 100 319 154
					yourself:
				)
				130
				((Polygon new:)
					init: 319 95 195 108 133 126 155 158 99 165 55 138 186 90 319 87
					yourself:
				)
				60
				((Polygon new:)
					init: 0 165 83 183 64 189 0 189
					yourself:
				)
				60
		)
		(atp1 init: setPri: 159)
		(atp2 init:)
		(stream1 setCycle: Fwd init: setPri: 169)
		(if (Btst 380) (theGame save: 1))
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(Btst 35)
				(not (cast contains: (ScriptID 51 2)))
				(!= (curRoom script?) (ScriptID 50 1))
				(> (ego y?) 182)
			)
			((ScriptID 51 2)
				posn: (ego x?) (+ (ego y?) 25 6)
				init:
				setMotion: PChase ego 25
			)
		)
		(if
			(and
				(Btst 35)
				(< (ego x?) 20)
				(not ((ScriptID 51 1) mover?))
			)
			((ScriptID 51 1) setMotion: PChase ego 25)
		)
	)
	
	(method (dispose)
		(if ((ScriptID 51 1) mover?)
			(((ScriptID 51 1) mover?) dispose:)
		)
		(super dispose:)
	)
	
	(method (notify param1)
		(if (== param1 1)
			(if (not (cast contains: (ScriptID 51 2)))
				((ScriptID 51 2)
					posn: 160 220
					init:
					setMotion: PChase ego 25
				)
			)
			(curRoom setScript: (ScriptID 51 4))
		)
	)
)

(instance atp1 of ForestView
	(properties
		x 183
		y 127
		view 434
		loop 2
	)
)

(instance atp2 of ForestView
	(properties
		x 203
		y 8
		view 430
		loop 1
		cel 1
	)
)

(instance stream1 of ForestView
	(properties
		x 265
		y 186
		view 434
		loop 6
		signal $4001
		detailLevel 2
	)
)
