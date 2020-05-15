;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmOutsideCasino) ;300
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use PChase)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm300 0
)

(local
	[local0 8]
	lookedAtAppleMan
	appleManTimer
	itemOffered
	autoDoorState
)
(instance rm300 of LLRoom
	(properties
		lookStr {You are outside the lovely Lost Wages Casino and Hotel. 
		You hear the sounds of money changing hands within. 
		A tasteful modern sculpture decorates a fountain.}
		picture rmOutsideCasino
		north rmInsideCasino
		east rmOutsideChapel
		west rmDarkAlley
	)
	
	(method (init)
		(LoadMany SOUND 300 301)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						128 128 115 135 100 137 105 142 117 142 117 147 35 147
						11 143 9 136 38 130 35 119 0 124 0 0 319 0 319 141
						282 141 282 132 301 132 301 128 210 128 196 108 179 108
						164 128
					yourself:
				)
		)
		(aDoorLeft init: stopUpd: approachVerbs: verbDo verbZipper verbTaste)
		(aDoorRight init: stopUpd: approachVerbs: verbDo verbZipper verbTaste)
		(switch prevRoomNum
			(rmOutsideChapel
				(self style: SCROLLRIGHT)
				(ego y: 143)
			)
			(rmInsideCasino
				(ego setPri: 6 posn: 185 120)
			)
			(rmDarkAlley (ego y: 154))
			(rmInsideTaxi 0)
			(else  (ego posn: 160 150))
		)
		(ego init:)
		(tit init: cycleSpeed: howFast setCycle: Forward)
		(knee init: cycleSpeed: howFast setCycle: Forward)
		(hip init: cycleSpeed: howFast setCycle: Forward)
		(fountain init: cycleSpeed: howFast setCycle: Forward)
		(fPlant init:)
		(fSign init:)
		(fLights init:)
		(statue init:)
		(super init:)
		(if (or debugging (CheckItemOwner iApple))
			(if (Btst fAppleManOnScreen)
				(= appleManTimer (Random 100 300))
				(LoadMany VIEW 302 808)
				(Load SOUND 302)
				(aAppleMan cycleSpeed: howFast moveSpeed: howFast init:)
				(appleHead cycleSpeed: howFast init:)
			)
			(Btoggle fAppleManOnScreen)
		)
		(self setRegions: rgSidewalk)
		(fountainSFX play:)
		(if (== prevRoomNum rmInsideCasino) (self setScript: sFromCasino))
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			((and (<= (ego x?) 50) (<= (ego y?) 130)) (curRoom newRoom: 170))
			((== script sFromCasino))
			((== script sToCasino))
			(
				(and
					(ego inRect: 143 123 230 135)
					(or (== autoDoorState 0) (== autoDoorState 3))
				)
				(if (aDoorLeft script?) ((aDoorLeft script?) dispose:))
				(aDoorLeft setScript: sAutoDoorOpen)
			)
			((< (ego y?) 124) (curRoom setScript: sToCasino))
			(
				(and
					(not (ego inRect: 143 123 230 135))
					(or (== autoDoorState 1) (== autoDoorState 2))
				)
				(if (aDoorLeft script?) ((aDoorLeft script?) dispose:))
				(aDoorLeft setScript: sAutoDoorClose)
			)
			(script)
			(
				(and
					appleManTimer
					(> (ego x?) 50)
					(or (== 1 (-- appleManTimer)) (> (ego x?) 260))
				)
				(= appleManTimer 0)
				(aAppleMan setScript: sAppleMan)
			)
		)
	)
)

(instance sToCasino of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setPri: 6 setMotion: MoveTo 185 121 self)
				(if (aDoorLeft script?) ((aDoorLeft script?) dispose:))
				(aDoorLeft setScript: sAutoDoorClose)
			)
			(1 0)
			(2 (curRoom newRoom: 310))
		)
	)
)

(instance sFromCasino of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setPri: 6 posn: 185 120)
				(if (aDoorLeft script?) ((aDoorLeft script?) dispose:))
				(aDoorLeft setScript: sAutoDoorOpen)
			)
			(1
				(ego setPri: -1 setMotion: MoveTo 185 136 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance sAutoDoorOpen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(doorSFX play:)
				(aDoorLeft setMotion: MoveTo 101 124 self)
				(aDoorRight setMotion: MoveTo 275 124 self)
				(= autoDoorState 1)
			)
			(1 0)
			(2
				(doorSFX stop:)
				(aDoorLeft stopUpd:)
				(aDoorRight stopUpd:)
				(= autoDoorState 2)
				(if (== (curRoom script?) sFromCasino)
					(sFromCasino cue:)
				)
				(self dispose:)
			)
		)
	)
)

(instance sAutoDoorClose of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(doorSFX play:)
				(aDoorLeft setMotion: MoveTo 141 124 self)
				(aDoorRight setMotion: MoveTo 235 124 self)
				(= autoDoorState 3)
			)
			(1 0)
			(2
				(doorSFX stop:)
				(aDoorLeft stopUpd:)
				(aDoorRight stopUpd:)
				(= autoDoorState 0)
				(if (== (curRoom script?) sToCasino) (sToCasino cue:))
				(self dispose:)
			)
		)
	)
)

(instance sAppleMan of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== state 5) (ego mover?))
			(= seconds 0)
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 302 setLoop: 1 flags: 1 play:)
				(aAppleMan setCycle: Walk setMotion: PolyPath 49 151 self)
			)
			(1
				(appleHead setCycle: ForwardCounter 3 self)
				(aAppleMan setLoop: 6 setCel: 0 setCycle: EndLoop)
			)
			(2
				(Print 300 0 #at -1 20)
				(aAppleMan
					setLoop: -1
					setCycle: Walk
					setMotion: PChase ego 45 self
				)
			)
			(3 (= cycles 3))
			(4
				(aAppleMan setLoop: 7 setCel: 0 setCycle: EndLoop)
				(appleHead setCycle: ForwardCounter 4 self)
			)
			(5
				(Printf 300 1 10)
				(= seconds 7)
			)
			(6
				(appleHead setCycle: ForwardCounter 2 self)
				(aAppleMan setCycle: BegLoop)
			)
			(7
				(if (ego mover?) (Print 300 2) else (Print 300 3))
				(= seconds 2)
			)
			(8
				(aAppleMan
					setLoop: -1
					setCycle: Walk
					setMotion: PolyPath -30 155 self
				)
			)
			(9
				(aAppleMan dispose:)
				(appleHead dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sBuyApple of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(HandsOff)
				(aAppleMan setLoop: 6 setCel: 0 setMotion: 0)
				(= cycles 1)
			)
			(1 (= cycles 1))
			(2
				2
				(if (> (ego distanceTo: aAppleMan) 30)
					(ego
						setCycle: Walk
						setMotion: PolyPath (+ (aAppleMan x?) 29) (aAppleMan y?) self
					)
				else
					(= cycles 1)
				)
			)
			(3
				3
				(Face ego aAppleMan)
				(appleHead setCycle: ForwardCounter 2)
				(aAppleMan setCycle: EndLoop self)
			)
			(4
				(cond 
					((== itemOffered iRing)
						(Print 300 4)
						(Print 300 5)
						(ego put: iRing 0)
					)
					((== ((inventory at: iApple) owner?) ego)
						(Printf 300 6 10)
						(= state 7)
					)
					(else
						(Printf 300 7 10)
						(SolvePuzzle fBuyApple 3)
					)
				)
				(= cycles 1)
			)
			(5
				(ego
					view: 808
					setLoop: (if (< (ego x?) (aAppleMan x?)) 0 else 1)
					setCel: 0
				)
				(aAppleMan setLoop: 7 setCycle: EndLoop self)
			)
			(6
				6
				(ego setCel: 1 setCycle: EndLoop)
				(aAppleMan setLoop: 8 setCel: 0 setCycle: EndLoop self)
			)
			(7 7 (ego setCycle: BegLoop self))
			(8
				8
				(cond 
					((== itemOffered iRing)
						(Print 300 8)
						(ego get: iApple)
					)
					((>= dollars 10)
						(= dollars (- dollars 10))
						(Print 300 9)
						(ego get: iApple)
					)
					(else
						(Print 300 10)
						(Print 300 11)
					)
				)
				(= cycles 30)
			)
			(9
				9
				(if (not (curRoom script?)) (HandsOn) (NormalEgo))
				(aAppleMan
					setLoop: -1
					setCycle: Walk
					setMotion: PolyPath -30 155 self
				)
			)
			(10 11 (self dispose:))
		)
	)
)

(instance sLookInBarrel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(aAppleMan setMotion: 0 setLoop: 6 setCel: 0)
				(ego setMotion: PChase aAppleMan 25 self)
			)
			(1
				(Face ego aAppleMan)
				(= cycles 5)
			)
			(2
				(if (not register)
					(Print 300 12)
					(if (not (curRoom script?)) (HandsOn))
					(= state 3)
					(= cycles 1)
				else
					(appleHead setCycle: ForwardCounter 2 self)
					(aAppleMan setLoop: 10 setCel: 0)
				)
			)
			(3
				(Print 300 13)
				(= cycles 10)
			)
			(4
				(if (not (curRoom script?)) (NormalEgo loopW) (HandsOn))
				(aAppleMan
					setLoop: -1
					setCycle: Walk
					setMotion: PolyPath -30 155 self
				)
			)
			(5
				(aAppleMan dispose:)
				(self dispose:)
			)
		)
	)
)

(instance fountainSFX of Sound
	(properties
		number 301
		loop -1
	)
)

(instance doorSFX of Sound
	(properties
		number 300
	)
)

(instance aAppleMan of Person
	(properties
		x -15
		y 150
		description {the apple salesman}
		view 302
		priority 13
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbUse
				(switch theItem
					(iWallet
						(if (!= (aAppleMan script?) sBuyApple)
							(aAppleMan setScript: sBuyApple)
							(= itemOffered iWallet)
						else
							(Print 300 14)
						)
					)
					(iRing
						(if (!= (aAppleMan script?) sBuyApple)
							(aAppleMan setScript: sBuyApple)
							(= itemOffered iRing)
						else
							(Print 300 14)
						)
					)
					(iBreathSpray
						(Print 300 15)
					)
					(iApple
						(Print 300 16)
					)
					(iWhiskey
						(Print 300 17)
					)
					(iRose
						(Print 300 18)
					)
					(iLubber
						(Print 300 19)
					)
					(iDiscoPass
						(Print 300 20)
					)
					(iPocketKnife
						(Print 300 21)
						(Print 300 22)
					)
					(iWine
						(Print 300 17)
					)
					(iHammer
						(Print 300 23)
						(Print 300 24 #at -1 140)
					)
					(else
						(Print 300 25)
					)
				)
			)
			(verbLook
				(if lookedAtAppleMan
					(aAppleMan setScript: sLookInBarrel 0 0)
				else
					(Print 300 26)
					(= lookedAtAppleMan TRUE)
				)
			)
			(verbTalk
				(Print 300 27)
				(Print 300 28)
			)
			(verbDo
				(aAppleMan setScript: sLookInBarrel 0 1)
			)
			(verbZipper
				(Print 300 29)
			)
			(verbTaste
				(Print 300 30)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance aDoorLeft of Actor
	(properties
		x 141
		y 124
		description {an automatic, motion-sensing door}
		approachX 189
		approachY 133
		view 300
		loop 4
		priority 7
		signal (| fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(aDoorRight doVerb: theVerb theItem)
	)
)

(instance aDoorRight of Actor
	(properties
		x 235
		y 124
		description {an automatic, motion-sensing door}
		approachX 189
		approachY 133
		view 300
		loop 4
		cel 1
		priority 7
		signal (| fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 300 31)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance aAppleManPoly of Polygon
	(properties
		type PBarredAccess
	)
)

(instance appleHead of Prop
	(properties
		description {the apple salesman}
		view 302
		loop 4
	)
	
	(method (doit)
		(super doit:)
		(if (OneOf (aAppleMan loop?) 6 7 9 8 10)
			(appleHead
				x: (+ (aAppleMan x?) 2)
				y: (aAppleMan y?)
				z: 35
			)
		else
			(appleHead z: 1000)
		)
	)
	
	(method (doVerb theVerb theItem)
		(aAppleMan doVerb: theVerb theItem)
	)
)

(instance tit of Prop
	(properties
		x 310
		y 34
		description {the sculpture}
		sightAngle 40
		view 300
		loop 1
		priority 15
		signal (| ignrAct fixPriOn)
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(statue doVerb: theVerb theItem)
	)
)

(instance hip of Prop
	(properties
		x 294
		y 90
		description {the sculpture}
		sightAngle 40
		view 300
		loop 3
		priority 15
		signal (| ignrAct fixPriOn)
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(statue doVerb: theVerb theItem)
	)
)

(instance knee of Prop
	(properties
		x 276
		y 129
		description {the sculpture}
		sightAngle 40
		view 300
		loop 2
		priority 15
		signal (| ignrAct fixPriOn)
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(statue doVerb: theVerb theItem)
	)
)

(instance fountain of Prop
	(properties
		x 291
		y 165
		description {the fountain}
		lookStr {That water sure looks cold.}
		view 300
		priority 15
		signal (| ignrAct fixPriOn)
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 300 32)
			)
			(verbZipper
				(Print 300 33)
			)
			(verbTaste
				(Print 300 34)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fPlant of Feature
	(properties
		x 64
		y 116
		nsTop 90
		nsLeft 23
		nsBottom 143
		nsRight 105
		description {the plant}
		sightAngle 40
		lookStr {Which is more lifelike, the plant or the pot?}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 300 35)
			)
			(verbTalk
				(Print 300 36)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fSign of Feature
	(properties
		x 151
		y 17
		z 100
		nsTop 1
		nsLeft 42
		nsBottom 33
		nsRight 260
		description {the sign}
		sightAngle 40
		lookStr {The sign above the door says "CASINO," in large, uneven letters.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 300 37)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fLights of Feature
	(properties
		x 143
		y 146
		z 100
		nsTop 34
		nsLeft 28
		nsBottom 58
		nsRight 259
		description {the lights over the door}
		sightAngle 40
		lookStr {These are only a few of the millions of lights that surround this casino, you just can't see the others!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 300 38)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance statue of Feature
	(properties
		x 249
		y 94
		nsLeft 179
		nsBottom 188
		nsRight 319
		description {the sculpture}
		sightAngle 40
		onMeCheck $1000
		lookStr {From the David Niven, Myrna Loy School of Design.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk
				(Print 300 39)
			)
			(verbDo
				(Print 300 40)
			)
			(verbZipper
				(Print 300 41)
			)
			(verbTaste
				(Print 300 42)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)
