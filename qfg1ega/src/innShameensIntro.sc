;;; Sierra Script 1.0 - (do not remove this comment)
(script# 165)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	shameensIntro 0
	shameenClaps 1
)

(instance shameensIntro of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 10))
			(1
				(if (and (!= prevRoomNum 0) (not (Btst fBeenIn301)))
					((ScriptID 301 1) cycleSpeed: 1 setCycle: EndLoop self)
				else
					(HandsOn)
					(Bclr SHAMEEN_SIGNALING)
					((ScriptID 301 1) setScript: (ScriptID 301 3))
				)
			)
			(2
				(Bset SHAMEEN_SIGNALING)
				((ScriptID 301 1) stopUpd:)
				(HighPrint 165 0)
				;"Welcome!  Welcome, traveler, to the Hero's Tale Inn!  I am Shameen, at your service.  May you find what you seek here."
				(HandsOn)
				(= cycles 100)
			)
			(3
				((ScriptID 301 1) cel: 2 setCycle: BegLoop self)
			)
			(4
				(Bclr SHAMEEN_SIGNALING)
				((ScriptID 301 1) stopUpd: setScript: (ScriptID 301 3))
				(DisposeScript 165)
			)
		)
	)
)

(instance shameenClaps of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 165)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst SHAMEEN_SIGNALING)
					(self cue:)
				else
					((ScriptID 301 1) setCycle: CycleTo 2 1 self)
				)
			)
			(1
				(Bset SHAMEEN_SIGNALING)
				((ScriptID 301 1)
					setLoop: 5
					cel: 0
					cycleSpeed: 2
					setCycle: Forward
				)
				(= cycles 12)
			)
			(2
				((ScriptID 301 1)
					setLoop: 4
					cel: 2
					cycleSpeed: 0
					setCycle: 0
					stopUpd:
				)
				(= cycles 50)
			)
			(3
				((ScriptID 301 1) setCycle: BegLoop self)
			)
			(4
				(Bclr SHAMEEN_SIGNALING)
				((ScriptID 301 1) stopUpd: setScript: (ScriptID 301 3))
			)
		)
	)
)
