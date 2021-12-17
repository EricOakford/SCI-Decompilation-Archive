;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15000)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use CueMe)
(use ExitFeature)
(use Talker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roGuardHouseExt 0
	toGuard1 1
	toGuard2 2
)

(instance foMainExit of ExitFeature
	(properties
		nsLeft 152
		nsTop 297
		nsRight 633
		nsBottom 319
		approachX 306
		approachY 310
		x 306
		y 313
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 2))
	)
	
	(method (doVerb)
		(ego nSaveTime: self self)
	)
	
	(method (cue)
		(curRoom newRoom: 15800)
	)
)

(instance foDoor of Feature
	(properties
		noun 1
		nsLeft 272
		nsTop 132
		nsRight 348
		nsBottom 232
		approachX 307
		approachY 241
		x 307
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self approachVerbs: 2)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(cond 
					(((ScriptID 64017 0) test: 28) (ego setScript: soSecondEntrance))
					(((ScriptID 64017 0) test: 8) (ego setScript: soFirstEntrance))
					(else (ego setScript: soEgoTriesDoor))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance voDoor of View
	(properties
		x 331
		y 222
		view 15002
		loop 3
	)
)

(instance soEgoTriesDoor of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego nSaveTime: foDoor self))
			(1
				(theGame handsOff:)
				(ego hide:)
				(poEgoTriesDoor setCel: 0 init: setCycle: CT 3 1 self)
			)
			(2 (= cycles 5))
			(3
				(sound1 lThumbLoop: 15005)
				(poEgoTriesDoor setCycle: End self)
			)
			(4
				(poEgoTriesDoor dispose:)
				(ego posn: 266 241 setLoop: 6 scrollTo: show:)
				(messager say: 1 1 5 0 self)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poEgoTriesDoor of Prop
	(properties
		x 306
		y 244
		view 15001
		loop 1
	)
)

(instance soSecondEntrance of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 310 228 self)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theGame handsOff:)
				(ego setMotion: MoveTo 365 213 self)
			)
			(3 (curRoom newRoom: 15100))
		)
	)
)

(instance soFirstEntrance of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 310 238 self)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theGame handsOff:)
				(ego hide:)
				(sound1 lThumbLoop: 15001)
				(poEgoKnocks setCel: 0 init: setCycle: End self)
			)
			(3
				(ego show:)
				(poEgoKnocks dispose:)
				(ego setLoop: 6 1)
				(ego setMotion: MoveTo 286 249 self)
			)
			(4
				(messager sayRange: 1 1 2 2 3 self)
			)
			(5
				(voDoor dispose:)
				(sound1 lThumbLoop: 15002)
				(music1 pageSize: 15099)
				(poGuardOpensDoor1 setCel: 0 init: setCycle: End self)
			)
			(6
				(= global226 1)
				(messager sayRange: 1 1 2 4 6 self)
			)
			(7
				(poGuardOpensDoor1 dispose:)
				(poGuardOpensDoor2 setCel: 0 init: setCycle: End self)
			)
			(8
				(= global226 2)
				(messager sayRange: 1 1 2 7 8 self)
			)
			(9
				(= global226 0)
				(messager say: 1 1 2 9 self)
				(poGuardOpensDoor2
					setLoop: 9
					setCel: 0
					setPri: 100
					setScript: soGuardWalksIn
				)
			)
			(10 (curRoom newRoom: 15100))
		)
	)
)

(instance soGuardWalksIn of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poGuardOpensDoor2 setCycle: End self)
			)
			(1
				(poGuardOpensDoor2 dispose:)
				(voOpenDoor init:)
				(ego setMotion: MoveTo 365 213 self)
			)
			(2 (self dispose:))
		)
	)
)

(instance poEgoKnocks of Prop
	(properties
		x 310
		y 238
		view 15001
		loop 2
	)
)

(instance foSign of Feature
	(properties
		noun 3
		nsLeft 356
		nsTop 181
		nsRight 384
		nsBottom 195
		approachX 370
		approachY 246
		x 370
		y 200
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self approachVerbs: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				((ScriptID 64017 0) set: 26)
				(messager say: noun theVerb 4)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poGuardOpensDoor1 of Prop
	(properties
		x 331
		y 222
		view 15002
		loop 3
	)
)

(instance voGuard1 of View
	(properties
		x 331
		y 222
		view 15002
		loop 4
	)
)

(instance toGuard1 of Talker
	(properties
		x 331
		y 222
		view 15002
		loop 5
		priority 222
	)
	
	(method (init)
		(poGuardOpensDoor1 hide:)
		(= frame voGuard1)
		(super init: &rest)
	)
	
	(method (dispose)
		(poGuardOpensDoor1 show:)
		(super dispose: &rest)
	)
)

(instance poGuardOpensDoor2 of Prop
	(properties
		x 331
		y 222
		view 15002
		loop 6
	)
)

(instance voGuard2 of View
	(properties
		x 331
		y 222
		view 15002
		loop 7
	)
)

(instance toGuard2 of Talker
	(properties
		x 331
		y 222
		view 15002
		loop 8
		priority 222
	)
	
	(method (init)
		(poGuardOpensDoor2 hide:)
		(= frame voGuard2)
		(super init: &rest)
	)
	
	(method (dispose)
		(poGuardOpensDoor2 show:)
		(super dispose: &rest)
	)
)

(instance foCrystal of Feature
	(properties
		nsLeft 133
		nsTop 184
		nsRight 230
		nsBottom 291
		approachX 258
		approachY 290
		y 290
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(if (not (ego has: ((ScriptID 64001 0) get: 8)))
			(self setVisibleRange: 2 14)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(ego setScript: soDoOnCrystal)
			)
			(14
				(ego setScript: soBoogleAxOnCrystal)
			)
			(2
				(ego setScript: soAxOnCrystal)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance soDoOnCrystal of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego nSaveTime: foCrystal self)
			)
			(1
				(theGame handsOff:)
				(if ((ScriptID 64017 0) test: 8)
					(self cue:)
				else
					(ego hide:)
					(poDoOnCrystal
						setCel: 0
						init:
						setPri: 292
						setCycle: End self
					)
				)
			)
			(2
				(if ((ScriptID 64017 0) test: 8)
					(messager say: 2 1 2 0 self)
				else
					(poDoOnCrystal dispose:)
					(ego posn: 318 290 setLoop: 5 scrollTo: show:)
					(messager say: 2 1 5 0 self)
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poDoOnCrystal of Prop
	(properties
		x 258
		y 290
		view 15001
	)
)

(instance soBoogleAxOnCrystal of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego nSaveTime: foCrystal self)
			)
			(1
				(theGame handsOff:)
				(messager say: 2 14 5 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soAxOnCrystal of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 64018 0)
					bSwing: 0
					setMotion: PolyPath 430 291
				)
				(ego setMotion: PolyPath 328 297 self)
			)
			(1
				(ego setHeading: 225 self)
				((ScriptID 64018 0) setMotion: PolyPath 430 291 self)
			)
			(2)
			(3
				((ScriptID 64018 0) setHeading: 270)
				(theGame handsOff:)
				(ego hide:)
				(poAxOnCrystal setCel: 0 init: setCycle: CT 15 1 self)
			)
			(4
				((ScriptID 64018 0) hide:)
				(poBoogleRinging
					setCel: 0
					init:
					setCycle: End coDisposeBoogleRinging
				)
				(poAxOnCrystal setCycle: CT 20 1 self)
			)
			(5
				(sound1 lThumbLoop: 15006)
				(poAxOnCrystal setCycle: CT 45 1 self)
			)
			(6
				(sound1 lThumbLoop: 15007)
				(voCrystal dispose:)
				(poAxOnCrystal setCycle: End self)
			)
			(7
				(poAxOnCrystal dispose:)
				(ego posn: 293 289 setLoop: 4 scrollTo: show:)
				(messager say: 2 2 5 1 self)
			)
			(8
				(ego get: ((ScriptID 64001 0) get: 8))
				(ego get: ((ScriptID 64001 0) get: 0))
				((ScriptID 64017 0) set: 8)
				(foCrystal setTotalWidth: 2 14)
				(voDoor dispose:)
				(poGuardOpensDoor1 setCel: 0 init: setCycle: End self)
				(ego oCuee: poGuardOpensDoor1 self)
				(music1 pageSize: 15099)
				(sound1 lThumbLoop: 15002)
			)
			(9)
			(10
				(= global226 1)
				(messager sayRange: 2 2 5 2 10 self)
			)
			(11
				(= global226 0)
				(poGuardOpensDoor1 setCycle: Beg self)
				(sound1 lThumbLoop: 15003)
			)
			(12
				(music1 pageSize: 15800)
				(voDoor init:)
				(poGuardOpensDoor1 dispose:)
				((ScriptID 64018 0) ID:)
				((ScriptID 64018 0) bSwing: 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance coDisposeBoogleRinging of CueMe
	(properties)
	
	(method (cue)
		((ScriptID 64018 0) show:)
		(poBoogleRinging dispose:)
	)
)

(instance poAxOnCrystal of Prop
	(properties
		x 328
		y 297
		view 15002
	)
)

(instance poBoogleRinging of Prop
	(properties
		x 429
		y 291
		view 15002
		loop 1
	)
)

(instance voCrystal of View
	(properties
		x 208
		y 274
		view 15002
		loop 2
	)
)

(instance voOpenDoor of View
	(properties
		x 331
		y 222
		view 15002
		loop 11
	)
)

(instance soSBoogleEntrance of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 64018 0)
					bSwing: 0
					setMotion: PolyPath 430 291 self
				)
			)
			(1
				(if (not ((ScriptID 64017 0) test: 8))
					((ScriptID 64018 0)
						addObstacle:
							((Polygon new:)
								type: 3
								init: 390 280 392 298 467 298 470 280
								yourself:
							)
					)
				)
				((ScriptID 64018 0) bSwing: 1)
			)
		)
	)
)

(instance roGuardHouseExt of TPRoom
	(properties
		picture 15000
		south 15800
	)
	
	(method (init)
		(super init: &rest)
		(ego init: oPanner: setScaler: Scaler 100 64 290 228)
		((ScriptID 64017 0) set: 25)
		(theGame handsOn:)
		(switch prevRoomNum
			(south
				(theGame handsOff:)
				(ego posn: 310 340 setLoop: 3)
				(ego setMotion: MoveTo 310 292 (ScriptID 64020 0))
				((ScriptID 64018 0)
					init:
					posn: 374 299
					oPanner:
					setScaler: Scaler 100 64 290 228
					setScript: soSBoogleEntrance
				)
			)
			(15100
				(ego
					posn: (foDoor approachX?) (foDoor approachY?)
					setLoop: 2
				)
				((ScriptID 64018 0)
					init:
					posn: 362 243
					oPanner:
					setLoop: 2
					setScaler: Scaler 100 64 290 228
				)
			)
			(else 
				(theGame handsOff:)
				(ego posn: 310 340 setLoop: 3)
				(ego setMotion: MoveTo 310 292 (ScriptID 64020 0))
				((ScriptID 64018 0)
					init:
					posn: 362 243
					oPanner:
					setLoop: 2
					setScaler: Scaler 100 64 290 228
				)
			)
		)
		(foMainExit init:)
		(foDoor init:)
		(foSign init:)
		(foCrystal init:)
		(if (not ((ScriptID 64017 0) test: 8))
			(voCrystal init: setPri: 291)
		)
		(if ((ScriptID 64017 0) test: 28)
			(voOpenDoor init:)
		else
			(voDoor init:)
		)
		(music1 pageSize: 15800)
		(Load rsAUDIO 15006 15001 15002 15007 15003 15005)
		(Load rsVIEW 15002 15001)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 608 312 494 232 28 232 100 319
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 120 295 121 270 240 270 239 297
					yourself:
				)
		)
	)
	
	(method (intoPouch)
		(curRoom newRoom: 15100)
	)
)
