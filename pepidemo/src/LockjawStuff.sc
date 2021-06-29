;;; Sierra Script 1.0 - (do not remove this comment)
(script# 838)
(include game.sh)
(use Main)
(use CycleBet)
(use ForCount)
(use Sound)
(use Motion)
(use System)

(public
	LockjawStuff 0
)

(local
	theLoop
	theView
)
(instance LockjawStuff of Script

	(method (doit)
		(cond 
			((== ((ScriptID 895 1) normal?) 99)
				((ScriptID 895 1) view: 1838 normal: 0)
				(UnLoad RES_VIEW 807)
				(UnLoad RES_VIEW 831)
				(UnLoad RES_VIEW 838)
				(= register 1)
				(= state 7)
			)
			((== ((ScriptID 895 1) normal?) 2)
				((ScriptID 895 1) normal: 1)
				(= seconds (= cycles (= ticks 0)))
				(self init:)
			)
			(
				(and
					(not register)
					(!= state 3)
					(not ((ScriptID 895 1) normal?))
				)
				(= state 2)
				(= seconds (= cycles (= ticks 0)))
				(= register 1)
				((ScriptID 895 1) setSpeed: speed)
				(sfx setLoop: 1 stop:)
				(self cue:)
			)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState &tmp [temp0 20])
		(switch (= state newState)
			(0
				(if (not ((ScriptID 895 1) normal?))
					(= state 2)
					(self cue:)
				else
					((ScriptID 895 1)
						normalize:
						ignoreActors: 1
						setLoop: -1
						setHeading: 225 self
					)
				)
			)
			(1
				(if (not ((ScriptID 895 1) normal?))
					(= state 2)
					(self cue:)
				else
					((ScriptID 895 1) setCycle: 0 cel: 5 setLoop: 8)
					(= ticks 30)
				)
			)
			(2
				(if ((ScriptID 895 1) normal?)
					((ScriptID 895 1)
						view: 838
						setLoop: 1
						cel: 0
						cycleSpeed: 6
						setCycle: EndLoop self
					)
					(UnLoad RES_VIEW 807)
				else
					(= cycles 2)
				)
			)
			(3
				(sfx stop:)
				(if (OneOf ((ScriptID 895 1) view?) 838 831)
					((ScriptID 895 1)
						view: 838
						setLoop: 1
						cel: 2
						setCycle: 0
						forceUpd:
						stopUpd:
					)
				)
				(if register (-- state) (= register 0))
				(= cycles 2)
			)
			(4
				(sfx stop:)
				(= register 0)
				(if (not (Random 0 1))
					(= theView 838)
					(UnLoad RES_VIEW 831)
					(if (== 1 (= theLoop (Random 0 2)))
						(-- state)
						(= cycles 2)
					else
						(= seconds (Random 6 15))
					)
				else
					(= theView 831)
					(UnLoad RES_VIEW 838)
					(= theLoop (Random 0 3))
					(= seconds (Random 6 15))
				)
			)
			(5
				(cond 
					(register ((ScriptID 895 1) setLoop: -1) (= cycles (= state 2)))
					(
					(not (OneOf ((ScriptID 895 1) view?) 831 838)) (self init:))
					((== theView 838)
						(switch theLoop
							(0
								(sfx number: 3800 setLoop: 1 play: self)
								(= state 2)
								((ScriptID 895 1)
									view: theView
									setLoop: theLoop
									cel: 0
									cycleSpeed: 6
									setCycle: Forward
								)
							)
							(2
								(sfx number: 4104 setLoop: 1 play: self)
								(= state 2)
								((ScriptID 895 1)
									view: theView
									setLoop: theLoop
									cel: 0
									cycleSpeed: 3
									setCycle: CycleBet 1 2 5
								)
							)
						)
					)
					(else
						(switch theLoop
							(0
								(sfx number: 2901 setLoop: -1 play:)
								((ScriptID 895 1)
									view: theView
									setLoop: theLoop
									cel: 0
									cycleSpeed: 5
									setCycle: EndLoop self
								)
							)
							(1
								(sfx number: 909 setLoop: 1 play: self)
								(= state 2)
								((ScriptID 895 1)
									view: theView
									setLoop: theLoop
									cycleSpeed: 6
									setCycle: Forward
								)
							)
							(2
								(sfx number: 911 setLoop: 1 play: self)
								(= state 2)
								((ScriptID 895 1)
									view: theView
									setLoop: theLoop
									cycleSpeed: 6
									setCycle: ForwardCounter 3
								)
							)
							(3
								(= state 2)
								(sfx number: 911 setLoop: 1 play: self)
								((ScriptID 895 1)
									view: theView
									setLoop: theLoop
									cel: 0
									cycleSpeed: 4
									setCycle: CycleBet 3 5 5
								)
							)
						)
					)
				)
			)
			(6
				(= state 2)
				(sfx number: 940 setLoop: 1 play: self)
			)
			(7 0)
		)
	)
)

(instance sfx of Sound
	(properties
		flags mNOPAUSE
	)
)
