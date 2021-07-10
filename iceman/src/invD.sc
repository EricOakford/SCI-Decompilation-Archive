;;; Sierra Script 1.0 - (do not remove this comment)
(script# 829)
(include game.sh)
(use Main)
(use Intrface)
(use Save)

(public
	invD 0
)

(local
	yesI
)
(instance invD of Dialog
	
	(method (init whom &tmp lastX lastY widest num el node obj)
		(= widest (= lastX (= lastY MARGIN)))
		(= num 0)
		(= node (inventory first:))
		(while node
			(if ((= obj (NodeValue node)) ownedBy: whom)
				(++ num)
				(self
					add:
						((= el (DText new:))
							value: obj
							text: (obj name?)
							nsLeft: lastX
							nsTop: lastY
							state: 3
							font: smallFont
							setSize:
							yourself:
						)
				)
				(if (< widest (- (el nsRight?) (el nsLeft?)))
					(= widest (- (el nsRight?) (el nsLeft?)))
				)
				(if
					(>
						(= lastY
							(+ lastY (- (el nsBottom?) (el nsTop?)) 1)
						)
						140
					)
					(= lastY 4)
					(= lastX (+ lastX widest 10))
					(= widest 0)
				)
			)
			(= node (inventory next: node))
		)
		(if (not num) (self dispose:) (return 0))
		(= window SysWindow)
		(self setSize:)
		(= yesI (DButton new:))
		(yesI
			text: {OK}
			setSize:
			moveTo: (- nsRight (+ 4 (yesI nsRight?))) nsBottom
		)
		(yesI move: (- (yesI nsLeft?) (yesI nsRight?)) 0)
		(self add: yesI setSize: center:)
		(return num)
	)
	
	(method (doit whom &tmp el)
		(if (not (self init: whom))
			(Print (inventory empty:))
			(return)
		)
		(self open: 4 15)
		(= el yesI)
		(repeat
			(if
				(or
					(not (= el (super doit: el)))
					(== el -1)
					(== el yesI)
				)
				(break)
			)
			((el value?) showSelf:)
		)
		(self dispose:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 829)
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
