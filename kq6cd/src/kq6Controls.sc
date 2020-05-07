;;; Sierra Script 1.0 - (do not remove this comment)
(script# KQ6CONTROLS) ;903
(include game.sh)
(use Main)
(use EgoGroop)
(use KQ6Print)
(use Kq6Window)
(use Print)
(use Slider)
(use IconBar)
(use GControl)
(use System)

(public
	kq6Controls 0
)

(local
	local0
	local1
)
(instance kq6Controls of GameControls
	(properties)
	
	(method (init)
		(Load RES_VIEW 947)
		(= gameControls self)
		(self
			window:
				(controlWin
					top: (- (/ (- 200 (+ (CelHigh 947 1 1) 0)) 2) 15)
					left: (- (/ (- 320 (+ 157 (CelWide 947 0 1) 0)) 2) 10)
					bottom:
						(-
							(+
								(CelHigh 947 1 1)
								0
								(/ (- 200 (+ (CelHigh 947 1 1) 0)) 2)
								10
							)
							5
						)
					right:
						(+
							157
							(CelWide 947 0 1)
							0
							(/ (- 320 (+ 157 (CelWide 947 0 1) 0)) 2)
							10
						)
					back: 19
					yourself:
				)
			add:
				iconOk
				(iconTextSwitch
					theObj: iconTextSwitch
					selector: 57
					yourself:
				)
				(iconSave init: theObj: theGame selector: 75 yourself:)
				(iconRestore init: theObj: theGame selector: 76 yourself:)
				(iconRestart
					init:
					theObj: theGame
					selector: 101
					yourself:
				)
				(iconQuit init: theObj: theGame selector: 100 yourself:)
				(iconAbout init: selector: 57 yourself:)
				(detailSlider
					init:
					theObj: theGame
					selector: 308
					topValue: 3
					bottomValue: 0
					yStep: 2
					yourself:
				)
				(volumeSlider
					init:
					theObj: theGame
					selector: 392
					topValue: 15
					bottomValue: 0
					yStep: 2
					yourself:
				)
				(speedSlider
					init:
					theObj: 0
					selector: 0
					yStep: 2
					yourself:
				)
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor 19
			curIcon: iconRestore
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 934)
		(DisposeScript 903)
	)
)

(instance controlWin of Kq6Window
	(properties)
	
	(method (dispose)
		(if local1 (Graph grRESTORE_BOX local1))
		(super dispose:)
	)
	
	(method (open &tmp temp0 [temp1 49] temp50 temp51 temp52 theScore temp54 temp55 temp56)
		(super open: &rest)
		(= priority -1)
		(= local0 (PicNotValid))
		(PicNotValid 1)
		(if
			(not
				(= temp54
					(if (and global169 (not (Platform 5))) (Platform 6))
				)
			)
			(DrawCel
				947
				0
				7
				(-
					(+
						(/
							(-
								(-
									(+ 157 (CelWide 947 0 1) 0)
									(+ 4 (CelWide 947 1 1))
								)
								(CelWide 947 0 5)
							)
							2
						)
						4
						(CelWide 947 1 1)
					)
					35
				)
				12
				-1
				0
			)
			(= local1 0)
		)
		(DrawCel 947 1 1 14 11 -1 0)
		(DrawCel 947 1 0 106 56 -1 0)
		(DrawCel 947 1 0 149 56 -1 0)
		(DrawCel
			947
			1
			2
			104
			(+ 0 (if (== numColors 256) 103 else 104) 7)
			-1
			0
		)
		(DrawCel
			947
			0
			4
			75
			(+ (- 30 (+ (CelHigh 947 0 4) 0)) 24)
			-1
			0
		)
		(DrawCel
			947
			0
			3
			115
			(+ (- 30 (+ (CelHigh 947 0 4) 0)) 24)
			-1
			0
		)
		(DrawCel
			947
			0
			2
			162
			(+ (- 30 (+ (CelHigh 947 0 4) 0)) 24)
			-1
			0
		)
		(PicNotValid local0)
		(= temp0 (GetPort))
		(SetPort 0)
		(Graph grUPDATE_BOX top left bottom right 1)
		(if temp54
			(= temp55
				(+
					left
					(-
						(+
							(/
								(-
									(-
										(+ 157 (CelWide 947 0 1) 0)
										(+ 4 (CelWide 947 1 1))
									)
									(CelWide 947 0 5)
								)
								2
							)
							4
							(CelWide 947 1 1)
						)
						35
					)
				)
			)
			(= temp56 (+ top 18))
			(= local1
				(Graph
					15
					temp56
					temp55
					(+ (/ (CelHigh 948 12 0) 2) temp56)
					(+ (/ (CelWide 948 12 0) 2) temp55)
				)
			)
			(DrawCel 948 12 0 0 0 -1 0 local1)
		)
		(SetPort temp0)
		(= theScore score)
		(DrawCel
			947
			10
			0
			(+
				(/
					(-
						(+ 157 (CelWide 947 0 1) 0)
						(+ 10 (CelWide 947 1 1) 6)
					)
					2
				)
				37
			)
			(+ 39 (CelHigh 947 0 1) 3 17)
			-1
			0
		)
		(= temp51 93)
		(= temp52 0)
		(while (< temp52 3)
			(= temp51 (- temp51 7))
			(if
				(and
					(== temp52 2)
					(== (mod theScore 10) 0)
					(not (== score 0))
				)
				(= temp50 11)
			else
				(= temp50 (mod theScore 10))
			)
			(= theScore (/ theScore 10))
			(DrawCel
				947
				11
				temp50
				(+
					(/
						(-
							(+ 157 (CelWide 947 0 1) 0)
							(+ 10 (CelWide 947 1 1) 6)
						)
						2
					)
					temp51
				)
				(+ 39 (CelHigh 947 0 1) 3 17)
				-1
				0
			)
			(++ temp52)
		)
		(DrawCel
			947
			11
			10
			(+
				(/
					(-
						(+ 157 (CelWide 947 0 1) 0)
						(+ 10 (CelWide 947 1 1) 6)
					)
					2
				)
				93
			)
			(+ 39 (CelHigh 947 0 1) 3 17)
			-1
			0
		)
		(= temp51 123)
		(= theScore possibleScore)
		(= temp52 0)
		(while (< temp52 3)
			(= temp51 (- temp51 7))
			(= temp50 (mod theScore 10))
			(= theScore (/ theScore 10))
			(DrawCel
				947
				11
				temp50
				(+
					(/
						(-
							(+ 157 (CelWide 947 0 1) 0)
							(+ 10 (CelWide 947 1 1) 6)
						)
						2
					)
					temp51
				)
				(+ 39 (CelHigh 947 0 1) 3 17)
				-1
				0
			)
			(++ temp52)
		)
		(theGame setCursor: normalCursor)
	)
)

(instance detailSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		signal $0080
		sliderView 947
		topValue 3
	)
	
	(method (show)
		(= nsLeft 79)
		(= nsTop 55)
		(= sRight 0)
		(super show: &rest)
	)
)

(instance volumeSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		signal $0080
		sliderView 947
		topValue 15
	)
	
	(method (doit param1)
		(if theObj
			(if (!= (Eval theObj selector) param1)
				(Eval theObj selector param1 &rest)
			else
				(Eval theObj selector)
			)
		)
	)
	
	(method (show)
		(= view 947)
		(= nsLeft 121)
		(= sliderView 947)
		(= nsTop 55)
		(= sRight 0)
		(super show: &rest)
	)
)

(instance speedSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		signal $0080
		sliderView 947
		bottomValue 15
	)
	
	(method (doit param1)
		(if argc
			(ego currentSpeed: param1)
			(ego setSpeed: (ego currentSpeed?))
		)
		(ego currentSpeed?)
	)
	
	(method (show)
		(if
		(and (ego looper?) (== (ego looper?) EgoGroop))
			(= topValue ((ego looper?) speedState?))
			(= bottomValue (+ topValue 15))
		else
			(= topValue 0)
			(= bottomValue 15)
		)
		(= nsLeft 163)
		(= nsTop 55)
		(= sRight 0)
		(super show: &rest)
	)
)

(instance iconSave of ControlIcon
	(properties
		view 947
		loop 2
		cel 0
		message 0
		signal $01c3
	)
	
	(method (init)
		(= nsLeft 18)
		(= nsTop (+ 0 (if (== numColors 256) 3 else 4) 10))
		(super init: &rest)
	)
	
	(method (show)
		(= nsLeft 18)
		(= nsTop (+ 0 (if (== numColors 256) 3 else 4) 10))
		(super show: &rest)
	)
)

(instance iconRestore of ControlIcon
	(properties
		view 947
		loop 3
		cel 0
		message 0
		signal $01c3
	)
	
	(method (init)
		(= nsLeft 18)
		(= nsTop (+ 0 (if (== numColors 256) 23 else 24) 10))
		(super init: &rest)
	)
	
	(method (show)
		(= nsLeft 18)
		(= nsTop (+ 0 (if (== numColors 256) 23 else 24) 10))
		(super show: &rest)
	)
)

(instance iconRestart of ControlIcon
	(properties
		view 947
		loop 4
		cel 0
		message 0
		signal $01c3
	)
	
	(method (init)
		(= nsLeft 18)
		(= nsTop (+ 0 (if (== numColors 256) 43 else 44) 10))
		(super init: &rest)
	)
	
	(method (show)
		(= nsLeft 18)
		(= nsTop (+ 0 (if (== numColors 256) 43 else 44) 10))
		(super show: &rest)
	)
)

(instance iconTextSwitch of ControlIcon
	(properties
		view 947
		cel 0
		signal $0183
	)
	
	(method (doit)
		(cond 
			((not (FileIO fiEXISTS {KQ6CD}))
				(Print
					font: userFont
					addText: {This button is reserved for the CD version of King's Quest VI}
					init:
				)
			)
			((not (DoAudio 9))
				(Print
					font: userFont
					addText:
						{Sorry, but a sound card capable of playing samples is required to hear speech}
					init:
				)
			)
			(else
				(switch msgType
					(1 (= msgType 2))
					(2 (= msgType 1))
				)
				(= loop (if (== loop 9) 8 else 9))
			)
		)
		(self show:)
	)
	
	(method (show &tmp [temp0 59])
		(if (== msgType 2) (= loop 8) else (= loop 9))
		(= nsLeft 108)
		(= nsTop (+ 0 (if (== numColors 256) 103 else 104) 10))
		(super show: &rest)
	)
)

(instance iconQuit of ControlIcon
	(properties
		view 947
		loop 5
		cel 0
		message 0
		signal $01c3
	)
	
	(method (init)
		(= nsLeft 18)
		(= nsTop (+ 0 (if (== numColors 256) 63 else 64) 10))
		(super init: &rest)
	)
	
	(method (show)
		(= nsLeft 18)
		(= nsTop (+ 0 (if (== numColors 256) 63 else 64) 10))
		(super show: &rest)
	)
)

(instance iconAbout of ControlIcon
	(properties
		view 947
		loop 6
		cel 0
		message 0
		signal $01c1
	)
	
	(method (init)
		(= nsLeft 18)
		(= nsTop (+ 0 (if (== numColors 256) 83 else 84) 10))
		(super init: &rest)
	)
	
	(method (show)
		(= nsLeft 18)
		(= nsTop (+ 0 (if (== numColors 256) 83 else 84) 10))
		(super show: &rest)
	)
	
	(method (select)`
		(super select: &rest)
		(gameControls hide:)
		(if (curRoom script?)
			(KQ6Print font: userFont say: 0 7 0 16 0 1 0 0 0 init:)
		else
			(curRoom setScript: 905)
		)
	)
)

(instance iconOk of IconItem
	(properties
		view 947
		loop 7
		cel 0
		message 0
		signal $01c3
	)
	
	(method (show)
		(= nsLeft 18)
		(= nsTop (+ 0 (if (== numColors 256) 103 else 104) 10))
		(super show: &rest)
	)
)

(instance helpCursor of Cursor
	(properties
		view 998
		loop 1
		cel 4
	)
)
