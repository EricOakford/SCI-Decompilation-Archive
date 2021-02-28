;;; Sierra Script 1.0 - (do not remove this comment)
(script# CASTJUGGLE) ;62
(include game.sh)
(use Main)
(use Motion)
(use Actor)
(use System)

(public
	castJuggle 0
)

(local
	local0
	local1
	local2
)
(procedure (LockIcons &tmp i n)
	(= n -32768)
	(= i 0)
	(while (<= i 10)
		(if (& disabledIcons n) (theIconBar disable: i))
		(= n (>> n $0001))
		(++ i)
	)
)

(instance castJuggle of Script
	
	(method (dispose)
		(Bset fInMainGame)
		(HandsOn)
		(LockIcons)
		(super dispose:)
		(DisposeScript 62)
	)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_00b9
			pushi    0
			callb    HandsOff,  0
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_0082
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_0082
			jmp      code_0072
code_0072:
			bnt      code_0082
			pushi    #setScript
			pushi    2
			lofsa    faceEgo
			push    
			pushSelf
			self     8
			jmp      code_0332
code_0082:
			pushi    #setMotion
			pushi    1
			pushi    0
			lag      ego
			send     6
			pushi    3
			lsg      ego
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      5
			add     
			push    
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      5
			add     
			push    
			callb    Face,  6
			pushi    #cycleSpeed
			pushi    0
			lag      ego
			send     4
			push    
			ldi      5
			add     
			aTop     cycles
			jmp      code_0332
code_00b9:
			dup     
			ldi      1
			eq?     
			bnt      code_0120
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
			bnt      code_0109
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_0109
			jmp      code_00eb
code_00eb:
			bnt      code_0109
			pushi    #view
			pushi    1
			pushi    19
			pushi    3
			pushi    1
			pushi    5
			pushi    4
			pushi    1
			pushi    0
			pushi    161
			pushi    2
			class    EndLoop
			push    
			pushSelf
			lag      ego
			send     26
			jmp      code_0332
code_0109:
			pushi    #view
			pushi    1
			pushi    15
			pushi    3
			pushi    1
			pushi    0
			pushi    161
			pushi    2
			class    EndLoop
			push    
			pushSelf
			lag      ego
			send     20
			jmp      code_0332
code_0120:
			dup     
			ldi      2
			eq?     
			bnt      code_015f
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_014b
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_014b
			jmp      code_0140
code_0140:
			bnt      code_014b
			pushi    #cue
			pushi    0
			self     4
			jmp      code_0332
code_014b:
			pushi    #setCycle
			pushi    4
			class    CycleTo
			push    
			pushi    4
			pushi    65535
			pushSelf
			lag      ego
			send     12
			jmp      code_0332
code_015f:
			dup     
			ldi      3
			eq?     
			bnt      code_0200
			pushi    2
			pushi    #x
			pushi    111
			pushi    3
			pushi    1
			pushi    0
			pushi    4
			pushi    1
			pushi    0
			pushi    1
			pushi    1
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    0
			pushi    1
			pushi    #nsTop
			pushi    0
			lag      ego
			send     4
			push    
			pushi    60
			pushi    1
			pushi    #priority
			pushi    0
			lag      ego
			send     4
			push    
			ldi      1
			add     
			push    
			pushi    14
			pushi    1
			pushi    16
			pushi    110
			pushi    0
			pushi    316
			pushi    0
			pushi    104
			pushi    1
			pushi    #scaleX
			pushi    0
			lag      ego
			send     4
			push    
			pushi    105
			pushi    1
			pushi    #scaleY
			pushi    0
			lag      ego
			send     4
			push    
			pushi    161
			pushi    1
			class    Forward
			push    
			pushi    #new
			pushi    0
			class    Prop
			send     4
			sal      local1
			send     68
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_01ed
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_01ed
			jmp      code_01e2
code_01e2:
			bnt      code_01ed
			pushi    #cue
			pushi    0
			self     4
			jmp      code_0332
code_01ed:
			pushi    #setCycle
			pushi    4
			class    CycleTo
			push    
			pushi    2
			pushi    65535
			pushSelf
			lag      ego
			send     12
			jmp      code_0332
code_0200:
			dup     
			ldi      4
			eq?     
			bnt      code_025a
			pushi    1
			pushi    6
			callb    Bclr,  2
			pushi    1
			pushi    2
			callk    PalVary,  2
			sal      local0
			push    
			ldi      0
			eq?     
			bnt      code_0223
			ldi      0
			sal      local2
			ldi      2
			aTop     seconds
			jmp      code_0253
code_0223:
			lsl      local0
			ldi      64
			eq?     
			bnt      code_0236
			ldi      1
			sal      local2
			pushi    2
			pushi    1
			pushi    1
			callk    PalVary,  4
			jmp      code_0253
code_0236:
			lsl      local0
			ldi      0
			gt?     
			bnt      code_0249
			ldi      2
			sal      local2
			pushi    2
			pushi    1
			pushi    1
			callk    PalVary,  4
			jmp      code_0253
code_0249:
			ldi      3
			sal      local2
			pushi    2
			pushi    1
			pushi    1
			callk    PalVary,  4
code_0253:
			ldi      2
			aTop     seconds
			jmp      code_0332
code_025a:
			dup     
			ldi      5
			eq?     
			bnt      code_0267
			ldi      3
			aTop     seconds
			jmp      code_0332
code_0267:
			dup     
			ldi      6
			eq?     
			bnt      code_0287
			pushi    #dispose
			pushi    0
			lal      local1
			send     4
			pushi    #setCycle
			pushi    4
			class    CycleTo
			push    
			pushi    0
			pushi    65535
			pushSelf
			lag      ego
			send     12
			jmp      code_0332
code_0287:
			dup     
			ldi      7
			eq?     
			bnt      code_02eb
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_02bd
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_02bd
			jmp      code_02a8
code_02a8:
			bnt      code_02bd
			pushi    #view
			pushi    1
			pushi    20
			pushi    3
			pushi    1
			pushi    2
			pushi    4
			pushi    1
			pushi    4
			lag      ego
			send     18
			jmp      code_02c7
code_02bd:
			pushi    #normalize
			pushi    1
			pushi    4
			lag      ego
			send     6
code_02c7:
			lal      local2
			not     
			bnt      code_02d4
			pushi    #dispose
			pushi    0
			self     4
			jmp      code_0332
code_02d4:
			pushi    4
			pushi    0
			lsg      curRoomNum
			pushi    2
			pushi    64
			callk    PalVary,  8
			pushi    2
			pushi    1
			lsl      local0
			callk    Abs,  2
			mul     
			aTop     ticks
			jmp      code_0332
code_02eb:
			dup     
			ldi      8
			eq?     
			bnt      code_0327
			lsl      local2
			dup     
			ldi      2
			eq?     
			bnt      code_0307
			pushi    2
			pushi    5
			pushi    64
			lal      local0
			sub     
			push    
			callk    PalVary,  4
			jmp      code_0320
code_0307:
			dup     
			ldi      3
			eq?     
			bnt      code_031b
			pushi    2
			pushi    1
			pushi    1
			lsl      local0
			callk    Abs,  2
			push    
			callk    PalVary,  4
			jmp      code_0320
code_031b:
			pushi    #dispose
			pushi    0
			self     4
code_0320:
			toss    
			ldi      1
			aTop     seconds
			jmp      code_0332
code_0327:
			dup     
			ldi      9
			eq?     
			bnt      code_0332
			pushi    #dispose
			pushi    0
			self     4
code_0332:
			toss    
			ret     
		)
	)
)

(instance faceEgo of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> (ego loop?) 2)
					(if (== (ego loop?) 6)
						(ego loop: 2 cel: 4)
					else
						(ego loop: (- (ego loop?) 1))
					)
				else
					(self dispose:)
				)
				(= cycles (ego cycleSpeed?))
			)
			(1
				(self init: client caller)
			)
		)
	)
)
