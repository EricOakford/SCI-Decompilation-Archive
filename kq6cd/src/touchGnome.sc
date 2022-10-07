;;; Sierra Script 1.0 - (do not remove this comment)
(script# 457)
(include sci.sh)
(use Main)
(use Gnome)
(use Kq6Procs)
(use Motion)
(use System)

(public
	touchGnome 0
)

(instance touchGnome of Gnome
	(properties
		x 187
		noun 18
		view 457
		EOLx 96
		FOLx 140
		startPoint 5
	)
	
	(method (init)
		(self
			gnomeScript: touchScript
			setScript: touchInit
			stopUpd:
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: noun theVerb 22 1 0 450)
		else
			((ScriptID 450 6) setScript: touchScript 0 theVerb)
		)
	)
)

(instance touchInit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(messager say: 18 0 41 1 self 450)
			)
			(2
				(((ScriptID 450 6) script?) cue:)
				(self dispose:)
			)
		)
	)
)

(instance touchScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(switch register
					(68
						(messager say: 18 68 41 1 self 450)
					)
					(83 (self cue:))
					(31 (self cue:))
					(37
						(messager say: 2 37 42 1 self 450)
					)
					(else 
						(messager say: 18 0 41 2 self 450)
					)
				)
			)
			(1
				(self setScript: (ScriptID 450 2) self register)
			)
			(2
				(soundFx number: 455 setLoop: 1)
				(touchGnome setLoop: 1 cel: 0 setCycle: CycleTo 6 1 self)
			)
			(3
				(soundFx play:)
				(touchGnome setCycle: EndLoop self)
			)
			(4
				(soundFx play:)
				(= cycles 1)
			)
			(5
				(if (== register 68)
					(touchGnome setLoop: 4 cel: 0 setCycle: EndLoop self)
				else
					(self setScript: failScript 0 register)
				)
			)
			(6
				(ego setCycle: EndLoop self)
				(touchGnome setLoop: 3)
				(touchGnome
					cel: (- (NumCels touchGnome) 1)
					setCycle: BegLoop self
				)
			)
			(7 0)
			(8
				(if ((ScriptID 40 0) alexX?)
					(ego
						posn: ((ScriptID 40 0) alexX?) ((ScriptID 40 0) alexY?)
					)
				)
				(if (!= (ego view?) 900) (ego reset: 1))
				(= cycles 6)
			)
			(9
				(messager say: 18 68 41 2 self 450)
			)
			(10
				((ScriptID 450 6)
					setScript: (ScriptID 450 3) 0 touchGnome
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
					(soundFx number: 455 setLoop: 1)
					(touchGnome setLoop: 1 cel: 0 setCycle: CycleTo 6 1 self)
				else
					(= cycles 1)
				)
			)
			(3
				(if (not register)
					(soundFx play:)
					(touchGnome setCycle: EndLoop self)
				else
					(= cycles 1)
				)
			)
			(4
				(if (not register)
					(soundFx play:)
					(= cycles 1)
				else
					(= cycles 1)
				)
			)
			(5
				(touchGnome setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(6
				(Bset 59)
				(if (not register)
					(messager say: 16 0 36 1 self 450)
				else
					(messager say: 18 0 41 3 self 450)
				)
			)
			(7
				(touchGnome cel: 4)
				(touchGnome setCycle: BegLoop self)
			)
			(8
				(touchGnome setLoop: 3)
				(touchGnome
					cel: (touchGnome lastCel:)
					setCycle: BegLoop self
				)
				(Bclr 59)
			)
			(9
				(self setScript: (ScriptID 450 4) self register)
			)
			(10
				(messager say: 16 0 37 1 self 450)
			)
			(11
				(touchGnome addToPic: delete: dispose:)
				(= cycles 10)
			)
			(12
				((ScriptID 450 6) setScript: (ScriptID 450 5))
			)
		)
	)
)
