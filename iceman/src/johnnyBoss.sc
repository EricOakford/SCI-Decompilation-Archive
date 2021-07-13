;;; Sierra Script 1.0 - (do not remove this comment)
(script# 377)
(include game.sh)
(use Main)
(use DiceRm)
(use Intrface)
(use Motion)
(use System)

(public
	johnnyBoss 0
	comeButton 1
	newRoundButton 2
	oldSaltRoll2 3
)

(local
	local0
)
(instance johnnyBoss of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (doit)
		(if (and local0 (not (-- local0)))
			(Print 377 0)
		)
		(super doit:)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 39 2) selected: 1)
				((ScriptID 39 9) loop: 0 cycleSpeed: 2 setCycle: Forward)
				(= seconds 3)
			)
			(1
				(switch (Random 0 2)
					(0 (Print 377 1))
					(1 (Print 377 2))
					(2 (Print 377 3))
				)
				(if (not ((ScriptID 39 0) notify: 9))
					(= local0 250)
				)
			)
			(2
				((ScriptID 39 0) notify: 9 1)
				((ScriptID 39 9) loop: 1 cel: 1 setCycle: 0)
				(if ((ScriptID 39 2) firstTrue: #selected)
					((ScriptID 39 4) setScript: (ScriptID 380 0) self)
				else
					(= cycles 2)
				)
			)
			(3)
			(4
				(client setScript: oldSaltRoll2)
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
			((and (MousedOn (ScriptID 39 13) event) (== state 3))
				(event claimed: TRUE)
				(client setScript: newRoundButton)
			)
			((and (MousedOn (ScriptID 39 12) event) (== state 1))
				(if
					(and
						(< 100 ((ScriptID 39 2) diceScore?))
						(< ((ScriptID 39 2) diceScore?) 200)
						(< (proc39_22 (ScriptID 39 2)) 3)
					)
					(Print 377 4)
				else
					((ScriptID 39 12) setCel: 0 setCycle: EndLoop)
					(self cue:)
				)
				(event claimed: TRUE)
			)
			((MousedOn (ScriptID 39 11) event)
				(event claimed: TRUE)
				(client setScript: comeButton)
			)
			((== (event type?) mouseDown)
				((ScriptID 39 2) firstTrue: #handleEvent event)
			)
		)
	)
)

(instance comeButton of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 39 11) setCel: 0 setCycle: EndLoop self)
				((ScriptID 39 8) show: cycleSpeed: 1 setCycle: Forward)
				(= seconds 3)
			)
			(1
				(switch (Random 0 2)
					(0
						(Print 377 5)
						(Print 377 6)
					)
					(1
						(Print 377 7)
					)
					(2
						(Print 377 8)
					)
				)
				(= cycles 2)
			)
			(2
				((ScriptID 39 8) setCycle: 0 hide:)
				(client setScript: oldSaltRoll2)
			)
		)
	)
)

(instance newRoundButton of Script

	(method (dispose)
		(super dispose:)
		(DisposeScript 377)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 39 13) setCel: 0 setCycle: EndLoop)
				((ScriptID 39 8) show: cycleSpeed: 1 setCycle: Forward)
				(= seconds 3)
			)
			(1
				(switch (Random 0 2)
					(0 (Print 377 9))
					(1 (Print 377 10))
					(2 (Print 377 11))
				)
				(= cycles 2)
			)
			(2
				((ScriptID 39 8) setCycle: 0 hide:)
				((ScriptID 39 0) notify: 10)
				(self dispose:)
			)
		)
	)
)

(instance oldSaltRoll2 of Script
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 377)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc39_1)
				(= cycles 2)
			)
			(1
				((ScriptID 39 6) setScript: (ScriptID 381 0) self)
			)
			(2
				((ScriptID 39 6) setScript: (ScriptID 379 0))
				(self dispose:)
			)
		)
	)
)
