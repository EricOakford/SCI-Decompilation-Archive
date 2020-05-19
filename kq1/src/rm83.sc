;;; Sierra Script 1.0 - (do not remove this comment)
(script# 83)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use RFeature)
(use Motion)
(use Game)
(use Actor)

(public
	rm83 0
)

(local
	i
	[ripple 6]
	[rippleX 6] = [263 224 62 24 203 123]
	[rippleY 6] = [167 171 169 159 178 178]
	[rippleLoop 6] = [3 3 3 3 2 1]
)
(instance rm83 of Room
	(properties
		picture 83
		horizon 74
		north 16
		east 8
		west 1
	)
	
	(method (init)
		(LoadMany VIEW 202 267)
		(self style:
			(switch prevRoomNum
				(north 5)
				(west 41)
				(east 2)
				(south 4)
			)
		)
		(super init:)
		(switch prevRoomNum
			(north
				(ego posn: 296 (+ horizon 2))
			)
			(west
				(ego x: 3)
			)
			(east
				(ego posn: 317 99)
			)
			(else 
				(ego loop: 1 posn: 317 99)
			)
		)
		(ego init:)
		(NormalEgo)
		(tree init:)
		(tree1 init:)
		(self setRegions: MOAT)
		(= i 0)
		(while (< i 6)
			((= [ripple i] (Clone Ripple))
				view: 202
				cycleSpeed: 1
				setPri: 3
				x: [rippleX i]
				y: [rippleY i]
				setLoop: [rippleLoop i]
				ignoreActors: TRUE
				ignoreActors: TRUE
				sightAngle: 180
				closeRangeDist: 500
				longRangeDist: 500
				description: {ripples}
				init:
				stopUpd:
			)
			(if (>= howFast 1)
				([ripple i] setCycle: Forward)
			)
			(++ i)
		)
		(addToPics add: urn eachElementDo: #init doit:)
		(bridge init:)
		(vine1 init:)
		(vine2 init:)
		(turret init:)
		(moat1 init:)
		(moat2 init:)
		(moat3 init:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/blossom')
				(if (< (ego y?) 112)
					(Print 83 0)
				else
					(Print 83 1)
				)
			)
			((Said 'get,take,use/planter,caldron,planter')
				(Print 83 2)
			)
			(
				(or
					(Said 'get,take<in/planter,planter,caldron')
					(Said 'hide<in/planter,planter,caldron')
				)
				(Print 83 3)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance urn of RPicView
	(properties
		x 34
		y 103
		noun '/planter,caldron'
		description {urn}
		sightAngle 180
		closeRangeDist 300
		longRangeDist 300
		view 267
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/planter,planter,planter,caldron')
				(Print 83 4)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 83 4)
			)
		)
	)
)

(instance Ripple of Prop
	(properties)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/moat,water,brook')
				(Print 83 5)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 83 5)
			)
		)
	)
)

(instance bridge of NewFeature
	(properties
		x 224
		y 113
		noun '/bridge'
		nsTop 94
		nsLeft 138
		nsBottom 133
		nsRight 311
		description {bridge}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {A sturdy stone bridge has been built here to span the moat.}
	)
)

(instance vine1 of NewFeature
	(properties
		x 35
		y 24
		noun '/vine,ivy,bury,ceder'
		nsTop -1
		nsLeft 3
		nsBottom 49
		nsRight 68
		description {vine}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The castle walls are carpeted with a thick tangle of vines.}
	)
)

(instance vine2 of NewFeature
	(properties
		x 31
		y 55
		noun '/vine,ivy,bury,ceder'
		nsTop 49
		nsLeft 27
		nsBottom 62
		nsRight 36
		description {vine}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The castle walls are carpeted with a thick tangle of vines.}
	)
)

(instance turret of NewFeature
	(properties
		x 144
		y 50
		noun '/turret,castle'
		nsTop -1
		nsLeft 75
		nsBottom 101
		nsRight 214
		description {turret}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {Massive turrets, from which the guards can defend the castle, stretch to the sky above.}
	)
)

(instance moat1 of NewFeature
	(properties
		x 225
		y 85
		noun '/water'
		nsTop 80
		nsLeft 215
		nsBottom 91
		nsRight 235
		description {moat}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {You never know what sort of fierce creatures lie just beneath the surface of the moat.}
	)
)

(instance moat2 of NewFeature
	(properties
		x 262
		y 121
		noun '/water'
		nsTop 93
		nsLeft 223
		nsBottom 150
		nsRight 302
		description {moat}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {You never know what sort of fierce creatures lie just beneath the surface of the moat.}
	)
)

(instance moat3 of NewFeature
	(properties
		x 245
		y 88
		noun '/water'
		nsTop 84
		nsLeft 236
		nsBottom 92
		nsRight 254
		description {moat}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {You never know what sort of fierce creatures lie just beneath the surface of the moat.}
	)
)

(instance tree of NewFeature
	(properties
		x 267
		y 32
		noun '/ceder'
		nsTop -1
		nsLeft 215
		nsBottom 65
		nsRight 319
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The forest is slowly encroaching on the castle grounds; there are tall, ancient trees everywhere you look.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 291
		y 83
		noun '/ceder'
		nsTop 64
		nsLeft 264
		nsBottom 103
		nsRight 319
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 500
		shiftClick 369
		lookStr {The forest is slowly encroaching on the castle grounds; there are tall, ancient trees everywhere you look.}
	)
)
