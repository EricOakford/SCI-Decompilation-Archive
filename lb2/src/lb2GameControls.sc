;;; Sierra Script 1.0 - (do not remove this comment)
(script# LBCONTROL)
(include game.sh) (include "24.shm")
(use Main)
(use Print)
(use SlideIcon)
(use IconBar)
(use GControl)
(use Window)

(public
	lb2GameControls 0
	gcWin 1
)

(instance lb2GameControls of GameControls
	
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
				(iconMode theObj: theGame selector: #doit yourself:)
				iconHelp
			eachElementDo: #highlightColor global169
			eachElementDo: #lowlightColor global151
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
		(DisposeScript LBCONTROL)
	)
	
	(method (show)
		(theGame setCursor: ARROW_CURSOR)
		(super show: &rest)
	)
)

(instance gcWin of SysWindow
	
	(method (open &tmp thePort [temp1 4] temp5 [temp6 20])
		(= type 128)
		(= top (/ (- 200 (+ (CelHigh 995 1 1) 6)) 2))
		(= left (/ (- 320 (+ 191 (CelWide 995 0 1))) 2))
		(= bottom
			(+
				(CelHigh 995 1 1)
				6
				(/ (- 200 (+ (CelHigh 995 1 1) 6)) 2)
			)
		)
		(= right
			(+
				191
				(CelWide 995 0 1)
				(/ (- 320 (+ 191 (CelWide 995 0 1))) 2)
			)
		)
		(= lsLeft (- left 6))
		(= lsTop (- top 6))
		(= lsRight (+ right 6))
		(= lsBottom (+ bottom 6))
		(= priority 15)
		(super open:)
		(= thePort (GetPort))
		(SetPort 0)
		(Graph GFillRect top left bottom right 3 global151 15)
		(Graph GDrawLine 33 49 33 269 global171 15)
		(Graph GDrawLine 34 50 34 268 myInsideColor 15)
		(Graph GDrawLine 35 51 35 267 global170 15)
		(Graph GDrawLine 33 49 166 49 global171 15)
		(Graph GDrawLine 34 50 165 50 myInsideColor 15)
		(Graph GDrawLine 35 51 164 51 global170 15)
		(Graph GDrawLine 166 49 166 269 global171 15)
		(Graph GDrawLine 165 50 165 268 myInsideColor 15)
		(Graph GDrawLine 164 51 164 267 global170 15)
		(Graph GDrawLine 33 269 166 269 global171 15)
		(Graph GDrawLine 34 268 165 268 myInsideColor 15)
		(Graph GDrawLine 35 267 164 267 global170 15)
		(DrawCel 995 0 6
			(WhichLanguage 155 155 178 155 155)
			(WhichLanguage 42 45 45 45 45)
			temp5
		)
		(DrawCel 995 1 1 56 39 temp5)
		(DrawCel 995 14 0 138 139 temp5)
		(DrawCel 995 13 (== msgType 2) 208 145 temp5)
		(DrawCel 995 1 0 146 73 temp5)
		(DrawCel 995 1 0 186 73 temp5)
		(DrawCel 995 1 0 226 73 temp5)
		(DrawCel 995 0 4 116
			(WhichLanguage 58 60 59 60 60)
			temp5
		)
		(DrawCel 995 0 3
			(WhichLanguage 145 154 154 154 154)
			(WhichLanguage 134 60 59 60 60)
			temp5
		)
		(DrawCel 995 0 2
			(WhichLanguage 178 198 193 198 198)
			(WhichLanguage 58 60 59 60 60)
			temp5
		)
		(DrawCel 995 0 5 238
			(WhichLanguage 134 60 59 60 60)
			temp5
		)
		(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
		(SetPort thePort)
	)
)

(instance detailSlider of Slider
	(properties
		view 995
		loop 0
		cel 1
		nsLeft 67
		nsTop 35
		signal FIXED_POSN
		noun N_DETAIL
		modNum LBCONTROL
		helpVerb V_HELP
		sliderView 995
		bottomValue 1
		topValue 5
	)
	
	(method (doit newDetail)
		(if argc (theGame detailLevel: newDetail))
		(theGame detailLevel:)
	)
)

(instance volumeSlider of Slider
	(properties
		view 995
		loop 0
		cel 1
		nsLeft 107
		nsTop 35
		signal FIXED_POSN
		noun N_DETAIL
		modNum LBCONTROL
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
		nsLeft 147
		nsTop 35
		signal FIXED_POSN
		noun N_SPEED
		modNum LBCONTROL
		helpVerb V_HELP
		sliderView 995
		bottomValue 15
	)
	
	(method (doit theSpeed)
		(if argc
			(ego setSpeed: theSpeed)
			(= global369 theSpeed)
		)
		(ego moveSpeed?)
	)
	
	(method (show)
		(if (not (user controls?))
			(= signal (| FIXED_POSN DISABLED))
			(= sliderLoop 9)
		else
			(= sliderLoop 0)
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
		loop 0
		cel 1
		nsLeft 187
		nsTop 35
		signal FIXED_POSN
		noun N_TEXT
		modNum LBCONTROL
		helpVerb V_HELP
		sliderView 995
		bottomValue 24
		topValue 1
	)
	
	(method (doit newSpeed)
		(if argc (= textSpeed newSpeed))
		(return textSpeed)
	)
)

(instance iconSave of ControlIcon
	(properties
		view 995
		loop 2
		cel 0
		nsLeft 8
		nsTop 8
		message V_MAGNIFIER
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_SAVE
		modNum LBCONTROL
		helpVerb V_HELP
	)
)

(instance iconRestore of ControlIcon
	(properties
		view 995
		loop 3
		cel 0
		nsLeft 8
		nsTop 28
		message V_MAGNIFIER
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_RESTORE
		modNum LBCONTROL
		helpVerb V_HELP
	)
)

(instance iconRestart of ControlIcon
	(properties
		view 995
		loop 4
		cel 0
		nsLeft 8
		nsTop 48
		message V_MAGNIFIER
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_RESTART
		modNum LBCONTROL
		helpVerb V_HELP
	)
)

(instance iconQuit of ControlIcon
	(properties
		view 995
		loop 5
		cel 0
		nsLeft 8
		nsTop 68
		message V_MAGNIFIER
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_QUIT
		modNum LBCONTROL
		helpVerb V_HELP
	)
)

(instance iconAbout of ControlIcon
	(properties
		view 995
		loop 6
		cel 0
		nsLeft 8
		nsTop 88
		message V_MAGNIFIER
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_ABOUT
		modNum LBCONTROL
		helpVerb V_HELP
	)
)

(instance iconHelp of IconItem
	(properties
		view 995
		loop 7
		cel 0
		nsLeft 34
		nsTop 88
		cursor 9
		message V_HELP
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_HELP
		modNum LBCONTROL
		helpVerb V_HELP
	)
)

(instance iconOk of IconItem
	(properties
		view 995
		loop 8
		cel 0
		nsLeft 8
		nsTop 108
		cursor 9
		message V_MAGNIFIER
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_OK
		modNum LBCONTROL
		helpVerb V_HELP
	)
)

(instance iconMode of ControlIcon
	(properties
		view 995
		loop 12
		cel 0
		nsLeft 90
		nsTop 108
		message V_MAGNIFIER
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_MODE
		modNum LBCONTROL
		helpVerb V_HELP
	)
	
	(method (doit &tmp temp0)
		(asm
			ldi      1
			bnt      code_054f
			lsg      msgType
			dup     
			eq?     
			bnt      code_052b
			ldi      2
			sag      msgType
			ldi      1
			sat      temp0
			jmp      code_0539
code_052b:
			dup     
			ldi      2
			eq?     
			bnt      code_0539
			ldi      1
			sag      msgType
			ldi      0
			sat      temp0
code_0539:
			toss    
			pushi    6
			pushi    995
			pushi    13
			lst      temp0
			pushi    154
			pushi    110
			pushi    15
			callk    DrawCel,  12
			jmp      code_0563
code_054f:
			pushi    #font
			pushi    1
			lsg      userFont
			pushi    205
			pushi    1
			lofsa    {*** You're not playing a cd!}
			push    
			pushi    110
			pushi    0
			class    Print
			send     16
code_0563:
			pushi    #show
			pushi    0
			self     4
			ret     
		)
	)
)
