;;; Sierra Script 1.0 - (do not remove this comment)
(script# CASTAREA)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	castAreaScript 0
)

(local
	local0
	local1
	[local2 8] = [2 3 0 3 0 1 2 3]
	[local10 8] = [0 0 0 1 0 0 2 3]
)
(instance castAreaScript of Script
	(properties)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_009e
			pushi    0
			callb    HandsOff,  0
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			sal      local0
			pushi    #number
			pushi    1
			pushi    900
			pushi    155
			pushi    1
			pushi    1
			pushi    39
			pushi    0
			lag      globalSound
			send     16
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_007f
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_007f
			jmp      code_0055
code_0055:
			bnt      code_007f
			pushi    #cel
			pushi    0
			lag      ego
			send     4
			sal      local1
			pushi    #setMotion
			pushi    1
			pushi    0
			pushi    2
			pushi    1
			pushi    19
			pushi    3
			pushi    1
			lal      local0
			lsli     local10
			pushi    161
			pushi    2
			class    EndLoop
			push    
			pushSelf
			lag      ego
			send     26
			jmp      code_0238
code_007f:
			pushi    #setMotion
			pushi    1
			pushi    0
			pushi    2
			pushi    1
			pushi    15
			pushi    3
			pushi    1
			lal      local0
			lsli     local2
			pushi    161
			pushi    2
			class    EndLoop
			push    
			pushSelf
			lag      ego
			send     26
			jmp      code_0238
code_009e:
			dup     
			ldi      1
			eq?     
			bnt      code_00b3
			pushi    #setCycle
			pushi    2
			class    BegLoop
			push    
			pushSelf
			lag      ego
			send     8
			jmp      code_0238
code_00b3:
			dup     
			ldi      2
			eq?     
			bnt      code_0238
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_00ea
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_00ea
			jmp      code_00d4
code_00d4:
			bnt      code_00ea
			pushi    #view
			pushi    1
			pushi    20
			pushi    3
			pushi    1
			lsl      local0
			pushi    4
			pushi    1
			lsl      local1
			lag      ego
			send     18
			jmp      code_00f4
code_00ea:
			pushi    #normalize
			pushi    1
			lsl      local0
			lag      ego
			send     6
code_00f4:
			pTos     register
			dup     
			ldi      78
			eq?     
			bnt      code_0118
			pushi    4
			dup     
			pushi    0
			pushi    255
			pushi    500
			callk    Palette,  8
			pushi    4
			dup     
			pushi    0
			pushi    255
			pushi    100
			callk    Palette,  8
			jmp      code_022e
code_0118:
			dup     
			ldi      76
			eq?     
			bnt      code_016e
			pushi    11
			lsg      curRoomNum
			pushi    230
			pushi    280
			pushi    310
			pushi    390
			pushi    430
			pushi    650
			pushi    770
			pushi    780
			pushi    810
			pushi    853
			calle    OneOf,  22
			not     
			bnt      code_015c
			pushi    #say
			pushi    6
			pushi    0
			pushi    0
			pushi    1
			pushi    1
			pushi    0
			pushi    12
			lag      messager
			send     16
			jmp      code_022e
code_015c:
			pushi    #doVerb
			pushi    1
			pushi    65460
			pushi    2
			lsg      curRoomNum
			pushi    0
			callk    ScriptID,  4
			send     6
			jmp      code_022e
code_016e:
			dup     
			ldi      77
			eq?     
			bnt      code_01c9
			pushi    13
			lsg      curRoomNum
			pushi    230
			pushi    280
			pushi    310
			pushi    400
			pushi    430
			pushi    650
			pushi    700
			pushi    850
			pushi    851
			pushi    852
			pushi    853
			pushi    854
			calle    OneOf,  26
			not     
			bnt      code_01b8
			pushi    #say
			pushi    6
			pushi    0
			pushi    0
			pushi    2
			pushi    1
			pushi    0
			pushi    12
			lag      messager
			send     16
			jmp      code_022e
code_01b8:
			pushi    #doVerb
			pushi    1
			pushi    65459
			pushi    2
			lsg      curRoomNum
			pushi    0
			callk    ScriptID,  4
			send     6
			jmp      code_022e
code_01c9:
			dup     
			ldi      80
			eq?     
			bnt      code_022e
			pushi    17
			lsg      curRoomNum
			pushi    230
			pushi    280
			pushi    310
			pushi    390
			pushi    400
			pushi    430
			pushi    450
			pushi    630
			pushi    650
			pushi    700
			pushi    820
			pushi    850
			pushi    851
			pushi    852
			pushi    853
			pushi    854
			calle    OneOf,  34
			not     
			bnt      code_021f
			pushi    #say
			pushi    6
			pushi    0
			pushi    0
			pushi    4
			pushi    1
			pushi    0
			pushi    12
			lag      messager
			send     16
			jmp      code_022e
code_021f:
			pushi    #doVerb
			pushi    1
			pushi    65456
			pushi    2
			lsg      curRoomNum
			pushi    0
			callk    ScriptID,  4
			send     6
code_022e:
			toss    
			pushi    0
			callb    HandsOn,  0
			pushi    #dispose
			pushi    0
			self     4
code_0238:
			toss    
			ret     
		)
	)
)
