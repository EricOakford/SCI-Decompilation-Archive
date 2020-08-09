;;; Sierra Script 1.0 - (do not remove this comment)
(script# FPCONTROLS)
(include game.sh) (include "24.shm")
(use Main)
(use SlideIcon)
(use GControl)
(use Window)

(public
	fpGameControls 0
	gcWin 1
)

(class FreddyControlIcon of ControlIcon
	
	(method (highlight tOrF &tmp theCel)
		(if (or (not (& signal IB_ACTIVE)) (== highlightColor -1))
			(return)
		)
		(if (and argc tOrF)
			(= theCel 3)
		else
			(= theCel 0)
		)
		(DrawCel view loop theCel nsLeft nsTop 15)
	)
)

(instance fpGameControls of GameControls

	(method (init)
		(= gameControls self)
		(self
			add:
				iconOk
				detailSlider
				(volumeSlider theObj: theGame selector: #masterVolume yourself:)
				(speedSlider theObj: ego selector: #setSpeed yourself:)
				textSlider
				(iconSave theObj: theGame selector: #save yourself:)
				(iconRestore theObj: theGame selector: #restore yourself:)
				(iconRestart theObj: theGame selector: #restart yourself:)
				(iconQuit theObj: theGame selector: #quitGame yourself:)
				(iconAbout theObj: theGame selector: #showAbout yourself:)
				iconHelp
			eachElementDo: #highlightColor 40
			eachElementDo: #lowlightColor 0
			helpIconItem: iconHelp
			curIcon: iconSave
			state: NOCLICKHELP
			window: gcWin
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
		(theGame setCursor: normalCursor)
		(DisposeScript SLIDEICON)
		(DisposeScript GCONTROL)
		(DisposeScript FPCONTROLS)
	)
	
	(method (show)
		(theGame setCursor: HAND_CURSOR)
		(super show: &rest)
	)
)

(instance gcWin of SysWindow
	
	(method (open &tmp savePort t l b r thePri [buffer 20] pnv)
		(= type 128)
		(= left 51)
		(= top 38)
		(= right 268)
		(= bottom 151)
		(= lsLeft left)
		(= lsTop top)
		(= lsRight right)
		(= lsBottom bottom)
		(= priority 15)
		(= pnv (PicNotValid))
		(PicNotValid TRUE)
		(super open:)
		(= savePort (GetPort))
		(SetPort 0)
		(DrawCel 995 0 0 51 38 thePri)
		(DrawCel 995 2 0 110 139 15)
		(DrawCel 995 2 1 151 139 15)
		(DrawCel 995 2 2 195 139 15)
		(DrawCel 995 2 3 232 139 15)
		(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
		(theGame setCursor: 999)
		(SetPort savePort)
		(PicNotValid pnv)
	)
)

(instance detailSlider of Slider
	(properties
		view 995
		loop 1
		cel 4
		nsLeft 79
		nsTop 22
		signal FIXED_POSN
		noun N_DETAIL
		modNum FPCONTROLS
		helpVerb V_HELP
		sliderView 995
		sliderLoop 1
		bottomValue 1
		topValue 5
	)
	
	(method (doit theDetail)
		(if argc
			(theGame detailLevel: theDetail)
		)
		(theGame detailLevel:)
	)
	
	(method (show)
		(= maxY 37)
		(super show: &rest)
	)
)

(instance volumeSlider of Slider
	(properties
		view 995
		loop 1
		cel 5
		nsLeft 107
		nsTop 22
		signal FIXED_POSN
		noun N_VOLUME
		modNum FPCONTROLS
		helpVerb V_HELP
		sliderView 995
		sliderLoop 1
		sliderCel 1
		topValue 15
	)
	
	(method (show)
		(= maxY 37)
		(super show: &rest)
	)
)

(instance speedSlider of Slider
	(properties
		view 995
		loop 1
		cel 6
		nsLeft 130
		nsTop 22
		signal FIXED_POSN
		noun N_SPEED
		modNum FPCONTROLS
		helpVerb V_HELP
		sliderView 995
		sliderLoop 1
		sliderCel 2
		bottomValue 15
	)
	
	(method (doit newSpeed)
		(if argc
			(ego setSpeed: newSpeed)
		)
		(ego moveSpeed?)
	)
	
	(method (show)
		(= maxY 37)
		(if (not (user controls?))
			(= signal (| FIXED_POSN DISABLED))
			(= sliderLoop 10)
		else
			(= sliderLoop 1)
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

(instance textSlider of Slider
	(properties
		view 995
		loop 1
		cel 7
		nsLeft 160
		nsTop 22
		signal FIXED_POSN
		noun N_TEXT
		modNum FPCONTROLS
		helpVerb V_HELP
		sliderView 995
		sliderLoop 1
		sliderCel 3
		bottomValue 24
		topValue 1
	)
	
	(method (doit newSpeed)
		(if argc
			(= textSpeed newSpeed)
		)
		(return textSpeed)
	)
	
	(method (show)
		(= maxY 37)
		(super show: &rest)
	)
)

(instance iconSave of FreddyControlIcon
	(properties
		view 995
		loop 3
		cel 0
		nsLeft 5
		nsTop 11
		message V_GAME
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_SAVE
		modNum FPCONTROLS
		helpVerb V_HELP
	)
)

(instance iconRestore of FreddyControlIcon
	(properties
		view 995
		loop 4
		cel 0
		nsLeft 7
		nsTop 27
		message V_GAME
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_RESTORE
		modNum FPCONTROLS
		helpVerb V_HELP
	)
)

(instance iconRestart of FreddyControlIcon
	(properties
		view 995
		loop 5
		cel 0
		nsLeft 5
		nsTop 42
		message V_GAME
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_RESTART
		modNum FPCONTROLS
		helpVerb V_HELP
	)
)

(instance iconQuit of FreddyControlIcon
	(properties
		view 995
		loop 6
		cel 0
		nsLeft 7
		nsTop 57
		message V_GAME
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_QUIT
		modNum FPCONTROLS
		helpVerb V_HELP
	)
)

(instance iconAbout of FreddyControlIcon
	(properties
		view 995
		loop 7
		cel 0
		nsLeft 5
		nsTop 73
		message V_GAME
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_ABOUT
		modNum FPCONTROLS
		helpVerb V_HELP
	)
)

(instance iconHelp of FreddyControlIcon
	(properties
		view 995
		loop 8
		cel 0
		nsLeft 27
		nsTop 74
		cursor 9
		message V_HELP
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_HELP
		modNum FPCONTROLS
		helpVerb V_HELP
	)
)

(instance iconOk of FreddyControlIcon
	(properties
		view 995
		loop 9
		cel 0
		nsLeft 7
		nsTop 89
		cursor 9
		message 8
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		noun 11
		modNum FPCONTROLS
		helpVerb V_HELP
	)
)
