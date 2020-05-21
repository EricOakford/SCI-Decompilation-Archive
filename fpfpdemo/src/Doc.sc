;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1804)
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
		view 1804
		loop 3
		talkWidth 150
		back 34
		textX 127
		textY 12
	)
	
	(method (init)
		(= font userFont)
		(super init: docBust docEyes docMouth &rest)
	)
)

(instance docBust of Prop
	(properties
		view 1804
		loop 1
	)
)

(instance docEyes of Prop
	(properties
		nsTop 21
		nsLeft 36
		view 1804
		loop 2
	)
)

(instance docMouth of Prop
	(properties
		nsTop 32
		nsLeft 28
		view 1804
	)
)
