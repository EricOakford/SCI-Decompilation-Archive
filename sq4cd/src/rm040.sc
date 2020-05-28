;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Motion)
(use System)

(public
	rm040 0
)

(local
	[supportPolyPts 24] = [18 0 160 85 161 105 141 164 173 169 199 148 181 99 182 81 224 0 184 0 167 51 123]
	[buldingPolyPts 20] = [78 38 15 0 0 0 0 189 23 189 54 167 51 144 63 144 87 128 74 50]
	[tankPolyPts 16] = [82 86 108 80 127 81 159 98 158 107 150 130 112 139 86 118]
	[holePolyPts 8] = [158 115 144 123 139 120 149 109]
	[street1PolyPts 18] = [74 77 79 73 162 83 161 99 138 84 114 79 107 79 96 84 81 86]
	[street2PolyPts 26] = [181 189 214 158 215 148 207 142 198 140 185 99 180 98 181 85 253 94 272 93 294 86 319 68 319 189]
	[sidewalk1PolyPts 32] = [26 189 55 167 52 144 61 144 87 129 86 119 127 151 150 136 139 164 174 169 200 148 198 140 210 144 215 148 214 157 180 189]
	[sidewalk2PolyPts 8] = [152 81 110 76 95 64 106 54]
	[sidewalk3PolyPts 6] = [155 38 171 38 167 52]
	[sidewalk4PolyPts 18] = [241 40 297 0 319 0 319 68 292 87 273 92 255 94 181 84 205 40]
)
(procedure (CopFires param1 param2 param3 param4 &tmp temp0 temp1 temp2)
	(globalSound number: 105 vol: 127 loop: 1 play:)
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

(instance rm040 of SQRoom
	(properties
		picture 40
		style FADEOUT
		horizon 48
		north 25
		east 45
		south 55
		vanishingX 319
		vanishingY -35
	)
	
	(method (init)
		(Load VIEW 40)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						298 0 251 49 102 49 97 60 102 66
						45 97 33 152 68 163 41 189 0 189 0 0
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 103 79 181 120 142 144 113 143 60 100 75 85
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 215 148 179 179 123 169 169 137
					yourself:
				)
		)
		(if (Btst fPoliceLanded) (LoadMany VIEW 7 13) (cop1 init:))
		(switch prevRoomNum
			(north
				(HandsOff)
				(self setScript: enterScript)
			)
			(south (ego x: 89))
			(east)
			(else 
				(Bset fPoliceLanded)
				(NormalEgo 0 0)
				(ego posn: 64 81)
				(self setScript: enterScript)
				(music number: 802 loop: -1 vol: 127 flags: mNOPAUSE playBed:)
				(cop1 init:)
			)
		)
		(ego init:)
		(theTank init:)
		(theSupport init:)
		(thePurpleBldg init:)
		(theLeftBldg init:)
		(theHole init:)
		(street1 init:)
		(street2 init:)
		(sidewalk1 init:)
		(sidewalk2 init:)
		(sidewalk3 init:)
		(sidewalk4 init:)
		(theArea init:)
		(self setRegions: STREET)
		(super init:)
		(self setRegions: BUNNY)
		(supportPoly points: @supportPolyPts size: 12)
		(buildingPoly points: @buldingPolyPts size: 10)
		(tankPoly points: @tankPolyPts size: 8)
		(holePoly points: @holePolyPts size: 4)
		(street1Poly points: @street1PolyPts size: 9)
		(street2Poly points: @street2PolyPts size: 13)
		(sidewalk1Poly points: @sidewalk1PolyPts size: 16)
		(sidewalk2Poly points: @sidewalk2PolyPts size: 4)
		(sidewalk3Poly points: @sidewalk3PolyPts size: 3)
		(sidewalk4Poly points: @sidewalk4PolyPts size: 9)
	)
	
	(method (newRoom newRoomNumber)
		(cond 
			(
				(and
					(== (ego edgeHit?) NORTH)
					(!= script exitScript)
					(!= script (ScriptID STREET 3))
					(!= ((ScriptID STREET 1) script?) (ScriptID STREET 4))
				)
				(HandsOff)
				(self setScript: exitScript 0 1)
			)
			(
			(and (== (ego edgeHit?) NORTH) (not (script register?))) (super newRoom: newRoomNumber))
			(
				(and
					(== (ego edgeHit?) SOUTH)
					(> (ego x?) 199)
					(not (Btst fPoliceLanded))
					(!= (curRoom script?) (ScriptID STREET 3))
					(!= ((ScriptID STREET 1) script?) (ScriptID STREET 4))
				)
				(super newRoom: 60)
			)
			(else (super newRoom: newRoomNumber))
		)
	)
)

(instance shootEgo of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (and (== state 1) (> (ego x?) 205)) (self cue:))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks register))
			(1
				(if (> (ego y?) 100) (cop1 y: 65) (cop2 y: 45))
				(cop1
					setCycle: Walk
					setMotion: MoveTo 275 (cop1 y?) self
				)
				(cop2 setCycle: Walk setMotion: MoveTo 0 (cop2 y?))
			)
			(2
				(Face cop1 ego)
				(= cycles 4)
			)
			(3
				(cop2 setMotion: 0)
				(Face cop2 ego)
				(cop1 view: 13 cel: 0 setMotion: 0 setCycle: CycleTo 2 1 self)
			)
			(4
				(HandsOff)
				(cop1 setCycle: EndLoop self)
				(if (< (cop1 x?) (ego x?))
					(CopFires
						(- (cop1 y?) 27)
						(cop1 x?)
						(- (ego y?) 32)
						(ego x?)
					)
				else
					(CopFires
						(- (ego y?) 32)
						(ego x?)
						(- (cop1 y?) 27)
						(cop1 x?)
					)
				)
			)
			(5
				(ego
					setMotion: 0
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
			(6
				(ego setMotion: 0 setCycle: CycleTo 1 -1 self)
			)
			(7
				(ego setMotion: 0 setCycle: EndLoop self)
			)
			(8 (= seconds 4) (EgoDead 8))
		)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== prevRoomNum 72)
					(ego setMotion: MoveTo (+ (ego x?) 15) (ego y?) self)
				else
					(ego posn: 333 1 setLoop: 5 setMotion: MoveTo 272 49 self)
				)
			)
			(1
				(NormalEgo)
				(HandsOn)
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
				(ego setMotion: MoveTo 310 -1 self)
			)
			(1
				(= register 0)
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance theTank of Sq4Feature
	(properties
		x 125
		y 110
		sightAngle 45
		lookStr 1
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: tankPoly)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if
				(or (curRoom script?) (cast contains: closeup))
					0
				else
					(HandsOff)
					(curRoom setScript: tankScript)
				)
			)
			(V_BOMB
				(HandsOff)
				(curRoom setScript: bombScript)
			)
			(V_SMELL (narrator say: 2))
			(V_TASTE (narrator say: 3))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance tankPoly of Polygon
	(properties)
)

(instance tankScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 154 137 self)
			)
			(1 (ego setHeading: 315 self))
			(2
				(if (== prevRoomNum 72)
					(HandsOff)
					(if (not (shootEgo state?)) (shootEgo ticks: 2))
				else
					(= cycles 2)
				)
			)
			(3
				(if (== ((inventory at: iBomb) owner?) 40)
					(theBomb init:)
				)
				(closeup init: stopUpd:)
				(= cycles 1)
			)
			(4
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance bombScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 154 137 self)
			)
			(1
				(ego setHeading: 315 self)
				(ego put: iBomb 40)
				(SolvePuzzle fReturnBomb -20)
			)
			(2
				(narrator say: 4)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance theSupport of Sq4Feature
	(properties
		x 172
		y 154
		sightAngle 45
		lookStr 5
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: supportPoly)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 5))
			(V_SMELL (narrator say: 6))
			(V_TASTE (narrator say: 7))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance supportPoly of Polygon
	(properties)
)

(instance thePurpleBldg of Sq4Feature
	(properties
		x 198
		y 15
		nsBottom 200
		nsRight 320
		sightAngle 45
		onMeCheck $2000
		lookStr 8
	)
)

(instance theLeftBldg of Sq4Feature
	(properties
		x 43
		y 123
		sightAngle 45
		lookStr 9
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: buildingPoly)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (narrator say: 10))
			(V_SMELL (narrator say: 11))
			(V_TASTE (narrator say: 12))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance buildingPoly of Polygon
	(properties)
)

(instance street1 of Sq4Feature
	(properties)
	
	(method (init)
		(super init:)
		(self onMeCheck: street1Poly)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 13))
			(V_SMELL (narrator say: 14))
			(V_TASTE (narrator say: 15))
			(V_ROPE
				((ScriptID BUNNY 4) doVerb: theVerb theItem)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance street1Poly of Polygon
	(properties)
)

(instance street2 of Sq4Feature
	(properties)
	
	(method (init)
		(super init:)
		(self onMeCheck: street2Poly)
	)
	
	(method (doVerb theVerb theItem)
		(street1 doVerb: theVerb theItem)
	)
)

(instance street2Poly of Polygon
	(properties)
)

(instance sidewalk1 of Sq4Feature
	(properties)
	
	(method (init)
		(super init:)
		(self onMeCheck: sidewalk1Poly)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 16))
			(V_SMELL (narrator say: 17))
			(V_TASTE (narrator say: 18))
			(V_ROPE
				((ScriptID BUNNY 4) doVerb: theVerb theItem)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sidewalk1Poly of Polygon
	(properties)
)

(instance sidewalk2 of Sq4Feature
	(properties)
	
	(method (init)
		(super init:)
		(self onMeCheck: sidewalk2Poly)
	)
	
	(method (doVerb theVerb theItem)
		(sidewalk1 doVerb: theVerb theItem)
	)
)

(instance sidewalk2Poly of Polygon
	(properties)
)

(instance sidewalk3 of Sq4Feature
	(properties)
	
	(method (init)
		(super init:)
		(self onMeCheck: sidewalk3Poly)
	)
	
	(method (doVerb theVerb theItem)
		(sidewalk1 doVerb: theVerb theItem)
	)
)

(instance sidewalk3Poly of Polygon
	(properties)
)

(instance sidewalk4 of Sq4Feature
	(properties)
	
	(method (init)
		(super init:)
		(self onMeCheck: sidewalk4Poly)
	)
	
	(method (doVerb theVerb theItem)
		(sidewalk1 doVerb: theVerb theItem)
	)
)

(instance sidewalk4Poly of Polygon
	(properties)
)

(instance theArea of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		lookStr 19
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_ROPE
				((ScriptID BUNNY 4) doVerb: theVerb theItem)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance closeup of Sq4View
	(properties
		x 132
		y 155
		z 77
		sightAngle 90
		view 40
		priority 13
		signal (| ignrAct fixPriOn)
	)
	
	(method (doit)
		(super doit:)
		(if (or (ego mover?) (!= (ego loop?) 7))
			(self hide: dispose:)
		)
		(if (== (curRoom curPic?) 31)
			(self z: 1000)
		else
			(self z: 77)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (== ((inventory at: iBomb) owner?) 40)
					(narrator say: 20)
				else
					(narrator say: 21)
				)
			)
			(V_BOMB (theTank doVerb: theVerb))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theBomb of Sq4Prop
	(properties
		x 134
		y 156
		z 97
		sightAngle 90
		view 40
		cel 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (doit)
		(super doit:)
		(if (or (ego mover?) (!= (ego loop?) 7))
			(self hide: dispose:)
		)
		(if (== (curRoom curPic?) 31)
			(self z: 1000)
		else
			(self z: 97)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 22))
			(V_DO
				(theBomb dispose:)
				(ego get: iBomb)
				(SolvePuzzle fGotBomb 25)
				(if (and (Btst fPoliceLanded) (== (shootEgo state?) 0))
					(shootEgo register: 20)
				)
				(Animate (cast elements?) FALSE)
				(narrator say: 23)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theHole of Sq4Feature
	(properties
		x 149
		y 115
		sightAngle 45
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: holePoly)
	)
	
	(method (doVerb theVerb theItem)
		(cond 
			((or (== theVerb V_LOOK) (== theVerb V_DO)) (theTank doVerb: V_DO theItem))
			(V_BOMB (theTank doVerb: theVerb))
			(else (super doVerb: theVerb))
		)
	)
)

(instance holePoly of Polygon
	(properties)
)

(instance cop1 of Sq4Actor
	(properties
		x 329
		y 165
		view 7
		loop 2
		lookStr 24
	)
	
	(method (init)
		(cop2 init:)
		(shootEgo register: 900)
		(self setScript: shootEgo)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 24))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cop2 of Sq4Actor
	(properties
		x 340
		y 145
		view 7
		lookStr 24
	)
)
