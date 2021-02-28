;;; Sierra Script 1.0 - (do not remove this comment)
(script# CASTOPEN)
(include game.sh)
(use Main)
(use Osc)
(use Motion)
(use Actor)
(use System)

(public
	castOpenScript 0
)

(local
	local0
	[local1 8] = [0 0 0 1 0 0 2 3]
	[local9 4] = [2 3 6 7]
	[local13 8] = [2 3 0 3 0 1 2 3]
	local21
	local22
)
(instance castOpenScript of Script
	(properties)
	
	(method (dispose)
		(= local0 (= register 0))
		(DisposeScript 939)
		(super dispose:)
	)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_004b
			pushi    0
			callb    HandsOff,  0
			pushi    #setHeading
			pushi    2
			pushi    4
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			lsg      projX
			lsg      projY
			callk    GetAngle,  8
			push    
			pushSelf
			lag      ego
			send     8
			jmp      code_0200
code_004b:
			dup     
			ldi      1
			eq?     
			bnt      code_00b3
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			sal      local21
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_0099
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_0099
			jmp      code_0074
code_0074:
			bnt      code_0099
			pushi    #cel
			pushi    0
			lag      ego
			send     4
			sal      local22
			pushi    #view
			pushi    1
			pushi    19
			pushi    3
			pushi    1
			lal      local21
			lsli     local1
			pushi    161
			pushi    2
			class    EndLoop
			push    
			pushSelf
			lag      ego
			send     20
			jmp      code_0200
code_0099:
			pushi    #view
			pushi    1
			pushi    15
			pushi    3
			pushi    1
			lal      local21
			lsli     local13
			pushi    161
			pushi    2
			class    EndLoop
			push    
			pushSelf
			lag      ego
			send     20
			jmp      code_0200
code_00b3:
			dup     
			ldi      2
			eq?     
			bnt      code_0138
			pushi    #x
			pushi    1
			lsg      projX
			pushi    0
			pushi    1
			lsg      projY
			pushi    316
			pushi    2
			pushi    65535
			lsg      ego
			pushi    247
			pushi    1
			pushi    0
			pushi    60
			pushi    1
			pushi    15
			pushi    110
			pushi    0
			pushi    161
			pushi    3
			class    Oscillate
			push    
			pushi    4
			pushSelf
			lofsa    openEffect
			send     46
			pToa     register
			bnt      code_00fb
			pushi    #onMe
			pushi    2
			lsg      projX
			lsg      projY
			send     8
			sal      local0
code_00fb:
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_0115
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_0115
			jmp      code_0115
code_0115:
			not     
			bnt      code_0123
			pushi    #setCycle
			pushi    1
			class    BegLoop
			push    
			lag      ego
			send     6
code_0123:
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
			jmp      code_0200
code_0138:
			dup     
			ldi      3
			eq?     
			bnt      code_014d
			pushi    #dispose
			pushi    0
			lofsa    openEffect
			send     4
			ldi      2
			aTop     cycles
			jmp      code_0200
code_014d:
			dup     
			ldi      4
			eq?     
			bnt      code_0200
			pushi    12
			lsg      curRoomNum
			pushi    230
			pushi    310
			pushi    380
			pushi    430
			pushi    450
			pushi    640
			pushi    650
			pushi    700
			pushi    810
			pushi    820
			pushi    853
			calle    OneOf,  24
			not     
			bnt      code_0194
			pushi    #say
			pushi    6
			pushi    0
			pushi    0
			pushi    1
			pushi    1
			pushi    0
			pushi    13
			lag      messager
			send     16
			jmp      code_01a6
code_0194:
			pToa     register
			bnt      code_01a6
			lal      local0
			bnt      code_01a6
			pushi    #doVerb
			pushi    1
			pushi    65461
			pToa     register
			send     6
code_01a6:
			pushi    0
			callb    HandsOn,  0
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_01f3
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_01f3
			jmp      code_01c4
code_01c4:
			bnt      code_01f3
			pushi    2
			pushi    1
			pushi    20
			pushi    3
			pushi    1
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			lsli     local9
			pushi    4
			pushi    1
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			ldi      6
			lt?     
			bnt      code_01ea
			ldi      4
			jmp      code_01ec
code_01ea:
			ldi      5
code_01ec:
			push    
			lag      ego
			send     18
			jmp      code_01fb
code_01f3:
			pushi    #normalize
			pushi    0
			lag      ego
			send     4
code_01fb:
			pushi    #dispose
			pushi    0
			self     4
code_0200:
			toss    
			ret     
		)
	)
)

(instance openEffect of Prop
	(properties
		view 21
		signal (| ignrAct ignrHrz fixPriOn)
	)
)
