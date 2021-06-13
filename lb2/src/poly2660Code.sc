;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2660)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2660Code 0
	pts2660 1
)

(local
	pts = [
		123 189
		0 189
		0 0
		319 0
		319 189
		197 189
		195 140
		182 114
		139 114
		123 141
		]
)
(instance poly2660Code of Code

	(method (doit obj)
		(obj add: (poly2660a init: yourself:))
	)
)

(instance poly2660a of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size 10)
		(= points @pts)
	)
)

(instance pts2660 of MuseumPoints
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
