;;; Sierra Script 1.0 - (do not remove this comment)
(script# PESTULON)
(include game.sh)
(use Main)
(use Intrface)
(use Follow)
(use Chase)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	PestRegion 0
)

(define guardCHASES 2)
(define guardSTOPPED 4)

(local
	guardX
	guardY
)
(instance guard of Actor)

(instance beam of View)

(instance blast of Actor)

(instance PestRegion of Region
	(method (init)
		(super init:)
		(Load VIEW 92)
		(Load SOUND 44)
	)
	
	(method (doit)
		(super doit:)
		(if (and (== egoInvisible TRUE) (<= (-- beltTimer) 0))
			(= egoInvisible FALSE)
			(= beltState beltDEPLETED)
			(ego view: 0)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(if
							(or
								(Said '/area')
								(Said '/around')
								(Said '[<around][/noword]')
							)
							(Print 503 0)
						)
					)
					((or (Said 'turn<on/belt') (Said 'activate/belt'))
						(cond 
							((== beltState beltDEPLETED)
								(Print 503 1)
							)
							((== beltState beltTURNEDON)
								(Print 503 2)
							)
							(else
								(Print 503 3)
								(= beltState beltTURNEDON)
								(= egoInvisible TRUE)
								(ego view: 123)
							)
						)
					)
					((or (Said 'turn<off/belt') (Said 'deactivate/belt'))
						(cond 
							((== beltState beltDEPLETED)
								(Print 503 4)
							)
							((== beltState beltTURNEDOFF)
								(Print 503 5)
							)
							(else
								(Print 503 3)
								(= egoInvisible FALSE)
								(= beltState beltTURNEDON)
								(ego view: 0)
							)
						)
					)
					((Said 'converse/guard')
						(= notifyCountdown 3)
						(self notify:)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (notify)
		(self setScript: guardActions)
	)
)

(instance guardActions of Script
	(method (doit)
		(super doit:)
		(if (and (== pestulonGuardState guardCHASES) (== egoInvisible TRUE) (>= state 2))
			(Print 503 6)
			(= pestulonGuardState guardSTOPPED)
			(guard setMotion: 0)
		)
		(if (and (== pestulonGuardState guardSTOPPED) (== egoInvisible FALSE))
			(= pestulonGuardState guardCHASES)
			(= state 1)
			(guard setMotion: Chase ego 250)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 503 7)
				(= seconds notifyCountdown)
			)
			(1
				(= pestulonGuardState guardCHASES)
				(= guardY (Random (- (ego y?) 20) (+ (ego y?) 20)))
				(if (< (ego y?) (+ (curRoom horizon?) 2))
					(= guardY (+ (curRoom horizon?) 2))
				)
				(if (< (Random 1 10000) 5000)
					(= guardX 326)
				else
					(= guardX -7)
				)
				(guard
					view: 593
					setCycle: Walk
					illegalBits: cWHITE
					setAvoider: Avoider
					posn: guardX guardY
					setMotion: Chase ego 250 self
					init:
				)
			)
			(2
				(cond 
					((or (< (guard x?) 3) (> (guard x?) 316))
						(-- state)
						(guard setMotion: Follow ego 5)
						(= seconds 1)
					)
					((and (!= pestulonGuardState guardSTOPPED) (== egoInvisible FALSE))
						(Print 503 8)
						(guard
							setMotion: 0
							setLoop: (if (== (guard loop?) 0) 2 else 3)
							cel: 255
							setCycle: EndLoop self
						)
					)
				)
			)
			(3
				(theMusic number: 44 loop: 1 priority: 2 play:)
				(guard
					setLoop: (if (== (guard loop?) 2) 4 else 5)
					cel: 255
					setCycle: EndLoop self
				)
			)
			(4
				(HandsOff)
				(blast
					view: 593
					setLoop: (if (== (guard loop?) 0) 6 else 7)
					cel: 255
					setCycle: EndLoop
					setStep: 30 10
					ignoreActors:
					illegalBits: 0
					setPri: (ego priority?)
					x: (if (== (guard loop?) 4)
						(+ (guard x?) 3)
					else
						(- (guard x?) 3)
					)
					y: (- (guard y?) 16)
					init:
				)
				(RedrawCast)
				(blast setMotion: MoveTo (ego x?) (- (ego y?) 18) self)
			)
			(5
				(blast dispose:)
				(ego view: 92 setLoop: 2 cel: 0 setCycle: Forward)
				(= seconds 7)
			)
			(6
				(EgoDead 901 0 7 999)
			)
		)
	)
)
