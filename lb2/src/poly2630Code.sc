;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2630)
(include game.sh)
(use Main)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2630Code 0
	pts2630 1
)

(local
	pts = [
		98 141
		90 144
		69 144
		46 154
		0 154
		0 189
		311 189
		311 151
		298 151
		257 143
		265 139
		298 139
		297 132
		268 132
		259 141
		231 141
		220 145
		169 145
		159 143
		115 143
		109 141
		]
	pts2 = [
		98 141
		90 144
		69 144
		46 154
		0 154
		0 189
		311 189
		311 151
		298 151
		258 143
		265 139
		298 139
		297 132
		268 132
		259 141
		231 141
		220 145
		169 145
		159 143
		115 143
		109 141
		]
)
(instance poly2630Code of Code
	
	(method (doit obj)
		(if (Btst 12)
			(obj add: (poly2630b init: yourself:))
		else
			(obj add: (poly2630a init: yourself:))
		)
	)
)

(instance poly2630a of Polygon
	
	(method (init)
		(= type PContainedAccess)
		(= size 21)
		(= points @pts)
	)
)

(instance poly2630b of Polygon

	(method (init)
		(= type PContainedAccess)
		(= size 21)
		(= points @pts2)
	)
)

(instance pts2630 of MuseumPoints
	(properties
		midPtX 230
		midPtY 155
		eastPtX 1255
		eastPtY 143
	)
)
