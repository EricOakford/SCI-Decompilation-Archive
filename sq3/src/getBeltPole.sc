;;; Sierra Script 1.0 - (do not remove this comment)
(script# 521)
(include game.sh)
(use Main)
(use Avoider)
(use Motion)
(use System)

(public
	getBeltPole 0
)

(instance getBeltPole of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setAvoider: Avoider
					setMotion: MoveTo (- (terminatorRemains x?) 40) 122 self
				)
			)
			(1
				(ego
					view: 104
					setLoop: 0
					cel: 255
					cycleSpeed: 3
					setCycle: CycleTo 4 1 self
				)
			)
			(2
				(theBelt dispose:)
				(ego setCycle: EndLoop)
				(= seconds 3)
			)
			(3
				(ego get: iInvisibilityBelt)
				(theGame changeScore: 35)
				(ego
					view: 0
					setLoop: -1
					setCel: -1
					setCycle: Walk
					cycleSpeed: 0
					setMotion: MoveTo 179 110 self
				)
			)
			(4
				(HandsOn)
				(ego loop: 2 setAvoider: 0)
				(curRoom setScript: 0)
				(DisposeScript 521)
			)
		)
	)
)
