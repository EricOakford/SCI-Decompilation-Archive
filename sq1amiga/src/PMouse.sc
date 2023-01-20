;;; Sierra Script 1.0 - (do not remove this comment)
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

	(method (handleEvent event)
		(if (and (user canInput?) (& (event type?) direction))
			;; save location of mouse
			;; set direction to move mouse
			(= prevDir
				(if (or 
							(not theIconBar) 
							(!= ((theIconBar curIcon?) message?) verbWalk)
					)
					(= prevDir
						(event message?)
					)
				else
					(return)
				)
			)
			;; find out how far to move it
			(= cursorInc 
				(if (& (event type?) keyDown)
					(if (& (event modifiers?) shiftDown)
						minInc
					else
						maxInc
					)
				else ;; joyStick
					joyInc
				)
			)

			(if (& (event type?) keyDown)
				;; move once 
				(if prevDir
					(self doit:)
				else
					(event claimed:FALSE)
					(return FALSE)
				)
			else
				;; caused by joystick
				;; move until joystick is centered
				(if prevDir
					(self start:)
				else
					(self stop:)
				)
			)
			(return (event claimed:TRUE))
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
