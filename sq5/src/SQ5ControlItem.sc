;;; Sierra Script 1.0 - (do not remove this comment)
(script# SQ5CONTROLS)
(include game.sh) (include "0.shm")
(use Main)
(use Print)
(use SlideIcon)
(use BordWind)
(use IconBar)
(use GControl)
(use System)

(public
	gameControlCode 0
	gcWin 1
)

(class SQ5ControlItem of ControlIcon
	
	(method (select)
		(super select: &rest)
		(self doit:)
	)
)

(instance SQ5GameControls of GameControls
	(properties
		name "gameControls"
	)
	
	(method (dispatchEvent event &tmp eO obj port [str 50] eType eMsg)
		(= eType (event type?))
		(= eMsg (event message?))
		(return
			(cond 
				((& eType helpEvent)
					(if
						(and
							(= obj (self firstTrue: #onMe event))
							(obj helpVerb?)
						)
						(= port (GetPort))
						(if (systemWindow respondsTo: #eraseOnly)
							(= eO (systemWindow eraseOnly?))
							(systemWindow eraseOnly: TRUE)
							(Print
								font: userFont
								width: 250
								addText: (obj noun?) (obj helpVerb?) NULL 1 0 0 (obj modNum?)
								init:
							)
							(systemWindow eraseOnly: eO)
						else
							(Print
								font: userFont
								width: 250
								addText: (obj noun?) (obj helpVerb?) NULL 1 0 0 (obj modNum?)
								init:
							)
						)
						(SetPort port)
					)
					(if helpIconItem
						(helpIconItem signal: (& (helpIconItem signal?) (~ TRANSLATOR)))
					)
					(theGame setCursor: ARROW_CURSOR)
					(return FALSE)
				)
				((& eType direction)
					(switch eMsg
						(dirS
							(cond 
								(
									(and
										(IsObject highlightedIcon)
										(highlightedIcon respondsTo: #retreat)
									)
									(highlightedIcon retreat:)
									(return FALSE)
								)
								(
									(or
										(not (IsObject highlightedIcon))
										(& (highlightedIcon signal?) VICON)
									)
									(self advance:)
									(return FALSE)
								)
							)
						)
						(dirN
							(cond 
								(
									(and
										(IsObject highlightedIcon)
										(highlightedIcon respondsTo: #advance)
									)
									(highlightedIcon advance:)
									(return FALSE)
								)
								(
									(or
										(not (IsObject highlightedIcon))
										(& (highlightedIcon signal?) VICON)
									)
									(self retreat:)
									(return FALSE)
								)
							)
						)
						(else 
							(super dispatchEvent: event)
						)
					)
				)
				(else
					(super dispatchEvent: event)
				)
			)
		)
	)
)

(instance gameControlCode of Code
	
	(method (doit)
		(= gameControls SQ5GameControls)
		(gameControls
			add:
				detailSlider
				(volumeSlider theObj: theGame selector: #masterVolume yourself:)
				(speedSlider theObj: ego selector: #setSpeed yourself:)
				(iconSave theObj: theGame selector: #save yourself:)
				(iconRestore theObj: theGame selector: #restore yourself:)
				(iconRestart theObj: theGame selector: #restart yourself:)
				(iconQuit theObj: theGame selector: #quitGame yourself:)
				iconOk
				(iconAbout theObj: theGame selector: #showAbout yourself:)
				iconHelp
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor 4
			eachElementDo: #modNum 0
			eachElementDo: #helpVerb V_HELP
			helpIconItem: iconHelp
			window: gcWin
			curIcon: iconSave
			state: NOCLICKHELP
			show:
		)
	)
)

(instance gcWin of BorderWindow
	
	(method (open &tmp temp0 [ofBuf 25] [str 25] thePri)
		(= thePri -1)
		(self
			top: (/ (- 200 (+ (CelHigh 995 1 1) 6)) 2)
			left: (/ (- 320 (+ 151 (CelWide 995 0 1))) 2)
			bottom:
				(+
					(CelHigh 995 1 1)
					6
					(/ (- 200 (+ (CelHigh 995 1 1) 6)) 2)
				)
			right:
				(+
					151
					(CelWide 995 0 1)
					(/ (- 320 (+ 151 (CelWide 995 0 1))) 2)
				)
			priority: thePri
		)
		(super open:)
		(DrawCel 995 0 5
			(+
				(/
					(-
						(- (+ 151 (CelWide 995 0 1)) (+ 4 (CelWide 995 1 1)))
						(CelWide 995 0 5)
					)
					2
				)
				4
				(CelWide 995 1 1)
			)
			3
			thePri
		)
		(DrawCel 995 1 1 4 3 thePri)
		(DrawCel 995 1 0 94 38 thePri)
		(DrawCel 995 1 0 135 38 thePri)
		(DrawCel 995 0 4 63
			(WhichLanguage
				(- (- 37 (+ (CelHigh 995 0 4) 3)) 9)
				(- 37 (+ (CelHigh 995 0 4) 3))
				(- 37 (+ (CelHigh 995 0 4) 3))
				(- 37 (+ (CelHigh 995 0 4) 3))
				(- 37 (+ (CelHigh 995 0 4) 3))
			)
			thePri
		)
		(DrawCel 995 0 3 101
			(WhichLanguage
				(- (- 37 (+ (CelHigh 995 0 4) 3)) 9)
				(- 37 (+ (CelHigh 995 0 4) 3))
				(- 37 (+ (CelHigh 995 0 4) 3))
				(- 37 (+ (CelHigh 995 0 4) 3))
				(- 37 (+ (CelHigh 995 0 4) 3))
			)
			thePri
		)
		(DrawCel 995 0 2
			(WhichLanguage 140 146 146 146 146)
			(WhichLanguage
				(- (- 37 (+ (CelHigh 995 0 4) 3)) 9)
				(- 37 (+ (CelHigh 995 0 4) 3))
				(- 37 (+ (CelHigh 995 0 4) 3))
				(- 37 (+ (CelHigh 995 0 4) 3))
				(- 37 (+ (CelHigh 995 0 4) 3))
			)
			thePri
		)
		(DrawCel 995 9 0
			(+ 5 (CelWide 995 1 1))
			(+ 38 (CelHigh 995 0 1))
			thePri
		)
		(Graph GShowBits 12 1 15 (+ 151 (CelWide 995 0 1)) 1)
		(Message MsgGet 0 N_OUTOF NULL 1 1 @ofBuf)
		(Format @str {%d %s %d} score @ofBuf possibleScore)
		(Display @str
			p_font userFont
			p_at (+ 5 (CelWide 995 1 1) 6) (+ 38 (CelHigh 995 0 1) 15)
		)
		(SetPort 0)
	)
)

(instance detailSlider of Slider
	(properties
		view 995
		loop 0
		cel 1
		nsLeft 139
		nsTop 73
		signal FIXED_POSN
		noun N_DETAIL
		helpVerb V_HELP
		sliderView 995
		bottomValue 1
		topValue 3
	)
	
	(method (doit theLevel)
		(if argc
			(theGame detailLevel: theLevel)
		)
		(theGame detailLevel:)
	)
)

(instance volumeSlider of Slider
	(properties
		view 995
		loop 0
		cel 1
		nsLeft 179
		nsTop 73
		signal FIXED_POSN
		noun N_VOLUME
		helpVerb V_HELP
		sliderView 995
		topValue 15
	)
)

(instance speedSlider of Slider
	(properties
		view 995
		loop 0
		cel 1
		nsLeft 219
		nsTop 73
		signal FIXED_POSN
		noun N_SPEED
		helpVerb V_HELP
		sliderView 995
		bottomValue 15
	)
	
	(method (doit newSpeed)
		(if argc
			(ego setSpeed: newSpeed)
		)
		(return egoSpeed)
	)
	
	(method (show)
		(if (not (user controls?))
			(= signal (| FIXED_POSN DISABLED))
		else
			(= signal FIXED_POSN)
		)
		(super show: &rest)
	)
	
	(method (mask)
	)
	
	(method (move)
		(if (user controls?)
			(super move: &rest)
		)
	)
)

(instance iconSave of SQ5ControlItem
	(properties
		view 995
		loop 2
		cel 0
		nsLeft 80
		nsTop 42
		message V_GAME
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_SAVE
		helpVerb V_HELP
	)
)

(instance iconRestore of SQ5ControlItem
	(properties
		view 995
		loop 3
		cel 0
		nsLeft 80
		nsTop 62
		message V_GAME
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_RESTORE
		helpVerb V_HELP
	)
)

(instance iconRestart of SQ5ControlItem
	(properties
		view 995
		loop 4
		cel 0
		nsLeft 80
		nsTop 82
		message V_GAME
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_RESTART
		helpVerb V_HELP
	)
)

(instance iconQuit of SQ5ControlItem
	(properties
		view 995
		loop 5
		cel 0
		nsLeft 80
		nsTop 102
		message V_GAME
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_QUIT
		helpVerb V_HELP
	)
)

(instance iconAbout of SQ5ControlItem
	(properties
		view 995
		loop 6
		cel 0
		nsLeft 80
		nsTop 142
		message V_GAME
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_ABOUT
		helpVerb V_HELP
	)
)

(instance iconHelp of IconItem
	(properties
		view 995
		loop 7
		cel 0
		nsLeft 106
		nsTop 142
		cursor 989
		message V_HELP
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_HELP
		helpVerb V_HELP
	)
)

(instance iconOk of IconItem
	(properties
		view 995
		loop 8
		cel 0
		nsLeft 80
		nsTop 122
		cursor 989
		message V_GAME
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_DONE2
		helpVerb V_HELP
	)
)
