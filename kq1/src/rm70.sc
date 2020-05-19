;;; Sierra Script 1.0 - (do not remove this comment)
(script# 70)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use RFeature)
(use Motion)
(use Game)
(use User)

(public
	rm70 0
)

(local
	i
	[leaf 12]
	[leafX 11] = [81 205 219 118 135 185 179 95 193 159 133]
	[leafY 11] = [46 83 117 108 110 43 34 146 151 48 136]
	[leafLoop 11] = [0 0 0 0 0 0 1 2 2 2 3]
	[leafCel 11] = [0 2 3 4 5 6 0 0 1 2]
	[leafPri 11] = [1 4 8 8 7 1 0 10 11 1 9]
)
(instance rm70 of Room
	(properties
		picture 70
		horizon 4
		north 71
	)
	
	(method (init)
		(LoadMany VIEW 271 8)
		(Load SOUND 6)
		(self south: roomWithBeanstalk)
		(self style:
		(switch prevRoomNum
			(north SCROLLUP)
			(south WIPEUP)
		))
		(super init:)
		((ScriptID 0 23) number: 6 loop: -1 play:)
		(switch prevRoomNum
			(north
				(ego posn: 137 8)
			)
			(else
				(ego posn: 142 178)
			)
		)
		(self setRegions: CLIMB)
		(Bset fLittleEgo)
		(ego init:)
		(NormalEgo)
		(ego
			looper: 0
			setCycle: Walk
			view: 8
			setStep: 2 2
			setPri: 11
		)
		(= i 0)
		(while (< i 11)
			([leaf i]
				view: 271
				x: [leafX i]
				y: [leafY (= [leaf i] (Clone RPicView))]
				loop: [leafLoop i]
				cel: [leafCel i]
				priority: [leafPri i]
				description: {leaf}
			)
			(addToPics
				add: [leaf i]
				eachElementDo: #signal 16384 93
				doit:
			)
			(++ i)
		)
		(if (Btst fGoatFollows)
			(Print 70 0)
			(Bclr fGoatFollows)
		)
		(stalk1 init:)
		(stalk2 init:)
		(cloud1 init:)
		(cloud2 init:)
		(cloud3 init:)
		(cloud4 init:)
		(cloud5 init:)
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
				(Print 70 1)
			)
			((Said 'look,check[<down][/grass]')
				(if (Btst fOfferedGoatCarrot)
					(if (Btst fGoatFollows)
						(Print 70 2)
					else
						(Print 70 3)
					)
				else
					(Print 70 4)
				)
			)
		)
	)
)

(instance stalk1 of NewFeature
	(properties
		x 134
		y 94
		noun '/beanstalk'
		nsTop -1
		nsLeft 101
		nsBottom 189
		nsRight 167
		description {stalk}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The beanstalk must be ten feet around!__It's incredibly tall...so tall that it vanishes in the clouds far, far above you.__There are plenty of footholds among the vines and offshoots of the beanstalk's stem.}
	)
)

(instance stalk2 of NewFeature
	(properties
		x 191
		y 90
		noun '/beanstalk'
		nsTop 43
		nsLeft 168
		nsBottom 137
		nsRight 214
		description {stalk}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The beanstalk must be ten feet around!__It's incredibly tall...so tall that it vanishes in the clouds far, far above you.__There are plenty of footholds among the vines and offshoots of the beanstalk's stem.}
	)
)

(instance cloud1 of NewFeature
	(properties
		x 45
		y 104
		noun '/cloud'
		nsTop 95
		nsBottom 114
		nsRight 91
		description {cloud}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {In the distance, and far above you, you can see heavy, dense clouds in streaks across the sky.__You think they're cumulus clouds, but it's been a long time since you went to Daventry High School.}
	)
)

(instance cloud2 of NewFeature
	(properties
		x 120
		y 75
		noun '/cloud'
		nsTop 66
		nsBottom 85
		nsRight 241
		description {cloud}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {In the distance, and far above you, you can see heavy, dense clouds in streaks across the sky.__You think they're cumulus clouds, but it's been a long time since you went to Daventry High School.}
	)
)

(instance cloud3 of NewFeature
	(properties
		x 95
		y 55
		noun '/cloud'
		nsTop 44
		nsLeft 38
		nsBottom 66
		nsRight 153
		description {cloud}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {In the distance, and far above you, you can see heavy, dense clouds in streaks across the sky.__You think they're cumulus clouds, but it's been a long time since you went to Daventry High School.}
	)
)

(instance cloud4 of NewFeature
	(properties
		x 259
		y 69
		noun '/cloud'
		nsTop 67
		nsLeft 225
		nsBottom 71
		nsRight 294
		description {cloud}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {In the distance, and far above you, you can see heavy, dense clouds in streaks across the sky.__You think they're cumulus clouds, but it's been a long time since you went to Daventry High School.}
	)
)

(instance cloud5 of NewFeature
	(properties
		x 288
		y 91
		noun '/cloud'
		nsTop 87
		nsLeft 256
		nsBottom 96
		nsRight 320
		description {cloud}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {In the distance, and far above you, you can see heavy, dense clouds in streaks across the sky.__You think they're cumulus clouds, but it's been a long time since you went to Daventry High School.}
	)
)
