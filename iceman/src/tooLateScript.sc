;;; Sierra Script 1.0 - (do not remove this comment)
(script# 319)
(include game.sh)
(use Main)
(use Intrface)
(use EgoDead)
(use Motion)
(use System)

(public
	tooLateScript 0
)

(instance tooLateScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (ego inRect: 84 142 142 170)
					(ego setMotion: MoveTo (ego x?) 140 self)
				else
					(= cycles 1)
				)
			)
			(1
				((ScriptID 310 3)
					init:
					ignoreActors: 0
					setCycle: Walk
					setMotion: MoveTo ((ScriptID 310 3) x?) 160 self
				)
				(Print 319 0)
			)
			(2
				(ego heading: 180 cycleSpeed: 1)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 5)
			)
			(3
				(Print 319 1)
				(= seconds 1)
			)
			(4
				(EgoDead 918 0 0 319 2)
			)
		)
	)
)
