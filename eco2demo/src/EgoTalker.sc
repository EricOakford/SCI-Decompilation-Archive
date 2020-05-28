;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkEgo)
(include game.sh)
(use Main)
(use Eco2Talker)
(use Actor)

(public
	EgoTalker 0
)

(instance EgoTalker of Eco2Talker
	(properties
		x 5
		y 5
		view tlkEgo
		loop 3
		talkWidth 150
		back 8
		textX 115
		textY 12
	)
	
	(method (init)
		(= font userFont)
		(super init: egoBust egoEyes egoMouth &rest)
	)
)

(instance egoMouth of Prop
	(properties
		nsTop 44
		nsLeft 22
		view tlkEgo
	)
)

(instance egoBust of Prop
	(properties
		view tlkEgo
		loop 1
	)
)

(instance egoEyes of Prop
	(properties
		nsTop 34
		nsLeft 31
		view tlkEgo
		loop 2
	)
)
