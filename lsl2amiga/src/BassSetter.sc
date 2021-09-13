;;; Sierra Script 1.0 - (do not remove this comment)
(script# BASS_SETTER)
(include game.sh)
(use System)


(class BassSetter of Code
	(properties
		radii 7
	)
	
	(method (doit theActor)
		(theActor brBottom: (+ (theActor y?) 1))
		(theActor brTop: (- (theActor brBottom?) (theActor yStep?)))
		(theActor brLeft: (- (theActor x?) radii))
		(theActor brRight: (+ (theActor x?) radii))
	)
)
