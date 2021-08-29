;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA_CALM) ;150
(include game.sh)
(use Main)
(use System)

(public
	egoCalm 0
)

(instance egoCalm of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript ARENA_CALM)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HighPrint 150 0)
				;Confidently, you cast the Calm spell.
				(EgoDead 150 1
					#title {You should have studied harder}
					#icon vEgoMagicDetect 1 4)
					;Why, how cute!  You cast the Calm spell, and the monster visibly relaxed.
					;Why, now it's calmly and relaxedly ripping you to shreds and eating you.
			)
		)
	)
)
