;;; Sierra Script 1.0 - (do not remove this comment)
(script# 30400)
(include sci.sh)
(use Main)
(use ScrollExit)
(use TPRoom)
(use TPScript)
(use Set)
(use CueMe)
(use ExitFeature)
(use Array)
(use Scaler)
(use RandCyc)
(use StopWalk)
(use Grooper)
(use Motion)
(use Actor)

(public
	roStarPuzzle 0
)

(local
	local0
	local1
	local2
	local3
	setSel_141
	theSel_1279
	theGSel_57
	local7
	local8
	local9
	local10
	local11
	local12
	[local13 2]
)
(procedure (localproc_01b2 &tmp temp0 temp1 temp2 temp3)
	(= theGSel_57 gameTime)
	(= local7 1800)
	(= local0
		(IntArray newWith: 11 0 3 17 5 9 18 6 10 20 24 12)
	)
	(= local1
		(IntArray
			newWith:
				22
				319
				126
				132
				102
				273
				102
				319
				77
				368
				102
				509
				102
				410
				139
				519
				226
				319
				166
				114
				226
				229
				139
		)
	)
	(= local2 (IntArray newWith: 11 0 1 2 5 6 9 8 4 3 7 10))
	(= theSel_1279 0)
	(= local3 (IntArray newWith: 5 0 0 0 0 0))
	(= setSel_141 (Set new:))
	(= temp0 0)
	(while (< temp0 10)
		(= temp1 (+ temp0 1))
		(= temp3
			(switch (= temp2 (Random 0 7))
				(0 90)
				(1 270)
				(2 180)
				(3 0)
				(4 135)
				(5 225)
				(6 45)
				(7 315)
			)
		)
		(setSel_141
			addToEnd:
				((Ostiary new:)
					audHolder: temp1
					view: (+ 30410 temp0)
					heading: temp3
					loop: temp2
					yourself:
				)
		)
		(++ temp0)
	)
	(= temp0 0)
	(while (< temp0 11)
		(if (= temp1 (local2 at: temp0))
			((setSel_141 at: (- temp1 1))
				startCorder: temp0
				posn: (localproc_042f temp0) (localproc_046c temp0)
				init:
				setHeading: 180
			)
		)
		(++ temp0)
	)
	(= local10 1)
	(localproc_04ad)
)

(procedure (localproc_042f param1)
	(if (not local1)
		(MonoOut {error in GetLocX -- 30400.sc DJM})
		(return 0)
	)
	(return (local1 at: (* param1 2)))
)

(procedure (localproc_046c param1)
	(if (not local1)
		(MonoOut {error in GetLocY -- 30400.sc DJM})
		(return 0)
	)
	(return (local1 at: (+ (* param1 2) 1)))
)

(procedure (localproc_04ad)
	(local3
		at:
			0
			(&
				(local0 at: (local2 at: 1))
				(local0 at: (local2 at: 2))
				(local0 at: (local2 at: 4))
				(local0 at: (local2 at: 5))
			)
	)
	(local3
		at:
			1
			(&
				(local0 at: (local2 at: 3))
				(local0 at: (local2 at: 4))
				(local0 at: (local2 at: 6))
				(local0 at: (local2 at: 7))
			)
	)
	(local3
		at:
			2
			(&
				(local0 at: (local2 at: 5))
				(local0 at: (local2 at: 6))
				(local0 at: (local2 at: 8))
				(local0 at: (local2 at: 9))
			)
	)
	(local3
		at:
			3
			(&
				(local0 at: (local2 at: 7))
				(local0 at: (local2 at: 8))
				(local0 at: (local2 at: 10))
				(local0 at: (local2 at: 1))
			)
	)
	(local3
		at:
			4
			(&
				(local0 at: (local2 at: 9))
				(local0 at: (local2 at: 10))
				(local0 at: (local2 at: 2))
				(local0 at: (local2 at: 3))
			)
	)
	(if
		(and
			(local3 at: 0)
			(local3 at: 1)
			(local3 at: 2)
			(local3 at: 3)
			(local3 at: 4)
		)
		(MonoOut {Winning position!})
		(curRoom setScript: soPuzzleSolved)
	)
)

(procedure (localproc_074c &tmp temp0 temp1)
	(= temp0 (IntArray newWith: 4 0 0 0 0))
	(= temp1 0)
	(cond 
		((local3 at: 0) (temp0 at: 0 1 2 4 5))
		((local3 at: 1) (temp0 at: 0 3 4 6 7))
		((local3 at: 2) (temp0 at: 0 5 6 8 9))
		((local3 at: 3) (temp0 at: 0 7 8 10 1))
		((local3 at: 4) (temp0 at: 0 9 10 2 3))
		(else (= temp1 1))
	)
	(if temp1
		((setSel_141 at: (Random 0 9)) doVerb: 7777)
	else
		((setSel_141
			at: (- (local2 at: (temp0 at: (Random 0 3))) 1)
		)
			doVerb: 7777
		)
	)
	(temp0 dispose:)
	(= temp0 0)
)

(instance foExit of CUExitFeature
	(properties)
	
	(method (doVerb)
		(messager say: 0 0 16 0 0 30600)
	)
)

(instance oDoneTalking of CueMe
	(properties)
	
	(method (cue)
		(= local8 0)
	)
)

(class Ostiary of Actor
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
		stopCorder -1
		setWinner -1
		deleteShard 0
		addBrokenShard 1
		fadeIn 0
		addRandomShards 0
		insertShard 0
		compressShards 666
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 7777)
		(= signal (| signal $5000))
		(self
			setCycle: StopWalk -1
			setLooper: Grooper
			setScaler: Scaler 100 80 251 83
		)
	)
	
	(method (doit &tmp temp0 theDeleteShard temp2 temp3 temp4 temp5 temp6)
		(super doit: &rest)
		(if
		(and (not addBrokenShard) (> (- gameTime fadeIn) 120))
			(MonoOut {FailSafe Off -- Ostiary %d} audHolder)
			(= addBrokenShard 1)
			(= fadeIn gameTime)
		)
		(if (and mover addBrokenShard)
			(cond 
				((or (!= x addRandomShards) (!= y insertShard))
					(= addRandomShards x)
					(= insertShard y)
					(= fadeIn gameTime)
					(= temp0 0)
					(while (< temp0 10)
						(if
							(and
								(!= (= theDeleteShard (setSel_141 at: temp0)) self)
								(!= theDeleteShard deleteShard)
							)
							(= temp2 (- (theDeleteShard x?) x))
							(= temp3 (* (- (theDeleteShard y?) y) 3))
							(if (< (= temp6 (GetDistance 0 0 temp2 temp3)) 28)
								(if (not local8)
									(theSound
										lDownArrowLoop: (- (+ 1 audHolder) 1) 0 18 1 oDoneTalking 30600 1
									)
									(= local8 1)
								)
								(= temp4 (+ x (/ (- 0 temp3) 2)))
								(= temp5 (+ y (/ temp2 6)))
								(self setLoop: loop 1)
								(= deleteShard theDeleteShard)
								(self setMotion: MoveTo temp4 temp5 self)
							)
						)
						(++ temp0)
					)
				)
				((> (- gameTime fadeIn) 20)
					(MonoOut {FailSafe On -- Ostiary %d} audHolder)
					(= fadeIn gameTime)
					(= addBrokenShard 0)
					(self setLoop: -1)
					(self
						setMotion:
							MoveTo
							(localproc_042f setWinner)
							(localproc_046c setWinner)
							self
					)
					(= deleteShard 0)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(= fadeIn gameTime)
		(self setLoop: -1)
		(= addBrokenShard 1)
		(= insertShard (= addRandomShards -1))
		(if (== theVerb 1)
			(= theGSel_57 gameTime)
			(= local7 1800)
			(= local11 0)
		else
			(= local11 1)
		)
		(if
		(and (== theVerb 1) (not local8) (< local9 2))
			(= local8 1)
			(++ local9)
			(if (== theSel_1279 0)
				(messager say: 0 0 1 1 oDoneTalking 30600)
			else
				(messager say: 0 0 1 2 oDoneTalking 30600)
			)
		)
		(if (!= startCorder -1)
			(= stopCorder startCorder)
		else
			(= stopCorder setWinner)
		)
		(= startCorder -1)
		(= setWinner theSel_1279)
		(self
			setMotion:
				MoveTo
				(localproc_042f setWinner)
				(localproc_046c setWinner)
				self
		)
		(= theSel_1279 stopCorder)
		(local2 at: theSel_1279 0)
		(localproc_04ad)
	)
	
	(method (cue)
		(if (not local11)
			(= theGSel_57 gameTime)
			(= local7 1800)
		)
		(if deleteShard
			(self setLoop: -1)
			(self
				setMotion:
					MoveTo
					(localproc_042f setWinner)
					(localproc_046c setWinner)
					self
			)
			(= deleteShard 0)
			(return)
		)
		(if (!= startCorder -1)
			(MonoOut
				{integrity failure -- invalid cue, point: %d, guy: %d}
				setWinner
				audHolder
			)
			(return)
		)
		(= startCorder setWinner)
		(= setWinner -1)
		(local2 at: startCorder audHolder)
		(self setHeading: 180)
		(localproc_04ad)
	)
)

(instance poTorin of Prop
	(properties)
)

(instance poBoogle of Prop
	(properties)
)

(instance poLeenah of Prop
	(properties)
)

(class SmallOst of Prop
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
		audHolder 0
	)
	
	(method (init)
		(= view (+ 30410 audHolder))
		(= loop 8)
		(= cel (Random 0 7))
		(self nCurPosY: 30)
		(super init: &rest)
		(= cycleSpeed (Random 10 30))
		(self setCycle: RandCycle)
	)
)

(instance oSmallOst0 of SmallOst
	(properties
		x 343
		y 223
	)
)

(instance oSmallOst1 of SmallOst
	(properties
		x 378
		y 232
		audHolder 1
	)
)

(instance oSmallOst2 of SmallOst
	(properties
		x 409
		y 224
		audHolder 2
	)
)

(instance oSmallOst3 of SmallOst
	(properties
		x 403
		y 203
		audHolder 3
	)
)

(instance oSmallOst4 of SmallOst
	(properties
		x 357
		y 205
		audHolder 4
	)
)

(instance oSmallOst5 of SmallOst
	(properties
		x 266
		y 206
		audHolder 5
	)
)

(instance oSmallOst6 of SmallOst
	(properties
		x 311
		y 251
		audHolder 6
	)
)

(instance oSmallOst7 of SmallOst
	(properties
		x 428
		y 247
		audHolder 7
	)
)

(instance oSmallOst8 of SmallOst
	(properties
		x 471
		y 209
		audHolder 8
	)
)

(instance oSmallOst9 of SmallOst
	(properties
		x 382
		y 179
		audHolder 9
	)
)

(instance soPuzzleSolved of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 0 0 2 0 self 30600)
			)
			(1
				(curRoom initThumb: oTreeScrollPlane)
				(oTreeScrollPlane fadeRel: 0 632)
				(= ticks 120)
			)
			(2
				(oTreeScrollPlane sitNSpin: 0 0 self 4 6 6)
			)
			(3 (= ticks 120))
			(4 (curRoom newRoom: 30500))
		)
	)
)

(instance soFirstScrewup of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= theGSel_57 gameTime)
				(= local7 9000)
				(= local12 1)
				(= local8 1)
				(localproc_074c)
				(messager sayRange: 0 0 17 1 2 self 30600)
			)
			(1
				(localproc_074c)
				(= theGSel_57 gameTime)
				(= local7 70)
				(messager sayRange: 0 0 17 3 6 self 30600)
			)
			(2
				(= local8 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soPlayRoomIntro of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom picture: 30400)
				(curRoom drawPic: 30400)
				(poTorin
					view: 30400
					loop: 0
					cel: 0
					posn: 324 259
					init:
					setCycle: End self
				)
				(poLeenah view: 30400 loop: 1 cel: 0 posn: 324 259 init:)
				(poBoogle view: 30400 loop: 2 cel: 0 posn: 324 259 init:)
			)
			(1
				(poTorin hide:)
				(poLeenah loop: 1 cel: 1 setCycle: End self)
			)
			(2
				(poTorin show: loop: 3 cel: 0 setCycle: End self)
			)
			(3
				(poBoogle setCycle: End self)
			)
			(4
				(poTorin dispose:)
				(poBoogle dispose:)
				(poLeenah dispose:)
				(curRoom picture: 30403)
				(curRoom drawPic: 30403)
				(oSmallOst0 init:)
				(oSmallOst1 init:)
				(oSmallOst2 init:)
				(oSmallOst3 init:)
				(oSmallOst4 init:)
				(oSmallOst5 init:)
				(oSmallOst6 init:)
				(oSmallOst7 init:)
				(oSmallOst8 init:)
				(oSmallOst9 init:)
				(foExit init:)
				(poTorin
					view: 30403
					loop: 0
					cel: 0
					posn: 284 226
					init:
					setCycle: End self
				)
				(poBoogle
					view: 30403
					loop: 2
					cel: 0
					posn: 198 243
					init:
					setCycle: End self
				)
				(poLeenah
					view: 30403
					loop: 1
					cel: 0
					posn: 352 212
					init:
					setCycle: End self
				)
			)
			(5)
			(6)
			(7 (= ticks 120))
			(8
				(oSmallOst0 dispose:)
				(oSmallOst1 dispose:)
				(oSmallOst2 dispose:)
				(oSmallOst3 dispose:)
				(oSmallOst4 dispose:)
				(oSmallOst5 dispose:)
				(oSmallOst6 dispose:)
				(oSmallOst7 dispose:)
				(oSmallOst8 dispose:)
				(oSmallOst9 dispose:)
				(poTorin dispose:)
				(poBoogle dispose:)
				(poLeenah dispose:)
				(curRoom picture: 30404)
				(curRoom drawPic: 30404)
				(localproc_01b2)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance oTreeScrollPlane of TorScrollPlane
	(properties
		priority 20
	)
	
	(method (init)
		(super init: (thePlane findData:) 948)
	)
	
	(method (nSeq)
		(AddPicAt self 30402 0 0)
		(AddPicAt self 30401 0 316)
		(AddPicAt self 30400 0 632)
	)
)

(instance roStarPuzzle of TPRoom
	(properties
		picture 30400
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: 30400)
		(= local10 0)
		(curRoom setScript: soPlayRoomIntro)
	)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and local10 (> (- gameTime theGSel_57) local7))
			(if (not local12)
				(self setScript: soFirstScrewup)
				(return)
			)
			(localproc_074c)
			(= theGSel_57 gameTime)
			(= local7 70)
		)
	)
	
	(method (dispose)
		(if local0 (local0 dispose:) (= local0 0))
		(if local1 (local1 dispose:) (= local1 0))
		(if local2 (local2 dispose:) (= local2 0))
		(if local3 (local3 dispose:) (= local3 0))
		(if setSel_141
			(setSel_141 release:)
			(setSel_141 dispose:)
			(= setSel_141 0)
		)
		(theSound stop:)
		(super dispose: &rest)
	)
	
	(method (setWander)
	)
	
	(method (zipTo)
		(curRoom setScript: soPuzzleSolved)
	)
	
	(method (intoPouch)
	)
)
