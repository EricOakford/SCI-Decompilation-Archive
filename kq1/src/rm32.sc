;;; Sierra Script 1.0 - (do not remove this comment)
(script# 32)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Motion)
(use Game)
(use User)
(use Actor)

(public
	rm32 0
)

(local
	i
	ripple
	rippleView =  241
	rippleX =  293
	rippleY =  107
	rippleLoop =  3
)
(instance rm32 of Room
	(properties
		picture 32
		horizon 51
		north 33
		east 25
		south 17
		west 31
	)
	
	(method (init)
		(Load VIEW 241)
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
				(ego posn: (proc0_17 174 (ego x?) 78) (+ horizon 2))
			)
			(south
				(ego
					posn: (proc0_17 319 (proc0_18 296 (ego x?) 180) 0) 188
				)
			)
			(west
				(ego posn: 3 (proc0_17 188 (ego y?) 83))
			)
			(else 
				(if (< (ego y?) 105)
					(ego posn: 317 (proc0_17 60 (ego y?) (+ 2 horizon)))
				else
					(ego posn: 317 (proc0_17 188 (ego y?) 129))
				)
			)
		)
		(ego init:)
		(NormalEgo)
		(self setRegions: RIVER)
		(= i 0)
		(while (< i 1)
			((= [ripple i] (Clone Ripple))
				view: [rippleView i]
				cycleSpeed: 1
				x: [rippleX i]
				y: [rippleY i]
				setLoop: [rippleLoop i]
				ignoreActors: TRUE
				description: {ripple}
				init:
				stopUpd:
			)
			(if (>= howFast 1) ([ripple i] setCycle: Forward))
			(++ i)
		)
		(bank1 init:)
		(river1 init:)
		(river2 init:)
		(river3 init:)
		(river4 init:)
		(river5 init:)
		(rock init:)
		(bush1 init:)
		(bush2 init:)
		(bush3 init:)
		(bush4 init:)
		(bush5 init:)
		(bush6 init:)
		(bush7 init:)
		(bush8 init:)
		(bush9 init:)
		(bush10 init:)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script (script doit:))
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
					((Said '<across[/brook,water,brook]')
						(Print 32 0)
					)
					((Said '/clearing')
						(Print 32 1)
					)
				)
			)
		)
	)
)

(instance bank1 of NewFeature
	(properties
		x 287
		y 74
		noun '/bank'
		nsTop 60
		nsLeft 256
		nsBottom 88
		nsRight 319
		description {bank}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The banks of the swiftly flowing river are steep.}
	)
)

(instance river1 of NewFeature
	(properties
		x 239
		y 71
		noun '[<at,around][/room,brook,brook,water]'
		nsTop 59
		nsLeft 222
		nsBottom 84
		nsRight 256
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The river flows swiftly between steep banks.}
	)
)

(instance river2 of NewFeature
	(properties
		x 277
		y 94
		noun '[<at,around][/room,brook,brook,water]'
		nsTop 84
		nsLeft 235
		nsBottom 105
		nsRight 320
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The river flows swiftly between steep banks.}
	)
)

(instance river3 of NewFeature
	(properties
		x 291
		y 112
		noun '[<at,around][/room,brook,brook,water]'
		nsTop 105
		nsLeft 262
		nsBottom 120
		nsRight 320
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The river flows swiftly between steep banks.}
	)
)

(instance river4 of NewFeature
	(properties
		x 240
		y 54
		noun '[<at,around][/room,brook,brook,water]'
		nsTop 49
		nsLeft 231
		nsBottom 59
		nsRight 249
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The river flows swiftly between steep banks.}
	)
)

(instance river5 of NewFeature
	(properties
		x 305
		y 123
		noun '[<at,around][/room,brook,brook,water]'
		nsTop 120
		nsLeft 291
		nsBottom 126
		nsRight 320
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The river flows swiftly between steep banks.}
	)
)

(instance Ripple of Prop
	(properties)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/brook,boulder,water')
				(Print 32 2)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 32 2)
			)
		)
	)
)

(instance rock of NewFeature
	(properties
		x 236
		y 185
		noun '/boulder'
		nsTop 182
		nsLeft 198
		nsBottom 189
		nsRight 275
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A large, run-of-the-mill boulder sits nearby.}
	)
)

(instance bush1 of NewFeature
	(properties
		x 232
		y 136
		noun '/bush,bury'
		nsTop 130
		nsLeft 214
		nsBottom 142
		nsRight 250
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Small bushes grow vigorously in this sunny patch of forest.}
	)
)

(instance bush2 of NewFeature
	(properties
		x 65
		y 165
		noun '/bush,bury'
		nsTop 152
		nsLeft 38
		nsBottom 179
		nsRight 93
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Bushes and rocks seem to complement each other, don't they?}
	)
)

(instance bush3 of NewFeature
	(properties
		x 23
		y 177
		noun '/bush,bury'
		nsTop 173
		nsLeft 8
		nsBottom 182
		nsRight 39
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Bushes and rocks seem to complement each other, don't they?}
	)
)

(instance bush4 of NewFeature
	(properties
		x 99
		y 171
		noun '/bush,bury'
		nsTop 164
		nsLeft 93
		nsBottom 179
		nsRight 106
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Bushes and rocks seem to complement each other, don't they?}
	)
)

(instance bush5 of NewFeature
	(properties
		x 60
		y 99
		noun '/bush,bury'
		nsTop 92
		nsLeft 12
		nsBottom 107
		nsRight 109
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Bushes and rocks seem to complement each other, don't they?}
	)
)

(instance bush6 of NewFeature
	(properties
		x 64
		y 86
		noun '/bush,bury'
		nsTop 80
		nsLeft 31
		nsBottom 92
		nsRight 97
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There certainly is an abundance of healthy plant life here.}
	)
)

(instance bush7 of NewFeature
	(properties
		x 151
		y 53
		noun '/bush,bury'
		nsTop 47
		nsLeft 141
		nsBottom 59
		nsRight 162
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There certainly is an abundance of healthy plant life here.}
	)
)

(instance bush8 of NewFeature
	(properties
		x 197
		y 61
		noun '/bush,bury'
		nsTop 56
		nsLeft 178
		nsBottom 67
		nsRight 216
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There certainly is an abundance of healthy plant life here.}
	)
)

(instance bush9 of NewFeature
	(properties
		x 198
		y 52
		noun '/bush,bury'
		nsTop 48
		nsLeft 189
		nsBottom 56
		nsRight 207
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There certainly is an abundance of healthy plant life here.}
	)
)

(instance bush10 of NewFeature
	(properties
		x 33
		y 67
		noun '/bush,bury'
		nsTop 55
		nsBottom 80
		nsRight 67
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There certainly is an abundance of healthy plant life here.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 47
		y 22
		noun '/ceder'
		nsTop -1
		nsBottom 46
		nsRight 95
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There's nothing unusual about this tree, though it does seem especially healthy.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 101
		y 35
		noun '/ceder'
		nsTop 28
		nsLeft 95
		nsBottom 42
		nsRight 107
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There's nothing unusual about this tree, though it does seem especially healthy.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 32
		y 50
		noun '/ceder'
		nsTop 46
		nsBottom 55
		nsRight 65
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There's nothing unusual about this tree, though it does seem especially healthy.}
	)
)
