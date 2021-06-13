;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2430)
(include game.sh)
(use Main)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2430Code 0
	pts2430 1
)

(local
	pts = [
		11 185
		307 185
		307 139
		292 129
		319 129
		319 125
		275 125
		264 114
		120 114
		110 70
		108 114
		92 114
		85 123
		24 116
		76 129
		11 163
		]
	pts2 = [
		34 156
		307 156
		307 139
		292 129
		319 129
		319 125
		274 125
		263 114
		120 114
		110 70
		102 114
		92 114
		85 123
		24 116
		76 129
		]
)
(instance poly2430Code of Code

	(method (doit obj)
		(obj
			add:
				(if
					(or
						(> currentAct 3)
						(and (== currentAct 3) (TriggerEvent -20222 1))
					)
					(poly2430b init: yourself:)
				else
					(poly2430a init: yourself:)
				)
		)
	)
)

(instance poly2430a of Polygon
	
	(method (init)
		(= type PContainedAccess)
		(= size 16)
		(= points @pts)
	)
)

(instance poly2430b of Polygon
	
	(method (init)
		(= type PContainedAccess)
		(= size 15)
		(= points @pts2)
	)
)

(instance pts2430 of MuseumPoints
	(properties
		midPtX 150
		midPtY 130
		northPtX 115
		northPtY 95
		eastPtX 315
		eastPtY 127
		westPtX 44
		westPtY 119
	)
)
