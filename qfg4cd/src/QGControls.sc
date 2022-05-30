;;; Sierra Script 1.0 - (do not remove this comment)
(script# GLORY_CONTROLS)
(include game.sh)
(use Main)
(use Plane)
(use Print)
(use IconBar)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	controlPanel 0
)

(local
	controlCast
	cantInput
)
(class QGSlider of IconItem
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 33
		nsRight 0
		nsBottom 97
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
		view 933
		loop 10
		cel 2
		bitmap 0
		yStep 2
		signal $0001
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
		highlightColor 0
		lowlightColor 0
		helpVerb 0
		object 0
		selector 0
		theObj 0
		cycler 0
		cycleSpeed 6
	)
	
	(method (init)
		(= x (- nsLeft 8))
		(= nsRight (+ nsLeft 23))
		(= signal (& signal $fff7))
		(controlCast add: self)
		(= plane controlPlane)
		(AddScreenItem self)
	)
	
	(method (dispose)
		(if cycler (self setCycle: 0))
	)
	
	(method (show)
		(= signal (| signal $0020))
		(UpdateScreenItem self)
	)
	
	(method (onMe theObjOrX)
		(return
			(if
				(and
					(< nsLeft (theObjOrX x?))
					(< (theObjOrX x?) nsRight)
					(< nsTop (theObjOrX y?))
				)
				(< (theObjOrX y?) nsBottom)
			else
				0
			)
		)
	)
	
	(method (select &tmp temp0)
		(while
			(not
				(OneOf
					((= temp0 ((user curEvent?) new:)) type?)
					2
					8
					64
				)
			)
			(temp0 localize: controlPlane)
			(cond 
				((< (temp0 y?) nsTop) (= y nsTop))
				((> (temp0 y?) 84) (= y 84))
				(else (= y (temp0 y?)))
			)
			(UpdateScreenItem self)
			(self move:)
			(FrameOut)
		)
		(temp0 localize: controlPlane)
		(cond 
			((< (temp0 y?) nsTop) (= y nsTop))
			((> (temp0 y?) 84) (= y 84))
			(else (= y (temp0 y?)))
		)
		(UpdateScreenItem self)
	)
	
	(method (highlight)
	)
	
	(method (move)
	)
	
	(method (setCycle cType)
		(if cycler (cycler dispose:))
		(if cType
			(= cycler
				(if (& (cType -info-?) $8000) (cType new:) else cType)
			)
			(cycler init: self &rest)
		else
			(= cycler 0)
		)
	)
)

(class QGControlIcon of IconItem
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
		signal $0001
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
		highlightColor 0
		lowlightColor 0
		helpVerb 0
		object 0
		selector 0
	)
	
	(method (init)
		(= nsRight (+ nsLeft (CelWide view loop cel)))
		(= nsBottom (+ nsTop (CelHigh view loop cel)))
		(= signal (& signal $fff7))
		(controlCast add: self)
		(= plane controlPlane)
		(AddScreenItem self)
	)
	
	(method (dispose)
	)
	
	(method (show)
		(= signal (| signal $0020))
		(UpdateScreenItem self)
	)
	
	(method (onMe theObjOrX)
		(return
			(if
				(and
					(< nsLeft (theObjOrX x?))
					(< (theObjOrX x?) nsRight)
					(< nsTop (theObjOrX y?))
				)
				(< (theObjOrX y?) nsBottom)
			else
				0
			)
		)
	)
	
	(method (select param1 &tmp temp0 temp1 temp2)
		(asm
			lap      argc
			bnt      code_04df
			lap      param1
			bnt      code_04df
			pTos     signal
			ldi      1
			and     
			bnt      code_04df
			ldi      1
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
code_0460:
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
			bnt      code_04c5
			pushi    #localize
			pushi    1
			lofsa    controlPlane
			push    
			lat      temp0
			send     6
			pushi    #onMe
			pushi    1
			lst      temp0
			self     6
			bnt      code_04ae
			lat      temp1
			not     
			bnt      code_0460
			ldi      1
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
			jmp      code_0460
code_04ae:
			lat      temp1
			bnt      code_0460
			ldi      0
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
			jmp      code_0460
code_04c5:
			lst      temp1
			ldi      1
			eq?     
			bnt      code_04db
			ldi      0
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
code_04db:
			lat      temp1
			jmp      code_04e1
code_04df:
			ldi      1
code_04e1:
			ret     
		)
	)
	
	(method (highlight param1)
		(= cel (if param1 2 else 0))
		(UpdateScreenItem self)
	)
)

(instance controlPanel of IconBar
	(properties
		state NOCLICKHELP
	)
	
	(method (init)
		(= plane controlPlane)
		(plane
			bitmap: 0
			picture: -2
			priority: (+ (GetHighPlanePri) 1)
			init:
			setBitmap: 933 14 0
			addCast: (= controlCast (Cast new:))
		)
		(self
			add:
				detailSlider
				volumeSlider
				speedSlider
				arcadeSlider
				iconSave
				iconRestore
				iconRestart
				iconQuit
				iconAbout
				iconHelp
				iconOk
				iconArcadeMode
				iconText
				iconAutoSave
				arcadeLight
				textLight
				autoSaveLight
				iconAudio
				audioLight
		)
		(if
		(or (not (User canInput:)) (not (User canInput:)))
			(= cantInput 1)
		else
			(= cantInput 0)
		)
		(self eachElementDo: #init self)
	)
	
	(method (doit &tmp temp0 temp1 temp2 temp3 temp4)
		(while
			(and
				(& state $0020)
				(= temp0 ((user curEvent?) new:))
			)
			(= temp1 (temp0 type?))
			(= temp2 (temp0 message?))
			(= temp3 (temp0 modifiers?))
			(= gameTime (+ tickOffset (GetTime)))
			(FrameOut)
			(if cuees (cuees eachElementDo: #doit))
			(if (== temp1 32)
				(= temp1 4)
				(= temp2 (if (& temp3 $0003) 27 else 13))
				(= temp3 0)
				(temp0 type: temp1 message: temp2 modifiers: temp3)
			)
			(temp0 localize: plane)
			(MapKeyToDir temp0)
			(breakif (self dispatchEvent: temp0))
		)
	)
	
	(method (dispose)
		(plane deleteCast: self dispose:)
		(= plane 0)
		(if elements
			(self eachElementDo: #dispose)
			(DisposeList elements)
		)
		(= size (= elements 0))
		(switch curIcon
			(iconAbout
				((ScriptID 19 0) doit:)
			)
			(iconSave
				(theGame panelObj: theGame)
				(theGame panelSelector: 86)
			)
			(iconRestore
				(theGame panelObj: theGame)
				(theGame panelSelector: 87)
			)
			(iconRestart
				(theGame panelObj: theGame)
				(theGame panelSelector: 111)
			)
			(iconQuit
				(theGame panelObj: theGame)
				(theGame panelSelector: 110)
			)
		)
		(= highlightedIcon (= curIcon 0))
		(DisposeClone self)
	)
	
	(method (show &tmp temp0 temp1 theNextNode temp3)
		(sounds pause:)
		(= state (| state $0020))
		(UpdatePlane plane)
		(= temp0 30)
		(= temp1 30)
		(= theNextNode (FirstNode elements))
		(while theNextNode
			(= nextNode (NextNode theNextNode))
			(if (not (= temp3 (NodeValue theNextNode))) (return))
			(if
				(and
					(not (& (temp3 signal?) $0080))
					(<= (temp3 nsRight?) 0)
				)
				(temp3 show: temp0 temp1)
				(= temp0 (+ 20 (temp3 nsRight?)))
			else
				(temp3 show:)
			)
			(= theNextNode nextNode)
		)
		(self doit: hide:)
	)
	
	(method (select theCurIcon)
		(super select: (= curIcon theCurIcon) &rest)
	)
	
	(method (highlight theHighlightedIcon param2)
		(if highlightedIcon (highlightedIcon highlight: 0))
		(cond 
			((not (= highlightedIcon theHighlightedIcon)) 0)
			((& (theHighlightedIcon signal?) $0004) (= highlightedIcon 0))
			(else
				(theHighlightedIcon highlight: 1)
				(if (and (>= argc 2) param2)
					(theGame
						setCursor:
							theCursor
							1
							(+
								(theHighlightedIcon nsLeft?)
								(/
									(-
										(theHighlightedIcon nsRight?)
										(theHighlightedIcon nsLeft?)
									)
									2
								)
							)
							(- (theHighlightedIcon nsBottom?) 3)
					)
				)
			)
		)
	)
	
	(method (dispatchEvent event &tmp temp0 eventClaimed temp2 temp3)
		(= eventClaimed (event claimed?))
		(= temp0 (self firstTrue: #onMe event))
		(self eachElementDo: #perform doCycleCode)
		(FrameOut)
		(if temp0
			(= temp2 (temp0 signal?))
			(= temp3 (== temp0 helpIconItem))
		)
		(switch (event type?)
			(nullEvt
				(if temp0
					(if (!= temp0 highlightedIcon)
						(= oldMouseY 0)
						(self highlight: temp0)
					)
				else
					(self highlight: 0)
				)
			)
			(mouseDown
				(if (and temp0 (self select: temp0 1))
					(= eventClaimed (& temp2 $0040))
				)
			)
			(keyDown
				(if (== (event message?) ESC) (return TRUE))
			)
		)
		(return eventClaimed)
	)
	
	(method (noClickHelp &tmp temp0 temp1 temp2 printDialog)
		(= temp1 (= temp2 (= printDialog 0)))
		(while
		(not ((= temp0 ((user curEvent?) new:)) type?))
			(temp0 localize: controlPlane)
			(cond 
				((= temp2 (self firstTrue: #onMe temp0))
					(if (and (!= temp2 temp1) (temp2 helpVerb?))
						(= temp1 temp2)
						(if printDialog
							(printDialog dispose:)
							(= printDialog 0)
							(FrameOut)
						)
						(Print
							font: userFont
							width: 250
							addText:
								(temp2 noun?)
								(temp2 helpVerb?)
								0
								1
								0
								0
								(if (== (temp2 modNum?) -1) 0 else (temp2 modNum?))
							modeless: 2
							init:
						)
						(= printDialog (Print dialog?))
						(FrameOut)
					)
				)
				(printDialog (printDialog dispose:) (= printDialog 0) (FrameOut))
				(else (= temp1 0))
			)
			(temp0 dispose:)
		)
		(theGame setCursor: normalCursor 1)
		(if printDialog
			(printDialog dispose:)
			(= printDialog 0)
			(FrameOut)
		)
		(if
		(and helpIconItem (not (helpIconItem onMe: temp0)))
			(self dispatchEvent: temp0)
		)
	)
)

(instance controlPlane of Plane
	(properties)
	
	(method (init)
		(AddPlane self)
		(= casts (Set new:))
		(= inTop (= top (/ (- 200 (CelHigh 933 14 0)) 2)))
		(= inLeft (= left (/ (- 320 (CelWide 933 14 0)) 2)))
		(= inBottom
			(= bottom
				(+
					(CelHigh 933 14 0)
					(/ (- 200 (CelHigh 933 14 0)) 2)
				)
			)
		)
		(= inRight
			(= right
				(+
					(CelWide 933 14 0)
					(/ (- 320 (CelWide 933 14 0)) 2)
				)
			)
		)
	)
)

(instance detailSlider of QGSlider
	(properties
		noun 15
		modNum 25
		nsLeft 82
		helpVerb 9
	)
	
	(method (init)
		(= theObj theGame)
		(= selector 340)
		(= y
			(- (+ nsTop (* (- 5 (Eval theObj selector)) 13)) 1)
		)
		(super init: &rest)
	)
	
	(method (select)
		(super select:)
		(Eval theObj selector (+ (/ (+ (- 84 y) 1) 13) 1))
		(volumeSlider setCycle: (turnLeft new:))
		(speedSlider setCycle: (turnLeft new:))
		(arcadeSlider setCycle: (turnLeft new:))
	)
)

(instance volumeSlider of QGSlider
	(properties
		noun 24
		modNum 25
		nsLeft 118
		helpVerb 9
	)
	
	(method (init)
		(= theObj theGame)
		(= y (- 84 (/ (* (theGame plane?) 34) 10)))
		(super init: &rest)
	)
	
	(method (select)
		(super select:)
		(theGame masterVolume: (/ (* (- 84 y) 10) 34))
		(detailSlider setCycle: (turnRight new:))
		(speedSlider setCycle: (turnLeft new:))
		(arcadeSlider setCycle: (turnLeft new:))
	)
	
	(method (move)
		(theGame masterVolume: (/ (* (- 84 y) 10) 34))
	)
)

(instance speedSlider of QGSlider
	(properties
		noun 23
		modNum 25
		nsLeft 154
		helpVerb 9
	)
	
	(method (init)
		(= theObj ego)
		(= selector 358)
		(= y (+ nsTop (/ (* (Eval theObj selector) 34) 10)))
		(if cantInput (= loop 11))
		(super init: &rest)
	)
	
	(method (onMe)
		(return (if cantInput (return 0) else (return (super onMe: &rest))))
	)
	
	(method (select)
		(super select:)
		(Eval theObj selector (/ (* (- y nsTop) 10) 34))
		(detailSlider setCycle: (turnRight new:))
		(volumeSlider setCycle: (turnRight new:))
		(arcadeSlider setCycle: (turnLeft new:))
	)
)

(instance arcadeSlider of QGSlider
	(properties
		noun 26
		modNum 25
		nsLeft 189
		helpVerb 9
	)
	
	(method (init)
		(= y (+ nsTop (* (- 3 arcadeLevel) 25)))
		(super init: &rest)
	)
	
	(method (select)
		(super select:)
		(= arcadeLevel (+ (/ (- 84 y) 25) 1))
		(detailSlider setCycle: (turnRight new:))
		(volumeSlider setCycle: (turnRight new:))
		(speedSlider setCycle: (turnRight new:))
	)
)

(instance iconSave of QGControlIcon
	(properties
		noun 22
		modNum 25
		nsLeft 8
		nsTop 11
		x 8
		y 11
		view 933
		loop 2
		signal $01c3
		message 9
		helpVerb 9
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(controlPanel state: (& (controlPanel state?) $ffdf))
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance iconRestore of QGControlIcon
	(properties
		noun 21
		modNum 25
		nsLeft 8
		nsTop 28
		x 8
		y 28
		view 933
		loop 3
		signal $01c3
		message 9
		helpVerb 9
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(controlPanel state: (& (controlPanel state?) $ffdf))
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance iconRestart of QGControlIcon
	(properties
		noun 20
		modNum 25
		nsLeft 8
		nsTop 45
		x 8
		y 45
		view 933
		loop 4
		signal $01c3
		message 9
		helpVerb 9
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(controlPanel state: (& (controlPanel state?) $ffdf))
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance iconQuit of QGControlIcon
	(properties
		noun 19
		modNum 25
		nsLeft 8
		nsTop 62
		x 8
		y 62
		view 933
		loop 5
		signal $01c3
		message 9
		helpVerb 9
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(controlPanel state: (& (controlPanel state?) $ffdf))
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance iconAbout of QGControlIcon
	(properties
		noun 16
		modNum 25
		nsLeft 8
		nsTop 79
		x 8
		y 79
		view 933
		loop 6
		signal $01c3
		message 9
		helpVerb 9
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(controlPanel state: (& (controlPanel state?) $ffdf))
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance iconHelp of QGControlIcon
	(properties
		noun 17
		modNum 25
		nsLeft 8
		nsTop 96
		x 8
		y 96
		view 933
		loop 7
		signal $0183
		message 9
		cursorView 949
		cursorLoop 0
		cursorCel 0
		helpVerb 9
	)
	
	(method (select)
		(if (super select: &rest)
			(theGame setCursor: (self getCursor:))
			(controlPanel noClickHelp:)
		)
		(return 0)
	)
)

(instance iconOk of QGControlIcon
	(properties
		noun 18
		modNum 25
		nsLeft 8
		nsTop 113
		x 8
		y 113
		view 933
		loop 8
		signal $01c3
		message 9
		helpVerb 9
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(controlPanel state: (& (controlPanel state?) $ffdf))
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance iconArcadeMode of QGControlIcon
	(properties
		noun 25
		modNum 25
		nsLeft 89
		nsTop 106
		x 89
		y 106
		view 933
		signal $0183
		message 9
		helpVerb 9
	)
	
	(method (init)
		(if (Btst 374)
			(self loop: 1)
			(= noun 4)
		else
			(self loop: 0)
			(= noun 25)
		)
		(super init: &rest)
		(= nsLeft (- nsLeft 10))
	)
	
	(method (select)
		(if (super select: &rest)
			(if (Btst 374)
				(self loop: 0)
				(= noun 25)
				(Bclr 374)
			else
				(self loop: 1)
				(= noun 4)
				(Bset 374)
			)
			(UpdateScreenItem iconArcadeMode)
		)
		(return 0)
	)
)

(instance arcadeLight of View
	(properties
		nsLeft 81
		nsTop 109
		x 81
		y 109
		view 933
		loop 15
	)
	
	(method (dispose)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance iconText of QGControlIcon
	(properties
		noun 29
		modNum 25
		nsLeft 89
		nsTop 119
		x 89
		y 119
		view 939
		loop 1
		signal $0183
		message 9
		helpVerb 9
	)
	
	(method (init)
		(super init: &rest)
		(= nsLeft (- nsLeft 10))
	)
	
	(method (select)
		(if (super select: &rest)
			(if
			(and (not (audioLight cel?)) (& msgType $0001))
				(= msgType (^ msgType $0001))
				(textLight cel: 1)
			else
				(= msgType (| msgType $0001))
				(textLight cel: 0)
			)
		)
		(UpdateScreenItem textLight)
		(return 0)
	)
)

(instance textLight of View
	(properties
		nsLeft 81
		nsTop 121
		x 81
		y 121
		view 933
		loop 15
	)
	
	(method (init)
		(if (& msgType $0001) (= cel 0) else (= cel 1))
		(super init: &rest)
	)
	
	(method (dispose)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance iconAutoSave of QGControlIcon
	(properties
		noun 27
		modNum 25
		nsLeft 153
		nsTop 108
		x 153
		y 108
		view 933
		loop 9
		signal $0183
		message 9
		helpVerb 9
	)
	
	(method (init)
		(super init: &rest)
		(= nsLeft (- nsLeft 10))
	)
	
	(method (select)
		(if (super select: &rest)
			(if (Btst 379)
				(Bclr 379)
				(autoSaveLight cel: 1)
			else
				(Bset 379)
				(autoSaveLight cel: 0)
			)
			(UpdateScreenItem autoSaveLight)
		)
		(return 0)
	)
)

(instance autoSaveLight of View
	(properties
		nsLeft 143
		nsTop 109
		x 143
		y 109
		view 933
		loop 15
	)
	
	(method (init)
		(if (Btst 379) (= cel 0) else (= cel 1))
		(super init: &rest)
	)
	
	(method (dispose)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance iconAudio of QGControlIcon
	(properties
		noun 28
		modNum 25
		nsLeft 153
		nsTop 117
		x 153
		y 117
		view 939
		signal $0183
		message 9
		helpVerb 9
	)
	
	(method (init)
		(super init: &rest)
		(= nsLeft (- nsLeft 10))
	)
	
	(method (select)
		(if (super select: &rest)
			(if (and (not (textLight cel?)) (Btst 400))
				(Bclr 400)
				(= msgType (^ msgType $0002))
				(audioLight cel: 1)
			else
				(Bset 400)
				(= msgType (| msgType $0002))
				(audioLight cel: 0)
			)
			(UpdateScreenItem audioLight)
		)
		(return 0)
	)
)

(instance audioLight of View
	(properties
		nsLeft 143
		nsTop 121
		x 143
		y 121
		view 933
		loop 15
	)
	
	(method (init)
		(if (Btst 400) (= cel 0) else (= cel 1))
		(super init: &rest)
	)
	
	(method (dispose)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance doCycleCode of Code
	(properties)
	
	(method (doit param1)
		(if
		(and (param1 isKindOf: QGSlider) (param1 cycler?))
			((param1 cycler?) doit:)
			(UpdateScreenItem param1)
		)
	)
)

(instance turnRight of CycleTo
	(properties)
	
	(method (init param1)
		(super init: param1 0 -1)
	)
	
	(method (cycleDone)
		(if (== (client cel?) 0)
			(super init: client 2 1)
		else
			(super cycleDone:)
		)
	)
)

(instance turnLeft of CycleTo
	(properties)
	
	(method (init param1)
		(super init: param1 (param1 lastCel:) 1)
	)
	
	(method (cycleDone)
		(if (== (client cel?) 4)
			(super init: client 2 -1)
		else
			(super cycleDone:)
		)
	)
)
