;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2435)
(include sci.sh)
(use Main)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2435Code 0
	pts2435 1
)

(local
	[thePoints 32] = [11 185 307 185 307 139 292 129 319 129 319 125 270 125 270 114 120 114 110 70 108 114 92 114 85 123 24 116 76 129 11 163]
	[thePoints_2 30] = [34 156 307 156 307 139 292 129 319 129 319 125 270 125 270 114 120 114 110 70 102 114 92 114 85 123 24 116 76 129]
)
(instance poly2435Code of Code
	(properties)
	
	(method (doit param1)
		(param1
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
	(properties)
	
	(method (init)
		(= type 3)
		(= size 16)
		(= points @thePoints)
	)
)

(instance poly2435b of Polygon
	(properties)
	
	(method (init)
		(= type 3)
		(= size 15)
		(= points @thePoints_2)
	)
)

(instance pts2435 of MuseumPoints
	(properties)
)
