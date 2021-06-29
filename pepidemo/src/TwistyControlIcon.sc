;;; Sierra Script 1.0 - (do not remove this comment)
(script# TWCONTROLS)
(include game.sh) (include "0.shm")
(use Main)
(use SlideIcon)
(use GControl)
(use Window)

(public
	theControls 0
	gcWin 1
)

(class TwistyControlIcon of ControlIcon
	
	(method (show)
		(self
			view: (if (FileIO fileExists {CDAUDIO}) 1904 else 904)
		)
		(super show: &rest)
	)
	
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
	
	(method (showMode &tmp savePort)
		(= savePort (GetPort))
		(SetPort 0)
		(switch msgType
			(TEXT_MSG
				(= msgType CD_MSG)
				(DrawCel 1904 10 1 204 163 -1 0)
				(DrawCel 1904 10 2 144 162 -1 0)
			)
			(CD_MSG
				(= msgType TEXT_MSG)
				(DrawCel 1904 10 0 144 162 -1 0)
				(DrawCel 1904 10 3 204 163 -1 0)
			)
		)
		(SetPort savePort)
	)
)

(instance gcWin of SysWindow

	(method (open &tmp savePort [temp1 2])
		(= priority 13)
		(self
			top:
				(+
					(-
						(/
							(-
								200
								(if (FileIO fileExists {CDAUDIO})
									(CelHigh 1908 0 0)
								else
									(CelHigh 908 0 0)
								)
							)
							2
						)
						1
					)
					25
				)
			left:
				(+
					(-
						(/
							(-
								320
								(if (FileIO fileExists {CDAUDIO})
									(CelWide 1908 0 0)
								else
									(CelWide 908 0 0)
								)
							)
							2
						)
						1
					)
					9
				)
			bottom:
				(-
					(+
						(-
							(/
								(-
									200
									(if (FileIO fileExists {CDAUDIO})
										(CelHigh 1908 0 0)
									else
										(CelHigh 908 0 0)
									)
								)
								2
							)
							1
						)
						(if (FileIO fileExists {CDAUDIO})
							(CelHigh 1908 0 0)
						else
							(CelHigh 908 0 0)
						)
						1
					)
					4
				)
			right:
				(-
					(+
						(-
							(/
								(-
									320
									(if (FileIO fileExists {CDAUDIO})
										(CelWide 1908 0 0)
									else
										(CelWide 908 0 0)
									)
								)
								2
							)
							1
						)
						(if (FileIO fileExists {CDAUDIO})
							(CelWide 1908 0 0)
						else
							(CelWide 908 0 0)
						)
						1
					)
					9
				)
			lsTop:
				(-
					(/
						(-
							200
							(if (FileIO fileExists {CDAUDIO})
								(CelHigh 1908 0 0)
							else
								(CelHigh 908 0 0)
							)
						)
						2
					)
					1
				)
			lsLeft:
				(-
					(/
						(-
							320
							(if (FileIO fileExists {CDAUDIO})
								(CelWide 1908 0 0)
							else
								(CelWide 908 0 0)
							)
						)
						2
					)
					1
				)
			lsBottom:
				(+
					(-
						(/
							(-
								200
								(if (FileIO fileExists {CDAUDIO})
									(CelHigh 1908 0 0)
								else
									(CelHigh 908 0 0)
								)
							)
							2
						)
						1
					)
					(if (FileIO fileExists {CDAUDIO})
						(CelHigh 1908 0 0)
					else
						(CelHigh 908 0 0)
					)
					1
				)
			lsRight:
				(+
					(-
						(/
							(-
								320
								(if (FileIO fileExists {CDAUDIO})
									(CelWide 1908 0 0)
								else
									(CelWide 908 0 0)
								)
							)
							2
						)
						1
					)
					(if (FileIO fileExists {CDAUDIO})
						(CelWide 1908 0 0)
					else
						(CelWide 908 0 0)
					)
					1
				)
		)
		(super open:)
		(= savePort (GetPort))
		(SetPort 0)
		(if (FileIO fileExists {CDAUDIO})
			(DrawCel
				1908
				0
				0
				(+
					(-
						(/
							(-
								320
								(if (FileIO fileExists {CDAUDIO})
									(CelWide 1908 0 0)
								else
									(CelWide 908 0 0)
								)
							)
							2
						)
						1
					)
					1
				)
				(+
					(-
						(/
							(-
								200
								(if (FileIO fileExists {CDAUDIO})
									(CelHigh 1908 0 0)
								else
									(CelHigh 908 0 0)
								)
							)
							2
						)
						1
					)
					1
				)
				15
				0
			)
		else
			(DrawCel
				908
				0
				0
				(+
					(-
						(/
							(-
								320
								(if (FileIO fileExists {CDAUDIO})
									(CelWide 1908 0 0)
								else
									(CelWide 908 0 0)
								)
							)
							2
						)
						1
					)
					1
				)
				(+
					(-
						(/
							(-
								200
								(if (FileIO fileExists {CDAUDIO})
									(CelHigh 1908 0 0)
								else
									(CelHigh 908 0 0)
								)
							)
							2
						)
						1
					)
					1
				)
				15
			)
		)
		(cond 
			((and (FileIO fileExists {CDAUDIO}) (== msgType TEXT_MSG))
				(DrawCel 1904 10 0 144 162 -1 0)
			)
			((FileIO fileExists {CDAUDIO})
				(DrawCel 1904 10 1 204 163 -1 0)
			)
		)
		(SetPort savePort)
	)
)

(instance theControls of GameControls

	(method (init param1)
		(super init: &rest)
		(gcWin color: 16 back: 16)
		((= gameControls self)
			window: gcWin
			add:
				textSlider
				(detailSlider theObj: theGame selector: #detailLevel yourself:)
				(speedSlider theObj: ego selector: #setSpeed yourself:)
				(volumeSlider theObj: theGame selector: #masterVolume yourself:)
				(iconSave theObj: theGame selector: #save yourself:)
				(iconRestore theObj: theGame selector: #restore yourself:)
				(iconRestart theObj: theGame selector: #restart yourself:)
				(iconQuit theObj: theGame selector: #quitGame yourself:)
				(iconAbout selector: #doit yourself:)
				iconHelp
				iconOk
			helpIconItem: iconHelp
			curIcon: iconRestore
			state: NOCLICKHELP
			eachElementDo: #highlightColor myHighlightColor
			eachElementDo: #lowlightColor myLowlightColor
		)
		(if (FileIO fileExists {CDAUDIO})
			(theControls addAfter: iconAbout iconMode)
		)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript TWCONTROLS)
	)
	
	(method (show)
		(self init: 0)
		(= global224 ((ScriptID 895 0) view?))
		((ScriptID 895 0) view: 2000)
		(UnLoad RES_VIEW ((ScriptID 895 0) view?))
		(super show: &rest)
	)
	
	(method (hide)
		((ScriptID 895 0) view: global224)
		(super hide: &rest)
	)
)

(instance detailSlider of Slider
	(properties
		view 904
		loop 9
		cel 1
		signal FIXED_POSN
		noun N_DETAIL
		helpVerb V_HELP
		sliderView 904
		sliderLoop 1
		sliderCel 1
		yStep 2
		topValue 3
	)
	
	(method (show)
		(self
			view: (if (FileIO fileExists {CDAUDIO}) 1904 else 904)
			nsLeft:
				(+
					(-
						(/
							(-
								320
								(if (FileIO fileExists {CDAUDIO})
									(CelWide 1908 0 0)
								else
									(CelWide 908 0 0)
								)
							)
							2
						)
						1
					)
					66
				)
			nsTop:
				(if (FileIO fileExists {CDAUDIO})
					(+
						(-
							(/
								(-
									200
									(if (FileIO fileExists {CDAUDIO})
										(CelHigh 1908 0 0)
									else
										(CelHigh 908 0 0)
									)
								)
								2
							)
							1
						)
						4
					)
				else
					(-
						(-
							(/
								(-
									200
									(if (FileIO fileExists {CDAUDIO})
										(CelHigh 1908 0 0)
									else
										(CelHigh 908 0 0)
									)
								)
								2
							)
							1
						)
						9
					)
				)
		)
		(super show: &rest)
	)
)

(instance volumeSlider of Slider
	(properties
		view 904
		loop 9
		cel 3
		signal FIXED_POSN
		noun N_VOLUME
		helpVerb V_HELP
		sliderView 904
		sliderLoop 1
		sliderCel 3
		yStep 2
		topValue 15
	)
	
	(method (show)
		(self
			view: (if (FileIO fileExists {CDAUDIO}) 1904 else 904)
			nsLeft:
				(+
					(-
						(/
							(-
								320
								(if (FileIO fileExists {CDAUDIO})
									(CelWide 1908 0 0)
								else
									(CelWide 908 0 0)
								)
							)
							2
						)
						1
					)
					127
				)
			nsTop:
				(if (FileIO fileExists {CDAUDIO})
					(+
						(-
							(/
								(-
									200
									(if (FileIO fileExists {CDAUDIO})
										(CelHigh 1908 0 0)
									else
										(CelHigh 908 0 0)
									)
								)
								2
							)
							1
						)
						4
					)
				else
					(-
						(-
							(/
								(-
									200
									(if (FileIO fileExists {CDAUDIO})
										(CelHigh 1908 0 0)
									else
										(CelHigh 908 0 0)
									)
								)
								2
							)
							1
						)
						9
					)
				)
		)
		(super show: &rest)
	)
)

(instance speedSlider of Slider
	(properties
		view 904
		loop 9
		cel 2
		signal FIXED_POSN
		noun N_SPEED
		helpVerb V_HELP
		sliderView 904
		sliderLoop 1
		sliderCel 2
		yStep 2
		bottomValue 15
	)
	
	(method (doit theSpeed)
		(if argc (ego setSpeed: theSpeed)
			(= speed theSpeed)
		)
		(ego moveSpeed?)
	)
	
	(method (show)
		(self
			view: (if (FileIO fileExists {CDAUDIO}) 1904 else 904)
			nsLeft:
				(+
					(-
						(/
							(-
								320
								(if (FileIO fileExists {CDAUDIO})
									(CelWide 1908 0 0)
								else
									(CelWide 908 0 0)
								)
							)
							2
						)
						1
					)
					97
				)
			nsTop:
				(if (FileIO fileExists {CDAUDIO})
					(+
						(-
							(/
								(-
									200
									(if (FileIO fileExists {CDAUDIO})
										(CelHigh 1908 0 0)
									else
										(CelHigh 908 0 0)
									)
								)
								2
							)
							1
						)
						4
					)
				else
					(-
						(-
							(/
								(-
									200
									(if (FileIO fileExists {CDAUDIO})
										(CelHigh 1908 0 0)
									else
										(CelHigh 908 0 0)
									)
								)
								2
							)
							1
						)
						9
					)
				)
		)
		(super show: &rest)
	)
)

(instance textSlider of Slider
	(properties
		view 904
		loop 9
		cel 0
		signal FIXED_POSN
		noun N_TEXT
		helpVerb V_HELP
		sliderView 904
		sliderLoop 1
		yStep 2
		bottomValue 15
	)
	
	(method (doit theTextSpeed)
		(if argc (= textSpeed theTextSpeed))
		(return textSpeed)
	)
	
	(method (show)
		(self
			view: (if (FileIO fileExists {CDAUDIO}) 1904 else 904)
			nsLeft:
				(+
					(-
						(/
							(-
								320
								(if (FileIO fileExists {CDAUDIO})
									(CelWide 1908 0 0)
								else
									(CelWide 908 0 0)
								)
							)
							2
						)
						1
					)
					35
				)
			nsTop:
				(if (FileIO fileExists {CDAUDIO})
					(+
						(-
							(/
								(-
									200
									(if (FileIO fileExists {CDAUDIO})
										(CelHigh 1908 0 0)
									else
										(CelHigh 908 0 0)
									)
								)
								2
							)
							1
						)
						4
					)
				else
					(-
						(-
							(/
								(-
									200
									(if (FileIO fileExists {CDAUDIO})
										(CelHigh 1908 0 0)
									else
										(CelHigh 908 0 0)
									)
								)
								2
							)
							1
						)
						9
					)
				)
		)
		(super show: &rest)
	)
)

(instance iconSave of TwistyControlIcon
	(properties
		view 904
		loop 2
		cel 0
		message 8
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_SAVE
		helpVerb V_HELP
	)
	
	(method (show)
		(self
			nsLeft:
				(-
					(-
						(/
							(-
								320
								(if (FileIO fileExists {CDAUDIO})
									(CelWide 1908 0 0)
								else
									(CelWide 908 0 0)
								)
							)
							2
						)
						1
					)
					27
				)
			nsTop:
				(if (FileIO fileExists {CDAUDIO})
					(-
						(-
							(/
								(-
									200
									(if (FileIO fileExists {CDAUDIO})
										(CelHigh 1908 0 0)
									else
										(CelHigh 908 0 0)
									)
								)
								2
							)
							1
						)
						13
					)
				else
					(-
						(-
							(/
								(-
									200
									(if (FileIO fileExists {CDAUDIO})
										(CelHigh 1908 0 0)
									else
										(CelHigh 908 0 0)
									)
								)
								2
							)
							1
						)
						23
					)
				)
		)
		(super show: &rest)
	)
)

(instance iconRestore of TwistyControlIcon
	(properties
		view 904
		loop 3
		cel 0
		message 8
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_RESTORE
		helpVerb V_HELP
	)
	
	(method (show)
		(self
			nsLeft:
				(-
					(-
						(/
							(-
								320
								(if (FileIO fileExists {CDAUDIO})
									(CelWide 1908 0 0)
								else
									(CelWide 908 0 0)
								)
							)
							2
						)
						1
					)
					27
				)
			nsTop:
				(+
					(if (FileIO fileExists {CDAUDIO})
						(-
							(-
								(/
									(-
										200
										(if (FileIO fileExists {CDAUDIO})
											(CelHigh 1908 0 0)
										else
											(CelHigh 908 0 0)
										)
									)
									2
								)
								1
							)
							13
						)
					else
						(-
							(-
								(/
									(-
										200
										(if (FileIO fileExists {CDAUDIO})
											(CelHigh 1908 0 0)
										else
											(CelHigh 908 0 0)
										)
									)
									2
								)
								1
							)
							23
						)
					)
					19
				)
		)
		(super show: &rest)
	)
)

(instance iconRestart of TwistyControlIcon
	(properties
		view 904
		loop 4
		cel 0
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_RESTART
		helpVerb V_HELP
	)
	
	(method (show)
		(self
			nsLeft:
				(-
					(-
						(/
							(-
								320
								(if (FileIO fileExists {CDAUDIO})
									(CelWide 1908 0 0)
								else
									(CelWide 908 0 0)
								)
							)
							2
						)
						1
					)
					27
				)
			nsTop:
				(+
					(if (FileIO fileExists {CDAUDIO})
						(-
							(-
								(/
									(-
										200
										(if (FileIO fileExists {CDAUDIO})
											(CelHigh 1908 0 0)
										else
											(CelHigh 908 0 0)
										)
									)
									2
								)
								1
							)
							13
						)
					else
						(-
							(-
								(/
									(-
										200
										(if (FileIO fileExists {CDAUDIO})
											(CelHigh 1908 0 0)
										else
											(CelHigh 908 0 0)
										)
									)
									2
								)
								1
							)
							23
						)
					)
					38
				)
		)
		(super show: &rest)
	)
)

(instance iconQuit of TwistyControlIcon
	(properties
		view 904
		loop 5
		cel 0
		message 8
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_QUIT
		helpVerb V_HELP
	)
	
	(method (show)
		(self
			nsLeft:
				(-
					(-
						(/
							(-
								320
								(if (FileIO fileExists {CDAUDIO})
									(CelWide 1908 0 0)
								else
									(CelWide 908 0 0)
								)
							)
							2
						)
						1
					)
					27
				)
			nsTop:
				(+
					(if (FileIO fileExists {CDAUDIO})
						(-
							(-
								(/
									(-
										200
										(if (FileIO fileExists {CDAUDIO})
											(CelHigh 1908 0 0)
										else
											(CelHigh 908 0 0)
										)
									)
									2
								)
								1
							)
							13
						)
					else
						(-
							(-
								(/
									(-
										200
										(if (FileIO fileExists {CDAUDIO})
											(CelHigh 1908 0 0)
										else
											(CelHigh 908 0 0)
										)
									)
									2
								)
								1
							)
							23
						)
					)
					57
				)
		)
		(super show: &rest)
	)
)

(instance iconAbout of TwistyControlIcon
	(properties
		view 904
		loop 6
		cel 0
		message 8
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_ABOUT
		helpVerb V_HELP
	)
	
	(method (show)
		(self
			nsLeft:
				(-
					(-
						(/
							(-
								320
								(if (FileIO fileExists {CDAUDIO})
									(CelWide 1908 0 0)
								else
									(CelWide 908 0 0)
								)
							)
							2
						)
						1
					)
					27
				)
			nsTop:
				(+
					(if (FileIO fileExists {CDAUDIO})
						(-
							(-
								(/
									(-
										200
										(if (FileIO fileExists {CDAUDIO})
											(CelHigh 1908 0 0)
										else
											(CelHigh 908 0 0)
										)
									)
									2
								)
								1
							)
							13
						)
					else
						(-
							(-
								(/
									(-
										200
										(if (FileIO fileExists {CDAUDIO})
											(CelHigh 1908 0 0)
										else
											(CelHigh 908 0 0)
										)
									)
									2
								)
								1
							)
							23
						)
					)
					76
				)
		)
		(super show: &rest)
	)
	
	(method (select)
		(if (super select: &rest) ((ScriptID 884 0) doit:))
		(return 1)
	)
)

(instance iconHelp of TwistyControlIcon
	(properties
		view 904
		loop 7
		cel 0
		cursor 990
		message V_HELP
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_HELP_CONTROL
		helpVerb V_HELP
	)
	
	(method (show)
		(self
			nsLeft:
				(+
					(-
						(-
							(/
								(-
									320
									(if (FileIO fileExists {CDAUDIO})
										(CelWide 1908 0 0)
									else
										(CelWide 908 0 0)
									)
								)
								2
							)
							1
						)
						27
					)
					(CelWide 904 6 0)
					2
				)
			nsTop:
				(+
					(if (FileIO fileExists {CDAUDIO})
						(-
							(-
								(/
									(-
										200
										(if (FileIO fileExists {CDAUDIO})
											(CelHigh 1908 0 0)
										else
											(CelHigh 908 0 0)
										)
									)
									2
								)
								1
							)
							13
						)
					else
						(-
							(-
								(/
									(-
										200
										(if (FileIO fileExists {CDAUDIO})
											(CelHigh 1908 0 0)
										else
											(CelHigh 908 0 0)
										)
									)
									2
								)
								1
							)
							23
						)
					)
					76
				)
		)
		(super show: &rest)
	)
)

(instance iconOk of TwistyControlIcon
	(properties
		view 904
		loop 8
		cel 0
		message 8
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		noun N_MODE
		helpVerb V_HELP
	)
	
	(method (show)
		(self
			nsLeft:
				(-
					(-
						(/
							(-
								320
								(if (FileIO fileExists {CDAUDIO})
									(CelWide 1908 0 0)
								else
									(CelWide 908 0 0)
								)
							)
							2
						)
						1
					)
					27
				)
			nsTop:
				(+
					(if (FileIO fileExists {CDAUDIO})
						(-
							(-
								(/
									(-
										200
										(if (FileIO fileExists {CDAUDIO})
											(CelHigh 1908 0 0)
										else
											(CelHigh 908 0 0)
										)
									)
									2
								)
								1
							)
							13
						)
					else
						(-
							(-
								(/
									(-
										200
										(if (FileIO fileExists {CDAUDIO})
											(CelHigh 1908 0 0)
										else
											(CelHigh 908 0 0)
										)
									)
									2
								)
								1
							)
							23
						)
					)
					95
				)
		)
		(super show: &rest)
	)
)

(instance iconMode of TwistyControlIcon
	(properties
		view 1904
		loop 11
		cel 0
		message 8
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_MODE
		helpVerb V_HELP
	)
	
	(method (show)
		(self
			nsLeft:
				(-
					(-
						(/
							(-
								320
								(if (FileIO fileExists {CDAUDIO})
									(CelWide 1908 0 0)
								else
									(CelWide 908 0 0)
								)
							)
							2
						)
						1
					)
					27
				)
			nsTop:
				(+
					(if (FileIO fileExists {CDAUDIO})
						(-
							(-
								(/
									(-
										200
										(if (FileIO fileExists {CDAUDIO})
											(CelHigh 1908 0 0)
										else
											(CelHigh 908 0 0)
										)
									)
									2
								)
								1
							)
							13
						)
					else
						(-
							(-
								(/
									(-
										200
										(if (FileIO fileExists {CDAUDIO})
											(CelHigh 1908 0 0)
										else
											(CelHigh 908 0 0)
										)
									)
									2
								)
								1
							)
							23
						)
					)
					114
					1
				)
		)
		(super show: &rest)
	)
	
	(method (select &tmp temp0)
		(super select: &rest)
		(self showMode:)
		(DrawCel view loop 3 nsLeft nsTop 15)
	)
)
