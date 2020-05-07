;;; Sierra Script 1.0 - (do not remove this comment)
(script# 216)
(include game.sh)
(use Main)
(use System)

(public
	egoFight 0
)

(instance egoFight of Script
	(properties)
	
	(method (doit)
		(if
			(or
				(and (client fightLeft?) (> (client x?) 300))
				(and (not (client fightLeft?)) (< (client x?) 20))
			)
			(client gotBeat: (ScriptID 223 1))
		else
			(super doit:)
		)
	)
	
	(method (dispose)
		(directionHandler release:)
		(super dispose:)
		(DisposeScript 216)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client drawWeapons:)
				(directionHandler addToFront: client)
				(= cycles 1)
			)
			(1
				(client
					canFight: 1
					action: 0
					cycleSpeed: 0
					moveSpeed: 0
					stopUpd:
					getTired: 4 (ScriptID 223 1)
				)
			)
			(2
				(if ((client opponent?) endFight?)
					(client setScript: 0)
				else
					(= cycles 5)
				)
			)
			(3
				(ego hide:)
				(self changeState: 1)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or script (not (client canFight?)))
			(event claimed: TRUE)
			(return)
		)
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'look')
						(HighPrint 216 0)
						;Quit sightseeing and watch what you're doing!
					)
					((Said 'escape,escape,run')
						(HighPrint 216 1)
						;C'mon!  He's not going to KILL you... is he?
					)
					((Said 'cast')
						(HighPrint 216 2)
						;You're too busy!
					)
					((Said 'chat,ask')
						(HighPrint 216 3)
						;Later!  Later!
					)
					((Said 'fight,use[/blade]')
						(HighPrint 216 4)
						;You ARE!
					)
					((Said 'kill,chop')
						(HighPrint 216 5)
						;You're not fighting, you're PRACTICING!
						)
					(else
						(HighPrint 216 6)
						;Why would you want to do that?
						(event claimed: TRUE)
					)
				)
			)
			(direction
				(switch (event message?)
					(dirN
						(if (client canFight?)
							(client canFight: FALSE)
							(switch (Random 0 1)
								(0
									(self setScript: (Clone (ScriptID 217 2)) self client)
								)
								(1
									(self setScript: (Clone (ScriptID 217 3)) self client)
								)
							)
							(TrySkill WEAPON 0 20)
							(if (client tryAttack: (client opponent?))
								((client opponent?) getHit:)
							)
						else
							(event claimed: TRUE)
						)
					)
					(dirW
						(if (client canFight?)
							(TrySkill DODGE 0 20)
							(client canFight: FALSE)
							(self setScript: (Clone (ScriptID 217 0)) self client)
						else
							(event claimed: TRUE)
						)
					)
					(dirE
						(if (client canFight?)
							(TrySkill DODGE 0 20)
							(client canFight: FALSE)
							(self setScript: (Clone (ScriptID 217 1)) self client)
						else
							(event claimed: TRUE)
						)
					)
					(dirS
						(if (client canFight?)
							(client canFight: 0)
							(TrySkill PARRY 0 20)
							(if (== ((client opponent?) action?) 2)
								(self setScript: (Clone (ScriptID 217 5)) self client)
							else
								(self setScript: (Clone (ScriptID 217 4)) self client)
							)
						else
							(event claimed: TRUE)
						)
					)
				)
				(event claimed: TRUE)
			)
		)
	)
)
