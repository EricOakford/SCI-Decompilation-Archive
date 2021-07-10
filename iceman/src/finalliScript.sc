;;; Sierra Script 1.0 - (do not remove this comment)
(script# 338)
(include game.sh)
(use Main)
(use Intrface)
(use subMarine)
(use System)

(public
	finalliScript 0
)

(instance finalliScript of Script
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 338)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 25 7) setCel: 16)
				((ScriptID 25 1)
					init:
					posn: 214 100
					heading: 270
					loop: 1
					illegalBits: 0
				)
				(self setScript: (ScriptID 394) self)
			)
			(1
				(subMarine roomFlags: (| (subMarine roomFlags?) $8000))
				(ego posn: 182 95)
				(Print 338 0)
				(Print 338 1)
				(Print 338 2)
				(Print 338 3)
				(HandsOn)
				(client setScript: (ScriptID 25 3))
			)
		)
	)
)
