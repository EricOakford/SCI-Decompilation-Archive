;;; Sierra Script 1.0 - (do not remove this comment)
(script# 39)
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
	rm39 0
)

(local
	local0
	local1
	paidToll
	talkCount
)
(procedure (TrollSays)
	(cls)
	(Print &rest
		#title {Troll}
		#font 4
		#at 16 120
		#width 280
		#dispose
	)
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(instance rm39 of Room
	(properties
		picture 39
		horizon 60
		north 42
		east 38
		south 26
		west 40
	)
	
	(method (init)
		(LoadMany VIEW 239 120 124 123 126 168 44)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(super init:)
		(pinetree init:)
		(pinetree2 init:)
		(treesOnBank init:)
		(treesOnBank2 init:)
		(bridge init:)
		(water init:)
		(water2 init:)
		(pinetree3 init:)
		(tree init:)
		(tree2 init:)
		(switch prevRoomNum
			(north
				(ego
					posn: (proc0_17 319 (proc0_18 217 (ego x?) 160) 157) 62
				)
			)
			(south
				(if (< (ego x?) 122)
					(ego posn: 9 188)
				else
					(ego posn: (proc0_17 319 (ego x?) 215) 188)
				)
			)
			(west (ego x: 3))
			(east
				(ego
					posn: 317 (proc0_17
						188
						(proc0_18 141 (ego y?) 129)
						(+ horizon 2)
					)
				)
			)
			(else  (ego posn: 3 137))
		)
		(ego init:)
		(NormalEgo)
		(self setRegions: TROLL)
		(self setRegions: RIVER)
		(waterRock setPri: 7 ignoreActors: init: stopUpd:)
		(if (>= howFast 1)
			(waterRock setCycle: Forward)
		)
		(theMenace illegalBits: 0 stopUpd:)
	)
	
	(method (doit &tmp nRoom)
		(if (and (== (ego view?) 54) (Btst fTrollBlocksBridge))
			((ScriptID 0 23) fade:)
		)
		(if
			(and
				(Btst fTrollBlocksBridge)
				(not (theMenace script?))
				(not (& (ego onControl: origin) $003c))
				(<= (ego distanceTo: theMenace) 40)
			)
			(theMenace setScript: pushBack)
		)
		(if (Btst fTrollPaid)
			(Bclr fTrollPaid)
			(= paidToll TRUE)
			(self setScript: TrollLeaves)
		)
		(if
			(and
				(!= prevRoomNum 40)
				(== (ego onControl: origin) cLCYAN)
				(not (Btst fTrollBlocksBridge))
				(not (Btst fTrollPaid))
				(not (Btst fTrollDead))
				(not paidToll)
			)
			(theMenace setScript: TrollFirst)
		)
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
			((super handleEvent: event) (return))
			((Said 'look,check[<at,around][/room]')
				(Print 39 0)
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
					(Print 39 1)
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
				(trollFace dispose:)
				((ScriptID 0 23) fade:)
				(ego stopUpd:)
				(theMenace
					view: 120
					setStep: 6 4
					setLoop: -1
					setCycle: Walk
				)
				(theMenace setMotion: MoveTo 20 106 self)
			)
			(1
				(ego stopUpd:)
				(theMenace setMotion: MoveTo 20 145 self)
			)
			(2
				(theMenace setMotion: MoveTo -25 145 self)
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
				((ScriptID 0 23) dispose:)
				(self cue:)
			)
			(1
				(HandsOff)
				(if (Btst fGoatFollows)
					(theGoat stopUpd:)
					((ScriptID 0 23) number: 53 play:)
				else
					((ScriptID 0 23) number: 61 play:)
				)
				(Bset fTrollBlocksBridge)
				(if (not (>= howFast 1))
					(ego setMotion: 0 stopUpd:)
				)
				(theMenace show:)
				(theMenace posn: -25 145 setMotion: MoveTo 20 145 self)
			)
			(2
				(theMenace setMotion: MoveTo 20 106 self)
			)
			(3
				(theMenace setMotion: MoveTo 80 106 self)
			)
			(4
				(theMenace view: 123 setLoop: 0 setCel: 0 ignoreControl:)
				(trollFace init: stopUpd:)
				(self cue:)
			)
			(5
				(theMenace stopUpd:)
				(if (Btst fInvisible)
					(Print 39 2)
				else
					(Print 39 3)
				)
				(self cue:)
			)
			(6
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
				(trollFace hide:)
				(theMenace view: 126 setLoop: 0 setCycle: CycleTo 2 1 self)
			)
			(1
				(if (Btst fInvisible)
					(Print 39 4)
					(Bclr fInvisible)
					(ego put: iMagicRing)
				)
				(ego view: 44 setLoop: 0)
				(theMenace setCycle: EndLoop self)
			)
			(2
				(ego x: (+ (ego x?) 4))
				(= cycles 1)
			)
			(3
				(theMenace setCycle: BegLoop)
				(ego setMotion: MoveTo (+ (ego x?) 15) (ego y?) self)
			)
			(4
				(HandsOn)
				(theMenace
					view: 123
					setLoop: 0
					setCel: 0
					ignoreControl:
					stopUpd:
				)
				(trollFace show: stopUpd:)
				(NormalEgo)
				(ego view: 0 loop: 1)
				(self dispose:)
			)
		)
	)
)

(instance trollFace of Prop
	(properties
		description {troll}
		view 123
		loop 1
		priority 8
		signal fixPriOn
	)
	
	(method (init)
		(= x (+ (theMenace x?) 17))
		(= y (- (theMenace y?) 29))
		(super init:)
	)
	
	(method (show)
		(= x (+ (theMenace x?) 17))
		(= y (- (theMenace y?) 29))
		(super show:)
	)
)

(instance waterRock of Prop
	(properties
		x 92
		y 152
		description {rock}
		view 239
		cycleSpeed 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 39 5)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance GoatButt of Script
	(properties)
	
	(method (doit)
		(if (and (== state 2) (<= (theGoat x?) 105) (not local1))
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
				(Print 39 6)
				(if (> (theGoat x?) 175)
					(ego illegalBits: 0 setMotion: MoveTo 155 95 self)
				else
					(ego illegalBits: 0 setMotion: MoveTo 175 125 self)
				)
			)
			(1
				(ego setLoop: 1 stopUpd:)
				(cond 
					((< (theGoat y?) 64)
						(= local0 1)
						(theGoat setMotion: MoveTo 242 65 self)
					)
					((< (theGoat y?) 121)
						(= local0 2)
						(theGoat setMotion: MoveTo 175 106 self)
					)
					(else
						(= local0 3)
						(theGoat setMotion: MoveTo 191 147 self)
					)
				)
			)
			(2
				(switch local0
					(1
						(theGoat setMotion: MoveTo 242 79 self)
					)
					(else  (self cue:))
				)
			)
			(3
				(theGoat setMotion: MoveTo 150 106 self)
			)
			(4
				(theGoat setMotion: MoveTo 100 106 self)
			)
			(5
				((ScriptID 0 21) number: 38 init: play:)
				(self cue:)
			)
			(6
				(trollFace dispose:)
				(theGoat stopUpd:)
				(theMenace
					view: 124
					setPri: 8
					illegalBits: 0
					ignoreActors:
					setStep: 12 9
					setLoop: 1
					setMotion: MoveTo 82 95 self
				)
			)
			(7
				(theMenace setMotion: MoveTo 84 100 self)
			)
			(8
				(theMenace setMotion: MoveTo 90 130 self)
			)
			(9
				(theMenace setMotion: MoveTo 99 176 self)
			)
			(10
				((ScriptID 0 21) number: 14 play:)
				(splash init:)
				(theMenace setMotion: MoveTo 102 183 self)
			)
			(11
				(theMenace dispose:)
				(Bclr fTrollBlocksBridge)
				(splash setCycle: EndLoop self)
			)
			(12
				(splash dispose:)
				(SolvePuzzle fGoatKilledTroll 4)
				(Print 39 7)
				((ScriptID 0 23) fade:)
				((theGoat looper?) viewNormal: 165)
				(theGoat setStep: 3 2 setMotion: MoveTo 340 110 self)
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

(instance splash of Prop
	(properties
		x 102
		y 185
		view 124
		loop 2
		priority 9
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 2
	)
	
	(method (doVerb)
	)
)

(instance pinetree of NewFeature
	(properties
		x 271
		y 86
		noun '/ceder<pine'
		nsTop 39
		nsLeft 246
		nsBottom 133
		nsRight 296
		description {pinetree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are many beautiful trees around Daventry.}
	)
)

(instance pinetree2 of NewFeature
	(properties
		x 231
		y 108
		noun '/ceder<pine'
		nsTop 81
		nsLeft 217
		nsBottom 136
		nsRight 245
		description {pinetree2}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are many beautiful trees around Daventry.}
	)
)

(instance treesOnBank of NewFeature
	(properties
		x 165
		y 36
		noun '/ceder,oak'
		nsLeft 114
		nsBottom 73
		nsRight 166
		description {treesOnBank}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are many beautiful trees around Daventry.}
	)
)

(instance treesOnBank2 of NewFeature
	(properties
		x 45
		y 42
		noun '/ceder,oak'
		nsLeft 25
		nsBottom 85
		nsRight 66
		description {treesOnBank2}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are many beautiful trees around Daventry.}
	)
)

(instance bridge of NewFeature
	(properties
		x 98
		y 111
		noun '/bridge,boulder'
		nsTop 95
		nsLeft 44
		nsBottom 128
		nsRight 152
		description {bridge}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This natural rock bridge connects the Daventry mainland to a small island.}
	)
)

(instance water of NewFeature
	(properties
		x 102
		y 174
		noun '/water,brook'
		nsTop 160
		nsLeft 57
		nsBottom 189
		nsRight 148
		description {water}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The water is particularly perilous as it passes downstream through this gully.}
	)
)

(instance water2 of NewFeature
	(properties
		x 87
		y 69
		noun '/water,brook'
		nsTop 46
		nsLeft 66
		nsBottom 93
		nsRight 109
		description {water2}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The swift river gushes through this deep gully.}
	)
)

(instance trollTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(trollFace setCycle: Forward)
				(self cue:)
			)
			(1
				(switch talkCount
					(0
						(TrollSays 39 8)
					)
					(1
						(TrollSays 39 9)
					)
					(else
						(TrollSays 39 10)
					)
				)
				(++ talkCount)
				(= register 1)
			)
			(2
				(trollFace setCycle: BegLoop self)
			)
			(3
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

(instance pinetree3 of NewFeature
	(properties
		x 190
		y 42
		noun '/ceder<pine'
		nsTop 17
		nsLeft 163
		nsBottom 68
		nsRight 217
		description {pinetree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are many beautiful trees around Daventry.}
	)
)

(instance tree of NewFeature
	(properties
		x 305
		y 28
		noun '/ceder'
		nsTop -1
		nsLeft 291
		nsBottom 57
		nsRight 320
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are many beautiful trees around Daventry.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 269
		y 18
		noun '/ceder'
		nsTop -1
		nsLeft 249
		nsBottom 38
		nsRight 289
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are many beautiful trees around Daventry.}
	)
)
