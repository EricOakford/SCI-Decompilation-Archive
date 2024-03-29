;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11000)
(include sci.sh)
(use Main)
(use soBooglePouch)
(use TPRoom)
(use TPScript)
(use TPSound)
(use Script)
(use ExitFeature)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Motion)
(use Actor)

(public
	roFahrmHouseExt 0
)

(instance foSouthExit of ExitFeature
	(properties
		approachX 107
		approachY 330
		x 100
		y 335
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 79 292 345 290 313 331 -1 319
					yourself:
				)
		)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 8))
	)
	
	(method (doVerb)
		(ego oPlane: self self)
	)
	
	(method (cue)
		(curRoom newRoom: 10100)
	)
)

(instance poDoorExit of Prop
	(properties
		approachX 378
		approachY 188
		x 386
		y 188
		priority 180
		fixPriority 1
		view 11004
	)
	
	(method (init)
		(= signal (| signal $1000))
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 1))
	)
	
	(method (doVerb)
		(curRoom setScript: soDoorExit)
	)
)

(instance oDoorSnd of TPSound
	(properties)
)

(instance soDoorExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 3 1 1 0 self)
				(oDoorSnd nHeight: 11003)
			)
			(1
				(self setScript: (ScriptID 64018 1) self)
			)
			(2
				(ego oPlane: poDoorExit self)
			)
			(3
				(ego hide:)
				(poDoorExit cel: 1 setCycle: CT 6 1 self)
			)
			(4
				(oDoorSnd lThumbLoop: 11003)
				(poDoorExit setCycle: End self)
			)
			(5 (curRoom newRoom: 11100))
		)
	)
)

(instance oAxPolygon of Polygon
	(properties
		type $0002
	)
	
	(method (init)
		(super
			init: 501 254 545 254 545 273 496 273 453 283 402 270 412 238 497 238
		)
		(curRoom addObstacle: self)
	)
	
	(method (dispose)
		((curRoom obstacles?) delete: self)
		(super dispose:)
	)
)

(instance oBlockPolygon of Polygon
	(properties
		type $0002
	)
	
	(method (init)
		(super init: 503 269 453 282 401 269 413 238 496 238)
		(curRoom addObstacle: self)
	)
	
	(method (dispose)
		((curRoom obstacles?) delete: self)
		(super dispose:)
	)
)

(instance voAx of View
	(properties
		x 503
		y 266
		priority 271
		fixPriority 1
		view 11006
	)
	
	(method (init)
		(= signal (| signal $1000))
		(super init: &rest)
		(oAxPolygon init:)
	)
	
	(method (dispose)
		(oAxPolygon dispose:)
		(oBlockPolygon init:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(curRoom setScript: soPickUpAx)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance poPickUpAx of Prop
	(properties
		view 11001
	)
)

(instance soPickUpAx of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 505 260 self)
			)
			(1
				(theGame handsOff:)
				(ego setMotion: MoveTo (voAx x?) (voAx y?) self)
			)
			(2 (ego setHeading: 225 self))
			(3
				(ego hide:)
				(poPickUpAx
					posn: (voAx x?) (voAx y?)
					setPri: (- (voAx priority?) 1)
					init:
					setCycle: CT 5 1 self
				)
			)
			(4
				(= ticks (poPickUpAx cycleSpeed?))
			)
			(5
				(voAx dispose:)
				(ego get: ((ScriptID 64001 0) get: 0))
				((ScriptID 64017 0) set: 0)
				(poPickUpAx setCycle: End self)
			)
			(6
				(ego
					posn: (+ (poPickUpAx x?) 28) (poPickUpAx y?)
					show:
				)
				(poPickUpAx dispose:)
				(messager say: 1 1 0 0 self)
			)
			(7
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poBoogleToAx of Prop
	(properties
		view 11002
	)
)

(instance soBoogleToAx of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(((ScriptID 64001 1) get: 0) moveTo: -3)
				(curRoom newTarget: foAx)
				((ScriptID 64018 0) hide:)
				(poBoogleToAx
					posn: (foAx approachX?) (foAx approachY?)
					init:
					cel: 0
					setCycle: End self
				)
			)
			(1
				((ScriptID 64018 0) show:)
				(poBoogleToAx dispose:)
				(if caller (caller cue:))
				(self dispose:)
			)
		)
	)
)

(instance foAx of BoogleLearnMeClass
	(properties
		approachX 434
		approachY 249
		x 435
		y 250
	)
)

(instance voRope of View
	(properties
		x 229
		y 32
		view 11005
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1) (curRoom setScript: soPickUpRope))
	)
)

(instance poPickUpRope of Prop
	(properties
		view 11000
	)
)

(instance soPickUpRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 208 184 self)
			)
			(1 (ego setHeading: 45 self))
			(2
				(ego hide:)
				(poPickUpRope
					posn: (ego x?) (ego y?)
					nCurPosY: 105
					init:
					setCycle: CT 9 1 self
				)
			)
			(3
				(= ticks (poPickUpRope cycleSpeed?))
			)
			(4
				((ScriptID 64017 0) set: 1)
				(ego get: ((ScriptID 64001 0) get: 1))
				(voRope dispose:)
				(poPickUpRope setCycle: End self)
			)
			(5
				(ego posn: (+ (ego x?) 13) (- (ego y?) 3) show:)
				(poPickUpRope dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance roFahrmHouseExt of TPRoom
	(properties
		picture 11000
		south 10100
	)
	
	(method (init)
		(super init: &rest)
		(= global202 1)
		(theMusic pageSize: 11100)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						271
						187
						272
						168
						194
						184
						80
						184
						57
						198
						72
						251
						12
						326
						155
						379
						294
						306
						480
						311
						597
						275
						632
						232
						623
						205
						587
						202
						575
						207
						548
						232
						504
						230
						478
						226
						421
						211
						406
						189
						365
						184
						334
						195
						303
						202
					yourself:
				)
		)
		(if (not ((ScriptID 64017 0) test: 0))
			(voAx init: setVisibleRange: 1)
		)
		(if (not ((ScriptID 64017 0) test: 1))
			(voRope init: setVisibleRange: 1)
		)
		(if (not (ego has: ((ScriptID 64001 1) get: 0)))
			(foAx init: soBoogleToAx)
			(self setDefault: foAx)
		)
		(foSouthExit init:)
		(poDoorExit init:)
		((ScriptID 64018 0)
			init:
			oPanner:
			setScaler: Scaler 100 47 287 172
		)
		(ego init: oPanner: setScaler: Scaler 100 50 287 172)
		(theGame handsOn:)
		(switch prevRoomNum
			(0
				(ego posn: 162 304 loop: 6)
				((ScriptID 64018 0) posn: 300 292)
			)
			(11100
				(ego
					posn: (poDoorExit approachX?) (+ 12 (poDoorExit approachY?))
					loop: 5
				)
				((ScriptID 64018 0) posn: 418 220)
			)
			(else 
				(theGame handsOff:)
				(ego posn: 66 410 loop: 6)
				(ego setMotion: MoveTo 162 304 (ScriptID 64020 0))
				((ScriptID 64018 0) posn: 300 292)
			)
		)
	)
	
	(method (intoPouch)
		((ScriptID 64017 0) clear: 0)
	)
)
