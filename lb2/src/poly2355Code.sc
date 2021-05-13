;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2355)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2355Code 0
	pts2355 1
)

(local
	ptsA = [
		0 156
		38 156
		66 136
		6 136
		6 125
		69 125
		69 134
		87 120
		231 120
		304 136
		304 157
		319 157
		319 179
		278 179
		255 161
		240 161
		233 154
		94 154
		90 161
		70 161
		44 179
		0 179
		]
	ptsB = [
		91 177
		88 171
		96 166
		223 166
		231 171
		227 177
		]
	ptsC = [
		0 0
		319 0
		319 99
		281 99
		281 66
		256 66
		256 97
		0 97
		]
)
(instance poly2355Code of Code
	
	(method (doit obj)
		(obj
			add:
				(poly2355a init: yourself:)
				(poly2355b init: yourself:)
				(poly2355c init: yourself:)
		)
	)
)

(instance poly2355a of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 22)
		(= points @ptsA)
	)
)

(instance poly2355b of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 6)
		(= points @ptsB)
	)
)

(instance poly2355c of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 8)
		(= points @ptsC)
	)
)

(instance pts2355 of MuseumPoints)
