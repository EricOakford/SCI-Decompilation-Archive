;;; Sierra Script 1.0 - (do not remove this comment)
(script# 189)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	egoTripsSouth 0
)

(instance egoTripsSouth of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 189)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: vEgoFall2
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					illegalBits: 0
					setCycle: EndLoop
					setMotion: MoveTo (- (ego x?) 3) (- (ego y?) 12) self
				)
				(if (not ((ScriptID 94 0) notify: 1))
					((ScriptID 94 11) setScript: (ScriptID 192 0))
				)
			)
			(1
				(ego
					view: vEgoTired
					setLoop: 1
					setCel: 4
					cycleSpeed: 3
					setCycle: BegLoop self
				)
			)
			(2
				(NormalEgo)
				(ego loop: 3)
				(= cycles 2)
			)
			(3
				(NormalEgo)
				(ego loop: 1)
				(= cycles 2)
			)
			(4
				(NormalEgo)
				(ego loop: 2)
				(= cycles 2)
			)
			(5 (HandsOn) (self dispose:))
		)
	)
)
