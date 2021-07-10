;;; Sierra Script 1.0 - (do not remove this comment)
(script# 329)
(include game.sh)
(use Main)
(use n396)
(use EgoDead)
(use System)

(public
	deadByTorpedo 0
)

(procedure (localproc_0146)
	(globalSound
		number: (SoundFX 20)
		loop: 1
		owner: theGame
		priority: 1
		play:
	)
	(ShakeScreen 30)
)

(instance deadByTorpedo of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(SubPrint 3 329 0)
				(localproc_0146)
				(= seconds 4)
			)
			(2
				(localproc_0146)
				(SubPrint 4 329 1)
				((ScriptID 372) dispose:)
				(= gamePhase 0)
				(DisposeScript 370)
				(= seconds 5)
			)
			(3
				(localproc_0146)
				(switch register
					(0 (EgoDead 926 1 0 329 2))
					(1 (EgoDead 926 1 0 329 3))
					(2 (EgoDead 926 1 0 329 4))
					(3 (EgoDead 926 1 0 329 5))
					(4 (EgoDead 926 1 0 329 6))
				)
			)
		)
	)
)
