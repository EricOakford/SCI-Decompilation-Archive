;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA_BLOCK) ;152
(include game.sh)
(use Main)
(use System)

(public
	egoBlock 0
)

(local
	theCycles
	local1
)
(instance egoBlock of Script
	(properties)
	
	(method (init)
		(= local1 ((= theCycles (ScriptID WARRIOR 0)) egoShield?))
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
				(theCycles
					getTired: 1
					canFight: 0
					action: 3
					posn: (theCycles baseX?) (+ (theCycles baseY?) 5)
					stopUpd:
				)
				(local1
					setCel: 2
					posn: (+ (local1 x?) 22) (- (local1 y?) 10)
				)
				(= cycles 2)
			)
			(1 (= cycles 6))
			(2
				(local1
					setCel: 0
					posn: (- (theCycles baseX?) 74) (theCycles baseY?)
				)
				(= cycles 4)
			)
			(3
				(local1 stopUpd:)
				(theCycles posn: (theCycles baseX?) (theCycles baseY?))
				(= egoCanFight TRUE)
				(self dispose:)
			)
		)
	)
)
