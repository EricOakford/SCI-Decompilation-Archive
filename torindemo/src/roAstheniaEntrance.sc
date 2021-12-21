;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40100)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use ExitFeature)
(use Print)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roAstheniaEntrance 0
)

(instance foExit of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 6))
		(self setRect: 475 76 583 181)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance soExit of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 530 175 self)
			)
			(1
				(theGame handsOff:)
				((ScriptID 64018 0) setScript: LOOKUP_ERROR)
				(ego setMotion: MoveTo 450 160 self)
			)
			(2
				((ScriptID 64018 0) setScript: 0)
				(curRoom newRoom: -25336)
			)
		)
	)
)

(instance soBoogleExit of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 64018 0)
					bSwing: 0
					setMotion: PolyPath 570 175 self
				)
			)
			(1
				((ScriptID 64018 0) setMotion: MoveTo 450 160 self)
			)
			(2 (self dispose:))
		)
	)
)

(instance poTorin of Prop
	(properties)
)

(instance foPhenocryst of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= noun 2)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 161 166 164 69 198 31 265 90 283 169 251 185
					yourself:
				)
		)
		(self setVisibleRange: 1 13)
	)
)

(instance voDirtyTop of View
	(properties
		x 377
		y 230
		priority 320
		fixPriority 1
		view -25435
		loop 7
	)
	
	(method (init)
		(super init: &rest)
		(= signal (| signal $1000))
		(self setVisibleRange: 1 49)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (Prints LOOKUP_ERROR))
			(49
				(ego setScript: LOOKUP_ERROR)
			)
		)
	)
)

(instance voConsoleDoor of View
	(properties
		x 295
		y 307
		priority 320
		fixPriority 1
		view -25435
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 354 235 377 254 377 308 354 285
					yourself:
				)
		)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(if ((ScriptID 64017 0) test: 99)
			(ego setScript: LOOKUP_ERROR)
		else
			(ego setScript: LOOKUP_ERROR)
		)
	)
)

(instance foButton of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setRect: 362 209 385 217)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance soHitButton of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 295 307 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 90 self)
			)
			(2
				(ego hide:)
				(LOOKUP_ERROR
					view: -25435
					loop: 5
					cel: 0
					posn: 295 307
					init:
					setCycle: End self
				)
			)
			(3
				(ego show:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init:)
				((ScriptID 64017 0) set: 101)
				(theGame handsOn:)
			)
		)
	)
)

(instance soCleanConsole of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 295 307 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 90 self)
			)
			(2
				(ego hide:)
				(LOOKUP_ERROR
					view: -25435
					loop: 4
					cel: 0
					posn: 295 307
					init:
					setCycle: End self
				)
			)
			(3
				(ego show:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init:)
				((ScriptID 64017 0) set: 100)
				(messager say: 3 49 2 0 self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soTakeAmmonia of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 295 307 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 90 self)
			)
			(2
				(ego hide:)
				(LOOKUP_ERROR
					view: -25435
					loop: 3
					cel: 0
					posn: 295 307
					init:
					setCycle: End self
				)
			)
			(3
				(ego get: ((ScriptID 64001 0) get: 35))
				((ScriptID 64017 0) set: 98)
				((ScriptID 64017 0) clear: 99)
				(ego show:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR setTotalWidth: 1)
				(messager say: 1 1 0 0 self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soOpenConsoleDoor of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 295 307 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 90 self)
			)
			(2
				(ego hide:)
				(LOOKUP_ERROR hide:)
				(LOOKUP_ERROR
					view: -25435
					loop: 2
					cel: 0
					posn: 295 307
					init:
					setCycle: End self
				)
			)
			(3
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR loop: 1 cel: 0 show:)
				(ego show:)
				((ScriptID 64017 0) set: 99)
				(theGame handsOn:)
			)
		)
	)
)

(instance soEnterRoom of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LOOKUP_ERROR
					view: -25436
					loop: 0
					cel: 0
					posn: 254 269
					init:
					setCycle: End self
				)
			)
			(1
				(LOOKUP_ERROR dispose:)
				(ego
					posn: 254 269
					init:
					oPanner:
					setLoop: 4
					heading: 135
					setScaler: Scaler 100 80 270 158
				)
				((ScriptID 64018 0)
					posn: 274 269
					init:
					oPanner:
					setScaler: Scaler 100 80 270 158
				)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR init:)
				(messager say: 0 0 1 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance roAstheniaEntrance of TPRoom
	(properties
		picture -25436
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: -25436)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						308
						313
						358
						313
						343
						293
						455
						268
						482
						268
						515
						246
						608
						245
						581
						168
						489
						158
						494
						180
						456
						189
						296
						207
						265
						188
						201
						191
						251
						234
						131
						258
						203
						304
					yourself:
				)
		)
		(LOOKUP_ERROR init:)
		(curRoom setScript: LOOKUP_ERROR)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
