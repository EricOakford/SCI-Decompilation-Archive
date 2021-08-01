;;; Sierra Script 1.0 - (do not remove this comment)
(script# 889)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use User)
(use System)

(public
	rm000Debug 0
)

(procedure (localproc_035e param1 &tmp [temp0 20])
	(= temp0 0)
	(return
		(if (GetInput @temp0 8 param1)
			(return (ReadNumber @temp0))
		else
			(return -1)
		)
	)
)

(instance rm000Debug of Code
	(properties)
	
	(method (doit param1 &tmp temp0 temp1 temp2 temp3 temp4 [temp5 255])
		(asm
			pushi    #message
			pushi    0
			lap      param1
			send     4
			push    
			dup     
			ldi      15872
			eq?     
			bnt      code_0062
			pushi    1
			pushi    4
			callk    Show,  2
			pushi    1
			pushi    #elements
			pushi    0
			lag      cast
			send     4
			push    
			callk    Animate,  2
code_0033:
			pushi    0
			pushi    #type
			pushi    0
			pushi    #new
			pushi    1
			pushi    32765
			class    Event
			send     6
			sap      param1
			send     4
			eq?     
			bnt      code_0053
			pushi    #dispose
			pushi    0
			lap      param1
			send     4
			jmp      code_0033
code_0053:
			pushi    #dispose
			pushi    0
			lap      param1
			send     4
			pushi    1
			pushi    1
			callk    Show,  2
			jmp      code_033b
code_0062:
			dup     
			ldi      16384
			eq?     
			bnt      code_0072
			pushi    1
			pushi    2
			callk    Show,  2
			jmp      code_033b
code_0072:
			dup     
			ldi      17408
			eq?     
			bnt      code_0082
			pushi    1
			pushi    1
			callk    Show,  2
			jmp      code_033b
code_0082:
			dup     
			ldi      11776
			eq?     
			bnt      code_00a8
code_008a:
			pushi    1
			lofsa    {Clear Flag#:_}
			push    
			calle    GetNumber,  2
			sat      temp0
			push    
			ldi      65535
			ne?     
			bt       code_009f
			jmp      code_008a
code_009f:
			pushi    1
			lst      temp0
			callb    Bclr,  2
			jmp      code_033b
code_00a8:
			dup     
			ldi      8448
			eq?     
			bnt      code_00bb
			pushi    #showMem
			pushi    0
			lag      theGame
			send     4
			jmp      code_033b
code_00bb:
			dup     
			ldi      8704
			eq?     
			bnt      code_0124
			ldi      0
			sat      temp5
			pushi    3
			lea      @temp5
			push    
			pushi    8
			lofsa    {From Inv. #}
			push    
			calle    GetInput,  6
			pushi    1
			lea      @temp5
			push    
			callk    ReadNumber,  2
			sat      temp2
			ldi      0
			sat      temp5
			pushi    3
			lea      @temp5
			push    
			pushi    8
			lofsa    {To Inv. #}
			push    
			calle    GetInput,  6
			pushi    1
			lea      @temp5
			push    
			callk    ReadNumber,  2
			sat      temp3
			lat      temp2
			sat      temp0
code_0103:
			lst      temp0
			lat      temp3
			le?     
			bnt      code_033b
			pushi    #moveTo
			pushi    1
			lsg      ego
			pushi    #at
			pushi    1
			lst      temp0
			lag      inventory
			send     6
			send     6
			+at      temp0
			jmp      code_0103
			jmp      code_033b
code_0124:
			dup     
			ldi      5888
			eq?     
			bnt      code_01b6
			pushi    #pause
			pushi    1
			pushi    1
			class    Sound
			send     6
			sat      temp1
			ldi      0
			sat      temp5
			pushi    3
			lea      @temp5
			push    
			pushi    8
			lofsa    {Inv. Object}
			push    
			calle    GetInput,  6
			pushi    1
			lea      @temp5
			push    
			callk    ReadNumber,  2
			sat      temp4
			ldi      0
			sat      temp5
			pushi    3
			lea      @temp5
			push    
			pushi    12
			lofsa    {Owner}
			push    
			calle    GetInput,  6
			pushi    2
			lofsa    {ego}
			push    
			lea      @temp5
			push    
			callk    StrCmp,  4
			not     
			bnt      code_018d
			pushi    #moveTo
			pushi    1
			lsg      ego
			pushi    #at
			pushi    1
			lst      temp4
			lag      inventory
			send     6
			send     6
			jmp      code_01a5
code_018d:
			pushi    #moveTo
			pushi    1
			pushi    1
			lea      @temp5
			push    
			callk    ReadNumber,  2
			push    
			pushi    #at
			pushi    1
			lst      temp4
			lag      inventory
			send     6
			send     6
code_01a5:
			ldi      0
			sat      temp5
			pushi    #pause
			pushi    1
			lst      temp1
			class    Sound
			send     6
			jmp      code_033b
code_01b6:
			dup     
			ldi      9216
			eq?     
			bnt      code_01d4
			pushi    3
			pushi    889
			pushi    0
			pushi    2
			pushi    2
			pushi    174
			callk    DoAudio,  4
			push    
			calle    Printf,  6
			jmp      code_033b
code_01d4:
			dup     
			ldi      4864
			eq?     
			bnt      code_01eb
			pushi    3
			pushi    889
			pushi    1
			lsg      curRoomNum
			calle    Printf,  6
			jmp      code_033b
code_01eb:
			dup     
			ldi      7936
			eq?     
			bnt      code_0211
code_01f3:
			pushi    1
			lofsa    {Set Flag#:_}
			push    
			calle    GetNumber,  2
			sat      temp0
			push    
			ldi      65535
			ne?     
			bt       code_0208
			jmp      code_01f3
code_0208:
			pushi    1
			lst      temp0
			callb    Bset,  2
			jmp      code_033b
code_0211:
			dup     
			ldi      5120
			eq?     
			bnt      code_022d
			pushi    #canControl
			pushi    1
			pushi    1
			class    User
			send     6
			ldi      0
			sag      prevRoomNum
			pushi    0
			callb    DebugTP,  0
			jmp      code_033b
code_022d:
			dup     
			ldi      4352
			eq?     
			bnt      code_023f
			pushi    #doit
			pushi    0
			class    100
			send     4
			jmp      code_033b
code_023f:
			dup     
			ldi      16128
			eq?     
			bnt      code_0263
			pushi    1
			pushi    0
			callb    Btst,  2
			bnt      code_0259
			pushi    1
			pushi    160
			callb    SpeakAudio,  2
			jmp      code_033b
code_0259:
			pushi    #save
			pushi    0
			lag      theGame
			send     4
			jmp      code_033b
code_0263:
			dup     
			ldi      16640
			eq?     
			bnt      code_0287
			pushi    1
			pushi    0
			callb    Btst,  2
			bnt      code_027d
			pushi    1
			pushi    161
			callb    SpeakAudio,  2
			jmp      code_033b
code_027d:
			pushi    #restore
			pushi    0
			lag      theGame
			send     4
			jmp      code_033b
code_0287:
			dup     
			ldi      17152
			eq?     
			bnt      code_02cc
			pushi    #pause
			pushi    1
			pushi    1
			class    Sound
			send     6
			sat      temp1
			pushi    10
			pushi    889
			pushi    2
			pushi    33
			pushi    0
			pushi    81
			lofsa    {__Restart__}
			push    
			pushi    1
			pushi    81
			lofsa    { Continue_}
			push    
			pushi    0
			calle    Print,  20
			bnt      code_02bf
			pushi    #restart
			pushi    0
			lag      theGame
			send     4
code_02bf:
			pushi    #pause
			pushi    1
			lst      temp1
			class    Sound
			send     6
			jmp      code_033b
code_02cc:
			dup     
			ldi      11520
			eq?     
			bnt      code_02db
			ldi      1
			sag      quit
			jmp      code_033b
code_02db:
			dup     
			ldi      5376
			eq?     
			bnt      code_030a
code_02e3:
			pushi    1
			pushi    2
			lea      @temp5
			push    
			lofsa    {Audio number#:}
			push    
			callk    Format,  4
			push    
			call     localproc_035e,  2
			sat      temp0
			push    
			ldi      65535
			ne?     
			bt       code_0301
			jmp      code_02e3
code_0301:
			pushi    1
			lst      temp0
			callb    SpeakAudio,  2
			jmp      code_033b
code_030a:
			dup     
			ldi      11264
			eq?     
			bnt      code_0333
			pushi    10
			pushi    889
			pushi    3
			pushi    33
			pushi    0
			pushi    81
			lofsa    {____Quit____}
			push    
			pushi    1
			pushi    81
			lofsa    { Continue_}
			push    
			pushi    0
			calle    Print,  20
			sag      quit
			jmp      code_033b
code_0333:
			pushi    #claimed
			pushi    1
			pushi    0
			lap      param1
			send     6
code_033b:
			toss    
			ret     
		)
	)
)
