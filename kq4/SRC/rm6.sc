;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)

(public
	Room6 0
)

(local
	local0
	smoke
)
(instance Room6 of Room
	(properties
		picture 6
	)
	
	(method (init)
		(= south 12)
		(= west 5)
		(= horizon 80)
		(= isIndoors FALSE)
		(ego view: 2 edgeHit: 0)
		(super init:)
		(if isNightTime (curRoom overlay: 106))
		(self setRegions: FOREST MOUNTAIN)
		(switch prevRoomNum
			(west
				(cond 
					((>= (ego y?) 160) (ego x: 1 y: 160))
					((<= (ego y?) 84) (ego x: 2 y: 84))
					(else (ego x: 1))
				)
			)
			(south
				(cond 
					((>= (ego x?) 265) (ego posn: 265 187))
					((<= (ego x?) 100) (ego posn: 100 187))
					(else (ego posn: (ego x?) 187))
				)
			)
			(57 (ego loop: 1 x: 200 y: 152))
			(0 (ego x: 180 y: 160))
		)
		(= smoke (Prop new:))
		(smoke
			view: 628
			loop: 1
			cel: 0
			x: 247
			y: 34
			setCycle: Forward
			setPri: 15
			cycleSpeed: 2
			init:
		)
		(ego init:)
		(ego setStep: 3 2)
	)
	
	(method (doit)
		(super doit:)
		(if (& (= local0 (ego onControl: 0)) $0040)
			(= newRoomNum 57)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look<in,in,in/cave,skull') (Print 6 0))
					(
						(or
							(Said 'look/room')
							(Said 'look/around')
							(Said 'look[<around][/!*]')
						)
						(Print 6 1)
					)
					((Said 'look>')
						(cond 
							((Said '/cave,skull') (Print 6 2))
							((Said '/grass') (Print 6 3))
							((Said '/bush') (Print 6 4))
							((Said '/flora') (Print 6 5))
							((Said 'blossom') (Print 6 6))
							((Said '/forest') (Print 6 7))
							((Said '/boulder') (Print 6 8))
							((or (Said '/sky') (Said '<up')) (if (not isNightTime) (Print 6 9) else (Print 6 10)))
						)
					)
					((Said 'get/blossom') (Print 6 6))
					((Said 'climb/boulder') (Print 6 11))
					((Said 'look/boulder') (Print 6 12))
					((Said 'look/dirt') (Print 6 13))
					((Said 'climb/forest') (Print 6 14))
					((Said 'kill/forest') (Print 6 15))
				)
			else
				0
			)
		)
	)
)
