;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2350)
(include game.sh)
(use Main)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2350Code 0
	pts2350 1
)

(local
	pts = [
		0 156
		38 156
		87 120
		231 120
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
	pts2 = [
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
	pts3 = [
		91 177
		88 171
		96 166
		223 166
		231 171
		227 177
		]
	pts4 = [
		91 177
		88 171
		96 166
		223 166
		231 171
		227 177
		210 177
		202 185
		133 185
		125 177
		]
	pts5 = [
		0 0
		319 0
		319 99
		281 99
		281 66
		259 66
		259 97
		0 97
		]
)
(instance poly2350Code of Code

	(method (doit obj)
		(obj
			add:
				(poly2350a init: yourself:)
				(poly2350b init: yourself:)
				(poly2350c init: yourself:)
		)
	)
)

(instance poly2350a of Polygon

	(method (init)
		(= type PBarredAccess)		
		(= size (if (>= currentAct 2) 16 else 22))
		(= points (if (>= currentAct 2) @pts else @pts2))
	)
)

(instance poly2350b of Polygon

	(method (init)
		(= type PBarredAccess)
		(if (OneOf global128 0 1 4 5 9 13)
			(= size 10)
			(= points @pts4)
		else
			(= size 6)
			(= points @pts3)
		)
	)
)

(instance poly2350c of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size 8)
		(= points @pts5)
	)
)

(instance pts2350 of MuseumPoints
	(properties
		midPtX 275
		midPtY 120
		northPtX 264
		northPtY 86
		eastPtX 330
		eastPtY 120
		westPtX -10
		westPtY 120
	)
)
