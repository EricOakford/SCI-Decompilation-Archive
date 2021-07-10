;;; Sierra Script 1.0 - (do not remove this comment)
(script# 326)
(include game.sh)
(use Main)
(use EgoDead)
(use System)

(public
	firedTooSoon 0
)

(instance firedTooSoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 372) dispose:)
				(= gamePhase 0)
				(DisposeScript 370)
				(= seconds 4)
			)
			(1
				(EgoDead 926 1 0 326 0)
			)
		)
	)
)
