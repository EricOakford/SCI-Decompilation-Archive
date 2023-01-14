;;; Sierra Script 1.0 - (do not remove this comment)
(script# RFEATURE)
(include game.sh)
(use Feature)
(use Actor)


(class	RFeature	of Feature
	(properties
		nsTop 	0				;nowSeen - current rectangle
		nsLeft 	0
		nsBottom 0
		nsRight 	0
	)

); RFeature

(class RPicView	of PicView
	(properties
		nsTop 	0				;nowSeen - current rectangle
		nsLeft 	0				;assumes	origin in bottom middle
		nsBottom 0				;i.e. view editor default 
		nsRight 	0
	)

	(method (init &tmp halfWidth)
		(= halfWidth (/ (CelWide view loop cel) 2))
		(= nsBottom	(- y z))
		(= nsLeft 	(- x halfWidth))
		(= nsRight 	(+ x halfWidth))
		(= nsTop 	(- y (+ z (CelHigh view loop cel))))
		(super init:)
	); init

); RPicView
