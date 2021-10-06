;;; Sierra Script 1.0 - (do not remove this comment)
(script# 520)
(include game.sh)
(use Main)
(use Avoider)
(use Motion)
(use Actor)
(use System)

(public
	getBelt 0
)

(local
	egoArm
)
(instance getBelt of Script
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
					view: 122
					setLoop: 0
					cel: 255
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(2
				(ego setLoop: 1 setCel: 0)
				(= egoArm (Prop new:))
				(egoArm
					view: 122
					setLoop: 2
					cel: 255
					posn: (+ (ego x?) 5) (- (ego y?) 2)
					setPri: 7
					cycleSpeed: 3
					setCycle: EndLoop self
					init:
				)
			)
			(3
				(theBelt dispose:)
				(= seconds 3)
			)
			(4
				(egoArm dispose:)
				(ego loop: 0 setCel: 255 setCycle: BegLoop self)
				(ego get: iInvisibilityBelt)
				(theGame changeScore: 35)
			)
			(5
				(ego
					view: 0
					setLoop: -1
					setCel: -1
					setCycle: Walk
					cycleSpeed: 0
					setMotion: MoveTo 179 110 self
				)
			)
			(6
				(HandsOn)
				(ego loop: 2 setAvoider: 0)
				(curRoom setScript: 0)
				(DisposeScript 520)
			)
		)
	)
)
