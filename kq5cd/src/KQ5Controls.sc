;;; Sierra Script 1.0 - (do not remove this comment)
(script# 755)
(include sci.sh)
(use Main)
(use BertaWindow)
(use KQCursor)
(use GControl)
(use IconBar)

(public
	slowControls 0
	fastControls 1
)

(local
	local0
)
(procedure (localproc_0012 param1 param2 param3 param4 param5)
	(switch (theGame printLang?)
		(49 param1)
		(34 param2)
		(33 param3)
		(39 param4)
		(else  param5)
	)
)

(procedure (localproc_004e)
	(localproc_0012 1026 1040 1051 1050 946)
)

(class KQ5Controls of GameControls
	(properties
		elements 0
		size 0
		height 200
		underBits 0
		oldMouseX 0
		oldMouseY 0
		curIcon 0
		highlightedIcon 0
		prevIcon 0
		curInvIcon 0
		useIconItem 0
		helpIconItem 0
		port 0
		window 0
		state $0000
		activateHeight 0
		y 0
		okButton 0
	)
	
	(method (init)
		(Load rsVIEW 946)
		(= gameControls self)
		(self
			add:
				iconOk
				(iconSave init: theObj: theGame selector: 78 yourself:)
				(iconRestore init: theObj: theGame selector: 79 yourself:)
				(iconRestart
					init:
					theObj: theGame
					selector: 104
					yourself:
				)
				(iconQuit init: theObj: theGame selector: 103 yourself:)
				(iconAbout
					init:
					theObj: (ScriptID 756 0)
					selector: 60
					yourself:
				)
				(iconHelp cursor: helpCursor yourself:)
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor (if isVGA 19 else 7)
			helpIconItem: iconHelp
			curIcon: iconRestore
		)
		(super init: &rest)
	)
	
	(method (dispatchEvent event &tmp temp0 temp1 temp2)
		(return
			(if
				(and
					(== (event type?) 16384)
					(== (event message?) JOY_DOWNLEFT)
				)
				(= temp2 0)
				(= temp1 (self firstTrue: #onMe event))
				(event dispose:)
				(if (and temp1 (temp1 helpStr?))
					(self hide:)
					(= temp0 (GetPort))
					(DoAudio 2 (temp1 helpStr?))
					(SetPort temp0)
					(= temp2 1)
				)
				(if helpIconItem
					(helpIconItem signal: (& (helpIconItem signal?) $ffef))
				)
				(theGame setCursor: normalCursor)
				(if temp2 (self show:))
				(return temp1)
			else
				(super dispatchEvent: event)
			)
		)
	)
)

(instance fastControls of KQ5Controls
	(properties)
	
	(method (init)
		(self
			window:
				(fastWin
					top: (-
						(/ (- 200 (+ (CelHigh (localproc_004e) 1 1) 6)) 2)
						10
					)
					left:
						(-
							(/
								(-
									320
									(+
										67
										(localproc_0012 -2 4 4 4 0)
										(CelWide (localproc_004e) 0 1)
										(* (+ 40 (localproc_0012 10 15 15 15 0)) 2)
										(localproc_0012 2 2 2 2 4)
										(localproc_0012 30 30 30 30 0)
									)
								)
								2
							)
							10
						)
					bottom:
						(+
							(CelHigh (localproc_004e) 1 1)
							6
							(/ (- 200 (+ (CelHigh (localproc_004e) 1 1) 6)) 2)
							(localproc_0012 -1 -1 -1 -1 10)
						)
					right:
						(+
							67
							(localproc_0012 -2 4 4 4 0)
							(CelWide (localproc_004e) 0 1)
							(* (+ 40 (localproc_0012 10 15 15 15 0)) 2)
							(localproc_0012 2 2 2 2 4)
							(localproc_0012 30 30 30 30 0)
							(/
								(-
									320
									(+
										67
										(localproc_0012 -2 4 4 4 0)
										(CelWide (localproc_004e) 0 1)
										(* (+ 40 (localproc_0012 10 15 15 15 0)) 2)
										(localproc_0012 2 2 2 2 4)
										(localproc_0012 30 30 30 30 0)
									)
								)
								2
							)
							(localproc_0012 5 -5 -5 -5 10)
						)
					yourself:
				)
			add:
				(detailSlider
					init:
					theObj: theGame
					selector: 290
					topValue: 3
					bottomValue: 0
					yStep: 2
					yourself:
				)
				(volumeSlider
					init:
					theObj: theGame
					selector: 379
					topValue: 15
					bottomValue: 0
					yStep: 2
					yourself:
				)
				(speedSlider
					init:
					theObj: 0
					selector: 0
					topValue: 0
					bottomValue: 10
					yStep: 2
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance slowControls of KQ5Controls
	(properties)
	
	(method (init)
		(self
			window:
				(slowWin
					top: (-
						(/ (- 200 (+ (CelHigh (localproc_004e) 1 1) 6)) 2)
						5
					)
					left:
						(-
							(/
								(-
									320
									(+
										67
										(localproc_0012 -2 4 4 4 0)
										(CelWide (localproc_004e) 0 1)
										(* (+ 40 (localproc_0012 10 15 15 15 0)) 2)
										(localproc_0012 2 2 2 2 4)
										(localproc_0012 30 30 30 30 0)
									)
								)
								2
							)
							5
						)
					bottom:
						(+
							(CelHigh (localproc_004e) 1 1)
							6
							(/ (- 200 (+ (CelHigh (localproc_004e) 1 1) 6)) 2)
							(localproc_0012 -1 -1 -1 -1 10)
						)
					right:
						(+
							67
							(localproc_0012 -2 4 4 4 0)
							(CelWide (localproc_004e) 0 1)
							(* (+ 40 (localproc_0012 10 15 15 15 0)) 2)
							(localproc_0012 2 2 2 2 4)
							(localproc_0012 30 30 30 30 0)
							(/
								(-
									320
									(+
										67
										(localproc_0012 -2 4 4 4 0)
										(CelWide (localproc_004e) 0 1)
										(* (+ 40 (localproc_0012 10 15 15 15 0)) 2)
										(localproc_0012 2 2 2 2 4)
										(localproc_0012 30 30 30 30 0)
									)
								)
								2
							)
							(localproc_0012 5 -5 -5 -5 10)
						)
					yourself:
				)
			add:
				(volumeSlider
					init:
					theObj: theGame
					selector: 379
					topValue: 15
					bottomValue: 0
					yStep: 2
					yourself:
				)
				(speedSlider
					init:
					theObj: 0
					selector: 0
					topValue: 0
					bottomValue: 15
					yStep: 2
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance fastWin of BertaWindow
	(properties)
	
	(method (open &tmp temp0 [temp1 4] [temp5 45])
		(super open:)
		(= local0 (PicNotValid))
		(PicNotValid 1)
		(DrawCel
			(localproc_004e)
			0
			5
			(+
				(/
					(-
						(-
							(+
								67
								(localproc_0012 -2 4 4 4 0)
								(CelWide (localproc_004e) 0 1)
								(* (+ 40 (localproc_0012 10 15 15 15 0)) 2)
								(localproc_0012 2 2 2 2 4)
								(localproc_0012 30 30 30 30 0)
							)
							(+
								(localproc_0012 2 2 2 2 4)
								(localproc_0012 2 2 2 2 0)
								(CelWide (localproc_004e) 1 1)
							)
						)
						(CelWide (localproc_004e) 0 5)
					)
					2
				)
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				(CelWide (localproc_004e) 1 1)
				10
			)
			(+ (localproc_0012 -2 0 0 0 6) 10)
			-1
			0
		)
		(DrawCel
			(localproc_004e)
			1
			1
			(+
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				10
			)
			(+ (- 3 (localproc_0012 5 5 5 5 0)) 10)
			-1
			0
		)
		(DrawCel
			(localproc_004e)
			1
			0
			(+
				67
				(localproc_0012 -2 4 4 4 0)
				(localproc_0012 42 41 41 41 27)
				10
			)
			48
			-1
			0
		)
		(DrawCel
			(localproc_004e)
			1
			0
			(+
				67
				(localproc_0012 -2 4 4 4 0)
				(-
					(+ 40 (localproc_0012 10 15 15 15 0))
					(localproc_0012 3 8 8 8 0)
				)
				(localproc_0012 42 48 48 48 28)
				10
			)
			48
			-1
			0
		)
		(DrawCel
			(localproc_004e)
			0
			4
			(+
				(-
					(+ 67 (localproc_0012 -2 4 4 4 0))
					(localproc_0012 -2 0 0 0 4)
				)
				10
			)
			(+ (- 37 (+ (CelHigh (localproc_004e) 0 4) 3)) 10)
			-1
			0
		)
		(DrawCel
			(localproc_004e)
			0
			3
			(+
				(-
					(+
						67
						(localproc_0012 -2 4 4 4 0)
						(-
							(+ 40 (localproc_0012 10 15 15 15 0))
							(localproc_0012 3 8 8 8 0)
						)
					)
					(localproc_0012 -2 1 1 1 6)
				)
				10
			)
			(+ (- 37 (+ (CelHigh (localproc_004e) 0 4) 3)) 10)
			-1
			0
		)
		(DrawCel
			(localproc_004e)
			0
			2
			(+
				(-
					(+
						67
						(localproc_0012 -2 4 4 4 0)
						(-
							(+ 40 (localproc_0012 10 15 15 15 0))
							(localproc_0012 3 8 8 8 0)
						)
						(-
							(+ 40 (localproc_0012 10 15 15 15 0))
							(localproc_0012 15 15 15 15 0)
						)
					)
					(localproc_0012 -5 -10 -10 -10 1)
				)
				10
			)
			(+ (- 37 (+ (CelHigh (localproc_004e) 0 4) 3)) 10)
			-1
			0
		)
		(PicNotValid local0)
		(= temp0 (GetPort))
		(SetPort 0)
		(Graph grUPDATE_BOX lsTop lsLeft lsBottom lsRight 1)
		(SetPort temp0)
		(Format @temp5 755 0 score possibleScore)
		(TextSize @temp1 @temp5 69 0)
		(Display
			@temp5
			dsFONT
			69
			dsCOLOR
			0
			dsCOORD
			(+
				6
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				(CelWide (localproc_004e) 1 1)
				(/
					(-
						(-
							(+
								67
								(localproc_0012 -2 4 4 4 0)
								(CelWide (localproc_004e) 0 1)
								(* (+ 40 (localproc_0012 10 15 15 15 0)) 2)
								(localproc_0012 2 2 2 2 4)
								(localproc_0012 30 30 30 30 0)
							)
							(+
								6
								(localproc_0012 2 2 2 2 4)
								(localproc_0012 2 2 2 2 0)
								(CelWide (localproc_004e) 1 1)
								(localproc_0012 2 2 2 2 4)
								2
							)
						)
						[temp1 3]
					)
					2
				)
				10
			)
			(+
				(localproc_0012 6 3 3 3 9)
				37
				(CelHigh (localproc_004e) 0 1)
				3
				10
			)
		)
	)
)

(instance slowWin of BertaWindow
	(properties)
	
	(method (open &tmp temp0 [temp1 4] [temp5 45])
		(super open:)
		(= local0 (PicNotValid))
		(PicNotValid 1)
		(DrawCel
			(localproc_004e)
			0
			5
			(+
				(/
					(-
						(-
							(+
								67
								(localproc_0012 -2 4 4 4 0)
								(CelWide (localproc_004e) 0 1)
								(* (+ 40 (localproc_0012 10 15 15 15 0)) 2)
								(localproc_0012 2 2 2 2 4)
								(localproc_0012 30 30 30 30 0)
							)
							(+
								(localproc_0012 2 2 2 2 4)
								(localproc_0012 2 2 2 2 0)
								(CelWide (localproc_004e) 1 1)
							)
						)
						(CelWide (localproc_004e) 0 5)
					)
					2
				)
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				(CelWide (localproc_004e) 1 1)
				10
			)
			(+ (localproc_0012 -2 0 0 0 6) 10)
			-1
			0
		)
		(DrawCel
			(localproc_004e)
			1
			1
			(+
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				10
			)
			(+ (- 3 (localproc_0012 5 5 5 5 0)) 10)
			-1
			0
		)
		(DrawCel
			(localproc_004e)
			1
			0
			(-
				(+
					67
					(localproc_0012 -2 4 4 4 0)
					(-
						(+ 40 (localproc_0012 10 15 15 15 0))
						(localproc_0012 3 8 8 8 0)
					)
					(localproc_0012 42 48 48 48 28)
				)
				10
			)
			48
			-1
			0
		)
		(DrawCel
			(localproc_004e)
			0
			3
			(-
				(-
					(+
						67
						(localproc_0012 -2 4 4 4 0)
						(-
							(+ 40 (localproc_0012 10 15 15 15 0))
							(localproc_0012 3 8 8 8 0)
						)
					)
					(localproc_0012 -2 1 1 1 6)
				)
				20
			)
			(+ (- 37 (+ (CelHigh (localproc_004e) 0 4) 3)) 10)
			-1
			0
		)
		(DrawCel
			(localproc_004e)
			0
			2
			(-
				(+
					67
					(localproc_0012 -2 4 4 4 0)
					(-
						(+ 40 (localproc_0012 10 15 15 15 0))
						(localproc_0012 3 8 8 8 0)
					)
					(-
						(+ 40 (localproc_0012 10 15 15 15 0))
						(localproc_0012 15 15 15 15 0)
					)
				)
				(localproc_0012 -5 -10 -10 -10 1)
			)
			(+ (- 37 (+ (CelHigh (localproc_004e) 0 4) 3)) 10)
			-1
			0
		)
		(PicNotValid local0)
		(= temp0 (GetPort))
		(SetPort 0)
		(Graph grUPDATE_BOX lsTop lsLeft lsBottom lsRight 1)
		(SetPort temp0)
		(Format @temp5 755 0 score possibleScore)
		(TextSize @temp1 @temp5 69 0)
		(Display
			@temp5
			dsFONT
			69
			dsCOLOR
			0
			dsCOORD
			(+
				6
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				(CelWide (localproc_004e) 1 1)
				(/
					(-
						(-
							(+
								67
								(localproc_0012 -2 4 4 4 0)
								(CelWide (localproc_004e) 0 1)
								(* (+ 40 (localproc_0012 10 15 15 15 0)) 2)
								(localproc_0012 2 2 2 2 4)
								(localproc_0012 30 30 30 30 0)
							)
							(+
								6
								(localproc_0012 2 2 2 2 4)
								(localproc_0012 2 2 2 2 0)
								(CelWide (localproc_004e) 1 1)
								(localproc_0012 2 2 2 2 4)
								2
							)
						)
						[temp1 3]
					)
					2
				)
				10
			)
			(+
				(localproc_0012 6 3 3 3 9)
				37
				(CelHigh (localproc_004e) 0 1)
				3
				10
			)
		)
	)
)

(instance detailSlider of Slider
	(properties
		loop 0
		cel 1
		signal $0080
		helpStr 9220
		topValue 3
	)
	
	(method (init)
		(= view (localproc_004e))
		(= nsLeft (+ 67 (localproc_0012 -2 4 4 4 0) 10))
		(= nsTop 47)
		(= sliderView (localproc_004e))
		(super init: &rest)
	)
	
	(method (show)
		(= view (localproc_004e))
		(= nsLeft
			(localproc_0012
				(+ 67 (localproc_0012 -2 4 4 4 0) 20)
				(+ 67 (localproc_0012 -2 4 4 4 0) 20)
				(+ 67 (localproc_0012 -2 4 4 4 0) 20)
				(+ 67 (localproc_0012 -2 4 4 4 0) 20)
				(+ 67 (localproc_0012 -2 4 4 4 0) 10)
			)
		)
		(= sliderView (localproc_004e))
		(= nsTop 47)
		(= sRight 0)
		(super show: &rest)
	)
)

(instance volumeSlider of Slider
	(properties
		loop 0
		cel 1
		signal $0080
		helpStr 9221
		topValue 15
	)
	
	(method (init)
		(= view (localproc_004e))
		(= nsLeft
			(+
				67
				(localproc_0012 -2 4 4 4 0)
				(-
					(+ 40 (localproc_0012 10 15 15 15 0))
					(localproc_0012 3 8 8 8 0)
				)
				10
			)
		)
		(= nsTop 47)
		(= sliderView (localproc_004e))
		(super init: &rest)
	)
	
	(method (show)
		(= view (localproc_004e))
		(= nsLeft
			(if (== gameControls fastControls)
				(+
					67
					(localproc_0012 -2 4 4 4 0)
					(-
						(+ 40 (localproc_0012 10 15 15 15 0))
						(localproc_0012 3 8 8 8 0)
					)
					10
				)
			else
				(-
					(+
						67
						(localproc_0012 -2 4 4 4 0)
						(-
							(+ 40 (localproc_0012 10 15 15 15 0))
							(localproc_0012 3 8 8 8 0)
						)
					)
					20
				)
			)
		)
		(= sliderView (localproc_004e))
		(= nsTop 47)
		(= sRight 0)
		(super show: &rest)
	)
)

(instance speedSlider of Slider
	(properties
		loop 0
		cel 1
		signal $0080
		helpStr 9222
		bottomValue 15
	)
	
	(method (init)
		(= view (localproc_004e))
		(= nsLeft
			(+
				67
				(localproc_0012 -2 4 4 4 0)
				(-
					(+ 40 (localproc_0012 10 15 15 15 0))
					(localproc_0012 3 8 8 8 0)
				)
				(-
					(+ 40 (localproc_0012 10 15 15 15 0))
					(localproc_0012 15 15 15 15 0)
				)
				10
			)
		)
		(= nsTop 47)
		(= sliderView (localproc_004e))
		(super init: &rest)
	)
	
	(method (doit param1)
		(if argc
			(ego moveSpeed: param1 cycleSpeed: param1)
			(theGame egoMoveSpeed: param1)
		)
		(ego moveSpeed?)
	)
	
	(method (show)
		(= view (localproc_004e))
		(= nsLeft
			(if (== gameControls fastControls)
				(+
					67
					(localproc_0012 -2 4 4 4 0)
					(-
						(+ 40 (localproc_0012 10 15 15 15 0))
						(localproc_0012 3 8 8 8 0)
					)
					(-
						(+ 40 (localproc_0012 10 15 15 15 0))
						(localproc_0012 15 15 15 15 0)
					)
					10
				)
			else
				(+
					67
					(localproc_0012 -2 4 4 4 0)
					(-
						(+ 40 (localproc_0012 10 15 15 15 0))
						(localproc_0012 3 8 8 8 0)
					)
					(-
						(+ 40 (localproc_0012 10 15 15 15 0))
						(localproc_0012 15 15 15 15 0)
					)
				)
			)
		)
		(= nsTop 47)
		(= sRight 0)
		(super show: &rest)
	)
)

(instance iconSave of ControlIcon
	(properties
		loop 2
		cel 0
		message 9
		signal $01c3
		helpStr 9223
	)
	
	(method (init)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				4
				10
			)
		)
		(= nsTop
			(+
				(- 3 (localproc_0012 5 5 5 5 0))
				(if isVGA 3 else 4)
				10
			)
		)
		(super init: &rest)
	)
	
	(method (show)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				4
				(localproc_0012 10 11 11 11 10)
			)
		)
		(= nsTop
			(+
				(- 3 (localproc_0012 5 5 5 5 0))
				(if isVGA 3 else 4)
				10
			)
		)
		(super show: &rest)
	)
)

(instance iconRestore of ControlIcon
	(properties
		loop 3
		cel 0
		message 9
		signal $01c3
		helpStr 9224
	)
	
	(method (init)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				4
				10
			)
		)
		(= nsTop
			(+
				(- 3 (localproc_0012 5 5 5 5 0))
				(if isVGA 23 else 24)
				10
			)
		)
		(super init: &rest)
	)
	
	(method (show)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				4
				(localproc_0012 10 11 11 11 10)
			)
		)
		(= nsTop
			(+
				(- 3 (localproc_0012 5 5 5 5 0))
				(if isVGA 23 else 24)
				10
			)
		)
		(super show: &rest)
	)
)

(instance iconRestart of ControlIcon
	(properties
		loop 4
		cel 0
		message 9
		signal $01c3
		helpStr 9225
	)
	
	(method (init)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				4
				10
			)
		)
		(= nsTop
			(+
				(- 3 (localproc_0012 5 5 5 5 0))
				(if isVGA 43 else 44)
				10
			)
		)
		(super init: &rest)
	)
	
	(method (show)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				4
				(localproc_0012 10 11 11 11 10)
			)
		)
		(= nsTop
			(+
				(- 3 (localproc_0012 5 5 5 5 0))
				(if isVGA 43 else 44)
				10
			)
		)
		(super show: &rest)
	)
)

(instance iconQuit of ControlIcon
	(properties
		loop 5
		cel 0
		message 9
		signal $01c3
		helpStr 9226
	)
	
	(method (init)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				4
				10
			)
		)
		(= nsTop
			(+
				(- 3 (localproc_0012 5 5 5 5 0))
				(if isVGA 63 else 64)
				10
			)
		)
		(super init: &rest)
	)
	
	(method (show)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				4
				(localproc_0012 10 11 11 11 10)
			)
		)
		(= nsTop
			(+
				(- 3 (localproc_0012 5 5 5 5 0))
				(if isVGA 63 else 64)
				10
			)
		)
		(super show: &rest)
	)
)

(instance iconAbout of ControlIcon
	(properties
		loop 6
		cel 0
		message 9
		signal $01c3
		helpStr 9227
	)
	
	(method (init)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				4
				10
			)
		)
		(= nsTop
			(+
				(- 3 (localproc_0012 5 5 5 5 0))
				(if isVGA 83 else 84)
				10
			)
		)
		(super init: &rest)
	)
	
	(method (show)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				4
				(localproc_0012 10 11 11 11 10)
			)
		)
		(= nsTop
			(+
				(- 3 (localproc_0012 5 5 5 5 0))
				(if isVGA 83 else 84)
				10
			)
		)
		(super show: &rest)
	)
)

(instance iconHelp of IconI
	(properties
		loop 7
		cel 0
		message 6
		signal $0183
	)
	
	(method (show)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				(localproc_0012 32 32 32 32 30)
				(localproc_0012 10 11 11 11 10)
			)
		)
		(= nsTop
			(+
				(- 3 (localproc_0012 5 5 5 5 0))
				(if isVGA 83 else 84)
				10
			)
		)
		(super show: &rest)
	)
)

(instance iconOk of IconI
	(properties
		loop 8
		cel 0
		message 9
		signal $01c3
		helpStr 9228
	)
	
	(method (show)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(localproc_0012 2 2 2 2 4)
				(localproc_0012 2 2 2 2 0)
				4
				(localproc_0012 10 11 11 11 10)
			)
		)
		(= nsTop
			(+
				(- 3 (localproc_0012 5 5 5 5 0))
				(if isVGA 103 else 104)
				10
			)
		)
		(super show: &rest)
	)
)

(instance helpCursor of KQCursor
	(properties
		view 942
		loop 1
		cel 4
	)
	
	(method (init)
		(if global400 (self number: 70 yourself:))
		(super init: &rest)
	)
)
