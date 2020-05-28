;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Sound)
(use Jump)
(use Motion)
(use System)

(public
	rm050 0
	grateScript 1
)

(local
	[supportPolyPts 26] = [108 0 140 69 139 88 121 145 147 170 182 164 161 98 160 76 271 0 190 0 158 40 153 37 145]
	[sidewalk1PolyPts 30] = [0 42 45 38 32 11 41 0 72 0 115 30 122 31 136 62 136 66 140 69 140 73 69 84 55 84 32 78 0 51]
	[sidewalk2PolyPts 6] = [251 59 170 70 229 29]
	[sidewalk3PolyPts 38] = [141 189 109 157 109 143 122 137 120 145 147 170 183 165 172 129 238 118 240 120 226 126 244 137 244 144 255 153 272 157 293 154 288 173 295 185 304 189]
	[street1PolyPts 26] = [0 51 31 78 55 83 72 84 139 73 140 87 123 136 111 141 104 150 94 157 94 165 117 189 0 189]
	[street2PolyPts 16] = [249 60 223 85 252 92 236 118 173 128 162 98 161 78 170 70]
	[otherGratePolyPts 10] = [0 56 5 56 21 70 9 71 0 62]
)
(instance rm050 of SQRoom
	(properties
		picture 50
		style FADEOUT
		horizon 37
		north 35
		south 65
		west 45
		vanishingX 2
		vanishingY -25
	)
	
	(method (init)
		(LoadMany VIEW 50)
		(LoadMany SOUND 131 808 809 103 123 147)
		(if (ego has: iBomb) (Load VIEW 66) (Load SCRIPT JUMP))
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 106 140 166 140 200 165 140 176
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 0 25 60 25 72 40 0 49
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 319 0 319 40 111 40 67 0
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						319 91 319 189 291 189 284 185 278 170
						286 164 286 159 250 158 233 146 232 136
						248 136 255 106
					yourself:
				)
		)
		(switch prevRoomNum
			(north
				(HandsOff)
				(self setScript: enterScript)
			)
			(south (ego x: 255))
			(else  (ego x: 100 y: 160))
		)
		(ego init:)
		(theGrate init:)
		(otherGrate init:)
		(grateSnd init:)
		(supportPoly points: @supportPolyPts size: 13)
		(sidewalk1Poly points: @sidewalk1PolyPts size: 15)
		(sidewalk2Poly points: @sidewalk2PolyPts size: 3)
		(sidewalk3Poly points: @sidewalk3PolyPts size: 19)
		(street1Poly points: @street1PolyPts size: 13)
		(street2Poly points: @street2PolyPts size: 8)
		(otherGratePoly points: @otherGratePolyPts size: 4)
		(self setRegions: STREET)
		(super init:)
		(self setRegions: BUNNY)
		(theHole init:)
		(theLiquid init:)
		(theSupports init:)
		(theSmallSupports init:)
		(theRedBldg init:)
		(street1 init:)
		(street2 init:)
		(sidewalk1 init:)
		(sidewalk2 init:)
		(sidewalk3 init:)
		(theArea init:)
	)
	
	(method (doit &tmp theControl)
		(= theControl (ego onControl: origin))
		(cond 
			((curRoom script?) 0)
			(
				(or
					(& theControl cGREEN)
					(& theControl cCYAN)
					(& theControl cRED)
					(& theControl cBLUE)
					(& theControl cYELLOW)
				)
				(HandsOff)
				(self setScript: fallScript 0 theControl)
			)
			((== (ego edgeHit?) NORTH) (HandsOff) (self setScript: exitScript 0 1))
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript JUMP)
		(super dispose:)
	)
	
	(method (newRoom newRoomNumber)
		(if
			(and
				(== (ego edgeHit?) SOUTH)
				(< (ego x?) 133)
				(!= (curRoom script?) (ScriptID STREET 3))
				(!= ((ScriptID STREET 1) script?) (ScriptID STREET 4))
			)
			(super newRoom: 60)
		else
			(if (== newRoomNumber 70)
				(music fade: 0 10 10 1)
				(Lock SOUND 116 0)
				(Lock VIEW 105 0)
			)
			(super newRoom: newRoomNumber)
		)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 50 -1 setMotion: MoveTo 85 47 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance exitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 15 -10 self)
			)
			(1
				(= register 0)
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance grateScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 68 85 self)
			)
			(1
				(ego setMotion: MoveTo 68 87 self)
			)
			(2
				(if (not (ego has: iBomb)) (SolvePuzzle fEnterGrate 5))
				(ego view: 50 normal: 0 loop: 0 cel: 0 cycleSpeed: 12)
				(= cycles 5)
			)
			(3 (ego setCycle: CycleTo 4 1 self))
			(4
				(grateSnd number: 809 loop: 1 play: setVol: 127)
				(= cycles 1)
			)
			(5
				(theGrate hide:)
				(ego setCycle: CycleTo 5 1 self)
			)
			(6
				(grateSnd play:)
				(ego setCycle: CycleTo 4 -1)
				(= cycles 3)
			)
			(7
				(grateSnd number: 808 loop: 1 play: setVol: 127)
				(ego setCycle: EndLoop self)
			)
			(8
				(ego
					loop: 1
					cel: 0
					x: (+ (ego x?) 35)
					y: (- (ego y?) 1)
					setCycle: CycleTo 5 1 self
				)
			)
			(9 (ego setCycle: EndLoop self))
			(10
				(grateSnd number: 809 play:)
				(ego x: 500)
				(theGrate show:)
				(= seconds 3)
			)
			(11
				(if (ego has: iBomb)
					(= ticks 60)
				else
					(curRoom newRoom: 70)
				)
			)
			(12
				(music number: 103 setVol: 127 loop: 1 playBed:)
				(theGrate
					yStep: 20
					setMotion: MoveTo (- (theGrate x?) 40) -100
				)
				(explosion
					x: 83
					y: 89
					view: 66
					loop: 0
					cel: 0
					init:
					setCycle: EndLoop
					ignoreActors: TRUE
				)
				(armBit
					init:
					setMotion: JumpTo (- (armBit x?) 15) (+ (armBit y?) 30)
				)
				(legBit
					init:
					setMotion: JumpTo (- (armBit x?) 15) (- (armBit y?) 30)
				)
				(headBit
					init:
					setMotion: JumpTo (+ (armBit x?) 30) (+ (armBit y?) 10)
				)
				(= seconds 5)
			)
			(13
				(theGrate
					setPri: 15
					yStep: 20
					setMotion: MoveTo (- (theGrate x?) 10) 101 self
				)
			)
			(14
				(grateSnd number: 809 loop: 1 play:)
				(theGrate
					setPri: -1
					setMotion: JumpTo (- (theGrate x?) 20) 111 self
				)
			)
			(15
				(grateSnd number: 123 play:)
				(theGrate
					setMotion: JumpTo (- (theGrate x?) 5) 113 self
				)
			)
			(16
				(grateSnd play:)
				(= seconds 3)
			)
			(17 (narrator say: 1 self))
			(18 (EgoDead iconEXPLODE deathBLOWNUP))
		)
	)
)

(instance fallScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((& register cCYAN) (ego setPri: 0))
					((& register cRED) (ego setPri: 1))
					((& register cGREEN) (ego setPri: 2))
					((& register cBLUE) (ego setPri: 3))
					((& register cYELLOW) (ego setPri: 4))
				)
				(ego
					x: (+ (ego x?) 8)
					y: (+ (ego y?) 12)
					setLoop: (ego loop?)
				)
				(music number: 131 loop: 1 setVol: 127 play:)
				(= cycles 1)
			)
			(1
				(ego
					yStep: 20
					setMotion: MoveTo (ego x?) (+ (ego y?) 250) self
				)
			)
			(2 (= seconds 2))
			(3
				(music fade: 0 1 2 1)
				(= seconds 1)
			)
			(4
				(music number: 147 loop: 1 setVol: 127 play:)
				(narrator say: 2)
				(EgoDead iconDEAD deathFALLSTREETS)
			)
		)
	)
)

(instance theGrate of Sq4Actor
	(properties
		x 84
		y 91
		heading 170
		sightAngle 40
		view 50
		loop 2
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		lookStr 3
	)
	
	(method (doit)
		(super doit:)
		(if (== (curRoom curPic?) 31)
			(self z: 1000)
		else
			(self z: 0)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (curRoom script?)
					0
				else
					(HandsOff)
					(curRoom setScript: grateScript)
				)
			)
			(V_SMELL (narrator say: 4))
			(V_TASTE (narrator say: 5))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance otherGrate of Sq4Feature
	(properties
		x 10
		y 66
		nsBottom 200
		nsRight 320
		sightAngle 40
		lookStr 6
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: otherGratePoly)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (narrator say: 7))
			(V_SMELL (narrator say: 4))
			(V_TASTE (narrator say: 5))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance otherGratePoly of Polygon
	(properties)
)

(instance theRedBldg of Sq4Feature
	(properties
		x 294
		y 143
		nsBottom 200
		nsRight 320
		sightAngle 45
		onMeCheck $0400
		lookStr 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (narrator say: 9))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theHole of Sq4Feature
	(properties
		x 249
		y 76
		nsBottom 200
		nsRight 320
		sightAngle 45
		onMeCheck $001c
		lookStr 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SMELL (narrator say: 11))
			(V_TASTE (narrator say: 12))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theLiquid of Sq4Feature
	(properties
		x 116
		y 174
		nsBottom 200
		nsRight 320
		sightAngle 45
		onMeCheck $0040
		lookStr 13
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (narrator say: 14))
			(V_SMELL (narrator say: 15))
			(V_TASTE (narrator say: 16))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theSupports of Sq4Feature
	(properties
		x 154
		y 149
		sightAngle 45
		lookStr 17
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: supportPoly)
	)
)

(instance supportPoly of Polygon
	(properties)
)

(instance theSmallSupports of Sq4Feature
	(properties
		x 10
		y 10
		nsBottom 200
		nsRight 320
		sightAngle 45
		onMeCheck $2000
		lookStr 17
	)
)

(instance street1 of Sq4Feature
	(properties
		sightAngle 40
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: street1Poly)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 18))
			(V_SMELL (narrator say: 19))
			(V_TASTE (narrator say: 20))
			(V_ROPE
				((ScriptID BUNNY 4) doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance street1Poly of Polygon
	(properties)
)

(instance street2 of Sq4Feature
	(properties
		y 1
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: street2Poly)
	)
	
	(method (doVerb theVerb)
		(street1 doVerb: theVerb)
	)
)

(instance street2Poly of Polygon
	(properties)
)

(instance sidewalk1 of Sq4Feature
	(properties
		y 2
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: sidewalk1Poly)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 21))
			(V_SMELL (narrator say: 22))
			(V_TASTE (narrator say: 23))
			(V_ROPE
				((ScriptID BUNNY 4) doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sidewalk1Poly of Polygon
	(properties)
)

(instance sidewalk2 of Sq4Feature
	(properties
		y 2
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: sidewalk2Poly)
	)
	
	(method (doVerb theVerb)
		(sidewalk1 doVerb: theVerb)
	)
)

(instance sidewalk2Poly of Polygon
	(properties)
)

(instance sidewalk3 of Sq4Feature
	(properties
		y 2
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: sidewalk3Poly)
	)
	
	(method (doVerb theVerb)
		(sidewalk1 doVerb: theVerb)
	)
)

(instance sidewalk3Poly of Polygon
	(properties)
)

(instance theArea of Sq4Feature
	(properties
		y 1
		nsBottom 200
		nsRight 320
		lookStr 21
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROPE
				((ScriptID BUNNY 4) doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance explosion of Sq4Prop
	(properties)
)

(instance armBit of Sq4Actor
	(properties
		x 83
		y 89
		view 66
		loop 1
		signal (| ignrAct ignrHrz fixedLoop)
	)
)

(instance legBit of Sq4Actor
	(properties
		x 83
		y 89
		view 66
		loop 1
		cel 1
		signal (| ignrAct ignrHrz fixedLoop)
	)
)

(instance headBit of Sq4Actor
	(properties
		x 83
		y 89
		view 66
		loop 1
		cel 2
		signal (| ignrAct ignrHrz fixedLoop)
	)
)

(instance grateSnd of Sound
	(properties
		number 808
		priority 15
	)
)
