;;; Sierra Script 1.0 - (do not remove this comment)
(script# LAIBON_TALKER) ;42
(include game.sh) (include "42.shm")
(use GloryTalker)
(use Actor)

(public
	laibonTalker 0
	Laibon 1
)

(instance Laibon of Actor
	(properties
		noun N_LAIBON
		view 451
	)
)

(instance laibonTalker of GloryTalker
	(properties
		x 5
		y 15
		view 452
		loop 1
		talkWidth 140
		back 57
		textX 140
		textY 3
		frame 2
		backColor 26
	)
	
	(method (init)
		(super init: laibonBust laibonEyes laibonMouth &rest)
	)
)

(instance laibonMouth of Prop
	(properties
		nsTop 47
		nsLeft 49
		view 452
	)
)

(instance laibonEyes of Prop
	(properties
		nsTop 38
		nsLeft 45
		view 452
		loop 2
	)
)

(instance laibonBust of View
	(properties
		nsTop 30
		nsLeft 47
		view 452
		loop 3
	)
)
