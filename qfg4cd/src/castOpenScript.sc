;;; Sierra Script 1.0 - (do not remove this comment)
(script# 13)
(include sci.sh)
(use Main)
(use Array)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	castOpenScript 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
)
(instance castOpenScript of Script
	(properties)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_00ab
			pushi    #with
			pushi    8
			pushi    0
			pushi    0
			pushi    0
			pushi    1
			pushi    0
			pushi    0
			pushi    2
			pushi    3
			class    IntArray
			send     20
			sal      local3
			pushi    #with
			pushi    4
			pushi    2
			pushi    3
			pushi    6
			pushi    7
			class    IntArray
			send     12
			sal      local4
			pushi    #with
			pushi    8
			pushi    2
			pushi    3
			pushi    0
			pushi    3
			pushi    0
			pushi    1
			pushi    2
			pushi    3
			class    IntArray
			send     20
			sal      local5
			pushi    #handsOff
			pushi    0
			lag      theGame
			send     4
			pushi    273
			pushi    2
			pushi    #scaleX
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
			pushi    #scaleY
			pushi    0
			lag      ego
			send     4
			push    
			ldi      3
			div     
			sub     
			push    
			lsg      projX
			lsg      projY
			callk    GetAngle,  8
			push    
			pushSelf
			lag      ego
			send     8
			jmp      code_0368
code_00ab:
			dup     
			ldi      1
			eq?     
			bnt      code_0134
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
			bnt      code_010c
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_010c
			bnt      code_010c
			pushi    #cel
			pushi    0
			lag      ego
			send     4
			sal      local2
			pushi    #view
			pushi    1
			pushi    19
			pushi    15
			pushi    1
			pushi    #at
			pushi    1
			lsl      local1
			lal      local3
			send     6
			push    
			pushi    333
			pushi    1
			pushi    0
			pushi    236
			pushi    2
			class    End
			push    
			pushSelf
			lag      ego
			send     26
			jmp      code_0368
code_010c:
			pushi    #view
			pushi    1
			pushi    14
			pushi    15
			pushi    1
			pushi    #at
			pushi    1
			lsl      local1
			lal      local5
			send     6
			push    
			pushi    333
			pushi    1
			pushi    0
			pushi    236
			pushi    2
			class    End
			push    
			pushSelf
			lag      ego
			send     26
			jmp      code_0368
code_0134:
			dup     
			ldi      2
			eq?     
			bnt      code_01d8
			pushi    #view
			pushi    1
			pushi    21
			pushi    1
			pushi    1
			lsg      projX
			pushi    2
			pushi    1
			lsg      projY
			pushi    254
			pushi    1
			pushi    0
			pushi    333
			pushi    1
			pushi    0
			pushi    26
			pushi    1
			pushi    24577
			pushi    342
			pushi    1
			lsg      ego
			pushi    265
			pushi    1
			pushi    0
			pushi    74
			pushi    1
			pushi    180
			pushi    147
			pushi    0
			pushi    236
			pushi    1
			class    Fwd
			push    
			lofsa    openEffect
			send     64
			pToa     register
			bnt      code_0197
			pushi    #onMe
			pushi    2
			lsg      projX
			lsg      projY
			send     8
			bnt      code_0193
			pToa     register
			sal      local0
code_0193:
			ldi      0
			aTop     register
code_0197:
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_01b3
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_01b3
code_01b3:
			not     
			bnt      code_01c2
			pushi    #setCycle
			pushi    1
			class    Beg
			push    
			lag      ego
			send     6
code_01c2:
			pushi    #number
			pushi    1
			pushi    934
			pushi    51
			pushi    0
			lofsa    soundFX
			send     10
			ldi      3
			aTop     seconds
			jmp      code_0368
code_01d8:
			dup     
			ldi      3
			eq?     
			bnt      code_01ef
			pushi    #dispose
			pushi    0
			lofsa    openEffect
			send     4
			ldi      2
			aTop     cycles
			jmp      code_0368
code_01ef:
			dup     
			ldi      4
			eq?     
			bnt      code_0337
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_024d
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_024d
			bnt      code_024d
			pushi    14
			pushi    1
			pushi    20
			pushi    15
			pushi    1
			pushi    #at
			pushi    1
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			lal      local4
			send     6
			push    
			pushi    16
			pushi    1
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			ldi      6
			lt?     
			bnt      code_0243
			ldi      4
			jmp      code_0245
code_0243:
			ldi      5
code_0245:
			push    
			lag      ego
			send     18
			jmp      code_0258
code_024d:
			pushi    #normalize
			pushi    1
			lsl      local1
			lag      ego
			send     6
code_0258:
			pushi    43
			lsg      curRoomNum
			pushi    250
			pushi    260
			pushi    270
			pushi    340
			pushi    440
			pushi    500
			pushi    510
			pushi    593
			pushi    641
			pushi    643
			pushi    660
			pushi    610
			pushi    662
			pushi    670
			pushi    600
			pushi    661
			pushi    662
			pushi    780
			pushi    460
			pushi    470
			pushi    790
			pushi    620
			pushi    621
			pushi    622
			pushi    623
			pushi    624
			pushi    625
			pushi    626
			pushi    627
			pushi    629
			pushi    630
			pushi    631
			pushi    632
			pushi    640
			pushi    642
			pushi    644
			pushi    633
			pushi    634
			pushi    645
			pushi    663
			pushi    680
			pushi    300
			calle    OneOf,  86
			not     
			bnt      code_02f9
			pushi    #say
			pushi    6
			pushi    0
			pushi    0
			pushi    1
			pushi    0
			pushSelf
			pushi    13
			lag      messager
			send     16
			jmp      code_0368
code_02f9:
			lal      local0
			bnt      code_0323
			pushi    #doVerb
			pushi    1
			pushi    65456
			send     6
			pushi    #dispose
			pushi    0
			lal      local3
			send     4
			pushi    #dispose
			pushi    0
			lal      local4
			send     4
			pushi    #dispose
			pushi    0
			lal      local5
			send     4
			jmp      code_0368
code_0323:
			pushi    #say
			pushi    6
			pushi    0
			pushi    0
			pushi    1
			pushi    0
			pushSelf
			pushi    13
			lag      messager
			send     16
			jmp      code_0368
code_0337:
			dup     
			ldi      5
			eq?     
			bnt      code_0368
			pushi    #dispose
			pushi    0
			lal      local3
			send     4
			pushi    #dispose
			pushi    0
			lal      local4
			send     4
			pushi    #dispose
			pushi    0
			lal      local5
			send     4
			pushi    #handsOn
			pushi    0
			lag      theGame
			send     4
			pushi    #dispose
			pushi    0
			self     4
code_0368:
			toss    
			ret     
		)
	)
)

(instance openEffect of Prop
	(properties
		view 21
		signal $6001
	)
	
	(method (doit &tmp temp0)
		(if script (script doit:))
		(if cycler (cycler doit:))
		(if local6
			(self dispose:)
		else
			(if scaler (scaler doit:))
			(super doit:)
		)
	)
	
	(method (dispose)
		(if local6 (super dispose:) else (= local6 1))
	)
)

(instance soundFX of Sound
	(properties)
)
