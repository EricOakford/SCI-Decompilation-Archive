;;; Sierra Script 1.0 - (do not remove this comment)
(script# 44)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Block)
(use RFeature)
(use Game)
(use User)
(use Actor)

(public
	rm44 0
)

(instance rm44 of Room
	(properties
		picture 44
		horizon 52
		north 5
		east 45
		south 37
		west 43
	)
	
	(method (init)
		(Load VIEW 244)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
				(else  IRISOUT)
			)
		)
		(super init:)
		(switch prevRoomNum
			(north
				(ego posn: (proc0_17 225 (ego x?) 41) (+ horizon 2))
			)
			(south
				(ego posn: (proc0_17 318 (ego x?) 73) 188)
			)
			(west
				(ego posn: 3 (proc0_17 188 (ego y?) 78))
			)
			(east
				(if (< (ego y?) 148)
					(ego posn: 273 101)
				else
					(ego posn: 317 (proc0_17 188 (ego y?) 169))
				)
			)
			(else 
				(ego posn: 251 157 loop: 1)
			)
		)
		(ego init: observeBlocks: pumpBlock)
		(NormalEgo)
		(addToPics add: stump pump eachElementDo: #init doit:)
		(smallRock init:)
		(pinetree1 init:)
		(pinetree2 init:)
		(pinetree3 init:)
		(pinetree4 init:)
		(stump1 init:)
		(stump2 init:)
		(stump3 init:)
		(stump5 init:)
		(stump6 init:)
		(window1 init:)
		(porch1 init:)
		(porch2 init:)
		(bush1 init:)
		(bush2 init:)
		(bush3 init:)
		(bush4 init:)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script
				(script doit:)
			)
			((& (ego onControl: origin) cYELLOW)
				(self newRoom: 79)
			)
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(self newRoom: nRoom)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'attack,kick/building')
				(Print 44 0)
			)
			((or (Said 'open,open/door') (Said 'close,shut/door'))
				(if (ego inRect: 233 149 264 160)
					(Print 44 1)
				else
					(CantReach)
				)
			)
			((Said 'look,check/hole')
				(Print 44 2)
			)
			((Said 'look,check/ceiling')
				(Print 44 3)
			)
			((Said 'look,check/chimney')
				(Print 44 4)
			)
			(
				(or
					(Said 'look,check[<in,in,through]/door,doorway')
					(and (ego inRect: 233 149 264 160) (Said 'look,check<in'))
					(MouseClaimed event 249 106 264 159)
					(MouseClaimed event 225 158 269 175)
				)
				(if (ego inRect: 233 149 264 160)
					(if (Btst fGaveStewToWoodCutter)
						(Print 44 5)
					else
						(Print 44 6)
					)
				else
					(Print 44 7)
				)
			)
			(
				(or
					(Said 'look,check/building')
					(MouseClaimed event 229 135 320 170)
					(MouseClaimed event 222 75 236 89)
					(MouseClaimed event 237 58 320 89)
					(MouseClaimed event 211 89 320 135)
				)
				(Print 44 8)
			)
			(
				(or
					(Said 'look,check<in,through,in/window')
					(and
						(< (GetDistance (ego x?) (ego y?) 228 139 60) 60)
						(Said 'look,check<in')
					)
				)
				(cond 
					((Btst fGaveStewToWoodCutter)
						(Print 44 9)
					)
					((< (GetDistance (ego x?) (ego y?) 228 139 60) 60)
						(Print 44 10)
					)
					(else
						(Print 44 11)
					)
				)
			)
			((Said 'look,check<in')
				(Print 44 12)
			)
			((Said 'look,check/window')
				(Print 44 13)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room]')
						(if (Btst fGaveStewToWoodCutter)
							(Print 44 14)
						else
							(Print 44 15)
						)
					)
					((or (Said '/man') (Said '/fairy'))
						(if (ego inRect: 233 149 264 160)
							(Print 44 16)
						else
							(Print 44 17)
						)
					)
				)
			)
		)
	)
)

(instance stump of RPicView
	(properties
		x 152
		y 155
		view 244
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((or (MousedOn self event shiftDown) (Said 'look,check/ax'))
				(Print 44 18)
				(Print 44 19)
				(event claimed: TRUE)
			)
			((Said 'look,check/stump')
				(if (ego inRect: 115 140 187 160)
					(Print 44 20)
				else
					(Print 44 21)
				)
			)
			((Said 'get,take/ax')
				(if (ego inRect: 115 140 187 160)
					(Print 44 22)
				else
					(CantReach)
				)
			)
			((Said 'use/ax')
				(if (ego inRect: 115 140 187 160)
					(Print 44 23)
				else
					(CantReach)
				)
			)
			(else
				(event claimed: FALSE)
			)
		)
	)
)

(instance pump of RPicView
	(properties
		x 50
		y 177
		view 244
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(
				(or
					(Said 'look,check/pump')
					(Said 'look,check/pump<water')
					(MousedOn self event shiftDown)
				)
				(event claimed: TRUE)
				(Print 44 24)
			)
			(
				(or
					(Said 'use/pump')
					(Said 'pump/water')
					(Said 'pump')
					(Said 'pump/pump')
					(Said 'work/pump')
				)
				(if (ego inRect: 30 167 70 187)
					(Print 44 25)
				else
					(CantReach)
				)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance smallRock of View
	(properties
		x 204
		y 176
		description {rock}
		view 244
		loop 2
		signal ignrAct
	)
)

(instance pumpBlock of Block
	(properties
		top 175
		left 45
		bottom 179
		right 55
	)
)

(instance pinetree1 of NewFeature
	(properties
		x 63
		y 110
		noun '/ceder[<pine]'
		nsTop 84
		nsLeft 25
		nsBottom 137
		nsRight 101
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are still a few graceful pines standing near the woodcutter's cottage.}
	)
)

(instance pinetree2 of NewFeature
	(properties
		x 60
		y 72
		noun '/ceder[<pine]'
		nsTop 61
		nsLeft 36
		nsBottom 84
		nsRight 84
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are still a few graceful pines standing near the woodcutter's cottage.}
	)
)

(instance pinetree3 of NewFeature
	(properties
		x 54
		y 53
		noun '/ceder[<pine]'
		nsTop 45
		nsLeft 40
		nsBottom 61
		nsRight 69
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are still a few graceful pines standing near the woodcutter's cottage.}
	)
)

(instance pinetree4 of NewFeature
	(properties
		x 282
		y 28
		noun '/ceder[<pine]'
		nsTop -1
		nsLeft 244
		nsBottom 57
		nsRight 320
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are still a few graceful pines standing near the woodcutter's cottage.}
	)
)

(instance stump1 of NewFeature
	(properties
		x 130
		y 102
		noun '/stump'
		nsTop 99
		nsLeft 125
		nsBottom 106
		nsRight 136
		description {stump}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These tree stumps sit where mighty trees once stood.}
	)
)

(instance stump2 of NewFeature
	(properties
		x 152
		y 116
		noun '/stump'
		nsTop 112
		nsLeft 147
		nsBottom 120
		nsRight 158
		description {stump}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These tree stumps sit where mighty trees once stood.}
	)
)

(instance stump3 of NewFeature
	(properties
		x 197
		y 101
		noun '/stump'
		nsTop 98
		nsLeft 193
		nsBottom 105
		nsRight 201
		description {stump}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These tree stumps sit where mighty trees once stood.}
	)
)

(instance porch1 of NewFeature
	(properties
		x 226
		y 150
		noun '/porch'
		nsTop 141
		nsLeft 207
		nsBottom 159
		nsRight 246
		description {porch}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This crude porch looks like it has seen better days.}
	)
)

(instance porch2 of NewFeature
	(properties
		x 246
		y 167
		noun '/porch'
		nsTop 159
		nsLeft 226
		nsBottom 175
		nsRight 267
		description {porch}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This crude porch looks like it has seen better days.}
	)
)

(instance stump5 of NewFeature
	(properties
		x 133
		y 67
		nsTop 60
		nsLeft 119
		nsBottom 74
		nsRight 148
		description {stump}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees were cut from these stumps years ago.}
	)
)

(instance stump6 of NewFeature
	(properties
		x 178
		y 68
		nsTop 61
		nsLeft 164
		nsBottom 76
		nsRight 192
		description {stump}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees were cut from these stumps years ago.}
	)
)

(instance window1 of NewFeature
	(properties
		x 229
		y 111
		z -27
		heading 235
		nsTop 98
		nsLeft 224
		nsBottom 125
		nsRight 234
		sightAngle 360
		getableDist 0
		seeableDist 60
		shiftClick 369
	)
	
	(method (doLook)
		(cond 
			((> (GetDistance (ego x?) (ego y?) x y) seeableDist)
				(Print 44 26)
			)
			((Btst fGaveStewToWoodCutter)
				(Print 44 9)
			)
			(else
				(Print 44 10)
			)
		)
	)
)

(instance bush1 of NewFeature
	(properties
		x 17
		y 173
		noun '/bush'
		nsTop 157
		nsBottom 189
		nsRight 35
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Bushes grow here and there around the front of the woodcutter's house.}
	)
)

(instance bush2 of NewFeature
	(properties
		x 49
		y 179
		noun '/bush'
		nsTop 169
		nsLeft 35
		nsBottom 189
		nsRight 64
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Bushes grow here and there around the front of the woodcutter's house.}
	)
)

(instance bush3 of NewFeature
	(properties
		x 8
		y 58
		noun '/bush'
		nsTop 42
		nsBottom 74
		nsRight 16
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Bushes grow here and there around the front of the woodcutter's house.}
	)
)

(instance bush4 of NewFeature
	(properties
		x 27
		y 63
		noun '/bush'
		nsTop 52
		nsLeft 16
		nsBottom 74
		nsRight 39
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Bushes grow here and there around the front of the woodcutter's house.}
	)
)
