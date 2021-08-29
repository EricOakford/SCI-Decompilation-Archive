;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA_MAGIC) ;146
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	doSpell 0
)

(local
	theWarrior
	theHand
	theBack
	theShield
)

(instance doSpell of Script
	(method (init)
		(= theWarrior (ScriptID WARRIOR 0))
		(= theHand ((ScriptID WARRIOR 0) egoHand?))
		(= theBack ((ScriptID WARRIOR 0) egosBack?))
		(= theShield ((ScriptID WARRIOR 0) egoShield?))
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript ARENA_MAGIC)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoCanFight FALSE)
				(theHand setCycle: EndLoop self)
			)
			(1
				(switch register
					(FLAMEDART
						(self setScript: (ScriptID ARENA_FLAME 0) self)
					)
					(ZAP
						(self setScript: (ScriptID ARENA_ZAP 0) self)
					)
					(DAZZLE
						(self setScript: (ScriptID ARENA_DAZZLE 0) self)
					)
					(CALM
						(self setScript: (ScriptID ARENA_CALM 0) self)
					)
				)
			)
			(2
				(= register 0)
				(theHand setCycle: BegLoop self)
			)
			(3
				(theHand stopUpd:)
				(= egoCanFight TRUE)
				(self dispose:)
			)
		)
	)
)
