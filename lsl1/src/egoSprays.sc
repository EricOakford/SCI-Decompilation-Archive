;;; Sierra Script 1.0 - (do not remove this comment)
(script# SPRAY) ;812
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use System)

(public
	egoSprays 0
)

(local
	local0
)
(instance egoSprays of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(Bclr fSprayDone)
		(DisposeScript SPRAY)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fSprayDone)
				(HandsOff)
				(if (!= (ego loop?) 1) (ego setHeading: 90))
				(= cycles 3)
			)
			(1
				(ego
					egoSpeed:
					view: 804
					loop: (if (not (ego loop?)) 0 else 1)
					cel: 0
					x: (if (!= (ego loop?) 1) (ego x?) else (- (ego x?) 1))
					setCycle: CycleTo 3 1 self
				)
			)
			(2
				((ScriptID 0 23) play:)
				(ego setCycle: CycleTo 1 -1 self)
			)
			(3 (ego setCycle: CycleTo 4 1 self))
			(4
				((ScriptID 0 23) play:)
				(= cycles 1)
			)
			(5
				(Print 812 0 #at -1 20)
				(ego setCycle: EndLoop self)
			)
			(6 (= seconds 2))
			(7
				(++ sprayCount)
				(= spraySeconds 600)
				(Bclr fMouthSmellsBad)
				(if (Btst fPersonComplained) (Bclr fPersonComplained) (Print 812 1))
				(if (> sprayCount 9)
					(Print 812 2)
					(ego put: iBreathSpray 510)
					(= sprayCount 0)
				)
				(ego
					x: (if (!= (ego loop?) 1) (ego x?) else (+ (ego x?) 1))
				)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
