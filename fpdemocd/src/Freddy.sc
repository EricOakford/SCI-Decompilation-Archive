;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkFreddy)
(include game.sh)
(use Main)
(use Talker)
(use Actor)

(public
	Freddy 49
)

(instance Freddy of Talker
	(properties
		x 205
		y 5
		view tlkFreddy
		loop 3
		disposeWhenDone 2
		talkWidth 150
		textX -185
		textY 12
	)
	
	(method (init)
		(= back myBackColor)
		(= font userFont)
		(super init: freddyBust freddyEyes freddyMouth &rest)
	)
)

(instance freddyBust of Prop
	(properties
		view tlkFreddy
		loop 1
	)
)

(instance freddyEyes of Prop
	(properties
		nsTop 33
		nsLeft 40
		view tlkFreddy
		loop 2
	)
)

(instance freddyMouth of Prop
	(properties
		nsTop 46
		nsLeft 38
		view tlkFreddy
	)
)
