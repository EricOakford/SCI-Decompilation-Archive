;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1800)
(include sci.sh)
(use fileScr)
(use LarryTalker)
(use Actor)

(public
	proc1800_0 0
	You 1
)

(procedure (proc1800_0)
	(if (Btst 74)
		(lBust view: 1901)
		(lMouth view: 1901)
		(lEyes view: 1901)
	else
		(lBust view: 1900)
		(lMouth view: 1900)
		(lEyes view: 1900)
	)
	(super init: lMouth lBust lEyes)
)

(instance You of LarryTalker
	(properties
		x 10
		y 10
		showTitle 1
		back 74
		view 98
		loop 1
		textX 125
		textY 11
		winPosn 1
	)
	
	(method (init)
		(if (Btst 74)
			(lBust view: 1901)
			(lMouth view: 1901)
			(lEyes view: 1901)
		else
			(lBust view: 1900)
			(lMouth view: 1900)
			(lEyes view: 1900)
		)
		(super init: lMouth lBust lEyes)
	)
)

(instance lBust of View
	(properties
		x -36
		y 2
		view 1900
		loop 1
	)
)

(instance lMouth of Prop
	(properties
		x -28
		y 23
		view 1900
	)
)

(instance lEyes of Prop
	(properties
		x -23
		y 17
		view 1900
		loop 2
	)
)
