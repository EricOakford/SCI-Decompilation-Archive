;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2360)
(include game.sh)
(use Main)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2360Code 0
	pts2360 1
)

(local
	pts = [
		0 0
		319 0
		319 95
		247 95
		200 100
		155 113
		89 137
		75 146
		52 146
		22 168
		17 189
		0 189
		]
	pts2 = [
		0 0
		319 0
		319 95
		247 95
		247 120
		200 120
		200 100
		155 113
		152 136
		89 137
		75 146
		52 146
		22 168
		17 189
		0 189
		]
	pts3 = [
		258 164
		420 164
		420 179
		245 179
		241 168
		]
	pts4 = [
		128 152
		128 181
		59 181
		59 152
		]
	pts5 = [
		148 176
		191 176
		191 189
		148 189
		]
)
(instance poly2360Code of Code

	(method (doit obj)
		(obj
			add: (poly2360a init: yourself:) (poly2360b init: yourself:)
		)
		(if (== currentAct 2)
			(obj add: (poly2360c init: yourself:))
		)
		(if (OneOf global128 2 3 10 11 12)
			(obj add: (poly2360d init: yourself:))
		)
	)
)

(instance poly2360a of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size (if (> currentAct 2) 12 else 15))
		(= points (if (> currentAct 2) @pts else @pts2))
	)
)

(instance poly2360b of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 5)
		(= points @pts3)
	)
)

(instance poly2360c of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size 4)
		(= points @pts4)
	)
)

(instance poly2360d of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 4)
		(= points @pts5)
	)
)

(instance pts2360 of MuseumPoints
	(properties
		midPtX 220
		midPtY 130
		eastPtX 330
		eastPtY 120
	)
)
