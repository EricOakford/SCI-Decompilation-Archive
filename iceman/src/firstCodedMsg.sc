;;; Sierra Script 1.0 - (do not remove this comment)
(script# 349)
(include game.sh)
(use Main)
(use subMarine)
(use n396)
(use Submarine_806)
(use EgoDead)
(use Monitor)
(use Eval)
(use System)

(public
	firstCodedMsg 0
)

(local
	local0
)
(instance checkHeading of Monitor
	(properties)
	
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
		(SubPrint 2 349 0)
	)
	
	(method (die)
		(EgoDead 7 0 0 349 1)
	)
)

(instance checkThrottle of Monitor
	(properties
		cycles 10
	)
	
	(method (warn)
		(SubPrint 2 349 2)
	)
	
	(method (die)
		(EgoDead 7 0 0 349 3)
	)
)

(instance checkDepth of Monitor
	(properties
		cycles 20
	)
	
	(method (warn)
		(SubPrint 2 349 4)
	)
	
	(method (die)
		(EgoDead 7 0 0 349 5)
	)
)

(instance checkPitch of Monitor
	(properties
		cycles 30
	)
	
	(method (warn)
		(SubPrint 2 349 6)
	)
	
	(method (die)
		(EgoDead 7 0 0 349 7)
	)
)

(instance firstCodedMsg of Script
	(properties
		seconds 5
	)
	
	(method (doit)
		(switch state
			(4
				(if
					(and
						(not (umod (++ local0) 50))
						(< 290 (Submarine depth:))
						(< (Submarine depth:) 310)
					)
					(if (> local0 200)
						(EgoDead 7 0 0 349 26)
					else
						(SubPrint 4 349 11)
					)
				)
			)
			(21
				(if
					(and
						(< 40 (Submarine depth:))
						(< (Submarine depth:) 70)
					)
					(checkPitch active: 0)
				)
			)
			(22
				(if
					(and
						(< 40 (Submarine depth:))
						(< (Submarine depth:) 70)
					)
					(self cue:)
				)
			)
			(25
				(if (<= (Submarine hSpeed?) 5) (self cue:))
			)
		)
		(checkHeading doit:)
		(checkThrottle doit:)
		(checkDepth doit:)
		(checkPitch doit:)
		(super doit: &rest)
	)
	
	(method (dispose)
		(cond 
			((<= state 25) (EgoDead 7 0 0 349 28))
			((!= (Submarine throttle?) 1) (EgoDead 7 0 0 349 29))
			((!= (Submarine pitch?) 0) (EgoDead 7 0 0 349 30))
			(else
				(theGame changeScore: 1)
				(subMarine cue:)
				(cls)
				(super dispose:)
				(DisposeScript 396)
				(DisposeScript 349)
				(DisposeScript 826)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				((ScriptID 27 4) posn: 46 54)
				(SubPrint 5 349 8)
				(checkHeading init: 353 (Submarine _absHeading?) 5)
				(= seconds 7)
			)
			(2
				(SubPrint 5 349 9)
				(= seconds 7)
			)
			(3
				(SubPrint 4 349 10)
				(= cycles 60)
				(checkThrottle init: 361 3 0)
			)
			(4
				(checkPitch init: 354 20 10)
				(SubPrint 5 349 11)
			)
			(5
				(checkPitch active: 0)
				(SubPrint 5 349 12)
				(checkDepth init: 391 300 10)
				(= seconds 10)
			)
			(6
				(SubPrint 2 349 13)
				(= seconds 2)
			)
			(7
				(SubPrint 2 349 13)
				(= seconds 2)
			)
			(8
				(SubPrint 2 349 13)
				(= seconds 2)
			)
			(9
				(SubPrint 2 349 13)
				(= seconds 2)
			)
			(10
				(SubPrint 5 349 14)
				(= seconds 6)
			)
			(11
				(SubPrint 5 349 15)
				(= seconds 6)
			)
			(12
				(SubPrint 5 349 16)
				(= seconds 6)
			)
			(13
				(SubPrint 5 349 17)
				(= seconds 6)
			)
			(14
				(SubPrint 2 349 18)
				(= seconds 2)
			)
			(15
				(SubPrint 2 349 18)
				(= seconds 2)
			)
			(16
				(SubPrint 2 349 18)
				(= seconds 2)
			)
			(17
				(SubPrint 3 349 19)
				(= seconds 4)
			)
			(18
				(SubPrint 3 349 20)
				(= seconds 15)
			)
			(19
				(SubPrint 5 349 21)
				(checkDepth active: 0)
				(checkPitch active: 0)
				(= seconds 5)
			)
			(20
				(checkPitch init: 354 -20 10)
				(= seconds 4)
			)
			(21
				(SubPrint 4 349 22)
				(checkThrottle active: 0)
				(= seconds 5)
			)
			(22
				(checkThrottle init: 361 2 0)
			)
			(23
				(checkPitch active: 0)
				(= seconds 5)
			)
			(24
				(checkDepth init: 391 60 80)
				(SubPrint 4 349 23)
				(checkThrottle active: 0)
				(= seconds 4)
			)
			(25
				(checkThrottle value: 1 active: 1)
				(= register 0)
			)
			(26
				(= start state)
				(SubPrint 5 349 24)
				(= seconds 15)
			)
			(27
				(if (>= (++ register) 4)
					(EgoDead 7 0 0 349 25)
				else
					(self init:)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(== state 4)
					(Said '/attain,acknowledge,confirm<depth')
				)
				(if
					(and
						(< 290 (Submarine depth:))
						(< (Submarine depth:) 310)
					)
					(self cue:)
				else
					(EgoDead 7 0 0 349 27)
				)
			)
		)
	)
)
