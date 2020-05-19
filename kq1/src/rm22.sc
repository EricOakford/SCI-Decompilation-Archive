;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm22 0
)

(local
	flyingWest
	local1
	birdChance
)
(procedure (CondorLeaves)
	(if (== local1 birdChance)
		(condor dispose:)
		((ScriptID 0 23) fade:)
	)
)

(instance rm22 of Room
	(properties
		picture 22
		horizon 57
		north 27
		east 21
		south 11
		west 23
	)
	
	(method (init)
		(LoadMany SOUND 10 55)
		(LoadMany VIEW 160 33)
		(self style:
				(switch prevRoomNum
					(north WIPEDOWN)
					(west WIPERIGHT)
					(east WIPELEFT)
					(south WIPEUP)
					(else  IRISOUT)
				)
		)
		(super init:)
		(switch prevRoomNum
			(north
				(ego posn: 278 (+ horizon 2))
			)
			(south
				(ego posn: (proc0_17 214 (ego x?) 0) 188)
			)
			(west
				(ego posn: 3 (proc0_17 189 (ego y?) 67))
			)
			(east
				(ego
					posn: 317 (proc0_17
						162
						(proc0_18 132 (ego y?) 124)
						(+ 2 horizon)
					)
				)
			)
			(else 
				(ego posn: 121 91 loop: 1)
			)
		)
		(ego init:)
		(NormalEgo)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(tree4 init:)
		(tree5 init:)
		(tree6 init:)
		(tree7 init:)
		(rock1 init:)
		(rock2 init:)
		(rock3 init:)
		(rock4 init:)
		(rock5 init:)
		(rock6 init:)
		(rock7 init:)
		(rock8 init:)
		(rock9 init:)
		(rock10 init:)
		(rock11 init:)
		(rock12 init:)
		(rock13 init:)
		(rock14 init:)
		(if
			(and
				(!= roomWithBeanstalk 22)
				(not (Btst fGotRideFromBird))
				(= birdChance (Random 0 4))
				(ego has: iMagicMirror)
				(ego has: iChest)
			)
			(Load VIEW (if (Btst fLittleEgo) 23 else 16))
			(Load VIEW (if (Btst fLittleEgo) 17 else 15))
			(Load VIEW 33)
			(condor
				illegalBits: 0
				ignoreActors: TRUE
				ignoreHorizon: TRUE
				init:
			)
		)
	)
	
	(method (doit &tmp temp0)
		(asm
			pushi    #contains
			pushi    1
			lofsa    condor
			push    
			lag      cast
			send     6
			bnt      code_02ee
			lsg      theCarrier
			ldi      1
			ne?     
			bnt      code_02ee
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #y
			pushi    0
			lofsa    condor
			send     4
			sub     
			push    
			ldi      80
			lt?     
			bnt      code_02ee
			pushi    1
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #x
			pushi    0
			lofsa    condor
			send     4
			sub     
			push    
			callk    Abs,  2
			push    
			ldi      5
			lt?     
			bnt      code_02ee
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			pushi    1
			pushi    0
			callb    Btst,  2
			bnt      code_02b7
			ldi      17
			jmp      code_02b9
code_02b7:
			ldi      15
code_02b9:
			eq?     
			bnt      code_02ee
			pushi    #cel
			pushi    0
			lag      ego
			send     4
			push    
			ldi      5
			ge?     
			bnt      code_02ee
			pushi    #number
			pushi    1
			pushi    55
			pushi    6
			pushi    1
			pushi    1
			pushi    42
			pushi    0
			pushi    2
			pushi    0
			pushi    23
			callk    ScriptID,  4
			send     16
			pushi    #setScript
			pushi    1
			lofsa    birdRide
			push    
			lag      curRoom
			send     6
			jmp      code_0390
code_02ee:
			pTos     script
			lofsa    birdRide
			eq?     
			bnt      code_0316
			pushi    #edgeHit
			pushi    0
			lag      ego
			send     4
			bnt      code_0316
			pushi    #dispose
			pushi    0
			lag      ego
			send     4
			pushi    #newRoom
			pushi    1
			pushi    80
			lag      curRoom
			send     6
			jmp      code_0390
code_0316:
			pToa     script
			bnt      code_0323
			pushi    #doit
			pushi    0
			send     4
			jmp      code_0390
code_0323:
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			push    
			ldi      2
			eq?     
			bnt      code_0342
			pushi    0
			callb    FadeBackgroundMusic,  0
			pushi    #newRoom
			pushi    1
			pushi    50
			self     6
			jmp      code_0390
code_0342:
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
			bnt      code_035d
			pToa     north
			jmp      code_037e
code_035d:
			dup     
			ldi      2
			eq?     
			bnt      code_0369
			pToa     east
			jmp      code_037e
code_0369:
			dup     
			ldi      3
			eq?     
			bnt      code_0375
			pToa     south
			jmp      code_037e
code_0375:
			dup     
			ldi      4
			eq?     
			bnt      code_037e
			pToa     west
code_037e:
			toss    
			sat      temp0
			bnt      code_0390
			pushi    0
			callb    FadeBackgroundMusic,  0
			pushi    #newRoom
			pushi    1
			lst      temp0
			self     6
code_0390:
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
			(
				(or
					(Said 'look,check/cave,doorway,doorway')
					(MouseClaimed event 112 40 130 59)
					(MouseClaimed event 106 60 115 86)
				)
				(if (ego inRect: 82 83 145 94)
					(Print 22 0)
				else
					(Print 22 1)
				)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,clearing]')
						(Print 22 2)
					)
					((Said '/bird,bird,bird')
						(if (and (<= 0 (condor x?)) (<= (condor x?) 319))
							(Print 22 3)
						else
							(Print 22 4)
						)
					)
				)
			)
			((Said 'get,take,take,capture/bird,bird,bird')
				(if (and (<= 0 (condor x?)) (<= (condor x?) 319))
					(Print 22 5)
				else
					(Print 22 6)
				)
			)
			((Said 'climb,scale/hill,boulder,boulder')
				(Print 22 7)
			)
			((Said 'kill/bird,bird,bird')
				(if (and (<= 0 (condor x?)) (<= (condor x?) 319))
					(Print 22 8)
				else
					(Print 22 4)
				)
			)
			((Said 'talk,speak/bird,bird,bird')
				(if (and (<= 0 (condor x?)) (<= (condor x?) 319))
					(Print 22 9)
				else
					(Print 22 4)
				)
			)
			((or (Said 'hello') (Said 'say/hello'))
				(cond 
					((and (<= 0 (condor x?)) (<= (condor x?) 319))
						(Print 22 9)
					)
					((Btst fGoatFollows)
						(Print 22 10)
					)
					(else
						(Print 22 11)
					)
				)
			)
		)
	)
)

(instance condor of Actor
	(properties
		x -10
		y -40
		yStep 5
		view 160
		xStep 8
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(ego inRect: 88 108 187 144)
				(not script)
				(!= roomWithBeanstalk 22)
				(< local1 birdChance)
			)
			(if (== local1 0)
				(++ local1)
				(++ birdChance)
				((ScriptID 0 23) number: 10 loop: -1 play:)
			)
			(self setScript:
					(if flyingWest
						(switch (Random 0 1)
							(0 (++ local1) swoopWest)
							(else  fakeWest)
						)
					else
						(switch (Random 0 1)
							(0 (++ local1) swoopEast)
							(else  fakeEast)
						)
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
			((Said 'look,check/bird,bird,bird')
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (and (<= 0 (condor x?)) (<= (condor x?) 319))
					(Print 22 3)
				else
					(Print 22 4)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance swoopEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(condor
					posn: (- (ego x?) 140) -10
					setPri: 8
					loop: 0
					cel: 1
					setCycle: 0
					setMotion: MoveTo (ego x?) (- (ego y?) 72) self
				)
			)
			(1
				(condor
					setCycle: Forward
					cycleSpeed: 2
					setMotion: MoveTo (+ (ego x?) 19) (- (ego y?) 74) self
				)
			)
			(2
				(condor
					cycleSpeed: 1
					setMotion: MoveTo (+ (ego x?) 115) (- (ego y?) 82) self
				)
				(User canControl: TRUE)
			)
			(3
				(condor setMotion: MoveTo 348 (- (ego y?) 137) self)
			)
			(4
				(= flyingWest 1)
				(CondorLeaves)
				(self dispose:)
			)
		)
	)
)

(instance swoopWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(condor
					posn: (+ (ego x?) 140) 6
					setPri: 8
					loop: 1
					cel: 1
					setCycle: 0
					setMotion: MoveTo (ego x?) (- (ego y?) 72) self
				)
			)
			(1
				(condor
					setCycle: Forward
					cycleSpeed: 2
					setMotion: MoveTo (- (ego x?) 15) (- (ego y?) 74) self
				)
			)
			(2
				(condor
					cycleSpeed: 1
					setMotion: MoveTo (- (ego x?) 115) (- (ego y?) 82) self
				)
				(User canControl: TRUE)
			)
			(3
				(condor setMotion: MoveTo -44 (- (ego y?) 137) self)
			)
			(4
				(= flyingWest FALSE)
				(CondorLeaves)
				(self dispose:)
			)
		)
	)
)

(instance fakeWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(condor
					posn: 302 -10
					setPri: 8
					loop: 0
					cel: 1
					setCycle: 0
					setMotion: MoveTo 177 39 self
				)
			)
			(1
				(condor setCycle: Forward setMotion: MoveTo -40 -21 self)
			)
			(2
				(= flyingWest FALSE)
				(CondorLeaves)
				(self dispose:)
			)
		)
	)
)

(instance fakeEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(condor
					posn: 52 -10
					setPri: 8
					loop: 0
					cel: 1
					setCycle: 0
					setMotion: MoveTo 141 53 self
				)
			)
			(1
				(condor setCycle: Forward setMotion: MoveTo 348 -10 self)
			)
			(2
				(= flyingWest TRUE)
				(CondorLeaves)
				(self dispose:)
			)
		)
	)
)

(instance birdRide of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(ego posn: (condor x?) (+ (condor y?) 60))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SolvePuzzle fGotRideFromBird 3)
				(= theCarrier carrierCONDOR)
				(HandsOff)
				(ego
					view: 33
					loop: (if flyingWest 1 else 0)
					setCycle: Forward
					setMotion: 0
					ignoreHorizon: 1
					ignoreActors: 1
					illegalBits: 0
					setPri: 7
				)
			)
		)
	)
)

(instance tree1 of NewFeature
	(properties
		x 271
		y 125
		noun '/ceder<little'
		nsTop 116
		nsLeft 242
		nsBottom 135
		nsRight 301
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the many small trees scattered around the lovely Daventry countryside.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 249
		y 148
		noun '/ceder<little'
		nsTop 143
		nsLeft 226
		nsBottom 154
		nsRight 273
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the many small trees scattered around the lovely Daventry countryside.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 37
		y 51
		noun '/ceder'
		nsTop 34
		nsLeft 23
		nsBottom 69
		nsRight 52
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees are somewhat sparse here at the foot of the rocky mountain.}
	)
)

(instance tree4 of NewFeature
	(properties
		x 156
		y 14
		noun '/ceder'
		nsLeft 132
		nsBottom 30
		nsRight 180
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees are somewhat sparse here at the foot of the rocky mountain.}
	)
)

(instance tree5 of NewFeature
	(properties
		x 308
		y 62
		noun '/ceder'
		nsLeft 297
		nsBottom 124
		nsRight 320
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees are somewhat sparse here at the foot of the rocky mountain.}
	)
)

(instance tree6 of NewFeature
	(properties
		x 251
		y 9
		noun '/ceder'
		nsLeft 212
		nsBottom 20
		nsRight 296
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees are somewhat sparse here at the foot of the rocky mountain.}
	)
)

(instance tree7 of NewFeature
	(properties
		x 266
		y 136
		noun '/ceder'
		nsTop 121
		nsLeft 224
		nsBottom 152
		nsRight 308
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees are somewhat sparse here at the foot of the rocky mountain.}
	)
)

(instance rock1 of NewFeature
	(properties
		x 56
		y 19
		noun '/hill'
		nsTop -1
		nsBottom 40
		nsRight 112
		description {rocky hill}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This jagged rocky hill reveals the entrance to a cave.}
	)
)

(instance rock2 of NewFeature
	(properties
		x 55
		y 50
		noun '/hill'
		nsTop 40
		nsBottom 60
		nsRight 111
		description {rocky hill}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This jagged rocky hill reveals the entrance to a cave.}
	)
)

(instance rock3 of NewFeature
	(properties
		x 72
		y 73
		noun '/hill'
		nsTop 61
		nsLeft 40
		nsBottom 86
		nsRight 104
		description {rocky hill}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This jagged rocky hill reveals the entrance to a cave.}
	)
)

(instance rock4 of NewFeature
	(properties
		x 125
		y 25
		noun '/hill'
		nsTop 12
		nsLeft 112
		nsBottom 39
		nsRight 138
		description {rocky hill}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This jagged rocky hill reveals the entrance to a cave.}
	)
)

(instance rock5 of NewFeature
	(properties
		x 152
		y 82
		noun '/hill'
		nsTop 60
		nsLeft 115
		nsBottom 104
		nsRight 189
		description {rocky hill}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This jagged rocky hill reveals the entrance to a cave.}
	)
)

(instance rock6 of NewFeature
	(properties
		x 162
		y 43
		noun '/hill'
		nsTop 28
		nsLeft 138
		nsBottom 59
		nsRight 187
		description {rocky hill}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This jagged rocky hill reveals the entrance to a cave.}
	)
)

(instance rock7 of NewFeature
	(properties
		x 214
		y 66
		noun '/hill'
		nsTop 57
		nsLeft 188
		nsBottom 76
		nsRight 240
		description {rocky hill}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This jagged rocky hill reveals the entrance to a cave.}
	)
)

(instance rock8 of NewFeature
	(properties
		x 196
		y 50
		noun '/hill'
		nsTop 44
		nsLeft 187
		nsBottom 57
		nsRight 206
		description {rocky hill}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This jagged rocky hill reveals the entrance to a cave.}
	)
)

(instance rock9 of NewFeature
	(properties
		x 114
		y 137
		noun '/boulder'
		nsTop 125
		nsLeft 78
		nsBottom 150
		nsRight 150
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Many large rocks and boulders are strewn about the area.}
	)
)

(instance rock10 of NewFeature
	(properties
		x 95
		y 163
		noun '/boulder'
		nsTop 150
		nsLeft 51
		nsBottom 177
		nsRight 140
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Many large rocks and boulders are strewn about the area.}
	)
)

(instance rock11 of NewFeature
	(properties
		x 72
		y 144
		noun '/boulder'
		nsTop 139
		nsLeft 66
		nsBottom 150
		nsRight 78
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Many large rocks and boulders are strewn about the area.}
	)
)

(instance rock12 of NewFeature
	(properties
		x 270
		y 181
		noun '/boulder'
		nsTop 174
		nsLeft 223
		nsBottom 189
		nsRight 318
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Many large rocks and boulders are strewn about the area.}
	)
)

(instance rock13 of NewFeature
	(properties
		x 298
		y 162
		noun '/boulder'
		nsTop 150
		nsLeft 277
		nsBottom 174
		nsRight 320
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Many large rocks and boulders are strewn about the area.}
	)
)

(instance rock14 of NewFeature
	(properties
		x 264
		y 170
		noun '/boulder'
		nsTop 166
		nsLeft 252
		nsBottom 174
		nsRight 277
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Many large rocks and boulders are strewn about the area.}
	)
)
