;;; Sierra Script 1.0 - (do not remove this comment)
(script# 160)
(include game.sh)
(use Main)
(use Talker)
(use Actor)

(public
	meepTalker 0
)

(instance meepTalker of Talker
	(properties
		x 10
		y 10
		view 1060
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2060)
		(PalVary PALVARYTARGET 2060)
		(AssertPalette 1060)
		(= font userFont)
		(super init: meepBust meepEye meepMouth &rest)
		(meepLeg setCycle: Blink 100)
	)
	
	(method (doit)
		(super doit:)
		(self cycle: meepLeg)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(super dispose: &rest)
		(meepLeg setCycle: 0 dispose: delete:)
	)
	
	(method (show)
		(super show:)
	)
)

(instance meepBust of Prop
	(properties
		view 1060
	)
)

(instance meepMouth of Prop
	(properties
		nsTop 32
		nsLeft 39
		view 1060
		loop 1
	)
)

(instance meepLeg of Prop
	(properties
		nsTop 49
		nsLeft 58
		view 1060
		loop 3
		priority 14
		signal fixPriOn
	)
)

(instance meepEye of Prop
	(properties
		nsTop 17
		nsLeft 42
		view 1060
		loop 2
	)
)
