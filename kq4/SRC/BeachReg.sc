;;; Sierra Script 1.0 - (do not remove this comment)
(script# BEACH)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use System)

(public
	beachReg 0
)
(synonyms
	(ocean seawater ocean)
)

(local
	[local0 7]
	thisControl
	saveViewer
)
(instance beachReg of Region
	(properties
		name "Beach Region"
	)
	
	(method (init)
		(super init:)
		(Load VIEW 21)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(cond 
							((Said '<in,under/ocean,water')
								(if (!= (ego view?) 2)
									(Print 503 0)
								else
									(Print 503 1)
								)
							)
							((Said '/ocean,water')
								(Print 503 2)
							)
							((Said '/fish')
								(if (ego has: iFish)
									((Inventory at: iFish) showSelf:)
								else
									(Print 503 3)
								)
							)
							((Said '/beach')
								(Print 503 4)
							)
							((or (Said '/sky') (Said '<up'))
								(if (not isNightTime)
									(Print 503 5)
								else
									(Print 503 6)
								)
							)
							((or (Said '/dirt') (Said '<down'))
								(Print 503 7)
							)
							((Said '/boulder')
								(Print 503 8)
							)
							((Said '/forest')
								(Print 503 9)
							)
							((Said '/grass')
								(Print 503 10)
							)
							((Said '/bush')
								(Print 503 11)
							)
							((Said '/flora,blossom')
								(Print 503 12)
							)
						)
					)
					((Said 'get/flora,blossom')
						(Print 503 13)
					)
					((Said 'get/water')
						(Print 503 14)
					)
					((or (Said 'drink') (Said 'get/drink'))
						(cond 
							((!= (ego view?) 2) (Print 503 15))
							(
								(or
									(& (= thisControl (IsObjectOnControl ego 12)) cCYAN)
									(& thisControl cLCYAN)
									(& thisControl cBLUE)
									(& thisControl cLBLUE)
								)
								(= oldEgoScript (ego script?))
								(ego setScript: drinking)
							)
							(else
								(Print 800 1)
							)
						)
					)
					((Said 'bathe,dive,wade[<enter][/ocean]>')
						(cond 
							((!= currentStatus egoSwimming)
								(Print 503 16)
							)
							((Said '<under')
								(Print 503 17)
							)
							(else
								(Print 503 18)
							)
						)
						(event claimed: TRUE)
					)
					(
					(or (Said 'capture,get/fish') (Said 'fish[<enter]'))
						(cond 
							((== currentStatus egoSwimming)
								(Print 503 19)
							)
							((ego has: iFishingPole)
								(Print 503 20)
							)
							(else
								(Print 503 21)
							)
						)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance drinking of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= isHandsOff TRUE)
				(HandsOff)
				(= saveViewer (ego viewer?))
				(ego viewer: 0 view: 21 cel: 0 setCycle: EndLoop self)
			)
			(1
				(Timer setReal: self 5)
				(= underBits (Print 503 22 #at -1 10 #dispose))
			)
			(2
				(ego setCycle: BegLoop self)
			)
			(3
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(= isHandsOff FALSE)
				(ego view: 2 setCycle: Walk)
				(ego script: oldEgoScript viewer: saveViewer)
				(HandsOn)
			)
		)
	)
)
