;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2370)
(include game.sh)
(use Main)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2370Code 0
	pts2370 1
)

(local
	pts = [
		0 0
		319 0
		319 189
		291 189
		311 185
		256 148
		306 148
		306 144
		250 144
		221 132
		209 132
		182 121
		154 121
		140 117
		139 112
		120 105
		94 102
		50 98
		0 98
		-100 179
		-100 165
		]
	pts2 = [
		0 0
		319 0
		319 189
		291 189
		311 185
		264 149
		309 149
		309 144
		258 144
		243 137
		230 137
		219 129
		186 136
		167 136
		160 133
		154 121
		140 117
		140 109
		100 109
		94 101
		50 98
		0 98
		]
	pts3 = [
		-100 179
		-100 165
		78 165
		88 171
		83 183
		68 179
		]
	pts4 = [
		202 162
		244 162
		264 168
		272 181
		229 181
		]
	pts5 = [
		137 175
		185 175
		185 189
		137 189
		]
)
(instance poly2370Code of Code
	
	(method (doit obj)
		(obj
			add: (poly2370a init: yourself:) (poly2370b init: yourself:)
		)
		(if (== currentAct 2)
			(obj add: (poly2370c init: yourself:))
		)
		(if (OneOf global128 6 7 8)
			(obj add: (poly2370d init: yourself:))
		)
	)
)

(instance poly2370a of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size (if (> currentAct 2) 21 else 22))
		(= points (if (> currentAct 2) @pts else @pts2))
	)
)

(instance poly2370b of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 6)
		(= points @pts3)
	)
)

(instance poly2370c of Polygon
	(properties)
	
	(method (init)
		(= type PBarredAccess)
		(= size 5)
		(= points @pts4)
	)
)

(instance poly2370d of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 4)
		(= points @pts5)
	)
)

(instance pts2370 of MuseumPoints
	(properties
		midPtX 110
		midPtY 130
		westPtX -10
		westPtY 120
	)
)
