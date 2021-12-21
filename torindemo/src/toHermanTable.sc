;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15100)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use CueMe)
(use ExitFeature)
(use Plane)
(use Talker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Motion)
(use Actor)

(public
	roGuardHouseInt 0
	toHermanTable 1
	toHermanDrinking 2
)

(instance foMainExit of ExitFeature
	(properties
		nsLeft 598
		nsTop 211
		nsRight 630
		nsBottom 315
		approachX 627
		approachY 249
		x 650
		y 249
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 3))
	)
	
	(method (doVerb)
		(ego nSaveTime: self self)
	)
	
	(method (cue)
		(curRoom newRoom: 15000)
	)
)

(instance foDoorExit of ExitFeature
	(properties
		nsLeft 284
		nsTop 40
		nsRight 402
		nsBottom 194
		approachX 340
		approachY 194
		x 340
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 1))
	)
	
	(method (doVerb)
		(ego nSaveTime: self self)
	)
	
	(method (cue)
		(if ((ScriptID 64017 0) test: 33)
			(curRoom newRoom: 15200)
		else
			(curRoom newRoom: 15500)
		)
	)
)

(instance soTorinServesBerries of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager sayRange: 1 8 4 1 4 self)
			)
			(1
				(ego setMotion: PolyPath 282 191 self)
				((ScriptID 64018 0)
					bSwing: 0
					setMotion: PolyPath 235 210 LOOKUP_ERROR
				)
			)
			(2
				(ego hide:)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
			)
			(3
				(LOOKUP_ERROR dispose:)
				(theSound lThumbLoop: 15103)
				(ego posn: 284 192 setLoop: 5 scrollTo: show:)
				(messager say: 1 8 4 5 self)
			)
			(4
				((ScriptID 64018 0) setMotion: PolyPath 235 210 self)
			)
			(5
				((ScriptID 64018 0) setHeading: 45 self)
			)
			(6
				(ego hide:)
				((ScriptID 64018 0) hide:)
				(LOOKUP_ERROR
					setCel: 0
					init:
					setPri: 240
					setCycle: End self
				)
				(theSound lThumbLoop: 15199)
			)
			(7
				(LOOKUP_ERROR dispose:)
				(ego
					posn: 419 239
					setLoop: 4
					scrollTo:
					show:
					nSaveTime: LOOKUP_ERROR self
				)
				((ScriptID 64018 0)
					posn: 469 240
					setLoop: 2
					scrollTo:
					show:
					bSwing: 1
				)
			)
			(8
				(messager sayRange: 1 8 4 6 7 self)
			)
			(9
				(curRoom initThumb: LOOKUP_ERROR)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
				(LOOKUP_ERROR setCycle: CT 44 1 self)
			)
			(10)
			(11
				(theSound lThumbLoop: 15104)
				(LOOKUP_ERROR setCycle: End self)
			)
			(12
				(LOOKUP_ERROR dispose:)
				(= global226 4)
				(messager sayRange: 1 8 4 8 17 self)
			)
			(13
				(= global226 3)
				(theGame handsOn:)
				(curRoom arrowDown: LOOKUP_ERROR)
				(self dispose:)
			)
		)
	)
)

(instance coFaceBoogleNE of CueMe
	(properties)
	
	(method (cue)
		((ScriptID 64018 0) setHeading: 45)
	)
)

(instance poTorinJuicesBerries of Prop
	(properties
		x 282
		y 191
		view 15101
		loop 1
	)
)

(instance poTorinDuckwalks of Prop
	(properties
		x 284
		y 192
		view 15101
	)
)

(instance poTorinServesBerries of Prop
	(properties
		x 599
		y 187
		view 15106
	)
)

(instance poGuardDrinks of Prop
	(properties
		x 159
		y 222
		view 15106
		loop 1
		cel 8
	)
)

(instance soTorinServesSlugetti of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager sayRange: 1 20 6 1 5 self)
			)
			(1
				(ego setMotion: PolyPath 310 187 self)
			)
			(2 (ego setHeading: 0 self))
			(3
				(ego
					hide:
					posn: (LOOKUP_ERROR approachX?) (LOOKUP_ERROR approachY?)
					setLoop: 6
				)
				(LOOKUP_ERROR
					setCel: 0
					setScaler: Scaler 110 110 1 0
					init:
					setCycle: End self
				)
			)
			(4 (= seconds 2))
			(5
				(LOOKUP_ERROR dispose:)
				(curRoom initThumb: LOOKUP_ERROR)
				(LOOKUP_ERROR setCel: 0 init:)
				(messager say: 1 20 6 6 self)
			)
			(6
				(LOOKUP_ERROR
					setCel: 0
					init:
					setPri: 255
					setCycle: CT 2 1 self
				)
			)
			(7
				(LOOKUP_ERROR setCycle: End)
				(LOOKUP_ERROR setCycle: CT 34 1 self)
			)
			(8
				(LOOKUP_ERROR setCycle: End self)
				(theSound lThumbLoop: 15105)
			)
			(9
				(curRoom arrowDown: LOOKUP_ERROR)
				(ego show:)
				(messager sayRange: 1 20 6 8 17 self)
			)
			(10
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poTorinCooksSlugs of Prop
	(properties
		x 310
		y 187
		view 15102
	)
)

(instance poTorinServesSlugetti of Prop
	(properties
		x 599
		y 186
		view 15105
	)
)

(instance poBowlSettles of Prop
	(properties
		x 197
		y 250
		view 15105
		loop 1
	)
)

(instance poGuardEats of Prop
	(properties
		x 43
		y 253
		view 15105
		loop 2
	)
)

(instance soGuardLeavesRoom of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager sayRange: 1 9 8 1 7 self)
			)
			(1
				(ego setMotion: PolyPath 249 199 self)
			)
			(2 (ego setHeading: 315 self))
			(3
				(ego hide:)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
			)
			(4
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
			)
			(5
				(LOOKUP_ERROR dispose:)
				(ego posn: 288 186 setLoop: 6 scrollTo: show:)
				(ego setMotion: PolyPath 231 234 self)
			)
			(6 (ego setHeading: 45 self))
			(7 (messager say: 1 9 8 8 self))
			(8
				(theSound lThumbLoop: 15101)
				(messager sayRange: 1 9 8 9 10 self)
			)
			(9
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR
					setCel: 0
					init:
					setPri: 200
					setCycle: End self
				)
			)
			(10 (curRoom newRoom: 15500))
		)
	)
)

(instance poPrepareSquareRoot of Prop
	(properties
		x 249
		y 199
		view 15104
	)
)

(instance poBakeSquareRoot of Prop
	(properties
		x 219
		y 201
		view 15104
		loop 1
	)
)

(instance poGuardLeavesRoom of Prop
	(properties
		x 596
		y 171
		view 15104
		loop 2
	)
)

(instance oTalkInset of Plane
	(properties
		picture 15101
		priority 20
	)
	
	(method (init)
		(super
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
	)
)

(instance soFirstEntrance of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego hide:)
				(LOOKUP_ERROR
					setLoop: 0
					setCel: 0
					init:
					setCycle: End self
				)
			)
			(1
				(ego show:)
				(ego setMotion: MoveTo 455 235 LOOKUP_ERROR)
				(= cycles 6)
			)
			(2
				(LOOKUP_ERROR setLoop: 1 setCel: 0 setCycle: End self)
			)
			(3
				(LOOKUP_ERROR
					setCel: 0
					init:
					setScaler: Scaler 90 90 0 1
					setCycle: CT 45 1 self
				)
			)
			(4
				(LOOKUP_ERROR hide:)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
				(LOOKUP_ERROR setCycle: End self)
			)
			(5)
			(6
				(LOOKUP_ERROR dispose:)
				(ego get: ((ScriptID 64001 1) get: 5))
				((ScriptID 64018 0) init: setScript: LOOKUP_ERROR)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance coFaceEgo of CueMe
	(properties)
	
	(method (cue)
		(ego setHeading: 45)
	)
)

(instance poGuard of Prop
	(properties
		noun 1
		approachX 510
		approachY 229
		x 587
		y 169
		view 15100
	)
	
	(method (init)
		(super init: &rest)
		(if ((ScriptID 64017 0) test: 32)
			(self setVisibleRange: 1 21 20 8 9)
		else
			(self setVisibleRange: 1)
		)
		(self approachVerbs: 1 2)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(cond 
					((not ((ScriptID 64017 0) test: 32))
						((ScriptID 64017 0) set: 32)
						(self setVisibleRange: 21 20 8 9)
						(messager say: noun theVerb 2 0)
					)
					((not ((ScriptID 64017 0) test: 30)) (messager say: noun theVerb 4 0))
					((not ((ScriptID 64017 0) test: 29)) (messager say: noun theVerb 6 0))
					((not ((ScriptID 64017 0) test: 31)) (messager say: noun theVerb 8 0))
					(else (MonoOut LOOKUP_ERROR))
				)
			)
			(20
				(if (not ((ScriptID 64017 0) test: 30))
					(messager say: noun theVerb 4 0)
					(return)
				)
				(if (ego has: ((ScriptID 64001 0) get: 11))
					((ScriptID 64017 0) set: 29)
					(ego put: ((ScriptID 64001 0) get: 10))
					(ego put: ((ScriptID 64001 0) get: 11))
					(ego setScript: LOOKUP_ERROR)
				else
					(messager say: noun theVerb 10 0)
				)
			)
			(21
				(if (not ((ScriptID 64017 0) test: 30))
					(messager say: noun theVerb 4 0)
					(return)
				)
				(if (ego has: ((ScriptID 64001 0) get: 10))
					((ScriptID 64017 0) set: 29)
					(ego put: ((ScriptID 64001 0) get: 10))
					(ego put: ((ScriptID 64001 0) get: 11))
					(ego setScript: LOOKUP_ERROR)
				else
					(messager say: noun theVerb 11 0)
				)
			)
			(8
				((ScriptID 64017 0) set: 30 27)
				(ego put: ((ScriptID 64001 0) get: 4))
				(ego setScript: LOOKUP_ERROR)
			)
			(9
				(if (not ((ScriptID 64017 0) test: 30))
					(messager say: noun theVerb 4 0)
					(return)
				)
				(if (not ((ScriptID 64017 0) test: 29))
					(messager say: noun theVerb 6 0)
					(return)
				)
				((ScriptID 64017 0) set: 31)
				(ego put: ((ScriptID 64001 0) get: 5))
				(ego setScript: LOOKUP_ERROR)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance toHermanTable of Talker
	(properties
		x 587
		y 169
		view 15100
		loop 2
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

(instance toHermanDrinking of Talker
	(properties)
	
	(method (init)
		(= frame LOOKUP_ERROR)
		(= mouth LOOKUP_ERROR)
		(LOOKUP_ERROR hide:)
		(super init: &rest)
	)
	
	(method (dispose)
		(LOOKUP_ERROR show:)
		(super dispose: &rest)
	)
)

(instance poHermanDrinkingMouth of Prop
	(properties
		x 159
		y 222
		view 15106
		loop 3
	)
	
	(method (init)
		(self setPri: 250)
		(super init: &rest)
	)
)

(instance voHermanDrinkingBody of View
	(properties
		x 159
		y 222
		view 15106
		loop 2
	)
)

(instance poBoogleSniffing of Prop
	(properties
		x 393
		y 375
		view 15103
	)
)

(instance poGuardReact of Prop
	(properties
		x 587
		y 169
		view 15103
		loop 1
	)
)

(instance soBoogleEntrance of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 3)
				((ScriptID 64018 0) hide:)
			)
			(1
				((ScriptID 64018 0)
					show:
					oPanner:
					setScaler: Scaler 100 92 239 192
					bSwing: 0
					posn: 629 291
					setMotion: MoveTo 540 293 self
				)
			)
			(2
				((ScriptID 64018 0) bSwing: 1)
				(self dispose:)
			)
		)
	)
)

(instance roGuardHouseInt of TPRoom
	(properties
		picture 15100
		south 15000
	)
	
	(method (init)
		(super init: &rest)
		(ego init: oPanner: setScaler: Scaler 100 92 239 192)
		(= global226 3)
		(theGame handsOn:)
		(switch prevRoomNum
			(15000
				(theGame handsOff:)
				(ego posn: 635 262 loop: 3)
				(if ((ScriptID 64017 0) test: 28)
					(ego setMotion: MoveTo 455 235 (ScriptID 64020 0))
					((ScriptID 64018 0) init: setScript: LOOKUP_ERROR)
				else
					(ego setScript: LOOKUP_ERROR)
				)
			)
			(15200
				(ego posn: 340 194 loop: 2)
				((ScriptID 64018 0)
					init:
					oPanner:
					setScaler: Scaler 100 92 239 192
					posn: 227 206
				)
			)
			(else 
				(ego posn: 282 292 loop: 3)
			)
		)
		(LOOKUP_ERROR init:)
		(cond 
			(((ScriptID 64017 0) test: 31) (LOOKUP_ERROR init:))
			(((ScriptID 64017 0) test: 28)
				(LOOKUP_ERROR
					setLoop: 1
					setCel: (LOOKUP_ERROR lastCel:)
					init:
				)
			)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						640
						212
						461
						227
						374
						193
						390
						176
						283
						174
						203
						200
						176
						197
						52
						227
						292
						287
						292
						329
						632
						320
					yourself:
				)
		)
		(theMusic pageSize: 15100)
		(if (not ((ScriptID 64017 0) test: 28))
			(Load rsVIEW 15100 15103)
		)
		(if (not ((ScriptID 64017 0) test: 30))
			(Load rsVIEW 15101)
		)
		(if (not ((ScriptID 64017 0) test: 29))
			(Load rsVIEW 15102 15105)
		)
		(if (not ((ScriptID 64017 0) test: 31))
			(Load rsVIEW 15104)
		)
		(Load 141 15103 15199 15104 15105)
		((ScriptID 64017 0) set: 28)
	)
	
	(method (dispose)
		(= global226 0)
		(super dispose: &rest)
	)
	
	(method (intoPouch)
		(ego
			get:
				((ScriptID 64001 0) get: 4)
				((ScriptID 64001 0) get: 5)
				((ScriptID 64001 0) get: 11)
				((ScriptID 64001 0) get: 10)
		)
	)
)
