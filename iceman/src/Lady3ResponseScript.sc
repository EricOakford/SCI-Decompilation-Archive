;;; Sierra Script 1.0 - (do not remove this comment)
(script# 352)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use User)
(use System)

(public
	Lady3ResponseScript 0
)

(local
	saveControls
)
(instance Lady3ResponseScript of Script
	
	(method (dispose)
		(client
			view: 702
			setLoop: -1
			setCycle: Walk
			setMotion: register
		)
		(super dispose:)
		(HandsOn)
		(User controls: saveControls)
		(DisposeScript 352)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveControls (User controls?))
				(HandsOff)
				(= register (client mover?))
				(client mover: 0)
				(ego setMotion: 0)
				(client
					setMotion: kickSandMover (ego x?) (+ (ego y?) 13) self
				)
			)
			(1
				(client view: 902 setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(2
				(switch (Random 0 2)
					(0 (Print 352 0 #time 10))
					(1 (Print 352 1 #time 10))
					(2 (Print 352 2 #time 10))
				)
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
