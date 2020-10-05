;;; Sierra Script 1.0 - (do not remove this comment)
(script# 8)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoRoom8 0
)

(local
	local0 =  1
)
(instance demoRoom8 of Room
	(properties
		picture 106
		style IRISIN
	)
	
	(method (init)
		(Load PICTURE 7801)
		(super init:)
		(scream play:)
		(self setScript: sCartoon)
	)
	
	(method (doit)
		(super doit:)
		(sparkle1 init:)
		(sparkle2 init:)
		(if local0 (Palette PALCycle 96 218 -1))
	)
)

(instance sCartoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(sparkle1 setCycle: EndLoop self)
			)
			(2 (= seconds 3))
			(3
				(sparkle2 setCycle: EndLoop self)
			)
			(4 (music fade:) (= seconds 2))
			(5
				(curRoom drawPic: 7801 FADEOUT)
				(= cycles 1)
			)
			(6 (= seconds 4))
			(7 (theGame restart:))
		)
	)
)

(instance sparkle1 of Prop
	(properties
		x 131
		y 29
		view 108
	)
)

(instance sparkle2 of Prop
	(properties
		x 60
		y 145
		view 108
		loop 1
	)
)

(instance scream of Sound
	(properties
		number 912
	)
)
