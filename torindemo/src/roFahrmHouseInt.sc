;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11100)
(include sci.sh)
(use Main)
(use soBooglePouch)
(use TPRoom)
(use Script)
(use CueMe)
(use ExitFeature)
(use Scaler)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roFahrmHouseInt 0
)

(instance foMomChair of Feature
	(properties
		noun 4
		x 80
		y 202
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 35 275 17 130 80 132 90 209 139 215 143 270
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
)

(instance foDadChair of Feature
	(properties
		noun 5
		x 144
		y 181
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						106
						209
						88
						130
						120
						112
						158
						120
						163
						180
						197
						188
						200
						230
						162
						250
						145
						247
						142
						213
						107
						208
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
)

(instance foAshtray of Feature
	(properties
		noun 2
		case 1
		approachX 253
		approachY 225
		x 200
		y 225
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 169 179 189 169 217 172 204 185
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(if ((ScriptID 64017 0) test: 2)
				(theGame handsOff:)
				(messager say: 2 1 3 1 (ScriptID 64020 0))
			else
				(ego setScript: LOOKUP_ERROR)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance soLookAtAshtray of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego nSaveTime: LOOKUP_ERROR self)
			)
			(1 (= ticks 2) (ego scrollTo:))
			(2 (curRoom newRoom: 11300))
		)
	)
)

(instance foSewingBasket of Feature
	(properties
		noun 1
		case 1
		approachX 159
		approachY 306
		x 158
		y 307
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 20 314 55 253 126 255 163 314
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(if ((ScriptID 64017 0) test: 3)
				(theGame handsOff:)
				(messager say: 1 1 2 1 (ScriptID 64020 0))
			else
				(ego setScript: LOOKUP_ERROR)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance soLookInsideBasket of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego nSaveTime: LOOKUP_ERROR self)
			)
			(1 (= ticks 2) (ego scrollTo:))
			(2 (curRoom newRoom: 11200))
		)
	)
)

(instance foStove of Feature
	(properties
		approachX 482
		approachY 218
		x 484
		y 219
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 587 192 502 190 467 220 477 287 581 293 630 290 630 221
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance poHot of Prop
	(properties
		view -5434
	)
)

(instance soHot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego oPlane: LOOKUP_ERROR self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 135 self)
			)
			(2
				(LOOKUP_ERROR
					loop: 4
					cel: 0
					posn: (ego x?) (ego y?)
					init:
					setScaler: ego
					setCycle: CT 4 1 self
				)
				(ego hide:)
			)
			(3
				(LOOKUP_ERROR cel: 7)
				(= ticks 1)
			)
			(4
				(LOOKUP_ERROR dispose:)
				(ego show:)
				(messager say: 3 1 0 0 self)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poFire of Prop
	(properties
		x 476
		y 218
		view 11101
		cycleSpeed 15
	)
)

(instance poBoogleToBox of Prop
	(properties
		view 11100
		loop 2
	)
)

(instance soBoogleLearnBox of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom newTarget: LOOKUP_ERROR)
				(ego get: ((ScriptID 64001 1) get: 1))
				((ScriptID 64018 0) hide:)
				(LOOKUP_ERROR
					cel: 0
					posn: ((ScriptID 64018 0) x?) ((ScriptID 64018 0) y?)
					init:
					setCycle: End self
				)
			)
			(1
				(LOOKUP_ERROR dispose:)
				((ScriptID 64018 0) loop: 6 show:)
				(self dispose:)
			)
		)
	)
)

(instance foBox of BoogleLearnMeClass
	(properties
		approachX 448
		approachY 187
		x 448
		y 187
	)
)

(instance soDoorScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego nSaveTime: LOOKUP_ERROR self)
			)
			(1 (curRoom newRoom: 11000))
		)
	)
)

(instance foDoor of ExitFeature
	(properties
		approachX 389
		approachY 167
		x 389
		y 167
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 366 74 385 61 410 66 421 93 426 166 351 163 355 114
					yourself:
				)
		)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 1))
	)
	
	(method (doVerb)
		(ego setScript: 'LOOKUP_ERROR')
	)
)

(instance coBoogleOutOfBag of CueMe
	(properties)
	
	(method (cue)
		(theGame handsOn:)
		((ScriptID 64018 0) show: bSwing: 1)
	)
)

(instance coBoogleJumpOutOfBag of CueMe
	(properties)
	
	(method (cue)
		(curRoom setScript: (ScriptID 64018 2) LOOKUP_ERROR)
	)
)

(instance roFahrmHouseInt of TPRoom
	(properties
		picture 11100
		style $0000
		exitStyle 0
		south 11000
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: 11100)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						174
						267
						153
						286
						162
						316
						320
						338
						635
						336
						626
						289
						459
						295
						442
						246
						462
						216
						599
						220
						546
						185
						440
						183
						420
						167
						362
						168
						348
						176
						234
						172
						213
						183
						232
						206
						226
						240
					yourself:
				)
		)
		(if (not (ego has: ((ScriptID 64001 1) get: 1)))
			(LOOKUP_ERROR init: LOOKUP_ERROR)
			(self setDefault: LOOKUP_ERROR)
		)
		(LOOKUP_ERROR init: setCycle: Fwd)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(theGame handsOn:)
		(switch prevRoomNum
			(11200
				((ScriptID 64018 0)
					init:
					oPanner:
					setScaler: Scaler 125 72 300 180
				)
				(ego
					oldScaleX: 128
					scaleX: 128
					scaleY: 128
					setScale: 0
					setScaler: Scaler 125 72 300 180
					init:
				)
			)
			(11300
				((ScriptID 64018 0)
					init:
					oPanner:
					setScaler: Scaler 125 72 300 180
				)
				(ego
					oldScaleX: 128
					scaleX: 128
					scaleY: 128
					setScale: 0
					setScaler: Scaler 125 72 300 180
					init:
				)
			)
			(else 
				(theGame handsOff:)
				((ScriptID 64018 0) oPanner:)
				(ego
					init:
					oPanner:
					setScaler: Scaler 125 72 300 180
					posn: (LOOKUP_ERROR approachX?) (LOOKUP_ERROR approachY?)
					loop: 2
				)
				(if ((ScriptID 64017 0) test: 144)
					(ego setMotion: MoveTo 339 207 LOOKUP_ERROR)
				else
					(ego setMotion: MoveTo 339 207 (ScriptID 64020 0))
					((ScriptID 64018 0)
						init:
						posn: 419 206
						setScaler: Scaler 125 72 300 180
					)
				)
			)
		)
	)
)
