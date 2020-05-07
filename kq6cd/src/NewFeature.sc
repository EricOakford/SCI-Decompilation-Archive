;;; Sierra Script 1.0 - (do not remove this comment)
(script# 83)
(include sci.sh)
(use Main)
(use PolyPath)
(use Feature)


(class NewFeature of Feature
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		normal 1
	)
	
	(method (init)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not normal)
				(& (event type?) evVERB)
				(self onMe: event)
				(& _approachVerbs (approachCode doit: (event message?)))
			)
			(CueObj
				state: 0
				cycles: 0
				client: self
				theVerb: (event message?)
			)
			(self doSpecial: (CueObj theVerb?))
			(event claimed: 1)
			(return)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doSpecial)
		(self cue:)
	)
	
	(method (cue)
		(ego setMotion: PolyPath approachX approachY CueObj)
	)
)
