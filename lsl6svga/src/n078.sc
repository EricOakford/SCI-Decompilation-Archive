;;; Sierra Script 1.0 - (do not remove this comment)
(script# 78)
(include sci.sh)
(use Main)
(use fileScr)
(use Print)

(public
	proc78_0 0
	proc78_1 1
)

(procedure (proc78_0 param1 param2 &tmp temp0 temp1 temp2 temp3)
	(asm
		pushi    1
		pushi    85
		calle    Btst,  2
		bnt      code_0019
		ret     
code_0019:
		lsp      argc
		ldi      1
		gt?     
		bnt      code_002a
		lap      param1
		sat      temp2
		lap      param2
		sat      temp3
		jmp      code_0032
code_002a:
		ldi      65535
		sat      temp2
		ldi      65535
		sat      temp3
code_0032:
		lag      theCursor
		sat      temp1
		pushi    #setCursor
		pushi    1
		lsg      normalCursor
		lag      theGame
		send     6
		pushi    2
		pushi    235
		pushi    100
		callk    SetCursor,  4
code_004b:
		pushi    #addTitle
		pushi    5
		pushi    9
		pushi    0
		pushi    4
		pushi    1
		pushi    0
		pushi    431
		pushi    7
		pushi    9
		pushi    0
		pushi    0
		pushi    1
		pushi    50
		pushi    1
		pushi    0
		pushi    446
		pushi    5
		pushi    972
		pushi    0
		pushi    0
		pushi    0
		pushi    0
		pushi    443
		pushi    8
		pushi    2
		pushi    9
		pushi    0
		pushi    3
		pushi    1
		pushi    50
		pushi    33
		pushi    0
		pushi    443
		pushi    8
		pushi    1
		pushi    9
		pushi    0
		pushi    2
		pushi    1
		pushi    155
		pushi    33
		pushi    0
		pushi    1
		pushi    1
		lst      temp2
		pushi    2
		pushi    1
		lst      temp3
		pushi    147
		pushi    0
		class    Print
		send     102
		push    
		dup     
		ldi      1
		eq?     
		bnt      code_00bc
		pushi    #save
		pushi    0
		lag      theGame
		send     4
		jmp      code_00c7
		jmp      code_00c4
code_00bc:
		dup     
		ldi      2
		eq?     
		bnt      code_00c4
		jmp      code_00c7
code_00c4:
		toss    
		jmp      code_004b
code_00c7:
		pushi    #setCursor
		pushi    1
		lst      temp1
		lag      theGame
		send     6
		ret     
	)
)

(procedure (proc78_1 &tmp theTheCursor)
	(= theTheCursor theCursor)
	(theGame setCursor: normalCursor)
	(SetCursor 225 85)
	(switch
		(Print
			width: 150
			addTitle: 11 0 4 1 0
			addText: 11 0 0 1 50 1 0
			addIcon: 972 0 0 0 0
			addButton: 2 11 0 3 1 50 33 0
			addButton: 1 11 0 2 1 155 33 0
			init:
		)
		(100 (Bclr 85))
		(200 (Bset 85))
	)
	(theGame setCursor: theTheCursor)
)
