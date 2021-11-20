;;; Sierra Script 1.0 - (do not remove this comment)
(script# 38)
(include sci.sh)
(use Main)
(use TextIcon)
(use combat)
(use Motion)
(use Actor)
(use System)


(class SView of View
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
		signal $4021
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
	)
	
	(method (init param1)
		(= signal (& signal $fff7))
		((param1 extraCast?) add: self)
		(= plane (param1 plane?))
		(AddScreenItem self)
		(= -info- (| -info- $0010))
		(SetNowSeen self)
	)
	
	(method (doVerb)
	)
)

(class SActor of Actor
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
		signal $0021
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
		typeView 0
		width 0
		height 0
		attackCode 0
		explodeX 0
	)
	
	(method (init param1)
		(= signal (& signal $fff7))
		((param1 puzzleCast?) add: self)
		(= plane (param1 plane?))
		(AddScreenItem self)
		(= -info- (| -info- $0010))
		(SetNowSeen self)
		(= width (/ (- nsRight nsLeft) 2))
		(= height (/ (- nsBottom nsTop) 2))
	)
	
	(method (doit)
		(return
			(if (not (& signal $0008))
				(if script (script doit:))
				(if mover (mover doit:))
				(if cycler (cycler doit:))
				(= xLast x)
				(= yLast y)
				(if (& -info- $0008) (UpdateScreenItem self))
				(return 1)
			else
				(return 0)
			)
		)
	)
	
	(method (doVerb)
		(switch ((gPuzzleBar combatEvent?) modifiers?)
			(8
				(if (== (gSActor typeView?) 44)
					(gPuzzleBar move: 14)
				else
					(gPuzzleBar move:)
				)
			)
			(4 (gPuzzleBar move: 15))
			(else  (gPuzzleBar move: 4))
		)
	)
	
	(method (onMe theObjOrX theY)
		(IsOnMe theObjOrX theY self (& signal $1000))
	)
	
	(method (cantBeHere)
		(return 0)
	)
	
	(method (getHurt)
	)
	
	(method (dedectPts)
	)
)

(class CombatSpell of SActor
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
		view 26
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $0021
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
		cycleSpeed 1
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
		typeView 0
		width 0
		height 0
		attackCode 0
		explodeX 0
		myTarget 0
		oldTarget 0
		type $0000
		active 0
		hit 0
	)
	
	(method (init)
		(super init: &rest)
		(= xStep gXStep)
	)
	
	(method (doit)
		(return
			(if (super doit:)
				(if (and myTarget (myTarget onMe: x y))
					(if
						(and
							(== ((= oldTarget myTarget) attackCode?) 23)
							(not global189)
						)
						(= myTarget 0)
						(self
							active: 0
							setPri: 240
							setMotion: 0
							setLoop: (+ loop (if (> loop 5) 1 else 2)) 1
							cel: 0
							setCycle: End self
						)
					else
						(= myTarget 0)
						(self toDamage:)
					)
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
	
	(method (hide)
		(= active 0)
		(super hide: &rest)
	)
	
	(method (setCycle cType)
		(if (== cType End) (proc810_13 3 944))
		(super setCycle: cType &rest)
	)
	
	(method (setMotion)
		(= active 1)
		(proc810_13 3 933)
		(super setMotion: &rest)
	)
	
	(method (toDamage)
	)
)

(class CombatIcon of TextIcon
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
		cel 2
		bitmap 0
		yStep 2
		signal $0081
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
		type $4000
		message -1
		modifiers $0000
		mainView 0
		mainLoop 0
		mainCel 0
		maskView 0
		maskLoop 0
		maskCel 0
		cursorView -1
		cursorLoop -1
		cursorCel -1
		highlightColor 7
		lowlightColor 0
		helpVerb 0
		object 0
		selector 0
		owner 0
		value 0
		value2 0
		value3 0
		font 999
		text 0
		textColor 50
		textType 0
		downClick 0
		upClick 0
		upClickOff 0
	)
	
	(method (init theOwner)
		(= nsRight (+ nsLeft (CelWide view loop cel)))
		(= nsBottom (+ nsTop (CelHigh view loop cel)))
		(= owner theOwner)
		(= signal (& signal $fff7))
		((owner extraCast?) add: self)
		(= plane (owner plane?))
		(AddScreenItem self)
	)
)

(class CombatBar of PuzzleBar
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
		underBits 0
		oldMouseX 0
		oldMouseY 0
		curIcon 0
		highlightedIcon 0
		prevIcon 0
		curInvIcon 0
		useIconItem 0
		helpIconItem 0
		walkIconItem 0
		plane 0
		state $0400
		y 0
		puzzleCast 0
		script 0
		eventX 0
		eventY 0
		picture 0
		noHands 0
		oldCursor 0
		cursorView 0
		cursorLoop 0
		cursorCel 0
		usePlane 0
		combatEvent 0
		extraCast 0
	)
	
	(method (init)
		(cond 
			((> global191 200) (= gXStep 12))
			((> global191 160) (= gXStep 10))
			((> global191 100) (= gXStep 9))
			((> global191 70) (= gXStep 9))
			((> global191 40) (= gXStep 8))
			((> global191 30) (= gXStep 4))
			(else (= gXStep 1))
		)
		(= extraCast (Cast new:))
		(super init: &rest)
	)
	
	(method (doit &tmp temp0)
		(while
			(and
				(& state $0020)
				(= combatEvent ((user curEvent?) new:))
			)
			(= gameTime (+ tickOffset (GetTime)))
			(puzzleCast doit:)
			(FrameOut)
			(if noHands (combatEvent type: 0))
			(breakif (self dispatchEvent:))
		)
		(= temp0 (GetTime))
		(while (< (- (GetTime) temp0) 60)
			(FrameOut)
			(sounds eachElementDo: #check)
		)
	)
	
	(method (dispose)
		(if extraCast (extraCast dispose:))
		(super dispose:)
	)
	
	(method (select theCurIcon param2)
		(return
			(if
				(if (theCurIcon isKindOf: TextIcon)
					(theCurIcon select: (if (>= argc 2) param2 else 0))
				else
					(theCurIcon doVerb:)
					0
				)
				(if (not (& (theCurIcon signal?) $0002))
					(= curIcon theCurIcon)
				)
				1
			else
				0
			)
		)
	)
	
	(method (dispatchEvent &tmp temp0 gSActorScript)
		(if script (script doit:))
		(if doMotionCue
			(= doMotionCue 0)
			(puzzleCast eachElementDo: #motionCue)
		)
		(if cuees
			(cuees eachElementDo: #doit)
			(if (cuees isEmpty:) (cuees dispose:) (= cuees 0))
		)
		(sounds eachElementDo: #check)
		(if (& (combatEvent type?) $02ff)
			(if noHands
				(switch (combatEvent type?)
					(1
						(if (Btst 374)
							(if (== (combatEvent modifiers?) 3)
								(Bset 376)
								(if
									(and
										(= gSActorScript (gSActor script?))
										(not (gSActorScript script?))
									)
									(gSActor cue:)
								)
							else
								((ScriptID 40 13) init: show: dispose:)
							)
						)
					)
					(4
						(if (== (combatEvent message?) 32)
							((ScriptID 40 13) init: show: dispose:)
						)
					)
				)
			else
				(= temp0
					(self
						firstTrue: #onMe (combatEvent x?) (combatEvent y?)
					)
				)
				(switch (combatEvent type?)
					(1
						(cond 
							((Btst 374)
								(if (== (combatEvent modifiers?) 3)
									(Bset 376)
									(if
										(and
											(= gSActorScript (gSActor script?))
											(not (gSActorScript script?))
										)
										(gSActor cue:)
									)
								else
									((ScriptID 40 13) init: show: dispose:)
								)
							)
							(temp0 (self select: temp0 1))
							((Btst 374)
								(if (== (combatEvent modifiers?) 3)
									(Bset 376)
									(if (not (gSActor script?))
										(gSActor cue:)
										(if (Btst 374) ((ScriptID 40 14) hide:))
									)
								else
									((ScriptID 40 13) init: show: dispose:)
								)
							)
							(
								(or
									(& (combatEvent modifiers?) $0002)
									(& (combatEvent modifiers?) $0001)
								)
								(cond 
									(
									(and (> (combatEvent y?) 156) (== heroType 2)) (self move: 13))
									((< (combatEvent y?) 63)
										(if
										(> (combatEvent x?) (+ (gSActor x?) (gSActor width:)))
											(self move: 3)
										else
											(self move: 16)
										)
									)
									(else (self move: 4))
								)
							)
							((< (combatEvent y?) 63)
								(cond 
									(
									(> (combatEvent x?) (+ (gSActor x?) (gSActor width:))) (self move: 17))
									(
									(< (combatEvent x?) (- (gSActor x?) (gSActor width:))) (self move: 18))
									(else (self move: 2))
								)
							)
							((< (combatEvent y?) 156)
								(switch (combatEvent modifiers?)
									(8
										(if (== (gSActor typeView?) 44)
											(self move: 14)
										else
											(self move:)
										)
									)
									(4 (self move: 15))
									(else  (self move:))
								)
							)
						)
					)
					(4
						(if
							(or
								(& (combatEvent modifiers?) $0002)
								(& (combatEvent modifiers?) $0001)
							)
							(switch (combatEvent message?)
								(18432 (self move: 3))
								(19712 (self move: 4))
								(19200 (self move: 19))
								(20480 (self move: 20))
							)
						else
							(switch (combatEvent message?)
								(27 (self move: 7))
								(15104
									(Bset 376)
									(cond 
										((Btst 374)
											(if
												(and
													(= gSActorScript (gSActor script?))
													(not (gSActorScript script?))
												)
												(gSActor cue:)
											)
										)
										((not (gSActor script?)) (gSActor cue:) ((ScriptID 40 14) hide:))
									)
								)
								(32
									(if (Btst 374) ((ScriptID 40 13) init: show: dispose:))
								)
								(19200 (self move: 1))
								(19712 (self move: 0))
								(20480 (self move: 6))
								(18432 (self move: 2))
								(122 (self move: 8))
								(120 (self move: 9))
								(99 (self move: 10))
								(118 (self move: 11))
								(98 (self move: 12))
							)
						)
					)
					(512
						(cond 
							((< (combatEvent z?) 0) (self move: 6))
							((> (combatEvent z?) 0) (self move: 2))
							((< (combatEvent roll?) 0) (self move: 0))
							((> (combatEvent roll?) 0) (self move: 1))
							((combatEvent yaw?) (self move: 4))
						)
					)
				)
			)
		)
		(return 0)
	)
	
	(method (setPlane)
		(= usePlane 0)
		(plane
			priority: (+ (GetHighPlanePri) 1)
			init:
			setRect: 0 0 319 199
			drawPic: (curRoom picture?) (if (Random 0 1) 0 else 1024)
			addCast: puzzleCast
		)
	)
	
	(method (addIcons)
	)
	
	(method (move)
	)
)

(class ShotTo of Obj
	(properties
		scratch 0
		client 0
		caller 0
		x 0
		y 0
		dx 0
		b-moveCnt 0
		completed 0
	)
	
	(method (init theClient theX theY theCaller &tmp clientCycler)
		(if (>= argc 1)
			(= client theClient)
			(if (>= argc 2)
				(= x theX)
				(if (>= argc 3)
					(= y theY)
					(if (>= argc 4) (= caller theCaller))
				)
			)
		)
		(= completed 0)
		(if (> x (client x?))
			(= dx (client xStep?))
		else
			(= dx (- 0 (client xStep?)))
		)
		(= b-moveCnt (+ 1 (client moveSpeed?) gameTime))
		(if (= clientCycler (client cycler?))
			(clientCycler cycleCnt: b-moveCnt)
		)
	)
	
	(method (doit)
		(if
		(>= (Abs (- gameTime b-moveCnt)) (client moveSpeed?))
			(cond 
				((< dx 0)
					(if (<= (client x?) x)
						(self moveDone:)
					else
						(= b-moveCnt gameTime)
						(client x: (+ (client x?) dx))
					)
				)
				((>= (client x?) x) (self moveDone:))
				(else (= b-moveCnt gameTime) (client x: (+ (client x?) dx)))
			)
		)
	)
	
	(method (moveDone)
		(= completed 1)
		(if caller (= doMotionCue 1) else (self motionCue:))
	)
	
	(method (motionCue)
		(client mover: 0)
		(if (and completed caller) (caller cue:))
		(self dispose:)
	)
)

(class CombatAttack of CT
	(properties
		scratch 0
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		clientLastCel 0
		endCel 0
		myTarget 0
		targetDist 0
		startCel 0
		hitPts 0
	)
	
	(method (init param1 param2 theCycleDir theCaller theMyTarget theTargetDist theStartCel theHitPts &tmp clientLastCel)
		(super init: param1)
		(= cycleDir theCycleDir)
		(= caller theCaller)
		(= myTarget theMyTarget)
		(= targetDist theTargetDist)
		(= startCel theStartCel)
		(= hitPts theHitPts)
		(= endCel
			(if (> param2 (= clientLastCel (client lastCel:)))
				clientLastCel
			else
				param2
			)
		)
	)
	
	(method (doit &tmp combatAttackNextCel clientLastCel)
		(if (> endCel (= clientLastCel (client lastCel:)))
			(= endCel clientLastCel)
		)
		(= combatAttackNextCel (self nextCel:))
		(if
			(and
				myTarget
				(!= (myTarget attackCode?) 23)
				(<= (Abs (- (client x?) (myTarget x?))) targetDist)
				(>= combatAttackNextCel startCel)
			)
			(myTarget getHurt: 2)
			(= myTarget 0)
			(client dedectPts: hitPts)
		)
		(client
			cel:
				(cond 
					((> combatAttackNextCel clientLastCel) 0)
					((< combatAttackNextCel 0) clientLastCel)
					(else combatAttackNextCel)
				)
		)
		(if
		(and (== gameTime cycleCnt) (== endCel (client cel?)))
			(self cycleDone:)
		)
	)
)
