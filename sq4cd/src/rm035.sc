;;; Sierra Script 1.0 - (do not remove this comment)
(script# 35)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use Polygon)
(use Motion)
(use System)

(public
	rm035 0
)

(local
	[rubblePoly1Pts 10] = [0 85 12 77 10 40 50]
	[rubblePoly2Pts 12] = [60 36 79 38 109 30 85 9 71 14 60 31]
	[buildingPolyPts 44] = [12 67 11 40 51 0 318 0 319 189 275 189 297 163 226 113 193 151 201 167 141 187 121 169 198 93 147 56 121 91 126 104 75 124 57 107 124 39 80 5 57 34 65 50]
	[streetPolyPts 16] = [0 84 10 79 11 70 14 68 140 187 156 186 164 189 0 189]
	[sidewalk1PolyPts 18] = [13 67 24 64 26 60 65 51 61 38 78 39 111 32 124 38 57 108]
	[sidewalk2PolyPts 16] = [75 125 86 124 88 119 128 105 120 90 147 58 198 93 122 168]
	[sidewalk3PolyPts 16] = [164 189 156 185 157 183 202 166 194 151 226 114 297 165 273 189]
)
(instance rm035 of SQRoom
	(properties
		picture 35
		style FADEOUT
		south 50
		west 30
		vanishingX -125
		vanishingY -112
	)
	
	(method (init)
		(switch prevRoomNum
			(45
				(ego init: x: 48 y: 245)
				(HandsOff)
				(self setScript: enterScript)
			)
			(south (ego x: 245))
			(east 0)
			(else  (ego x: 160 y: 130))
		)
		(rubblePoly1 points: @rubblePoly1Pts size: 5)
		(rubblePoly2 points: @rubblePoly2Pts size: 6)
		(buildingPoly points: @buildingPolyPts size: 22)
		(streetPoly points: @streetPolyPts size: 8)
		(sidewalk1Poly points: @sidewalk1PolyPts size: 9)
		(sidewalk2Poly points: @sidewalk2PolyPts size: 8)
		(sidewalk3Poly points: @sidewalk3PolyPts size: 8)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 238 152 230 163 163 189 126 189 110 174 129 155 209 144
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 38 110 126 79 158 103 71 135
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 0 0 319 0 319 189 306 189 298 173 108 35 86 39 92 43 81 51 0 80
					yourself:
				)
		)
		(ego init:)
		(theRubble1 init:)
		(theRubble2 init:)
		(theWhiteBldg init:)
		(theStreet init:)
		(sidewalk1 init:)
		(sidewalk2 init:)
		(sidewalk3 init:)
		(theArea init:)
		(self setRegions: 701)
		(super init:)
		(self setRegions: 705)
	)
	
	(method (newRoom newRoomNumber)
		(if
			(and
				(!= (curRoom script?) (ScriptID STREET 3))
				(!= ((ScriptID STREET 1) script?) (ScriptID STREET 4))
				(== (ego edgeHit?) SOUTH)
				(< (ego x?) 124)
			)
			(= newRoomNumber 45)
		)
		(super newRoom: newRoomNumber)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 78 188 self)
			)
			(1
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance theRubble1 of Sq4Feature
	(properties
		x 11
		y 18
		sightAngle 45
		lookStr 1
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: rubblePoly1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (narrator say: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rubblePoly1 of Polygon
	(properties)
)

(instance theRubble2 of Sq4Feature
	(properties
		x 82
		y 22
		sightAngle 45
		lookStr 1
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: rubblePoly2)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (narrator say: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rubblePoly2 of Polygon
	(properties)
)

(instance theWhiteBldg of Sq4Feature
	(properties
		x 350
		y 180
		lookStr 2
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: buildingPoly)
	)
)

(instance buildingPoly of Polygon
	(properties)
)

(instance theStreet of Sq4Feature
	(properties
		lookStr 3
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: streetPoly)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_TASTE
				(narrator modNum: 25 say: 13)
			)
			(V_SMELL
				(narrator modNum: 25 say: 12)
			)
			(V_ROPE
				((ScriptID BUNNY 4) doVerb: theVerb theItem)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance streetPoly of Polygon
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
			(V_LOOK (narrator modNum: 25 say: 9))
			(V_TASTE
				(narrator modNum: 25 say: 10)
			)
			(V_SMELL (narrator modNum: 25 say:))
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
	
	(method (doVerb theVerb param2)
		(sidewalk1 doVerb: theVerb param2)
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

(instance theArea of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		lookStr 4
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
