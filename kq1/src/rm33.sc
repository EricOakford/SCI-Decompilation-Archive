;;; Sierra Script 1.0 - (do not remove this comment)
(script# 33)
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
	rm33 0
)

(local
	i
	[ripple 4]
	[rippleView 4] = [241 242 242 226]
	[rippleX 4] = [136 57 264 282]
	[rippleY 4] = [147 140 94 150]
	[rippleLoop 4] = [1 3 3 3]
)
(instance rm33 of Room
	(properties
		picture 33
		horizon 51
		north 48
		east 40
		south 32
		west 34
	)
	
	(method (init)
		(LoadMany VIEW 241 242 226)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(super init:)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(switch prevRoomNum
			(north
				(ego posn: (proc0_17 256 (ego x?) 39) (+ horizon 2))
			)
			(south
				(if (< (ego x?) 247)
					(ego posn: (proc0_17 273 (ego x?) 0) 188)
				else
					(ego posn: 317 121)
				)
			)
			(west
				(if (< (ego y?) 128)
					(ego posn: 3 (proc0_17 69 (ego y?) (+ horizon 2)))
				else
					(ego posn: 3 (proc0_17 188 (ego y?) 183))
				)
			)
			(else 
				(ego posn: 317 (proc0_17 139 (ego y?) 113))
			)
		)
		(ego init:)
		(NormalEgo)
		(self setRegions: RIVER)
		(= i 0)
		(while (< i 4)
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
		(rock1 init:)
		(river1 init:)
		(river2 init:)
		(river3 init:)
		(river4 init:)
		(river5 init:)
		(river6 init:)
		(river7 init:)
		(river8 init:)
		(river9 init:)
		(river10 init:)
		(river11 init:)
		(boulder1 init:)
		(boulder2 init:)
		(boulder3 init:)
		(boulder4 init:)
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
		(bush11 init:)
		(bush12 init:)
		(bush13 init:)
		(bush14 init:)
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
					((Said '<across/brook,brook,water')
						(Print 33 0)
					)
					((Said '/rapid')
						(Print 33 1)
					)
					((Said '/clearing')
						(Print 33 2)
					)
				)
			)
		)
	)
)

(instance river1 of NewFeature
	(properties
		x 50
		y 142
		noun '[<at,around][/room,brook,brook,water]'
		nsTop 111
		nsBottom 174
		nsRight 100
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a raging river, filled with massive boulders and treacherous whitewater rapids.}
	)
)

(instance river2 of NewFeature
	(properties
		x 118
		y 138
		noun '[<at,around][/room,brook,brook,water]'
		nsTop 109
		nsLeft 100
		nsBottom 168
		nsRight 137
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a raging river, filled with massive boulders and treacherous whitewater rapids.}
	)
)

(instance river3 of NewFeature
	(properties
		x 149
		y 107
		noun '[<at,around][/room,brook,brook,water]'
		nsTop 100
		nsLeft 136
		nsBottom 114
		nsRight 162
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a raging river, filled with massive boulders and treacherous whitewater rapids.}
	)
)

(instance river4 of NewFeature
	(properties
		x 161
		y 97
		noun '[<at,around][/room,brook,brook,water]'
		nsTop 94
		nsLeft 148
		nsBottom 100
		nsRight 175
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a raging river, filled with massive boulders and treacherous whitewater rapids.}
	)
)

(instance river5 of NewFeature
	(properties
		x 281
		y 84
		noun '[<at,around][/room,brook,brook,water]'
		nsTop 73
		nsLeft 243
		nsBottom 90
		nsRight 320
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a raging river, filled with massive boulders and treacherous whitewater rapids.}
	)
)

(instance river6 of NewFeature
	(properties
		x 271
		y 99
		noun '[<at,around][/room,brook,brook,water]'
		nsTop 94
		nsLeft 248
		nsBottom 105
		nsRight 294
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a raging river, filled with massive boulders and treacherous whitewater rapids.}
	)
)

(instance river7 of NewFeature
	(properties
		x 149
		y 146
		noun '[<at,around][/room,brook,brook,water]'
		nsTop 131
		nsLeft 136
		nsBottom 162
		nsRight 163
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a raging river, filled with massive boulders and treacherous whitewater rapids.}
	)
)

(instance river8 of NewFeature
	(properties
		x 173
		y 142
		noun '[<at,around][/room,brook,brook,water]'
		nsTop 135
		nsLeft 163
		nsBottom 150
		nsRight 184
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a raging river, filled with massive boulders and treacherous whitewater rapids.}
	)
)

(instance river9 of NewFeature
	(properties
		x 234
		y 142
		noun '[<at,around][/room,brook,brook,water]'
		nsTop 136
		nsLeft 208
		nsBottom 148
		nsRight 261
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a raging river, filled with massive boulders and treacherous whitewater rapids.}
	)
)

(instance river10 of NewFeature
	(properties
		x 275
		y 160
		noun '[<at,around][/room,brook,brook,water]'
		nsTop 148
		nsLeft 230
		nsBottom 172
		nsRight 320
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a raging river, filled with massive boulders and treacherous whitewater rapids.}
	)
)

(instance river11 of NewFeature
	(properties
		x 298
		y 180
		noun '[<at,around][/room,brook,brook,water]'
		nsTop 172
		nsLeft 277
		nsBottom 189
		nsRight 320
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a raging river, filled with massive boulders and treacherous whitewater rapids.}
	)
)

(instance boulder1 of NewFeature
	(properties
		x 212
		y 116
		noun '/boulder'
		nsTop 105
		nsLeft 163
		nsBottom 128
		nsRight 262
		description {boulder}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {It is a massive boulder that splits the river.}
	)
)

(instance boulder2 of NewFeature
	(properties
		x 213
		y 100
		noun '/boulder'
		nsTop 96
		nsLeft 175
		nsBottom 104
		nsRight 251
		description {boulder}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {It is a massive boulder that splits the river.}
	)
)

(instance boulder3 of NewFeature
	(properties
		x 208
		y 91
		noun '/boulder'
		nsTop 86
		nsLeft 186
		nsBottom 96
		nsRight 230
		description {boulder}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {It is a massive boulder that splits the river.}
	)
)

(instance boulder4 of NewFeature
	(properties
		x 205
		y 80
		noun '/boulder'
		nsTop 74
		nsLeft 194
		nsBottom 86
		nsRight 217
		description {boulder}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {It is a massive boulder that splits the river.}
	)
)

(instance Ripple of Prop
	(properties)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((Said 'look,check/brook,boulder,water') (Print 33 3))
			(else (super handleEvent: event))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook (Print 33 3))
		)
	)
)

(instance tree1 of NewFeature
	(properties
		x 43
		y 22
		noun '/ceder'
		nsTop -1
		nsBottom 45
		nsRight 86
		description {tree1}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Several lovely trees sway peacefully in the breeze.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 25
		y 58
		noun '/ceder'
		nsTop 46
		nsLeft 6
		nsBottom 70
		nsRight 44
		description {tree2}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Several lovely trees sway peacefully in the breeze.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 138
		y 18
		noun '/ceder'
		nsTop -1
		nsLeft 109
		nsBottom 38
		nsRight 167
		description {tree3}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Several lovely trees sway peacefully in the breeze.}
	)
)

(instance bush1 of NewFeature
	(properties
		x 21
		y 67
		noun '/bush'
		nsTop 57
		nsBottom 77
		nsRight 43
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The bushes here grow well, helped by the refreshing spray of the nearby river.}
	)
)

(instance bush2 of NewFeature
	(properties
		x 51
		y 69
		noun '/bush'
		nsTop 63
		nsLeft 43
		nsBottom 75
		nsRight 60
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The bushes here grow well, helped by the refreshing spray of the nearby river.}
	)
)

(instance bush3 of NewFeature
	(properties
		x 68
		y 72
		noun '/bush'
		nsTop 67
		nsLeft 60
		nsBottom 77
		nsRight 77
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The bushes here grow well, helped by the refreshing spray of the nearby river.}
	)
)

(instance bush4 of NewFeature
	(properties
		x 50
		y 43
		noun '/bush'
		nsTop 35
		nsLeft 36
		nsBottom 51
		nsRight 64
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The bushes here grow well, helped by the refreshing spray of the nearby river.}
	)
)

(instance bush5 of NewFeature
	(properties
		x 78
		y 43
		noun '/bush'
		nsTop 36
		nsLeft 67
		nsBottom 51
		nsRight 90
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The bushes here grow well, helped by the refreshing spray of the nearby river.}
	)
)

(instance bush6 of NewFeature
	(properties
		x 142
		y 36
		noun '/bush'
		nsTop 27
		nsLeft 120
		nsBottom 45
		nsRight 164
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The bushes here grow well, helped by the refreshing spray of the nearby river.}
	)
)

(instance bush7 of NewFeature
	(properties
		x 5
		y 44
		noun '/bush'
		nsTop 38
		nsBottom 51
		nsRight 11
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The bushes here grow well, helped by the refreshing spray of the nearby river.}
	)
)

(instance bush8 of NewFeature
	(properties
		x 303
		y 35
		noun '/bush'
		nsTop 19
		nsLeft 286
		nsBottom 52
		nsRight 320
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The bushes here grow well, helped by the refreshing spray of the nearby river.}
	)
)

(instance bush9 of NewFeature
	(properties
		x 276
		y 24
		noun '/bush'
		nsTop 16
		nsLeft 268
		nsBottom 32
		nsRight 285
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The bushes here grow well, helped by the refreshing spray of the nearby river.}
	)
)

(instance bush10 of NewFeature
	(properties
		x 240
		y 40
		noun '/bush'
		nsTop 36
		nsLeft 221
		nsBottom 45
		nsRight 260
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The bushes here grow well, helped by the refreshing spray of the nearby river.}
	)
)

(instance bush11 of NewFeature
	(properties
		x 255
		y 30
		noun '/bush'
		nsTop 25
		nsLeft 242
		nsBottom 35
		nsRight 269
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The bushes here grow well, helped by the refreshing spray of the nearby river.}
	)
)

(instance bush12 of NewFeature
	(properties
		x 277
		y 49
		noun '/bush'
		nsTop 46
		nsLeft 268
		nsBottom 52
		nsRight 286
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The bushes here grow well, helped by the refreshing spray of the nearby river.}
	)
)

(instance bush13 of NewFeature
	(properties
		x 313
		y 102
		noun '/bush'
		nsTop 86
		nsLeft 307
		nsBottom 119
		nsRight 320
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The bushes here grow well, helped by the refreshing spray of the nearby river.}
	)
)

(instance bush14 of NewFeature
	(properties
		x 301
		y 105
		noun '/bush'
		nsTop 92
		nsLeft 296
		nsBottom 118
		nsRight 307
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The bushes here grow well, helped by the refreshing spray of the nearby river.}
	)
)

(instance rock1 of NewFeature
	(properties
		x 273
		y 40
		noun '/boulder'
		nsTop 34
		nsLeft 260
		nsBottom 46
		nsRight 286
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Several large, rough rocks sit around the riverbank.}
	)
)
