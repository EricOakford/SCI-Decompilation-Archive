;;; Sierra Script 1.0 - (do not remove this comment)
(script# GULL)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)

(public
	regGull 0
)

(local
	numGulls
	[aGull 4]
	i
)
(instance gullBlock1 of Cage)

(instance regGull of Region
	(properties
		name "Gull Region"
	)
	
	(method (init)
		(if (not isNightTime)
			(gullBlock1
				top: -20
				bottom: 55
				left: -30
				right: 349
				init:
			)
			(= numGulls 2)
			(if (and (!= curRoomNum 7) (!= curRoomNum 40) howFast)
				(for ((= i 1)) (<= i numGulls) ((++ i))
					(= [aGull i]
						((Actor new:)
							x: (Random 1 280)
							y: (Random 1 30)
							xStep: 1
							yStep: 1
							cycleSpeed: 1
							observeBlocks: gullBlock1
							setPri: 0
							setCycle: Forward
							setMotion: Wander 15
							view: (if (== curRoomNum 32) 325 else (Random 325 326))
							ignoreHorizon: TRUE
							illegalBits: cYELLOW
							init:
							yourself:
						)
					)
				)
			)
		)
		(super init:)
	)
	
	(method (handleEvent event &tmp invIndex)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(if (not isNightTime)
					(cond 
						((Said '/gull,gull,bird>')
							(cond 
								((Said 'look')
									(Print 504 0)
								)
								((Said 'converse')
									(Print 504 1)
								)
								((Said 'get,capture')
									(Print 504 2)
								)
								((Said 'kiss')
									(Print 504 3)
								)
								((Said 'feed')
									(Print 504 4)
								)
							)
						)
						((Said 'deliver/anyword/gull,gull,bird')
							(cond 
								((not (= invIndex (inventory saidMe:)))
									(Print 504 5)
								)
								((ego has: (inventory indexOf: invIndex))
									(Print 504 6)
								)
								(else
									(DontHave)
								)
							)
						)
						((Said 'throw')
							(cond 
								((not (= invIndex (inventory saidMe:)))
									(Print 504 7)
								)
								((ego has: (inventory indexOf: invIndex))
									(Print 504 8)
								)
								(else
									(DontHave)
								)
							)
						)
					)
				else
					(Print 504 9)
				)
			else
				FALSE
			)
		)
	)
)
