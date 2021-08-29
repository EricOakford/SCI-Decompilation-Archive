;;; Sierra Script 1.0 - (do not remove this comment)
(script# 169)
(include game.sh)
(use Main)
(use Motion)
(use User)
(use System)

(public
	goingIn 0
)

(instance goingIn of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 169)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fShemaBringsOrder)
				(Bset fHaveOrdered)
				((ScriptID 301 2)
					ignoreActors:
					setMotion: MoveTo 192 114 self
				)
			)
			(1
				((ScriptID 301 2) loop: 3)
				(self cue:)
			)
			(2
				((ScriptID 301 5) loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(3
				((ScriptID 301 2) setMotion: MoveTo 192 99 self)
			)
			(4
				(Bclr fHaveOrdered)
				((ScriptID 301 5) setCycle: BegLoop self)
				((ScriptID 301 2) setMotion: MoveTo 155 99)
			)
			(5
				(Bclr fShemaAsks)
				((ScriptID 301 5) loop: 1 cel: 0)
				(= cycles 2)
			)
			(6
				((ScriptID 301 5) cel: 1)
				(= cycles 2)
			)
			(7
				((ScriptID 301 5) cel: 0)
				(= cycles 1)
			)
			(8
				((ScriptID 301 5) cel: 2)
				(= cycles 1)
			)
			(9
				(Bclr fShemaBringsOrder)
				((ScriptID 301 5) cel: 0 stopUpd:)
				(HandsOn)
				(User canControl: FALSE)
				((ScriptID 301 2) stopUpd: setScript: 0)
			)
		)
	)
)
