;;; Sierra Script 1.0 - (do not remove this comment)
(script# 275)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	egoClimbsDown 0
)

(instance egoClimbsDown of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 275)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: vEgoClimbing
					setLoop: loopE
					setCel: 0
					setPri: 6
					illegalBits: 0
					posn: 293 67
				)
				(= cycles 4)
			)
			(1
				(ego setCel: 2 posn: 292 82)
				(= cycles 4)
			)
			(2
				(ego setCel: 0 posn: 289 96)
				(= cycles 4)
			)
			(3
				(ego setCel: 2 posn: 286 109)
				(= cycles 4)
			)
			(4
				(ego view: vEgo setLoop: loopN setCel: 0 posn: 275 109)
				(= cycles 4)
			)
			(5
				(NormalEgo)
				(ego
					loop: loopN
					illegalBits: 0
					setPri: 6
					setMotion: MoveTo 248 105 self
				)
			)
			(6
				((ScriptID 93 0) notify: 0)
				(NormalEgo)
				(ego illegalBits: (| cWHITE cLRED))
				(HandsOn)
			)
		)
	)
)
