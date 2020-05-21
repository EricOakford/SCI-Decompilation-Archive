;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1800)
(include game.sh)
(use Main)
(use Talker)
(use Actor)

(public
	Freddy 49
)

(instance Freddy of Talker
	(properties
		x 5
		y 5
		view 1800
		loop 3
		talkWidth 150
		back 34
		textX 127
		textY 12
	)
	
	(method (init)
		(= font userFont)
		(if (== curRoomNum 670)
			(= x 200)
			(= y 76)
			(= textX -185)
		)
		(super init: freddyBust freddyEyes freddyMouth &rest)
	)
)

(instance freddyBust of Prop
	(properties
		view 1800
		loop 1
	)
)

(instance freddyEyes of Prop
	(properties
		nsTop 30
		nsLeft 37
		view 1800
		loop 2
	)
)

(instance freddyMouth of Prop
	(properties
		nsTop 41
		nsLeft 35
		view 1800
	)
)
