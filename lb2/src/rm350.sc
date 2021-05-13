;;; Sierra Script 1.0 - (do not remove this comment)
(script# 350)
(include sci.sh)
(use Main)
(use LBRoom)
(use ExitFeature)
(use RotundaRgn)
(use Scaler)
(use PolyPath)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use StopWalk)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm350 0
	proc350_2 2
	proc350_23 23
)

(local
	local0
)
(procedure (proc350_2)
	(asm
		+ag      global14336
		ssgi     ego
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    10
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    0
		pushi    153
		pushi    2
		pushi    149
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    12
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
		dup     
		ldi      2
		eq?     
		bnt      code_04d9
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    3
		pushi    153
		pushi    2
		pushi    140
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    1
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    1
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    0
		pushi    153
		pushi    2
		pushi    165
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    12
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_04d9:
		dup     
		ldi      3
		eq?     
		bnt      code_054d
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    3
		pushi    153
		pushi    2
		pushi    140
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    1
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    5
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    0
		pushi    153
		pushi    2
		pushi    165
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    12
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_054d:
		dup     
		ldi      4
		eq?     
		bnt      code_059c
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    5
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    1
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    2
		pushi    153
		pushi    2
		pushi    165
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_059c:
		dup     
		ldi      5
		eq?     
		bnt      code_05eb
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    5
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    1
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    0
		pushi    153
		pushi    2
		pushi    160
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    12
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_05eb:
		dup     
		ldi      6
		eq?     
		bnt      code_063c
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    5
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    4
		pushi    153
		pushi    2
		pushi    165
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    9
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_063c:
		dup     
		ldi      7
		eq?     
		bnt      code_068d
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    5
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    4
		pushi    153
		pushi    2
		pushi    165
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    9
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_068d:
		dup     
		ldi      8
		eq?     
		bnt      code_0701
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    1
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    4
		pushi    153
		pushi    2
		pushi    150
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    9
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    2
		pushi    153
		pushi    2
		pushi    165
		pushi    180
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    11
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_0701:
		dup     
		ldi      9
		eq?     
		bnt      code_077a
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    2
		pushi    153
		pushi    2
		pushi    165
		pushi    179
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    5
		pushi    153
		pushi    2
		pushi    180
		dup     
		pushi    306
		pushi    1
		pushi    170
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    9
		callk    ScriptID,  4
		send     30
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    0
		pushi    153
		pushi    2
		pushi    155
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    11
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_077a:
		dup     
		ldi      10
		eq?     
		bnt      code_07cb
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    5
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    4
		pushi    153
		pushi    2
		pushi    165
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    9
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_07cb:
		dup     
		ldi      11
		eq?     
		bnt      code_081c
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    5
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    4
		pushi    153
		pushi    2
		pushi    165
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    9
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_081c:
		dup     
		ldi      12
		eq?     
		bnt      code_086a
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    1
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    0
		pushi    153
		pushi    2
		pushi    160
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    11
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_086a:
		dup     
		ldi      13
		eq?     
		bnt      code_08b7
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    4
		pushi    153
		pushi    2
		pushi    160
		pushi    181
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    8
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    2
		pushi    153
		pushi    2
		pushi    175
		pushi    183
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    11
		callk    ScriptID,  4
		send     24
code_08b7:
		toss    
		lsg      prevRoomNum
		ldi      340
		eq?     
		bnt      code_08f7
		lsg      curRoomNum
		pushi    #convRoom
		pushi    0
		class    RotundaRgn
		send     4
		eq?     
		bnt      code_08f7
code_08cd:
		pushi    2
		lsg      ego
		pushi    8
		callb    IsObjectOnControl,  4
		bnt      code_08f7
		pushi    1
		pushi    1
		pushi    #x
		pushi    0
		lag      ego
		send     4
		push    
		ldi      1
		sub     
		push    
		pushi    0
		pushi    1
		pushi    #y
		pushi    0
		lag      ego
		send     4
		push    
		ldi      1
		sub     
		push    
		lag      ego
		send     12
		jmp      code_08cd
code_08f7:
		ret     
	)
)

(procedure (proc350_23)
	(asm
		+ag      global14336
		ssgi     ego
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    10
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    0
		pushi    153
		pushi    2
		pushi    149
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    12
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
		dup     
		ldi      2
		eq?     
		bnt      code_04d9
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    3
		pushi    153
		pushi    2
		pushi    140
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    1
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    1
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    0
		pushi    153
		pushi    2
		pushi    165
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    12
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_04d9:
		dup     
		ldi      3
		eq?     
		bnt      code_054d
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    3
		pushi    153
		pushi    2
		pushi    140
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    1
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    5
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    0
		pushi    153
		pushi    2
		pushi    165
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    12
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_054d:
		dup     
		ldi      4
		eq?     
		bnt      code_059c
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    5
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    1
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    2
		pushi    153
		pushi    2
		pushi    165
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_059c:
		dup     
		ldi      5
		eq?     
		bnt      code_05eb
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    5
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    1
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    0
		pushi    153
		pushi    2
		pushi    160
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    12
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_05eb:
		dup     
		ldi      6
		eq?     
		bnt      code_063c
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    5
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    4
		pushi    153
		pushi    2
		pushi    165
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    9
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_063c:
		dup     
		ldi      7
		eq?     
		bnt      code_068d
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    5
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    4
		pushi    153
		pushi    2
		pushi    165
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    9
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_068d:
		dup     
		ldi      8
		eq?     
		bnt      code_0701
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    1
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    4
		pushi    153
		pushi    2
		pushi    150
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    9
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    2
		pushi    153
		pushi    2
		pushi    165
		pushi    180
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    11
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_0701:
		dup     
		ldi      9
		eq?     
		bnt      code_077a
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    2
		pushi    153
		pushi    2
		pushi    165
		pushi    179
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    5
		pushi    153
		pushi    2
		pushi    180
		dup     
		pushi    306
		pushi    1
		pushi    170
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    9
		callk    ScriptID,  4
		send     30
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    0
		pushi    153
		pushi    2
		pushi    155
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    11
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_077a:
		dup     
		ldi      10
		eq?     
		bnt      code_07cb
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    5
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    4
		pushi    153
		pushi    2
		pushi    165
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    9
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_07cb:
		dup     
		ldi      11
		eq?     
		bnt      code_081c
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    5
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    4
		pushi    153
		pushi    2
		pushi    165
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    9
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_081c:
		dup     
		ldi      12
		eq?     
		bnt      code_086a
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    1
		pushi    153
		pushi    2
		pushi    180
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    7
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    0
		pushi    153
		pushi    2
		pushi    160
		pushi    182
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    11
		callk    ScriptID,  4
		send     24
		jmp      code_08b7
code_086a:
		dup     
		ldi      13
		eq?     
		bnt      code_08b7
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    4
		pushi    153
		pushi    2
		pushi    160
		pushi    181
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    8
		callk    ScriptID,  4
		send     24
		pushi    #setLoop
		pushi    1
		pushi    8
		pushi    156
		pushi    1
		pushi    2
		pushi    153
		pushi    2
		pushi    175
		pushi    183
		pushi    304
		pushi    0
		pushi    2
		pushi    93
		pushi    11
		callk    ScriptID,  4
		send     24
code_08b7:
		toss    
		lsg      prevRoomNum
		ldi      340
		eq?     
		bnt      code_08f7
		lsg      curRoomNum
		pushi    #convRoom
		pushi    0
		class    RotundaRgn
		send     4
		eq?     
		bnt      code_08f7
code_08cd:
		pushi    2
		lsg      ego
		pushi    8
		callb    IsObjectOnControl,  4
		bnt      code_08f7
		pushi    1
		pushi    1
		pushi    #x
		pushi    0
		lag      ego
		send     4
		push    
		ldi      1
		sub     
		push    
		pushi    0
		pushi    1
		pushi    #y
		pushi    0
		lag      ego
		send     4
		push    
		ldi      1
		sub     
		push    
		lag      ego
		send     12
		jmp      code_08cd
code_08f7:
		ret     
	)
)

(instance rm350 of LBRoom
	(properties
		noun 5
		picture 350
		north 420
		east 370
		south 335
		west 360
	)
	
	(method (init &tmp [temp0 5])
		(ego init: normalize: 831 setScale: Scaler 95 0 190 0)
		(if (== currentAct 5) (LoadMany 128 424))
		(if (> currentAct 2)
			(self setRegions: 90)
		else
			(self setRegions: 93)
			((ScriptID 2350 0) doit: (= obstacles (List new:)))
		)
		(switch prevRoomNum
			(north
				(if (== currentAct 5)
					(ego
						x: 275
						y: 100
						setSpeed: 6
						setStep: 2 1
						setHeading: 180
					)
					(self setScript: sLauraDies)
				else
					(ego posn: 270 100 edgeHit: 0 setHeading: 180)
				)
			)
			(south
				(self setScript: sEnterSouth)
			)
			(330
				(self setScript: sEnterSouth 0 1)
			)
			(east (= style 11))
			(west (= style 12))
			(340 (= style 9))
			(else 
				(ego posn: 70 175)
				(= currentAct 2)
				(theGame handsOn:)
			)
		)
		(super init:)
		(if (not script) (theGame handsOn:))
		(southExitFeature init:)
		(eastExitFeature init:)
		(westExitFeature init:)
		(tables setOnMeCheck: 1 4096 init:)
		(waterGlass1 init: approachVerbs: 4)
		(waterGlass2 init: approachVerbs: 4)
		(waterGlass3 init: approachVerbs: 4)
		(tutHead setOnMeCheck: 1 16384 init:)
		(plants setOnMeCheck: 1 8192 init:)
		(arch init:)
		(column1 init:)
		(column2 init:)
		(if (== currentAct 2)
			(partyATP1 init: setScale: 170 addToPic:)
			(partyATP2 init: setScale: 170 addToPic:)
			(partyATP3 init: setScale: 170 addToPic:)
			(partyATP4 init: setScale: 170 addToPic:)
		)
		(cond 
			((not (== currentAct 2)))
			(
				(and
					(TriggerEvent 29188)
					(not (TriggerEvent 8))
					(> (ego y?) 175)
					(not (OneOf global128 0 1 4 5 9 13))
				)
				(self setScript: sMeetSteve)
			)
			(else
				(switch global128
					(0
						((ScriptID 93 6)
							setLoop: 8
							setCel: 4
							posn: 158 179
							addToPic:
						)
						((ScriptID 93 7)
							setLoop: 8
							setCel: 5
							posn: 180 179
							addToPic:
						)
						((ScriptID 93 10)
							setLoop: 8
							setCel: 1
							posn: 192 180
							addToPic:
						)
						((ScriptID 93 11)
							setLoop: 8
							setCel: 2
							posn: 166 180
							addToPic:
						)
						((ScriptID 93 12)
							setLoop: 8
							setCel: 0
							posn: 149 182
							addToPic:
						)
					)
					(1
						((ScriptID 93 6)
							setLoop: 8
							setCel: 4
							posn: 158 180
							addToPic:
						)
						((ScriptID 93 7)
							setLoop: 8
							setCel: 5
							posn: 180 179
							addToPic:
						)
						((ScriptID 93 10)
							setLoop: 8
							setCel: 1
							posn: 192 185
							addToPic:
						)
						((ScriptID 93 12)
							setLoop: 8
							setCel: 0
							posn: 149 182
							addToPic:
						)
					)
					(2
						((ScriptID 93 1)
							setLoop: 8
							setCel: 3
							posn: 140 182
							addToPic:
						)
						((ScriptID 93 7)
							setLoop: 8
							setCel: 1
							posn: 180 182
							addToPic:
						)
						((ScriptID 93 12)
							setLoop: 8
							setCel: 0
							posn: 165 182
							addToPic:
						)
					)
					(3
						((ScriptID 93 1)
							setLoop: 8
							setCel: 3
							posn: 140 182
							addToPic:
						)
						((ScriptID 93 7)
							setLoop: 8
							setCel: 5
							posn: 180 182
							addToPic:
						)
						((ScriptID 93 12)
							setLoop: 8
							setCel: 0
							posn: 165 182
							addToPic:
						)
					)
					(4
						((ScriptID 93 1)
							setLoop: 8
							setCel: 5
							posn: 180 182
							addToPic:
						)
						((ScriptID 93 7)
							setLoop: 8
							setCel: 2
							posn: 165 182
							addToPic:
						)
					)
					(5
						((ScriptID 93 1)
							setLoop: 8
							setCel: 5
							posn: 180 182
							addToPic:
						)
						((ScriptID 93 12)
							setLoop: 8
							setCel: 0
							posn: 160 182
							addToPic:
						)
					)
					(6
						((ScriptID 93 7)
							setLoop: 8
							setCel: 5
							posn: 180 182
							addToPic:
						)
						((ScriptID 93 9)
							setLoop: 8
							setCel: 4
							posn: 165 182
							addToPic:
						)
					)
					(7
						((ScriptID 93 7)
							setLoop: 8
							setCel: 5
							posn: 180 182
							addToPic:
						)
						((ScriptID 93 9)
							setLoop: 8
							setCel: 4
							posn: 165 182
							addToPic:
						)
					)
					(8
						((ScriptID 93 7)
							setLoop: 8
							setCel: 1
							posn: 180 182
							addToPic:
						)
						((ScriptID 93 9)
							setLoop: 8
							setCel: 4
							posn: 150 182
							addToPic:
						)
						((ScriptID 93 11)
							setLoop: 8
							setCel: 2
							posn: 165 180
							addToPic:
						)
					)
					(9
						((ScriptID 93 7)
							setLoop: 8
							setCel: 2
							posn: 165 179
							addToPic:
						)
						((ScriptID 93 9)
							setLoop: 8
							setCel: 5
							posn: 180 180
							setScale: 170
							addToPic:
						)
						((ScriptID 93 11)
							setLoop: 8
							setCel: 0
							posn: 155 182
							addToPic:
						)
					)
					(10
						((ScriptID 93 7)
							setLoop: 8
							setCel: 5
							posn: 180 182
							addToPic:
						)
						((ScriptID 93 9)
							setLoop: 8
							setCel: 4
							posn: 165 182
							addToPic:
						)
					)
					(11
						((ScriptID 93 7)
							setLoop: 8
							setCel: 5
							posn: 180 182
							addToPic:
						)
						((ScriptID 93 9)
							setLoop: 8
							setCel: 4
							posn: 165 182
							addToPic:
						)
					)
					(12
						((ScriptID 93 7)
							setLoop: 8
							setCel: 1
							posn: 180 182
							addToPic:
						)
						((ScriptID 93 11)
							setLoop: 8
							setCel: 0
							posn: 160 182
							addToPic:
						)
					)
					(13
						((ScriptID 93 8)
							setLoop: 8
							setCel: 4
							posn: 160 181
							addToPic:
						)
						((ScriptID 93 11)
							setLoop: 8
							setCel: 2
							posn: 175 183
							addToPic:
						)
					)
				)
			)
		)
		(if
			(and
				(== prevRoomNum 340)
				(== curRoomNum (RotundaRgn convRoom?))
			)
			(while (IsObjectOnControl ego 8)
				(ego x: (- (ego x?) 1) y: (- (ego y?) 1))
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			(
				(and
					(== currentAct 2)
					(IsObjectOnControl ego 8)
					(== curRoomNum (RotundaRgn convRoom?))
				)
				(RotundaRgn notify:)
			)
			((IsObjectOnControl ego 2) (self setScript: sExitNorth))
			(
			(and (IsObjectOnControl ego 4) (not (== currentAct 2))) (self setScript: sCantLeave))
		)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 2350)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(messager
				say: noun theVerb (if (> currentAct 2) 5 else 4)
			)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (newRoom n)
		(if (== n south) (DrawPic 780 dpOPEN_FADEPALETTE))
		(super newRoom: n)
	)
)

(instance sEnterSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 25)
				(if (== (theMusic number?) 335)
					(theMusic fade: 127 5 5 0)
				else
					(theMusic number: 335 flags: 1 setLoop: -1 play:)
				)
				(ego
					edgeHit: 0
					posn: 100 225
					setMotion: PolyPath 100 180 self
				)
				(if register
					((ScriptID 93 8)
						init:
						posn: 110 230
						setCycle: StopWalk -1
						setMotion: PolyPath 110 180 self
					)
				else
					(= cycles 1)
				)
			)
			(1 0)
			(2
				(if register
					(ego setSpeed: (= global369 global149))
					((ScriptID 93 8)
						illegalBits: 0
						setMotion: PolyPath 330 180 self
					)
				else
					(= cycles 1)
				)
			)
			(3
				(if register ((ScriptID 93 8) dispose:))
				(theGame handsOn:)
				(ego normalize: 831 setScale: Scaler 95 0 190 0)
				(self dispose:)
			)
		)
	)
)

(instance sExitNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 265 (- (ego y?) 15) self)
			)
			(1
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance sCantLeave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo (ego x?) 180 self)
			)
			(1
				(if (not (Bset 70)) (messager say: 12 1 2))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sMeetSteve of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< (ego x?) 160)
					((ScriptID 93 8)
						setCycle: StopWalk -1
						posn: 240 180
						loop: 1
						init:
						setMotion: PolyPath 180 180 self
					)
				else
					((ScriptID 93 8)
						setCycle: StopWalk -1
						posn: 80 180
						loop: 0
						init:
						setMotion: PolyPath 145 180 self
					)
				)
				(= cycles 2)
			)
			(1
				(theGame handsOff:)
				(= global149 global369)
				(ego setSpeed: (= global369 6))
				(if (< (ego x?) 160)
					(ego setMotion: PolyPath 145 180 self)
				else
					(ego setMotion: PolyPath 180 180 self)
				)
			)
			(2 0)
			(3
				(ego setStep: 3 2)
				(= cycles 2)
			)
			(4
				(messager say: 15 0 0 0 self)
			)
			(5
				(ego setScale: 0 setMotion: MoveTo (ego x?) 240)
				(= ticks 120)
			)
			(6
				((ScriptID 93 8)
					setStep: 3 2
					setSpeed: 6
					setMotion: MoveTo ((ScriptID 93 8) x?) 250 self
				)
			)
			(7
				((ScriptID 22 0) doit: 8)
				(curRoom newRoom: 335)
			)
		)
	)
)

(instance sGetWaterGlass of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 855
					setScale: Scaler 100 100 190 0
					setCel: 0
					setLoop: (if (== register waterGlass3) 0 else 1)
					cycleSpeed: 12
					setCycle: CT 5 1 self
				)
			)
			(1
				(ego
					get: 28
					normalize: 831
					setScale: Scaler 95 0 190 0
					loop: (if (== register waterGlass3) 6 else 7)
				)
				((ScriptID 21 0) doit: 797)
				(self dispose:)
			)
		)
	)
)

(instance sLauraDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(ego setMotion: MoveFwd 80)
				(oriley
					init:
					posn: 270 85
					setScale: 165
					setHeading: 180
					setCycle: StopWalk -1
					setMotion: MoveFwd 20 self
				)
				(theMusic number: 3 flags: 1 loop: 1 play:)
			)
			(2
				(oriley view: 424 setLoop: 0 setCel: 0 setCycle: End self)
			)
			(3
				(thudSound play:)
				(ego setMotion: 0 view: 858 setLoop: 4 setCycle: End self)
			)
			(4 (= ticks 60))
			(5
				(= deathReason 0)
				(curRoom newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance oriley of Actor
	(properties
		view 423
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsLeft 6
		nsBottom 189
		nsRight 314
		cursor 11
		exitDir 3
		noun 12
	)
)

(instance eastExitFeature of ExitFeature
	(properties
		nsTop 94
		nsLeft 315
		nsBottom 189
		nsRight 320
		cursor 14
		exitDir 2
		noun 14
	)
)

(instance westExitFeature of ExitFeature
	(properties
		nsTop 94
		nsBottom 189
		nsRight 5
		cursor 12
		exitDir 4
		noun 13
	)
)

(instance tables of Feature
	(properties
		y 152
		noun 1
		sightAngle 40
	)
)

(instance waterGlass1 of Feature
	(properties
		x 42
		y 153
		noun 8
		nsTop 132
		nsLeft 30
		nsBottom 142
		nsRight 54
		sightAngle 40
		approachX 61
		approachY 168
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (ego has: 28)
					(messager say: noun theVerb 3)
				else
					(curRoom setScript: sGetWaterGlass 0 self)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance waterGlass2 of Feature
	(properties
		x 211
		y 153
		noun 8
		nsTop 147
		nsLeft 206
		nsBottom 157
		nsRight 217
		sightAngle 40
		approachX 227
		approachY 174
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (ego has: 28)
					(messager say: noun theVerb 3)
				else
					(curRoom setScript: sGetWaterGlass 0 self)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance waterGlass3 of Feature
	(properties
		x 110
		y 153
		noun 8
		nsTop 141
		nsLeft 104
		nsBottom 152
		nsRight 116
		sightAngle 40
		approachX 90
		approachY 170
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (ego has: 28)
					(messager say: noun theVerb 3)
				else
					(curRoom setScript: sGetWaterGlass 0 self)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance tutHead of Feature
	(properties
		x 160
		y 100
		noun 2
		sightAngle 40
	)
)

(instance plants of Feature
	(properties
		x 100
		y 100
		noun 3
	)
)

(instance arch of Feature
	(properties
		x 268
		y 72
		noun 6
		nsTop 56
		nsLeft 255
		nsBottom 89
		nsRight 281
		sightAngle 40
	)
)

(instance column1 of Feature
	(properties
		x 27
		y 68
		noun 4
		nsTop 50
		nsLeft 24
		nsBottom 87
		nsRight 31
		sightAngle 40
	)
)

(instance column2 of Feature
	(properties
		x 289
		y 71
		noun 4
		nsTop 53
		nsLeft 286
		nsBottom 89
		nsRight 293
		sightAngle 40
	)
)

(instance partyATP1 of View
	(properties
		x 287
		y 151
		noun 7
		view 374
		cel 15
		signal $4000
	)
	
	(method (init)
		(super init:)
		(self addToPic:)
	)
)

(instance partyATP2 of View
	(properties
		x 271
		y 145
		noun 7
		view 374
		signal $4000
	)
	
	(method (init)
		(= cel
			(switch (Random 0 8)
				(0 6)
				(1 9)
				(2 10)
				(3 11)
				(4 13)
				(5 17)
				(6 18)
				(7 20)
				(8 30)
			)
		)
		(= loop (/ cel 16))
		(= cel (mod cel 16))
		(super init:)
		(self addToPic:)
	)
)

(instance partyATP3 of View
	(properties
		x 50
		y 130
		noun 7
		view 374
		signal $4000
	)
	
	(method (init)
		(= cel
			(switch (Random 0 5)
				(0 3)
				(1 4)
				(2 19)
				(3 25)
				(4 27)
				(5 31)
			)
		)
		(= loop (/ cel 16))
		(= cel (mod cel 16))
		(super init:)
		(self addToPic:)
	)
)

(instance partyATP4 of View
	(properties
		x 28
		y 131
		noun 7
		view 374
		signal $4000
	)
	
	(method (init)
		(= cel
			(switch (Random 0 4)
				(0 2)
				(1 7)
				(2 24)
				(3 26)
				(4 29)
			)
		)
		(= loop (/ cel 16))
		(= cel (mod cel 16))
		(super init:)
		(self addToPic:)
	)
)

(instance thudSound of Sound
	(properties
		flags $0001
		number 80
	)
)
