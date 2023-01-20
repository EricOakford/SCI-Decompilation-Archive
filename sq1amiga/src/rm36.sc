;;; Sierra Script 1.0 - (do not remove this comment)
(script# 36)
(include sci.sh)
(use Main)
(use rm65)
(use rm103)
(use Intrface)
(use Keypad)
(use SQRoom)
(use LoadMany)
(use Reverse)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm36 0
)

(local
	local0
	local1
	eMsg
	local3
	local4
	local5
	local6
	skippedArcade
	local8
	local9
	local10
	local11
	gEgoCel
	oldEgoSpeed
	local14
)
(procedure (localproc_000e)
	(return (if (< local5 160) rock1 else rock2))
)

(procedure (localproc_0021 param1 &tmp [temp0 100])
	(asm
		pushi    #eachElementDo
		pushi    1
		pushi    154
		lag      sounds
		send     6
		pushi    1
		lea      @temp0
		push    
		&rest    param1
		callk    Format,  2
		pushi    #number
		pushi    1
		pushi    900
		pushi    97
		pushi    1
		pushi    127
		pushi    6
		pushi    1
		pushi    1
		pushi    102
		pushi    1
		pushi    1
		pushi    42
		pushi    0
		lag      theMusic
		send     28
		pushi    #setCursor
		pushi    2
		lsg      normalCursor
		pushi    1
		lag      theGame
		send     8
code_005c:
		pushi    19
		lea      @temp0
		push    
		pushi    30
		pushi    1
		pushi    81
		lofsa    {Try Again}
		push    
		pushi    0
		pushi    81
		lofsa    {Restore}
		push    
		pushi    1
		pushi    81
		lofsa    {Restart}
		push    
		pushi    2
		pushi    81
		lofsa    {__Quit__}
		push    
		pushi    3
		pushi    82
		pushi    26
		pushi    5
		pushi    1
		calle    Print,  38
		push    
		dup     
		ldi      0
		eq?     
		bnt      code_009f
		ldi      0
		sag      curRoomNum
		jmp      code_00d3
		jmp      code_00cf
code_009f:
		dup     
		ldi      1
		eq?     
		bnt      code_00b0
		pushi    #restore
		pushi    0
		lag      theGame
		send     4
		jmp      code_00cf
code_00b0:
		dup     
		ldi      2
		eq?     
		bnt      code_00c1
		pushi    #restart
		pushi    0
		lag      theGame
		send     4
		jmp      code_00cf
code_00c1:
		dup     
		ldi      3
		eq?     
		bnt      code_00cf
		ldi      1
		sag      quit
		jmp      code_00d3
code_00cf:
		toss    
		jmp      code_005c
code_00d3:
		ret     
	)
)

(procedure (localproc_00d4)
	(theIconBar disable: 1 2 3 4 5 6 7)
)

(procedure (localproc_00ea)
	(if
		(= skippedArcade
			(Print 36 0 #button { Play_} 0 #button { Skip_} 1)
		)
		(= local10 (= local11 (+ 40 (* howFast 20))))
		(rock1 inc: (rock1 newCelHorizon?))
		(rock2 newCelHorizon: (rock2 inc?))
		(HandsOff)
	else
		(= local10 (= local11 (+ 150 (* howFast 150))))
		(localproc_00d4)
	)
)

(procedure (localproc_0153)
	(if (!= (flyingEgo x?) 160)
		(= local14 (/ (- 160 (flyingEgo x?)) 3))
	else
		(= local14 0)
	)
)

(class Rock of Actor
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		description 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 26505
		lookStr 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $2000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
	)
	
	(method (doit)
		(super doit:)
		(if (and local3 (not (& signal $4000)))
			(self ignoreActors: 1)
		)
	)
)

(class SMover of Motion
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		description 0
		sightAngle 0
		actions 0
		onMeCheck $0000
		approachX 0
		approachY 0
		approachDist 1
		_approachVerbs 10
	)
	
	(method (init)
		(super init: &rest)
		(motorSound pause:)
		(skimmerBanks loop: 1 play:)
		(if (x cycler?) ((x cycler?) whoToCue: (self x?)))
	)
	
	(method (doit)
		(super doit:)
		(if (== (skimmerBanks prevSignal?) -1)
			(skimmerBanks prevSignal: 0)
			(motorSound pause: 0)
		)
		(if (not (x isBlocked:))
			(if
				(and
					sightAngle
					approachDist
					(< (Abs noun) _approachVerbs)
				)
				(if (> z (x x?))
					(= noun (+ noun approachDist))
				else
					(= noun (- noun approachDist))
				)
			)
		else
			(self moveDone:)
			(return)
		)
	)
	
	(method (moveDone)
		(if (not (OneOf (ego loop?) 3 4)) (motorSound play:))
		(skimmerBanks stop:)
		(x mover: 0)
		(self dispose:)
	)
)

(class SCycler of Fwd
	(properties
		x 0
		y 0
		z 1
		heading 0
		noun 0
		nsTop 1
		nsLeft 0
		nsBottom 0
	)
	
	(method (doit &tmp temp0 xLastCel)
		(cond 
			((and (x mover?) ((x mover?) b-xAxis?))
				(cond 
					((or (not (x loop?)) (!= nsLeft nsBottom))
						(= nsBottom nsLeft)
						(x
							setLoop: (if (< ((x mover?) x?) (x x?)) 1 else 2)
							cel: 0
						)
					)
					((!= (x cel?) nsTop) (x cel: (self nextCel:)))
				)
			)
			(
				(and
					(x loop?)
					(== (x cel?) (= xLastCel (x lastCel:)))
				)
				(x setLoop: 0 cel: 0)
			)
			(else (super doit:))
		)
	)
)

(class RockCycler of Cycle
	(properties
		x 0
		y 0
		z 1
		heading 0
		noun 0
		nsTop 2
		nsLeft 6
		nsBottom 0
		nsRight 2
		description 55
	)
	
	(method (init)
		(super init: &rest)
		(x ignoreActors: 1)
		(= nsBottom (x lastCel:))
	)
	
	(method (doit)
		(if (>= (x y?) description)
			(if (!= (x cel?) nsBottom) (x cel: (self nextCel:)))
			(if (== nsRight 16) (x ignoreActors: 0))
			(= description (+ description nsRight))
			(= nsRight (* nsRight 2))
		)
	)
)

(instance rm36 of SQRoom
	(properties
		picture 36
		north 41
	)
	
	(method (init)
		(Load rsVIEW 136)
		(LoadMany 132 437 438 439 440 441 442 443 444 445 446)
		(mouseDownHandler addToFront: self)
		(super init: &rest)
		(= oldEgoSpeed (theGame egoMoveSpeed?))
		(theGame egoMoveSpeed: 0)
		((theIconBar at: 0) cursor: 26)
		(HandsOff)
		(addToPics
			add: mtn1 mtn2 mtn3 damageGauge
			eachElementDo: #init
			doit:
		)
		(ego
			init:
			view: 26
			setLoop: 0
			cel: 0
			x: 160
			y: 225
			xStep: 3
			yStep: 3
			illegalBits: 16384
			ignoreActors: 0
			setPri: 10
			normal: 0
			setCycle: PadButton
			baseSetter: skimmerBase
			setScript: egoHandleEvent
		)
		(city init: stopUpd:)
		(if (> howFast 1) (shadow init: setCycle: Rev))
		(self setScript: comeOnScreen)
		(= local4 (GetTime 1))
	)
	
	(method (doit &tmp temp0 temp1)
		(super doit:)
		(if (and local6 (== (-- local10) 0) (not local3))
			(if (== (city cel?) 3)
				(= local9 1)
			else
				(= local10 local11)
				(city cel: (+ (city cel?) 1) forceUpd:)
			)
		)
		(if
			(and
				local9
				(not (rock1 script?))
				(not (rock2 script?))
			)
			(cond 
				(skippedArcade (Print 36 1))
				((not local0) (Print 36 2))
				(else (Print 36 3))
			)
			(theMusic fade:)
			(self newRoom: 41)
		)
		(= temp0 (GetTime 1))
		(if
			(and
				(not local3)
				(not (curRoom script?))
				(not (ego mover?))
				(>= (- temp0 local4) 3)
			)
			(= local4 temp0)
			(if (!= (ego x?) 160)
				(= local5
					(+
						(/ -13400 (= temp1 (/ -9000 (- (ego x?) 160))))
						160
					)
				)
			else
				(= local5
					(if (> (ego x?) 160)
						(rock2 newCelHorizon?)
					else
						(rock1 inc?)
					)
				)
			)
		)
	)
	
	(method (dispose)
		(if (== (theGame egoMoveSpeed?) 0)
			(theGame egoMoveSpeed: oldEgoSpeed)
		)
		((theIconBar at: 0) cursor: 6)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (== (event type?) evMOUSEBUTTON)
			(if
				(and
					(User controls?)
					(not script)
					(== (event type?) evMOUSEBUTTON)
				)
				(ego setMotion: theSMover (event x?) (ego y?))
				(event claimed: 1)
			)
		else
			(super handleEvent: event)
		)
	)
)

(instance egoHandleEvent of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((& (event type?) evJOYSTICK)
				(switch (event message?)
					(JOY_RIGHT
						(if
						(or (!= (event message?) eMsg) (not (ego mover?)))
							(ego setMotion: theSMover 400 (ego y?))
						else
							(ego setMotion: 0)
						)
					)
					(JOY_LEFT
						(if
						(or (!= (event message?) eMsg) (not (ego mover?)))
							(ego setMotion: theSMover -400 (ego y?))
						else
							(ego setMotion: 0)
						)
					)
					(JOY_NULL (ego setMotion: 0))
				)
				(= eMsg (event message?))
				(event claimed: 1)
			)
			(
				(and
					(User controls?)
					(== (event type?) evKEYBOARD)
					(== (event message?) KEY_RETURN)
				)
				(ego setMotion: theSMover (event x?) (ego y?))
				(event claimed: 1)
			)
			(else (super handleEvent: event))
		)
	)
)

(instance comeOnScreen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(motorSound play: hold: 1)
				(ego setMotion: MoveTo 160 145 self)
			)
			(1
				(rock1
					init:
					moveSpeed: (* howFast 3)
					setPri: 2
					baseSetter: RockBase
					setScript: (Clone rockAttack)
				)
				(rock2
					init:
					moveSpeed: (* howFast 3)
					setPri: 2
					baseSetter: RockBase
					setScript: (Clone rockAttack)
				)
				(HandsOn)
				(= local6 1)
				(localproc_00ea)
				(= cycles 1)
			)
			(2
				(theMusic number: 437 loop: -1 play:)
				(self dispose:)
			)
		)
	)
)

(instance rockAttack of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not local3)
				(not local9)
				(client isBlocked:)
				(not (curRoom script?))
			)
			(curRoom setScript: hitRock 0 client)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client hide: setPri: 2 setLoop: (Random 1 4))
				(= cycles (Random 3 30))
			)
			(1
				(if (and (not local9) (not local3))
					(client
						show:
						cel: 0
						posn: (client firstStaticCel?) 55
						lastStaticCel: (if (== (client loop?) 1) 1 else 0)
						ignoreActors: 0
						setCycle: FastHand
						setMotion:
							MoveTo
							(if
								(and
									(not skippedArcade)
									local5
									(== client (localproc_000e))
									(not local8)
									(= local8 1)
									(client minCyclesToGo: 1)
								)
								local5
							else
								(Random (client newCelHorizon?) (client inc?))
							)
							189
							self
					)
				else
					(self dispose:)
				)
			)
			(2
				(if (client minCyclesToGo?)
					(client minCyclesToGo: 0)
					(= local8 0)
					(= local5 0)
				)
				(self changeState: 0)
			)
		)
	)
)

(instance tossEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_0153)
				(flyingEgo
					setLoop: 5
					cycleSpeed: 5
					setCycle: Fwd
					setStep: 5 13
					setMotion: MoveTo (+ (flyingEgo x?) local14) -25 self
				)
			)
			(1 (= ticks 50))
			(2
				(flyingEgo
					setLoop: 7
					cycleSpeed: 3
					setStep: 2 5
					setPri: -1
					posn: (+ (flyingEgo x?) (/ local14 2)) -10
					setMotion: MoveTo (+ (flyingEgo x?) local14) 62 self
				)
			)
			(3
				(flyingEgo
					setLoop: 8
					cel: 0
					cycleSpeed: 7
					setCycle: End self
				)
			)
			(4 (= seconds 3))
			(5
				(if ((client register?) lastStaticCel?)
					(localproc_0021 36 4)
				else
					(localproc_0021 36 5)
				)
			)
		)
	)
)

(instance hitRock of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				local3
				(OneOf (ego cel?) 4 6 8 11 14)
				(!= gEgoCel (ego cel?))
			)
			(if (== (ego cel?) 4)
				(soundFx number: 441 loop: 1 play:)
			else
				(skimmerCrash
					number: (if (== (ego cel?) 6)
						442
					else
						(+ (skimmerCrash number?) 1)
					)
					loop: 1
					play:
				)
			)
		)
		(= gEgoCel (ego cel?))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(register ignoreActors: 1 setPri: -1)
				(if (register lastStaticCel?) (= local0 4))
				(soundFx number: 438 loop: 1 play:)
				(if (<= (++ local0) 4)
					(ego
						ignoreActors: 1
						setMotion: 0
						setCycle: 0
						setLoop: 3
						cel: 0
						cycleSpeed: 5
						setCycle: End self
					)
				else
					(= local3 1)
					(theMusic stop:)
					(motorSound stop:)
					(shadow dispose:)
					(flyingEgo
						init:
						setPri: (+ (mtn1 priority?) 1)
						posn: (ego x?) (- (ego y?) 24)
					)
					(ego
						setMotion: 0
						setCycle: 0
						setLoop: 4
						cel: 0
						cycleSpeed: 8
						setCycle: End self
						setMotion: MoveTo (ego x?) (+ (ego y?) 20)
					)
					(self setScript: tossEgo)
				)
				(DrawCel 136 0 local0 276 145 14)
			)
			(1
				(if (<= local0 4)
					(if (== local0 4) (soundFx number: 446 loop: 4 play:))
					(ego
						ignoreActors: 0
						setLoop: 0
						cel: 0
						cycleSpeed: 5
						setCycle: PadButton
					)
					(HandsOn)
					(localproc_00d4)
					(self dispose:)
				else
					(= local0 0)
					(ego setMotion: 0)
					(rock1 setMotion: 0 setScript: 0 setCycle: 0)
					(rock2 setMotion: 0 setScript: 0 setCycle: 0)
				)
			)
		)
	)
)

(instance theSMover of FastHand3
	(properties
		sightAngle 0
		onMeCheck $0000
		approachDist 1
		_approachVerbs 10
	)
)

(instance mtn1 of PicView
	(properties
		x 38
		y 55
		view 238
		loop 1
		cel 2
		priority 0
		signal $0010
	)
)

(instance mtn2 of PicView
	(properties
		x 247
		y 56
		view 238
		priority 0
		signal $0010
	)
)

(instance mtn3 of PicView
	(properties
		x 282
		y 56
		view 238
		loop 3
		cel 2
		priority 0
		signal $0010
	)
)

(instance city of View
	(properties
		x 160
		y 55
		view 136
		loop 5
		signal $6800
	)
)

(instance damageGauge of PicView
	(properties
		x 293
		y 183
		description {gauge}
		lookStr {This gauge tells the amount of damage done to the skimmer.}
		view 136
		priority 13
	)
)

(instance shadow of Prop
	(properties
		view 26
		loop 6
		priority 1
		signal $6010
		cycleSpeed 2
	)
	
	(method (init)
		(self posn: (+ (ego x?) 3) (+ (ego y?) 3))
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(= x (+ (ego x?) 3))
		(= y (ego y?))
	)
)

(instance flyingEgo of Actor
	(properties
		view 26
		loop 5
		signal $6800
		cycleSpeed 8
		illegalBits $0000
		moveSpeed 2
	)
)

(instance rock1 of FastHand2
	(properties
		onMeCheck $6789
		yStep 6
		view 136
		cel 0
		priority 0
		signal $2000
	)
)

(instance rock2 of FastHand2
	(properties
		onMeCheck $6789
		yStep 6
		view 136
		cel 0
		priority 0
		signal $2000
	)
)

(instance skimmerBase of Code
	(properties)
	
	(method (doit param1)
		(param1
			brTop: (- (param1 y?) 13)
			brLeft: (- (param1 x?) 25)
			brRight: (+ (param1 x?) 25)
			brBottom: (param1 y?)
		)
	)
)

(instance RockBase of Code
	(properties)
	
	(method (doit param1 &tmp temp0 temp1 temp2 temp3)
		(= temp0 (= temp1 (= temp2 (= temp3 0))))
		(switch (param1 loop?)
			(1
				(= temp0 7)
				(= temp1 10)
				(= temp2 10)
			)
			(2 (= temp0 3) (= temp2 8))
			(3
				(= temp0 3)
				(= temp1 7)
				(= temp2 9)
			)
			(4
				(= temp0 3)
				(= temp1 8)
				(= temp2 16)
				(= temp3 1)
			)
		)
		(param1
			brTop: (- (param1 y?) temp0)
			brLeft: (- (param1 x?) temp1)
			brRight: (+ (param1 x?) temp2)
			brBottom: (- (param1 y?) temp3)
		)
	)
)

(instance motorSound of Sound
	(properties
		number 439
		loop -1
	)
)

(instance skimmerCrash of Sound
	(properties
		number 442
	)
)

(instance skimmerBanks of Sound
	(properties
		number 440
	)
)
