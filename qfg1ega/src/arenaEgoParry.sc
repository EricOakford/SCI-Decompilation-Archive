;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA_PARRY)
(include game.sh)
(use Main)
(use System)

(public
	egoParry 0
)

(local
	theWarrior
)
(instance egoParry of Script
	(properties)
	
	(method (init)
		(= theWarrior (ScriptID WARRIOR 0))
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript ARENA_PARRY)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoCanFight FALSE)
				(TrySkill PARRY 0 20)
				(theWarrior
					getTired: 1
					canFight: FALSE
					action: ActParryUp
					setLoop: 4
					setCel: 0
					stopUpd:
				)
				(= cycles 1)
			)
			(1
				(theWarrior setCel: 1)
				(= cycles 7)
			)
			(2
				(theWarrior setCel: 0)
				(= cycles 1)
			)
			(3
				(theWarrior
					posn: (theWarrior baseX?) (theWarrior baseY?)
					setLoop: 2
					cel: 0
				)
				(= egoCanFight TRUE)
				(self dispose:)
			)
		)
	)
)
