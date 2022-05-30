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
	controlCast
)
(class sqControlIcon of IconItem
	(properties
		theObj 0
	)
	
	(method (doit)
		(if theObj
			(theGame panelObj: theObj panelSelector: selector)
		)
	)
	
	(method (highlight tOrF)
		(if (or (not (& signal IB_ACTIVE)) (== highlightColor -1))
			(return)
		)
		(= cel (* 1 (if argc tOrF else 0)))
		(UpdateScreenItem self)
	)
)

(class SlideBar of sqControlIcon
	(properties
		signal FIXED_POSN
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
			init: controlCast
			posn: 140 y
			setCel: (/ value 10)
		)
		((= indicator2 (View new:))
			view: 950
			loop: 11
			init: controlCast
			posn: 148 y
			setCel: (mod value 10)
		)
		((= minusButton (buttonIcon new:))
			mainView: 950
			mainLoop: 9
			mainCel: 0
			theObj: self
			selector: #retreat
			posn: x y
			noun: noun
			helpVerb: helpVerb
			signal: (| RELVERIFY IMMEDIATE)
		)
		((= plusButton (buttonIcon new:))
			mainView: 950
			mainLoop: 10
			mainCel: 0
			theObj: self
			selector: #advance
			posn: (+ x 68) y
			noun: noun
			helpVerb: helpVerb
			signal: (| RELVERIFY IMMEDIATE)
		)
		(SQ6Controls add: minusButton plusButton)
	)
	
	(method (show)
		(|= signal IB_ACTIVE)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
		(if (not position)
			(self valueToPosn: value)
		)
		(self refresh:)
	)
	
	(method (select relVer &tmp event theTheMinX theMinX)
		(if (& signal DISABLED) (return FALSE))
		(return
			(if (and argc relVer)
				(= theTheMinX 500)
				(while (!= ((= event (Event new:)) type?) 2)
					(event localize: (SQ6Controls plane?))
					(if (!= (= theMinX (event x?)) theTheMinX)
						(if (< theMinX minX) (= theMinX minX))
						(if (> theMinX maxX) (= theMinX maxX))
						(= value (self posnToValue: theMinX))
						(self valueToPosn: value)
						(self doit:)
						(self refresh:)
						(FrameOut)
					)
					(= theTheMinX theMinX)
					(event dispose:)
				)
				(self refresh:)
				(FrameOut)
				(event dispose:)
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
			(+= value 1)
			(if (> value maxValue)
				(= value maxValue)
			)
			(self valueToPosn: value)
			(self refresh:)
		)
	)
	
	(method (retreat)
		(if (> value minValue)
			(-= value 1)
			(if (< value minValue)
				(= value minValue)
			)
			(self valueToPosn: value)
			(self refresh:)
		)
	)
	
	(method (valueToPosn val)
		(cond 
			((>= val maxValue)
				(= position maxX)
			)
			((<= val minValue)
				(= position minX)
			)
			(else
				(= position
					(+
						minX
						(/ (* (/ (* (- maxX minX) 10) 99) val) 10)
					)
				)
			)
		)
	)
	
	(method (posnToValue val &tmp theMinValue)
		(cond 
			((<= val minX) (= theMinValue minValue))
			((>= val maxX) (= theMinValue maxValue))
			(else (= theMinValue (/ (* (- val minX) 99) 53)))
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

(instance controlWind of Plane)

(class SQ6Controls of IconBar
	(method (init)
		(= gameControls self)
		((= controlCast (Cast new:)) add:)
		((= plane controlWind)
			picture: -3
			priority: (+ (GetHighPlanePri) 1)
			init: 0 0 0 0
			addCast: controlCast
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
			state: NOCLICKHELP
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
		(testBar init: controlCast)
		(testBar1 init: controlCast)
		(testBar2 init: controlCast)
		(testBar3 init: controlCast)
		(testBar4 init: controlCast)
	)
	
	(method (doit &tmp event eType eMsg eMod tut)
		(while
			(and
				(& state IB_ACTIVE)
				(= event ((user curEvent?) new:))
			)
			(= eType (event type?))
			(= eMsg (event message?))
			(= eMod (event modifiers?))
			(= gameTime (+ tickOffset (GetTime)))
			(FrameOut)
			(if (narrator initialized?)
				(narrator doit:)
				(narrator handleEvent: event)
			else
				(if
					(and
						(= tut (theGame script?))
						(tut isKindOf: Tutorial)
					)
					(tut doit:)
				)
				(if (== eType joyDown)
					(= eType keyDown)
					(= eMsg (if (& eMod (| RELVERIFY IMMEDIATE)) ESC else ENTER))
					(= eMod 0)
					(event type: eType message: eMsg modifiers: eMod)
				)
				(event localize: plane)
				(if
					(and
						(or (== eType mouseDown) (and (== eType keyDown) (== eMsg ENTER)))
						helpIconItem
						(& (helpIconItem signal?) TRANSLATOR)
					)
					(event type: (| userEvent helpEvent) message: (helpIconItem message?))
				)
				(MapKeyToDir event)
				(breakif (self dispatchEvent: event))
			)
		)
	)
	
	(method (handleEvent event &tmp keyInvoked eType newEvent newCursor oldCursor oldCurIcon oldInvIcon)
		(= eType (event type?))
		(cond 
			((& state DISABLED))
			(
				(and
					(not eType)
					(& state OPENIFONME)
					(self shouldOpen: event)
					(not (= keyInvoked 0))
				)
				(= oldCursor theCursor)
				(= oldCurIcon curIcon)
				(= oldInvIcon curInvIcon)
				(self show:)
				(self doit:)
				(= newCursor
					(if (or (user canControl:) (user canInput:))
						(self getCursor:)
					else
						waitCursor
					)
				)
				(theGame setCursor: newCursor TRUE)
				(self hide:)
			)
			((& eType mouseDown)
				(cond 
					((& (event modifiers?) shiftDown)
						(self advanceCurIcon: show: highlight: curIcon hide:)
						(event claimed: TRUE)
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
	(method (doit &tmp temp0)
		(Eval theObj #doit)
	)
	
	(method (select &tmp event temp1 temp2)
		(= temp2 15)
		(= scratch gameTime)
		(= temp1 1)
		(while (!= ((= event (Event new:)) type?) mouseUp)
			(event localize: plane)
			(if (self onMe: event)
				(if temp1 (Eval theObj selector) (FrameOut))
				(if (< (Abs (- gameTime scratch)) temp2)
					(= temp1 0)
				else
					(= temp1 1)
					(= scratch gameTime)
					(= temp2 1)
				)
			)
			(event dispose:)
			(= gameTime (+ tickOffset (GetTime)))
		)
		(event dispose:)
	)
)

(instance iconSave of sqControlIcon
	(properties
		noun N_SAVEGAME
		x 8
		y 6
		signal (| HIDEBAR VICON FIXED_POSN IMMEDIATE RELVERIFY)
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
		signal (| HIDEBAR VICON FIXED_POSN IMMEDIATE RELVERIFY)
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
		signal (| HIDEBAR VICON FIXED_POSN RELVERIFY)
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
		signal (| VICON FIXED_POSN IMMEDIATE RELVERIFY)
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
		signal (| VICON FIXED_POSN IMMEDIATE RELVERIFY)
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
				(&= msgType (~ CD_MSG))
				(= mainCel 0)
				(if (not (& msgType (= cel 1)))
					(= msgType TEXT_MSG)
					(iconText mainCel: 2 cel: 2)
					(UpdateScreenItem iconText)
				)
			else
				(|= msgType CD_MSG)
				(= mainCel 2)
				(= cel 3)
			)
			(UpdateScreenItem self)
		)
	)
	
	(method (highlight tOrF)
		(if (or (not (& signal IB_ACTIVE)) (== highlightColor -1))
			(return)
		)
		(cond 
			((and (& msgType CD_MSG) argc tOrF) (= cel 3))
			((& msgType CD_MSG) (= cel 2))
			((and argc tOrF) (= cel 1))
			(else (= cel 0))
		)
		(UpdateScreenItem self)
	)
)

(instance iconText of sqControlIcon
	(properties
		noun N_TEXT
		x 8
		y 66
		signal (| VICON FIXED_POSN IMMEDIATE RELVERIFY) ;$0183
		mainView 950
		mainLoop 5
		helpVerb V_HELP
	)
	
	(method (init)
		(if (& msgType TEXT_MSG)
			(= mainCel 2)
		)
		(super init: &rest)
	)
	
	(method (select)
		(if (super select: &rest)
			(if (& msgType TEXT_MSG)
				(&= msgType (~ TEXT_MSG))
				(= mainCel 0)
				(= cel 1)
				(if (not (& msgType CD_MSG))
					(= msgType CD_MSG)
					(iconSpeech mainCel: 2 cel: 2)
					(UpdateScreenItem iconSpeech)
				)
			else
				(|= msgType TEXT_MSG)
				(= mainCel 2)
				(= cel 3)
			)
			(UpdateScreenItem self)
		)
	)
	
	(method (highlight tOrF)
		(if (or (not (& signal IB_ACTIVE)) (== highlightColor -1))
			(return)
		)
		(cond 
			((and (& msgType TEXT_MSG) argc tOrF) (= cel 3))
			((& msgType TEXT_MSG) (= cel 2))
			((and argc tOrF) (= cel 1))
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
		signal (| HIDEBAR VICON FIXED_POSN IMMEDIATE RELVERIFY)
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
		noun N_QUITGAME
		x 8
		y 91
		signal (| HIDEBAR VICON FIXED_POSN IMMEDIATE RELVERIFY)
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
		noun N_OK
		x 8
		y 102
		signal (| HIDEBAR VICON FIXED_POSN IMMEDIATE RELVERIFY)
		message 0
		mainView 950
		mainLoop 8
		helpVerb V_HELP
	)
	
	(method (select &tmp temp0)
		(return (if (super select: &rest) (return TRUE) else (return FALSE)))
	)
)

(instance speedBar of SlideBar
	(properties
		noun N_GAMESPEED
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
					type: PContainedAccess
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
		noun N_MUSVOLUME
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
					type: PContainedAccess
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
		noun N_AUDVOLUME
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
					type: PContainedAccess
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
		oppose TRUE
	)
	
	(method (init)
		(= barObj testBar3)
		(= value (- maxValue (* inc textSpeed)))
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: PContainedAccess
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
		noun N_DETAIL
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
					type: PTotalAccess
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
	(method (init)
		(if (not plane)
			(= plane (systemPlane new:))
		)
		(dialog mouseHiliting: TRUE)
		(plane picture: -2)
		(super init: &rest)
	)
)

(instance theArrowCurs of Cursor
	(properties
		view ARROW_CURSOR
	)
)
