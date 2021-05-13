;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2456)
(include sci.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2456Code 0
	pts2456 1
)

(local
	[thePoints 8] = [0 0 5 0 5 5 0 5]
)
(instance poly2456Code of Code
	(properties)
	
	(method (doit param1)
		(param1 add: (poly2456a init: yourself:))
	)
)

(instance poly2456a of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 4)
		(= points @thePoints)
	)
)

(instance pts2456 of MuseumPoints
	(properties)
)
