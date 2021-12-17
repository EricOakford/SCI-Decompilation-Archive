;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15700)
(include sci.sh)
(use Main)
(use TPRoom)
(use ExitFeature)
(use Print)
(use Feature)

(public
	roEressdyFont 0
)

(instance foExit of CUExitFeature
	(properties
		unregisterDefaultHandler 15200
	)
)

(instance foFont of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= nsLeft (plane left:))
		(= nsRight (plane right:))
		(= nsTop (plane top?))
		(= nsBottom (plane bottom?))
		(self setVisibleRange: 3 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 3 1 1 0 0 15200)
			)
			(3
				(ego put: ((ScriptID 64001 0) get: 2))
				(ego get: ((ScriptID 64001 0) get: 9))
				((ScriptID 64017 0) set: 9)
				(curRoom newRoom: 15200)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance roEressdyFont of TPRoom
	(properties
		picture 15700
		east 15200
		west 15200
	)
	
	(method (init)
		(super init: &rest)
		(music1 pageSize: 15600)
		(theGame handsOn:)
		(foExit init:)
		(foFont init:)
		((ScriptID 64017 0) set: 34)
		(Prints {You are now staring at the eressdy font})
	)
)
