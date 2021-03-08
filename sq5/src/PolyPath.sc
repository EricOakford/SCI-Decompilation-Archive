;;; Sierra Script 1.0 - (do not remove this comment)
(script# POLYPATH)
(include game.sh)
(use Main)
(use Motion)
(use System)


;; Path around an arbitrary set of obstacles, all of which are
;; defined as Polygons and added to the obstacle list via the
;; Rooms setObstacle method. 07/24/90 J.M.H.


(class PolyPath	kindof Motion
	(properties
		value		2	; current location in path
		points	0	; pointer to path array allocated in the kernel
		finalX	0
		finalY	0
		obstacles 0
	)

	(method (init actor theX theY whoCares opt obstList &tmp [buffer 30])
		(if argc											 
			(= client actor)
			(if (> argc 1)
				(cond
					((>= argc 6)
						(= obstacles obstList)
					)
					((not (IsObject obstacles))
						(= obstacles (curRoom obstacles?))
					)
				)
				(if points (Memory MDisposePtr points))
				(= points 
					(AvoidPath 
						(actor x?)
						(actor y?) 
						(= finalX theX) (= finalY theY) 
						(if obstacles
							(obstacles elements?)
						)
						(if obstacles
							(obstacles size?)
						)
						(if (>= argc 5)
							opt
						else
							TRUE
						)
					)
				)
				(if (> argc 3)
					(= caller whoCares)
				)
			)
			(self setTarget:)
		)
		(super init:)
	)

	(method (dispose)
		(if points (Memory MDisposePtr points))
		(= points NULL)
		(super dispose:)
	)

	(method (setTarget &tmp newPoints newX newY theSize [buffer 30])
		(if (!= (WordAt points value) $7777)
			(= x (WordAt points value))
			(= y (WordAt points (++ value)))
			(++ value)
			(if (and	(IsObject altPolyList)
						(= theSize (altPolyList size?))
				)
				(= newPoints
					(AvoidPath  (client x?)
									(client y?)
									x y
									(altPolyList elements?)
									theSize
									0
					)
				)
				(= newX (WordAt newPoints 2))
				(= newY (WordAt newPoints 3))
				(if (or	(!= x newX)
							(!= y newY)
					)
					(= x newX)
					(= y newY)
					(Memory MWriteWord (+ points value 2) $7777)
				)
				(Memory MDisposePtr newPoints)
			)
		)
	)

	(method (moveDone)
		(if (== (WordAt points value) $7777)
			(super moveDone:)
		else
			(self
				setTarget:,
				init:
			)
		)
	)
)