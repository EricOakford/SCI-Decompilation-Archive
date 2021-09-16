;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room9 0
)
(synonyms
	(pool water)
)

(local
	aBird1
	aBird2
)
(instance Room9 of Room
	(properties
		picture 9
	)
	
	(method (init)
		(= north 3)
		(= south 15)
		(= east 10)
		(= west 8)
		(= horizon 75)
		(= isIndoors FALSE)
		(if (<= (ego y?) (+ horizon 1))
			(ego y: (+ horizon 2))
		)
		(if (< (ego x?) 93) (ego x: 93))
		(super init:)
		(ego view: 2 init:)
		(if isNightTime
			(curRoom overlay: 109)
		)
		(self setRegions: WOODS PAN)
		(if (not isNightTime)
			(if (< (Random 1 100) 50)
				(= aBird1 (Prop new:))
				(aBird1
					view: 342
					loop: 5
					cel: 2
					posn: 283 17
					setPri: 14
					setScript: bird1Actions
					init:
				)
			)
			(if (< (Random 1 100) 50)
				(= aBird2 (Prop new:))
				(aBird2
					view: 342
					loop: 4
					cel: 2
					posn: 58 30
					setPri: 14
					setScript: bird2Actions
					init:
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'climb/boulder')
						(Print 9 0)
					)
					((Said 'look>')
						(cond 
							((Said '/pool')
								(Print 9 1)
							)
							((Said '/boulder')
								(Print 9 2)
							)
							((Said '/blossom')
								(Print 9 3)
							)
							((Said '[<around][/room]')
								(Print 9 4)
							)
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(if (cast contains: pan)
			(= hourLastMetPan gameHours)
			(= minutesLastMetPan gameMinutes)
		)
		(super newRoom: n)
	)
)

(instance bird1Actions of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aBird1 setCycle: Forward)
				(= seconds (Random 1 12))
			)
			(1
				(aBird1 setCycle: 0 cel: 0)
				(= seconds (Random 1 8))
			)
			(2
				(self changeState: 0)
			)
		)
	)
)

(instance bird2Actions of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aBird2 setCycle: Forward)
				(= seconds (Random 1 12))
			)
			(1
				(aBird2 setCycle: 0 cel: 0)
				(= seconds (Random 1 8))
			)
			(2
				(self changeState: 0)
			)
		)
	)
)
