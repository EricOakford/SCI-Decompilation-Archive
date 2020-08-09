;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkBilly)
(include game.sh)
(use Main)
(use Talker)
(use Actor)

(public
	Billy_ 1
)

(instance Billy_ of Talker
	(properties
		x 5
		y 5
		view tlkBilly
		loop 3
		disposeWhenDone 2
		talkWidth 150
		textX 133
		textY 12
		name "Billy_"
	)
	
	(method (init)
		(= back myBackColor)
		(= font userFont)
		(super init: billyBust billyEyes billyMouth &rest)
	)
)

(instance billyBust of Prop
	(properties
		view tlkBilly
		loop 1
	)
)

(instance billyEyes of Prop
	(properties
		nsTop 36
		nsLeft 34
		view tlkBilly
		loop 2
	)
)

(instance billyMouth of Prop
	(properties
		nsTop 44
		nsLeft 29
		view tlkBilly
	)
)
