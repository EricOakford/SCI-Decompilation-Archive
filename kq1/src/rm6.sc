;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Game)

(public
	rm6 0
)

(instance rm6 of Room
	(properties
		picture 6
		horizon 68
		north 11
		east 5
		south 43
		west 7
	)
	
	(method (init)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(super init:)
		(switch prevRoomNum
			(north
				(ego
					x:
						(if (> (ego x?) 181)
							(proc0_17 300 (ego x?) 247)
						else
							(proc0_17 122 (ego x?) 10)
						)
					y: (+ horizon 2)
				)
			)
			(south
				(if (> (ego x?) 189)
					(ego
						posn: (proc0_17 318 (+ 264 (- (ego x?) 190)) 264) 188
					)
				else
					(ego
						posn: (proc0_17 263 (/ (* (ego x?) 25) 18) 5) 188
					)
				)
			)
			(west
				(ego
					posn: 5 (proc0_17 147 (proc0_18 102 (ego y?) 91) 70)
				)
			)
			(east
				(ego
					posn: 317 (proc0_17 187 (proc0_18 109 (ego y?) 100) 70)
				)
			)
			(else  (ego posn: 315 142))
		)
		(ego init:)
		(if (not egoInWater) (NormalEgo))
		(large_tree init:)
		(large_tree1 init:)
		(large_tree2 init:)
		(large_tree3 init:)
		(large_tree4 init:)
		(large_tree5 init:)
		(tree init:)
		(tree1 init:)
		(tree2 init:)
		(farBush1 init:)
		(farBush init:)
		(lake init:)
		(rock init:)
		(self setRegions: WATER)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'find/bag')
				(cond 
					((ego has: iPouch)
						(Print 6 0)
					)
					((Btst fSawPouchInStump)
						(Print 6 1)
					)
					((not (Btst fGotPouch))
						(Print 6 2)
					)
				)
			)
			((Said 'look,check<in/stump')
				(if (ego inRect: 30 143 100 175)
					(if (not (Btst fGotPouch))
						(Print 6 3)
						(SolvePuzzle fSawPouchInStump 1)
					else
						(Print 6 4)
					)
				else
					(Print 6 5)
				)
			)
			((or (Said 'look,check<in/branch,hole') (Said 'look,check/hole'))
				(if (ego inRect: 154 110 172 125)
					(Print 6 6)
				else
					(Print 6 7)
				)
			)
			(
				(or
					(Said 'look,check/stump')
					(MouseClaimed event 38 124 89 139)
					(MouseClaimed event 7 139 92 160)
				)
				(if (ego inRect: 20 129 125 189)
					(Print 6 8)
				else
					(Print 6 9)
				)
			)
			(
				(or
					(Said 'look,check/branch')
					(MouseClaimed event 182 94 193 101)
					(MouseClaimed event 89 89 107 101)
					(MouseClaimed event 76 102 198 119)
				)
				(if (ego inRect: 70 105 215 125)
					(Print 6 10)
				else
					(Print 6 11)
				)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,ceder]')
						(Print 6 12)
					)
					((Said '/ant')
						(Print 6 13)
					)
				)
			)
			((Said 'eat,consume/ant')
				(Print 6 14)
			)
			((Said 'get,take,take/ant')
				(Print 6 15)
			)
			((Said 'get,take/branch')
				(Print 6 16)
			)
			((Said 'get,take/(bag[<leather]),diamond')
				(cond 
					((and (ego has: iPouch) (Btst fOpenedPouch))
						(Print 6 17)
					)
					((ego has: iPouch)
						(Print 6 18)
					)
					((Btst fGotPouch)
						(Print 6 19)
					)
					((not (Btst fSawPouchInStump))
						(Print 6 20)
					)
					((ego inRect: 20 129 125 189)
						((ScriptID 0 21) number: 105 loop: 1 init: play:)
						(Print 6 21)
						(ego get: iPouch)
						(SolvePuzzle fGotPouch 3)
					)
					(else
						(Print 6 22)
					)
				)
			)
		)
	)
)

(instance tree of NewFeature
	(properties
		x 24
		y 66
		noun '/ceder'
		nsTop 36
		nsLeft 12
		nsBottom 96
		nsRight 37
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {A totally typical example of the trees in this forest.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 64
		y 60
		noun '/ceder'
		nsTop 36
		nsLeft 49
		nsBottom 84
		nsRight 80
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {A totally typical example of the trees in this forest.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 72
		y 18
		noun '/ceder'
		nsTop -1
		nsBottom 37
		nsRight 144
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {A totally typical example of the trees in this forest.}
	)
)

(instance farBush of NewFeature
	(properties
		x 157
		y 55
		noun '/bush'
		nsTop 44
		nsLeft 130
		nsBottom 66
		nsRight 185
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {You've found a bush, but there isn't a quail in the game.}
	)
)

(instance farBush1 of NewFeature
	(properties
		x 202
		y 56
		noun '/bush'
		nsTop 49
		nsLeft 185
		nsBottom 64
		nsRight 220
		description {farBush1}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {You've found a bush, but there isn't a quail in the game.}
	)
)

(instance large_tree of NewFeature
	(properties
		x 257
		y 22
		nsLeft 195
		nsBottom 45
		nsRight 319
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {A distinctively common tree sits near the shore of the lake.}
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'look,check/ceder')
				(if
					(<
						(GetDistance (ego x?) (ego y?) 51 89)
						(GetDistance (ego x?) (ego y?) 248 165)
					)
					(Print (tree lookStr?))
				else
					(Print lookStr)
				)
			)
		)
	)
)

(instance large_tree1 of NewFeature
	(properties
		x 268
		y 94
		noun '/ceder'
		nsTop 46
		nsLeft 252
		nsBottom 143
		nsRight 285
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {A distinctively common tree sits near the shore of the lake.}
	)
)

(instance large_tree2 of NewFeature
	(properties
		x 238
		y 51
		noun '/ceder'
		nsTop 45
		nsLeft 225
		nsBottom 58
		nsRight 251
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {A distinctively common tree sits near the shore of the lake.}
	)
)

(instance large_tree3 of NewFeature
	(properties
		x 247
		y 158
		noun '/ceder'
		nsTop 142
		nsLeft 215
		nsBottom 175
		nsRight 280
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {A distinctively common tree sits near the shore of the lake.}
	)
)

(instance large_tree4 of NewFeature
	(properties
		x 184
		y 18
		noun '/ceder'
		nsLeft 174
		nsBottom 36
		nsRight 194
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {A distinctively common tree sits near the shore of the lake.}
	)
)

(instance large_tree5 of NewFeature
	(properties
		x 303
		y 57
		noun '/ceder'
		nsTop 46
		nsLeft 286
		nsBottom 68
		nsRight 320
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {A distinctively common tree sits near the shore of the lake.}
	)
)

(instance lake of NewFeature
	(properties
		x 139
		y 173
		noun '/water,lake,lake'
		nsTop 158
		nsLeft 16
		nsBottom 189
		nsRight 263
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {You can just see the northern edge of a nearby lake.}
	)
)

(instance rock of NewFeature
	(properties
		x 311
		y 88
		noun '/boulder'
		nsTop 72
		nsLeft 303
		nsBottom 104
		nsRight 320
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {There's a single large rock sitting off to the side.}
	)
)
