;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG_INV)
(include game.sh)
(use Main)
(use Intrface)
(use Dialog)
(use Invent)

(public
	dInvD 0
	proc16_1 1
	proc16_2 2
)

(local
	newDButton
	[local1 2]
)
(procedure (proc16_1)
)

(procedure (proc16_2)
)

(instance dInvD of Dialog
	(properties)
	
	(method (init &tmp l t temp2 i newDText node obj)
		(= temp2 (= l (= t 4)))
		(= i 0)
		(= node (inventory first:))
		(while node
			(= obj (NodeValue node))
			(++ i)
			(if (obj isKindOf: InvItem)
				(self
					add:
						((= newDText (DText new:))
							value: obj
							text: (obj name?)
							nsLeft: l
							nsTop: t
							state: 3
							font: smallFont
							setSize:
							yourself:
						)
				)
			)
			(if
			(< temp2 (- (newDText nsRight?) (newDText nsLeft?)))
				(= temp2 (- (newDText nsRight?) (newDText nsLeft?)))
			)
			(if
				(>
					(= t
						(+ t (- (newDText nsBottom?) (newDText nsTop?)) 1)
					)
					140
				)
				(= t 4)
				(= l (+ l temp2 10))
				(= temp2 0)
			)
			(= node (inventory next: node))
		)
		(= window systemWindow)
		(self setSize:)
		(= newDButton (DButton new:))
		(newDButton
			text: {All Done!}
			setSize:
			moveTo: (- nsRight (+ 4 (newDButton nsRight?))) nsBottom
		)
		(newDButton
			move: (- (newDButton nsLeft?) (newDButton nsRight?)) 0
		)
		(self add: newDButton setSize: center:)
		(return i)
	)
	
	(method (doit &tmp theNewDButton)
		(self init:)
		(self open: 4 15)
		(= theNewDButton newDButton)
		(repeat
			(if
				(or
					(not (= theNewDButton (super doit: theNewDButton)))
					(== theNewDButton -1)
					(== theNewDButton newDButton)
				)
				(break)
			)
			(ego get: (inventory indexOf: (theNewDButton value?)))
		)
		(self eachElementDo: #dispose 1 dispose:)
	)
	
	(method (handleEvent event &tmp eMsg eType)
		(= eMsg (event message?))
		(switch (= eType (event type?))
			(keyDown
				(switch eMsg
					(UPARROW
						(= eMsg SHIFTTAB)
					)
					(DOWNARROW
						(= eMsg TAB)
					)
				)
			)
			(direction
				(switch eMsg
					(dirN
						(= eMsg SHIFTTAB)
						(= eType keyDown)
					)
					(dirS
						(= eMsg TAB)
						(= eType keyDown)
					)
				)
			)
		)
		(event type: eType message: eMsg)
		(super handleEvent: event)
	)
)
