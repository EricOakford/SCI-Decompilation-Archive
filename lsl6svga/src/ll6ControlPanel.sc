;;; Sierra Script 1.0 - (do not remove this comment)
(script# 94)
(include sci.sh)
(use Main)
(use Dial)
(use Array)
(use Print)
(use IconBar)
(use GControl)
(use System)

(public
	ll6ControlPanel 0
)

(instance detailDial of Dial
	(properties
		noun 9
		modNum 94
		x 30
		y 2
		mainView 969
		helpVerb 14
		points 14
	)
	
	(method (update param1)
		(return
			(if argc
				(= global193 param1)
				(theGame
					detailLevel: (if param1 (+ (/ (* param1 10) 28) 1) else 1)
				)
			else
				(return global193)
			)
		)
	)
)

(instance volumeDial of Dial
	(properties
		noun 8
		modNum 94
		x 30
		y 12
		mainView 969
		helpVerb 14
		points 14
	)
	
	(method (update param1)
		(return
			(if argc
				(= global194 param1)
				(theGame
					masterVolume: (if param1 (/ (* 1155 param1) 1000) else 0)
				)
			else
				(return global194)
			)
		)
	)
)

(instance speedDial of Dial
	(properties
		noun 10
		modNum 94
		x 30
		y 22
		mainView 969
		helpVerb 14
		points 14
	)
	
	(method (update param1)
		(return
			(if argc
				(ego setSpeed: (= gGEgoCycleSpeed_2 (- 14 param1)))
			else
				(return (- 14 gGEgoCycleSpeed_2))
			)
		)
	)
)

(instance textDial of Dial
	(properties
		noun 11
		modNum 94
		x 30
		y 32
		mainView 969
		helpVerb 14
		points 14
	)
	
	(method (select &tmp newEvent temp1)
		(while (!= ((= newEvent (Event new:)) type?) 2)
			(newEvent localize: plane)
			(cond 
				(
				(> curPos (= temp1 (self findClosestPoint: newEvent))) (self decrement:))
				((< curPos temp1) (self increment:))
			)
			(newEvent dispose:)
		)
		(newEvent dispose:)
		(while (!= cel 13)
			(self increment:)
		)
		(return curPos)
	)
	
	(method (update param1)
		(= param1 14)
	)
)

(instance question of ControlIcon
	(properties
		noun 4
		modNum 94
		x 46
		y 3
		signal $0181
		mainView 969
		mainLoop 2
		cursorView 998
		cursorLoop 0
		cursorCel 6
		helpVerb 14
	)
)

(instance ok of ControlIcon
	(properties
		noun 7
		modNum 94
		x 46
		y 19
		signal $01c1
		mainView 969
		mainLoop 3
		helpVerb 14
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(theGame panelObj: 0 panelSelector: 0)
				(= gameControls 0)
				1
			else
				0
			)
		)
	)
)

(instance ll6ControlPanel of GameControls
	(properties
		state $0800
	)
	
	(method (init &tmp temp0)
		(= gameControls self)
		(super init: &rest)
		(plane
			setRect:
				130
				54
				(+ 129 (CelWide 969 1 0) (CelWide 969 2))
				(+ 52 (CelHigh 969 1 0))
		)
		(= temp0
			(IntArray
				with: 3 11 1 9 0 7 0 5 0 2 1 0 3 0 5 0 8 0 9 0 11 3 11 5 11 8 10 9
			)
		)
		(self
			add:
				(detailDial locations: temp0 yourself:)
				(volumeDial locations: temp0 yourself:)
				(speedDial locations: temp0 yourself:)
				(textDial locations: temp0 yourself:)
				ok
				(question theObj: question selector: 69 yourself:)
			eachElementDo: #highlightColor -1
			helpIconItem: question
			curIcon: ok
		)
		((IconI new:) mainView: 969 mainLoop: 1 init: self)
		(self show:)
		(temp0 dispose:)
		(DisposeScript 94)
	)
	
	(method (dispose)
		(Print modeless: 0)
		(super dispose: &rest)
	)
	
	(method (noClickHelp &tmp temp0 temp1 temp2 printDialog)
		(= temp1 (= temp2 (= printDialog 0)))
		(while
		(not ((= temp0 ((user curEvent?) new:)) type?))
			(temp0 localize: plane)
			(cond 
				((= temp2 (self firstTrue: #onMe temp0))
					(if (and (!= temp2 temp1) (temp2 helpVerb?))
						(= temp1 temp2)
						(if printDialog
							(printDialog dispose:)
							(= printDialog 0)
							(FrameOut)
						)
						(Message 0 94 0 0 1 1 (global186 data?))
						(Print
							addTitle: (global186 data?)
							font: userFont
							width: 250
							addText:
								(temp2 noun?)
								(temp2 helpVerb?)
								0
								1
								0
								0
								(if (== (temp2 modNum?) -1) 0 else (temp2 modNum?))
							modeless: 2
							init:
						)
						(= printDialog (Print dialog?))
						(FrameOut)
					)
				)
				(printDialog (printDialog dispose:) (= printDialog 0) (FrameOut))
				(else (= temp1 0))
			)
			(temp0 dispose:)
		)
		(temp0 dispose:)
		(theGame setCursor: normalCursor 1)
		(if printDialog (printDialog dispose:) (FrameOut))
	)
)

(instance cpanelHelpScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Message 0 94 0 0 1 1 (global186 data?))
				(Print
					addTitle: (global186 data?)
					addText: 31 14 0 1 0 0 94
					init: self
				)
			)
			(1
				(ll6ControlPanel noClickHelp: 1)
				(Print title: 0)
				(self dispose:)
			)
		)
	)
)
