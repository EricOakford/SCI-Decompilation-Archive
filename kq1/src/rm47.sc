;;; Sierra Script 1.0 - (do not remove this comment)
(script# 47)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use RFeature)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm47 0
)

(instance rm47 of Room
	(properties
		picture 47
		east 48
		south 34
		west 46
		picAngle 50
	)
	
	(method (init)
		(LoadMany VIEW 247 242 1 370)
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
				(ego posn: 142 (+ horizon 2))
			)
			(south
				(if (< (ego x?) 230)
					(ego posn: (proc0_17 83 (- (ego x?) 100) 3) 187)
				else
					(ego posn: 283 187)
				)
			)
			(west
				(ego posn: 3 (proc0_17 188 (ego y?) 79))
			)
			(east
				(ego posn: 315 (proc0_17 188 (ego y?) 100))
			)
			(else  (ego posn: 3 137))
		)
		(ego init:)
		(NormalEgo)
		(if (not (Btst fGotMushroom))
			(mushroom init:)
		)
		(addToPics add: castle eachElementDo: #init doit:)
		(self setRegions: RIVER)
		(rock init: stopUpd:)
		(water0 init: stopUpd:)
		(water1 init: stopUpd:)
		(if (>= howFast 1)
			(water0 setCycle: Forward)
			(water1 setCycle: Forward)
			(water2 init: setCycle: Forward)
			(water3 init: setCycle: Forward)
			(water4 init: setCycle: Forward)
		)
		(cliff1 init:)
		(cliff2 init:)
		(cliff3 init:)
		(cliff4 init:)
		(bush1 init:)
		(bush2 init:)
		(river1 init:)
		(river2 init:)
		(river3 init:)
		(bush3 init:)
		(bush4 init:)
		(tree1 init:)
		(tree2 init:)
		(stump init:)
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
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,clearing]')
						(cond 
							((Btst fGotMushroom)
								(Print 47 0)
							)
							((<= (ego x?) 140)
								(Print 47 1)
							)
							(else
								(Print 47 2)
							)
						)
					)
					((or (Said '/grass') (Said '<down'))
						(if
							(and
								(not (Btst fGotMushroom))
								(<= (ego distanceTo: mushroom) 30)
							)
							(Print 47 3)
						else
							(Print 47 4)
						)
					)
					((Said '<across[/brook,water,brook]')
						(if (> (ego x?) 140)
							(Print 47 5)
						else
							(Print 47 6)
						)
					)
					((Said '/boulder')
						(Print 47 7)
					)
					((Said '/rapid')
						(Print 47 8)
					)
				)
			)
			((Said 'smell,sniff/mushroom,aroma')
				(cond 
					((Btst fGotMushroom)
						(if (ego has: iMushroom)
							(Print 47 9)
						else
							(DontHave)
						)
					)
					((> (ego distanceTo: mushroom) 30)
						(Print 47 10)
					)
					(else
						(Print 47 11)
					)
				)
			)
			((Said 'get,take,pick/mushroom')
				(cond 
					((Btst fGotMushroom)
						(Print 47 12)
					)
					((<= (ego x?) 140)
						(Print 47 13)
					)
					((> (ego distanceTo: mushroom) 20)
						(CantReach)
					)
					((Btst fInvisible)
						(Print 47 14)
					)
					(else
						(curRoom setScript: getMush)
					)
				)
			)
		)
	)
)

(instance castle of RPicView
	(properties
		x 121
		y 58
		longRangeDist 500
		view 370
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'look,check/castle')
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 47 15)
			)
		)
	)
)

(instance mushroom of View
	(properties
		x 200
		y 127
		view 247
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'look,check/mushroom')
				(if (and (Btst fGotMushroom) (ego has: iMushroom))
					(event claimed: FALSE)
				else
					(self doVerb: verbLook)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(cond 
					((Btst fGotMushroom)
						(Print 47 16)
					)
					((<= (ego x?) 140)
						(Print 47 13)
					)
					((> (ego distanceTo: mushroom) 30)
						(Print 47 17)
					)
					(else
						(Print 47 18)
					)
				)
			)
		)
	)
)

(instance getMush of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego mushroom)
				(= cycles 2)
			)
			(1
				(ego view: 1 setMotion: 0 setCycle: EndLoop self)
			)
			(2
				((ScriptID 0 21) number: 105 loop: 1 init: play:)
				(SolvePuzzle fGotMushroom 1)
				(ego get: iMushroom)
				(mushroom dispose:)
				(= cycles 4)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance rock of View
	(properties
		x 159
		y 169
		view 242
		loop 6
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'look,check/boulder')
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 47 19)
			)
		)
	)
)

(instance water0 of Prop
	(properties
		x 162
		y 188
		description {rock in river}
		view 242
		loop 5
		priority 6
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 47 20)
			)
		)
	)
)

(instance water1 of Prop
	(properties
		x 105
		y 109
		description {waterfall}
		view 247
		cel 1
		priority 6
		signal fixPriOn
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'look,check/waterfall')
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 47 21)
			)
		)
	)
)

(instance water2 of Prop
	(properties
		x 114
		y 79
		description {waterfall}
		view 247
		loop 1
		cel 2
		priority 6
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 47 21)
			)
		)
	)
)

(instance water3 of Prop
	(properties
		x 116
		y 120
		description {waterfall}
		view 247
		loop 3
		cel 1
		priority 6
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 47 21)
			)
		)
	)
)

(instance water4 of Prop
	(properties
		x 103
		y 128
		description {waterfall}
		view 247
		loop 4
		priority 6
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 47 21)
			)
		)
	)
)

(instance cliff1 of NewFeature
	(properties
		x 191
		y 39
		noun '/cliff'
		nsTop 23
		nsLeft 164
		nsBottom 55
		nsRight 219
		description {cliff}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There's an impressive cliff here, overlooking the castle and the small waterfall.}
	)
)

(instance cliff2 of NewFeature
	(properties
		x 234
		y 36
		noun '/cliff'
		nsTop 12
		nsLeft 217
		nsBottom 60
		nsRight 251
		description {cliff}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There's an impressive cliff here, overlooking the castle and the small waterfall.}
	)
)

(instance cliff3 of NewFeature
	(properties
		x 264
		y 26
		noun '/cliff'
		nsTop 10
		nsLeft 251
		nsBottom 43
		nsRight 277
		description {cliff}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There's an impressive cliff here, overlooking the castle and the small waterfall.}
	)
)

(instance cliff4 of NewFeature
	(properties
		x 155
		y 52
		noun '/cliff'
		nsTop 43
		nsLeft 146
		nsBottom 62
		nsRight 164
		description {cliff}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There's an impressive cliff here, overlooking the castle and the small waterfall.}
	)
)

(instance bush1 of NewFeature
	(properties
		x 14
		y 44
		noun '/bush,bury'
		nsTop 22
		nsBottom 67
		nsRight 28
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is just one of the many leafy bushes growing throughout the countryside.}
	)
)

(instance bush2 of NewFeature
	(properties
		x 218
		y 84
		noun '/bush,bury'
		nsTop 58
		nsLeft 144
		nsBottom 110
		nsRight 292
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is just one of the many leafy bushes growing throughout the countryside.}
	)
)

(instance bush3 of NewFeature
	(properties
		x 298
		y 62
		noun '/bush,bury'
		nsTop 27
		nsLeft 277
		nsBottom 97
		nsRight 320
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is just one of the many leafy bushes growing throughout the countryside.}
	)
)

(instance bush4 of NewFeature
	(properties
		x 266
		y 51
		noun '/bush,bury'
		nsTop 45
		nsLeft 255
		nsBottom 58
		nsRight 277
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is just one of the many leafy bushes growing throughout the countryside.}
	)
)

(instance river1 of NewFeature
	(properties
		x 119
		y 137
		noun '/brook,water,brook'
		nsTop 121
		nsLeft 82
		nsBottom 154
		nsRight 156
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a wild and perilous raging river, filled with jagged boulders and treacherous rapids.}
	)
)

(instance river2 of NewFeature
	(properties
		x 149
		y 171
		noun '/brook,water,brook'
		nsTop 154
		nsLeft 99
		nsBottom 189
		nsRight 199
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a wild and perilous raging river, filled with jagged boulders and treacherous rapids.}
	)
)

(instance river3 of NewFeature
	(properties
		x 165
		y 147
		noun '/brook,water,brook'
		nsTop 140
		nsLeft 155
		nsBottom 154
		nsRight 175
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a wild and perilous raging river, filled with jagged boulders and treacherous rapids.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 61
		y 14
		noun '/ceder'
		nsBottom 28
		nsRight 122
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {An old tree hangs over the river's rapids.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 33
		y 44
		noun '/ceder'
		nsTop 29
		nsLeft 21
		nsBottom 60
		nsRight 45
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {An old tree hangs over the river's rapids.}
	)
)

(instance stump of NewFeature
	(properties
		x 53
		y 76
		noun '/stump'
		nsTop 61
		nsLeft 26
		nsBottom 91
		nsRight 80
		description {stump}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This extremely old tree stump has nearly splintered completely away.}
	)
)
