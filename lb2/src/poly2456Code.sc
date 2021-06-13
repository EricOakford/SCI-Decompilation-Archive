;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2456)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2456Code 0
	pts2456 1
)

(local
	pts = [
		0 0
		5 0
		5 5
		0 5
		]
)
(instance poly2456Code of Code

	(method (doit obj)
		(obj add: (poly2456a init: yourself:))
	)
)

(instance poly2456a of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size 4)
		(= points @pts)
	)
)

(instance pts2456 of MuseumPoints)
