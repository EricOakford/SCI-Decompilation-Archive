;;; Sierra Script 1.0 - (do not remove this comment)
(script# 389)
(include game.sh)
(use Main)
(use Intrface)
(use pearlScript)
(use n396)
(use Submarine_806)
(use System)

(public
	pearl3Script 0
)

(local
	local0
)
(instance pearl3Script of Script
	
	(method (doit)
		(cond 
			((== state 1)
				(if (== (Submarine throttle?) 0)
					(self cue: 1)
				)
			)
			((== state 3)
				(cond 
					((umod (++ local0) 50))
					((== (Submarine throttle?) 0)
						(if (== (Submarine hSpeed?) 0)
							(self cue:)
						)
					)
					((< (++ register) 4)
						(SubPrint 3 389 7)
					)
					(else
						(PearlDeath 389 8)
					)
				)
			)
			((>= state 4)
				(cond 
					(modelessDialog)
					((!= (Submarine throttle?) 0)
						(SubPrint 3 389 9)
					)
					((> (Abs (Submarine pitch?)) 10)
						(SubPrint 3 389 10)
					)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(cond 
			((< state 4))
			((!= (Submarine throttle?) 0)
				(PearlDeath 389 11)
			)
			((> (Abs (Submarine pitch?)) 10)
				(PearlDeath 389 12)
			)
		)
		(super dispose:)
		(DisposeScript 389)
	)
	
	(method (changeState newState param2)
		(switch (= state newState)
			(0
				(Print 389 0)
				(Submarine latitude: 30 longitude: 170)
				((ScriptID 27 4) x: 44 y: 57)
				(Submarine lastH: (umod (+ (Submarine lastH?) 21) 24))
				((ScriptID 27 6) doit:)
				(= cycles 2)
			)
			(1
				(= start state)
				((ScriptID 373 1) active: 0)
				(SubPrint 5 389 1)
				(if (> (++ register) 3) (PearlDeath 389 2))
				(= seconds 10)
			)
			(2
				(if (and (>= argc 2) param2)
					(= seconds (= register 0))
					(self cue:)
				else
					(self init:)
				)
			)
			(3)
			(4
				(Print 389 3)
				(SubPrint 5 389 4)
				(= seconds 10)
			)
			(5
				(= start state)
				(= register 0)
				(= seconds 10)
			)
			(6
				(SubPrint 5 389 5)
				(if (> (++ register) 3)
					(PearlDeath 389 6)
				else
					(self init:)
				)
			)
		)
	)
)
