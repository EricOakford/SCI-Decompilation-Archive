;;; Sierra Script 1.0 - (do not remove this comment)
(script# GK_INV)
(include game.sh) (include "15.shm")
(use Main)
(use Procs)
(use Print)
(use IconBar)
(use Tutorial)
(use Window)
(use Invent)
(use System)


(class GKInvItem of InvItem
	(properties
		owner 0
		script 0
		value 0
		cursorView 0
		cursorLoop 0
		cursorCel 0
		page 1
		visible 0
	)
	
	(method (select)
		(genericCursor
			view: cursorView
			loop: cursorLoop
			cel: cursorCel
		)
		(theGame setCursor: genericCursor)
		(super select: &rest)
	)
	
	(method (doVerb theVerb &tmp theScript)
		(if (== modNum -1)
			(= modNum curRoomNum)
		)
		(if msgType
			(cond 
				((Message MsgGet modNum noun theVerb NULL 1)
					(messager say: noun theVerb NULL 0 0 modNum)
				)
				((Message MsgGet modNum NULL theVerb NULL 1)
					(messager say: NULL theVerb NULL 0 0 modNum)
				)
				((Message MsgGet modNum NULL NULL NULL 1)
					(messager say: NULL NULL NULL 0 0 modNum)
				)
			)
		)
		(if
			(and
				(= theScript (theGame script?))
				(theScript isKindOf: Tutorial)
			)
			(cond 
				((!= (theScript nextItem?) self)
					(theScript report: self)
				)
				((!= (theScript nextAction?) theVerb)
					(theScript report: theVerb)
				)
				(else
					(theScript cue:)
				)
			)
		)
	)
	
	(method (setCursor theView theLoop theCel)
		(= cursorView theView)
		(= cursorLoop theLoop)
		(= cursorCel theCel)
		(= cursor genericCursor)
	)
	
	(method (cue)
	)
)

(class GKInventory of Inventory
	(properties
		normalHeading {Gabriel is carrying}
		empty {nothing.}
		iconBarInvItem 0
		okButton 0
		selectIcon 0
		lastPage 0
		numLastPage 0
		curPage 0
		indicatorY 0
		tallestInv 0
		widestInv 0
		mode 0
		theCaller 0
	)
	
	(method (init)
		(= inventory GKInventory)
		(self
			window: inventWind
			helpIconItem: invHelp
			okButton: invExit
			add:
				(phoEnvelope setCursor: 942 0 5 yourself:)
				(murderPhoto setCursor: 942 0 8 yourself:)
				(gradPhoto setCursor: 942 0 7 yourself:)
				(tweezers setCursor: 942 0 2 yourself:)
				(magGlass setCursor: 942 0 1 yourself:)
				(giftCert setCursor: 942 1 1 yourself:)
				(hundred setCursor: 942 1 5 yourself:)
				(phonePage setCursor: 942 1 8 yourself:)
				(wolfPhone setCursor: 942 1 9 yourself:)
				(news1810 setCursor: 942 2 0 yourself:)
				(guntJournal setCursor: 942 2 3 yourself:)
				(ritLetter setCursor: 942 0 15 yourself:)
				(mosKey setCursor: 942 2 11 yourself:)
				(drumBook setCursor: 942 2 1 yourself:)
				(veveCopy setCursor: 942 0 13 yourself:)
				(policeVeve_ setCursor: 942 0 12 yourself:)
				(badge setCursor: 942 1 0 yourself:)
				(tracker setCursor: 942 2 6 yourself:)
				(crocMask setCursor: 942 1 6 yourself:)
				(oil setCursor: 942 1 7 yourself:)
				(musScale setCursor: 942 0 0 yourself:)
				(lakeScale setCursor: 942 0 0 yourself:)
				(twoScales setCursor: 942 2 5 yourself:)
				(tatooTrace setCursor: 942 0 9 yourself:)
				(keyEnvelope setCursor: 942 2 9 yourself:)
				(mosLetter setCursor: 942 2 10 yourself:)
				(clay setCursor: 942 0 3 yourself:)
				(sketchBook setCursor: 942 0 4 yourself:)
				(sLakePatter setCursor: 942 0 6 yourself:)
				(VoodooCode1 setCursor: 942 0 10 yourself:)
				(sVoodooCode setCursor: 942 0 11 yourself:)
				(ritPhoto_ setCursor: 942 0 14 yourself:)
				(lostDrawing setCursor: 942 1 2 yourself:)
				(luckyDog_ setCursor: 942 1 3 yourself:)
				(snakeSkin_ setCursor: 942 1 4 yourself:)
				(reconVeve_ setCursor: 942 1 10 yourself:)
				(collar_ setCursor: 942 1 11 yourself:)
				(braceMold_ setCursor: 942 1 12 yourself:)
				(braceRep_ setCursor: 942 1 13 yourself:)
				(veil_ setCursor: 942 1 14 yourself:)
				(fortScale_ setCursor: 942 1 15 yourself:)
				(signalDev_ setCursor: 942 2 7 yourself:)
				(brick_ setCursor: 942 2 8 yourself:)
				(hartNotes_ setCursor: 942 2 4 yourself:)
				(wolfLetter_ setCursor: 942 2 2 yourself:)
				(invRead cursor: cInvRead yourself:)
				(invOpen cursor: cInvOpen yourself:)
				(invOper cursor: cInvOper yourself:)
				(invLook cursor: cInvLook yourself:)
				(invHelp cursor: cInvHelp yourself:)
				invSelect
				invExit
				invUp
				invDown
			selectIcon: invSelect
			eachElementDo: #lowlightColor myLowlightColor
			eachElementDo: #highlightColor myHighlightColor
			eachElementDo: #modNum 15
			eachElementDo: #init
			state: NOCLICKHELP
		)
		(= heading normalHeading)
	)
	
	(method (hide &tmp theIcon oldPort)
		(narrator modeless: mode)
		(= theIcon (theIconBar curInvIcon?))
		(super hide:)
		(if
			(and
				(theIconBar curInvIcon?)
				(!= theIcon (theIconBar curInvIcon?))
				(theGame barUp?)
			)
			(= oldPort (GetPort))
			(SetPort -1)
			(theIconBar showInvItem:)
			(SetPort oldPort)
		)
		(if ((theIconBar curIcon?) cursor?)
			(theGame setCursor: ((theIconBar curIcon?) cursor?))
		)
		(if theCaller
			(theCaller cue:)
		)
		(= theCaller 0)
	)
	
	(method (advance amount &tmp theIcon toMove highlightedNo nextIcon)
		(= toMove (if argc amount else 1))
		(= nextIcon
			(+ toMove (= highlightedNo (self indexOf: highlightedIcon)))
		)
		(repeat
			(= theIcon
				(self
					at: (if (<= nextIcon size) nextIcon else (mod nextIcon (- size 1)))
				)
			)
			(if (not (IsObject theIcon))
				(= theIcon (NodeValue (self first:)))
			)
			(if
				(or
					(and (theIcon isKindOf: InvItem) (theIcon visible?))
					(not (& (theIcon signal?) DISABLED))
				)
				(break)
			)
			(++ nextIcon)
		)
		(self highlight: theIcon 1)
	)
	
	(method (retreat amount &tmp theIcon toMove highlightedNo nextIcon)
		(= toMove (if argc amount else 1))
		(= nextIcon
			(- (= highlightedNo (self indexOf: highlightedIcon)) toMove)
		)
		(repeat
			(= theIcon (self at: nextIcon))
			(if (not (IsObject theIcon))
				(= theIcon (NodeValue (self last:)))
			)
			(if
				(or
					(and (theIcon isKindOf: InvItem) (theIcon visible?))
					(not (& (theIcon signal?) DISABLED))
				)
				(break)
			)
			(++ nextIcon)
		)
		(self highlight: theIcon 1)
	)
	
	(method (swapCurIcon &tmp firstIcon)
		(cond 
			((& state DISABLED) (return))
			(
				(and
					(!= curIcon (= firstIcon invSelect))
					(not (& (firstIcon signal?) DISABLED))
				)
				(= prevIcon curIcon)
				(= curIcon firstIcon)
			)
			((and prevIcon (not (& (prevIcon signal?) DISABLED)))
				(= curIcon prevIcon)
			)
		)
		(if (curIcon isKindOf: InvItem)
			(genericCursor
				view: (curIcon cursorView?)
				loop: (curIcon cursorLoop?)
				cel: (curIcon cursorCel?)
			)
		)
		(theGame setCursor: (curIcon cursor?) TRUE)
	)
	
	(method (advanceCurIcon &tmp theIcon i newIcon)
		(if (& state DISABLED) (return))
		(= theIcon curIcon)
		(= i 0)
		(while
			(&
				((= theIcon
					(self at: (mod (+ (self indexOf: theIcon) 1) size))
				)
					signal?
				)
				(| DISABLED IMMEDIATE)
			)
			(if (> i (+ 1 size))
				(return)
			else
				(++ i)
			)
		)
		(= curIcon theIcon)
		(if (curIcon isKindOf: InvItem)
			(genericCursor
				view: (curIcon cursorView?)
				loop: (curIcon cursorLoop?)
				cel: (curIcon cursorCel?)
			)
		)
		(theGame setCursor: (curIcon cursor?) TRUE)
	)
	
	(method (noClickHelp &tmp event lastIcon thisIcon oldPort eO oldCur)
		(= lastIcon (= thisIcon 0))
		(= oldPort (GetPort))
		(= eO (systemWindow eraseOnly?))
		(systemWindow eraseOnly: TRUE)
		(while (not ((= event ((user curEvent?) new:)) type?))
			(if (not (self isMemberOf: IconBar))
				(event localize:)
			)
			(cond 
				((= thisIcon (self firstTrue: #onMe event))
					(if (and (!= thisIcon lastIcon) (thisIcon helpVerb?))
						(= lastIcon thisIcon)
						(if modelessDialog (modelessDialog dispose:))
						(Print
							font: userFont
							width: 300
							addText: (thisIcon noun?) (thisIcon helpVerb?) NULL 1 0 0 (thisIcon modNum?)
							modeless: TRUE
							y: 147
							init:
						)
						(SetPort oldPort)
					)
				)
				(modelessDialog
					(modelessDialog dispose:)
					(SetPort oldPort)
				)
				(else
					(= lastIcon 0)
					(SetPort oldPort)
				)
			)
			(event dispose:)
		)
		(systemWindow eraseOnly: eO)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(SetPort oldPort)
		(if (not (helpIconItem onMe: event))
			(self dispatchEvent: event)
		)
	)
	
	(method (drawInvWindow page &tmp temp0 pnv [temp2 5] node obj [buffer 62] temp71 temp72 temp73 temp74 temp75 temp76)
		(if (not (self assignPages:))
			(messager say: NULL NULL C_CARRYING_NOTHING 1 0 15)
			(return FALSE)
		)
		(= mode (narrator modeless?))
		(narrator modeless: FALSE)
		(window open:)
		(= pnv (PicNotValid))
		(PicNotValid TRUE)
		(DrawCel 951 8 0 0 0 15)
		(self drawPage: (if argc page else 1))
		(= node (self first:))
		(while node
			(if
				(not
					((= obj (NodeValue node)) isKindOf: InvItem)
				)
				(obj signal: (& (obj signal?) (~ DISABLED)) show:)
			)
			(= node (self next: node))
		)
		(PicNotValid FALSE)
		(= temp71
			(= temp75
				(= temp74
					(= temp72
						(= temp73 0)
					)
				)
			)
		)
		(while (< temp71 97)
			(= temp75 (- 96 temp71))
			(= temp74 (+ 96 temp71))
			(if (< temp71 68)
				(= temp72 (- 59 temp71))
				(= temp73 (+ 59 temp71))
			)
			(Graph GShowBits temp72 temp75 temp73 temp74 VMAP)
			(+= temp71 4)
			(Wait 1)
		)
		(return TRUE)
	)
	
	(method (drawPage thePage &tmp theNumLastPage temp1 temp2 node atY atX cWide cHigh obj)
		(= curPage thePage)
		(self moveIndicator:)
		(= temp2 0)
		(if (!= thePage lastPage)
			(= theNumLastPage (= temp1 3))
		else
			(= temp1
				(cond 
					((< numLastPage 4) 1)
					((< numLastPage 7) 2)
					(else 3)
				)
			)
			(if (> (= theNumLastPage numLastPage) 3)
				(= theNumLastPage 3)
			)
		)
		(= atY 14)
		(= atX 12)
		(= node (self first:))
		(while node
			(if
				(and
					(not
						(&
							((= obj (NodeValue node)) signal?)
							DISABLED
						)
					)
					(obj isKindOf: InvItem)
					(== (obj page?) thePage)
				)
				(++ temp2)
				(if (not (& (obj signal?) FIXED_POSN))
					(obj
						visible: TRUE
						nsLeft:
							(+
								atX
								(/
									(-
										widestInv
										(= cWide
											(CelWide (obj view?) (obj loop?) (obj cel?))
										)
									)
									2
								)
							)
						nsTop:
							(+
								atY
								(/
									(-
										tallestInv
										(= cHigh
											(CelHigh (obj view?) (obj loop?) (obj cel?))
										)
									)
									2
								)
							)
					)
					(obj
						nsRight: (+ (obj nsLeft?) cWide)
						nsBottom: (+ (obj nsTop?) cHigh)
					)
					(if (mod temp2 3)
						(= atX (+ atX widestInv 3))
					else
						(= atY (+ atY tallestInv 6))
						(= atX 12)
					)
				else
					(= atX (obj nsLeft?))
					(= atY (obj nsTop?))
				)
				(obj show:)
			)
			(= node (self next: node))
		)
	)
	
	(method (eraseItems &tmp node obj)
		(= node (self first:))
		(while node
			(if
				(and
					(not
						(&
							((= obj (NodeValue node)) signal?)
							DISABLED
						)
					)
					(obj isKindOf: InvItem)
					(== (obj page?) curPage)
				)
				(obj
					nsLeft: 0
					nsTop: 0
					nsRight: 0
					nsBottom: 0
					visible: 0
				)
			)
			(= node (self next: node))
		)
	)
	
	(method (moveIndicator &tmp theY temp1)
		(cond 
			((== curPage 1)
				(= theY 20)
			)
			((== curPage lastPage)
				(= theY 80)
			)
			(else
				(= temp1 (/ 56 (- lastPage 1)))
				(= theY (+ 21 (* (- curPage 1) temp1)))
			)
		)
		(DrawCel 951 8 1 142 20 -1)
		(DrawCel 951 7 0 142 theY -1)
	)
	
	(method (assignPages &tmp i theLastPage node obj cWide cHigh)
		(= i 0)
		(= numLastPage 0)
		(= theLastPage 1)
		(= node (self first:))
		(while node
			(if ((= obj (NodeValue node)) isKindOf: InvItem)
				(obj visible: FALSE)
				(if (obj ownedBy: ego)
					(obj signal: (& (obj signal?) (~ DISABLED)))
					(if
						(>
							(= cWide
								(CelWide (obj view?) (obj loop?) (obj cel?))
							)
							widestInv
						)
						(= widestInv cWide)
					)
					(if
						(>
							(= cHigh
								(CelHigh (obj view?) (obj loop?) (obj cel?))
							)
							tallestInv
						)
						(= tallestInv cHigh)
					)
					(++ i)
					(++ numLastPage)
					(obj page: theLastPage)
					(= lastPage theLastPage)
					(if (not (mod i 9))
						(++ theLastPage)
						(= numLastPage 0)
					)
				else
					(obj signal: (| (obj signal?) DISABLED))
				)
			)
			(= node (self next: node))
		)
		(return i)
	)
)

(instance invRead of IconItem
	(properties
		view 951
		loop 1
		cel 0
		nsLeft 165
		nsTop 6
		nsRight 184
		nsBottom 22
		message V_READ
		signal (| FIXED_POSN RELVERIFY)
		noun N_READ
		helpVerb V_HELP
	)
	
	(method (highlight tOrF &tmp theCel)
		(if (== highlightColor -1) (return))
		(= theCel (if (and argc tOrF) 1 else 0))
		(DrawCel view loop theCel nsLeft nsTop 15)
	)
)

(instance invOpen of IconItem
	(properties
		view 951
		loop 2
		cel 0
		nsLeft 165
		nsTop 22
		nsRight 174
		nsBottom 36
		message V_OPEN
		signal (| FIXED_POSN RELVERIFY)
		noun N_OPEN
		helpVerb V_HELP
	)
	
	(method (highlight tOrF &tmp theCel)
		(if (== highlightColor -1) (return))
		(= theCel (if (and argc tOrF) 1 else 0))
		(DrawCel view loop theCel nsLeft nsTop 15)
	)
)

(instance invOper of IconItem
	(properties
		view 951
		loop 3
		cel 0
		nsLeft 165
		nsTop 36
		nsRight 184
		nsBottom 52
		message V_OPERATE
		signal (| FIXED_POSN RELVERIFY)
		noun N_OPERATE
		helpVerb V_HELP
	)
	
	(method (highlight tOrF &tmp theCel)
		(if (== highlightColor -1) (return))
		(= theCel (if (and argc tOrF) 1 else 0))
		(DrawCel view loop theCel nsLeft nsTop 15)
	)
)

(instance invLook of IconItem
	(properties
		view 951
		loop 4
		cel 0
		nsLeft 165
		nsTop 52
		nsRight 184
		nsBottom 66
		message V_LOOK
		signal (| FIXED_POSN RELVERIFY)
		noun N_LOOK
		helpVerb V_HELP
	)
	
	(method (highlight tOrF &tmp theCel)
		(if (== highlightColor -1) (return))
		(= theCel (if (and argc tOrF) 1 else 0))
		(DrawCel view loop theCel nsLeft nsTop 15)
	)
)

(instance invHelp of IconItem
	(properties
		view 951
		loop 5
		cel 0
		nsLeft 165
		nsTop 66
		nsRight 184
		nsBottom 81
		message V_HELP
		signal (| FIXED_POSN RELVERIFY)
		noun N_HELP
		helpVerb V_HELP
	)
	
	(method (highlight tOrF &tmp theCel)
		(if (== highlightColor -1) (return))
		(= theCel (if (and argc tOrF) 1 else 0))
		(DrawCel view loop theCel nsLeft nsTop 15)
	)
)

(instance invSelect of IconItem
	(properties
		view 951
		loop 9
		cel 0
		nsLeft 165
		nsTop 81
		nsRight 184
		nsBottom 96
		cursor ARROW_CURSOR
		signal (| FIXED_POSN RELVERIFY)
		noun N_SELECT
		helpVerb V_HELP
	)
	
	(method (highlight tOrF &tmp theCel)
		(if (== highlightColor -1) (return))
		(= theCel (if (and argc tOrF) 1 else 0))
		(DrawCel view loop theCel nsLeft nsTop 15)
	)
)

(instance invExit of IconItem
	(properties
		view 951
		loop 6
		cel 0
		nsLeft 165
		nsTop 96
		nsRight 184
		nsBottom 111
		cursor ARROW_CURSOR
		signal (| FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_OK
		helpVerb V_HELP
	)
	
	(method (highlight tOrF &tmp theCel)
		(if (== highlightColor -1) (return))
		(= theCel (if (and argc tOrF) 1 else 0))
		(DrawCel view loop theCel nsLeft nsTop 15)
	)
)

(instance invDown of IconItem
	(properties
		view 951
		loop 11
		cel 0
		nsLeft 141
		nsTop 97
		nsRight 157
		nsBottom 110
		cursor ARROW_CURSOR
		signal (| FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_SCROLL_BAR
		helpVerb V_HELP
	)
	
	(method (select &tmp temp0 pnv)
		(return
			(if (!= (inventory curPage?) (inventory lastPage?))
				(inventory eraseItems:)
				(= pnv (PicNotValid))
				(PicNotValid TRUE)
				(Graph GFillRect 10 10 107 141 VMAP myLowlightColor)
				(inventory drawPage: (+ (inventory curPage?) 1))
				(PicNotValid pnv)
				(Graph GShowBits 10 10 107 141 VMAP)
				(Graph GShowBits 19 142 98 156 VMAP)
				(return FALSE)
			else
				(messager say: NULL NULL C_LAST_PAGE 1 0 GK_INV)
			)
		)
	)
)

(instance invUp of IconItem
	(properties
		view 951
		loop 10
		cel 0
		nsLeft 142
		nsTop 9
		nsRight 156
		nsBottom 23
		cursor ARROW_CURSOR
		signal (| FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_SCROLL_BAR
		helpVerb V_HELP
	)
	
	(method (select &tmp temp0 pnv)
		(return
			(if (!= (inventory curPage?) 1)
				(inventory eraseItems:)
				(= pnv (PicNotValid))
				(PicNotValid TRUE)
				(Graph GFillRect 10 10 107 141 VMAP myLowlightColor)
				(inventory drawPage: (- (inventory curPage?) 1))
				(PicNotValid pnv)
				(Graph GShowBits 10 10 107 141 VMAP)
				(Graph GShowBits 19 142 98 156 VMAP)
				(return FALSE)
			else
				(messager say: NULL NULL C_FIRST_PAGE 1 0 GK_INV)
			)
		)
	)
)

(instance tweezers of GKInvItem
	(properties
		view 940
		cel 2
		message V_TWEEZERS
		signal IMMEDIATE
		noun N_TWEEZERS
	)
)

(instance magGlass of GKInvItem
	(properties
		view 940
		cel 1
		message V_MAG_GLASS
		signal IMMEDIATE
		noun N_MAG_GLASS
	)
)

(instance giftCert of GKInvItem
	(properties
		view 940
		loop 1
		cel 1
		message V_GIFT_CERTIFICATE
		signal IMMEDIATE
		noun N_GIFT_CERTIFICATE
	)
)

(instance hundred of GKInvItem
	(properties
		view 940
		loop 1
		cel 5
		message V_HUNDRED_DOLLARS
		signal IMMEDIATE
		noun N_HUNDRED_DOLLARS
	)
)

(instance phonePage of GKInvItem
	(properties
		view 940
		loop 1
		cel 8
		message V_PHONE_PAGE
		signal IMMEDIATE
		noun N_PHONE_PAGE
	)
)

(instance wolfPhone of GKInvItem
	(properties
		view 940
		loop 1
		cel 9
		message V_WOLFGANG_PHONE_NUMBER
		signal IMMEDIATE
		noun N_WOLFGANG_PHONE_NUMBER
	)
)

(instance news1810 of GKInvItem
	(properties
		view 940
		loop 2
		message V_1810_NEWS_CLIP
		signal IMMEDIATE
		noun N_1810_NEWS_CLIP
	)
)

(instance guntJournal of GKInvItem
	(properties
		view 940
		loop 2
		cel 3
		message V_GUNTER_JOURNAL
		signal IMMEDIATE
		noun N_GUNTER_JOURNAL
	)
)

(instance ritLetter of GKInvItem
	(properties
		view 940
		cel 15
		message V_RITTER_LETTER
		signal IMMEDIATE
		noun N_RITTER_LETTER
	)
)

(instance mosLetter of GKInvItem
	(properties
		view 940
		loop 2
		cel 10
		message V_MOSELY_LETTER
		signal IMMEDIATE
		noun N_MOSELY_LETTER
	)
)

(instance mosKey of GKInvItem
	(properties
		view 940
		loop 2
		cel 11
		message V_MOSELY_KEY
		signal IMMEDIATE
		noun N_MOSELY_KEY
	)
)

(instance drumBook of GKInvItem
	(properties
		view 940
		loop 2
		cel 1
		message V_DRUM_BOOK
		signal IMMEDIATE
		noun N_DRUM_BOOK
	)
)

(instance murderPhoto of GKInvItem
	(properties
		view 940
		cel 8
		message V_MURDER_PHOTO
		signal IMMEDIATE
		noun N_MURDER_PHOTO
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (== theVerb V_LOOK)
			(inventory hide:)
			((ScriptID 23 0) doit: 26 0 0 87 38 93 0)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance gradPhoto of GKInvItem
	(properties
		view 940
		cel 7
		message V_GRAD_PHOTO
		signal IMMEDIATE
		noun N_GRAD_PHOTO
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (== theVerb V_LOOK)
			(inventory hide:)
			((ScriptID 23 0) doit: 25 0 0 87 38 92 0)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance policeVeve_ of GKInvItem
	(properties
		view 940
		cel 12
		message V_POLICE_VEVE
		signal IMMEDIATE
		noun N_POLICE_VEVE
		name "policeVeve_"
	)
)

(instance veveCopy of GKInvItem
	(properties
		view 940
		cel 13
		message V_VEVE_COPY
		signal IMMEDIATE
		noun N_VEVE_COPY
	)
)

(instance badge of GKInvItem
	(properties
		view 940
		loop 1
		message V_TWEEZERS
		signal IMMEDIATE
		noun N_BADGE
	)
)

(instance tracker of GKInvItem
	(properties
		view 940
		loop 2
		cel 6
		message V_TRACKER
		signal IMMEDIATE
		noun N_TRACKER
	)
)

(instance crocMask of GKInvItem
	(properties
		view 940
		loop 1
		cel 6
		message V_CROC_MASK
		signal IMMEDIATE
		noun N_CROC_MASK
	)
)

(instance oil of GKInvItem
	(properties
		view 940
		loop 1
		cel 7
		message V_OIL
		signal IMMEDIATE
		noun N_OIL
	)
)

(instance musScale of GKInvItem
	(properties
		view 940
		message V_MUSEUM_SCALE
		signal IMMEDIATE
		noun N_MUSEUM_SCALE
	)
)

(instance lakeScale of GKInvItem
	(properties
		view 940
		message V_LAKE_SCALE
		signal IMMEDIATE
		noun N_LAKE_SCALE
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (== theVerb V_LOOK)
			(if (not (Btst fLookingAtLakeGrass))
				(inventory hide:)
				((ScriptID 23 0) doit: 70 0 0 151 75 2 1)
			else
				(messager say: NULL V_LOOK C_ALREADY_USING_GLASS 0 0 GK_INV)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance twoScales of GKInvItem
	(properties
		view 940
		loop 2
		cel 5
		message V_TWO_SCALES
		signal IMMEDIATE
		noun N_TWO_SCALES
	)
)

(instance tatooTrace of GKInvItem
	(properties
		view 940
		cel 9
		message V_TATTOO_TRACE
		signal IMMEDIATE
		noun N_TATTOO_TRACE
	)
)

(instance keyEnvelope of GKInvItem
	(properties
		view 940
		loop 2
		cel 9
		message V_KEY_ENVELOPE
		signal IMMEDIATE
		noun N_KEY_ENVELOPE
	)
)

(instance clay of GKInvItem
	(properties
		view 940
		cel 3
		message V_CLAY
		signal IMMEDIATE
		noun N_CLAY
	)
)

(instance sketchBook of GKInvItem
	(properties
		view 940
		cel 4
		message V_SKETCHBOOK
		signal IMMEDIATE
		noun N_SKETCHBOOK
	)
)

(instance phoEnvelope of GKInvItem
	(properties
		view 940
		cel 5
		message V_PHOTO_ENVELOPE
		signal IMMEDIATE
		noun N_PHOTO_ENVELOPE
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(V_OPEN
				(messager say: N_PHOTO_ENVELOPE V_OPEN 0 1 self GK_INV)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue &tmp pnv)
		(inventory eraseItems:)
		(= pnv (PicNotValid))
		(PicNotValid TRUE)
		(Graph GFillRect 10 10 107 141 1 myLowlightColor)
		(ego put: self)
		(self owner: 0)
		(self signal: (| (self signal?) DISABLED))
		(ego get: iGradPhoto)
		(ego get: iMurderPhoto)
		(inventory assignPages:)
		(inventory drawPage: (inventory curPage?))
		(PicNotValid pnv)
		(Graph GShowBits 10 10 107 141 1)
		(Graph GShowBits 19 142 98 156 1)
		(messager say: N_PHOTO_ENVELOPE V_OPEN NULL 2 0 GK_INV)
	)
)

(instance sLakePatter of GKInvItem
	(properties
		view 940
		cel 6
		message V_LAKE_PATTERN
		signal IMMEDIATE
		noun N_LAKE_PATTERN
	)
)

(instance VoodooCode1 of GKInvItem
	(properties
		view 940
		cel 10
		message V_VOODOO_CODE_1
		signal IMMEDIATE
		noun N_VOODOO_CODE_1
	)
)

(instance sVoodooCode of GKInvItem
	(properties
		view 940
		cel 11
		message V_VOODOO_CODE_2
		signal IMMEDIATE
		noun N_VOODOO_CODE_2
	)
)

(instance ritPhoto_ of GKInvItem
	(properties
		view 940
		cel 14
		message V_RITTER_PHOTO
		signal IMMEDIATE
		noun N_RITTER_PHOTO
		name "ritPhoto_"
	)
)

(instance lostDrawing of GKInvItem
	(properties
		view 940
		loop 1
		cel 2
		message V_LOST_DRAWING
		signal IMMEDIATE
		noun N_LOST_DRAWING
	)
)

(instance luckyDog_ of GKInvItem
	(properties
		view 940
		loop 1
		cel 3
		message V_LUCKY_DOG
		signal IMMEDIATE
		noun N_LUCKY_DOG
		name "luckyDog_"
	)
)

(instance snakeSkin_ of GKInvItem
	(properties
		view 940
		loop 1
		cel 4
		message V_SNAKE_SKIN
		signal IMMEDIATE
		noun N_SNAKE_SKIN
		name "snakeSkin_"
	)
)

(instance reconVeve_ of GKInvItem
	(properties
		view 940
		loop 1
		cel 10
		message V_RECON_VEVE
		signal IMMEDIATE
		noun N_RECON_VEVE
		name "reconVeve_"
	)
)

(instance collar_ of GKInvItem
	(properties
		view 940
		loop 1
		cel 11
		message V_COLLAR
		signal IMMEDIATE
		noun N_COLLAR
		name "collar_"
	)
)

(instance braceMold_ of GKInvItem
	(properties
		view 940
		loop 1
		cel 12
		message V_BRACELET_MOLD
		signal IMMEDIATE
		noun N_BRACELET_MOLD
		name "braceMold_"
	)
)

(instance braceRep_ of GKInvItem
	(properties
		view 940
		loop 1
		cel 13
		message V_BRACELET_REPLICA
		signal IMMEDIATE
		noun N_BRACELET_REPLICA	;was 37, but that's for the bracelet mold, not the replica
		name "braceRep_"
	)
)

(instance veil_ of GKInvItem
	(properties
		view 940
		loop 1
		cel 14
		message V_VEIL
		signal IMMEDIATE
		noun N_VEIL
		name "veil_"
	)
)

(instance fortScale_ of GKInvItem
	(properties
		view 940
		loop 1
		cel 15
		message V_FORTUNE_SCALE
		signal IMMEDIATE
		noun N_FORTUNE_SCALE
		name "fortScale_"
	)
)

(instance signalDev_ of GKInvItem
	(properties
		view 940
		loop 2
		cel 7
		message V_SIGNAL_DEVICE
		signal IMMEDIATE
		noun N_SIGNAL_DEVICE
		name "signalDev_"
	)
)

(instance brick_ of GKInvItem
	(properties
		view 940
		loop 2
		cel 8
		message V_BRICK
		signal IMMEDIATE
		noun N_BRICK
		name "brick_"
	)
)

(instance hartNotes_ of GKInvItem
	(properties
		view 940
		loop 2
		cel 4
		message V_HARTRIDGE_NOTES
		signal IMMEDIATE
		noun N_HARTRIDGE_NOTES
		name "hartNotes_"
	)
)

(instance wolfLetter_ of GKInvItem
	(properties
		view 940
		loop 2
		cel 2
		message V_WOLFGANG_LETTER
		signal IMMEDIATE
		noun N_WOLFGANG_LETTER
		name "wolfLetter_"
	)
)

(instance inventWind of SysWindow
	(properties
		top 25
		left 64
		bottom 143
		right 256
		type wCustom
		lsTop 25
		lsLeft 64
		lsBottom 143
		lsRight 256
	)
)

(instance cInvRead of Cursor
	(properties
		view 958
		loop 9
	)
)

(instance cInvOpen of Cursor
	(properties
		view 958
		loop 2
	)
)

(instance cInvOper of Cursor
	(properties
		view 958
		loop 1
	)
)

(instance cInvLook of Cursor
	(properties
		view 958
		loop 5
	)
)

(instance cInvHelp of Cursor
	(properties
		view 958
		loop 8
	)
)

(instance cInvSelect of Cursor
	(properties
		view 951
		loop 8
	)
)

(instance genericCursor of Cursor)
