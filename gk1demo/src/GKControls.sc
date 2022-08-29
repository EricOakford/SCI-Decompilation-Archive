;;; Sierra Script 1.0 - (do not remove this comment)
(script# GK_CONTROLS)
(include game.sh) (include "0.shm")
(use Main)
(use GKIconItem)
(use Procs)
(use Print)
(use IconBar)
(use GControl)
(use Window)
(use Game)
(use System)

(public
	GKControls 0
)

(class GKControlIcon of GKIconItem
	(properties
		theObj 0
		selector 0
	)
	
	(method (doit)
		(if theObj
			(if (& signal HIDEBAR)
				((if gameControls else GameControls) hide:)
			)
			(theGame panelObj: theObj panelSelector: selector)
		)
	)
)

(class SlideBar of GKIconItem
	(properties
		barView 0
		highLoop 0
		highCel 0
		lowLoop 0
		lowCel 0
		maxValue 0
		minValue 0
		barY 0
		minX 0
		maxX 0
		resolution 0
		inc 0
		value 0
		position 0
		underBits 0
		theObj 0
		selector 0
	)
	
	(method (show)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
		(= resolution (+ (/ (- maxX minX) inc) 1))
		(= position (self valueToPosn:))
		(self refresh: position)
	)
	
	(method (select relVer &tmp event theTheMinX theMinX)
		(if (& signal DISABLED) (return FALSE))
		(return
			(if (and argc relVer)
				(= theTheMinX 500)
				(while (!= ((= event (Event new:)) type?) 2)
					(event localize:)
					(if (!= (= theMinX (event x?)) theTheMinX)
						(if (< theMinX minX) (= theMinX minX))
						(= position (- theMinX (mod theMinX inc)))
						(self refresh:)
					)
					(= theTheMinX theMinX)
					(event dispose:)
				)
				(= value (self posnToValue: position))
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
		(if (< position maxX)
			(+= position inc)
			(self refresh:)
			(= value (self posnToValue: position))
		)
	)
	
	(method (retreat)
		(if (> position minX)
			(-= position inc)
			(self refresh:)
			(= value (self posnToValue: position))
		)
	)
	
	(method (valueToPosn)
		(= position
			(+
				minX
				(* inc (/ (* value resolution) (- maxValue minValue)))
			)
		)
	)
	
	(method (posnToValue param1 &tmp theMinValue)
		(cond 
			(
				(<
					(= theMinValue
						(/
							(* (- maxValue minValue) (- param1 minX))
							(- maxX minX)
						)
					)
					minValue
				)
				(= theMinValue minValue)
			)
			((> theMinValue maxValue) (= theMinValue maxValue))
		)
		(return theMinValue)
	)
	
	(method (refresh &tmp theMinX theHighLoop theHighCel)
		(= theHighLoop highLoop)
		(= theHighCel highCel)
		(= theMinX minX)
		(while (<= theMinX maxX)
			(if (and (== theMinX position) (!= theMinX maxX))
				(= theHighLoop lowLoop)
				(= theHighCel lowCel)
			)
			(DrawCel barView theHighLoop theHighCel theMinX barY 15)
			(= theMinX (+ theMinX inc))
		)
	)
)

(class GKControls of GameControls
	
	(method (init)
		(Load RES_VIEW 953)
		(= gameControls self)
		(= window controlWind)
		(self
			add:
				(iconExit init: yourself:)
				(iconBarUp init: theObj: theGame selector: #keepIconBar yourself:)
				(iconText init: theObj: iconText selector: #doit yourself:)
				(iconAbout init: theObj: theGame selector: #showAbout yourself:)
				(iconHelp init: yourself:)
				(iconQuit init: theObj: theGame selector: #quitGame yourself:)
				(iconRestart
					init:
					theObj: theGame
					selector: #restart
					yourself:
				)
				(iconRestore init: theObj: theGame selector: #restore yourself:)
				(iconSave init: theObj: theGame selector: #save yourself:)
				(detailBar init: theObj: theGame selector: #detailLevel yourself:)
				(speedBar init: theObj: 0 selector: 0 yourself:)
				(soundBar init: theObj: 0 selector: 0 yourself:)
				(musicBar init: theObj: 0 selector: 0 yourself:)
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor 2
			curIcon: iconRestore
			helpIconItem: iconHelp
			state: 2048
		)
		(if (Btst fSpeedDisabled)
			(self disable: speedBar)
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript GK_CONTROLS)
	)
	
	(method (show &tmp theX theY node nextNode obj temp5 temp6 temp7 temp8 temp9)
		(sounds pause:)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
		(|= state IB_ACTIVE)
		(if (IsObject window)
			(window open:)
		else
			(= window
				((systemWindow new:)
					top: 40
					left: 84
					bottom: 130
					right: 234
					priority: 15
					open:
					yourself:
				)
			)
		)
		(PicNotValid TRUE)
		(DrawCel 953 10 0 0 0 15)
		(= theX 30)
		(= theY 30)
		(= node (FirstNode elements))
		(while node
			(= nextNode (NextNode node))
			(if (not (IsObject (= obj (NodeValue node))))
				(return)
			)
			(if
				(and
					(not (& (obj signal?) FIXED_POSN))
					(<= (obj nsRight?) 0)
				)
				(obj show: theX theY)
				(= theX (+ 20 (obj nsRight?)))
			else
				(obj show:)
			)
			(= node nextNode)
		)
		(if (not okButton)
			(= okButton (NodeValue (self first:)))
		)
		(PicNotValid FALSE)
		(= temp5
			(= temp6
				(= temp7
					(= temp8
						(= temp9 0)
					)
				)
			)
		)
		(while (< temp5 77)
			(= temp6 (- 76 temp5))
			(= temp7 (+ 76 temp5))
			(if (< temp5 60)
				(= temp8 (- 48 temp5))
				(= temp9 (+ 48 temp5))
			)
			(Graph GShowBits temp8 temp6 temp9 temp7 VMAP)
			(= temp5 (+ temp5 4))
			(Wait 1)
		)
		(if (and (not (HaveMouse)) curIcon)
			(theGame
				setCursor: theCursor TRUE
					(+
						(curIcon nsLeft?)
						(/ (- (curIcon nsRight?) (curIcon nsLeft?)) 2)
					)
					(- (curIcon nsBottom?) 3)
			)
		)
		(self doit: hide:)
	)
	
	(method (advance &tmp theIcon)
		(= theIcon (highlightedIcon rightIcon?))
		(if (not (IsObject theIcon))
			(= theIcon (NodeValue (self first:)))
		else
			(while (& (theIcon signal?) DISABLED)
				(= theIcon
					(theIcon rightIcon?)
				)
			)
		)
		(self
			highlight: theIcon (& state IB_ACTIVE)
		)
	)
	
	(method (retreat &tmp theIcon)
		(= theIcon (highlightedIcon leftIcon?))
		(if (not (IsObject theIcon))
			(= theIcon (NodeValue (self first:)))
		else
			(while (& (theIcon signal?) DISABLED)
				(= theIcon
					(theIcon leftIcon?)
				)
			)
		)
		(self highlight: theIcon (& state IB_ACTIVE))
	)
	
	(method (highlight thisIcon tOrF &tmp temp0)
		(if (not (& (thisIcon signal?) DISABLED))
			(if (IsObject highlightedIcon)
				(highlightedIcon highlight: FALSE)
			)
			((= highlightedIcon thisIcon) highlight: TRUE)
		)
		(if (and (>= argc 2) tOrF)
			(theGame
				setCursor:
					theCursor
					1
					(if (thisIcon isKindOf: SlideBar)
						(+ (thisIcon nsLeft?) 6)
					else
						(+
							(thisIcon nsLeft?)
							(/
								(-
									(thisIcon nsRight?)
									(thisIcon nsLeft?)
								)
								2
							)
						)
					)
					(- (thisIcon nsBottom?) 2)
			)
		)
	)
	
	(method (dispatchEvent event &tmp eO thisIcon thePort [buffer 50] eType eMsg evt)
		(= eType (event type?))
		(= eMsg (event message?))
		(return
			(cond 
				((== eType helpEvent)
					(if
						(and
							(= thisIcon (self firstTrue: #onMe event))
							(thisIcon helpVerb?)
						)
						(= thePort (GetPort))
						(if (systemWindow respondsTo: #eraseOnly)
							(= eO (systemWindow eraseOnly?))
							(systemWindow eraseOnly: TRUE)
							(Print
								font: userFont
								width: 250
								addText: (thisIcon noun?) (thisIcon helpVerb?) NULL 1 0 0 (thisIcon modNum?)
								init:
							)
							(systemWindow eraseOnly: eO)
						else
							(Print
								font: userFont
								width: 250
								addText: (thisIcon noun?) (thisIcon helpVerb?) NULL 1 0 0 (thisIcon modNum?)
								init:
							)
						)
						(SetPort thePort)
					)
					(if helpIconItem
						(helpIconItem signal: (& (helpIconItem signal?) (~ TRANSLATOR)))
					)
					(theGame setCursor: ARROW_CURSOR)
					(return FALSE)
				)
				((& eType direction)
					(switch eMsg
						(dirE
							(cond 
								(
									(and
										(IsObject highlightedIcon)
										(highlightedIcon respondsTo: #retreat)
									)
									(while (!= ((= evt (Event new:)) type?) keyUp)
										(evt localize:)
										(highlightedIcon advance:)
										(evt dispose:)
									)
									(evt dispose:)
									(return FALSE)
								)
								(
									(or
										(not (IsObject highlightedIcon))
										(& (highlightedIcon signal?) joyDown)
									)
									(if (IsObject highlightedIcon)
										(self advance:)
									else
										(self highlight: iconQuit (& state IB_ACTIVE))
									)
									(return 0)
								)
							)
						)
						(dirW
							(cond 
								(
									(and
										(IsObject highlightedIcon)
										(highlightedIcon respondsTo: #advance)
									)
									(while (!= ((= evt (Event new:)) type?) keyUp)
										(evt localize:)
										(highlightedIcon retreat:)
										(evt dispose:)
									)
									(evt dispose:)
									(return FALSE)
								)
								(
									(or
										(not (IsObject highlightedIcon))
										(& (highlightedIcon signal?) joyDown)
									)
									(if (IsObject highlightedIcon)
										(self retreat:)
									else
										(self highlight: iconQuit (& state IB_ACTIVE))
									)
									(return 0)
								)
							)
						)
						(dirN
							(if (IsObject highlightedIcon)
								(self goUp:)
							else
								(self highlight: iconQuit (& state IB_ACTIVE))
							)
							(return FALSE)
						)
						(dirS
							(if (IsObject highlightedIcon)
								(self goDown:)
							else
								(self highlight: iconQuit (& state IB_ACTIVE))
							)
							(return FALSE)
						)
						(else 
							(super dispatchEvent: event)
						)
					)
				)
				(else (super dispatchEvent: event))
			)
		)
	)
	
	(method (noClickHelp &tmp event lastIcon thisIcon oldPort eO oldCur)
		(= lastIcon (= thisIcon 0))
		(= oldPort (GetPort))
		(= eO (systemWindow eraseOnly?))
		(systemWindow eraseOnly: TRUE)
		(while (not ((= event ((user curEvent?) new:)) type?))
			(if (not (self isMemberOf: IconBar)) (event localize:))
			(cond 
				((= thisIcon (self firstTrue: #onMe event))
					(if (and (!= thisIcon lastIcon) (thisIcon helpVerb?))
						(= lastIcon thisIcon)
						(if modelessDialog
							(modelessDialog dispose:)
						)
						(Print
							font: userFont
							width: 300
							addText: (thisIcon noun?) (thisIcon helpVerb?) NULL 1 0 0 (thisIcon modNum?)
							modeless: TRUE
							y: 147
							init:
						)
						(SetPort oldPort)
					)
				)
				(modelessDialog
					(modelessDialog dispose:)
					(SetPort oldPort)
				)
				(else
					(= lastIcon 0)
					(SetPort oldPort)
				)
			)
			(event dispose:)
		)
		(systemWindow eraseOnly: eO)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(SetPort oldPort)
		(if (not (helpIconItem onMe: event))
			(self dispatchEvent: event)
		)
	)
	
	(method (goUp &tmp theIcon temp1)
		(= theIcon (highlightedIcon topIcon:))
		(if (not (IsObject theIcon))
			(= theIcon (NodeValue (self first:)))
		else
			(while (& (theIcon signal?) DISABLED)
				(= theIcon
					(theIcon topIcon:)
				)
			)
		)
		(self highlight: theIcon (& state IB_ACTIVE))
	)
	
	(method (goDown &tmp theIcon)
		(= theIcon
			(highlightedIcon bottomIcon?)
		)
		(if (not (IsObject theIcon))
			(= theIcon (NodeValue (self first:)))
		else
			(while (& (theIcon signal?) DISABLED)
				(= theIcon
					(theIcon bottomIcon?)
				)
			)
		)
		(self
			highlight: theIcon (& state IB_ACTIVE)
		)
	)
)

(instance controlWind of SysWindow
	(properties
		top 40
		left 84
		bottom 130
		right 234
		type wCustom
		lsTop 40
		lsLeft 84
		lsBottom 130
		lsRight 234
	)
)

(instance iconBarUp of GKControlIcon
	(properties
		view 953
		loop 1
		cel 0
		nsLeft 91
		nsTop 67
		message NULL
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_ICONBAR_UP
		helpVerb V_HELP
	)
	
	(method (init)
		(= topIcon musicBar)
		(= leftIcon iconHelp)
		(= bottomIcon iconRestore)
		(= rightIcon iconQuit)
		(super init: &rest)
	)
	
	(method (doit)
		(theGame keepBar: (not (theGame keepBar?)))
		(= loop (if (theGame keepBar?) 2 else 1))
		(super doit: &rest)
	)
	
	(method (show)
		(= loop (if (theGame keepBar?) 2 else 1))
		(super show: &rest)
	)
)

(instance iconText of GKControlIcon
	(properties
		view 953
		loop 3
		cel 0
		nsLeft 28
		nsTop 67
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_VOICE
		helpVerb V_HELP
	)
	
	(method (init)
		(= topIcon musicBar)
		(= leftIcon iconExit)
		(= bottomIcon detailBar)
		(= rightIcon iconAbout)
		(super init: &rest)
	)
	
	(method (doit &tmp savePort)
		(if (FileIO fileExists {GKCD})
			(switch msgType
				(TEXT_MSG
					(= msgType CD_MSG)
				)
				(CD_MSG
					(= msgType TEXT_MSG)
				)
			)
			(self show:)
		else
			(= savePort (GetPort))
			(Print
				font: userFont
				addText: {*** You not playing a cd!}
				init:
			)
			(SetPort savePort)
		)
	)
	
	(method (show)
		(= loop (if (== msgType TEXT_MSG) 4 else 3))
		(super show: &rest)
	)
)

(instance iconAbout of GKControlIcon
	(properties
		view 953
		loop 7
		cel 0
		nsLeft 49
		nsTop 67
		message NULL
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY)
		noun N_ABOUT
		helpVerb V_HELP
	)
	
	(method (init)
		(= topIcon musicBar)
		(= leftIcon iconText)
		(= bottomIcon detailBar)
		(= rightIcon iconHelp)
		(super init: &rest)
	)
)

(instance iconHelp of GKControlIcon
	(properties
		view 953
		loop 12
		cel 0
		nsLeft 70
		nsTop 67
		message V_HELP
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_CONTROL_HELP
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursor cHelpControl)
		(= topIcon musicBar)
		(= leftIcon iconAbout)
		(= bottomIcon iconRestore)
		(= rightIcon iconBarUp)
		(super init: &rest)
	)
)

(instance iconExit of GKIconItem
	(properties
		view 953
		loop 5
		cel 0
		nsLeft 7
		nsTop 67
		message NULL
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_OK
		helpVerb V_HELP
	)
	
	(method (init)
		(= topIcon musicBar)
		(= leftIcon iconQuit)
		(= bottomIcon detailBar)
		(= rightIcon iconText)
		(super init: &rest)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance iconQuit of GKControlIcon
	(properties
		view 953
		loop 9
		cel 0
		nsLeft 120
		nsTop 67
		message 0
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_QUIT
		helpVerb V_HELP
	)
	
	(method (init)
		(= topIcon iconRestart)
		(= leftIcon iconBarUp)
		(= bottomIcon iconRestore)
		(= rightIcon iconExit)
		(super init: &rest)
	)
)

(instance iconRestart of GKControlIcon
	(properties
		view 953
		loop 8
		cel 0
		nsLeft 120
		nsTop 51
		message 0
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_RESTART
		helpVerb V_HELP
	)
	
	(method (init)
		(= topIcon iconSave)
		(= leftIcon musicBar)
		(= bottomIcon iconQuit)
		(= rightIcon musicBar)
		(super init: &rest)
	)
)

(instance iconRestore of GKControlIcon
	(properties
		view 953
		loop 13
		cel 0
		nsLeft 120
		nsTop 7
		message NULL
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_RESTORE
		helpVerb V_HELP
	)
	
	(method (init)
		(= topIcon iconQuit)
		(= leftIcon detailBar)
		(= bottomIcon iconSave)
		(= rightIcon detailBar)
		(super init: &rest)
	)
)

(instance iconSave of GKControlIcon
	(properties
		view 953
		loop 6
		cel 0
		nsLeft 120
		nsTop 23
		message NULL
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_SAVE
		helpVerb V_HELP
	)
	
	(method (init)
		(= topIcon iconRestore)
		(= leftIcon speedBar)
		(= bottomIcon iconRestart)
		(= rightIcon speedBar)
		(super init: &rest)
	)
)

(instance cHelpControl of Cursor
	(properties
		view 958
		loop 8
	)
)

(instance detailBar of SlideBar
	(properties
		nsLeft 6
		nsTop 6
		nsRight 112
		nsBottom 17
		noun N_DETAIL
		helpVerb V_HELP
		barView 953
		highLoop 11
		lowLoop 11
		lowCel 1
		maxValue 3
		barY 10
		minX 36
		maxX 106
		inc 2
	)
	
	(method (init)
		(= topIcon iconExit)
		(= bottomIcon speedBar)
		(super init: &rest)
	)
	
	(method (show)
		(= value (theGame detailLevel:))
		(super show:)
	)
)

(instance speedBar of SlideBar
	(properties
		nsLeft 6
		nsTop 21
		nsRight 112
		nsBottom 32
		noun N_SPEED
		helpVerb V_HELP
		barView 953
		highLoop 11
		lowLoop 11
		lowCel 1
		maxValue 12
		barY 25
		minX 36
		maxX 106
		inc 2
	)
	
	(method (init)
		(= topIcon detailBar)
		(= bottomIcon soundBar)
		(super init: &rest)
	)
	
	(method (doit)
		(theGame currentSpeed: (- maxValue value))
		(ego setSpeed: (theGame currentSpeed?))
	)
	
	(method (show)
		(= value (- maxValue (ego moveSpeed?)))
		(super show:)
	)
)

(instance soundBar of SlideBar
	(properties
		nsLeft 6
		nsTop 36
		nsRight 112
		nsBottom 47
		noun N_SOUND
		helpVerb V_HELP
		barView 953
		highLoop 11
		lowLoop 11
		lowCel 1
		maxValue 127
		barY 40
		minX 36
		maxX 106
		inc 2
	)
	
	(method (init)
		(= topIcon speedBar)
		(= bottomIcon musicBar)
		(super init: &rest)
	)
	
	(method (doit)
		(GKSound soundVolume: value)
	)
	
	(method (show)
		(= value (GKSound soundVolume?))
		(super show:)
	)
)

(instance musicBar of SlideBar
	(properties
		nsLeft 6
		nsTop 51
		nsRight 112
		nsBottom 62
		noun N_MUSIC
		helpVerb V_HELP
		barView 953
		highLoop 11
		lowLoop 11
		lowCel 1
		maxValue 80
		barY 55
		minX 36
		maxX 106
		inc 2
	)
	
	(method (init)
		(= topIcon soundBar)
		(= bottomIcon iconExit)
		(super init: &rest)
	)
	
	(method (doit &tmp i obj)
		(if (> value maxValue) (= value maxValue))
		(if (< value minValue) (= value minValue))
		(GKSound musicVolume: value)
		(for ((= i 0)) (< i (Sounds size?)) ((++ i))
			(if (== ((= obj (Sounds at: i)) type?) soundMUSIC)
				(obj setVol: value)
			)
		)
	)
	
	(method (show)
		(= value (GKSound musicVolume?))
		(super show:)
	)
)
