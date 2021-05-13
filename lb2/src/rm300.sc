;;; Sierra Script 1.0 - (do not remove this comment)
(script# 300)
(include sci.sh)
(use Main)
(use LbDoor)
(use LBRoom)
(use ExitFeature)
(use Print)
(use Talker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm300 0
	Bouncer 17
)

(local
	local0
	local1
	gEgoMoveSpeed
)
(instance rm300 of LBRoom
	(properties
		noun 20
		picture 300
		north 310
		south 260
		vanishingX 167
	)
	
	(method (init)
		(LoadMany 128 303 301 852 304 284)
		(LoadMany 132 94 40)
		(self setRegions: 91)
		(ego
			init:
			normalize: (if (ego wearingGown?) 831 else 830)
			setScale: Scaler 90 110 167 125
		)
		(switch prevRoomNum
			(north
				(ego edgeHit: 0 setHeading: 180)
				(theMusic fade:)
			)
			(south
				(ego x: 230 y: 200)
				(curRoom setScript: sOverControl)
			)
			(else 
				(taxi setScale: 199 init: posn: 269 199)
				(curRoom setScript: sOutCab)
			)
		)
		(super init:)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init: 291 165 298 175 277 179 244 177 242 169
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						0
						0
						319
						0
						319
						158
						165
						168
						127
						168
						97
						161
						98
						155
						127
						146
						123
						121
						95
						141
						84
						156
						11
						185
						122
						185
						122
						178
						161
						178
						161
						185
						231
						185
						232
						189
						0
						189
					yourself:
				)
		)
		(mugger init: setScript: sMugTimer)
		(frontDoor init: approachVerbs: 11 6 4)
		(taxiSign approachVerbs: 4 addToPic:)
		(fireHydrant init:)
		(trash init:)
		(flowersDoor init:)
		(flowerWinL init:)
		(flowerWinR init:)
		(tinyWin init:)
		(moxieSign init:)
		(streetLight setOnMeCheck: 1 16384 init:)
		(southExitFeature init:)
	)
	
	(method (newRoom n)
		(if (== n 310) (theMusic fade:))
		(super newRoom: n)
	)
)

(instance sOverControl of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath (ego x?) 170 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOutCab of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego x: 241 y: 179)
				(= seconds 1)
			)
			(1
				(theMusic2 send: 2 224 2000)
				(taxi setLoop: 5 setMotion: MoveTo 157 275 self)
				(theMusic2 fade:)
			)
			(2
				(theGame handsOn:)
				(taxi dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sMugTimer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego wearingGown?)
					(self dispose:)
				else
					(= seconds 240)
				)
			)
			(1
				(client setScript: sMuggerKills)
			)
		)
	)
)

(instance sMuggerKills of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(if (curRoom script?)
					(self dispose:)
				else
					(theGame handsOff:)
					(= cycles 1)
				)
			)
			(2
				(cond 
					((IsObjectOnControl ego 2) (ego setMotion: PolyPath 101 169 self))
					((> (ego x?) 300) (ego setMotion: PolyPath 280 165 self))
					(else (= cycles 1))
				)
			)
			(3
				(Face ego mugger)
				(noise number: 163 loop: -1 flags: 1 play:)
				(= cycles 5)
			)
			(4
				(mugger
					view: 303
					setLoop: 0
					setPri: -1
					cycleSpeed: 4
					moveSpeed: 4
					setCycle: Walk
					setMotion: MoveTo 311 162 self
				)
			)
			(5
				(mugger
					setMotion: PolyPath (+ (ego x?) 22) (+ (ego y?) 3) self
				)
			)
			(6
				(mugger
					setLoop: 1
					cel: 0
					posn: (- (mugger x?) 5) (- (mugger y?) 1)
					cycleSpeed: 12
					moveSpeed: 12
					setCycle: End self
				)
				(noise stop:)
				(sFX number: 3 play:)
			)
			(7 (= seconds 1))
			(8
				(ego
					view: 303
					setScale: Scaler 100 100 167 125
					loop: 2
					posn: (ego x?) (- (ego y?) 3)
					cycleSpeed: 12
					moveSpeed: 12
					setCycle: End self
				)
			)
			(9
				(messager say: 1 0 0 0 self)
				(ego looper: 0)
			)
			(10
				(= deathReason 12)
				(curRoom newRoom: 99)
			)
		)
	)
)

(instance sGivePass of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1 (= seconds 1))
			(2
				(ego put: 6)
				(ego
					view: 301
					loop: 0
					posn: 88 160
					setScale: Scaler 100 100 167 125
					cycleSpeed: 10
					moveSpeed: 10
					setCycle: End self
				)
			)
			(3
				(messager say: 3 11 0 0 self)
			)
			(4
				(ego
					posn: 79 159
					setScale: Scaler 90 110 167 125
					normalize: (if (ego wearingGown?) 831 else 830)
				)
				(= cycles 1)
			)
			(5
				(theGame handsOn:)
				(if (mugger script?) ((mugger script?) dispose:))
				(theGame handsOff:)
				(mugger setScript: sMuggerKills)
				(self dispose:)
			)
		)
	)
)

(instance sHailCab of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 252 loop: -1 flags: 1 play: 20)
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(taxi init: setScale: 199 posn: 415 186)
				(ego setMotion: PolyPath 242 179 self)
			)
			(2
				(ego
					view: (if (ego wearingGown?) 304 else 852)
					setLoop: (if (ego wearingGown?) 2 else 0)
					posn: (- (ego x?) 2) (- (ego y?) 1)
					setScale: Scaler 105 0 190 0
					setCycle: End self
				)
				(theMusic number: 97 loop: 1 flags: 1 play:)
			)
			(3
				(theMusic2 send: 2 224 2000 fade: 127 5 5 0)
				(taxi
					setLoop: 5
					setMotion: MoveTo (+ (taxiSign x?) 10) (+ (taxiSign y?) 25) self
				)
			)
			(4
				(theMusic2 send: 2 224 1600)
				(= cycles 1)
			)
			(5
				(theMusic2 send: 2 224 1200)
				(= cycles 1)
			)
			(6
				(theMusic2 send: 2 224 800)
				(= cycles 1)
			)
			(7
				(theMusic2 send: 2 224 400)
				(= cycles 1)
			)
			(8
				(theMusic2 send: 2 224 0)
				(noise number: 40 flags: 1 play: self)
			)
			(9
				(ego normalize: 830 hide:)
				(= cycles 1)
			)
			(10 (curRoom newRoom: 250))
		)
	)
)

(instance sEnterBar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gEgoMoveSpeed (ego moveSpeed?))
				(= cycles 1)
			)
			(1
				(ego
					view: 301
					loop: 0
					cel: 0
					posn: (+ (ego x?) 10) (ego y?)
					setScale: Scaler 100 100 167 125
					setSpeed: 6
					setCycle: CT 5 1 self
				)
			)
			(2
				(sFX number: 297 play:)
				(ego setCycle: CT 6 1 self)
			)
			(3 (ego setCycle: CT 2 -1 self))
			(4 (ego setCycle: CT 5 1 self))
			(5 (ego setCycle: CT 6 1 self))
			(6
				(ego
					loop: 6
					posn: (- (ego x?) 10) (ego y?)
					setSpeed: gEgoMoveSpeed
					setScale: Scaler 90 110 167 125
					normalize: (if (ego wearingGown?) 831 else 830)
				)
				(= cycles 5)
			)
			(7 (messager say: 3 4 0 1 self))
			(8
				(switch
					(Print
						addText: 2 0 0 0
						addButton: 1 14 0 0 1 5 (WhichLanguage 25 25 25 25 20)
						addButton: 2 14 0 0 2 5 (WhichLanguage 43 43 43 43 38)
						addButton: 3 14 0 0 3 5 (WhichLanguage 61 61 61 61 56)
						init:
					)
					(1 (= cycles 1))
					(2 (messager say: 3 4 0 5 self))
					(3
						(= local0 1)
						(messager say: 3 4 0 6 self)
					)
					(else  (= cycles 1))
				)
			)
			(9 (= cycles 1))
			(10
				(if local0
					(client setScript: sAskEnterBar)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
		)
	)
)

(instance sAskEnterBar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 0)
				(if local0
					(switch (curRoom setInset: (ScriptID 20 0))
						(1029
							(messager say: 3 4 0 7 self)
							(= register 1)
						)
						(else 
							(messager say: 3 4 0 8 self)
							(= local0 0)
						)
					)
				else
					(messager say: 3 4 0 8 self)
					(= local0 0)
				)
			)
			(1
				(if (== register 1)
					(messager say: 3 4 0 9 self)
				else
					(= cycles 1)
				)
			)
			(2
				(if (== register 1)
					(= local1 1)
					(= disabledIcons 1024)
					(= oldCanControl (= oldCanInput 1))
					(frontDoor doVerb: 4)
				else
					(theGame handsOn:)
				)
				(self dispose:)
			)
		)
	)
)

(instance mugger of Actor
	(properties
		x 330
		y 160
		noun 1
		view 303
	)
)

(instance frontDoor of LbDoor
	(properties
		x 104
		y 155
		noun 3
		approachX 79
		approachY 159
		view 304
		entranceTo 310
		moveToX 121
		moveToY 143
		enterType 0
		exitType 0
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if local1
					(super doVerb: theVerb &rest)
				else
					(curRoom setScript: sEnterBar)
				)
			)
			(11
				(curRoom setScript: sGivePass)
			)
			(6
				(if local0
					(theGame handsOff:)
					(curRoom setScript: sAskEnterBar)
				else
					(curRoom setScript: sEnterBar)
				)
			)
			(14
				(if local0
					(theGame handsOff:)
					(curRoom setScript: sAskEnterBar)
				else
					(curRoom setScript: sEnterBar)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(super cue: &rest)
		(if (ego wearingGown?) (curRoom setScript: sHailCab))
	)
	
	(method (createPoly)
		(super createPoly: 77 146 109 148 109 157 77 154)
	)
)

(instance taxi of Actor
	(properties
		x 415
		y 186
		noun 8
		view 852
		loop 5
		moveSpeed 4
	)
)

(instance taxiSign of View
	(properties
		x 259
		y 174
		noun 9
		sightAngle 40
		approachX 242
		approachY 179
		view 284
		cel 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sHailCab)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fireHydrant of Feature
	(properties
		x 142
		y 169
		noun 10
		nsTop 153
		nsLeft 136
		nsBottom 185
		nsRight 148
		sightAngle 40
	)
)

(instance trash of Feature
	(properties
		x 33
		y 143
		noun 11
		nsTop 133
		nsLeft 13
		nsBottom 154
		nsRight 54
		sightAngle 40
	)
)

(instance flowersDoor of Feature
	(properties
		x 185
		y 125
		noun 5
		nsTop 98
		nsLeft 171
		nsBottom 152
		nsRight 199
		sightAngle 40
	)
)

(instance flowerWinL of Feature
	(properties
		x 152
		y 122
		noun 6
		nsTop 98
		nsLeft 135
		nsBottom 147
		nsRight 169
		sightAngle 40
	)
)

(instance flowerWinR of Feature
	(properties
		x 225
		y 121
		noun 6
		nsTop 98
		nsLeft 200
		nsBottom 144
		nsRight 251
		sightAngle 40
	)
)

(instance tinyWin of Feature
	(properties
		x 91
		y 109
		noun 4
		nsTop 106
		nsLeft 88
		nsBottom 112
		nsRight 95
		sightAngle 40
	)
)

(instance moxieSign of Feature
	(properties
		x 34
		y 78
		noun 7
		nsTop 60
		nsBottom 97
		nsRight 69
		sightAngle 40
	)
)

(instance streetLight of Feature
	(properties
		x 275
		y 78
		noun 12
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsLeft 219
		nsBottom 189
		nsRight 289
		cursor 11
		exitDir 3
		noun 15
	)
)

(instance Bouncer of Narrator
	(properties
		x 100
		y 50
		talkWidth 150
		back 15
	)
	
	(method (init)
		(= font userFont)
		(super init: &rest)
	)
)

(instance noise of Sound
	(properties
		flags $0001
	)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)
