;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2510)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2510Code 0
	pts2510 1
)

(local
	pts = [
		45 162
		4 169
		4 189
		0 189
		0 0
		319 0
		319 189
		313 189
		313 133
		309 133
		309 168
		284 168
		252 161
		256 154
		286 154
		286 139
		207 151
		89 151
		84 148
		40 148
		5 137
		5 153
		36 153
		]
	pts2 = [
		125 175
		188 175
		188 183
		125 183
		]
)
(instance poly2510Code of Code
	
	(method (doit obj)
		(obj
			add: (poly2510a init: yourself:) (poly2510b init: yourself:)
		)
	)
)

(instance poly2510a of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size 23)
		(= points @pts)
	)
)

(instance poly2510b of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size 4)
		(= points @pts2)
	)
)

(instance pts2510 of MuseumPoints
	(properties
		midPtX 230
		midPtY 160
		northPtX 280
		northPtY 140
		southPtX 100
		southPtY 250
		eastPtX 1305
		eastPtY 163
		westPtX 15
		westPtY 150
	)
)
