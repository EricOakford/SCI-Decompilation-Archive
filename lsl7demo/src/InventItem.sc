;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64895)
(include sci.sh)
(use Main)
(use GenDialog)
(use PArray)
(use Array)
(use Print)
(use CueObj)
(use Actor)
(use System)


(class InventItem of Obj
	(properties
		scratch 0
		owner -1
		verb 0
		noun 0
		case 0
		vInventory 0
		lInventory 0
		cInventory 2
		vCloseup 0
		oVerbs 0
		oMyHandler 0
	)
	
	(method (dispose)
		(self moveTo: -1)
		(if oVerbs (oVerbs dispose:))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb &tmp temp0 temp1)
		(cond 
			(
				(and
					(!= theVerb 12)
					(!= theVerb 112)
					(!= theVerb 70)
					msgType
					(Message 0 11 noun theVerb case 1)
				)
				(messager say: noun theVerb case 0 0 11)
			)
			((= temp0 (GetNumMessages 16 0 theVerb 0))
				(= temp1 (proc64896_11 1 temp0))
				(messager say: 0 theVerb 0 temp1 0 16)
			)
			((= temp0 (GetNumMessages 16 0 69 0))
				(= temp1 (proc64896_11 1 temp0))
				(messager say: 0 69 0 temp1 0 16)
			)
			(else (MonoOut {No default verb handler messages}))
		)
	)
	
	(method (ownedBy param1)
		(return (== owner param1))
	)
	
	(method (moveTo theOwner theOMyHandlerFindFirstOpen &tmp oMyHandlerFindFirstOpen temp1)
		(if (== owner theOwner) (return (== owner theOwner)))
		(if
			(and
				(> argc 1)
				(< theOMyHandlerFindFirstOpen (oMyHandler invSlotsTot?))
			)
			(= oMyHandlerFindFirstOpen theOMyHandlerFindFirstOpen)
		else
			(= oMyHandlerFindFirstOpen (oMyHandler findFirstOpen:))
		)
		(if (== owner -2) (= gVerb 2) (= gInventItem 0))
		(if (== theOwner -2)
			(if gInventItem (gInventItem moveTo: -3))
			(= gVerb verb)
			(= gInventItem self)
		)
		(if (== owner -3) (oMyHandler removeItem: self))
		(if (== theOwner -3)
			(oMyHandler addItem: self oMyHandlerFindFirstOpen)
		)
		(= owner theOwner)
		(return self)
	)
	
	(method (addHotspotVerb)
		(if (not oVerbs) (= oVerbs (Set new:)))
		(oVerbs add: &rest)
	)
	
	(method (deleteHotspotVerb)
		(if oVerbs (oVerbs delete: &rest))
	)
	
	(method (getHotspotVerbList)
		(return oVerbs)
	)
	
	(method (addSelfToCursorList param1)
		(param1 add: verb vInventory)
	)
	
	(method (testHotspotVerb param1)
		(return
			(if oVerbs
				(return (oVerbs contains: param1))
			else
				(return 0)
			)
		)
	)
	
	(method (getName)
		(MakeMessageText 0 verb 0 1 11)
	)
)

(class InvSlot of View
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -5531
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $5021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		ioMine 0
		curSlot 0
		oMyHandler 0
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if ioMine
			(ioMine doVerb: theVerb &rest)
			(return)
		else
			(MonoOut {empty slot getting doVerbs. INV.SC DJM})
			(return)
		)
	)
	
	(method (testHotspotVerb param1)
		(if (and (!= ioMine 0) (== param1 2)) (return 1))
		(if
			(and
				(not ioMine)
				oMyHandler
				(oMyHandler getVerbItem: param1)
			)
			(return 1)
		)
		(return
			(if ioMine
				(return (ioMine testHotspotVerb: param1 &rest))
			else
				(return 0)
			)
		)
	)
	
	(method (getHotspotVerbList)
		(if (not ioMine) (return 0))
		(return (ioMine oVerbs?))
	)
	
	(method (getName)
		(if ioMine
			(ioMine getName: &rest)
			(return)
		else
			(MonoOut {trying to get name of empty slot. INV.SC DJM})
			(return)
		)
	)
	
	(method (setCurItem theIoMine theCurSlot)
		(if (> argc 0)
			(= ioMine theIoMine)
			(if ioMine
				(= view (theIoMine vInventory?))
				(= loop (theIoMine lInventory?))
				(= cel (theIoMine cInventory?))
				(= noun (theIoMine noun?))
			else
				(= view (oMyHandler blankID?))
				(= noun (= cel (= loop 0)))
			)
			(if (> argc 1) (= curSlot theCurSlot))
		)
	)
)

(class InvHandler of Obj
	(properties
		scratch 0
		viewSlotsX 2
		viewSlotsY 2
		invSlotsX 2
		invSlotsY 2
		screenLocX 0
		screenLocY 0
		slotIncX 15
		slotIncY 15
		blankID -1
		oAllHandledItems 0
		oInventItemSlots 0
		oScreenSlots 0
		viewSlotsTot 0
		invSlotsTot 0
		curULX 0
		curULY 0
		bScrollable 1
	)
	
	(method (init theTheCast param2 &tmp theCast temp1 temp2 theScreenLocX theScreenLocY temp5 oAllHandledItemsSize temp7)
		(if (not argc)
			(= theCast cast)
		else
			(= theCast theTheCast)
		)
		(if (> argc 1) (= temp7 param2) else (= temp7 0))
		(if temp7
			(= invSlotsTot (/ (temp7 size:) 2))
			(= viewSlotsTot invSlotsTot)
		else
			(= invSlotsTot (* invSlotsX invSlotsY))
			(= viewSlotsTot (* viewSlotsX viewSlotsY))
		)
		(if (== invSlotsTot viewSlotsTot)
			(= bScrollable 0)
		else
			(= bScrollable 1)
		)
		(= oInventItemSlots (IntArray new: invSlotsTot))
		(= oScreenSlots (List new:))
		(= oAllHandledItems (PArray new:))
		(if temp7
			(= temp1 0)
			(while (< temp1 viewSlotsTot)
				(oScreenSlots
					addToEnd:
						((InvSlot new:)
							view: blankID
							oMyHandler: self
							posn: (temp7 at: (* temp1 2)) (temp7 at: (+ (* temp1 2) 1))
							init: theCast
							curSlot: temp1
							yourself:
						)
				)
				(++ temp1)
			)
		else
			(= theScreenLocY screenLocY)
			(= temp1 0)
			(while (< temp1 viewSlotsY)
				(= theScreenLocX screenLocX)
				(= temp2 0)
				(while (< temp2 viewSlotsX)
					(oScreenSlots
						addToEnd:
							((InvSlot new:)
								view: blankID
								oMyHandler: self
								posn: theScreenLocX theScreenLocY
								init: theCast
								curSlot: (+ temp2 (* temp1 invSlotsX))
								yourself:
							)
					)
					(= theScreenLocX (+ theScreenLocX slotIncX))
					(++ temp2)
				)
				(= theScreenLocY (+ theScreenLocY slotIncY))
				(++ temp1)
			)
		)
		(self initItems:)
		(if oAllHandledItems
			(= oAllHandledItemsSize (oAllHandledItems size:))
			(= temp1 0)
			(while (< temp1 oAllHandledItemsSize)
				(if (= temp5 (oAllHandledItems at: temp1))
					(temp5 oMyHandler: self init:)
				)
				(++ temp1)
			)
		)
	)
	
	(method (dispose)
		(if oInventItemSlots (oInventItemSlots dispose:))
		(if oScreenSlots (oScreenSlots dispose:))
		(if oAllHandledItems (oAllHandledItems dispose:))
		(super dispose: &rest)
	)
	
	(method (initItems)
	)
	
	(method (getVerbItem param1 &tmp temp0 oAllHandledItemsSize temp2)
		(if oAllHandledItems
			(= oAllHandledItemsSize (oAllHandledItems size:))
			(= temp0 0)
			(while (< temp0 oAllHandledItemsSize)
				(if
					(and
						(= temp2 (oAllHandledItems at: temp0))
						(== param1 (temp2 verb?))
					)
					(return temp2)
				)
				(++ temp0)
			)
		)
		(return 0)
	)
	
	(method (findFirstOpen &tmp temp0)
		(if oInventItemSlots
			(= temp0 0)
			(while (< temp0 invSlotsTot)
				(if (not (oInventItemSlots at: temp0)) (return temp0))
				(++ temp0)
			)
		)
		(Prints
			{error -- can't find open inventory slot. djm, dminv.sc}
		)
		(return -1)
	)
	
	(method (scroll param1 param2)
		(self scrollTo: (+ curULX param1) (+ curULY param2))
	)
	
	(method (scrollTo theCurULX_2 theCurULY_2 &tmp theCurULX theCurULY)
		(if (not bScrollable) (return))
		(= theCurULX curULX)
		(= theCurULY curULY)
		(= curULX theCurULX_2)
		(= curULY theCurULY_2)
		(cond 
			((> (+ curULX viewSlotsX) invSlotsX) (= curULX (- invSlotsX viewSlotsX)))
			((< curULX 0) (= curULX 0))
			((> (+ curULY viewSlotsY) invSlotsY) (= curULY (- invSlotsY viewSlotsY)))
			((< curULY 0) (= curULY 0))
		)
		(if
		(or (!= theCurULX curULX) (!= theCurULY curULY))
			(self update:)
		)
	)
	
	(method (compress &tmp temp0 temp1 temp2)
		(= temp1 0)
		(while (< temp1 invSlotsTot)
			(if (= temp0 (oInventItemSlots at: temp1))
				(= temp2 0)
				(while (< temp2 temp1)
					(if (not (oInventItemSlots at: temp2))
						(oInventItemSlots at: temp2 temp0)
						(oInventItemSlots at: temp1 0)
					)
					(++ temp2)
				)
			)
			(++ temp1)
		)
	)
	
	(method (addItem param1 param2)
		(if (and (>= param2 0) (< param2 invSlotsTot))
			(oInventItemSlots at: param2 param1)
			(self updateItemSlot: param2)
		)
	)
	
	(method (removeItem param1 &tmp temp0)
		(= temp0 0)
		(while (< temp0 invSlotsTot)
			(if (== param1 (oInventItemSlots at: temp0))
				(oInventItemSlots at: temp0 0)
				(self updateItemSlot: temp0)
				(break)
			)
			(++ temp0)
		)
	)
	
	(method (get what)
		(oAllHandledItems at: what)
	)
	
	(method (add)
		(if oAllHandledItems (oAllHandledItems add: &rest))
	)
	
	(method (findNearestOpen param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5)
		(= temp2 30000)
		(= temp4 0)
		(if oScreenSlots
			(= temp0 0)
			(while (< temp0 viewSlotsTot)
				(if
					(or
						(not (= temp5 (oScreenSlots at: temp0)))
						(not (temp5 ioMine?))
					)
					(= temp4 1)
					(if
						(<
							(= temp1
								(GetDistance param1 param2 (temp5 x?) (temp5 y?))
							)
							temp2
						)
						(= temp2 temp1)
						(= temp3 temp0)
					)
				)
				(++ temp0)
			)
		)
		(return
			(if temp4
				(return (oScreenSlots at: temp3))
			else
				(Prints
					{error -- find nearest open can't find open inventory slot. djm, dminv.sc}
				)
				(return 0)
			)
		)
	)
	
	(method (update &tmp temp0 temp1 temp2 temp3 temp4 temp5)
		(if bScrollable
			(= temp0 0)
			(while (< temp0 viewSlotsY)
				(= temp1 0)
				(while (< temp1 viewSlotsX)
					(= temp2 (+ temp1 (* temp0 viewSlotsX)))
					(= temp3 (oScreenSlots at: temp2))
					(= temp4
						(+ temp1 curULX (* (+ temp0 curULY) invSlotsX))
					)
					(= temp5 (oInventItemSlots at: temp4))
					(temp3 setCurItem: temp5 temp4)
					(++ temp1)
				)
				(++ temp0)
			)
		else
			(= temp0 0)
			(while (< temp0 viewSlotsTot)
				(= temp3 (oScreenSlots at: temp0))
				(= temp5 (oInventItemSlots at: temp0))
				(temp3 setCurItem: temp5 temp4)
				(++ temp0)
			)
		)
	)
	
	(method (updateItemSlot param1 &tmp temp0 temp1 temp2)
		(if bScrollable
			(= temp1 (- (/ param1 invSlotsX) curULY))
			(= temp2
				(+
					(= temp0 (- (- param1 (* temp1 invSlotsX)) curULX))
					(* temp1 viewSlotsX)
				)
			)
		else
			(= temp2 param1)
		)
		((oScreenSlots at: temp2)
			setCurItem: (oInventItemSlots at: param1)
		)
	)
)

(class InventWellFeature of Feature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y -1
		z 0
		oMyHandler 0
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (not oMyHandler)
			(Prints {no handler for InventWellFeature. djm, inv.sc})
			(return)
		)
		(if
			(not
				(= temp0
					(oMyHandler
						findNearestOpen: (- mouseX (plane left:)) (- mouseY (plane top?))
					)
				)
			)
			(return)
		)
		(temp0 doVerb: theVerb &rest)
	)
	
	(method (testHotspotVerb param1)
		(return
			(if
			(and oMyHandler (oMyHandler getVerbItem: param1))
				(return 1)
			else
				(return 0)
			)
		)
	)
)
