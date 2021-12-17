;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64864)
(include sci.sh)
(use Motion)
(use System)


(class SoTwinkle of Script
	(properties
		scratch 0
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
		minCycle 6
		maxCycle 45
		minDelay 30
		maxDelay 200
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setCel: 0
					cycleSpeed: (Random minCycle maxCycle)
					setCycle: End self
				)
			)
			(1
				(= ticks (Random minDelay maxDelay))
				(= state -1)
			)
		)
	)
)

(class SoSuperTwinkle of Script
	(properties
		scratch 0
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
		minX 0
		maxX 0
		minY 0
		maxY 0
		minLoop 0
		maxLoop 0
		minCycle 6
		maxCycle 45
		minDelay 30
		maxDelay 200
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setCel: 0
					setLoop: (Random minLoop maxLoop)
					x: (Random minX maxX)
					y: (Random minY maxY)
					cycleSpeed: (Random minCycle maxCycle)
					show:
					setCycle: End self
				)
			)
			(1
				(client hide:)
				(= ticks (Random minDelay maxDelay))
				(= state -1)
			)
		)
	)
)
