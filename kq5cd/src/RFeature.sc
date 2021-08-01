;;; Sierra Script 1.0 - (do not remove this comment)
(script# 978)
(include sci.sh)
(use Feature)
(use Actor)


(class RFeature of Feature
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		description 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 26505
		lookStr 0
	)
)

(class RPicView of PicView
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		description 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 26505
		lookStr 0
		view 0
		loop 0
		cel 0
		priority -1
		signal $0000
		palette 0
	)
	
	(method (init &tmp temp0)
		(= temp0 (/ (CelWide view loop cel) 2))
		(= nsBottom (- y z))
		(= nsLeft (- x temp0))
		(= nsRight (+ x temp0))
		(= nsTop (- y (+ z (CelHigh view loop cel))))
		(super init:)
	)
)
