;;; Sierra Script 1.0 - (do not remove this comment)
(script# 157)
(include sci.sh)
(use Main)
(use Intrface)
(use Grooper)
(use Motion)
(use Actor)
(use System)

(public
	killEgo 0
	guard 1
)

(instance guard of Actor
	(properties
		view 417
		cycleSpeed 3
		xStep 4
		moveSpeed 3
	)
	
	(method (init)
		(super init: &rest)
		(self
			hide:
			posn: 327 223 moveSpeed (theGame egoMoveSpeed?)
			cycleSpeed: (theGame egoMoveSpeed?)
			setCycle: Walk
			setLoop: Grooper
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 157 0))
			(2 (Print 157 1))
			(11 (Print 157 2))
			(12 (Print 157 2))
			(4 (Print 157 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance killEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(guard init: show: setMotion: MoveTo 270 175 self)
			)
			(1 (Face ego guard self))
			(2
				(cond 
					((& (ego onControl: 0) $0010) (guard setLoop: 1))
					((& (ego onControl: 0) $0040) (guard setLoop: 4))
					(else (guard setLoop: 5))
				)
				(guard view: 415 cel: 0 setCycle: End self)
			)
			(3
				(ego
					view: 48
					setLoop: 1
					cycleSpeed: 6
					cel: 0
					setCycle: End self
				)
			)
			(4 (EgoDead) (self dispose:))
		)
	)
)
