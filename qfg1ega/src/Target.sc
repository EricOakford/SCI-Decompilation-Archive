;;; Sierra Script 1.0 - (do not remove this comment)
(script# TARGET)
(include game.sh)
(use Actor)


(class TargActor of Actor
	(properties
		targDeltaX 0
		targDeltaY 0
	)
	
	(method (getHurt)
	)
)

(class TargFeature of Feature
	(properties
		targDeltaX 0
		targDeltaY 0
	)
	
	(method (getHurt)
	)
)
