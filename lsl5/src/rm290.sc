;;; Sierra Script 1.0 - (do not remove this comment)
(script# 290)
(include game.sh)
(use Main)
(use LLRoom)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm290 0
)

(local
	roomState
	[str 50]
	[str2 50]
)
(instance rm290 of LLRoom
	(properties
		lookStr {All your life you assumed those snooty, private airport waiting rooms were filled with luxurious appointments.}
		picture 290
		north 310
		south 270
	)
	
	(method (init)
		(Load VIEW 291)
		(Load VIEW 550)
		(Load VIEW 291)
		(ego init: normalize:)
		(door init: stopUpd:)
		(switch prevRoomNum
			(north
				(ego view: 291 setLoop: 5 setCycle: 0 posn: 152 93)
				(door posn: 111 118)
				(InFirstPerson 0)
				(Bclr fNowBoarding)
				(self setScript: sReturning)
			)
			(south
				(ego posn: 159 225)
				(self setScript: sEnter270)
			)
			(else 
				(HandsOn)
				(ego posn: 160 160 edgeHit: 0)
			)
		)
		(super init:)
		(cond 
			((not prevRoomNum)
				(StartTimer 90 0 curRoom)
			)
			((Btst 32)
				(boardingSign init:)
			)
			((and (ego has: iBoardingPass) (Btst fFlightAvailable))
				(StartTimer 90 1 curRoom)	;EO: is now a cycle timer because I'm impatient
			)
		)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						91 126
						115 126
						111 137
						96 145
						74 144
						67 150
						63 167
						109 189
						0 189
						0 0
						319 0
						319 189
						202 189
						232 169
						261 150
						210 123
						102 123
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						201 132
						213 137
						217 144
						198 150
						180 148
						172 145
						170 137
					yourself:
				)
		)
		(chair ignoreActors: TRUE init: stopUpd:)
		(ABM init:)
		(pot init: approachVerbs: verbDo)
		(cabinet init:)
		(light init:)
		(leftPainting init:)
		(rightPainting init:)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego cBLUE)
				(HandsOff)
				(curRoom setScript: sExit270)
			)
		)
	)
	
	(method (doVerb theVerb &tmp [str3 150])
		(switch theVerb
			(verbLook
				(Format @str3 290 0
					(switch currentCity
						(LOS_ANGELES
							{Los Angeles}
						)
						(NEW_YORK
							{New York}
						)
						(ATLANTIC_CITY
							{Atlantic City}
						)
						(MIAMI
							{Miami}
						)
					)
				)
				(TimePrint @str3)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(switch (++ roomState)
			(1
				(HandsOff)
				(Bset fNowBoarding)
				(boardingSign init:)
				(StartTimer 4 0 curRoom)
			)
			(2
				(theMusic number: 297 loop: 1 play:)
				(StartTimer 5 1 curRoom)
			)
			(3
				(TimePrint 290 1)
				(TimePrint 290 2)
				(StartTimer 2 0 curRoom)
			)
			(4 (HandsOn))
		)
	)
	
	(method (newRoom n)
		(globalTimer dispose: delete:)
		(super newRoom: n)
	)
)

(instance chair of View
	(properties
		x 190
		y 144
		description {the chair}
		sightAngle 90
		view 290
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 290 3)
			)
			(verbDo
				(curRoom setScript: sChair)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance door of Actor
	(properties
		x 152
		y 118
		description {the jetway door}
		sightAngle 90
		view 290
		loop 1
		priority 3
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		illegalBits $0000
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 290 4)
			)
			(verbDo
				(TimePrint 290 5)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance boardingSign of Prop
	(properties
		x 153
		y 62
		description {the boarding sign}
		sightAngle 90
		view 290
		loop 2
		priority 5
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (init)
		(super init:)
		(self cycleSpeed: 8 setCycle: Forward)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (Btst fNowBoarding)
					(TimePrint 290 6)
				else
					(TimePrint 290 7)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sExit270 of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (ego x?) 225 self)
			)
			(1
				(curRoom newRoom: (curRoom south?))
			)
		)
	)
)

(instance sEnter270 of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 159 160 self)
			)
			(1
				(HandsOn)
				(ego normalize:)
				(self dispose:)
			)
		)
	)
)

(instance ABM of Feature
	(properties
		x 185
		y 98
		nsTop 92
		nsLeft 176
		nsBottom 105
		nsRight 194
		description {the Automatic Boarding Pass Machine}
		sightAngle 90
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbUse
				(switch theItem
					(iGoldCard
						(TimePrint 290 8)
					)
					(iBoardingPass
						(if (Btst fNowBoarding)
							(ego put: iBoardingPass 0)
							(curRoom setScript: sJetWay)
						else
							(TimePrint 290 9)
						)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(2
				(TimePrint 290 10)
				(TimePrint 290 11)
				(TimePrint 290 12
					#at -1 185
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance pot of Feature
	(properties
		x 91
		y 211
		z 100
		nsTop 107
		nsLeft 85
		nsBottom 116
		nsRight 97
		description {the coffee pot}
		sightAngle 40
		approachX 104
		approachY 136
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 290 13)
			)
			(verbDo
				(TimePrint 290 14)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance cabinet of Feature
	(properties
		x 85
		y 119
		nsTop 101
		nsLeft 68
		nsBottom 137
		nsRight 102
		description {the coffee cabinet}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 290 15)
			)
			(verbDo
				(TimePrint 290 16)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance light of Feature
	(properties
		x 150
		y 35
		nsTop 26
		nsLeft 140
		nsBottom 45
		nsRight 161
		description {the light fixture}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 290 17)
			)
			(verbDo
				(TimePrint 290 18)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance leftPainting of Feature
	(properties
		x 76
		y 81
		nsTop 67
		nsLeft 64
		nsBottom 96
		nsRight 89
		description {the lovely painting}
		sightAngle 40
		lookStr {You've always admired paintings on velvet!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 290 19)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance rightPainting of Feature
	(properties
		x 229
		y 94
		nsTop 84
		nsLeft 213
		nsBottom 105
		nsRight 246
		description {the beautiful painting}
		sightAngle 40
		lookStr {The last time you saw art this nice, you were in Tijuana.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 290 19)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sChair of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(Load SOUND 291)
				(Load SOUND 295)
				(Load SOUND 296)
				(Load VIEW 291)
				(Load VIEW 291)
				(ego
					setMotion: PolyPath (- (chair x?) 10) (- (chair y?) 1) self
				)
			)
			(1
				(ego setHeading: 180 self)
			)
			(2
				(chair hide:)
				(ego
					setPri: 14
					view: 291
					setLoop: 0
					posn: (+ (chair x?) 1) (chair y?)
					cycleSpeed: 6
					setCel: 0
					setCycle: CycleTo 5 1 self
				)
			)
			(3
				(theMusic2 number: 291 loop: 1 play:)
				(ego setCycle: CycleTo 9 1 self)
			)
			(4
				(theMusic2 number: 295 play:)
				(ego setCycle: EndLoop self)
			)
			(5
				(chair show:)
				(theMusic2 number: 296 loop: 1 play:)
				(ego
					posn: (+ (chair x?) 44) (+ (chair y?) 3)
					setLoop: 1
					cycleSpeed: 4
					setCycle: Forward
				)
				(= ticks 60)
			)
			(6
				(ego
					setLoop: 2
					cycleSpeed: 6
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(7
				(ego posn: (- (ego x?) 12) (ego y?) normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sReturning of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (HandsOff) (= ticks 60))
			(1
				(ego
					setCel: 0
					cycleSpeed: 12
					moveSpeed: 5
					setCycle: EndLoop self
				)
			)
			(2
				(ego
					normalize:
					posn: 152 123
					loop: 2
					cycleSpeed: 7
					moveSpeed: 7
					setMotion: MoveTo 152 131 self
				)
			)
			(3
				(ego normalize:)
				(theMusic2 number: 293 loop: -1 play:)
				(door setMotion: MoveTo 152 118 self)
			)
			(4
				(theMusic2 stop:)
				(= cycles 2)
			)
			(5
				(Format @str 290 20
					(switch currentCity
						(LOS_ANGELES
							{Los Angeles, cheater}	;obviously can't be seen legitimately
						)
						(NEW_YORK
							{New York}
						)
						(ATLANTIC_CITY
							{Atlantic City}
						)
						(MIAMI
							{Miami}
						)
					)
				)
				(TimePrint @str)
				(= ticks 60)
			)
			(6
				(cond 
					((Btst fAfterNewYork)
						(Format @str2 {New York})
					)
					((Btst fAfterMiami)
						(Format @str2 {Miami})
					)
					((Btst fAfterAtlanticCity)
						(Format @str2 {Atlantic City})
					)
					(else
						(Format @str2 {Los Angeles})
					)
				)
				(Format @str 290 21 @str2)
				(TimePrint @str)
				(= ticks 60)
			)
			(7
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sJetWay of Script

	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 179 124 self)
			)
			(1
				(ego
					view: 291
					setLoop: 3
					cycleSpeed: 6
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(2 (= ticks 30))
			(3
				(SolvePuzzle 7 fInsertBoardingPass)
				(if (== currentCity LOS_ANGELES)
					(TimePrint 290 22)
					(TimePrint 290 23)
				)
				(theMusic2 number: 292 loop: 1 play:)
				(= ticks 30)
			)
			(4
				(ego cycleSpeed: 8 setCycle: BegLoop)
				(theMusic2 number: 293 loop: -1 play:)
				(door setMotion: MoveTo 110 (door y?) self)
			)
			(5
				(theMusic2 stop:)
				(ego normalize: setMotion: PolyPath 154 124 self)
			)
			(6
				(ego
					view: 291
					setLoop: 4
					cycleSpeed: 8
					setCel: 0
					posn: 152 94
					setCycle: EndLoop self
				)
			)
			(7
				(= numQuarters 0)
				(curRoom newRoom: 310)
			)
		)
	)
)
