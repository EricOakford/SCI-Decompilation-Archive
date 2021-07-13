;;; Sierra Script 1.0 - (do not remove this comment)
(script# 381)
(include game.sh)
(use Main)
(use DiceRm)
(use Motion)
(use System)

(public
	pickSaltDice 0
)

(instance pickSaltDice of Script
	
	(method (doit)
		(if
			(and
				(not ((ScriptID 39 17) register?))
				(< (self state?) 1)
			)
			(self cue:)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 381)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				((ScriptID 39 0) notify: 7 1)
				((ScriptID 39 7) setLoop: 4 setCycle: CycleTo 3 1 self)
				(= register (proc39_22 (ScriptID 39 3)))
			)
			(2
				((ScriptID 39 20) setLoop: register setCel: 0 hide:)
				((ScriptID 39 21) setCel: (- 5 register))
				((ScriptID 39 7) setLoop: 4 setCycle: EndLoop self)
			)
			(3
				((ScriptID 39 3) eachElementDo: #roll)
				((ScriptID 39 7) stopUpd:)
				(= cycles 2)
			)
			(4
				((ScriptID 39 7)
					setLoop: 6
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(5
				((ScriptID 39 0) notify: 7 0)
				((ScriptID 39 7)
					setLoop: 4
					setCel: 0
					cycleSpeed: 0
					stopUpd:
				)
				(= register (Random 2 4))
				(= cycles 1)
			)
			(6
				((ScriptID 39 6)
					setLoop: 2
					setPri: (+ ((ScriptID 39 7) priority?) 1)
					setCel: 0
				)
				((ScriptID 39 23) number: (SoundFX 76) loop: 1 play:)
				(= cycles 1)
			)
			(7
				((ScriptID 39 6) setCel: 1)
				((ScriptID 39 23) number: (SoundFX 77) loop: 1 play:)
				(= cycles 1)
			)
			(8
				(if register
					(-- register)
					(self changeState: 6)
				else
					(= cycles 1)
				)
			)
			(9
				((ScriptID 39 6) setLoop: 3 setCycle: EndLoop self)
			)
			(10
				((ScriptID 39 6) stopUpd:)
				((ScriptID 39 20) show: setCycle: EndLoop self)
				((ScriptID 39 23) number: (SoundFX 75) loop: 1 play:)
			)
			(11
				((ScriptID 39 3) selected: 0 cue:)
				((ScriptID 39 20) stopUpd:)
				((ScriptID 39 21) stopUpd:)
				((ScriptID 39 23) dispose:)
				(= cycles 2)
			)
			(12
				((ScriptID 39 6) setLoop: 3 setCycle: BegLoop self)
			)
			(13
				((ScriptID 39 6)
					setPri: (- ((ScriptID 39 7) priority?) 1)
					setLoop: 1
				)
				(= cycles 2)
			)
			(14
				((ScriptID 39 6) stopUpd:)
				(self dispose:)
			)
		)
	)
)
