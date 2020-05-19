;;; Sierra Script 1.0 - (do not remove this comment)
(script# 34)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)

(public
	rm34 0
)

(local
	i
	[ripple 2]
	[rippleView 2] = [234 226]
	[rippleX 2] = [230 281]
	[rippleY 2] = [133 150]
	[rippleLoop 2] = [0 3]
)
(instance rm34 of Room
	(properties
		picture 34
		horizon 47
		north 47
		east 33
		south 31
		west 35
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
		(LoadMany VIEW 234 226)
		(super init:)
		(switch prevRoomNum
			(north
				(if (< (ego x?) 230)
					(ego posn: (+ (ego x?) 100) (+ horizon 2))
				else
					(ego posn: (proc0_17 319 (ego x?) 297) (+ horizon 2))
				)
			)
			(south
				(ego y: 188)
			)
			(west
				(ego posn: 3 (proc0_17 188 (ego y?) 68))
			)
			(east
				(if (< (ego y?) 140)
					(ego posn: 317 (proc0_17 66 (ego y?) 49))
				else
					(ego posn: 317 187)
				)
			)
			(else
				(ego posn: 3 137)
			)
		)
		(ego init:)
		(NormalEgo)
		(self setRegions: RIVER)
		(= i 0)
		(while (< i 2)
			((= [ripple i] (Clone Ripple))
				view: [rippleView i]
				cycleSpeed: 1
				x: [rippleX i]
				y: [rippleY i]
				setLoop: [rippleLoop i]
				setPri: 2
				ignoreActors: TRUE
				description: {ripple}
				init:
				stopUpd:
			)
			(if (>= howFast 1) ([ripple i] setCycle: Forward))
			(++ i)
		)
		(rock1 init:)
		(rock2 init:)
		(rock3 init:)
		(rock4 init:)
		(rock5 init:)
		(rock6 init:)
		(rock7 init:)
		(rock8 init:)
		(river1 init:)
		(river2 init:)
		(river3 init:)
		(river4 init:)
		(cliff1 init:)
		(cliff2 init:)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(bush1 init:)
		(bush2 init:)
		(bush3 init:)
		(bush4 init:)
		(bush5 init:)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script
				(script doit:)
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
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,grass]')
						(Print 34 0)
					)
					((or (Said '<across') (Said '<across/brook,water,brook'))
						(Print 34 1)
					)
					((Said '/clearing')
						(Print 34 2)
					)
				)
			)
		)
	)
)

(instance rock1 of NewFeature
	(properties
		x 38
		y 85
		noun '/boulder'
		nsTop 80
		nsLeft 16
		nsBottom 90
		nsRight 60
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the countless rocks and boulders that dot the countryside.}
	)
)

(instance rock2 of NewFeature
	(properties
		x 40
		y 96
		noun '/boulder'
		nsTop 90
		nsLeft 5
		nsBottom 102
		nsRight 75
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the countless rocks and boulders that dot the countryside.}
	)
)

(instance rock3 of NewFeature
	(properties
		x 53
		y 105
		noun '/boulder'
		nsTop 102
		nsLeft 13
		nsBottom 109
		nsRight 94
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the countless rocks and boulders that dot the countryside.}
	)
)

(instance rock4 of NewFeature
	(properties
		x 67
		y 114
		noun '/boulder'
		nsTop 109
		nsLeft 31
		nsBottom 119
		nsRight 103
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the countless rocks and boulders that dot the countryside.}
	)
)

(instance rock5 of NewFeature
	(properties
		x 86
		y 123
		noun '/boulder'
		nsTop 119
		nsLeft 63
		nsBottom 127
		nsRight 110
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the countless rocks and boulders that dot the countryside.}
	)
)

(instance rock6 of NewFeature
	(properties
		x 106
		y 132
		noun '/boulder'
		nsTop 127
		nsLeft 85
		nsBottom 138
		nsRight 128
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the countless rocks and boulders that dot the countryside.}
	)
)

(instance rock7 of NewFeature
	(properties
		x 177
		y 99
		noun '/boulder'
		nsTop 83
		nsLeft 154
		nsBottom 116
		nsRight 201
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the countless rocks and boulders that dot the countryside.}
	)
)

(instance rock8 of NewFeature
	(properties
		x 187
		y 127
		noun '/boulder'
		nsTop 116
		nsLeft 165
		nsBottom 139
		nsRight 209
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the countless rocks and boulders that dot the countryside.}
	)
)

(instance Ripple of Prop
	(properties)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/boulder')
				(self doVerb: verbLook)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 34 3)
			)
		)
	)
)

(instance river1 of NewFeature
	(properties
		x 232
		y 63
		noun '/brook,brook,water'
		nsTop 49
		nsLeft 199
		nsBottom 78
		nsRight 266
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The river is dangerous and powerful.}
	)
)

(instance river2 of NewFeature
	(properties
		x 246
		y 102
		noun '/brook,brook,water'
		nsTop 78
		nsLeft 202
		nsBottom 126
		nsRight 290
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The river is dangerous and powerful.}
	)
)

(instance river3 of NewFeature
	(properties
		x 268
		y 138
		noun '/brook,brook,water'
		nsTop 126
		nsLeft 217
		nsBottom 150
		nsRight 319
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The river is dangerous and powerful.}
	)
)

(instance river4 of NewFeature
	(properties
		x 287
		y 162
		noun '/brook,brook,water'
		nsTop 150
		nsLeft 254
		nsBottom 175
		nsRight 320
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The river is dangerous and powerful.}
	)
)

(instance cliff1 of NewFeature
	(properties
		x 242
		y 39
		noun '/cliff,brook<bank'
		nsTop 23
		nsLeft 213
		nsBottom 56
		nsRight 272
		description {cliff}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The banks of the river are tall and steep.__If you fell down, you'd never be able to climb back up again.}
	)
)

(instance cliff2 of NewFeature
	(properties
		x 292
		y 97
		noun '/cliff,brook<bank'
		nsTop 57
		nsLeft 264
		nsBottom 138
		nsRight 320
		description {cliff}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The banks of the river are tall and steep.__If you fell down, you'd never be able to climb back up again.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 15
		y 26
		noun '/ceder'
		nsBottom 52
		nsRight 30
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A few oak trees serenely overlook the turbulent river below.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 89
		y 19
		noun '/ceder'
		nsTop -1
		nsLeft 68
		nsBottom 39
		nsRight 110
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A few oak trees serenely overlook the turbulent river below.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 303
		y 17
		noun '/ceder'
		nsTop -1
		nsLeft 286
		nsBottom 36
		nsRight 320
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A few oak trees serenely overlook the turbulent river below.}
	)
)

(instance bush1 of NewFeature
	(properties
		x 31
		y 59
		noun '/bush'
		nsTop 51
		nsBottom 67
		nsRight 63
		description {bush1}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Low bushes are growing in abundance in the clean Daventry air.}
	)
)

(instance bush2 of NewFeature
	(properties
		x 94
		y 46
		noun '/bush'
		nsTop 40
		nsLeft 66
		nsBottom 53
		nsRight 123
		description {bush2}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Low bushes are growing in abundance in the clean Daventry air.}
	)
)

(instance bush3 of NewFeature
	(properties
		x 47
		y 33
		noun '/bush'
		nsTop 25
		nsLeft 27
		nsBottom 42
		nsRight 68
		description {bush3}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Low bushes are growing in abundance in the clean Daventry air.}
	)
)

(instance bush4 of NewFeature
	(properties
		x 178
		y 73
		noun '/bush'
		nsTop 66
		nsLeft 168
		nsBottom 81
		nsRight 188
		description {bush4}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Low bushes are growing in abundance in the clean Daventry air.}
	)
)

(instance bush5 of NewFeature
	(properties
		x 309
		y 41
		noun '/bush'
		nsTop 35
		nsLeft 298
		nsBottom 47
		nsRight 320
		description {bush5}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Low bushes are growing in abundance in the clean Daventry air.}
	)
)
