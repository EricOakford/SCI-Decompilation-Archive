;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use StopWalk)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm4 0
)

(local
	local0
)
(instance rm4 of Room
	(properties
		picture 4
		north 13
		east 3
		south 45
		west 5
	)
	
	(method (init)
		(if (>= howFast 1)
			(Load VIEW 302)
		)
		(Load VIEW 1)
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
				(ego posn: (proc0_17 220 (ego x?) 92) 78)
				(Bset fClimbingHill)
			)
			(south
				(ego posn: (proc0_17 237 (ego x?) 3) 188)
			)
			(west
				(cond 
					(egoInWater (ego y: (proc0_17 158 (ego y?) 100)))
					((< (ego y?) 131) (ego y: 84))
				)
				(ego x: 4)
			)
			(east
				(ego posn: 317 125)
			)
			(else
				(ego posn: 315 145)
			)
		)
		(ego init:)
		(if (not egoInWater)
			(NormalEgo)
		)
		(if (== prevRoomNum north)
			(ego setPri: 3)
		)
		(self setRegions: WATER)
		(lake1 init:)
		(lake2 init:)
		(lake3 init:)
		(sand1 init:)
		(sand2 init:)
		(sand3 init:)
		(sand4 init:)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(if (>= howFast 1)
			(fish init:)
		)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(
				(and
					(OneOf (ego onControl: origin) cBLUE cGREEN cCYAN cRED)
					(!= (ego illegalBits?) -24576)
				)
				(ego illegalBits: -24576)
			)
			(
				(and
					(not (OneOf (ego onControl: origin) cBLUE cGREEN cCYAN cRED))
					(== (ego illegalBits?) -24576)
				)
				(ego illegalBits: cWHITE)
			)
		)
		(cond 
			((and script (not egoInWater)) (script doit:))
			((and (not (ego script?)) (& (ego onControl: origin) cBROWN))
				(ego setScript: climbBackHill)
			)
			((and (& (ego onControl: origin) cLMAGENTA) (== newRoomNum curRoomNum))
				(curRoom setScript: slipIntoWater)
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
				(Bclr fClimbingHill)
				(ego illegalBits: cWHITE priority: -1)
				(self newRoom: nRoom)
			)
			(
				(or
					(and
						(Btst fClimbingHill)
						(>= (ego x?) 157)
						(>= (ego y?) 95)
						(== (ego script?) 0)
					)
					(and
						(& (ego onControl: origin) cLBLUE)
						(not (Btst fClimbingHill))
						(== (ego script?) 0)
					)
				)
				(ego setScript: climbHill)
			)
			(
				(and
					(not (& (ego onControl: origin) cYELLOW))
					(< (ego y?) 108)
					(> (ego x?) 119)
					(== (ego script?) 0)
					(not (Btst fClimbingHill))
					(!= (ego priority?) 3)
					(not (& (ego onControl: origin) cRED))
				)
				(= local0 0)
				(Bset fClimbingHill)
				(ego setPri: 3)
			)
			(
				(and
					(& (ego onControl: origin) cYELLOW)
					(not (Btst fClimbingHill))
					(== (ego script?) 0)
					(!= (ego priority?) 7)
				)
				(= local0 0)
				(ego setPri: 7)
			)
			(
				(and
					(not
						(if
							(and
								(not (& (ego onControl: origin) cYELLOW))
								(< (ego y?) 108)
							)
							(> (ego x?) 119)
						)
					)
					(not (& (ego onControl: origin) cYELLOW))
					(!= local0 1)
				)
				(= local0 1)
				(Bclr fClimbingHill)
				(ego setPri: -1)
			)
			(script (script doit:))
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
					((Said '[<at,around][/room,grass]')
						(Print 4 0)
					)
					((Said '<down')
						(if (& (ego onControl: origin) cGREY)
							(Print 4 1)
						else
							(Print 4 2)
						)
					)
					((Said '/water,bank,bank')
						(Print 4 3)
					)
					((Said '[<at]/beach')
						(Print 4 4)
					)
					((Said '/boulder')
						(Print 4 5)
					)
					((Said '/pebble')
						(cond 
							((ego has: iPebbles)
								(event claimed: FALSE)
							)
							((& (ego onControl: origin) cGREY)
								(Print 4 1)
							)
							(else
								(Print 4 6)
							)
						)
					)
				)
			)
			((Said 'get,take/boulder')
				(Print 4 7)
			)
			((Said 'get,take/pebble')
				(cond 
					((not (& (ego onControl: origin) cGREY))
						(Print 4 8)
					)
					((== numPebbles 5)
						(Print 4 9)
					)
					((Btst fInvisible)
						(Print 4 10)
					)
					(else
						(ego setScript: getPebbles)
					)
				)
			)
		)
	)
)

(instance getPebbles of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 1 setMotion: 0 setCycle: EndLoop self)
			)
			(1
				(SolvePuzzle fGotPebbles 1)
				((ScriptID 0 21) number: 105 loop: 1 init: play:)
				(switch numPebbles
					(0 (ego get: iPebbles)
						(Print 4 11)
					)
					(4
						(Print 4 12)
					)
					(else
						(Print 4 13)
					)
				)
				(= numPebbles 6)
				(PebbleCount)
				(= cycles 4)
			)
			(2 (ego setCycle: BegLoop self))
			(3
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance climbHill of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not (Btst 44))
					(ego
						illegalBits: 0
						setLoop: 3
						setPri: 3
						setMotion: MoveTo (ego x?) 94 self
					)
				else
					(ego
						illegalBits: 0
						setLoop: 2
						setMotion: MoveTo 216 84 self
					)
				)
			)
			(1
				(if (Btst fClimbingHill)
					(Bclr fClimbingHill)
					(ego setPri: 7 setMotion: MoveTo 217 89 self)
				else
					(Bset fClimbingHill)
					(= cycles 1)
				)
			)
			(2
				(HandsOn)
				(ego setLoop: -1 illegalBits: cWHITE)
				(self dispose:)
			)
		)
	)
)

(instance climbBackHill of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					setLoop: 3
					setPri: 1
					setMotion: MoveTo (ego x?) (+ (ego y?) 5) self
				)
			)
			(1
				(NormalEgo)
				(HandsOn)
				(curRoom newRoom: (curRoom north?))
				(self dispose:)
			)
		)
	)
)

(instance slipIntoWater of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (== state 0)
			(cond 
				((& (ego onControl: origin) cBLUE)
					(self changeState: 1)
				)
				((< (ego yStep?) 9)
					(ego setStep: -1 (+ (ego yStep?) 2))
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setCycle: 0
					ignoreActors:
					setMotion: MoveTo (- (ego x?) 200) (+ (ego y?) 140)
					setStep: 6 4
				)
				(if (Btst fInvisible)
					(Print 4 14)
					(theGame changeScore: -3)
				)
			)
			(1
				(ego
					posn: (- (ego x?) 2) (ego y?)
					setCycle: StopWalk 2
					setStep: 3 2
					ignoreActors: 0
					setMotion: 0
				)
				(HandsOn)
				(self dispose:)
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
					posn: (Random 10 125) (Random 110 140)
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
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
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
					(Print 4 15)
				else
					(Print 4 16)
				)
			)
			(verbGet
				(Print 4 17)
			)
		)
	)
)

(instance lake1 of NewFeature
	(properties
		x 81
		y 104
		noun '[<at,around][/room,lake,water,lake]'
		nsTop 91
		nsBottom 118
		nsRight 163
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a serene mountain lake with a small beach of pebbles on its edge.}
	)
)

(instance lake2 of NewFeature
	(properties
		x 85
		y 139
		noun '[<at,around][/room,lake,water,lake]'
		nsTop 118
		nsBottom 161
		nsRight 171
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a serene mountain lake with a small beach of pebbles on its edge.}
	)
)

(instance lake3 of NewFeature
	(properties
		x 190
		y 135
		noun '[<at,around][/room,lake,water,lake]'
		nsTop 122
		nsLeft 170
		nsBottom 149
		nsRight 211
		description {lake3}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a serene mountain lake with a small beach of pebbles on its edge.}
	)
)

(instance sand1 of NewFeature
	(properties
		x 125
		y 169
		noun '/beach,sand'
		nsTop 166
		nsLeft 56
		nsBottom 172
		nsRight 195
		description {sand}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {On the beach are some smooth pebbles.}
	)
)

(instance sand2 of NewFeature
	(properties
		x 176
		y 164
		noun '/beach,sand'
		nsTop 162
		nsLeft 155
		nsBottom 166
		nsRight 198
		description {sand}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {On the beach are some smooth pebbles.}
	)
)

(instance sand3 of NewFeature
	(properties
		x 188
		y 158
		noun '/beach,sand'
		nsTop 154
		nsLeft 172
		nsBottom 162
		nsRight 204
		description {sand}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {On the beach are some smooth pebbles.}
	)
)

(instance sand4 of NewFeature
	(properties
		x 150
		y 176
		noun '/beach,sand'
		nsTop 172
		nsLeft 118
		nsBottom 180
		nsRight 183
		description {sand}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {On the beach are some smooth pebbles.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 159
		y 34
		noun '/ceder'
		nsTop -1
		nsBottom 69
		nsRight 319
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are many lush trees surrounding the lake.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 178
		y 82
		noun '/ceder'
		nsTop 69
		nsLeft 160
		nsBottom 95
		nsRight 196
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are many lush trees surrounding the lake.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 289
		y 129
		noun '/ceder'
		nsTop 69
		nsLeft 259
		nsBottom 189
		nsRight 320
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are many lush trees surrounding the lake.}
	)
)
