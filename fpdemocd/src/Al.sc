;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkAl)
(include game.sh)
(use Main)
(use Talker)
(use Actor)

(public
	Al 31
)

(instance Al of Talker
	(properties
		x 5
		y 5
		view tlkAl
		loop 3
		disposeWhenDone 2
		talkWidth 150
		textX 133
		textY 12
	)
	
	(method (init)
		(= back myBackColor)
		(= font userFont)
		(super init: tBust tEyes tMouth &rest)
	)
)

(instance tBust of Prop
	(properties
		x 5
		y 5
		view tlkAl
		loop 1
	)
)

(instance tMouth of Prop
	(properties
		nsTop 37
		nsLeft 31
		view tlkAl
	)
)

(instance tEyes of Prop
	(properties
		nsTop 12
		nsLeft 23
		view tlkAl
		loop 2
	)
)
