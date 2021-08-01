;;; Sierra Script 1.0 - (do not remove this comment)
(script# 980)
(include sci.sh)
(use Main)


(procedure (localproc_018a param1 param2)
	(param1 initialized: 1)
	(param2 x: (param1 x?) y: (param1 y?))
	(if (param2 respondsTo: #nsRight)
		(param2
			nsRight: (param1 nsRight?)
			nsLeft: (param1 nsLeft?)
			nsBottom: (param1 nsBottom?)
			nsTop: (param1 nsTop?)
		)
	)
)

(class TalkingExtra
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
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		cycleSpeed 1
		script 0
		cycler 0
		timer 0
		detailLevel 0
		cycleType 0
		hesitation 0
		pauseCel 0
		minPause 10
		maxPause 30
		minCycles 8
		maxCycles 20
		counter 0
		state $ffff
		cycles 0
		surrogate 0
		initialized 0
	)
	
	(method (init theSurrogate)
		(if argc (= surrogate theSurrogate))
		(super init:)
		(self isExtra: 1)
	)
	
	(method (dispose)
		(if
		(and surrogate (not (features contains: surrogate)))
			(surrogate dispose:)
		)
		(super dispose:)
	)
	
	(method (handleEvent)
		(if surrogate
			(if (not initialized) (localproc_018a self surrogate))
			(surrogate handleEvent: &rest)
		else
			(super handleEvent: &rest)
		)
	)
	
	(method (addToPic)
		(if surrogate
			(if (not initialized) (localproc_018a self surrogate))
			(features add: surrogate)
		)
		(super addToPic: &rest)
	)
)
