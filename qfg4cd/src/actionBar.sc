;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include sci.sh)
(use Main)
(use Print)
(use IconBar)
(use System)

(public
	actionBar 0
)

(local
	local0
	theNsLeft
	local2
	selectedJump
	local4
)
(instance actionBar of IconBar
	(properties)
	
	(method (init &tmp [temp0 6])
		(self add: iconRun iconSneak iconRest iconSheet iconTime)
		(if (& disabledActions $0001)
			(iconRun signal: (| (iconRun signal?) $0004))
		)
		(if (& disabledActions $0002)
			(iconSneak signal: (| (iconSneak signal?) $0004))
		)
		(if (& disabledActions $0004)
			(iconRest signal: (| (iconRest signal?) $0004))
		)
		(if [egoStats 15]
			(self add: iconJump)
			(= theNsLeft 193)
		else
			(= theNsLeft 164)
		)
		(= local2 (+ theNsLeft 29))
		(self
			add: iconActionHelp
			eachElementDo: #highlightColor -1
			eachElementDo: #lowlightColor -1
			eachElementDo: #y 13
			curIcon: iconSheet
			helpIconItem: iconActionHelp
			state: 3072
		)
		(if (not plane) (= plane (systemPlane new:)))
		(plane
			back: 0
			priority: -1
			init: 0 0 210 100
			addCast: self
		)
		(self eachElementDo: #init self)
		(plane
			picture: -2
			setSize:
			setRect:
				(- (plane left:) 10)
				(- (plane top?) 10)
				(+ (plane right:) 10)
				(+ (plane bottom?) 10)
			setBitmap: 931 (if [egoStats 15] 12 else 13) 0
			setSize:
			posn: -1 -1
		)
		(= selectedJump 0)
	)
	
	(method (hide)
		(super hide:)
		(FrameOut)
		(if (not selectedJump) (RestoreTheCursor))
		(plane deleteCast: self dispose:)
		(= plane 0)
		(if elements (DisposeList elements))
		(= size (= elements 0))
	)
	
	(method (dispatchEvent event)
		(return
			(cond 
				((== (event type?) evMOUSEBUTTON)
					(if (not (self firstTrue: #onMe event))
						(self hide:)
						(return 1)
					else
						(super dispatchEvent: event)
					)
				)
				(
					(and
						(== (event type?) evKEYBOARD)
						(== (event message?) KEY_ESCAPE)
					)
					(self hide:)
					(return 1)
				)
				(else (super dispatchEvent: event))
			)
		)
	)
	
	(method (noClickHelp &tmp temp0 temp1 temp2 printDialog)
		(theGame
			setCursor: ((theIconBar getCursor:) view: 949 loop: 0 cel: 0)
		)
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
						(Print
							font: userFont
							width: 250
							y: 150
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
		(theGame setCursor: normalCursor 1)
		(if printDialog
			(printDialog dispose:)
			(= printDialog 0)
			(FrameOut)
		)
		(if
		(and helpIconItem (not (helpIconItem onMe: temp0)))
			(self dispatchEvent: temp0)
		)
	)
)

(instance iconRun of IconI
	(properties
		noun 1
		modNum 20
		nsLeft 19
		nsTop 13
		x 16
		priority 1
		fixPriority 1
		signal $01c3
		message 3
		mainView 931
		maskView 931
		maskLoop 11
		helpVerb 9
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(actionBar hide:)
				(theIconBar curIcon: (theIconBar at: 0))
				(ego changeGait: 1 1)
				(return 1)
			else
				(return 0)
			)
		)
	)
	
	(method (highlight theCel)
		(= cel theCel)
		(UpdateScreenItem self)
	)
)

(instance iconSneak of IconI
	(properties
		noun 2
		modNum 20
		nsLeft 48
		nsTop 13
		priority 1
		fixPriority 1
		signal $01c3
		message 3
		mainView 931
		mainLoop 1
		maskView 931
		maskLoop 11
		helpVerb 9
	)
	
	(method (init)
		(= x
			(+
				(iconRun x?)
				(CelWide
					(iconRun view?)
					(iconRun loop?)
					(iconRun cel?)
				)
			)
		)
		(super init: &rest)
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(actionBar hide:)
				(if (!= egoGait 2)
					(if (ego trySkill: 8 5 0)
						(theIconBar curIcon: (theIconBar at: 0))
						(ego changeGait: 2 1)
					else
						(messager say: 7 6 1 0 0 20)
					)
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
	
	(method (highlight theCel)
		(= cel theCel)
		(UpdateScreenItem self)
	)
)

(instance iconRest of IconI
	(properties
		noun 3
		modNum 20
		nsLeft 77
		nsTop 13
		priority 1
		fixPriority 1
		signal $01c3
		message 104
		mainView 931
		mainLoop 3
		maskView 931
		maskLoop 11
		helpVerb 9
	)
	
	(method (init)
		(= x
			(+
				(iconSneak x?)
				(CelWide
					(iconSneak view?)
					(iconSneak loop?)
					(iconSneak cel?)
				)
			)
		)
		(super init: &rest)
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(actionBar hide:)
				(if global365
					(messager say: 7 6 6 0 0 20)
				else
					((ScriptID 7 2) init:)
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
	
	(method (highlight theCel)
		(= cel theCel)
		(UpdateScreenItem self)
	)
)

(instance iconSheet of IconI
	(properties
		noun 4
		modNum 20
		nsLeft 106
		nsTop 13
		priority 1
		fixPriority 1
		signal $01c3
		mainView 931
		mainLoop 5
		maskView 931
		maskLoop 11
		helpVerb 9
	)
	
	(method (init)
		(= x
			(+
				(iconRest x?)
				(CelWide
					(iconRest view?)
					(iconRest loop?)
					(iconRest cel?)
				)
			)
		)
		(super init: &rest)
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(actionBar hide:)
				(if (not isHandsOff)
					(= local0 1)
					((ScriptID 15) doit:)
					(DisposeScript 15)
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
	
	(method (highlight theCel)
		(= cel theCel)
		(UpdateScreenItem self)
	)
)

(instance iconTime of IconI
	(properties
		noun 5
		modNum 20
		nsLeft 135
		nsTop 13
		priority 1
		fixPriority 1
		signal $01c3
		mainView 931
		mainLoop 6
		maskView 931
		maskLoop 11
		helpVerb 9
	)
	
	(method (init)
		(= x
			(+
				(iconSheet x?)
				(CelWide
					(iconSheet view?)
					(iconSheet loop?)
					(iconSheet cel?)
				)
			)
		)
		(super init: &rest)
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(actionBar hide:)
				(if (OneOf curRoomNum 710 720 730 740 750 760 770)
					(messager say: 7 6 3 0 0 20)
				else
					((ScriptID 7 3) init:)
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
	
	(method (highlight theCel)
		(= cel theCel)
		(UpdateScreenItem self)
	)
)

(instance iconJump of IconI
	(properties
		noun 8
		modNum 20
		nsLeft 164
		nsTop 13
		priority 1
		fixPriority 1
		signal $01c3
		mainView 931
		mainLoop 10
		maskView 931
		maskLoop 11
		cursorView 998
		cursorLoop 0
		cursorCel 0
		helpVerb 9
	)
	
	(method (init)
		(= x
			(+
				(iconTime x?)
				(CelWide
					(iconTime view?)
					(iconTime loop?)
					(iconTime cel?)
				)
			)
		)
		(super init: &rest)
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(= selectedJump 1)
				((theIconBar at: 5)
					cursorView: 998
					cursorLoop: 0
					cursorCel: 0
					message: 10
				)
				(theIconBar curIcon: (theIconBar at: 5))
				(actionBar hide:)
				(return 1)
			else
				0
			)
		)
	)
	
	(method (highlight theCel)
		(= cel theCel)
		(UpdateScreenItem self)
	)
)

(instance iconActionHelp of IconI
	(properties
		noun 6
		modNum 20
		nsTop 13
		priority 1
		fixPriority 1
		signal $0083
		message 9
		mainView 931
		mainLoop 8
		cursorView 949
		cursorLoop 0
		cursorCel 0
		helpVerb 9
	)
	
	(method (init)
		(if [egoStats 15]
			(= x
				(+
					(iconJump x?)
					(CelWide
						(iconJump mainView?)
						(iconJump mainLoop?)
						(iconJump mainCel?)
					)
				)
			)
		else
			(= x
				(+
					(iconTime x?)
					(CelWide
						(iconTime mainView?)
						(iconTime mainLoop?)
						(iconTime mainCel?)
					)
				)
			)
		)
		(super init: &rest)
	)
	
	(method (show)
		(= nsLeft theNsLeft)
		(super show: &rest)
	)
	
	(method (select)
		(if (super select: &rest) (actionBar noClickHelp:))
		(return 0)
	)
	
	(method (highlight theCel)
		(= cel theCel)
		(UpdateScreenItem self)
	)
)
