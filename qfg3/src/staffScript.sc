;;; Sierra Script 1.0 - (do not remove this comment)
(script# STAFF_SCRIPT) ;46
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use System)

(public
	staffScript 0
)

(local
	theLoop = [0 1 0 4 0 1 4 5]
)
(instance staffScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (IsObject (ego looper?))
					((ego looper?) dispose:)
					(ego looper: 0)
				)
				(ego
					setMotion: 0
					view: 20
					loop: [theLoop (ego loop?)]
					cel: 0
					setCycle: EndLoop self
				)
				(sFx number: 900 play:)
			)
			(1
				(ego loop: (+ (ego loop?) 2) cel: 0 setCycle: EndLoop self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sFx of Sound)
