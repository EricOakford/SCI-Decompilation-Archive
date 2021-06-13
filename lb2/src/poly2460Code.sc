;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2460)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2460Code 0
	pts2460 1
)

(local
	pts = [
		0 0
		319 0
		319 162
		284 150
		265 140
		186 139
		151 145
		111 143
		97 89
		90 145
		78 146
		72 169
		0 168
		]
	pts2 = [
		192 144
		222 144
		222 155
		193 155
		]
)
(instance poly2460Code of Code

	(method (doit obj)
		(obj
			add: (poly2460a init: yourself:) (poly2460b init: yourself:)
		)
	)
)

(instance poly2460a of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size 13)
		(= points @pts)
	)
)

(instance poly2460b of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size 4)
		(= points @pts2)
	)
)

(instance pts2460 of MuseumPoints
	(properties)
)
