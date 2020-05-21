;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1808)
(include game.sh)
(use Main)
(use Talker)
(use Actor)

(public
	Sam_Andreas 3
)

(instance Sam_Andreas of Talker
	(properties
		x 200
		y 71
		view 1805
		loop 3
		talkWidth 150
		back 34
		textX -185
		textY 12
		name "Sam Andreas"
	)
	
	(method (init)
		(= font userFont)
		(super
			init: samAndreasBust samAndreasEyes samAndreasMouth &rest
		)
	)
)

(instance samAndreasBust of Prop
	(properties
		view 1805
		loop 1
	)
)

(instance samAndreasEyes of Prop
	(properties
		nsTop 24
		nsLeft 43
		view 1805
		loop 2
	)
)

(instance samAndreasMouth of Prop
	(properties
		nsTop 36
		nsLeft 42
		view 1805
	)
)
