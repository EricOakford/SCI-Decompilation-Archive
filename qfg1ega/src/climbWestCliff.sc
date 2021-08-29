;;; Sierra Script 1.0 - (do not remove this comment)
(script# 277)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	climbWestCliff 0
)

(local
	climbCued
)
(instance climbWestCliff of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 277)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 93 0) notify: 2)
				(HandsOff)
				(= climbCued TRUE)
				(ego
					illegalBits: 0
					setPri: 6
					setMotion: MoveTo 78 99 self
				)
			)
			(1
				(ego setLoop: 1 setCycle: Forward)
				(= cycles 1)
			)
			(2
				(ego setMotion: MoveTo 71 96 self)
			)
			(3
				(ego setMotion: MoveTo 64 90 self)
			)
			(4
				(ego setMotion: MoveTo 57 84 self)
			)
			(5
				(ego setMotion: MoveTo 54 78 self)
			)
			(6
				(ego setMotion: MoveTo 76 99 self)
			)
			(7
				(if climbCued
					(= climbCued FALSE)
					(self changeState: 2)
				else
					(self changeState: 8)
				)
			)
			(8
				(HighPrint 277 0)
				;The dirt is too loose for climbing here.
				(ego setMotion: MoveTo 86 99 self)
			)
			(9
				(NormalEgo)
				(ego loop: 2)
				(if (Btst fBrigGateOpen)
					(ego illegalBits: cWHITE)
				else
					(ego illegalBits: (| cWHITE cLRED))
				)
				(HandsOn)
				((ScriptID 93 0) notify: 3)
				(self dispose:)
			)
		)
	)
)
