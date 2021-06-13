;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2565)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2565Code 0
	pts2565 1
)

(local
	pts = [
		3 131
		3 185
		314 184
		310 158
		280 148
		258 147
		172 129
		91 139
		50 150
		14 152
		]
)
(instance poly2565Code of Code
	
	(method (doit obj)
		(obj add: (poly2565a init: yourself:))
	)
)

(instance poly2565a of Polygon
	
	(method (init)
		(= type PContainedAccess)
		(= size 10)
		(= points @pts)
	)
)

(instance pts2565 of MuseumPoints)
