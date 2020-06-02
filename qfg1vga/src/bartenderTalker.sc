;;; Sierra Script 1.0 - (do not remove this comment)
(script# 342)
(include game.sh)
(use Main)
(use Talker)
(use Actor)

(public
	bartenderTalker 0
)

(instance bartenderTalker of Talker
	(properties
		x 10
		y 10
		view 1331
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2331)
		(PalVary PALVARYTARGET 2331)
		(kernel_128 1331)
		(= font userFont)
		(super
			init: bartenderBust bartenderEye bartenderMouth &rest
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(super dispose:)
	)
)

(instance bartenderBust of Prop
	(properties
		view 1331
	)
)

(instance bartenderMouth of Prop
	(properties
		nsTop 49
		nsLeft 31
		view 1331
		loop 1
	)
)

(instance bartenderEye of Prop
	(properties
		nsTop 24
		nsLeft 16
		view 1331
		loop 2
	)
)
