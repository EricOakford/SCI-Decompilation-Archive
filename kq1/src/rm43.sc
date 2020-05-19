;;; Sierra Script 1.0 - (do not remove this comment)
(script# 43)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm43 0
)

(local
	i
	[ripple 6]
	[rippleX 5] = [174 212 170 148 26]
	[rippleY 5] = [162 130 134 110 150]
	[rippleLoop 5] = [2 3 3 3]
)
(instance rm43 of Room
	(properties
		picture 43
		horizon 50
		north 6
		east 44
		south 38
		west 42
	)
	
	(method (init)
		(Load VIEW 243)
		(if (>= howFast 1)
			(Load VIEW 302)
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
				(if (> (ego x?) 263)
					(ego posn: (+ 190 (- (ego x?) 264)) (+ horizon 2))
				else
					(ego posn: (/ (* (ego x?) 18) 25) (+ horizon 2))
				)
			)
			(south (ego y: 188))
			(west
				(if (< (ego y?) 140)
					(ego posn: 3 (proc0_17 108 (ego y?) (+ horizon 2)))
				else
					(ego posn: 3 (proc0_17 188 (ego y?) 175))
				)
			)
			(east
				(ego
					posn: 317 (proc0_17 188 (proc0_18 78 (ego y?) 67) 61)
				)
			)
			(else  (ego posn: 3 178))
		)
		(ego init:)
		(if (not egoInWater)
			(NormalEgo)
		else
			(ego setCycle: Forward)
		)
		(rock init:)
		(lake1 init:)
		(lake2 init:)
		(lake3 init:)
		(lake4 init:)
		(lake5 init:)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(bush1 init:)
		(bush2 init:)
		(self setRegions: WATER_42)
		(= i (if (>= howFast 1) 0 else 4))
		(while (< i 5)
			((= [ripple i] (Clone Ripple))
				view: 243
				cycleSpeed: 1
				setPri: 0
				x: [rippleX i]
				y: [rippleY i]
				setLoop: [rippleLoop i]
				setCycle: Forward
				ignoreActors: 1
				description: {ripple}
				init:
			)
			(++ i)
		)
		(if (>= howFast 1) (fish init:))
	)
	
	(method (doit &tmp nRoom)
		(cond 
			((and script (not egoInWater))
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
			((and (== egoInWater 4) (& (ego onControl: origin) cLMAGENTA))
				(ego cycleSpeed: 1 moveSpeed: 1)
			)
			((and (== egoInWater 4) (& (ego onControl: origin) cYELLOW))
				(ego cycleSpeed: 2 moveSpeed: 2)
			)
			((== egoInWater 4)
				(if
					(not
						(if (& (ego onControl: origin) cLMAGENTA)
						else
							(& (ego onControl: origin) cYELLOW)
						)
					)
					(ego cycleSpeed: 0 moveSpeed: 0)
				)
			)
		)
		(if (and script egoInWater)
			(script doit:)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			(
				(or
					(Said 'look,check/boulder')
					(MouseClaimed event 232 131 263 148)
					(MouseClaimed event 264 113 289 135)
				)
				(Print 43 0)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,lake,water,lake]')
						(Print 43 1)
					)
					((Said '/clearing')
						(Print 43 2)
					)
					((Said '/brook')
						(Print 43 3)
					)
					((Said '/ceder')
						(Print 43 4)
					)
				)
			)
		)
	)
)

(instance fishJump of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fish
					posn: (Random 30 155) (Random 100 150)
					loop: (Random 0 1)
					setCycle: EndLoop self
				)
			)
			(1
				(fish stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance fish of Prop
	(properties
		view 302
	)
	
	(method (init)
		(self
			cycleSpeed: (if (>= howFast 1) 1 else 0)
			ignoreActors:
			stopUpd:
		)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(< (Random 1 100) 3)
				(not (fish script?))
				(not egoInWater)
			)
			(fish setScript: fishJump)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'look,check/fish')
				(self doVerb: verbLook)
			)
			((Said 'get,take,capture/fish')
				(self doVerb: verbGet)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (fish script?)
					(Print 43 5)
				else
					(Print 43 6)
				)
			)
			(verbGet
				(Print 43 7)
			)
		)
	)
)

(instance lake1 of NewFeature
	(properties
		x 74
		y 105
		noun '/lake,lake,water'
		nsTop 47
		nsBottom 163
		nsRight 148
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The clear blue lake is surrounded by a pretty meadow.}
	)
)

(instance lake2 of NewFeature
	(properties
		x 169
		y 105
		noun '/lake,lake,water'
		nsTop 51
		nsLeft 148
		nsBottom 160
		nsRight 191
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The clear blue lake is surrounded by a pretty meadow.}
	)
)

(instance lake3 of NewFeature
	(properties
		x 202
		y 104
		noun '/lake,lake,water'
		nsTop 61
		nsLeft 191
		nsBottom 147
		nsRight 213
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The clear blue lake is surrounded by a pretty meadow.}
	)
)

(instance lake4 of NewFeature
	(properties
		x 221
		y 101
		noun '/lake,lake,water'
		nsTop 70
		nsLeft 212
		nsBottom 133
		nsRight 231
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The clear blue lake is surrounded by a pretty meadow.}
	)
)

(instance lake5 of NewFeature
	(properties
		x 241
		y 101
		noun '/lake,lake,water'
		nsTop 91
		nsLeft 231
		nsBottom 112
		nsRight 251
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The clear blue lake is surrounded by a pretty meadow.}
	)
)

(instance Ripple of Prop
	(properties)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((Said 'look,check/lake,water')
				(Print 43 1)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 43 1)
			)
		)
	)
)

(instance tree1 of NewFeature
	(properties
		x 281
		y 28
		noun '/ceder'
		nsLeft 256
		nsBottom 57
		nsRight 307
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Several pretty trees stand by the lakeside, offering shade and beauty to wandering adventurers.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 163
		y 27
		noun '/ceder'
		nsTop 12
		nsLeft 146
		nsBottom 43
		nsRight 180
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Several pretty trees stand by the lakeside, offering shade and beauty to wandering adventurers.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 161
		y 5
		noun '/ceder'
		nsTop -1
		nsLeft 84
		nsBottom 12
		nsRight 238
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Several pretty trees stand by the lakeside, offering shade and beauty to wandering adventurers.}
	)
)

(instance rock of NewFeature
	(properties
		x 19
		y 162
		noun '/boulder'
		nsTop 153
		nsBottom 172
		nsRight 38
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Rocks lie along the edge of the lake, adding to the quiet serenity of the area.}
	)
)

(instance bush1 of NewFeature
	(properties
		x 297
		y 61
		noun '/bush'
		nsTop 46
		nsLeft 274
		nsBottom 76
		nsRight 320
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Some pretty flowering bushes grow in a bunch near the lake's edge.}
	)
)

(instance bush2 of NewFeature
	(properties
		x 276
		y 103
		noun '/bush'
		nsTop 91
		nsLeft 256
		nsBottom 115
		nsRight 296
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Some pretty flowering bushes grow in a bunch near the lake's edge.}
	)
)
