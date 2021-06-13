;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2450)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2450Code 0
	pts2450 1
)

(local
	pts = [
		0 0
		319 0
		319 189
		314 189
		314 141
		272 125
		224 120
		0 120
		]
	pts2 = [
		18 124
		137 124
		137 144
		18 144
		]
	pts3 = [
		7 150
		61 150
		61 177
		7 177
		]
	pts4 = [
		94 147
		153 147
		153 180
		94 180
		]
	pts5 = [
		202 136
		239 136
		239 153
		202 153
		]
)
(instance poly2450Code of Code

	(method (doit obj)
		(obj
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

	(method (init)
		(= type PBarredAccess)
		(= size 8)
		(= points @pts)
	)
)

(instance poly2450b of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 4)
		(= points @pts2)
	)
)

(instance poly2450c of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 4)
		(= points @pts3)
	)
)

(instance poly2450d of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 4)
		(= points @pts4)
	)
)

(instance poly2450e of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size 4)
		(= points @pts5)
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
