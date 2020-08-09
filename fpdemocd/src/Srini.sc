;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkSrini)
(include game.sh)
(use Main)
(use Talker)
(use Actor)

(public
	Srini 40
)

(instance Srini of Talker
	(properties
		x 5
		y 5
		view 1801
		loop 3
		disposeWhenDone 2
		talkWidth 150
		textX 133
		textY 12
	)
	
	(method (init)
		(= back myBackColor)
		(= font userFont)
		(super init: sriniBust sriniEyes sriniMouth &rest)
	)
)

(instance sriniBust of Prop
	(properties
		view 1801
		loop 1
	)
)

(instance sriniEyes of Prop
	(properties
		nsTop 45
		nsLeft 45
		view 1801
		loop 2
	)
)

(instance sriniMouth of Prop
	(properties
		nsTop 51
		nsLeft 41
		view 1801
	)
)
