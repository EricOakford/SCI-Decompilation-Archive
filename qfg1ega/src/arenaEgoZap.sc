;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA_ZAP) ;148
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	egoZap 0
)

(local
	theWarrior
	theSpell
)
(instance egoZap of Script
	(method (init)
		(= theWarrior (ScriptID WARRIOR 0))
		(= theSpell (ScriptID CLOSECOMBAT 1))
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript ARENA_ZAP)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= zapPower (+ 5 (/ [egoStats ZAP] 10)))
				(theWarrior
					canFight: FALSE
					action: ActCast
					setCel: 2
				)
				(= cycles 1)
			)
			(1
				(theSpell
					setLoop: 7
					cel: 0
					cycleSpeed: 1
					ignoreActors:
					posn: (- (theWarrior x?) 7) (- (theWarrior y?) 70)
					setPri: (+ (theWarrior priority?) 1)
					init:
					setCycle: EndLoop self
				)
			)
			(2
				(theSpell dispose:)
				(theWarrior canFight: TRUE setCel: 0)
				(self dispose:)
			)
		)
	)
)
