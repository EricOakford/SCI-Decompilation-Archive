;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50100)
(include sci.sh)
(use Main)
(use FRButton)
(use TPRoom)
(use TPScript)
(use Plane)
(use Print)
(use Scaler)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roStoreroom 0
)

(local
	planeSel_141
	local1
)
(instance foDuctExit of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self
			onMeCheck: ((Polygon new:)
				type: 0
				init: 1 43 39 46 38 5 2 2
				yourself:
			)
		)
		(self setVisibleRange: 1 51)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(curRoom setScript: soTryVent)
			)
			(51
				(curRoom setScript: soLeaveRoom)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance soClimbBoxes of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 262 269 self)
			)
			(1 (ego setHeading: 315 self))
			(2
				(theGame handsOff:)
				(ego hide:)
				(poTorinClimbs
					view: -15433
					loop: 8
					cel: 0
					posn: 260 268
					init:
					setCycle: End self
				)
			)
			(3
				(= local1 1)
				(foFloor init:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soComeDown of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(poTorinClimbs
					view: -15433
					loop: 9
					cel: 0
					posn: 97 278
					init:
					setCycle: End self
				)
			)
			(1
				(= local1 0)
				(foFloor dispose:)
				(poTorinClimbs dispose:)
				(ego posn: 97 269 setLoop: 2 show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soLeaveRoom of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(((ScriptID 64001 0) get: 34) moveTo: -3)
				(if (not local1)
					(self setScript: soClimbBoxes self)
				else
					(self cue:)
				)
			)
			(1
				(theGame handsOff:)
				(sound1 lThumbLoop: -15431)
				(poTorinClimbs
					view: -15432
					loop: 2
					cel: 0
					posn: 91 55
					setCycle: End self
				)
			)
			(2
				(curRoom newRoom: -15336)
				(self dispose:)
			)
		)
	)
)

(instance soTryVent of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not local1)
					(self setScript: soClimbBoxes self)
				else
					(self cue:)
				)
			)
			(1
				(poTorinClimbs
					view: -15432
					loop: 1
					cel: 0
					posn: 91 55
					setCycle: End self
				)
			)
			(2
				(poTorinClimbs view: -15432 loop: 0 cel: 0 posn: 91 55)
			)
		)
	)
)

(instance poTorinClimbs of Prop
	(properties)
)

(instance foBoxes of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init:
						2
						260
						30
						269
						63
						260
						65
						249
						89
						248
						90
						255
						130
						261
						187
						255
						215
						257
						279
						257
						279
						203
						224
						204
						220
						139
						168
						138
						166
						100
						154
						97
						153
						58
						3
						60
					yourself:
				)
		)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if local1
					(curRoom setScript: soComeDown)
				else
					(curRoom setScript: soClimbBoxes)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance foFloor of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self
			onMeCheck:
				((Polygon new:)
					type: 3
					init:
						187
						261
						126
						270
						71
						262
						80
						313
						207
						299
						271
						311
						530
						316
						506
						263
						454
						273
						347
						268
						289
						261
						215
						265
					yourself:
				)
		)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(curRoom setScript: soComeDown)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance foPhenocryst of Feature
	(properties
		noun 2
	)
	
	(method (init)
		(super init: &rest)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init: 628 51 592 51 560 89 559 143 580 185 614 217 622 177 630 173
					yourself:
				)
		)
		(self setVisibleRange: 1 13)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 2 1 0 0))
			(13 (messager say: 2 1 0 0))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poTorinEnters of Prop
	(properties
		view -15435
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 71 49)
		(= signal (| signal $1000))
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(Prints {something should happen here})
			)
			(71
				(curRoom setScript: soReviveTorin)
			)
			(49
				(curRoom setScript: soReviveTorin)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poBoogle of Prop
	(properties
		view -15435
	)
)

(instance soReviveTorin of TPScript
	(properties
		oMessageList 1
		nTextWidth 1
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(((ScriptID 64001 0) get: 35) moveTo: -3)
				(((ScriptID 64001 1) get: 6) moveTo: -3)
				(poBoogle
					view: -15433
					loop: 0
					cel: 0
					posn: 436 312
					setCycle: End self
				)
			)
			(1
				(poTorinEnters dispose:)
				(poBoogle loop: 1 cel: 0 posn: 374 302 setCycle: End self)
				(sound1 lThumbLoop: -15433)
			)
			(2
				(poBoogle loop: 2 cel: 0 posn: 370 303 setCycle: End self)
			)
			(3
				(poBoogle loop: 3 cel: 0 posn: 368 302 setCycle: End self)
			)
			(4
				((= planeSel_141 (Plane new:))
					picture: -2
					init: 4 4 640 480
					setPri: 700
				)
				(poTorinEnters init:)
				(poBoogle dispose:)
				(poBoogle
					loop: 4
					cel: 0
					init: planeSel_141
					posn: 211 296
					setCycle: End self
				)
			)
			(5
				(proc64002_7)
				(planeSel_141 setPri: 10)
				(planeSel_141 setPri: 700)
				(poBoogle loop: 5 cel: 0 setCycle: End self)
			)
			(6
				(poBoogle dispose:)
				(planeSel_141 dispose:)
				(= planeSel_141 0)
				(((ScriptID 64001 0) get: 35) moveTo: -1)
				((ScriptID 64018 0)
					posn: 384 313
					setLoop: 7
					show:
					bSwing: 1
				)
				(poTorinEnters
					view: -15433
					loop: 0
					cel: 0
					setCycle: End self
				)
			)
			(7
				(poTorinEnters dispose:)
				(foBoxes init:)
				(foPhenocryst init:)
				(foDuctExit init:)
				(ego
					posn: 425 306
					setLoop: 4
					setScaler: Scaler 100 84 313 262
					show:
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(if planeSel_141
			(planeSel_141 dispose:)
			(= planeSel_141 0)
		)
		(((ScriptID 64001 0) get: 35) moveTo: -1)
		(poBoogle dispose:)
		(poTorinEnters dispose:)
		(foBoxes init:)
		(foPhenocryst init:)
		(foDuctExit init:)
		(ego
			posn: 425 306
			setLoop: 4
			setScaler: Scaler 100 84 313 262
			show:
		)
		((ScriptID 64018 0)
			posn: 384 313
			setLoop: 7
			show:
			bSwing: 1
		)
		(theGame handsOn:)
	)
	
	(method (sayMessage)
		(ego get: ((ScriptID 64001 0) get: 35))
		(if planeSel_141
			(planeSel_141 dispose:)
			(= planeSel_141 0)
		)
		(ego hide:)
		((ScriptID 64018 0) hide:)
		(curRoom setScript: self)
	)
)

(instance soEnterRoom of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sound1 lThumbLoop: -15430)
				(poTorinEnters
					view: -15435
					loop: 0
					cel: 0
					init:
					posn: 371 309
					setCycle: End self
				)
				(poBoogle
					view: -15435
					loop: 1
					cel: 0
					init:
					posn: 440 310
					setCycle: End
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance roStoreroom of TPRoom
	(properties
		picture -15436
	)
	
	(method (init)
		(super init: &rest)
		(= global202 5)
		(music1 pageSize: -15436)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						187
						261
						126
						270
						71
						262
						80
						313
						207
						299
						271
						311
						530
						316
						506
						263
						454
						273
						347
						268
						289
						261
						215
						265
					yourself:
				)
		)
		(ego init: oPanner: hide:)
		(theGame handsOn:)
		(curRoom setScript: soEnterRoom)
		(switch prevRoomNum
			(else  (ego posn: 600 300))
		)
		((ScriptID 64018 0)
			posn: 600 300
			init:
			oPanner:
			bSwing: 0
			hide:
		)
	)
	
	(method (setWander)
		(return foDuctExit)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 1) get: 6))
		(ego get: ((ScriptID 64001 0) get: 34))
		(ego get: ((ScriptID 64001 0) get: 35))
	)
)
