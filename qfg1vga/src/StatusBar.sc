;;; Sierra Script 1.0 - (do not remove this comment)
(script# STATUSBAR)
(include game.sh)
(use Actor)
(use System)


(class StatusBar of Object
	(properties
		value 0
		max 1000
		x 1000
		y 1000
		priority 15
		titleCel 0
		lineObj 0
		theLoop 0
	)
	
	(method (init)
		(Load VIEW 803)
		(super init: &rest)
		((= lineObj (View new:))
			ignoreActors:
			view: 803
			posn: x y 3
			stopUpd:
			init:
		)
		(self draw:)
	)
	
	(method (dispose)
		(if (IsObject lineObj) (lineObj dispose:))
		(super dispose:)
	)
	
	(method (draw &tmp valCel scaledValue)
		(if (< max 1) (= max 1))
		(if (> (= scaledValue value) max) (= scaledValue max))
		(= valCel
			(/
				(+ (= scaledValue (/ (+ (* scaledValue 100) max -1) max)) 9)
				10
			)
		)
		(lineObj
			loop: 1
			cel: valCel
			posn: (+ x 2) y
			forceUpd:
			stopUpd:
		)
	)
)
