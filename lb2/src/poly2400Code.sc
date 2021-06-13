;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2400)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2400Code 0
	pts2400 1
)

(local
	pts = [
		0 163
		0 0
		319 0
		319 189
		299 167
		283 167
		225 109
		91 109
		37 163
		]
	pts2 = [
		186 123
		233 123
		267 163
		207 163
		]
)
(instance poly2400Code of Code
	
	(method (doit obj)
		(obj
			add: (poly2400a init: yourself:) (poly2400b init: yourself:)
		)
	)
)

(instance poly2400a of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 9)
		(= points @pts)
	)
)

(instance poly2400b of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 4)
		(= points @pts2)
	)
)

(instance pts2400 of MuseumPoints)
