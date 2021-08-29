;;; Sierra Script 1.0 - (do not remove this comment)
(script# 278)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	forceGate 0
)

(instance forceGate of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 278)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 93 0) notify: 2)
				((ScriptID 93 0) notify: 1)
				(ChangeGait MOVE_WALK FALSE)
				(ego setMotion: MoveTo 190 151 self)
			)
			(1
				(ego setLoop: 2)
				(HighPrint 278 0)
				;"Well I'll huff, and I'll puff, and I'll smash the gate down."
				(= cycles 1)
			)
			(2
				(ego view: vEgoBreathHeavy setLoop: 0 setCel: 0 setCycle: Forward)
				(= seconds 3)
			)
			(3
				(NormalEgo)
				(ego loop: 3 cel: 0)
				(= cycles 1)
			)
			(4
				(ego view: vEgoRunning setMotion: MoveTo 175 122 self)
			)
			(5
				(ego view: vEgoFall setLoop: 0 setCel: 0 posn: 175 112)
				(= cycles 1)
			)
			(6
				(ego view: vEgoFall setLoop: 0 setCel: 0 posn: 175 108)
				(= cycles 1)
			)
			(7
				(ego setCel: 1 posn: 175 104)
				(= cycles 1)
			)
			(8
				(ego setCel: 1 posn: 175 100)
				(= cycles 1)
			)
			(9
				(ego setCel: 2 setPri: 7 posn: 175 96)
				(= cycles 1)
			)
			(10
				(ego setCel: 2 setPri: 7 posn: 175 92)
				(= cycles 1)
			)
			(11
				(ego view: vEgoFallDown setLoop: 0 setCel: 0 posn: 163 87)
				(= cycles 3)
			)
			(12
				(ego setCel: 1)
				(= cycles 3)
			)
			(13
				(ego setCel: 2 posn: 163 90)
				(= cycles 3)
			)
			(14
				(ego setCel: 3 posn: 163 93)
				(= cycles 3)
			)
			(15
				(ego setCel: 4 posn: 163 98)
				(= cycles 3)
			)
			(16
				(ego
					view: vEgoDefeated
					setLoop: 4
					setCel: -1
					cel: 0
					posn: 160 102
					cycleSpeed: 12
					setCycle: CycleTo 3 1 self
				)
			)
			(17
				(ego setCel: -1 cel: 3 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(18
				((ScriptID 93 0) notify: 0)
				(HighPrint 278 1)
				;"Boy that feels good."
				(if (TrySkill STR tryBreakDownBrigandGate)
					((ScriptID 93 2) setScript: (ScriptID 93 3) self)
				else
					(self cue:)
				)
			)
			(19
				(HandsOff)
				(= seconds 3)
			)
			(20
				(ego posn: (+ (ego x?) 9) (ego y?) loop: 2)
				(= seconds 1)
			)
			(21
				(NormalEgo)
				(ego posn: (- (ego x?) 9) (+ (ego y?) 2))
				(if (Btst fBrigGateOpen)
					(ego illegalBits: cWHITE)
				else
					(ego illegalBits: (| cWHITE cLRED))
				)
				((ScriptID 93 0) notify: 3)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
