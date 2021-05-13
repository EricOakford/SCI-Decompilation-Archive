;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1897)
(include game.sh)
(use Main)
(use Talker)

(public
	Sergeant 5
)

(instance Sergeant of Narrator
	(properties
		x 10
		y 70
		talkWidth 150
		back 15
	)
	
	(method (init)
		(= font userFont)
		(= showTitle TRUE)
		(super init: &rest)
	)
)
