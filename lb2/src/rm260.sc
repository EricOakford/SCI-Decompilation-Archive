;;; Sierra Script 1.0 - (do not remove this comment)
(script# 260)
(include sci.sh)
(use Main)
(use LbDoor)
(use LBRoom)
(use ExitFeature)
(use Talker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm260 0
	Stinky 32
	Biff 33
	Tubby 34
)

(local
	local0 =  1
	local1 =  1
	local2 =  1
)
(instance rm260 of LBRoom
	(properties
		noun 13
		picture 260
		north 270
		south 300
		vanishingY 50
	)
	
	(method (init)
		(LoadMany 128 284 260 852 282 830)
		(LoadMany 132 40 97 260 261)
		(self setRegions: 91)
		(ego init: normalize: 830 setScale: Scaler 98 0 190 50)
		(switch prevRoomNum
			(north
				(ego edgeHit: 0 setHeading: 180)
			)
			(south
				(curRoom setScript: sUpCurb)
			)
			(else 
				(curRoom setScript: sOutCab)
			)
		)
		(super init:)
		(theMusic number: 260 flags: 1 loop: 1 play:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						319
						0
						319
						189
						311
						189
						311
						166
						151
						171
						142
						159
						122
						159
						122
						165
						143
						165
						147
						171
						90
						171
						83
						145
						61
						143
						78
						172
						63
						172
						63
						178
						9
						178
						9
						189
						0
						189
						0
						0
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 92 174 127 174 127 179 92 179
					yourself:
				)
		)
		(if (not (Btst 129))
			(kidR
				init:
				approachVerbs: 6 4 2 8 15
				setScript: (sKidsPlaying new:)
			)
			(kidL
				init:
				approachVerbs: 6 4 2 8 15
				setScript: (sKidsPlaying new:)
			)
			(kid
				init:
				approachVerbs: 6 4 2 8 15
				setScript: (sKidsPlaying new:)
			)
		)
		(southExitFeature init:)
		(sky setOnMeCheck: 1 8 init:)
		(window1 init:)
		(window2 init:)
		(store init:)
		(lofats init:)
		(storeSign init:)
		(lamp1 init:)
		(lamp2 init:)
		(plant1 init:)
		(plant2 init:)
		(street init:)
		(stairs init:)
		(streetLamp setOnMeCheck: 1 2 init:)
		(frontDoor init:)
		(taxiSign init:)
		(taxi init: setScale: 220)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego 16) (curRoom setScript: sDownStairs))
		)
	)
	
	(method (dispose)
		(carSound dispose:)
		(theMusic fade: 0 30 12 1)
		(super dispose: &rest)
	)
)

(instance sDownStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 3)
			)
			(1
				(ego setMotion: PolyPath 91 174 self)
			)
			(2
				(messager say: 11 0 11 0 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sUpCurb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 161 170 self)
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
				(ego x: 127 y: 171)
				(taxi init: posn: 115 188 setLoop: 5)
				(= cycles 1)
			)
			(1
				(theMusic2 send: 2 224 2000 fade:)
				(taxi setMotion: MoveTo -30 251 self)
			)
			(2
				(theGame handsOn:)
				(taxi posn: 411 171)
				(taxi dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sKidsPlaying of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: CT (Random 1 5) 1 self)
			)
			(1 (= ticks (Random 10 120)))
			(2 (client setCycle: End self))
			(3 (= ticks (Random 10 120)))
			(4
				(client setCycle: CT (Random 1 5) -1 self)
			)
			(5 (= ticks (Random 10 120)))
			(6 (client setCycle: Beg self))
			(7 (= ticks (Random 10 120)))
			(8 (self changeState: 0))
		)
	)
)

(instance sTradeBaseBall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(kid setScript: 0)
				(kidL cel: 0)
				(= cycles 1)
			)
			(2
				(ego view: 282 loop: 5 cel: 0 setCycle: End self)
			)
			(3
				(messager say: 18 7 0 0 self)
				(kidL setCycle: CT 8 1)
			)
			(4
				(kid loop: 3 cel: 0 setCycle: CT 7 1 self)
			)
			(5
				(kid setCycle: End self)
				(kidL setCycle: CT 0 -1)
			)
			(6 (kid loop: 0) (= cycles 1))
			(7
				(kid setScript: sKidsPlaying)
				(theGame points: 1 129)
				((ScriptID 22 0) doit: 4)
				((ScriptID 21 0) doit: 791)
				((ScriptID 21 1) doit: 773)
				(ego put: 4 get: 22 setCycle: Beg self)
			)
			(8
				(theMusic2 number: 261 flags: 1 loop: 1 play:)
				(ego view: 830 setCycle: StopWalk -1)
				(= cycles 2)
			)
			(9
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sHailTaxi of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: PolyPath (- (taxiSign x?) 20) (+ (taxiSign y?) 2) self
				)
			)
			(1
				(theMusic2 number: 252 flags: 1 loop: -1 play: 20)
				(ego
					view: 852
					loop: 0
					setScale: Scaler 100 100 190 50
					posn: (- (ego x?) 2) (ego y?)
					setCycle: End self
				)
				(theMusic number: 97 flags: 1 loop: 1 play:)
			)
			(2
				(theMusic2 send: 2 224 2000 fade: 127 5 5 0)
				(taxi
					init:
					setLoop: 5
					setMotion: MoveTo (+ (ego x?) 28) (+ (ego y?) 11) self
				)
			)
			(3
				(theMusic2 send: 2 224 1600)
				(= cycles 1)
			)
			(4
				(theMusic2 send: 2 224 1200)
				(= cycles 1)
			)
			(5
				(theMusic2 send: 2 224 800)
				(= cycles 1)
			)
			(6
				(theMusic2 send: 2 224 400)
				(= cycles 1)
			)
			(7
				(ego
					normalize: 830
					ignoreActors:
					setScale: Scaler 98 0 190 50
					setMotion: MoveTo 122 185 self
				)
			)
			(8
				(theMusic2 send: 2 224 0)
				(carSound play:)
				(ego hide:)
				(= cycles 5)
			)
			(9 (curRoom newRoom: 250))
		)
	)
)

(instance frontDoor of LbDoor
	(properties
		x 155
		y 112
		noun 1
		approachX 151
		approachY 172
		view 260
		entranceTo 270
		moveToX 126
		moveToY 164
		enterType 0
		exitType 0
	)
	
	(method (createPoly)
		(super createPoly: 135 162 162 162 162 169 135 169)
	)
)

(instance kid of Prop
	(properties
		x 40
		y 172
		noun 18
		approachX 63
		approachY 172
		view 282
		signal $5000
		cycleSpeed 8
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(6
				(switch (= temp0 (curRoom setInset: (ScriptID 20 0)))
					(1026 (messager say: 18 6 5))
					(1027 (messager say: 18 6 6))
					(1028 (messager say: 18 6 10))
					(519 (messager say: 18 6 4))
					(else 
						(cond 
							((< temp0 512) (messager say: 18 6 1))
							((< temp0 768) (messager say: 18 6 2))
							((< temp0 1024) (messager say: 18 6 3))
						)
					)
				)
			)
			(7
				(curRoom setScript: sTradeBaseBall)
			)
			(4
				(if local0
					(messager say: 18 4 7)
					(= local0 0)
				else
					(messager say: 18 4 8)
				)
			)
			(2
				(if local1
					(messager say: 18 2 7)
					(= local1 0)
				else
					(messager say: 18 2 8)
				)
			)
			(1
				(if (Btst 129)
					(messager say: 18 1 12)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance kidR of Prop
	(properties
		x 42
		y 174
		noun 17
		approachX 63
		approachY 172
		view 282
		loop 1
		signal $5000
		cycleSpeed 8
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(6
				(switch (= temp0 (curRoom setInset: (ScriptID 20 0)))
					(1026 (messager say: 17 6 5))
					(1027 (messager say: 17 6 6))
					(1028 (messager say: 17 6 10))
					(519 (messager say: 17 6 4))
					(else 
						(cond 
							((< temp0 512) (messager say: 17 6 1))
							((< temp0 768) (messager say: 17 6 2))
							((< temp0 1024) (messager say: 17 6 3))
						)
					)
				)
			)
			(2
				(cond 
					((Btst 129) (messager say: 17 2 12))
					(local2 (messager say: 17 2 7) (= local2 0))
					(else (messager say: 17 2 8))
				)
			)
			(1
				(if (Btst 129)
					(messager say: 17 1 12)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance kidL of Prop
	(properties
		x 29
		y 173
		noun 19
		approachX 63
		approachY 172
		view 282
		loop 2
		signal $5000
		cycleSpeed 8
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(6
				(switch (= temp0 (curRoom setInset: (ScriptID 20 0)))
					(1026 (messager say: 19 6 5))
					(1027 (messager say: 19 6 6))
					(1028 (messager say: 19 6 10))
					(519 (messager say: 19 6 4))
					(else 
						(cond 
							((< temp0 512) (messager say: 19 6 1))
							((< temp0 768) (messager say: 19 6 2))
							((< temp0 1024) (messager say: 19 6 3))
						)
					)
				)
			)
			(2
				(if (Btst 129)
					(messager say: 19 2 12)
				else
					(super doVerb: theVerb)
				)
			)
			(1
				(if (Btst 129)
					(messager say: 19 1 12)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance taxi of Actor
	(properties
		x 411
		y 171
		view 852
		loop 5
		cel 1
		cycleSpeed 4
		moveSpeed 0
	)
)

(instance Biff of Narrator
	(properties
		back 15
	)
	
	(method (init)
		(= font userFont)
		(= showTitle 1)
		(super init: &rest)
	)
)

(instance Stinky of Narrator
	(properties
		back 15
	)
	
	(method (init)
		(= font userFont)
		(= showTitle 1)
		(super init: &rest)
	)
)

(instance Tubby of Narrator
	(properties
		back 15
	)
	
	(method (init)
		(= font userFont)
		(= showTitle 1)
		(super init: &rest)
	)
)

(instance taxiSign of View
	(properties
		x 109
		y 175
		noun 3
		sightAngle 40
		view 284
		loop 1
		cel 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sHailTaxi)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sky of Feature
	(properties
		y 3
		noun 16
	)
)

(instance window1 of Feature
	(properties
		y 3
		noun 4
		nsTop 33
		nsLeft 28
		nsBottom 71
		nsRight 57
	)
)

(instance window2 of Feature
	(properties
		y 3
		noun 4
		nsTop 43
		nsLeft 75
		nsBottom 74
		nsRight 98
	)
)

(instance store of Feature
	(properties
		y 3
		noun 5
		nsTop 78
		nsBottom 169
		nsRight 64
	)
)

(instance lofats of Feature
	(properties
		y 3
		noun 6
		nsTop 92
		nsLeft 108
		nsBottom 166
		nsRight 206
	)
)

(instance storeSign of Feature
	(properties
		y 4
		noun 7
		nsTop 102
		nsLeft 148
		nsBottom 111
		nsRight 188
	)
)

(instance lamp1 of Feature
	(properties
		y 4
		noun 8
		nsTop 97
		nsLeft 125
		nsBottom 118
		nsRight 137
	)
)

(instance lamp2 of Feature
	(properties
		y 4
		noun 8
		nsTop 104
		nsLeft 189
		nsBottom 118
		nsRight 198
	)
)

(instance plant1 of Feature
	(properties
		y 3
		noun 9
		nsTop 64
		nsLeft 110
		nsBottom 79
		nsRight 135
	)
)

(instance plant2 of Feature
	(properties
		y 3
		noun 9
		nsTop 80
		nsLeft 163
		nsBottom 89
		nsRight 199
	)
)

(instance street of Feature
	(properties
		y 3
		noun 10
		nsTop 168
		nsBottom 189
		nsRight 319
	)
)

(instance stairs of Feature
	(properties
		y 3
		noun 11
		nsTop 89
		nsLeft 66
		nsBottom 169
		nsRight 101
	)
)

(instance streetLamp of Feature
	(properties
		y 3
		noun 12
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsBottom 189
		nsRight 320
		cursor 11
		exitDir 3
		noun 2
	)
)

(instance carSound of Sound
	(properties
		flags $0001
		number 40
	)
)
