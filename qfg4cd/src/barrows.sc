;;; Sierra Script 1.0 - (do not remove this comment)
(script# 53)
(include sci.sh)
(use Main)
(use TargFeature)
(use EgoDead)
(use Polygon)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	barrows 0
)

(local
	local0
	local1
	local2
	local3
)
(instance barrows of Rgn
	(properties)
	
	(method (init)
		(Bset 35)
		(if
			(and
				(!= (theMusic number?) 556)
				(!= (theMusic number?) 557)
			)
			(theMusic number: 556 setLoop: 1 play: (ScriptID 50))
		)
		(= global366 0)
		(= global365 850)
		(mound posn: 180 140 init: approachVerbs: 4)
		(if
			(and
				(or
					Night
					(and (== global365 850) (== prevRoomNum 810))
				)
				(not (Btst 66))
			)
			(if (== prevRoomNum 810)
				(= local0 1)
				(if (< (ego x?) (mound x?))
					(ego x: (- (- (mound x?) 30) 1) y: 135 setHeading: 270)
				else
					(ego x: (+ (mound x?) 30 1) y: 135 setHeading: 90)
				)
				(switch battleResult
					(1
						(curRoom setScript: (ScriptID 50 2))
					)
					(2
						(wraith setScript: monsterDies)
					)
					(3
						(if (< (ego x?) (mound x?))
							(ego x: (- (mound x?) 100))
						else
							(ego x: (+ (mound x?) 100))
						)
						(sWrithe start: 4)
						(wraith cel: (wraith lastCel:) init: setScript: sWrithe)
						(theGame handsOn:)
					)
				)
			else
				(wraith posn: (mound x?) (mound y?) init: hide:)
				(if (== curRoomNum 575)
					(= monsterHealth 400)
				else
					(= monsterHealth 300)
				)
				((ScriptID 50 1) caller: wraith)
			)
		)
		(super init: &rest)
		(if (!= prevRoomNum 810) (Bset 380))
	)
	
	(method (dispose)
		(Bclr 66)
		(Bclr 65)
		(Bclr 35)
		(super dispose: &rest)
	)
	
	(method (newRoom n)
		(if (and (!= n 810) (== global365 850))
			(= global365 0)
		)
		(if (== n 810) (theMusic client: 0))
		(super newRoom: n)
	)
)

(instance mound of Prop
	(properties
		noun 2
		approachY 140
		view 854
		signal $6000
	)
	
	(method (init)
		(self approachX: 145)
		(super init: &rest)
		(SetNowSeen self)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						(- nsLeft 5)
						(- nsBottom 5)
						(+ nsRight 5)
						(- nsBottom 5)
						(+ nsRight 5)
						nsBottom
						(- nsLeft 5)
						nsBottom
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 4)
				(theGame handsOff:)
				(if (cast contains: wraith)
					(= global365 850)
					(curRoom newRoom: 810)
				else
					(curRoom setScript: sSearch)
				)
			)
			((Message msgSIZE 53 noun theVerb 0 1) (messager say: noun theVerb 0 0 0 53))
			(else ((ScriptID 50) doVerb: theVerb))
		)
	)
)

(instance spark of Prop
	(properties
		x 180
		y 140
		view 854
		loop 1
	)
	
	(method (doVerb theVerb)
		((ScriptID 50) doVerb: theVerb)
	)
)

(instance wraith of TargProp
	(properties
		noun 3
		view 850
		signal $5001
	)
	
	(method (init)
		(super init: &rest)
		(= x (mound x?))
		(= y (mound y?))
	)
	
	(method (doit)
		(if (> monsterHealth 0)
			(if
			(and (not local0) (< (ego distanceTo: self 65) 110))
				(= local0 1)
				(sWrithe start: 0)
				(self setScript: sWrithe)
			)
			(if local0
				(if (> (++ local2) 5)
					(= local2 0)
					(ego takeDamage: 1)
				)
				(cond 
					((< (ego x?) 160)
						(cond 
							((== loop 4) (wraith setLoop: 5 1))
							((== loop 2) (wraith setLoop: 3 1))
						)
					)
					((> (ego x?) 160)
						(cond 
							((== loop 5) (wraith setLoop: 4 1))
							((== loop 3) (wraith setLoop: 2 1))
						)
					)
					((< (ego y?) 130)
						(cond 
							((== loop 3) (wraith setLoop: 5 1))
							((== loop 2) (wraith setLoop: 4 1))
						)
					)
					((== loop 5) (wraith setLoop: 3 1))
					((== loop 4) (wraith setLoop: 2 1))
				)
			)
			(if (< (ego distanceTo: self 65) 30)
				(= global365 850)
				(curRoom newRoom: 810)
			)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(if (Message msgSIZE 53 noun theVerb 0 1)
			(messager say: noun theVerb 0 0 0 53)
		else
			((ScriptID 50) doVerb: theVerb)
		)
	)
	
	(method (cue)
		(curRoom setScript: sStartWraith)
	)
	
	(method (getHurt param1 param2)
		(if
			(and
				(!= param1 37)
				(!= param1 21)
				(!= param1 79)
				(!= script monsterDies)
				(not local3)
				(<= (= monsterHealth (- monsterHealth (/ param2 2))) 0)
			)
			(= local3 1)
			(self setScript: monsterDies)
		)
	)
)

(instance wraithEffect of Actor
	(properties
		fixPriority 1
		view 21
		signal $7001
	)
	
	(method (init)
		(self
			setPri: (+ (wraith priority?) 1)
			setMotion:
				MoveTo
				(ego x?)
				(- (- (ego y?) (/ (ego scaleY?) 4)) (ego z?))
				self
		)
		(wraithFX
			number: (if (== (sWrithe register?) 1) 974 else 983)
			play:
		)
		(super init: &rest)
		(SetNowSeen self)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (> y (wraith y?))
			(self setPri: y)
		else
			(self setPri: (+ (wraith priority?) 1))
		)
	)
	
	(method (doVerb theVerb)
		((ScriptID 50) doVerb: theVerb)
	)
	
	(method (cue &tmp temp0)
		(if (or (== loop 4) (== loop 13))
			(if (ego onMe: x y)
				(= temp0
					(*
						(if (== (sWrithe register?) 1) 12 else 16)
						(switch curRoomNum
							(566 1)
							(567 1)
							(576 2)
							(575 4)
							(590 3)
							(else  1)
						)
					)
				)
				(if protectTimer (= temp0 (/ temp0 2)))
				(if resistTimer
					(= temp0
						(/ (* temp0 (- 100 (/ [egoStats 39] 7))) 100)
					)
				)
				(if
				(and (not (Btst 8)) (not (ego takeDamage: temp0)))
					(EgoDead 16 53)
				)
			)
			(self
				setLoop: (if (== loop 4) 10 else 14) 1
				setCel: 0
				setCycle: End self
			)
		else
			(if (not local3) (sWrithe changeState: 4))
			(self dispose:)
		)
	)
)

(instance sStartWraith of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 1 6 7 0 self 53)
			)
			(1
				(if (and (== heroType 3) (< [egoStats 14] 50))
					(messager say: 1 6 8 0 self 53)
				else
					(self cue:)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sWrithe of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (curRoom script?)) (ego setMotion: 0))
				(spark
					init:
					x: 180
					y: 140
					ignoreActors:
					setCycle: End self
				)
			)
			(1
				(spark setLoop: 2 1 setCel: 0 setCycle: End self)
			)
			(2
				(spark dispose:)
				(mound
					signal: (| (mound signal?) $0001)
					setCycle: End self
				)
			)
			(3
				(mound signal: (& (mound signal?) $fffe))
				(wraith
					x: 180
					show:
					setLoop: (if (< (ego x?) 160) 1 else 0) 1
					setCel: 0
					setCycle: End self
				)
			)
			(4
				(wraith
					cycleSpeed: 8
					setLoop:
						(cond 
							((< (ego x?) 160) (if (< (ego y?) 130) 5 else 3))
							((< (ego y?) 130) 4)
							(else 2)
						)
						1
					setCycle: Fwd
				)
				(= cycles (Random 5 15))
			)
			(5
				(if (< (Random 0 100) 75)
					(= register 1)
				else
					(= register 0)
				)
				(wraithEffect
					moveSpeed: 0
					setStep: 8 5
					x:
						(if (mod (wraith loop?) 2)
							(- (wraith x?) 20)
						else
							(+ (wraith x?) 20)
						)
					y: (- (wraith y?) 53)
					illegalBits: 0
					setLoop: (if register 4 else 13) 1
					setCycle: Fwd
					ignoreActors:
					init:
				)
			)
		)
	)
)

(instance monsterDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not (cast contains: wraith)) (wraith init:))
				(wraith
					signal: (| (wraith signal?) $0001)
					view: 850
					setLoop: (if (mod (wraith loop?) 2) 1 else 0) 1
					setCel: 13
					setCycle: Beg self
				)
				(mound
					signal: (| (mound signal?) $0001)
					setCycle: Beg self
				)
			)
			(1 (wraithFX number: 853 play:))
			(2
				(mound signal: (& (mound signal?) $fffe))
				(wraith signal: (& (wraith signal?) $fffe))
				(switch curRoomNum
					(566
						(Bset 60)
						(ego solvePuzzle: 488 2 1 addHonor: 25)
					)
					(567
						(Bset 61)
						(ego solvePuzzle: 488 2 1 addHonor: 25)
					)
					(575
						(Bset 62)
						(ego
							solvePuzzle: 479 15 1
							solvePuzzle: 516 6 4
							solvePuzzle: 528 15 8
							addHonor: 150
						)
					)
					(576
						(Bset 63)
						(ego solvePuzzle: 488 2 1 addHonor: 25)
					)
					(590
						(Bset 64)
						(ego solvePuzzle: 488 2 1 addHonor: 25)
					)
				)
				(messager say: 3 6 12 0 self 53)
			)
			(3
				(wraith dispose:)
				(= global365 0)
				(= global366 0)
				(Bset 66)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sSearch of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_0c83
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #y
			pushi    0
			lofsa    mound
			send     4
			gt?     
			bnt      code_0c29
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #x
			pushi    0
			lofsa    mound
			send     4
			gt?     
			bnt      code_0c22
			ldi      1
			sat      temp0
			jmp      code_0c46
code_0c22:
			ldi      0
			sat      temp0
			jmp      code_0c46
code_0c29:
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #x
			pushi    0
			lofsa    mound
			send     4
			gt?     
			bnt      code_0c42
			ldi      3
			sat      temp0
			jmp      code_0c46
code_0c42:
			ldi      2
			sat      temp0
code_0c46:
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			sat      temp1
			pushi    #cycleSpeed
			pushi    0
			lag      ego
			send     4
			sal      local1
			pushi    #view
			pushi    1
			pushi    4
			pushi    333
			pushi    1
			pushi    0
			pushi    254
			pushi    2
			lst      temp0
			pushi    1
			pushi    265
			pushi    1
			lsg      defaultCycles
			pushi    236
			pushi    2
			class    End
			push    
			pushSelf
			lag      ego
			send     34
			jmp      code_105e
code_0c83:
			dup     
			ldi      1
			eq?     
			bnt      code_0d11
			lsg      curRoomNum
			dup     
			ldi      566
			eq?     
			bnt      code_0c9d
			pushi    1
			pushi    55
			callb    Btst,  2
			jmp      code_0cdd
code_0c9d:
			dup     
			ldi      567
			eq?     
			bnt      code_0cae
			pushi    1
			pushi    56
			callb    Btst,  2
			jmp      code_0cdd
code_0cae:
			dup     
			ldi      575
			eq?     
			bnt      code_0cbf
			pushi    1
			pushi    57
			callb    Btst,  2
			jmp      code_0cdd
code_0cbf:
			dup     
			ldi      576
			eq?     
			bnt      code_0ccf
			pushi    1
			pushi    58
			callb    Btst,  2
			jmp      code_0cdd
code_0ccf:
			dup     
			ldi      590
			eq?     
			bnt      code_0cdd
			pushi    1
			pushi    59
			callb    Btst,  2
code_0cdd:
			toss    
			bnt      code_0cfa
			pushi    #say
			pushi    6
			pushi    2
			pushi    4
			pushi    10
			pushi    0
			pushSelf
			pushi    53
			lag      messager
			send     16
			ipToa    state
			jmp      code_105e
code_0cfa:
			pushi    #say
			pushi    6
			pushi    2
			pushi    4
			pushi    11
			pushi    0
			pushSelf
			pushi    53
			lag      messager
			send     16
			jmp      code_105e
code_0d11:
			dup     
			ldi      2
			eq?     
			bnt      code_0e18
			lsg      curRoomNum
			dup     
			ldi      566
			eq?     
			bnt      code_0d38
			pushi    #say
			pushi    6
			pushi    1
			pushi    6
			pushi    2
			pushi    0
			pushSelf
			pushi    53
			lag      messager
			send     16
			jmp      code_0e14
code_0d38:
			dup     
			ldi      567
			eq?     
			bnt      code_0d56
			pushi    #say
			pushi    6
			pushi    1
			pushi    6
			pushi    1
			pushi    0
			pushSelf
			pushi    53
			lag      messager
			send     16
			jmp      code_0e14
code_0d56:
			dup     
			ldi      575
			eq?     
			bnt      code_0dd9
			lsg      heroType
			dup     
			ldi      0
			eq?     
			bnt      code_0d7e
			pushi    #say
			pushi    6
			pushi    1
			pushi    6
			pushi    13
			pushi    0
			pushSelf
			pushi    53
			lag      messager
			send     16
			jmp      code_0dd5
code_0d7e:
			dup     
			ldi      1
			eq?     
			bnt      code_0d9c
			pushi    #say
			pushi    6
			pushi    1
			pushi    6
			pushi    15
			pushi    0
			pushSelf
			pushi    53
			lag      messager
			send     16
			jmp      code_0dd5
code_0d9c:
			dup     
			ldi      2
			eq?     
			bnt      code_0dba
			pushi    #say
			pushi    6
			pushi    1
			pushi    6
			pushi    14
			pushi    0
			pushSelf
			pushi    53
			lag      messager
			send     16
			jmp      code_0dd5
code_0dba:
			dup     
			ldi      3
			eq?     
			bnt      code_0dd5
			pushi    #say
			pushi    6
			pushi    1
			pushi    6
			pushi    5
			pushi    0
			pushSelf
			pushi    53
			lag      messager
			send     16
code_0dd5:
			toss    
			jmp      code_0e14
code_0dd9:
			dup     
			ldi      576
			eq?     
			bnt      code_0df8
			pushi    #say
			pushi    6
			pushi    1
			pushi    6
			pushi    3
			pushi    0
			pushSelf
			pushi    53
			lag      messager
			send     16
			jmp      code_0e14
code_0df8:
			dup     
			ldi      590
			eq?     
			bnt      code_0e14
			pushi    #say
			pushi    6
			pushi    1
			pushi    6
			pushi    4
			pushi    0
			pushSelf
			pushi    53
			lag      messager
			send     16
code_0e14:
			toss    
			jmp      code_105e
code_0e18:
			dup     
			ldi      3
			eq?     
			bnt      code_0e2e
			pushi    #setCycle
			pushi    2
			class    Beg
			push    
			pushSelf
			lag      ego
			send     8
			jmp      code_105e
code_0e2e:
			dup     
			ldi      4
			eq?     
			bnt      code_105e
			pushi    1
			pushi    65
			callb    Bset,  2
			lsg      curRoomNum
			dup     
			ldi      566
			eq?     
			bnt      code_0e7e
			pushi    1
			pushi    55
			callb    Btst,  2
			not     
			bnt      code_103c
			pushi    1
			pushi    55
			callb    Bset,  2
			pushi    #get
			pushi    2
			pushi    5
			pushi    1
			pushi    513
			pushi    2
			pushi    0
			pushi    38
			pushi    513
			pushi    2
			pushi    29
			pushi    1
			lag      ego
			send     24
			lsg      kopeks
			ldi      75
			add     
			sag      kopeks
			jmp      code_103c
code_0e7e:
			dup     
			ldi      567
			eq?     
			bnt      code_0ebe
			pushi    1
			pushi    56
			callb    Btst,  2
			not     
			bnt      code_103c
			pushi    1
			pushi    56
			callb    Bset,  2
			pushi    #get
			pushi    2
			pushi    45
			pushi    1
			pushi    513
			pushi    2
			pushi    29
			pushi    1
			pushi    513
			pushi    2
			pushi    0
			pushi    6
			lag      ego
			send     24
			lsg      kopeks
			ldi      40
			add     
			sag      kopeks
			jmp      code_103c
code_0ebe:
			dup     
			ldi      575
			eq?     
			bnt      code_0f9f
			pushi    1
			pushi    57
			callb    Btst,  2
			not     
			bnt      code_103c
			pushi    1
			pushi    57
			callb    Bset,  2
			lsg      heroType
			dup     
			ldi      0
			eq?     
			bnt      code_0f22
			pushi    #state
			pushi    1
			pushi    2
			pushi    #at
			pushi    1
			pushi    19
			lag      inventory
			send     6
			send     6
			pushi    #has
			pushi    1
			pushi    19
			lag      ego
			send     6
			not     
			bnt      code_0f0c
			pushi    #get
			pushi    2
			pushi    19
			pushi    1
			lag      ego
			send     8
code_0f0c:
			pushi    #get
			pushi    2
			pushi    0
			pushi    150
			pushi    513
			pushi    1
			pushi    56
			lag      ego
			send     14
			jmp      code_0f9b
code_0f22:
			dup     
			ldi      1
			eq?     
			bnt      code_0f38
			pushi    #get
			pushi    2
			pushi    0
			pushi    150
			lag      ego
			send     8
			jmp      code_0f9b
code_0f38:
			dup     
			ldi      2
			eq?     
			bnt      code_0f55
			pushi    #get
			pushi    2
			pushi    0
			pushi    150
			pushi    513
			pushi    1
			pushi    56
			lag      ego
			send     14
			jmp      code_0f9b
code_0f55:
			dup     
			ldi      3
			eq?     
			bnt      code_0f9b
			pushi    #state
			pushi    1
			pushi    3
			pushi    #at
			pushi    1
			pushi    19
			lag      inventory
			send     6
			send     6
			pushi    #has
			pushi    1
			pushi    19
			lag      ego
			send     6
			not     
			bnt      code_0f88
			pushi    #get
			pushi    2
			pushi    19
			pushi    1
			lag      ego
			send     8
code_0f88:
			pushi    #get
			pushi    2
			pushi    0
			pushi    150
			pushi    513
			pushi    1
			pushi    56
			lag      ego
			send     14
code_0f9b:
			toss    
			jmp      code_103c
code_0f9f:
			dup     
			ldi      576
			eq?     
			bnt      code_0fd6
			pushi    1
			pushi    58
			callb    Btst,  2
			not     
			bnt      code_103c
			pushi    1
			pushi    58
			callb    Bset,  2
			pushi    #get
			pushi    2
			pushi    0
			pushi    15
			pushi    513
			pushi    2
			pushi    45
			pushi    1
			pushi    513
			pushi    2
			pushi    3
			pushi    1
			lag      ego
			send     24
			jmp      code_103c
code_0fd6:
			dup     
			ldi      590
			eq?     
			bnt      code_103c
			pushi    1
			pushi    59
			callb    Btst,  2
			not     
			bnt      code_103c
			pushi    1
			pushi    59
			callb    Bset,  2
			pushi    #get
			pushi    2
			pushi    0
			pushi    25
			pushi    513
			pushi    2
			pushi    45
			pushi    1
			lag      ego
			send     16
			lsg      heroType
			ldi      3
			eq?     
			bt       code_1011
			lsg      heroType
			ldi      0
			eq?     
			bnt      code_1011
code_1011:
			pushi    #state
			pushi    1
			pushi    1
			pushi    #at
			pushi    1
			pushi    17
			lag      inventory
			send     6
			send     6
			pushi    #has
			pushi    1
			pushi    17
			lag      ego
			send     6
			not     
			bnt      code_103c
			pushi    #get
			pushi    2
			pushi    17
			pushi    1
			lag      ego
			send     8
code_103c:
			toss    
			pushi    #setSpeed
			pushi    1
			lsl      local1
			pushi    790
			pushi    1
			pushi    4
			lag      ego
			send     12
			pushi    #handsOn
			pushi    0
			lag      theGame
			send     4
			pushi    #dispose
			pushi    0
			self     4
code_105e:
			toss    
			ret     
		)
	)
)

(instance wraithFX of Sound
	(properties)
)
