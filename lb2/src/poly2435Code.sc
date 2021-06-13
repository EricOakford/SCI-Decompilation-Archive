;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2435)
(include game.sh)
(use Main)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2435Code 0
	pts2435 1
)

(local
	pts = [
		11 185
		307 185
		307 139
		292 129
		319 129
		319 125
		270 125
		270 114
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
		270 125
		270 114
		120 114
		110 70
		102 114
		92 114
		85 123
		24 116
		76 129
		]
)
(instance poly2435Code of Code

	(method (doit obj)
		(obj
			add:
				(if
					(or
						(> currentAct 3)
						(and (== currentAct 3) (TriggerEvent -20222 1))
					)
					(poly2435b init: yourself:)
				else
					(poly2435a init: yourself:)
				)
		)
	)
)

(instance poly2435a of Polygon
	
	(method (init)
		(= type PContainedAccess)
		(= size 16)
		(= points @pts)
	)
)

(instance poly2435b of Polygon

	(method (init)
		(= type PContainedAccess)
		(= size 15)
		(= points @pts2)
	)
)

(instance pts2435 of MuseumPoints)
