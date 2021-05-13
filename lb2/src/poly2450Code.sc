;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2450)
(include sci.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2450Code 0
	pts2450 1
)

(local
	[thePoints 16] = [0 0 319 0 319 189 314 189 314 141 272 125 224 120 0 120]
	[thePoints_2 8] = [18 124 137 124 137 144 18 144]
	[thePoints_3 8] = [7 150 61 150 61 177 7 177]
	[thePoints_4 8] = [94 147 153 147 153 180 94 180]
	[thePoints_5 8] = [202 136 239 136 239 153 202 153]
)
(instance poly2450Code of Code
	(properties)
	
	(method (doit param1)
		(param1
			add:
				(poly2450a init: yourself:)
				(poly2450b init: yourself:)
				(poly2450c init: yourself:)
				(poly2450d init: yourself:)
				(poly2450e init: yourself:)
		)
	)
)

(instance poly2450a of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 8)
		(= points @thePoints)
	)
)

(instance poly2450b of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 4)
		(= points @thePoints_2)
	)
)

(instance poly2450c of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 4)
		(= points @thePoints_3)
	)
)

(instance poly2450d of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 4)
		(= points @thePoints_4)
	)
)

(instance poly2450e of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 4)
		(= points @thePoints_5)
	)
)

(instance pts2450 of MuseumPoints
	(properties
		midPtX 200
		midPtY 160
		southPtX 200
		southPtY 250
		westPtX -10
		westPtY 175
	)
)
