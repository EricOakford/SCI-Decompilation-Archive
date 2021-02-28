;;; Sierra Script 1.0 - (do not remove this comment)
(script# OPEN_GIFT) ;30
(include game.sh) (include "0.shm")
(use Main)
(use StopWalk)
(use Sound)
(use Motion)
(use System)

(public
	giftScript 0
)

(instance giftScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= register (if (< (ego heading?) 180) 0 else 1))
				(ego
					setMotion: 0
					view: 33
					setCel: 0
					setLoop: register
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(1
				(ego setCel: 0 setCycle: EndLoop self)
			)
			(2
				(= ticks 60)
			)
			(3
				(ego
					setLoop: (+ (ego loop?) 2)
					setCel: 0
					setCycle: EndLoop self
				)
				(giftSfx number: 930 play:)
			)
			(4
				(messager say: N_CUE V_DOIT C_OPEN_GIFT 0 0 0)
				(ego
					view: 0
					cycleSpeed: speed
					setLoop: -1
					setLoop: (ScriptID GLORY_EGO 1)
					setCycle: StopWalk 5
				)
				(= ticks 60)
			)
			(5
				(HandsOn)
				(DisableIcons)
				(self dispose:)
			)
		)
	)
)

(instance giftSfx of Sound)
