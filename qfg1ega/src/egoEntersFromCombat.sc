;;; Sierra Script 1.0 - (do not remove this comment)
(script# 274)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	egoEntersFromCombat 0
)

(instance egoEntersFromCombat of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 274)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 181 158 self)
				((ScriptID 93 1)
					ignoreActors:
					setPri: 9
					cycleSpeed: 4
					setCycle: EndLoop
				)
			)
			(1
				(ego loop: loopS)
				(= cycles 2)
			)
			(2
				(ego
					view: vEgoDanceBow
					setLoop: 0
					setCel: 0
					posn: (- (ego x?) 3) (+ (ego y?) 8)
				)
				(TimePrint 4 274 0)
				;"I'M BAAD!"
				(= seconds 4)
			)
			(3
				((ScriptID 93 0) notify: 0)
				(NormalEgo)
				(HandsOn)
				(ego loop: 2 posn: (+ (ego x?) 3) (- (ego y?) 8))
				(if (Btst fBrigGateOpen)
					(ego illegalBits: (| cWHITE cYELLOW))
				else
					(ego illegalBits: (| cWHITE cYELLOW cLMAGENTA))
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
