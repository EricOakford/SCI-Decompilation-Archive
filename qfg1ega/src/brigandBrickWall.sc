;;; Sierra Script 1.0 - (do not remove this comment)
(script# 233)
(include game.sh)
(use Main)
(use Reverse)
(use Motion)
(use System)

(public
	brickWall 0
)

(instance brickWall of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 233)
		(DisposeScript REVERSE)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (< (ego y?) 103)
					(ego
						setLoop: 3
						setCycle: Reverse
						setMotion: MoveTo (+ (ego x?) 5) 103 self
					)
				else
					(self cue:)
				)
			)
			(1
				(NormalEgo)
				((ScriptID 96 13) init:)
				((ScriptID 96 9) setCycle: EndLoop self)
			)
			(2
				(HighPrint 233 0)
				;Oops!  You thought this door led SOMEWHERE!
				(= cycles 4)
			)
			(3
				((ScriptID 96 9) setCycle: BegLoop self)
			)
			(4
				((ScriptID 96 13) dispose:)
				((ScriptID 96 9) stopUpd:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)
