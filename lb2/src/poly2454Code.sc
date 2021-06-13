;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2454)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2454Code 0
	pts2454 1
)

(local
	pts = [
		0 0
		319 0
		319 123
		266 123
		272 128
		165 129
		159 125
		104 127
		62 135
		27 145
		7 158
		7 183
		319 183
		319 189
		0 189
		]
)
(instance poly2454Code of Code

	(method (doit obj)
		(obj add: (poly2454a init: yourself:))
	)
)

(instance poly2454a of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 15)
		(= points @pts)
	)
)

(instance pts2454 of MuseumPoints
	(properties
		midPtX 180
		midPtY 155
		eastPtX 330
		eastPtY 155
	)
)
