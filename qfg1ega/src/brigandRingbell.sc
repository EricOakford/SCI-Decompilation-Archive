;;; Sierra Script 1.0 - (do not remove this comment)
(script# 279)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	ringBell 0
)

(instance ringBell of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 279)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 93 0) notify: 2)
				((ScriptID 93 4) setCycle: Forward)
				(HighPrint 279 0)
				;'DING DING DING DING....'
				;"Well, maybe the brigands aren't such inhospitable creatures after all."
				(= seconds 5)
			)
			(1
				((ScriptID 93 5)
					setLoop: 4
					setCel: 0
					illegalBits: 0
					init:
					setMotion: MoveTo ((ScriptID 93 5) x?) (- ((ScriptID 93 5) y?) 25)
				)
				(= cycles 2)
			)
			(2
				((ScriptID 93 7)
					setLoop: 5
					setCel: 0
					illegalBits: 0
					init:
					setMotion: MoveTo ((ScriptID 93 7) x?) (- ((ScriptID 93 7) y?) 25)
				)
				(= cycles 2)
			)
			(3
				((ScriptID 93 4) setCel: 0 setCycle: 0)
				((ScriptID 93 6)
					setLoop: 4
					setCel: 0
					illegalBits: 0
					init:
					setMotion: MoveTo ((ScriptID 93 6) x?) (- ((ScriptID 93 6) y?) 25)
				)
				(= cycles 2)
			)
			(4
				((ScriptID 93 8)
					setLoop: 5
					setCel: 0
					illegalBits: 0
					init:
					setMotion: MoveTo ((ScriptID 93 8) x?) (- ((ScriptID 93 8) y?) 25)
				)
				(= cycles 2)
			)
			(5 (= seconds 2))
			(6
				((ScriptID 93 5) setCycle: EndLoop)
				((ScriptID 93 6) setCycle: EndLoop)
				((ScriptID 93 7) setCycle: EndLoop)
				((ScriptID 93 8) setCycle: EndLoop)
				(HighPrint 279 1)
				;Then again, maybe they are.
				(= seconds 4)
			)
			(7
				(EgoDead 279 2
					#icon vEgoDefeatedMagic 0 9
					#title {You ring the bell and your bell gets rung.}
					;The brigands have an even temperament...all bad!   You seem to have a knack for doing the wrong thing.
				)
			)
		)
	)
)
