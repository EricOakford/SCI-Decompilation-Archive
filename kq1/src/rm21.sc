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
	
	(method (doit &tmp temp0)
		(asm
			pushi    #contains
			pushi    1
			lofsa    witch
			push    
			lag      cast
			send     6
			bnt      code_028b
			lsg      theCarrier
			ldi      2
			ne?     
			bnt      code_028b
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			pushi    1
			pushi    0
			callb    Btst,  2
			bnt      code_0233
			ldi      23
			jmp      code_0235
code_0233:
			ldi      16
code_0235:
			ne?     
			bnt      code_028b
			pushi    1
			pushi    1
			callb    Btst,  2
			not     
			bnt      code_028b
			pushi    30
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #y
			pushi    0
			lofsa    witch
			send     4
			sub     
			lt?     
			bnt      code_028b
			pprev   
			ldi      54
			lt?     
			bnt      code_028b
			pushi    1
			pushi    #x
			pushi    0
			lofsa    witch
			send     4
			push    
			pushi    #x
			pushi    0
			lag      ego
			send     4
			sub     
			push    
			callk    Abs,  2
			push    
			ldi      4
			lt?     
			bnt      code_028b
			pushi    #setScript
			pushi    1
			lofsa    witchRide
			push    
			lag      curRoom
			send     6
			jmp      code_0423
code_028b:
			pTos     script
			lofsa    witchRide
			eq?     
			bnt      code_02b6
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      65504
			lt?     
			bnt      code_02b6
			pushi    #dispose
			pushi    0
			lag      ego
			send     4
			pushi    #newRoom
			pushi    1
			pushi    80
			lag      curRoom
			send     6
			jmp      code_0423
code_02b6:
			pToa     script
			bnt      code_02c3
			pushi    #doit
			pushi    0
			send     4
			jmp      code_0423
code_02c3:
			pushi    #edgeHit
			pushi    0
			pushi    #alterEgo
			pushi    0
			class    User
			send     4
			send     4
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_02de
			pToa     north
			jmp      code_02ff
code_02de:
			dup     
			ldi      2
			eq?     
			bnt      code_02ea
			pToa     east
			jmp      code_02ff
code_02ea:
			dup     
			ldi      3
			eq?     
			bnt      code_02f6
			pToa     south
			jmp      code_02ff
code_02f6:
			dup     
			ldi      4
			eq?     
			bnt      code_02ff
			pToa     west
code_02ff:
			toss    
			sat      temp0
			bnt      code_0314
			pushi    0
			callb    FadeBackgroundMusic,  0
			pushi    #newRoom
			pushi    1
			lst      temp0
			self     6
			jmp      code_0423
code_0314:
			lal      witchTimer
			not     
			bnt      code_032c
			pushi    #has
			pushi    1
			pushi    16
			lag      ego
			send     6
			bnt      code_032c
			ldi      0
			jmp      code_0423
code_032c:
			pushi    #contains
			pushi    1
			lofsa    witch
			push    
			lag      cast
			send     6
			not     
			bnt      code_03a9
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			push    
			ldi      4096
			eq?     
			bnt      code_03a9
			lal      witchTimer
			bnt      code_03a9
			pushi    1
			pushi    1
			callb    Btst,  2
			not     
			bnt      code_03a9
			lal      witchIsHere
			not     
			bnt      code_037a
			ldi      1
			sal      witchIsHere
			pushi    #number
			pushi    1
			pushi    36
			pushi    6
			pushi    1
			pushi    65535
			pushi    42
			pushi    0
			pushi    2
			pushi    0
			pushi    23
			callk    ScriptID,  4
			send     16
code_037a:
			pushi    #init
			pushi    0
			pushi    18
			pushi    1
			pushi    0
			pushi    242
			pushi    1
			pushi    1
			pushi    231
			pushi    1
			pushi    1
			pushi    250
			pushi    2
			pushi    10
			pushi    6
			pushi    131
			pushi    1
			class    Forward
			push    
			pushi    127
			pushi    1
			lofsa    longSwoop
			push    
			lofsa    witch
			send     42
			jmp      code_0423
code_03a9:
			pushi    #contains
			pushi    1
			lofsa    witch
			push    
			lag      cast
			send     6
			not     
			bnt      code_0423
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			push    
			ldi      8192
			eq?     
			bnt      code_0423
			lal      witchTimer
			bnt      code_0423
			pushi    1
			pushi    1
			callb    Btst,  2
			not     
			bnt      code_0423
			lal      witchIsHere
			not     
			bnt      code_03f7
			ldi      1
			sal      witchIsHere
			pushi    #number
			pushi    1
			pushi    36
			pushi    6
			pushi    1
			pushi    65535
			pushi    42
			pushi    0
			pushi    2
			pushi    0
			pushi    23
			callk    ScriptID,  4
			send     16
code_03f7:
			pushi    #init
			pushi    0
			pushi    18
			pushi    1
			pushi    0
			pushi    242
			pushi    1
			pushi    1
			pushi    231
			pushi    1
			pushi    1
			pushi    250
			pushi    2
			pushi    10
			pushi    6
			pushi    131
			pushi    1
			class    Forward
			push    
			pushi    127
			pushi    1
			lofsa    shortSwoop
			push    
			lofsa    witch
			send     42
code_0423:
			ret     
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
