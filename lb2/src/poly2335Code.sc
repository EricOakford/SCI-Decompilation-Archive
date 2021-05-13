;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2335)
(include game.sh)
(use Main)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2335Code 0
	pts2335 1
)

(local
	ptsA = [
		85 179
		85 189
		0 189
		0 0
		319 0
		319 189
		284 189
		284 158
		178 170
		161 167
		161 134
		150 134
		150 168
		143 179
		]
	ptsB = [
		85 179
		85 189
		0 189
		0 0
		319 0
		319 189
		284 189
		284 158
		178 170
		161 167
		161 134
		150 134
		150 168
		]
)
(instance poly2335Code of Code

	(method (doit obj)
		(if (and (== currentAct 2) (not (Btst fFlag25)))
			(obj add: (poly2335a init: yourself:))
		else
			(obj add: (poly2335b init: yourself:))
		)
	)
)

(instance poly2335a of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 14)
		(= points @ptsA)
	)
)

(instance poly2335b of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 13)
		(= points @ptsB)
	)
)

(instance pts2335 of MuseumPoints)
