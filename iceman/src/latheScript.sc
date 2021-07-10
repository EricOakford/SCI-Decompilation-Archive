;;; Sierra Script 1.0 - (do not remove this comment)
(script# 388)
(include game.sh)
(use Main)
(use Intrface)
(use subMarine)
(use SolvePuzzle)
(use Motion)
(use System)

(public
	latheScript 0
	drillScript 1
	grindScript 2
)

(instance latheScript of Script

	(method (dispose)
		(super dispose:)
		(DisposeScript 388)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 388 0)
				((ScriptID 34 2) setCycle: Forward)
				((ScriptID 34 3) setCycle: Forward)
				((ScriptID 34 4) setCycle: Forward)
				((ScriptID 34 5) setCycle: Forward)
				(client setMotion: MoveTo 115 93 client)
				((ScriptID 34 1)
					number: (SoundFX 30)
					loop: 1
					play: self
				)
			)
			(1
				(Print 388 1)
				((ScriptID 34 2) stopUpd:)
				((ScriptID 34 3) stopUpd:)
				((ScriptID 34 4) stopUpd:)
				((ScriptID 34 5) dispose:)
				(client setMotion: 0 stopUpd:)
				(HandsOn)
				(SolvePuzzle subMarine 406 512 1)
				(self dispose:)
			)
		)
	)
)

(instance drillScript of Script

	(method (dispose)
		(super dispose:)
		(DisposeScript 388)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego heading: 0)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 5)
			)
			(1
				(ego view: 434 setLoop: 0 setCel: 4 setCycle: BegLoop self)
			)
			(2
				(ego setCycle: EndLoop self)
				(client setCycle: EndLoop)
			)
			(3
				(Print 388 2)
				((ScriptID 34 1)
					number: (SoundFX 31)
					loop: 1
					play: self
				)
			)
			(4
				(ego setCycle: BegLoop self)
				(client setCycle: BegLoop)
			)
			(5
				(client stopUpd:)
				(ego setCycle: EndLoop self)
			)
			(6
				(ego
					view: 232
					loop: 3
					setLoop: -1
					setCycle: Walk
					setAvoider: 0
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance grindScript of Script
	
	(method (doit)
		(super doit:)
		(if (== ((ScriptID 34 1) prevSignal?) -1) (self cue:))
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 388)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 388 3)
				(ego view: 434 loop: 1 cel: 0 cycleSpeed: 3 setCycle: Forward)
				((ScriptID 34 1) number: (SoundFX 17) loop: 1 play:)
			)
			(1
				(Print 388 4)
				(ego view: 232 loop: 3 cycleSpeed: 0 setCycle: Walk)
				(SolvePuzzle subMarine 406 4096 1)
				(subMarine roomFlags: (| (subMarine roomFlags?) $0004))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
