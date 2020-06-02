;;; Sierra Script 1.0 - (do not remove this comment)
(script# KEYCURSOR) ;810
(include game.sh)
(use Main)
(use System)


(class CursorCoords of Object
	(properties
		cursorX 0
		cursorY 0
	)
)

(class InputList of Set
	
	(method (handleEvent event &tmp
			thisDistance currentObj inputObj bestAngle bestDistance t t2
			targetAngle thisAngle workingAngle evX evY curX curY)
		(if
			(and
				(<= mouseDown (event message?))
				(<= (event message?) keyUp)
			)
			(= targetAngle [targetAngles (event message?)])
			(= bestAngle 60)
			(= bestDistance 400)
			(= evX (event x?))
			(= evY (event y?))
			(= t (= inputObj 0))
			(while (< t size)
				(= curX ((= currentObj (self at: t)) cursorX?))
				(= curY (currentObj cursorY?))
				(if (or (!= evX curX) (!= evY curY))
					(= thisAngle (GetAngle evX evY curX curY))
					(= thisDistance (GetDistance evX evY curX curY))
					(if
					(> (= workingAngle (Abs (- targetAngle thisAngle))) 180)
						(= workingAngle (- 360 workingAngle))
					)
					(= t2 (<= workingAngle (+ bestAngle 10)))
					(if
						(or
							(<= workingAngle (- bestAngle 10))
							(and t2 (< (+ workingAngle thisDistance) (+ bestAngle bestDistance)))
							(and
								t2
								(== (+ workingAngle thisDistance) (+ bestAngle bestDistance))
								(< workingAngle bestAngle)
							)
						)
						(= bestAngle workingAngle)
						(= bestDistance thisDistance)
						(= inputObj currentObj)
					)
				)
				(++ t)
			)
			(if inputObj
				(Bclr fHideCursor)
				(theGame
					setCursor: 942 TRUE (inputObj cursorX?) (inputObj cursorY?)
				)
				(Bset fHideCursor)
			)
		)
	)
	
	(method (empty &tmp t)
		(if size
			(= t (- size 1))
			(while (>= t 0)
				((self at: t) dispose:)
				(self delete: (self at: t))
				(-- t)
			)
		)
	)
)
