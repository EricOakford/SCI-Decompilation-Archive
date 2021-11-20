;;; Sierra Script 1.0 - (do not remove this comment)
(script# 34)
(include sci.sh)
(use Main)
(use Plane)
(use IconBar)
(use User)
(use System)

(public
	PuzzleBar 0
	TextIcon 1
	pHelp 2
	pExit 3
)

(local
	local0
)
(procedure (localproc_092c &tmp temp0)
	(while ((= temp0 ((user curEvent?) new:)) type?)
		(temp0 dispose:)
	)
	(temp0 dispose:)
)

(class TextIcon of IconI
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
	
	(method (init theOwner &tmp temp0)
		(= nsRight (+ nsLeft (CelWide view loop cel)))
		(= nsBottom (+ nsTop (CelHigh view loop cel)))
		(= owner theOwner)
		(= signal (& signal $fff7))
		((owner puzzleCast?) add: self)
		(= plane (owner plane?))
		(AddScreenItem self)
	)
	
	(method (show)
		(= signal (| signal $0020))
		(UpdateScreenItem self)
	)
	
	(method (onMe theObjOrX theY)
		(return
			(if
				(and
					(< nsLeft theObjOrX)
					(< theObjOrX nsRight)
					(< nsTop theY)
				)
				(< theY nsBottom)
			else
				0
			)
		)
	)
	
	(method (select param1 &tmp temp0 temp1)
		(asm
			lap      argc
			bnt      code_02c3
			lap      param1
			bnt      code_02c3
			pTos     signal
			ldi      1
			and     
			bnt      code_02c3
			ldi      1
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
code_0238:
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
			bnt      code_02a9
			pushi    #localize
			pushi    1
			pTos     plane
			lat      temp0
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
			bnt      code_0292
			lat      temp1
			not     
			bnt      code_0238
			ldi      1
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
			jmp      code_0238
code_0292:
			lat      temp1
			bnt      code_0238
			ldi      0
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
			jmp      code_0238
code_02a9:
			lst      temp1
			ldi      1
			eq?     
			bnt      code_02bf
			ldi      0
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
code_02bf:
			lat      temp1
			jmp      code_02c5
code_02c3:
			ldi      1
code_02c5:
			ret     
		)
	)
	
	(method (highlight param1)
		(= cel (if param1 0 else 2))
		(UpdateScreenItem self)
		(FrameOut)
	)
	
	(method (doAction1)
	)
	
	(method (doAction2)
	)
)

(class PuzzleBar of IconBar
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
	)
	
	(method (init)
		(ego useSkill: 1 50)
		(= gPuzzleBar self)
		(= puzzleCast (Cast new:))
		(puzzleCast add:)
		(= plane puzzlePlane)
		(self add: setPlane: addIcons:)
	)
	
	(method (doit &tmp temp0 temp1 temp2 temp3)
		(while
			(and
				(& state $0020)
				(= temp0 ((user curEvent?) new:))
			)
			(= gameTime (+ tickOffset (GetTime)))
			(puzzleCast doit:)
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
		(if script (script dispose:) (= script 0))
		(if puzzleCast (puzzleCast dispose:) (= puzzleCast 0))
		(plane deleteCast: self dispose:)
		(= plane 0)
		(if elements (DisposeList elements))
		(= size (= elements 0))
		(DisposeClone self)
	)
	
	(method (show &tmp [temp0 3] temp3)
		(= highlightedIcon (= curIcon 0))
		(= state (| state $0020))
		(UpdatePlane plane)
		(if picture (plane drawPic: picture))
		(if usePlane
			(pBag
				cel: 2
				nsLeft:
					(= temp3
						(+ (- 150 (CelWide (pBag view?) (pBag loop?) 0)) 2)
					)
				x:
					(pExit
						cel: 2
						nsLeft:
							(= temp3
								(+
									(- 75 (/ (CelWide (pExit view?) (pExit loop?) 0) 2))
									5
								)
							)
						x: temp3
					)
			)
		)
		(self eachElementDo: #init self)
		(cond 
			((not curIcon) 0)
			((HaveMouse) (self changeCursor: theCursor))
			(else
				(self
					changeCursor: theCursor
					changeCursor:
						(+
							(curIcon nsLeft?)
							(/ (- (curIcon nsRight?) (curIcon nsLeft?)) 2)
						)
						(- (curIcon nsBottom?) 3)
				)
			)
		)
		(localproc_092c)
		(self resetPuzzle:)
		(self doit: hide:)
		(return 1)
	)
	
	(method (eachElementDo param1 &tmp theNextNode_2 temp1 theNextNode)
		(= theNextNode_2 (FirstNode elements))
		(while theNextNode_2
			(= nextNode (NextNode theNextNode_2))
			(= temp1 (NodeValue theNextNode_2))
			(= theNextNode nextNode)
			(temp1 param1: &rest)
			(= nextNode theNextNode)
			(= theNextNode_2 nextNode)
		)
	)
	
	(method (hide)
		(if (& state $0020) (= state (& state $ffdf)))
		(if picture (curRoom drawPic: (curRoom picture?) 0))
		(= usePlane 0)
	)
	
	(method (highlight theHighlightedIcon param2)
		(if highlightedIcon (highlightedIcon highlight: 0))
		(cond 
			((not (= highlightedIcon theHighlightedIcon)) 0)
			((& (theHighlightedIcon signal?) $0004) (= highlightedIcon 0))
			(noHands 0)
			(else
				(theHighlightedIcon highlight: 1)
				(if (and (>= argc 2) param2)
					(self changeCursor: theCursor)
				)
			)
		)
	)
	
	(method (dispatchEvent event &tmp temp0 temp1 eventMessage temp3 eventClaimed [temp5 2])
		(if script (script doit:))
		(if doMotionCue
			(= doMotionCue 0)
			(puzzleCast eachElementDo: #motionCue)
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
			(repeat
				(prints
					eachElementDo: #doit
					firstTrue: #handleEvent event
				)
				(= gameTime (+ tickOffset (GetTime)))
				(sounds eachElementDo: #check)
				(FrameOut)
				(breakif (prints allTrue: #isModeless 2))
				((User curEvent?) new:)
			)
			(event claimed: 1)
			(return 0)
		)
		(sounds eachElementDo: #check)
		(= eventX (event x?))
		(= eventY (event y?))
		(= temp1 (if noHands 0 else (event type?)))
		(= eventMessage (event message?))
		(= eventClaimed (event claimed?))
		(if
			(and
				(!=
					highlightedIcon
					(= temp3 (self firstTrue: #onMe eventX eventY))
				)
				(not noHands)
			)
			(self highlight: temp3)
		)
		(event dispose:)
		(switch temp1
			(0 (= eventClaimed 0))
			(1
				(if
					(and
						temp3
						(self select: temp3 1)
						(= eventClaimed (& (temp3 signal?) $0040))
					)
					(= state (& state $ffdf))
				)
			)
		)
		(return eventClaimed)
	)
	
	(method (resetPuzzle)
		(self eachElementDo: #show)
	)
	
	(method (setScript newScript)
		(if script (script dispose:))
		(if newScript (newScript init: self &rest))
	)
	
	(method (changeCursor param1 param2 param3 &tmp temp0 theIconBarGetCursor)
		(= temp0
			(= theIconBarGetCursor (theIconBar getCursor:))
		)
		(switch argc
			(0 (return temp0))
			(1
				(self cursorView: param1)
				(self cursorLoop: 0)
				(self cursorCel: 0)
				(theIconBarGetCursor view: param1)
				(theIconBarGetCursor loop: 0)
				(theIconBarGetCursor cel: 0)
				(theGame setCursor: theIconBarGetCursor)
			)
			(2 (SetCursor param1 param2))
			(3
				(self cursorView: param1)
				(self cursorLoop: param2)
				(self cursorCel: param3)
				(SetCursor cursorView cursorLoop cursorCel &rest)
			)
		)
		(return temp0)
	)
	
	(method (helpYou)
		(messager say: 27 0 40 0 0 0)
	)
	
	(method (giveYou)
		(messager say: 27 0 0 0 0 0)
	)
	
	(method (setPlane)
		(= usePlane 1)
		(plane bitmap: 0)
		(plane
			priority: (+ (GetHighPlanePri) 1)
			init:
			setRect: 82 40 250 175
			setBitmap: 935 0 0 1
			addCast: puzzleCast
		)
	)
	
	(method (addIcons)
		(self add: pHelp pExit pBag)
	)
)

(instance puzzlePlane of Plane
	(properties
		left 82
		top 40
		right 250
		bottom 175
		picture -2
	)
)

(instance pHelp of TextIcon
	(properties
		nsLeft 8
		nsTop 8
		x 9
		y 8
		view 936
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 100)
	)
	
	(method (select)
		(if (super select: &rest) (gPuzzleBar helpYou:))
	)
)

(instance pExit of TextIcon
	(properties
		nsTop 8
		y 8
		view 936
		loop 2
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 100)
	)
	
	(method (select)
		(if (super select: &rest)
			(gPuzzleBar state: (& (gPuzzleBar state?) $ffdf))
		)
	)
)

(instance pBag of TextIcon
	(properties
		nsTop 8
		y 8
		view 936
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 100)
	)
	
	(method (select)
		(if (super select: &rest) (gPuzzleBar giveYou:))
	)
)
