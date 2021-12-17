;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	PAVOID.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	J. Mark Hood & Jack Magn‚
;;;;	Updated: Brian K. Hughes
;;;;
;;;;	This is the polygon avoider class
;;;;
;;;;	Classes:
;;;;		PAvoider
;;;;
;;;;	Procedures:
;;;;		GetPolySize
;;;;		RestoreMergedGons


(script# PAVOID)
(include game.sh)
(use PolyPath)
(use Polygon)
(use System)


;;;(procedure
;;;	GetPolySize
;;;	RestoreMergedGons
;;;
;;;)


(class PAvoider kindof Code
	(properties
		client				0
		oldBlocker			0
		oldBlockerMover	0
		oldMoverX		 	-99
		oldMoverY		 	-99	
	)

	(method (init aClient)
		(if (>= argc 1)
			(= client aClient)
		)
	)

	(method (dispose)
		(if oldBlockerMover
			(oldBlockerMover dispose:)
		)
		(super dispose:)

	)

	(method (doit &tmp mDx mDy dX dY theBlocker  l t r b clMover h xDist yDist
							i 	case mergedPoly mergedPolyPts thePoly [str 5]  head)

		(= clMover (client mover?))
		(if (and
				oldBlocker
;					(or
;						(not clMover)
						(>= (client distanceTo: oldBlocker) 20)
;						(!= (clMover finalX?) oldMoverX)
;						(!= (clMover finalY?) oldMoverY)
;					)
			)

			(oldBlocker ignoreActors: FALSE)		; restore ignore actors
			(if oldBlockerMover
				(oldBlocker mover: oldBlockerMover)
			)

			(= oldMoverX -99)
 			(= oldMoverY -99)
			(= oldBlockerMover 0)
			(= oldBlocker 0)

			(if (and	clMover
						(clMover obstacles?)
						((clMover obstacles?) isEmpty:)
			 	)
				((clMover obstacles?) dispose:)
				(clMover obstacles: 0)
			)
		)

		(if (and 
				(= clMover (client mover?))
			  	(= theBlocker (clMover doit:))
				(not (clMover completed?))
				(clMover isKindOf: PolyPath)
;				(IsObject ((clMover?) obstacles?))
			)				 									; we are blocked

			(if (theBlocker respondsTo: #mover)
				(= oldBlockerMover (theBlocker mover?))
				(if oldBlockerMover
					(theBlocker mover: 0)
				)
			else
				(= oldBlockerMover 0)
			)
			
			(= oldMoverX (clMover finalX?))
			(= oldMoverY (clMover finalY?))

			(= oldBlocker theBlocker)
			(theBlocker ignoreActors: TRUE)		  ; set ignore actors until ready

			;; first, set initial rectangle around obstacle
			;; with no account for motion	taking cel width and xStep
			;;	for client and base rect for blocker (width),
			;; and priority band of blocker and yStep of client (height)
			(= l 
				(- 
					(theBlocker brLeft?) 
					(= dX 
						(+ 
							(* 2 (client xStep?)) 
							(/ 
								(Max 
									(CelWide (client view?) facingSouth 0) 
									(CelWide (client view?) facingEast 0) 
								)
								2
							)
						)
					)
				)
			)

;			(= t (CoordPri PTopOfBand (CoordPri (theBlocker y?))))
			(= dY (* 2 (theBlocker yStep?)))

			(= r (+ (theBlocker brRight?) dX))
			(= b (+ (theBlocker y?) dY 2))
			;; add the polygon to the appropriate list
				

			(if (<= (- b t) 3)					; extend degenerate polygons
				(-= t 2)
				(+= b 2)
			)

			(= mDx (- (clMover finalX?) (client x?)))
			(= mDy (- (clMover finalY?) (client y?)))

														; modify gon based on client heading
			(= head (client heading?))

			(cond
				((<= 85 head 95)
					(= case facingEast)
				)
				((<= 265 head 275)
					(= case facingWest)

				)
				(else
					(if (>= mDy 0)
						(= case facingSouth)
					else
						(= case facingNorth)
					)
				)

			)
 														; construct gon
			(switch case
				(facingNorth
					(= thePoly
						((Polygon new:)
							init:	   
									l (client y?) l t r t r (client y?)  
									$7777 0,

							type:		PBarredAccess
							name		{isBlockedPoly}
							yourself:
						)
					)
				)
				(facingSouth
					(= thePoly
						((Polygon new:)
							init: 	
									r (client y?) r b l b l (client y?) 
									$7777 0,
 

							type:		PBarredAccess
							name		{isBlockedPoly}
							yourself:
						)
					)
				)
				(facingEast
					(= thePoly
						((Polygon new:)
							init:		 
									(client x?) t r t r b (client x?) b 
									$7777 0,
 
 
							type:		PBarredAccess
							name		{isBlockedPoly}
							yourself:
						)
					)
				)

				(facingWest
					(= thePoly
						((Polygon new:)
							init:	   
									(client x?) b l b l t (client x?) t 
									$7777 0,
	

							type:		PBarredAccess
							name		{isBlockedPoly}
							yourself:
						)
					)
				)
			) 

; Actually merge new polygon into list
			(if (not (clMover obstacles?))
				(clMover obstacles: (List new:))
			)
			(=  mergedPolyPts	 (MergePoly (thePoly points?) ((clMover obstacles?) elements?)
						((clMover obstacles?) size?)))

			(if mergedPolyPts
				((= mergedPoly (Polygon new:))
					points: 	mergedPolyPts,
					size:		(GetPolySize mergedPolyPts),
					type: 	PBarredAccess,
					dynamic:	TRUE
				)
		
			)
			; add merged gon to the list
			((clMover obstacles?)
				add: mergedPoly
			)

			;; recalculate path and restart the mover
			(clMover
				value:	2,
				init: 	client (clMover finalX?) (clMover finalY?)
			)
			
			;; dump the polygon
			((clMover obstacles?) delete: mergedPoly)
			((clMover obstacles?) delete: thePoly)

			; If we already had a polygon list, 
			; restore gons that were previously absorbed into the merge op
			(if (clMover obstacles?)
				(RestoreMergedGons (clMover obstacles?))
			)

			(thePoly dispose:)
			(mergedPoly dispose:)
		)
	)
)

; procedure to obtain the size of a polygon

(procedure (GetPolySize tPoly &tmp i j thePts theX theY)
	(= theX -100)
	(= i 0)
	(while (!= theX $7777)
		(= theX	(tPoly at: (* 2 i)))
		(++ i)
	)
	(return (-- i))
)

; procedure to restore gons absorbed by the merge op

(procedure (RestoreMergedGons  pList &tmp i tPoly tType sz)
	(= sz (pList size?))
	(for ((= i 0)) (< i sz) ((++ i))
		(= tPoly (pList at: i))
		(= tType (tPoly type?))
		(if (>=  tType  16)
			(tPoly type: (- tType 16))
		)
	)
)