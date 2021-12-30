;;; Sierra Script 1.0 - (do not remove this comment)
(script# 16100)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use Script)
(use AnimPic)
(use ExitFeature)
(use Talker)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roCrystalCityMoat 0
	toZax 1
)

(instance animPlayer of AnimPlayer
	(properties)
)

(instance foExit of ExitFeature
	(properties
		approachX 115
		approachY 414
		x 114
		y 415
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					init: 78 293 323 302 301 329 50 326
					yourself:
				)
		)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 8))
	)
	
	(method (doVerb)
		(animPlayer dispose:)
		(curRoom newRoom: 16000)
	)
)

(instance poGuard of Prop
	(properties
		noun 3
		x 488
		y 151
		view 16101
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 4 1 3 0))
		)
	)
)

(instance poZaxMouth of Prop
	(properties
		view 16101
		loop 2
	)
)

(instance voZaxBust of View
	(properties
		view 16101
		loop 1
	)
)

(instance toZax of Talker
	(properties)
	
	(method (init &tmp poGuardCuX poGuardCuY poGuardCuPriority)
		(poGuardCu hide:)
		(= poGuardCuX (poGuardCu x?))
		(= poGuardCuY (poGuardCu y?))
		(= priority (= poGuardCuPriority (poGuardCu priority?)))
		(= bust (voZaxBust x: poGuardCuX y: poGuardCuY))
		(= mouth (poZaxMouth x: poGuardCuX y: poGuardCuY))
		(super init:)
		(mouth setPri: (+ 1 poGuardCuPriority))
	)
	
	(method (dispose)
		(if (not (poGuardCu plane?)) (poGuardCu init:))
		(poGuardCu show:)
		(super dispose: &rest)
	)
)

(instance poGuardCu of Prop
	(properties
		x 489
		y 141
		view 16101
	)
	
	(method (init)
		(super init: &rest)
	)
)

(instance soGuardCuTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poGuardCu cel: 0 init: setCycle: End self)
			)
			(1
				(switch global230
					(0 (messager say: 3 1 2 0 self))
					(1 (messager say: 3 1 4 0 self))
					(2 (messager say: 3 1 5 0 self))
					(3 (messager say: 3 1 6 0 self))
					(4 (messager say: 3 1 7 0 self))
					(else 
						(messager say: 3 1 8 0 self)
					)
				)
				(if (< global230 6) (++ global230))
			)
			(2
				(poGuardCu setCycle: Beg self)
			)
			(3
				(poGuardCu dispose:)
				(self dispose:)
			)
		)
	)
)

(instance foGuard of Feature
	(properties
		noun 3
		sightAngle 40
		x 486
		y 105
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 472 129 473 86 502 88 502 129
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(curRoom setScript: soGuardCuTalk)
			)
		)
	)
)

(instance soTorinJumpInMoat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego nSaveTime: foMoat self))
			(1
				(ego hide:)
				(animPlayer init: load: {fallin} cuee: self play:)
			)
			(2
				(animPlayer hide:)
				(if ((ScriptID 64019 0) show: 0 42 9) (ego show:))
				(self dispose:)
			)
		)
	)
)

(instance soDropSnails of TPScript
	(properties
		oMessageList 1
		nTextWidth 1
	)
	
	(method (init)
		(animPlayer init: load: {snail} cuee: self)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego nSaveTime: foMoat self)
			)
			(1
				(ego hide:)
				(((ScriptID 64001 0) get: 6) dispose:)
				(animPlayer play:)
			)
			(2 (self rememberMessage:))
		)
	)
	
	(method (rememberMessage)
		(((ScriptID 64001 0) get: 6) dispose:)
		(animPlayer stop: hide:)
		(ego posn: 154 293 loop: 4 show:)
		(ego get: ((ScriptID 64001 0) get: 7))
		((ScriptID 64017 0) set: 7)
		(theGame handsOn:)
		(self dispose:)
	)
	
	(method (sayMessage)
		(ego show:)
		((ScriptID 64017 0) unlockAudio: 7)
		(animPlayer stop: hide:)
		(ego setScript: self)
	)
)

(instance foMoat of Feature
	(properties
		noun 1
		sightAngle 40
		approachX 307
		approachY 259
		x 308
		y 258
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 157 243 1 229 3 221 200 232 634 297 632 312 447 315 341 266
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 1 10)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(ego setScript: soTorinJumpInMoat)
			)
			(10
				(ego setScript: soDropSnails)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance foDrawbridge of Feature
	(properties
		noun 2
		sightAngle 40
		x 356
		y 148
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 272 266 266 43 446 12 432 288
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
)

(instance roCrystalCityMoat of TPRoom
	(properties
		picture 16100
		west 16000
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: 16000)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: -2 256 -2 384 424 321 360 286 317 278 146 255
					yourself:
				)
		)
		(foMoat init:)
		(foDrawbridge init:)
		(foGuard init:)
		(foExit init:)
		((ScriptID 64018 0)
			init:
			posn: 100 350
			setHeading: 25
			oPanner:
		)
		(ego
			init:
			oPanner:
			posn: 120 347
			loop: 6
			setMotion: MoveTo 201 316 (ScriptID 64020 0)
		)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 0) get: 6))
	)
)
