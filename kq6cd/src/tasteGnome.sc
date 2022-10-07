;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4561)
(include sci.sh)
(use Main)
(use Gnome)
(use Motion)
(use System)

(public
	tasteGnome 0
)

(instance tasteGnome of Gnome
	(properties
		x 190
		noun 17
		view 4561
		EOLx 95
		FOLx 149
		startPoint 3
	)
	
	(method (init)
		(self
			gnomeScript: tasteScript
			setScript: tasteInit
			stopUpd:
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: noun theVerb 22 1 0 450)
		else
			((ScriptID 450 6) setScript: tasteScript 0 theVerb)
		)
	)
)

(instance tasteInit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(messager say: 17 0 39 1 self 450)
			)
			(2
				(((ScriptID 450 6) script?) cue:)
				(self dispose:)
			)
		)
	)
)

(instance tasteScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(switch register
					(63
						(messager say: 17 63 39 1 self 450)
					)
					(83 (self cue:))
					(31 (self cue:))
					(37
						(messager say: 2 37 42 1 self 450)
					)
					(else 
						(messager say: 17 0 39 2 self 450)
					)
				)
			)
			(1
				(self setScript: (ScriptID 450 2) self register)
			)
			(2
				(soundFx number: 454 setLoop: 1 play:)
				(tasteGnome setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3
				(if (== register 63)
					(tasteGnome setLoop: 4 cel: 0 setCycle: EndLoop self)
				else
					(self setScript: failScript 0 register)
				)
			)
			(4
				(tasteGnome setLoop: 0 cel: 0)
				(ego setCycle: EndLoop self)
			)
			(5
				(if ((ScriptID 40 0) alexX?)
					(ego
						posn: ((ScriptID 40 0) alexX?) ((ScriptID 40 0) alexY?)
					)
				)
				(if (!= (ego view?) 900) (ego reset: 1))
				(= cycles 6)
			)
			(6
				(messager say: 17 63 39 2 self 450)
			)
			(7
				(ego put: 23 450)
				(theIconBar curIcon: (theIconBar at: 0))
				((ScriptID 450 6)
					setScript: (ScriptID 450 3) 0 tasteGnome
				)
			)
		)
	)
)

(instance failScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(cond 
					(
					(or ((ScriptID 40 0) alexInvisible?) (== register 31)) (self state: (+ state 1) cue:))
					(register (ego setCycle: EndLoop self))
					(else (= cycles 1))
				)
			)
			(1
				(if ((ScriptID 40 0) alexX?)
					(ego
						posn: ((ScriptID 40 0) alexX?) ((ScriptID 40 0) alexY?)
					)
				)
				(if (!= (ego view?) 900) (ego reset: 1))
				(= cycles 2)
			)
			(2
				(if (not register)
					(tasteGnome setCycle: BegLoop self)
					(soundFx number: 454 setLoop: 1 play:)
				else
					(= cycles 1)
				)
			)
			(3
				(tasteGnome setLoop: 2 setCycle: EndLoop self)
			)
			(4
				(tasteGnome setCycle: BegLoop self)
			)
			(5
				(if (not register)
					(messager say: 16 0 35 1 self 450)
				else
					(messager say: 17 0 39 3 self 450)
				)
			)
			(6
				(self setScript: (ScriptID 450 4) self register)
			)
			(7
				(messager say: 16 0 31 1 self 450)
			)
			(8
				(tasteGnome addToPic: delete: dispose:)
				(= cycles 10)
			)
			(9
				((ScriptID 450 6) setScript: (ScriptID 450 5))
			)
		)
	)
)
