;;; Sierra Script 1.0 - (do not remove this comment)
(script# 378)
(include game.sh)
(use Main)
(use DiceRm)
(use Intrface)
(use Motion)
(use System)

(public
	oldSaltBoss 0
	johnnyRoll2 1
)

(local
	local0
)
(instance oldSaltBoss of Script
	
	(method (dispose)
		(super dispose:)
		(if register (DisposeScript 378))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch (Random 0 2)
					(0 (Print 378 0))
					(1 (Print 378 1))
					(2 (Print 378 2))
				)
				(proc39_1)
				(= cycles 2)
			)
			(1
				(if ((ScriptID 39 3) selected?)
					((ScriptID 39 6) setScript: (ScriptID 381 0) self)
				else
					(= cycles 2)
				)
			)
			(2
				((ScriptID 39 9) loop: 0 cycleSpeed: 2 setCycle: Forward)
				(= seconds 3)
			)
			(3
				((ScriptID 39 9) loop: 1 cel: 1 setCycle: 0)
				((ScriptID 39 3) dScore:)
				((ScriptID 39 2) dScore:)
				(if
					(or
						(> ((ScriptID 39 3) diceScore?) 500)
						(and
							(> ((ScriptID 39 3) diceScore?) 400)
							(< ((ScriptID 39 2) diceScore?) 200)
						)
						(and
							(> ((ScriptID 39 3) diceScore?) 300)
							(< ((ScriptID 39 2) diceScore?) 200)
						)
						(and
							(> ((ScriptID 39 3) diceScore?) 200)
							(< ((ScriptID 39 2) diceScore?) 100)
						)
					)
					(switch (Random 0 2)
						(0 (Print 378 3))
						(1 (Print 378 4))
						(2 (Print 378 5))
					)
					(= register 0)
					(client setScript: johnnyRoll2)
				else
					(switch (Random 0 2)
						(0 (Print 378 6))
						(1 (Print 378 7))
						(2 (Print 378 8))
					)
					(= register 1)
					((ScriptID 39 0) notify: 10)
					(self dispose:)
				)
			)
		)
	)
)

(instance johnnyRoll2 of Script
	
	(method (init)
		(super init: &rest)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (doit)
		(if (and local0 (not (-- local0)))
			(Print 378 9)
		)
		(super doit:)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
		(DisposeScript 378)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not ((ScriptID 39 0) notify: 9)) (= local0 250))
			)
			(1
				((ScriptID 39 0) notify: 9 1)
				(if ((ScriptID 39 2) firstTrue: #selected)
					(= cycles 2)
				else
					((ScriptID 39 4) setScript: (ScriptID 379 0))
					(self dispose:)
				)
			)
			(2
				((ScriptID 39 4) setScript: (ScriptID 380 0) self)
			)
			(3
				((ScriptID 39 3) eachElementDo: #show)
				(= cycles 2)
			)
			(4
				((ScriptID 39 4) setScript: (ScriptID 379 0))
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event message?) ENTER)
				(== (event type?) keyDown)
			)
			(event type: mouseDown)
		)
		(cond 
			((super handleEvent: event))
			((== (event type?) direction)
				((ScriptID 39 10) handleEvent: event)
			)
			((and (MousedOn (ScriptID 39 12) event) (== state 0))
				((ScriptID 39 12) setCel: 0 setCycle: EndLoop)
				(event claimed: TRUE)
				(self cue:)
			)
			((== (event type?) mouseDown)
				((ScriptID 39 2) firstTrue: #handleEvent event)
			)
		)
	)
)
