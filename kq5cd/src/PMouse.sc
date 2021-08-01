;;; Sierra Script 1.0 - (do not remove this comment)
(script# 933)
(include sci.sh)
(use Main)
(use System)


(class PseudoMouse of Code
	(properties
		cursorInc 2
		minInc 2
		maxInc 20
		prevDir 0
		joyInc 5
	)
	
	(method (doit &tmp lastEventX lastEventY)
		(= lastEventX (lastEvent x?))
		(= lastEventY (lastEvent y?))
		(switch prevDir
			(1
				(= lastEventY (- lastEventY cursorInc))
			)
			(2
				(= lastEventX (+ lastEventX cursorInc))
				(= lastEventY (- lastEventY cursorInc))
			)
			(3
				(= lastEventX (+ lastEventX cursorInc))
			)
			(4
				(= lastEventX (+ lastEventX cursorInc))
				(= lastEventY (+ lastEventY cursorInc))
			)
			(5
				(= lastEventY (+ lastEventY cursorInc))
			)
			(6
				(= lastEventX (- lastEventX cursorInc))
				(= lastEventY (+ lastEventY cursorInc))
			)
			(7
				(= lastEventX (- lastEventX cursorInc))
			)
			(8
				(= lastEventX (- lastEventX cursorInc))
				(= lastEventY (- lastEventY cursorInc))
			)
		)
		(if global400
			(theGame setCursor: theCursor 1 lastEventX lastEventY)
		else
			(theGame setCursor: theCursor)
			(Intersections lastEventX lastEventY)
		)
	)
	
	(method (handleEvent event)
		(if
		(and (user canInput:) (& (event type?) evJOYSTICK))
			(= prevDir
				(if
					(or
						(not theIconBar)
						(!= ((theIconBar curIcon?) message?) 1)
					)
					(= prevDir (event message?))
				else
					(return)
				)
			)
			(= cursorInc
				(if (& (event type?) evKEYBOARD)
					(if (& (event modifiers?) emSHIFT) minInc else maxInc)
				else
					joyInc
				)
			)
			(cond 
				((& (event type?) evKEYBOARD)
					(if prevDir
						(self doit:)
					else
						(event claimed: 0)
						(return)
					)
				)
				(prevDir (self start:))
				(else (self stop:))
			)
			(event claimed: 1)
			(return)
		)
	)
	
	(method (start thePrevDir)
		(if argc (= prevDir thePrevDir))
		(theDoits add: self)
	)
	
	(method (stop)
		(= prevDir 0)
		(theDoits delete: self)
	)
)
