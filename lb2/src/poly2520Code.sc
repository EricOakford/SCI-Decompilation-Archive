;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2520)
(include sci.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2520Code 0
	pts2520 1
)

(local
	[thePoints 32] = [68 102 21 145 123 177 123 189 0 189 0 0 319 0 319 189 232 189 232 177 288 167 277 149 214 149 214 118 224 112 212 102]
	[thePoints_2 8] = [77 107 203 107 203 126 77 126]
)
(instance poly2520Code of Code
	(properties)
	
	(method (doit param1)
		(param1
			add: (poly2520a init: yourself:) (poly2520b init: yourself:)
		)
	)
)

(instance poly2520a of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 16)
		(= points @thePoints)
	)
)

(instance poly2520b of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 4)
		(= points @thePoints_2)
	)
)

(instance pts2520 of MuseumPoints
	(properties
		midPtX 130
		midPtY 145
		southPtX 165
		southPtY 250
	)
)
