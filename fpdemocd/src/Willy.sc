;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkWilly)
(include game.sh)
(use Main)
(use Talker)
(use Actor)

(public
	Willy 57
)

(instance Willy of Talker
	(properties
		x 5
		y 5
		view tlkWilly
		loop 3
		disposeWhenDone 2
		talkWidth 150
		textX 133
		textY 12
	)
	
	(method (init)
		(= back myBackColor)
		(= font userFont)
		(super init: willyBust willyEyes willyMouth &rest)
	)
)

(instance willyBust of Prop
	(properties
		view tlkWilly
		loop 1
	)
)

(instance willyEyes of Prop
	(properties
		nsTop 38
		nsLeft 44
		view tlkWilly
		loop 2
	)
)

(instance willyMouth of Prop
	(properties
		nsTop 46
		nsLeft 18
		view tlkWilly
	)
)
