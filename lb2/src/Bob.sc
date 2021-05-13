;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1903)
(include game.sh)
(use Main)
(use Talker)

(public
	Bob 14
)

(instance Bob of Narrator
	(properties
		x 15
		y 120
		talkWidth 275
		modeless TRUE
		back 15
	)
	
	(method (init)
		(= font userFont)
		(= showTitle TRUE)
		(super init: &rest)
	)
)
