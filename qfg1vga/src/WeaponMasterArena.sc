;;; Sierra Script 1.0 - (do not remove this comment)
(script# 223)
(include game.sh)
(use Main)
(use Procs)
(use Motion)
(use User)
(use System)

(public
	tooGood 0
	tooTired 1
)

(instance tooGood of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 304)
				((ScriptID 39 1) stop:)
				(= cycles 2)
			)
			(1
				(messager say: 8 0 3)
				((ScriptID 39 2)
					view: 639
					setLoop: (if (client fightLeft?) 5 else 4)
					setCel: 0
				)
				(= cycles 6)
			)
			(2
				(SkillUsed 4 50)
				(if (== heroType 0) (SolvePuzzle 608 10 0))
				(messager say: 8 0 10)
				((ScriptID 39 2)
					setLoop: (if (== ((ScriptID 39 2) loop?) 5) 1 else 0)
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Walk
					setMotion:
						MoveTo
						(if ((ScriptID 39 2) fightLeft?) 340 else -20)
						((ScriptID 39 2) y?)
						self
				)
				(Bclr 239)
				(Bset 249)
			)
			(3
				(Bclr 304)
				((client opponent?) dispose:)
				(ego
					posn: ((client opponent?) x?) ((client opponent?) y?)
					loop: (if ((ScriptID 39 2) fightLeft?) 0 else 1)
					cel: 0
					show:
				)
				(NormalEgo)
				(HandsOn)
				(User canInput: 1)
				(client dispose:)
			)
		)
	)
)

(instance tooTired of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset 304)
				((ScriptID 39 1) stop:)
				(= cycles 2)
			)
			(1
				(theGame setCursor: waitCursor 1)
				(client
					view: 514
					setLoop: 0
					cel: 0
					cycleSpeed: 6
					setCycle: EndLoop self
				)
			)
			(2
				(if (< [egoStats 15] 2)
					(messager say: 8 0 9 1 self)
				else
					(messager say: 8 0 13 1 self)
				)
			)
			(3
				(client setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(4 (= ticks 120))
			(5
				(client setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(6
				(client hide: forceUpd:)
				(= ticks 1)
			)
			(7
				(ego
					loop: (if (client fightLeft?) 1 else 0)
					posn: (client x?) (client y?)
					show:
				)
				(NormalEgo)
				(= ticks 20)
			)
			(8
				(if ((ScriptID 218 0) endFight?)
					(((ScriptID 218 0) opponent?)
						setScript: (ScriptID 224 0)
					)
				)
				(Bclr 304)
				(= ticks 14)
			)
			(9 (client dispose:))
		)
	)
)
