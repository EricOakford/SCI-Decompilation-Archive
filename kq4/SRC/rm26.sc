;;; Sierra Script 1.0 - (do not remove this comment)
(script# 26)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)

(public
	Room26 0
)

(local
	local0
	aRipple1
	aRipple2
	aRipple6
	aRipple5
	aRipple3
	local6
	aRipple4
)
(instance Room26 of Room
	(properties
		picture 26
	)
	
	(method (init)
		(= north 20)
		(= south 2)
		(= east 27)
		(= west 25)
		(= horizon 75)
		(= isIndoors FALSE)
		(ego edgeHit: 0)
		(super init:)
		(if isNightTime (curRoom overlay: 126))
		(self setRegions: WATER RIVER MEADOW UNICORN)
		(switch prevRoomNum
			(2
				(cond 
					((> (ego x?) 251) (ego posn: 251 188))
					((< (ego x?) 80) (ego posn: 82 188))
					(else (ego posn: (ego x?) 188))
				)
			)
			(21
				(cond 
					((!= (ego view?) 2) (ego posn: 215 (+ horizon 2)))
					((< (ego y?) 155) (ego posn: 155 (+ horizon 2)))
					(else (ego posn: 255 76))
				)
				(Animate (cast elements?) FALSE)
			)
			(25 (ego posn: 1 (ego y?)))
			(20
				(if (> (ego x?) 185)
					(ego posn: 185 (+ horizon (ego yStep?)))
				else
					(ego posn: (ego x?) (+ horizon (ego yStep?)))
				)
			)
			(27 (ego posn: 318 (ego y?)))
		)
		(ego init:)
		(if (== prevRoomNum 0) (ego posn: 150 149))
		(= aRipple1 (Prop new:))
		(aRipple1
			isExtra: TRUE
			view: 658
			loop: 6
			cel: 1
			posn: 171 85
			setPri: 0
			cycleSpeed: 1
			setCycle: Forward
			ignoreActors:
			init:
		)
		(= aRipple2 (Prop new:))
		(aRipple2
			isExtra: TRUE
			view: 658
			loop: 2
			cel: 1
			posn: 184 118
			setPri: 0
			cycleSpeed: 1
			setCycle: Forward
			ignoreActors:
			init:
		)
		(= aRipple3 (Prop new:))
		(= aRipple4 (Prop new:))
		(aRipple3
			isExtra: TRUE
			view: 658
			loop: 5
			cel: 1
			posn: 117 169
			setPri: 0
			cycleSpeed: 1
			setCycle: Forward
			ignoreActors:
			init:
		)
		(aRipple4
			isExtra: TRUE
			view: 658
			loop: 7
			cel: 0
			posn: 68 181
			setPri: 0
			cycleSpeed: 1
			setCycle: Forward
			ignoreActors:
			init:
		)
		(= aRipple5 (Prop new:))
		(= aRipple6 (Prop new:))
		(aRipple5
			isExtra: TRUE
			view: 658
			loop: 4
			cel: 1
			posn: 159 159
			setPri: 0
			cycleSpeed: 1
			setCycle: Forward
			ignoreActors:
			init:
		)
		(aRipple6
			isExtra: TRUE
			view: 658
			loop: 3
			cel: 1
			posn: 125 137
			setPri: 0
			cycleSpeed: 1
			setCycle: Forward
			ignoreActors:
			init:
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
						(Said 'look/room')
						(Said 'look[<around][/!*]')
					)
				)
				(Print 26 0)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (and (== (ego edgeHit?) NORTH) (> (ego x?) 202))
			(super newRoom: 21)
		else
			(super newRoom: newRoomNumber)
		)
	)
)
