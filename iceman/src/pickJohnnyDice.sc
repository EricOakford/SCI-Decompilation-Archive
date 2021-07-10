;;; Sierra Script 1.0 - (do not remove this comment)
(script# 380)
(include game.sh)
(use Main)
(use Die)
(use Motion)
(use System)

(public
	pickJohnnyDice 0
)

(instance pickJohnnyDice of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(not ((ScriptID 39 16) register?))
				(< (self state?) 1)
			)
			(self cue:)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 380)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				((ScriptID 39 0) notify: 6 1)
				(= register (proc39_22 (ScriptID 39 2)))
				((ScriptID 39 5) setLoop: 5 setCycle: CycleTo 3 1 self)
			)
			(2
				((ScriptID 39 18) setLoop: register setCel: 0 hide:)
				((ScriptID 39 19) setCel: (- 5 register))
				((ScriptID 39 5) setCycle: EndLoop self)
			)
			(3
				((ScriptID 39 2) eachElementDo: #roll)
				((ScriptID 39 5) stopUpd:)
				(= cycles 2)
			)
			(4
				((ScriptID 39 5)
					setLoop: 9
					setCel: 255
					setCycle: BegLoop self
				)
				(= register (Random 2 4))
			)
			(5
				((ScriptID 39 4) setLoop: 6 setCel: 0)
				((ScriptID 39 23) number: (SoundFX 76) loop: 1 play:)
				(= cycles 1)
			)
			(6
				((ScriptID 39 4) setLoop: 6 setCel: 1)
				((ScriptID 39 23) number: (SoundFX 77) loop: 1 play:)
				(= cycles 1)
			)
			(7
				(if register
					(-- register)
					(self changeState: 5)
				else
					(= cycles 1)
				)
			)
			(8
				((ScriptID 39 23) number: (SoundFX 77) loop: 1 play:)
				((ScriptID 39 4) setLoop: 7 setCycle: EndLoop self)
			)
			(9
				((ScriptID 39 4) stopUpd:)
				((ScriptID 39 18) show: setCycle: EndLoop)
				((ScriptID 39 0) notify: 6 0)
				((ScriptID 39 23) number: (SoundFX 89) loop: 1 play:)
				(= seconds 4)
			)
			(10
				((ScriptID 39 23) dispose:)
				((ScriptID 39 11) init:)
				((ScriptID 39 12) init:)
				((ScriptID 39 13) init:)
				((ScriptID 39 2) eachElementDo: #show selected: 0 cue:)
				((ScriptID 39 18) stopUpd:)
				((ScriptID 39 19) stopUpd:)
				(= cycles 2)
			)
			(11
				((ScriptID 39 4) setLoop: 7 setCycle: BegLoop self)
			)
			(12
				((ScriptID 39 4) stopUpd:)
				(= cycles 2)
			)
			(13 (self dispose:))
		)
	)
)
