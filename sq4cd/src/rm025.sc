;;; Sierra Script 1.0 - (do not remove this comment)
(script# 25)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use Polygon)
(use LoadMany)
(use DPath)
(use Motion)
(use System)

(public
	rm025 0
)

(local
	[buildingPolyPts 30] = [32 189 54 172 72 168 91 154 99 141 101 101 106 97 147 100 170 83 189 75 201 67 212 49 211 0 0 0 0 189]
	[rubblePolyPts 14] = [319 87 289 88 244 74 199 69 212 51 212 0 319]
	[sidewalkPolyPts 38] = [33 189 55 172 71 168 82 162 94 151 99 143 102 128 102 115 100 102 105 97 148 100 171 82 185 78 199 71 258 78 267 82 280 82 287 88 182 189]
	[local82 8] = [181 189 285 89 319 92 319 189]
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

(instance rm025 of SQRoom
	(properties
		picture 25
		style FADEOUT
		east 30
		south 40
		vanishingX 319
		vanishingY -35
	)
	
	(method (init)
		(switch prevRoomNum
			(south (ego x: 103))
			(45
				(HandsOff)
				(ego init: x: 272 y: 245)
				(self setScript: enterScript)
			)
			(else 
				(HandsOn)
				(ego view: 0 posn: 160 160)
			)
		)
		(if (Btst fPoliceLanded)
			(LoadMany VIEW 7 13)
			(cop1 init:)
			(cop2 init:)
		)
		(ego init:)
		(theRubble init:)
		(theGreenBuilding init:)
		(theStreet init:)
		(theSidewalk init:)
		(theArea init:)
		(self setRegions: STREET)
		(super init:)
		(self setRegions: BUNNY)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189 0 0 319 0 319 89 299 92 266 85 246 79
						211 77 195 73 153 104 111 101 62 146 69 169
						45 189
					yourself:
				)
		)
		(buildingPoly points: @buildingPolyPts size: 15)
		(rubblePoly points: @rubblePolyPts size: 7)
		(sidewalkPoly points: @sidewalkPolyPts size: 19)
		(streetPoly points: @local82 size: 4)
	)
	
	(method (newRoom newRoomNumber)
		(if
			(and
				(!= (curRoom script?) (ScriptID STREET 3))
				(!= ((ScriptID STREET 1) script?) (ScriptID STREET 4))
				(== (ego edgeHit?) SOUTH)
				(> (ego x?) 199)
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
				(ego setLoop: 7 setMotion: MoveTo 242 188 self)
			)
			(1
				(NormalEgo)
				(if (not (Btst fPoliceLanded)) (HandsOn))
				(client setScript: 0)
			)
		)
	)
)

(instance cop1 of Sq4Actor
	(properties
		x 241
		y 110
		view 7
		loop 2
	)
	
	(method (doit)
		(super doit: &rest)
		(if (not (self script?)) (self setScript: shootEgo))
	)
)

(instance cop2 of Sq4Actor
	(properties
		x 88
		y 126
		view 7
	)
)

(instance shootEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cop1 setCycle: Walk setMotion: MoveTo 200 140 self)
				(cop2 setCycle: Walk setMotion: DPath 120 126 120 136)
			)
			(1
				(cop1 view: 13 cel: 0 setMotion: 0 setCycle: CycleTo 4 1 self)
			)
			(2
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
			(3
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
			(4 (ego setCycle: CycleTo 1 -1 self))
			(5 (ego setCycle: EndLoop self))
			(6 (= seconds 4))
			(7 (EgoDead iconLASER))
		)
	)
)

(instance theRubble of Sq4Feature
	(properties
		x 270
		y 33
		sightAngle 40
		lookStr 1
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: rubblePoly)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 1))
			(V_DO (narrator say: 2))
			(V_SMELL (narrator say: 3))
			(V_TASTE (narrator say: 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rubblePoly of Polygon
	(properties)
)

(instance theGreenBuilding of Sq4Feature
	(properties
		y 180
		sightAngle 40
		lookStr 5
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: buildingPoly)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 5))
			(V_DO (narrator say: 6))
			(V_SMELL (narrator say: 7))
			(V_TASTE (narrator say: 8))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance buildingPoly of Polygon
	(properties)
)

(instance theSidewalk of Sq4Feature
	(properties
		x 100
		y 150
		sightAngle 40
		lookStr 9
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: sidewalkPoly)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 9))
			(V_TASTE (narrator say: 10))
			(V_SMELL (narrator say: 11))
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

(instance theStreet of Sq4Feature
	(properties
		x 300
		y 150
		sightAngle 40
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: streetPoly)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (theArea doVerb: V_LOOK))
			(V_ROPE
				((ScriptID BUNNY 4) doVerb: theVerb theItem)
			)
			(V_SMELL (narrator say: 12))
			(V_TASTE (narrator say: 13))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance streetPoly of Polygon
	(properties)
)

(instance theArea of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 14))
			(V_ROPE
				((ScriptID BUNNY 4) doVerb: theVerb theItem)
			)
			(else  (super doVerb: theVerb))
		)
	)
)
