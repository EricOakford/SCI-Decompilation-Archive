;;; Sierra Script 1.0 - (do not remove this comment)
(script# 17)
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
	rm17 0
)

(local
	[local0 3]
)

(define cliffCtrl $0016) ;(| cBLUE cGREEN cRED)

(instance rm17 of Room
	(properties
		picture 17
		horizon 57
		north 32
		east 24
		south 16
		west 18
	)
	
	(method (init)
		(LoadMany VIEW 54 13 14)
		(LoadMany SOUND 17 14)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(super init:)
		(ego init:)
		(switch prevRoomNum
			(north
				(ego posn: (proc0_17 309 (ego x?) 240) (+ horizon 2))
			)
			(south (ego y: 188))
			(west
				(if (not egoInWater)
					(ego posn: 3 (proc0_17 188 (ego y?) 182))
				else
					(= swimTimer 1000)
					(ego
						illegalBits: 128
						view: 13
						setStep: 3 1
						posn: 3 (proc0_17 186 (ego y?) 104)
						setCycle: Forward
						setPri: 4
						cycleSpeed: 0
						moveSpeed: 0
					)
				)
			)
			(else 
				(ego posn: 317 (proc0_17 189 (ego y?) (+ horizon 2)))
			)
		)
		(if (not egoInWater)
			(NormalEgo)
		)
		(cliff1 init:)
		(cliff2 init:)
		(cliff3 init:)
		(cliff4 init:)
		(lake1 init:)
		(lake2 init:)
		(lake3 init:)
		(lake4 init:)
		(lake5 init:)
		(rock1 init:)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(tree4 init:)
		(tree5 init:)
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
			((== (ego view?) 13)
				(if (and swimTimer (not (-- swimTimer)))
					(curRoom setScript: drowning)
				)
				(if (>= (ego y?) 159)
					(ego setPri: 1)
				else
					(ego setPri: -1)
				)
			)
			(
			(and (& (ego onControl: origin) cliffCtrl) (not egoInWater))
				(self setScript: offCliff)
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
			((Said 'swim/')
				(cond 
					((== egoInWater 6)
						(Print 17 0)
					)
					(egoInWater
						(Print 17 1)
					)
					(else
						(Print 17 2)
					)
				)
			)
			((Said 'dive')
				(if egoInWater
					(Print 17 3)
				else
					(event claimed: FALSE)
				)
			)
			((Said 'fill/bucket')
				(cond 
					((not egoInWater)
						(Print 17 4)
					)
					((ego has: iWaterBucket)
						(if (Btst fWaterInBucket)
							(Print 17 5)
						else
							(if (== egoInWater 4)
								(Print 17 6)
							else
								(Print 17 7)
							)
							(BucketState TRUE)
						)
					)
					(else
						(Print 17 8)
					)
				)
			)
			((Said 'drink,get,take/drink,water')
				(Print 17 9)
			)
			((Said 'climb,scale,move,pull/boulder')
				(Print 17 10)
			)
		)
	)
)

(instance offCliff of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== state 0)
			(ego setStep: -1 (+ (ego yStep?) 4))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 21) number: 17 loop: 1 init: play:)
				(ego
					view: 54
					illegalBits: 0
					setCycle: 0
					cel: 0
					ignoreActors:
					ignoreHorizon:
					setPri:
					(switch (ego onControl: origin)
						(cGREEN 2)
						(else  3)
					)
					setStep: 4 7
				)
				(switch (ego onControl: origin)
					(cRED
						(ego
							yStep: 1
							setLoop: 3
							setMotion: MoveTo (ego x?) 240 self
						)
					)
					(else 
						(ego
							setLoop: 1
							setMotion: MoveTo (- (ego x?) 114) (+ (ego y?) 180) self
						)
					)
				)
				(if (cast contains: theGoat)
					(Face theGoat ego)
					(theGoat setMotion: 0)
				)
			)
			(1
				((ScriptID 0 21) number: 14 loop: 1 play:)
				(= seconds 2)
			)
			(2
				(EgoDead
					{Your attempt at cliffdiving was a smashing failure.}
				)
			)
		)
	)
)

(instance cliff1 of NewFeature
	(properties
		x 203
		y 51
		noun '/cliff'
		nsTop 39
		nsLeft 194
		nsBottom 63
		nsRight 213
		description {cliff}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Treacherous cliffs surround a picturesque mountain lake.}
	)
)

(instance cliff2 of NewFeature
	(properties
		x 205
		y 76
		noun '/cliff'
		nsTop 63
		nsLeft 199
		nsBottom 89
		nsRight 212
		description {cliff}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Treacherous cliffs surround a picturesque mountain lake.}
	)
)

(instance cliff3 of NewFeature
	(properties
		x 224
		y 84
		noun '/cliff'
		nsTop 70
		nsLeft 212
		nsBottom 99
		nsRight 236
		description {cliff}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Treacherous cliffs surround a picturesque mountain lake.}
	)
)

(instance cliff4 of NewFeature
	(properties
		x 218
		y 136
		noun '/cliff'
		nsTop 122
		nsLeft 198
		nsBottom 151
		nsRight 239
		description {cliff}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Treacherous cliffs surround a picturesque mountain lake.}
	)
)

(instance lake1 of NewFeature
	(properties
		x 156
		y 52
		noun '[<at,around][/room,lake,water,cliff]'
		nsTop 39
		nsLeft 118
		nsBottom 66
		nsRight 194
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The clear mountain lake is surrounded by steep cliffs.}
	)
)

(instance lake2 of NewFeature
	(properties
		x 121
		y 74
		noun '[<at,around][/room,lake,water,cliff]'
		nsTop 65
		nsLeft 46
		nsBottom 83
		nsRight 197
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The clear mountain lake is surrounded by steep cliffs.}
	)
)

(instance lake3 of NewFeature
	(properties
		x 115
		y 121
		noun '[<at,around][/room,lake,water,cliff]'
		nsTop 83
		nsLeft 32
		nsBottom 159
		nsRight 198
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The clear mountain lake is surrounded by steep cliffs.}
	)
)

(instance lake4 of NewFeature
	(properties
		x 15
		y 135
		noun '[<at,around][/room,lake,water,cliff]'
		nsTop 95
		nsBottom 176
		nsRight 31
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The clear mountain lake is surrounded by steep cliffs.}
	)
)

(instance lake5 of NewFeature
	(properties
		x 94
		y 167
		noun '[<at,around][/room,lake,water,cliff]'
		nsTop 159
		nsLeft 31
		nsBottom 176
		nsRight 157
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The clear mountain lake is surrounded by steep cliffs.}
	)
)

(instance rock1 of NewFeature
	(properties
		x 264
		y 32
		noun '/boulder'
		nsTop 20
		nsLeft 234
		nsBottom 45
		nsRight 295
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A large, jagged boulder sits at the top of the cliff.}
	)
)

(instance drowning of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE)
				(ego
					setMotion: 0
					setLoop: 0
					view: 14
					cel: 5
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(ego cel: 5 setCycle: EndLoop self)
			)
			(2
				(ego cel: 5 setCycle: EndLoop self)
			)
			(3
				(ego hide:)
				(= seconds 4)
			)
			(4
				(if (not swimTimer)
					(EgoDead
						{After swimming for a long time, your strength ebbs and your arms and legs grow weary.__As your life swims before your eyes, you decide to...}
					)
				else
					(EgoDead
						{You splash around for awhile, but unfortunately that won't keep your head above water.__As you go down for the third time, a sense of peace washes over you...}
					)
				)
			)
		)
	)
)

(instance tree1 of NewFeature
	(properties
		x 50
		y 13
		noun '/ceder'
		nsTop -1
		nsBottom 28
		nsRight 101
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A spreading tree is silhouetted against the sun.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 230
		y 8
		noun '/ceder'
		nsTop -1
		nsLeft 179
		nsBottom 17
		nsRight 281
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A spreading tree is silhouetted against the sun.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 171
		y 20
		noun '/ceder'
		nsTop 14
		nsLeft 164
		nsBottom 26
		nsRight 179
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A spreading tree is silhouetted against the sun.}
	)
)

(instance tree4 of NewFeature
	(properties
		x 224
		y 27
		noun '/ceder'
		nsTop 17
		nsLeft 214
		nsBottom 37
		nsRight 234
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A spreading tree is silhouetted against the sun.}
	)
)

(instance tree5 of NewFeature
	(properties
		x 310
		y 14
		noun '/ceder'
		nsTop -1
		nsLeft 300
		nsBottom 30
		nsRight 320
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A spreading tree is silhouetted against the sun.}
	)
)
