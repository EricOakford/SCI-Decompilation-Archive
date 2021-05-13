;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1898)
(include game.sh)
(use Main)
(use Talker)

(public
	Pulitzer 26
)

(instance Pulitzer of Narrator
	(properties
		x 100
		y 10
		talkWidth 150
		back 15
	)
	
	(method (init)
		(= font userFont)
		(super init: &rest)
	)
)
