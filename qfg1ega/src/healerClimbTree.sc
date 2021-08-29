;;; Sierra Script 1.0 - (do not remove this comment)
(script# 236)
(include game.sh)
(use Main)
(use Motion)
(use User)
(use System)

(public
	climbTree 0
	cantClimbTree 1
)

(local
	stepCount
)

(instance climbTree of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 236)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 40 186 self)
			)
			(1
				(ego view: vEgoClimbing setLoop: 0 setCel: 0 setPri: 13)
				(self cue:)
			)
			(2 (= cycles 4))
			(3
				(++ stepCount)
				(ego
					setCel: (if (== (ego cel?) 3) 0 else (+ (ego cel?) 1))
					posn:
						(ego x?)
						(if (== (mod (ego cel?) 2) 0)
							(- (ego y?) 20)
						else
							(ego y?)
						)
				)
				(self cue:)
			)
			(4
				(if (< stepCount 6)
					(self changeState: 2)
				else
					(= stepCount 0)
					(= cycles 4)
				)
			)
			(5
				(ego
					setLoop: 1
					cel: 0
					x: (- (ego x?) 12)
					y: (- (ego y?) 33)
					setStep: 0 4
					cycleSpeed: 2
					moveSpeed: 2
					setCycle: EndLoop self
				)
			)
			(6
				(Bset fClimbedTree)
				(User canInput: TRUE)
				((ScriptID 54 3) setScript: (ScriptID 54 5))
				(client setScript: 0)
			)
		)
	)
)

(instance cantClimbTree of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 236)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 40 180 self)
			)
			(1
				(ego view: vEgoClimbing setLoop: 0 setCel: 0 setPri: 13)
				(= cycles 4)
			)
			(2
				(ego setCel: 1 posn: 40 160 cycleSpeed: 1 setCycle: Forward)
				(= seconds 4)
			)
			(3
				(HighPrint 236 0)
				;It takes a lot of skill and practice to climb this tree.
				(HandsOn)
				(NormalEgo)
				(ego loop: loopS posn: 40 180)
				(client setScript: 0)
			)
		)
	)
)
