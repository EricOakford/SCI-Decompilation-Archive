;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2666)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2666Code 0
	pts2666 1
)

(local
	pts = [
		0 0
		5 0
		5 5
		0 5
		]
)
(instance poly2666Code of Code
	
	(method (doit obj)
		(obj add: (poly2666a init: yourself:))
	)
)

(instance poly2666a of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size 4)
		(= points @pts)
	)
)

(instance pts2666 of MuseumPoints)
