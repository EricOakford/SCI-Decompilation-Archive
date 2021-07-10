;;; Sierra Script 1.0 - (do not remove this comment)
(script# 346)
(include game.sh)
(use Main)
(use Intrface)
(use Approach)
(use Grooper)
(use Avoider)
(use Motion)
(use System)

(public
	fromGalleyScript 0
)

(instance fromGalleyScript of Script
	(properties
		cycles 2
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 346)
		(DisposeScript 953)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
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
				(Print 346 0 #time 7 #at -1 10 #dispose)
				(= seconds 3)
			)
			(2
				(ego
					setAvoider: Avoider
					setMotion: Approach (ScriptID 25 4) 20 self
				)
			)
			(3
				(ego setScript: (ScriptID 25 2))
			)
		)
	)
)
