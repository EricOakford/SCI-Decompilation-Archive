;;; Sierra Script 1.0 - (do not remove this comment)
(script# 14)
(include game.sh)
(use Main)
(use PQRoom)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	theStairs 0
)

(instance theStairs of PQRoom
	(properties
		picture 14
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: demoScript)
	)
)

(instance demoScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(swatPups init:)
				(= cycles 3)
			)
			(1
				(swatPups init: setCycle: CycleTo 14 1 self)
			)
			(2
				(squeakSound number: 2035 loop: 1 play:)
				(swatPups setCycle: EndLoop self)
			)
			(3
				(curRoom newRoom: 16)
			)
		)
	)
)

(instance swatPups of Prop
	(properties
		x 114
		y 79
		priority 1
		fixPriority TRUE
		view 140
		cycleSpeed 10
	)
)

(instance squeakSound of Sound)
