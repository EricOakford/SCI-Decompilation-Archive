;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2560)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2560Code 0
	pts2560 1
)

(local
	pts = [
		3 131
		3 185
		314 184
		310 158
		280 148
		258 147
		172 129
		91 139
		50 150
		14 152
		]
	pts2 = [
		42 160
		59 172
		27 179
		13 170
		]
	pts3 = [
		132 142
		162 143
		235 162
		229 166
		119 173
		69 165
		69 154
	]
	pts4 = [
		264 150
		256 156
		224 156
		172 142
		173 140
		]
	pts5 = [
		280 162
		302 174
		283 179
		250 169
		]
)
(instance poly2560Code of Code
	
	(method (doit obj)
		(obj
			add:
				(poly2560a init: yourself:)
				(poly2560b init: yourself:)
				(poly2560c init: yourself:)
				(poly2560d init: yourself:)
				(poly2560e init: yourself:)
		)
	)
)

(instance poly2560a of Polygon

	(method (init)
		(= type PContainedAccess)
		(= size 10)
		(= points @pts)
	)
)

(instance poly2560b of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 4)
		(= points @pts2)
	)
)

(instance poly2560c of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size 7)
		(= points @pts3)
	)
)

(instance poly2560d of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size 5)
		(= points @pts4)
	)
)

(instance poly2560e of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size 4)
		(= points @pts5)
	)
)

(instance pts2560 of MuseumPoints
	(properties
		midPtX 160
		midPtY 165
		westPtX 1010
		westPtY 155
	)
)
