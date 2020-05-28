;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkPaBird)
(include game.sh)
(use Main)
(use Eco2Talker)
(use Actor)

(public
	paBird 0
)

(instance paBird of Eco2Talker
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
		(super init: paBirdBust paBirdEyes paBirdMouth &rest)
	)
)

(instance paBirdMouth of Prop
	(properties
		nsTop 31
		nsLeft 47
		view tlkPaBird
		loop 4
	)
)

(instance paBirdBust of Prop
	(properties
		view tlkPaBird
		loop 1
	)
)

(instance paBirdEyes of Prop
	(properties
		nsTop 10
		nsLeft 75
		view tlkPaBird
		loop 5
	)
)
