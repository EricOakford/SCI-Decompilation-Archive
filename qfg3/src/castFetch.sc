;;; Sierra Script 1.0 - (do not remove this comment)
(script# CASTFETCH) ;37
(include game.sh) (include "37.shm")
(use Main)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	castFetchScript 0
)

(local
	local0
	local1
	local2
	local3 = [30 -30 20 -20]
	local7 = [-45 -45 -50 -50]
	local11 = [0 1 0 2 0 1 2 3]
	local19 = [0 1 1 2 0 1 2 3]
	local27 = [2 3 6 7]
)
(instance castFetchScript of Script
	(method (init what &tmp i)
		(FindTarget
			((User curEvent?) x?)
			((User curEvent?) y?)
		)
		(if (>= argc 3)
			(= register (Collection new:))
			(for ((= i 2)) (< i argc) ((++ i))
				(if (IsObject [what i])
					(register add: [what i])
				)
			)
		)
		(super init: [what 0] [what 1])
	)
	
	(method (dispose)
		(if (IsObject register)
			(register release: dispose:)
		)
		(super dispose:)
	)
	
	(method (changeState newState &tmp temp0 temp1)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_00be
			pushi    0
			callb    HandsOff,  0
			pushi    #setMotion
			pushi    1
			pushi    0
			lag      ego
			send     6
			ldi      5
			aTop     cycles
			pushi    #cel
			pushi    0
			lag      ego
			send     4
			sal      local0
			jmp      code_03a7
code_00be:
			dup     
			ldi      1
			eq?     
			bnt      code_00ea
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
			jmp      code_03a7
code_00ea:
			dup     
			ldi      2
			eq?     
			bnt      code_015b
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			sal      local1
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_013c
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_013c
			jmp      code_0113
code_0113:
			bnt      code_013c
			pushi    #view
			pushi    1
			pushi    18
			pushi    3
			pushi    1
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			lsli     local19
			pushi    4
			pushi    1
			pushi    0
			pushi    161
			pushi    4
			class    CycleTo
			push    
			pushi    6
			pushi    1
			pushSelf
			lag      ego
			send     30
			jmp      code_03a7
code_013c:
			pushi    #view
			pushi    1
			pushi    14
			pushi    3
			pushi    1
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			lsli     local11
			pushi    161
			pushi    2
			class    EndLoop
			push    
			pushSelf
			lag      ego
			send     20
			jmp      code_03a7
code_015b:
			dup     
			ldi      3
			eq?     
			bnt      code_0233
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_018c
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_018c
			jmp      code_017c
code_017c:
			bnt      code_018c
			pushi    #setCycle
			pushi    3
			class    CycleTo
			push    
			pushi    0
			pushi    1
			lag      ego
			send     10
code_018c:
			pushi    2
			pushi    1
			pushi    21
			pushi    155
			pushi    1
			pushi    7
			pushi    #x
			pushi    1
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #maxScale
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			lali     local3
			mul     
			push    
			ldi      128
			div     
			add     
			push    
			pushi    0
			pushi    1
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #maxScale
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			lali     local7
			mul     
			push    
			ldi      128
			div     
			add     
			push    
			pushi    334
			pushi    2
			pushi    12
			dup     
			pushi    312
			pushi    0
			pushi    15
			pushi    1
			pushi    0
			pushi    326
			pushi    0
			pushi    110
			pushi    0
			pushi    316
			pushi    2
			pushi    65535
			lsg      ego
			pushi    161
			pushi    1
			class    Forward
			push    
			pushi    307
			pushi    4
			class    MoveTo
			push    
			lsg      projX
			lsg      projY
			pushSelf
			pushi    63
			pushi    1
			pushi    14
			pushi    #new
			pushi    0
			class    Actor
			send     4
			sal      local2
			send     82
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
			jmp      code_03a7
code_0233:
			dup     
			ldi      4
			eq?     
			bnt      code_0288
			pushi    1
			pTos     register
			callk    IsObject,  2
			bnt      code_0274
			pushi    #firstTrue
			pushi    2
			pushi    219
			lsl      local2
			pToa     register
			send     8
			sat      temp0
			bnt      code_0260
			pushi    #doVerb
			pushi    1
			pushi    65454
			send     6
			ldi      5
			aTop     cycles
			jmp      code_03a7
code_0260:
			pushi    #say
			pushi    6
			pushi    1
			pushi    6
			pushi    1
			pushi    0
			pushSelf
			pushi    37
			lag      messager
			send     16
			jmp      code_03a7
code_0274:
			pushi    #say
			pushi    6
			pushi    1
			pushi    6
			pushi    1
			pushi    0
			pushSelf
			pushi    37
			lag      messager
			send     16
			jmp      code_03a7
code_0288:
			dup     
			ldi      5
			eq?     
			bnt      code_02de
			pushi    307
			pushi    4
			class    MoveTo
			push    
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #maxScale
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			lali     local3
			mul     
			push    
			ldi      128
			div     
			add     
			push    
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #maxScale
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			lali     local7
			mul     
			push    
			ldi      128
			div     
			add     
			push    
			pushSelf
			lal      local2
			send     12
			jmp      code_03a7
code_02de:
			dup     
			ldi      6
			eq?     
			bnt      code_031f
			pushi    #dispose
			pushi    0
			lal      local2
			send     4
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_0310
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_0310
			jmp      code_0305
code_0305:
			bnt      code_0310
			pushi    #cue
			pushi    0
			self     4
			jmp      code_03a7
code_0310:
			pushi    #setCycle
			pushi    2
			class    BegLoop
			push    
			pushSelf
			lag      ego
			send     8
			jmp      code_03a7
code_031f:
			dup     
			ldi      7
			eq?     
			bnt      code_036c
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_035c
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_035c
			jmp      code_033f
code_033f:
			bnt      code_035c
			pushi    #view
			pushi    1
			pushi    20
			pushi    4
			pushi    1
			lsl      local0
			pushi    3
			pushi    1
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			lsli     local27
			lag      ego
			send     18
			jmp      code_0366
code_035c:
			pushi    #normalize
			pushi    1
			lsl      local1
			lag      ego
			send     6
code_0366:
			ldi      2
			aTop     cycles
			jmp      code_03a7
code_036c:
			dup     
			ldi      8
			eq?     
			bnt      code_03a7
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_039e
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_039e
			jmp      code_038c
code_038c:
			bnt      code_039e
			pushi    #cel
			pushi    1
			pushi    #lastCel
			pushi    0
			lag      ego
			send     4
			push    
			lag      ego
			send     6
code_039e:
			pushi    0
			callb    HandsOn,  0
			pushi    #dispose
			pushi    0
			self     4
code_03a7:
			toss    
			ret     
		)
	)
)
