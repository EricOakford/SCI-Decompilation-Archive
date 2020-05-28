;;; Sierra Script 1.0 - (do not remove this comment)
(script# 70)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use Talker)
(use RandCyc)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Sound)
(use Motion)
(use User)
(use System)

(public
	rm070 0
)

(local
	saveBits
	local1
	[drCycle 26] = [5 0 45 127 5 0 53 122 5 0 61 117 5 0 69 112 5 0 77 107 5 0 85 102 -32768]
	local28
)
(procedure (ProfessorSays theString)
	(= saveBits
		(Display theString
			p_width 300
			p_at 6 1
			p_mode teJustLeft
			p_font 69
			p_color myLowlightColor
			p_save
		)
	)
	(Display theString
		p_width 300
		p_at 6 1
		p_mode teJustLeft
		p_font 68
		p_color myTopBordColor
	)
)

(procedure (RestoreDialog)
	(if saveBits (Display 70 0 p_restore saveBits) (= saveBits 0))
)

(instance rm070 of SQRoom
	(properties
		picture 70
	)
	
	(method (init)
		(Load VIEW 70)
		(super init:)
		(aSound init:)
		(if (== ((inventory at: iJar) owner?) 70) (theJar init:))
		(blotter init:)
		(dr init: stopUpd:)
		(grid init:)
		(euroTrip init:)
		(theShadow init:)
		(theDesk init:)
		(theWires init:)
		(thePipes init:)
		(theRoom init:)
		(ego
			init:
			normal: 0
			view: 70
			loop: 0
			cel: 0
			illegalBits: (- cBLUE cWHITE)
			x: 223
			y: -2
			xStep: 3
			yStep: 15
		)
		(HandsOff)
		(self setScript: fallScript)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 166 126 264 129 265 160 137 160
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 275 82 319 10 319 183 296 162
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 9 189 29 174 298 174 316 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 0 162 43 162 49 171 0 171
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 166 73 144 0 319 0 272 77
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 138 0 162 74 87 127 71 127 46 144 56 144 47 151 45 160 0 160 0 0
					yourself:
				)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((and local28 (IsObjectOnControl ego cBLUE)) (curRoom setScript: enterSewer))
		)
	)
)

(instance fallScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(music number: 815 vol: 127 loop: -1 playBed:)
				(ego setLoop: 0 setCycle: 0 setMotion: MoveTo 223 89 self)
			)
			(2
				(aSound init: number: 115 loop: 1 play:)
				(ego cycleSpeed: 12 setCycle: EndLoop self)
			)
			(3
				(NormalEgo 0 0)
				(ego illegalBits: (- cBLUE cWHITE) setHeading: 180)
				(= seconds 2)
			)
			(4
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance drScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: PolyPath 50 150 self)
			)
			(1
				(ego setPri: 13 setHeading: 270 self)
			)
			(2
				(aSound number: 816 play:)
				(ego
					view: 70
					normal: 0
					setLoop: 4
					cel: 0
					illegalBits: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(3
				(aSound play:)
				(ego cel: 0 setCycle: EndLoop self)
			)
			(4
				(aSound play:)
				(ego cel: 0 setCycle: EndLoop self)
			)
			(5
				(ego view: 0 setLoop: 1)
				(= cycles 1)
			)
			(6
				(aSound number: 810 loop: 1 play:)
				(dr setCycle: MoveCycle @drCycle self)
			)
			(7
				(dr setPri: 3 stopUpd:)
				(= local28 1)
				(aSound number: 811 play:)
				(= seconds 1)
			)
			(8
				(HandsOn)
				(NormalEgo 1)
				(ego illegalBits: cWHITE)
				(features
					addToFront: drOpening
					eachElementDo: #init
					doit:
				)
				(self dispose:)
			)
		)
	)
)

(instance buttonScript of Script
	(properties)
	
	(method (init)
		(if (not (keyDownHandler contains: self))
			(keyDownHandler addToFront: self)
		)
		(if (not (mouseDownHandler contains: self))
			(mouseDownHandler addToFront: self)
		)
		(if (not (directionHandler contains: self))
			(directionHandler addToFront: self)
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(directionHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 195 159 self)
			)
			(1
				(ego
					setPri: (+ (ego priority?) 1)
					setMotion: MoveTo (ego x?) (- (ego y?) 2) self
				)
			)
			(2
				(ego view: 70 normal: 0 setLoop: 7 setCycle: BegLoop self)
			)
			(3
				(if (not (Btst fFindSecretButton)) (narrator say: 1))
				(SolvePuzzle fSeeProfessorMessage 10)
				(ego view: 70 setLoop: 7 setCycle: EndLoop self)
				(if (not local28)
					(ego illegalBits: (- cBLUE cWHITE))
				else
					(ego illegalBits: cWHITE)
				)
			)
			(4
				(aSound number: 812 loop: 1 play:)
				(= seconds 2)
			)
			(5 (ego setCycle: BegLoop self))
			(6 (= seconds 1))
			(7
				(NormalEgo 3 0)
				(ego y: (+ (ego y?) 2) setHeading: 0 self)
				(if (not local28)
					(ego illegalBits: (- cBLUE cWHITE))
				else
					(ego illegalBits: cWHITE)
				)
			)
			(8
				(if (not (Btst fFindSecretButton))
					(Bset fFindSecretButton)
					(aSound init: number: 813 loop: 1 play:)
					(if (== ((inventory at: iJar) owner?) curRoomNum)
						(theJar stopUpd:)
					)
					(ego stopUpd:)
					(hologram init: cycleSpeed: 12 setCycle: EndLoop)
					(= seconds 3)
				else
					(narrator say: 2)
					(HandsOn)
					(self dispose:)
				)
			)
			(9
				(hologramHead init: cycleSpeed: 12 setCycle: RandCycle)
				(hologram setLoop: 2 posn: 170 83 setCycle: RandCycle)
				(= cycles 1)
			)
			(10
				(music number: 814 loop: -1 play:)
				(theProfessor say: 1 self)
			)
			(11 (theProfessor say: 2 self))
			(12 (theProfessor say: 3 self))
			(13 (theProfessor say: 4 self))
			(14 (theProfessor say: 5 self))
			(15 (theProfessor say: 6 self))
			(16 (theProfessor say: 7 self))
			(17 (theProfessor say: 8 self))
			(18 (theProfessor say: 9 self))
			(19 (theProfessor say: 10 self))
			(20 (theProfessor say: 11 self))
			(21 (theProfessor say: 12 self))
			(22 (theProfessor say: 13 self))
			(23 (theProfessor say: 14 self))
			(24
				(music fade:)
				(hologram setCycle: 0)
				(hologramHead setCycle: 0)
				(= seconds 2)
			)
			(25
				(aSound play:)
				(hologramHead dispose:)
				(hologram
					setLoop: 1
					setCel: 4
					posn: 160 81
					setCycle: BegLoop self
				)
			)
			(26
				(music number: 815 vol: 127 play:)
				(hologram dispose:)
				(HandsOn)
				((ego _head?) startUpd:)
				(client setScript: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(if saveBits
			(event claimed: TRUE)
			(= seconds 0)
		else
			(super handleEvent: event)
		)
	)
)

(instance jarScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 197 165 self)
			)
			(1
				(if (not local28)
					(ego illegalBits: (- cBLUE cWHITE))
				else
					(ego illegalBits: cWHITE)
				)
				(Face ego theJar self)
			)
			(2
				(narrator say: 3)
				(SolvePuzzle fGetJar 5)
				(ego get: iJar)
				(theJar dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enterSewer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 63 136 self)
			)
			(1 (ego setHeading: 270 self))
			(2
				(ego view: 70 setLoop: 8 cel: 0 setCycle: EndLoop self)
			)
			(3
				(HandsOn)
				(curRoom newRoom: 85)
			)
		)
	)
)

(instance tripShow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego theShadow self)
			)
			(1
				(narrator
					modeless: 1
					returnVal: 0
					nMsgType: 1
					say: 21 self 2
						#at 63 20
						#width 315
						#color myTextColor2
						#back myLowlightColor
						#mode teJustCenter
						#font 310
				)
			)
			(2
				(screen init: setMotion: MoveTo 75 0 self)
			)
			(3 (= seconds 1))
			(4
				(screen view: 651)
				(narrator say: 26 self)
			)
			(5
				(screen view: 652)
				(narrator say: 27 self)
			)
			(6
				(screen view: 653)
				(narrator say: 28 self)
			)
			(7
				(screen view: 650)
				(narrator say: 29 self)
			)
			(8
				(screen view: 650 setMotion: MoveTo 75 -110 self)
			)
			(9
				(narrator
					returnVal: 0
					nMsgType: 1
					say: 22 self 2
						#at 63 20
						#width 315
						#color myTextColor2
						#back myLowlightColor
						#mode teJustCenter
						#font 310
				)
			)
			(10 (= cycles 2))
			(11
				(narrator
					returnVal: 0
					nMsgType: 1
					say: 23 self 2
						#at 63 20
						#width 315
						#color myTextColor2
						#back myLowlightColor
						#mode teJustCenter
						#font 310
				)
			)
			(12 (= cycles 2))
			(13
				(narrator
					returnVal: 0
					nMsgType: 1
					say: 24 self 2
						#at 63 20
						#width 315
						#color myTextColor2
						#back myLowlightColor
						#mode teJustCenter
						#font 310
				)
			)
			(14 (= cycles 2))
			(15
				(narrator
					returnVal: 0
					nMsgType: 1
					say: 25 self 2
						#at 63 20
						#width 315
						#color myTextColor2
						#back myLowlightColor
						#mode teJustCenter
						#font 310
				)
			)
			(16
				(HandsOn)
				(narrator nMsgType: -1 modeless: FALSE)
				(self dispose:)
			)
		)
	)
)

(instance screen of Sq4Actor
	(properties
		x 75
		y -110
		yStep 4
		view 650
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
	)
)

(instance euroTrip of Sq4Feature
	(properties
		x 18
		y 155
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_BUCKAZOID
				(if (and (> (ego y?) 170) (< (ego x?) 60))
					(curRoom setScript: tripShow)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event)
		(InRect 0 180 8 190 event)
	)
)

(instance dr of Sq4Actor
	(properties
		x 45
		y 127
		z -16
		sightAngle 45
		view 70
		loop 5
		priority 9
		signal (| ignrAct fixedLoop fixPriOn)
		illegalBits $0000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (not local28)
					(HandsOff)
					(curRoom setScript: drScript)
				else
					(narrator say: 5)
				)
			)
			(V_LOOK
				(if local28
					(narrator say: 6)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance drOpening of Sq4Feature
	(properties
		x 49
		y 137
		z 33
		nsTop 85
		nsLeft 36
		nsBottom 123
		nsRight 63
		sightAngle 45
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO 0)
			(V_LOOK
				(if (not local28) 0 else (narrator say: 7))
			)
			(V_SMELL (narrator say: 8))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theJar of Sq4View
	(properties
		x 197
		y 150
		z 22
		sightAngle 45
		view 70
		loop 6
		priority 12
		signal (| ignrAct fixPriOn)
		lookStr 9
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (== ((inventory at: iJar) owner?) 70)
					(ego illegalBits: 0)
					(curRoom setScript: jarScript)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance hologram of Sq4Prop
	(properties
		x 160
		y 81
		view 70
		loop 1
		signal $4000
	)
)

(instance hologramHead of Sq4Prop
	(properties
		x 167
		y 45
		view 70
		loop 3
		signal $4000
	)
)

(instance theRoom of Sq4Feature
	(properties
		x 152
		z -82
		nsBottom 189
		nsRight 319
		sightAngle 180
		lookStr 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SMELL (narrator say: 11))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance blotter of Sq4Feature
	(properties
		x 230
		y 155
		z 31
		nsTop 115
		nsLeft 211
		nsBottom 133
		nsRight 249
		sightAngle 45
		lookStr 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(HandsOff)
				(ego illegalBits: 0)
				(curRoom setScript: buttonScript)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance grid of Sq4Feature
	(properties
		x 166
		y 83
		nsTop 76
		nsLeft 145
		nsBottom 91
		nsRight 188
		sightAngle 45
		onMeCheck $4040
		lookStr 13
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (narrator say: 14))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theDesk of Sq4Feature
	(properties
		x 212
		y 123
		z 30
		nsBottom 200
		nsRight 320
		sightAngle 45
		onMeCheck $0080
		lookStr 15
	)
)

(instance theWires of Sq4Feature
	(properties
		x 155
		y 75
		nsBottom 200
		nsRight 320
		sightAngle 45
		onMeCheck $0020
		lookStr 16
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (narrator say: 17))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theShadow of Sq4Feature
	(properties
		x 227
		y 10
		nsLeft 198
		nsBottom 30
		nsRight 246
		sightAngle 45
		lookStr 18
	)
)

(instance thePipes of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		sightAngle 45
		onMeCheck $0010
		lookStr 19
	)
	
	(method (doVerb theVerb)
		(self
			x: ((User curEvent?) x?)
			y: ((User curEvent?) y?)
		)
		(switch theVerb
			(4 (narrator say: 20))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance aSound of Sound
	(properties)
)

(instance theProfessor of Narrator
	(properties
		noun PROFESSOR
		modeless TRUE
		talkerNum PROFESSOR
	)
	
	(method (dispose)
		(RestoreDialog)
		(super dispose: &rest)
	)
	
	(method (display &tmp temp0 theTalkWidth)
		(RestoreDialog)
		(if (> (+ x talkWidth) 318)
			(= theTalkWidth (- 318 x))
		else
			(= theTalkWidth talkWidth)
		)
		(if (not x) (= x (+ (- nsRight nsLeft) 5)))
		(ProfessorSays &rest #at x y 111 77 name #width theTalkWidth)
	)
)
