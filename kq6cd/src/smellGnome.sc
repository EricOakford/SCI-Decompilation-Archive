;;; Sierra Script 1.0 - (do not remove this comment)
(script# 455)
(include sci.sh)
(use Main)
(use Gnome)
(use Kq6Procs)
(use Motion)
(use System)

(public
	smellGnome 0
)

(instance smellGnome of Gnome
	(properties
		x 197
		noun 14
		view 455
		EOLx 105
		FOLx 157
		startPoint 4
	)
	
	(method (init)
		(self
			gnomeScript: smellScript
			setScript: smellInit
			stopUpd:
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: noun theVerb 22 1 0 450)
		else
			((ScriptID 450 6) setScript: smellScript 0 theVerb)
		)
	)
)

(instance smellInit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(messager say: 14 0 24 1 self 450)
			)
			(2
				(((ScriptID 450 6) script?) cue:)
				(self dispose:)
			)
		)
	)
)

(instance smellScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (= cycles 6))
			(1
				(switch register
					(47
						(messager say: 14 47 24 1 self 450)
					)
					(83 (self cue:))
					(31 (self cue:))
					(37
						(messager say: 2 37 42 1 self 450)
					)
					(else 
						(messager say: 14 0 24 2 self 450)
					)
				)
			)
			(2
				(self setScript: (ScriptID 450 2) self register)
			)
			(3
				(soundFx number: 453 setLoop: 1 play:)
				(smellGnome setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(4
				(if (== register 47)
					(smellGnome
						setLoop: 4
						cel: 0
						cycleSpeed: 12
						setCycle: EndLoop self
					)
				else
					(self setScript: failScript 0 register)
				)
			)
			(5
				(smellGnome setLoop: 3 cycleSpeed: 6)
				(smellGnome
					cel: (smellGnome lastCel:)
					setCycle: BegLoop self
				)
			)
			(6
				(smellGnome cycleSpeed: 6 setLoop: 0 cel: 0)
				(ego setCycle: EndLoop self)
			)
			(7
				(if ((ScriptID 40 0) alexX?)
					(ego
						posn: ((ScriptID 40 0) alexX?) ((ScriptID 40 0) alexY?)
					)
				)
				(if (!= (ego view?) 900) (ego reset: 1))
				(= cycles 6)
			)
			(8
				(messager say: 14 47 24 2 self 450)
			)
			(9
				((ScriptID 450 6)
					setScript: (ScriptID 450 3) 0 smellGnome
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
				(if (not register)
					(smellGnome setLoop: 1 cel: 0 setCycle: EndLoop self)
					(soundFx number: 453 setLoop: 1 play:)
				else
					(= cycles 1)
				)
			)
			(2
				(smellGnome setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(3
				(Bset 59)
				(if (not register)
					(messager say: 16 0 33 1 self 450)
				else
					(messager say: 14 0 24 3 self 450)
				)
			)
			(4
				(smellGnome setLoop: 3 cycleSpeed: 6)
				(smellGnome
					cel: (smellGnome lastCel:)
					setCycle: BegLoop self
				)
				(Bclr 59)
			)
			(5
				(self setScript: (ScriptID 450 4) self register)
			)
			(6
				(messager say: 16 0 29 1 self 450)
			)
			(7
				(smellGnome addToPic: delete: dispose:)
				(= cycles 10)
			)
			(8
				((ScriptID 450 6) setScript: (ScriptID 450 5))
			)
		)
	)
)
