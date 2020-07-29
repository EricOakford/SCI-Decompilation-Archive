;;; Sierra Script 1.0 - (do not remove this comment)
(script# 270)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm270 0
)

(local
	local0
	nearRedhead
	nearBlonde
	redHeadX
	blondeX
	local5
	useCount
	[str 500]
)
(instance rm270 of LLRoom
	(properties
		lookStr {You are inside the AeroDork Airline waiting room and former ticket counter. A security camera peers at you from the far wall.}
		picture 270
		north 290
		east 280
		south 250
		west 260
	)
	
	(method (init)
		(ego init: normalize:)
		(blonde
			x: (Random 178 235)
			y: 82
			ignoreActors: TRUE
			setLoop: gBlondeLoop
			init:
		)
		(redHead
			x: (Random 260 305)
			y: 82
			ignoreActors: TRUE
			setLoop: gRedHeadLoop
			init:
		)
		(= blondeX (blonde x?))
		(= redHeadX (redHead x?))
		(if (!= prevRoomNum (curRoom east?))
			(blonde setLoop: 3)
			(redHead setLoop: 4)
		)
		(door init: setPri: 4)
		(camera init:)
		(switch prevRoomNum
			(south
				(camera setCel: 5)
				(if (Btst fFlightAvailable)
					(Bclr fLimoAvailable)
				)
			)
			(north
				(ego edgeHit: 0)
			)
			(east
				(blonde init: posn: (+ gBlondeX 319) 82)
				(redHead init: posn: (+ gRedHeadX 319) 82)
				(HandsOn)
				(camera setCel: 255)
				(= style 11)
			)
			(west
				(HandsOn)
				(= style SCROLLLEFT)
			)
			(else 
				(HandsOn)
				(ego posn: 253 185 edgeHit: 0 setHeading: 360)
				(camera setCel: 5)
			)
		)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 113
						96 113
						84 102
						76 102
						68 113
						0 113
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						64 174
						27 170
						16 157
						7 157
						10 154
						17 154
						35 141
						87 141
						234 141
						241 151
						258 151
						268 155
						269 162
						253 168
						250 174
					yourself:
				)
		)
		(plant init:)
		(chairs init:)
		(ashtray init:)
		(vipSign init:)
		(theCounter init:)
		(if (== currentCity NEW_YORK)
			(cannister init: approachVerbs: verbLook verbDo)
		)
		(switch currentCity
			(LOS_ANGELES
				(blonde description: {Buffi})
				(redHead description: {Muffi})
				(ad1 loop: 4 cel: 0 init:)
				(ad2 loop: 4 cel: 1 init:)
				(ad3 loop: 4 cel: 2 init:)
				(ad4 loop: 4 cel: 3 init:)
			)
			(NEW_YORK
				(blonde description: {Tracie})
				(redHead description: {Stacie})
				(ad1 loop: 5 cel: 0 init:)
				(ad2 loop: 5 cel: 1 init:)
				(ad3 loop: 5 cel: 2 init:)
				(ad4 loop: 5 cel: 3 init:)
			)
			(ATLANTIC_CITY
				(blonde description: {Cherri})
				(redHead description: {Barri})
				(ad1 loop: 6 cel: 0 init:)
				(ad2 loop: 6 cel: 1 init:)
				(ad3 loop: 6 cel: 2 init:)
				(ad4 loop: 6 cel: 3 init:)
			)
			(MIAMI
				(blonde description: {Dixie})
				(redHead description: {Trixie})
				(ad1 loop: 7 cel: 0 init:)
				(ad2 loop: 7 cel: 1 init:)
				(ad3 loop: 7 cel: 2 init:)
				(ad4 loop: 7 cel: 3 init:)
			)
		)
		(addToPics doit:)
		(blonde setScript: sBlonde)
		(redHead setScript: sRedHead)
		(DoSound SetReverb 10)
		(StartTimer (Random 40 150) 0 self)
	)
	
	(method (doit)
		(super doit:)
		(if (ego mover?)
			(switch (ego cel?)
				(1
					(if (!= (stepSound number?) 260)
						(stepSound number: 260 play:)
					)
				)
				(4
					(if (!= (stepSound number?) 261)
						(stepSound number: 261 play:)
					)
				)
			)
		)
		(cond 
			(script)
			((== (ego edgeHit?) EAST)
				(redHead setScript: 0)
				(blonde setScript: 0)
				(= gRedHeadX (redHead x?))
				(= gRedHeadLoop (redHead loop?))
				(= gBlondeX (blonde x?))
				(= gBlondeLoop (blonde loop?))
			)
			((ego inRect: 167 105 325 124)
				(if (not nearBlonde)
					(= nearBlonde 1)
					(blonde setScript: sBlonde 0 365)
				)
				(if (not nearRedhead)
					(= nearRedhead 1)
					(redHead setScript: sRedHead 0 375)
				)
			)
		)
	)
	
	(method (doVerb theVerb &tmp [str2 150])
		(switch theVerb
			(verbLook
				(Format @str2 270 0
					(switch currentCity
						(LOS_ANGELES {Los Angeles})
						(NEW_YORK {New York})
						(ATLANTIC_CITY {Atlantic City})
						(MIAMI {Miami})
					)
				)
				(TimePrint @str2)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(announcement
			number: (Random 273 275)
			setLoop: 1
			flags: 1
			play:
		)
		(StartTimer (Random 20 40) 2 self)
	)
	
	(method (newRoom n)
		(if (not (OneOf n 260 280)) (DoSound SetReverb 0))
		(globalTimer dispose: delete:)
		(super newRoom: n)
	)
)

(instance cannister of PicView
	(properties
		x 142
		y 111
		description {the charity collection canister}
		sightAngle 90
		approachX 139
		approachY 114
		view 273
		priority 4
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (and (not (ego has: iChange)) (not (Btst fStoleChange)))
					(Load VIEW 291)
					(curRoom setScript: sStealChange)
				else
					(TimePrint 270 1)
					(TimePrint 270 2 67 -1 185)
				)
			)
			(verbLook
				(if (and (not (ego has: iChange)) (not (Btst fStoleChange)))
					(TimePrint 270 3)
				else
					(TimePrint 270 4)
				)
			)
			(verbUse
				(if (== theItem iChange)
					(++ useCount)
					(TimePrint 270 5)
					(if (and (> useCount 3) (not (Random 0 10)))
						(TimePrint 270 6 #at -1 185)
						(= useCount 0)
					)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance plant of Feature
	(properties
		x 46
		y 140
		nsTop 117
		nsLeft 28
		nsBottom 164
		nsRight 64
		description {the plant}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 270 7)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance chairs of Feature
	(properties
		x 156
		y 146
		nsTop 126
		nsLeft 72
		nsBottom 167
		nsRight 240
		description {the chairs}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 270 8)
			)
			(verbDo
				(TimePrint 270 9)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ashtray of Feature
	(properties
		x 249
		y 148
		nsTop 134
		nsLeft 240
		nsBottom 162
		nsRight 258
		description {the ashtray}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem &tmp [temp0 30])
		(switch theVerb
			(verbLook
				(TimePrint 270 10)
			)
			(verbDo
				(if (Random 0 1)
					(TimePrint 270 11)
				else
					(TimePrint 270 12)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance vipSign of Feature
	(properties
		x 82
		y 31
		nsTop 19
		nsLeft 51
		nsBottom 44
		nsRight 114
		description {the sign}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 270 13)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance theCounter of Feature
	(properties
		y 1
		nsTop 44
		nsLeft 162
		nsBottom 88
		nsRight 317
		description {the ticket counter}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 270 14)
				(TimePrint 270 15 #at -1 185)
			)
			(verbDo
				(Format @str 270 16
					(blonde description?)
					(redHead description?)
				)
				(TimePrint @str)
				(TimePrint 270 17 #at -1 185)
			)
			(verbTalk
				(TimePrint 270 18)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance blonde of Actor
	(properties
		sightAngle 40
		view 277
		illegalBits $0000
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Format @str 270 19 (blonde description?))
				(TimePrint @str)
			)
			(verbDo
				(Format @str 270 20 (blonde description?))
				(TimePrint @str)
			)
			(verbTalk
				(TimePrint 270 21)
				(TimePrint 270 22)
			)
			(verbUse
				(Format @str 270 23 (blonde description?))
				(TimePrint @str)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance redHead of Actor
	(properties
		sightAngle 90
		view 276
		illegalBits $0000
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Format @str 270 24 (redHead description?))
				(TimePrint @str)
				(TimePrint 270 25)
			)
			(verbDo
				(TimePrint 270 26)
				(TimePrint 270 27)
			)
			(verbTalk
				(TimePrint 270 21)
				(TimePrint 270 28)
			)
			(verbUse
				(Format @str 270 23 (redHead description?))
				(TimePrint @str)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sStealChange of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 291
					ignoreActors: 1
					illegalBits: 0
					setLoop: 3
					cycleSpeed: 6
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(TimePrint 270 29)
				(ego get: iChange)
				(= numQuarters TRUE)
				(ego normalize:)
				(SolvePuzzle 5 fStoleChange)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sBlonde of Script
	
	(method (changeState newState &tmp blondeX_2)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(= blondeX_2 (blonde x?))
				(if register
					(= blondeX register)
				else
					(switch (Random 1 2)
						(1
							(= blondeX (Random 178 307))
						)
						(2
							(= blondeX (Random 332 375))
						)
					)
				)
				(if
					(and
						(not register)
						(or
							(and
								(< blondeX (redHead x?))
								(< (redHead x?) blondeX_2)
							)
							(and
								(< blondeX_2 (redHead x?))
								(< (redHead x?) blondeX)
							)
						)
					)
					(self init:)
				else
					(blonde
						setLoop: -1
						setCycle: Walk
						setMotion: MoveTo blondeX 82 self
					)
				)
			)
			(2
				(if (== (blonde x?) blondeX) (blonde setLoop: 3))
				(= seconds (Random 2 5))
			)
			(3
				(= nearBlonde (= register 0))
				(self init:)
			)
		)
	)
)

(instance sRedHead of Script

	(method (changeState newState &tmp redHeadX_2)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(= redHeadX_2 (redHead x?))
				(if register
					(= redHeadX register)
				else
					(switch (Random 1 2)
						(1
							(= redHeadX (Random 178 307))
						)
						(2
							(= redHeadX (Random 332 375))
						)
					)
				)
				(if
					(and
						(not register)
						(or
							(and
								(< redHeadX (blonde x?))
								(< (blonde x?) redHeadX_2)
							)
							(and
								(< redHeadX_2 (blonde x?))
								(< (blonde x?) redHeadX)
							)
						)
					)
					(self init:)
				else
					(redHead
						setLoop: -1
						setCycle: Walk
						setMotion: MoveTo redHeadX 82 self
					)
				)
			)
			(2
				(if (== (redHead x?) redHeadX) (redHead setLoop: 4))
				(= seconds (Random 2 5))
			)
			(3
				(= nearRedhead (= register 0))
				(self init:)
			)
		)
	)
)

(instance door of Door
	(properties
		x 98
		y 106
		description {the door}
		sightAngle 90
		approachX 81
		approachY 111
		view 270
		entranceTo 290
		locked 1
		moveToX 81
		moveToY 105
		enterType 0
		exitType 0
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 270 30)
			)
			(verbDo
				(TimePrint 270 31)
			)
			(verbTalk
				(TimePrint 270 32)
				(TimePrint 270 33 #at -1 185)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ad1 of PicView
	(properties
		x 11
		y 11
		description {the sign}
		sightAngle 90
		view 263
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch currentCity
					(LOS_ANGELES
						(TimePrint 270 34)
					)
					(NEW_YORK
						(TimePrint 270 35)
					)
					(ATLANTIC_CITY
						(TimePrint 270 36)
					)
					(MIAMI
						(TimePrint 270 37)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ad2 of PicView
	(properties
		x 163
		y 11
		description {the sign}
		sightAngle 90
		view 263
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch currentCity
					(LOS_ANGELES
						(TimePrint 270 38)
					)
					(NEW_YORK
						(TimePrint 270 39)
					)
					(ATLANTIC_CITY
						(SolvePuzzle 1 fLearnAtlanticCityLimoNumber)
						(TimePrint 270 40)
					)
					(MIAMI
						(TimePrint 270 41)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ad3 of PicView
	(properties
		x 249
		y 11
		description {the sign}
		sightAngle 90
		view 263
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch currentCity
					(LOS_ANGELES
						(TimePrint 270 42)
					)
					(NEW_YORK
						(TimePrint 270 43)
					)
					(ATLANTIC_CITY
						(TimePrint 270 44)
					)
					(MIAMI
						(TimePrint 270 45)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ad4 of PicView
	(properties
		x 317
		y 8
		description {the sign}
		sightAngle 90
		view 263
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch currentCity
					(LOS_ANGELES
						(TimePrint 270 46)
					)
					(NEW_YORK
						(TimePrint 270 47)
					)
					(ATLANTIC_CITY
						(TimePrint 270 48)
					)
					(MIAMI
						(SolvePuzzle 1 fLearnGreenCardNumber)
						(TimePrint 270 49)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance camera of Prop
	(properties
		x 126
		y 58
		nsTop 46
		nsLeft 112
		nsBottom 63
		nsRight 130
		description {the security camera}
		sightAngle 90
		approachX 92
		approachY 113
		view 270
		loop 1
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: verbUse verbDo verbLook)
	)
	
	(method (doit &tmp temp0 temp1 theCel theLoop [temp4 20])
		(super doit:)
		(if (not (curRoom script?))
			(= theCel cel)
			(= theLoop loop)
			(= temp1
				(/ (- (GetAngle x y (ego x?) (ego y?)) 110) 14)
			)
			(self
				setLoop: (if (> (ego y?) 159) 1 else 5)
				setCel: (- 9 temp1)
			)
			(if (or (!= theLoop loop) (!= theCel cel))
				(theMusic2 number: 271 loop: 1 play:)
			)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 270 50)
			)
			(verbLook
				(TimePrint 270 51)
			)
			(verbUse
				(switch theItem
					(iGoldCard
						(curRoom setScript: sShowCard)
					)
					(iBoardingPass
						(TimePrint 270 52)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sShowCard of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(= register 5)
				(SolvePuzzle 9 fShowedCardToCamera)
				(ego
					view: 271
					setLoop: 1
					cycleSpeed: 4
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(= start state)
				(= cycles 6)
			)
			(2
				(theMusic2 number: 272 setLoop: 1 play: self)
			)
			(3
				(if (not (-- register))
					(= cycles 1)
				else
					(if (not (camera cel?))
						(camera setCel: 1 setLoop: 3)
					else
						(camera setCel: 0 setLoop: 3)
					)
					(self init:)
				)
			)
			(4
				(camera setCel: 0)
				(ego normalize:)
				(door locked: FALSE open:)
				(self dispose:)
			)
		)
	)
)

(instance stepSound of Sound)

(instance announcement of Sound)
