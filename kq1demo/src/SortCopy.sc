;;; Sierra Script 1.0 - (do not remove this comment)
(script# SORTCOPY)
(include game.sh)
(use Main)
(use Sight)
(use System)

(public
	SortedAdd 0
)

(local
	frontList
	backList
	outList
)
(procedure (SortedAdd &tmp frontList2 outList2 backList2)
	((= frontList (EventHandler new:))
		add:
		name: {fl}
	)
	((= outList (EventHandler new:))
		add:
		name: {ol}
	)
	((= backList (EventHandler new:))
		add:
		name: {bl}
	)
	((= frontList2 (EventHandler new:)) name: {fl2})
	((= outList2 (EventHandler new:)) name: {ol2})
	((= backList2 (EventHandler new:)) name: {bl2})
	(cast eachElementDo: #perform preSortCode)
	(features eachElementDo: #perform preSortCode)
	(Sort frontList frontList2 frontSortCode)
	(sortedFeatures add: frontList2)
	(Sort outList outList2 frontSortCode)
	(sortedFeatures add: outList2)
	(sortedFeatures add: regions)
	(sortedFeatures add: locales)
	(Sort backList backList2 backSortCode)
	(sortedFeatures add: backList2)
	(frontList release: dispose:)
	(outList release: dispose:)
	(backList release: dispose:)
)

(instance preSortCode of Code
	(properties)
	
	(method (doit param1)
		(cond 
			((CantBeSeen param1 ego) (backList add: param1))
			((IsOffScreen param1) (outList add: param1))
			(else (frontList add: param1))
		)
	)
)

(instance frontSortCode of Code
	(properties)
	
	(method (doit param1 &tmp temp0 temp1)
		(= temp0 (ego distanceTo: param1))
		(= temp1
			(AngleDiff
				(ego heading?)
				(GetAngle (ego x?) (ego y?) (param1 x?) (param1 y?))
			)
		)
		(if (== (umod temp1 180) 0) (-- temp1))
		(if
		(< (= temp0 (Abs (CosDiv (/ temp1 2) temp0))) 0)
			(= temp0 32767)
		)
		(return temp0)
	)
)

(instance backSortCode of Code
	(properties)
	
	(method (doit param1 &tmp temp0 temp1)
		(= temp0 (ego distanceTo: param1))
		(= temp1
			(AngleDiff
				(ego heading?)
				(GetAngle (ego x?) (ego y?) (param1 x?) (param1 y?))
			)
		)
		(if (== (umod temp1 90) 0) (-- temp1))
		(if (< (= temp0 (SinDiv temp1 temp0)) 0)
			(= temp0 32767)
		)
	)
)
