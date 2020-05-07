;;; Sierra Script 1.0 - (do not remove this comment)
(script# 276)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	egoClimbsUp 0
)

(instance egoClimbsUp of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 276)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setPri: 6
					illegalBits: 0
					setMotion: MoveTo 272 104 self
				)
			)
			(1
				(ego setLoop: 3)
				(= cycles 4)
			)
			(2
				(HighPrint 276 0)
				;You find a good purchase on the rocks to climb.
				(ego view: vEgoClimbing setLoop: 0 setCel: 1 posn: 282 102)
				(= cycles 4)
			)
			(3
				(ego setCel: 2 posn: 285 103)
				(= cycles 4)
			)
			(4
				(ego setCel: 1 posn: 291 84)
				(= cycles 4)
			)
			(5
				(ego setCel: 2 posn: 293 83)
				(= cycles 4)
			)
			(6
				(ego setCel: 3 posn: 294 65)
				(= cycles 4)
			)
			(7 (= cycles 1))
			(8
				(ego setCel: 0 posn: 293 66)
				(= cycles 4)
			)
			(9
				(ego setLoop: 1 setCel: 0 posn: 282 34)
				(= cycles 6)
			)
			(10
				(ego setCel: 1 posn: 282 40)
				(= cycles 6)
			)
			(11
				(ego setCel: 2 posn: 281 43)
				(= cycles 6)
			)
			(12
				(ego setCel: 3 setPri: 5 posn: 284 40)
				(= cycles 4)
			)
			(13
				(ego setCel: 4 posn: 285 48)
				(= cycles 3)
			)
			(14
				(ego setCel: 4 posn: 285 56)
				(= cycles 3)
			)
			(15
				(ego setCel: 4 posn: 285 62)
				(= cycles 3)
			)
			(16
				(ego setCel: 4 posn: 285 68)
				(= cycles 3)
			)
			(17
				(= monsterNum (= monsterHealth 0))
				(curRoom newRoom: 94)
			)
		)
	)
)
