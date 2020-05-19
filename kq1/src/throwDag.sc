;;; Sierra Script 1.0 - (do not remove this comment)
(script# 780)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use System)

(public
	throwDag 0
)

(instance throwDag of Script
	(properties)
	
	(method (changeState newState &tmp oldIllBits)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (cast contains: theMenace)
					(Face ego theMenace)
				)
				(cond 
					((Btst fLittleEgo)
						(ego view: 53)
					)
					((!= curRoomNum 65)
						(ego
							loop:
								(if (and (< 0 (ego heading?)) (< (ego heading?) 180))
									0
								else
									1
								)
							view: 51
						)
					)
					(else
						(ego view: 51)
					)
				)
				(ego cel: 0 setCycle: EndLoop self)
			)
			(1
				(Print 780 0)
				(theGame changeScore: -5)
				(ego put: iDagger)
				(= oldIllBits (ego illegalBits?))
				(NormalEgo)
				(ego illegalBits: oldIllBits)
				(ego loop: loopS)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
