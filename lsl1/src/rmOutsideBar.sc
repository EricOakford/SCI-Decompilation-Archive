;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmOutsideBar) ;100
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm100 0
)

(local
	deathTimer
)
(instance rm100 of LLRoom
	(properties
		picture rmOutsideBar
		horizon 120
		north rmInsideBar
		east rmDarkAlley
	)
	
	(method (init)
		(if (== debugging 2) (Bset 43))
		(if (== debugging 1) (Bset 15))
		(Load VIEW 100)
		(LoadMany SOUND 801 802)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0 319 0 319 140 300 140 300 124 296 124
						296 146 98 146 99 137 82 137 72 146 9 146
						0 116
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 28 150 55 150 55 154 28 154
					yourself:
				)
		)
		(switch prevRoomNum
			(rmInsideBar
				(HandsOff)
				(self setScript: fromBarScript)
			)
			(rmAlley
				(ego x: 300 y: 155 setHeading: 180)
			)
			(rmDarkAlley
				(if (> (ego x?) 160)
					(self west: rmDarkAlley)
					(self east: 0)
				)
				(ego y: 157)
			)
			(rmInsideTaxi 0)
			(else 
				(self style: IRISOUT)
				(HandsOn)
				(theIconBar enable:)
				(ego x: 140 y: 149 setHeading: 180)
			)
		)
		(ego init:)
		(super init:)
		(self setRegions: rgSidewalk)
		(door cycleSpeed: howFast init: approachVerbs: verbDo stopUpd:)
		(if (< (GameHour) 4)	;before 3 a.m., when Lefty's closes
			(leftySign cycleSpeed: (+ 3 howFast) setCycle: Forward init:)
			(hotelSign cycleSpeed: 30 setCycle: Forward init:)
			(glass cycleSpeed: (+ 2 howFast) setCycle: Forward init:)
		)
		(taxiSign
			approachY: 159
			approachX: (ego x?)
			approachVerbs: verbDo verbTalk
			init:
		)
		(pole init:)
		(theWindow init:)
		(upperWindows init:)
		(building init:)
		(doormat init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(cans init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(if (Btst fDiedOfTheClap)
			(Load VIEW 101)
			(LoadMany SOUND 105 101)
			(= deathTimer 150)
		)
		(if (and (not (Btst fStiffedCabbie)) (Btst fWearingLubber))
			(Load VIEW 104)
			(Load SOUND 104)
			(aCop
				init:
				setCycle: Walk
				x: (if (== prevRoomNum rmAlley) -20 else 340)
				setScript: sChaseEgo
			)
		)
		(self east: rmDarkAlley)
		(self west: 0)
	)
	
	(method (doit &tmp roomNum)
		(taxiSign approachX: (ego x?))
		(if deathTimer
			(if (cast contains: (ScriptID rgSidewalk 2)) (= deathTimer 1))
			(if
				(and
					(== deathTimer 1)
					(!= (curRoom script?) sDieOfTheClap)
					(!= (curRoom script?) (ScriptID 813 1))
				)
				(HandsOff)
				(curRoom setScript: sDieOfTheClap)
			)
			(-- deathTimer)
		)
		(super doit:)
		(return
			(cond 
				(script)
				((< (ego x?) 2) (HandsOff) (curRoom setScript: sWalkWest))
				((< (ego y?) 135)
					(if (< (ego x?) 160) (= roomNum rmDarkAlley) else (= roomNum rmAlley))
					(if (not (Btst 15))
						(super newRoom: roomNum)
					else
						(HandsOff)
						(curRoom setScript: sDieOfTheClap)
					)
				)
				(else 0)
			)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook (Print 100 0) (Print 100 1))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sWalkWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo -20 (ego y?) self)
			)
			(1
				(curRoom newRoom: rmDarkAlley)
				(self dispose:)
			)
		)
	)
)

(instance fromBarScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(door setCel: 255)
				(ego posn: 93 125 setMotion: MoveTo 92 148 self)
			)
			(1
				(soundFX number: 802 loop: 1 play:)
				(door setCycle: BegLoop self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance sEnterDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setPri: (+ (door priority?) 1))
				(soundFX number: 801 loop: 1 play:)
				(door setCycle: EndLoop self)
			)
			(1
				(ego setMotion: MoveTo 97 130 self)
			)
			(2
				(ego setPri: (- (door priority?) 1))
				(= cycles 3)
			)
			(3
				(soundFX number: 802 loop: 1 play:)
				(ego setMotion: 0)
				(door setCycle: BegLoop self)
			)
			(4 (curRoom newRoom: 110))
		)
	)
)

(instance sChaseEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aCop setMotion: PolyPath (ego x?) (ego y?))
				(= cycles 5)
			)
			(1
				(cond 
					(
					(and (not register) (< (aCop distanceTo: ego) 160)) (= register 1) (soundFX play:))
					(
					(and (== register 1) (< (aCop distanceTo: ego) 80)) (= register 2) (Print 100 2 #at -1 20))
				)
				(if (> (aCop distanceTo: ego) 50)
					(self init:)
				else
					(= cycles 1)
				)
			)
			(2
				(HandsOff)
				(aCop setMotion: 0)
				(Print 100 3 #at -1 20)
				(= seconds 3)
			)
			(3
				(Print 100 4 #at -1 20)
				(= seconds 3)
			)
			(4
				(ShowDeathIcon 103 0 1)
				(Format @str1 100 5)
				(EgoDead 100 6)
			)
		)
	)
)

(instance sDieOfTheClap of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 100 7 #at -1 20)
				(if
				(or (== (ego view?) 800) (== (ego view?) 809))
					(ego egoSpeed: setHeading: 90 self)
				else
					(= cycles 1)
				)
			)
			(1
				(ego
					view: 101
					cycleSpeed: 6
					setLoop: 0
					setCel: 0
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(soundFx number: 105 flags: 1 setLoop: 1 play: 127)
				(Print 100 8 #at -1 20)
				(ego setCycle: EndLoop self)
			)
			(3
				(soundFx number: 101 setLoop: 1 play:)
				(ego setLoop: 1 setCel: 0)
				(= seconds 3)
			)
			(4 (ego setCycle: EndLoop self))
			(5
				(egoEyes
					init:
					x: (ego x?)
					y: (ego y?)
					setPri: (+ (ego priority?) 1)
					cycleSpeed: (* 2 howFast)
					setCycle: Forward
				)
				(= seconds 3)
			)
			(6
				(ShowDeathIcon 101 3)
				(Format @str1 100 9)
				(EgoDead 100 10)
			)
		)
	)
)

(instance leftySign of Prop
	(properties
		x 111
		y 144
		z 95
		description {the neon sign}
		lookStr {You are outside Lefty's bar. Isn't it wonderful what can be done with a little neon?}
		view 100
		cel 1
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 3
	)
)

(instance hotelSign of Prop
	(properties
		x 283
		y 144
		z 144
		description {the neon sign}
		lookStr {The "L" in this place must be hot!}
		view 100
		loop 2
		priority 14
		signal (| ignrAct fixPriOn)
		detailLevel 2
	)
)

(instance glass of Prop
	(properties
		x 180
		y 144
		z 106
		description {the neon sign}
		lookStr {This is the cleanest glass in Lefty's collection.}
		view 100
		loop 1
		cel 1
		priority 14
		signal (| ignrAct fixPriOn)
		detailLevel 1
	)
)

(instance door of Prop
	(properties
		x 84
		y 120
		z -15
		description {Lefty's door}
		approachX 97
		approachY 135
		lookStr {Nice tuck and roll job, eh?}
		view 100
		loop 3
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(cond 
					((and (> 2 (GameHour)) (> (GameHour) 10)) (Print 100 11) (Print 100 12))
					((aCop script?) (Print 100 13))
					(else (HandsOff) (curRoom setScript: sEnterDoor))
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance egoEyes of Prop
	(properties
		z 33
		view 101
		loop 2
	)
)

(instance theWindow of Feature
	(properties
		x 211
		y 142
		z 57
		nsTop 67
		nsLeft 155
		nsBottom 103
		nsRight 268
		description {Lefty's window}
		sightAngle 40
		lookStr {Lefty's windows are too dirty to see through.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 100 14)
				(Print 100 15 #at -1 140)
			)
			(verbUse
				(switch theItem
					(iHammer (Print 100 16))
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance taxiSign of Feature
	(properties
		x 42
		y 152
		z 72
		nsTop 73
		nsLeft 36
		nsBottom 87
		nsRight 49
		description {the taxi sign}
		sightAngle 40
		lookStr {High up that telephone pole sits a lonely sign that reads, "Taxi Stand."}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 100 17)
				(HandsOff)
				(curRoom setScript: (ScriptID rgSidewalk 3))
			)
			(verbTalk
				(Print 100 18)
				(HandsOff)
				(curRoom setScript: (ScriptID rgSidewalk 3))
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance pole of Feature
	(properties
		x 38
		y 150
		nsLeft 31
		nsBottom 152
		nsRight 45
		description {the utility pole}
		sightAngle 40
		lookStr {It's tall and skinny; built like a pole!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 100 19))
			(verbUse
				(switch theItem
					(iRibbon (Print 100 20))
					(iHammer (Print 100 21))
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance upperWindows of Feature
	(properties
		x 158
		y 142
		z 126
		nsLeft 48
		nsBottom 33
		nsRight 268
		description {the windows}
		sightAngle 80
		lookStr {You cleverly notice that light shines from only one of the windows above Lefty's. Could there be someone up there?}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 100 22))
			(verbTalk
				(Print 100 23)
				(Print 100 24 #at -1 140)
			)
			(verbUse (Print 100 25))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance building of Feature
	(properties
		x 146
		y 92
		nsTop 42
		nsLeft 13
		nsBottom 142
		nsRight 280
		description {Lefty's bar}
		sightAngle 85
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (and (> 2 (GameHour)) (> (GameHour) 10))
					(Print 100 12)
				else
					(Print 100 26)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance aCop of Actor
	(properties
		y 155
		description {that cop}
		lookStr {He appears to be chasing someone!}
		view 104
		signal ignrAct
		xStep 6
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk (Print 100 27))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance doormat of Feature
	(properties
		x 90
		y 139
		nsTop 134
		nsLeft 73
		nsBottom 144
		nsRight 108
		description {the doormat}
		sightAngle 40
		approachX 89
		approachY 142
		lookStr {"Welcome"}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 100 28)
				(Print 100 29)
				(Print 100 30 #at -1 140)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance cans of Feature
	(properties
		x 307
		y 118
		nsTop 99
		nsLeft 295
		nsBottom 137
		nsRight 319
		description {the trash cans}
		sightAngle 40
		approachX 305
		approachY 140
		lookStr {Can can._}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 100 31))
			(verbTaste (Print 100 32))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance soundFX of Sound
	(properties
		flags mNOPAUSE
		number 104
	)
)
