;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	PMOUSE.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	J. Mark Hood
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	This class handles moving the cursor using the direction keys or joystick
;;;;
;;;;	Classes:
;;;;		PseudoMouse


(script# PMOUSE)
(include game.sh)
(use Main)
(use System)


(class PseudoMouse of Code
	(properties
		cursorInc	2
		minInc		2
		maxInc		20
		prevDir		0
		joyInc		5
	)

;;;	(methods
;;;		handleEvent
;;;		doit
;;;		start
;;;		stop
;;;	)

	(method (handleEvent event &tmp eType eMsg eMod)
		(= eType (event type?))
		(= eMsg	(event message?))
		(= eMod	(event modifiers?))

		(if (& eType direction)
			(= prevDir eMsg)

			; Find out how far to move it
			(= cursorInc 
				(if (& eType keyDown)
					(if (& eMod shiftDown)
						minInc
					else
						maxInc
					)
				else
					joyInc
				)
			)

			(if (& eType keyDown)
				; Move once 
				(if prevDir
					(self doit:)
				else
					(return (event claimed: FALSE))
				)
			else
				; Caused by joystick - move until joystick is centered
				(if prevDir
					(self start:)
				else
					(self stop:)
				)
			)
			(return (event claimed: TRUE))
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
				(-= theY cursorInc)
			)
			(dirNE
				(+= theX cursorInc)							 
				(-= theY cursorInc)
			)
			(dirE													 
				(+= theX cursorInc)							 
			)
			(dirSE
				(+= theX cursorInc)							 
				(+= theY cursorInc)							 
			)
			(dirS
				(+= theY cursorInc)
			)
			(dirSW
				(-= theX cursorInc)							 
				(+= theY cursorInc)
			)
			(dirW													 
				(-= theX cursorInc)							 
			)														 
			(dirNW												 
				(-= theX cursorInc)							 
				(-= theY cursorInc)							 
			)
		)
		(theGame setCursor: theCursor TRUE theX theY)
	)

)
