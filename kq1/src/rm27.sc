;;; Sierra Script 1.0 - (do not remove this comment)
(script# 27)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use RFeature)
(use Extra)
(use Motion)
(use Game)
(use Actor)

(public
	rm27 0
)

(instance rm27 of Room
	(properties
		picture 27
		horizon 84
		north 38
		east 28
		south 22
		west 26
	)
	
	(method (init)
		(Load VIEW 300)
		(self
			style:
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
				(ego posn: (proc0_18 106 (ego x?) 70) (+ horizon 2))
			)
			(south
				(ego y: 188)
			)
			(west
				(ego
					posn: 3 (proc0_17
						188
						(proc0_18 112 (ego y?) 102)
						(+ 2 horizon)
					)
				)
			)
			(east
				(ego posn: 317 (proc0_17 188 (ego y?) (+ 2 horizon)))
			)
			(else
				(ego posn: 3 137)
			)
		)
		(ego init:)
		(NormalEgo)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(tree4 init:)
		(tree5 init:)
		(bush1 init:)
		(bush2 init:)
		(babeBirds setCycle: Forward setPri: 7 init:)
		(bird setPri: 7 init:)
		(addToPics
			add: (nest priority: 7)
			eachElementDo: #init
			doit:
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
			((Said 'look,check>')
				(if (Said '[<at,around][/room,clearing,ceder]')
					(Print 27 0)
				)
			)
			((Said 'get,take/bird')
				(Print 27 1)
			)
			((Said 'feed/bird')
				(Print 27 2)
			)
		)
	)
)

(instance tree1 of NewFeature
	(properties
		x 117
		y 16
		noun '/ceder'
		nsTop -1
		nsBottom 34
		nsRight 235
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the oldest, grandest trees in all of Daventry, though it holds no secrets for you, Sir Graham.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 70
		y 52
		noun '/ceder'
		nsTop 34
		nsBottom 71
		nsRight 141
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the oldest, grandest trees in all of Daventry.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 189
		y 65
		noun '/ceder'
		nsTop 52
		nsLeft 141
		nsBottom 78
		nsRight 237
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the oldest, grandest trees in all of Daventry.}
	)
)

(instance tree4 of NewFeature
	(properties
		x 91
		y 120
		noun '/ceder'
		nsTop 72
		nsLeft 70
		nsBottom 169
		nsRight 112
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the oldest, grandest trees in all of Daventry.}
	)
)

(instance bird of Extra
	(properties
		x 147
		y 49
		view 300
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((or (Said 'look,check/bird') (MousedOn self event shiftDown))
				(Print 27 3)
				(event claimed: TRUE)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance babeBirds of Prop
	(properties
		x 156
		y 52
		description {baby bird}
		view 300
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((or (Said 'look,check/(bird<baby),chick') (MousedOn self event shiftDown))
				(Print 27 4)
				(event claimed: TRUE)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance nest of RPicView
	(properties
		x 156
		y 56
		description {bird's nest}
		view 300
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((or (Said 'look,check/nest') (MousedOn self event shiftDown))
				(Print 27 5)
				(event claimed: TRUE)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance bush1 of NewFeature
	(properties
		x 33
		y 99
		noun '/bush'
		nsTop 87
		nsBottom 112
		nsRight 66
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A couple of bushes lie among the undergrowth of the forest floor.}
	)
)

(instance bush2 of NewFeature
	(properties
		x 123
		y 173
		noun '/bush'
		nsTop 163
		nsLeft 87
		nsBottom 183
		nsRight 159
		description {bush by tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A couple of bushes lie among the undergrowth of the forest floor.}
	)
)

(instance tree5 of NewFeature
	(properties
		x 301
		y 35
		noun '/ceder'
		nsTop 3
		nsLeft 282
		nsBottom 67
		nsRight 320
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You can hardly see the forest for all the trees here!}
	)
)
