;;; Sierra Script 1.0 - (do not remove this comment)
(script# CLIMB)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use RFeature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	climbRg 0
	climbing 1
	fallRight 2
	fallLeft 3
)

(instance climbRg of Region
	(properties)
	
	(method (init)
		(LoadMany VIEW 34 8 270)
		(LoadMany SOUND 95 17)
		(self keep: FALSE)
		(super init: &rest)
		(curRoom setScript: climbing)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/leaf')
				(Print 609 0)
			)
			((Said 'climb,scale>')
				(if (Said '<up,down')
					(Print 609 1)
				else
					(Print 609 2)
					(event claimed: TRUE)
				)
			)
			((Said 'get,take/leaf')
				(Print 609 3)
			)
		)
	)
)

(instance climbing of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(switch (ego onControl: origin)
			(cLGREY
				(curRoom setScript: fallRight)
			)
			(cBROWN
				(curRoom setScript: fallLeft)
			)
		)
	)
)

(instance fallRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 21) number: 17 loop: -1 init: play:)
				(ego
					view: 8
					setLoop: 5
					setCycle: Forward
					cycleSpeed: 1
					cel: 0
					ignoreActors:
					illegalBits: 0
					setStep: 4 6
					setPri: 15
					setMotion: MoveTo (+ (ego x?) 20) 214 self
				)
			)
			(1
				(switch curRoomNum
					(72
						(self cue:)
					)
					(71
						(self changeState: 3)
					)
					(70
						(self changeState: 4)
					)
				)
			)
			(2
				(curRoom style: SCROLLUP drawPic: 71)
				(ego
					posn: 168 -10
					setStep: 4 8
					setMotion: MoveTo 180 214 self
				)
			)
			(3
				(curRoom style: SCROLLUP drawPic: 70)
				(ego
					posn: 182 -10
					setStep: 4 12
					setMotion: MoveTo 210 214 self
				)
			)
			(4
				(curRoom drawPic: roomWithBeanstalk overlay: 270 PLAIN)
				(ego
					view: 34
					setCycle: 0
					setLoop: 5
					posn: 270 -10
					setStep: 2 15
					setMotion: MoveTo 276 170 self
				)
			)
			(5
				(crater loop: 1 init:)
				((ScriptID 0 21) number: 95 loop: 1 play:)
				(self cue:)
			)
			(6
				(ego loop: 3 cel: 0 cycleSpeed: 1 posn: 276 182)
				(RedrawCast)
				(ShakeScreen 6)
				(ego setCycle: EndLoop)
				(= seconds 4)
			)
			(7
				(EgoDead {Looks like you had a bad fall this spring.})
			)
		)
	)
)

(instance fallLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 21) number: 17 loop: -1 init: play:)
				(ego
					view: 8
					setLoop: 4
					cel: 0
					setCycle: Forward
					cycleSpeed: 1
					ignoreActors:
					illegalBits: 0
					setStep: 4 6
					setPri: 15
					setMotion: MoveTo (- (ego x?) 20) 214 self
				)
			)
			(1
				(switch curRoomNum
					(72
						(self cue:)
					)
					(71
						(self changeState: 3)
					)
					(70
						(self changeState: 4)
					)
				)
			)
			(2
				(curRoom style: SCROLLUP drawPic: 71)
				(ego
					posn: 148 -10
					setStep: 4 8
					setMotion: MoveTo 120 214 self
				)
			)
			(3
				(curRoom style: SCROLLUP drawPic: 70)
				(ego
					posn: 118 -10
					setStep: 4 12
					setMotion: MoveTo 106 214 self
				)
			)
			(4
				(curRoom drawPic: roomWithBeanstalk overlay: 270 PLAIN)
				(ego
					setCycle: 0
					setLoop: 4
					view: 34
					posn: 54 -10
					setStep: 2 15
					setMotion: MoveTo 50 170 self
				)
			)
			(5
				(crater loop: 0 posn: 50 180 init:)
				((ScriptID 0 21) number: 95 loop: 1 play:)
				(self cue:)
			)
			(6
				(ego loop: 2 cel: 0 cycleSpeed: 1 posn: 50 180)
				(RedrawCast)
				(ShakeScreen 6)
				(ego setCycle: EndLoop)
				(= seconds 4)
			)
			(7
				(EgoDead {Looks like you had a bad fall this spring.})
			)
		)
	)
)

(instance leaf1 of RPicView
	(properties
		description {leaf}
		view 270
	)
)

(instance crater of View
	(properties
		x 276
		y 182
		description {hole}
		view 34
	)
)
