;;; Sierra Script 1.0 - (do not remove this comment)
(script# PAVOID)
(include game.sh)
(use PolyPath)
(use Polygon)
(use Sight)
(use System)


(class PAvoider of Code
	(properties
		blocker	0
		client	0
	)

;;;	(methods init doit)
	(method (init aClient)
		(if (>= argc 1)	(= client aClient))
	)

	(method (doit &tmp dX dY theObj thePoly objHeading clMover)
	 	(if (and blocker (blocker respondsTo: #mover))
			;; keep obj stopped until client is past
			(if (and 
					(blocker mover?)
					(> (AngleDiff (GetAngle (blocker nsLeft?)) (client heading?)) 45)
					(> (AngleDiff (GetAngle (blocker nsTop?)) (client heading?)) 45)
					(> (AngleDiff (GetAngle (blocker nsRight?)) (client heading?)) 45)
					(> (AngleDiff (GetAngle (blocker nsBottom?)) (client heading?)) 45)
				)
				(blocker signal:(| (blocker signal?) staticView))
			else
				(blocker signal:(& (blocker signal?) (~ staticView)))
				(= blocker 0)
			)
		)
		(if (and 
				(= clMover (client mover?))
				(IsObject (= theObj (clMover doit:)))
				(not (clMover completed?))
				(clMover isKindOf: PolyPath)
			)
			(= blocker theObj)
			
			(= dX (+ (client xStep?) (/ (CelWide (client view?) facingSouth 0) 2)))
			(= dY (* (client yStep?) 2))
			(if (IsObject (clMover obstacles?))
				((clMover obstacles?)
					add:
						(= thePoly
							((Polygon new:)
								init:
								;; left top
								(- (theObj brLeft?) dX) 
								(- (CoordPri PTopOfBand (CoordPri (theObj y?))) dY)
			
								;; right top
								(+ (theObj brRight?) dX) 
								(- (CoordPri PTopOfBand (CoordPri (theObj y?))) dY)

								;; right bottom
								(+ (theObj brRight?) dX) 
								(+ (theObj y?) dY)

								;; left bottom
								(- (theObj brLeft?) dX) 
								(+ (theObj y?) dY),
								name:{isBlockedPoly?}
								yourself:
							)
						)
				)
			)
			(clMover value:2, init: client (clMover finalX?) (clMover finalY?))
			((clMover obstacles?) delete:thePoly)
			(thePoly dispose:)
		)
	)
)
