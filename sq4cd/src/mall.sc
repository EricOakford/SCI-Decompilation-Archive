;;; Sierra Script 1.0 - (do not remove this comment)
(script# 700)
(include game.sh)
(use Main)
(use RegionPath)
(use Inertia)
(use Sq4Narrator)
(use Sq4Feature)
(use Print)
(use PolyPath)
(use StopWalk)
(use Motion)
(use Game)
(use System)

(public
	mall 0
	enS 1
	exS 2
	proc700_3 3
	eSwimCode 4
	proc700_5 5
	sp1 6
	sp2 7
	proc700_8 8
	shopper3 9
	newSpeedCode 10
)

(local
	[shopperPoly2 43] = [32767 400 324 90 -5 90 32767 370 78 231 324 87 32767 375 78 233 324 87 32767 380 -5 142 324 127 32767 385 -5 142 324 158 32767 390 -5 89 239 233 32767 395 -5 88 230 238 -32768]
	[shopperPoly3 43] = [32767 400 -5 145 324 145 32767 395 324 179 -5 3 32767 390 324 173 -5 0 32767 385 324 116 -5 100 32767 380 324 87 -5 101 32767 375 324 -3 -5 169 32767 370 324 -3 -5 169 -32768]
	[theLoop 8] = [3 6 0 4 2 5 1 7]
)
(procedure (proc700_3 event claimed param3)
	(return
		(if (& (event claimed?) param3)
			(return TRUE)
		else
			(return FALSE)
		)
	)
)

(procedure (proc700_5 param1)
	(if param1
		(= useObstacles TRUE)
		(ego illegalBits: 0)
		(if (IsObject (ego mover?))
			(ego
				setMotion: PolyPath ((ego mover?) x?) ((ego mover?) y?)
			)
		)
	else
		(= useObstacles FALSE)
		(ego illegalBits: cWHITE)
	)
)

(procedure (proc700_8 &tmp temp0)
	(asm
		bnot    
		send     6
		pushi    #z
		pushi    1
		pushi    20000
		lofsa    shopper3
		send     6
		lsg      curRoomNum
		ldi      387
		ne?     
		bnt      code_042f
		pushi    2
		pushi    1
		pushi    1
		pushi    fPoliceLookForEgo
		callb    Btst,  2
		bnt      code_03d1
		ldi      7
		jmp      code_03d4
code_03d1:
		ldi      384
code_03d4:
		push    
		pushi    161
		pushi    1
		class    Walk
		push    
		pushi    315
		pushi    2
		pushi    4
		pushi    2
		pushi    317
		pushi    1
		lsg      speed
		pushi    110
		pushi    0
		pushi    290
		pushi    1
		lofsa    shopperPath2
		push    
		lofsa    shopper2
		send     36
		pushi    2
		pushi    1
		pushi    1
		pushi    fPoliceLookForEgo
		callb    Btst,  2
		bnt      code_0407
		ldi      7
		jmp      code_040a
code_0407:
		ldi      388
code_040a:
		push    
		pushi    161
		pushi    1
		class    Walk
		push    
		pushi    315
		pushi    2
		pushi    5
		pushi    2
		pushi    317
		pushi    1
		lsg      speed
		pushi    110
		pushi    0
		pushi    290
		pushi    1
		lofsa    shopperPath3
		push    
		lofsa    shopper3
		send     36
code_042f:
		lsg      prevRoomNum
		ldi      396
		ne?     
		bnt      code_0486
		pushi    5
		lsg      curRoomNum
		pushi    405
		pushi    406
		pushi    410
		pushi    411
		calle    OneOf,  10
		not     
		bnt      code_0486
		pushi    #view
		pushi    0
		lag      ego
		send     4
		push    
		dup     
		ldi      0
		eq?     
		bnt      code_0461
		ldi      4
		jmp      code_0476
code_0461:
		dup     
		ldi      402
		eq?     
		bnt      code_046c
		ldi      14
		jmp      code_0476
code_046c:
		dup     
		ldi      373
		eq?     
		bnt      code_0476
		ldi      374
code_0476:
		toss    
		sat      temp0
		pushi    #setCycle
		pushi    2
		class    StopWalk
		push    
		lst      temp0
		lag      ego
		send     8
code_0486:
		pushi    8
		lsg      curRoomNum
		pushi    370
		pushi    375
		pushi    380
		pushi    385
		pushi    390
		pushi    395
		pushi    400
		calle    OneOf,  16
		bnt      code_04c3
		pushi    #init
		pushi    0
		lofsa    belts
		send     4
		pushi    #init
		pushi    0
		lofsa    restOfMall
		send     4
		pushi    #setScript
		pushi    1
		lofsa    nearScript
		push    
		lag      ego
		send     6
code_04c3:
		ret     
	)
)

(procedure (localproc_0640 param1)
	(if
		(and
			(ego mover?)
			(not (param1 z?))
			(& (param1 signal?) $0400)
			(< (ego distanceTo: param1) 35)
		)
		(ego setMotion: 0)
	)
)

(instance newSpeedCode of Code
	(properties)
	
	(method (doit theSpeed &tmp temp0 temp1 temp2)
		(if argc
			(= speed theSpeed)
			(= temp0 (FirstNode (cast elements?)))
			(while temp0
				(= temp2 (NextNode temp0))
				(if
					(or
						(not (IsObject (= temp1 (NodeValue temp0))))
						(not (temp1 respondsTo: #setSpeed))
					)
				else
					(temp1 setSpeed: theSpeed &rest)
				)
				(= temp0 temp2)
			)
		)
		(return speed)
	)
)

(class mall of Region
	(properties
		enterBelt 0
		whichBelt 0
		rFlag1 0
		rFlag2 0
		rFlag3 0
		rFlag4 0
	)
	
	(method (init &tmp temp0)
		(super init: &rest)
		(Load VIEW 370)
		(Load VIEW 7)
		(newSpeedCode doit: speed)
		(if (and (Btst fPoliceInSkateORama) (not (Btst fPoliceLookForEgo)))
			(if (!= curRoomNum 390)
				(Bset fPoliceLookForEgo)
				(shopperPath2
					x: 239
					y: 233
					dx: 3
					dy: 1
					b-moveCnt: 0
					b-i1: 258
					b-i2: -78
					b-di: 138
					b-xAxis: 1
					b-incr: 1
					completed: FALSE
					xLast: 92
					yLast: 146
					currentRoom: 390
					value: 35
					intermediate: 0
				)
				(shopperPath3
					x: -5
					y: 0
					dx: -2
					dy: -1
					b-moveCnt: 0
					b-i1: 34
					b-i2: -624
					b-di: -523
					b-xAxis: 1
					b-incr: -1
					completed: FALSE
					xLast: 262
					yLast: 140
					currentRoom: 390
					value: 17
					intermediate: 0
				)
				(shopper2
					x: 95
					y: 148
					z: 1000
					heading: 120
					xLast: 95
					yLast: 148
				)
				(shopper3
					x: 260
					y: 139
					z: 1000
					heading: 298
					xLast: 260
					yLast: 139
				)
			else
				(shopper2 z: 20000)
				(shopper3 z: 20000)
			)
		)
		(if (!= curRoomNum 387)
			(shopper2
				view: (if (Btst fPoliceLookForEgo) 7 else 384)
				setCycle: Walk
				setStep: 4 2
				setSpeed: speed
				init:
				setMotion: shopperPath2
			)
			(shopper3
				view: (if (Btst fPoliceLookForEgo) 7 else 388)
				setCycle: Walk
				setStep: 5 2
				setSpeed: speed
				init:
				setMotion: shopperPath3
			)
		)
		(if
			(and
				(!= prevRoomNum 396)
				(not (OneOf curRoomNum 405 406 410 411))
			)
			(= temp0
				(switch (ego view?)
					(0 4)
					(402 14)
					(373 374)
				)
			)
			(ego setCycle: StopWalk temp0)
		)
		(if (OneOf curRoomNum 370 375 380 385 390 395 400)
			(belts init:)
			(restOfMall init:)
			(ego setScript: nearScript)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (Btst 24)
			(++ mallRoomVisits)
			(if (and (Btst 25) (> mallRoomVisits 2)) (Bclr 25))
		)
		(= keep
			(OneOf newRoomNumber
				370 371 375 376 380 381 385 386 387 390 391
				395 396 397 398 399 400 405 406 410 411 290
			)
		)
		(= initialized FALSE)
		(super newRoom: newRoomNumber &rest)
	)
	
	(method (offScreen)
		(return
			(if
				(or
					(< (ego nsRight?) 0)
					(> (ego nsLeft?) 319)
					(< (ego nsBottom?) 0)
				)
			else
				(> ((ego _head?) nsTop?) 189)
			)
		)
	)
)

(instance enS of Script
	(properties)
	
	(method (init)
		(ego illegalBits: 0)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(or
				(not (ego onControl:))
				(<= (ego x?) 0)
				(>= (ego x?) 319)
				(<= (ego y?) (curRoom horizon?))
				(>= (ego y?) 189)
			)
			0
		else
			(ego illegalBits: cWHITE)
			(self dispose:)
			(HandsOn)
		)
		(register who: ego doit:)
	)
)

(instance exS of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(ego setMotion: 0)
		(if (mall offScreen:)
			(curRoom newRoom: register)
		else
			((mall enterBelt?) who: ego doit:)
		)
	)
)

(instance moveNear of Motion
	(properties)
	
	(method (doit &tmp temp0)
		(if (self onTarget:)
			(self moveDone:)
		else
			(if
				(!=
					(client heading?)
					(GetAngle (client x?) (client y?) x y)
				)
				(InitBresen self)
			)
			(super doit:)
		)
	)
)

(instance nearScript of Script
	(properties)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(
				(or
					(!= (theIconBar curIcon?) (theIconBar at: ICON_WALK))
					(not (user controls?))
					(!= (event type?) userEvent)
					(event modifiers?)
				)
				(super handleEvent: event)
			)
			(else
				(client
					setMotion:
						(if useObstacles PolyPath else moveNear)
						(event x?)
						(event y?)
				)
				(user prevDir: 0)
				(event claimed: TRUE)
			)
		)
	)
)

(instance eSwimCode of Inertia
	(properties
		maxInertia 5
	)
)

(instance spSwimCode of Inertia
	(properties
		maxInertia 4
	)
)

(instance shopper2 of Sq4Actor
	(properties
		z 1000
		view 367
		signal ignrHrz
		illegalBits $0000
	)
	
	(method (doit)
		(super doit:)
		(localproc_0640 self)
		(cond 
			(script)
			((IsObjectOnControl self 1)
				(if (not cycler)
					(self
						view: (if (Btst fPoliceLookForEgo) 7 else 384)
						setCycle: Walk
						setCel: -1
						setStep: 4 2
					)
					(InitBresen mover)
				)
			)
			(else
				(if cycler
					(self
						view: (if (Btst fPoliceLookForEgo) 13 else 367)
						setCycle: 0
						setCel: 0
						setStep: 2 2
					)
					(InitBresen mover)
				)
				(= loop
					[theLoop (/
						(umod (+ heading (/ 180 (NumLoops self))) 360)
						(/ 360 (NumLoops self))
					)]
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst fPoliceLookForEgo)
					(narrator modNum: 376 say: 17)
				else
					(narrator modNum: 700 say: 6)
				)
			)
			(4
				(narrator modNum: 700 say: 2)
			)
			(2
				(narrator modNum: 700 say: 7)
			)
			(6
				(narrator modNum: 700 say: 8)
			)
			(7
				(narrator modNum: 700 say: 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance shopper3 of Sq4Actor
	(properties
		z 1000
		view 368
		signal ignrHrz
		illegalBits $0000
	)
	
	(method (doit)
		(super doit:)
		(localproc_0640 self)
		(cond 
			(script)
			((IsObjectOnControl self 1)
				(if (not cycler)
					(self
						view: (if (Btst fPoliceLookForEgo) 7 else 388)
						setCycle: Walk
						setCel: -1
						setStep: 5 2
					)
					(InitBresen mover)
				)
			)
			(else
				(if cycler
					(self
						view: (if (Btst fPoliceLookForEgo) 13 else 368)
						setCycle: 0
						setCel: 0
						setStep: 2 2
					)
					(InitBresen mover)
				)
				(= loop
					[theLoop (/
						(umod (+ heading (/ 180 (NumLoops self))) 360)
						(/ 360 (NumLoops self))
					)]
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fPoliceLookForEgo)
					(narrator modNum: 376 say: 17)
				else
					(narrator modNum: 700 say: 9)
				)
			)
			(V_DO
				(narrator modNum: 700 say: 2)
			)
			(V_TALK
				(switch (Random 1 20)
					(19
						(wierdNar say: 1)
						(Prints
							{"Mr. Carlos sent me. I want to know about the sheep."}
						)
					)
					(else 
						(narrator modNum: 700 say: 10)
					)
				)
			)
			(V_SMELL
				(narrator modNum: 700 say: 11)
			)
			(V_TASTE
				(narrator modNum: 700 say: 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sp1 of Sq4Actor
	(properties
		view 7
		signal (| ignrAct ignrHrz)
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 376 say: 17)
			)
			(V_TALK
				(narrator modNum: 376 say: 1)
			)
			(V_SMELL
				(narrator modNum: 376 say: 2)
			)
			(V_TASTE
				(narrator modNum: 700 say: 3)
			)
			(V_DO
				(narrator modNum: 376 say: 2)
			)
			(V_BUCKAZOID
				(narrator modNum: 376 say: 5)
			)
			(V_ROPE
				(narrator modNum: 376 say: 6)
			)
			(V_RABBIT
				(narrator modNum: 700 say: 4)
			)
			(V_BATTERY
				(narrator modNum: 700 say: 12)
			)
			(V_GUM
				(narrator modNum: 376 say: 7)
			)
			(V_PEN
				(narrator modNum: 700 say: 5)
			)
			(V_ATMCARD
				(narrator modNum: 376 say: 5)
			)
			((OneOf theVerb V_CIGAR V_MATCHES V_DISKETTE V_LAPTOP)
				(narrator modNum: 376 say: 4)
			)
			(else 
				(super doVerb: theVerb param2 &rest)
			)
		)
	)
)

(instance sp2 of Sq4Actor
	(properties
		view 7
		signal (| ignrAct ignrHrz)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 376 say: 17)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance shopperPath2 of RegionPath
	(properties
		value 17
		theRegion MALL
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(== currentRoom curRoomNum)
				(!= (client script?) spOnBelt)
				(not (spOnBelt client?))
				(Btst fPoliceLookForEgo)
			)
			(client setScript: spOnBelt)
		)
	)
	
	(method (at param1)
		(return [shopperPoly2 param1])
	)
)

(instance shopperPath3 of RegionPath
	(properties
		value 17
		theRegion MALL
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(== currentRoom curRoomNum)
				(!= (client script?) spOnBelt)
				(not (spOnBelt client?))
				(Btst fPoliceLookForEgo)
			)
			(client setScript: spOnBelt)
		)
	)
	
	(method (at param1)
		(return [shopperPoly3 param1])
	)
)

(instance egoBumpScript of Script
	(properties)
	
	(method (changeState newState &tmp theX temp1)
		(switch (= state newState)
			(0
				(HandsOff)
				(= temp1
					(GetAngle
						(register x?)
						(register y?)
						(ego x?)
						(ego y?)
					)
				)
				(= temp1 (umod (+ temp1 45) 360))
				(ego
					view: 370
					cel: 0
					setMotion: 0
					x: theX
					setCycle: EndLoop self
				)
			)
			(1
				(user canInput: TRUE)
				(self dispose:)
			)
		)
	)
)

(instance egoStandScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: BegLoop self)
			)
			(1
				(ego view: 0 setCycle: Walk)
				(self dispose:)
				(HandsOn)
			)
		)
	)
)

(instance spOnBelt of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(Bset fSpOnBelt)
	)
	
	(method (doit)
		(cond 
			((curRoom script?) (client setCel: 0) (Face client ego))
			(
				(and
					(== state 0)
					(< 40 (client y?))
					(< (client y?) 170)
					(< 20 (client x?))
					(< (client x?) 300)
					(not (curRoom script?))
				)
				(self cue:)
			)
			((!= curRoomNum ((client mover?) currentRoom?)) (HandsOn) (self dispose:))
		)
		(super doit: &rest)
	)
	
	(method (changeState newState &tmp [temp0 4])
		(switch (= state newState)
			(0 0)
			(1
				(HandsOff)
				(Bclr fPoliceLookForEgo)
				(Face client ego self)
			)
			(2
				(client setCel: 0 view: 13 setCycle: EndLoop)
				(ego view: 26 cel: 0 setCycle: EndLoop self)
			)
			(3 (EgoDead iconLASER deathZAPZAP))
		)
	)
)

(instance belts of Sq4Feature
	(properties
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(narrator modNum: 700 say: 13)
			)
			(4
				(narrator modNum: 700 say: 14)
			)
			(6
				(narrator modNum: 700 say: 15)
			)
			(7
				(narrator modNum: 700 say: 16)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe param1)
		(return (& (OnControl 4 (param1 x?) (param1 y?)) $0006))
	)
)

(instance restOfMall of Sq4Feature
	(properties
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 700 say: 17)
			)
			(V_SMELL
				(narrator modNum: 380 say: 11)
			)
			(V_TASTE
				(narrator modNum: 380 say: 12)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe)
		(return TRUE)
	)
)

(instance wierdNar of Sq4Narrator
	(properties
		modNum MALL
		talkerNum WEIRDNAR
	)
)
