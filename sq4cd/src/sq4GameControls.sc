;;; Sierra Script 1.0 - (do not remove this comment)
(script# SQGCONTROL) ;818
(include game.sh)
(use Main)
(use SlideIcon)
(use BordWind)
(use IconBar)
(use GControl)

(public
	sq4GameControls 0
	gcWin 1
)

(instance sq4GameControls of GameControls
	(properties)
	
	(method (init)
		(= gameControls self)
		(self
			add:
				iconOk
				(detailSlider
					theObj: theGame
					selector: #detailLevel
					topValue: 3
					bottomValue: 0
					yourself:
				)
				(volumeSlider
					theObj: theGame
					selector: #masterVolume
					topValue: (if (> numVoices 1) 15 else 1)
					bottomValue: 0
					yourself:
				)
				(speedSlider
					theObj: ego
					selector: #setSpeed
					topValue: 1
					bottomValue: 15
					yourself:
				)
				(iconSave theObj: theGame selector: #save yourself:)
				(iconRestore theObj: theGame selector: #restore yourself:)
				(iconRestart theObj: theGame selector: #restart yourself:)
				(iconQuit theObj: theGame selector: #quitGame yourself:)
				(iconAbout
					theObj: (ScriptID 811 0)
					selector: #doit
					yourself:
				)
				iconHelp
				(iconTextSwitch
					theObj: iconTextSwitch
					selector: #doit
					yourself:
				)
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor myRgtBordColor
			helpIconItem: iconHelp
			curIcon: iconSave
			window: gcWin
		)
		(gcWin
			color: 0
			back: myBackColor
			topBordColor: myTopBordColor
			lftBordColor: myLftBordColor
			rgtBordColor: myRgtBordColor
			botBordColor: myBotBordColor
		)
	)
)

(instance gcWin of BorderWindow
	(properties)
	
	(method (open &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 theMyBotBordColor theMyTopBordColor theMyLftBordColor theMyRgtBordColor temp11 temp12 [temp13 20] [temp33 20] [temp53 34])
		(self
			top: (/ (- 200 (+ (CelHigh 947 1 1) 6)) 2)
			left: (/ (- 320 (+ 151 (CelWide 947 0 1))) 2)
			bottom:
				(+
					(CelHigh 947 1 1)
					6
					(/ (- 200 (+ (CelHigh 947 1 1) 6)) 2)
				)
			right:
				(+
					151
					(CelWide 947 0 1)
					(/ (- 320 (+ 151 (CelWide 947 0 1))) 2)
				)
			priority: 15
		)
		(super open:)
		(DrawCel
			947
			0
			5
			(+
				(/
					(-
						(- (+ 151 (CelWide 947 0 1)) (+ 4 (CelWide 947 1 1)))
						(CelWide 947 0 5)
					)
					2
				)
				4
				(CelWide 947 1 1)
			)
			6
			15
		)
		(DrawCel 947 1 1 4 3 15)
		(DrawCel 947 1 0 94 38 15)
		(DrawCel 947 1 0 135 38 15)
		(DrawCel 947 0 4 63 (- 37 (+ (CelHigh 947 0 4) 3)) 15)
		(DrawCel 947 0 3 101 (- 37 (+ (CelHigh 947 0 4) 3)) 15)
		(DrawCel 947 0 2 146 (- 37 (+ (CelHigh 947 0 4) 3)) 15)
		(= temp4 (+ (= temp1 (+ 49 (CelHigh 947 0 1))) 26))
		(= temp3
			(+
				(= temp2 (+ 10 (CelWide 947 1 1)))
				(-
					(+ 151 (CelWide 947 0 1))
					(+ 10 (CelWide 947 1 1) 6)
				)
			)
		)
		(= temp11 15)
		(= temp5 0)
		(= theMyBotBordColor myBotBordColor)
		(= theMyRgtBordColor myRgtBordColor)
		(= theMyLftBordColor myLftBordColor)
		(= theMyTopBordColor myTopBordColor)
		(= temp0 3)
		(= temp6 3)
		(Graph
			GFillRect
			temp1
			temp2
			(+ temp4 1)
			(+ temp3 1)
			temp6
			temp5
			temp11
		)
		(= temp1 (- temp1 temp0))
		(= temp2 (- temp2 temp0))
		(= temp3 (+ temp3 temp0))
		(= temp4 (+ temp4 temp0))
		(Graph
			GFillRect
			temp1
			temp2
			(+ temp1 temp0)
			temp3
			temp6
			theMyBotBordColor
			temp11
		)
		(Graph
			GFillRect
			(- temp4 temp0)
			temp2
			temp4
			temp3
			temp6
			theMyTopBordColor
			temp11
		)
		(= temp12 0)
		(while (< temp12 temp0)
			(Graph
				GDrawLine
				(+ temp1 temp12)
				(+ temp2 temp12)
				(- temp4 (+ temp12 1))
				(+ temp2 temp12)
				theMyRgtBordColor
				temp11
				-1
			)
			(Graph
				GDrawLine
				(+ temp1 temp12)
				(- temp3 (+ temp12 1))
				(- temp4 (+ temp12 1))
				(- temp3 (+ temp12 1))
				theMyLftBordColor
				temp11
				-1
			)
			(++ temp12)
		)
		(Graph
			GShowBits
			temp1
			temp2
			(+ temp4 1)
			(+ temp3 1)
			1
		)
		(Message MsgGet SQGCONTROL 97 0 2 1 @temp33)
		(Format @temp13 @temp33 score)
		(TextSize @temp53 @temp13 999 0)
		(Display @temp13
			p_font 999
			p_color myLftBordColor
			p_at
			(+
				10
				(CelWide 947 1 1)
				(/
					(-
						(-
							(+ 151 (CelWide 947 0 1))
							(+ 10 (CelWide 947 1 1) 6)
						)
						[temp53 3]
					)
					2
				)
			)
			(+ 49 (CelHigh 947 0 1) 3 10)
		)
	)
)

(instance detailSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		nsLeft 67
		nsTop 37
		signal FIXED_POSN
		noun 98
		modNum SQGCONTROL
		helpVerb 44
		sliderView 947
		topValue 3
	)
)

(instance volumeSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		nsLeft 107
		nsTop 37
		signal FIXED_POSN
		noun 98
		modNum SQGCONTROL
		helpVerb 51
		sliderView 947
		topValue 15
	)
)

(instance speedSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		nsLeft 147
		nsTop 37
		signal FIXED_POSN
		noun 98
		modNum SQGCONTROL
		helpVerb 50
		sliderView 947
		bottomValue 15
	)
	
	(method (doit)
		(= speed (super doit: &rest))
	)
	
	(method (show)
		(if (not (user controls?))
			(= sliderCel 8)
			(= signal (| FIXED_POSN DISABLED))
		else
			(= sliderCel 0)
			(= signal FIXED_POSN)
		)
		(super show: &rest)
	)
	
	(method (mask)
	)
	
	(method (move)
		(if (user controls?) (super move: &rest))
	)
)

(instance iconSave of ControlIcon
	(properties
		view 947
		loop 2
		cel 0
		nsLeft 8
		nsTop 6
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR) 
		noun 98
		modNum SQGCONTROL
		helpVerb 49
	)
)

(instance iconRestore of ControlIcon
	(properties
		view 947
		loop 3
		cel 0
		nsLeft 8
		nsTop 26
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR) 
		noun 98
		modNum SQGCONTROL
		helpVerb 48
	)
)

(instance iconRestart of ControlIcon
	(properties
		view 947
		loop 4
		cel 0
		nsLeft 8
		nsTop 46
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR) 
		noun 98
		modNum SQGCONTROL
		helpVerb 47
	)
)

(instance iconQuit of ControlIcon
	(properties
		view 947
		loop 5
		cel 0
		nsLeft 8
		nsTop 66
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR) 
		noun 98
		modNum SQGCONTROL
		helpVerb 46
	)
)

(instance iconAbout of ControlIcon
	(properties
		view 947
		loop 6
		cel 0
		nsLeft 8
		nsTop 86
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR) 
		noun 98
		modNum SQGCONTROL
		helpVerb 43
	)
)

(instance iconHelp of IconItem
	(properties
		view 947
		loop 7
		cel 0
		nsLeft 34
		nsTop 86
		cursor 856
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		noun 98
		modNum SQGCONTROL
		helpVerb 42
	)
)

(instance iconTextSwitch of ControlIcon
	(properties
		view 947
		loop 9
		cel 0
		nsLeft 8
		nsTop 106
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		noun 98
		modNum SQGCONTROL
		helpVerb 54
	)
	
	(method (doit)
		(switch msgType
			(1 (= msgType 2))
			(2 (= msgType 1))
		)
		(self show:)
	)
	
	(method (show &tmp [temp0 25] [temp25 4] [temp29 20] [temp49 10])
		(switch msgType
			(1
				(= cDAudio 0)
				(self loop: 10)
				(Message MsgGet SQGCONTROL 97 0 3 1 @temp49)
			)
			(2
				(= cDAudio 1)
				(self loop: 9)
				(Message MsgGet SQGCONTROL 97 0 4 1 @temp49)
			)
			(3
				(= cDAudio 1)
				(self loop: 9)
				(Message MsgGet SQGCONTROL 97 0 5 1 @temp49)
			)
		)
		(Graph GFillRect 114 73 121 164 myLowlightColor)
		(Graph GShowBits 114 73 121 164 1)
		(super show: &rest)
		(Message MsgGet SQGCONTROL 97 0 1 1 @temp29)
		(Format @temp0 @temp29 @temp49)
		(TextSize @temp25 @temp0 999 0)
		(Display @temp0
			p_font 999
			p_color myLftBordColor
			p_at
			(+
				10
				(CelWide 947 1 1)
				(/
					(-
						(-
							(+ 151 (CelWide 947 0 1))
							(+ 10 (CelWide 947 1 1) 6)
						)
						[temp25 3]
					)
					2
				)
			)
			(+ 49 (CelHigh 947 0 1) 3)
		)
	)
)

(instance iconOk of IconItem
	(properties
		view 947
		loop 8
		cel 0
		nsLeft 8
		nsTop 126
		cursor 999
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR) 
		noun 98
		modNum SQGCONTROL
		helpVerb 45
	)
)
