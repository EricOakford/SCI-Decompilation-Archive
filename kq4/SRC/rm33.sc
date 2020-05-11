;;; Sierra Script 1.0 - (do not remove this comment)
(script# 33)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)

(public
	Room33 0
)

(local
	ripple1
	ripple2
)
(instance Room33 of Room
	(properties
		picture 33
	)
	
	(method (init)
		(= north 31)
		(= south 36)
		(= east 34)
		(= west 31)
		(= horizon 80)
		(= isIndoors FALSE)
		(= oldHorizon horizon)
		(ego edgeHit: 0)
		(super init:)
		(= ripple1 (Prop new:))
		(= ripple2 (Prop new:))
		(ripple1
			isExtra: TRUE
			view: 661
			loop: 0
			cel: 1
			posn: 139 184
			setPri: 0
			setCycle: Forward
			ignoreActors:
			cycleSpeed: 2
			init:
		)
		(ripple2
			isExtra: TRUE
			view: 661
			loop: 1
			cel: 3
			posn: 227 132
			setPri: 0
			setCycle: Forward
			ignoreActors:
			cycleSpeed: 2
			init:
		)
		(switch prevRoomNum
			(36 (ego x: 246 y: 186))
			(39
				(cond 
					((== (ego view?) 2) (ego posn: 138 188))
					((== (ego view?) 5) (ego posn: 113 187))
					((== (ego view?) 6) (ego posn: 84 187))
					((== (ego view?) 7) (ego posn: 58 187))
					(else (ego posn: 20 188))
				)
				(Animate (cast elements?) FALSE)
			)
			(34 (ego posn: 317 (ego y?)))
			(31
				(if (< (ego y?) horizon)
					(ego posn: (ego x?) (+ horizon 2))
				else
					(ego posn: 2 (ego y?))
				)
			)
		)
		(ego xStep: 3 yStep: 2 init:)
		(self setRegions: GENESTA WATER BEACH GULL)
	)
	
	(method (doit)
		(cond 
			((>= (ego y?) 189)
				(if (< (ego x?) 160)
					(= newRoomNum 39)
					(Animate (cast elements?) FALSE)
				else
					(Animate (cast elements?) FALSE)
					(= newRoomNum 36)
				)
			)
			((>= (ego x?) 319) (Animate (cast elements?) FALSE) (= newRoomNum 34))
			((<= (ego x?) 0) (Animate (cast elements?) FALSE) (= newRoomNum 31))
			((<= (ego y?) horizon) (Animate (cast elements?) FALSE) (= newRoomNum 31))
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if
				(and
					(== (event type?) saidEvent)
					(or
						(Said 'look/around')
						(Said 'look/island')
						(Said 'look/room')
						(Said 'look[<around][/!*]')
					)
				)
				(Print 33 0)
			else
				FALSE
			)
		)
	)
)
