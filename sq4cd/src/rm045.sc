;;; Sierra Script 1.0 - (do not remove this comment)
(script# 45)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use PolyPath)
(use Polygon)
(use ForCount)
(use LoadMany)
(use DPath)
(use Sound)
(use Motion)
(use System)

(public
	rm045 0
)

(local
	local0 =  250
	[shipPolyPts 56] =
		[18 115 42 97 92 85 119 55 130 51 151 66 159 65 163 58 175 52 193 61 211 60
		256 67 275 84 274 95 261 104 249 104 230 120 217 119 215 131 184 137 182 146
		162 148 107 142 93 134 80 131 63 110 48 119 18 125]
	[compartmentPolyPts 10] = [127 122 153 112 159 123 149 131 129 132]
	[cockpitPolyPts 12] = [219 120 218 131 206 136 197 132 197 122 205 116]
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

(instance rm045 of SQRoom
	(properties
		style FADEOUT
		horizon 150
		north 30
		east 50
		south 60
		west 40
	)
	
	(method (init)
		(LoadMany VIEW 4 27 46)
		(LoadMany SOUND 149)
		(Load SCRIPT FORCOUNT)
		(switch prevRoomNum
			(25
				(ego x: 20 y: (+ horizon (ego yStep?)))
			)
			(30
				(ego
					loop: 2
					x:
						(cond 
							((< (ego x?) 80) 80)
							((> (ego x?) 240) 240)
							(else (ego x?))
						)
					y: 162
				)
			)
			(35
				(ego x: 300 y: (+ horizon (ego yStep?)))
			)
			(40 (ego x: 5 y: 160))
			(50 (ego x: 315 y: 160))
			(60
				(ego y: 189 setMotion: MoveTo (ego x?) 188)
			)
			(72
				(gear1 init: stopUpd:)
				(gear2 init: stopUpd:)
				(flap init: stopUpd:)
				(HandsOff)
				(self setScript: egoClimbsDown)
			)
			(else 
				(ego loop: 2 posn: 160 162)
			)
		)
		(lightningScript start: 1)
		(lightning1 init: hide: setScript: lightningScript)
		(if (Btst fPoliceLanded)
			(Load PICTURE 46)
			(Load SCRIPT DPATH)
			(LoadMany VIEW 7 13)
			(LoadMany SOUND 109 158 136 105 159 888)
			(shipPoly points: @shipPolyPts size: 27)
			(compartmentPoly points: @compartmentPolyPts size: 5)
			(cockpitPoly points: @cockpitPolyPts size: 6)
			(shipFeature init:)
			(compartmentFeature init:)
			(cockpitFeature init:)
			(self horizon: 140)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							207 0 207 148 276 153 273 164 251 165 246 175
							206 175 193 171 147 172 141 178 103 178 81 170
							89 165 45 154 0 154 0 0
						yourself:
					)
			)
			(ego setLoop: -1 illegalBits: 0)
			(LoadMany VIEW 7 45)
			(gear1 init: stopUpd:)
			(gear2 init: stopUpd:)
			(flap init: stopUpd:)
		)
		(self drawPic: 45)
		(if (or (Btst fPoliceLanded) (== prevRoomNum 72))
			(self overlay: 46 0)
		)
		(theRubble init:)
		(theRightBldg init:)
		(theLeftBldg init:)
		(theBackBldgs init:)
		(theStreet init:)
		(theArea init:)
		(self setRegions: STREET)
		(thunder init:)
		(super init:)
		(self setRegions: BUNNY)
		(ego init:)
		(= picture 45)
		(if (!= prevRoomNum 72) (HandsOn))
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((or (not (Btst fPoliceLanded)) (curRoom script?)) 0)
			(
				(or
					(not (if (< 155 (ego y?)) (< (ego y?) 185)))
					(> (ego x?) 250)
					(< local0 0)
				)
				(curRoom setScript: shootEgo)
			)
			(else (= local0 (- local0 1)))
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (== newRoomNumber 30)
			(cond 
				((< (ego x?) 80) (= newRoomNumber 25))
				((> (ego x?) 240) (= newRoomNumber 35))
			)
		)
		(super newRoom: newRoomNumber)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: -5 y: 160 setMotion: MoveTo 10 160 self)
			)
			((HandsOn) (self dispose:))
		)
	)
)

(instance lightningScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 10 20)))
			(1
				(self start: 0)
				(= seconds (Random 3 6))
			)
			(2
				(if (== (curRoom script?) egoHides)
					(self init:)
				else
					(client
						show:
						setCel: 0
						setCycle: ForwardCounter (Random 1 3) self
					)
				)
			)
			(3
				(thunder play:)
				(client hide:)
				(= cycles 15)
			)
			(4 (self init:))
		)
	)
)

(instance thunder of Sound
	(properties
		number 149
		priority 13
	)
)

(instance egoHides of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(curRoom horizon: 0)
				(ego setMotion: PolyPath 132 150 self)
			)
			(1
				(narrator say: 18)
				(ego
					posn: 137 142
					view: 45
					loop: 3
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(SolvePuzzle fHideInCompartment 5)
				(ego hide: dispose:)
				(= seconds 4)
			)
			(3
				(music number: 107 loop: 1 vol: 127 play:)
				(= seconds 3)
			)
			(4
				(globalSound number: 158 loop: -1 setVol: 127 play:)
				(gear1
					setPri: 1
					cycleSpeed: 12
					setMotion: MoveTo (gear1 x?) 104
					setCycle: EndLoop flap
				)
				(gear2
					setPri: 1
					setMotion: DPath 188 134 174 120 174 78 self
				)
			)
			(5
				(music number: 109 loop: -1 play: setVol: 127)
				(= seconds 3)
			)
			(6
				(curRoom newRoom: 72)
				(client setScript: 0)
			)
		)
	)
)

(instance egoClimbsDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					normal: 0
					view: 45
					loop: 3
					cel: 8
					x: 137
					y: 142
					setCycle: BegLoop self
				)
			)
			(1
				(EgoHeadMove)
				(ego
					normal: 1
					view: 0
					x: 135
					y: 164
					setCycle: Walk
					setMotion: MoveTo 157 172 self
				)
			)
			(2
				(ego setMotion: PolyPath 160 250 self)
			)
			(3
				(ego dispose:)
				(egoHides start: 2)
				(curRoom setScript: egoHides)
			)
		)
	)
)

(instance shootEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cop1
					init:
					setCycle: Walk
					setMotion: MoveTo 310 (cop1 y?) self
				)
				(cop2
					init:
					setCycle: Walk
					setMotion: PolyPath 0 (cop2 y?)
				)
			)
			(1
				(cop1 view: 13 cel: 0 setMotion: 0 setCycle: CycleTo 4 1 self)
			)
			(2
				(HandsOff)
				(cop2 setMotion: 0)
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

(instance deathScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom horizon: 0)
				(ego setMotion: PolyPath 210 175 self)
			)
			(1
				(ego setMotion: MoveTo (ego x?) 165 self)
			)
			(2
				(ego
					view: 45
					loop: 4
					cel: 0
					x: (+ (ego x?) 5)
					y: (- (ego y?) 13)
					setCycle: EndLoop self
				)
			)
			(3
				(ego hide: dispose:)
				(= seconds 3)
			)
			(4
				(narrator say: 1)
				(globalSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 4)
			)
			(5
				(globalSound vol: 85 play:)
				(= cycles 2)
			)
			(6
				(globalSound vol: 55 play:)
				(= cycles 6)
			)
			(7
				(globalSound vol: 25 play:)
				(= cycles 4)
			)
			(8
				(globalSound vol: 5 play:)
				(= cycles 10)
			)
			(9
				(globalSound stop:)
				(narrator say: 2)
				(EgoDead iconLASER deathSPLEEN)
			)
		)
	)
)

(instance lightning1 of Sq4Prop
	(properties
		x 150
		y 48
		view 46
		lookStr 3
	)
)

(instance gear1 of Sq4Actor
	(properties
		x 132
		y 120
		view 45
		priority 13
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		lookStr 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 4))
			(V_DO
				(if (and (Btst fPoliceLanded) (not (curRoom script?)))
					(if (not script)
						(HandsOff)
						(curRoom setScript: egoHides)
					else
						(narrator say: 5)
					)
				else
					(super doVerb: theVerb)
				)
			)
			(V_ROPE (narrator say: 6))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance gear2 of Sq4Actor
	(properties
		x 194
		y 134
		view 45
		loop 1
		priority 9
		signal (| ignrAct ignrHrz fixPriOn)
		lookStr 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 4))
			(V_DO (narrator say: 7))
			(V_ROPE (narrator say: 6))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance flap of Sq4Prop
	(properties
		x 141
		y 111
		view 45
		loop 2
		priority 13
		signal (| ignrAct ignrHrz fixPriOn)
		lookStr 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 8))
			(V_DO
				(if (Btst fPoliceLanded)
					(if (not script)
						(HandsOff)
						(curRoom setScript: egoHides)
					else
						(narrator say: 5)
					)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(if cel
			(flapSound number: 159 loop: 1 vol: 127 play:)
		else
			(self setCycle: EndLoop self)
		)
	)
)

(instance flapSound of Sound
	(properties)
)

(instance cop1 of Sq4Actor
	(properties
		x 319
		y 175
		view 7
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 703 say: 3)
			)
			(else  (super doVerb: &rest))
		)
	)
)

(instance cop2 of Sq4Actor
	(properties
		x 330
		y 185
		view 7
	)
	
	(method (doVerb theVerb)
		(cop1 doVerb: theVerb)
	)
)

(instance theRubble of Sq4Feature
	(properties
		x 144
		y 99
		z 12
		nsBottom 200
		nsRight 320
		sightAngle 45
		onMeCheck $0040
		lookStr 9
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 9))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theRightBldg of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		onMeCheck $0080
		lookStr 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 10))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theLeftBldg of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		onMeCheck $2000
		lookStr 11
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 11))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theBackBldgs of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		onMeCheck (| NEARCHECK ISNOTHIDDEN)
		lookStr 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 12))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theArea of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		lookStr 13
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 13))
			(24
				((ScriptID BUNNY 4) doVerb: theVerb theItem)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shipFeature of Sq4Feature
	(properties
		x 160
		y 100
		sightAngle 25
		lookStr 14
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: shipPoly)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 14))
			(V_DO (narrator say: 15))
			(V_ROPE (narrator say: 6))
			(V_SMELL (narrator say: 16))
			(V_TASTE (narrator say: 17))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shipPoly of Polygon
	(properties)
)

(instance compartmentFeature of Sq4Feature
	(properties
		x 160
		y 189
		z 89
		sightAngle 25
		lookStr 18
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: compartmentPoly)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 18))
			(V_DO
				(gear1 doVerb: theVerb theItem)
			)
			(V_ROPE (narrator say: 19))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance compartmentPoly of Polygon
	(properties)
)

(instance cockpitFeature of Sq4Feature
	(properties
		x 207
		y 189
		z 64
		sightAngle 25
		lookStr 20
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: cockpitPoly)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb V_LOOK) (narrator say: 20))
			((== theVerb V_DO) (HandsOff) (curRoom setScript: deathScript))
			(else (super doVerb: theVerb))
		)
	)
)

(instance cockpitPoly of Polygon
	(properties)
)

(instance theStreet of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		onMeCheck NEARCHECK
		lookStr 21
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 21))
			(V_SMELL (narrator say: 22))
			(V_TASTE (narrator say: 23))
			(24
				((ScriptID BUNNY 4) doVerb: theVerb theItem)
			)
			(else  (super doVerb: theVerb))
		)
	)
)
