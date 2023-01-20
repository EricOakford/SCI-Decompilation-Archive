;;; Sierra Script 1.0 - (do not remove this comment)
(script# 402)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Actor)
(use System)

(public
	romulanWarbird 0
)

(instance romulanWarbird of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: (ScriptID 49 3) self)
			)
			(1
				(Print 402 0)
				(self setScript: (ScriptID 49 5) self)
			)
			(2
				(warBird init: setCycle: Beg self)
			)
			(3 (= seconds 3))
			(4
				(warBird setCycle: End setMotion: MoveTo 31 4 self)
			)
			(5
				(warBird dispose:)
				(= seconds 3)
			)
			(6 (EgoDead 945 0 0 402 1))
		)
	)
)

(instance warBird of Actor
	(properties
		x 60
		y 69
		view 119
		priority 2
		signal $5810
		cycleSpeed 6
		moveSpeed 2
	)
)
