;;; Sierra Script 1.0 - (do not remove this comment)
(script# FPCONTROLS) ;24
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
	
	(method (highlight param1 &tmp theCel)
		(if (or (not (& signal IB_ACTIVE)) (== highlightColor -1))
			(return)
		)
		(if (and argc param1)
			(= theCel 3)
		else
			(= theCel 0)
		)
		(DrawCel view loop theCel nsLeft nsTop 15)
	)
)

(instance fpGameControls of GameControls
	(properties)
	
	(method (init)
		(= gameControls self)
		(self
			add:
				iconOk
				detailSlider
				(volumeSlider
					theObj: theGame
					selector: #masterVolume
					yourself:
				)
				(speedSlider
					theObj: ego
					selector: #setSpeed
					yourself:
				)
				textSlider
				iconSave
				iconRestore
				(iconRestart
					theObj: theGame
					selector: #restart
					yourself:
				)
				(iconQuit
					theObj: theGame
					selector: #quitGame
					yourself:
				)
				(iconAbout
					theObj: theGame
					selector: #showAbout
					yourself:
				)
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
		(theGame setCursor: ARROW_CURSOR)
		(super show: &rest)
	)
)

(instance gcWin of SysWindow
	(properties)
	
	(method (open &tmp port [temp1 4] temp5 [temp6 20] pnv)
		(= type $80)
		(= top (= left 24))
		(= right 295)
		(= bottom 175)
		(= lsLeft left)
		(= lsTop top)
		(= lsRight right)
		(= lsBottom bottom)
		(= priority 15)
		(= pnv (PicNotValid))
		(PicNotValid 1)
		(super open:)
		(= port (GetPort))
		(SetPort 0)
		(DrawCel 995 0 0 24 24 temp5)
		(DrawCel 995 2 0 110 150 15)
		(DrawCel 995 2 1 151 150 15)
		(DrawCel 995 2 2 195 150 15)
		(DrawCel 995 2 3 232 150 15)
		(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
		(SetPort port)
		(PicNotValid pnv)
	)
)

(instance detailSlider of Slider
	(properties
		view 995
		loop 1
		cel 4
		nsLeft 97
		nsTop 28
		signal FIXED_POSN
		noun N_DETAIL
		modNum FPCONTROLS
		helpVerb V_HELP
		sliderView 995
		sliderLoop 1
		bottomValue 1
		topValue 5
	)
	
	(method (doit theLevel)
		(if argc
			(theGame detailLevel: theLevel)
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
		nsLeft 133
		nsTop 28
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
		nsLeft 162
		nsTop 28
		signal FIXED_POSN
		noun N_SPEED
		modNum FPCONTROLS
		helpVerb V_HELP
		sliderView 995
		sliderLoop 1
		sliderCel 2
		bottomValue 15
	)
	
	(method (doit theSpeed)
		(if argc
			(ego setSpeed: theSpeed)
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
		nsLeft 200
		nsTop 28
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
		nsTop 15
		message V_EXIT
		signal (| RELVERIFY IMMEDIATE FIXED_POSN VICON HIDEBAR)
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
		nsLeft 8
		nsTop 34
		message V_EXIT
		signal (| RELVERIFY IMMEDIATE FIXED_POSN VICON HIDEBAR)
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
		nsTop 53
		message V_EXIT
		signal (| RELVERIFY IMMEDIATE FIXED_POSN VICON HIDEBAR)
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
		nsLeft 8
		nsTop 71
		message V_EXIT
		signal (| RELVERIFY IMMEDIATE FIXED_POSN VICON HIDEBAR)
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
		nsTop 91
		message V_EXIT
		signal (| RELVERIFY IMMEDIATE FIXED_POSN VICON HIDEBAR)
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
		nsLeft 31
		nsTop 92
		cursor 9
		message V_HELP
		signal (| RELVERIFY IMMEDIATE FIXED_POSN VICON)
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
		nsLeft 8
		nsTop 111
		cursor 9
		message V_EXIT
		signal (| RELVERIFY IMMEDIATE FIXED_POSN VICON HIDEBAR)
		noun N_OK
		modNum FPCONTROLS
		helpVerb V_HELP
	)
)
