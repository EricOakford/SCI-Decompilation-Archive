;;; Sierra Script 1.0 - (do not remove this comment)
(script# 192)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	jackJumps 0
)

(instance jackJumps of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 192)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 15)
			)
			(1
				((ScriptID 94 14) init: setPri: 13)
				((ScriptID 94 0) notify: 1)
				(= cycles 5)
			)
			(2
				((ScriptID 94 14) dispose:)
				((ScriptID 94 15)
					init:
					show:
					setPri: 13
					setCycle: CycleTo 1 1 self
				)
				((ScriptID 94 16)
					number: (SoundFX 81)
					init:
					loop: 1
					play:
				)
				(if ((ScriptID 94 0) notify: 0)
					((ScriptID 94 1) setScript: (ScriptID 94 5))
					((ScriptID 94 2) setScript: (ScriptID 94 6))
					((ScriptID 94 3) setScript: (ScriptID 94 7))
					((ScriptID 94 4) setScript: (ScriptID 94 8))
				)
			)
			(3
				((ScriptID 94 15) setCel: 2 setCycle: EndLoop self)
				((ScriptID 94 16) play:)
			)
			(4
				((ScriptID 94 15) cycleSpeed: 1 setCycle: CycleTo 1 1 self)
				((ScriptID 94 16) play:)
			)
			(5
				((ScriptID 94 15) setCycle: EndLoop self)
				((ScriptID 94 16) play:)
			)
			(6
				((ScriptID 94 15) cycleSpeed: 2 setCycle: CycleTo 1 1 self)
				((ScriptID 94 16) play:)
			)
			(7
				((ScriptID 94 15) setCycle: EndLoop self)
				((ScriptID 94 16) play:)
			)
			(8
				((ScriptID 94 15) cycleSpeed: 3 setCycle: CycleTo 1 1 self)
				((ScriptID 94 16) play:)
			)
			(9
				((ScriptID 94 15) setCycle: EndLoop self)
				((ScriptID 94 16) play:)
			)
			(10
				((ScriptID 94 15) cycleSpeed: 4 setCycle: CycleTo 1 1 self)
				((ScriptID 94 16) play:)
			)
			(11
				((ScriptID 94 15) setCycle: EndLoop self)
				((ScriptID 94 16) play:)
			)
			(12
				((ScriptID 94 15) cycleSpeed: 5 setCycle: CycleTo 1 1 self)
				((ScriptID 94 16) play:)
			)
			(13
				((ScriptID 94 15) setCycle: EndLoop self)
				((ScriptID 94 16) play:)
			)
			(14
				((ScriptID 94 15) cycleSpeed: 6 setCycle: EndLoop self)
				((ScriptID 94 16) play:)
			)
			(15
				((ScriptID 94 16) dispose:)
				(self dispose:)
			)
		)
	)
)
