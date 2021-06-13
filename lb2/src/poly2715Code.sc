;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2715)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2715Code 0
	pts2715 1
)

(local
	pts = [
		168 170
		217 170
		217 167
		161 167
		203 152
		152 152
		30 170
		]
	pts2 = [
		93 167
		123 162
		169 162
		148 167
		]
)
(instance poly2715Code of Code

	(method (doit obj)
		(obj
			add: (poly2715a init: yourself:) (poly2715b init: yourself:)
		)
	)
)

(instance poly2715a of Polygon
	
	(method (init)
		(= type PContainedAccess)
		(= size 7)
		(= points @pts)
	)
)

(instance poly2715b of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 4)
		(= points @pts2)
	)
)

(instance pts2715 of MuseumPoints
	(properties
		midPtX 200
		midPtY 164
		northPtX 298
		northPtY 140
		eastPtX 319
		eastPtY 189
		westPtY 153
	)
)
