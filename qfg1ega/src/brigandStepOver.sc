;;; Sierra Script 1.0 - (do not remove this comment)
(script# 195)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	stepOverN 0
	stepOverS 1
)

(instance stepOverN of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 195)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 94 0) notify: 6)
				(HandsOff)
				(ego illegalBits: 0)
				(if (< (ego y?) 77)
					(ego setMotion: MoveTo (ego x?) 90 self)
				else
					(ego setMotion: MoveTo (ego x?) 69 self)
				)
			)
			(1
				((ScriptID 94 0) notify: 7)
				(if (< (ego y?) 77)
					(ego setMotion: MoveTo 214 67 self)
				else
					(ego illegalBits: cWHITE)
					(self changeState: 3)
				)
			)
			(2
				((ScriptID 94 17) setCel: 2 setPri: 1 init:)
				(= cycles 1)
			)
			(3
				((ScriptID 94 0) notify: 4)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance stepOverS of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 195)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 94 0) notify: 6)
				(HandsOff)
				(ego illegalBits: 0)
				(if (< (ego y?) 156)
					(ego setMotion: MoveTo (ego x?) 168 self)
				else
					(ego setMotion: MoveTo (ego x?) 150 self)
				)
			)
			(1
				((ScriptID 94 0) notify: 7)
				(ego illegalBits: cWHITE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
