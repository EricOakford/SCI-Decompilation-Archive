;;; Sierra Script 1.0 - (do not remove this comment)
(script# 30500)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use Set)
(use CueMe)
(use Array)
(use Talker)
(use Feature)
(use StopWalk)
(use Grooper)
(use Motion)
(use Actor)

(public
	roMusicPuzzle 0
)

(local
	setSel_141
	local1
	local2
	local3
	local4
	theSinger
	local6
	local7
	local8
	local9
	local10
)
(procedure (localproc_01c2 &tmp temp0 temp1 temp2 temp3)
	(= temp2
		(IntArray
			newWith: 10 30514 30513 30512 30511 30510 30515 30516 30517 30518 30519
		)
	)
	(= temp3 (IntArray newWith: 10 9 8 7 6 5 13 11 10 14 12))
	(= local2
		(IntArray
			newWith:
				20
				68
				268
				116
				268
				164
				268
				212
				268
				260
				268
				370
				268
				418
				268
				466
				268
				514
				268
				562
				268
		)
	)
	(= local3
		(IntArray
			newWith:
				20
				110
				300
				145
				300
				180
				300
				215
				300
				250
				300
				380
				300
				415
				300
				450
				300
				485
				300
				510
				300
		)
	)
	(= local1 (IntArray newWith: 10 0 5 1 6 2 7 3 8 4 9))
	(= setSel_141 (Set new:))
	(= temp0 0)
	(while (< temp0 10)
		(setSel_141
			addToEnd:
				((Singer new:)
					audHolder: temp0
					tray: (temp2 at: temp0)
					case: (temp3 at: temp0)
					view: (+ 30410 temp0)
					heading: 225
					loop: 5
					yourself:
				)
		)
		(++ temp0)
	)
	(temp2 dispose:)
	(= temp2 0)
	(temp3 dispose:)
	(= temp3 0)
	(= temp0 0)
	(while (< temp0 10)
		(= temp1 (local1 at: temp0))
		((setSel_141 at: temp1)
			startCorder: temp0
			posn: (localproc_0487 temp0) (localproc_04c4 temp0)
			init:
		)
		(++ temp0)
	)
	(localproc_05c0)
)

(procedure (localproc_0487 param1)
	(if (not local2)
		(MonoOut {error in GetLocX -- 30400.sc DJM})
		(return 0)
	)
	(return (local2 at: (* param1 2)))
)

(procedure (localproc_04c4 param1)
	(if (not local2)
		(MonoOut {error in GetLocY -- 30400.sc DJM})
		(return 0)
	)
	(return (local2 at: (+ (* param1 2) 1)))
)

(procedure (localproc_0505 param1)
	(if (not local3)
		(MonoOut {error in GetLocX -- 30400.sc DJM})
		(return 0)
	)
	(return (local3 at: (* param1 2)))
)

(procedure (localproc_0542 param1)
	(if (not local3)
		(MonoOut {error in GetLocY -- 30400.sc DJM})
		(return 0)
	)
	(return (local3 at: (+ (* param1 2) 1)))
)

(procedure (localproc_0583 param1)
	(return (< param1 5))
)

(procedure (localproc_059d param1)
	(return (== (local1 at: param1) -1))
)

(procedure (localproc_05c0 &tmp temp0 temp1)
	(if (== local4 0)
		(= temp0 0)
		(while (< temp0 4)
			(if
				(and
					(not (localproc_059d temp0))
					(not (localproc_059d (+ temp0 1)))
					(not (localproc_0583 (local1 at: temp0)))
					(not (localproc_0583 (local1 at: (+ temp0 1))))
				)
				((setSel_141 at: (local1 at: temp0)) invObject: 2)
				((setSel_141 at: (local1 at: (+ temp0 1)))
					invObject: 1
				)
			else
				(if (not (localproc_059d temp0))
					((setSel_141 at: (local1 at: temp0)) sappify: 2)
				)
				(if (not (localproc_059d (+ temp0 1)))
					((setSel_141 at: (local1 at: (+ temp0 1))) sappify: 1)
				)
			)
			(if
				(and
					(not (localproc_059d (+ temp0 5)))
					(not (localproc_059d (+ temp0 6)))
					(localproc_0583 (local1 at: (+ temp0 5)))
					(localproc_0583 (local1 at: (+ temp0 6)))
				)
				((setSel_141 at: (local1 at: (+ temp0 5)))
					invObject: 2
				)
				((setSel_141 at: (local1 at: (+ temp0 6)))
					invObject: 1
				)
			else
				(if (not (localproc_059d (+ temp0 5)))
					((setSel_141 at: (local1 at: (+ temp0 5))) sappify: 2)
				)
				(if (not (localproc_059d (+ temp0 6)))
					((setSel_141 at: (local1 at: (+ temp0 6))) sappify: 1)
				)
			)
			(++ temp0)
		)
		(= temp1 1)
		(= temp0 0)
		(while (< temp0 5)
			(if
				(or
					(localproc_059d temp0)
					(localproc_059d (+ temp0 5))
					(localproc_0583 (local1 at: temp0))
					(not (localproc_0583 (local1 at: (+ temp0 5))))
				)
				(= temp1 0)
				(break)
			)
			(++ temp0)
		)
		(if temp1 (curRoom setScript: soWinStage1))
	else
		(= local6 1)
		(= local7 1)
		(= temp0 0)
		(while (< temp0 5)
			(if (!= (local1 at: temp0) (+ temp0 5)) (= local6 0))
			(if (!= (local1 at: (+ temp0 5)) temp0) (= local7 0))
			(++ temp0)
		)
	)
)

(instance foLeftSide of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setRect: 0 0 290 316)
		(self setVisibleRange: 80)
	)
	
	(method (doVerb)
		(curRoom setScript: soLeftSings)
	)
)

(instance foRightSide of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setRect: 340 0 632 316)
		(self setVisibleRange: 80)
	)
	
	(method (doVerb)
		(curRoom setScript: soRightSings)
	)
)

(instance poPodium of Prop
	(properties
		x 316
		y 316
		view 30505
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 80)
	)
	
	(method (doVerb)
		(voBaton init:)
		(= gVerb 1)
	)
)

(instance voBaton of View
	(properties
		x 316
		y 246
		view 30505
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self setPri: 400)
	)
	
	(method (doVerb)
		(= gVerb 80)
		(self dispose:)
	)
)

(instance coHandsOnWhenCuedThrice of CueMe
	(properties)
	
	(method (cue)
		(if (== (++ local10) 3) (theGame handsOn:))
	)
)

(class Singer of Actor
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
		nYTarget 0
		audHolder -1
		startCorder -1
		nMode 0
		bSapped 0
		nEatLoop 0
		nHideLoop 0
		nReturnLoop 0
		nStuckLoop 0
		tray 0
		compressShards 666
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= signal (| signal $5000))
		(= cycleSpeed (= moveSpeed 2))
		(self setLooper: Grooper)
	)
	
	(method (dispose)
		(if (and nEatLoop (nEatLoop scratch?))
			(nEatLoop dispose:)
			(= nEatLoop 0)
		)
		(if (and nHideLoop (nHideLoop scratch?))
			(nHideLoop dispose:)
			(= nHideLoop 0)
		)
		(super dispose: &rest)
	)
	
	(method (doVerb)
		(if (== local4 0)
			(if theSinger
				(if (== self theSinger)
					(self nMyFlag:)
				else
					(self BAD_SELECTOR: theSinger)
					(= theSinger 0)
				)
			else
				(self select:)
			)
		else
			(= local10 0)
			(theGame handsOff:)
			(self BAD_SELECTOR: coHandsOnWhenCuedThrice)
			((self eat:)
				BAD_SELECTOR: (self stick:) coHandsOnWhenCuedThrice
			)
		)
	)
	
	(method (cue)
		(self BAD_SELECTOR: 1)
	)
	
	(method (select)
		(if theSinger (theSinger nMyFlag:))
		(self BAD_SELECTOR:)
		(= theSinger self)
		(self setScript: (soSelect new:))
	)
	
	(method (nMyFlag)
		(if (!= self theSinger) (return))
		(= theSinger 0)
		(self setScript: (soUnselect new:))
	)
	
	(method (eat)
		(cond 
			((< audHolder 5)
				(if (== audHolder 4)
					(setSel_141 at: 0)
					(return)
				else
					(setSel_141 at: (+ audHolder 1))
					(return)
				)
			)
			((== audHolder 9) (setSel_141 at: 5) (return))
			(else (setSel_141 at: (+ audHolder 1)) (return))
		)
	)
	
	(method (stick)
		(cond 
			((< audHolder 5)
				(if (>= audHolder 3)
					(setSel_141 at: (- audHolder 3))
					(return)
				else
					(setSel_141 at: (+ audHolder 2))
					(return)
				)
			)
			((>= audHolder 8) (setSel_141 at: (- audHolder 3)) (return))
			(else (setSel_141 at: (+ audHolder 2)) (return))
		)
	)
	
	(method (BAD SELECTOR param1 param2 &tmp temp0)
		(if (or (not argc) (not param1))
			(MonoOut {illegal switchWith. 30500.sc djm})
			(return)
		)
		(if (> argc 1) (= temp0 param2) else (= temp0 0))
		(self
			setScript: (soStrollTo new:) temp0 (param1 startCorder?)
		)
		(param1
			setScript: (soStrollTo new:) temp0 (self startCorder?)
		)
	)
	
	(method (BAD SELECTOR theNStuckLoop)
		(if nReturnLoop (return))
		(if (and argc theNStuckLoop)
			(= nStuckLoop theNStuckLoop)
		else
			(= nStuckLoop 0)
		)
		(theSound cDownArrowCel: tray 0 0 case 1 self 30600)
		(MonoOut {ID: %d} audHolder)
		(= nReturnLoop 1)
		(self BAD_SELECTOR:)
	)
	
	(method (BAD SELECTOR param1)
		(if (not nReturnLoop) (return))
		(= nReturnLoop 0)
		(self BAD_SELECTOR:)
		(if (and argc param1 nStuckLoop)
			(nStuckLoop cue:)
			(= nStuckLoop 0)
		)
	)
	
	(method (invObject param1)
		(if (not argc)
			(MonoOut {invalid call of raiseHands. 30500.sc})
			(return)
		)
		(if (& param1 $0001) (= bSapped 1))
		(if (& param1 $0002) (= nMode 1))
		(self BAD_SELECTOR:)
	)
	
	(method (sappify param1)
		(if (not argc)
			(MonoOut {invalid call of lowerHands. 30500.sc})
			(return)
		)
		(if (& param1 $0001) (= bSapped 0))
		(if (& param1 $0002) (= nMode 0))
		(self BAD_SELECTOR:)
	)
	
	(method (BAD SELECTOR &tmp theView temp1 temp2)
		(if nReturnLoop
			(if (or (not cycler) (cycler isKindOf: StopWalk))
				(= view (+ 30420 audHolder))
				(= cel (= loop 0))
				(self setCycle: Fwd)
			)
			(return)
		)
		(if (== local4 0)
			(if nMode
				(self setCycle: 0)
				(= theView (+ 30420 audHolder))
				(= temp1 1)
				(= temp2 0)
				(if (not nEatLoop)
					(= nEatLoop
						((View new:)
							view: (+ 30420 audHolder)
							loop: 3
							cel: 0
							posn: x y
							init:
							setPri: (+ priority 1)
							yourself:
						)
					)
				)
			else
				(= theView (+ 30410 audHolder))
				(= temp2 5)
				(= temp1 8)
				(self setCycle: StopWalk -1)
				(if nEatLoop (nEatLoop dispose:) (= nEatLoop 0))
			)
			(cond 
				(bSapped
					(if (not nHideLoop)
						(= nHideLoop
							((View new:)
								view: (+ 30420 audHolder)
								loop: 2
								cel: 0
								posn: x y
								init:
								setPri: (- priority 1)
								yourself:
							)
						)
					)
				)
				(nHideLoop (nHideLoop dispose:) (= nHideLoop 0))
			)
			(if (!= view theView) (= view theView))
			(if (!= loop temp1) (self setLoop: temp1))
			(if (!= cel temp2) (self setCel: temp2))
		else
			(if nEatLoop (nEatLoop dispose:) (= nEatLoop 0))
			(if nHideLoop (nHideLoop dispose:) (= nHideLoop 0))
			(= view (+ 30410 audHolder))
			(cond 
				((localproc_0583 audHolder) (if local9 (= temp1 3) else (= temp1 5)))
				(local8 (= temp1 3))
				(else (= temp1 4))
			)
			(self setCycle: StopWalk -1)
			(self setLoop: temp1)
		)
	)
	
	(method (BAD SELECTOR)
		(if (== local4 0) (return 0))
		(return
			(switch audHolder
				(0 -10)
				(1 -5)
				(2 0)
				(3 5)
				(4 10)
				(5 10)
				(6 5)
				(7 0)
				(8 -5)
				(9 -10)
			)
		)
	)
)

(instance soLeftSings of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((setSel_141 at: (local1 at: 0)) BAD_SELECTOR: self)
			)
			(1
				((setSel_141 at: (local1 at: 1)) BAD_SELECTOR: self)
			)
			(2
				((setSel_141 at: (local1 at: 2)) BAD_SELECTOR: self)
			)
			(3
				((setSel_141 at: (local1 at: 3)) BAD_SELECTOR: self)
			)
			(4
				((setSel_141 at: (local1 at: 4)) BAD_SELECTOR: self)
			)
			(5
				(if local6
					(self setScript: soOpenLeftDoor self)
				else
					(self cue:)
				)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soRightSings of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((setSel_141 at: (local1 at: 5)) BAD_SELECTOR: self)
			)
			(1
				((setSel_141 at: (local1 at: 6)) BAD_SELECTOR: self)
			)
			(2
				((setSel_141 at: (local1 at: 7)) BAD_SELECTOR: self)
			)
			(3
				((setSel_141 at: (local1 at: 8)) BAD_SELECTOR: self)
			)
			(4
				((setSel_141 at: (local1 at: 9)) BAD_SELECTOR: self)
			)
			(5
				(if local7
					(self setScript: soOpenRightDoor self)
				else
					(self cue:)
				)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soOpenLeftDoor of TPScript
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= temp0 0)
				(while (< temp0 5)
					((setSel_141 at: (local1 at: temp0))
						heading: 135
						setLoop: 4
						setLooper: Grooper
						setCycle: StopWalk -1
						setHeading: 0
						setTotalWidth: 1
					)
					(++ temp0)
				)
				(foLeftSide dispose:)
				(self cue:)
			)
			(1
				(MonoOut {The left door opens!})
				(= local8 1)
				(if local9
					(self setScript: soWin self)
				else
					(messager say: 0 0 15 0 self 30600)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance soOpenRightDoor of TPScript
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= temp0 5)
				(while (< temp0 10)
					((setSel_141 at: (local1 at: temp0))
						heading: 215
						setLoop: 5
						setLooper: Grooper
						setCycle: StopWalk -1
						setHeading: 0
						setTotalWidth: 1
					)
					(++ temp0)
				)
				(foRightSide dispose:)
				(self cue:)
			)
			(1
				(MonoOut {The right door opens!})
				(= local9 1)
				(if local8
					(self setScript: soWin self)
				else
					(messager say: 0 0 15 0 self 30600)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance poTorin of Prop
	(properties)
)

(instance poLeenah of Prop
	(properties)
)

(instance toTorinEndCh3 of Talker
	(properties
		x 299
		y 263
		view 30503
		loop 4
		priority 400
	)
)

(instance toLeenahEndCh3 of Talker
	(properties
		x 299
		y 263
		view 30503
		loop 6
		priority 400
	)
)

(instance soWin of TPScript
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gVerb 1)
				(poPodium setCycle: Beg self)
				(messager sayRange: 0 0 4 1 3 self 30600)
			)
			(1)
			(2
				(poPodium dispose:)
				(MonoOut {Win!})
				(= temp0 0)
				(while (< temp0 10)
					((setSel_141 at: temp0)
						nCurPosY: 30
						posn: (localproc_0505 temp0) (localproc_0542 temp0)
					)
					(++ temp0)
				)
				(curRoom picture: 30501)
				(curRoom drawPic: 30501)
				(poTorin
					view: 30503
					posn: 299 263
					loop: 1
					cel: 0
					init:
					setCycle: End self
				)
				(poLeenah
					view: 30503
					posn: 299 263
					loop: 0
					cel: 0
					init:
					setCycle: End self
				)
				(messager sayRange: 0 0 4 4 5 0 30600)
			)
			(3)
			(4
				(poLeenah dispose:)
				(poTorin loop: 2 cel: 0 setCycle: End self)
			)
			(5
				(poTorin loop: 3 cel: 0)
				(= gToTorinPullsOutMeat toTorinEndCh3)
				(= gToLeenahReactsToLocket toLeenahEndCh3)
				(messager sayRange: 0 0 4 6 10 self 30600)
			)
			(6
				(poTorin loop: 7 cel: 0 setCycle: End self)
			)
			(7
				(messager sayRange: 0 0 4 11 13 self 30600)
			)
			(8
				(poLeenah view: 30504 loop: 0 cel: 0 init: setCycle: Fwd)
				(poTorin view: 30504 loop: 1 cel: 0 setCycle: End self)
			)
			(9
				(poTorin view: 30504 loop: 2 cel: 0 setCycle: End self)
			)
			(10
				(poLeenah dispose:)
				(poTorin loop: 3 cel: 0 setCycle: End self)
				(messager say: 0 0 4 14 0 30600)
			)
			(11
				(= gToTorinPullsOutMeat 0)
				(= gToLeenahReactsToLocket 0)
				(curRoom newRoom: -25536)
			)
		)
	)
)

(instance soWinStage1 of TPScript
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(MonoOut {Won stage 1. Moving to stage 2.})
				(= local4 1)
				(= temp0 0)
				(while (< temp0 5)
					((setSel_141 at: temp0)
						setScript: (soMenStage2 new:) self
					)
					((setSel_141 at: (+ temp0 5))
						setScript: (soWomenStage2 new:) self
					)
					(++ temp0)
				)
				(poPodium cel: 0 init: setCycle: End self)
			)
			(2)
			(3)
			(4)
			(5)
			(6)
			(7)
			(8)
			(9)
			(10)
			(11)
			(12
				(voBaton init:)
				(foLeftSide init:)
				(foRightSide init:)
				(theGame handsOn:)
				(theMusic pageSize: 0)
				(self dispose:)
			)
		)
	)
)

(instance soMenStage2 of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(client sappify: 3)
				(client
					heading: 215
					setCycle: StopWalk -1
					setLooper: Grooper
					setMotion:
						MoveTo
						(client x?)
						(+ (client y?) (client BAD_SELECTOR:))
						self
				)
			)
			(2
				(client setHeading: 225 self)
			)
			(3 (self dispose:))
		)
	)
)

(instance soWomenStage2 of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(client sappify: 3)
				(client
					heading: 215
					setCycle: StopWalk -1
					setLooper: Grooper
					setMotion:
						MoveTo
						(client x?)
						(+ (client y?) (client BAD_SELECTOR:))
						self
				)
			)
			(2
				(client setHeading: 135 self)
			)
			(3 (self dispose:))
		)
	)
)

(instance soSelect of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setTotalWidth: 1)
				(local1 at: (client startCorder?) -1)
				(client sappify: 3)
				(localproc_05c0)
				(client setCycle: StopWalk -1)
				(client
					setMotion: MoveTo (client x?) (+ (client y?) 5) self
				)
			)
			(1
				(client setVisibleRange: 1)
				(client setCycle: 0)
				(self dispose:)
			)
		)
	)
)

(instance soUnselect of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setTotalWidth: 1)
				(client setCycle: StopWalk -1)
				(client setLoop: 2 1)
				(client
					setMotion: MoveTo (client x?) (- (client y?) 5) self
				)
			)
			(1
				(client setLoop: -1)
				(client setHeading: 225 self)
			)
			(2
				(client setVisibleRange: 1)
				(client setCycle: 0)
				(local1 at: (client startCorder?) (client audHolder?))
				(localproc_05c0)
				(self dispose:)
			)
		)
	)
)

(class soStrollTo of TPScript
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
		oMessageList 0
		nTextWidth 0
		createDisplay 1
		BAD_SELECTOR -1
		BAD_SELECTOR -1
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setTotalWidth: 1)
				(client sappify: 3)
				(= BAD_SELECTOR (client startCorder?))
				(= BAD_SELECTOR register)
				(local1 at: BAD_SELECTOR -1)
				(localproc_05c0)
				(client setCycle: StopWalk -1)
				(if (< BAD_SELECTOR BAD_SELECTOR)
					(client
						setMotion:
							MoveTo
							(localproc_0487 BAD_SELECTOR)
							(- (localproc_04c4 BAD_SELECTOR) 15)
							self
					)
				else
					(client
						setMotion:
							MoveTo
							(localproc_0487 BAD_SELECTOR)
							(+ (localproc_04c4 BAD_SELECTOR) 15)
							self
					)
				)
			)
			(1
				(if (< BAD_SELECTOR BAD_SELECTOR)
					(client
						setMotion:
							MoveTo
							(localproc_0487 BAD_SELECTOR)
							(- (localproc_04c4 BAD_SELECTOR) 15)
							self
					)
				else
					(client
						setMotion:
							MoveTo
							(localproc_0487 BAD_SELECTOR)
							(+ (localproc_04c4 BAD_SELECTOR) 15)
							self
					)
				)
			)
			(2
				(client
					setMotion:
						MoveTo
						(localproc_0487 BAD_SELECTOR)
						(+ (localproc_04c4 BAD_SELECTOR) (client BAD_SELECTOR:))
						self
				)
			)
			(3
				(if
					(and
						(not (localproc_0583 (client audHolder?)))
						(== local4 1)
					)
					(client setHeading: 135 self)
				else
					(client setHeading: 225 self)
				)
			)
			(4
				(client startCorder: BAD_SELECTOR)
				(client setVisibleRange: 1)
				(local1 at: BAD_SELECTOR (client audHolder?))
				(if (== local4 0) (client setCycle: 0))
				(localproc_05c0)
				(self dispose:)
			)
		)
	)
)

(instance soStartMeUp of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 0 0 3 0 self 30600)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance roMusicPuzzle of TPRoom
	(properties
		picture 30500
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: 30500)
		(localproc_01c2)
		(curRoom setScript: soStartMeUp)
	)
	
	(method (dispose)
		(if local2 (local2 dispose:) (= local2 0))
		(if local1 (local1 dispose:) (= local1 0))
		(if local3 (local3 dispose:) (= local3 0))
		(if setSel_141
			(setSel_141 release:)
			(setSel_141 dispose:)
			(= setSel_141 0)
		)
		(super dispose: &rest)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
