;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkMaBird)
(include game.sh)
(use Main)
(use Eco2Talker)
(use Actor)

(public
	MaBird 0
)

(instance MaBird of Eco2Talker
	(properties
		x 192
		y 107
		view tlkPaBird
		loop 3
		talkWidth 150
		back 8
		textX -175
		textY 10
		textSpacer 20
	)
	
	(method (init)
		(= font userFont)
		(self setupTalker: 4)
		(super init: maBirdBust maBirdEyes maBirdMouth &rest)
	)
)

(instance maBirdMouth of Prop
	(properties
		nsTop 27
		nsLeft 12
		view tlkPaBird
	)
)

(instance maBirdBust of Prop
	(properties
		view tlkPaBird
		loop 1
	)
)

(instance maBirdEyes of Prop
	(properties
		nsTop 20
		nsLeft 24
		view tlkPaBird
		loop 2
	)
)
