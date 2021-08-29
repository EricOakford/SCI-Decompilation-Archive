;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA_BLOCK) ;152
(include game.sh)
(use Main)
(use System)

(public
	egoBlock 0
)

(local
	theWarrior
	theShield
)
(instance egoBlock of Script
	(method (init)
		(= theShield ((= theWarrior (ScriptID WARRIOR 0)) egoShield?))
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript ARENA_BLOCK)
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
					posn: (theWarrior baseX?) (+ (theWarrior baseY?) 5)
					stopUpd:
				)
				(theShield
					setCel: 2
					posn: (+ (theShield x?) 22) (- (theShield y?) 10)
				)
				(= cycles 2)
			)
			(1
				(= cycles 6)
			)
			(2
				(theShield
					setCel: 0
					posn: (- (theWarrior baseX?) 74) (theWarrior baseY?)
				)
				(= cycles 4)
			)
			(3
				(theShield stopUpd:)
				(theWarrior posn: (theWarrior baseX?) (theWarrior baseY?))
				(= egoCanFight TRUE)
				(self dispose:)
			)
		)
	)
)
