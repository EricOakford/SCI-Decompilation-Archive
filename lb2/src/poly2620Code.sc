;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2620)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2620Code 0
	pts2620 1
)

(local
	pts = [
		0 0
		5 0
		5 5
		0 5
		]
)
(instance poly2620Code of Code
	
	(method (doit obj)
		(obj add: (poly2620a init: yourself:))
	)
)

(instance poly2620a of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 4)
		(= points @pts)
	)
)

(instance pts2620 of MuseumPoints)