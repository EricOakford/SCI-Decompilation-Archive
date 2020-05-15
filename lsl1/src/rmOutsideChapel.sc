;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmOutsideChapel) ;400
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
(use Invent)
(use Actor)
(use System)

(public
	rm400 0
)

(instance rm400 of LLRoom
	(properties
		lookStr {You are outside Lost Wages' finest instant marriage center, the "Quiki-Wed" Wedding Chapel,
		 another in the vast conglomerate that has become the fabulous "Quiki" empire.  An interesting form resides beneath that light pole.}
		picture rmOutsideChapel
		north rmInsideChapel
		east rmDarkAlley
		west rmOutsideCasino
	)
	
	(method (init)
		(LoadMany SOUND 301 400 401 801 802)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 254 137 310 137 310 145 254 145
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0 319 0 319 128 258 131 207 131 196 134 162 135
						92 131 36 131 36 134 63 134 63 138 0 138
					yourself:
				)
		)
		(soundFx loop: 1 flags: mNOPAUSE)
		(switch prevRoomNum
			(rmOutsideCasino (self style: SCROLLLEFT))
			(rmInsideChapel
				(if (Btst fMarriedFawn)
					(self setScript: sWhereIsShe)
				else
					(HandsOff)
					(self setScript: sFromDoor)
				)
			)
			(rmDarkAlley (ego init: y: 130))
			(rmInsideTaxi 0)
			(else  (ego posn: 160 140))
		)
		(ego init:)
		(flasher init: stopUpd: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(quikiSign cycleSpeed: howFast setCycle: Forward init:)
		(billionSign init:)
		(reflection cycleSpeed: howFast setCycle: Forward init:)
		(bigFountain cycleSpeed: howFast setCycle: Forward init:)
		(smallFountain cycleSpeed: howFast setCycle: Forward init:)
		(flasherOpenSFX init:)
		(flasherCloseSFX init:)
		(super init:)
		(fountainSFX init: play:)
		(self setRegions: rgSidewalk)
		(doors init: stopUpd:)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(
				(and
					(== (flasher cel?) 0)
					(ego inRect: 250 130 300 140)
				)
				(flasher setCycle: EndLoop flasher)
				(flasherOpenSFX play:)
			)
			(
				(and
					(== (flasher cel?) 3)
					(not (ego inRect: 250 130 300 140))
				)
				(flasher setCycle: BegLoop flasher)
				(flasherCloseSFX play:)
			)
		)
	)
)

(instance sEnterDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setHeading: 0)
				(doors setCycle: EndLoop self)
				(soundFx setLoop: 1 number: 801 play:)
			)
			(1
				(ego setMotion: MoveTo 217 125 self)
			)
			(2
				(soundFx number: 802 play:)
				(doors setCycle: BegLoop self)
			)
			(3 (curRoom newRoom: 410))
		)
	)
)

(instance sFromDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(doors cel: 3)
				(ego posn: 217 125 setMotion: MoveTo 217 135 self)
			)
			(1
				(soundFx number: 802 play:)
				(doors setCycle: BegLoop self)
			)
			(2
				(doors stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sWhereIsShe of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(doors cel: 3)
				(ego posn: 217 125 setMotion: MoveTo 217 135 self)
			)
			(1 (doors setCycle: BegLoop self))
			(2
				(Print 400 0 #at -1 20)
				(doors stopUpd:)
				(ego view: 811 setLoop: 0 setCel: 10 setCycle: EndLoop self)
			)
			(3 (NormalEgo 2) (= cycles 1))
			(4
				(Bclr 11)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sLookFlasher of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setHeading:
						(GetAngle (ego x?) (ego y?) (flasher x?) (flasher y?))
						self
				)
			)
			(1 (= cycles 2))
			(2
				(if (!= (flasher cel?) 0)
					(Print 400 1)
				else
					(Print 400 2)
				)
				(HandsOn)
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

(instance flasherOpenSFX of Sound
	(properties
		number 400
	)
)

(instance flasherCloseSFX of Sound
	(properties
		number 401
	)
)

(instance doors of Prop
	(properties
		x 216
		y 128
		description {the doors}
		sightAngle 40
		lookStr {"Through these doors walk the finest suckers in the world!"}
		view 400
		loop 4
		signal ignrAct
		cycleSpeed 1
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(ego setMotion: PolyPath 217 131 self)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(HandsOff)
		(curRoom setScript: sEnterDoor)
	)
)

(instance bigFountain of Prop
	(properties
		x 78
		y 126
		description {the chapel fountain}
		sightAngle 40
		lookStr {Between the Casino and the Quiki-Wed Chapel a lovely fountain gurgles gently in this good night. Above it, a pair of cherubs frolic in a tasteful sculpture.}
		view 400
		loop 2
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 400 3)
			)
			(verbTaste
				(Print 400 4)
			)
			(verbZipper
				(Print 400 5)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance smallFountain of Prop
	(properties
		x 9
		y 189
		description {the small fountain}
		sightAngle 40
		lookStr {Most of that fountain is in the next scene.}
		view 400
		loop 3
		priority 15
		signal fixPriOn
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance quikiSign of Prop
	(properties
		x 210
		y 40
		description {the Quiki-Wed sign}
		sightAngle 40
		lookStr {How romantic -- service with a smile!}
		view 400
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance billionSign of Feature
	(properties
		x 220
		y 128
		z 92
		nsTop 8
		nsLeft 170
		nsBottom 65
		nsRight 270
		description {the Quiki-Wed sign}
		sightAngle 40
		lookStr {How romantic -- "Over 1 Billion Served!"}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance reflection of Prop
	(properties
		x 78
		y 93
		description {the chapel fountain}
		view 400
		loop 1
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(bigFountain doVerb: theVerb theItem)
	)
)

(instance flasher of Person
	(properties
		x 276
		y 143
		description {the flasher}
		sightAngle 40
		approachX 251
		approachY 131
		view 402
		signal $4000
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(HandsOff)
				(curRoom setScript: sLookFlasher)
			)
			(verbTalk
				(if (!= (flasher cel?) 0)
					(Print 400 6)
				else
					(Print 400 7)
					(Print 400 8)
				)
				(SolvePuzzle fTalkedToFlasher 1)
			)
			(verbDo
				(Print 400 9 #at -1 120)
				(Print 400 9 #at -1 160)
			)
			(verbUse
				(Printf 400 10 ((Inventory at: theItem) description?))
			)
			(verbZipper
				(Print 400 11 #at -1 120)
				(Print 400 12 #at -1 160)
			)
			(verbTaste
				(Print 400 13)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(self stopUpd:)
	)
)
