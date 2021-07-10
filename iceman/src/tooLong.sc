;;; Sierra Script 1.0 - (do not remove this comment)
(script# 327)
(include game.sh)
(use Main)
(use EgoDead)
(use System)

(public
	tooLong 0
)

(instance tooLong of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 372) dispose:)
				(= gamePhase 0)
				(DisposeScript 370)
				(= seconds 1)
			)
			(1
				(EgoDead 926 1 0 327 0)
			)
		)
	)
)
