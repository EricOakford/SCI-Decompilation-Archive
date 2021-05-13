;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2430)
(include sci.sh)
(use Main)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2430Code 0
	pts2430 1
)

(local
	[thePoints 32] = [11 185 307 185 307 139 292 129 319 129 319 125 275 125 264 114 120 114 110 70 108 114 92 114 85 123 24 116 76 129 11 163]
	[thePoints_2 30] = [34 156 307 156 307 139 292 129 319 129 319 125 274 125 263 114 120 114 110 70 102 114 92 114 85 123 24 116 76 129]
)
(instance poly2430Code of Code
	(properties)
	
	(method (doit param1)
		(param1
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
	(properties)
	
	(method (init)
		(= type 3)
		(= size 16)
		(= points @thePoints)
	)
)

(instance poly2430b of Polygon
	(properties)
	
	(method (init)
		(= type 3)
		(= size 15)
		(= points @thePoints_2)
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
