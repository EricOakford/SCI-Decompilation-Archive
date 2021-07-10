;;; Sierra Script 1.0 - (do not remove this comment)
(script# 395)
(include game.sh)
(use Main)
(use Intrface)
(use Follow)
(use Grooper)
(use Motion)
(use System)

(public
	goodJobScript 0
)

(instance goodJobScript of Script
	(properties)
	
	(method (dispose)
		(ego illegalBits: cWHITE)
		(super dispose:)
		(DisposeScript 395)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
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
			(1
				(ego posn: 180 98)
				(Print 395 0 #time 7 #at -1 1 #dispose)
				(= cycles 1)
			)
			(2
				(HandsOff)
				((ScriptID 25 1) setMotion: MoveTo 138 116 self)
			)
			(3
				(ego illegalBits: 0 setMotion: Follow (ScriptID 25 1) 30)
				((ScriptID 25 1) setMotion: MoveTo 25 177 self)
			)
			(4
				(client setScript: (ScriptID 25 5) 0 30)
			)
		)
	)
)
