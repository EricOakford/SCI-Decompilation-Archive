;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA_PARRY)
(include game.sh)
(use Main)
(use System)

(public
	egoParry 0
)

(local
	theCycles
)
(instance egoParry of Script
	(properties)
	
	(method (init)
		(= theCycles (ScriptID WARRIOR 0))
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
				(theCycles
					getTired: 1
					canFight: 0
					action: 3
					setLoop: 4
					setCel: 0
					stopUpd:
				)
				(= cycles 1)
			)
			(1
				(theCycles setCel: 1)
				(= cycles 7)
			)
			(2
				(theCycles setCel: 0)
				(= cycles 1)
			)
			(3
				(theCycles
					posn: (theCycles baseX?) (theCycles baseY?)
					setLoop: 2
					cel: 0
				)
				(= egoCanFight TRUE)
				(self dispose:)
			)
		)
	)
)
