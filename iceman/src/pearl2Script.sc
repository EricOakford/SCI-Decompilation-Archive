;;; Sierra Script 1.0 - (do not remove this comment)
(script# 384)
(include game.sh)
(use Intrface)
(use pearlScript)
(use n396)
(use Submarine_806)
(use ChangeScriptState)
(use System)

(public
	pearl2Script 0
)

(instance pearl2Script of Script
	
	(method (doit)
		(switch state
			(3
				(if
					(or
						(> (Submarine absHeading:) 355)
						(< (Submarine absHeading:) 5)
					)
					(= cycles 1)
				)
			)
			(10
				(if (== (Submarine throttle?) 4) (self cue: 1))
			)
			(11
				(if (== (Submarine throttle?) 4) (self cue: 1))
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 384)
	)
	
	(method (changeState newState param2)
		(switch (= state newState)
			(0
				((ScriptID 373 1) init: 358 20 5)
				(SubPrint 5 384 0)
				(= cycles 50)
			)
			(1
				((ScriptID 373 3) init: 354 10 0)
				(= cycles 20)
			)
			(2
				(SubPrint 5 384 1)
				(ChangeScriptState self (ScriptID 373 4) 0 200)
			)
			(3
				((ScriptID 373 4) dispose:)
				((ScriptID 373 3) active: 0)
				((ScriptID 373 6) active: 0)
				(SubPrint 5 384 2)
				((ScriptID 373 2) init: 356 4 2)
			)
			(4
				(= start state)
				((ScriptID 373 2) active: 0)
				((ScriptID 373 6) init: 353 360 10)
				(SubPrint 5 384 3)
				(if (> (++ register) 3) (PearlDeath 384 4))
				(= cycles 70)
			)
			(5
				(ChangeScriptState self (ScriptID 373 5) self 1)
			)
			(6
				(if (and (>= argc 2) param2)
					(= register 0)
					(self cue:)
				else
					(self init:)
				)
			)
			(7
				(= start state)
				(SubPrint 5 384 5)
				(if (> (++ register) 3) (PearlDeath 384 4))
				(= cycles 10)
			)
			(8
				(ChangeScriptState self (ScriptID 373 5) self 1)
			)
			(9
				(if (and (>= argc 2) param2)
					(= register 0)
					(= cycles 1)
				else
					(self init:)
				)
			)
			(10
				((ScriptID 373 1) active: 0)
				(SubPrint 5 384 6)
				(= cycles 50)
			)
			(11
				(= start state)
				(SubPrint 5 384 7)
				(if (> (++ register) 3) (PearlDeath 384 8))
				(= cycles 55)
			)
			(12
				(if (and (>= argc 2) param2)
					(self cue:)
				else
					(self init:)
				)
			)
			(13 (self dispose:))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said '/attain,acknowledge,confirm<depth')
					(Said '[!*]<depth/correct,right')
					(Said '[!*]/depth/affirmative')
					(Said '[!*]/obtain<depth')
				)
				(cond 
					((> state 2) (Print 384 9))
					((< state 2) (Print 384 10))
					(
						(and
							(< 190 (Submarine depth:))
							(< (Submarine depth:) 210)
						)
						(self cue: 1)
					)
					(else (PearlDeath 384 11))
				)
			)
		)
	)
)
