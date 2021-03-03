;;; Sierra Script 1.0 - (do not remove this comment)
(script# GLORY_CONTROL) ;25
(include game.sh) (include "25.shm")
(use Main)
(use GloryWindow)
(use SlideIcon)
(use IconBar)
(use GControl)
(use User)

(public
	controlPanel 0
)

(instance controlPanel of GameControls
	
	(method (init)
		(= window gcWin)
		(self
			add:
				detailSlider
				(volumeSlider
					theObj: theGame
					selector: #masterVolume
					yourself:
				)
				(speedSlider
					theObj: theGame
					selector: #setSpeed
					yourself:
				)
				arcadeSlider
				(iconSave
					theObj: theGame
					selector: #save
					yourself:
				)
				(iconRestore
					theObj: theGame
					selector: #restore
					yourself:
				)
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
				iconOk
		)
		(self eachElementDo: #highlightColor -1)
		(self eachElementDo: #lowlightColor -1)
		(= helpIconItem iconHelp)
		(= curIcon iconRestore)
		(= state 2048)
	)
)

(instance gcWin of GloryWindow

	(method (open &tmp savePort wMap temp2 temp3 temp4 temp5 temp6 temp7 [temp8 4] thePri temp13 [str 25] [len 4])
		(= thePri -1)
		(= top (/ (- 200 (+ (CelHigh 933 1 1) 6)) 2))
		(= left (/ (- 320 (+ 184 (CelWide 933 0 1))) 2))
		(= bottom
			(+
				(CelHigh 933 1 1)
				6
				(/ (- 200 (+ (CelHigh 933 1 1) 6)) 2)
			)
		)
		(= right
			(+
				184
				(CelWide 933 0 1)
				(/ (- 320 (+ 184 (CelWide 933 0 1))) 2)
			)
		)
		(= priority thePri)
		(super open:)
		(DrawCel 933 1 1 4 3 thePri)
		(DrawCel 933 1 0 95 28 thePri)
		(DrawCel 933 1 0 131 28 thePri)
		(DrawCel 933 1 0 167 28 thePri)
		(DrawCel 933 0 2 136 (- 24 (+ (CelHigh 933 0 4) 3)) thePri)
		(DrawCel 933 0 3 100 (- 24 (+ (CelHigh 933 0 4) 3)) thePri)
		(DrawCel 933 0 4 64 (- 24 (+ (CelHigh 933 0 4) 3)) thePri)
		(DrawCel 933 0 5 173 (- 24 (+ (CelHigh 933 0 4) 3)) thePri)
		(= temp5 (+ (= temp2 (+ 31 (CelHigh 933 0 1))) 20))
		(= temp4
			(+
				(= temp3 (+ 4 (CelWide 933 1 1)))
				(-
					(+ 184 (CelWide 933 0 1))
					(+ 4 (CelWide 933 1 1) 10)
				)
			)
		)
		(= temp6 22)
		(= temp7 1)
		(Message MsgGet GLORY_CONTROL N_ABOUT NULL C_PAUSED 1 @str)
		(TextSize @len @str 999 0)
		(Display @str
			p_font 123
			p_color 30
			p_at (- (+ (CelWide 933 0 5) 4 (CelWide 933 1 1)) 30) (- (+ 31 (CelHigh 933 0 1) 7) 2)
		)
		(Display @str
			p_font 123
			p_color 25
			p_at (- (+ (CelWide 933 0 5) 4 (CelWide 933 1 1)) 31) (- (+ 31 (CelHigh 933 0 1) 7) 3)
		)
	)
)

(instance detailSlider of Slider
	(properties
		view 933
		loop 0
		cel 1
		nsLeft 67
		nsTop 24
		signal FIXED_POSN
		noun N_DETAIL
		modNum GLORY_CONTROL
		helpVerb V_HELP
		sliderView 933
		yStep 2
		bottomValue 1
		topValue 5
	)
	
	(method (doit newDetail)
		(if argc
			(theGame detailLevel: newDetail)
		)
		(theGame detailLevel:)
	)
)

(instance volumeSlider of Slider
	(properties
		view 933
		loop 0
		cel 1
		nsLeft 103
		nsTop 24
		signal FIXED_POSN
		noun N_VOLUME
		modNum GLORY_CONTROL
		helpVerb V_HELP
		sliderView 933
		yStep 2
		topValue 15
	)
)

(instance speedSlider of Slider
	(properties
		view 933
		loop 0
		cel 1
		nsLeft 139
		nsTop 24
		signal FIXED_POSN
		noun N_SPEED
		modNum GLORY_CONTROL
		helpVerb V_HELP
		sliderView 933
		yStep 2
		bottomValue 15
	)
	
	(method (show)
		(if (or (not (User controls?)) (Btst fSpeedDisabled))
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
		(if (and (User controls?) (not (Btst fSpeedDisabled)))
			(super move: &rest)
		)
	)
)

(instance arcadeSlider of Slider
	(properties
		view 933
		loop 0
		cel 1
		nsLeft 174
		nsTop 24
		signal FIXED_POSN
		noun N_ARCADE
		modNum GLORY_CONTROL
		helpVerb V_HELP
		sliderView 933
		yStep 2
		bottomValue 1
		topValue 15
	)
	
	(method (doit newDifficulty)
		(if argc (= arcadeDifficulty (/ (+ newDifficulty 4) 5)))
		(return (* (- arcadeDifficulty 1) 8))
	)
)

(instance iconSave of ControlIcon
	(properties
		view 933
		loop 2
		cel 0
		nsLeft 8
		nsTop 6
		message V_ROYALS
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_SAVE
		modNum GLORY_CONTROL
		helpVerb V_HELP
	)
	
	(method (highlight tOrF)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
		else
			(DrawCel view loop cel nsLeft nsTop -1)
		)
	)
)

(instance iconRestore of ControlIcon
	(properties
		view 933
		loop 3
		cel 0
		nsLeft 8
		nsTop 23
		message V_ROYALS
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_RESTORE
		modNum GLORY_CONTROL
		helpVerb V_HELP
	)
	
	(method (highlight tOrF)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
		else
			(DrawCel view loop cel nsLeft nsTop -1)
		)
	)
)

(instance iconRestart of ControlIcon
	(properties
		view 933
		loop 4
		cel 0
		nsLeft 8
		nsTop 40
		message V_ROYALS
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_RESTART
		modNum GLORY_CONTROL
		helpVerb V_HELP
	)
	
	(method (highlight tOrF)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
		else
			(DrawCel view loop cel nsLeft nsTop -1)
		)
	)
)

(instance iconQuit of ControlIcon
	(properties
		view 933
		loop 5
		cel 0
		nsLeft 8
		nsTop 57
		message V_ROYALS
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_QUIT
		modNum GLORY_CONTROL
		helpVerb V_HELP
	)
	
	(method (highlight tOrF)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
		else
			(DrawCel view loop cel nsLeft nsTop -1)
		)
	)
)

(instance iconAbout of ControlIcon
	(properties
		view 933
		loop 6
		cel 0
		nsLeft 8
		nsTop 74
		message V_ROYALS
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_ABOUT
		modNum GLORY_CONTROL
		helpVerb V_HELP
	)
	
	(method (highlight tOrF)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
		else
			(DrawCel view loop cel nsLeft nsTop -1)
		)
	)
)

(instance iconHelp of IconItem
	(properties
		view 933
		loop 7
		cel 0
		nsLeft 8
		nsTop 91
		cursor 949
		message V_HELP
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_HELP
		modNum GLORY_CONTROL
		helpVerb V_HELP
	)
	
	(method (highlight tOrF)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
		else
			(DrawCel view loop cel nsLeft nsTop -1)
		)
	)
)

(instance iconOk of IconItem
	(properties
		view 933
		loop 8
		cel 0
		nsLeft 8
		nsTop 108
		cursor 949
		message V_HELP
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_OK
		modNum GLORY_CONTROL
		helpVerb V_HELP
	)
	
	(method (highlight tOrF)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
		else
			(DrawCel view loop cel nsLeft nsTop -1)
		)
	)
)
