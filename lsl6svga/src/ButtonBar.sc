;;; Sierra Script 1.0 - (do not remove this comment)
(script# 87)
(include sci.sh)
(use Main)
(use LL6InvItem)
(use IconBar)
(use User)

(public
	ButtonBar 0
)

(class ButtonBar of IconBar
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
		underBits 0
		oldMouseX 0
		oldMouseY 0
		curIcon 0
		highlightedIcon 0
		prevIcon 0
		curInvIcon 0
		useIconItem 0
		helpIconItem 0
		walkIconItem 0
		plane 0
		state $0000
		y 0
		x 0
		showInvAfter 0
	)
	
	(method (init &tmp temp0 temp1 temp2 temp3)
		(super init:)
		(plane setRect: 0 0 320 200 picture: -2)
		(UpdatePlane plane)
		(= temp0 0)
		(= temp1 (FirstNode elements))
		(while temp1
			(= temp2 (NextNode temp1))
			((= temp3 (NodeValue temp1))
				highlightColor: -1
				posn: temp0 0
			)
			(SetNowSeen temp3)
			(= temp0
				(+
					temp0
					(CelWide (temp3 view?) (temp3 loop?) (temp3 cel?))
				)
			)
			(= temp1 temp2)
		)
		(plane setSize:)
		(plane
			setRect:
				x
				y
				(+ temp0 (- x 4))
				(+ y (- (plane bottom?) (plane top?)))
			priority: (+ (GetHighPlanePri) 1)
		)
	)
	
	(method (show &tmp theTheCursor)
		(= theTheCursor theCursor)
		(super show: &rest)
		(theGame setCursor: theTheCursor)
	)
	
	(method (handleEvent event &tmp eventType theCurIcon)
		(= eventType (event type?))
		(if (not (theGame isHandsOff?))
			(if gGraphMenuBar (gGraphMenuBar handleEvent: event))
			(if global208 (global208 handleEvent: event))
		)
		(event localize: plane)
		(= theCurIcon (self onMe: event))
		(cond 
			((& state $0004))
			(
				(or
					(& eventType evMOUSEBUTTON)
					(and
						(& eventType evKEYBOARD)
						(== (event message?) KEY_RETURN)
					)
				)
				(cond 
					((& (event modifiers?) emSHIFT) (self advanceCurIcon:) (event claimed: 1))
					((& (event modifiers?) emCTRL)
						(if (user canControl:) (self swapCurIcon:))
						(event claimed: 1)
					)
					((event modifiers?) 0)
					(theCurIcon
						(if
							(and
								(self select: theCurIcon (& eventType evMOUSEBUTTON))
								(not (& (theCurIcon signal?) $0002))
							)
							(= curIcon theCurIcon)
							(if (curIcon cursorView?)
								(theGame setCursor: (curIcon getCursor:))
							else
								(event
									type: (curIcon type?)
									message: (curIcon message?)
								)
							)
						)
						(event plane: plane)
						(event claimed: 1)
					)
					(curIcon
						(event
							type: (curIcon type?)
							message: (curIcon message?)
						)
					)
				)
			)
		)
		(if (and (not (event claimed?)) (User canInput:))
			(event globalize:)
			(cond 
				(
					(and
						theCurIcon
						(or
							(!= theCurIcon (ScriptID 0 8))
							(and
								(== theCurIcon (ScriptID 0 8))
								(not (& ((ScriptID 0 8) signal?) $0004))
							)
						)
					)
					(if (not global209)
						(theGame setCursor: normalCursor)
						(= global209 1)
					)
				)
				(global209 (theGame setCursor: (self getCursor:)) (= global209 0))
			)
			(if inventory (inventory handleEvent: event))
		)
		(event localize: (cast plane?))
	)
	
	(method (advance)
		(return 0)
	)
	
	(method (retreat)
		(return 0)
	)
	
	(method (highlight)
		(return 0)
	)
	
	(method (swapCurIcon &tmp temp0)
		(cond 
			((& state $0004) (return))
			(
				(and
					(!= curIcon (= temp0 (NodeValue (self first:))))
					(not (& (temp0 signal?) $0004))
				)
				(= prevIcon curIcon)
				(if
					(==
						(= curIcon (NodeValue (self first:)))
						(ScriptID 0 11)
					)
					(= curIcon (ScriptID 0 4))
				)
			)
			(
				(and
					prevIcon
					(not (& (prevIcon signal?) $0004))
					(== (= curIcon prevIcon) (ScriptID 0 11))
				)
				(= curIcon (ScriptID 0 4))
			)
		)
		(theGame setCursor: (self getCursor:))
	)
	
	(method (advanceCurIcon)
		(if
			(or
				(not showInvAfter)
				(!= curIcon showInvAfter)
				(not (inventory curIcon?))
				(& state $0004)
			)
			(if (curIcon isKindOf: LL6InvItem)
				(= curIcon (ScriptID 0 8))
			)
			(super advanceCurIcon: &rest)
		else
			(theGame
				setCursor: ((= curIcon (inventory curIcon?)) getCursor:)
			)
		)
	)
	
	(method (onMe theObjOrX &tmp temp0)
		(return
			(if
				(and
					(==
						(= temp0 (self firstTrue: #onMe theObjOrX))
						(ScriptID 0 8)
					)
					(& ((ScriptID 0 8) signal?) $0004)
				)
				0
			else
				temp0
			)
		)
	)
)
