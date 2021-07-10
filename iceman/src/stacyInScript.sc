;;; Sierra Script 1.0 - (do not remove this comment)
(script# 369)
(include game.sh)
(use Main)
(use Intrface)
(use EgoDead)
(use Motion)
(use User)
(use System)

(public
	stacyInScript 0
)

(instance egoMoveTo of MoveTo
	(properties)
	
	(method (dispose)
		(if
			(and
				(IsObject caller)
				(caller client?)
				(== (stacyInScript state?) 1)
			)
			(caller cue:)
		)
		(super dispose:)
	)
)

(instance stacyInScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 15))
			(1
				(HandsOff)
				(if (ego inRect: 84 142 142 170)
					(ego setMotion: egoMoveTo (ego x?) 140 self)
				else
					(= cycles 1)
				)
			)
			(2
				(User canInput: TRUE)
				((ScriptID 310 3)
					init:
					ignoreActors: 0
					setCycle: Walk
					setMotion: MoveTo ((ScriptID 310 3) x?) 160
				)
				(Print 369 0)
				(Print 369 1)
			)
			(3
				(if register
					(Print 369 2)
					(Print 369 3)
					(Print 369 4)
					(Print 369 5)
					((ScriptID 310 3)
						setMotion: MoveTo ((ScriptID 310 3) x?) 200 self
					)
					(HandsOn)
				else
					(HandsOff)
					(Print 369 6)
					(Print 369 7)
					(= seconds 2)
				)
			)
			(4
				(if register
					(client cue:)
				else
					(EgoDead 918 0 0 369 8)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((< state 2))
			((Said 'affirmative')
				(= register TRUE)
				(self cue:)
			)
			((Said 'n')
				(self cue:)
			)
			((Said '/film')
				(Print 369 9)
				(Print 369 1)
			)
		)
	)
)
