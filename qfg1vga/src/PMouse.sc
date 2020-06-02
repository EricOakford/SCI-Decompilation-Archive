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
	
	(method (handleEvent event &tmp eType eMsg eMod)
		(= eType (event type?))
		(= eMsg (event message?))
		(= eMod (event modifiers?))
		(return
			(if (& eType direction)
				(if
					(or
						(not theIconBar)
						(!= (theIconBar curIcon?) (theIconBar walkIconItem?))
					)
					(= prevDir eMsg)
				else
					(return 0)
				)
				(= cursorInc
					(if (& eType keyDown)
						(if (& eMod shiftDown) minInc else maxInc)
					else
						joyInc
					)
				)
				(cond 
					((& eType keyDown)
						(if prevDir
							(self doit:)
						else
							(return (event claimed: FALSE))
						)
					)
					(prevDir (self start:))
					(else (self stop:))
				)
				(return (event claimed: TRUE))
			else
				FALSE
			)
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
