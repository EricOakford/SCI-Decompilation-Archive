;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkDoc)
(include game.sh)
(use Main)
(use Talker)
(use Actor)

(public
	Doc 48
)

(instance Doc of Talker
	(properties
		x 5
		y 5
		view tlkDoc
		loop 3
		disposeWhenDone 2
		talkWidth 150
		textX 133
		textY 12
	)
	
	(method (init)
		(= back myBackColor)
		(= font userFont)
		(super init: docBust docEyes docMouth &rest)
	)
)

(instance docBust of Prop
	(properties
		view tlkDoc
		loop 1
	)
)

(instance docEyes of Prop
	(properties
		nsTop 22
		nsLeft 42
		view tlkDoc
		loop 2
	)
)

(instance docMouth of Prop
	(properties
		nsTop 32
		nsLeft 28
		view tlkDoc
	)
)
