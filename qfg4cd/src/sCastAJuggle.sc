;;; Sierra Script 1.0 - (do not remove this comment)
(script# 807)
(include sci.sh)
(use Main)
(use Motion)
(use System)

(public
	sCastAJuggle 0
)

(local
	local0
)
(instance sCastAJuggle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego trySkill: 31)
				(sCastAJuggle setScript: (ScriptID 62) self)
			)
			(1
				(theGame handsOff:)
				(if (== ((ScriptID 800 5) cel?) 0)
					(if (> ((ScriptID 800 1) cel?) 0)
						(= local0 0)
						(self cue:)
					else
						(= local0 1)
						((ScriptID 800 1)
							init:
							approachVerbs: 43
							setCycle: End self
						)
					)
				else
					(self cue:)
				)
			)
			(2
				(if (== ((ScriptID 800 5) cel?) 0)
					(cond 
						((== local0 0) (messager say: 15 6 33 0 self))
						((< (ego y?) 100) (messager say: 15 6 32 0 self))
						(else (messager say: 15 6 10 0 self))
					)
				else
					(messager say: 15 6 34 0 self)
				)
			)
			(3
				(if (== ((ScriptID 800 5) cel?) 0)
					(if (== local0 0)
						(self cue:)
					else
						((ScriptID 800 1) setCycle: Beg self)
					)
				else
					(self cue:)
				)
			)
			(4
				(if
				(and (== ((ScriptID 800 1) cel?) 0) (== local0 1))
					((ScriptID 800 1) hide: dispose:)
				)
				(= ticks 6)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
