;;; Sierra Script 1.0 - (do not remove this comment)
(script# 530)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use StopWalk)
(use Sound)
(use Motion)
(use System)

(public
	rm530 0
)

(local
	local0
	local1
	[buildingPolyPts 48] =
		[131 40 143 42 157 41 168 36 175 30 180 23 184 16 255 15
		260 24 262 50 248 63 240 64 224 79 219 79 203 80 187 80
		187 79 172 79 171 80 146 80 146 78 123 78 129 52 124 37]
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

(instance rm530 of SQRoom
	(properties
		picture 530
		east 535
		vanishingX 319
		vanishingY -60
	)
	
	(method (init)
		(HandsOff)
		(LoadMany VIEW 0 378 530 300 26)
		(LoadMany SOUND 124 125 131 838)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 257 172 270 152 319 152 319 172
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						31 170 0 170 0 0 319 0 319 52 267 52 254 65 246 67
						230 83 133 83 117 81 71 114 77 117 111 112 144 119
						158 126 181 114 205 115 228 134 216 140 180 144
						143 151 128 150 119 138 85 149 69 150 60 145
					yourself:
				)
				((Polygon new:)
					type: PTotalAccess
					init: 167 165 167 189 127 189 127 166
					yourself:
				)
		)
		(timePod0 init:)
		(terminal init:)
		(switch prevRoomNum
			(531
				(self
					setScript:
						(if (Btst fTimeTravel)
							egoArrives
						else
							(if (not (Btst fFirstTimeTravel))
								(LoadMany VIEW 7 13)
								(Load SOUND 105)
								(sp1 loop: 2 init:)
								(sp2 view: 7 loop: 2 x: 185 y: 91 init:)
							)
							(ego
								view: 378
								loop: 0
								cel: 0
								posn: 188 149
								setPri: 11
								ignoreActors:
								illegalBits: 0
								cycleSpeed: 12
								normal: 0
								moveHead: 0
								init:
							)
							egoExits
						)
				)
			)
			(else 
				(LoadMany VIEW 7 13)
				(Load SOUND 105)
				(self style: SCROLLRIGHT)
				(if (Btst fFindTimepod)
					(HandsOff)
					(hatch init: cel: (if (Btst fFirstTimeTravel) 0 else 0))
					(pod init:)
					(if (not (Btst fFirstTimeTravel))
						(sp1 loop: 0 init:)
						(sp2 view: 7 loop: 0 x: 185 y: 91 init:)
					)
					(ego
						x: 282
						y:
							(cond 
								((not (Btst fFirstTimeTravel)) 130)
								((> (ego y?) 152) 152)
								(else (ego y?))
							)
						init:
					)
					(EgoHeadMove)
					(self setScript: enterScript)
				else
					(sp1 init:)
					(ego x: 284 y: 130 init:)
					(sp1 setScript: policeArrive)
				)
			)
		)
		(Bset fFindTimepod)
		(features
			add: theRedBall theRailView1 theRailView2 building
			eachElementDo: #init
			doit:
		)
		(super init:)
		(self setRegions: LANDING)
		(buildingPoly points: @buildingPolyPts size: 24)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (> (ego x?) 300)
			(HandsOff)
			(curRoom newRoom: 535)
		)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0 (= cycles 1))
			(1
				(ego
					setCycle: StopWalk 4
					setMotion: MoveTo (if (< (ego y?) 85) 273 else 255) (ego y?) self
				)
			)
			(2
				(if (Btst fFirstTimeTravel)
					(HandsOn)
					(client setScript: 0)
				else
					(policeArrive start: 18)
					(client setScript: policeArrive)
				)
			)
		)
	)
)

(instance egoArrives of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 8))
			(1
				(sparks init: setCycle: Forward)
				(= cycles 10)
			)
			(2
				(pod cel: 0 init: setCycle: CycleTo 6 1 self)
			)
			(3 (= cycles 12))
			(4
				(sparks dispose:)
				(= cycles 1)
			)
			(5
				(globalSound fade: 80 15 10 0)
				(curRoom newRoom: 531)
			)
		)
	)
)

(instance policeArrive of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(= register 200)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and (> 18 state) (> state 13)) (-- register))
		(if
			(or
				(== register 0)
				(and (< (ego y?) 128) (< state 18))
			)
			(= register -1)
			(= state 17)
			(= cycles (= seconds 0))
			(if (not local1) (self cue:) else (self dispose:))
		)
		(if (== (curRoom script?) (ScriptID LANDING 1))
			(self dispose:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 8))
			(1
				(sparks init: setCycle: Forward)
				(= cycles 10)
			)
			(2
				(Face sp1 pod)
				(pod cel: 0 init: setCycle: CycleTo 6 1 self)
			)
			(3
				(hatch init: cel: 0 setPri: (+ (hatch priority?) 2))
				(pod setCel: 7)
				(= cycles 12)
			)
			(4
				(sp1 stopUpd:)
				(sparks dispose:)
				(pod stopUpd:)
				(= cycles 1)
			)
			(5
				(music number: 124 loop: 1 vol: 127 play: self)
			)
			(6
				(hatch setCycle: EndLoop self)
				(sp2 view: 378 loop: 2 cel: 0 x: 198 y: 148 init:)
			)
			(7
				(hatch stopUpd:)
				(sp2 cycleSpeed: 4 setCycle: EndLoop self)
			)
			(8 (= cycles 2))
			(9
				(sp2
					view: 7
					loop: 0
					x: (+ (sp2 x?) 13)
					y: (+ (sp2 y?) 5)
					moveSpeed: 5
					cycleSpeed: 5
					setCycle: Walk
					setMotion: PolyPath 185 91 self
				)
			)
			(10
				(HandsOn)
				(sp2 setCel: 5)
				(ego setMotion: MoveTo 262 130)
				(sp1 setHeading: 90 self)
			)
			(11
				(sp1 stopUpd:)
				(= cycles 5)
			)
			(12
				(sp2 stopUpd:)
				(tSP1 say: 1 self)
			)
			(13 (tSP1 say: 2 self))
			(14
				(HandsOn)
				(tSP1 say: 3 self)
			)
			(15 (tSP2 say: 4 self))
			(16 (tSP2 say: 5 self))
			(17)
			(18
				(HandsOff)
				(Face sp1 ego)
				(= cycles 3)
			)
			(19
				(Face sp2 ego)
				(sp1 view: 13 cel: 0 setCycle: EndLoop self)
			)
			(20
				(if (< (sp1 x?) (ego x?))
					(PoliceFire
						(- (sp1 y?) 27)
						(sp1 x?)
						(- (ego y?) 32)
						(ego x?)
					)
				else
					(PoliceFire
						(- (ego y?) 32)
						(ego x?)
						(- (sp1 y?) 27)
						(sp1 x?)
					)
				)
				(ego
					view: 26
					loop:
						(switch (ego loop?)
							(4 0)
							(5 1)
							(6 0)
							(7 1)
							(else  (ego loop?))
						)
					cel: 0
					cycleSpeed: 6
					setCycle: CycleTo 2 1 self
				)
			)
			(21
				(ego setCycle: CycleTo 1 -1 self)
			)
			(22 (ego setCycle: EndLoop self))
			(23 (= seconds 2))
			(24 (tSP1 say: 6 self))
			(25 (tSP1 say: 7 self))
			(26 (EgoDead iconLASER))
		)
	)
)

(instance egoExits of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(if
			(and
				(cast contains: sp1)
				(== (sp1 script?) policeArrive)
			)
			(sp1 setScript: 0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(globalSound number: 535 loop: -1 vol: 127 changeState:)
				(pod setCel: 7 init: stopUpd:)
				(hatch
					init:
					cel: 0
					setPri: (+ (hatch priority?) 2)
					setCycle: EndLoop self
				)
			)
			(1 (= cycles 4))
			(2 (ego setCycle: EndLoop self))
			(3
				(ego loop: 1 normal: 1 moveHead: 1 view: 0)
				(NormalEgo)
				(= cycles 1)
			)
			(4
				(if (Btst fFirstTimeTravel)
					(hatch setPri: 10 setCycle: BegLoop self)
				else
					(policeArrive start: 18)
					(client setScript: policeArrive)
				)
			)
			(5
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance egoEntersPod of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local1 1)
				(ego setMotion: PolyPath 188 150 self)
			)
			(1
				(if (not (hatch cel?))
					(hatch setCycle: EndLoop self)
				else
					(= cycles 1)
				)
			)
			(2
				(= useObstacles FALSE)
				(ego
					view: 378
					loop: 1
					cel: 0
					setPri: 11
					posn: 191 126
					ignoreActors:
					illegalBits: 0
					cycleSpeed: 18
					normal: 0
					moveHead: 0
					setCycle: EndLoop self
				)
			)
			(3 (= cycles 4))
			(4
				(hatch cel: 5 setPri: 12 setCycle: BegLoop self)
			)
			(5
				(music number: 125 loop: 0 play: self)
				(SolvePuzzle fEnterHangarTimepod 10)
				(= useObstacles TRUE)
			)
			(6
				(globalSound fade: 80 15 10 0)
				(curRoom newRoom: 531)
			)
		)
	)
)

(instance pod of Sq4Prop
	(properties
		x 127
		y 129
		view 530
		loop 1
		cel 7
		priority 10
		signal (| ignrAct fixPriOn)
		cycleSpeed 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (not (hatch cel?))
					(narrator say: 1)
				else
					(narrator say: 1)
				)
			)
			(V_DO
				(curRoom setScript: egoEntersPod)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance hatch of Sq4Prop
	(properties
		x 162
		y 131
		z 18
		view 530
		loop 2
		cel 9
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(pod doVerb: theVerb)
	)
	
	(method (motionCue)
		(hatchSound stop: loop: 0)
		(super motionCue: &rest)
	)
	
	(method (setCycle)
		(super setCycle: &rest)
		(hatchSound init: play:)
	)
)

(instance hatchSound of Sound
	(properties
		number 142
	)
)

(instance sparks of Sq4Prop
	(properties
		x 145
		y 108
		view 530
		priority 13
		signal  (| ignrAct fixPriOn)
	)
	
	(method (init)
		(super init: &rest)
		(aSound number: 838 loop: 1 play:)
	)
	
	(method (dispose)
		(aSound stop:)
		(super dispose:)
	)
)

(instance sp1 of Sq4Actor
	(properties
		x 156
		y 91
		view 7
		loop 3
		signal ignrAct
		lookStr 2
	)
)

(instance sp2 of Sq4Actor
	(properties
		signal ignrAct
		lookStr 2
	)
)

(instance terminal of Sq4Feature
	(properties
		x 159
		y 61
		heading 180
		nsTop 43
		nsLeft 148
		nsBottom 80
		nsRight 170
		sightAngle 150
		lookStr 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(HandsOff)
				(curRoom setScript: doTerminal)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance doTerminal of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setMotion: PolyPath (terminal x?) (terminal y?) self
				)
			)
			(1
				(narrator say: 4)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance theRedBall of Sq4Feature
	(properties
		x 147
		y 141
		z 120
		nsBottom 200
		nsRight 320
		onMeCheck $0010
		lookStr 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theRailView1 of Sq4Feature
	(properties
		x 59
		y 68
		nsBottom 200
		nsRight 320
		onMeCheck $0020
		lookStr 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theRailView2 of Sq4Feature
	(properties
		x 270
		y 33
		nsBottom 200
		nsRight 320
		sightAngle 180
		onMeCheck $0040
		lookStr 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else  (super doVerb: theVerb))
		)
	)
)

(instance timePod0 of Sq4Feature
	(properties
		lookStr 1
	)
	
	(method (onMe event)
		(return (& (OnControl cGREEN (event x?) (event y?)) mouseUp))
	)
)

(instance building of Sq4Feature
	(properties
		sightAngle 45
		lookStr 7
	)
	
	(method (init)
		(super init: &rest)
		(self onMeCheck: buildingPoly)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (narrator say: 8))
			(V_SMELL (narrator say: 9))
			(V_TASTE (narrator say: 10))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance buildingPoly of Polygon
	(properties)
)

(instance aSound of Sound
	(properties)
)

(instance tROGER of Sq4Talker
	(properties
		z 400
		noun ROGER
		view 1008
		talkerNum ROGER
		mouthOffsetX 22
		mouthOffsetY 31
		eyeOffsetX 27
		eyeOffsetY 21
	)
)

(instance tSP1 of Sq4Talker
	(properties
		z 400
		noun SP4
		view 1015
		talkerNum SP4
		mouthOffsetX 24
		mouthOffsetY 34
		eyeOffsetX 1
		eyeOffsetY 1
	)
)

(instance tSP2 of Sq4Talker
	(properties
		z 400
		noun SP3
		view 1016
		talkerNum SP3
		mouthOffsetX 21
		mouthOffsetY 34
		eyeOffsetX 1
		eyeOffsetY 1
	)
)
