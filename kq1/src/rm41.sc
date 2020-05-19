;;; Sierra Script 1.0 - (do not remove this comment)
(script# 41)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Block)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm41 0
)

(local
	talkCount
	local1
	paidToll
	i
	[ripple 2]
	[rippleX 2] = [281 58]
	[rippleY 2] = [162 159]
	[rippleLoop 2] = [3 1]
	[ripplePri 2] = [6 6]
)
(procedure (TrollSays)
	(cls)
	(Print &rest
		#title {Troll}
		#font 4
		#at 16 20
		#width 152
		#dispose
	)
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(instance rm41 of Room
	(properties
		picture 41
		horizon 54
		north 8
		east 42
		south 40
		west 48
	)
	
	(method (init)
		(LoadMany VIEW 241 120 124 123 126 168 44)
		(self
			style:
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
				(ego posn: (proc0_17 319 (ego x?) 201) (+ horizon 2))
			)
			(south
				(ego posn: (proc0_17 319 (ego x?) 73) 188)
			)
			(west
				(ego y: (proc0_17 198 (ego y?) (+ horizon 2)))
				(ego x: 3)
			)
			(east
				(ego
					posn:
						315
						(if (< (ego y?) 134)
							(proc0_17 122 (ego y?) 65)
						else
							(proc0_17 188 (ego y?) 178)
						)
				)
			)
			(else  (ego posn: 163 188))
		)
		(ego init:)
		(NormalEgo)
		(rRock init:)
		(rRock1 init:)
		(bridge init:)
		(bridge1 init:)
		(tree init:)
		(tree1 init:)
		(smallTree init:)
		(rocks init:)
		(trunk init:)
		(rock2 init:)
		(tree3 init:)
		(rock4 init:)
		(bush3 init:)
		(bush4 init:)
		(river init:)
		(= i 0)
		(while (< i 2)
			((= [ripple i] (Clone Ripple))
				view: 241
				x: [rippleX i]
				y: [rippleY i]
				setLoop: [rippleLoop i]
				setPri: [ripplePri i]
				ignoreActors: TRUE
				description: {ripple}
				init:
				stopUpd:
			)
			(if (>= howFast 1) ([ripple i] setCycle: Forward))
			(++ i)
		)
		(self setRegions: TROLL)
		(self setRegions: RIVER)
		(bridge41Block init:)
		(theMenace stopUpd:)
	)
	
	(method (doit &tmp nRoom)
		(if (and (== (ego view?) 54) (Btst fTrollBlocksBridge))
			((ScriptID 0 23) fade:)
		)
		(if (and (Btst fTrollBlocksBridge) (not (theMenace script?)))
			(cond 
				(
					(and
						(<= (ego distanceTo: theMenace) 45)
						(<= (ego x?) 212)
						(>= (ego x?) 196)
						(== (leftArm cel?) 0)
						(== (rightArm cel?) 0)
					)
					(if (>= (ego y?) 132) (theMenace setScript: pushBack))
				)
				(
					(or
						(>= (ego distanceTo: theMenace) 45)
						(and
							(<= (ego distanceTo: theMenace) 45)
							(>= (ego x?) 196)
							(<= (ego x?) 212)
						)
					)
					(if (!= (leftArm cel?) 0)
						(leftArm setScript: leftArmBeg)
					)
					(if (!= (rightArm cel?) 0)
						(rightArm setScript: rightArmBeg)
					)
				)
				(
					(and
						(>= (ego x?) 213)
						(<= (ego distanceTo: theMenace) 45)
					)
					(if (!= (rightArm cel?) (rightArm lastCel:))
						(rightArm setScript: rightArmEnd)
					)
					(if (== (leftArm cel?) (leftArm lastCel:))
						(leftArm setScript: leftArmBeg)
					)
				)
				(
					(and
						(<= (ego x?) 195)
						(<= (ego distanceTo: theMenace) 45)
					)
					(if (!= (leftArm cel?) (leftArm lastCel:))
						(leftArm setScript: leftArmEnd)
					)
					(if (== (rightArm cel?) (rightArm lastCel:))
						(rightArm setScript: rightArmBeg)
					)
				)
			)
		else
			0
		)
		(if (Btst fTrollPaid)
			(Bclr fTrollPaid)
			(= paidToll TRUE)
			(self setScript: TrollLeaves)
		)
		(if
			(and
				(!= prevRoomNum 40)
				(== (ego onControl: origin) 2048)
				(not (Btst fTrollBlocksBridge))
				(not (Btst fTrollPaid))
				(not (Btst fTrollDead))
				(not paidToll)
			)
			(theMenace setScript: TrollFirst)
		)
		(cond 
			(script (script doit:))
			((and (& (ego onControl: origin) $003c) (Btst fTrollBlocksBridge))
				(ego ignoreBlocks: bridge41Block)
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
			((Said 'look,check[<at,around][/room]')
				(Print 41 0)
			)
			(
				(or
					(Said 'talk,speak//troll')
					(Said 'talk,speak')
					(Said 'hello')
					(Said 'say/hello')
				)
				(if (Btst fTrollBlocksBridge)
					(curRoom setScript: trollTalk)
				else
					(Print 41 1)
				)
			)
		)
	)
)

(instance TrollLeaves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 23) fade:)
				(leftArm dispose:)
				(rightArm dispose:)
				(theMenace
					view: 120
					setStep: 6 4
					setLoop: -1
					setCycle: Walk
				)
				(theMenace setMotion: MoveTo 204 187 self)
			)
			(1
				(ego stopUpd:)
				(theMenace setMotion: MoveTo 347 187 self)
			)
			(2
				(ego ignoreBlocks: bridge41Block)
				(self cue:)
			)
			(3
				(theMenace dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance TrollFirst of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (Btst fGoatFollows)
					(theGoat stopUpd:)
					((ScriptID 0 23) number: 53 init: play:)
				else
					((ScriptID 0 23) number: 61 init: play:)
				)
				(Bset fTrollBlocksBridge)
				(if (not (>= howFast 1))
					(ego setMotion: 0 stopUpd:)
				)
				(theMenace show:)
				(ego observeBlocks: bridge41Block)
				(theMenace posn: 347 187 setMotion: MoveTo 204 187 self)
			)
			(1
				(theMenace setMotion: MoveTo 204 147 self)
			)
			(2
				(leftArm init: stopUpd:)
				(rightArm init: stopUpd:)
				(theMenace view: 123 setLoop: 0 setCel: 2 ignoreControl:)
				(self cue:)
			)
			(3
				(theMenace stopUpd:)
				(if (Btst fInvisible)
					(Print 41 2)
				else
					(Print 41 3)
				)
				(self cue:)
			)
			(4
				(HandsOn)
				(if
					(and
						(or (cast contains: theGoat) (Btst fGoatFollows))
						(!= roomWithDeadGoat curRoomNum)
					)
					(curRoom setScript: GoatButt)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance pushBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(leftArm hide:)
				(rightArm hide:)
				(theMenace
					view: 126
					setLoop: 2
					cel: 0
					setCycle: CycleTo 2 1 self
				)
			)
			(1
				(if (Btst fInvisible)
					(Print 41 4)
					(Bclr fInvisible)
					(ego put: iMagicRing)
				)
				(ego view: 44 setLoop: 2)
				(theMenace setCycle: EndLoop self)
			)
			(2
				(ego y: (- (ego y?) 3))
				(= cycles 1)
			)
			(3
				(ego setMotion: MoveTo (ego x?) (- (ego y?) 10))
				(= cycles 3)
			)
			(4
				(theMenace cycleSpeed: 1 setCycle: BegLoop self)
			)
			(5
				(HandsOn)
				(leftArm show: stopUpd:)
				(rightArm show: stopUpd:)
				(theMenace
					view: 123
					setLoop: 0
					setCel: 2
					cycleSpeed: 0
					ignoreControl:
					stopUpd:
				)
				(ego view: 0 loop: 2)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance leftArm of Prop
	(properties
		x 198
		y 109
		description {troll}
		view 123
		loop 5
		priority 11
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook (theMenace doVerb: verbLook))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rightArm of Prop
	(properties
		x 210
		y 109
		description {troll}
		view 123
		loop 4
		priority 11
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(theMenace doVerb: verbLook)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bridge41Block of Block
	(properties
		top 143
		left 175
		bottom 147
		right 240
	)
)

(instance GoatButt of Script
	(properties)
	
	(method (doit)
		(if
		(and (== state 3) (>= (theGoat y?) 140) (not local1))
			(= local1 1)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((theGoat looper?) viewNormal: 168)
				(theGoat
					view: 168
					setStep: 6 4
					setAvoider: 0
					setMotion: 0
					ignoreActors:
					illegalBits: 0
				)
				(Print 41 5)
				(ego setMotion: MoveTo (ego x?) (- (ego y?) 4) self)
			)
			(1
				(ego setMotion: MoveTo 240 117 self)
			)
			(2
				(ego setLoop: 2)
				(theGoat setMotion: MoveTo 204 120 self)
			)
			(3
				(theGoat setMotion: MoveTo 204 145 self)
			)
			(4
				((ScriptID 0 21) number: 38 init: play:)
				(self cue:)
			)
			(5
				(leftArm dispose:)
				(rightArm dispose:)
				(ego ignoreBlocks: bridge41Block)
				(theMenace
					view: 124
					setPri: 8
					illegalBits: 0
					setStep: 12 9
					setLoop: 0
					setMotion: MoveTo 193 132 self
				)
			)
			(6
				(theMenace setMotion: MoveTo 161 124 self)
			)
			(7
				(theMenace setMotion: MoveTo 128 141 self)
			)
			(8
				(theMenace setMotion: MoveTo 116 167 self)
			)
			(9
				((ScriptID 0 21) number: 14 play:)
				(splash
					view: 124
					setLoop: 2
					cycleSpeed: 2
					setPri: 8
					posn: 110 176
					init:
				)
				(theMenace setMotion: MoveTo 112 174 self)
			)
			(10
				(theMenace dispose:)
				(Bclr fTrollBlocksBridge)
				(splash setCycle: EndLoop self)
			)
			(11
				(splash dispose:)
				(SolvePuzzle fGoatKilledTroll 4)
				(Print 41 6)
				((ScriptID 0 23) fade:)
				((theGoat looper?) viewNormal: 165)
				(theGoat setStep: 3 2 setMotion: MoveTo 204 110 self)
			)
			(12
				(theGoat setMotion: MoveTo 350 110 self)
			)
			(13
				(ego illegalBits: cWHITE setLoop: -1)
				(HandsOn)
				(Bclr fGoatFollows)
				(Bset fTrollDead)
				(LoadMany FALSE FOLLOW AVOIDER)
				(theGoat dispose:)
				(self dispose:)
			)
		)
	)
)

(instance splash of Prop)

(instance leftArmBeg of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (leftArm setCycle: BegLoop self))
			(1
				(leftArm stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance rightArmBeg of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rightArm setCycle: BegLoop self)
			)
			(1
				(rightArm stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance leftArmEnd of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (leftArm setCycle: EndLoop self))
			(1
				(leftArm stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance rightArmEnd of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rightArm setCycle: EndLoop self)
			)
			(1
				(rightArm stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance Ripple of Prop
	(properties)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((Said 'look,check/boulder')
				(Print 41 7)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 41 7)
			)
		)
	)
)

(instance bridge of NewFeature
	(properties
		x 218
		y 143
		noun '/bridge'
		nsTop 128
		nsLeft 183
		nsBottom 159
		nsRight 253
		description {bridge}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The small, rickety wooden bridge is very old, but quite solid enough to support many times your weight.}
	)
)

(instance bridge1 of NewFeature
	(properties
		x 170
		y 152
		noun '/bridge'
		nsTop 143
		nsLeft 159
		nsBottom 161
		nsRight 181
		description {bridge}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The small, rickety wooden bridge is very old, but quite solid enough to support many times your weight.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 97
		y 31
		noun '/ceder'
		nsTop -1
		nsBottom 64
		nsRight 195
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Many mature, graceful trees line the river's banks.}
	)
)

(instance tree of NewFeature
	(properties
		x 282
		y 29
		noun '/ceder'
		nsTop -1
		nsLeft 265
		nsBottom 60
		nsRight 300
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Many mature, graceful trees line the river's banks.}
	)
)

(instance smallTree of NewFeature
	(properties
		x 64
		y 88
		noun '/ceder[<little]'
		nsTop 69
		nsLeft 43
		nsBottom 108
		nsRight 86
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Why, look! It's a tiny little baby tree!}
	)
)

(instance trunk of NewFeature
	(properties
		x 110
		y 89
		noun '/ceder'
		nsTop 64
		nsLeft 96
		nsBottom 114
		nsRight 125
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Many mature, graceful trees line the river's banks.}
	)
)

(instance rocks of NewFeature
	(properties
		x 145
		y 108
		noun '/boulder'
		nsTop 104
		nsLeft 127
		nsBottom 113
		nsRight 164
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A number of ordinary rocks sit in a pile here by the riverside.}
	)
)

(instance rock2 of NewFeature
	(properties
		x 75
		y 135
		noun '/boulder'
		nsTop 118
		nsBottom 153
		nsRight 150
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This cluster of rocks by the river's edge controls the flow of the river.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 36
		y 87
		noun '/ceder'
		nsTop 65
		nsLeft 29
		nsBottom 109
		nsRight 44
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Many mature, graceful trees line the river's banks.}
	)
)

(instance bush3 of NewFeature
	(properties
		x 302
		y 154
		noun '/bush'
		nsTop 140
		nsLeft 286
		nsBottom 169
		nsRight 319
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A large, healthy bush sits by the river.}
	)
)

(instance bush4 of NewFeature
	(properties
		x 310
		y 133
		noun '/bush'
		nsTop 126
		nsLeft 301
		nsBottom 140
		nsRight 319
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A large, healthy bush sits by the river.}
	)
)

(instance rock4 of NewFeature
	(properties
		x 270
		y 140
		noun '/boulder'
		nsTop 128
		nsLeft 257
		nsBottom 152
		nsRight 284
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This cluster of rocks by the river's edge controls the flow of the river.}
	)
)

(instance river of NewFeature
	(properties
		x 91
		y 158
		noun '/brook,brook'
		nsTop 151
		nsLeft 32
		nsBottom 166
		nsRight 151
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The river ripples past the rocks.}
	)
)

(instance rRock of NewFeature
	(properties
		x 50
		y 152
		noun '/boulder'
		nsTop 148
		nsLeft 42
		nsBottom 156
		nsRight 58
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A few rocks project above the surface of the wildly flowing river.}
	)
)

(instance rRock1 of NewFeature
	(properties
		x 274
		y 155
		noun '/boulder'
		nsTop 153
		nsLeft 266
		nsBottom 158
		nsRight 282
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A few rocks project above the surface of the wildly flowing river.}
	)
)

(instance trollTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(switch talkCount
					(0
						(TrollSays 41 8)
					)
					(1
						(TrollSays 41 9)
					)
					(else
						(TrollSays 41 10)
					)
				)
				(++ talkCount)
				(= register 1)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			(
				(or
					(== (event type?) mouseDown)
					(and
						(== (event type?) keyDown)
						(== (event message?) ENTER)
					)
				)
				(if modelessDialog
					(RedrawCast)
					(modelessDialog dispose:)
					(if seconds
						(= seconds 0)
						(= cycles 1)
					)
					(if register
						(= register 0)
						(= cycles 1)
					)
				)
				(event claimed: TRUE)
			)
		)
	)
)
