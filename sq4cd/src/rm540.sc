;;; Sierra Script 1.0 - (do not remove this comment)
(script# 540)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use IconBar)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use StopWalk)
(use Sound)
(use Motion)
(use Invent)
(use System)

(public
	rm540 0
)

(local
	[rogerJrCycle 37] = [0 0 155 138 0 1 155 138 0 1 155 138 0 1 155 138 0 2 155 138 0 3 155 138 0 4 155 138 0 5 155 138 0 6 155 138 -32768]
	[blastCycle 33] = [0 0 0 0 1 0 145 108 1 1 145 108 1 2 145 108 1 3 145 108 1 4 145 107 1 5 149 101 0 0 0 0 -32768]
	policeTimer
)
(procedure (PoliceFire param1 param2 param3 param4 &tmp temp0 temp1 temp2)
	(aSound number: 105 loop: 1 vol: 127 play:)
	(if (< param1 param3)
		(= temp0 (- param1 1))
		(= temp1 (+ param3 1))
	else
		(= temp0 (- param3 1))
		(= temp1 (+ param1 1))
	)
	(= temp2
		(Graph
			GSaveBits
			temp0
			(- param2 1)
			temp1
			(+ param4 1)
			1
		)
	)
	(Graph
		GDrawLine
		param1
		param2
		param3
		param4
		myTextColor13
		-1
		-1
	)
	(Graph
		GReAnimate
		temp0
		(- param2 1)
		temp1
		(+ param4 1)
	)
	(Wait 1)
	(Wait 4)
	(Graph GRestoreBits temp2)
	(Graph
		GReAnimate
		temp0
		(- param2 1)
		temp1
		(+ param4 1)
	)
)

(instance aSound of Sound
	(properties)
)

(instance rm540 of SQRoom
	(properties
		picture 540
		north 541
		west 535
		vanishingX 0
		vanishingY -60
	)
	
	(method (init)
		(LoadMany VIEW 515 300 7 13 511)
		(if (!= prevRoomNum 556)
			(LoadMany SOUND 105 131 124 154 155 820 825)
		)
		(tunnel init:)
		(switch prevRoomNum
			(541
				(self setScript: enterScript)
			)
			(556
				(LoadMany VIEW 558 556)
				(LoadMany SOUND 821 871)
				(ego moveHead: 0 setLoop: 0 x: 115 y: 142 init:)
				(NormalEgo 0 0 4)
				(EgoHeadMove 4)
				(rogerJr init:)
				(globalSound number: 871 vol: 127 loop: -1 flags: 1)
				(self setScript: endScript)
			)
			(west
				(self style: SCROLLLEFT setScript: enterFromWest 0 (Btst fMeetLatexBabes))
			)
			(else  (ego x: 160 y: 160))
		)
		(if (!= prevRoomNum 541)
			(drBottom init: stopUpd:)
			(drTop init: stopUpd:)
		)
		(ego
			view: 0
			headView: 4
			normal: 1
			moveHead: 1
			setCycle: StopWalk 4
			init:
		)
		(super init:)
		(self setRegions: LANDING)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 0 151 53 151 59 189 0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0 319 0 319 189 241 135 254 135 231 121 271
						108 210 56 193 56 215 86 163 86 159 78 136 78
						147 88 87 88 62 53 0 53
					yourself:
				)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((< (ego x?) 28) (curRoom newRoom: 535))
		)
	)
)

(instance police of Sq4Actor
	(properties
		x 259
		y 147
		view 7
		loop 2
		cel 2
	)
	
	(method (init)
		(super init:)
		(= policeTimer 75)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not (-- policeTimer))
				(!= (curRoom script?) (ScriptID 709 1))
			)
			(curRoom setScript: 0)
			(self setScript: policeScript)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 1))
			(V_TALK (= policeTimer 1))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance drTop of Sq4Actor
	(properties
		x 141
		y 77
		z 22
		sightAngle 90
		view 515
		priority 3
		signal (| ignrAct ignrHrz fixPriOn fixedLoop)
		illegalBits $0000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst 7) (narrator say: 2) else (narrator say: 3))
			)
			(V_DO
				(HandsOff)
				(curRoom setScript: drScript)
			)
			(V_JAR
				(cond 
					(
					(and (== ((Inventory at: iJar) cel?) 2) (not (Btst fMeltedLock))) (HandsOff) (curRoom setScript: acidScript))
					((== ((Inventory at: iJar) cel?) 2) (narrator say: 4))
					(else (super doVerb: theVerb))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance drBottom of Sq4Actor
	(properties
		x 141
		y 76
		view 515
		cel 1
		priority 2
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		illegalBits $0000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fMeltedLock) (narrator say: 2) else (narrator say: 3))
			)
		)
	)
)

(instance enterFromWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 5 setMotion: MoveTo 50 (ego y?) self)
				(if (not register) (police init:))
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 4])
		(switch (= state newState)
			(0
				(HandsOff)
				(globalSound number: 535 loop: -1 playBed:)
				(ego
					x: 129
					y: 70
					setLoop: 4
					illegalBits: 0
					setMotion: PolyPath 165 96 self
				)
			)
			(1
				(blopSound init: number: 154 vol: 127 loop: -1 play:)
				(drTop init: y: 35 setMotion: MoveTo (drTop x?) 77 self)
				(drBottom init: y: 95 setMotion: MoveTo (drBottom x?) 76)
			)
			(2
				(blopSound number: 155 vol: 127 loop: 0 play:)
				(HandsOn)
				(ego setLoop: -1)
				(client setScript: 0)
			)
		)
	)
)

(instance policeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(police setHeading: 315)
				(= cycles 4)
			)
			(1
				(narrator say: 5)
				(police view: 13 setLoop: 1 setCel: 0)
				(= cycles 1)
			)
			(2
				(police setCycle: EndLoop self)
				(PoliceFire
					(- (ego y?) 32)
					(ego x?)
					(- (police y?) 27)
					(police x?)
				)
			)
			(3
				(ego
					view: 511
					setLoop: 0
					setMotion: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(4 (EgoDead iconLASER))
		)
	)
)

(instance drScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 155 78 self)
			)
			(1 (= cycles 5))
			(2
				(if (Btst fMeltedLock)
					(= cycles 1)
				else
					(narrator say: 6)
					(HandsOn)
					(self dispose:)
				)
			)
			(3
				(music number: 124 loop: 0 play: self)
			)
			(4
				(blopSound init: number: 154 vol: 127 loop: -1 play:)
				(drTop setMotion: MoveTo (drTop x?) 37 self)
				(drBottom setMotion: MoveTo (drBottom x?) 107 self)
			)
			(5 0)
			(6
				(blopSound stop: loop: 0)
				(ego setMotion: MoveTo 148 73 self)
			)
			(7
				(ego setMotion: MoveTo 90 35 self)
			)
			(8
				(blopSound number: 154 vol: 127 loop: -1 play:)
				(drBottom setMotion: MoveTo (drBottom x?) 76)
				(drTop setMotion: MoveTo (drTop x?) 77 self)
			)
			(9
				(blopSound number: 155 vol: 127 loop: 0 play: self)
			)
			(10
				(globalSound fade:)
				(curRoom newRoom: 541)
			)
		)
	)
)

(instance acidScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 155 78 self)
			)
			(1
				(music number: 825 loop: 1 play:)
				(blopSound init: vol: 127 loop: 0 play: self)
				(ego normal: 0 view: 515 loop: 1 cel: 0 cycleSpeed: 12)
			)
			(2 (ego setCycle: EndLoop self))
			(3 (= policeTimer 1) (= cycles 5))
			(4
				(music stop:)
				(blopSound dispose:)
				(NormalEgo 3 0)
				(= cycles 2)
			)
			(5
				(narrator say: 7)
				(SolvePuzzle fMeltedLock 10)
				((Inventory at: iJar) cel: 1 cursor: 952)
				((IconBar at: ICON_ITEM) cursor: 952)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance endScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (music fade:) (= cycles 10))
			(1
				(HandsOff)
				(globalSound playBed:)
				(rogerJr setCycle: MoveCycle @rogerJrCycle)
				(= cycles 2)
			)
			(2
				(blast init: setCycle: MoveCycle @blastCycle)
				(= seconds 1)
			)
			(3
				(music number: 821 vol: 127 loop: -1 play:)
				(rip init: setCycle: EndLoop self)
			)
			(4
				(blast dispose:)
				(rip setLoop: 4 setCycle: Forward)
				(= cycles 1)
			)
			(5
				(ego moveSpeed: 6 setMotion: MoveTo 101 124 self)
			)
			(6
				(if (& msgType TEXT_MSG)
					(tROGJR
						modNum: 557
						say: 18 self 2 64 2 2 25 myTextColor20 26 myLowlightColor 27 1 67 315
					)
				else
					(tROGJR2 modNum: 557 say: 18 self)
				)
			)
			(7 (Face ego 115 130 self))
			(8
				(ego moveHead: 0)
				(= seconds 1)
			)
			(9
				(if (& msgType TEXT_MSG)
					(tROGJR
						modNum: 557
						say: 19 self 2 64 2 2 25 myTextColor20 26 myLowlightColor 27 1 67 315
					)
				else
					(tROGJR2 modNum: 557 say: 19 self)
				)
				(= seconds 10)
			)
			(10
				(ego moveHead: 1)
				(Face ego 101 115 self)
			)
			(11
				(ego setLoop: 3)
				(= cycles 1)
			)
			(12
				(ego
					view: 558
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(13
				(music stop:)
				(= seconds 1)
			)
			(14 (curRoom newRoom: 21))
		)
	)
)

(instance rogerJr of Sq4Prop
	(properties
		x 155
		y 139
		view 556
	)
)

(instance blast of Sq4Prop
	(properties
		x 156
		y 110
		view 556
		loop 1
	)
)

(instance rip of Sq4Prop
	(properties
		x 99
		y 93
		view 556
		loop 3
	)
)

(instance tunnel of Sq4Feature
	(properties
		nsBottom 189
		nsRight 319
		sightAngle 180
		onMeCheck $0010
		lookStr 8
	)
)

(instance blopSound of Sound
	(properties
		number 820
		priority 5
	)
)

(instance tROGJR2 of Sq4Talker
	(properties
		noun ROGERJR
		modNum 557
		view 1524
		disposeWhenDone 0
		modeless TRUE
		talkerNum ROGERJR
		mouthOffsetX 21
		mouthOffsetY 32
		eyeOffsetX 25
		eyeOffsetY 20
		tStyle 1
	)
)

(instance tROGJR of Sq4Narrator
	(properties
		noun ROGERJR
		modNum 557
		modeless TRUE
		talkerNum ROGERJR
	)
)
