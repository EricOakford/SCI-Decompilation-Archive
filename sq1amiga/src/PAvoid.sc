;;; Sierra Script 1.0 - (do not remove this comment)
(script# PAVOID)
(include game.sh)
(use PolyPath)
(use Polygon)
(use System)


(class PAvoider of Code
	(properties
		client 0
	)
	
	(method (init theClient)
		(if (>= argc 1) (= client theClient))
	)
	
	(method (doit &tmp temp0 temp1 clientMoverDoit temp3 temp4 temp5 temp6 temp7 clientMover clientMoverDoitHeading temp10 temp11 temp12)
		(if
			(and
				(= clientMover (client mover?))
				(IsObject (= clientMoverDoit (clientMover doit:)))
				(not (clientMover completed?))
				(clientMover isKindOf: PolyPath)
				(IsObject (clientMover obstacles?))
			)
			(= temp4
				(-
					(clientMoverDoit brLeft?)
					(= temp0
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
			(= temp5
				(-
					(CoordPri 1 (CoordPri (clientMoverDoit y?)))
					(= temp1 (* (client yStep?) 2))
				)
			)
			(= temp6 (+ (clientMoverDoit brRight?) temp0))
			(= temp7 (+ (clientMoverDoit y?) temp1))
			(if
				(and
					(clientMoverDoit respondsTo: #mover)
					(clientMoverDoit mover?)
				)
				(= clientMoverDoitHeading (clientMoverDoit heading?))
				(= temp10
					(*
						(= temp12
							(Min
								(/
									(* 60 (+ (clientMoverDoit moveSpeed?) 1))
									(* 10 (+ (client moveSpeed?) 1))
								)
								20
							)
						)
						(client xStep?)
					)
				)
				(= temp11 (* temp12 (client yStep?)))
				(= temp0 (SinMult clientMoverDoitHeading temp10))
				(= temp1 (- (CosMult clientMoverDoitHeading temp11)))
				(cond 
					((< temp0 0) (= temp4 (+ temp4 temp0)))
					((> temp0 0) (= temp6 (+ temp6 temp0)))
				)
				(cond 
					((< temp1 0) (= temp5 (+ temp5 temp0)))
					((> temp1 0) (= temp7 (+ temp7 temp0)))
				)
			)
			((clientMover obstacles?)
				add:
					(= temp3
						((Polygon new:)
							init:
								temp4
								temp5
								temp6
								temp5
								(+ temp6 (client xStep?))
								(clientMoverDoit y?)
								temp6
								temp7
								temp4
								temp7
								(- temp4 (client xStep?))
								(clientMoverDoit y?)
							type: 2
							name: {isBlockedPoly}
							yourself:
						)
					)
			)
			(clientMover
				value: 2
				init: client (clientMover finalX?) (clientMover finalY?)
			)
			((clientMover obstacles?) delete: temp3)
			(temp3 dispose:)
		)
	)
)
