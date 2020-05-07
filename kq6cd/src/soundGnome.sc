;;; Sierra Script 1.0 - (do not remove this comment)
(script# 456)
(include sci.sh)
(use Main)
(use Gnome)
(use Kq6Procs)
(use Motion)
(use System)

(public
	soundGnome 0
)

(instance soundGnome of Gnome
	(properties
		x 191
		noun 15
		view 456
		EOLx 99
		FOLx 146
		startPoint 2
	)
	
	(method (init)
		(self
			gnomeScript: soundScript
			setScript: soundInit
			stopUpd:
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: noun theVerb 22 1 0 450)
		else
			((ScriptID 450 6) setScript: soundScript 0 theVerb)
		)
	)
)

(instance soundInit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(messager say: 15 0 12 1 self 450)
			)
			(2
				(((ScriptID 450 6) script?) cue:)
				(self dispose:)
			)
		)
	)
)

(instance soundScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(switch register
					(37
						(messager say: 15 37 12 1 self 450)
					)
					(83 (self cue:))
					(31
						(Bset 59)
						(messager say: 15 31 12 1 self 450)
					)
					(else 
						(messager say: 15 0 12 2 self 450)
					)
				)
			)
			(1
				(self setScript: (ScriptID 450 2) self register)
			)
			(2
				(if (== register 37)
					(soundGnome setLoop: 4 cel: 0 setCycle: End self)
				else
					(self setScript: failScript 0 register)
				)
			)
			(3
				(soundGnome setLoop: 3 cycleSpeed: 6)
				(soundGnome
					cel: (soundGnome lastCel:)
					setCycle: Beg self
				)
			)
			(4
				(soundGnome setLoop: 0 cel: 0)
				(ego setCycle: End self)
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
				(messager say: 15 37 12 2 self 450)
			)
			(7
				((ScriptID 450 6)
					setScript: (ScriptID 450 3) 0 soundGnome
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
					(register (ego setCycle: End self))
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
				(soundGnome setLoop: 2 cel: 0 setCycle: End self)
			)
			(3
				(soundGnome setCycle: Beg self)
			)
			(4
				(Bset 59)
				(cond 
					((not register) (messager say: 16 0 34 1 self 450))
					((== register 31) (messager say: 15 31 12 2 self 450))
					(else (messager say: 15 0 12 3 self 450))
				)
			)
			(5
				(soundGnome setLoop: 3 cycleSpeed: 6)
				(soundGnome
					cel: (soundGnome lastCel:)
					setCycle: Beg self
				)
				(Bclr 59)
			)
			(6
				(self setScript: (ScriptID 450 4) self register)
			)
			(7
				(messager say: 16 0 30 1 self 450)
			)
			(8
				(soundGnome addToPic: delete: dispose:)
				(= cycles 10)
			)
			(9
				((ScriptID 450 6) setScript: (ScriptID 450 5))
			)
		)
	)
)
