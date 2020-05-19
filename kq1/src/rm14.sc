;;; Sierra Script 1.0 - (do not remove this comment)
(script# 14)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm14 0
)

(instance rm14 of Room
	(properties
		picture 14
		horizon 65
		north 19
		east 15
		south 3
		west 13
	)
	
	(method (init)
		(LoadMany VIEW 19 18)
		(LoadMany SOUND 70 95 2)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(super init:)
		(if (Btst fFallFromTree)
			(Bclr fFallFromTree)
			(curRoom setScript: skyDiver)
		else
			(if (!= ((ScriptID 0 23) number?) 98)
				((ScriptID 0 23) number: 2 loop: -1 play:)
			)
			(switch prevRoomNum
				(north
					(ego posn: (proc0_17 319 (ego x?) 159) (+ horizon 2))
					(NormalEgo)
				)
				(south
					(ego posn: (proc0_17 319 (ego x?) 151) 188)
					(NormalEgo)
				)
				(west
					(ego posn: 3 (proc0_17 104 (ego y?) (+ horizon 2)))
					(NormalEgo)
				)
				(east (ego x: 317) (NormalEgo))
				(else 
					(curRoom setScript: downTree)
				)
			)
		)
		(ego init:)
		(farTree1 init:)
		(farTree2 init:)
		(farTree3 init:)
		(farTree init:)
		(tree5 init:)
		(tree4 init:)
		(tree3 init:)
		(tree2 init:)
		(tree1 init:)
		(tree init:)
		(rock init:)
		(rock1 init:)
		(rock2 init:)
		(rock3 init:)
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
				(if (Said '[<at,around][/room,ceder]')
					(Print 14 0)
				)
			)
			((Said 'climb,scale[/ceder,oak,branch]')
				(cond 
					((Btst fInvisible)
						(Print 14 1)
					)
					((Btst fGoatFollows)
						(Print 14 2)
					)
					((ego inRect: 54 142 98 172)
						(Print 14 3)
						(if (curRoom script?)
							(Print 14 4)
						else
							(curRoom setScript: climbTree)
						)
					)
					(else
						(Print 14 5)
					)
				)
			)
		)
	)
)

(instance climbTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreHorizon:
					setMotion: MoveTo 88 162 self
				)
			)
			(1
				(ego
					view: 19
					setPri: 11
					setStep: 1 3
					loop: 0
					cel: 0
					setCycle: Forward
					setPri: 12
					cycleSpeed: 1
					moveSpeed: 1
					setMotion: MoveTo 100 133 self
				)
			)
			(2
				(ego setMotion: MoveTo 149 57 self)
			)
			(3
				(ego setMotion: 0)
				(SolvePuzzle fClimbedTree 2)
				(HandsOn)
				(curRoom newRoom: 63)
			)
		)
	)
)

(instance downTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 19
					setPri: 12
					setStep: 1 3
					loop: 0
					cel: 0
					setCycle: Forward
					ignoreHorizon:
					illegalBits: 0
					cycleSpeed: 1
					moveSpeed: 1
					posn: 149 57
					setMotion: MoveTo 100 133 self
				)
			)
			(1
				(ego loop: 0 setCycle: Forward setMotion: MoveTo 88 162 self)
			)
			(2
				(HandsOn)
				(NormalEgo)
				(ego view: 0 loop: 3)
				(Bclr fInvisible)
				(self dispose:)
			)
		)
	)
)

(instance skyDiver of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					init:
					ignoreHorizon: 1
					view: 18
					loop: 0
					cel: 0
					posn: 182 -40
					yStep: 14
					illegalBits: 0
					setCycle: 0
					setMotion: MoveTo 182 133 self
				)
			)
			(1
				((ScriptID 0 23) number: 2 loop: -1 play:)
				((ScriptID 0 21) number: 95 loop: 1 play:)
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
				(= seconds 3)
			)
			(3
				(ego setLoop: 2 setCel: 0 posn: 181 132)
				(= cycles 2)
			)
			(4
				((ScriptID 0 21) number: 70 loop: -1 play:)
				(ego cycleSpeed: 2 setCycle: EndLoop self)
			)
			(5
				((ScriptID 0 21) stop:)
				(ego view: 0)
				(Bclr fInvisible)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance tree of NewFeature
	(properties
		x 159
		y 19
		noun '/ceder,oak'
		nsTop -1
		nsBottom 40
		nsRight 319
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is the largest oak tree you've ever seen.__Its trunk seems to be about ten feet around, and the thick, sturdy branches look like they could hold many times your weight.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 119
		y 50
		noun '/ceder,oak'
		nsTop 40
		nsLeft 79
		nsBottom 61
		nsRight 160
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is the largest oak tree you've ever seen.__Its trunk seems to be about ten feet around, and the thick, sturdy branches look like they could hold many times your weight.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 110
		y 70
		noun '/ceder,oak'
		nsTop 61
		nsLeft 68
		nsBottom 79
		nsRight 153
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is the largest oak tree you've ever seen.__Its trunk seems to be about ten feet around, and the thick, sturdy branches look like they could hold many times your weight.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 99
		y 86
		noun '/ceder,oak'
		nsTop 79
		nsLeft 59
		nsBottom 94
		nsRight 140
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is the largest oak tree you've ever seen.__Its trunk seems to be about ten feet around, and the thick, sturdy branches look like they could hold many times your weight.}
	)
)

(instance tree4 of NewFeature
	(properties
		x 80
		y 130
		noun '/ceder,oak'
		nsTop 95
		nsLeft 28
		nsBottom 145
		nsRight 133
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is the largest oak tree you've ever seen.__Its trunk seems to be about ten feet around, and the thick, sturdy branches look like they could hold many times your weight.}
	)
)

(instance tree5 of NewFeature
	(properties
		x 157
		y 154
		noun '/ceder,oak'
		nsTop 154
		nsLeft 117
		nsBottom 169
		nsRight 153
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is the largest oak tree you've ever seen.__Its trunk seems to be about ten feet around, and the thick, sturdy branches look like they could hold many times your weight.}
	)
)

(instance rock1 of NewFeature
	(properties
		x 14
		y 103
		noun '/boulder,root'
		nsTop 58
		nsBottom 149
		nsRight 28
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The roots of the colossal oak tree are entwined among the rocks.}
	)
)

(instance rock of NewFeature
	(properties
		x 43
		y 85
		noun '/boulder,root'
		nsTop 77
		nsLeft 28
		nsBottom 94
		nsRight 58
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The roots of the colossal oak tree are entwined among the rocks.}
	)
)

(instance farTree of NewFeature
	(properties
		x 259
		y 47
		noun '/ceder<birch'
		nsTop 41
		nsLeft 211
		nsBottom 54
		nsRight 308
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A small thicket of unimpressive white birch trees grows here.}
	)
)

(instance farTree1 of NewFeature
	(properties
		x 220
		y 69
		noun '/ceder<birch'
		nsTop 54
		nsLeft 215
		nsBottom 85
		nsRight 226
		description {farTree1}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A small thicket of unimpressive white birch trees grows here.}
	)
)

(instance farTree2 of NewFeature
	(properties
		x 261
		y 80
		noun '/ceder<birch'
		nsTop 54
		nsLeft 257
		nsBottom 107
		nsRight 266
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A small thicket of unimpressive white birch trees grows here.}
	)
)

(instance farTree3 of NewFeature
	(properties
		x 282
		y 85
		noun '/ceder<birch'
		nsTop 54
		nsLeft 277
		nsBottom 117
		nsRight 287
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A small thicket of unimpressive white birch trees grows here.}
	)
)

(instance rock2 of NewFeature
	(properties
		x 74
		y 158
		noun '/boulder'
		nsTop 149
		nsLeft 62
		nsBottom 167
		nsRight 87
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are plenty of rocks around the base of the enormous tree.}
	)
)

(instance rock3 of NewFeature
	(properties
		x 146
		y 151
		noun '/boulder'
		nsTop 148
		nsLeft 138
		nsBottom 155
		nsRight 154
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are plenty of rocks around the base of the enormous tree.}
	)
)
