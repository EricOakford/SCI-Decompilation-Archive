;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA_PAIN) ;155
(include game.sh)
(use Main)
(use System)

(public
	painReaction 0
)

(local
	local0
	local1
	local2
	local3
)
(instance painReaction of Script
	(properties)
	
	(method (init)
		(= local0 (ScriptID WARRIOR 0))
		(= local1 ((ScriptID WARRIOR 0) egoHand?))
		(= local2 ((ScriptID WARRIOR 0) egosBack?))
		(= local3 ((ScriptID WARRIOR 0) egoShield?))
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
				(local2 posn: (- (local2 x?) 3) (+ (local2 y?) 5))
				(local0 posn: (- (local0 x?) 3) (+ (local0 y?) 5))
				(if local3
					(local3 posn: (- (local3 x?) 5) (+ (local3 y?) 10))
				else
					(local1 posn: (- (local1 x?) 5) (+ (local1 y?) 10))
				)
				(= cycles 3)
			)
			(1
				(if (Btst HERO_KILLED_IN_BATTLE)
					(EgoDead 155 0
						#title {What a monster!}
						#icon vEgoDefeatedMagic 0 9)
					;It was a tough battle, and you lost.
					;Never fear!  All you have to do is restore your game, and...\nWhat do you mean, "Restore WHAT game?"
				else
					(local2 posn: (- (local0 baseX?) 41) (local0 baseY?))
					(local0 posn: (local0 baseX?) (local0 baseY?))
					(if local3
						(local3 posn: (- (local0 baseX?) 74) (local0 baseY?))
					else
						(local1 posn: (- (local0 baseX?) 73) (local0 baseY?))
					)
					(= cycles 1)
				)
				(= egoCanFight TRUE)
			)
			(2 (self dispose:))
		)
	)
)
