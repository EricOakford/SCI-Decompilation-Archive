;;; Sierra Script 1.0 - (do not remove this comment)
(script# 806)
(include sci.sh)
(use Main)
(use PolyPath)
(use Motion)
(use System)

(public
	sToppleSquid 0
	sPushOver 1
)

(local
	local0
	local1
)
(instance sToppleSquid of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego trySkill: 22)
				(walkHandler addToFront: (ScriptID 800 5))
				(if (== ((ScriptID 800 1) loop?) 6)
					(= local0 1)
					((ScriptID 800 1) setLoop: 0 setCel: 0)
				else
					(= local0 0)
				)
				(self cue:)
			)
			(1
				(if local0 ((ScriptID 800 1) hide: dispose:))
				(= cycles 1)
			)
			(2
				((ScriptID 800 5) setPri: 64 setCycle: End self)
			)
			(3
				(messager say: 4 82 0 0 self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sPushOver of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 152 162 self)
			)
			(1
				(ego
					view: 31
					posn: 151 166
					setLoop: 0 1
					setCel: 0
					setCycle: End self
				)
			)
			(2
				(if (== (ego trySkill: 0 300) 1)
					(= local1 1)
					(walkHandler addToFront: (ScriptID 800 5))
					(if (== ((ScriptID 800 1) loop?) 6)
						(= local0 1)
						((ScriptID 800 1) setLoop: 0 setCel: 0)
					else
						(= local0 0)
					)
					(self cue:)
				else
					(messager say: 4 4 7 0 self)
				)
			)
			(3
				(if local1
					(if local0 ((ScriptID 800 1) hide: dispose:))
					((ScriptID 800 5) setPri: 64 setCel: 0 setCycle: End)
				)
				(ego setCycle: Beg self)
			)
			(4
				(ego posn: 152 164 normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
