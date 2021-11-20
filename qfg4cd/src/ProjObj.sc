;;; Sierra Script 1.0 - (do not remove this comment)
(script# 32)
(include sci.sh)
(use Main)
(use Array)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	project 0
)

(local
	local0
	local1 =  30
	local2
	local3
	local4
	local5
	local6
	local7
	local8
	local9
	local10
	local11
	local12
)
(instance project of Script
	(properties)
	
	(method (init theClient theCaller theRegister param4)
		(= local6 (IntArray with: 20 -20 10 -10))
		(= local7 (IntArray with: 0 0 0 0))
		(= local8 (IntArray with: 10 -10 -10 10))
		(= local9 (IntArray with: -5 -5 -5 -5))
		(= local10 (IntArray with: 0 1 0 0 0 1 0 1))
		(= local11 (IntArray with: 2 3 6 7))
		(= lastTicks gameTime)
		(if (>= argc 1)
			((= client theClient) script: self)
			(if (>= argc 2)
				(= caller theCaller)
				(if (>= argc 3)
					(= register theRegister)
					(if (>= argc 4) (= local4 param4) else (= local4 0))
				)
			)
		)
		(= state (- start 1))
		(self cue:)
	)
	
	(method (dispose)
		(local6 dispose:)
		(local7 dispose:)
		(local8 dispose:)
		(local9 dispose:)
		(local10 dispose:)
		(local11 dispose:)
		(super dispose: &rest)
	)
	
	(method (changeState newState &tmp temp0)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_0206
			pushi    2
			lsg      mouseX
			lsg      mouseY
			ldi      10
			sub     
			push    
			callb    AutoTarget,  4
			pushi    #handsOff
			pushi    0
			lag      theGame
			send     4
			pushi    4
			lsg      ego
			lsg      projX
			lsg      projY
			pushSelf
			callb    Face,  8
			jmp      code_050f
code_0206:
			dup     
			ldi      1
			eq?     
			bnt      code_031c
			pTos     register
			ldi      36
			eq?     
			bnt      code_0218
			ldi      37
			aTop     register
code_0218:
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			sal      local3
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
			pushi    14
			pushi    1
			pTos     register
			ldi      75
			lt?     
			bnt      code_0244
			ldi      9
			jmp      code_026c
code_0244:
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_0260
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_0260
code_0260:
			push    
			ldi      1
			eq?     
			bnt      code_026a
			ldi      18
			jmp      code_026c
code_026a:
			ldi      14
code_026c:
			push    
			pushi    15
			pushi    1
			pushi    0
			lal      local3
			le?     
			bnt      code_0282
			pprev   
			ldi      85
			le?     
			bnt      code_0282
			ldi      2
			sal      local2
			jmp      code_02af
code_0282:
			pushi    86
			lal      local3
			le?     
			bnt      code_0296
			pprev   
			ldi      180
			le?     
			bnt      code_0296
			ldi      0
			sal      local2
			jmp      code_02af
code_0296:
			pushi    181
			lal      local3
			le?     
			bnt      code_02ab
			pprev   
			ldi      274
			le?     
			bnt      code_02ab
			ldi      1
			sal      local2
			jmp      code_02af
code_02ab:
			ldi      3
			sal      local2
code_02af:
			push    
			pushi    333
			pushi    1
			pushi    0
			lag      ego
			send     18
			pTos     register
			ldi      75
			lt?     
			bnt      code_02d6
			pushi    #setCycle
			pushi    4
			class    CT
			push    
			pushi    4
			pushi    1
			pushSelf
			lag      ego
			send     12
			jmp      code_050f
code_02d6:
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_02f2
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_02f2
code_02f2:
			push    
			ldi      1
			eq?     
			bnt      code_030c
			pushi    #setCycle
			pushi    4
			class    CT
			push    
			pushi    2
			pushi    1
			pushSelf
			lag      ego
			send     12
			jmp      code_050f
code_030c:
			pushi    #setCycle
			pushi    2
			class    End
			push    
			pushSelf
			lag      ego
			send     8
			jmp      code_050f
code_031c:
			dup     
			ldi      2
			eq?     
			bnt      code_0476
			pushi    3
			pTos     register
			pushi    21
			pushi    37
			calle    OneOf,  6
			bnt      code_03a9
			ldi      10
			lagi     egoStats
			bnt      code_0356
			pushi    #useSkill
			pushi    2
			pushi    10
			pushi    25
			pushi    770
			pushi    2
			pushi    0
			pushi    3
			pushi    770
			pushi    2
			pushi    2
			pushi    5
			lag      ego
			send     24
code_0356:
			pushi    #useStamina
			pushi    1
			pushi    2
			lag      ego
			send     6
			pushi    2
			pushi    0
			pushi    400
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
			bnt      code_038b
			pushi    0
			lat      temp0
			sub     
			jmp      code_038d
code_038b:
			lat      temp0
code_038d:
			add     
			sag      projX
			lsg      projY
			pushi    2
			pushi    0
			pushi    1
			callk    Random,  4
			bnt      code_03a3
			pushi    0
			lat      temp0
			sub     
			jmp      code_03a5
code_03a3:
			lat      temp0
code_03a5:
			add     
			sag      projY
code_03a9:
			pushi    #fixPriority
			pushi    1
			pushi    1
			pushi    43
			pushi    1
			pTos     register
			pushi    147
			pushi    0
			pushi    #new
			pushi    0
			lofsa    ProjObj
			send     4
			send     16
			pTos     register
			dup     
			ldi      86
			eq?     
			bnt      code_03dc
			pushi    #number
			pushi    1
			pushi    933
			pushi    51
			pushi    0
			lofsa    spellSoundFX
			send     10
			jmp      code_0430
code_03dc:
			dup     
			ldi      88
			eq?     
			bnt      code_03f3
			pushi    #number
			pushi    1
			pushi    938
			pushi    51
			pushi    0
			lofsa    spellSoundFX
			send     10
			jmp      code_0430
code_03f3:
			dup     
			ldi      93
			eq?     
			bnt      code_040a
			pushi    #number
			pushi    1
			pushi    974
			pushi    51
			pushi    0
			lofsa    spellSoundFX
			send     10
			jmp      code_0430
code_040a:
			dup     
			ldi      79
			eq?     
			bnt      code_0421
			pushi    #number
			pushi    1
			pushi    983
			pushi    51
			pushi    0
			lofsa    spellSoundFX
			send     10
			jmp      code_0430
code_0421:
			pushi    #number
			pushi    1
			pushi    916
			pushi    51
			pushi    0
			lofsa    spellSoundFX
			send     10
code_0430:
			toss    
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_044f
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_044f
			bt       code_0456
code_044f:
			pTos     register
			ldi      75
			lt?     
			bnt      code_0466
code_0456:
			pushi    #setCycle
			pushi    2
			class    End
			push    
			pushSelf
			lag      ego
			send     8
			jmp      code_050f
code_0466:
			pushi    #setCycle
			pushi    2
			class    Beg
			push    
			pushSelf
			lag      ego
			send     8
			jmp      code_050f
code_0476:
			dup     
			ldi      3
			eq?     
			bnt      code_04ef
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_0499
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_0499
code_0499:
			not     
			bnt      code_04a9
			pushi    #normalize
			pushi    1
			lsl      local0
			lag      ego
			send     6
			jmp      code_04e9
code_04a9:
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
			lal      local11
			send     6
			push    
			pushi    16
			pushi    1
			pushi    #at
			pushi    1
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			lal      local11
			send     6
			push    
			ldi      6
			lt?     
			bnt      code_04e1
			ldi      4
			jmp      code_04e3
code_04e1:
			ldi      5
code_04e3:
			push    
			lag      ego
			send     18
code_04e9:
			ldi      2
			aTop     cycles
			jmp      code_050f
code_04ef:
			dup     
			ldi      4
			eq?     
			bnt      code_050f
			pushi    1
			pushi    9
			callb    Btst,  2
			not     
			bnt      code_0508
			pushi    #handsOn
			pushi    0
			lag      theGame
			send     4
code_0508:
			pushi    #dispose
			pushi    0
			self     4
code_050f:
			toss    
			ret     
		)
	)
)

(class ProjObj of Actor
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $6001
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		robot 0
		type $0000
		cuedOnce 0
	)
	
	(method (init)
		(if (== type 37) (ego drop: 5 1))
		(if (== type 21) (ego drop: 6 1))
		(self
			view: (if (> type 75) 21 else 46)
			setLoop:
				(switch type
					(37 (+ (local10 at: local0) 2))
					(21 4)
					(86 2)
					(88 13)
					(93 4)
					(79 13)
					(33 6)
					(else  4)
				)
				1
			x:
				(+
					(ego x?)
					(/
						(*
							(ego scaleY?)
							(switch (ego view?)
								(14 (local6 at: (ego loop?)))
								(9 (local8 at: (ego loop?)))
								(18 (local6 at: (ego loop?)))
								(else 
									(local8 at: (ego loop?))
								)
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
								(14 (local7 at: (ego loop?)))
								(9 (local9 at: (ego loop?)))
								(else 
									(local9 at: (ego loop?))
								)
							)
						)
						128
					)
					(- 0 (/ scaleY 4))
				)
			setStep: 8 5
			moveSpeed: 0
			illegalBits: 0
			scaleX: (ego scaleX?)
			scaleY: (ego scaleY?)
			setCycle: Fwd
		)
		(if local4
			(self setMotion: MoveTo (local4 x?) (local4 y?) self)
		else
			(self setMotion: MoveTo projX projY self)
		)
		(super init:)
		(SetNowSeen self)
	)
	
	(method (doit &tmp temp0 temp1 temp2)
		(if (> y (ego y?))
			(= priority y)
		else
			(= priority (ego priority?))
		)
		(super doit: &rest)
		(= temp2 (/ (- nsRight nsLeft) 2))
		(if
			(and
				(not cuedOnce)
				global392
				(= temp0 (global392 firstTrue: #onMe x y))
			)
			(switch type
				(37
					(= temp1 (+ 16 (/ [egoStats 0] 20)))
				)
				(21
					(= temp1 (+ 3 (/ [egoStats 0] 20)))
				)
				(86
					(= temp1 (+ 10 (/ [egoStats 26] 10)))
				)
				(88
					(= temp1 (+ 8 (/ [egoStats 28] 20)))
				)
				(93
					(= temp1 (+ 12 (/ [egoStats 33] 10)))
				)
				(79
					(= temp1 (+ 16 (/ [egoStats 34] 10)))
				)
			)
			(cond 
				(local4 (local4 getHurt: type temp1))
				(temp0 (temp0 getHurt: type temp1))
			)
			(= local5 0)
			(self cue:)
		)
	)
	
	(method (cue)
		(if (not cuedOnce)
			(= cuedOnce 1)
			(if local4 (= local5 1))
			(self setScript: (checkLoop new:) self)
		else
			(if (self script?)
				((self script?) caller: 0)
				(self setScript: 0)
			)
			(self dispose:)
		)
	)
)

(instance checkLoop of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client cuedOnce: 1)
				(if (== (client view?) 21)
					(cond 
						((== (client loop?) 13) (client setLoop: 14 1 setMotion: 0 setCycle: End self))
						(
						(and (< -1 (client loop?)) (< (client loop?) 4))
							(client setLoop: 9 1 setMotion: 0 setCycle: End self)
							(spellSoundFX number: 930 play: 0)
						)
						((< (client loop?) 6)
							(client setLoop: 10 1 setMotion: 0 setCycle: End self)
							(spellSoundFX number: 930 play:)
						)
						(else (client setMotion: 0 hide: setCycle: End self))
					)
				else
					(spellSoundFX number: 921 play:)
					(client setMotion: 0 hide: setCycle: End self)
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance spellSoundFX of Sound
	(properties)
)
