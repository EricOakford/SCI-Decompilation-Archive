;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmOutsideDisco) ;600
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use RandCyc)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm600 0
)

(local
	local0
	nearBouncer
	local2
	[bouncerStepAsideCycle 33] = [1 1 184 136 1 2 180 136 1 3 173 136 1 4 173 136 1 5 173 136 1 6 173 136 1 7 173 136 1 8 173 136 -32768]
	[bouncerStopCycle 29] = [1 7 173 136 1 6 173 136 1 5 173 136 1 4 173 136 1 3 173 136 1 2 180 136 1 1 184 136 -32768]
)
(instance rm600 of LLRoom
	(properties
		picture rmOutsideDisco
		north rmInsideDisco
		east rmDarkAlley
		west rmOutside7_11
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 22 146 55 146 55 155 22 155
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 241 143 265 143 265 147 241 147
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 0 0 168 0 168 122 188 127 200 141 112 141 94 137 60 137 57 132 0 132
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 192 122 192 0 319 0 319 117 277 117 280 140 208 140
					yourself:
				)
		)
		(switch prevRoomNum
			(rmOutside7_11
				(self style: SCROLLLEFT)
				(if (< (ego y?) 135) (ego y: 135))
			)
			(rmInsideDisco
				(HandsOff)
				(soundFx fade:)
				(ego init: posn: 190 64)
				(self setScript: sFromDisco)
			)
			(rmDarkAlley (ego y: 145))
			(rmInsideTaxi 0)
			(else  (ego posn: 160 160))
		)
		(ego init:)
		(discoThump play:)
		(bouncer
			cycleSpeed: howFast
			init:
			approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk verbTaste verbZipper
		)
		(firePlug init:)
		(disco init:)
		(darkAlley init:)
		(artGallery init:)
		(lights init:)
		(super init:)
		(self setRegions: rgSidewalk)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script)
			((< (ego y?) 122)
				(if (> (ego x?) 240)
					(curRoom newRoom: 170)
				else
					(HandsOff)
					(self setScript: sToDisco)
				)
			)
		)
		(if
			(and
				(== nearBouncer FALSE)
				(ego inRect: 140 137 225 145)
				(== (curRoom script?) 0)
			)
			(= nearBouncer TRUE)
			(curRoom setScript: sBouncer)
		)
		(if
			(and
				nearBouncer
				(not (ego inRect: 140 137 225 145))
				(== (curRoom script?) 0)
			)
			(= nearBouncer FALSE)
		)
	)
	
	(method (dispose)
		(theMusic fade:)
		(super dispose:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print rmOutsideDisco 0)
				(Print rmOutsideDisco 1 #at -1 140)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sFromDisco of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bouncer setLoop: 1 setCel: 8 x: 173 y: 136)
				(= cycles 1)
			)
			(1
				(ego
					ignoreActors: TRUE
					posn: 190 64
					setMotion: PolyPath 190 150 self
				)
			)
			(2
				(HandsOn)
				(ego ignoreActors: 0)
				(bouncer setCycle: MoveCycle @bouncerStopCycle self)
			)
			(3
				(bouncer setLoop: 0 x: 174 y: 136 setCel: 0)
				(self dispose:)
			)
		)
	)
)

(instance sToDisco of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 177 64 self)
			)
			(1 (curRoom newRoom: 610))
		)
	)
)

(instance sBouncer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(Face ego bouncer)
				(bouncerHead
					init:
					cycleSpeed: howFast
					setCycle: RandCycle 20 self
				)
			)
			(1
				(Print rmOutsideDisco 2 #at -1 20)
				(Print rmOutsideDisco 3 #at -1 20)
				(= cycles 1)
			)
			(2
				(ego setMotion: PolyPath (ego x?) 146 self)
			)
			(3
				(HandsOn)
				(bouncerHead dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sCard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego bouncer)
				(= cycles 10)
			)
			(1
				(ego moveHead: 0)
				(SolvePuzzle fShowDiscoPass 5)
				(Print rmOutsideDisco 4)
				(= cycles 10)
			)
			(2
				(ego
					view: 601
					setLoop: 0
					setCel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(3 (= cycles 10))
			(4 (Print rmOutsideDisco 5) (= cycles 10))
			(5 (ego setCycle: BegLoop self))
			(6
				(bouncerHead
					init:
					cycleSpeed: howFast
					setCycle: RandCycle 20 self
				)
				(bouncer
					setLoop: 1
					setCel: 0
					x: 184
					y: 136
					setCycle: CycleTo 1
				)
			)
			(7
				(Print rmOutsideDisco 6 #at -1 20)
				(= cycles 10)
			)
			(8
				(bouncerHead dispose:)
				(bouncer setCycle: MoveCycle @bouncerStepAsideCycle self)
			)
			(9
				(NormalEgo)
				(ego ignoreActors: TRUE setMotion: PolyPath 190 90 self)
			)
			(10
				(ego moveHead: 1)
				(= cycles 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sDiamond of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego bouncer)
				(= cycles 1)
			)
			(1 (Print rmOutsideDisco 7) (= cycles 1))
			(2
				(bouncerHead
					init:
					cycleSpeed: (+ 2 howFast)
					setCycle: RandCycle 20 self
				)
			)
			(3
				(Print rmOutsideDisco 8 #at -1 20)
				(bouncerHead dispose:)
				(= cycles 1)
			)
			(4
				(bouncer
					setLoop: 1
					setCel: 1
					x: 184
					y: 136
					setCycle: MoveCycle @bouncerStepAsideCycle self
				)
			)
			(5
				(ego ignoreActors: TRUE setMotion: PolyPath 190 90 self)
			)
			(6 (self dispose:))
		)
	)
)

(instance bouncerHead of Prop
	(properties
		view 602
		loop 2
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(super init:)
		(if (== (bouncer loop?) 0)
			(bouncerHead
				x: (+ (bouncer x?) 2)
				y: (bouncer y?)
				z: 39 ;species
			)
		else
			(bouncerHead
				x: (- (bouncer x?) 8)
				y: (bouncer y?)
				z: 39 ;species
			)
		)
	)
)

(instance bouncer of Person
	(properties
		x 174
		y 136
		description {Pickhinke}
		sightAngle 20
		approachX 190
		approachY 153
		view 602
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (== prevRoomNum rmInsideDisco)
					(Print rmOutsideDisco 9)
				else
					(Print rmOutsideDisco 10)
				)
			)
			(verbDo
				(Print rmOutsideDisco 11)
				(Print rmOutsideDisco 12 #at -1 140)
			)
			(verbTalk
				(if (== prevRoomNum rmInsideDisco)
					(Print rmOutsideDisco 13 #at -1 20)
					(Print rmOutsideDisco 14 #at -1 140)
				else
					(Print rmOutsideDisco 15)
					(Print rmOutsideDisco 16 #at -1 20)
					(Print rmOutsideDisco 17 #at -1 140)
				)
			)
			(verbZipper
				(Print rmOutsideDisco 18)
				(Print rmOutsideDisco 19 #at -1 20)
			)
			(verbTaste (Print rmOutsideDisco 20))
			(verbUse
				(switch theItem
					(iWallet
						(Print rmOutsideDisco 21)
						(Print rmOutsideDisco 22 #at -1 20)
					)
					(iBreathSpray
						(Print rmOutsideDisco 23)
						(Print rmOutsideDisco 24 #at -1 20)
					)
					(iWatch
						(Print rmOutsideDisco 25)
						(Print rmOutsideDisco 26 #at -1 20)
					)
					(iApple
						(Print rmOutsideDisco 27)
						(Print rmOutsideDisco 28 #at -1 20)
					)
					(iRing
						(ego put: iRing)
						(curRoom setScript: sDiamond)
					)
					(iWhiskey
						(Print rmOutsideDisco 29)
						(Print rmOutsideDisco 30 #at -1 20)
					)
					(iRose
						(Print rmOutsideDisco 31)
						(Print rmOutsideDisco 32)
					)
					(iLubber
						(Print rmOutsideDisco 33)
						(Print rmOutsideDisco 34)
					)
					(iCandy
						(Print rmOutsideDisco 35)
						(Print rmOutsideDisco 36 #at -1 20)
					)
					(iPocketKnife (Print rmOutsideDisco 37))
					(iWine
						(Print rmOutsideDisco 29)
						(Print rmOutsideDisco 38 #at -1 20)
					)
					(iMagazine
						(Print rmOutsideDisco 39)
						(Print rmOutsideDisco 40 #at -1 20)
					)
					(iPills
						(Print rmOutsideDisco 41)
						(Print rmOutsideDisco 42)
						(Print rmOutsideDisco 43 #at -1 140)
					)
					(iDiscoPass (curRoom setScript: sCard))
					(iGraffiti (Print rmOutsideDisco 44))
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

(instance firePlug of Feature
	(properties
		x 39
		y 141
		nsTop 129
		nsLeft 29
		nsBottom 153
		nsRight 50
		description {the fire plug}
		sightAngle 40
		lookStr {Why doesn't that dog use this fireplug instead of you?}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print rmOutsideDisco 45))
			(verbZipper (Print rmOutsideDisco 45))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance disco of Feature
	(properties
		x 170
		y 30
		nsLeft 82
		nsBottom 139
		nsRight 258
		description {the disco sign}
		sightAngle 40
		lookStr {You are outside a flashy disco with an unhappy bouncer.}
	)
)

(instance lights of Feature
	(properties
		x 176
		y 36
		nsTop 16
		nsLeft 81
		nsBottom 56
		nsRight 272
		description {the lights}
		sightAngle 40
		lookStr {Gaudy, aren't they?}
	)
)

(instance artGallery of Feature
	(properties
		x 39
		y 65
		nsBottom 131
		nsRight 79
		description {the art gallery}
		sightAngle 40
		lookStr {The Lost Wages Art Emporium will satisfy your every need for modern art, as long as you're looking for something with dead fish!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print rmOutsideDisco 46))
			(verbTalk (Print rmOutsideDisco 47))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance darkAlley of Feature
	(properties
		x 294
		y 136
		z 69
		nsLeft 270
		nsBottom 135
		nsRight 319
		description {the dark alley}
		sightAngle 40
		lookStr {You're not sure, but you think you hear cries of "Help" coming from that dark alley over there.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk (Print rmOutsideDisco 48))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance discoThump of Sound
	(properties
		flags mNOPAUSE
		number rmOutsideDisco
		loop -1
	)
)
