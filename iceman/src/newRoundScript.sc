;;; Sierra Script 1.0 - (do not remove this comment)
(script# 375)
(include game.sh)
(use Intrface)
(use Motion)
(use System)

(public
	newRoundScript 0
)

(instance newRoundScript of Script
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not ((ScriptID 39 2) firstTrue: #rolling))
				(not ((ScriptID 39 3) firstTrue: #rolling))
				(== state 4)
			)
			(self cue:)
		)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 375)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 39 3)
					selected: TRUE
					eachElementDo: #select 1
					eachElementDo: #roll
					eachElementDo: #hide
				)
				((ScriptID 39 3) eachElementDo: #selected TRUE)
				((ScriptID 39 11) dispose:)
				((ScriptID 39 12) dispose:)
				((ScriptID 39 13) dispose:)
				((ScriptID 39 2)
					selected: 1
					eachElementDo: #select 1
					eachElementDo: #roll
					eachElementDo: #hide
				)
				((ScriptID 39 2) eachElementDo: #selected TRUE)
				(= cycles 2)
			)
			(1
				(switch (Random 0 1)
					(0
						(= register 1)
						((ScriptID 39 6) setScript: (ScriptID 381 0) self)
					)
					(1
						(= register 0)
						((ScriptID 39 4) setScript: (ScriptID 380 0) self)
					)
				)
			)
			(2
				(if register
					((ScriptID 39 3) eachElementDo: #show)
				)
				(= cycles 2)
			)
			(3
				(if register
					((ScriptID 39 4) setScript: (ScriptID 380 0) self)
				else
					((ScriptID 39 6) setScript: (ScriptID 381 0) self)
				)
			)
			(4
				(if (not register)
					((ScriptID 39 3) eachElementDo: #show)
				)
			)
			(5
				((ScriptID 39 3) dScore:)
				((ScriptID 39 2) dScore:)
				(cond 
					(
						(and
							((ScriptID 39 2) highPair?)
							((ScriptID 39 3) highPair?)
						)
						(cond 
							(
								(==
									((ScriptID 39 2) highPair?)
									((ScriptID 39 3) highPair?)
								)
								(self changeState: 6)
							)
							(
								(<
									((ScriptID 39 2) highPair?)
									((ScriptID 39 3) highPair?)
								)
								((ScriptID 39 0) notify: 0 0)
								((ScriptID 39 0) notify: 12)
								(self dispose:)
							)
							(
								(>
									((ScriptID 39 2) highPair?)
									((ScriptID 39 3) highPair?)
								)
								((ScriptID 39 0) notify: 0 1)
								((ScriptID 39 0) notify: 11)
								(self dispose:)
							)
						)
					)
					(
						(<
							((ScriptID 39 2) diceScore?)
							((ScriptID 39 3) diceScore?)
						)
						((ScriptID 39 0) notify: 0 0)
						((ScriptID 39 0) notify: 12)
						(self dispose:)
					)
					(
						(>
							((ScriptID 39 2) diceScore?)
							((ScriptID 39 3) diceScore?)
						)
						((ScriptID 39 0) notify: 0 1)
						((ScriptID 39 0) notify: 11)
						(self dispose:)
					)
					(
						(==
							((ScriptID 39 2) diceScore?)
							((ScriptID 39 3) diceScore?)
						)
						(self changeState: 6)
					)
				)
			)
			(6
				(if ((ScriptID 39 2) diceScore?)
					((ScriptID 39 8) show: cycleSpeed: 1 setCycle: Forward)
				else
					((ScriptID 39 9) loop: 0 cycleSpeed: 2 setCycle: Forward)
				)
				(= seconds 3)
			)
			(7
				(if ((ScriptID 39 2) diceScore?)
					(switch (Random 0 1)
						(0 (Print 375 0))
						(1 (Print 375 1))
					)
				else
					(switch (Random 0 1)
						(0 (Print 375 2))
						(1 (Print 375 3))
					)
				)
				(= cycles 2)
			)
			(8
				(if ((ScriptID 39 2) diceScore?)
					((ScriptID 39 8) setCycle: 0 hide:)
				else
					((ScriptID 39 9) loop: 1 cel: 1 setCycle: 0)
				)
				(self changeState: 0)
			)
		)
	)
)
