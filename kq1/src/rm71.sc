;;; Sierra Script 1.0 - (do not remove this comment)
(script# 71)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm71 0
)

(instance rm71 of Room
	(properties
		picture 71
		horizon 4
		north 72
		south 70
	)
	
	(method (init)
		(Load VIEW 8)
		(Load SOUND 7)
		(self
			style:
			(switch prevRoomNum
				(north SCROLLUP)
				(south SCROLLDOWN)
			)
		)
		(super init:)
		((ScriptID 0 23) number: 7 loop: -1 play:)
		(switch prevRoomNum
			(south
				(ego y: 193)
			)
			(else
				(ego posn: 159 8)
			)
		)
		(self setRegions: CLIMB)
		(ego init:)
		(NormalEgo)
		(ego
			looper: 0
			setCycle: Walk
			view: 8
			setStep: 2 2
			setPri: 11
		)
		(if (== prevRoomNum south)
			(self setScript: climbIntoView)
		)
		(stalk1 init:)
		(stalk2 init:)
		(stalk3 init:)
		(cloud1 init:)
		(cloud2 init:)
		(cloud3 init:)
		(cloud4 init:)
	)
	
	(method (doit &tmp nRoom)
		(if (== script (ScriptID 609 1))
			(script doit:)
		)
		(cond 
			((and script (!= script (ScriptID 609 1)))
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
				(if (== nRoom north)
					(SolvePuzzle fClimbedToCloudland 2)
				)
				(self newRoom: nRoom)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check[<at,around][/room,beanstalk]')
				(Print 71 0)
			)
			((Said 'look,check[<down][/grass,goat]')
				(Print 71 1)
			)
		)
	)
)

(instance climbIntoView of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 137 178 self)
			)
			(1
				(HandsOn)
				(curRoom setScript: (ScriptID 609 1))
				(self dispose:)
			)
		)
	)
)

(instance stalk1 of NewFeature
	(properties
		x 135
		y 94
		noun '/beanstalk'
		nsLeft 112
		nsBottom 189
		nsRight 158
		description {stalk}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You can see neither the top nor the bottom of the gigantic beanstalk you're clinging to.__The ropey green vines easily hold your weight, and the massive main stem winds gently upward.}
	)
)

(instance stalk2 of NewFeature
	(properties
		x 94
		y 68
		noun '/beanstalk'
		nsTop 23
		nsLeft 77
		nsBottom 114
		nsRight 111
		description {stalk}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You can see neither the top nor the bottom of the gigantic beanstalk you're clinging to.__The ropey green vines easily hold your weight, and the massive main stem winds gently upward.}
	)
)

(instance stalk3 of NewFeature
	(properties
		x 167
		y 22
		noun '/beanstalk'
		nsLeft 160
		nsBottom 45
		nsRight 175
		description {stalk}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You can see neither the top nor the bottom of the gigantic beanstalk you're clinging to.__The ropey green vines easily hold your weight, and the massive main stem winds gently upward.}
	)
)

(instance cloud1 of NewFeature
	(properties
		x 160
		y 127
		noun '/cloud'
		nsTop 91
		nsBottom 164
		nsRight 320
		description {cloud}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are clouds all around you.__You can see neither the land below you nor the top of the beanstalk above you.__In fact, there's little to see up here but clouds and the occasional bird.}
	)
)

(instance cloud2 of NewFeature
	(properties
		x 244
		y 46
		noun '/cloud'
		nsTop 33
		nsLeft 169
		nsBottom 60
		nsRight 320
		description {cloud}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are clouds all around you.__You can see neither the land below you nor the top of the beanstalk above you.__In fact, there's little to see up here but clouds and the occasional bird.}
	)
)

(instance cloud3 of NewFeature
	(properties
		x 35
		y 24
		noun '/cloud'
		nsTop 17
		nsBottom 32
		nsRight 70
		description {cloud}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are clouds all around you.__You can see neither the land below you nor the top of the beanstalk above you.__In fact, there's little to see up here but clouds and the occasional bird.}
	)
)

(instance cloud4 of NewFeature
	(properties
		x 160
		y 6
		noun '/cloud'
		nsBottom 13
		nsRight 320
		description {cloud}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are clouds all around you.__You can see neither the land below you nor the top of the beanstalk above you.__In fact, there's little to see up here but clouds and the occasional bird.}
	)
)
