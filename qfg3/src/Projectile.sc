;;; Sierra Script 1.0 - (do not remove this comment)
(script# PROJECTILE) ;32
(include game.sh)
(use Main)
(use Chase)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	project 0
)

(local
	local0
	local1 = [20 -20 10 -10]
	[local5 4]
	local9 =  30
	local10 = [10 -10 -10 10]
	local14 = [-5 -5 -5 -5]
	local18
	local19
	local20
	local21
	local22 = [0 1 2 3 0 1 0 1]
	local30 = [0 1 0 0 0 1 0 1]
	local38 = [2 3 6 7]
)
(instance project of Script

	(method (init actor whoCares theRegister param4)
		(= lastTicks gameTime)
		(if (>= argc 1)
			((= client actor) script: self)
			(if (>= argc 2)
				(= caller whoCares)
				(if (>= argc 3)
					(= register theRegister)
					(if (>= argc 4)
						(= local20 param4)
					else
						(= local20 0)
					)
				)
			)
		)
		(= state (- start 1))
		(self cue:)
	)
	
	(method (changeState newState &tmp temp0)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_0139
			pushi    0
			callb    HandsOff,  0
			pushi    2
			pushi    #x
			pushi    0
			pushi    #curEvent
			pushi    0
			class    User
			send     4
			send     4
			push    
			pushi    #y
			pushi    0
			pushi    #curEvent
			pushi    0
			class    User
			send     4
			send     4
			push    
			callb    AutoTarget,  4
			pushi    4
			lsg      ego
			lsg      projX
			lsg      projY
			pushSelf
			callb    Face,  8
			jmp      code_0408
code_0139:
			dup     
			ldi      1
			eq?     
			bnt      code_0258
			pTos     register
			ldi      56
			eq?     
			bt       code_014e
			pTos     register
			ldi      11
			eq?     
			bnt      code_0157
code_014e:
			pushi    2
			pushi    194
			pushi    90
			callb    AutoTarget,  4
code_0157:
			pTos     register
			ldi      11
			eq?     
			bnt      code_0162
			ldi      20
			aTop     register
code_0162:
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			sal      local19
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			sal      local0
			pushi    #setMotion
			pushi    1
			pushi    0
			lag      ego
			send     6
			pushi    2
			pushi    1
			pTos     register
			ldi      75
			lt?     
			bnt      code_018a
			ldi      9
			jmp      code_01b0
code_018a:
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_01a4
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_01a4
			jmp      code_01a4
code_01a4:
			push    
			ldi      1
			eq?     
			bnt      code_01ae
			ldi      18
			jmp      code_01b0
code_01ae:
			ldi      14
code_01b0:
			push    
			pushi    3
			pushi    1
			pushi    0
			lal      local19
			le?     
			bnt      code_01c6
			pprev   
			ldi      85
			le?     
			bnt      code_01c6
			ldi      2
			sal      local18
			jmp      code_01f3
code_01c6:
			pushi    86
			lal      local19
			le?     
			bnt      code_01da
			pprev   
			ldi      180
			le?     
			bnt      code_01da
			ldi      0
			sal      local18
			jmp      code_01f3
code_01da:
			pushi    181
			lal      local19
			le?     
			bnt      code_01ef
			pprev   
			ldi      274
			le?     
			bnt      code_01ef
			ldi      1
			sal      local18
			jmp      code_01f3
code_01ef:
			ldi      3
			sal      local18
code_01f3:
			push    
			pushi    156
			pushi    1
			pushi    0
			lag      ego
			send     18
			pTos     register
			ldi      75
			lt?     
			bnt      code_0217
			pushi    #setCycle
			pushi    4
			class    CycleTo
			push    
			pushi    4
			pushi    1
			pushSelf
			lag      ego
			send     12
			jmp      code_0408
code_0217:
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_0231
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_0231
			jmp      code_0231
code_0231:
			push    
			ldi      1
			eq?     
			bnt      code_0249
			pushi    #setCycle
			pushi    4
			class    CycleTo
			push    
			pushi    2
			pushi    1
			pushSelf
			lag      ego
			send     12
			jmp      code_0408
code_0249:
			pushi    #setCycle
			pushi    2
			class    EndLoop
			push    
			pushSelf
			lag      ego
			send     8
			jmp      code_0408
code_0258:
			dup     
			ldi      2
			eq?     
			bnt      code_038e
			pushi    3
			pTos     register
			pushi    33
			pushi    20
			calle    OneOf,  6
			bnt      code_02cb
			pushi    #useSkill
			pushi    2
			pushi    10
			pushi    25
			lag      ego
			send     8
			pushi    #useSkill
			pushi    2
			pushi    0
			pushi    2
			lag      ego
			send     8
			pushi    2
			pushi    0
			pushi    300
			ldi      10
			lagi     egoStats
			sub     
			push    
			ldi      4
			add     
			push    
			ldi      2
			shr     
			push    
			callk    Random,  4
			sat      temp0
			lsg      projX
			pushi    2
			pushi    0
			pushi    1
			callk    Random,  4
			bnt      code_02ae
			pushi    0
			lat      temp0
			sub     
			jmp      code_02b0
code_02ae:
			lat      temp0
code_02b0:
			add     
			sag      projX
			lsg      projY
			pushi    2
			pushi    0
			pushi    1
			callk    Random,  4
			bnt      code_02c5
			pushi    0
			lat      temp0
			sub     
			jmp      code_02c7
code_02c5:
			lat      temp0
code_02c7:
			add     
			sag      projY
code_02cb:
			pushi    #signal
			pushi    1
			pushi    16
			pushi    60
			pushi    1
			pushi    #priority
			pushi    0
			lag      ego
			send     4
			push    
			pushi    31
			pushi    1
			pTos     register
			pushi    110
			pushi    0
			pushi    #new
			pushi    0
			class    ProjObj
			send     4
			send     22
			pTos     register
			dup     
			ldi      81
			eq?     
			bnt      code_0307
			pushi    #number
			pushi    1
			pushi    13
			pushi    155
			pushi    1
			pushi    1
			pushi    39
			pushi    0
			lag      globalSound
			send     16
			jmp      code_034c
code_0307:
			dup     
			ldi      83
			eq?     
			bnt      code_0321
			pushi    #number
			pushi    1
			pushi    943
			pushi    155
			pushi    1
			pushi    1
			pushi    39
			pushi    0
			lag      globalSound
			send     16
			jmp      code_034c
code_0321:
			dup     
			ldi      88
			eq?     
			bnt      code_033a
			pushi    #number
			pushi    1
			pushi    11
			pushi    155
			pushi    1
			pushi    1
			pushi    39
			pushi    0
			lag      globalSound
			send     16
			jmp      code_034c
code_033a:
			pushi    #number
			pushi    1
			pushi    916
			pushi    155
			pushi    1
			pushi    1
			pushi    39
			pushi    0
			lag      globalSound
			send     16
code_034c:
			toss    
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_0369
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_0369
			jmp      code_0367
code_0367:
			bt       code_0370
code_0369:
			pTos     register
			ldi      75
			lt?     
			bnt      code_037f
code_0370:
			pushi    #setCycle
			pushi    2
			class    EndLoop
			push    
			pushSelf
			lag      ego
			send     8
			jmp      code_0408
code_037f:
			pushi    #setCycle
			pushi    2
			class    BegLoop
			push    
			pushSelf
			lag      ego
			send     8
			jmp      code_0408
code_038e:
			dup     
			ldi      3
			eq?     
			bnt      code_03f0
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_03af
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_03af
			jmp      code_03af
code_03af:
			not     
			bnt      code_03be
			pushi    #normalize
			pushi    1
			lsl      local0
			lag      ego
			send     6
			jmp      code_03ea
code_03be:
			pushi    2
			pushi    1
			pushi    20
			pushi    3
			pushi    1
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			lsli     local38
			pushi    4
			pushi    1
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			lsli     local38
			ldi      6
			lt?     
			bnt      code_03e3
			ldi      4
			jmp      code_03e5
code_03e3:
			ldi      5
code_03e5:
			push    
			lag      ego
			send     18
code_03ea:
			ldi      2
			aTop     cycles
			jmp      code_0408
code_03f0:
			dup     
			ldi      4
			eq?     
			bnt      code_0408
			pushi    1
			pushi    15
			callb    Btst,  2
			not     
			bnt      code_0403
			pushi    0
			callb    HandsOn,  0
code_0403:
			pushi    #dispose
			pushi    0
			self     4
code_0408:
			toss    
			ret     
		)
	)
)

(class ProjObj of Actor
	(properties
		type $0000
		cuedOnce 0
	)
	
	(method (init)
		(if (== type 20) (ego drop: 10 1))
		(if (== type 33) (ego drop: 23 1))
		(self
			view: (if (> type 75) 21 else 46)
			setLoop:
				(switch type
					(20 (+ [local30 local0] 2))
					(33 4)
					(81 2)
					(83 13)
					(32 4)
					(16 6)
					(56 6)
					(else  4)
				)
			x:
				(+
					(ego x?)
					(/
						(*
							(ego scaleY?)
							(switch (ego view?)
								(14 [local1 (ego loop?)])
								(9 [local10 (ego loop?)])
								(18 [local1 (ego loop?)])
								(else  [local10 (ego loop?)])
							)
						)
						128
					)
				)
			y:
				(+
					(ego y?)
					(/
						(*
							(ego scaleY?)
							(switch (ego view?)
								(14 [local5 (ego loop?)])
								(9 [local14 (ego loop?)])
								(else  [local14 (ego loop?)])
							)
						)
						128
					)
				)
			setStep: 8 5
			z: (if (IsObject local20)
				(+ (local20 z?) local9)
			else
				local9
			)
			moveSpeed: 0
			ignoreActors: 1
			ignoreHorizon: 1
			illegalBits: 0
			setScale: -1 ego
			setCycle: (if (== type 56) 0 else Forward)
		)
		(if (IsObject local20)
			(self setMotion: Chase local20 0 self)
		else
			(self setMotion: MoveTo projX (+ projY local9) self)
		)
		(super init:)
		(SetNowSeen self)
	)
	
	(method (doit &tmp temp0 temp1 temp2)
		(super doit: &rest)
		(= temp2 (/ (- nsRight nsLeft) 2))
		(if
			(or
				(and
					(not cuedOnce)
					(IsObject gNewList)
					(= temp0
						(gNewList
							firstTrue: #onMe (+ nsLeft temp2) (+ nsTop temp2)
						)
					)
				)
				local21
			)
			(switch type
				(20
					(= temp1 (+ 8 (/ [egoStats 0] 20)))
				)
				(33
					(= temp1 (+ 5 (/ [egoStats 0] 20)))
				)
				(81
					(= temp1 (+ 10 (/ [egoStats 25] 10)))
				)
				(83
					(= temp1 (+ 10 (/ [egoStats 27] 20)))
				)
				(88
					(= temp1 (+ 12 (/ [egoStats 32] 10)))
				)
			)
			(cond 
				((IsObject local20) (local20 getHurt: type temp1))
				((IsObject temp0) (temp0 getHurt: type temp1))
			)
			(= local21 0)
			(self cue:)
		)
	)
	
	(method (cue)
		(if (not cuedOnce)
			(= cuedOnce TRUE)
			(if (IsObject local20)
				(= local21 1)
			)
			(self setScript: checkLoop self)
		else
			(self setMotion: 0 dispose:)
		)
	)
)

(instance checkLoop of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (client view?) 21)
					(cond 
						((== (client loop?) 13)
							(client setLoop: 14 setMotion: 0 setCycle: EndLoop self)
							(globalSound number: 930 setLoop: 1 play: 127)
						)
						(
						(and (< -1 (client loop?)) (< (client loop?) 4))
							(client setLoop: 9 setMotion: 0 setCycle: EndLoop self)
							(globalSound number: 930 setLoop: 1 play: 127)
						)
						((< (client loop?) 6)
							(client setLoop: 10 setMotion: 0 setCycle: EndLoop self)
							(globalSound number: 930 setLoop: 1 play:)
						)
						(else (self cue:))
					)
				else
					(globalSound number: 921 setLoop: 1 play: 127)
					(self dispose:)
				)
			)
			(1
				(self dispose:)
			)
		)
	)
)
