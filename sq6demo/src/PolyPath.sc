;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	POLYPATH.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1990
;;;;
;;;;	Author: 	J. Mark Hood
;;;;	Updated:	
;;;;
;;;;	This mover used the polygon avoidance system to negotiate around
;;;;	polygons.
;;;;
;;;;	Classes:
;;;;		PolyPath


(script# POLYPATH)
(include game.sh)
(use Main)
(use Array)
(use Motion)


(class PolyPath	kindof Motion
	(properties
		value		2	; current location in path
		points	0	; pointer to path array allocated in the kernel
		finalX	0
		finalY	0
		obstacles 0
	)

	(method (init actor theX theY whoCares opt obstList
						&tmp [buffer 30] planeW planeH cp pathData)
		(if argc											 
			(= client actor)
			(if (> argc 1)
				(cond
					((>= argc 6)
						(= obstacles obstList)
					)
					((not obstacles)
						(= obstacles (curRoom obstacles?))
					)
				)
				(if points
					(points dispose:)
				)
				(= cp (client plane?))
				(= planeW (+ (- (cp right?) (cp left?)) 1))
				(= planeH (+ (- (cp bottom?) (cp top?)) 1))

				(= pathData
					(AvoidPath 
						(actor x?)
						(actor y?) 
						(= finalX theX) (= finalY theY) 
						obstacles
						planeW
						planeH
						(if (>= argc 5)
							opt
						else
							TRUE
						)
					)
				)
				((= points (IntArray new:))
					copy: pathData
				)
				(Array ArrayFree pathData)	;EO: Was "KArray"

				(if (> argc 3)
					(= caller whoCares)
				)
			)
			(self setTarget:)
		)
		(super init:)
	)

	(method (dispose)
		(if points
			(points dispose:)
		)
		(= points NULL)
		(super dispose:)
	)

	(method (setTarget &tmp newPoints newX newY theSize planeW planeH cp)
		(if (!= (points at: value) $7777)
			(= x (points at: value))
			(= y (points at: (++ value)))
			(++ value)

			(= cp (client plane?))
			(= planeW (+ (- (cp right?) (cp left?)) 1))
			(= planeH (+ (- (cp bottom?) (cp top?)) 1))
			
			(if (and	altPolyList
						(altPolyList size?)
				)
				(= newPoints (IntArray new:))
				(newPoints copy: 
					(AvoidPath  (client x?)
									(client y?)
									x y
									altPolyList
									planeW
									planeH
									0
					)
				)
				(= newX (newPoints at: 2))
				(= newY (newPoints at: 3))
				(if (or	(!= x newX)
							(!= y newY)
					)
					(= x newX)
					(= y newY)
					(points at: (+ value 2) $7777)
				)
				(newPoints dispose:)
			)
		)
	)

	(method (moveDone)
		(if (== (points at: value) $7777)
			(super moveDone:)
		else
			(self
				setTarget:,
				init:
			)
		)
	)
)