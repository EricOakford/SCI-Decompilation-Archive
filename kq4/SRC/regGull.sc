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
	local0
	[local1 4]
	numGulls
)
(instance gullBlock1 of Cage
	(properties)
)

(instance regGull of Region
	(properties
	;	name "Gull Region"
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
			(= local0 2)
			(if
			(and (!= curRoomNum 7) (!= curRoomNum 40) howFast)
				(= numGulls 1)
				(while (<= numGulls local0)
					(= [local1 numGulls]
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
							ignoreHorizon: 1
							illegalBits: 16384
							init:
							yourself:
						)
					)
					(++ numGulls)
				)
			)
		)
		(super init:)
	)
	
	(method (handleEvent event &tmp inventorySaidMe)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(if (not isNightTime)
					(cond 
						((Said '/gull,gull,bird>')
							(cond 
								((Said 'look') (Print 504 0))
								((Said 'converse') (Print 504 1))
								((Said 'get,capture') (Print 504 2))
								((Said 'kiss') (Print 504 3))
								((Said 'feed') (Print 504 4))
							)
						)
						((Said 'deliver/*/gull,gull,bird')
							(cond 
								((not (= inventorySaidMe (inventory saidMe:))) (Print 504 5))
								((ego has: (inventory indexOf: inventorySaidMe)) (Print 504 6))
								(else (DontHave))
							)
						)
						((Said 'throw')
							(cond 
								((not (= inventorySaidMe (inventory saidMe:))) (Print 504 7))
								((ego has: (inventory indexOf: inventorySaidMe)) (Print 504 8))
								(else (DontHave))
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
