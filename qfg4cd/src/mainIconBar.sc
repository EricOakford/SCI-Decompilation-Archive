;;; Sierra Script 1.0 - (do not remove this comment)
(script# 36)
(include game.sh) (include "0.shm")
(use Main)
(use Print)
(use IconBar)
(use Invent)
(use Actor)
(use System)

(public
	mainIconBar 0
	invItem 1
	iconUseIt 2
)

(instance mainIconBar of IconBar
	(method (init &tmp obj theCast)
		(self
			add:
				iconWalk
				iconLook
				iconDo
				iconTalk
				iconActions
				iconCast
				iconUseIt
				iconInventory
				iconControlPanel
				iconHelp
		)
		(if (not plane)
			(= plane (systemPlane new:))
		)
		(plane
			back: 0
			priority: -1
			init: 0 0 320 28 0 0 320 28
			addCast: self
		)
		(self eachElementDo: #init self)
		(plane addCast: (= theCast (Cast new:)))
		(skipIcon1 init: theCast)
		(skipIcon2 init: theCast)
		(invItem init: theCast)
		(self
			eachElementDo: #highlightColor -1
			eachElementDo: #lowlightColor -1
			useIconItem: iconUseIt
			helpIconItem: iconHelp
			walkIconItem: iconWalk
			state: (| OPENIFONME NOCLICKHELP)
			disable: ICON_USEIT
			curIcon: iconWalk
		)
	)
	
	(method (handleEvent event &tmp keyInvoked eType newEvent
									newCursor oldCursor oldCurIcon oldInvIcon)
		(if (& state IB_ACTIVE)
			(event localize: plane)
		)
		(= eType (event type?))
		(cond 
			((& state DISABLED))
			(
				(and
					(not eType)
					(& state OPENIFONME)
					(self shouldOpen: event)
					(not (= keyInvoked FALSE))
				)
				(= oldMouseX (event x?))
				(= oldMouseY (event y?))
				(= oldCursor theCursor)
				(= oldCurIcon curIcon)
				(= oldInvIcon curInvIcon)
				(self show:)
				(theGame setCursor: normalCursor)
				(if keyInvoked
					(theGame setCursor: theCursor TRUE
						(+
							(curIcon nsLeft?)
							(/ (- (curIcon nsRight?) (curIcon nsLeft?)) 2)
						)
						(- (curIcon nsBottom?) 3)
					)
				)
				(self doit:)
				(= newCursor
					(if (or (user canControl:) (user canInput:))
						(self getCursor:)
					else
						waitCursor
					)
				)
				(if keyInvoked
					(theGame setCursor: newCursor TRUE oldMouseX oldMouseY)
				else
					(theGame setCursor: newCursor TRUE
						((event new:) x?)
						(Max (event y?) (+ 1 (plane bottom?)))
					)
				)
				(self hide:)
			)
			((& eType keyDown)
				(switch (event message?)
					(ENTER
						(cond 
							((not curIcon))
							((or (!= curIcon useIconItem) curInvIcon)
								(event
									type: (curIcon type?)
									message:
										(if (== curIcon useIconItem)
											(curInvIcon message?)
										else
											(curIcon message?)
										)
								)
							)
							(else
								(event type: nullEvt)
							)
						)
					)
					(INSERT
						(if (user canControl:)
							(self swapCurIcon:)
						)
						(event claimed: TRUE)
					)
					(dirStop
						(if (& (event type?) direction)
							(self advanceCurIcon:)
							(event claimed: TRUE)
						)
					)
				)
			)
			((& eType mouseDown)
				(cond 
					((& (event modifiers?) shiftDown)
						(self advanceCurIcon:)
						(event claimed: TRUE)
					)
					((& (event modifiers?) ctrlDown)
						(if (user canControl:)
							(self swapCurIcon:)
						)
						(event claimed: TRUE)
					)
					(curIcon
						(event
							type: (curIcon type?)
							message:
								(if (and curInvIcon (== curIcon useIconItem))
									(curInvIcon message?)
								else
									(curIcon message?)
								)
						)
					)
				)
			)
		)
	)
	
	(method (dispatchEvent event &tmp thisIcon evtClaimed theSignal isHelp)
		(= evtClaimed (event claimed?))
		(if (= thisIcon (self firstTrue: #onMe event))
			(= theSignal (thisIcon signal?))
			(= isHelp (== thisIcon helpIconItem))
		)
		(switch (event type?)
			(nullEvt
				(cond 
					((self shouldClose: event)
						(if
							(and
								(& state OPENIFONME)
								(or
									(not helpIconItem)
									(not (& (helpIconItem signal?) TRANSLATOR))
								)
							)
							(= oldMouseY 0)
							(= evtClaimed TRUE)
						)
					)
					((and thisIcon (!= thisIcon highlightedIcon))
						(= oldMouseY 0)
						(self highlight: thisIcon)
					)
				)
			)
			(mouseDown
				(if (and thisIcon (self select: thisIcon TRUE))
					(if isHelp
						(theGame setCursor: (thisIcon getCursor:) TRUE)
						(if (& state NOCLICKHELP)
							(self noClickHelp:)
						else
							(helpIconItem signal: (| (helpIconItem signal?) TRANSLATOR))
						)
					else
						(= evtClaimed (& theSignal HIDEBAR))
					)
					(if (thisIcon object?)
						(theGame
							panelObj: (thisIcon object?)
							panelSelector: (thisIcon selector?)
						)
					)
					(thisIcon doit:)
				)
			)
			((| userEvent helpEvent)
				(if (and thisIcon (thisIcon helpVerb?))
					(Print
						font: userFont
						width: 250
						addText: (thisIcon noun?) (thisIcon helpVerb?) NULL 1 0 0 (thisIcon modNum?)
						init:
					)
				)
				(if helpIconItem
					(helpIconItem signal: (& (helpIconItem signal?) (~ TRANSLATOR)))
				)
				(theGame setCursor: normalCursor)
			)
		)
		(return evtClaimed)
	)
	
	(method (shouldOpen event)
		(return
			(if (Btst fCantSave)
				FALSE
			else
				(if (& (iconUseIt signal?) DISABLED)
					(iconUseIt setPri: 100)
				else
					(iconUseIt setPri: 0)
				)
				(if
					(or
						(and (== (event type?) nullEvt) (<= (event y?) 10))
						(and
							(== (event type?) keyDown)
							(OneOf (event message?) ESC DELETE)
						)
					)
					TRUE
				)
			)
		)
	)
)

(instance skipIcon1 of View
	(properties
		view 930
		loop 12
	)
)

(instance skipIcon2 of View
	(properties
		x 306
		view 930
		loop 13
	)
)

(instance invItem of View
	(properties
		x 203
		y 15
		view 905
	)
	
	(method (init)
		(super init: &rest)
		(self hide:)
	)
)

(instance iconWalk of IconItem
	(properties
		noun N_WALK
		modNum 0
		signal (| HIDEBAR RELVERIFY)
		type (| userEvent walkEvent)
		message V_WALK
		mainView 930
		maskView 930
		maskLoop 14
		helpVerb V_HELP
	)
	
	(method (init)
		(self setCursor: 940 0 0)
		(= x (CelWide 930 13 0))
		(super init: &rest)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(ego changeGait: MOVE_WALK TRUE)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
	
	(method (highlight theCel)
		(if (not (& signal DISABLED))
			(= cel theCel)
			(UpdateScreenItem self)
		)
	)
)

(instance iconLook of IconItem
	(properties
		noun N_LOOK
		modNum 0
		signal (| HIDEBAR RELVERIFY)
		message V_LOOK
		mainView 930
		mainLoop 1
		maskView 930
		maskLoop 14
		maskCel 1
		helpVerb V_HELP
	)
	
	(method (init)
		(self setCursor: 941 0 0)
		(= x
			(+
				(iconWalk x?)
				(CelWide
					(iconWalk view?)
					(iconWalk loop?)
					(iconWalk cel?)
				)
			)
		)
		(super init: &rest)
	)
	
	(method (highlight theCel)
		(if (not (& signal DISABLED))
			(= cel theCel)
			(UpdateScreenItem self)
		)
	)
)

(instance iconDo of IconItem
	(properties
		noun N_DO
		modNum 0
		signal (| HIDEBAR RELVERIFY)
		message V_DO
		mainView 930
		mainLoop 2
		maskView 930
		maskLoop 14
		maskCel 2
		helpVerb V_HELP
	)
	
	(method (init)
		(self setCursor: 942 0 0)
		(= x
			(+
				(iconLook x?)
				(CelWide
					(iconLook view?)
					(iconLook loop?)
					(iconLook cel?)
				)
			)
		)
		(super init: &rest)
	)
	
	(method (highlight theCel)
		(if (not (& signal DISABLED))
			(= cel theCel)
			(UpdateScreenItem self)
		)
	)
)

(instance iconTalk of IconItem
	(properties
		noun N_TALK
		modNum 0
		signal (| HIDEBAR RELVERIFY)
		message V_TALK
		mainView 930
		mainLoop 3
		maskView 930
		maskLoop 14
		maskCel 3
		helpVerb V_HELP
	)
	
	(method (init)
		(self setCursor: 943 0 0)
		(= x
			(+
				(iconDo x?)
				(CelWide (iconDo view?) (iconDo loop?) (iconDo cel?))
			)
		)
		(super init: &rest)
	)
	
	(method (highlight theCel)
		(if (not (& signal DISABLED))
			(= cel theCel)
			(UpdateScreenItem self)
		)
	)
)

(instance iconActions of IconItem
	(properties
		noun N_ACTIONS
		modNum 0
		signal (| HIDEBAR IMMEDIATE)
		message V_ACTIONS
		mainView 930
		mainLoop 10
		maskView 930
		maskLoop 14
		maskCel 8
		helpVerb V_HELP
	)
	
	(method (init)
		(self setCursor: 942 0 0)
		(= x
			(+
				(iconTalk x?)
				(CelWide
					(iconTalk view?)
					(iconTalk loop?)
					(iconTalk cel?)
				)
			)
		)
		(super init: &rest)
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(SaveTheCursor)
				(theIconBar hide:)
				((ScriptID GLORY_ACTIONS) init: show: doit:)
				(DisposeScript GLORY_ACTIONS)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
	
	(method (highlight theCel)
		(if (not (& signal DISABLED))
			(= cel theCel)
			(UpdateScreenItem self)
		)
	)
)

(instance iconCast of IconItem
	(properties
		noun N_CAST
		modNum 0
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		message V_CONTROLS
		mainView 930
		mainLoop 11
		maskView 930
		maskLoop 14
		maskCel 9
		helpVerb V_HELP
	)
	
	(method (init)
		(self setCursor: ARROW_CURSOR 0 0)
		(= x
			(+
				(iconActions x?)
				(CelWide
					(iconActions view?)
					(iconActions loop?)
					(iconActions cel?)
				)
			)
		)
		(super init: &rest)
	)
	
	(method (select &tmp whichSkill spellNum)
		(return
			(if (super select: &rest)
				(if (!= (theIconBar getCursor:) 948) (SaveTheCursor))
				(theIconBar hide:)
				(cond 
					((not [egoStats MAGIC])
						(messager say: N_CUE V_DOIT C_NO_MAGIC_ABILITY 0 0 0)
					)
					(
						(and
							(OneOf curRoomNum 240 250 300 310 320 370 380)
							(not Night)
						)
						(messager say: N_CUE V_DOIT C_NO_CAST_MORDAVIA 0 0 0)
					)
					(else
						(for ((= spellNum 0)) (< spellNum NUM_SPELLS) ((++ spellNum))
							(if (= whichSkill [egoStats (+ OPEN spellNum)]) (break))
						)
						(if (not whichSkill)
							(messager say: N_CUE V_DOIT C_KNOW_NO_SPELLS 0 0 0)
						else
							((ScriptID GLORY_MAGIC) showSelf: ego)
						)
					)
				)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
	
	(method (highlight theCel)
		(if (not (& signal DISABLED))
			(= cel theCel)
			(UpdateScreenItem self)
		)
	)
)

(instance iconUseIt of IconItem
	(properties
		noun N_USEIT
		modNum 0
		signal (| HIDEBAR RELVERIFY)
		message NULL
		mainView 930
		mainLoop 4
		maskView 930
		maskLoop 14
		maskCel 4
		helpVerb V_HELP
	)
	
	(method (init)
		(self setCursor: ARROW_CURSOR 0 0)
		(= x
			(+
				(iconCast x?)
				(CelWide
					(iconCast view?)
					(iconCast loop?)
					(iconCast cel?)
				)
			)
		)
		(super init: &rest)
	)
)

(instance iconInventory of IconItem
	(properties
		noun 21
		modNum 0
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		type NULL
		message NULL
		mainView 930
		mainLoop 5
		maskView 930
		maskLoop 14
		maskCel 5
		helpVerb V_HELP
	)
	
	(method (init)
		(self setCursor: ARROW_CURSOR 0 0)
		(= x
			(+
				(iconUseIt x?)
				(CelWide
					(iconUseIt view?)
					(iconUseIt loop?)
					(iconUseIt cel?)
				)
			)
		)
		(super init: &rest)
	)
	
	(method (select &tmp thisIcon theView theLoop theCel)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(inventory showSelf: ego)
				(if
					(and
						(= thisIcon (inventory curIcon?))
						(thisIcon isKindOf: InvItem)
					)
					(= theView (thisIcon view?))
					(= theLoop (thisIcon loop?))
					(= theCel (thisIcon cel?))
					(iconUseIt cursorView: theView)
					(iconUseIt cursorLoop: theLoop)
					(iconUseIt cursorCel: theCel)
					(invItem
						view: theView
						loop: theLoop
						cel: theCel
						show:
					)
					(UpdateScreenItem invItem)
				)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
	
	(method (highlight theCel)
		(if (not (& signal DISABLED))
			(= cel theCel)
			(UpdateScreenItem self)
		)
	)
)

(instance iconControlPanel of IconItem
	(properties
		noun 22
		modNum 0
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		message V_CONTROLS
		mainView 930
		mainLoop 7
		maskView 930
		maskLoop 14
		maskCel 6
		helpVerb V_HELP
	)
	
	(method (init)
		(self setCursor: ARROW_CURSOR 0 0)
		(= x
			(+
				(iconInventory x?)
				(CelWide
					(iconInventory view?)
					(iconInventory loop?)
					(iconInventory cel?)
				)
			)
		)
		(super init: &rest)
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				((ScriptID GLORY_CONTROLS) init: show: dispose:)
				(DisposeScript GLORY_CONTROLS)
				(DisposeScript GLORY_ABOUT)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
	
	(method (highlight theCel)
		(if (not (& signal DISABLED))
			(= cel theCel)
			(UpdateScreenItem self)
		)
	)
)

(instance iconHelp of IconItem
	(properties
		noun N_HELP
		modNum 0
		signal (| RELVERIFY IMMEDIATE)
		message V_HELP
		mainView 930
		mainLoop 9
		maskView 930
		maskLoop 14
		maskCel 7
		cursorView 949
		cursorLoop 0
		cursorCel 0
		helpVerb V_HELP
	)
	
	(method (init)
		(= x
			(+
				(iconControlPanel x?)
				(CelWide
					(iconControlPanel view?)
					(iconControlPanel loop?)
					(iconControlPanel cel?)
				)
			)
		)
		(super init: &rest)
	)
	
	(method (highlight theCel)
		(if (not (& signal DISABLED))
			(= cel theCel)
			(UpdateScreenItem self)
		)
	)
)
