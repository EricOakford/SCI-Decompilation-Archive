;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include sci.sh)
(use Main)
(use GloryRm)
(use TargFeature)
(use EgoDead)
(use Array)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use StopWalk)
(use Timer)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Swamp 0
	sDownCandy 1
	reflection 2
	sToJump 3
	sJumpWater 4
	sEgoDrownGlide 5
)

(local
	local0
	local1
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
	local13
	local14
	local15
	local16
	local17
	local18
	theGLastTicks
	pEventX
	pEventY
	local22
)
(instance Swamp of Rgn
	(properties
		modNum 10
	)
	
	(method (init &tmp temp0)
		(theMusic number: 550 setLoop: -1 play:)
		(= local7 (IntArray with: 54 48 95 158 132))
		(= local8 (IntArray with: 175 108 119 142 178))
		(= local9 (IntArray with: 83 76 111 191 15))
		(= local10 (IntArray with: 160 103 130 171 136))
		(= local11 (IntArray with: 84 157 206 276))
		(= local12 (IntArray with: 157 176 139 173))
		(= local13 (IntArray with: 182 86 175 11))
		(= local14 (IntArray with: 112 138 171 173))
		(= local15 (IntArray with: 106 183 259 70 16))
		(= local16 (IntArray with: 121 142 149 156 137))
		(super init:)
		(switch curRoomNum
			(530
				(= temp0 0)
				(while (< temp0 5)
					((hands new:)
						init: temp0 (local9 at: temp0) (local10 at: temp0)
					)
					(++ temp0)
				)
			)
			(535
				(= temp0 0)
				(while (< temp0 5)
					((hands new:)
						init: temp0 (local7 at: temp0) (local8 at: temp0)
					)
					(++ temp0)
				)
			)
			(541
				(= temp0 0)
				(while (< temp0 4)
					((hands new:)
						init: temp0 (local11 at: temp0) (local12 at: temp0)
					)
					(++ temp0)
				)
			)
			(542
				(= temp0 0)
				(while (< temp0 4)
					((hands new:)
						init: temp0 (local13 at: temp0) (local14 at: temp0)
					)
					(++ temp0)
				)
			)
			(543
				(= temp0 0)
				(while (< temp0 4)
					((hands new:)
						init: temp0 (local15 at: temp0) (local16 at: temp0)
					)
					(++ temp0)
				)
			)
		)
		(if
			(and
				Night
				(OneOf curRoomNum 535 530)
				(not (Btst 459))
				(not (ego has: 41))
			)
			(= local6 (IntArray new: 4))
			(= temp0 0)
			(while (< temp0 4)
				(local6 at: temp0 ((wisps new:) init: temp0 yourself:))
				(++ temp0)
			)
		)
	)
	
	(method (doit)
		(if (and (Btst 359) (ego mover?)) (ego useStamina: 8))
		(if
		(and (not local22) (Btst 149) (< [egoStats 19] 20))
			(messager say: 8 6 25 0 0 535)
			(= local22 1)
		)
		(if (and local22 (> [egoStats 19] 20)) (= local22 0))
		(if (and (== local4 15) local3) (= local5 1))
		(super doit: &rest)
	)
	
	(method (dispose &tmp temp0)
		(local7 dispose:)
		(local8 dispose:)
		(local9 dispose:)
		(local10 dispose:)
		(local11 dispose:)
		(local12 dispose:)
		(local13 dispose:)
		(local14 dispose:)
		(local15 dispose:)
		(local16 dispose:)
		(if local6 (local6 dispose:))
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(switch theVerb
				(3
					(if (Btst 359)
						(cond 
							(
								(or
									(>
										(= temp0
											(GetAngle
												(ego x?)
												(ego y?)
												((User curEvent?) x?)
												((User curEvent?) y?)
											)
										)
										315
									)
									(< temp0 45)
								)
								(ego
									setMotion: PolyPath (ego x?) (- (ego y?) (ego yStep?))
								)
							)
							((or (>= temp0 45) (< temp0 135))
								(ego
									setMotion: PolyPath (+ (ego x?) (ego xStep?)) (ego y?)
								)
							)
							((or (>= temp0 135) (< temp0 225))
								(ego
									setMotion: PolyPath (ego x?) (+ (ego y?) (ego yStep?))
								)
							)
							((or (>= temp0 225) (< temp0 315))
								(ego
									setMotion: PolyPath (- (ego x?) (ego xStep?)) (ego y?)
								)
							)
						)
						(ego useStamina: 40)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(87
					(if (and (not (ego has: 41)) local6)
						(curRoom
							setScript:
								(ScriptID 37 0)
								0
								(local6 at: 0)
								(local6 at: 1)
								(local6 at: 2)
								(local6 at: 3)
						)
					else
						(curRoom setScript: (ScriptID 37))
					)
				)
				(86
					(if (Btst 167)
						(messager say: 8 6 2 0 0 535)
					else
						(curRoom setScript: (ScriptID 32) 0 86)
					)
				)
				(88
					(if (Btst 167)
						(messager say: 8 6 2 0 0 535)
					else
						(curRoom setScript: (ScriptID 32) 0 88)
					)
				)
				(93
					(if (Btst 167)
						(messager say: 8 6 2 0 0 535)
					else
						(curRoom setScript: (ScriptID 32) 0 93)
					)
				)
				(81
					(messager say: 0 81 0 0 0 535)
				)
				(60
					(self setScript: sReleaseWisps)
				)
				(104
					(messager say: 8 6 24 0 0 535)
				)
				(else 
					(GloryRm doVerb: theVerb)
				)
			)
		)
	)
	
	(method (notify param1 param2)
		(= local0 param1)
		(= local1 param2)
	)
)

(class SwampView of View
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
		extra 0
	)
	
	(method (dispose)
		(if heading (heading dispose: 1) (= heading 0))
		(super dispose:)
	)
	
	(method (handleEvent event)
		(= pEventX (event x?))
		(= pEventY (event y?))
		(super handleEvent: event)
	)
	
	(method (doVerb theVerb &tmp theEgoStats)
		(switch theVerb
			(21
				(if (not (Btst 167))
					(curRoom setScript: (ScriptID 32) 0 21)
				)
			)
			(10
				(if (not (self onMe: ego))
					(if (or (Btst 149) (Btst 167))
						(messager say: 0 10 14 0 0 535)
					else
						(if (< [egoStats 15] 150)
							(= theEgoStats 150)
						else
							(= theEgoStats [egoStats 15])
						)
						(= local1 (+ (/ theEgoStats 3) 20))
						(if (<= local1 (= local0 (ego distanceTo: self)))
							(ego
								setHeading: (GetAngle (ego x?) (ego y?) pEventX pEventY)
							)
							(curRoom setScript: sJumpWater 0 self)
						else
							(curRoom setScript: sToJump 0 self)
						)
					)
				else
					(super doVerb: theVerb)
				)
			)
			(3
				(curRoom setScript: sGlideToHummock 0 self)
			)
			(4
				(if (Btst 149)
					(curRoom setScript: sGlideToHummock 0 self)
				else
					(curRoom doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(class hands of TargActor
	(properties
		scratch 0
		heading 0
		noun 5
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
		view 535
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $6800
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
		cycleSpeed 12
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $0000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 0
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		robot 0
		dead 0
	)
	
	(method (init param1 theX theY)
		(super init:)
		(= x theX)
		(= y theY)
		(= loop (Random 0 5))
		((Timer new:) setReal: self (Random 5 25))
		(self
			signal: (| (self signal?) $0001)
			setScaler: Scaler 100 60 125 65
		)
	)
	
	(method (dispose)
		(if script (script disposeNow: 1 dispose:))
		(= script 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(21
				(if (not (Btst 167))
					(curRoom setScript: (ScriptID 32) 0 21)
				)
			)
			(86 (Swamp doVerb: theVerb))
			(88 (Swamp doVerb: theVerb))
			(93 (Swamp doVerb: theVerb))
			(36
				(if
					(and
						(!= cel 10)
						(or (== (ego view?) 56) (== (ego view?) 538))
					)
					(curRoom setScript: sArmFighting 0 self)
				)
			)
			(1
				(messager say: 5 1 0 0 0 535)
			)
			(4
				(messager say: 5 4 0 0 0 535)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(cond 
			(dead (self dispose:))
			(
			(or (== (ego view?) 56) (== (ego view?) 538)) (self setCycle: (myEndLoop new:)))
			(else (self setCycle: (cueEndLoop new:)))
		)
	)
	
	(method (getHurt &tmp [temp0 2])
		(self cycleSpeed: 6 setCycle: CT 0 -1 self dead: 1)
	)
)

(instance myEndLoop of CT
	(properties)
	
	(method (init param1)
		(super
			init:
				param1
				(if (== argc 2) 0 else (param1 lastCel:))
				(if (== argc 2) -1 else 1)
		)
	)
	
	(method (doit)
		(if
			(and
				(>= (client cel?) 2)
				(< (ego distanceTo: client) 10)
				(User canControl:)
			)
			(curRoom setScript: sEgoDrown)
		)
		(super doit: &rest)
	)
	
	(method (cycleDone)
		(if endCel
			(self init: client 1)
		else
			(if (User canControl:)
				(client setScript: (sSearchEgo new:) client client)
			)
			(super cycleDone:)
		)
	)
)

(instance cueEndLoop of CT
	(properties)
	
	(method (init param1)
		(super
			init:
				param1
				(if (== argc 2) 0 else (param1 lastCel:))
				(if (== argc 2) -1 else 1)
		)
	)
	
	(method (doit)
		(if
			(and
				(>= (client cel?) 2)
				(< (ego distanceTo: client) 10)
				(User canControl:)
			)
			(curRoom setScript: sEgoDrown)
		)
		(super doit: &rest)
	)
	
	(method (cycleDone)
		(if endCel
			(self init: client 1)
		else
			((Timer new:) setReal: client (Random 5 25))
			(super cycleDone:)
		)
	)
)

(instance sJumpWater of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 egoHeading castSize temp4)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gGTheObj_2CycleSpeed (ego cycleSpeed?))
				(= local1 (- local0 local1))
				(= egoHeading (ego heading?))
				(= temp0 (+ (ego x?) (SinMult egoHeading local1)))
				(= temp1 (- (ego y?) (CosMult egoHeading local1)))
				(dummy
					x: (ego x?)
					y: (ego y?)
					setStep: local1 local1
					signal: (| (dummy signal?) $0001)
					init:
					setMotion: MoveTo temp0 temp1 self
				)
			)
			(1
				(if
					(and
						(register isMemberOf: Feature)
						(not
							(((curRoom obstacles?) at: 0)
								onMe: (dummy x?) (dummy y?)
							)
						)
					)
					(messager say: 8 6 5 0 0 535)
					(theGame handsOn:)
					(self dispose:)
				else
					(= castSize (cast size:))
					(= local17 0)
					(= temp4 (= register 0))
					(while (< temp4 castSize)
						(if
							(and
								((cast at: temp4) isKindOf: SwampView)
								((cast at: temp4) onMe: (dummy x?) (dummy y?))
								((cast at: temp4) heading?)
							)
							(if ((cast at: temp4) onMe: ego)
								(messager say: 8 6 5 0 0 535)
								(= register 1)
								(break)
							)
							(= local17 (cast at: temp4))
						)
						(++ temp4)
					)
					(cond 
						(register (theGame handsOn:) (self dispose:))
						(local17
							((curRoom obstacles?) dispose:)
							(curRoom obstacles: 0 addObstacle: (local17 heading?))
							(= gGTheObj_2CycleSpeed (ego cycleSpeed?))
							(ego
								useStamina: 8
								useSkill: 15
								view: 30
								setLoop: (if (> (dummy x?) (ego x?)) 2 else 3)
								cel: 0
								code: 0
								cycleSpeed: 6
								moveSpeed: 6
								setScale: 0
								setPri: (local17 priority?)
								setCycle: CT 8 1
								setMotion: JumpTo (local17 approachX?) (local17 approachY?) self
							)
							(cast delete: dummy)
							(dummy dispose:)
						)
						(else (messager say: 8 6 5 0 0 535) (= ticks (= register 1)))
					)
				)
			)
			(2
				(if register
					(cast delete: dummy)
					(dummy dispose:)
					(theGame handsOn:)
					(self dispose:)
				else
					(ego setCycle: End self)
				)
			)
			(3
				(ego
					setPri: -1
					view: 0
					z: 0
					setLoop: -1
					setLoop: (ScriptID 28 1)
					setCycle: StopWalk 5
					setMotion: 0
					setPri: (+ (ego priority?) 2)
					changeGait: -1 0
					illegalBits: 0
					setPri: -1
					ignoreHorizon:
					state: 2
					x: (if (== (ego loop?) 3)
						(- (ego x?) 15)
					else
						(+ (ego x?) 15)
					)
					y: (+ (ego y?) 3)
					cycleSpeed: gGTheObj_2CycleSpeed
					moveSpeed: gGTheObj_2CycleSpeed
					setScaler: Scaler 100 60 125 65
				)
				(ego loop: 2)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGlideToHummock of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(walkHandler delete: curRoom)
				(= temp0 0)
				(while (< temp0 (cast size:))
					(if ((cast at: temp0) isMemberOf: SwampView)
						(walkHandler delete: (cast at: temp0))
					)
					(++ temp0)
				)
				(if gFShore (walkHandler delete: gFShore) (= gFShore 0))
				(= disabledActions (& disabledActions $fffc))
				(ego
					setMotion: PolyPath (register approachX?) (register approachY?) self
				)
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
				)
				(curRoom addObstacle: (register heading?))
			)
			(1
				(Bclr 149)
				(ego
					normalize:
					moveSpeed: gGTheObj_2CycleSpeed
					cycleSpeed: gGTheObj_2CycleSpeed
					setMotion:
						PolyPath
						(register approachX?)
						(+ (register approachY?) 1)
						self
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(class sSearchEgo of Script
	(properties
		scratch 0
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
		disposeNow 0
	)
	
	(method (doit &tmp theLastSeconds)
		(if script (script doit:))
		(cond 
			(disposeNow (self dispose:))
			(cycles (if (not (-- cycles)) (self cue:)))
			(seconds
				(if
				(!= lastSeconds (= theLastSeconds (GetTime 1)))
					(= lastSeconds theLastSeconds)
					(if (not (-- seconds)) (self cue:))
				)
			)
			(ticks
				(if
					(<=
						(= ticks (- ticks (Abs (- gameTime lastTicks))))
						0
					)
					(= ticks 0)
					(self cue:)
				)
				(= lastTicks gameTime)
			)
		)
	)
	
	(method (dispose)
		(if disposeNow (super dispose:) else (= disposeNow 1))
	)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(= temp0 (+ (ego x?) (- 25 (Random 0 50))))
				(= temp1 (+ (ego y?) (- 20 (Random 0 40))))
				(register setMotion: PolyPath temp0 temp1 self)
			)
			(1 (= ticks (Random 30 150)))
			(2 (self dispose:))
		)
	)
)

(instance sArmFighting of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: PolyPath (register x?) (register y?) self
				)
			)
			(1
				(ego
					view: 53
					setLoop: (if (>= (ego heading?) 180) 0 else 1)
					setCel: 0
					setCycle: End self
				)
			)
			(2
				(register setCycle: Beg self)
			)
			(3
				(ego view: 536 setLoop: -1 loop: 5 setCycle: StopWalk 538)
				((Timer new:) setReal: client (Random 5 25))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEgoDrown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(EgoDead 1 535)
				(self dispose:)
			)
		)
	)
)

(instance wisps of Actor
	(properties
		noun 10
		priority 190
		fixPriority 1
		view 532
		signal $6800
	)
	
	(method (init param1 &tmp temp0 temp1)
		(= loop (Random 0 3))
		(= temp0 (Random 5 140))
		(= temp1 (Random 80 130))
		(super init:)
		(self setLoop: param1 1)
		(if (< argc 2)
			(self
				x: temp0
				y: temp1
				moveSpeed: (Random 0 3)
				cycleSpeed: 3
				signal: (| (self signal?) $0001)
				setScaler: Scaler 100 60 125 65
				setCycle: Fwd
				setMotion:
					MoveTo
					(+ temp0 (- 10 (Random 5 15)))
					(+ temp1 (- 3 (Random 0 5)))
					self
			)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(if local5
			(if local3
				(ego drop: 9 1 get: 41 1 solvePuzzle: 445 6)
				(= local3 0)
				(messager say: 10 87 11 0 0 535)
			)
			(self dispose:)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(21
				(if (not (Btst 167))
					(curRoom setScript: (ScriptID 32) 0 21)
				)
			)
			(24
				(cond 
					((not local4) (messager say: 10 60 17 0 0 535))
					((== local4 15) (curRoom setScript: sCatchWisps))
					(else (messager say: 10 60 18 0 0 535))
				)
			)
			(23
				(curRoom setScript: sDownCandy)
			)
			(-87
				(if (ego has: 9)
					(= local3 1)
				else
					(messager say: 10 87 12 0 0 535)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (motionCue)
		(if (and mover (mover completed?)) (mover motionCue:))
	)
	
	(method (cue &tmp temp0 temp1)
		(cond 
			((OneOf curRoomNum 541 542 543 545) (self dispose:))
			(local2 (self setScript: (sToCandy new:) 0 self))
			(local3 (self setScript: (sToFlask new:) 0 self))
			(else
				(cond 
					((< (= temp0 (+ x (- 5 (Random 0 10)))) 0) (= temp0 0))
					((> temp0 148) (= temp0 148))
				)
				(cond 
					((> (= temp1 (+ y (- 3 (Random 0 6)))) 140) (= temp1 140))
					((< temp1 80) (= temp1 80))
				)
				(self setMotion: MoveTo temp0 temp1 self)
			)
		)
	)
)

(instance sDownCandy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 253 123 self)
			)
			(1
				(ego view: 4 setLoop: 0 setCycle: End self)
			)
			(2
				(ego drop: 8)
				(= local2 1)
				(messager say: 9 23 19 0 self 535)
			)
			(3 (ego setCycle: Beg self))
			(4
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sReleaseWisps of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 4 setLoop: 0 cel: 0 setCycle: End self)
			)
			(1
				(ego drop: 41 1 get: 9 1)
				(= local5 0)
				(= local2 0)
				(messager say: 0 60 28 0 0 535)
				(= local6 (IntArray new: 4))
				(= temp0 0)
				(while (< temp0 4)
					(local6 at: temp0 (wisps new:))
					((local6 at: temp0)
						x: (ego x?)
						y: (ego y?)
						moveSpeed: (Random 0 5)
						cycleSpeed: 3
						setScaler: Scaler 100 60 125 65
						setPri: 190
						signal: (| ((local6 at: temp0) signal?) $0001)
						setCycle: Fwd
						init: temp0 1
					)
					(if (OneOf curRoomNum 530 535)
						((local6 at: temp0)
							setMotion:
								MoveTo
								(+ 100 (Random 5 25))
								(- (ego y?) (Random 5 20))
								(local6 at: temp0)
						)
					else
						((local6 at: temp0)
							setMotion:
								MoveTo
								(if (> (ego x?) 160) -10 else 330)
								(- (ego y?) (Random 20 30))
								(local6 at: temp0)
						)
					)
					(++ temp0)
				)
				(= cycles 1)
			)
			(2 (ego setCycle: Beg self))
			(3
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCatchWisps of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 253 123 self)
			)
			(1
				(ego view: 4 setLoop: 0 cel: 0 setCycle: End self)
			)
			(2
				(ego drop: 9 1 get: 41 1 solvePuzzle: 445 6)
				(= local5 1)
				(messager say: 10 60 11 0 self 535)
			)
			(3 (ego setCycle: Beg self))
			(4
				(theGame handsOn:)
				(ego normalize:)
				(self dispose:)
			)
		)
	)
)

(instance sToCandy of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(register
					setSpeed: 0
					setMotion:
						MoveTo
						(+ 269 (Random 0 8))
						(- (+ 123 (Random 0 4)) 5)
						self
				)
			)
			(1
				(register setPri: -1)
				(= temp0 0)
				(while (& (>> local4 temp0) $0001)
					(++ temp0)
				)
				(= local4 (| local4 (<< $0001 temp0)))
				(self dispose:)
			)
		)
	)
)

(instance sToFlask of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(register
					setSpeed: 0
					setMotion:
						MoveTo
						(+ (- (ego x?) 5) (Random 0 5))
						(+ (- (ego y?) 25) (Random 0 4))
						self
				)
			)
			(1
				(register setPri: -1)
				(= temp0 0)
				(while (& (>> local4 temp0) $0001)
					(++ temp0)
				)
				(= local4 (| local4 (<< $0001 temp0)))
				(self dispose:)
			)
		)
	)
)

(instance sToJump of Script
	(properties)
	
	(method (changeState newState &tmp registerApproachX registerApproachY)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= registerApproachX (register approachX?))
				(= registerApproachY (register approachY?))
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
				)
				(curRoom obstacles: 0 addObstacle: (register heading?))
				(= gGTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					useStamina: 8
					useSkill: 15
					view: 30
					setLoop: (if (> mouseX (ego x?)) 2 else 3)
					cel: 0
					code: 0
					cycleSpeed: 6
					moveSpeed: 6
					setScale: 0
					setPri:
						(if (register isMemberOf: SwampView)
							(register priority?)
						else
							167
						)
					setCycle: CT 8 1
					setMotion: JumpTo registerApproachX registerApproachY self
				)
			)
			(1
				(ego setPri: -1 setCycle: End self)
			)
			(2
				(ego
					view: 0
					z: 0
					setLoop: -1
					setLoop: (ScriptID 28 1)
					setCycle: StopWalk 5
					setMotion: 0
					changeGait: -1 0
					ignoreHorizon:
					x: (if (== (ego loop?) 3)
						(- (ego x?) 15)
					else
						(+ (ego x?) 15)
					)
					y: (+ (ego y?) 3)
					cycleSpeed: gGTheObj_2CycleSpeed
					moveSpeed: gGTheObj_2CycleSpeed
					setScaler: Scaler 100 60 125 65
				)
				(ego loop: 2)
				(if (register isKindOf: View) 0 else (ego setPri: -1))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEgoDrownGlide of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 8 6 22 0 self 535)
			)
			(1
				(ego
					view: 5
					setPri: (ego priority?)
					setStep: 0 10
					setMotion: MoveTo (ego x?) (+ (ego y?) 15) self
				)
				(splash
					view: (if (OneOf curRoomNum 530 535) 531 else 551)
					x: (ego x?)
					y: (ego y?)
					setPri: (+ (ego priority?) 1)
					signal: (| (splash signal?) $0001)
					setCycle: End self
					init:
				)
			)
			(2 (ego hide:))
			(3 (= cycles 12))
			(4
				(EgoDead 23)
				(self dispose:)
			)
		)
	)
)

(class SwampPoly of Polygon
	(properties
		scratch 0
		size 0
		points 0
		type $0003
		dynamic 0
	)
	
	(method (dispose param1)
		(if (and argc param1) (super dispose:))
	)
)

(instance dummy of Actor
	(properties
		view 30
		loop 6
		signal $6800
		origStep 20560
		moveSpeed 0
	)
)

(instance reflection of Prop
	(properties
		priority 1
		fixPriority 1
		view 536
		signal $5000
		cycleSpeed 18
	)
	
	(method (init)
		(super init:)
		(self setScaler: Scaler 100 60 125 65)
		(self hide:)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(cond 
			((ego mover?)
				(reflection
					x: (ego x?)
					y: (ego y?)
					loop: (+ (ego loop?) 8)
					cel: (ego cel?)
				)
			)
			(
			(>= (Abs (- gameTime theGLastTicks)) cycleSpeed)
				(= theGLastTicks gameTime)
				(if (> (= temp0 (+ (reflection cel?) 1)) 7)
					(reflection cel: 0)
				else
					(reflection cel: temp0)
				)
			)
		)
	)
	
	(method (show)
		(super show: &rest)
		(= theGLastTicks gameTime)
	)
)

(instance splash of Prop
	(properties
		signal $4000
	)
)
