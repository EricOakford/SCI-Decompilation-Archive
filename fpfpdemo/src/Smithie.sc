;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1809)
(include game.sh)
(use Main)
(use Talker)
(use Actor)

(public
	Smithie 59
)

(instance Smithie of Talker
	(properties
		x 5
		y 10
		view 1803
		loop 3
		talkWidth 150
		back 34
		textX 127
		textY 12
	)
	
	(method (init)
		(= font userFont)
		(smithieBust setCel: (if (Btst fGotMole) 0 else 1))
		(super init: smithieBust smithieEyes smithieMouth &rest)
	)
)

(instance smithieBust of Prop
	(properties
		view 1803
		loop 1
	)
)

(instance smithieEyes of Prop
	(properties
		nsTop 31
		nsLeft 27
		view 1803
		loop 2
	)
)

(instance smithieMouth of Prop
	(properties
		nsTop 48
		nsLeft 24
		view 1803
	)
)
