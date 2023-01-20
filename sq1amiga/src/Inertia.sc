;;; Sierra Script 1.0 - (do not remove this comment)
(script# INERTIA) ;809
(include game.sh)
(use Main)
(use PolyPath)
(use Sound)
(use Motion)
(use User)
(use System)


(local
	local0 = [359 338 22 0 67 23 112 68 157 113 202 158 247 203 292 248 337 293]
	theXOff = [0 -1 0 -1 1 -1 1 0 1 1 0 1 -1 1 -1 0 -1 -1]
)
(class Inertia of Code
	(properties
		client 0
		inertia 0
		maxInertia 0
		oldDir 0
		oldSpeed 0
		oldMover 0
		swimCnt 0
		xOff 0
		yOff 0
		inertizing 0
	)
	
	(method (init theClient)
		(if argc
			(= client theClient)
		else
			(= client ego)
		)
		(= xOff
			(= yOff
				(= inertia (= swimCnt (= inertizing (= oldMover 0))))
			)
		)
		(= oldDir (client heading?))
		(if (client mover?)
			(= oldMover (client mover?))
		)
		(if (== client ego)
			(client setScript: inertiaScript)
		)
		(client code: self)
	)
	
	(method (doit &tmp i)
		(cond 
			((client isBlocked:)
				(self moveDone:)
			)
			(inertizing
				(cond 
					((and (== (client heading?) oldDir) (client mover?))
						(self moveDone:)
					)
					((> swimCnt 0)
						(self doMove:)
						(-- swimCnt)
					)
					((> inertia 0)
						(self doMove:)
						(-- inertia)
						(= swimCnt inertia)
					)
					(else
						(self moveDone:)
					)
				)
			)
			(
				(and
					(< inertia maxInertia)
					(>= (++ swimCnt) (client moveSpeed?))
					(client mover?)
				)
				(= oldMover (client mover?))
				(= swimCnt 0)
				(++ inertia)
			)
			(
				(or
					(!= (client heading?) oldDir)
					(and (not (client mover?)) oldMover)
				)
				(for ((= i 0)) (< i 17) ((+= i 2))
					(if
						(and
							(>= [local0 i] oldDir)
							(>= oldDir [local0 (+ i 1)])
						)
						(= xOff [theXOff i])
						(= yOff [theXOff (+ i 1)])
						(= i 17)
					)
				)
				(= inertizing 1)
				(= swimCnt inertia)
				(= oldSpeed (client moveSpeed?))
				(self doMove:)
			)
		)
	)
	
	(method (dispose)
		(client moveSpeed: oldSpeed code: 0)
		(if (== client ego)
			(client setScript: 0)
		)
		(= client 0)
		(super dispose:)
	)
	
	(method (doMove)
		(client
			moveSpeed: inertia
			x: (+ (client x?) (* (client xStep?) xOff))
			y: (+ (client y?) (* (client yStep?) yOff))
		)
	)
	
	(method (moveDone)
		(client moveSpeed: oldSpeed)
		(= oldDir (client heading?))
		(= xOff
			(= yOff
				(= inertia (= swimCnt (= inertizing (= oldMover 0))))
			)
		)
	)
)

(class Swim of Forward
	(method (doit)
		(if (and (client mover?) (not (client isBlocked:)))
			(super doit:)
		)
	)
)

(instance InertTo of Motion
	(method (doit &tmp temp0)
		(if (self onTarget:)
			(self moveDone:)
		else
			(if
				(!=
					(client heading?)
					(GetAngle (client x?) (client y?) x y)
				)
				(InitBresen self)
			)
			(super doit:)
		)
	)
	
	(method (onTarget)
		(return
			(if
				(and
					(>= (+ x 10) (client x?))
					(>= (client x?) (- x 10))
					(>= (+ y 10) (client y?))
				)
				(>= (client y?) (- y 10))
			else
				0
			)
		)
	)
)

(instance inertiaScript of Script
	(method (init)
		(super init: &rest)
		(directionHandler addToFront: self)
	)
	
	(method (dispose)
		(jetSound dispose:)
		(super dispose: &rest)
		(directionHandler delete: self)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(
				(or
					(!= (theIconBar curIcon?) (theIconBar at: ICON_WALK))
					(not (User controls?))
					(!= (event type?) userEvent)
					(event modifiers?)
				)
				(if (& (event type?) direction)
					(jetSound number: 502 loop: 1 play:)
				)
				(super handleEvent: event)
			)
			(else
				(client
					setMotion:
						(if useObstacles PolyPath else InertTo)
						(event x?)
						(event y?)
				)
				(jetSound number: 502 loop: 1 play:)
				(User prevDir: 0)
				(event claimed: TRUE)
			)
		)
	)
)

(instance jetSound of Sound)
