;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2730)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2730Code 0
	pts2730 1
)

(local
	pts = [
		0 189
		0 0
		319 0
		319 22
		38 189
		]
	pts2 = [
		51 189
		319 30
		319 189
		]
)
(instance poly2730Code of Code

	(method (doit obj)
		(obj
			add: (poly2730a init: yourself:) (poly2730b init: yourself:)
		)
	)
)

(instance poly2730a of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 5)
		(= points @pts)
	)
)

(instance poly2730b of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size 3)
		(= points @pts2)
	)
)

(instance pts2730 of MuseumPoints
	(properties
		midPtX 200
		midPtY 164
		northPtX 298
		northPtY 140
		eastPtX 319
		eastPtY 189
		westPtY 153
	)
)
