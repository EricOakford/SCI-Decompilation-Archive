;;; Sierra Script 1.0 - (do not remove this comment)
(script# 80)
(include sci.sh)
(use Main)
(use TextIcon)
(use Array)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	swap 0
)

(local
	local0
	local1
	theTextIcon
	local3
	local4
	local5
	local6
	local7
	local8
)
(procedure (localproc_00e6 &tmp temp0 temp1)
	(if (== arcadeLevel 1)
		(while (== (local4 at: (= temp0 (Random 0 11))) -1)
		)
	else
		(while (== (local4 at: (= temp0 (Random 0 19))) -1)
		)
	)
	(= temp1 (local4 at: temp0))
	(local4 at: temp0 -1)
	(return temp1)
)

(procedure (localproc_0143 &tmp temp0 temp1)
	(if (== arcadeLevel 1) (= temp1 12) else (= temp1 20))
	(= temp0 0)
	(while (< temp0 temp1)
		(if
			(or
				(!=
					((local6 at: temp0) value?)
					((local6 at: temp0) value3?)
				)
				(and
					(!= ((local6 at: temp0) cel?) 0)
					(!= ((local6 at: temp0) cel?) 4)
				)
			)
			(return 0)
		)
		(++ temp0)
	)
	(return 1)
)

(class SelectProp of Prop
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
	)
	
	(method (highlight)
	)
	
	(method (select)
		(self doVerb:)
	)
)

(instance swap of PuzzleBar
	(properties)
	
	(method (init &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 [temp8 2])
		(Bclr 52)
		(= local4 (IntArray new: 20))
		(= local5 (IntArray new: 20))
		(= local6 (IntArray new: 20))
		(Load rsVIEW 389)
		(super init:)
		(= local0 (CelWide 383 0 0))
		(= local1 (CelHigh 383 0 0))
		(= local3
			(-
				(-
					(/ (- (plane right:) (plane left:)) 2)
					(/ (* local0 5) 2)
				)
				4
			)
		)
		(self add: lock)
		(highLightCel init: puzzleCast hide:)
		(highLightCel2 init: puzzleCast hide:)
		(coverUp init: puzzleCast)
		(pointerCel init: puzzleCast hide:)
		(sideViewLeft init: puzzleCast)
		(sideViewRight init: puzzleCast)
		(if (== arcadeLevel 1)
			(local5 at: 0 1)
			(local4 at: 0 1)
			(local5 at: 1 2)
			(local4 at: 1 2)
			(local5 at: 2 3)
			(local4 at: 2 3)
			(local5 at: 3 6)
			(local4 at: 3 6)
			(local5 at: 4 7)
			(local4 at: 4 7)
			(local5 at: 5 8)
			(local4 at: 5 8)
			(local5 at: 6 11)
			(local4 at: 6 11)
			(local5 at: 7 12)
			(local4 at: 7 12)
			(local5 at: 8 13)
			(local4 at: 8 13)
			(local5 at: 9 16)
			(local4 at: 9 16)
			(local5 at: 10 17)
			(local4 at: 10 17)
			(local5 at: 11 18)
			(local4 at: 11 18)
		else
			(= temp0 0)
			(while (< temp0 20)
				(local4 at: temp0 temp0)
				(++ temp0)
			)
		)
		(= temp3 36)
		(if (== arcadeLevel 1)
			(= temp5 12)
			(= temp6 3)
		else
			(= temp5 20)
			(= temp6 5)
		)
		(= temp0 0)
		(= temp1 -1)
		(while (< temp0 temp5)
			(if (mod temp0 temp6)
				(= temp2 (+ temp2 local0))
			else
				(if (== arcadeLevel 1)
					(= temp2 (+ local3 local0))
				else
					(= temp2 local3)
				)
				(= temp3 (+ 36 (* local1 (/ temp0 temp6))))
			)
			(if (not (mod temp0 2)) (++ temp1))
			(= temp4 (localproc_00e6))
			(local6
				at:
					temp0
					(= temp7
						((myButton new:)
							nsLeft: temp2
							x: temp2
							nsTop: temp3
							y: temp3
							cel:
								(switch arcadeLevel
									(1 (if (mod temp4 2) 4 else 0))
									(2 (if (mod temp4 2) 4 else 0))
									(else 
										(if (mod temp4 2) (Random 4 7) else (Random 0 3))
									)
								)
							loop: (/ temp4 2)
							value: (+ temp4 1)
							value3:
								(if (== arcadeLevel 1)
									(+ (local5 at: temp0) 1)
								else
									(+ temp0 1)
								)
							yourself:
						)
					)
			)
			(self add: temp7)
			(++ temp0)
		)
		(rotateIcon signal: 5)
		(self add: rotateIcon)
	)
	
	(method (doit &tmp temp0 temp1 temp2 temp3)
		(while
			(and
				(& state $0020)
				(= temp0 ((user curEvent?) new:))
			)
			(= gameTime (+ tickOffset (GetTime)))
			(puzzleCast doit:)
			(if (not (& (lock signal?) $0008)) (lock doit:))
			(FrameOut)
			(temp0 localize: plane)
			(= temp1 (temp0 type?))
			(= temp2 (temp0 message?))
			(= temp3 (temp0 modifiers?))
			(if (== temp1 32) (temp0 type: 4 message: 13))
			(MapKeyToDir temp0)
			(if noHands (temp0 type: 0))
			(breakif (self dispatchEvent: temp0))
		)
	)
	
	(method (dispose)
		(local4 dispose:)
		(local5 dispose:)
		(local6 dispose:)
		(self changeCursor: 942)
		(super dispose:)
		(DisposeScript 80)
	)
	
	(method (dispatchEvent event &tmp temp0 temp1 eventMessage temp3 eventClaimed [temp5 2])
		(= gameTime (+ tickOffset (GetTime)))
		(if script (script doit:))
		(if doMotionCue
			(= doMotionCue 0)
			(puzzleCast eachElementDo: #motionCue)
			(lock motionCue:)
		)
		(if cuees (cuees eachElementDo: #doit))
		(if (not (talkers isEmpty:))
			(repeat
				(talkers
					eachElementDo: #doit
					firstTrue: #handleEvent event
				)
				(= gameTime (+ tickOffset (GetTime)))
				(sounds eachElementDo: #check)
				(FrameOut)
				(breakif (talkers allTrue: #isModeless 2))
				((User curEvent?) new:)
			)
			(event claimed: 1)
			(return 0)
		)
		(if (not (prints isEmpty:))
			(prints eachElementDo: #doit)
			(if (prints firstTrue: #isModeless 1)
				(if (and ((= event (Event new:)) type?) prints)
					(prints firstTrue: #handleEvent event)
				)
				(event dispose:)
				(= gameTime (+ tickOffset (GetTime)))
				(return (FrameOut))
			)
		)
		(sounds eachElementDo: #check)
		(= eventX (event x?))
		(= eventY (event y?))
		(= temp1 (if noHands 0 else (event type?)))
		(= eventMessage (event message?))
		(= eventClaimed (event claimed?))
		(if (not noHands)
			(= temp3 (self firstTrue: #onMe eventX eventY))
		else
			(= temp3 0)
		)
		(if (and (!= highlightedIcon temp3) (not local7))
			(self highlight: temp3)
		)
		(event dispose:)
		(switch temp1
			(0 (= eventClaimed 0))
			(1
				(cond 
					(
						(and
							(& (event modifiers?) emSHIFT)
							highlightedIcon
							(== arcadeLevel 3)
						)
						(highlightedIcon doAction2:)
					)
					(
						(and
							temp3
							(self select: temp3 1)
							(= eventClaimed (& (temp3 signal?) $0040))
						)
						(= state (& state $ffdf))
					)
				)
			)
			(4
				(switch eventMessage
					(KEY_RETURN
						(if temp3
							(self select: temp3)
							(= eventClaimed (& (temp3 signal?) $0040))
						)
					)
					(KEY_SHIFTTAB (self retreat:))
					(KEY_TAB (self advance:))
				)
			)
			(else 
				(if (& temp1 $0010)
					(switch eventMessage
						(JOY_RIGHT (self advance:))
						(JOY_LEFT (self retreat:))
					)
				)
			)
		)
		(return eventClaimed)
	)
	
	(method (resetPuzzle)
		(SetNowSeen rotateIcon)
	)
	
	(method (helpYou)
		(if (not (Btst 52))
			(lock show:)
			(FrameOut)
			(while
			(not (OneOf (((user curEvent?) new:) type?) 2 8 64))
			)
			(lock hide:)
		)
	)
	
	(method (giveYou &tmp temp0)
		(if local8
			(if (ego has: 58)
				(Bclr 51)
				(theGame
					setCursor: ((theIconBar getCursor:)
						view: 905
						loop: 2
						cel: 15
						yourself:
					)
				)
				(Bset 51)
			else
				(messager say: 20 4 21)
			)
		else
			(messager say: 20 4 23)
		)
	)
	
	(method (setPlane)
		(= usePlane 1)
		(plane
			bitmap: 0
			priority: (+ (GetHighPlanePri) 1)
			init:
			setRect: 62 20 230 165
			setBitmap: 935 0 0 1
			addCast: puzzleCast
		)
	)
)

(instance reward of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(if (== arcadeLevel 1) (= temp1 12) else (= temp1 20))
				(= temp0 0)
				(while (< temp0 temp1)
					((local6 at: temp0) signal: 5)
					(++ temp0)
				)
				(= local8 1)
				(swap noHands: 1)
				(= cycles 2)
			)
			(1
				(highLightCel setPri: 80 hide:)
				(highLightCel2 setPri: 80 hide:)
				(lock
					setLoop: 0 1
					setCel: 0
					cycleSpeed: 8
					setPri: 110
					show:
				)
				(= seconds 2)
			)
			(2 (lock setCycle: End self))
			(3
				(Bset 52)
				(swap noHands: 0)
				(self dispose:)
			)
		)
	)
)

(instance lock of SelectProp
	(properties
		x 37
		y 83
		z 40
		priority 207
		fixPriority 1
		view 389
		cel 6
	)
	
	(method (init)
		(super init: &rest)
		(self hide:)
	)
	
	(method (doVerb)
		(Bset 375)
		(swap state: (& (swap state?) $ffdf))
	)
)

(instance highLightCel of View
	(properties
		priority 120
		fixPriority 1
		view 383
		loop 10
	)
)

(instance highLightCel2 of View
	(properties
		priority 120
		fixPriority 1
		view 383
		loop 10
	)
)

(instance coverUp of View
	(properties
		x 35
		y 104
		priority 120
		fixPriority 1
		view 383
		loop 12
	)
)

(instance pointerCel of View
	(properties
		view 383
	)
)

(instance sideViewLeft of View
	(properties
		x 7
		y 30
		view 383
		loop 13
	)
)

(instance sideViewRight of View
	(properties
		x 136
		y 30
		view 383
		loop 13
	)
)

(instance myButton of TextIcon
	(properties
		priority 100
		fixPriority 1
		view 383
		cel 0
	)
	
	(method (select &tmp theValue temp1 temp2)
		(if
			(cond 
				((& signal $0004) 0)
				((and argc (& signal $0001)) downClick temp2)
				(else 1)
			)
			(swap noHands: 0)
			(cond 
				((and theTextIcon (!= self theTextIcon))
					(rotateIcon signal: (| (rotateIcon signal?) $0004))
					(coverUp setPri: 180 show:)
					(FrameOut)
					(= theValue (theTextIcon value?))
					(theTextIcon value: value)
					(= value theValue)
					(= theValue (theTextIcon cel?))
					(theTextIcon cel: cel)
					(= cel theValue)
					(= theValue (theTextIcon loop?))
					(theTextIcon loop: loop)
					(= loop theValue)
					(= value2 0)
					(theTextIcon value2: 0)
					(highLightCel2 setPri: 80 hide:)
					(pointerCel
						loop: (theTextIcon loop?)
						cel: (theTextIcon cel?)
						x: (theTextIcon nsLeft?)
						y: (theTextIcon nsTop?)
						show:
					)
					(self loop: loop cel: cel x: x y: y show:)
					(FrameOut)
					(= theTextIcon 0)
					(= local7 0)
					(if (localproc_0143) (swap setScript: reward))
				)
				(value2
					(= theTextIcon 0)
					(= value2 0)
					(rotateIcon signal: (| (rotateIcon signal?) $0004))
					(coverUp loop: 12 cel: 0 setPri: 180 show:)
					(= local7 1)
					(highLightCel2 x: x y: y cel: 2 setPri: 190 show:)
					(FrameOut)
				)
				(else
					(= theTextIcon self)
					(= value2 1)
					(if (== arcadeLevel 3)
						(rotateIcon signal: (& (rotateIcon signal?) $fffb))
						(coverUp setPri: 80)
						(rotateIcon x: 34 y: 104 cel: 2)
					)
					(= local7 1)
					(highLightCel2 x: x y: y cel: 2 setPri: 190 show:)
					(FrameOut)
				)
			)
		)
	)
	
	(method (highlight param1)
		(if param1
			(if value2
				(highLightCel2 x: x y: y cel: 1 show:)
			else
				(highLightCel x: x y: y setCel: 0 show:)
			)
		else
			(highLightCel x: x y: y cel: 0 show:)
			(if value2 (highLightCel2 x: x y: y cel: 1 hide:))
		)
	)
	
	(method (doAction2)
		(cond 
			((< cel 4) (if (== (++ cel) 4) (= cel 0)))
			((== (++ cel) 8) (= cel 4))
		)
		(self x: x y: y cel: cel loop: loop)
		(highLightCel x: x y: y cel: 0 show:)
		(if value2
			(highLightCel2 x: x y: y cel: 1 setPri: 80 hide:)
		)
		(FrameOut)
		(if (localproc_0143) (swap setScript: reward))
	)
)

(instance rotateIcon of TextIcon
	(properties
		x 34
		y 104
		priority 100
		fixPriority 1
		view 383
		loop 11
		signal $0005
		maskView 383
		maskLoop 12
	)
	
	(method (select param1 &tmp temp0 temp1)
		(asm
			lal      theTextIcon
			bnt      code_0e6d
			pTos     signal
			ldi      4
			and     
			not     
			bnt      code_0e6d
			lap      argc
			bnt      code_0e54
			lap      param1
			bnt      code_0e54
			pTos     signal
			ldi      1
			and     
			bnt      code_0e54
			pushi    #setPri
			pushi    1
			pushi    80
			lofsa    coverUp
			send     6
			ldi      1
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
code_0dbd:
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			pushi    #curEvent
			pushi    0
			lag      user
			send     4
			send     4
			sat      temp0
			send     4
			push    
			ldi      2
			ne?     
			bnt      code_0e3a
			pushi    #localize
			pushi    1
			pTos     plane
			lat      temp0
			send     6
			pushi    #setPri
			pushi    1
			pushi    80
			lofsa    coverUp
			send     6
			pushi    #onMe
			pushi    2
			pushi    #x
			pushi    0
			lat      temp0
			send     4
			push    
			pushi    #y
			pushi    0
			lat      temp0
			send     4
			push    
			self     8
			bnt      code_0e23
			lat      temp1
			not     
			bnt      code_0dbd
			ldi      1
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
			jmp      code_0dbd
code_0e23:
			lat      temp1
			bnt      code_0dbd
			ldi      0
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
			jmp      code_0dbd
code_0e3a:
			lst      temp1
			ldi      1
			eq?     
			bnt      code_0e50
			ldi      0
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
code_0e50:
			lat      temp1
			jmp      code_0e62
code_0e54:
			pushi    #setPri
			pushi    1
			pushi    180
			lofsa    coverUp
			send     6
			ldi      1
code_0e62:
			bnt      code_0e6d
			pushi    #doAction2
			pushi    0
			lal      theTextIcon
			send     4
code_0e6d:
			ret     
		)
	)
)
