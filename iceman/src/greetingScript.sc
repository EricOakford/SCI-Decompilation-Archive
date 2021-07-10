;;; Sierra Script 1.0 - (do not remove this comment)
(script# 390)
(include game.sh)
(use Main)
(use Intrface)
(use Follow)
(use Grooper)
(use Motion)
(use System)

(public
	greetingScript 0
)

(instance greetingScript of Script
	
	(method (dispose)
		(ego illegalBits: cWHITE)
		(super dispose:)
		(DisposeScript 390)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 25 1)
					init:
					setCycle: Walk
					setLoop: GradualLooper
					posn: 138 116
					illegalBits: 0
					setMotion: MoveTo 112 127 self
				)
				(ego
					view: 232
					illegalBits: 0
					setMotion: MoveTo 96 131 self
				)
			)
			(1)
			(2
				(Print 390 0)
				(Print 390 1)
				(Print 390 2)
				(Print 390 3)
				((ScriptID 25 1) setMotion: MoveTo 174 100 self)
				(ego
					illegalBits: 0
					ignoreActors: 1
					setMotion: Follow (ScriptID 25 1)
				)
			)
			(3
				((ScriptID 25 1) heading: 230)
				(((ScriptID 25 1) looper?)
					doit: (ScriptID 25 1) ((ScriptID 25 1) heading?) self
				)
			)
			(4
				(Print 390 4)
				(Print 390 5)
				(Print 390 6)
				(Print 390 7)
				(Print 390 8)
				((ScriptID 25 1) setMotion: MoveTo 217 100 self)
				(ego illegalBits: 0 setMotion: MoveTo 182 82 self)
			)
			(5)
			(6
				(ego setScript: (ScriptID 25 2))
			)
		)
	)
)
