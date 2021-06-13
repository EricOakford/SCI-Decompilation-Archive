;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2521)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2521Code 0
	pts2521 1
)

(local
	pts = [
		0 0
		5 0
		5 5
		0 5
		]
)
(instance poly2521Code of Code
	
	(method (doit obj)
		(obj add: (poly2521a init: yourself:))
	)
)

(instance poly2521a of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size 4)
		(= points @pts)
	)
)

(instance pts2521 of MuseumPoints)
