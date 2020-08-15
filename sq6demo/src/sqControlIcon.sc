;;; Sierra Script 1.0 - (do not remove this comment)
(script# 32)
(include game.sh) (include "0.shm")
(use Main)
(use Plane)
(use Print)
(use IconBar)
(use Polygon)
(use Cursor)
(use Tutorial)
(use Actor)
(use System)

(public
	SQ6Controls 0
)

(local
	newCast
)
(class sqControlIcon of IconItem
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
		sightAngle ftrDefault
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
		signal RELVERIFY
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
		type userEvent
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
	)
	
	(method (doit)
		(if theObj
			(theGame panelObj: theObj panelSelector: selector)
		)
	)
	
	(method (highlight param1)
		(if
		(or (not (& signal IB_ACTIVE)) (== highlightColor -1))
			(return)
		)
		(= cel (* 1 (if argc param1 else 0)))
		(UpdateScreenItem self)
	)
)

(class SlideBar of sqControlIcon
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
		sightAngle ftrDefault
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
		signal FIXED_POSN
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
		type userEvent
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
		maxValue 99
		minValue 0
		minX 0
		maxX 0
		inc 0
		value 0
		realValue 0
		position 0
		barObj 0
		oppose 0
		indicator1 0
		indicator2 0
		minusButton 0
		plusButton 0
	)
	
	(method (init)
		(= inc (/ 990 inc))
		(super init: &rest)
		((= indicator1 (View new:))
			view: 950
			loop: 11
			init: newCast
			posn: 140 y
			setCel: (/ value 10)
		)
		((= indicator2 (View new:))
			view: 950
			loop: 11
			init: newCast
			posn: 148 y
			setCel: (mod value 10)
		)
		((= minusButton (buttonIcon new:))
			mainView: 950
			mainLoop: 9
			mainCel: 0
			theObj: self
			selector: 412
			posn: x y
			noun: noun
			helpVerb: helpVerb
			signal: 3
		)
		((= plusButton (buttonIcon new:))
			mainView: 950
			mainLoop: 10
			mainCel: 0
			theObj: self
			selector: 411
			posn: (+ x 68) y
			noun: noun
			helpVerb: helpVerb
			signal: 3
		)
		(SQ6Controls add: minusButton plusButton)
	)
	
	(method (show)
		(= signal (| signal IB_ACTIVE))
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
		(if (not position) (self valueToPosn: value))
		(self refresh:)
	)
	
	(method (select param1 &tmp newEvent theTheMinX theMinX)
		(if (& signal DISABLED) (return FALSE))
		(return
			(if (and argc param1)
				(= theTheMinX 500)
				(while (!= ((= newEvent (Event new:)) type?) 2)
					(newEvent localize: (SQ6Controls plane?))
					(if (!= (= theMinX (newEvent x?)) theTheMinX)
						(if (< theMinX minX) (= theMinX minX))
						(if (> theMinX maxX) (= theMinX maxX))
						(= value (self posnToValue: theMinX))
						(self valueToPosn: value)
						(self doit:)
						(self refresh:)
						(FrameOut)
					)
					(= theTheMinX theMinX)
					(newEvent dispose:)
				)
				(self refresh:)
				(FrameOut)
				(newEvent dispose:)
			else
				(return TRUE)
			)
		)
	)
	
	(method (highlight)
	)
	
	(method (mask)
	)
	
	(method (advance)
		(if (< value maxValue)
			(= value (+ value 1))
			(if (> value maxValue) (= value maxValue))
			(self valueToPosn: value)
			(self refresh:)
		)
	)
	
	(method (retreat)
		(if (> value minValue)
			(= value (- value 1))
			(if (< value minValue) (= value minValue))
			(self valueToPosn: value)
			(self refresh:)
		)
	)
	
	(method (valueToPosn param1)
		(cond 
			((>= param1 maxValue) (= position maxX))
			((<= param1 minValue) (= position minX))
			(else
				(= position
					(+
						minX
						(/ (* (/ (* (- maxX minX) 10) 99) param1) 10)
					)
				)
			)
		)
	)
	
	(method (posnToValue param1 &tmp theMinValue)
		(cond 
			((<= param1 minX) (= theMinValue minValue))
			((>= param1 maxX) (= theMinValue maxValue))
			(else (= theMinValue (/ (* (- param1 minX) 99) 53)))
		)
		(return theMinValue)
	)
	
	(method (refresh)
		(UpdateScreenItem barObj)
		(indicator1 setCel: (/ value 10))
		(indicator2 setCel: (mod value 10))
		(barObj x: position)
		(UpdateScreenItem barObj)
		(UpdateScreenItem indicator1)
		(UpdateScreenItem indicator2)
		(self doit:)
	)
)

(instance controlWind of Plane
	(properties)
)

(class SQ6Controls of IconBar
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
		state OPENIFONME
		y 0
	)
	
	(method (init)
		(= gameControls self)
		((= newCast (Cast new:)) add:)
		((= plane controlWind)
			picture: -3
			priority: (+ (GetHighPlanePri) 1)
			init: 0 0 0 0
			addCast: newCast
		)
		(controlWind setBitmap: 950 13 0 0)
		(self
			add:
				(iconSave theObj: theGame selector: #save yourself:)
				(iconLoad theObj: theGame selector: #restore yourself:)
				(iconAbout theObj: theGame selector: #showAbout yourself:)
				iconHelp
				(iconSpeech theObj: iconSpeech selector: #doit yourself:)
				(iconText theObj: iconText selector: #doit yourself:)
				(iconRestart theObj: theGame selector: #restart yourself:)
				(iconQuit theObj: theGame selector: #quitGame yourself:)
				iconPlay
				detailBar
				speedBar
				soundBar
				musicBar
				textBar
			curIcon: iconLoad
			helpIconItem: iconHelp
			state: 2048
		)
		(super init:)
		(plane
			setRect:
				(plane left:)
				(plane top?)
				(+ (plane right:) 24)
				(+ (plane bottom?) 5)
		)
		(plane posn: -1 11)
		(UpdatePlane plane)
		(testBar init: newCast)
		(testBar1 init: newCast)
		(testBar2 init: newCast)
		(testBar3 init: newCast)
		(testBar4 init: newCast)
	)
	
	(method (doit &tmp temp0 temp1 temp2 temp3 theGameScript)
		(while
			(and
				(& state IB_ACTIVE)
				(= temp0 ((user curEvent?) new:))
			)
			(= temp1 (temp0 type?))
			(= temp2 (temp0 message?))
			(= temp3 (temp0 modifiers?))
			(= gameTime (+ tickOffset (GetTime)))
			(FrameOut)
			(if (narrator initialized?)
				(narrator doit:)
				(narrator handleEvent: temp0)
			else
				(if
					(and
						(= theGameScript (theGame script?))
						(theGameScript isKindOf: Tutorial)
					)
					(theGameScript doit:)
				)
				(if (== temp1 32)
					(= temp1 4)
					(= temp2 (if (& temp3 (| RELVERIFY IMMEDIATE)) 27 else 13))
					(= temp3 0)
					(temp0 type: temp1 message: temp2 modifiers: temp3)
				)
				(temp0 localize: plane)
				(if
					(and
						(or (== temp1 1) (and (== temp1 4) (== temp2 13)))
						helpIconItem
						(& (helpIconItem signal?) $0010)
					)
					(temp0 type: 24576 message: (helpIconItem message?))
				)
				(MapKeyToDir temp0)
				(breakif (self dispatchEvent: temp0))
			)
		)
	)
	
	(method (handleEvent event &tmp temp0 eventType temp2 temp3 theTheCursor theCurIcon theCurInvIcon)
		(= eventType (event type?))
		(cond 
			((& state DISABLED))
			(
				(and
					(not eventType)
					(& state OPENIFONME)
					(self shouldOpen: event)
					(not (= temp0 0))
				)
				(= theTheCursor theCursor)
				(= theCurIcon curIcon)
				(= theCurInvIcon curInvIcon)
				(self show:)
				(self doit:)
				(= temp3
					(if (or (user canControl:) (user canInput:))
						(self getCursor:)
					else
						waitCursor
					)
				)
				(theGame setCursor: temp3 1)
				(self hide:)
			)
			((& eventType mouseDown)
				(cond 
					((& (event modifiers?) shiftDown)
						(self advanceCurIcon: show: highlight: curIcon hide:)
						(event claimed: 1)
					)
					((& (event modifiers?) ctrlDown)
						(if (user canControl:)
							(self swapCurIcon: show: highlight: curIcon hide:)
						)
						(event claimed: TRUE)
					)
					(curIcon
						(event
							type: (curIcon type?)
							message:
								(if (== curIcon useIconItem)
									(curInvIcon message?)
								else
									(curIcon message?)
								)
						)
					)
				)
			)
		)
	)
	
	(method (showSelf)
		(sounds pause:)
		(= curIcon iconPlay)
		(self show: doit: hide:)
	)
)

(instance buttonIcon of sqControlIcon
	(properties)
	
	(method (doit &tmp temp0)
		(Eval theObj 69)
	)
	
	(method (select &tmp newEvent temp1 temp2)
		(= temp2 15)
		(= scratch gameTime)
		(= temp1 1)
		(while (!= ((= newEvent (Event new:)) type?) mouseUp)
			(newEvent localize: plane)
			(if (self onMe: newEvent)
				(if temp1 (Eval theObj selector) (FrameOut))
				(if (< (Abs (- gameTime scratch)) temp2)
					(= temp1 0)
				else
					(= temp1 1)
					(= scratch gameTime)
					(= temp2 1)
				)
			)
			(newEvent dispose:)
			(= gameTime (+ tickOffset (GetTime)))
		)
		(newEvent dispose:)
	)
)

(instance iconSave of sqControlIcon
	(properties
		noun N_SAVEGAME
		x 8
		y 6
		signal $01c3
		message 0
		mainView 950
		helpVerb V_HELP
	)
	
	(method (select)
		;EO: This demo does not contain the needed view (64990) for the save/restore dialog.
		;As a result, trying to bring it up crashes the game.
		(Prints
			{Do you really need to SAVE in this demo?\nI think not!}
		)
		(return FALSE)
	)
)

(instance iconLoad of sqControlIcon
	(properties
		noun N_RESTOREGAME
		x 8
		y 17
		signal $01c3
		message 0
		mainView 950
		mainLoop 1
		helpVerb V_HELP
	)
	
	(method (select)
		(Prints
			{If you don't need to save in the demo then\nyou certainly don't need to RESTORE!}
		)
		(return FALSE)
	)
)

(instance iconAbout of sqControlIcon
	(properties
		noun N_ABOUT
		x 8
		y 31
		signal $01c1
		message 0
		mainView 950
		mainLoop 2
		helpVerb V_HELP
	)
)

(instance iconHelp of sqControlIcon
	(properties
		noun N_CONTROLHELP
		x 8
		y 42
		signal $0183
		message V_HELP
		mainView 950
		mainLoop 3
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursorView cHelpControl)
		(super init: &rest)
	)
)

(instance iconSpeech of sqControlIcon
	(properties
		noun N_VOICE
		x 8
		y 55
		signal $0183
		message 0
		mainView 950
		mainLoop 4
		helpVerb V_HELP
	)
	
	(method (init)
		(if (& msgType CD_MSG) (= mainCel 2))
		(super init: &rest)
	)
	
	(method (select)
		(if (super select: &rest)
			(if (& msgType CD_MSG)
				(= msgType (& msgType $fffd))
				(= mainCel 0)
				(if (not (& msgType (= cel 1)))
					(= msgType TEXT_MSG)
					(iconText mainCel: 2 cel: 2)
					(UpdateScreenItem iconText)
				)
			else
				(= msgType (| msgType CD_MSG))
				(= mainCel 2)
				(= cel 3)
			)
			(UpdateScreenItem self)
		)
	)
	
	(method (highlight param1)
		(if
		(or (not (& signal IB_ACTIVE)) (== highlightColor -1))
			(return)
		)
		(cond 
			((and (& msgType CD_MSG) argc param1) (= cel 3))
			((& msgType CD_MSG) (= cel 2))
			((and argc param1) (= cel 1))
			(else (= cel 0))
		)
		(UpdateScreenItem self)
	)
)

(instance iconText of sqControlIcon
	(properties
		noun 9
		x 8
		y 66
		signal (| VICON FIXED_POSN IMMEDIATE RELVERIFY) ;$0183
		mainView 950
		mainLoop 5
		helpVerb V_HELP
	)
	
	(method (init)
		(if (& msgType TEXT_MSG) (= mainCel 2))
		(super init: &rest)
	)
	
	(method (select)
		(if (super select: &rest)
			(if (& msgType TEXT_MSG)
				(= msgType (& msgType $fffe))
				(= mainCel 0)
				(= cel 1)
				(if (not (& msgType CD_MSG))
					(= msgType CD_MSG)
					(iconSpeech mainCel: 2 cel: 2)
					(UpdateScreenItem iconSpeech)
				)
			else
				(= msgType (| msgType TEXT_MSG))
				(= mainCel 2)
				(= cel 3)
			)
			(UpdateScreenItem self)
		)
	)
	
	(method (highlight param1)
		(if
		(or (not (& signal IB_ACTIVE)) (== highlightColor -1))
			(return)
		)
		(cond 
			((and (& msgType TEXT_MSG) argc param1) (= cel 3))
			((& msgType TEXT_MSG) (= cel 2))
			((and argc param1) (= cel 1))
			(else (= cel 0))
		)
		(UpdateScreenItem self)
	)
)

(instance iconRestart of sqControlIcon
	(properties
		noun N_RESTARTGAME
		x 8
		y 80
		signal $01c3
		message 0
		mainView 950
		mainLoop 6
		helpVerb V_HELP
	)
	
	(method (select)
		(Prints
			{Just quit and start over!\nDo you need a RESTART button in this demo?}
		)
		(return FALSE)
	)
)

(instance iconQuit of sqControlIcon
	(properties
		noun 11
		x 8
		y 91
		signal $01c3
		message 0
		mainView 950
		mainLoop 7
		helpVerb V_HELP
	)
	
	(method (select)
		(= quit TRUE)
	)
)

(instance iconPlay of sqControlIcon
	(properties
		noun 12
		x 8
		y 102
		signal $01c3
		message 0
		mainView 950
		mainLoop 8
		helpVerb V_HELP
	)
	
	(method (select &tmp temp0)
		(return (if (super select: &rest) (return 1) else (return 0)))
	)
)

(instance speedBar of SlideBar
	(properties
		noun 13
		x 57
		y 14
		mainView 950
		mainLoop 14
		helpVerb V_HELP
		minX 68
		maxX 121
		inc 11
		value 6
	)
	
	(method (init)
		(= scratch -10)
		(= barObj testBar)
		(super init: &rest)
		(= value (- maxValue (/ (* inc egoSpeed) 10)))
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 68 15 124 15 124 22 68 22
					yourself:
				)
		)
	)
	
	(method (doit)
		(if
			(==
				(= egoSpeed (/ (* (- maxValue value) 10) inc))
				11
			)
			(= value 0)
		)
		(ego setSpeed: (= scratch egoSpeed))
	)
	
	(method (show)
		(if (!= scratch egoSpeed)
			(= value (- maxValue (/ (* inc egoSpeed) 10)))
			(if (== egoSpeed 11) (= value 0))
		)
		(self valueToPosn: value)
		(self refresh:)
	)
)

(instance musicBar of SlideBar
	(properties
		noun 16
		x 57
		y 79
		mainView 950
		mainLoop 14
		mainCel 1
		helpVerb V_HELP
		minX 68
		maxX 121
		inc 2
	)
	
	(method (init)
		(= barObj testBar1)
		(= value
			(/ (* maxValue (theGame masterMusicVolume:)) 15)
		)
		(= scratch value)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 68 80 124 80 124 87 68 87
					yourself:
				)
		)
	)
	
	(method (doit &tmp [temp0 2])
		(if (!= value scratch)
			(theGame masterMusicVolume: (/ (* value 15) maxValue))
			(= scratch value)
		)
	)
)

(instance soundBar of SlideBar
	(properties
		noun 15
		x 57
		y 58
		mainView 950
		mainLoop 14
		helpVerb V_HELP
		minX 68
		maxX 121
		inc 2
	)
	
	(method (init)
		(= barObj testBar2)
		(= value (/ (* maxValue (DoAudio 8)) 127))
		(= scratch value)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 68 59 124 59 124 66 68 66
					yourself:
				)
		)
	)
	
	(method (doit &tmp [temp0 2])
		(if (!= value scratch)
			(DoAudio 8 (/ (* value 127) maxValue))
			(= scratch value)
		)
	)
)

(instance textBar of SlideBar
	(properties
		noun 17
		x 57
		y 101
		mainView 950
		mainLoop 14
		mainCel 1
		helpVerb V_HELP
		minX 68
		maxX 121
		inc 8
		oppose 1
	)
	
	(method (init)
		(= barObj testBar3)
		(= value (- maxValue (* inc textSpeed)))
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 68 102 124 102 124 109 68 109
					yourself:
				)
		)
	)
	
	(method (doit &tmp [temp0 2])
		(= textSpeed (/ (* (- maxValue value) 10) inc))
	)
	
	(method (show)
		(self valueToPosn: value)
		(self refresh:)
	)
)

(instance detailBar of SlideBar
	(properties
		noun 14
		x 57
		y 36
		mainView 950
		mainLoop 14
		helpVerb V_HELP
		minX 68
		maxX 121
		inc 3
	)
	
	(method (init)
		(= barObj testBar4)
		(super init: &rest)
		(= inc 330)
		(= value (* (theGame detailLevel:) 33))
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 68 37 124 37 124 44 68 44
					yourself:
				)
		)
	)
	
	(method (doit)
		(theGame detailLevel: (/ (* value 10) inc))
	)
	
	(method (show)
		(super show:)
	)
)

(instance testBar of View
	(properties
		x 68
		y 15
		view 950
		loop 12
	)
)

(instance testBar1 of View
	(properties
		x 68
		y 81
		view 950
		loop 12
		cel 1
	)
)

(instance testBar2 of View
	(properties
		x 68
		y 59
		view 950
		loop 12
	)
)

(instance testBar3 of View
	(properties
		x 68
		y 103
		view 950
		loop 12
		cel 1
	)
)

(instance testBar4 of View
	(properties
		x 68
		y 37
		view 950
		loop 12
	)
)

(instance backGround of View
	(properties
		view 950
		loop 13
	)
)

(instance cHelpControl of Cursor
	(properties
		view 953
		loop 8
	)
)

(instance myP of Print
	(properties)
	
	(method (init)
		(if (not plane) (= plane (systemPlane new:)))
		(dialog mouseHiliting: 1)
		(plane picture: -2)
		(super init: &rest)
	)
)

(instance theArrowCurs of Cursor
	(properties
		view ARROW_CURSOR
	)
)
