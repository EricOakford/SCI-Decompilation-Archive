;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50)
(include sci.sh)
(use Main)
(use TargFeature)
(use EgoDead)
(use String)
(use Array)
(use Talker)
(use PChase)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Grooper)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	forest 0
	enterRoomScr 1
	egoDies 2
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	theX
	theY
	local9
	local10 =  1
	local11
	local12
	local13
	local14
	local15
	local16
	local17
	local18
	newStr
	local20
	gTheObj_2CycleSpeed
	local22
)
(procedure (localproc_01ee)
	(cond 
		(Night
			(if
				(and
					(!= (theMusic number?) 556)
					(!= (theMusic number?) 557)
				)
				(theMusic number: 556 setLoop: 1 play: forest)
			)
		)
		((!= (theMusic number?) 558) (theMusic number: 558 setLoop: -1 play:))
	)
)

(procedure (localproc_024d)
	(= gGGNumber 0)
	(while
	(or (not gGGNumber) (== gGGNumber prevRoomNum))
		(= gGGNumber (local16 at: (Random 0 3)))
	)
	(return gGGNumber)
)

(class ForestView of Prop
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
		sightAngle 44
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
		signal $5000
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
	)
	
	(method (handleEvent event)
		(= theX x)
		(= theY y)
		(= approachX (event x?))
		(= approachY (event y?))
		(super handleEvent: event)
	)
	
	(method (doVerb theVerb)
		(= noun
			(switch view
				(410 (if (< loop 2) 1 else 41))
				(411
					(switch cel
						(0 2)
						(else  3)
					)
				)
				(412
					(switch cel
						(0 4)
						(else  2)
					)
				)
				(413
					(switch cel
						(0 4)
						(else  2)
					)
				)
				(414
					(if (< loop 2)
						(switch cel
							(0 1)
							(1 1)
							(2 5)
							(3 6)
							(4 2)
							(5 6)
							(else  7)
						)
					else
						40
					)
				)
				(415
					(switch cel
						(0 1)
						(1 4)
						(2 16)
						(3 9)
						(else  7)
					)
				)
				(416
					(switch cel
						(0 6)
						(3 7)
						(4 4)
						(else  1)
					)
				)
				(420 (if (< loop 2) 1 else 40))
				(421 (if (< loop 2) 4 else 42))
				(424
					(cond 
						((< loop 2)
						(switch cel
							(3 5)
							(4 11)
							(else  1)
						))
						((< loop 4)
						(switch cel
							(2 9)
							(4 1)
							(else  8)
						))
						(else 40)
					)
				)
				(429 6)
				(430
					(switch cel
						(0 4)
						(else  1)
					)
				)
				(434
					(cond 
						((< loop 2)
						(switch cel
							(0 9)
							(1 1)
							(2 8)
							(else  6)
						))
						((< loop 6)
						(switch cel
							(0 5)
							(1 9)
							(else  3)
						))
						(else 40)
					)
				)
				(439 6)
				(else  4)
			)
		)
		(switch theVerb
			(1
				(messager say: noun theVerb 0 0 0 50)
			)
			(4
				(cond 
					((or (== noun 8) (== noun 16)) (ego get: 40 1) (messager say: noun theVerb 0 0 0 50))
					((or (== noun 6) (== noun 27) (== noun 1))
						(if [egoStats 11]
							(ego trySkill: 11 300)
							(messager say: 12 6 7 0 0 50)
						else
							(messager say: 12 6 8 0 0 50)
						)
					)
					((Message msgSIZE 50 noun theVerb 0 1) (messager say: noun theVerb 0 0 0 50))
					(else (super doVerb: theVerb))
				)
			)
			(24
				(if (== noun 40)
					(ego drop: 9 1 get: 10)
					(messager say: 5 24 0 0 0 50)
				else
					(forest doVerb: theVerb)
				)
			)
			(else  (forest doVerb: theVerb))
		)
		(= x theX)
		(= y theY)
	)
)

(class it of TargActor
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
		view 0
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $7001
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
		monStartX 0
		monStartY 0
		dead 0
	)
	
	(method (init)
		(super init: &rest)
		(theMusic client: 0)
		(self setScaler: ego)
		(switch global365
			(820
				(self view: 820 setStep: 5 3 setLooper: Grooper noun: 43)
				(if (!= (theMusic number?) 870)
					(theMusic number: 870 setLoop: -1 play:)
				)
			)
			(825
				(self
					view: 825
					setStep: 5 3
					x: (- (+ x 30) (Random 0 60))
					cel: (Random 0 5)
					z: (Random 20 40)
					noun: 31
				)
				(if (!= (theMusic number?) 870)
					(theMusic number: 870 setLoop: -1 play:)
				)
			)
			(830
				(self
					view: 830
					setStep: 3 2
					setLooper: Grooper
					setSpeed: defaultCycles
					noun: 32
				)
			)
			(835
				(self view: 835 setStep: 5 3 noun: 33 z: 20)
				(if (!= (theMusic number?) 870)
					(theMusic number: 870 setLoop: -1 play:)
				)
			)
			(870
				(self view: 870 setStep: 8 4 setLooper: Grooper noun: 37)
				(if (!= (theMusic number?) 870)
					(theMusic number: 870 setLoop: -1 play:)
				)
			)
			(840
				(self view: 840 setStep: 3 2 noun: 34 z: 10)
				(if (!= (theMusic number?) 870)
					(theMusic number: 870 setLoop: -1 play:)
				)
			)
		)
	)
	
	(method (doit)
		(cond 
			((and (< y local14) (< z 1000)) (= z (+ z 1000)))
			((and (> y local14) (>= z 1000)) (= z (- z 1000)))
		)
		(if
			(and
				(!= global365 830)
				(!= moveSpeed (+ (ego moveSpeed?) 1))
			)
			(self setSpeed: (+ (ego moveSpeed?) 1))
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if dead
					(messager say: noun 1 6 0 0 50)
				else
					(messager say: noun 1 0 0 0 50)
				)
			)
			(4
				(if dead
					(curRoom setScript: sSearchMonster)
				else
					(messager say: noun 4 0 0 0 50)
				)
			)
			(else  (forest doVerb: theVerb))
		)
	)
	
	(method (getHurt param1 param2)
		(if
			(or
				(and (== self it) (not (self dead?)))
				(and (== self local11) (not (local11 dead?)))
				(and (== self local12) (not (local12 dead?)))
				(and (== self local13) (not (local13 dead?)))
			)
			(switch global365
				(820
					(monsterRoar number: 822 play:)
				)
				(825
					(monsterRoar number: 828 play:)
				)
				(830
					(monsterRoar number: 837 play:)
				)
				(835
					(monsterRoar number: 837 play:)
				)
				(870
					(monsterRoar number: 873 play:)
				)
				(840
					(monsterRoar number: 843 play:)
				)
			)
			(switch self
				(local11
					(if
						(and
							(> gABad2Health 0)
							(< (= gABad2Health (- gABad2Health param2)) 1)
						)
						(= gABad2Health 0)
						(local11 setScript: monsterDies2)
					)
				)
				(local12
					(if
						(and
							(> gABad3Health 0)
							(< (= gABad3Health (- gABad3Health param2)) 1)
						)
						(= gABad3Health 0)
						(local12 setScript: monsterDies3)
					)
				)
				(local13
					(if
						(and
							(> gABad4Health 0)
							(< (= gABad4Health (- gABad4Health param2)) 1)
						)
						(= gABad4Health 0)
						(local13 setScript: monsterDies4)
					)
				)
				(it
					(if (> monsterHealth 0)
						(if
							(and
								local0
								(it script?)
								((it script?) isKindOf: sChaseHim)
							)
							((it script?) seconds: 0 cue:)
						)
						(if
							(and
								local0
								(it script?)
								(not ((it script?) isKindOf: sChaseHim))
							)
							(= local0 0)
						)
						(if
						(< (= monsterHealth (- monsterHealth param2)) 1)
							(= monsterHealth 0)
							(it setScript: monsterDies)
						)
					)
				)
			)
		)
	)
	
	(method (runChasing)
		(switch global365
			(820
				(self view: 820 setStep: 5 3)
			)
			(825
				(self view: 825 setStep: 5 3)
			)
			(830
				(self view: 830 setStep: 3 2)
			)
			(835
				(self view: 835 setStep: 5 3)
			)
			(870
				(self view: 870 setStep: 8 4)
			)
		)
		(if (!= prevRoomNum 810)
			(= x (if (Random 0 1) 50 else 270))
		)
		(self
			y: 136
			setCycle: Walk
			setSpeed: (+ (ego moveSpeed?) 1)
		)
		(cond 
			((and gABad2Health (== self local11))
				(local11
					x: (- (+ x 30) (Random 0 60))
					cel: (Random 0 5)
					setScript: (sChaseHim new:)
				)
			)
			((and gABad3Health (== self local12))
				(local11
					x: (- (+ x 30) (Random 0 60))
					cel: (Random 0 5)
					setScript: (sChaseHim new:)
				)
			)
			((and gABad4Health (== self local13))
				(local11
					x: (- (+ x 30) (Random 0 60))
					cel: (Random 0 5)
					setScript: (sChaseHim new:)
				)
			)
			(else (self setScript: (sChaseHim new:)))
		)
		(= monStartX x)
		(= monStartY y)
	)
	
	(method (startChasing)
		(switch gGGNumber
			((curRoom north?)
				(self x: (curRoom topX?) y: (curRoom horizon?))
			)
			((curRoom south?)
				(self x: 160 y: 280)
			)
			((curRoom east?)
				(self x: 375 y: 90)
			)
			((curRoom west?)
				(self x: -55 y: 90)
			)
			(else 
				(= gGGNumber prevRoomNum)
				(self x: 160 y: 110)
			)
		)
		(cond 
			((and gABad2Health (== self local11))
				(local11
					x: (+ x (Random -30 30))
					cel: (Random 0 5)
					setCycle: Walk
					setScript: (sChaseHim new:)
				)
			)
			((and gABad3Health (== self local12))
				(local12
					x: (+ x (Random -30 30))
					cel: (Random 0 5)
					setCycle: Walk
					setScript: (sChaseHim new:)
				)
			)
			((and gABad4Health (== self local13))
				(local13
					x: (+ x (Random -30 30))
					cel: (Random 0 5)
					setCycle: Walk
					setScript: (sChaseHim new:)
				)
			)
			((== global365 830)
				(self
					view: 833
					x: 160
					y: 135
					setLoop: 0
					setCel: 0
					setScript: sRevenant
				)
			)
			(else (self setCycle: Walk setScript: (sChaseHim new:)))
		)
		(= monStartX x)
		(= monStartY y)
	)
	
	(method (keepChasing)
		(switch prevRoomNum
			((curRoom north?)
				(self x: (curRoom topX?) y: (- (curRoom horizon?) 50))
			)
			((curRoom south?)
				(self x: 160 y: 290)
			)
			((curRoom east?)
				(self x: 385 y: 90)
			)
			((curRoom west?)
				(self x: -65 y: 90)
			)
			(else  (self x: 250 y: 120))
		)
		(switch global365
			(820
				(self view: 820 setStep: 3 2)
			)
			(825
				(self view: 825 setStep: 3 2)
			)
			(830
				(self view: 830 setStep: 3 2)
			)
			(835
				(self view: 835 setStep: 5 3)
			)
			(870
				(self view: 870 setStep: 8 4)
			)
			(840
				(self view: 840 setStep: 3 2)
			)
		)
		(cond 
			((and gABad2Health (== self local11))
				(= x (+ x (Random -20 20)))
				(= cel (Random 0 5))
				(local11 setCycle: Walk setScript: (sChaseHim new:))
			)
			((and gABad3Health (== self local12))
				(= x (+ x (Random -20 20)))
				(= cel (Random 0 5))
				(local12 setCycle: Walk setScript: (sChaseHim new:))
			)
			((and gABad4Health (== self local13))
				(= x (+ x (Random -20 20)))
				(= cel (Random 0 5))
				(local13 setCycle: Walk setScript: (sChaseHim new:))
			)
			(else (self setCycle: Walk setScript: sChaseHim))
		)
		(= monStartX x)
		(= monStartY y)
	)
)

(instance combatMonster of Prop
	(properties
		view 0
		signal $7001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: noun 1 6 0 0 50)
			)
			(4
				(curRoom setScript: sSearchMonster)
			)
			(else  (forest doVerb: theVerb))
		)
	)
)

(instance forest of Rgn
	(properties
		modNum 50
	)
	
	(method (init param1 &tmp temp0 temp1)
		(asm
			pushi    #with
			pushi    4
			pushi    0
			pushi    6
			pushi    4
			pushi    2
			class    IntArray
			send     12
			sal      local15
			pushi    #with
			pushi    4
			pushi    0
			pushi    0
			pushi    0
			pushi    0
			class    IntArray
			send     12
			sal      local16
			pushi    #with
			pushi    4
			pushi    0
			pushi    60
			pushi    63
			pushi    80
			class    IntArray
			send     12
			sal      local18
			pushi    #with
			pushi    92
			pushi    551
			pushi    178
			pushi    46
			pushi    30
			pushi    554
			pushi    99
			pushi    155
			pushi    45
			pushi    555
			pushi    174
			pushi    81
			pushi    23
			pushi    558
			pushi    217
			pushi    56
			pushi    14
			pushi    560
			pushi    88
			pushi    52
			pushi    80
			pushi    561
			pushi    262
			pushi    39
			pushi    29
			pushi    563
			pushi    273
			pushi    125
			pushi    20
			pushi    564
			pushi    205
			pushi    34
			pushi    44
			pushi    565
			pushi    261
			pushi    8
			pushi    92
			pushi    566
			pushi    95
			pushi    155
			pushi    45
			pushi    567
			pushi    143
			pushi    21
			pushi    53
			pushi    569
			pushi    101
			pushi    32
			pushi    75
			pushi    571
			pushi    166
			pushi    51
			pushi    23
			pushi    574
			pushi    22
			pushi    18
			pushi    170
			pushi    578
			pushi    267
			pushi    32
			pushi    80
			pushi    579
			pushi    126
			pushi    40
			pushi    71
			pushi    580
			pushi    298
			pushi    61
			pushi    44
			pushi    583
			pushi    192
			pushi    71
			pushi    46
			pushi    585
			pushi    168
			pushi    33
			pushi    78
			pushi    586
			pushi    210
			pushi    28
			pushi    50
			pushi    588
			pushi    274
			pushi    80
			pushi    40
			pushi    590
			pushi    89
			pushi    52
			pushi    82
			pushi    592
			pushi    28
			pushi    138
			pushi    42
			class    IntArray
			send     188
			sal      local17
			pushi    75
			pushi    1
			pushi    #picture
			pushi    0
			lag      curRoom
			send     4
			push    
			ldi      400
			sub     
			push    
			ldi      10
			div     
			push    
			lal      local18
			send     6
			sal      local14
			pushi    #at
			pushi    2
			pushi    0
			pushi    #north
			pushi    0
			lag      curRoom
			send     4
			push    
			lal      local16
			send     8
			pushi    #at
			pushi    2
			pushi    1
			pushi    #south
			pushi    0
			lag      curRoom
			send     4
			push    
			lal      local16
			send     8
			pushi    #at
			pushi    2
			pushi    2
			pushi    #east
			pushi    0
			lag      curRoom
			send     4
			push    
			lal      local16
			send     8
			pushi    #at
			pushi    2
			pushi    3
			pushi    #west
			pushi    0
			lag      curRoom
			send     4
			push    
			lal      local16
			send     8
			pushi    5
			lsg      curRoomNum
			pushi    575
			pushi    593
			pushi    588
			pushi    551
			calle    OneOf,  10
			bnt      code_1297
			ldi      65535
			sag      gGGNumber
			jmp      code_12a0
code_1297:
			pushi    0
			call     localproc_024d,  0
			sag      gGGNumber
code_12a0:
			pushi    #init
			pushi    0
			pushi    342
			pushi    5
			class    Scaler
			push    
			pushi    100
			pushi    40
			pushi    147
			pushi    61
			lag      ego
			send     18
			lsg      battleResult
			ldi      1
			ne?     
			bnt      code_12cc
			pushi    #normalize
			pushi    0
			lag      ego
			send     4
code_12cc:
			pushi    #init
			pushi    0
			&rest    param1
			super    Rgn,  4
			lsg      prevRoomNum
			ldi      810
			ne?     
			bnt      code_1582
			pushi    1
			pushi    35
			callb    Btst,  2
			bnt      code_1303
			lsg      curRoomNum
			ldi      568
			eq?     
			bt       code_12fa
			lsg      prevRoomNum
			ldi      480
			eq?     
			bnt      code_1582
code_12fa:
			pushi    0
			call     localproc_01ee,  0
			jmp      code_1582
code_1303:
			lsg      global365
			ldi      850
			eq?     
			bnt      code_130f
			jmp      code_1582
code_130f:
			lag      global365
			bnt      code_13c2
			push    
			ldi      870
			ne?     
			bnt      code_134d
			+ag      global470
			push    
			pushi    2
			pushi    3
			pushi    6
			callk    Random,  4
			gt?     
			bnt      code_134d
			ldi      0
			sag      global366
			sag      global365
			sag      global470
			ldi      0
			sag      gABad4Health
			sag      gABad3Health
			sag      gABad2Health
			pushi    0
			call     localproc_01ee,  0
			jmp      code_1582
code_134d:
			lsg      gABad2Health
			ldi      0
			gt?     
			bnt      code_136c
			pushi    #init
			pushi    0
			pushi    823
			pushi    0
			pushi    #new
			pushi    0
			class    it
			send     4
			sal      local11
			send     8
code_136c:
			lsg      gABad3Health
			ldi      0
			gt?     
			bnt      code_138b
			pushi    #init
			pushi    0
			pushi    823
			pushi    0
			pushi    #new
			pushi    0
			class    it
			send     4
			sal      local12
			send     8
code_138b:
			lsg      gABad4Health
			ldi      0
			gt?     
			bnt      code_13aa
			pushi    #init
			pushi    0
			pushi    823
			pushi    0
			pushi    #new
			pushi    0
			class    it
			send     4
			sal      local13
			send     8
code_13aa:
			lsg      monsterHealth
			ldi      0
			gt?     
			bnt      code_1582
			pushi    #init
			pushi    0
			pushi    823
			pushi    0
			class    it
			send     8
			jmp      code_1582
code_13c2:
			+ag      global366
			ldi      0
			sag      global470
			lsg      global366
			pushi    #at
			pushi    1
			lsg      arcadeLevel
			lal      local15
			send     6
			gt?     
			bnt      code_157c
			pushi    2
			pushi    0
			pushi    100
			callk    Random,  4
			push    
			lag      Night
			bnt      code_13ed
			ldi      10
			jmp      code_13ef
code_13ed:
			ldi      5
code_13ef:
			push    
			lsg      global366
			pushi    #at
			pushi    1
			lsg      arcadeLevel
			lal      local15
			send     6
			sub     
			mul     
			lt?     
			bnt      code_157c
			lsg      egoGait
			ldi      2
			eq?     
			bnt      code_1425
			pushi    2
			pushi    0
			ldi      8
			lsgi     egoStats
			callk    Random,  4
			push    
			ldi      200
			gt?     
			bnt      code_1425
			ldi      1
			sal      local9
			jmp      code_1582
code_1425:
			pushi    1
			pushi    380
			callb    Bset,  2
			ldi      1
			sal      local3
			ldi      17
			lagi     egoStats
			sag      global344
			pushi    2
			pushi    0
			pushi    100
			callk    Random,  4
			sat      temp0
			lag      Night
			bnt      code_1496
			lst      temp0
			ldi      30
			lt?     
			bnt      code_145c
			ldi      825
			sag      global365
			ldi      50
			sag      monsterHealth
			jmp      code_150d
code_145c:
			lst      temp0
			ldi      60
			lt?     
			bnt      code_1472
			ldi      830
			sag      global365
			ldi      250
			sag      monsterHealth
			jmp      code_150d
code_1472:
			lst      temp0
			ldi      80
			lt?     
			bnt      code_1488
			ldi      840
			sag      global365
			ldi      300
			sag      monsterHealth
			jmp      code_150d
code_1488:
			ldi      870
			sag      global365
			ldi      350
			sag      monsterHealth
			jmp      code_150d
code_1496:
			lsg      Day
			ldi      10
			gt?     
			bnt      code_14d7
			lst      temp0
			ldi      25
			lt?     
			bnt      code_14b3
			ldi      820
			sag      global365
			ldi      100
			sag      monsterHealth
			jmp      code_150d
code_14b3:
			lst      temp0
			ldi      60
			lt?     
			bnt      code_14c9
			ldi      835
			sag      global365
			ldi      300
			sag      monsterHealth
			jmp      code_150d
code_14c9:
			ldi      870
			sag      global365
			ldi      350
			sag      monsterHealth
			jmp      code_150d
code_14d7:
			lst      temp0
			ldi      60
			lt?     
			bnt      code_14ec
			ldi      820
			sag      global365
			ldi      100
			sag      monsterHealth
			jmp      code_150d
code_14ec:
			lst      temp0
			ldi      90
			lt?     
			bnt      code_1502
			ldi      835
			sag      global365
			ldi      300
			sag      monsterHealth
			jmp      code_150d
code_1502:
			ldi      870
			sag      global365
			ldi      350
			sag      monsterHealth
code_150d:
			lsg      global365
			ldi      825
			eq?     
			bnt      code_156c
			pushi    #init
			pushi    0
			pushi    822
			pushi    0
			pushi    #new
			pushi    0
			class    it
			send     4
			sal      local11
			send     8
			pushi    #init
			pushi    0
			pushi    822
			pushi    0
			pushi    #new
			pushi    0
			class    it
			send     4
			sal      local12
			send     8
			pushi    #init
			pushi    0
			pushi    822
			pushi    0
			pushi    #new
			pushi    0
			class    it
			send     4
			sal      local13
			send     8
			ldi      50
			sag      gABad2Health
			ldi      50
			sag      gABad3Health
			ldi      50
			sag      gABad4Health
			ldi      50
			sag      monsterHealth
code_156c:
			pushi    #init
			pushi    0
			pushi    822
			pushi    0
			class    it
			send     8
			jmp      code_1582
code_157c:
			pushi    0
			call     localproc_01ee,  0
code_1582:
			lsg      prevRoomNum
			dup     
			pushi    #west
			pushi    0
			lag      curRoom
			send     4
			eq?     
			bnt      code_15cc
			pushi    #posn
			pushi    2
			pushi    65521
			pushi    136
			pushi    273
			pushi    1
			pushi    90
			lag      ego
			send     14
			pushi    7
			lag      global365
			bnt      code_15b2
			ldi      1
			jmp      code_15b4
code_15b2:
			ldi      21
code_15b4:
			add     
			sal      local1
			ldi      136
			sal      local2
			pushi    #setScript
			pushi    1
			lofsa    enterRoomScr
			push    
			lag      curRoom
			send     6
			jmp      code_1914
code_15cc:
			dup     
			pushi    #east
			pushi    0
			lag      curRoom
			send     4
			eq?     
			bnt      code_1617
			pushi    #posn
			pushi    2
			pushi    335
			pushi    136
			pushi    273
			pushi    1
			pushi    270
			lag      ego
			send     14
			pushi    313
			lag      global365
			bnt      code_15fd
			ldi      1
			jmp      code_15ff
code_15fd:
			ldi      21
code_15ff:
			sub     
			sal      local1
			ldi      136
			sal      local2
			pushi    #setScript
			pushi    1
			lofsa    enterRoomScr
			push    
			lag      curRoom
			send     6
			jmp      code_1914
code_1617:
			dup     
			pushi    #north
			pushi    0
			lag      curRoom
			send     4
			eq?     
			bnt      code_1680
			pushi    332
			pushi    #y
			pushi    160
			pushi    #horizon
			pushi    0
			lag      curRoom
			send     4
			push    
			ldi      1
			sub     
			push    
			pushi    273
			pushi    1
			pushi    180
			lag      ego
			send     14
			ldi      160
			sal      local1
			pushi    #horizon
			pushi    0
			lag      curRoom
			send     4
			push    
			lag      global365
			bnt      code_1660
			ldi      1
			jmp      code_1662
code_1660:
			ldi      21
code_1662:
			add     
			sal      local2
			pushi    #start
			pushi    1
			pushi    0
			lofsa    enterRoomScr
			send     6
			pushi    #setScript
			pushi    1
			lofsa    enterRoomScr
			push    
			lag      curRoom
			send     6
			jmp      code_1914
code_1680:
			dup     
			pushi    #south
			pushi    0
			lag      curRoom
			send     4
			eq?     
			bnt      code_16ca
			pushi    #posn
			pushi    2
			pushi    160
			pushi    240
			pushi    273
			pushi    1
			pushi    0
			lag      ego
			send     14
			ldi      160
			sal      local1
			pushi    182
			lag      global365
			bnt      code_16b5
			ldi      1
			jmp      code_16b7
code_16b5:
			ldi      21
code_16b7:
			sub     
			sal      local2
			pushi    #setScript
			pushi    1
			lofsa    enterRoomScr
			push    
			lag      curRoom
			send     6
			jmp      code_1914
code_16ca:
			dup     
			ldi      810
			eq?     
			bnt      code_18ee
			lsg      global365
			ldi      850
			ne?     
			bnt      code_1914
			pushi    #x
			pushi    1
			pushi    160
			pushi    2
			pushi    1
			pushi    130
			lag      ego
			send     12
			lsg      battleResult
			dup     
			ldi      1
			eq?     
			bnt      code_1704
			pushi    #setScript
			pushi    1
			lofsa    egoDies
			push    
			lag      curRoom
			send     6
			jmp      code_18ea
code_1704:
			dup     
			ldi      2
			eq?     
			bnt      code_1725
			pushi    #dead
			pushi    1
			pushi    1
			class    it
			send     6
			pushi    #setScript
			pushi    1
			lofsa    combatMonsterDies
			push    
			lag      curRoom
			send     6
			jmp      code_18ea
code_1725:
			dup     
			ldi      3
			eq?     
			bnt      code_18ea
			ldi      0
			sag      global470
			pushi    #changeGait
			pushi    1
			pushi    1
			lag      ego
			send     6
			lsg      gGGNumber
			dup     
			pushi    #east
			pushi    0
			lag      curRoom
			send     4
			eq?     
			bnt      code_177d
			pushi    #setMotion
			pushi    3
			pushi    #new
			pushi    0
			pushi    1
			pushi    17
			callk    ScriptID,  2
			send     4
			push    
			pushi    335
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			lag      ego
			send     10
			pushi    #x
			pushi    1
			pushi    30
			class    it
			send     6
			jmp      code_1864
code_177d:
			dup     
			pushi    #west
			pushi    0
			lag      curRoom
			send     4
			eq?     
			bnt      code_17bc
			pushi    #setMotion
			pushi    3
			pushi    #new
			pushi    0
			pushi    1
			pushi    17
			callk    ScriptID,  2
			send     4
			push    
			pushi    65521
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			lag      ego
			send     10
			pushi    #x
			pushi    1
			pushi    290
			class    it
			send     6
			jmp      code_1864
code_17bc:
			dup     
			pushi    #south
			pushi    0
			lag      curRoom
			send     4
			eq?     
			bnt      code_180c
			pushi    #setMotion
			pushi    3
			pushi    #new
			pushi    0
			pushi    1
			pushi    17
			callk    ScriptID,  2
			send     4
			push    
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    240
			lag      ego
			send     10
			pushi    1
			pushi    1
			pushi    2
			pushi    0
			pushi    1
			callk    Random,  4
			bnt      code_1801
			ldi      290
			jmp      code_1803
code_1801:
			ldi      30
code_1803:
			push    
			class    it
			send     6
			jmp      code_1864
code_180c:
			dup     
			pushi    #north
			pushi    0
			lag      curRoom
			send     4
			eq?     
			bnt      code_1864
			pushi    322
			pushi    #z
			pushi    #new
			pushi    0
			pushi    1
			pushi    17
			callk    ScriptID,  2
			send     4
			push    
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #horizon
			pushi    0
			lag      curRoom
			send     4
			push    
			ldi      1
			sub     
			push    
			lag      ego
			send     10
			pushi    1
			pushi    1
			pushi    2
			pushi    0
			pushi    1
			callk    Random,  4
			bnt      code_185c
			ldi      290
			jmp      code_185e
code_185c:
			ldi      30
code_185e:
			push    
			class    it
			send     6
code_1864:
			toss    
			lsg      global365
			ldi      825
			eq?     
			bnt      code_18cc
			lsg      gABad2Health
			ldi      0
			gt?     
			bnt      code_188e
			pushi    #init
			pushi    0
			pushi    821
			pushi    0
			pushi    #new
			pushi    0
			class    it
			send     4
			sal      local11
			send     8
code_188e:
			lsg      gABad3Health
			ldi      0
			gt?     
			bnt      code_18ad
			pushi    #init
			pushi    0
			pushi    821
			pushi    0
			pushi    #new
			pushi    0
			class    it
			send     4
			sal      local12
			send     8
code_18ad:
			lsg      gABad4Health
			ldi      0
			gt?     
			bnt      code_18cc
			pushi    #init
			pushi    0
			pushi    821
			pushi    0
			pushi    #new
			pushi    0
			class    it
			send     4
			sal      local13
			send     8
code_18cc:
			lsg      monsterHealth
			ldi      0
			gt?     
			bnt      code_18e1
			pushi    #init
			pushi    0
			pushi    821
			pushi    0
			class    it
			send     8
code_18e1:
			pushi    #handsOn
			pushi    0
			lag      theGame
			send     4
code_18ea:
			toss    
			jmp      code_1914
code_18ee:
			pushi    #posn
			pushi    2
			pushi    160
			pushi    120
			lag      ego
			send     8
			pushi    #start
			pushi    1
			pushi    2
			lofsa    enterRoomScr
			send     6
			pushi    #setScript
			pushi    1
			lofsa    enterRoomScr
			push    
			lag      curRoom
			send     6
code_1914:
			toss    
			lag      Night
			not     
			bnt      code_1946
			pushi    1
			pushi    35
			callb    Btst,  2
			not     
			bnt      code_1946
			lag      global365
			not     
			bnt      code_1946
			pushi    2
			pushi    0
			pushi    100
			callk    Random,  4
			push    
			ldi      30
			lt?     
			bnt      code_1946
			pushi    #init
			pushi    0
			lofsa    bird
			send     4
code_1946:
			pushi    #number
			pushi    0
			lag      theMusic
			send     4
			push    
			ldi      556
			eq?     
			bnt      code_195f
			pushi    #client
			pushi    1
			pushSelf
			lag      theMusic
			send     6
code_195f:
			ret     
		)
	)
	
	(method (dispose)
		(local15 dispose:)
		(local16 dispose:)
		(local18 dispose:)
		(local17 dispose:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(10
					(curRoom setScript: sJumpUp)
				)
				(21
					(curRoom setScript: (ScriptID 32) 0 21)
				)
				(4
					(if (> ((User curEvent?) y?) (curRoom horizon?))
						(curRoom setScript: getRocks)
					else
						(super doVerb: theVerb &rest)
					)
				)
				(37
					(if (> ((inventory at: 5) amount?) 1)
						(curRoom setScript: (ScriptID 32) 0 37)
						(++ global478)
					else
						(messager say: 12 6 9 0 0 50)
					)
				)
				(86
					(if (>= [egoStats 19] [spellCost 6])
						(curRoom setScript: (ScriptID 32) 0 86)
					)
					(return 1)
				)
				(88
					(if (>= [egoStats 19] [spellCost 8])
						(curRoom setScript: (ScriptID 32) 0 88)
					)
					(return 1)
				)
				(93
					(if (>= [egoStats 19] [spellCost 13])
						(curRoom setScript: (ScriptID 32) 0 93)
					)
					(return 1)
				)
				(79
					(if (>= [egoStats 19] [spellCost 14])
						(curRoom setScript: (ScriptID 32) 0 79)
					)
					(return 1)
				)
				(80
					(messager say: 0 80 0 0 0 50)
					(return 1)
				)
				(82
					(messager say: 0 82 0 0 0 50)
					(return 1)
				)
				(94
					(messager say: 0 94 0 0 0 50)
					(return 1)
				)
				(83
					(if (>= [egoStats 19] [spellCost 3])
						(curRoom setScript: (ScriptID 12) 0 83)
						(if
							(and
								(cast contains: it)
								(it script?)
								((it script?) isKindOf: sChaseHim)
								(!= global365 830)
								(!= global365 825)
							)
							((it script?) register: theVerb changeState: 1)
						)
					)
					(return 1)
				)
				(87
					(if (>= [egoStats 19] [spellCost 7])
						(curRoom setScript: (ScriptID 37))
					)
					(return 1)
				)
				(89
					((ScriptID 31 0)
						init: (ego x?) (ego y?) (/ (* (ego scaleY?) 80) 100)
					)
				)
				(85
					(if
						(and
							(cast contains: it)
							((it script?) isKindOf: sChaseHim)
							(!= global365 830)
							(!= global365 825)
						)
						((it script?) register: theVerb changeState: 1)
					else
						(messager say: 0 85 0 0 0 50)
					)
				)
				(else 
					(Glory pragmaFail:)
					(return 1)
				)
			)
		)
	)
	
	(method (cue)
		(if (== (theMusic number?) 556)
			(theMusic number: 557 setLoop: -1 play:)
		)
	)
	
	(method (newRoom n)
		(if (!= n 810)
			(if global365
				(if
					(not
						(if
							(or
								(> monsterHealth 0)
								(> gABad2Health 0)
								(> gABad3Health 0)
							)
						else
							(> gABad4Health 0)
						)
					)
					(localproc_01ee)
					(= global470 (= global365 (= global366 0)))
					(= gABad2Health (= gABad3Health (= gABad4Health 0)))
				)
			)
		else
			(theMusic client: 0)
		)
		(super newRoom: n)
	)
)

(instance enterRoomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 3)
			)
			(1
				(if local9 (messager say: 0 0 5 0 0 50))
				(ego setMotion: PolyPath local1 local2 self)
			)
			(2
				(if (and global365 (not local3))
					(switch prevRoomNum
						((curRoom west?)
							(if (curRoom east?)
								(ego setMotion: ((ScriptID 17) new:) 335 (ego y?))
							)
						)
						((curRoom east?)
							(if (curRoom west?)
								(ego setMotion: ((ScriptID 17) new:) -15 (ego y?))
							)
						)
						((curRoom north?)
							(if (curRoom south?)
								(ego setMotion: ((ScriptID 17) new:) (ego x?) 240)
							)
						)
						((curRoom south?)
							(if (curRoom north?)
								(ego
									setMotion: ((ScriptID 17) new:) (ego x?) (- (curRoom horizon?) 1)
								)
							)
						)
					)
				)
				(theGame handsOn:)
				(self start: 0 dispose:)
			)
		)
	)
)

(instance egoDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(combatMonster y: 130)
				(switch global365
					(825
						(combatMonster
							view: 825
							loop: 3
							setCycle: Fwd
							cel: 2
							x: 195
							y: 100
							init:
						)
					)
					(840
						(combatMonster
							view: 840
							loop: 1
							setCycle: Fwd
							x: 205
							y: 120
							init:
						)
					)
					(870
						(combatMonster view: 870 loop: 1 cel: 6 x: 210 init:)
					)
					(820
						(combatMonster view: 820 loop: 1 x: 185 init:)
					)
					(830
						(combatMonster
							view: 830
							loop: 1
							cel: 5
							x: 185
							y: 128
							init:
						)
					)
					(835
						(combatMonster view: 835 loop: 1 x: 190 y: 130 init:)
					)
					(850
						(combatMonster
							view: 850
							loop: 3
							x: 180
							y: 140
							loop: 5
							init:
						)
					)
				)
				(ego
					view: 43
					setLoop: 2
					cel: 0
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(1
				(switch global365
					(825 (EgoDead 11 50))
					(840 (EgoDead 12 50))
					(870 (EgoDead 13 50))
					(820 (EgoDead 14 50))
					(830 (EgoDead 15 50))
					(835 (EgoDead 16 50))
					(850 (EgoDead 17 50))
					(else  (EgoDead))
				)
			)
		)
	)
)

(instance combatMonsterDies of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch global365
					(820
						(combatMonster view: 822 noun: 43)
						(it noun: 43)
					)
					(825
						(combatMonster view: 827 noun: 31)
						(it noun: 31)
					)
					(830
						(combatMonster view: 832 noun: 32)
						(it noun: 32)
					)
					(835
						(combatMonster view: 837 noun: 33)
						(it noun: 33)
					)
					(870
						(combatMonster view: 872 noun: 37)
						(it noun: 37)
					)
					(840
						(combatMonster view: 842 noun: 34)
						(it noun: 34)
					)
				)
				(combatMonster setCel: 0 setLoop: 0 1 x: 160 y: 135 init:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					x: 160
					y: 130
					view: 13
					setCel: 0
					setLoop: 1
					cycleSpeed: defaultCycles
				)
				(= cycles 1)
			)
			(1
				(theMusic number: 107 setLoop: 1 play:)
				(combatMonster setCycle: End)
				(= cycles 5)
			)
			(2
				(= temp0
					(/
						(CelWide
							(combatMonster view?)
							(combatMonster loop?)
							(combatMonster cel?)
						)
						2
					)
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								(- (- (combatMonster x?) temp0) 6)
								(- (combatMonster y?) 5)
								(+ 6 temp0 (combatMonster x?))
								(- (combatMonster y?) 5)
								(+ 6 temp0 (combatMonster x?))
								(+ 3 (combatMonster y?))
								(- (- (combatMonster x?) temp0) 6)
								(+ 3 (combatMonster y?))
							yourself:
						)
				)
				(combatMonster
					approachX: (combatMonster x?)
					approachY: (- (combatMonster y?) 5)
					approachVerbs: 4
				)
				(ego setCycle: End)
				(= ticks 240)
			)
			(3
				(switch global365
					(825 (ego solvePuzzle: 480 2 1))
					(820 (ego solvePuzzle: 481 2 1))
					(830 (ego solvePuzzle: 482 2 1))
					(835 (ego solvePuzzle: 483 2 1))
					(840 (ego solvePuzzle: 484 2 1))
					(870 (ego solvePuzzle: 486 2 1))
				)
				(if
					(not
						(if
							(or
								(> monsterHealth 0)
								(> gABad2Health 0)
								(> gABad3Health 0)
							)
						else
							(> gABad4Health 0)
						)
					)
					(if Night
						(theMusic number: 557 setLoop: -1 play:)
					else
						(theMusic number: 558 setLoop: -1 play:)
					)
					(= global470 (= global365 (= global366 0)))
					(= gABad2Health (= gABad3Health (= gABad4Health 0)))
				)
				(ego
					setSpeed: gTheObj_2CycleSpeed
					normalize: 2
					addHonor: 5
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance monsterDies of Script
	(properties)
	
	(method (doit &tmp [temp0 3])
		(super doit: &rest)
		(if
			(and
				(not state)
				(< (ego distanceTo: it) 25)
				(< (client z?) 1000)
				(== (ego z?) 0)
			)
			(if (< monsterHealth 1) (= monsterHealth 1))
			(curRoom newRoom: 810)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(it setCycle: Walk setMotion: PolyPath 160 135 self)
			)
			(1
				(if (!= global365 825)
					(= temp0
						(/ (CelWide (it view?) (it loop?) (it cel?)) 2)
					)
					(curRoom
						addObstacle:
							((Polygon new:)
								type: 2
								init:
									(- (- (it x?) temp0) 6)
									(- (it y?) 5)
									(+ 6 temp0 (it x?))
									(- (it y?) 5)
									(+ 6 temp0 (it x?))
									(+ 3 (it y?))
									(- (- (it x?) temp0) 6)
									(+ 3 (it y?))
								yourself:
							)
					)
				)
				(it
					view: (+ (it view?) 2)
					approachX: (it x?)
					approachY: (- (it y?) 5)
					approachVerbs: 4
					setCel: 0
					setLoop: 0
					setCycle: End self
					z: 0
					dead: 1
				)
			)
			(2
				(switch global365
					(825 (ego solvePuzzle: 480 2 1))
					(820 (ego solvePuzzle: 481 2 1))
					(830 (ego solvePuzzle: 482 2 1))
					(835 (ego solvePuzzle: 483 2 1))
					(840 (ego solvePuzzle: 484 2 1))
					(870 (ego solvePuzzle: 486 2 1))
				)
				(if
					(not
						(if
							(or
								(> monsterHealth 0)
								(> gABad2Health 0)
								(> gABad3Health 0)
							)
						else
							(> gABad4Health 0)
						)
					)
					(= global470 (= global365 (= global366 0)))
					(= gABad2Health (= gABad3Health (= gABad4Health 0)))
					(localproc_01ee)
				)
				(ego addHonor: 5)
				(self dispose:)
			)
		)
	)
)

(instance monsterDies2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: PolyPath 155 125 self)
			)
			(1
				(client
					view: (+ (client view?) 2)
					approachX: (client x?)
					approachY: (client y?)
					approachVerbs: 4
					setCel: 0
					setLoop: 0
					setCycle: End self
					z: 0
					dead: 1
				)
			)
			(2
				(if
					(not
						(if
							(or
								(> monsterHealth 0)
								(> gABad2Health 0)
								(> gABad3Health 0)
							)
						else
							(> gABad4Health 0)
						)
					)
					(= global470 (= global365 (= global366 0)))
					(= gABad2Health (= gABad3Health (= gABad4Health 0)))
					(localproc_01ee)
				)
				(ego addHonor: 5)
				(self dispose:)
			)
		)
	)
)

(instance monsterDies3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: PolyPath 165 140 self)
			)
			(1
				(client
					view: (+ (client view?) 2)
					approachX: (client x?)
					approachY: (client y?)
					approachVerbs: 4
					setCel: 0
					setLoop: 0
					setCycle: End self
					z: 0
					dead: 1
				)
			)
			(2
				(if
					(not
						(if
							(or
								(> monsterHealth 0)
								(> gABad2Health 0)
								(> gABad3Health 0)
							)
						else
							(> gABad4Health 0)
						)
					)
					(= global470 (= global365 (= global366 0)))
					(= gABad2Health (= gABad3Health (= gABad4Health 0)))
					(localproc_01ee)
				)
				(ego addHonor: 5)
				(self dispose:)
			)
		)
	)
)

(instance monsterDies4 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: PolyPath 158 138 self)
			)
			(1
				(client
					view: (+ (client view?) 2)
					approachX: (client x?)
					approachY: (client y?)
					approachVerbs: 4
					setCel: 0
					setLoop: 0
					setCycle: End self
					z: 0
					dead: 1
				)
			)
			(2
				(if
					(not
						(if
							(or
								(> monsterHealth 0)
								(> gABad2Health 0)
								(> gABad3Health 0)
							)
						else
							(> gABad4Health 0)
						)
					)
					(= global470 (= global365 (= global366 0)))
					(= gABad2Health (= gABad3Health (= gABad4Health 0)))
					(localproc_01ee)
				)
				(ego addHonor: 5)
				(self dispose:)
			)
		)
	)
)

(instance getRocks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (ego looper?)
					((ego looper?) dispose:)
					(ego looper: 0)
				)
				(ego
					setMotion: 0
					view: 4
					loop: (mod (ego loop?) 2)
					setCycle: End self
				)
				(= register (Narrator y?))
			)
			(1
				(Narrator y: 20)
				(if global478
					(messager say: 12 6 2 0 self 50)
				else
					(messager say: 12 6 1 0 self 50)
				)
			)
			(2
				(Narrator y: register)
				(ego setCycle: Beg self)
			)
			(3
				(ego normalize:)
				(if global478
					(ego get: 5 global478)
					(= global478 0)
				else
					(ego get: 6 (Random 2 5))
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance bird of TargActor
	(properties
		noun 10
		modNum 50
		view 910
		signal $6001
	)
	
	(method (init &tmp temp0)
		(= temp0 0)
		(while (<= temp0 88)
			(if (== curRoomNum (local17 at: temp0))
				(= local4 (local17 at: (+ temp0 1)))
				(= local5
					(+
						(local17 at: (+ temp0 2))
						(local17 at: (+ temp0 3))
					)
				)
				(= local6 (local17 at: (+ temp0 3)))
			)
			(= temp0 (+ temp0 4))
		)
		(if local4
			(super init: &rest)
			(self
				x: -100
				y: local5
				z: local6
				setStep: 8 2
				setScript: sBird
			)
		)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 2) (screechFX play:))
			((Message msgSIZE 50 noun theVerb 0 1) (messager say: noun theVerb 0 0 0 50))
			(else (forest doVerb: theVerb))
		)
	)
	
	(method (getHurt)
		(screechFX play:)
		(sBird register: 1)
		(if
		(and (>= (sBird state?) 3) (< (sBird state?) 7))
			(sBird changeState: 7)
		)
	)
)

(instance sBird of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 0 (= seconds 1))
			(1
				1
				(client
					y: (- (+ local5 25) (Random 0 50))
					setScaler: ego
				)
				(= seconds (Random 1 12))
			)
			(2
				2
				(if (Random 0 1)
					(client x: -20 setLoop: 0)
					(= temp0 (- (/ (client scaleX?) 8)))
				else
					(client x: 340 setLoop: 1)
					(= temp0 (/ (client scaleX?) 8))
				)
				(if (Random 0 1) (= register 1) (screechFX play:))
				(client
					setCycle: Fwd
					setMotion:
						MoveTo
						(+ local4 temp0)
						(+ local5 (/ (client scaleX?) 18))
						self
				)
			)
			(3
				3
				(client
					setLoop: (+ (client loop?) 2)
					setCel: 0
					setCycle: End self
				)
			)
			(4 4 (= seconds (Random 3 6)))
			(5
				5
				(client posn: local4 local5)
				(if (< (Random 0 100) 30)
					(self cue:)
				else
					(if (< (client loop?) 6)
						(client setLoop: (+ (client loop?) 4))
					)
					(client setCel: 0 setCycle: End)
					(self changeState: (-- state))
				)
			)
			(6
				6
				(= temp0 (Random 4 5))
				(client setLoop: temp0 setCycle: End self)
			)
			(7
				7
				(if (not register)
					(screechFX play:)
				else
					(= register 0)
				)
				(client
					setLoop: (if (mod (client loop?) 2) 1 else 0)
					setCycle: Fwd
					setMotion:
						MoveTo
						(if (mod (client loop?) 2) -20 else 340)
						(- (+ (client y?) (Random 0 30)) 15)
						self
				)
			)
			(8 8 (self dispose:))
		)
	)
)

(instance sChaseHim of Script
	(properties)
	
	(method (init)
		(= ticks 0)
		(super init: &rest)
	)
	
	(method (doit &tmp [temp0 3])
		(super doit: &rest)
		(cond 
			(
				(and
					(< (ego distanceTo: client) 25)
					(< (client z?) 1000)
					(== (ego z?) 0)
				)
				(curRoom newRoom: 810)
			)
			(
			(and (not (ego isNotHidden:)) (!= global365 825)) (client setScript: sLookAround))
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(client setCycle: Walk setMotion: PChase ego 20)
			)
			(1
				(client setMotion: 0)
				(switch global365
					(820 (client setCycle: End))
					(835 (client setCycle: Fwd))
				)
				(switch register
					(85
						(= seconds (+ 2 (/ [egoStats 25] 20)))
						(= register 0)
						(= local0 1)
					)
					(83
						(= seconds (+ 2 (/ [egoStats 23] 30)))
						(= register 0)
					)
				)
			)
			(2
				(= local0 0)
				(self changeState: 0)
			)
		)
	)
)

(instance sRevenant of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (== (ego z?) 0) (< (ego distanceTo: client) 25))
			(curRoom newRoom: 810)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: End self))
			(1
				(client
					setLoop: (+ (client loop?) 2)
					setCel: 0
					setCycle: End self
				)
			)
			(2
				(theMusic number: 831 setLoop: -1 play:)
				(client
					view: 830
					setCycle: Walk
					setLooper: Grooper
					setScript: sChaseHim
				)
			)
		)
	)
)

(instance sLookAround of Script
	(properties)
	
	(method (doit &tmp [temp0 3])
		(super doit: &rest)
		(if (and (ego isNotHidden:) (< state 3))
			(client setScript: sChaseHim)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: 0)
				(switch global365
					(820
						(monsterRoar number: 822 play: self)
					)
					(830
						(monsterRoar number: 832 play: self)
					)
					(835
						(monsterRoar number: 837 play: self)
						(client setCycle: Fwd)
					)
					(870
						(monsterRoar number: 873 play: self)
					)
					(840
						(monsterRoar number: 843 play: self)
					)
				)
			)
			(1 (= ticks 60))
			(2
				(client
					setCycle: Walk
					setMotion: PolyPath (client monStartX?) (client monStartY?) self
				)
			)
			(3
				(if (== global365 830)
					(= local22 1)
					(client setHeading: 225 self)
				else
					(self cue:)
				)
				(localproc_01ee)
				(= global470 (= global365 (= global366 0)))
			)
			(4
				(if local22
					(client
						view: 833
						setLoop: 3
						setCel: 12
						setCycle: Beg self
					)
				else
					(self cue:)
				)
			)
			(5
				(if local22
					(client setLoop: 1 setCel: 8 setCycle: Beg self)
				else
					(self cue:)
				)
			)
			(6
				(client hide:)
				(= caller client)
				(self dispose:)
			)
		)
	)
)

(instance sJumpUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= register (ego loop?))
				(ego
					useStamina: 8
					useSkill: 15
					view: 30
					setLoop:
						(switch register
							(0 2)
							(1 3)
							(2 2)
							(3 4)
							(else  (- register 2))
						)
					setCel: 0
					setCycle: End self
				)
			)
			(1
				(ego normalize: register)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sSearchMonster of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0
				(ego view: 4 setCel: 0 setLoop: 2 1 setCycle: End self)
			)
			(1
				(if local20
					(messager say: 29 4 4 0 self 50)
				else
					(= local20 1)
					(switch (it noun?)
						(34
							(= newStr (Str new:))
							(Message msgGET 50 (it noun?) 4 4 1 (newStr data?))
							(= temp0 (+ 3 (Random 0 5)))
							(= temp1 (+ 10 (Random 0 15)))
							(newStr format: (newStr data?) temp0 temp1)
							(= kopeks (+ kopeks temp1))
							(ego get: 0 temp0)
							(if (& msgType $0002)
								(= temp2 (IntArray with: 0 0 0 0 0))
								(Message 9 (temp2 data?))
							)
							(narrator say: newStr self temp2)
						)
						(32
							(= newStr (Str new:))
							(Message msgGET 50 (it noun?) 4 4 1 (newStr data?))
							(= temp1 (+ 15 (Random 25)))
							(newStr format: (newStr data?) temp1)
							(= kopeks (+ kopeks temp1))
							(if (& msgType $0002)
								(= temp2 (IntArray with: 0 0 0 0 0))
								(Message 9 (temp2 data?))
							)
							(narrator say: newStr self temp2)
						)
						(else 
							(messager say: (it noun?) 4 4 0 self 50)
						)
					)
				)
			)
			(2
				(if newStr (newStr dispose:) (= newStr 0))
				(ego setCycle: Beg self)
			)
			(3
				(ego normalize: 4)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance monsterRoar of Sound
	(properties)
)

(instance screechFX of Sound
	(properties
		number 976
	)
)
