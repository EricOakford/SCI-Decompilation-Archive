;;; Sierra Script 1.0 - (do not remove this comment)
(script# 347)
(include game.sh)
(use Main)
(use Intrface)
(use subMarine)
(use n396)
(use Submarine_806)
(use EgoDead)
(use Monitor)
(use Eval)
(use System)

(public
	secondCoordsScript 0
)

(instance secondCoordsScript of Script
	
	(method (doit)
		(checkHeading doit:)
		(checkThrottle doit:)
		(checkDive doit:)
		(checkDepth doit:)
		(switch state
			(6
				(if
					(and
						(< 290 (Submarine depth:))
						(< (Submarine depth:) 310)
						(not modelessDialog)
					)
					(if (< (++ register) 4)
						(SubPrint 5 347 3)
					else
						(EgoDead 7 0 0 347 13)
					)
				)
			)
			(9
				(if (== (Submarine hSpeed?) 40) (= cycles 1))
			)
			(11
				(if
					(and
						(< 90 (Submarine depth:))
						(< (Submarine depth:) 110)
					)
					(= cycles 1)
				)
			)
			(13
				(if (== (Submarine hSpeed?) 5) (= cycles 1))
			)
			(15
				(if (< (Submarine depth:) 50) (= cycles 1))
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(if (>= state 17)
			(subMarine cue:)
		else
			(EgoDead 7 0 0 347 16)
		)
		(Submarine dive: 3)
		(DisposeScript 396)
		(DisposeScript 826)
		(super dispose:)
		(cls)
		(DisposeScript 347)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 27 4) posn: 56 60)
				(= seconds 5)
			)
			(1
				(SubPrint 5 347 0)
				(= seconds 4)
			)
			(2
				(checkHeading init: 353 (Submarine _absHeading?) 5)
				(= seconds 4)
			)
			(3
				(SubPrint 5 347 1)
				(= seconds 4)
			)
			(4
				(checkThrottle init: 361 4 0)
				(= seconds 25)
			)
			(5
				(SubPrint 5 347 2)
				(= seconds 5)
			)
			(6
				(checkDive init: 354 20 10)
				(SubPrint 5 347 3)
			)
			(7
				(checkDive active: 0)
				(checkDepth init: 391 300 10)
				(SubPrint 5 347 4)
				(= seconds 20)
			)
			(8
				(SubPrint 5 347 5)
				(checkThrottle active: 0)
				(= seconds 4)
			)
			(9
				(checkThrottle active: 1 value: 3)
			)
			(10
				(SubPrint 6 347 6)
				(checkDepth active: 0)
				(= seconds 4)
			)
			(11
				(checkDive active: 1 value: -20)
			)
			(12
				(checkDive active: 0)
				(checkDepth active: 1 value: 100)
				(SubPrint 6 347 7)
				(checkThrottle active: 0)
				(= seconds 4)
			)
			(13
				(checkThrottle active: 1 value: 1) ;0)
			)
			(14
				(SubPrint 5 347 8)
				(checkDepth active: 0)
				(= seconds 4)
			)
			(15 (checkDive active: 1))
			(16
				(checkDepth active: 1 value: 30 variance: 30)
				(= seconds 10)
			)
			(17
				(checkDive active: 0)
				(Print 347 9)
				(SubPrint 5 347 10)
				(= seconds 5)
				(= register 0)
			)
			(18
				(= start state)
				(if (< (++ register) 4)
					(SubPrint 5 347 11)
				else
					(EgoDead 7 0 0 347 12)
				)
				(= seconds 10)
			)
			(19 (self init:))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '/attain,acknowledge,confirm<depth')
				(if (== state 6)
					(if
						(and
							(< 290 (Submarine depth:))
							(< (Submarine depth:) 310)
						)
						(= cycles 1)
					else
						(EgoDead 7 0 0 347 14)
					)
				else
					(Print 347 15)
				)
			)
		)
	)
)

(instance checkThrottle of Monitor
	(properties
		cycles 10
	)
	
	(method (warn)
		(SubPrint 2 347 17)
	)
	
	(method (die)
		(EgoDead 7 0 0 347 18)
	)
)

(instance checkDive of Monitor
	(properties)
	
	(method (warn)
		(SubPrint 3 347 19)
	)
	
	(method (die)
		(EgoDead 7 0 0 347 20)
	)
)

(instance checkDepth of Monitor
	(properties
		cycles 20
	)
	
	(method (warn)
		(SubPrint 2 347 21)
	)
	
	(method (die)
		(EgoDead 7 0 0 347 22)
	)
)

(instance checkHeading of Monitor
	
	(method (doit)
		(if
			(and
				active
				(not (umod (++ cycles) 50))
				(<
					variance
					(umod (- value (Eval Submarine theSelector)) 360)
				)
				(<
					(umod (- value (Eval Submarine theSelector)) 360)
					(- 360 variance)
				)
			)
			(if (<= (++ count) 3)
				(if modelessDialog (-- count) else (self warn:))
			else
				(self die:)
			)
		)
	)
	
	(method (warn)
		(SubPrint 4 347 23)
	)
	
	(method (die)
		(EgoDead 7 0 0 347 24)
	)
)
