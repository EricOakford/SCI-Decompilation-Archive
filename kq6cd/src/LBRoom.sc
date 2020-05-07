;;; Sierra Script 1.0 - (do not remove this comment)
(script# 400)
(include sci.sh)
(use Main)
(use rLab)
(use KQ6Print)
(use n401)
(use n402)
(use n403)
(use n404)
(use Kq6Procs)
(use Print)
(use System)

(public
	LBRoom 0
)

(local
	[local0 100]
	[local100 24] = [-430 1 -435 7 -420 20 -408 51 -425 66 -410 68 -415 71 -405 117 -406 152 -407 180 -409 181 -440 182]
)
(class LBRoom of LabRoom
	(properties
		script 0
		number 0
		modNum 400
		noun 2
		timer 0
		keep 0
		initialized 0
		picture 400
		style $ffff
		horizon 135
		controls 0
		north 0
		east 0
		south 0
		west 0
		curPic 0
		picAngle 0
		vanishingX 160
		vanishingY 0
		obstacles 0
		inset 0
		walkOffEdge 1
		autoLoad 0
	)
	
	(method (init &tmp temp0)
		(super init:)
		(if (!= (theMusic number?) 400)
			(theMusic number: 400 setLoop: -1 play:)
		)
		(if (== prevRoomNum 99)
			((ScriptID 30 0) prevEdgeHit: 1)
			(= prevRoomNum 405)
		)
		(if (== prevRoomNum 411)
			(= temp0 ((ScriptID 30 0) labCoords?))
			(switch ((ScriptID 30 0) prevEdgeHit?)
				(1 (= temp0 (- temp0 16)))
				(3 (= temp0 (+ temp0 16)))
				(2 (++ temp0))
				(4 (-- temp0))
				(else 
					(Prints {No oldDir coming in from 411})
				)
			)
			((ScriptID 30 0) labCoords: temp0)
		else
			(= temp0
				(self
					calcRoom: (- 0 prevRoomNum) ((ScriptID 30 0) prevEdgeHit?)
				)
			)
		)
		(self
			initPseudoRoom: temp0 ((ScriptID 30 0) prevEdgeHit?)
		)
	)
	
	(method (newRoom n)
		(if ((ScriptID 30 0) holeIsUp?)
			((ScriptID 30 0) holeIsUp: 0)
			(proc404_2)
		)
		(self dumpPolys:)
		(if
			(<
				(= n
					(self
						calcRoom: ((ScriptID 30 0) labCoords?) ((ScriptID 30 0) prevEdgeHit?)
					)
				)
				0
			)
			(super newRoom: (- n))
		else
			(self initPseudoRoom: n ((ScriptID 30 0) prevEdgeHit?))
		)
	)
	
	(method (makeDoors param1 &tmp temp0)
		(if
			(OneOf
				param1
				19
				22
				35
				38
				51
				67
				85
				87
				97
				99
				101
				103
				113
				115
				117
				146
				149
				161
				163
				165
				168
				177
				179
				184
				193
				197
				200
				209
				213
				216
				226
				228
				230
				243
			)
			((ScriptID 30 7) addToPic:)
		)
		(if
			(OneOf
				param1
				2
				3
				7
				20
				21
				22
				39
				66
				67
				68
				69
				82
				83
				86
				87
				113
				114
				115
				116
				117
				146
				147
				148
				149
				150
				151
				152
				177
				178
				179
				180
				184
				210
				213
				214
				215
				216
				227
				228
			)
			((ScriptID 30 5) addToPic:)
			((ScriptID 30 9) addToPic:)
		)
		(if
			(OneOf
				param1
				1
				2
				6
				19
				20
				21
				38
				65
				66
				67
				68
				81
				82
				85
				86
				112
				113
				114
				115
				116
				145
				146
				147
				148
				149
				150
				151
				176
				177
				178
				179
				183
				209
				212
				213
				214
				215
				226
				227
			)
			((ScriptID 30 6) addToPic:)
			((ScriptID 30 10) addToPic:)
		)
		(if
			(not
				(OneOf
					param1
					3
					6
					19
					22
					35
					51
					69
					71
					81
					83
					85
					87
					97
					99
					101
					117
					130
					133
					145
					147
					149
					152
					161
					163
					168
					177
					184
					193
					197
					200
					210
					212
					214
					227
				)
			)
			((ScriptID 30 8) addToPic:)
		)
		((ScriptID 30 3) show:)
	)
	
	(method (makePolys param1)
		(cond 
			((OneOf param1 149 177) (proc401_4) ((ScriptID 30 4) dispose:))
			((OneOf param1 67 113 115 146 131 179 213) (proc403_0) ((ScriptID 30 4) dispose:))
			((OneOf param1 147 214 227) (proc403_1) ((ScriptID 30 0) initCrypt: 1))
			((OneOf param1 22 87 117 184) (proc403_2) ((ScriptID 30 0) initCrypt: 2))
			((OneOf param1 19 85) (proc403_3) ((ScriptID 30 0) initCrypt: 4))
			(
			(OneOf param1 35 51 97 99 101 161 163 168 193 197 200) (proc402_0) ((ScriptID 30 0) initCrypt: 4))
			(
				(OneOf
					param1
					2
					20
					21
					66
					68
					82
					86
					114
					116
					148
					150
					151
					178
					215
				)
				(proc402_1)
				((ScriptID 30 0) initCrypt: 1)
			)
			((OneOf param1 216 228) (proc402_2) ((ScriptID 30 0) initCrypt: 2))
			((OneOf param1 38 209 226) (proc402_3) ((ScriptID 30 0) initCrypt: 4))
			((OneOf param1 3 69 83 152 210) (proc402_4) ((ScriptID 30 0) initCrypt: 2))
			((OneOf param1 6 81 145 212) (proc402_5) ((ScriptID 30 0) initCrypt: 4))
			(
				(OneOf
					param1
					19
					22
					35
					38
					51
					67
					85
					87
					97
					99
					101
					103
					113
					115
					117
					146
					149
					161
					163
					165
					168
					177
					179
					184
					193
					197
					200
					209
					213
					216
					226
					228
					230
					243
				)
				(proc401_0)
				((ScriptID 30 0) initCrypt: 4)
			)
			(
				(OneOf
					param1
					3
					6
					19
					22
					35
					51
					69
					71
					81
					83
					85
					87
					97
					99
					101
					117
					130
					133
					145
					147
					149
					152
					161
					163
					168
					177
					184
					193
					197
					200
					210
					212
					214
					227
				)
				(proc401_1)
				((ScriptID 30 0) initCrypt: 2)
			)
			(
				(OneOf
					param1
					2
					3
					7
					20
					21
					22
					39
					66
					67
					68
					69
					82
					83
					86
					87
					113
					114
					115
					116
					117
					146
					147
					148
					149
					150
					151
					152
					177
					178
					179
					180
					184
					210
					213
					214
					215
					216
					227
					228
				)
				(proc401_2)
				((ScriptID 30 0) initCrypt: 1)
			)
			(else (proc401_3) ((ScriptID 30 0) initCrypt: 4))
		)
	)
	
	(method (dumpPolys)
		(if (curRoom obstacles?)
			((curRoom obstacles?) dispose:)
		)
		(curRoom obstacles: 0)
	)
	
	(method (calcRoom param1 param2 &tmp temp0 temp1)
		(if (< (= temp1 param1) 0)
			(= temp0 0)
			(while [local100 temp0]
				(if (== param1 [local100 temp0])
					(= temp1 [local100 (+ temp0 1)])
					(switch param2
						(1 (= temp1 (- temp1 16)))
						(3 (= temp1 (+ temp1 16)))
						(2 (++ temp1))
						(4 (-- temp1))
						(else 
							(Prints {No oldDir coming in})
						)
					)
					((ScriptID 30 0) labCoords: temp1)
					(return temp1)
					(break)
				)
				(= temp0 (+ temp0 2))
			)
			(if (< temp1 0)
				(KQ6Print
					addTextF: {Bad labyrinth room: room %d, direction %d} temp1 param2
				)
				(= temp1 117)
				(= param2 3)
				(ego posn: 160 80)
			)
		)
		(switch param2
			(1 (= temp1 (- temp1 16)))
			(3 (= temp1 (+ temp1 16)))
			(2 (++ temp1))
			(4 (-- temp1))
			(else 
				(Prints {No oldDir going out})
			)
		)
		((ScriptID 30 0) labCoords: temp1)
		(= temp0 1)
		(while (< temp0 24)
			(if (== temp1 [local100 temp0])
				(return [local100 (- temp0 1)])
			)
			(= temp0 (+ temp0 2))
		)
		(if (OneOf temp1 65 103 112 130 165 183 230)
			(return -411)
		)
		(return temp1)
	)
	
	(method (initPseudoRoom param1)
		(if (cast contains: (ScriptID 30 12))
			((ScriptID 30 12) dispose:)
		)
		(if (cast contains: (ScriptID 30 13))
			((ScriptID 30 13) dispose:)
		)
		(if (Btst 48)
			(self drawPic: 98 10)
		else
			(self drawPic: 98 -32761)
		)
		(self
			setScript: (ScriptID 30 1)
			makePolys: param1
			makeDoors: param1
			makeCritters:
		)
		(if
			(==
				((ScriptID 30 0) holeCoords?)
				((ScriptID 30 0) labCoords?)
			)
			(proc404_1)
		)
	)
)
