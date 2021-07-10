;;; Sierra Script 1.0 - (do not remove this comment)
(script# 328)
(include game.sh)
(use Main)
(use n396)
(use EgoDead)
(use System)

(public
	deadByDepthCharge 0
)

(procedure (localproc_00e2)
	(globalSound
		number: (SoundFX 20)
		loop: 1
		owner: theGame
		priority: 1
		play:
	)
	(ShakeScreen 30)
)

(instance deadByDepthCharge of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 372) dispose:)
				(= gamePhase 0)
				(DisposeScript 370)
				(= seconds 1)
			)
			(1
				(SubPrint 3 328 0)
				(= seconds 4)
			)
			(2
				(SubPrint 3 328 1)
				(localproc_00e2)
				(= seconds 4)
			)
			(3
				(SubPrint 3 328 2)
				(localproc_00e2)
				(= seconds 4)
			)
			(4
				(EgoDead 926 1 0 328 3)
			)
		)
	)
)
