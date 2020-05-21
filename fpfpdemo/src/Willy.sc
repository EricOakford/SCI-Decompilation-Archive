;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1806)
(include game.sh)
(use Main)
(use Talker)
(use Actor)

(public
	Willy 1
)

(instance Willy of Talker
	(properties
		x 5
		y 5
		view 1806
		loop 3
		talkWidth 150
		back 34
		textX 127
		textY 12
	)
	
	(method (init)
		(= font userFont)
		(super init: willyBust willyEyes willyMouth &rest)
	)
)

(instance willyBust of Prop
	(properties
		view 1806
		loop 1
	)
)

(instance willyEyes of Prop
	(properties
		nsTop 39
		nsLeft 44
		view 1806
		loop 2
	)
)

(instance willyMouth of Prop
	(properties
		nsTop 45
		nsLeft 18
		view 1806
	)
)
