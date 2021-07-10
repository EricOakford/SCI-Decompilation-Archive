;;; Sierra Script 1.0 - (do not remove this comment)
(script# 345)
(include game.sh)
(use Main)
(use Intrface)
(use subMarine)
(use Follow)
(use Grooper)
(use Motion)
(use System)

(public
	goBridgeScript 0
)

(instance goBridgeScript of Script

	(method (dispose)
		(subMarine cue:)
		(curRoom setScript: (ScriptID 25 5) 0 28)
		(super dispose:)
		(DisposeScript 345)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 25 1)
					init:
					setCycle: Walk
					posn: 214 100
					heading: 270
					loop: 1
					illegalBits: 0
					setLoop: GradualLooper
				)
				(self setScript: (ScriptID 394) self)
			)
			(1 (= cycles 1))
			(2
				(HandsOff)
				(Print 345 0 #at -1 11 #time 7 #dispose)
				((ScriptID 25 1) setMotion: MoveTo 258 108 self)
				(ego setMotion: Follow (ScriptID 25 1) 20)
			)
			(3
				((ScriptID 25 1)
					setMotion: MoveTo ((ScriptID 25 6) x?) ((ScriptID 25 6) y?) self
				)
			)
			(4
				((ScriptID 25 1)
					illegalBits: 0
					view: 625
					setLoop: 0
					heading: 0
					setMotion: MoveTo 298 22 self
				)
				(ego
					setMotion: MoveTo ((ScriptID 25 6) x?) ((ScriptID 25 6) y?) self
				)
			)
			(5)
			(6
				(ego
					illegalBits: 0
					view: 238
					setLoop: 0
					heading: 0
					setMotion: MoveTo 298 52 self
				)
			)
			(7 (self dispose:))
		)
	)
)
