;;; Sierra Script 1.0 - (do not remove this comment)
(script# 320)
(include game.sh)
(use Main)
(use n396)
(use EgoDead)
(use System)

(public
	deadByTorpedo 0
)

(instance deadByTorpedo of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 1)
			)
			(1
				(SubPrint 3 320 0)
				(globalSound
					number: (SoundFX 20)
					loop: 1
					owner: theGame
					priority: 1
					play:
				)
				(ShakeScreen 30)
				(= seconds 4)
			)
			(2
				(globalSound
					number: (SoundFX 20)
					loop: 1
					owner: theGame
					priority: 1
					play:
				)
				(ShakeScreen 30)
				(SubPrint 4 320 1)
				(if gamePhase ((ScriptID 372) dispose:))
				(= gamePhase 0)
				(DisposeScript 370)
				(DisposeScript 396)
				(= seconds 5)
			)
			(3
				(switch register
					(4 (EgoDead 926 1 0 320 2))
					(6 (EgoDead 926 1 0 320 3))
					(else  (EgoDead 926 1 0 320 4))
				)
			)
		)
	)
)
