;;; Sierra Script 1.0 - (do not remove this comment)
(script# 42)
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
	rm42 0
)

(local
	i
	[ripple 3]
	[rippleView 3] = [241 241 226]
	[rippleX 3] = [249 63 51]
	[rippleY 3] = [134 142 180]
	[rippleLoop 3] = [3 3 2]
)
(instance rm42 of Room
	(properties
		picture 42
		horizon 44
		north 7
		east 43
		south 39
		west 41
	)
	
	(method (init)
		(LoadMany VIEW 14 241 226)
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
				(ego posn: (proc0_17 264 (ego x?) 0) (+ horizon 2))
			)
			(south
				(if (< (ego x?) 60)
					(ego posn: 10 176)
				else
					(ego y: 188)
				)
			)
			(west
				(if (< (ego y?) 148)
					(ego posn: 3 (proc0_17 103 (ego y?) (+ horizon 2)))
				else
					(ego posn: 10 170)
				)
			)
			(east
				(if (and egoInWater (<= (ego y?) 119))
					(ego posn: 317 (proc0_17 119 (ego y?) (+ horizon 2)))
				else
					(ego x: 315)
				)
			)
			(else  (ego posn: 142 76))
		)
		(ego init:)
		(if (not egoInWater)
			(NormalEgo)
		)
		(= i 0)
		(while (< i 3)
			((= [ripple i] (Clone Ripple))
				view: [rippleView i]
				x: [rippleX i]
				y: [rippleY i]
				setLoop: [rippleLoop i]
				ignoreActors: TRUE
				description: {ripple}
				setPri: 1
				init:
				stopUpd:
			)
			(if (>= howFast 1) ([ripple i] setCycle: Forward))
			(++ i)
		)
		(self setRegions: RIVER)
		(self setRegions: WATER_42)
		(bridge1 init:)
		(bridge2 init:)
		(bridge3 init:)
		(bridge4 init:)
		(bush1 init:)
		(bush2 init:)
		(river1 init:)
		(river2 init:)
		(river3 init:)
		(river4 init:)
		(river5 init:)
		(lake1 init:)
		(bank1 init:)
		(pinetree1 init:)
		(pinetree2 init:)
		(pinetree3 init:)
		(oaktree1 init:)
		(oaktree2 init:)
		(oaktree3 init:)
		(oaktree4 init:)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			((and script (or (not egoInWater) (== egoInWater 6)))
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
			((and script egoInWater)
				(script doit:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'climb,scale')
				(if egoInWater
					(Print 42 0)
				else
					(event claimed: FALSE)
				)
			)
			((Said 'swim')
				(if (& (ego onControl: origin) cLBLUE)
					(Print 42 1)
				else
					(event claimed: FALSE)
				)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room]')
						(Print 42 2)
					)
					((Said '/clearing')
						(Print 42 3)
					)
					((Said '/water')
						(if (ego inRect: 165 44 312 116)
							(Print 42 4)
						else
							(Print 42 5)
						)
					)
					((Said '<below,below/bridge')
						(Print 42 6)
					)
				)
			)
		)
	)
)

(instance river1 of NewFeature
	(properties
		x 167
		y 138
		noun '/brook,brook'
		nsTop 121
		nsLeft 18
		nsBottom 156
		nsRight 317
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The river gets to be rather rough around these parts.}
	)
)

(instance river2 of NewFeature
	(properties
		x 93
		y 161
		noun '/brook,brook'
		nsTop 156
		nsLeft 68
		nsBottom 166
		nsRight 119
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The river gets to be rather rough around these parts.}
	)
)

(instance river3 of NewFeature
	(properties
		x 80
		y 173
		noun '/brook,brook'
		nsTop 167
		nsLeft 61
		nsBottom 179
		nsRight 99
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The river gets to be rather rough around these parts.}
	)
)

(instance river4 of NewFeature
	(properties
		x 62
		y 180
		noun '/brook,brook'
		nsTop 177
		nsLeft 43
		nsBottom 184
		nsRight 81
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The river gets to be rather rough around these parts.}
	)
)

(instance river5 of NewFeature
	(properties
		x 45
		y 186
		noun '/brook,brook'
		nsTop 184
		nsLeft 24
		nsBottom 189
		nsRight 66
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The river gets to be rather rough around these parts.}
	)
)

(instance bush1 of NewFeature
	(properties
		x 5
		y 112
		noun '/bush'
		nsTop 104
		nsBottom 121
		nsRight 11
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You see lush plants and bushes throughout the kingdom of Daventry.}
	)
)

(instance bush2 of NewFeature
	(properties
		x 12
		y 142
		noun '/bush'
		nsTop 122
		nsBottom 162
		nsRight 25
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You see lush plants and bushes throughout the kingdom of Daventry.}
	)
)

(instance lake1 of NewFeature
	(properties
		x 307
		y 88
		noun '/lake,lake'
		nsTop 65
		nsLeft 295
		nsBottom 111
		nsRight 319
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The clear blue lake is surrounded by a pretty meadow.}
	)
)

(instance bridge1 of NewFeature
	(properties
		x 157
		y 108
		noun '/bridge,crossing'
		nsTop 95
		nsLeft 112
		nsBottom 121
		nsRight 203
		description {bridge}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {It is an old stone bridge, worn smooth by the ravages of weather and time.}
	)
)

(instance bridge2 of NewFeature
	(properties
		x 180
		y 139
		noun '/bridge,crossing'
		nsTop 121
		nsLeft 133
		nsBottom 157
		nsRight 228
		description {bridge}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {It is an old stone bridge, worn smooth by the ravages of weather and time.}
	)
)

(instance bank1 of NewFeature
	(properties
		x 153
		y 112
		noun '/bank[<brook]'
		nsTop 107
		nsLeft 12
		nsBottom 118
		nsRight 294
		description {bank}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These rocky banks are damp and slippery.__Be careful not to come too close!}
	)
)

(instance bridge3 of NewFeature
	(properties
		x 115
		y 88
		noun '/bridge,crossing'
		nsTop 82
		nsLeft 107
		nsBottom 95
		nsRight 123
		description {bridge}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {It is an old stone bridge, worn smooth by the ravages of weather and time.}
	)
)

(instance bridge4 of NewFeature
	(properties
		x 175
		y 89
		noun '/bridge,crossing'
		nsTop 83
		nsLeft 162
		nsBottom 95
		nsRight 189
		description {bridge}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {It is an old stone bridge, worn smooth by the ravages of weather and time.}
	)
)

(instance oaktree1 of NewFeature
	(properties
		x 73
		y 7
		noun '/ceder[<oak]'
		nsLeft 3
		nsBottom 14
		nsRight 143
		description {oak tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A stand of lovely golden oaks line the riverbank.}
	)
)

(instance oaktree2 of NewFeature
	(properties
		x 70
		y 43
		noun '/ceder[<oak]'
		nsTop 14
		nsLeft 60
		nsBottom 72
		nsRight 81
		description {oak tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A stand of lovely golden oaks line the riverbank.}
	)
)

(instance pinetree1 of NewFeature
	(properties
		x 238
		y 71
		noun '/ceder[<pine]'
		nsTop 45
		nsLeft 212
		nsBottom 98
		nsRight 265
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There is a beautiful pine tree by the clear blue lake.}
	)
)

(instance pinetree2 of NewFeature
	(properties
		x 239
		y 35
		noun '/ceder[<pine]'
		nsTop 27
		nsLeft 226
		nsBottom 44
		nsRight 253
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There is a beautiful pine tree by the clear blue lake.}
	)
)

(instance pinetree3 of NewFeature
	(properties
		x 239
		y 21
		noun '/ceder[<pine]'
		nsTop 16
		nsLeft 232
		nsBottom 27
		nsRight 246
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There is a beautiful pine tree by the clear blue lake.}
	)
)

(instance Ripple of Prop
	(properties)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((Said 'look,check/brook,boulder,water')
				(Print 42 7)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 42 7)
			)
		)
	)
)

(instance oaktree3 of NewFeature
	(properties
		x 136
		y 31
		noun '/ceder[<oak]'
		nsLeft 121
		nsBottom 62
		nsRight 151
		description {oaktree2}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A stand of lovely golden oaks line the riverbank.}
	)
)

(instance oaktree4 of NewFeature
	(properties
		x 188
		y 35
		noun '/ceder[<oak]'
		nsLeft 177
		nsBottom 70
		nsRight 199
		description {oaktree3}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A stand of lovely golden oaks line the riverbank.}
	)
)
