;;; Sierra Script 1.0 - (do not remove this comment)
(script# SWAT_INTERFACE)
(include game.sh)
(use Main)
(use Button)
(use Print)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	aniProp 0
	rollOutButn 1
	coverButn 2
	scrollUpArrow 3
)

(class SwatInterface of Object
	(properties
		dftMessage 29
		curIcon 0
		curInvSet 0
		curInvItem 0
		numInvPages 0
		curInvPage 0
		curInvLevel 0
		curHandSignal 0
		curDoorEntry 0
	)
	
	(method (handleEvent event)
		(if (& (event type?) mouseDown)
			(event globalize:)
			(cond 
				(
					(and
						curInvItem
						(== curIcon curInvItem)
						(theInventoryPlane onMe: event)
						(event localize: theInventoryPlane)
						(not (curInvSet firstTrue: #onMe event))
					)
					(self setInvItem: 0)
					(self swapCursor:)
					(event claimed: 1)
				)
				(
					(or
						(& (event modifiers?) shiftDown)
						(& (event modifiers?) ctrlDown)
					)
					(self swapCursor:)
					(event claimed: 1)
				)
				(
					(or
						(and curHandSignal (== curIcon curHandSignal))
						(and curDoorEntry (== curIcon curDoorEntry))
					)
					(event type: (| (event type?) userEvent))
					(event message: (curIcon noun?))
				)
				(else
					(event type: (| (event type?) userEvent))
					(if (not curIcon)
						(event message: dftMessage)
					else
						(event message: (curInvItem verb?))
						(if
							(and
								(curInvItem gunData?)
								((curInvItem gunData?) evtHandler?)
							)
							(((curInvItem gunData?) evtHandler?) handleEvent: event)
						)
					)
				)
			)
		else
			(event claimed: FALSE)
			(return)
		)
	)
	
	(method (swapCursor)
		(cond 
			((and curInvItem (not curIcon)) (= curIcon curInvItem) (theGame setCursor: invCursor))
			(
			(and curHandSignal (OneOf curIcon 0 curInvItem))
				(= curIcon curHandSignal)
				(theGame setCursor: handSigCursor)
			)
			(
				(and
					curDoorEntry
					(OneOf curIcon 0 curInvItem curHandSignal)
				)
				(= curIcon curDoorEntry)
				(theGame setCursor: doorEntryCursor)
			)
			(else (= curIcon 0) (theGame setCursor: normalCursor))
		)
	)
	
	(method (setInvItem theCurInvItem)
		(if curInvItem (curInvItem show:))
		(= curIcon (= curInvItem theCurInvItem))
		(if curInvItem
			(invCursor
				view: (curInvItem cView?)
				loop: (curInvItem cLoop?)
				cel: (curInvItem cCel?)
			)
			(theGame setCursor: invCursor)
		)
	)
	
	(method (setInvSet theCurInvSet)
		(if (and argc theCurInvSet)
			(= curInvSet theCurInvSet)
		else
			(= curInvSet inventory)
		)
		(= curInvItem 0)
	)
	
	(method (nextInvPage param1 &tmp temp0 temp1 [temp2 3] theCurInvPage temp6)
		(if (not param1) (return))
		(= theCurInvPage
			(+ (= theCurInvPage curInvPage) param1)
		)
		(if
			(!=
				(= theCurInvPage
					(Min numInvPages (Max 0 theCurInvPage))
				)
				curInvPage
			)
			(= curInvPage theCurInvPage)
			(= temp6
				(/ (= temp1 (- (* param1 (= temp0 209)))) 8)
			)
			(self slidePlaneH: theInventoryPlane temp6 temp1)
		)
	)
	
	(method (newInvLevel param1 param2 &tmp temp0 temp1 temp2 theCurInvLevel temp4)
		(if (not param1) (return))
		(= temp4 0)
		(if (> argc 1) (= temp4 param2))
		(= temp1 209)
		(= temp0 25)
		(= theCurInvLevel curInvLevel)
		(= curInvLevel (+ curInvLevel param1))
		(if (>= curInvLevel 4) (= curInvLevel 0))
		(if (< curInvLevel 0) (= curInvLevel 3))
		(= temp2
			(- (* (- curInvLevel theCurInvLevel) (+ 2 temp0)))
		)
		(if curInvItem (curInvItem show:))
		(MovePlaneItems theInventoryPlane 0 temp2 1)
		(if curInvItem (curInvItem hide:))
		(if (== theCurInvLevel 1)
			(titlePlate posn: 0)
			(handSignals posn: 0)
			(doorEntries posn: 0)
			(UpdateScreenItem titlePlate)
			(UpdateScreenItem handSignals)
			(UpdateScreenItem lookButn)
			(UpdateScreenItem attentionButn)
			(UpdateScreenItem coverButn)
			(UpdateScreenItem holdButn)
			(UpdateScreenItem hurryButn)
			(UpdateScreenItem clearButn)
			(UpdateScreenItem suspectButn)
			(UpdateScreenItem stopButn)
			(UpdateScreenItem doorEntries)
			(UpdateScreenItem rollOutButn)
			(UpdateScreenItem slicePieButn)
		)
		(self
			rotateToLevel: param1 theCurInvLevel curInvLevel temp4
		)
		(UpdatePlane theInventoryPlane)
	)
	
	(method (slidePlaneH param1 param2 param3 &tmp temp0)
		(= temp0 0)
		(while (!= temp0 param3)
			(if
				(or
					(and (> param2 0) (> (+ param2 temp0) param3))
					(and (< param2 0) (< (+ param2 temp0) param3))
				)
				(= param2 (- param3 temp0))
			)
			(= temp0 (+ temp0 param2))
			(if curInvItem (curInvItem show:))
			(MovePlaneItems param1 param2 0 1)
			(if curInvItem (curInvItem hide:))
			(scrollLeftButn x: 0)
			(scrollRightButn x: 198)
			(UpdateScreenItem scrollLeftButn)
			(UpdateScreenItem scrollRightButn)
			(UpdatePlane param1)
			(FrameOut)
		)
	)
	
	(method (rotateToLevel param1 param2 param3 param4 &tmp temp0)
		(if (or (< argc 4) (not param4))
			(aniProp
				view: 10
				loop: 14
				posn: 0 0
				setPri: 200
				cycleSpeed: 12
				cel:
					(switch param2
						(0 (if (< param1 0) 11 else 0))
						(1 3)
						(2 6)
						(3 9)
					)
				init: theInventoryCast
				setCycle:
					CycleTo
					(switch param3
						(0 (if (> param1 0) 11 else 0))
						(1 3)
						(2 6)
						(3 9)
					)
					param1
					aniProp
			)
			(theDoits add: aniProp)
			(UpdateScreenItem aniProp)
			(flipSound number: 2010 loop: 1 play:)
		)
		(SetNowSeen lookButn)
		(SetNowSeen attentionButn)
		(SetNowSeen coverButn)
		(SetNowSeen holdButn)
		(SetNowSeen hurryButn)
		(SetNowSeen clearButn)
		(SetNowSeen suspectButn)
		(SetNowSeen stopButn)
		(SetNowSeen rollOutButn)
		(SetNowSeen slicePieButn)
	)
	
	(method (showInterface &tmp theInterfaceCurInvLevel)
		(if (not (Bset 3))
			(interfaceView init: theInterfaceCast)
			(controlButn init: theInterfaceCast)
			(scrollUpArrow active: (Btst 4) init: theInterfaceCast)
			(scrollDownArrow active: (Btst 4) init: theInterfaceCast)
			(titlePlate init: theInventoryCast)
			(scrollLeftButn init: theInventoryCast)
			(scrollRightButn init: theInventoryCast)
			(handSignals init: theInventoryCast)
			(lookButn init: theInventoryCast)
			(attentionButn init: theInventoryCast)
			(coverButn init: theInventoryCast)
			(holdButn init: theInventoryCast)
			(hurryButn init: theInventoryCast)
			(clearButn init: theInventoryCast)
			(suspectButn init: theInventoryCast)
			(stopButn init: theInventoryCast)
			(doorEntries init: theInventoryCast)
			(rollOutButn init: theInventoryCast)
			(slicePieButn init: theInventoryCast)
			(lashButn init: theInterfaceCast)
			(informationButn init: theInterfaceCast)
			(UpdatePlane theInterfacePlane)
		)
		(if (not (Btst 4))
			(if curInvItem (curInvItem show:))
			(if
				(or
					(!= (theInterface curInvLevel?) 0)
					(!= (theInterface curInvPage?) 0)
				)
				(MovePlaneItems
					theInventoryPlane
					(- (* (- (theInterface curInvPage?)) 209))
					(- (* (- 0 (theInterface curInvLevel?)) 27))
					1
				)
			)
			(= theInterfaceCurInvLevel (theInterface curInvLevel?))
			(theInterface curInvLevel: 0 curInvPage: 0)
			(= curIcon
				(= curInvItem (= curHandSignal (= curDoorEntry 0)))
			)
			(titlePlate x: 0)
			(handSignals x: 0)
			(doorEntries x: 0)
			(scrollLeftButn x: 0)
			(scrollRightButn x: 198)
			(UpdateScreenItem titlePlate)
			(UpdateScreenItem handSignals)
			(UpdateScreenItem doorEntries)
			(UpdateScreenItem scrollLeftButn)
			(UpdateScreenItem scrollRightButn)
			(Bclr 12)
			(Bclr 16)
			(if (!= theInterfaceCurInvLevel 0)
				(theInterface rotateToLevel: -1 theInterfaceCurInvLevel 0)
			)
		)
		(if (theInventoryCast contains: aniProp)
			(theDoits add: aniProp)
		)
		(if (and (Btst fScrollingEnabled) (not (Bset 12)))
			(theInterface newInvLevel: 1)
			(scrollUpArrow active: (not (Btst 16)))
			(scrollDownArrow active: (not (Btst 16)))
		)
		(UpdatePlane theInventoryPlane)
	)
	
	(method (startAlert param1)
		(if (or (not argc) param1)
			(alertButn view: 13 loop: 2 setCycle: Forward)
			(theDoits add: alertButn)
		else
			(alertButn view: 10 loop: 6 cel: 0 setCycle: 0)
			(theDoits delete: alertButn)
		)
	)
	
	(method (setHandSignal param1 theGScript &tmp temp0 temp1 temp2 temp3 temp4)
		(if (> argc 1) (= gScript theGScript))
		(= curHandSignal handSigCursor)
		(switch param1
			(1
				(handSigCursor view: 997 noun: 42)
			)
			(2
				(handSigCursor view: 997 noun: 43)
			)
			(3
				(handSigCursor view: 997 noun: 44)
				(= temp0 55)
				(= temp1 1)
				(= temp2 0)
				(= temp3 6)
				(= temp4 5)
			)
			(4
				(handSigCursor view: 997 noun: 45)
			)
			(5
				(handSigCursor view: 997 noun: 46)
			)
			(6
				(handSigCursor view: 997 noun: 47)
			)
			(7
				(handSigCursor view: 997 noun: 48)
			)
			(8
				(handSigCursor view: 997 noun: 49)
			)
			(else 
				(= curHandSignal 0)
				(= temp0 10)
				(= temp1 13)
				(= temp2 0)
				(= temp3 5)
				(= temp4 4)
			)
		)
		(theDoits add: informationButn)
		(informationButn
			view: temp0
			loop: temp1
			cel: temp2
			posn: temp3 temp4
			setCycle: EndLoop informationButn
		)
		(infoSound number: 2015 loop: 1 play:)
		(UpdateScreenItem informationButn)
	)
	
	(method (setDoorEntry param1)
		(= curDoorEntry doorEntryCursor)
		(switch param1
			(1
				(doorEntryCursor view: 997 noun: 50)
			)
			(2
				(doorEntryCursor view: 997 noun: 51)
			)
			(else  (= curDoorEntry 0))
		)
		(if curDoorEntry (theGame setCursor: doorEntryCursor))
	)
	
	(method (setInvPage param1 &tmp theCurInvPage)
		(if (== (= theCurInvPage curInvPage) param1)
			(return theCurInvPage)
		)
		(MovePlaneItems
			theInventoryPlane
			(- (* (- (theInterface curInvPage?)) 209))
			0
			1
		)
		(theInterface curInvPage: param1)
		(titlePlate x: 0)
		(handSignals x: 0)
		(doorEntries x: 0)
		(scrollLeftButn x: 0)
		(scrollRightButn x: 198)
		(UpdateScreenItem titlePlate)
		(UpdateScreenItem handSignals)
		(UpdateScreenItem doorEntries)
		(UpdateScreenItem scrollLeftButn)
		(UpdateScreenItem scrollRightButn)
		(return theCurInvPage)
	)
)

(instance aniProp of Prop
	(properties)
	
	(method (cue)
		(theDoits delete: self)
		(if gScript (gScript cue:) (= gScript 0))
		(self dispose:)
	)
)

(instance interfaceView of View
	(properties
		view 10
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance titlePlate of View
	(properties
		priority 100
		fixPriority 1
		view 10
		loop 14
	)
)

(instance alertButn of Prop
	(properties
		view 10
		loop 5
	)
	
	(method (init)
		(self posn: 288 6)
		(super init: &rest)
	)
)

(instance lashButn of Button
	(properties
		view 10
		loop 5
		cel 1
		upCel 1
	)
	
	(method (init)
		(self posn: 288 6)
		(super init: &rest)
	)
	
	(method (doVerb)
		(Prints {lash button})
	)
)

(instance controlButn of Button
	(properties
		view 10
		loop 4
	)
	
	(method (init)
		(self posn: 286 15)
		(super init: &rest)
	)
	
	(method (doVerb)
		(theGame controlPanel:)
	)
)

(instance scrollLeftButn of Button
	(properties
		view 10
		loop 2
	)
	
	(method (init)
		(self setPri: 150 posn: 0 27)
		(super init: &rest)
	)
	
	(method (doVerb)
		(if (Btst 4) (theInterface nextInvPage: -1))
	)
)

(instance scrollRightButn of Button
	(properties
		x 199
		view 10
		loop 3
	)
	
	(method (init)
		(self setPri: 150 posn: 198 27)
		(super init: &rest)
	)
	
	(method (doVerb)
		(if (Btst 4) (theInterface nextInvPage: 1))
	)
)

(instance scrollDownArrow of Button
	(properties
		x 267
		y 21
		view 10
		loop 6
	)
	
	(method (doVerb)
		(if (Btst 4) (theInterface newInvLevel: 1))
	)
)

(instance scrollUpArrow of Button
	(properties
		x 267
		y 5
		view 10
		loop 7
	)
	
	(method (doVerb)
		(if (Btst 4) (theInterface newInvLevel: -1))
	)
)

(instance handSignals of View
	(properties
		view 10
		loop 10
	)
	
	(method (init)
		(self posn: 0 54)
		(super init: &rest)
	)
	
	(method (posn)
		(super posn: &rest)
		(lookButn posn:)
		(attentionButn posn:)
		(coverButn posn:)
		(holdButn posn:)
		(hurryButn posn:)
		(clearButn posn:)
		(suspectButn posn:)
		(stopButn posn:)
	)
)

(instance lookButn of Button
	(properties
		upView 10
		upLoop 11
		downView 0
		downLoop 0
		downCel 0
		useRect 1
	)
	
	(method (init)
		(self posn:)
		(super init: &rest)
	)
	
	(method (doVerb)
		(theInterface setHandSignal: 1)
	)
	
	(method (posn)
		(if (not argc)
			(= x (+ (handSignals x?) 2))
			(= y (+ (handSignals y?) 2))
		else
			(super posn: &rest)
		)
	)
)

(instance attentionButn of Button
	(properties
		upView 10
		upLoop 11
		upCel 1
		downView 0
		downLoop 0
		downCel 0
		useRect 1
	)
	
	(method (init)
		(self posn:)
		(super init: &rest)
	)
	
	(method (doVerb)
		(theInterface setHandSignal: 2)
	)
	
	(method (posn)
		(if (not argc)
			(= x (+ (handSignals x?) 2))
			(= y (+ (handSignals y?) 14))
		else
			(super posn: &rest)
		)
	)
)

(instance coverButn of Button
	(properties
		upView 10
		upLoop 11
		upCel 2
		downView 0
		downLoop 0
		downCel 0
		useRect 1
	)
	
	(method (init)
		(self posn:)
		(super init: &rest)
	)
	
	(method (doVerb)
		(if gScript (gScript cue:) (= gScript 0))
		(theInterface setHandSignal: 3)
	)
	
	(method (posn)
		(if (not argc)
			(= x (+ (handSignals x?) 54))
			(= y (+ (handSignals y?) 2))
		else
			(super posn: &rest)
		)
	)
)

(instance holdButn of Button
	(properties
		upView 10
		upLoop 11
		upCel 3
		downView 0
		downLoop 0
		downCel 0
		useRect 1
	)
	
	(method (init)
		(self posn:)
		(super init: &rest)
	)
	
	(method (doVerb)
		(theInterface setHandSignal: 4)
	)
	
	(method (posn)
		(if (not argc)
			(= x (+ (handSignals x?) 54))
			(= y (+ (handSignals y?) 14))
		else
			(super posn: &rest)
		)
	)
)

(instance hurryButn of Button
	(properties
		upView 10
		upLoop 11
		upCel 4
		downView 0
		downLoop 0
		downCel 0
		useRect 1
	)
	
	(method (init)
		(self posn:)
		(super init: &rest)
	)
	
	(method (doVerb)
		(theInterface setHandSignal: 5)
	)
	
	(method (posn)
		(if (not argc)
			(= x (+ (handSignals x?) 106))
			(= y (+ (handSignals y?) 2))
		else
			(super posn: &rest)
		)
	)
)

(instance clearButn of Button
	(properties
		upView 10
		upLoop 11
		upCel 5
		downView 0
		downLoop 0
		downCel 0
		useRect 1
	)
	
	(method (init)
		(self posn:)
		(super init: &rest)
	)
	
	(method (doVerb)
		(theInterface setHandSignal: 6)
	)
	
	(method (posn)
		(if (not argc)
			(= x (+ (handSignals x?) 106))
			(= y (+ (handSignals y?) 14))
		else
			(super posn: &rest)
		)
	)
)

(instance suspectButn of Button
	(properties
		upView 10
		upLoop 11
		upCel 6
		downView 0
		downLoop 0
		downCel 0
		useRect 1
	)
	
	(method (init)
		(self posn:)
		(super init: &rest)
	)
	
	(method (doVerb)
		(theInterface setHandSignal: 7)
	)
	
	(method (posn)
		(if (not argc)
			(= x (+ (handSignals x?) 159))
			(= y (+ (handSignals y?) 2))
		else
			(super posn: &rest)
		)
	)
)

(instance stopButn of Button
	(properties
		upView 10
		upLoop 11
		upCel 7
		downView 0
		downLoop 0
		downCel 0
		useRect 1
	)
	
	(method (init)
		(self posn:)
		(super init: &rest)
	)
	
	(method (doVerb)
		(theInterface setHandSignal: 8)
	)
	
	(method (posn)
		(if (not argc)
			(= x (+ (handSignals x?) 159))
			(= y (+ (handSignals y?) 14))
		else
			(super posn: &rest)
		)
	)
)

(instance doorEntries of View
	(properties
		view 10
		loop 16
	)
	
	(method (init)
		(self posn: 0 81)
		(super init: &rest)
	)
	
	(method (posn)
		(super posn: &rest)
		(rollOutButn posn:)
		(slicePieButn posn:)
	)
)

(instance rollOutButn of Button
	(properties
		upView 10
		upLoop 15
		downView 0
		downLoop 0
		downCel 0
		useRect 1
	)
	
	(method (init)
		(self posn:)
		(super init: &rest)
	)
	
	(method (doVerb)
		(if gScript (gScript cue:) (= gScript 0))
	)
	
	(method (posn)
		(if (not argc)
			(= x (+ (doorEntries x?) 12))
			(= y (+ (doorEntries y?) 7))
		else
			(super posn: &rest)
		)
	)
)

(instance slicePieButn of Button
	(properties
		upView 10
		upLoop 15
		upCel 1
		downView 0
		downLoop 0
		downCel 0
		useRect 1
	)
	
	(method (init)
		(self posn:)
		(super init: &rest)
	)
	
	(method (doVerb)
		(theInterface setDoorEntry: 2)
	)
	
	(method (posn)
		(if (not argc)
			(= x (+ (doorEntries x?) 113))
			(= y (+ (doorEntries y?) 7))
		else
			(super posn: &rest)
		)
	)
)

(instance informationButn of Prop
	(properties
		view 10
		loop 13
		cel 11
	)
	
	(method (init)
		(self posn: 5 4)
		(super init: &rest)
	)
	
	(method (doVerb &tmp [temp0 4])
		(curRoom displayTravel:)
	)
	
	(method (cue)
		(theDoits delete: self)
		(if gScript (gScript cue:) (= gScript 0))
	)
)

(instance sniperButn of Button
	(properties
		view 10
		loop 10
	)
	
	(method (init)
		(self posn: 5 4)
		(super init: &rest)
	)
	
	(method (doVerb)
		(Prints {sniper assignment})
	)
)

(instance assaultButn of Button
	(properties
		view 10
		loop 11
	)
	
	(method (init)
		(self posn: 5 4)
		(super init: &rest)
	)
	
	(method (doVerb)
		(Prints {assaulter assignment})
	)
)

(instance flipSound of Sound
	(properties)
)

(instance infoSound of Sound
	(properties)
)
