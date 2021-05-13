;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1906)
(include game.sh)
(use Main)
(use Talker)

(public
	Lesbian_Flapper 8
)

(instance Lesbian_Flapper of Narrator
	(properties
		x 100
		y 50
		talkWidth 150
		back 15
		name "Lesbian Flapper"
	)
	
	(method (init)
		(= font userFont)
		(super init: &rest)
	)
)
