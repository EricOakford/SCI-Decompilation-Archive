;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA_PAIN) ;155
(include game.sh)
(use Main)
(use System)

(public
	painReaction 0
)

(local
	theWarrior
	theHand
	theBack
	theShield
)
(instance painReaction of Script
	(method (init)
		(= theWarrior (ScriptID WARRIOR 0))
		(= theHand ((ScriptID WARRIOR 0) egoHand?))
		(= theBack ((ScriptID WARRIOR 0) egosBack?))
		(= theShield ((ScriptID WARRIOR 0) egoShield?))
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript ARENA_PAIN)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoCanFight FALSE)
				(theBack posn: (- (theBack x?) 3) (+ (theBack y?) 5))
				(theWarrior posn: (- (theWarrior x?) 3) (+ (theWarrior y?) 5))
				(if theShield
					(theShield posn: (- (theShield x?) 5) (+ (theShield y?) 10))
				else
					(theHand posn: (- (theHand x?) 5) (+ (theHand y?) 10))
				)
				(= cycles 3)
			)
			(1
				(if (Btst fDiedInBattle)
					(EgoDead 155 0
						#title {What a monster!}
						#icon vEgoDefeatedMagic 0 9
					)
					;It was a tough battle, and you lost.
					;Never fear!  All you have to do is restore your game, and...\nWhat do you mean, "Restore WHAT game?"
				else
					(theBack posn: (- (theWarrior baseX?) 41) (theWarrior baseY?))
					(theWarrior posn: (theWarrior baseX?) (theWarrior baseY?))
					(if theShield
						(theShield posn: (- (theWarrior baseX?) 74) (theWarrior baseY?))
					else
						(theHand posn: (- (theWarrior baseX?) 73) (theWarrior baseY?))
					)
					(= cycles 1)
				)
				(= egoCanFight TRUE)
			)
			(2
				(self dispose:)
			)
		)
	)
)
