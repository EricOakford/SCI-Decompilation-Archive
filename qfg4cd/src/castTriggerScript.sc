;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11)
(include sci.sh)
(use Main)
(use Array)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	castTriggerScript 0
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
(instance castTriggerScript of Script
	(properties)
	
	(method (dispose)
		(DisposeScript -597)
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
			bnt      code_00a0
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
			sal      local4
			pushi    #with
			pushi    4
			pushi    2
			pushi    3
			pushi    6
			pushi    7
			class    IntArray
			send     12
			sal      local5
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
			sal      local6
			pushi    #handsOff
			pushi    0
			lag      theGame
			send     4
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
			jmp      code_02b8
code_00a0:
			dup     
			ldi      1
			eq?     
			bnt      code_0129
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
			bnt      code_0101
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_0101
			bnt      code_0101
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
			lal      local4
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
			jmp      code_02b8
code_0101:
			pushi    #view
			pushi    1
			pushi    14
			pushi    15
			pushi    1
			pushi    #at
			pushi    1
			lsl      local1
			lal      local6
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
			jmp      code_02b8
code_0129:
			dup     
			ldi      2
			eq?     
			bnt      code_01b5
			pushi    #x
			pushi    1
			lsg      projX
			pushi    2
			pushi    1
			lsg      projY
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
			lofsa    triggerEffect
			send     40
			pToa     register
			bnt      code_0177
			pushi    #onMe
			pushi    2
			lsg      projX
			lsg      projY
			send     8
			bnt      code_0173
			pToa     register
			sal      local0
code_0173:
			ldi      0
			aTop     register
code_0177:
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_0193
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_0193
code_0193:
			not     
			bnt      code_01a3
			pushi    #setCycle
			pushi    2
			class    Beg
			push    
			pushSelf
			lag      ego
			send     8
code_01a3:
			pushi    #number
			pushi    1
			pushi    934
			pushi    51
			pushi    0
			lofsa    soundFX
			send     10
			jmp      code_02b8
code_01b5:
			dup     
			ldi      3
			eq?     
			bnt      code_01cc
			pushi    #dispose
			pushi    0
			lofsa    triggerEffect
			send     4
			ldi      2
			aTop     cycles
			jmp      code_02b8
code_01cc:
			dup     
			ldi      4
			eq?     
			bnt      code_02a2
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_022a
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_022a
			bnt      code_022a
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
			lal      local5
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
			bnt      code_0220
			ldi      4
			jmp      code_0222
code_0220:
			ldi      5
code_0222:
			push    
			lag      ego
			send     18
			jmp      code_0233
code_022a:
			pushi    #normalize
			pushi    0
			lag      ego
			send     4
code_0233:
			pushi    15
			lsg      curRoomNum
			pushi    270
			pushi    290
			pushi    340
			pushi    440
			pushi    460
			pushi    520
			pushi    580
			pushi    593
			pushi    600
			pushi    641
			pushi    643
			pushi    650
			pushi    750
			pushi    800
			calle    OneOf,  30
			not     
			bnt      code_027f
			pushi    #say
			pushi    6
			pushi    0
			pushi    0
			pushi    1
			pushi    0
			pushSelf
			pushi    11
			lag      messager
			send     16
			jmp      code_02b8
code_027f:
			lal      local0
			bnt      code_028e
			pushi    #doVerb
			pushi    1
			pushi    65454
			send     6
			jmp      code_02b8
code_028e:
			pushi    #say
			pushi    6
			pushi    0
			pushi    0
			pushi    1
			pushi    0
			pushSelf
			pushi    11
			lag      messager
			send     16
			jmp      code_02b8
code_02a2:
			dup     
			ldi      5
			eq?     
			bnt      code_02b8
			pushi    #handsOn
			pushi    0
			lag      theGame
			send     4
			pushi    #dispose
			pushi    0
			self     4
code_02b8:
			toss    
			ret     
		)
	)
)

(instance triggerEffect of Prop
	(properties
		view 21
		signal $6001
	)
	
	(method (doit &tmp temp0)
		(if script (script doit:))
		(if cycler (cycler doit:))
		(if local3
			(self dispose:)
		else
			(if scaler (scaler doit:))
			(super doit:)
		)
	)
	
	(method (dispose)
		(if local3 (super dispose:) else (= local3 1))
	)
)

(instance soundFX of Sound
	(properties)
)
