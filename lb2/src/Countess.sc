;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1884)
(include game.sh)
(use Main)
(use Talker)
(use Actor)

(public
	Countess 29
)

(instance Countess of Talker
	(properties
		x 5
		y 5
		view 1884
		loop 3
		talkWidth 150
		back 15
		textX 120
		textY 12
	)
	
	(method (init)
		(if (Btst fCountessDead)
			(countessBust cel: 1)
			(countessEyes setLoop: 3)
			(countessMouth setLoop: 3)
		)
		(= font userFont)
		(super
			init: countessBust countessEyes countessMouth &rest
		)
	)
)

(instance countessBust of Prop
	(properties
		view 1884
		loop 1
	)
)

(instance countessEyes of Prop
	(properties
		nsTop 35
		nsLeft 42
		view 1884
		loop 2
	)
)

(instance countessMouth of Prop
	(properties
		nsTop 51
		nsLeft 42
		view 1884
	)
)
