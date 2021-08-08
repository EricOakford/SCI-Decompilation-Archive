;;; Sierra Script 1.0 - (do not remove this comment)
(script# 755)
(include game.sh)
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
	pnv
)
(procedure (WhichLanguage german spanish french italian english)
	(switch (theGame printLang?)
		(GERMAN german)
		(SPANISH spanish)
		(FRENCH french)
		(ITALIAN italian)
		(else english)
	)
)

(procedure (localproc_004e)
	(WhichLanguage 1026 1040 1051 1050 946)
)

(class KQ5Controls of GameControls
	
	(method (init)
		(Load VIEW 946)
		(= gameControls self)
		(self
			add:
				iconOk
				(iconSave
					init:
					theObj: theGame
					selector: #save
					yourself:
				)
				(iconRestore
					init:
					theObj: theGame
					selector: #restore
					yourself:
				)
				(iconRestart
					init:
					theObj: theGame
					selector: #restart
					yourself:
				)
				(iconQuit
					init:
					theObj: theGame
					selector: #quitGame
					yourself:
				)
				(iconAbout
					init:
					theObj: (ScriptID 756 0)
					selector: #doit
					yourself:
				)
				(iconHelp
					cursor: helpCursor
					yourself:
				)
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor (if isVGA 19 else 7)
			helpIconItem: iconHelp
			curIcon: iconRestore
		)
		(super init: &rest)
	)
	
	(method (dispatchEvent event &tmp thePort obj temp2)
		(return
			(if
				(and
					(== (event type?) userEvent)
					(== (event message?) verbHelp)
				)
				(= temp2 0)
				(= obj (self firstTrue: #onMe event))
				(event dispose:)
				(if (and obj (obj helpStr?))
					(self hide:)
					(= thePort (GetPort))
					(DoAudio Play (obj helpStr?))
					(SetPort thePort)
					(= temp2 1)
				)
				(if helpIconItem
					(helpIconItem signal: (& (helpIconItem signal?) (~ TRANSLATOR)))
				)
				(theGame setCursor: normalCursor)
				(if temp2
					(self show:)
				)
				(return obj)
			else
				(super dispatchEvent: event)
			)
		)
	)
)

(instance fastControls of KQ5Controls
	
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
										(WhichLanguage -2 4 4 4 0)
										(CelWide (localproc_004e) 0 1)
										(* (+ 40 (WhichLanguage 10 15 15 15 0)) 2)
										(WhichLanguage 2 2 2 2 4)
										(WhichLanguage 30 30 30 30 0)
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
							(WhichLanguage -1 -1 -1 -1 10)
						)
					right:
						(+
							67
							(WhichLanguage -2 4 4 4 0)
							(CelWide (localproc_004e) 0 1)
							(* (+ 40 (WhichLanguage 10 15 15 15 0)) 2)
							(WhichLanguage 2 2 2 2 4)
							(WhichLanguage 30 30 30 30 0)
							(/
								(-
									320
									(+
										67
										(WhichLanguage -2 4 4 4 0)
										(CelWide (localproc_004e) 0 1)
										(* (+ 40 (WhichLanguage 10 15 15 15 0)) 2)
										(WhichLanguage 2 2 2 2 4)
										(WhichLanguage 30 30 30 30 0)
									)
								)
								2
							)
							(WhichLanguage 5 -5 -5 -5 10)
						)
					yourself:
				)
			add:
				(detailSlider
					init:
					theObj: theGame
					selector: #detailLevel
					topValue: 3
					bottomValue: 0
					yStep: 2
					yourself:
				)
				(volumeSlider
					init:
					theObj: theGame
					selector: #masterVolume
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
										(WhichLanguage -2 4 4 4 0)
										(CelWide (localproc_004e) 0 1)
										(* (+ 40 (WhichLanguage 10 15 15 15 0)) 2)
										(WhichLanguage 2 2 2 2 4)
										(WhichLanguage 30 30 30 30 0)
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
							(WhichLanguage -1 -1 -1 -1 10)
						)
					right:
						(+
							67
							(WhichLanguage -2 4 4 4 0)
							(CelWide (localproc_004e) 0 1)
							(* (+ 40 (WhichLanguage 10 15 15 15 0)) 2)
							(WhichLanguage 2 2 2 2 4)
							(WhichLanguage 30 30 30 30 0)
							(/
								(-
									320
									(+
										67
										(WhichLanguage -2 4 4 4 0)
										(CelWide (localproc_004e) 0 1)
										(* (+ 40 (WhichLanguage 10 15 15 15 0)) 2)
										(WhichLanguage 2 2 2 2 4)
										(WhichLanguage 30 30 30 30 0)
									)
								)
								2
							)
							(WhichLanguage 5 -5 -5 -5 10)
						)
					yourself:
				)
			add:
				(volumeSlider
					init:
					theObj: theGame
					selector: #masterVolume
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
	
	(method (open &tmp thePort [len 4] [buf 45])
		(super open:)
		(= pnv (PicNotValid))
		(PicNotValid TRUE)
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
								(WhichLanguage -2 4 4 4 0)
								(CelWide (localproc_004e) 0 1)
								(* (+ 40 (WhichLanguage 10 15 15 15 0)) 2)
								(WhichLanguage 2 2 2 2 4)
								(WhichLanguage 30 30 30 30 0)
							)
							(+
								(WhichLanguage 2 2 2 2 4)
								(WhichLanguage 2 2 2 2 0)
								(CelWide (localproc_004e) 1 1)
							)
						)
						(CelWide (localproc_004e) 0 5)
					)
					2
				)
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				(CelWide (localproc_004e) 1 1)
				10
			)
			(+ (WhichLanguage -2 0 0 0 6) 10)
			-1
			0
		)
		(DrawCel
			(localproc_004e)
			1
			1
			(+
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				10
			)
			(+ (- 3 (WhichLanguage 5 5 5 5 0)) 10)
			-1
			0
		)
		(DrawCel
			(localproc_004e)
			1
			0
			(+
				67
				(WhichLanguage -2 4 4 4 0)
				(WhichLanguage 42 41 41 41 27)
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
				(WhichLanguage -2 4 4 4 0)
				(-
					(+ 40 (WhichLanguage 10 15 15 15 0))
					(WhichLanguage 3 8 8 8 0)
				)
				(WhichLanguage 42 48 48 48 28)
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
					(+ 67 (WhichLanguage -2 4 4 4 0))
					(WhichLanguage -2 0 0 0 4)
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
						(WhichLanguage -2 4 4 4 0)
						(-
							(+ 40 (WhichLanguage 10 15 15 15 0))
							(WhichLanguage 3 8 8 8 0)
						)
					)
					(WhichLanguage -2 1 1 1 6)
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
						(WhichLanguage -2 4 4 4 0)
						(-
							(+ 40 (WhichLanguage 10 15 15 15 0))
							(WhichLanguage 3 8 8 8 0)
						)
						(-
							(+ 40 (WhichLanguage 10 15 15 15 0))
							(WhichLanguage 15 15 15 15 0)
						)
					)
					(WhichLanguage -5 -10 -10 -10 1)
				)
				10
			)
			(+ (- 37 (+ (CelHigh (localproc_004e) 0 4) 3)) 10)
			-1
			0
		)
		(PicNotValid pnv)
		(= thePort (GetPort))
		(SetPort 0)
		(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
		(SetPort thePort)
		(Format @buf 755 0 score possibleScore)
		(TextSize @len @buf 69 0)
		(Display @buf
			p_font 69
			p_color 0
			p_at
			(+
				6
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				(CelWide (localproc_004e) 1 1)
				(/
					(-
						(-
							(+
								67
								(WhichLanguage -2 4 4 4 0)
								(CelWide (localproc_004e) 0 1)
								(* (+ 40 (WhichLanguage 10 15 15 15 0)) 2)
								(WhichLanguage 2 2 2 2 4)
								(WhichLanguage 30 30 30 30 0)
							)
							(+
								6
								(WhichLanguage 2 2 2 2 4)
								(WhichLanguage 2 2 2 2 0)
								(CelWide (localproc_004e) 1 1)
								(WhichLanguage 2 2 2 2 4)
								2
							)
						)
						[len 3]
					)
					2
				)
				10
			)
			(+
				(WhichLanguage 6 3 3 3 9)
				37
				(CelHigh (localproc_004e) 0 1)
				3
				10
			)
		)
	)
)

(instance slowWin of BertaWindow

	(method (open &tmp thePort [len 4] [buf 45])
		(super open:)
		(= pnv (PicNotValid))
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
								(WhichLanguage -2 4 4 4 0)
								(CelWide (localproc_004e) 0 1)
								(* (+ 40 (WhichLanguage 10 15 15 15 0)) 2)
								(WhichLanguage 2 2 2 2 4)
								(WhichLanguage 30 30 30 30 0)
							)
							(+
								(WhichLanguage 2 2 2 2 4)
								(WhichLanguage 2 2 2 2 0)
								(CelWide (localproc_004e) 1 1)
							)
						)
						(CelWide (localproc_004e) 0 5)
					)
					2
				)
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				(CelWide (localproc_004e) 1 1)
				10
			)
			(+ (WhichLanguage -2 0 0 0 6) 10)
			-1
			0
		)
		(DrawCel
			(localproc_004e)
			1
			1
			(+
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				10
			)
			(+ (- 3 (WhichLanguage 5 5 5 5 0)) 10)
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
					(WhichLanguage -2 4 4 4 0)
					(-
						(+ 40 (WhichLanguage 10 15 15 15 0))
						(WhichLanguage 3 8 8 8 0)
					)
					(WhichLanguage 42 48 48 48 28)
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
						(WhichLanguage -2 4 4 4 0)
						(-
							(+ 40 (WhichLanguage 10 15 15 15 0))
							(WhichLanguage 3 8 8 8 0)
						)
					)
					(WhichLanguage -2 1 1 1 6)
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
					(WhichLanguage -2 4 4 4 0)
					(-
						(+ 40 (WhichLanguage 10 15 15 15 0))
						(WhichLanguage 3 8 8 8 0)
					)
					(-
						(+ 40 (WhichLanguage 10 15 15 15 0))
						(WhichLanguage 15 15 15 15 0)
					)
				)
				(WhichLanguage -5 -10 -10 -10 1)
			)
			(+ (- 37 (+ (CelHigh (localproc_004e) 0 4) 3)) 10)
			-1
			0
		)
		(PicNotValid pnv)
		(= thePort (GetPort))
		(SetPort 0)
		(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
		(SetPort thePort)
		(Format @buf 755 0 score possibleScore)
		(TextSize @len @buf 69 0)
		(Display @buf
			p_font 69
			p_color 0
			p_at
			(+
				6
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				(CelWide (localproc_004e) 1 1)
				(/
					(-
						(-
							(+
								67
								(WhichLanguage -2 4 4 4 0)
								(CelWide (localproc_004e) 0 1)
								(* (+ 40 (WhichLanguage 10 15 15 15 0)) 2)
								(WhichLanguage 2 2 2 2 4)
								(WhichLanguage 30 30 30 30 0)
							)
							(+
								6
								(WhichLanguage 2 2 2 2 4)
								(WhichLanguage 2 2 2 2 0)
								(CelWide (localproc_004e) 1 1)
								(WhichLanguage 2 2 2 2 4)
								2
							)
						)
						[len 3]
					)
					2
				)
				10
			)
			(+
				(WhichLanguage 6 3 3 3 9)
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
		signal FIXED_POSN
		helpStr 9220
		topValue 3
	)
	
	(method (init)
		(= view (localproc_004e))
		(= nsLeft (+ 67 (WhichLanguage -2 4 4 4 0) 10))
		(= nsTop 47)
		(= sliderView (localproc_004e))
		(super init: &rest)
	)
	
	(method (show)
		(= view (localproc_004e))
		(= nsLeft
			(WhichLanguage
				(+ 67 (WhichLanguage -2 4 4 4 0) 20)
				(+ 67 (WhichLanguage -2 4 4 4 0) 20)
				(+ 67 (WhichLanguage -2 4 4 4 0) 20)
				(+ 67 (WhichLanguage -2 4 4 4 0) 20)
				(+ 67 (WhichLanguage -2 4 4 4 0) 10)
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
		signal FIXED_POSN
		helpStr 9221
		topValue 15
	)
	
	(method (init)
		(= view (localproc_004e))
		(= nsLeft
			(+
				67
				(WhichLanguage -2 4 4 4 0)
				(-
					(+ 40 (WhichLanguage 10 15 15 15 0))
					(WhichLanguage 3 8 8 8 0)
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
					(WhichLanguage -2 4 4 4 0)
					(-
						(+ 40 (WhichLanguage 10 15 15 15 0))
						(WhichLanguage 3 8 8 8 0)
					)
					10
				)
			else
				(-
					(+
						67
						(WhichLanguage -2 4 4 4 0)
						(-
							(+ 40 (WhichLanguage 10 15 15 15 0))
							(WhichLanguage 3 8 8 8 0)
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
		signal FIXED_POSN
		helpStr 9222
		bottomValue 15
	)
	
	(method (init)
		(= view (localproc_004e))
		(= nsLeft
			(+
				67
				(WhichLanguage -2 4 4 4 0)
				(-
					(+ 40 (WhichLanguage 10 15 15 15 0))
					(WhichLanguage 3 8 8 8 0)
				)
				(-
					(+ 40 (WhichLanguage 10 15 15 15 0))
					(WhichLanguage 15 15 15 15 0)
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
					(WhichLanguage -2 4 4 4 0)
					(-
						(+ 40 (WhichLanguage 10 15 15 15 0))
						(WhichLanguage 3 8 8 8 0)
					)
					(-
						(+ 40 (WhichLanguage 10 15 15 15 0))
						(WhichLanguage 15 15 15 15 0)
					)
					10
				)
			else
				(+
					67
					(WhichLanguage -2 4 4 4 0)
					(-
						(+ 40 (WhichLanguage 10 15 15 15 0))
						(WhichLanguage 3 8 8 8 0)
					)
					(-
						(+ 40 (WhichLanguage 10 15 15 15 0))
						(WhichLanguage 15 15 15 15 0)
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
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		helpStr 9223
	)
	
	(method (init)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				4
				10
			)
		)
		(= nsTop
			(+
				(- 3 (WhichLanguage 5 5 5 5 0))
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
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				4
				(WhichLanguage 10 11 11 11 10)
			)
		)
		(= nsTop
			(+
				(- 3 (WhichLanguage 5 5 5 5 0))
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
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		helpStr 9224
	)
	
	(method (init)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				4
				10
			)
		)
		(= nsTop
			(+
				(- 3 (WhichLanguage 5 5 5 5 0))
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
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				4
				(WhichLanguage 10 11 11 11 10)
			)
		)
		(= nsTop
			(+
				(- 3 (WhichLanguage 5 5 5 5 0))
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
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		helpStr 9225
	)
	
	(method (init)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				4
				10
			)
		)
		(= nsTop
			(+
				(- 3 (WhichLanguage 5 5 5 5 0))
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
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				4
				(WhichLanguage 10 11 11 11 10)
			)
		)
		(= nsTop
			(+
				(- 3 (WhichLanguage 5 5 5 5 0))
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
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		helpStr 9226
	)
	
	(method (init)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				4
				10
			)
		)
		(= nsTop
			(+
				(- 3 (WhichLanguage 5 5 5 5 0))
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
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				4
				(WhichLanguage 10 11 11 11 10)
			)
		)
		(= nsTop
			(+
				(- 3 (WhichLanguage 5 5 5 5 0))
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
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		helpStr 9227
	)
	
	(method (init)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				4
				10
			)
		)
		(= nsTop
			(+
				(- 3 (WhichLanguage 5 5 5 5 0))
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
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				4
				(WhichLanguage 10 11 11 11 10)
			)
		)
		(= nsTop
			(+
				(- 3 (WhichLanguage 5 5 5 5 0))
				(if isVGA 83 else 84)
				10
			)
		)
		(super show: &rest)
	)
)

(instance iconHelp of IconItem
	(properties
		loop 7
		cel 0
		message verbHelp
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
	)
	
	(method (show)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				(WhichLanguage 32 32 32 32 30)
				(WhichLanguage 10 11 11 11 10)
			)
		)
		(= nsTop
			(+
				(- 3 (WhichLanguage 5 5 5 5 0))
				(if isVGA 83 else 84)
				10
			)
		)
		(super show: &rest)
	)
)

(instance iconOk of IconItem
	(properties
		loop 8
		cel 0
		message 9
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		helpStr 9228
	)
	
	(method (show)
		(= view (localproc_004e))
		(= nsLeft
			(+
				(WhichLanguage 2 2 2 2 4)
				(WhichLanguage 2 2 2 2 0)
				4
				(WhichLanguage 10 11 11 11 10)
			)
		)
		(= nsTop
			(+
				(- 3 (WhichLanguage 5 5 5 5 0))
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
