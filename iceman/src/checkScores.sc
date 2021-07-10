;;; Sierra Script 1.0 - (do not remove this comment)
(script# 379)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use System)

(public
	checkScores 0
)

(instance checkScores of Script

	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 379)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 39 3) eachElementDo: #show dScore:)
				((ScriptID 39 2) dScore:)
				(= cycles 2)
			)
			(1
				(if
					(<
						((ScriptID 39 3) diceScore?)
						((ScriptID 39 2) diceScore?)
					)
					((ScriptID 39 8) show: cycleSpeed: 1 setCycle: Forward)
				else
					((ScriptID 39 9) loop: 0 cycleSpeed: 2 setCycle: Forward)
				)
				(= seconds 3)
			)
			(2
				(cond 
					(
						(>
							((ScriptID 39 3) diceScore?)
							((ScriptID 39 2) diceScore?)
						)
						(if (== ((ScriptID 39 0) notify: 2) 1)
							((ScriptID 39 0) notify: 2 0)
							((ScriptID 39 0) notify: 1 0)
							((ScriptID 39 0)
								notify: 4 (+ ((ScriptID 39 0) notify: 4) 1)
							)
							((ScriptID 39 0)
								notify: 3 (- ((ScriptID 39 0) notify: 3) 1)
							)
							(switch (Random 0 2)
								(0 (Print 379 2))
								(1 (Print 379 3))
								(2 (Print 379 4))
							)
						else
							(if ((ScriptID 39 0) notify: 0)
								(switch (Random 0 1)
									(0 (Print 379 5))
									(1 (Print 379 6))
								)
							else
								(switch (Random 0 2)
									(0 (Print 379 7))
									(1 (Print 379 8))
									(2 (Print 379 9))
								)
							)
							((ScriptID 39 0) notify: 2 1)
						)
						(if (not ((ScriptID 39 0) notify: 8))
							((ScriptID 39 15)
								setCel:
									(-
										((ScriptID 39 0) notify: 4)
										(if ((ScriptID 39 0) notify: 5) 0 else 1)
									)
							)
							((ScriptID 39 14) setCel: ((ScriptID 39 0) notify: 3))
						)
					)
					(
						(<
							((ScriptID 39 3) diceScore?)
							((ScriptID 39 2) diceScore?)
						)
						((ScriptID 39 8) setCycle: 0 hide:)
						(if (== ((ScriptID 39 0) notify: 1) 1)
							((ScriptID 39 0) notify: 1 0)
							((ScriptID 39 0) notify: 2 0)
							((ScriptID 39 0)
								notify: 4 (- ((ScriptID 39 0) notify: 4) 1)
							)
							(if ((ScriptID 39 0) notify: 5)
								((ScriptID 39 0)
									notify: 3 (+ ((ScriptID 39 0) notify: 3) 1)
								)
								(if (not ((ScriptID 39 0) notify: 8))
									((ScriptID 39 15)
										setCel:
											(-
												((ScriptID 39 0) notify: 4)
												(if ((ScriptID 39 0) notify: 5) 0 else 1)
											)
									)
									((ScriptID 39 14) setCel: ((ScriptID 39 0) notify: 3))
								)
								(switch (Random 0 2)
									(0 (Print 379 10))
									(1 (Print 379 11))
									(2 (Print 379 12))
								)
							else
								((ScriptID 39 0) notify: 5 1)
								((ScriptID 39 0) notify: 8 999)
								(ego get: iRumBottle)
								(Print 379 13)
							)
						else
							(if ((ScriptID 39 0) notify: 0)
								(switch (Random 0 2)
									(0 (Print 379 14))
									(1 (Print 379 15))
									(2 (Print 379 16))
								)
							else
								(switch (Random 0 1)
									(0 (Print 379 17))
									(1 (Print 379 18))
								)
							)
							((ScriptID 39 0) notify: 1 1)
						)
					)
					(else
						(switch (Random 0 2)
							(0 (Print 379 19))
							(1 (Print 379 20))
							(2 (Print 379 21))
						)
					)
				)
				(= cycles 2)
			)
			(3
				((ScriptID 39 0) notify: 13)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(Said 'affirmative')
					(or ((ScriptID 39 0) notify: 8) (== state 3))
				)
				(Print 379 0)
				((ScriptID 39 0) notify: 10)
				(self dispose:)
			)
			(
				(and
					(Said 'n')
					(or ((ScriptID 39 0) notify: 8) (== state 3))
				)
				(Print 379 1)
				(SetCursor theCursor TRUE 310 180)
				(curRoom newRoom: 32)
			)
		)
	)
)
