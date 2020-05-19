;;; Sierra Script 1.0 - (do not remove this comment)
(script# 62)
(include game.sh)
(use Main)
(use Intrface)
(use rgClouds)
(use NewFeature)
(use Motion)
(use System)

(public
	rm62 0
)

(procedure (FindSlingshot item owner)
	(return
		(==
			((inventory at: item) owner?)
			(if (== argc 1) curRoomNum else owner)
		)
	)
)

(instance rm62 of cloudRoom
	(properties
		picture 62
		horizon 100
		north 59
		west 61
	)
	
	(method (init)
		(Load VIEW 67)
		(super init:)
		(StartCloudRoom
			(proc0_18 96 (proc0_17 222 (ego x?) 3) 60)
			(+ horizon 4)
			0
			0
			0
			0
			3
			(proc0_17 148 (ego y?) (+ horizon 2))
			299
		)
		(ego init:)
		(NormalEgo)
		(hole init:)
		(cave init:)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(tree4 init:)
		(tree5 init:)
		(cloud1 init:)
		(cloud2 init:)
		(cloud3 init:)
		(cloud4 init:)
		(cloud5 init:)
		(cloud6 init:)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((super handleEvent: event)
					(return TRUE)
				)
				((Said 'look,check/ceder')
					(Print 62 0)
				)
				((Said 'look,check/hole')
					(cond 
						((not (ego inRect: 214 129 268 138))
							(Print 62 1)
						)
						((FindSlingshot iSlingshot)
							(Print 62 2)
						)
						(else
							(Print 62 3)
						)
					)
				)
				((Said 'get,take/shot')
					(cond 
						((not (FindSlingshot iSlingshot))
							(Print 62 4)
						)
						((not (ego inRect: 214 129 268 138))
							(CantReach)
						)
						(else
							(curRoom setScript: getSlingshot)
						)
					)
				)
				((Said 'look,check/shot')
					(if (and (FindSlingshot iSlingshot) (ego inRect: 214 129 268 138))
						(Print 62 5)
					else
						(event claimed: FALSE)
					)
				)
			)
		)
	)
)

(instance getSlingshot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego 248 117)
				(self cue:)
			)
			(1
				(ego
					view: 67
					loop: (+
						(if (Btst fInvisible) 2 else 0)
						(if (< (ego x?) 248) 0 else 1)
					)
					cel: 0
					setMotion: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(= cycles 7)
			)
			(2
				((ScriptID 0 21) number: 105 loop: 1 init: play:)
				(Print 62 6)
				(SolvePuzzle fGotSlingshot 2)
				(ego get: iSlingshot setCycle: BegLoop self)
			)
			(3
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance tree1 of NewFeature
	(properties
		x 63
		y 132
		nsTop 103
		nsLeft 48
		nsBottom 162
		nsRight 78
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The immense, oddly-shaped trees loom over you.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 72
		y 80
		nsTop 56
		nsLeft 57
		nsBottom 104
		nsRight 88
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The immense, oddly-shaped trees loom over you.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 88
		y 29
		nsBottom 59
		nsRight 176
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The immense, oddly-shaped trees loom over you.}
	)
)

(instance tree4 of NewFeature
	(properties
		x 231
		y 21
		nsTop -1
		nsLeft 208
		nsBottom 44
		nsRight 254
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The immense, oddly-shaped trees loom over you.}
	)
)

(instance tree5 of NewFeature
	(properties
		x 250
		y 81
		nsTop 45
		nsLeft 223
		nsBottom 118
		nsRight 272
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The immense, oddly-shaped trees loom over you.}
	)
)

(instance hole of NewFeature
	(properties
		x 256
		y 114
		nsTop 104
		nsLeft 249
		nsBottom 124
		nsRight 264
		description {hole}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There is a small, dark hole in the base of this tree.}
	)
)

(instance cave of NewFeature
	(properties
		x 305
		y 22
		noun '/cave,boulder'
		nsTop -1
		nsLeft 291
		nsBottom 45
		nsRight 319
		description {rocks}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You can barely make out the cave opening to the north.}
	)
)

(instance cloud1 of NewFeature
	(properties
		x 22
		y 161
		noun '/cloud'
		nsTop 134
		nsBottom 189
		nsRight 45
		description {cloud}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The dense clouds start to flow over the land here.__Somewhere just out in those clouds the land comes to an end; better be careful where you walk!}
	)
)

(instance cloud2 of NewFeature
	(properties
		x 182
		y 174
		noun '/cloud'
		nsTop 159
		nsLeft 45
		nsBottom 189
		nsRight 320
		description {cloud}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The dense clouds start to flow over the land here.__Somewhere just out in those clouds the land comes to an end; better be careful where you walk!}
	)
)

(instance cloud3 of NewFeature
	(properties
		x 302
		y 103
		noun '/cloud'
		nsTop 48
		nsLeft 285
		nsBottom 159
		nsRight 320
		description {cloud}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The dense clouds start to flow over the land here.__Somewhere just out in those clouds the land comes to an end; better be careful where you walk!}
	)
)

(instance cloud4 of NewFeature
	(properties
		x 261
		y 149
		noun '/cloud'
		nsTop 140
		nsLeft 237
		nsBottom 159
		nsRight 285
		description {cloud}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The dense clouds start to flow over the land here.__Somewhere just out in those clouds the land comes to an end; better be careful where you walk!}
	)
)

(instance cloud5 of NewFeature
	(properties
		x 234
		y 99
		noun '/cloud'
		nsTop 93
		nsLeft 184
		nsBottom 105
		nsRight 285
		description {cloud}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The dense clouds start to flow over the land here.__Somewhere just out in those clouds the land comes to an end; better be careful where you walk!}
	)
)

(instance cloud6 of NewFeature
	(properties
		x 277
		y 84
		noun '/cloud'
		nsTop 76
		nsLeft 271
		nsBottom 92
		nsRight 284
		description {cloud}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The dense clouds start to flow over the land here.__Somewhere just out in those clouds the land comes to an end; better be careful where you walk!}
	)
)
