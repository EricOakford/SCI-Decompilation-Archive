;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2)
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
	rm2 0
)

(local
	i
	[ripple 6]
	[rippleX 6] = [137 203 296 258 96 56]
	[rippleY 6] = [178 178 159 168 171 167]
	[rippleLoop 6] = [1 2 3 3 3 3]
)
(instance rm2 of Room
	(properties
		picture 2
		horizon 67
		north 15
		east 1
		south 47
		west 3
	)
	
	(method (init)
		(LoadMany VIEW 202 267)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east SCROLLRIGHT)
				(south WIPEUP)
			)
		)
		(super init:)
		(switch prevRoomNum
			(north
				(ego posn: 58 (+ horizon 2))
			)
			(south
				(ego posn: 163 188)
			)
			(west
				(ego posn: 3 100)
			)
			(east
				(ego x: 317)
			)
			(else
				(ego posn: 3 100)
			)
		)
		(ego init:)
		(NormalEgo)
		(bridge init:)
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
					(Print 2 0)
				else
					(Print 2 1)
				)
			)
			((Said 'get,take,use/planter,caldron,planter')
				(Print 2 2)
			)
			((or (Said 'get,take<in/urn,vase,caldron') (Said 'hide<in/urn,vase,caldron'))
				(Print 2 3)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance urn of RPicView
	(properties
		x 288
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
				(Print 2 4)
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
				(Print 2 5)
			)
		)
	)
)

(instance bridge of NewFeature
	(properties
		x 91
		y 111
		noun '/bridge'
		nsTop 95
		nsLeft 3
		nsBottom 128
		nsRight 179
		description {bridge}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A sturdy bridge has been built here to span the moat.}
	)
)

(instance vine1 of NewFeature
	(properties
		x 281
		y 27
		noun '/vine,ivy,bury,ceder'
		nsLeft 246
		nsBottom 55
		nsRight 317
		description {vine}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The castle walls are carpeted with a thick tangle of vines.}
	)
)

(instance vine2 of NewFeature
	(properties
		x 280
		y 59
		noun '/vine,ivy,bury,ceder'
		nsTop 55
		nsLeft 274
		nsBottom 63
		nsRight 287
		description {vine}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The castle walls are carpeted with a thick tangle of vines.}
	)
)

(instance turret of NewFeature
	(properties
		x 173
		y 49
		noun '/castle,turret'
		nsTop -1
		nsLeft 104
		nsBottom 100
		nsRight 242
		description {turret}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Massive turrets, from which the guards can defend the castle, stretch to the sky above.}
	)
)

(instance moat1 of NewFeature
	(properties
		x 57
		y 125
		noun '/moat'
		nsTop 102
		nsLeft 22
		nsBottom 149
		nsRight 93
		description {moat}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You never know what sort of fierce creatures lie just beneath the surface of the moat.}
	)
)

(instance moat2 of NewFeature
	(properties
		x 78
		y 93
		noun '/moat'
		nsTop 86
		nsLeft 59
		nsBottom 101
		nsRight 97
		description {moat}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You never know what sort of fierce creatures lie just beneath the surface of the moat.}
	)
)

(instance moat3 of NewFeature
	(properties
		x 97
		y 82
		noun '/moat'
		nsTop 75
		nsLeft 90
		nsBottom 90
		nsRight 105
		description {moat}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You never know what sort of fierce creatures lie just beneath the surface of the moat.}
	)
)

(instance tree of NewFeature
	(properties
		x 52
		y 32
		noun '/ceder'
		nsTop -1
		nsBottom 65
		nsRight 104
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees around the castle are tall and old; they were just as tall back when King Edward was just a young prince!}
	)
)

(instance tree1 of NewFeature
	(properties
		x 13
		y 74
		noun '/ceder'
		nsTop 64
		nsBottom 84
		nsRight 27
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees around the castle are tall and old; they were just as tall back when King Edward was just a young prince!}
	)
)
