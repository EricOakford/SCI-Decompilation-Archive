;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA_DAZZLE) ;149
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	egoDazzle 0
)

(local
	theWarrior
	theHand
	theSpell
)
(instance egoDazzle of Script
	(method (init)
		(= theWarrior (ScriptID WARRIOR 0))
		(= theSpell (ScriptID CLOSECOMBAT 1))
		(= theHand (theWarrior egoHand?))
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript ARENA_DAZZLE)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theWarrior
					canFight: FALSE
					action: ActCast
				)
				(self cue:)
			)
			(1
				(theSpell
					setLoop: 5
					cel: 0
					setPri: (- (theHand priority?) 1)
					cycleSpeed: 1
					ignoreActors:
					posn: (- (theWarrior x?) 78) (- (theWarrior y?) 81)
					init:
					setCycle: EndLoop self
				)
			)
			(2
				(theSpell dispose:)
				(Bset fMonsterDazzled)
				(= monsterDazzle [egoStats DAZZLE])
				(theWarrior canFight: FALSE action: 0)
				(self dispose:)
			)
		)
	)
)
