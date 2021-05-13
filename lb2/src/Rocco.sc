;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1902)
(include game.sh)
(use Main)
(use Talker)

(public
	Rocco 13
)

(instance Rocco of Narrator
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
