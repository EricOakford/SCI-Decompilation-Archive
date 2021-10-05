;;; Sierra Script 1.0 - (do not remove this comment)
(script# 31)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm31 0
)

(instance rm31 of Room
	(properties
		picture 31
		north 34
		east 32
		south 18
		west 30
	)
	
	(method (init)
		(if (not (Btst fGotBowl))
			(LoadMany VIEW 231 1)
		)
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
				(ego posn: (proc0_17 170 (ego x?) 141) 61)
			)
			(south (ego y: 188))
			(west
				(ego
					posn: 3 (proc0_17 188 (proc0_18 131 (ego y?) 116) 72)
				)
			)
			(east
				(ego x: 317 (proc0_17 188 (ego y?) 76))
			)
			(else  (ego posn: 3 137))
		)
		(ego init:)
		(NormalEgo)
		(if (not (Btst fGotBowl))
			(bowl init:)
		)
		(pinetree1 init:)
		(pinetree2 init:)
		(pinetree3 init:)
		(pinetree4 init:)
		(pinetree6 init:)
		(pinetree7 init:)
		(pinetree8 init:)
		(pinetree9 init:)
		(pinetree10 init:)
		(pinetree11 init:)
		(pinetree12 init:)
		(pinetree13 init:)
		(pinetree14 init:)
		(pinetree15 init:)
		(pinetree16 init:)
		(pinetree17 init:)
		(pinetree18 init:)
		(pinetree19 init:)
		(pinetree20 init:)
		(pinetree21 init:)
		(pinetree22 init:)
		(pinetree23 init:)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(tree4 init:)
		(tree5 init:)
		(tree6 init:)
		(tree7 init:)
		(tree8 init:)
		(tree9 init:)
		(tree10 init:)
		(tree11 init:)
		(tree12 init:)
		(tree13 init:)
		(plant1 init:)
		(plant2 init:)
		(plant3 init:)
		(plant4 init:)
		(plant5 init:)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script (script doit:))
			(
				(and
					(& (ego onControl: origin) cLBLUE)
					(!= (ego script?) climbHill)
					(not (Btst fClimbing))
				)
				(ego setScript: climbHill)
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
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'climb,scale,get,take/ceder[<pine]')
				(Print 31 0)
			)
			((Said 'get,take,take,pick/blossom')
				(Print 31 1)
			)
			((or (Said 'look,check/bowl[<ceramic]') (MousedOn bowl event shiftDown))
				(if (ego has: iCeramicBowl)
					(event claimed: FALSE)
				else
					(Print 31 2)
				)
			)
			((or (Said 'look,check<down[/grass]') (Said 'look,check[<down]/grass'))
				(if (not (Btst fGotBowl))
					(if (< (ego distanceTo: bowl) 20)
						(Print 31 3)
					else
						(Print 31 4)
					)
				else
					(Print 31 5)
				)
				(if (== stalkRoom curRoomNum)
					(Print 31 6)
				)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/grass,room,ceder,clearing]')
						(cond 
							((== stalkRoom curRoomNum)
								(Print 31 7)
							)
							((not (ego has: iCeramicBowl))
								(Print 31 8)
							)
							(else
								(Print 31 9)
							)
						)
					)
					((Said '/grass')
						(Print 31 5)
					)
				)
			)
			((Said 'get,take/bowl')
				(cond 
					((Btst fGotBowl)
						(Print 31 10)
					)
					((> (ego distanceTo: bowl) 20)
						(CantReach)
					)
					((Btst fInvisible)
						(Print 31 11)
					)
					(script
						(script doit:)
						(Print 31 12)
					)
					(else
						(curRoom setScript: getBowl)
					)
				)
			)
		)
	)
)

(instance bowl of View
	(properties
		x 92
		y 135
		view 231
		signal staticView
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 31 3)
			)
		)
	)
)

(instance getBowl of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego bowl)
				(= cycles 2)
			)
			(1
				(ego view: 1 setMotion: 0 setCycle: EndLoop self)
			)
			(2
				((ScriptID 0 21) number: 105 loop: 1 init: play:)
				(SolvePuzzle fGotBowl 3)
				(ego get: iCeramicBowl)
				(bowl dispose:)
				(= cycles 4)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance climbHill of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					setLoop: 3
					setPri: 1
					setMotion: MoveTo (ego x?) (+ (ego y?) 15) self
				)
			)
			(1
				(NormalEgo)
				(HandsOn)
				(curRoom newRoom: (curRoom north?))
				(self dispose:)
			)
		)
	)
)

(instance pinetree1 of NewFeature
	(properties
		x 54
		y 109
		noun '/ceder[<pine]'
		nsTop 93
		nsLeft 18
		nsBottom 126
		nsRight 91
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree2 of NewFeature
	(properties
		x 49
		y 81
		noun '/ceder[<pine]'
		nsTop 69
		nsLeft 30
		nsBottom 93
		nsRight 69
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree3 of NewFeature
	(properties
		x 46
		y 58
		noun '/ceder[<pine]'
		nsTop 48
		nsLeft 36
		nsBottom 69
		nsRight 56
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree4 of NewFeature
	(properties
		x 228
		y 88
		noun '/ceder[<pine]'
		nsTop 68
		nsLeft 200
		nsBottom 109
		nsRight 257
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree6 of NewFeature
	(properties
		x 222
		y 57
		noun '/ceder[<pine]'
		nsTop 47
		nsLeft 206
		nsBottom 68
		nsRight 239
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree7 of NewFeature
	(properties
		x 218
		y 41
		noun '/ceder[<pine]'
		nsTop 35
		nsLeft 213
		nsBottom 47
		nsRight 224
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance plant1 of NewFeature
	(properties
		x 242
		y 157
		noun '/bury'
		nsTop 151
		nsLeft 211
		nsBottom 163
		nsRight 273
		description {plant}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You see a dazzling spray of tiny red flowers.}
	)
)

(instance plant2 of NewFeature
	(properties
		x 218
		y 140
		noun '/bury'
		nsTop 135
		nsLeft 213
		nsBottom 145
		nsRight 224
		description {plant}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You see a dazzling spray of tiny red flowers.}
	)
)

(instance plant3 of NewFeature
	(properties
		x 243
		y 135
		noun '/bury'
		nsTop 130
		nsLeft 238
		nsBottom 140
		nsRight 248
		description {plant}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You see a dazzling spray of tiny red flowers.}
	)
)

(instance plant4 of NewFeature
	(properties
		x 250
		y 141
		noun '/bury'
		nsTop 137
		nsLeft 245
		nsBottom 146
		nsRight 255
		description {plant}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You see a dazzling spray of tiny red flowers.}
	)
)

(instance plant5 of NewFeature
	(properties
		x 271
		y 143
		noun '/bury'
		nsTop 138
		nsLeft 266
		nsBottom 148
		nsRight 276
		description {plant}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You see a dazzling spray of tiny red flowers.}
	)
)

(instance pinetree8 of NewFeature
	(properties
		x 40
		y 35
		noun '/ceder[<pine]'
		nsTop -1
		nsLeft 23
		nsBottom 71
		nsRight 58
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree9 of NewFeature
	(properties
		x 65
		y 28
		noun '/ceder[<pine]'
		nsTop 24
		nsLeft 58
		nsBottom 32
		nsRight 72
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree10 of NewFeature
	(properties
		x 67
		y 43
		noun '/ceder[<pine]'
		nsTop 40
		nsLeft 64
		nsBottom 46
		nsRight 71
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree11 of NewFeature
	(properties
		x 60
		y 41
		noun '/ceder[<pine]'
		nsTop 35
		nsLeft 58
		nsBottom 47
		nsRight 63
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree12 of NewFeature
	(properties
		x 67
		y 59
		noun '/ceder[<pine]'
		nsTop 47
		nsLeft 58
		nsBottom 71
		nsRight 76
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree13 of NewFeature
	(properties
		x 81
		y 68
		noun '/ceder[<pine]'
		nsTop 62
		nsLeft 76
		nsBottom 74
		nsRight 86
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree14 of NewFeature
	(properties
		x 90
		y 71
		noun '/ceder[<pine]'
		nsTop 68
		nsLeft 86
		nsBottom 74
		nsRight 95
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree15 of NewFeature
	(properties
		x 19
		y 15
		noun '/ceder[<pine]'
		nsTop 13
		nsLeft 17
		nsBottom 18
		nsRight 22
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree16 of NewFeature
	(properties
		x 19
		y 6
		noun '/ceder[<pine]'
		nsTop 4
		nsLeft 17
		nsBottom 9
		nsRight 22
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree17 of NewFeature
	(properties
		x 18
		y 32
		noun '/ceder[<pine]'
		nsTop 28
		nsLeft 14
		nsBottom 36
		nsRight 22
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree18 of NewFeature
	(properties
		x 17
		y 45
		noun '/ceder[<pine]'
		nsTop 43
		nsLeft 12
		nsBottom 48
		nsRight 22
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree19 of NewFeature
	(properties
		x 13
		y 62
		noun '/ceder[<pine]'
		nsTop 54
		nsLeft 5
		nsBottom 70
		nsRight 22
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree20 of NewFeature
	(properties
		x 17
		y 51
		noun '/ceder[<pine]'
		nsTop 50
		nsLeft 12
		nsBottom 53
		nsRight 23
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 78
		y 11
		noun '/ceder'
		nsTop -1
		nsLeft 57
		nsBottom 23
		nsRight 99
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are an endless variety of trees in Daventry; none of the ones you can see here seem the least bit unusual.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 85
		y 28
		noun '/ceder'
		nsTop 24
		nsLeft 71
		nsBottom 33
		nsRight 100
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are an endless variety of trees in Daventry; none of the ones you can see here seem the least bit unusual.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 76
		y 36
		noun '/ceder'
		nsTop 32
		nsLeft 63
		nsBottom 41
		nsRight 89
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are an endless variety of trees in Daventry; none of the ones you can see here seem the least bit unusual.}
	)
)

(instance tree4 of NewFeature
	(properties
		x 77
		y 44
		noun '/ceder'
		nsTop 41
		nsLeft 71
		nsBottom 47
		nsRight 84
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are an endless variety of trees in Daventry; none of the ones you can see here seem the least bit unusual.}
	)
)

(instance tree5 of NewFeature
	(properties
		x 79
		y 53
		noun '/ceder'
		nsTop 47
		nsLeft 76
		nsBottom 60
		nsRight 83
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are an endless variety of trees in Daventry; none of the ones you can see here seem the least bit unusual.}
	)
)

(instance tree6 of NewFeature
	(properties
		x 283
		y 9
		noun '/ceder'
		nsTop -1
		nsLeft 246
		nsBottom 19
		nsRight 320
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are an endless variety of trees in Daventry; none of the ones you can see here seem the least bit unusual.}
	)
)

(instance tree7 of NewFeature
	(properties
		x 281
		y 29
		noun '/ceder'
		nsTop 20
		nsLeft 248
		nsBottom 38
		nsRight 315
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are an endless variety of trees in Daventry; none of the ones you can see here seem the least bit unusual.}
	)
)

(instance tree8 of NewFeature
	(properties
		x 298
		y 43
		noun '/ceder'
		nsTop 39
		nsLeft 282
		nsBottom 47
		nsRight 314
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are an endless variety of trees in Daventry; none of the ones you can see here seem the least bit unusual.}
	)
)

(instance tree9 of NewFeature
	(properties
		x 266
		y 47
		noun '/ceder'
		nsTop 39
		nsLeft 261
		nsBottom 55
		nsRight 271
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are an endless variety of trees in Daventry; none of the ones you can see here seem the least bit unusual.}
	)
)

(instance tree10 of NewFeature
	(properties
		x 5
		y 25
		noun '/ceder'
		nsTop -2
		nsBottom 53
		nsRight 11
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are an endless variety of trees in Daventry; none of the ones you can see here seem the least bit unusual.}
	)
)

(instance tree11 of NewFeature
	(properties
		x 14
		y 12
		noun '/ceder'
		nsTop -1
		nsLeft 11
		nsBottom 26
		nsRight 18
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are an endless variety of trees in Daventry; none of the ones you can see here seem the least bit unusual.}
	)
)

(instance tree12 of NewFeature
	(properties
		x 230
		y 15
		noun '/ceder'
		nsLeft 213
		nsBottom 31
		nsRight 247
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are an endless variety of trees in Daventry; none of the ones you can see here seem the least bit unusual.}
	)
)

(instance tree13 of NewFeature
	(properties
		x 241
		y 43
		noun '/ceder'
		nsTop 32
		nsLeft 234
		nsBottom 54
		nsRight 248
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are an endless variety of trees in Daventry; none of the ones you can see here seem the least bit unusual.}
	)
)

(instance pinetree21 of NewFeature
	(properties
		x 105
		y 48
		noun '/ceder/[<pine]'
		nsTop 41
		nsLeft 84
		nsBottom 55
		nsRight 126
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree22 of NewFeature
	(properties
		x 104
		y 37
		noun '/ceder/[<pine]'
		nsTop 35
		nsLeft 91
		nsBottom 40
		nsRight 117
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)

(instance pinetree23 of NewFeature
	(properties
		x 121
		y 61
		noun '/ceder/[<pine]'
		nsTop 57
		nsLeft 113
		nsBottom 65
		nsRight 129
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Flourishing pine trees abound in this part of the forest.}
	)
)
