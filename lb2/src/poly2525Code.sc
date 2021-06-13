;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2525)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2525Code 0
	pts2525 1
)

(local
	pts = [
		0 0
		5 0
		5 5
		0 5
		]
)
(instance poly2525Code of Code

	(method (doit obj)
		(obj add: (poly2525a init: yourself:))
	)
)

(instance poly2525a of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 4)
		(= points @pts)
	)
)

(instance pts2525 of MuseumPoints)
