;;; Sierra Script 1.0 - (do not remove this comment)
(script# 21000)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use Script)
(use ExitFeature)
(use Plane)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roStartingLedgeCu 0
)

(instance foBitternutExit of Feature
	(properties
		nsLeft 100
		nsTop 88
		nsRight 318
		nsBottom 264
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 8))
	)
	
	(method (doVerb)
		(ego setScript: soBitternutExit)
	)
)

(instance soBitternutExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 364 163 self)
			)
			(1 (ego setHeading: 315 self))
			(2
				((ScriptID 64017 0) set: 82)
				((ScriptID 64017 0) clear: 81)
				(curRoom newRoom: 20100)
			)
		)
	)
)

(instance foClotheslineExit of ExitFeature
	(properties
		nsLeft 520
		nsTop 172
		nsRight 608
		nsBottom 312
		approachX 528
		approachY 182
		x 530
		y 180
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 2))
	)
	
	(method (doVerb)
		(ego nSaveTime: self self)
	)
	
	(method (cue)
		((ScriptID 64017 0) clear: 81 82)
		(curRoom newRoom: 20100)
	)
)

(instance foTopExit of Feature
	(properties
		nsLeft 396
		nsTop 43
		nsRight 510
		nsBottom 172
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 5))
	)
	
	(method (doVerb)
		(ego setScript: soTopExit)
	)
)

(instance soTopExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 426 160 self)
			)
			(1 (ego setHeading: 45 self))
			(2
				((ScriptID 64017 0) set: 81)
				((ScriptID 64017 0) clear: 82)
				(curRoom newRoom: 20100)
			)
		)
	)
)

(instance oTileCU of Plane
	(properties
		picture 21001
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

(instance voBody of View
	(properties
		x 444
		y 311
		view 20101
		loop 4
	)
)

(instance poTorinLooksUp of Prop
	(properties
		x 417
		y 241
		view 20101
	)
)

(instance poTile of Prop
	(properties
		x 281
		y 132
		view 20101
		loop 2
	)
)

(instance poTorinPicksUpTile of Prop
	(properties
		x 380
		y 186
		view 20101
		loop 1
	)
)

(instance soTileFalling of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(curRoom initThumb: oTileCU)
				(voBody init:)
				(poTorinLooksUp
					setCel: 0
					init:
					setPri: 400
					setCycle: End self
				)
				(poTile setCel: 0 init: setCycle: End self)
				(theSound lThumbLoop: 20107)
			)
			(1)
			(2 (= ticks 20))
			(3
				(curRoom arrowDown: oTileCU)
				(poTorinPicksUpTile setCel: 0 init: setCycle: End self)
			)
			(4
				(if ((ScriptID 64017 0) test: 42)
					(ego get: ((ScriptID 64001 0) get: 15))
					((ScriptID 64017 0) set: 43)
				else
					(ego get: ((ScriptID 64001 0) get: 14))
					((ScriptID 64017 0) set: 42)
				)
				(ego
					posn: 380 186
					init:
					oPanner: 0 -5436 7
					scrollTo:
					hide:
				)
				(= cycles 2)
			)
			(5
				(poTorinPicksUpTile dispose:)
				(ego show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance roStartingLedgeCu of TPRoom
	(properties
		picture 21000
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: 20100)
		(Load 141 20107)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 261 171 532 228 580 204 457 171 409 164 337 174 318 179 276 171
					yourself:
				)
		)
		(foBitternutExit init:)
		(foTopExit init:)
		(foClotheslineExit init:)
		(curRoom setScript: soTileFalling)
	)
	
	(method (intoPouch)
	)
)
