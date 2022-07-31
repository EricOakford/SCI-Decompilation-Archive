;;; Sierra Script 1.0 - (do not remove this comment)
(script# 21)
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
	rm21 0
)

(local
	witchTimer
	witchIsHere
)
(instance rm21 of Room
	(properties
		picture 21
		horizon 57
		north 28
		east 20
		south 12
		west 22
	)
	
	(method (init)
		(Load VIEW 117)
		(if (not (ego has: iMagicShield))
			(Load SOUND 36)
		)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(if
			(and
				(not (Btst fKilledWitch))
				(not haloTimer)
				(not (ego has: iMagicShield))
				(= witchTimer (Random 0 4))
			)
			(Load VIEW (if (Btst fLittleEgo) 23 else 16))
		)
		(super init:)
		(switch prevRoomNum
			(north
				(ego
					posn: (proc0_17 202 (proc0_18 73 (ego x?) 43) 13) (+ horizon 2)
				)
			)
			(south
				(ego posn: (proc0_17 319 (ego x?) 120) 188)
			)
			(west
				(ego
					posn: 3 (proc0_17
						175
						(proc0_18 123 (ego y?) 111)
						(+ 2 horizon)
					)
				)
			)
			(east
				(ego
					posn: 317 (proc0_18 95 (proc0_18 114 (ego y?) 105) 77)
				)
			)
			(else
				(ego posn: 3 137)
			)
		)
		(ego init:)
		(NormalEgo)
		(tree init:)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(tree4 init:)
		(tree5 init:)
		(tree6 init:)
		(tree7 init:)
		(tree8 init:)
		(tree9 init:)
		(tree10 init:)
		(tree11 init:)
		(rock init:)
		(rock1 init:)
		(rock2 init:)
		(bush init:)
	)
	(method (doit &tmp edge)
		(cond 
			(
				(and
					(cast contains: witch)
					(!= theCarrier carrierWITCH)
					(!= (ego view?) (if (Btst fLittleEgo) 23 else 16))
					(not (Btst fInvisible))
					(< 30 (- (ego y?) (witch y?)))
					(< (- (ego y?) (witch y?)) 54)
					(< (Abs (- (witch x?) (ego x?))) 4)
				)
				(curRoom setScript: witchRide)
			)
			((and (== script witchRide) (< (ego x?) -32))
				(ego dispose:)
				(curRoom newRoom: 80)
			)
			(script (script doit:))
			(
				(= edge
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(FadeBackgroundMusic)
				(self newRoom: edge)
			)
			((and (not witchTimer) (ego has: iMagicShield)) 0)
			(
				(and
					(not (cast contains: witch))
					(== (ego onControl: origin) cLRED)
					witchTimer
					(not (Btst fInvisible))
				)
				(if (not witchIsHere)
					(= witchIsHere TRUE)
					((ScriptID 0 23) number: 36 loop: -1 play:)
				)
				(witch
					init:
					illegalBits: 0
					ignoreHorizon: TRUE
					ignoreActors: TRUE
					setStep: 10 6
					setCycle: Forward
					setScript: longSwoop
				)
			)
			(
				(and
					(not (cast contains: witch))
					(== (ego onControl: origin) cLMAGENTA)
					witchTimer
					(not (Btst fInvisible))
				)
				(if (not witchIsHere)
					(= witchIsHere TRUE)
					((ScriptID 0 23) number: 36 loop: -1 play:)
				)
				(witch
					init:
					illegalBits: 0
					ignoreHorizon: TRUE
					ignoreActors: TRUE
					setStep: 10 6
					setCycle: Forward
					setScript: shortSwoop
				)
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
			((Said '/witch')
				(Print 21 0)
			)
			((and (Said 'look,check>') (Said '[<at,around][/room,ceder]'))
				(Print 21 1)
			)
		)
	)
)

(instance witch of Actor
	(properties
		view 117
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event))
			((or (Said 'look,check/witch') (MousedOn self event shiftDown))
				(Print 21 2)
			)
			((Said 'kill/witch')
				(if (cast contains: witch)
					(Print 21 3)
				else
					(Print 21 4)
				)
			)
			((or (Said 'talk,speak') (Said '<hello') (Said 'say/hello'))
				(Print 21 5)
			)
			((Said 'get,take,capture,eat,consume,bite/witch')
				(cond 
					((Btst fWitchHasEgo)
						(Print 21 6)
					)
					((cast contains: witch)
						(Print 21 7)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			((Said 'jump')
				(cond 
					((Btst fWitchHasEgo)
						(Print 21 8)
					)
					((cast contains: witch)
						(Print 21 9)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			((Said 'attack,kick/witch')
				(Print 21 10)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 21 2)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance longSwoop of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(Print 21 11)
				(witch
					setPri: 5
					setLoop: 0
					posn: 28 1
					setMotion: MoveTo 114 66 self
				)
			)
			(1
				(if (Btst fInvisible)
					(Print 21 12)
				)
				(witch setMotion: MoveTo 183 58 self)
			)
			(2
				(witch setMotion: MoveTo 256 39 self)
			)
			(3
				(witch setMotion: MoveTo 349 68 self)
			)
			(4
				(witch
					setLoop: 1
					setPri: 14
					setMotion: MoveTo 282 106 self
				)
			)
			(5
				(witch setMotion: MoveTo (ego x?) (- (ego y?) 40) self)
			)
			(6
				(if (and (> (ego x?) 160) (> (ego y?) 140))
					(witch setMotion: MoveTo 121 126 self)
				else
					(self cue:)
				)
			)
			(7
				(witch setMotion: MoveTo -40 64 self)
			)
			(8
				(-- witchTimer)
				(witch dispose:)
				(if (not witchTimer)
					((ScriptID 0 23) fade:)
					(= witchIsHere FALSE)
				)
				(self dispose:)
			)
		)
	)
)

(instance shortSwoop of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(Print 21 11)
				(witch
					setPri: 9
					setLoop: 1
					posn: 320 12
					setMotion: MoveTo 207 86 self
				)
			)
			(1
				(if (Btst fInvisible)
					(Print 21 12)
				)
				(witch setMotion: MoveTo (ego x?) (- (ego y?) 34) self)
			)
			(2
				(witch setMotion: MoveTo -40 49 self)
			)
			(3
				(-- witchTimer)
				(witch dispose:)
				(if (not witchTimer)
					((ScriptID 0 23) fade:)
					(= witchIsHere FALSE)
				)
				(self dispose:)
			)
		)
	)
)

(instance witchRide of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(ego posn: (- (witch x?) 15) (+ (witch y?) 22))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= theCarrier carrierWITCH)
				((ScriptID 0 23) loop: 1 fade:)
				(Print 21 13)
				(HandsOff)
				(ego
					view: 117
					loop: 3
					setCycle: Forward
					setMotion: 0
					ignoreHorizon: 1
					ignoreActors: 1
					illegalBits: 0
					setPri: (witch priority?)
				)
			)
		)
	)
)

(instance rock2 of NewFeature
	(properties
		x 40
		y 175
		noun '/boulder'
		nsTop 161
		nsBottom 189
		nsRight 81
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of many uninteresting rocks that pepper the forest floor.}
	)
)

(instance rock1 of NewFeature
	(properties
		x 17
		y 154
		noun '/boulder'
		nsTop 147
		nsBottom 161
		nsRight 34
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of many uninteresting rocks that pepper the forest floor.}
	)
)

(instance rock of NewFeature
	(properties
		x 95
		y 183
		noun '/boulder'
		nsTop 177
		nsLeft 81
		nsBottom 189
		nsRight 110
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of many uninteresting rocks that pepper the forest floor.}
	)
)

(instance tree11 of NewFeature
	(properties
		x 227
		y 144
		noun '/ceder'
		nsTop 135
		nsLeft 157
		nsBottom 153
		nsRight 297
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These trees are grotesquely bent and twisted, and the branches seem like they're reaching out to grab you.}
	)
)

(instance tree10 of NewFeature
	(properties
		x 235
		y 156
		noun '/ceder'
		nsTop 153
		nsLeft 185
		nsBottom 159
		nsRight 286
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These trees are grotesquely bent and twisted, and the branches seem like they're reaching out to grab you.}
	)
)

(instance tree9 of NewFeature
	(properties
		x 222
		y 66
		noun '/ceder'
		nsTop -1
		nsLeft 198
		nsBottom 134
		nsRight 246
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These trees are grotesquely bent and twisted, and the branches seem like they're reaching out to grab you.}
	)
)

(instance tree8 of NewFeature
	(properties
		x 153
		y 41
		noun '/ceder'
		nsTop -1
		nsLeft 109
		nsBottom 84
		nsRight 197
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These trees are grotesquely bent and twisted, and the branches seem like they're reaching out to grab you.}
	)
)

(instance tree7 of NewFeature
	(properties
		x 269
		y 19
		noun '/ceder'
		nsTop -1
		nsLeft 245
		nsBottom 40
		nsRight 293
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These trees are grotesquely bent and twisted, and the branches seem like they're reaching out to grab you.}
	)
)

(instance tree6 of NewFeature
	(properties
		x 257
		y 59
		noun '/ceder'
		nsTop 45
		nsLeft 246
		nsBottom 74
		nsRight 269
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These trees are grotesquely bent and twisted, and the branches seem like they're reaching out to grab you.}
	)
)

(instance tree5 of NewFeature
	(properties
		x 252
		y 85
		noun '/ceder'
		nsTop 74
		nsLeft 247
		nsBottom 96
		nsRight 258
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These trees are grotesquely bent and twisted, and the branches seem like they're reaching out to grab you.}
	)
)

(instance tree4 of NewFeature
	(properties
		x 10
		y 59
		noun '/ceder'
		nsTop -1
		nsBottom 119
		nsRight 20
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These trees are grotesquely bent and twisted, and the branches seem like they're reaching out to grab you.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 27
		y 112
		noun '/ceder'
		nsTop 105
		nsLeft 20
		nsBottom 119
		nsRight 34
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These trees are grotesquely bent and twisted, and the branches seem like they're reaching out to grab you.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 64
		y 18
		noun '/ceder'
		nsTop -1
		nsLeft 20
		nsBottom 37
		nsRight 108
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These trees are grotesquely bent and twisted, and the branches seem like they're reaching out to grab you.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 65
		y 56
		noun '/ceder'
		nsTop 37
		nsLeft 40
		nsBottom 76
		nsRight 91
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These trees are grotesquely bent and twisted, and the branches seem like they're reaching out to grab you.}
	)
)

(instance tree of NewFeature
	(properties
		x 63
		y 72
		noun '/ceder'
		nsTop 66
		nsLeft 26
		nsBottom 78
		nsRight 100
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These trees are grotesquely bent and twisted, and the branches seem like they're reaching out to grab you.}
	)
)

(instance bush of NewFeature
	(properties
		x 298
		y 79
		noun '/bush'
		nsTop 40
		nsLeft 278
		nsBottom 119
		nsRight 319
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Bushes and vines grow in a jumble throughout this part of the forest.}
	)
)
