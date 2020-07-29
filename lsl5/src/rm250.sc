;;; Sierra Script 1.0 - (do not remove this comment)
(script# 250)
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
	rm250 0
)

(local
	greenCardHere
	limoState
)
(instance rm250 of LLRoom
	(properties
		picture 250
		north 270
	)
	
	(method (init)
		(HandsOff)
		(Load VIEW 250)
		(ego init: normalize: 553 setStep: 2 1)
		(plane
			init:
			setLoop: 0
			setCel: (Random 4 6)
			setScript: sPlane
		)
		(door init: setPri: 4)
		(if (or (Btst fLimoAvailable) (Btst fLimoHere))
			(limo init: posn: 277 115)
		)
		(switch prevRoomNum
			(north
				(ego edgeHit: 0)
				(StartTimer 5 1 curRoom)
			)
			(258
				(ego posn: 191 105 illegalBits: cWHITE)
				(StartTimer 5 1 curRoom)
			)
			(else 
				(ego posn: 1000 1000 0)
				(if (not (Btst fLimoAvailable))
					(if (not (cast contains: limo)) (limo init:))
					(limo posn: 400 115)
				)
				(curRoom setScript: sLimo)
			)
		)
		(super init:)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						257 0
						257 104
						182 104
						182 106
						315 106
						316 104
						263 104
						263 0
						319 0
						319 189
						0 189
					yourself:
				)
		)
		(otherDoor init:)
		(driveway init:)
		(car init:)
		(car2 init:)
		(luggage init:)
		(luggage2 init:)
		(bush init:)
		(tower init:)
		(ATM init: approachVerbs: verbDo verbUse)
		(trashCan init: approachVerbs: verbDo)
		(if
			(and
				(Btst fCalledForGreenCard)
				(== currentCity MIAMI)
				(not (ego has: iGreenCard))
				(not (Btst fAfterMiami))
			)
			(envelope init: approachVerbs: verbDo)
			(= greenCardHere TRUE)
		)
		(addToPics doit:)
		(cloud
			init:
			setCel: 8
			posn: (Random 90 230) (Random 1 20) -30
			stopUpd:
		)
		(theSign init: setCel: currentCity setPri: 4)
		(theMusic number: 251 loop: -1 play:)
	)
	
	(method (doit)
		(super doit:)
		(if script
			(script doit:)
		)
	)
	
	(method (doVerb theVerb theItem &tmp [str 100])
		(switch theVerb
			(verbLook
				(if greenCardHere
					(TimePrint 250 0)
				else
					(Format @str 250 1
						(switch currentCity
							(LOS_ANGELES {Los Angeles})
							(NEW_YORK {New York})
							(ATLANTIC_CITY {Atlantic City})
							(MIAMI {Miami})
						)
					)
					(TimePrint @str)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(HandsOn)
	)
	
	(method (newRoom n)
		(if (!= n 258)
			(theMusic stop:)
			(theMusic2 stop:)
			(theMusic3 stop:)
		else
			(theMusic2 fade:)
		)
		(super newRoom: n)
	)
)

(instance ATM of Feature
	(properties
		y 1
		nsTop 82
		nsLeft 176
		nsBottom 94
		nsRight 190
		description {the ATM}
		sightAngle 40
		approachX 189
		approachY 102
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 250 2)
			)
			(verbDo
				(curRoom newRoom: 258)
			)
			(verbTalk
				(TimePrint 250 3)
				(TimePrint 250 4)
			)
			(verbUse
				(switch theItem
					(iGoldCard
						(ego put: iGoldCard)
						(curRoom newRoom: 258)
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

(instance limo of Actor
	(properties
		description {your limousine}
		approachX 288
		approachY 107
		view 250
		loop 3
		priority 8
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
		illegalBits $0000
	)
	
	(method (init)
		(self approachVerbs: verbDo)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(TimePrint 250 5)
			)
			(verbDo
				(HandsOff)
				(theMusic3 number: 191 loop: 1 play:)
				(self cue:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(switch (++ limoState)
			(1 (StartTimer 2 2 self))
			(2
				(ego hide:)
				(StartTimer 30 1 self)
			)
			(3
				(theMusic3 number: 192 loop: 1 play:)
				(self cue:)
			)
			(4 (StartTimer 20 1 self))
			(5 (curRoom newRoom: 200))
		)
	)
)

(instance tower of Feature
	(properties
		y 1
		nsTop 20
		nsLeft 256
		nsBottom 68
		nsRight 295
		description {the control tower}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 250 6)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance bush of Feature
	(properties
		y 1
		nsTop 130
		nsLeft 242
		nsBottom 171
		nsRight 319
		description {the bushes}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 250 7)
			)
			(verbDo
				(TimePrint 250 8)
			)
			(verbTalk
				(TimePrint 250 9)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance luggage of Feature
	(properties
		y 1
		nsTop 101
		nsLeft 118
		nsBottom 124
		nsRight 149
		description {the luggage}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 250 10)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance luggage2 of Feature
	(properties
		y 1
		nsTop 135
		nsLeft 55
		nsBottom 150
		nsRight 82
		description {the luggage}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 250 10)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance car of Feature
	(properties
		y 1
		nsTop 124
		nsLeft 95
		nsBottom 161
		nsRight 160
		description {the car}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 250 11)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance car2 of Feature
	(properties
		y 1
		nsTop 161
		nsLeft 61
		nsBottom 188
		nsRight 147
		description {the car}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 250 12)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance driveway of Feature
	(properties
		y 1
		nsTop 105
		nsLeft 150
		nsBottom 129
		nsRight 319
		description {the driveway}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 250 13)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance otherDoor of Feature
	(properties
		y 1
		nsTop 67
		nsLeft 46
		nsBottom 131
		nsRight 99
		description {the door}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 250 14)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance theSign of View
	(properties
		x 224
		y 67
		description {the sign}
		view 250
	)
	
	(method (doVerb theVerb &tmp [str 40])
		(switch theVerb
			(verbLook
				(Format @str 250 15
					(switch currentCity
						(LOS_ANGELES {Los Angeles})
						(NEW_YORK {New York})
						(ATLANTIC_CITY {Atlantic City})
						(MIAMI {Miami})
					)
				)
				(TimePrint @str)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance envelope of Prop
	(properties
		x 206
		y 99
		description {the plain brown envelope}
		approachX 205
		approachY 93
		view 250
		loop 2
		priority 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(TimePrint 250 17)
			)
			(verbDo
				(if (not (ego has: iGreenCard))
					(HandsOff)
					(= greenCardHere FALSE)
					(ego get: iGreenCard)
					(HandsOn)
					(SolvePuzzle 12)
					(TimePrint 250 18)
					(StartTimer 3 1 self)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(TimePrint 250 16)
		(self dispose:)
	)
)

(instance plane of Actor
	(properties
		description {the airplane}
		view 250
		priority 3
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(TimePrint 250 19)
			)
			(verbDo
				(TimePrint 250 20)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance trashCan of Feature
	(properties
		x 201
		y 94
		nsTop 88
		nsLeft 196
		nsBottom 101
		nsRight 206
		description {the trash can}
		sightAngle 90
		approachX 205
		approachY 93
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if greenCardHere
					(TimePrint 250 21)
				else
					(TimePrint 250 22)
				)
			)
			(verbDo
				(if greenCardHere
					(envelope doVerb: verbDo)
				else
					(TimePrint 250 23)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance cloud of View
	(properties
		description {the cloud}
		view 250
		priority 1
		signal (| ignrAct ignrHrz fixedLoop fixedCel fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(TimePrint 250 24)
			)
			(verbDo
				(TimePrint 250 25)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance door of Door
	(properties
		x 260
		y 101
		description {the door}
		sightAngle 90
		approachX 261
		approachY 104
		lookStr {This door leads to the exclusive AeroDork gates.}
		view 251
		entranceTo 270
		openSnd 293
		closeSnd 0
		moveToX 261
		moveToY 102
		enterType 0
		exitType 0
	)
)

(instance theMusic3 of Sound)

(instance sPlane of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (= seconds (Random 6 11)))
			(1
				(theMusic2 number: 254 loop: 1 flags: 1 play: 127)
				(switch (Random 1 4)
					(1
						(plane
							show:
							setCel: 4
							posn: 347 31
							setMotion: MoveTo 120 55 self
						)
					)
					(2
						(plane
							show:
							setCel: 7
							posn: 319 18
							setMotion: MoveTo 108 57 self
						)
					)
					(3
						(plane
							show:
							setCel: 5
							posn: 319 25
							setMotion: MoveTo 124 66 self
						)
					)
					(4
						(plane
							show:
							setCel: 6
							posn: 283 -3
							setMotion: MoveTo 73 48 self
						)
					)
				)
			)
			(2 (plane hide:) (= ticks 30))
			(3
				(theMusic2 fade: 0 10 10 1)
				(self init:)
			)
		)
	)
)

(instance sLimo of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ego hide: posn: 287 110)
				(= cycles 2)
			)
			(1
				(if (Btst fLimoAvailable)
					(self changeState: 20)
				else
					(theMusic3 number: 255 loop: 1 play: self hold: 10)
					(limo setMotion: MoveTo 340 115 self)
				)
			)
			(2
				(limo xStep: 2 setMotion: MoveTo 295 115 self)
			)
			(3
				(limo xStep: 1 setMotion: MoveTo 277 115 self)
			)
			(4
				(theMusic3 release:)
				(limo stopUpd:)
				(= state 19)
			)
			(20
				(theMusic3 number: 191 loop: 1 play:)
				(= ticks 100)
			)
			(21 (= ticks 10))
			(22
				(ego
					show:
					setLoop: 3
					moveSpeed: 15
					setMotion: MoveTo 287 105 self
				)
			)
			(23
				(theMusic3 number: 192 loop: 1 play:)
				(ego normalize: 553 setStep: 2 1)
				(Bset fLimoAvailable)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
