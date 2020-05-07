;;; Sierra Script 1.0 - (do not remove this comment)
(script# 458)
(include sci.sh)
(use Main)
(use Gnome)
(use Kq6Procs)
(use Motion)
(use System)

(public
	sightGnome 0
)

(instance sightGnome of Gnome
	(properties
		x 179
		noun 12
		view 458
		EOLx 96
		FOLx 145
		startPoint 5
	)
	
	(method (init)
		(self
			gnomeScript: sightScript
			setScript: sightInit
			stopUpd:
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: noun theVerb 22 1 0 450)
		else
			((ScriptID 450 6) setScript: sightScript 0 theVerb)
		)
	)
)

(instance sightInit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(messager say: 12 0 14 1 self 450)
			)
			(2
				(DisposeScript 1037)
				(UnLoad 128 8930)
				(((ScriptID 450 6) script?) cue:)
				(self dispose:)
			)
		)
	)
)

(instance sightScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(switch register
					(83 (self cue:))
					(31 (self cue:))
					(37
						(messager say: 2 37 42 1 self 450)
					)
					(else 
						(messager say: 12 0 14 2 self 450)
					)
				)
			)
			(1
				(self setScript: (ScriptID 450 2) self register)
			)
			(2
				(sightGnome setLoop: 1 cel: 0 setCycle: CT 2 1 self)
			)
			(3
				(soundFx number: 456 setLoop: 1 play:)
				(sightGnome setCycle: CT 5 1 self)
			)
			(4
				(soundFx play:)
				(sightGnome setCycle: End self)
			)
			(5
				(soundFx play:)
				(if (== register 83)
					(sightGnome setLoop: 4 cel: 0 setCycle: End self)
				else
					(self setScript: failScript 0 register)
				)
			)
			(6
				(sightGnome setLoop: 0 cel: 0)
				(= cycles 2)
			)
			(7
				(messager say: 2 83 14 2 self 450)
			)
			(8
				((ScriptID 450 6)
					setScript: (ScriptID 450 3) 0 sightGnome
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
					((== register 31) (self state: (+ state 1) cue:))
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
				(sightGnome setLoop: 2 cel: 0 setCycle: CT 2 1 self)
			)
			(3
				(soundFx number: 456 setLoop: 1 play:)
				(sightGnome setCycle: CT 4 1 self)
			)
			(4
				(soundFx play:)
				(sightGnome setCycle: CT 9 1 self)
			)
			(5
				(soundFx number: 459 setLoop: 1 play:)
				(sightGnome setCycle: End self)
			)
			(6
				(Bset 59)
				(if (not register)
					(messager say: 16 0 32 1 self 450)
				else
					(messager say: 12 0 14 3 self 450)
				)
			)
			(7
				(Bclr 59)
				(sightGnome setLoop: 0 cel: 0)
				(self setScript: (ScriptID 450 4) self register)
			)
			(8
				(messager say: 16 0 28 1 self 450)
			)
			(9
				(sightGnome addToPic: delete: dispose:)
				(= cycles 10)
			)
			(10
				((ScriptID 450 6) setScript: (ScriptID 450 5))
			)
		)
	)
)
