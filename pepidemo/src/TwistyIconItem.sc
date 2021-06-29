;;; Sierra Script 1.0 - (do not remove this comment)
(script# 883)
(include game.sh) (include "0.shm")
(use Main)
(use Print)
(use IconBar)
(use System)

(public
	InitIconBars 0
	pepIcon5 1
	pepperIconBar 2
	dogIconBar 3
	dogIcon6 4
	SetIconBar 5
	proc883_6 6
	iconBarCursor 7
)

(procedure (InitIconBars)
	(pepperIconBar init:)
	(dogIconBar init:)
	(SetIconBar)
)

(procedure (SetIconBar)
	(if
		(==
			(= theIconBar
				(if (== ego (ScriptID 895 0))
					pepperIconBar
				else
					dogIconBar
				)
			)
			pepperIconBar
		)
		(= gPepIcon5 pepIcon5)
		(theIconBar curIcon: pepIcon0)
	else
		(= gPepIcon5 dogIcon6)
		(theIconBar curIcon: dogIcon0)
	)
	(iconBarCursor init:)
)

(procedure (proc883_6 param1)
	(if param1
		(theIconBar
			delete: pepIcon0
			addToFront: (pepIcon10 init: yourself:)
		)
		(theIconBar
			curIcon: pepIcon10
			walkIconItem: 0
			enable: 0
			disable: 6 4
		)
		(Bset 2)
		(theGame setCursor: (pepIcon10 cursor?))
	else
		(theIconBar
			delete: pepIcon10
			addToFront: (pepIcon0 init: yourself:)
		)
		(theIconBar
			curIcon: pepIcon0
			walkIconItem: pepIcon0
			enable: 6 4
		)
		(Bclr fCantSave)
		(theGame setCursor: iconBarCursor)
	)
)

(class TwistyIconItem of IconItem
	(properties
		cel 0
		cursor 0
		maskCel 3
		ulXoff 0
		ulYoff 0
		brXoff 0
		brYoff 0
		lowlightColor2 0
		cursorCel 0
	)
	
	(method (init)
		(if (not cursor)
			(= cursor iconBarCursor)
		)
		(super init: &rest)
	)
	
	(method (highlight tOrF &tmp t l b r temp4)
		(if (or (not (& signal IB_ACTIVE)) (== highlightColor -1))
			(return)
		)
		(= t (+ nsTop ulYoff))
		(= l (+ nsLeft ulXoff))
		(= b (+ nsTop brYoff))
		(= r (+ nsLeft brXoff))
		(if (and argc tOrF)
			(Graph GDrawLine t l t r highlightColor -1 -1)
			(Graph GDrawLine t r b r highlightColor -1 -1)
			(Graph GDrawLine b r b l highlightColor -1 -1)
			(Graph GDrawLine b l t l highlightColor -1 -1)
		else
			(Graph GDrawLine t l t r lowlightColor -1 -1)
			(Graph GDrawLine t r b r lowlightColor -1 -1)
			(Graph GDrawLine b r b l lowlightColor2 -1 -1)
			(Graph GDrawLine b l t l lowlightColor2 -1 -1)
		)
		(Graph GShowBits (- nsTop 2) (- nsLeft 2) nsBottom (+ nsRight 3) VMAP)
	)
)

(instance iconBarCursor of Cursor
	(properties
		view 903
	)
	
	(method (init)
		(= loop (if (== theIconBar pepperIconBar) 0 else 1))
		(if (IsObject (theIconBar curIcon?))
			(= cel ((theIconBar curIcon?) cursorCel?))
		)
		(super init: &rest)
	)
)

(instance pepperIconBar of IconBar

	(method (init)
		(self
			add:
				pepIcon0
				pepIcon1
				pepIcon2
				pepIcon3
				pepIcon4
				pepIcon5
				pepIcon6
				pepIcon7
				pepIcon8
				pepIcon9
			eachElementDo: #init
			eachElementDo: #highlightColor myHighlightColor
			eachElementDo: #lowlightColor 45
			eachElementDo: #lowlightColor2 47
			eachElementDo: #view 900
			eachElementDo: #maskView 900
			curIcon: pepIcon0
			useIconItem: pepIcon5
			helpIconItem: pepIcon7
			walkIconItem: pepIcon0
			disable: 5
			activateHeight: -1
			state: (| OPENIFONME NOCLICKHELP)
			y: 5
		)
		(pepIcon4 message: (if (HaveMouse) SHIFTTAB else TAB))
	)
)

(instance pepIcon0 of TwistyIconItem
	(properties
		loop 0
		type (| userEvent walkEvent)
		message V_WALK
		signal (| HIDEBAR RELVERIFY)
		noun N_PEP_WALK
		helpVerb V_HELP
		ulXoff 11
		ulYoff 11
		brXoff 38
		brYoff 32
	)
)

(instance pepIcon1 of TwistyIconItem
	(properties
		loop 1
		message V_LOOK
		signal (| HIDEBAR RELVERIFY)
		maskLoop 1
		noun N_PEP_LOOK
		helpVerb V_HELP
		ulXoff 2
		ulYoff 11
		brXoff 29
		brYoff 32
		cursorCel 1
	)
)

(instance pepIcon2 of TwistyIconItem
	(properties
		loop 2
		message V_DO
		signal (| HIDEBAR RELVERIFY)
		maskLoop 2
		noun N_DO
		helpVerb V_HELP
		ulXoff 2
		ulYoff 11
		brXoff 29
		brYoff 32
		cursorCel 2
	)
)

(instance pepIcon3 of TwistyIconItem
	(properties
		loop 3
		message V_TALK
		signal (| HIDEBAR RELVERIFY)
		maskLoop 3
		noun N_PEP_TALK
		helpVerb V_HELP
		ulXoff 2
		ulYoff 11
		brXoff 29
		brYoff 32
		cursorCel 3
	)
)

(instance pepIcon4 of TwistyIconItem
	(properties
		loop 4
		cursor ARROW_CURSOR
		type nullEvt
		message NULL
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskLoop 4
		noun N_PEP_INVENTORY
		helpVerb V_HELP
		ulXoff 2
		ulYoff 11
		brXoff 29
		brYoff 32
	)
	
	(method (select)
		(if (super select: &rest)
			(theIconBar hide:)
			(inventory showSelf: ego)
		)
		(return TRUE)
	)
)

(instance pepIcon5 of TwistyIconItem
	(properties
		loop 5
		cursor 992
		signal (| HIDEBAR RELVERIFY)
		maskLoop 5
		noun N_PEP_ITEM
		helpVerb V_HELP
		ulXoff 2
		ulYoff 11
		brXoff 34
		brYoff 32
	)
	
	(method (select relVer &tmp event whichCel thisIcon theWidth theHeight [temp5 30])
		(return
			(cond 
				((& signal DISABLED) FALSE)
				((and argc relVer (& signal RELVERIFY))
					(if (= thisIcon (theIconBar curInvIcon?))
						(= theWidth
							(+
								(/
									(-
										(- nsRight nsLeft)
										(CelWide
											(thisIcon view?)
											(thisIcon loop?)
											(thisIcon cel?)
										)
									)
									2
								)
								nsLeft
							)
						)
						(= theHeight
							(+
								(theIconBar y?)
								(/
									(-
										(- nsBottom nsTop)
										(CelHigh
											(thisIcon view?)
											(thisIcon loop?)
											(thisIcon cel?)
										)
									)
									2
								)
								nsTop
							)
						)
					)
					(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
					(if (= thisIcon (theIconBar curInvIcon?))
						(DrawCel
							(thisIcon view?)
							(thisIcon loop?)
							(thisIcon cel?)
							theWidth
							theHeight
							-1
						)
					)
					(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
					(while (!= ((= event (Event new:)) type?) mouseUp)
						(event localize:)
						(cond 
							((self onMe: event)
								(if (not whichCel)
									(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
									(if (= thisIcon (theIconBar curInvIcon?))
										(DrawCel
											(thisIcon view?)
											(thisIcon loop?)
											(thisIcon cel?)
											theWidth
											theHeight
											-1
										)
									)
									(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
								)
							)
							(whichCel
								(DrawCel view loop (= whichCel 0) nsLeft nsTop -1)
								(if (= thisIcon (theIconBar curInvIcon?))
									(DrawCel
										(thisIcon view?)
										(thisIcon loop?)
										(thisIcon cel?)
										theWidth
										theHeight
										-1
									)
								)
								(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
							)
						)
						(event dispose:)
					)
					(event dispose:)
					(if (== whichCel 1)
						(DrawCel view loop 0 nsLeft nsTop -1)
						(if (= thisIcon (theIconBar curInvIcon?))
							(DrawCel
								(thisIcon view?)
								(thisIcon loop?)
								(thisIcon cel?)
								theWidth
								theHeight
								-1
							)
						)
						(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
					)
					whichCel
				)
				(else TRUE)
			)
		)
	)
)

(instance pepIcon6 of TwistyIconItem
	(properties
		loop 6
		cursor ARROW_CURSOR
		message V_GAME
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskLoop 6
		noun N_PEP_CONTROLS
		helpVerb V_HELP
		ulXoff 2
		ulYoff 11
		brXoff 29
		brYoff 32
	)
	
	(method (select)
		(if (super select: &rest)
			(theIconBar hide:)
			((ScriptID 888 0) show:)
		)
		(return 1)
	)
)

(instance pepIcon7 of TwistyIconItem
	(properties
		loop 7
		cursor 990
		message V_HELP
		signal (| RELVERIFY IMMEDIATE)
		maskLoop 7
		noun N_PEP_HELP
		helpVerb V_HELP
		ulXoff 2
		ulYoff 11
		brXoff 29
		brYoff 32
	)
)

(instance pepIcon8 of TwistyIconItem
	(properties
		loop 8
		message V_TRIVIA
		signal (| HIDEBAR RELVERIFY)
		maskLoop 8
		noun N_PEP_TRIVIA
		helpVerb V_HELP
		ulXoff 2
		ulYoff 11
		brXoff 29
		brYoff 32
		cursorCel 5
	)
)

(instance pepIcon9 of TwistyIconItem
	(properties
		loop 9
		cursor 999
		message V_TRIVIA
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskLoop 9
		noun N_PEP_REVIEW
		helpVerb V_HELP
		ulXoff 2
		ulYoff 11
		brXoff 34
		brYoff 32
	)
	
	(method (select &tmp theNoun)
		(if (super select: &rest)
			(ego setMotion: 0)
			(theIconBar hide:)
			(= theNoun 7)
			(narrator keepWindow: 1)
			(if (== gameAct 0)
				(Prints {Setting your act to 1, cause you teleported!!})
				(= gameAct 1)
			)
			(messager say: theNoun NULL gameAct 2 0 110)
		)
		(return TRUE)
	)
)

(instance pepIcon10 of TwistyIconItem
	(properties
		view 900
		loop 10
		message V_EXIT
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 900
		maskLoop 10
		highlightColor 126
		lowlightColor 45
		noun N_EXIT
		helpVerb V_HELP
		ulXoff 11
		ulYoff 11
		brXoff 38
		brYoff 32
		lowlightColor2 47
		cursorCel 6
	)
	
	(method (doit &tmp event)
		((= event ((user curEvent?) new:))
			type: type
			message: message
		)
		(user handleEvent: event)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance dogIconBar of IconBar

	(method (init)
		(self
			add:
				dogIcon0
				dogIcon1
				dogIcon2
				dogIcon3
				dogIcon4
				dogIcon5
				dogIcon6
				dogIcon7
				dogIcon8
				dogIcon9
				dogIcon10
			eachElementDo: #init
			eachElementDo: #highlightColor myHighlightColor
			eachElementDo: #view 902
			eachElementDo: #maskView 902
			curIcon: dogIcon0
			useIconItem: dogIcon6
			helpIconItem: dogIcon8
			walkIconItem: dogIcon0
			disable: 6
			activateHeight: -1
			state: (| OPENIFONME NOCLICKHELP)
			y: 10
		)
		(dogIcon5 message: (if (HaveMouse) 3840 else 9))
	)
)

(instance dogIcon0 of TwistyIconItem
	(properties
		loop 0
		type $5000
		message V_WALK
		signal (| HIDEBAR RELVERIFY)
		lowlightColor 46
		noun N_DOG_WALK
		helpVerb V_HELP
		ulXoff 13
		ulYoff 8
		brXoff 36
		brYoff 27
		lowlightColor2 22
	)
)

(instance dogIcon1 of TwistyIconItem
	(properties
		loop 1
		message V_LOOK
		signal (| HIDEBAR RELVERIFY)
		maskLoop 1
		lowlightColor 46
		noun N_DOG_LOOK
		helpVerb V_HELP
		ulXoff 2
		ulYoff 8
		brXoff 25
		brYoff 27
		lowlightColor2 22
		cursorCel 1
	)
)

(instance dogIcon2 of TwistyIconItem
	(properties
		loop 2
		message V_PAW
		signal (| HIDEBAR RELVERIFY)
		maskLoop 2
		lowlightColor 46
		noun N_PAW
		helpVerb V_HELP
		ulXoff 2
		ulYoff 8
		brXoff 21
		brYoff 27
		lowlightColor2 22
		cursorCel 2
	)
)

(instance dogIcon3 of TwistyIconItem
	(properties
		loop 3
		message V_NOSE
		signal (| HIDEBAR RELVERIFY)
		maskLoop 3
		lowlightColor 46
		noun N_NOSE
		helpVerb V_HELP
		ulXoff 2
		ulYoff 8
		brXoff 26
		brYoff 27
		lowlightColor2 22
		cursorCel 3
	)
)

(instance dogIcon4 of TwistyIconItem
	(properties
		loop 4
		message V_TEETH
		signal (| HIDEBAR RELVERIFY)
		maskLoop 4
		lowlightColor 46
		noun N_TEETH
		helpVerb V_HELP
		ulXoff 2
		ulYoff 8
		brXoff 24
		brYoff 27
		lowlightColor2 22
		cursorCel 4
	)
)

(instance dogIcon5 of TwistyIconItem
	(properties
		loop 5
		cursor 999
		type nullEvt
		message NULL
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskLoop 5
		lowlightColor 46
		noun N_DOG_INVENTORY
		helpVerb V_HELP
		ulXoff 2
		ulYoff 8
		brXoff 25
		brYoff 27
		lowlightColor2 22
	)
	
	(method (init)
		(= lowlightColor myLowlightColor)
		(super init:)
	)
	
	(method (select)
		(if (super select: &rest)
			(theIconBar hide:)
			(inventory showSelf: ego)
		)
		(return TRUE)
	)
)

(instance dogIcon6 of TwistyIconItem
	(properties
		loop 6
		cursor ARROW_CURSOR
		signal (| HIDEBAR RELVERIFY)
		maskLoop 6
		lowlightColor 46
		noun N_DOG_ITEM
		helpVerb V_HELP
		ulXoff 2
		ulYoff 8
		brXoff 32
		brYoff 27
		lowlightColor2 22
	)
	
	(method (select relVer &tmp event whichCel thisIcon theWidth theHeight)
		(return
			(cond 
				((& signal DISABLED) FALSE)
				((and argc relVer (& signal RELVERIFY))
					(if
					(= thisIcon (theIconBar curInvIcon?))
						(= theWidth
							(+
								(/
									(-
										(- nsRight nsLeft)
										(CelWide
											(thisIcon view?)
											(thisIcon loop?)
											(thisIcon cel?)
										)
									)
									2
								)
								nsLeft
							)
						)
						(= theHeight
							(+
								(theIconBar y?)
								(/
									(-
										(- nsBottom nsTop)
										(CelHigh
											(thisIcon view?)
											(thisIcon loop?)
											(thisIcon cel?)
										)
									)
									2
								)
								nsTop
							)
						)
					)
					(= theHeight (- theHeight 7))
					(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
					(if
					(= thisIcon (theIconBar curInvIcon?))
						(DrawCel
							(thisIcon view?)
							(thisIcon loop?)
							(thisIcon cel?)
							theWidth
							theHeight
							-1
						)
					)
					(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
					(while (!= ((= event (Event new:)) type?) mouseUp)
						(event localize:)
						(cond 
							((self onMe: event)
								(if (not whichCel)
									(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
									(if
									(= thisIcon (theIconBar curInvIcon?))
										(DrawCel
											(thisIcon view?)
											(thisIcon loop?)
											(thisIcon cel?)
											theWidth
											theHeight
											-1
										)
									)
									(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
								)
							)
							(whichCel
								(DrawCel view loop (= whichCel 0) nsLeft nsTop -1)
								(if
								(= thisIcon (theIconBar curInvIcon?))
									(DrawCel
										(thisIcon view?)
										(thisIcon loop?)
										(thisIcon cel?)
										theWidth
										theHeight
										-1
									)
								)
								(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
							)
						)
						(event dispose:)
					)
					(event dispose:)
					(if (== whichCel 1)
						(DrawCel view loop 0 nsLeft nsTop -1)
						(if
						(= thisIcon (theIconBar curInvIcon?))
							(DrawCel
								(thisIcon view?)
								(thisIcon loop?)
								(thisIcon cel?)
								theWidth
								theHeight
								-1
							)
						)
						(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
					)
					whichCel
				)
				(else TRUE)
			)
		)
	)
)

(instance dogIcon7 of TwistyIconItem
	(properties
		loop 7
		cursor ARROW_CURSOR
		message V_GAME
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskLoop 7
		lowlightColor 7
		noun N_DOG_CONTROLS
		helpVerb V_HELP
		ulXoff 2
		ulYoff 8
		brXoff 22
		brYoff 27
		lowlightColor2 21
	)
	
	(method (select)
		(if (super select: &rest)
			(theIconBar hide:)
			((ScriptID 888 0) show:)
		)
		(return TRUE)
	)
)

(instance dogIcon8 of TwistyIconItem
	(properties
		loop 8
		cursor 990
		message V_HELP
		signal (| RELVERIFY IMMEDIATE)
		maskLoop 8
		lowlightColor 7
		noun N_DOG_HELP
		helpVerb V_HELP
		ulXoff 2
		ulYoff 8
		brXoff 19
		brYoff 27
		lowlightColor2 21
	)
)

(instance dogIcon9 of TwistyIconItem
	(properties
		loop 9
		message V_TRIVIA
		signal (| HIDEBAR RELVERIFY)
		maskLoop 9
		lowlightColor 7
		noun N_DOG_TRIVIA
		helpVerb V_HELP
		ulXoff 2
		ulYoff 8
		brXoff 24
		brYoff 27
		lowlightColor2 21
		cursorCel 6
	)
)

(instance dogIcon10 of TwistyIconItem
	(properties
		loop 10
		cursor ARROW_CURSOR
		message V_TRIVIA
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskLoop 10
		lowlightColor 7
		noun N_DOG_REVIEW
		helpVerb V_HELP
		ulXoff 2
		ulYoff 8
		brXoff 32
		brYoff 27
		lowlightColor2 21
	)
	
	(method (select &tmp theNoun)
		(if (super select: &rest)
			(ego setMotion: 0)
			(theIconBar hide:)
			(= theNoun 7)
			(narrator keepWindow: TRUE)
			(if (== gameAct 0)
				(Prints {Setting your act to 1, cause you teleported!!})
				(= gameAct 1)
			)
			(messager say: theNoun NULL gameAct 2 0 110)
		)
		(return TRUE)
	)
)
