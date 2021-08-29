;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA_DODGE) ;154
(include game.sh)
(use Main)
(use System)

(public
	egoDodge 0
)

(local
	theWarrior
	theShield
	theHand
	theBack
)
(instance egoDodge of Script
	(method (init)
		(= theShield ((= theWarrior (ScriptID WARRIOR 0)) egoShield?))
		(= theHand (theWarrior egoHand?))
		(= theBack (theWarrior egosBack?))
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript ARENA_DODGE)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoCanFight FALSE)
				(TrySkill DODGE 0 20)
				(theWarrior
					getTired: 2
					canFight: 0
					action: (if (== register 0) ActDodgeLeft else ActDodgeRight)
				)
				(if theShield
					(switch register
						(0
							(theShield posn: (- (theShield x?) 25) (- (theShield y?) 5))
						)
						(1
							(theShield posn: (+ (theShield x?) 40) (- (theShield y?) 5))
						)
					)
				else
					(switch register
						(0
							(theHand posn: (- (theHand x?) 38) (+ (theHand y?) 5))
						)
						(1
							(theHand posn: (+ (theHand x?) 46) (+ (theHand y?) 5))
						)
					)
				)
				(switch register
					(0
						(theWarrior posn: (- (theWarrior x?) 41) (+ (theWarrior y?) 5))
						(theBack posn: (- (theBack x?) 40) (+ (theBack y?) 5))
					)
					(1
						(theWarrior posn: (+ (theWarrior x?) 42) (+ (theWarrior y?) 5))
						(theBack posn: (+ (theBack x?) 40) (+ (theBack y?) 5))
					)
				)
				(= cycles 7)
			)
			(1
				(theWarrior posn: (theWarrior baseX?) (theWarrior baseY?))
				(if theShield
					(theShield posn: (- (theWarrior baseX?) 74) (theWarrior baseY?))
				else
					(theHand posn: (- (theWarrior baseX?) 74) (theWarrior baseY?))
				)
				(theBack posn: (- (theWarrior baseX?) 41) (theWarrior baseY?))
				(self cue:)
			)
			(2
				(= egoCanFight TRUE)
				(self dispose:)
			)
		)
	)
)

