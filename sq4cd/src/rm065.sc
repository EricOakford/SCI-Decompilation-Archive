;;; Sierra Script 1.0 - (do not remove this comment)
(script# 65)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use PolyPath)
(use Polygon)
(use Motion)
(use System)

(public
	rm065 0
)

(local
	[buildingPolyPts 46] = [26 0 27 36 42 48 66 50 86 42 86 89 96 99 133 100 152 87 169 97 192 97 194 116 204 124 201 162 230 160 231 148 241 143 243 148 246 160 271 162 273 155 319 149 319]
	[streetPolyPts 16] = [0 70 106 173 81 184 75 172 65 168 41 175 40 182 0 183]
	[sidewalkPolyPts 48] = [0 0 26 0 27 35 43 49 67 51 86 43 86 89 94 100 118 103 138 100 151 86 166 97 191 98 192 115 203 122 199 164 190 167 186 163 151 174 143 167 128 173 111 170 105 172 0 70]
	[liquidPolyPts 32] = [0 70 79 148 78 151 78 152 76 153 74 152 73 150 62 139 57 140 43 126 23 113 15 107 3 93 1 90 1 88 0 87]
)
(instance rm065 of SQRoom
	(properties
		picture 65
		style FADEOUT
		horizon 48
		north 50
		west 60
		vanishingX 0
		vanishingY -65
	)
	
	(method (init)
		(Load VIEW 8)
		(switch prevRoomNum
			(north
				(HandsOff)
				(self setScript: enterScript)
			)
			(west
				(if (> (ego y?) 179) (ego y: 179))
			)
			(else  (ego x: 160 y: 160))
		)
		(ego init:)
		(if (== ((inventory at: iRope) owner?) 65) (aRope init:))
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 179 185 179 192 159 227 149 152 103 139 109
						101 109 82 99 77 85 91 71 105 69 86 51 77 55 40
						54 22 46 18 30 30 26 6 0 319 0 319 189 0 189
					yourself:
				)
		)
		(buildingPoly points: @buildingPolyPts size: 23)
		(streetPoly points: @streetPolyPts size: 8)
		(sidewalkPoly points: @sidewalkPolyPts size: 24)
		(liquidPoly points: @liquidPolyPts size: 16)
		(theRubble init:)
		(theRedBldg init:)
		(theLiquid init:)
		(theGrate init:)
		(theStreet init:)
		(theSidewalk init:)
		(if (not (Btst fPoliceLanded)) (self setRegions: STREET))
		(self setRegions: BUNNY)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_ROPE
				((ScriptID BUNNY 4) doVerb: theVerb theItem)
			)
			(else  (super doVerb: theVerb))
		)
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
			(else (super newRoom: newRoomNumber))
		)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 4 -1 setMotion: MoveTo 17 49 self)
			)
			(1
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance exitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 5 -10 self)
			)
			(1
				(= register 0)
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance getRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 169 177 self)
			)
			(1
				(ego
					view: 8
					loop: 0
					cel: 0
					x: 168
					y: 177
					cycleSpeed: 16
					heading: 225
				)
				(= cycles 1)
			)
			(2 (ego setCycle: CycleTo 2 1 self))
			(3 (narrator say: 1 self))
			(4
				(aRope dispose:)
				(SolvePuzzle fGetRope 5)
				(= cycles 1)
			)
			(5
				(ego cycleSpeed: 12 setCycle: EndLoop self)
			)
			(6
				(NormalEgo 2 0)
				(ego posn: 169 177 get: 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance aRope of Sq4View
	(properties
		x 162
		y 195
		z 10
		sightAngle 45
		view 33
		priority 14
		signal (| ignrAct fixPriOn)
		lookStr 2
	)
	
	(method (doit)
		(super doit:)
		(if (== (curRoom curPic?) 31)
			(self z: 1000)
		else
			(self z: 10)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(HandsOff)
				(curRoom setScript: getRope)
			)
			(V_SMELL (narrator say: 3))
			(V_TASTE (narrator say: 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theRubble of Sq4Feature
	(properties
		x 160
		y 188
		nsBottom 200
		nsRight 320
		onMeCheck $0040
		lookStr 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (!= ((inventory at: iRope) owner?) 65)
					(narrator say: 6)
				else
					(narrator say: 7)
				)
			)
			(V_SMELL (narrator say: 8))
			(V_TASTE (narrator say: 9))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theRedBldg of Sq4Feature
	(properties
		x 280
		y 180
		sightAngle 40
		lookStr 10
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: buildingPoly)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (narrator modNum: 50 say: 9))
			(V_SMELL (narrator say: 11))
			(V_TASTE (tRog say: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance buildingPoly of Polygon
	(properties)
)

(instance theLiquid of Sq4Feature
	(properties
		x 39
		y 115
		nsBottom 200
		nsRight 320
		sightAngle 45
		lookStr 12
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: liquidPoly)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SMELL (narrator say: 13))
			(V_TASTE (narrator say: 14))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance liquidPoly of Polygon
	(properties)
)

(instance theGrate of Sq4Feature
	(properties
		x 85
		y 163
		nsBottom 200
		nsRight 320
		sightAngle 45
		onMeCheck $0200
		lookStr 15
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (narrator say: 15))
			(V_SMELL (narrator say: 16))
			(V_TASTE (narrator say: 17))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theStreet of Sq4Feature
	(properties)
	
	(method (init)
		(super init:)
		(self onMeCheck: streetPoly)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 18))
			(V_SMELL (narrator say: 19))
			(V_TASTE
				(narrator modNum: 40 say: 12)
			)
			(V_ROPE
				((ScriptID 705 4) doVerb: theVerb theItem)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance streetPoly of Polygon
	(properties)
)

(instance theSidewalk of Sq4Feature
	(properties)
	
	(method (init)
		(super init:)
		(self onMeCheck: sidewalkPoly)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 20))
			(V_SMELL (narrator say: 21))
			(V_TASTE (narrator say: 22))
			(V_ROPE
				((ScriptID BUNNY 4) doVerb: theVerb theItem)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sidewalkPoly of Polygon
	(properties)
)

(instance tRog of Sq4Talker
	(properties
		z 400
		noun ROGER
		modNum 65
		view 1008
		talkerNum ROGER
		mouthOffsetX 21
		mouthOffsetY 32
		eyeOffsetX 27
		eyeOffsetY 21
	)
)
