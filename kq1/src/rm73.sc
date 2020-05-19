;;; Sierra Script 1.0 - (do not remove this comment)
(script# 73)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use Extra)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm73 0
)

(instance rm73 of Room
	(properties
		picture 73
		north 48
		south 74
	)
	
	(method (init)
		(self style:
			(switch prevRoomNum
				(north IRISOUT)
				(south WIPEUP)
			)
		)
		(LoadMany SOUND 17 70 31 95)
		(LoadMany VIEW 18 274)
		(super init:)
		(NormalEgo)
		(switch prevRoomNum
			(south
				(ego
					posn:
						(cond 
							((>= (ego x?) 267) 250)
							((<= (ego x?) 240) 157)
							(else 199)
						)
						188
					init:
				)
			)
			(else 
				(ego
					view: 18
					setLoop: 0
					posn: 182 -42
					ignoreControl: cWHITE
					init:
				)
				(self setScript: fallScript)
			)
		)
		(if (>= howFast 1)
			(d1 setPri: 14 init:)
			(d2 setPri: 15 init:)
			(d3 setPri: 14 init:)
			(d4 setPri: 13 init:)
			(d5 setPri: 14 init:)
			(d6 setPri: 13 init:)
		)
		(light1 init:)
		(sunbeam1 init:)
		(sunbeam2 init:)
		(sunbeam3 init:)
		(sunbeam4 init:)
		(sunbeam5 init:)
		(wall init:)
		(wall2 init:)
		(stalactite1 init:)
		(stalactite2 init:)
		(stalactite3 init:)
		(stalactite4 init:)
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
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'listen')
				(Print 73 0)
			)
			((Said 'smell,sniff/cave')
				(Print 73 1)
			)
			((Said 'drink,get,take/drink,water')
				(Print 73 2)
			)
			((Said 'light/torch,match')
				(Print 73 3)
			)
			((or (Said '/wall[<cave]>') (and (Said '/cave>') (Said '/side>')))
				(cond 
					((Said 'look,check')
						(Print 73 4)
					)
					((Said 'climb,scale,ascend')
						(Print 73 5)
					)
				)
			)
			((Said 'get,take,turn,switch,light/light,flashlight')
				(Print 73 6)
			)
			((Said '/boulder,mold>')
				(cond 
					((Said 'eat,consume')
						(Print 73 7)
					)
					((Said 'take')
						(Print 73 8)
					)
					((Said 'look,check')
						(Print 73 9)
					)
				)
			)
			((Said '/stalactite,stalactite>')
				(cond 
					((Said 'take,bend')
						(Print 73 10)
					)
					((Said 'look,check')
						(Print 73 11)
					)
				)
			)
			((Said 'look,check>')
				(cond 
					((Said '/drip')
						(Print 73 12)
					)
					((Said '/tunnel')
						(Print 73 13)
					)
					((or (Said '/hole') (Said '<up'))
						(Print 73 14)
					)
					((or (Said '/floor') (Said '<down'))
						(Print 73 15)
					)
					((Said '[<at,around][/room,cave]')
						(Print 73 16)
					)
				)
			)
			(
				(and
					(== (event type?) mouseDown)
					(& (event modifiers?) shiftDown)
					(== (OnControl PMAP (event x?) (event y?)) allEvents)
				)
				(Print 73 11)
			)
		)
	)
)

(instance d1 of Extra
	(properties
		x 128
		y 34
		description {drip}
		view 274
		loop 2
		cel 3
		cycleType 1
		minPause 30
		maxPause 40
		minCycles 1
		maxCycles 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 73 12)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance d2 of Extra
	(properties
		x 227
		y 40
		description {drip}
		view 274
		loop 2
		cel 1
		cycleType 1
		minPause 40
		maxPause 50
		minCycles 1
		maxCycles 1
	)
	
	(method (doVerb theVerb)
		(d1 doVerb: theVerb &rest)
	)
)

(instance d3 of Extra
	(properties
		x 254
		y 40
		description {drip}
		view 274
		loop 2
		cel 5
		cycleType 1
		minPause 50
		maxPause 60
		minCycles 1
		maxCycles 1
	)
	
	(method (doVerb theVerb)
		(d1 doVerb: theVerb &rest)
	)
)

(instance d4 of Extra
	(properties
		x 210
		y 53
		description {drip}
		view 274
		loop 2
		cel 4
		cycleType 1
		minPause 36
		maxPause 40
		minCycles 1
		maxCycles 1
	)
	
	(method (doVerb theVerb)
		(d1 doVerb: theVerb &rest)
	)
)

(instance d5 of Extra
	(properties
		x 289
		y 57
		description {drip}
		view 274
		loop 2
		cel 6
		cycleType 1
		minPause 40
		maxPause 48
		minCycles 1
		maxCycles 1
	)
	
	(method (doVerb theVerb)
		(d1 doVerb: theVerb &rest)
	)
)

(instance d6 of Extra
	(properties
		x 266
		y 53
		description {drip}
		view 274
		loop 2
		cel 1
		cycleType 1
		minPause 20
		minCycles 1
		maxCycles 1
	)
	
	(method (doVerb theVerb)
		(d1 doVerb: theVerb &rest)
	)
)

(instance fallScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 21) number: 17 loop: 1 init: play:)
				(ego yStep: 11 setMotion: MoveTo 182 133 self)
			)
			(1
				((ScriptID 0 21) number: 95 play:)
				(ego
					setLoop: 1
					cycleSpeed: 1
					posn: 181 132
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(ShakeScreen 6)
				(ego setCycle: EndLoop)
				(= seconds 5)
			)
			(3
				(ego setLoop: 2 setCel: 0 posn: 181 132)
				(= cycles 2)
			)
			(4
				((ScriptID 0 21) number: 70 play:)
				(ego cycleSpeed: 2 setCycle: EndLoop self)
			)
			(5
				(ego observeControl: cWHITE)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance light1 of NewFeature
	(properties
		x 181
		y 134
		noun '/circle,light,sunlight'
		nsTop 131
		nsLeft 152
		nsBottom 138
		nsRight 211
		description {light}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You can see a circle of light on the floor of the cave, shining down from the hole above.}
	)
)

(instance sunbeam1 of NewFeature
	(properties
		x 182
		y 72
		noun '/beam,sunbeam'
		nsTop 14
		nsLeft 166
		nsBottom 131
		nsRight 198
		description {sunbeam}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A shaft of sunlight from above pierces the gloom of the cave.}
	)
)

(instance sunbeam2 of NewFeature
	(properties
		x 201
		y 93
		noun '/beam,sunbeam'
		nsTop 55
		nsLeft 197
		nsBottom 131
		nsRight 205
		description {sunbeam}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A shaft of sunlight from above pierces the gloom of the cave.}
	)
)

(instance sunbeam3 of NewFeature
	(properties
		x 161
		y 93
		noun '/beam,sunbeam'
		nsTop 55
		nsLeft 158
		nsBottom 131
		nsRight 165
		description {sunbeam}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A shaft of sunlight from above pierces the gloom of the cave.}
	)
)

(instance sunbeam4 of NewFeature
	(properties
		x 208
		y 117
		noun '/beam,sunbeam'
		nsTop 104
		nsLeft 205
		nsBottom 131
		nsRight 211
		description {sunbeam}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A shaft of sunlight from above pierces the gloom of the cave.}
	)
)

(instance sunbeam5 of NewFeature
	(properties
		x 154
		y 122
		noun '/beam,sunbeam'
		nsTop 113
		nsLeft 152
		nsBottom 131
		nsRight 157
		description {sunbeam}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A shaft of sunlight from above pierces the gloom of the cave.}
	)
)

(instance wall of NewFeature
	(properties
		x 265
		y 107
		noun '/wall'
		nsTop 62
		nsLeft 211
		nsBottom 153
		nsRight 320
		description {wall}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The walls of this cave are damp and moldy.}
	)
)

(instance wall2 of NewFeature
	(properties
		x 128
		y 86
		noun '/wall'
		nsTop 59
		nsLeft 105
		nsBottom 114
		nsRight 152
		description {wall2}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The walls of this cave are damp and moldy.}
	)
)

(instance stalactite1 of NewFeature
	(properties
		x 131
		y 23
		noun '/stalactite'
		nsTop 16
		nsLeft 120
		nsBottom 31
		nsRight 143
		description {stalactite}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These rock formations were slowly created over thousands of years.}
	)
)

(instance stalactite2 of NewFeature
	(properties
		x 138
		y 42
		noun '/stalactite'
		nsTop 32
		nsLeft 135
		nsBottom 52
		nsRight 141
		description {stalactite2}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These rock formations were slowly created over thousands of years.}
	)
)

(instance stalactite3 of NewFeature
	(properties
		x 254
		y 31
		noun '/stalactite'
		nsTop 22
		nsLeft 249
		nsBottom 41
		nsRight 259
		description {stalactite}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These rock formations were slowly created over thousands of years.}
	)
)

(instance stalactite4 of NewFeature
	(properties
		x 289
		y 40
		noun '/stalactite'
		nsTop 23
		nsLeft 284
		nsBottom 57
		nsRight 294
		description {stalactite}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These rock formations were slowly created over thousands of years.}
	)
)
