;;; Sierra Script 1.0 - (do not remove this comment)
(script# 951)
(include sci.sh)
(use Main)

(public
	proc951_0 0
	proc951_1 1
	proc951_2 2
	proc951_3 3
	proc951_4 4
	proc951_5 5
	proc951_6 6
	proc951_7 7
	proc951_8 8
	proc951_9 9
	proc951_10 10
	proc951_11 11
	proc951_12 12
	proc951_13 13
	proc951_14 14
	proc951_15 15
	proc951_16 16
	proc951_17 17
	proc951_18 18
	proc951_19 19
)

(procedure (proc951_0 param1 &tmp temp0 temp1)
	(asm
		pushi    2
		pushi    0
		pushi    22
		callk    Random,  4
		sat      temp0
code_003c:
		lst      temp0
		ldi      23
		ge?     
		bnt      code_0047
		ldi      0
		sat      temp0
code_0047:
		lst      temp0
		ldi      2
		mul     
		sat      temp1
		lsp      param1
		ldi      204
		eq?     
		bt       code_005e
		lsp      param1
		ldi      214
		eq?     
		bnt      code_0069
code_005e:
		lat      temp1
		lsgi     global118
		ldi      25050
		eq?     
		bt       code_00e3
code_0069:
		lsp      param1
		ldi      203
		eq?     
		bt       code_0079
		lsp      param1
		ldi      213
		eq?     
		bnt      code_0083
code_0079:
		lat      temp1
		lsgi     global118
		ldi      14080
		eq?     
		bt       code_00e3
code_0083:
		lsp      param1
		ldi      203
		eq?     
		bt       code_0093
		lsp      param1
		ldi      213
		eq?     
		bnt      code_009d
code_0093:
		lat      temp1
		lsgi     global118
		ldi      25050
		eq?     
		bt       code_00e3
code_009d:
		pushi    1
		pushi    62
		call     proc951_5,  2
		push    
		ldi      0
		eq?     
		bnt      code_00b5
		lat      temp1
		lsgi     global118
		ldi      8100
		eq?     
		bt       code_00e3
code_00b5:
		pushi    1
		pushi    62
		call     proc951_5,  2
		push    
		ldi      0
		eq?     
		bnt      code_00cd
		lat      temp1
		lsgi     global118
		ldi      11310
		eq?     
		bt       code_00e3
code_00cd:
		pushi    1
		pushi    62
		call     proc951_5,  2
		push    
		ldi      0
		eq?     
		bnt      code_00e3
		lat      temp1
		lsgi     global118
		ldi      22190
		eq?     
code_00e3:
		not     
		bnt      code_0220
		lst      temp1
		ldi      1
		add     
		lsgi     global118
		ldi      0
		eq?     
		bnt      code_0220
		lat      temp1
		lsgi     global118
		ldi      7112
		eq?     
		bnt      code_010e
		pushi    1
		pushi    64
		call     proc951_4,  2
		pushi    1
		pushi    8
		call     proc951_4,  2
code_010e:
		lat      temp1
		lsgi     global118
		ldi      8490
		eq?     
		bnt      code_0120
		pushi    1
		pushi    40
		call     proc951_4,  2
code_0120:
		lat      temp1
		lsgi     global118
		ldi      12181
		eq?     
		bnt      code_0132
		pushi    1
		pushi    10
		call     proc951_4,  2
code_0132:
		lat      temp1
		lsgi     global118
		ldi      16420
		eq?     
		bnt      code_014c
		pushi    1
		pushi    77
		call     proc951_4,  2
		pushi    1
		pushi    13
		call     proc951_4,  2
code_014c:
		lat      temp1
		lsgi     global118
		ldi      20553
		eq?     
		bnt      code_015e
		pushi    1
		pushi    17
		call     proc951_4,  2
code_015e:
		lat      temp1
		lsgi     global118
		ldi      21070
		eq?     
		bnt      code_0178
		pushi    1
		pushi    21
		call     proc951_4,  2
		pushi    1
		pushi    83
		call     proc951_4,  2
code_0178:
		lat      temp1
		lsgi     global118
		ldi      23550
		eq?     
		bnt      code_018a
		pushi    1
		pushi    23
		call     proc951_4,  2
code_018a:
		lat      temp1
		lsgi     global118
		ldi      24380
		eq?     
		bnt      code_01a4
		pushi    1
		pushi    66
		call     proc951_4,  2
		pushi    1
		pushi    91
		call     proc951_4,  2
code_01a4:
		lat      temp1
		lsgi     global118
		ldi      25005
		eq?     
		bnt      code_01b6
		pushi    1
		pushi    25
		call     proc951_4,  2
code_01b6:
		lat      temp1
		lsgi     global118
		ldi      29080
		eq?     
		bnt      code_01c8
		pushi    1
		pushi    68
		call     proc951_4,  2
code_01c8:
		lat      temp1
		lsgi     global118
		ldi      30420
		eq?     
		bnt      code_01da
		pushi    1
		pushi    58
		call     proc951_4,  2
code_01da:
		lat      temp1
		lsgi     global118
		ldi      31310
		eq?     
		bnt      code_01ec
		pushi    1
		pushi    11
		call     proc951_4,  2
code_01ec:
		lat      temp1
		lsgi     global118
		ldi      32570
		eq?     
		bnt      code_0206
		pushi    1
		pushi    82
		call     proc951_4,  2
		pushi    1
		pushi    81
		call     proc951_4,  2
code_0206:
		lat      temp1
		lsgi     global118
		ldi      35110
		eq?     
		bnt      code_0225
		ldi      1200
		sag      global546
		pushi    1
		pushi    90
		call     proc951_4,  2
		jmp      code_0225
code_0220:
		+at      temp0
		jmp      code_003c
code_0225:
		lsp      param1
		lst      temp1
		ldi      1
		add     
		sagi     global118
		pushi    3
		lofsa    {%d in %d}
		push    
		lsp      param1
		lat      temp1
		lsgi     global118
		callk    MonoOut,  6
		ret     
	)
)

(procedure (proc951_1 param1 &tmp temp0 temp1 temp2 temp3)
	(= temp1 [global164 (* 2 param1)])
	(= temp0 0)
	(switch param1
		(0
			(= temp3 {water})
			(cond 
				((== temp1 -1) (= temp0 3000))
				(
				(and (>= curRoomNum 20000) (proc951_11 4 25000)) (= temp0 9000))
				(else
					(switch (Random 0 1)
						(0 (= temp0 9000))
						(1 (= temp0 25000))
					)
				)
			)
		)
		(1
			(= temp3 {wax})
			(if (== temp1 -1)
				(= temp2 (Random 0 2))
			else
				(= temp2 (Random 0 3))
			)
			(switch temp2
				(0 (= temp0 8000))
				(1 (= temp0 22000))
				(2 (= temp0 24000))
				(3 (= temp0 temp1))
			)
		)
		(2
			(= temp3 {ash})
			(cond 
				((or (== temp1 -1) global186) (= temp0 6000))
				((not (proc951_5 59)) (= temp0 6000) (proc951_3 59))
				(else
					(switch (Random 0 1)
						(0 (= temp0 6000))
						(1 (= temp0 21000))
					)
				)
			)
		)
		(3
			(= temp3 {tar})
			(cond 
				(global186 (= temp0 14000))
				(
					(or
						(and (<= 14000 curRoomNum) (<= curRoomNum 14999))
						(== curRoomNum 13342)
					)
					(= temp0 14000)
				)
				(else
					(switch (Random 0 1)
						(0 (= temp0 11000))
						(1 (= temp0 14000))
					)
				)
			)
		)
		(4
			(= temp3 {fabric})
			(if
				(or
					(== temp1 -1)
					(and (<= 25000 curRoomNum) (<= curRoomNum 25999))
					(== curRoomNum 26210)
				)
				(= temp0 25000)
			else
				(switch (Random 0 3)
					(0 (= temp0 temp1))
					(1 (= temp0 25000))
					(2 (= temp0 20000))
					(3 (= temp0 21000))
				)
			)
		)
		(5
			(= temp3 {wood})
			(if (== temp1 -1)
				(= temp2 (Random 0 3))
			else
				(= temp2 (Random 0 7))
			)
			(switch temp2
				(0 (= temp0 7000))
				(1 (= temp0 23000))
				(2 (= temp0 24000))
				(3 (= temp0 -29536))
				(4 (= temp0 temp1))
				(5 (= temp0 temp1))
				(6 (= temp0 temp1))
				(7 (= temp0 temp1))
			)
		)
		(6
			(= temp3 {crystal})
			(if global186
				(= temp0 12000)
			else
				(switch (Random 0 1)
					(0 (= temp0 12000))
					(1 (= temp0 9000))
				)
			)
		)
		(7
			(= temp3 {electric})
			(cond 
				((< global547 9) (= temp2 (Random 0 1)))
				(
				(and (<= -26536 curRoomNum) (<= curRoomNum -25537)) (= temp2 2))
				((proc951_5 79) (= temp2 2))
				(else (= temp2 (Random 0 2)))
			)
			(switch temp2
				(0 (= temp0 29000))
				(1 (= temp0 32000))
				(2 (= temp0 -26536))
			)
		)
		(8
			(= temp3 {sand})
			(if global186
				(= temp0 12000)
			else
				(switch (Random 0 1)
					(0 (= temp0 12000))
					(1 (= temp0 19000))
				)
			)
		)
		(9
			(= temp3 {metal})
			(if (== temp1 -1)
				(= temp2 (Random 0 2))
			else
				(= temp2 (Random 0 3))
			)
			(if global186
				(= temp0 17000)
			else
				(switch temp2
					(0 (= temp0 17000))
					(1 (= temp0 -28536))
					(2 (= temp0 11000))
					(3 (= temp0 temp1))
				)
			)
		)
	)
	(cond 
		((== temp1 0) (MonoOut {%s is dead} temp3))
		(temp0
			(= [global164 (* 2 param1)] temp0)
			(MonoOut {%s goes to %d} temp3 temp0)
		)
		(else (MonoOut {%s stays in %d} temp3 temp0))
	)
)

(procedure (proc951_2 param1 &tmp temp0)
	(= global109 (+ global109 param1))
	(if (> param1 0)
		(proc951_15 (* param1 75))
	else
		(proc951_15 (* param1 150))
	)
	(cond 
		((> global109 100) (= global109 100))
		((< global109 0) (= global109 0))
	)
	(= temp0 (- 10 (/ global109 10)))
	((ScriptID 950 1) cel: temp0)
	(UpdateScreenItem (ScriptID 950 1))
	(if (== global109 0)
		(sounds play: 15011 0 98 0)
		(curRoom newRoom: 914)
	)
)

(procedure (proc951_3 param1)
	(= [global209 (/ param1 16)]
		(|
			[global209 (/ param1 16)]
			(>> $8000 (mod param1 16))
		)
	)
)

(procedure (proc951_4 param1)
	(= [global209 (/ param1 16)]
		(&
			[global209 (/ param1 16)]
			(~ (>> $8000 (mod param1 16)))
		)
	)
)

(procedure (proc951_5 param1)
	(return
		(&
			[global209 (/ param1 16)]
			(>> $8000 (mod param1 16))
		)
	)
)

(procedure (proc951_6)
	(return
		(if (or (!= mouseX gGEventX) (!= mouseY gGEventY))
			(= gGEventX mouseX)
			(= gGEventY mouseY)
			(return 1)
		else
			(return 0)
		)
	)
)

(procedure (proc951_7 param1)
	(if (ResCheck 140 param1)
		(Load 140 param1)
	else
		(Load 141 param1)
	)
	(MonoOut {Loading %d} param1)
)

(procedure (proc951_8 param1)
	(if (ResCheck 140 param1)
		(Unload 140 param1)
	else
		(Unload 141 param1)
	)
	(MonoOut {Unloading %d} param1)
)

(procedure (proc951_9 &tmp temp0)
)

(procedure (proc951_10)
)

(procedure (proc951_11 param1 param2 &tmp temp0)
	(= temp0 (== param2 [global164 (* 2 param1)]))
	(if
		(and
			(== param1 3)
			(== param2 14000)
			[global164 (* 2 param1)]
		)
		(= temp0 1)
	)
	(if (and (== param2 25000) [global164 8])
		(if (== param1 4) (= temp0 1))
		(if (and (== param1 0) [global164 0])
			(= temp0 0)
			(= [global164 0] 9000)
		)
	)
	(return temp0)
)

(procedure (proc951_12)
	(if global105
		(global108 dispose:)
		(= global108 0)
		(= global184 0)
		(switch global105
			(220
				((ScriptID 950 10) init:)
				(= global104 (| global104 $0080))
			)
			(226
				((ScriptID 950 4) init:)
				(= global104 (| global104 $0002))
			)
			(228
				((ScriptID 950 3) init:)
				(= global104 (| global104 $0001))
			)
			(229
				((ScriptID 950 5) init:)
				(= global104 (| global104 $0004))
			)
			(221
				((ScriptID 950 12) init:)
				(= global104 (| global104 $0200))
			)
			(223
				((ScriptID 950 6) init:)
				(= global104 (| global104 $0008))
			)
			(222
				((ScriptID 950 9) init:)
				(= global104 (| global104 $0040))
			)
			(224
				((ScriptID 950 11) init:)
				(= global104 (| global104 $0100))
			)
			(225
				((ScriptID 950 7) init:)
				(= global104 (| global104 $0010))
			)
			(227
				((ScriptID 950 8) init:)
				(= global104 (| global104 $0020))
			)
		)
		(sounds play: 15015 0 90 0)
		(++ global547)
		(MonoOut {%d captured} global547)
		(= global105 0)
		(normalCursor show:)
	)
)

(procedure (proc951_13 &tmp temp0 temp1)
	(if global105
		(global108 dispose:)
		(= global108 0)
		(= global184 0)
		(normalCursor show:)
		(if (> global105 219)
			(= temp1 (+ (= temp0 (+ 200 (mod global105 10))) 10))
			(proc951_0 temp0)
			(proc951_0 temp1)
		else
			(proc951_0 global105)
		)
		(= global105 0)
	)
)

(procedure (proc951_14 &tmp temp0 temp1 temp2 temp3)
	(= temp3 0)
	(while (< temp3 23)
		(cond 
			((== [global118 (= temp2 (* temp3 2))] 6220) (= [global118 (+ temp2 1)] 212))
			((== [global118 temp2] 19220) (= [global118 (+ temp2 1)] 202))
			((== [global118 temp2] 9420) (= [global118 (+ temp2 1)] 217))
			(else (= [global118 (+ temp2 1)] 0))
		)
		(++ temp3)
	)
	(= temp3 0)
	(while (< temp3 94)
		(proc951_4 temp3)
		(++ temp3)
	)
	(= temp3 0)
	(while (< temp3 178)
		(cond 
			((< temp3 20) (= [global350 temp3] 2500))
			((< temp3 66) (= [global350 temp3] 250))
			((< temp3 80) (= [global350 temp3] 300))
			((< temp3 130) (= [global350 temp3] 350))
			((< temp3 140) (= [global350 temp3] 625))
			((< temp3 143) (= [global350 temp3] 1000))
			((< temp3 160) (= [global350 temp3] 1500))
			((< temp3 167) (= [global350 temp3] 2750))
			((< temp3 170) (= [global350 temp3] 6500))
			((< temp3 177) (= [global350 temp3] 300))
		)
		(++ temp3)
	)
	(= temp0 200)
	(while (<= temp0 219)
		(if
		(and (!= temp0 212) (!= temp0 202) (!= temp0 217))
			(proc951_0 temp0)
		)
		(++ temp0)
	)
	(= temp1 0)
	(while (<= temp1 9)
		(= [global164 (* temp1 2)] -1)
		(proc951_1 temp1)
		(= [global164 (+ (* temp1 2) 1)] 0)
		(++ temp1)
	)
	(proc951_3 37)
	(if (> (theGame detailLevel:) 5)
		(proc951_4 35)
		(proc951_4 38)
	else
		(proc951_3 35)
		(proc951_3 38)
	)
	(= global109 100)
	(= global105 0)
	(= global106 0)
	(= global107 0)
	(= gExitFeature 0)
	(= global184 0)
	(= global185 0)
	(= global347 0)
	(= global104 0)
	(= global547 0)
	(= score 0)
	(= global349 0)
	(= global546 1200)
	(= global549 2)
	(= global550 0)
	(= global551 0)
	(= temp0 0)
	(while (< temp0 6)
		(= [global322 temp0] (+ (* (Random 1 5) 2) 1))
		(if
			(==
				[gCurColor temp0]
				[global334 (= [gCurColor temp0] (Random 0 3))]
			)
			(= [gCurColor temp0] (- 3 [global334 temp0]))
		)
		(= [global540 temp0] (Random 1 3))
		(++ temp0)
	)
)

(procedure (proc951_15 param1)
	(asm
		pushi    1
		pushi    87
		call     proc951_5,  2
		bnt      code_0d52
		ldi      0
		sap      param1
code_0d52:
		lsp      param1
		ldi      0
		ge?     
		bnt      code_0d8d
		lsg      score
		lap      param1
		add     
		sag      score
		lsg      global349
		push    
		ldi      1000
		div     
		add     
		sag      global349
		lsg      score
		ldi      1000
		mod     
		sag      score
		lsg      global349
		ldi      999
		gt?     
		bnt      code_0dd0
		ldi      999
		sag      global349
		ldi      999
		sag      score
		jmp      code_0dd0
code_0d8d:
		pushi    0
		lap      param1
		sub     
		sap      param1
code_0d93:
		lsp      param1
		ldi      1000
		ge?     
		bnt      code_0dbb
		lag      global349
		bnt      code_0daf
		-ag      global349
		lsp      param1
		ldi      1000
		sub     
		sap      param1
		jmp      code_0d93
code_0daf:
		ldi      0
		sag      score
		ldi      0
		sap      param1
		jmp      code_0dbb
		jmp      code_0d93
code_0dbb:
		lsg      score
		lap      param1
		gt?     
		bnt      code_0dcc
		lsg      score
		lap      param1
		sub     
		sag      score
		jmp      code_0dd0
code_0dcc:
		ldi      0
		sag      score
code_0dd0:
		lag      global349
		bnt      code_0de8
		pushi    3
		lofsa    {score:%3d%.3d}
		push    
		lsg      global349
		lsg      score
		callk    MonoOut,  6
		jmp      code_0df3
code_0de8:
		pushi    2
		lofsa    {score:%6d}
		push    
		lsg      score
		callk    MonoOut,  4
code_0df3:
		ret     
	)
)

(procedure (proc951_16 param1)
	(if [global350 param1]
		(MonoOut {scored event:%d} param1)
		(proc951_15 [global350 param1])
		(= [global350 param1] 0)
	)
)

(procedure (proc951_17 &tmp temp0 temp1 temp2 temp3)
	(= temp3 0)
	(while (< temp3 23)
		(= temp2 (* temp3 2))
		(= [global118 (+ temp2 1)] 0)
		(++ temp3)
	)
	(= temp3 0)
	(while (< temp3 94)
		(proc951_4 temp3)
		(++ temp3)
	)
	(= temp3 0)
	(while (< temp3 178)
		(= [global350 temp3] 0)
		(++ temp3)
	)
	(= temp1 0)
	(while (<= temp1 9)
		(= [global164 (* temp1 2)] 0)
		(++ temp1)
	)
	(proc951_3 87)
	(if (> (theGame detailLevel:) 5)
		(proc951_4 35)
		(proc951_4 38)
	else
		(proc951_3 35)
		(proc951_3 38)
	)
	(proc951_3 2)
	(= global109 100)
	(= global105 0)
	(= global106 0)
	(= global107 0)
	(= gExitFeature 0)
	(= global184 0)
	(= global185 0)
	(= global347 0)
	(= global104 1023)
	(= global547 10)
	(= global546 1200)
	(= global550 0)
	(= temp0 0)
	(while (< temp0 6)
		(= [global322 temp0] (+ (* (Random 1 5) 2) 1))
		(if
			(==
				[gCurColor temp0]
				[global334 (= [gCurColor temp0] (Random 0 3))]
			)
			(= [gCurColor temp0] (- 3 [global334 temp0]))
		)
		(= [global540 temp0] (Random 1 3))
		(++ temp0)
	)
)

(procedure (proc951_18 param1 &tmp temp0)
	(cond 
		((ResCheck 140 param1) (if (!= (Load 140 param1) 0) (Lock 140 param1 1)))
		((!= (Load 141 param1) 0) (Lock 141 param1 1))
	)
	(MonoOut {Locking %d} param1)
)

(procedure (proc951_19 param1)
	(if (ResCheck 140 param1)
		(Lock 140 param1 0)
	else
		(Lock 141 param1 0)
	)
	(MonoOut {Unlocking %d} param1)
)
