;;; Sierra Script 1.0 - (do not remove this comment)
(script# FPINV)
(include game.sh) (include "15.shm")
(use Main)
(use Print)
(use BordWind)
(use IconBar)
(use Sound)
(use Invent)
(use System)

(public
	fpInv 0
	invWin 1
	resetInv 2
)

(local
	[local0 2]
	theItem
	theOwner
	numCols
	local5
)
(class FPIconItem of IconItem
	
	(method (ownedBy)
		(return TRUE)
	)
)

(class FPInvItem of InvItem
	(properties
		realOwner 0
	)
	
	(method (init)
		(= lowlightColor 19)
		(super init:)
	)
	
	(method (ownedBy who)
		(return
			(if (== owner who)
			else
				(== realOwner who)
			)
		)
	)
	
	(method (doVerb theVerb &tmp oldCur)
		(if
			(not
				(OneOf name
					{Med 1}
					{Med 2}
					{Med 3}
					{Incorrect Med}
					{Incorrect Med2}
					{Correct Rx}
				)
			)
			(narrator name: name showTitle: TRUE)
		)
		(cond 
			((== theVerb V_LOOK)
				(messager say: noun V_LOOK NULL 0 0 FPINV)
			)
			((not (OneOf theVerb V_WALK V_DO V_TALK V_HELP V_GAME))
				(sHuh play:)
				(Wait 5)
				(= oldCur (theGame setCursor: 5))
				(Wait 30)
				(theGame setCursor: oldCur)
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance resetInv of Code
	
	(method (doit who &tmp i what temp2 temp3)
		(= temp3 0)
		(= theOwner who)
		(= i (= temp2 0))
		(while (< i iLastInvItem)
			(if
				(or
					(== ((= what (inventory at: i)) owner?) theOwner)
					(== (what realOwner?) theOwner)
				)
				(++ temp3)
				(what realOwner: theOwner owner: 0)
				(if (<= (++ temp2) 8)
					(what owner: theOwner)
					(= theItem i)
				)
			)
			(++ i)
		)
		(if (<= temp3 8)
			(invMore loop: 6)
		else
			(invMore loop: 5)
		)
	)
)

(instance fpInv of Inventory
	(properties
		normalHeading 9
		empty 27
	)
	
	(method (init)
		(= inventory self)
		(self
			window: invWin
			helpIconItem: invHelp
			selectIcon: invSelect
			okButton: ok
			add:
				Beer
				Ladder
				Money
				Open_Beer
				invLook
				invHand
				invSelect
				invHelp
				invMore
				ok
			eachElementDo: #highlightColor 37
			eachElementDo: #lowlightColor 33
			eachElementDo: #modNum 15
			eachElementDo: #init
			state: NOCLICKHELP
		)
	)
	
	(method (hide)
		(= highlightedIcon 0)
		(super hide:)
	)
	
	(method (drawInvWindow whom selection
						&tmp
						numOwned tallestInv widestInv
						numIcons tallestIcon iconBarWidth
						cWide cHigh node obj invW invH
						iTop iLeft iBottom iRight
						numRows atX atY firstX i invWindow
						[buffer 50])
		(= numOwned
			(= tallestInv
				(= widestInv
					(= numIcons
						(= tallestIcon
							(= iconBarWidth 0)
						)
					)
				)
			)
		)
		(= node (self first:))
		(while node
			(if ((= obj (NodeValue node)) isKindOf: InvItem)
				(if (== (obj owner?) whom)
					(obj signal: (& (obj signal?) (~ DISABLED)))
					(++ numOwned)
					(if
						(>
							(= cWide (CelWide (obj view?) (obj loop?) (obj cel?)))
							widestInv
						)
						(= widestInv cWide)
					)
					(if
						(>
							(= cHigh (CelHigh (obj view?) (obj loop?) (obj cel?)))
							tallestInv
						)
						(= tallestInv cHigh)
					)
				else
					(obj signal: (| (obj signal?) DISABLED))
				)
			else
				(++ numIcons)
				(= iconBarWidth
					(+
						iconBarWidth
						(CelWide (obj view?) (obj loop?) (obj cel?))
					)
				)
				(if
					(>
						(= cHigh (CelHigh (obj view?) (obj loop?) (obj cel?)))
						tallestIcon
					)
					(= tallestIcon cHigh)
				)
			)
			(= node (self next: node))
		)
		(if (not numOwned)
			(Print addTextF: {%s %s} normalHeading empty init:)
			(return 0)
		)
		(if (> (* (= numRows (Sqrt numOwned)) numRows) numOwned)
			(-- numRows)
		)
		(if (> numRows 3)
			(= numRows 3)
		)
		(if (< (* numRows (= numCols (/ numOwned numRows))) numOwned)
			(++ numCols)
		)
		(= invW (Max (+ 4 iconBarWidth) (* numCols (+ 4 widestInv))))
		(= iTop (/ (- 190 (= invH (* numRows (+ 4 tallestInv)))) 2))
		(= iLeft (/ (- 320 invW) 2))
		(= iBottom (+ iTop invH))
		(= iRight (+ iLeft invW))
		(if (= invWindow (self window?))
			(invWindow
				top: iTop
				left: iLeft
				right: iRight
				bottom: iBottom
				open:
			)
		)
		(= i numCols)
		(if numOwned
			(= atY
				(+
					2
					(if (invWindow respondsTo: #yOffset)
						(invWindow yOffset?)
					else
						0
					)
				)
			)
			(= firstX
				(= atX
					(+
						4
						(if (invWindow respondsTo: #xOffset)
							(invWindow xOffset?)
						else
							0
						)
					)
				)
			)
			(= node (self first:))
			(while node
				(if
					(and
						(not (& ((= obj (NodeValue node)) signal?) DISABLED))
						(obj isKindOf: InvItem)
					)
					(if (not (& (obj signal?) $0080))
						(obj
							nsLeft:
								(+
									atX
									(/
										(-
											widestInv
											(= cWide (CelWide (obj view?) (obj loop?) (obj cel?)))
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
											(= cHigh (CelHigh (obj view?) (obj loop?) (obj cel?)) )
										)
										2
									)
								)
						)
						(obj
							nsRight: (+ (obj nsLeft?) cWide)
							nsBottom: (+ (obj nsTop?) cHigh)
						)
						(if (-- i)
							(= atX (+ atX widestInv))
						else
							(= i numCols)
							(= atY (+ atY tallestInv))
							(= atX firstX)
						)
					else
						(= atX (obj nsLeft?))
						(= atY (obj nsTop?))
					)
					(obj show:)
					(if (== obj selection) (obj highlight:))
				)
				(= node (self next: node))
			)
		)
		(= atX
			(/
				(- (- (invWindow right?) (invWindow left?)) iconBarWidth)
				2
			)
		)
		(= invH (- (invWindow bottom?) (invWindow top?)))
		(= atY 32767)
		(= node (self first:))
		(while node
			(if (not ((= obj (NodeValue node)) isKindOf: InvItem))
				(obj nsTop: 0)
				(= cWide
					(CelWide (obj view?) (obj loop?) (obj cel?))
				)
				(= cHigh
					(CelHigh (obj view?) (obj loop?) (obj cel?))
				)
				(if (not (& (obj signal?) $0080))
					(if (== atY 32767) (= atY (- invH cHigh)))
					(obj
						nsLeft: atX
						nsTop: atY
						nsBottom: invH
						nsRight: (+ atX cWide)
					)
				)
				(= atX (+ (obj nsLeft?) cWide))
				(= atY (obj nsTop?))
				(obj signal: (& (obj signal?) $fffb) show:)
			)
			(= node (self next: node))
		)
		(return 1)
	)
)

(instance invWin of InsetWindow
	(properties
		topBordHgt 28
		botBordHgt 5
	)
	
	(method (open &tmp theX node obj)
		(= theX 0)
		(= node (inventory first:))
		(while node
			(if
				(not
					((= obj (NodeValue node)) isKindOf: InvItem)
				)
				(= theX
					(+
						theX
						(CelWide (obj view?) (obj loop?) (obj cel?))
					)
				)
			)
			(= node (inventory next: node))
		)
		(super open:)
		(invLook nsLeft: (/ (- (- right left) theX) 2))
	)
)

(instance ok of FPIconItem
	(properties
		view 991
		loop 3
		cel 0
		cursor 999
		signal $0043
		noun 17
		helpVerb 7
	)
)

(instance invLook of FPIconItem
	(properties
		view 991
		loop 2
		cel 0
		cursor 1
		message 1
		signal $0081
		noun 16
		helpVerb 7
	)
)

(instance invHand of FPIconItem
	(properties
		view 991
		loop 0
		cel 0
		cursor 2
		message 4
		noun 14
		helpVerb 7
	)
)

(instance invHelp of FPIconItem
	(properties
		view 991
		loop 1
		cel 0
		cursor 9
		message 7
		signal $0003
		noun 15
		helpVerb 7
	)
)

(instance invSelect of FPIconItem
	(properties
		view 991
		loop 4
		cel 0
		cursor 999
		noun 18
		helpVerb 7
	)
)

(instance invMore of FPIconItem
	(properties
		view 991
		loop 5
		cel 0
		cursor 999
		signal $0003
		maskView 991
		noun 43
		helpVerb 7
	)
	
	(method (select &tmp i what obj temp3)
		(return
			(if (and (super select: &rest) (== loop 5))
				(= i 0)
				(while (<= i theItem)
					(if
					(== ((= obj (inventory at: i)) owner?) theOwner)
						(obj realOwner: theOwner owner: 0)
					)
					(++ i)
				)
				(= temp3 0)
				(= what theItem)
				(while (< i iLastInvItem)
					(if
						(and
							(==
								((= obj (inventory at: i)) realOwner?)
								theOwner
							)
							(<= (++ temp3) 8)
						)
						(obj owner: theOwner)
						(= theItem i)
					)
					(++ i)
				)
				(if (== what theItem)
					(resetInv doit: theOwner)
				)
				(inventory hide: highlightedIcon: 0 show: theOwner)
				(return 0)
			else
				0
			)
		)
	)
	
	(method (highlight)
		(if (== loop 5)
			(super highlight: &rest)
		)
	)
)

(instance Ladder of FPInvItem
	(properties
		view 67
		loop 1
		cursor 67
		message V_LADDER
		signal IMMEDIATE
		noun N_LADDER
	)
)

(instance Money of FPInvItem
	(properties
		view 62
		loop 1
		cursor 62
		message V_MONEY
		signal IMMEDIATE
		noun N_MONEY
	)
)

(instance Beer of FPInvItem
	(properties
		view 63
		loop 1
		cursor 63
		message V_BEER
		signal IMMEDIATE
		noun N_BEER
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_CHURCH_KEY
				(narrator name: name showTitle: TRUE)
				((inventory at: iBeer) owner: ego)
				(ego put: iBeer get: -1 iOpenBeer)
				(inventory curIcon: 0)
				((inventory at: iOpenBeer) owner: ego)
				(messager say: noun theVerb NULL 0 self FPINV)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
;;;	(method (cue)
;;;		(theGame points: 2)
;;;		(super cue:)
;;;	)
)

(instance Open_Beer of FPInvItem
	(properties
		view 64
		loop 1
		cursor 64
		message V_OPEN_BEER
		signal IMMEDIATE
		noun N_OPEN_BEER
		name "Open Beer"
	)
)

(instance sHuh of Sound
	(properties
		flags mNOPAUSE
		number 2990
	)
)
