;;; Sierra Script 1.0 - (do not remove this comment)
(script# 632)
(include sci.sh)
(use Main)
(use GloryRm)
(use TargFeature)
(use EgoDead)
(use Scaler)
(use DPath)
(use Chase)
(use Sound)
(use Jump)
(use Motion)
(use System)

(public
	rm632 0
)

(local
	local0
	gTheObj_2CycleSpeed
	local2
)
(instance rm632 of GloryRm
	(properties
		modNum 620
		picture 620
		north 663
	)
	
	(method (init param1)
		(asm
			lsg      prevRoomNum
			ldi      810
			ne?     
			bnt      code_01a2
			ldi      0
			sag      battleResult
			pushi    1
			pushi    331
			callb    Btst,  2
			not     
			bnt      code_0159
			pushi    #health
			pushi    1
			pushi    50
			lofsa    aBad1
			send     6
code_0159:
			pushi    1
			pushi    332
			callb    Btst,  2
			not     
			bnt      code_0170
			pushi    #health
			pushi    1
			pushi    50
			lofsa    aBad2
			send     6
code_0170:
			pushi    1
			pushi    333
			callb    Btst,  2
			not     
			bnt      code_0187
			pushi    #health
			pushi    1
			pushi    50
			lofsa    aBad3
			send     6
code_0187:
			pushi    1
			pushi    334
			callb    Btst,  2
			not     
			bnt      code_025b
			pushi    #health
			pushi    1
			pushi    50
			lofsa    aBad4
			send     6
			jmp      code_025b
code_01a2:
			lsg      monsterHealth
			ldi      0
			gt?     
			bnt      code_01b7
			pushi    #health
			pushi    1
			lsg      monsterHealth
			lofsa    aBad1
			send     6
			jmp      code_01ce
code_01b7:
			ldi      0
			sag      monsterHealth
			pushi    1
			pushi    331
			callb    Bset,  2
			pushi    #health
			pushi    1
			pushi    0
			lofsa    aBad1
			send     6
code_01ce:
			lsg      gABad2Health
			ldi      0
			gt?     
			bnt      code_01e5
			pushi    #health
			pushi    1
			lsg      gABad2Health
			lofsa    aBad2
			send     6
			jmp      code_01fd
code_01e5:
			ldi      0
			sag      gABad2Health
			pushi    1
			pushi    332
			callb    Bset,  2
			pushi    #health
			pushi    1
			pushi    0
			lofsa    aBad2
			send     6
code_01fd:
			lsg      gABad3Health
			ldi      0
			gt?     
			bnt      code_0214
			pushi    #health
			pushi    1
			lsg      gABad3Health
			lofsa    aBad3
			send     6
			jmp      code_022c
code_0214:
			ldi      0
			sag      gABad3Health
			pushi    1
			pushi    333
			callb    Bset,  2
			pushi    #health
			pushi    1
			pushi    0
			lofsa    aBad3
			send     6
code_022c:
			lsg      gABad4Health
			ldi      0
			gt?     
			bnt      code_0243
			pushi    #health
			pushi    1
			lsg      gABad4Health
			lofsa    aBad4
			send     6
			jmp      code_025b
code_0243:
			ldi      0
			sag      gABad4Health
			pushi    1
			pushi    334
			callb    Bset,  2
			pushi    #health
			pushi    1
			pushi    0
			lofsa    aBad4
			send     6
code_025b:
			lsg      prevRoomNum
			dup     
			ldi      663
			eq?     
			bnt      code_0285
			pushi    #play
			pushi    0
			lofsa    batSound
			send     4
			pushi    1
			pushi    251
			callb    Bset,  2
			pushi    #posn
			pushi    2
			pushi    88
			pushi    32
			lag      ego
			send     8
			jmp      code_0332
code_0285:
			dup     
			ldi      810
			eq?     
			bnt      code_0312
			lsg      battleResult
			dup     
			ldi      1
			eq?     
			bnt      code_02ad
			pushi    #play
			pushi    0
			lofsa    batSound
			send     4
			pushi    #setScript
			pushi    1
			lofsa    sEgosDead
			push    
			lag      curRoom
			send     6
			jmp      code_030f
code_02ad:
			dup     
			ldi      2
			eq?     
			bnt      code_02d1
			pushi    #posn
			pushi    2
			pushi    260
			pushi    163
			lag      ego
			send     8
			pushi    #setScript
			pushi    1
			lofsa    sMonstersDead
			push    
			lag      curRoom
			send     6
			jmp      code_030f
code_02d1:
			pushi    #play
			pushi    0
			lofsa    batSound
			send     4
			pushi    1
			pushi    251
			callb    Btst,  2
			bnt      code_02f3
			pushi    #x
			pushi    1
			pushi    91
			pushi    2
			pushi    1
			pushi    36
			lag      ego
			send     12
			jmp      code_0302
code_02f3:
			pushi    #x
			pushi    1
			pushi    269
			pushi    2
			pushi    1
			pushi    167
			lag      ego
			send     12
code_0302:
			pushi    #setScript
			pushi    1
			lofsa    sHeroRan
			push    
			lag      curRoom
			send     6
code_030f:
			toss    
			jmp      code_0332
code_0312:
			pushi    #play
			pushi    0
			lofsa    batSound
			send     4
			pushi    1
			pushi    251
			callb    Bclr,  2
			pushi    #posn
			pushi    2
			pushi    276
			pushi    152
			lag      ego
			send     8
code_0332:
			toss    
			pushi    #init
			pushi    0
			pushi    2
			pushi    633
			pushi    0
			callk    ScriptID,  4
			send     4
			lsg      prevRoomNum
			ldi      810
			ne?     
			bnt      code_0367
			pushi    147
			pushi    0
			pushi    790
			pushi    1
			lsg      prevRoomNum
			ldi      644
			eq?     
			bnt      code_035f
			ldi      2
			jmp      code_0361
code_035f:
			ldi      3
code_0361:
			push    
			lag      ego
			send     10
code_0367:
			pushi    #health
			pushi    0
			lofsa    aBad1
			send     4
			bnt      code_0390
			pushi    #setPri
			pushi    1
			pushi    196
			pushi    147
			pushi    0
			pushi    335
			pushi    1
			pushi    1
			pushi    184
			pushi    1
			lofsa    sBad1
			push    
			lofsa    aBad1
			send     22
code_0390:
			pushi    #health
			pushi    0
			lofsa    aBad2
			send     4
			bnt      code_03b9
			pushi    #setPri
			pushi    1
			pushi    196
			pushi    147
			pushi    0
			pushi    335
			pushi    1
			pushi    1
			pushi    184
			pushi    1
			lofsa    sBad2
			push    
			lofsa    aBad2
			send     22
code_03b9:
			pushi    #health
			pushi    0
			lofsa    aBad3
			send     4
			bnt      code_03e2
			pushi    #setPri
			pushi    1
			pushi    196
			pushi    147
			pushi    0
			pushi    335
			pushi    1
			pushi    1
			pushi    184
			pushi    1
			lofsa    sBad3
			push    
			lofsa    aBad3
			send     22
code_03e2:
			pushi    #health
			pushi    0
			lofsa    aBad4
			send     4
			bnt      code_040b
			pushi    #setPri
			pushi    1
			pushi    196
			pushi    147
			pushi    0
			pushi    335
			pushi    1
			pushi    1
			pushi    184
			pushi    1
			lofsa    sBad4
			push    
			lofsa    aBad4
			send     22
code_040b:
			pushi    #init
			pushi    0
			&rest    param1
			super    GloryRm,  4
			lsg      prevRoomNum
			ldi      810
			eq?     
			not     
			bnt      code_0469
			lsg      heroType
			ldi      3
			eq?     
			bnt      code_0469
			pushi    1
			pushi    331
			callb    Btst,  2
			not     
			bt       code_0451
			pushi    1
			pushi    332
			callb    Btst,  2
			not     
			bt       code_0451
			pushi    1
			pushi    333
			callb    Btst,  2
			not     
			bt       code_0451
			pushi    1
			pushi    334
			callb    Btst,  2
			not     
			bnt      code_0469
code_0451:
			pushi    #say
			pushi    6
			pushi    4
			pushi    6
			pushi    24
			pushi    0
			pushi    0
			pushi    620
			lag      messager
			send     16
			bnt      code_0469
code_0469:
			ret     
		)
	)
	
	(method (dispose)
		(if script (script dispose:))
		((ScriptID 633 3) stop: clean: (ScriptID 633 3))
		(DisposeScript 633)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1
					(messager say: 0 1 0 0 0 632)
				)
				(91
					(messager say: 7 91 0 1 0 620)
				)
				(21
					(curRoom setScript: (ScriptID 32) 0 21)
				)
				(37
					(curRoom setScript: (ScriptID 32) 0 37)
				)
				(86
					(curRoom setScript: (ScriptID 32) 0 86)
				)
				(88
					(curRoom setScript: (ScriptID 32) 0 88)
				)
				(93
					(curRoom setScript: (ScriptID 32) 0 93)
				)
				(79
					(curRoom setScript: (ScriptID 32) 0 79)
				)
				(85
					(if (Btst 334)
						(messager say: 4 6 35 0 0 620)
					else
						(= local2 1)
						(messager say: 0 85 29 0 0 620)
						(return 1)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (newRoom n)
		(if (== n 810)
			(= monsterHealth (aBad1 health?))
			(= gABad2Health (aBad2 health?))
			(= gABad3Health (aBad3 health?))
			(= gABad4Health (aBad4 health?))
		)
		(= global365 825)
		(if (== n 810) (theMusic doSongs: fade:))
		(theMusic2 stop:)
		(super newRoom: n)
	)
)

(instance sHeroRan of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOn:)
				(ego init:)
				(theMusic fade:)
				(= ticks 30)
			)
			(1
				(theGame handsOff:)
				(theMusic doSongs: 630 631 632)
				(if (Btst 251)
					((ScriptID 633 2) setCycle: End self)
				else
					((ScriptID 633 1) setCycle: End self)
				)
			)
			(2
				(ego setMotion: MoveTo (ego x?) (- (ego y?) 5) self)
			)
			(3
				(if (Btst 251)
					(curRoom newRoom: (curRoom north?))
				else
					(curRoom newRoom: 644)
				)
			)
		)
	)
)

(instance sGoToCombat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= global365 825)
				(= seconds 2)
			)
			(1
				(if local2
					(theGame handsOn:)
					(self dispose:)
				else
					(curRoom newRoom: 810)
				)
			)
		)
	)
)

(instance sEgosDead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego init: view: 6 setLoop: 0 1 setCel: 0 x: 271 y: 180)
				(= cycles 1)
			)
			(1 (ego setCycle: End self))
			(2 (EgoDead 29 620))
		)
	)
)

(instance sMonstersDead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic fade:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					init:
					x: 271
					y: 180
					view: 13
					setCel: 0
					setLoop: 1 1
					cycleSpeed: defaultCycles
				)
				(= ticks 30)
			)
			(1
				(theMusic number: 107 setLoop: 1 play: self)
				(ego setCycle: End)
			)
			(2
				(theMusic doSongs: 630 631 632)
				(ego setSpeed: gTheObj_2CycleSpeed normalize: 2)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDeadBadders of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1
				(aBad1
					setLoop: 4 1
					setCel: 0
					setCycle: 0
					setMotion: JumpTo 186 169
				)
				(aBad2
					setLoop: 4 1
					setCel: 0
					setCycle: 0
					setMotion: JumpTo 51 112
				)
				(aBad3
					setLoop: 4 1
					setCel: 0
					setCycle: 0
					setMotion: JumpTo 263 170
				)
				(aBad4
					setLoop: 4 1
					setCel: 0
					setCycle: 0
					setMotion: JumpTo 265 170
				)
				(= seconds 2)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sBad1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 2))
			(1
				(aBad1
					setCycle: Fwd
					ignoreActors: 1
					signal: (| (aBad1 signal?) $0001)
					setMotion: DPath 150 148 231 43 105 48 62 71 self
				)
			)
			(2
				(aBad1 setMotion: Chase ego 10)
			)
		)
	)
)

(instance sBad2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 2))
			(1
				(aBad2
					setCycle: Fwd
					ignoreActors: 1
					signal: (| (aBad1 signal?) $0001)
					setMotion: DPath 259 101 194 157 146 14 self
				)
			)
			(2
				(aBad2 setMotion: Chase ego 10)
			)
		)
	)
)

(instance sBad3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 2))
			(1
				(aBad3
					setCycle: Fwd
					ignoreActors: 1
					signal: (| (aBad1 signal?) $0001)
					setMotion: DPath 225 72 282 80 262 45 self
				)
			)
			(2
				(aBad3 setMotion: Chase ego 10)
			)
		)
	)
)

(instance sBad4 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 2))
			(1
				(aBad4
					setCycle: Fwd
					ignoreActors: 1
					signal: (| (aBad1 signal?) $0001)
					setMotion: DPath 75 180 13 163 118 100 27 134 self
				)
			)
			(2
				(aBad4 setMotion: Chase ego 10)
			)
		)
	)
)

(instance sWaitItOut of Script
	(properties)
	
	(method (dispose)
		(theMusic fade: 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local0 (= local0 0) (self cue:) else (self cue:))
			)
			(1 (= cycles 60))
			(2
				(if (not (Btst 332))
					(aBad2
						setLoop: 4 1
						setCel: 0
						setCycle: 0
						setMotion: JumpTo 51 112
					)
				)
				(if (not (Btst 333))
					(aBad3
						setLoop: 4 1
						setCel: 0
						setCycle: 0
						setMotion: JumpTo 263 170
					)
				)
				(= cycles 60)
			)
			(3
				(if (not (Btst 331))
					(aBad1
						setLoop: 4 1
						setCel: 0
						setCycle: 0
						setMotion: JumpTo 186 169
					)
				)
				(if (not (Btst 334))
					(aBad4
						setLoop: 4 1
						setCel: 0
						setCycle: 0
						setMotion: JumpTo 265 170
					)
				)
				(= cycles 160)
			)
			(4
				(if local2
					(theGame handsOn:)
					(self dispose:)
				else
					(curRoom newRoom: 810)
				)
			)
		)
	)
)

(instance sMonsterDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< monsterHealth 1) (Bset 331))
				(if (< gABad2Health 1) (Bset 332))
				(if (< gABad3Health 1) (Bset 333))
				(if (< gABad4Health 1) (Bset 334))
				(= seconds 1)
			)
			(1
				(if (Btst 331)
					0
				else
					(aBad1 init: setCycle: Fwd setScript: sBad1)
				)
				(if (Btst 332)
					0
				else
					(aBad2 init: setCycle: Fwd setScript: sBad2)
				)
				(if (Btst 333)
					0
				else
					(aBad3 init: setCycle: Fwd setScript: sBad3)
				)
				(if (Btst 334)
					0
				else
					(aBad4 init: setCycle: Fwd setScript: sBad4)
				)
				(= ticks 30)
			)
			(2
				(switch battleResult
					(1
						(cond 
							((Btst 334) 0)
							((Btst 333) (= monsterHealth gABad4Health) (= gABad4Health 0))
							((Btst 332) (= monsterHealth gABad3Health) (= gABad3Health 0))
							((Btst 331) (= monsterHealth gABad2Health) (= gABad2Health 0))
							(else 0)
						)
						(ego view: 6 setLoop: 0 1 setCel: 0 setCycle: End self)
					)
					(2 (self cue:))
					(3 (curRoom newRoom: 810))
				)
			)
			(3 (self cue:))
			(4
				(cond 
					((Btst 334) (theGame handsOn:) (self dispose:))
					(local2 (theGame handsOn:) (self dispose:))
					(else (curRoom newRoom: 810))
				)
			)
		)
	)
)

(class ABad of TargActor
	(properties
		scratch 0
		heading 0
		noun 11
		case 0
		modNum 620
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
		x 62
		y 71
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 196
		fixPriority 1
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view 825
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $4001
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
		health 0
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(!= battleResult 1)
				(!= battleResult 2)
				(!= battleResult 3)
				(<
					(GetDistance
						(ego x?)
						(- (ego y?) (/ (ego scaleY?) 4))
						x
						y
					)
					50
				)
				(> health 0)
				(!= (curRoom script?) sEgosDead)
				(not local2)
			)
			(curRoom newRoom: 810)
		)
	)
	
	(method (getHurt param1 param2)
		(cond 
			((== param1 79)
				(if (aBad1 health?)
					(aBad1 getHurt: 86 (+ 16 (/ [egoStats 34] 10)))
				)
				(if (aBad2 health?)
					(aBad2 getHurt: 86 (+ 16 (/ [egoStats 34] 10)))
				)
				(if (aBad3 health?)
					(aBad3 getHurt: 86 (+ 16 (/ [egoStats 34] 10)))
				)
				(if (aBad4 health?)
					(aBad4 getHurt: 86 (+ 16 (/ [egoStats 34] 10)))
				)
				(messager say: 11 79 0 0 0 620)
			)
			((> health 0)
				(= health (- health param2))
				(batHurt play:)
				(if (< health 1) (= health 0) (self cue:))
			)
		)
	)
)

(instance aBad1 of ABad
	(properties)
	
	(method (init)
		(super init: &rest)
		(if (not (== prevRoomNum 810)) (= health 50))
		(self setStep: 4 6)
		(self setScaler: Scaler 81 54 115 85)
	)
	
	(method (cue)
		(Bset 331)
		(self
			setLoop: 4 1
			setCel: 0
			setCycle: 0
			setMotion: JumpTo 186 169
		)
	)
)

(instance aBad2 of ABad
	(properties
		x 146
		y 14
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(self setStep: 4 6)
		(if (not (== prevRoomNum 810)) (= health 50))
	)
	
	(method (cue)
		(Bset 332)
		(self
			setLoop: 4 1
			setCel: 0
			setCycle: 0
			setMotion: JumpTo 51 112
		)
	)
)

(instance aBad3 of ABad
	(properties
		x 262
		y 45
		loop 2
		scaleSignal $0001
	)
	
	(method (init)
		(if (not (== prevRoomNum 810)) (= health 50))
		(super init: &rest)
		(self setStep: 4 6)
		(self setScaler: Scaler 100 55 94 37)
	)
	
	(method (cue)
		(Bset 333)
		(self
			setLoop: 4 1
			setCel: 0
			setCycle: 0
			setMotion: JumpTo 263 170
		)
	)
)

(instance aBad4 of ABad
	(properties
		x 27
		y 134
		loop 3
	)
	
	(method (init)
		(super init: &rest)
		(self setStep: 4 6)
		(if (not (== prevRoomNum 810)) (= health 50))
	)
	
	(method (cue)
		(Bset 334)
		(aBad4
			setLoop: 4 1
			setCel: 0
			setCycle: 0
			setMotion: JumpTo 265 170
		)
	)
)

(instance batSound of Sound
	(properties
		number 132
	)
)

(instance batHurt of Sound
	(properties
		number 828
	)
)
