;;; Sierra Script 1.0 - (do not remove this comment)
(script# 393)
(include game.sh)
(use Main)
(use Intrface)
(use Submarine_806)
(use EgoDead)
(use Motion)
(use User)
(use System)

(public
	climbLadderScript 0
)

(instance climbLadderScript of Script

	(method (init)
		(super init: &rest)
		(User mapKeyToDir: FALSE)
		(keyDownHandler add: self)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(User mapKeyToDir: TRUE)
		(super dispose:)
		(DisposeScript 393)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setLoop: 6
					illegalBits: 0
					setMotion: MoveTo 298 (ego y?) self
				)
			)
			(1
				(ego
					view: 238
					setLoop: 0
					heading: 0
					setMotion: MoveTo 298 52 self
				)
			)
			(2
				(User canInput: TRUE)
				(Print 393 0)
			)
			(3
				(User canInput: FALSE)
				(ego setMotion: MoveTo 298 42 self)
			)
			(4 (curRoom newRoom: 28))
		)
	)
	
	(method (handleEvent event)
		(cond 
			(
				(and
					(== (event type?) keyDown)
					(== (event message?) DOWNARROW)
				)
				(ego setScript: (ScriptID 391))
			)
			((super handleEvent: event))
			((Said 'open/hatch')
				(if (> (Submarine depth:) 20)
					(EgoDead 926 3 0 393 1)
				else
					(self cue:)
				)
			)
			((Said '[go,climb]<down')
				(ego setScript: (ScriptID 391))
			)
		)
	)
)
