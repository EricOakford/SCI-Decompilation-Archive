;;; Sierra Script 1.0 - (do not remove this comment)
(script# 397)
(include game.sh)
(use subMarine)
(use Submarine_806)
(use EgoDead)
(use LoadMany)
(use System)

(public
	russianSub 0
)

(local
	local0
)
(instance russianSub of Script

	(method (doit)
		(tempController doit:)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 396)
		(if (< state 8)
			(EgoDead 926 1 0 397 0)
		else
			(super dispose:)
			(DisposeScript 397)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 27 4) posn: 76 60)
				(LoadMany SCRIPT 399)
				(self setScript: (ScriptID 398) self)
			)
			(1 (= cycles 2))
			(2
				(self setScript: (ScriptID 399) self)
			)
			(3 (= cycles 2))
			(4
				(self setScript: (ScriptID 342) self)
			)
			(5 (= cycles 2))
			(6
				(self setScript: (ScriptID 341) self)
			)
			(7 (= cycles 2))
			(8
				(self setScript: (ScriptID 340) self)
			)
			(9
				(subMarine cue:)
				(self dispose:)
			)
		)
	)
)

(instance tempController of Code

	(method (doit &tmp submarineDepth)
		(if (not (umod (++ local0) 10))
			(Submarine
				waterTemp:
					(cond 
						((< (= submarineDepth (Submarine depth:)) 1100)
							(- 12 (/ submarineDepth 75))
						)
						((< (= submarineDepth (Submarine depth:)) 1300)
							(- 3 (Abs (/ (- submarineDepth 1200) 50)))
						)
						(else
							(- 12 (/ submarineDepth 100))
						)
					)
			)
		)
	)
)
