;;; Sierra Script 1.0 - (do not remove this comment)
(script# 280)
(include sci.sh)
(use Main)
(use LbDoor)
(use LBRoom)
(use ExitFeature)
(use Inset)
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
	rm280 0
	carSound 1
	drunk 2
	theDrunk 41
)

(local
	[local0 20]
)
(instance rm280 of LBRoom
	(properties
		noun 6
		picture 280
		north 290
		south 210
		vanishingX 155
	)
	
	(method (init)
		(LoadMany 128 285 284 852 281 830 283)
		(LoadMany 132 40 97 281 280 252)
		(self setRegions: 91)
		(ego init: normalize: 830 setScale: Scaler 90 20 190 0)
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
		(theMusic number: 280 flags: 1 loop: 1 play:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						121
						129
						101
						129
						123
						151
						124
						160
						94
						163
						94
						179
						55
						182
						49
						187
						206
						187
						195
						189
						0
						189
						0
						0
						319
						0
						319
						189
						311
						139
						256
						144
						288
						152
						226
						161
						209
						151
						182
						154
						168
						127
						140
						127
						140
						116
						148
						116
						148
						120
						162
						120
						162
						106
						121
						106
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 246 164 277 162 275 167 249 170
					yourself:
				)
		)
		(if (Btst 8)
			(if
				(not
					(if (or (ego has: 0) (ego has: 3)) else (Btst 7))
				)
				(newsPaper init: approachVerbs: 1 4 8)
			)
		else
			(drunk init: approachVerbs: 1 2 6 setScript: sSnore)
		)
		(column setOnMeCheck: 1 8 init:)
		(leftLion setOnMeCheck: 1 64 init:)
		(rightLion setOnMeCheck: 1 128 init:)
		(sidewalk setOnMeCheck: 1 2 init:)
		(steps setOnMeCheck: 1 16384 init:)
		(street setOnMeCheck: 1 4 init:)
		(policeSign init:)
		(windows setOnMeCheck: 1 32 init:)
		(southExitFeature init:)
		(frontDoor init:)
		(stuckDoor init: approachVerbs: 4)
		(taxiSign init:)
		(taxi init: setScale: 200)
		(Message msgGET 280 2 48 0 1 @local0)
		(theDrunk name: @local0)
	)
	
	(method (dispose)
		(carSound dispose:)
		(theMusic fade: 0 30 12 1)
		(super dispose: &rest)
	)
)

(instance sPutUpInset of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(messager say: (if register 3 else 14) 1 0 0 self)
			)
			(2 (self dispose:))
		)
	)
)

(instance sUpCurb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 164 170 self)
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
				(ego x: 209 y: 172)
				(taxi init: x: 238 y: 189 setLoop: 5)
				(= cycles 1)
			)
			(1
				(theMusic2 send: 2 224 2000 fade:)
				(taxi setMotion: MoveTo 0 280 self)
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

(instance sDrunkWakes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 8)
				(ego setMotion: PolyPath 56 182 self)
			)
			(1 (Face ego drunk self))
			(2
				(narrator x: 15 y: 15)
				(messager say: 2 4 1 1)
				(ego
					view: 283
					setScale: Scaler 100 100 190 0
					loop: 1
					cel: 0
					setCycle: End self
				)
			)
			(3
				(drunk setScript: 0 cycleSpeed: 10 setCycle: End self)
			)
			(4
				(messager say: 2 4 1 2)
				(ego setCycle: Beg self)
			)
			(5
				(ego
					view: 830
					setScale: Scaler 90 20 190 0
					setCycle: StopWalk -1
				)
				(narrator x: -1 y: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetNewsPaper of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 281
					setScale: Scaler 100 100 190 0
					loop: 0
					cel: 0
					setCycle: CT 2 1 self
				)
				(newsPaper dispose:)
			)
			(1 (ego setCycle: End self))
			(2
				(theGame handsOn:)
				(ego get: 0)
				((ScriptID 21 0) doit: 769)
				(ego
					view: 830
					setScale: Scaler 90 20 190 0
					loop: 1
					cel: 1
					setCycle: StopWalk -1
					setMotion: MoveTo (+ (ego x?) 5) (ego y?) self
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance sSnore of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 30))
			(1
				(drunk setCycle: CT 2 1)
				(carSound number: 281 flags: 1 loop: 1 play: self)
			)
			(2 (= ticks 30))
			(3
				(drunk setCycle: CT 0 -1 self)
			)
			(4 (self init:))
		)
	)
)

(instance sHailCab of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic2 number: 252 flags: 1 loop: -1 play: 20)
				(ego
					setMotion: PolyPath (- (taxiSign x?) 20) (+ (taxiSign y?) 2) self
				)
			)
			(1
				(ego
					view: 852
					loop: 0
					posn: (- (ego x?) 2) (ego y?)
					setCycle: End self
				)
				(theMusic number: 97 flags: 1 loop: 1 play:)
			)
			(2
				(theMusic2 send: 2 224 2000 fade: 127 5 5 0)
				(taxi init: setLoop: 5 setMotion: MoveTo 238 189 self)
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
				(theMusic2 send: 2 224 0)
				(carSound number: 40 loop: 1 flags: 1 play:)
				(ego hide:)
				(= ticks 30)
			)
			(8 (curRoom newRoom: 250))
		)
	)
)

(instance frontDoor of LbDoor
	(properties
		x 120
		y 72
		noun 1
		approachX 131
		approachY 131
		view 281
		loop 2
		entranceTo 290
		moveToX 152
		moveToY 119
		enterType 0
		exitType 0
	)
	
	(method (createPoly)
		(super createPoly: 116 121 145 118 146 125 117 128)
	)
)

(instance drunk of Prop
	(properties
		x 20
		y 184
		noun 2
		approachX 56
		approachY 182
		view 283
		signal $4000
		cycleSpeed 30
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not (Btst 8))
					(curRoom setScript: sDrunkWakes)
				else
					(messager say: 2 4 2)
				)
			)
			(6 (messager say: 2 6 4))
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
		cycleSpeed 4
		moveSpeed 0
	)
)

(instance newsPaper of View
	(properties
		x 37
		y 187
		noun 11
		approachX 54
		approachY 186
		view 281
		loop 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setInset: paperInset)
			)
			(1
				(curRoom setInset: paperInset)
			)
			(8
				(curRoom setInset: paperInset)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance taxiSign of View
	(properties
		x 257
		y 165
		noun 4
		sightAngle 40
		view 284
		loop 1
		cel 1
		priority 12
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sHailCab)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance stuckDoor of Feature
	(properties
		x 155
		y 98
		noun 1
		nsTop 73
		nsLeft 144
		nsBottom 123
		nsRight 167
		sightAngle 40
		approachX 131
		approachY 131
	)
	
	(method (doVerb theVerb)
		(frontDoor doVerb: theVerb)
	)
)

(instance column of Feature
	(properties
		y 3
		noun 15
	)
)

(instance leftLion of Feature
	(properties
		y 3
		noun 9
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6 (messager say: 9 6 4))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rightLion of Feature
	(properties
		y 3
		noun 16
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6 (messager say: 16 6 4))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sidewalk of Feature
	(properties
		y 3
		noun 12
	)
)

(instance steps of Feature
	(properties
		y 3
		noun 10
	)
)

(instance street of Feature
	(properties
		y 3
		noun 8
	)
)

(instance policeSign of Feature
	(properties
		y 3
		noun 17
		nsTop 20
		nsLeft 115
		nsBottom 32
		nsRight 186
	)
)

(instance windows of Feature
	(properties
		y 3
		noun 13
	)
)

(instance paperInset of Inset
	(properties
		view 285
		loop 1
		x 7
		y 110
		disposeNotOnMe 1
		noun 11
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: sPutUpInset 0 0)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(self dispose:)
				(curRoom setInset: inCoupon)
			)
			(8
				(self dispose:)
				(curRoom setInset: inCoupon)
			)
			(1
				(self dispose:)
				(curRoom setInset: inCoupon)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance inCoupon of Inset
	(properties
		view 285
		x 7
		y 110
		disposeNotOnMe 1
		noun 3
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: sPutUpInset 0 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(self dispose:)
				(curRoom setScript: sGetNewsPaper)
			)
			(8 (messager say: 3 1))
			(1 (messager say: 3 1))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsLeft 183
		nsBottom 189
		nsRight 320
		cursor 11
		exitDir 3
		noun 7
	)
)

(instance carSound of Sound
	(properties
		flags $0001
		number 40
	)
)

(instance theDrunk of Narrator
	(properties
		back 15
	)
	
	(method (init)
		(= font userFont)
		(= showTitle 1)
		(super init: &rest)
	)
)
