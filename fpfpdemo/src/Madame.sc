;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1817)
(include game.sh)
(use Main)
(use Talker)
(use Actor)

(public
	Madame 47
)

(instance Madame of Talker
	(properties
		x 5
		y 5
		view 1802
		loop 3
		talkWidth 150
		back 34
		textX 127
		textY 12
	)
	
	(method (init)
		(= font userFont)
		(super init: madameBust madameEyes madameMouth &rest)
	)
)

(instance madameBust of Prop
	(properties
		view 1802
		loop 1
	)
)

(instance madameEyes of Prop
	(properties
		nsTop 38
		nsLeft 34
		view 1802
		loop 2
	)
)

(instance madameMouth of Prop
	(properties
		nsTop 61
		nsLeft 38
		view 1802
	)
)
