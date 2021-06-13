;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2720)
(include game.sh)
(use Main)
(use Polygon)
(use System)

(public
	poly2720Code 0
	proc2720_1 1
)

(local
	pts = [
		3 173
		30 173
		54 187
		315 186
		315 167
		303 172
		235 159
		253 142
		207 140
		189 153
		154 161
		99 161
		43 148
		30 142
		4 148
		]
	pts2 = [
		3 173
		30 173
		54 187
		315 186
		315 167
		303 172
		294 181
		253 185
		252 176
		267 166
		235 159
		253 142
		207 140
		189 153
		154 161
		99 161
		43 148
		30 142
		4 148
		]
	pts3 = [
		3 173
		30 173
		54 187
		315 186
		309 174
		283 172
		273 180
		245 172
		250 164
		235 160
		253 142
		207 140
		189 153
		154 161
		99 161
		43 148
		30 142
		4 148
		]
)
(procedure (proc2720_1)
)

(instance poly2720Code of Code
	
	(method (doit obj)
		(cond 
			((Btst 65)
				(obj add: (poly2720b init: yourself:))
			)
			((Btst 121)
				(obj add: (poly2720c init: yourself:))
			)
			(else
				(obj add: (poly2720a init: yourself:))
			)
		)
	)
)

(instance poly2720a of Polygon

	(method (init)
		(= type PContainedAccess)
		(= size 15)
		(= points @pts)
	)
)

(instance poly2720b of Polygon

	(method (init)
		(= type PContainedAccess)
		(= size 19)
		(= points @pts2)
	)
)

(instance poly2720c of Polygon

	(method (init)
		(= type PContainedAccess)
		(= size 18)
		(= points @pts3)
	)
)
