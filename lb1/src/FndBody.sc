;;; Sierra Script 1.0 - (do not remove this comment)
(script# 415)
(include game.sh)
(use Main)
(use System)

(public
	FndBody 0
	proc415_1 1
)

(local
	local0 = [7 36 10 43 5 37 -32768 2 48 10 50 5 46 3 50 -32768 2 48 10 52 5 46 6 51 -32768 2 51 7 44 10 51 5 49 6 52 9 53 -32768 10 52 -32768 10 52 -32768 10 50 -32768 -4096 -32768 2 60 10 63 5 57 3 57 -32768 2 60 10 53 5 57 6 60 -32768 2 64 7 58 10 64 5 60 9 64 -32768 10 65 -32768 10 65 -32768 10 63 -32768 -4096 -32768 -32768 2 49 10 53 5 47 6 52 -32768 2 52 7 45 10 52 5 50 6 53 9 54 -32768 10 52 -32768 10 50 -32768 10 51 -32768 -4096 -32768 -32768 -32768 2 49 7 42 10 49 5 48 6 50 9 51 -32768 10 50 -32768 10 50 -32768 10 48 -32768 -4096 -32768 -32768 -32768 -32768 10 55 -32768 10 55 -32768 10 53 -32768 -4096 -32768 -32768 -32768 -32768 -32768 10 45 -32768 10 43 -32768 -4096]
)
(procedure (proc415_1 param1 &tmp temp0 temp1 temp2 temp3 temp4 [temp5 50])
	(asm
		lsg      currentAct
		ldi      3
		eq?     
		bnt      code_0072
		lag      global135
		not     
		bnt      code_0072
		pushi    43
		ldi      17
		sali     local0
		pushi    52
		ldi      59
		sali     local0
		pushi    44
		ldi      91
		sali     local0
code_0072:
		ldi      0
		sat      temp0
		ldi      1
		sat      temp1
code_007a:
		lst      temp1
		lap      param1
		ne?     
		bnt      code_0099
		lat      temp0
		lsli     local0
		ldi      61440
		eq?     
		bnt      code_0094
		lst      temp1
		ldi      1
		shl     
		sat      temp1
code_0094:
		+at      temp0
		jmp      code_007a
code_0099:
		ldi      2
		sat      temp2
code_009d:
		lst      temp2
		lag      currentAct
		le?     
		bnt      code_00b7
		lat      temp0
		lsli     local0
		ldi      32768
		eq?     
		bnt      code_00b2
		+at      temp2
code_00b2:
		+at      temp0
		jmp      code_009d
code_00b7:
		lat      temp0
		lsli     local0
		ldi      1
		sub     
		push    
		ldi      7
		mul     
		sat      temp3
		push    
		+at      temp0
		lsli     local0
		ldi      16
		div     
		add     
		sat      temp3
		pushi    1
		lat      temp0
		lsli     local0
		ldi      16
		mod     
		shl     
		sat      temp4
		lat      temp3
		lsgi     global225
		lat      temp4
		bnot    
		and     
		push    
		lat      temp3
		sagi     global225
		+at      temp0
		lsli     local0
		ldi      32768
		eq?     
		bnt      code_00f5
		jmp      code_00f8
code_00f5:
		jmp      code_00b7
code_00f8:
		pushi    #dispose
		pushi    0
		lofsa    FndBody
		send     4
		ret     
	)
)

(instance FndBody of Script
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 415)
	)
)
