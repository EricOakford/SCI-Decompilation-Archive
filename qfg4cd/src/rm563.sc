;;; Sierra Script 1.0 - (do not remove this comment)
(script# 563)
(include sci.sh)
(use Main)
(use GloryRm)
(use ForestView)
(use PChase)
(use Polygon)
(use Motion)

(public
	rm563 0
)

(instance rm563 of GloryRm
	(properties
		picture 420
		style $0400
		horizon 70
		north 290
		south 564
		west 557
		topX 145
		leftY 138
		bottomX 224
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 0 128 0 128 84 153 106 105 98 83 107 100 115 23 131 0 131
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 46 145 66 159 117 159 135 167 158 167 210 189 0 189 0 142
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						267
						95
						228
						95
						207
						102
						167
						102
						144
						83
						162
						72
						162
						0
						619
						0
						619
						189
						237
						189
						258
						178
						266
						156
						224
						156
						224
						147
						280
						114
						267
						110
					yourself:
				)
		)
		(self setRegions: 50)
		(if (Btst 35)
			(self setRegions: 51)
			(= south 0)
			((ScriptID 51 1)
				init:
				posn: 220 160
				setHeading: 315
				setMotion: PChase ego 25
			)
		)
		(if (and Night (== prevRoomNum 290) (Btst 164))
			(self setRegions: 51)
			(Bclr 164)
			(theGame handsOff:)
			((ScriptID 51 1)
				posn: -30 100
				init:
				setMotion: PChase ego 25
			)
			((ScriptID 51 2)
				posn: 160 250
				init:
				setMotion: PChase ego 25
			)
			(= west (= south 0))
		)
		(super init: &rest)
		(curRoom
			addPoly:
				((Polygon new:)
					init: 31 155 145 124 181 124 220 111 241 109 319 149 319 189 25 189
					yourself:
				)
				60
				((Polygon new:)
					init: 152 84 187 102 41 104 31 88
					yourself:
				)
				130
		)
		(atp1 init:)
		(atp2 init:)
		(atp3 init: setPri: 42)
		(atp4 init:)
		(if Night
			(atp5 view: 428 loop: 0 cel: 2 x: 171 y: 189 setPri: 189)
			(if (== prevRoomNum 564) (ego posn: 221 210))
		else
			(atp5 setPri: 85)
		)
		(atp5 init:)
		(bridge init: setPri: 53)
		(town init:)
		(stream1 setPri: 42 setCycle: Fwd init:)
		(stream2 setPri: 42 setCycle: Fwd init:)
		(stream3 setPri: 42 setCycle: Fwd init:)
		(streamMat init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(Btst 35)
				(not (cast contains: (ScriptID 51 2)))
				(!= (curRoom script?) (ScriptID 50 1))
				(< (ego x?) 7)
			)
			((ScriptID 51 2)
				posn: (- (- (ego x?) 25) 15) (ego y?)
				init:
				setMotion: PChase ego 25
			)
		)
		(if
			(and
				(Btst 35)
				(> (ego y?) 160)
				(not ((ScriptID 51 1) mover?))
			)
			((ScriptID 51 1) setMotion: PChase ego 25)
		)
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
	
	(method (newRoom n)
		(if (and (Btst 35) (== n 290))
			(Bclr 35)
			(Bset 80)
			(Bset 164)
		)
		(super newRoom: n)
	)
	
	(method (notify param1)
		(if
			(and
				(== param1 1)
				(!= (curRoom script?) (ScriptID 51 4))
			)
			(if (not (cast contains: (ScriptID 51 2)))
				((ScriptID 51 2)
					posn: -40 100
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
		x 317
		y 164
		view 424
		loop 3
		cel 2
	)
)

(instance atp2 of ForestView
	(properties
		x 316
		y 50
		view 423
		loop 1
	)
)

(instance atp3 of ForestView
	(properties
		x 197
		y 132
		view 424
		loop 1
		cel 3
	)
)

(instance atp4 of ForestView
	(properties
		x 280
		y 14
		view 424
		loop 3
		cel 4
	)
)

(instance atp5 of ForestView
	(properties
		x 78
		y 5
		view 424
		loop 3
	)
)

(instance bridge of ForestView
	(properties
		x 13
		y 198
		z 50
		view 424
		loop 1
		cel 4
	)
)

(instance town of ForestView
	(properties
		x 173
		y 63
		view 421
		loop 2
	)
)

(instance stream1 of ForestView
	(properties
		x 187
		y 102
		view 420
		loop 5
		signal $4001
		detailLevel 2
	)
)

(instance stream2 of ForestView
	(properties
		x 30
		y 126
		view 420
		loop 7
		signal $4001
		detailLevel 2
	)
)

(instance stream3 of ForestView
	(properties
		x 56
		y 188
		view 424
		loop 5
		signal $4001
		detailLevel 2
	)
)

(instance streamMat of Polygon
	(properties)
	
	(method (init)
		(super init: 171 101 141 101 139 97 149 98 151 94 164 95)
	)
)
