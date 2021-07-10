;;; Sierra Script 1.0 - (do not remove this comment)
(script# 353)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use User)
(use System)

(public
	Lady4ResponseScript 0
)

(local
	saveControls
)
(instance Lady4ResponseScript of Script
	
	(method (dispose)
		(client
			view: 202
			setLoop: -1
			setCycle: Walk
			setMotion: register
		)
		(HandsOn)
		(super dispose:)
		(User controls: saveControls)
		(DisposeScript 353)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveControls (User controls?))
				(HandsOff)
				(= register (client mover?))
				(ego setMotion: 0)
				(client
					mover: 0
					setMotion:
						kickSandMover
						(if (<= (ego x?) (client x?))
							(+ (ego x?) 15)
						else
							(- (ego x?) 15)
						)
						(if (<= (ego y?) (client y?))
							(+ (ego y?) 5)
						else
							(- (ego y?) 5)
						)
						self
				)
			)
			(1
				(client
					view: 902
					cel: 0
					setLoop: (if (<= (ego x?) (client x?)) 1 else 0)
					setCycle: EndLoop self
				)
			)
			(2
				(Print 353 0 #time 10)
				(client setScript: 0)
			)
		)
	)
)

(instance kickSandMover of MoveTo

	(method (doit)
		(super doit:)
		(if (client isStopped:) (self moveDone:))
	)
)
