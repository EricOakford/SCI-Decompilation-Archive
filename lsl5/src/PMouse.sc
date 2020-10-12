;;; Sierra Script 1.0 - (do not remove this comment)
(script# PMOUSE)
(include game.sh)
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
	
	(method (handleEvent event)
		(if
		(and (user canInput:) (& (event type?) direction))
			(= prevDir
				(if
					(or
						(not theIconBar)
						(!= ((theIconBar curIcon?) message?) verbWalk)
					)
					(= prevDir (event message?))
				else
					(return)
				)
			)
			(= cursorInc
				(if (& (event type?) keyDown)
					(if (& (event modifiers?) shiftDown) minInc else maxInc)
				else
					joyInc
				)
			)
			(cond 
				((& (event type?) keyDown)
					(if prevDir
						(self doit:)
					else
						(event claimed: FALSE)
						(return)
					)
				)
				(prevDir (self start:))
				(else (self stop:))
			)
			(event claimed: TRUE)
			(return)
		)
	)

	(method (start dir)
		(if argc (= prevDir dir))
		(theDoits add: self)
	)
	
	(method (stop)
		(= prevDir 0)
		(theDoits delete: self)
	)

	(method (doit &tmp theX theY)
		(= theX (lastEvent x?))
		(= theY (lastEvent y?))
		(switch prevDir
			(dirN
				(= theY (- theY cursorInc))
			)
			(dirNE
				(= theX (+ theX cursorInc))
				(= theY (- theY cursorInc))
			)
			(dirE
				(= theX (+ theX cursorInc))
			)
			(dirSE
				(= theX (+ theX cursorInc))
				(= theY (+ theY cursorInc))
			)
			(dirS
				(= theY (+ theY cursorInc))
			)
			(dirSW
				(= theX (- theX cursorInc))
				(= theY (+ theY cursorInc))
			)
			(dirW
				(= theX (- theX cursorInc))
			)
			(dirNW
				(= theX (- theX cursorInc))
				(= theY (- theY cursorInc))
			)
		)
		(theGame setCursor: theCursor TRUE theX theY)
	)
)
