;;; Sierra Script 1.0 - (do not remove this comment)
(script# 984)
(include game.sh)
(use Main)

(public
	SortedAdd 0
)

(procedure (SortedAdd param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9 temp10 temp11 temp12 temp13 temp14 temp15)
	(asm
		ldi      0
		lapi     param2
		sat      temp1
		pushi    #x
		pushi    0
		lap      param1
		send     4
		sat      temp12
		pushi    #y
		pushi    0
		lap      param1
		send     4
		sat      temp13
		pushi    #heading
		pushi    0
		lap      param1
		send     4
		sat      temp14
		push    
		ldi      90
		lt?     
		bnt      code_0048
		pushi    5
		lst      temp12
		lst      temp13
		pushi    320
		pushi    0
		lsg      perspective
		callk    GetDistance,  10
		jmp      code_008d
code_0048:
		lst      temp14
		ldi      180
		lt?     
		bnt      code_0065
		pushi    5
		lst      temp12
		lst      temp13
		pushi    320
		pushi    200
		lsg      perspective
		callk    GetDistance,  10
		jmp      code_008d
code_0065:
		lst      temp14
		ldi      270
		lt?     
		bnt      code_0080
		pushi    5
		lst      temp12
		lst      temp13
		pushi    0
		pushi    200
		lsg      perspective
		callk    GetDistance,  10
		jmp      code_008d
code_0080:
		pushi    5
		lst      temp12
		lst      temp13
		pushi    0
		pushi    0
		lsg      perspective
		callk    GetDistance,  10
code_008d:
		sat      temp15
		-ap      argc
code_0091:
		ldi      0
		sat      temp7
		ldi      32767
		sat      temp6
		ldi      1
		sat      temp0
code_009e:
		lst      temp0
		lap      argc
		lt?     
		bnt      code_0190
		lat      temp0
		lapi     param2
		sat      temp2
		pushi    1
		pushi    #elements
		pushi    0
		send     4
		push    
		callk    FirstNode,  2
		sat      temp3
code_00b8:
		lat      temp3
		bnt      code_018b
		pushi    1
		pushi    1
		push    
		callk    NodeValue,  2
		sat      temp5
		push    
		callk    IsObject,  2
		bnt      code_018b
		pushi    #contains
		pushi    1
		lst      temp5
		lat      temp1
		send     6
		bnt      code_00db
		jmp      code_0180
code_00db:
		pushi    #x
		pushi    0
		lat      temp5
		send     4
		sat      temp8
		pushi    #y
		pushi    0
		lat      temp5
		send     4
		sat      temp9
		pushi    4
		lst      temp12
		lst      temp13
		lst      temp8
		push    
		callk    GetAngle,  8
		push    
		lat      temp14
		sub     
		sat      temp10
		pushi    1
		push    
		push    
		ldi      65356
		le?     
		bnt      code_010f
		ldi      360
		jmp      code_011b
code_010f:
		lst      temp10
		ldi      180
		gt?     
		bnt      code_011b
		ldi      65176
code_011b:
		add     
		push    
		callk    Abs,  2
		sat      temp10
		push    
		pushi    180
		lag      egoBlindSpot
		sub     
		le?     
		sat      temp11
		pushi    5
		lst      temp12
		lst      temp13
		lst      temp8
		lst      temp9
		lsg      perspective
		callk    GetDistance,  10
		push    
		lat      temp11
		bnt      code_0143
		lat      temp15
code_0143:
		add     
		sat      temp4
		push    
		lat      temp6
		le?     
		bnt      code_0180
		pushi    1
		lat      temp8
		le?     
		bnt      code_0180
		pprev   
		ldi      320
		le?     
		bnt      code_0180
		pushi    1
		lat      temp9
		le?     
		bnt      code_0180
		pprev   
		ldi      200
		le?     
		bnt      code_0180
		lst      temp4
		ldi      0
		eq?     
		bt       code_0178
		lat      temp11
		bnt      code_0180
code_0178:
		lat      temp5
		sat      temp7
		lat      temp4
		sat      temp6
code_0180:
		pushi    1
		lst      temp3
		callk    NextNode,  2
		sat      temp3
		jmp      code_00b8
code_018b:
		+at      temp0
		jmp      code_009e
code_0190:
		lat      temp7
		bnt      code_01a6
		pushi    #addToEnd
		pushi    1
		push    
		lat      temp1
		send     6
		jmp      code_0091
		jmp      code_01a6
		jmp      code_0091
code_01a6:
		ret     
	)
)
