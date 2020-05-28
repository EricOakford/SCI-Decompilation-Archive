;;; Sierra Script 1.0 - (do not remove this comment)
(script# 320)
(include game.sh)
(use Main)
(use butte)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Sound)
(use Motion)
(use System)

(public
	rm320 0
)

(local
	[local0 7]
	[babe2Cycle 25] = [5 0 124 82 5 1 116 90 5 2 110 93 5 3 112 95 5 4 112 95 5 5 113 95 -32768]
	[babe3Cycle 37] = [6 0 295 110 6 1 277 113 6 2 265 118 6 3 258 132 6 4 259 132 6 5 259 132 6 6 259 132 6 7 259 132 6 8 259 132 -32768]
	[subCycle 17] = [1 0 131 152 1 1 131 150 1 2 131 148 1 0 131 146 -32768]
	[subTopCycle 17] = [3 1 135 152 3 2 135 150 3 3 135 148 3 4 135 146 -32768]
)
(instance rm320 of SQRoom
	(properties
		picture 320
		horizon 15
		north 310
	)
	
	(method (init)
		(LoadMany VIEW 0 300)
		(LoadMany SOUND 143 51 57 132 133 115 883 884 887 889)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						319 189 0 189 0 0 165 0 245 126 218 147 174 145
						171 133 142 144 191 158 193 170 297 146 315 132
						310 116 294 115 295 110 304 106 298 94 310 90
						299 59 303 52 298 49 303 47 308 38 319 29
					yourself:
				)
				((Polygon new:)
					type: PNearestAccess
					init: 319 26 307 33 270 5 272 0 319 0
					yourself:
				)
				((Polygon new:)
					type: PNearestAccess
					init: 200 0 226 29 252 46 271 83 247 123 176 0
					yourself:
				)
		)
		(globalSound number: 133 loop: 1)
		(music number: 57 flags: mNOPAUSE loop: -1)
		(if (> (butte policeLanded?) 0)
			(Load VIEW 305)
			(Load PICTURE 300)
		else
			(LoadMany VIEW 7 5)
		)
		(if (and (== prevRoomNum 297) (not (Btst fMeetLatexBabes)))
			(babe2 init: z: 1000)
			(babe3 init: z: 1000)
			(landSFX init:)
			(Load SCRIPT 810)
		)
		(self setRegions: BUTTE)
		(theSea init:)
		(theRoom init:)
		(if
			(or
				(and (== prevRoomNum north) (butte egoSwims?))
				(== prevRoomNum 297)
			)
			(HandsOff)
			(Load VIEW 323)
			(butte egoSwims: 0)
			(ego
				view: 323
				normal: 0
				loop: 1
				posn:
					(cond 
						((< (ego x?) 51) 45)
						((> (ego x?) 128) 128)
						(else (ego x?))
					)
					150
			)
			(if (== prevRoomNum north) (self style: 13))
			(self setScript: swimScript)
		else
			(HandsOff)
			(ego illegalBits: -32752)
			(self style: 13 setScript: enterScript)
		)
		(ego setPri: 8 init:)
		(super init:)
		(if
			(or
				(== (butte curPolice1Room?) 320)
				(== (butte curPolice2Room?) 320)
			)
			(butte junctioned: TRUE)
			((ScriptID BUTTE 4)
				posn: 255 153
				init:
				setScript: copEnters
			)
		else
			(butte junctioned: FALSE)
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script)
			(
			(and (== script fallScript) (== (ego edgeHit?) SOUTH)) 0)
			((and (not script) (== (ego edgeHit?) NORTH))
				(HandsOff)
				(ego illegalBits: cWHITE)
				(self setScript: exitScript)
			)
		)
		(super doit:)
		(= temp0 (ego onControl: origin))
		(cond 
			(script)
			(
				(and
					(butte junctioned?)
					(not ((ScriptID BUTTE 4) script?))
				)
				(if (== (butte oldPoliceRoom?) 310)
					((ScriptID BUTTE 4) posn: 270 -5 init:)
				)
				((ScriptID BUTTE 4) setScript: copEnters)
			)
			((or (& temp0 $0004) (& temp0 $0040)) (HandsOff) (self setScript: fallScript 0 temp0))
		)
	)
)

(instance fallScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((& register $0004) (ego setPri: 3))
					((& register $0040) (ego setPri: 3))
				)
				(curRoom setScript: (ScriptID BUTTE 1))
				(self dispose:)
			)
		)
	)
)

(instance exitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (ego x?) -2 self)
			)
			(1
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					x:
						(cond 
							((< (ego x?) 250) 250)
							((> (ego x?) 291) 291)
							(else (ego x?))
						)
					y: 50
				)
				(= cycles 1)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance copEnters of Script
	(properties)
	
	(method (doit)
		(if (and (not (curRoom script?)) (== state 0))
			(self cue:)
		)
		(if
			(and
				(not (curRoom script?))
				(< ((ScriptID BUTTE 4) distanceTo: ego) 80)
			)
			((ScriptID BUTTE 4) setScript: (ScriptID BUTTE 5))
			(self dispose:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				((ScriptID BUTTE 4)
					setCycle: Walk
					setMotion: MoveTo 300 100 self
				)
			)
			(2
				((ScriptID BUTTE 4)
					setMotion: PolyPath (ego x?) (ego y?) self
				)
			)
		)
	)
)

(instance swimScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(globalSound number: 883 loop: -1 vol: 127 flags: 0 play:)
				(ego
					illegalBits: 0
					setCycle: Walk
					setLoop: -1
					setMotion: MoveTo 160 154 self
				)
			)
			(2
				(globalSound number: 0 stop:)
				(ego
					setLoop: 1
					cel: 0
					setCycle: EndLoop
					xStep: 2
					setMotion: MoveTo 185 (ego y?) self
				)
			)
			(3
				(NormalEgo 0 0)
				(= cycles 30)
			)
			(4
				(ego
					setPri: 8
					xStep: 3
					yStep: 2
					setMotion: MoveTo 205 (ego y?) self
				)
			)
			(5
				(cond 
					((!= prevRoomNum 297) (HandsOn) (client setScript: 0))
					((not (Btst fMeetLatexBabes)) (Bset fMeetLatexBabes) (music play:) (= seconds 3))
					(else (HandsOn) (client setScript: 0))
				)
			)
			(6
				(curRoom setScript: subRiseScript)
			)
		)
	)
)

(instance subRiseScript of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (> state 2)
			(if (> (ego x?) 206)
				(HandsOff)
				(curRoom setScript: shootScript)
			)
			(if (!= (ego y?) 154)
				(HandsOff)
				(curRoom setScript: shootScript)
			)
			(if (< (ego x?) 204)
				(HandsOff)
				(curRoom setScript: enterSubScript)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego moveHead: 0 setHeading: 270)
				(babe2 z: 0 setCycle: MoveCycle @babe2Cycle)
				(babe3 z: 0 setCycle: MoveCycle @babe3Cycle self)
			)
			(1 (= seconds 4))
			(2
				(sub
					setLoop: 1
					setCycle: Forward
					init:
					setMotion: MoveTo 131 154 self
				)
			)
			(3
				(globalSound number: 884 loop: 1 vol: 127 play:)
				(sub setCycle: MoveCycle @subCycle)
				(subTop init: setCycle: MoveCycle @subTopCycle self)
			)
			(4
				(sub setCycle: Forward)
				(= seconds 4)
			)
			(5
				(hatch
					init:
					x: (subTop x?)
					y: (subTop y?)
					setPri: (+ (subTop priority?) 1)
					setCycle: EndLoop
				)
				(hatchSnd number: 133 loop: 1 init: play:)
				(= seconds 3)
			)
			(6
				(babeLeader init: cycleSpeed: 12 setCycle: CycleTo 5 1 self)
			)
			(7
				(babeLeader setCycle: CycleTo 8 1)
				(= cycles 9)
			)
			(8
				(theIconBar disable:)
				(= cycles 1)
			)
			(9
				(bubble z: 2000)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 325 FADEOUT)
				(arm init:)
				(theMouth init:)
				(theEyes init:)
				(zondraTalker init: 0 theEyes theMouth)
				(= cycles 1)
			)
			(10
				(theIconBar enable:)
				(= seconds 2)
			)
			(11
				(arm setCel: 1 stopUpd:)
				(= seconds 2)
			)
			(12
				(zondraTalker
					say: 1 self 2
						#at 2 2
						#color (VGAOrEGA myTextColor23 myTextColor7)
						#back myLowlightColor
						#mode teJustCenter
						#width 315
				)
			)
			(13
				(zondraTalker
					say:
						2
						self
						2
						64
						2
						2
						25
						(VGAOrEGA myTextColor23 myTextColor7)
						26
						myLowlightColor
						27
						1
						67
						315
				)
			)
			(14
				(zondraTalker
					say:
						3
						self
						2
						64
						2
						2
						25
						(VGAOrEGA myTextColor23 myTextColor7)
						26
						myLowlightColor
						27
						1
						67
						315
				)
			)
			(15
				(thoreenTalker
					init: 0 0 0
					say: 1 self 2 64 270 125 25 myTopBordColor 26 myLowlightColor 27 0 67 48
				)
				(if (& msgType $0001)
					(vibraTalker
						init: 0 0 0
						say: 1 0 2 64 3 160 25 myTextColor20 26 myLowlightColor 27 0 67 315
					)
				)
			)
			(16
				(if (& msgType $0001) (vibraTalker dispose:))
				(zondraTalker
					say:
						4
						self
						2
						64
						2
						2
						25
						(VGAOrEGA myTextColor23 myTextColor7)
						26
						myLowlightColor
						27
						1
						67
						315
				)
			)
			(17
				(rogerTalker
					init: 0 0 0
					say: 1 self 2 64 48 155 25 myTextColor10 26 myLowlightColor 27 0 67 270
				)
			)
			(18
				(zondraTalker
					say:
						5
						self
						2
						64
						2
						2
						25
						(VGAOrEGA myTextColor23 myTextColor7)
						26
						myLowlightColor
						27
						1
						67
						315
				)
			)
			(19
				(rogerTalker
					say: 2 self 2 64 48 155 25 myTextColor10 26 myLowlightColor 27 0 67 270
				)
			)
			(20
				(zondraTalker
					say:
						6
						self
						2
						64
						2
						2
						25
						(VGAOrEGA myTextColor23 myTextColor7)
						26
						myLowlightColor
						27
						1
						67
						315
				)
			)
			(21
				(thoreenTalker
					say: 2 self 2 64 270 125 25 myTopBordColor 26 myLowlightColor 27 0 67 48
				)
				(if (& msgType $0001)
					(vibraTalker
						modNum: 322
						say: 2 0 2 64 3 160 25 myTextColor20 26 myLowlightColor 27 0 67 315
					)
				)
			)
			(22
				(if (& msgType $0001) (vibraTalker dispose:))
				(zondraTalker
					say:
						7
						self
						2
						64
						2
						2
						25
						(VGAOrEGA myTextColor23 myTextColor7)
						26
						myLowlightColor
						27
						1
						67
						315
				)
			)
			(23
				(theIconBar disable:)
				(zondraTalker dispose:)
				(= cycles 1)
			)
			(24
				(cast eachElementDo: #show)
				(curRoom drawPic: 320 FADEOUT)
				(babeLeader loop: 0 cel: 0 setPri: 14 posn: 117 144)
				(theMouth dispose:)
				(arm dispose:)
				(theEyes dispose:)
				(= cycles 1)
			)
			(25
				(NormalEgo 2 0 4)
				(EgoHeadMove 4)
				(theIconBar enable:)
				(HandsOn)
			)
		)
	)
)

(instance shootScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(globalSound number: 887 vol: 127 loop: 1 play:)
				(babeLeader
					loop: 0
					cel: 0
					x: 117
					y: 144
					setCycle: EndLoop self
				)
			)
			(1
				(globalSound number: 889 vol: 127 loop: 1 play:)
				(ego
					normal: 0
					view: 321
					loop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(Animate (cast elements?) FALSE)
				(EgoDead iconSPEAR deathSPEARED)
			)
		)
	)
)

(instance enterSubScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					setCycle: Walk
					setMotion: PolyPath 179 145
				)
				(= cycles 1)
			)
			(1
				(ego
					normal: 0
					view: 321
					loop: 2
					cel: 0
					posn: 179 144
					setPri: 15
					setCycle: EndLoop
				)
				(= cycles 7)
			)
			(2
				(landSFX play:)
				(ego
					illegalBits: 0
					loop: 3
					cel: 0
					posn: 143 144
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(3
				(ego normal: 1 dispose:)
				(babeLeader
					loop: 4
					cel: 0
					posn: 130 144
					setPri: 15
					setCycle: EndLoop self
				)
			)
			(4
				(babeLeader dispose:)
				(hatch setCycle: BegLoop self)
			)
			(5
				(hatchSnd number: 132 play:)
				(hatch dispose:)
				(= cycles 1)
			)
			(6
				(music number: 51 loop: -1 flags: 1 play:)
				(subTop setCycle: BegLoop)
				(subTop dispose:)
				(sub setMotion: MoveTo 131 200)
				(= seconds 3)
			)
			(7 (sub dispose:) (= cycles 1))
			(8
				(curRoom newRoom: 321)
				(self dispose:)
			)
		)
	)
)

(instance babeLeader of Sq4Actor
	(properties
		x 142
		y 122
		sightAngle 90
		view 321
		loop 7
		priority 15
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 1))
			(V_DO (narrator say: 2))
			(V_TALK (narrator say: 3))
			(V_SMELL (narrator say: 4))
			(V_TASTE (narrator say: 5))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance landSFX of Sound
	(properties
		number 115
		priority 15
	)
)

(instance babe2 of Sq4Prop
	(properties
		x 126
		y 92
		view 321
		loop 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 6))
			(V_DO (narrator say: 7))
			(V_TALK (narrator say: 3))
			(V_SMELL (narrator say: 8))
			(V_TASTE (narrator say: 9))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance babe3 of Sq4Prop
	(properties
		x 298
		y 121
		view 321
		loop 6
		priority 14
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 10))
			(V_DO (narrator say: 11))
			(V_TALK (narrator say: 12))
			(V_SMELL (narrator say: 8))
			(V_TASTE (narrator say: 13))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sub of Sq4Actor
	(properties
		x 120
		y 200
		sightAngle 90
		yStep 3
		view 320
		loop 1
		priority 12
		signal ignrAct
		cycleSpeed 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 14))
			(V_DO
				(curRoom setScript: enterSubScript)
			)
			(V_SMELL (narrator say: 15))
			(V_TASTE (narrator say: 16))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance subTop of Sq4Actor
	(properties
		x 135
		y 154
		sightAngle 90
		view 320
		loop 3
		priority 13
		signal (| ignrAct fixPriOn)
		cycleSpeed 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 14))
			(V_DO
				(curRoom setScript: enterSubScript)
			)
			(V_TALK)
			(V_SMELL (narrator say: 15))
			(V_TASTE (narrator say: 16))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance hatch of Sq4Prop
	(properties
		sightAngle 90
		view 320
		loop 2
		signal ignrAct
		cycleSpeed 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 17))
			(V_DO
				(curRoom setScript: enterSubScript)
			)
			(V_SMELL (narrator say: 15))
			(V_TASTE (narrator say: 18))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bubble of Sq4Prop
	(properties
		x 130
		y 184
		sightAngle 90
		view 320
		priority 15
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 19))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance arm of Sq4Prop
	(properties
		x 171
		y 104
		view 1325
	)
)

(instance theMouth of Sq4Prop
	(properties
		x 171
		y 60
		view 1325
		loop 2
		signal ignrAct
	)
)

(instance theEyes of Sq4Prop
	(properties
		x 172
		y 42
		view 1325
		loop 4
		signal ignrAct
	)
)

(instance zCode of Code
	(properties)
	
	(method (doit param1)
		(param1 z: (- (param1 z?) 1000))
	)
)

(instance theRoom of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				((ScriptID BUTTE 6) doVerb: theVerb)
			)
			(V_LOOK (narrator say: 20))
			(else  (super doVerb:))
		)
	)
)

(instance theSea of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		onMeCheck $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 21))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance hatchSnd of Sound
	(properties)
)

(instance zondraTalker of FaceTalker
	(properties
		noun ZONDRA
		modNum 322
		talkerNum ZONDRA
	)
)

(instance rogerTalker of Sq4Narrator
	(properties
		noun ROGER
		modNum 322
		disposeWhenDone 0
		talkerNum ROGER
	)
)

(instance thoreenTalker of Sq4Narrator
	(properties
		noun 20
		modNum 322
		disposeWhenDone 0
		modeless TRUE
		talkerNum 20
	)
)

(instance vibraTalker of Sq4Narrator
	(properties
		noun 20
		modNum 322
		disposeWhenDone 0
		modeless TRUE
		talkerNum 20
	)
)
