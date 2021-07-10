;;; Sierra Script 1.0 - (do not remove this comment)
(script# 336)
(include game.sh)
(use Main)
(use Intrface)
(use Approach)
(use Avoider)
(use System)

(public
	mapDoneScript 0
)

(instance mapDoneScript of Script
	
	(method (dispose)
		(cls)
		(super dispose:)
		(DisposeScript APPROACH)
		(DisposeScript 336)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 2)
			)
			(1
				(Print 336 0 #at -1 1 #time 7 #dispose)
				(= seconds 7)
			)
			(2
				(Print 336 1 #at -1 1 #time 7 #dispose)
				(ego
					illegalBits: cWHITE
					setMotion: Approach (ScriptID 25 4) 20 self
					setAvoider: Avoider
				)
			)
			(3
				(ego setAvoider: 0 setScript: (ScriptID 25 2))
			)
		)
	)
)
