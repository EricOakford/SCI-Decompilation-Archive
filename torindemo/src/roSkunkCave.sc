;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20500)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use Script)
(use ExitFeature)
(use Talker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roSkunkCave 0
)

(local
	local0
	local1
)
(instance foToCliffExit of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 4))
		(= nsTop 0)
		(= nsBottom 314)
		(= nsLeft 0)
		(= nsRight 30)
	)
	
	(method (doVerb)
		(switch local1
			(0
				(ego setScript: LOOKUP_ERROR)
			)
			(2
				(ego setScript: LOOKUP_ERROR)
			)
			(3
				(ego setScript: LOOKUP_ERROR)
			)
			(else  (MonoOut LOOKUP_ERROR))
		)
	)
	
	(method (cue)
		(curRoom newRoom: 20100)
	)
)

(instance soCrawlBackOut of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LOOKUP_ERROR view: 20500 loop: 0 posn: 110 212)
				(LOOKUP_ERROR
					cel: (LOOKUP_ERROR lastCel:)
					setCycle: Beg self
				)
			)
			(1
				((ScriptID 64017 0) clear: 84)
				(curRoom newRoom: 20100)
				(self dispose:)
			)
		)
	)
)

(instance soClothespinOffAndOut of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LOOKUP_ERROR
					view: 20500
					loop: 2
					posn: 110 212
					cel: 0
					setCycle: End self
				)
			)
			(1
				((ScriptID 64017 0) clear: 84)
				(curRoom newRoom: 20100)
				(self dispose:)
			)
		)
	)
)

(instance soWalkOutFrontDoor of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 317 262 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 225 self)
			)
			(2
				(ego hide:)
				(LOOKUP_ERROR
					view: 20503
					loop: 4
					cel: 0
					posn: 317 262
					init:
					setCycle: End self
				)
			)
			(3
				((ScriptID 64017 0) clear: 84)
				(curRoom newRoom: 20100)
				(self dispose:)
			)
		)
	)
)

(instance foToGuruExit of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 1))
		(= onMeCheck
			((Polygon new:)
				type: 0
				init: 514 146 556 189 540 199 506 194 500 170
				yourself:
			)
		)
	)
	
	(method (doVerb)
		(cond 
			(((ScriptID 64017 0) test: 83) (ego setScript: LOOKUP_ERROR))
			((== local1 2) (ego setScript: LOOKUP_ERROR))
			(else (messager say: 0 0 7 0))
		)
	)
)

(instance soCrawlIn of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR
					view: 20500
					loop: 0
					cel: 0
					posn: 110 212
					init:
					setCycle: End self
				)
				(if (not ((ScriptID 64017 0) test: 87))
					(messager say: 0 0 6 0 self)
					((ScriptID 64017 0) set: 87)
				else
					(self cue:)
				)
			)
			(1)
			(2
				(if (not ((ScriptID 64017 0) test: 83))
					(LOOKUP_ERROR setVisibleRange: 33)
					(self setScript: LOOKUP_ERROR self)
				else
					(self cue:)
				)
			)
			(3
				(theGame handsOn:)
				(= local1 0)
				(LOOKUP_ERROR init:)
				(self dispose:)
			)
		)
	)
)

(instance soSkunksEnter of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LOOKUP_ERROR
					view: 20501
					loop: 0
					cel: 0
					posn: 587 278
					init:
					setCycle: End self
				)
			)
			(1
				(LOOKUP_ERROR loop: 2)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR init:)
				(= gToSam LOOKUP_ERROR)
				(= gToMax LOOKUP_ERROR)
				(self dispose:)
			)
		)
	)
)

(instance soClimbOutOfTunnel of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR loop: 4 cel: 0 setCycle: CT 19 1 self)
			)
			(1
				(LOOKUP_ERROR dispose:)
				(ego posn: 360 264 oPanner: 1 -5436 6 show:)
				(LOOKUP_ERROR dispose:)
				(= local1 3)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soLeaveBackDoor of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== local1 0)
					(self setScript: LOOKUP_ERROR self)
				else
					(self cue:)
				)
			)
			(1
				(ego setMotion: PolyPath 540 199 self)
			)
			(2
				((ScriptID 64017 0) set: 84)
				(curRoom newRoom: 20100)
				(self dispose:)
			)
		)
	)
)

(instance poTorin of Prop
	(properties
		view 20500
	)
	
	(method (doVerb)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance soClothespinSelf of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not ((ScriptID 64017 0) test: 88))
					((ScriptID 64017 0) set: 88)
					(messager say: 4 33 2 0 self)
				else
					(self cue:)
				)
			)
			(1
				(LOOKUP_ERROR loop: 1 cel: 0 setCycle: CT 15 1 self)
			)
			(2
				(LOOKUP_ERROR setCycle: End self)
				(LOOKUP_ERROR hide:)
				(LOOKUP_ERROR hide:)
				(LOOKUP_ERROR loop: 1 cel: 0 setCycle: End self)
			)
			(3)
			(4
				(LOOKUP_ERROR loop: 5)
				(LOOKUP_ERROR loop: 6 show:)
				(LOOKUP_ERROR loop: 7 show:)
				(LOOKUP_ERROR loop: 6)
				(LOOKUP_ERROR loop: 7)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR init:)
				(theGame handsOn:)
				(LOOKUP_ERROR setTotalWidth: 33)
				(LOOKUP_ERROR dispose:)
				((ScriptID 64017 0) set: 85)
				(LOOKUP_ERROR setTotalWidth: 40)
				(= local1 2)
			)
		)
	)
)

(instance foFloor of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self
			setPolygon:
				((Polygon new:)
					type: 3
					init:
						295
						250
						299
						267
						366
						280
						430
						264
						555
						216
						525
						211
						532
						198
						502
						198
						426
						201
						346
						225
					yourself:
				)
		)
	)
	
	(method (doVerb)
		(if ((ScriptID 64017 0) test: 83)
			(curRoom setScript: LOOKUP_ERROR)
		else
			(messager say: 0 0 7 0)
		)
	)
)

(instance voSamNormalMouth of View
	(properties
		x 587
		y 278
		priority 300
		fixPriority 1
		view 20501
		loop 3
	)
	
	(method (doVerb)
		(LOOKUP_ERROR doVerb: &rest)
	)
	
	(method (setSpeedDirect)
		(LOOKUP_ERROR setSpeedDirect: &rest)
	)
)

(instance toSam of Talker
	(properties
		x 587
		y 278
		view 20501
		loop 3
		priority 300
	)
	
	(method (init)
		(LOOKUP_ERROR hide:)
		(super init: &rest)
	)
	
	(method (dispose)
		(LOOKUP_ERROR show:)
		(super dispose: &rest)
	)
)

(instance voMaxNormalMouth of View
	(properties
		x 587
		y 278
		priority 300
		fixPriority 1
		view 20501
		loop 4
		cel 2
	)
	
	(method (doVerb)
		(LOOKUP_ERROR doVerb: &rest)
	)
	
	(method (setSpeedDirect)
		(LOOKUP_ERROR setSpeedDirect: &rest)
	)
)

(instance toMax of Talker
	(properties
		x 587
		y 278
		view 20501
		loop 4
		cel 2
		priority 300
	)
	
	(method (init)
		(LOOKUP_ERROR hide:)
		(super init: &rest)
	)
	
	(method (dispose)
		(LOOKUP_ERROR show:)
		(super dispose: &rest)
	)
)

(instance poSamTail of Prop
	(properties
		x 587
		y 278
		priority 200
		fixPriority 1
		view 20501
		loop 8
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: (LOOKUP_ERROR new:))
	)
	
	(method (doVerb)
		(LOOKUP_ERROR doVerb: &rest)
	)
	
	(method (setSpeedDirect)
		(LOOKUP_ERROR setSpeedDirect: &rest)
	)
)

(instance poMaxTail of Prop
	(properties
		x 587
		y 278
		priority 190
		fixPriority 1
		view 20501
		loop 9
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: (LOOKUP_ERROR new:))
	)
	
	(method (doVerb)
		(LOOKUP_ERROR doVerb: &rest)
	)
	
	(method (setSpeedDirect)
		(LOOKUP_ERROR setSpeedDirect: &rest)
	)
)

(instance soTailFlick of Script
	(properties)
	
	(method (dispose)
		(client setCycle: 0)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks (Random 200 300)))
			(1 (client setCycle: End self))
			(2
				(client cel: 0)
				(self changeState: 0)
			)
		)
	)
)

(instance poSkunks of Prop
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setPri: 250)
		(self setVisibleRange: 1 38 40 37)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(switch global264
					(0
						(messager say: 2 1 8 0)
						(++ global264)
					)
					(1
						(messager say: 2 1 9 0)
						(++ global264)
					)
					(2 (messager say: 2 1 10 0))
				)
			)
			(38
				(if ((ScriptID 64017 0) test: 85)
					(ego setScript: LOOKUP_ERROR)
				else
					(messager say: 2 47 2 0)
				)
			)
			(40
				(ego setScript: LOOKUP_ERROR)
			)
			(37
				(if ((ScriptID 64017 0) test: 85)
					(messager say: 2 47 1 0)
				else
					(messager say: 2 47 2 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance soSneakPastSkunks of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR hide:)
				(LOOKUP_ERROR hide:)
				(LOOKUP_ERROR hide:)
				(LOOKUP_ERROR hide:)
				(LOOKUP_ERROR hide:)
				(LOOKUP_ERROR
					view: 20502
					posn: 356 267
					loop: 0
					cel: 0
					setCycle: End self
				)
			)
			(1
				(LOOKUP_ERROR view: 20500 loop: 1 posn: 110 212)
				(LOOKUP_ERROR cel: (LOOKUP_ERROR lastCel:))
				(LOOKUP_ERROR
					view: 20502
					loop: 1
					cel: 0
					posn: 371 254
					show:
					setCycle: End self
				)
			)
			(2
				(LOOKUP_ERROR view: 20501 loop: 5 cel: 0 posn: 587 278)
				(LOOKUP_ERROR show:)
				(LOOKUP_ERROR show:)
				(LOOKUP_ERROR show:)
				(LOOKUP_ERROR show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soFanSkunks of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= local1 0) (MonoOut LOOKUP_ERROR))
				(theGame handsOff:)
				(messager say: 2 40 2 1 self)
			)
			(1
				(LOOKUP_ERROR
					view: 20500
					loop: 3
					cel: 0
					setCycle: End self
				)
			)
			(2
				(LOOKUP_ERROR loop: 0)
				(LOOKUP_ERROR cel: (LOOKUP_ERROR lastCel:))
				(messager sayRange: 2 40 2 2 3 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soCarpetSkunks of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 2 38 1 1 self)
			)
			(1
				(LOOKUP_ERROR
					view: 20503
					loop: 0
					cel: 0
					posn: 336 277
					setCycle: End self
				)
			)
			(2
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR view: 20502 loop: 1)
				(LOOKUP_ERROR
					cel: (LOOKUP_ERROR lastCel:)
					posn: 371 254
					setCycle: Beg self
				)
			)
			(3
				(LOOKUP_ERROR
					view: 20503
					loop: 2
					cel: 0
					posn: 505 260
					setCycle: CT 27 1 self
				)
			)
			(4
				(LOOKUP_ERROR
					view: 20503
					loop: 1
					cel: 0
					setCycle: End self
				)
				(LOOKUP_ERROR setCycle: End self)
			)
			(5)
			(6
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR loop: 3 cel: 0 setCycle: End self)
			)
			(7
				(LOOKUP_ERROR dispose:)
				(ego posn: 367 272 oPanner: 1 -5436 6 show:)
				(= local1 3)
				((ScriptID 64017 0) clear: 85)
				(ego put: ((ScriptID 64001 0) get: 28))
				(ego get: ((ScriptID 64001 0) get: 29))
				((ScriptID 64017 0) set: 57)
				((ScriptID 64017 0) set: 83)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance roSkunkCave of TPRoom
	(properties
		picture 20500
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: 20500)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						295
						250
						299
						267
						366
						280
						430
						264
						555
						216
						525
						211
						532
						198
						502
						198
						426
						201
						346
						225
					yourself:
				)
		)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(ego
			init:
			oPanner:
			setScaler: Scaler 100 43 262 200
			hide:
		)
		(theGame handsOn:)
		(if ((ScriptID 64017 0) test: 84)
			(ego posn: 540 199)
			(ego setMotion: MoveTo 540 199)
			(ego show:)
			(= local1 3)
		else
			(= local1 0)
			(ego posn: 356 267)
			(curRoom setScript: LOOKUP_ERROR)
		)
	)
	
	(method (dispose)
		(= gToMax 0)
		(= gToSam 0)
		(super dispose: &rest)
	)
	
	(method (setWander param1)
		(return
			(if (== param1 20900)
				(return LOOKUP_ERROR)
			else
				(return LOOKUP_ERROR)
			)
		)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 0) get: 28))
		(ego get: ((ScriptID 64001 0) get: 30))
		(ego get: ((ScriptID 64001 0) get: 27))
		(ego get: ((ScriptID 64001 0) get: 23))
	)
)
