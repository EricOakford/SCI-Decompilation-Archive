;;; Sierra Script 1.0 - (do not remove this comment)
(script# 781)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	eatIt 0
)

(instance eatIt of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= oldIllegalBits (ego illegalBits?))
				(ego
					view: (if (Btst fLittleEgo) 27 else 24)
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
					setMotion: 0
				)
			)
			(1
				(NormalEgo)
				(ego loop: 2 illegalBits: oldIllegalBits)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
