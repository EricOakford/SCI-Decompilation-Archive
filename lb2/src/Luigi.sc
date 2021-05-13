;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1907)
(include game.sh)
(use Main)
(use Talker)

(public
	Luigi 24
)

(instance Luigi of Narrator
	(properties
		x 100
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
