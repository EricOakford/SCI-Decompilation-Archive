;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2560)
(include sci.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2560Code 0
	pts2560 1
)

(local
	[thePoints 20] = [3 131 3 185 314 184 310 158 280 148 258 147 172 129 91 139 50 150 14 152]
	[thePoints_2 8] = [42 160 59 172 27 179 13 170]
	[thePoints_3 14] = [132 142 162 143 235 162 229 166 119 173 69 165 69 154]
	[thePoints_4 10] = [264 150 256 156 224 156 172 142 173 140]
	[thePoints_5 8] = [280 162 302 174 283 179 250 169]
)
(instance poly2560Code of Code
	(properties)
	
	(method (doit param1)
		(param1
			add:
				(poly2560a init: yourself:)
				(poly2560b init: yourself:)
				(poly2560c init: yourself:)
				(poly2560d init: yourself:)
				(poly2560e init: yourself:)
		)
	)
)

(instance poly2560a of Polygon
	(properties)
	
	(method (init)
		(= type 3)
		(= size 10)
		(= points @thePoints)
	)
)

(instance poly2560b of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 4)
		(= points @thePoints_2)
	)
)

(instance poly2560c of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 7)
		(= points @thePoints_3)
	)
)

(instance poly2560d of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 5)
		(= points @thePoints_4)
	)
)

(instance poly2560e of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 4)
		(= points @thePoints_5)
	)
)

(instance pts2560 of MuseumPoints
	(properties
		midPtX 160
		midPtY 165
		westPtX 1010
		westPtY 155
	)
)
