;;; Sierra Script 1.0 - (do not remove this comment)
(script# 927)
(include sci.sh)
(use PolyPath)
(use Polygon)
(use System)


(procedure (localproc_042b param1 &tmp temp0 [temp1 2] temp3 temp4)
	(= temp3 -100)
	(= temp0 0)
	(while (!= temp3 30583)
		(= temp3 (WordAt param1 (* 2 temp0)))
		(++ temp0)
	)
	(return (-- temp0))
)

(procedure (localproc_0454 param1 &tmp temp0 temp1 temp2 temp3)
	(= temp3 (param1 size?))
	(= temp0 0)
	(while (< temp0 temp3)
		(if
		(>= (= temp2 ((= temp1 (param1 at: temp0)) type?)) 16)
			(temp1 type: (- temp2 16))
		)
		(++ temp0)
	)
)

(class PAvoider of Code
	(properties
		client 0
		oldBlocker 0
		oldBlockerMover 0
		oldMoverX -99
		oldMoverY -99
	)
	
	(method (init theClient)
		(if (>= argc 1) (= client theClient))
	)
	
	(method (doit &tmp temp0 temp1 temp2 temp3 theOldBlocker temp5 temp6 temp7 temp8 clientMover [temp10 4] temp14 newPolygon temp16 temp17 [temp18 5] clientHeading)
		(= clientMover (client mover?))
		(if
		(and oldBlocker (>= (client distanceTo: oldBlocker) 20))
			(oldBlocker ignoreActors: 0)
			(if oldBlockerMover (oldBlocker mover: oldBlockerMover))
			(= oldMoverY (= oldMoverX -99))
			(= oldBlocker (= oldBlockerMover 0))
			(if
				(and
					clientMover
					(IsObject (clientMover obstacles?))
					((clientMover obstacles?) isEmpty:)
				)
				((clientMover obstacles?) dispose:)
				(clientMover obstacles: 0)
			)
		)
		(if
			(and
				(= clientMover (client mover?))
				(IsObject (= theOldBlocker (clientMover doit:)))
				(not (clientMover completed?))
				(clientMover isKindOf: PolyPath)
			)
			(if (theOldBlocker respondsTo: #mover)
				(= oldBlockerMover (theOldBlocker mover?))
				(if oldBlockerMover (theOldBlocker mover: 0))
			else
				(= oldBlockerMover 0)
			)
			(= oldMoverX (clientMover finalX?))
			(= oldMoverY (clientMover finalY?))
			((= oldBlocker theOldBlocker) ignoreActors: 1)
			(= temp5
				(-
					(theOldBlocker brLeft?)
					(= temp2
						(+
							(* 2 (client xStep?))
							(/
								(Max
									(CelWide (client view?) 2 0)
									(CelWide (client view?) 0 0)
								)
								2
							)
						)
					)
				)
			)
			(= temp6 (CoordPri 1 (CoordPri (theOldBlocker y?))))
			(= temp3 (* 2 (theOldBlocker yStep?)))
			(= temp7 (+ (theOldBlocker brRight?) temp2))
			(if
				(<=
					(- (= temp8 (+ (theOldBlocker y?) temp3 2)) temp6)
					3
				)
				(= temp6 (- temp6 2))
				(= temp8 (+ temp8 2))
			)
			(= temp0 (- (clientMover finalX?) (client x?)))
			(= temp1 (- (clientMover finalY?) (client y?)))
			(cond 
				(
					(and
						(<= 85 (= clientHeading (client heading?)))
						(<= clientHeading 95)
					)
					(= temp14 0)
				)
				(
				(and (<= 265 clientHeading) (<= clientHeading 275)) (= temp14 1))
				((>= temp1 0) (= temp14 2))
				(else (= temp14 3))
			)
			(switch temp14
				(3
					(= temp17
						((Polygon new:)
							init:
								temp5
								(client y?)
								temp5
								temp6
								temp7
								temp6
								temp7
								(client y?)
								30583
								0
							type: 2
							name: {isBlockedPoly}
							yourself:
						)
					)
				)
				(2
					(= temp17
						((Polygon new:)
							init:
								temp7
								(client y?)
								temp7
								temp8
								temp5
								temp8
								temp5
								(client y?)
								30583
								0
							type: 2
							name: {isBlockedPoly}
							yourself:
						)
					)
				)
				(0
					(= temp17
						((Polygon new:)
							init:
								(client x?)
								temp6
								temp7
								temp6
								temp7
								temp8
								(client x?)
								temp8
								30583
								0
							type: 2
							name: {isBlockedPoly}
							yourself:
						)
					)
				)
				(1
					(= temp17
						((Polygon new:)
							init:
								(client x?)
								temp8
								temp5
								temp8
								temp5
								temp6
								(client x?)
								temp6
								30583
								0
							type: 2
							name: {isBlockedPoly}
							yourself:
						)
					)
				)
			)
			(if (not (clientMover obstacles?))
				(clientMover obstacles: (List new:))
			)
			(if
				(= temp16
					(MergePoly
						(temp17 points?)
						((clientMover obstacles?) elements?)
						((clientMover obstacles?) size?)
					)
				)
				((= newPolygon (Polygon new:))
					points: temp16
					size: (localproc_042b temp16)
					type: 2
					dynamic: 1
				)
			)
			((clientMover obstacles?) add: newPolygon)
			(clientMover
				value: 2
				init: client (clientMover finalX?) (clientMover finalY?)
			)
			((clientMover obstacles?) delete: newPolygon)
			((clientMover obstacles?) delete: temp17)
			(if (IsObject (clientMover obstacles?))
				(localproc_0454 (clientMover obstacles?))
			)
			(temp17 dispose:)
			(newPolygon dispose:)
		)
	)
	
	(method (dispose)
		(if (IsObject oldBlockerMover)
			(oldBlockerMover dispose:)
		)
		(super dispose:)
	)
)
