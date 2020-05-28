;;; Sierra Script 1.0 - (do not remove this comment)
(script# 60)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use RandCyc)
(use Polygon)
(use ForCount)
(use LoadMany)
(use Sound)
(use Motion)
(use System)

(public
	rm060 0
)

(local
	[poly1Pts 16] = [0 163 44 163 62 180 247 180 251 184 319 184 319 189 0 189]
)
(instance rm060 of SQRoom
	(properties
		picture 60
		style FADEOUT
		horizon 130
		north 45
		east 65
		west 55
	)
	
	(method (init)
		(theGame setCursor: normalCursor TRUE)
		(LoadMany VIEW 0 61)
		(LoadMany SOUND 150 802)
		(Load SCRIPT RANDCYC)
		(switch prevRoomNum
			(40
				(HandsOn)
				(ego x: 20 y: (+ horizon (ego yStep?)))
			)
			(50
				(HandsOn)
				(ego x: 300 y: (+ horizon (ego yStep?)))
			)
			(59
				(LoadMany VIEW 62 0 4)
				(LoadMany SOUND 821 822 115)
				(Load SCRIPT FORCOUNT)
				(globalSound
					number: 115
					priority: 15
					vol: 127
					loop: 1
					init:
					changeState:
				)
				(HandsOff)
				(self setScript: ripScript style: 13)
			)
			(55
				(HandsOff)
				(self setScript: enterScript)
				(ego
					y:
						(cond 
							((> (ego y?) 160) 160)
							((< (ego y?) (+ horizon (ego yStep?))) (+ horizon (ego yStep?)))
							(else (ego y?))
						)
				)
				(self setScript: enterScript)
			)
			(65
				(HandsOff)
				(ego
					y:
						(cond 
							((> (ego y?) 175) 172)
							((< (ego y?) (+ horizon (ego yStep?))) (+ horizon (ego yStep?)))
							(else (ego y?))
						)
				)
				(self setScript: enterScript)
			)
			(72
				(ego x: 160 y: (+ horizon (ego yStep?)) setHeading: 180)
			)
			(45
				(HandsOn)
				(ego
					x:
						(cond 
							((> (ego x?) 290) 290)
							((< (ego x?) 30) 30)
							(else (ego x?))
						)
					y: (+ horizon (ego yStep?))
				)
			)
			(else 
				(HandsOn)
				(ego view: 0 x: 155 y: 160 setHeading: 0)
				(music number: 802 loop: -1 vol: 127 flags: 1 playBed:)
			)
		)
		(if (> (ego y?) 175) (ego y: 172))
		(if (!= prevRoomNum 59)
			(lightning1 init: setScript: lightningScript)
			(thunder init:)
		)
		((theBuildings new:) init: onMeCheck: 8192 x: -100)
		((theBuildings new:) init: onMeCheck: 512 x: 160)
		((theBuildings new:) init: onMeCheck: 128 x: 400)
		(theFrontRubble init:)
		(theBackRubble init:)
		(theStreet init:)
		(theSuperComputer init:)
		(theArea init:)
		(thunder init:)
		(poly1 points: @poly1Pts size: 8)
		(if (not (Btst fPoliceLanded)) (self setRegions: STREET))
		(ShowStatus 12)
		(super init:)
		(self setRegions: BUNNY addObstacle: poly1)
		(if (!= prevRoomNum 59) (ego init:))
		(if (== prevRoomNum 72)
			(music number: 802 loop: -1 vol: 127 flags: 1 playBed:)
			(HandsOn)
		)
	)
	
	(method (doit)
		(if
			(and
				(!= curPic 31)
				(not (lightning1 script?))
				(!= script ripScript)
			)
			(lightning1 init: setScript: lightningScript)
		)
		(super doit: &rest)
	)
	
	(method (newRoom newRoomNumber)
		(if (!= script ripScript)
			(super newRoom: newRoomNumber)
		)
	)
)

(instance lightningScript of Script
	(properties)
	
	(method (doit)
		(if (== (curRoom curPic?) 31) (self dispose:))
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 10 25)))
			(1 (= seconds 3))
			(2
				(self changeState: (+ (* (Random 0 1) 2) 3) start: 0)
			)
			(3
				(lightning1 setCycle: RandCycle (Random 1 3) self)
			)
			(4
				(lightning1 setCel: 0)
				(= cycles 1)
			)
			(5
				(lightning2 init: setCycle: RandCycle (Random 3 5) self)
			)
			(6
				(lightning1 setCel: 0 setCycle: BegLoop self)
			)
			(7
				(lightning1 setCel: 0)
				(lightning2 dispose:)
				(= seconds 2)
			)
			(8
				(thunder play:)
				(Animate (cast elements?) FALSE)
				(lightning1 dispose:)
				(self init:)
			)
		)
	)
)

(instance ripScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(globalSound number: 821 loop: -1 vol: 127 play:)
				(rip setLoop: 0 cycleSpeed: 3 init:)
				(rip setCycle: ForwardCounter 7 self)
			)
			(2
				(globalSound number: 822 play:)
				(rip y: 66 setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(3
				(rip setLoop: 2 setCel: 0)
				(rip setCycle: ForwardCounter 4 self)
			)
			(4
				(ego
					normal: 0
					x: 155
					y: 75
					yStep: 7
					view: 62
					setLoop: 3
					setCel: 0
					setCycle: 0
					setPri: 3
					ignoreActors: TRUE
					ignoreHorizon: TRUE
					init:
					setMotion: MoveTo 155 110 self
				)
			)
			(5
				(ego
					setStep: 3 14
					setCel: 0
					setCycle: 0
					setMotion: MoveTo 155 160 self
				)
			)
			(6
				(globalSound
					priority: 15
					vol: 127
					number: 115
					loop: 1
					play:
				)
				(rip setLoop: 1 setCel: 15 setCycle: BegLoop)
				(ego cycleSpeed: 0 setCycle: CycleTo 2 1 self)
			)
			(7
				(rip dispose:)
				(= seconds 2)
			)
			(8
				(ego cycleSpeed: 12 setCycle: EndLoop self)
			)
			(9 (= seconds 2))
			(10
				(ego setCycle: CycleTo 5 -1 self)
			)
			(11
				(globalSound number: 0)
				(ego setLoop: 4 setCel: 0 setCycle: EndLoop self)
			)
			(12
				(music number: 802 loop: -1 flags: 1 playBed:)
				(NormalEgo 2 0)
				(EgoHeadMove)
				(= seconds 3)
			)
			(13
				(ego cycleSpeed: 4 setHeading: 0 self)
			)
			(14 (= seconds 1))
			(15
				(narrator
					nMsgType: -1
					modeless: FALSE
					say: 1 self 0 64 30 35 67 250
				)
			)
			(16
				(narrator say: 2 self 0 64 30 35 67 250)
			)
			(17
				(narrator say: 3 self 0 64 30 30 67 250)
			)
			(18
				(ego setSpeed: 6)
				(lightningScript start: TRUE)
				(lightning1 init: setScript: lightningScript)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch prevRoomNum
					(55
						(ego x: -8 setMotion: MoveTo 10 (ego y?) self)
					)
					(else 
						(ego x: 328 setMotion: MoveTo 310 (ego y?) self)
					)
				)
			)
			(1
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance rip of Sq4Prop
	(properties
		x 154
		y 82
		view 62
		loop 2
		cel 1
		priority 2
		signal fixPriOn
	)
)

(instance lightning1 of Sq4Prop
	(properties
		x 124
		y 15
		view 61
	)
	
	(method (doit)
		(super doit:)
		(if (== (curRoom curPic?) 31)
			(self z: 1000)
		else
			(self z: 0)
		)
	)
)

(instance lightning2 of Sq4Prop
	(properties
		x 152
		y 24
		view 61
		loop 1
		priority 6
		signal fixPriOn
	)
	
	(method (doit)
		(super doit:)
		(if (== (curRoom curPic?) 31)
			(self z: 1000)
		else
			(self z: 0)
		)
	)
)

(instance poly1 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance theFrontRubble of Sq4Feature
	(properties
		x 160
		y 300
		nsBottom 200
		nsRight 320
		sightAngle 45
		onMeCheck $0040
		lookStr 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 4))
			(V_DO (narrator say: 5))
			(V_TASTE (narrator say: 6))
			(V_SMELL (narrator say: 7))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theBackRubble of Sq4Feature
	(properties
		x 155
		y 78
		nsBottom 200
		nsRight 320
		sightAngle 45
		onMeCheck $0010
		lookStr 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 8))
			(V_TASTE (narrator say: 9))
			(V_SMELL (narrator say: 10))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theBuildings of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		sightAngle 45
		lookStr 11
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 11))
			(V_TASTE (narrator say: 12))
			(V_SMELL (narrator say: 13))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theStreet of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		onMeCheck $0004
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (theArea doVerb: V_LOOK))
			(24
				((ScriptID BUNNY 4) doVerb: 24 theItem)
			)
			(V_TASTE (narrator say: 14))
			(V_SMELL (narrator say: 15))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theSuperComputer of Sq4Feature
	(properties
		x 160
		y 3
		nsBottom 200
		nsRight 320
		sightAngle 45
		onMeCheck $0008
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 30 say: 11)
			)
			(V_TASTE (narrator say: 17))
			(V_SMELL (narrator say: 17))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theArea of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 18))
			(24
				((ScriptID 705 4) doVerb: 24 theItem)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance thunder of Sound
	(properties
		number 148
		priority 15
	)
)
