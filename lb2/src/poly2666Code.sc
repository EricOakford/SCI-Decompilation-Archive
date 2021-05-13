;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2666)
(include sci.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2666Code 0
	pts2666 1
)

(local
	[thePoints 8] = [0 0 5 0 5 5 0 5]
)
(instance poly2666Code of Code
	(properties)
	
	(method (doit param1)
		(param1 add: (poly2666a init: yourself:))
	)
)

(instance poly2666a of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 4)
		(= points @thePoints)
	)
)

(instance pts2666 of MuseumPoints
	(properties)
)
