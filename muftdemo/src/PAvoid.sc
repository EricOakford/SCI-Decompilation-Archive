;;; Sierra Script 1.0 - (do not remove this comment)
(script# PAVOID)
(include game.sh)
(use Main)
(use PolyPath)
(use Polygon)
(use System)


(procedure (localproc_0492 param1 param2 param3 param4 param5 &tmp temp0 temp1 temp2 temp3 temp4)
	(= temp1 (Min param1 param3))
	(= temp0 (Min param2 param4))
	(= temp3 (Max param1 param3))
	(= temp2 (Max param2 param4))
	(if (<= param1 param3)
		(Graph GDrawLine
			param2
			param1
			param4
			param3
			param5
			-1
			-1
		)
	else
		(Graph GDrawLine
			param4
			param3
			param2
			param1
			param5
			-1
			-1
		)
	)
	(Graph GShowBits
		(- temp0 1)
		(- temp1 1)
		(+ temp2 1)
		(+ temp3 1)
		1
	)
)

(class PAvoider of Code
	(properties
		client 0
		oldBlocker 0
		oldMoverX -1
		oldMoverY -1
	)
	
	(method (init theClient)
		(if (>= argc 1) (= client theClient))
	)
	
	(method (doit &tmp temp0 temp1 temp2 temp3 theOldBlocker temp5 temp6 temp7 temp8 clientMover theOldBlockerHeading temp11 temp12 temp13 temp14 temp15 temp16 temp17 temp18 temp19 temp20 temp21 temp22 [temp23 2] temp25)
		(= temp17 0)
		(= clientMover (client mover?))
		(if (and oldBlocker (not clientMover))
			(oldBlocker ignoreActors: 0)
			(= oldMoverY (= oldMoverX -1))
			(= oldBlocker 0)
		)
		(if
			(and
				(= clientMover (client mover?))
				(IsObject (= theOldBlocker (clientMover doit:)))
				(not (clientMover completed?))
				(clientMover isKindOf: PolyPath)
				(IsObject (clientMover obstacles?))
			)
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
			(= temp8 (+ (theOldBlocker y?) temp3 2))
			(if
				(and
					(theOldBlocker respondsTo: #mover)
					(theOldBlocker mover?)
					(not (theOldBlocker isStopped:))
				)
				(= theOldBlockerHeading (theOldBlocker heading?))
				(= temp11
					(*
						(= temp13
							(Min
								(/
									(* 60 (+ (theOldBlocker moveSpeed?) 1))
									(* 10 (+ (client moveSpeed?) 1))
								)
								20
							)
						)
						(client xStep?)
					)
				)
				(= temp12 (* temp13 (client yStep?)))
				(= temp2 (SinMult theOldBlockerHeading temp11))
				(= temp3 (- (CosMult theOldBlockerHeading temp12)))
				(cond 
					((< temp2 0) (= temp5 (+ temp5 temp2)))
					((> temp2 0) (= temp7 (+ temp7 temp2)))
				)
				(cond 
					((< temp3 0) (= temp6 (+ temp6 temp2)))
					((> temp3 0) (= temp8 (+ temp8 temp2)))
				)
			)
			(if (<= (- temp8 temp6) 3) (-- temp6) (++ temp8))
			(= temp16 ((clientMover obstacles?) size?))
			(= temp14 0)
			(while (< temp14 temp16)
				(= temp15 ((clientMover obstacles?) at: temp14))
				(= temp18 (AvoidPath temp5 temp6 temp15))
				(= temp19 (AvoidPath temp7 temp6 temp15))
				(= temp20 (AvoidPath temp7 temp8 temp15))
				(= temp21 (AvoidPath temp5 temp8 temp15))
				(if (or temp18 temp19 temp20 temp21) (= temp17 1))
				(++ temp14)
			)
			(= temp0 (- (clientMover x?) (client x?)))
			(= temp1 (- (clientMover y?) (client y?)))
			(cond 
				(
				(and (<= temp6 (ego y?)) (<= (ego y?) temp8)) (if (>= temp0 0) (= temp22 0) else (= temp22 1)))
				(
				(and (<= temp5 (ego x?)) (<= (ego x?) temp7)) (if (>= temp1 0) (= temp22 2) else (= temp22 3)))
			)
			(switch temp22
				(3
					(= temp25
						((Polygon new:)
							init:
								(client x?)
								(client y?)
								temp5
								temp8
								temp5
								temp6
								temp7
								temp6
								temp7
								temp8
							type: 2
							name: {isBlockedPoly}
							yourself:
						)
					)
				)
				(2
					(= temp25
						((Polygon new:)
							init:
								(client x?)
								(client y?)
								temp7
								temp6
								temp7
								temp8
								temp5
								temp8
								temp8
								temp6
							type: 2
							name: {isBlockedPoly}
							yourself:
						)
					)
				)
				(0
					(= temp25
						((Polygon new:)
							init:
								(client x?)
								(client y?)
								temp5
								temp6
								temp7
								temp6
								temp7
								temp8
								temp5
								temp8
							type: 2
							name: {isBlockedPoly}
							yourself:
						)
					)
				)
				(1
					(= temp25
						((Polygon new:)
							init:
								(client x?)
								(client y?)
								temp7
								temp8
								temp5
								temp8
								temp5
								temp6
								temp7
								temp6
							type: 2
							name: {isBlockedPoly}
							yourself:
						)
					)
				)
			)
			(if (not temp17)
				((clientMover obstacles?) add: temp25)
			else
				(= temp17 0)
			)
			(clientMover
				value: 2
				init: client (clientMover finalX?) (clientMover finalY?)
			)
			(= oldMoverX (clientMover finalX?))
			(= oldMoverY (clientMover finalY?))
			((clientMover obstacles?) delete: temp25)
			(temp25 dispose:)
		)
	)
)
