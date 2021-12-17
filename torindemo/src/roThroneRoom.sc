;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20300)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use Script)
(use ExitFeature)
(use Plane)
(use Print)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roThroneRoom 0
)

(local
	local0
)
(instance foToCliffExit of ExitFeature
	(properties
		approachX 28
		approachY 419
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 4))
		(self
			setPolygon: ((Polygon new:)
				init: 26 254 95 319 -1 316 -2 252
				yourself:
			)
		)
	)
	
	(method (doVerb)
		(ego oPlane: self self)
	)
	
	(method (cue)
		(curRoom newRoom: 20100)
	)
)

(instance soKneelAtTile of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 306 329 self)
			)
			(1 (ego setHeading: 45 self))
			(2 (= ticks 1))
			(3
				(ego hide:)
				(poTorin
					view: 20303
					loop: 0
					cel: 0
					posn: 299 314
					init:
					setCycle: End self
				)
			)
			(4 (self dispose:))
		)
	)
)

(instance soUnKneelAtTile of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poTorin loop: 1 cel: 0 setCycle: End self)
			)
			(1
				(poTorin dispose:)
				(ego show:)
				(self dispose:)
			)
		)
	)
)

(instance soHumbleBoy of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: soKneelAtTile self)
				(messager sayRange: 0 0 1 1 2 self)
			)
			(1)
			(2
				(self setScript: soUnKneelAtTile self)
				(messager say: 0 0 1 3 self)
			)
			(3)
			(4
				(messager sayRange: 0 0 1 4 12 self)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soGiveShardToKing of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 262 282 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 45 self)
			)
			(2
				(messager sayRange: 1 12 0 1 5 self)
			)
			(3
				(ego hide:)
				(poTorin
					view: 20300
					cel: 0
					loop: 0
					posn: (ego x?) (ego y?)
					init:
					setCycle: End self
				)
				(messager say: 1 12 0 6 self)
			)
			(4)
			(5
				(poTorin cel: 0 loop: 1 setCycle: CT 8 1 self)
				(ego put: ((ScriptID 64001 0) get: 8))
				(Prints {missing anim of shard in roof})
				(Prints {missing anim of rainbow on the wall})
			)
			(6
				(poTorin setCycle: End self)
				(messager say: 1 12 0 7 self)
			)
			(7)
			(8
				(poTorin dispose:)
				(ego posn: 276 290 show:)
				(messager sayRange: 1 12 0 8 20 self)
				(Prints {missing anim giving/getting invite})
				(ego get: ((ScriptID 64001 0) get: 24))
				((ScriptID 64017 0) set: 52)
			)
			(9
				(messager say: 1 12 0 21 self)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poKing of Prop
	(properties
		x 470
		y 206
		view 20302
	)
	
	(method (init)
		(= signal (| signal $1000))
		(super init: &rest)
		(if (< global293 6) (self setVisibleRange: 1))
		(if (ego has: ((ScriptID 64001 0) get: 8))
			(self setVisibleRange: 12)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(switch global293
					(0
						(messager say: 1 1 2 0)
						(++ global293)
					)
					(1
						(messager say: 1 1 3 0)
						(++ global293)
					)
					(2
						(messager say: 1 1 4 0)
						(++ global293)
					)
					(3
						(curRoom initThumb: oThroneCUPlane)
						(++ global293)
					)
					(4
						(messager say: 1 1 13 0)
						(++ global293)
					)
					(5
						(messager say: 1 1 8 0)
						(++ global293)
						((ScriptID 64017 0) set: 65)
						(self setTotalWidth: 1)
					)
				)
			)
			(12
				(ego setScript: soGiveShardToKing)
			)
		)
	)
)

(instance poQueen of Prop
	(properties
		x 522
		y 206
		view 20302
		loop 1
	)
	
	(method (init)
		(= signal (| signal $1000))
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(poKing doVerb: theVerb)
	)
)

(instance poGirl1Fanning of Prop
	(properties
		x 378
		y 252
		view 20302
		loop 2
	)
	
	(method (init)
		(= signal (| signal $1000))
		(super init: &rest)
		(self setVisibleRange: 1 setCycle: Fwd)
	)
	
	(method (doVerb)
		(messager say: 3 0 0 0)
	)
)

(instance poGirl2Fanning of Prop
	(properties
		x 602
		y 276
		view 20302
		loop 3
	)
	
	(method (init)
		(= signal (| signal $1000))
		(super init: &rest)
		(self setVisibleRange: 1 setCycle: Fwd)
	)
	
	(method (doVerb)
		(messager say: 4 0 0 0)
	)
)

(instance soPickUpBeestLeg of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 134 257 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 315 self)
			)
			(2
				(ego hide:)
				(poTorin
					cel: 0
					loop: 0
					view: 20301
					posn: (ego x?) (ego y?)
					init:
					setCycle: CT 4 1 self
				)
			)
			(3
				(voBeestLeg dispose:)
				((ScriptID 64017 0) set: 67)
				(poTorin setCycle: CT 12 1 self)
			)
			(4
				(if ((ScriptID 64017 0) test: 67)
					(messager say: 6 1 5 0 self)
				else
					(messager say: 6 1 11 0 self)
				)
				(poTorin setCycle: End self)
			)
			(5)
			(6
				(ego get: ((ScriptID 64001 0) get: 25))
				((ScriptID 64017 0) set: 53)
				(poTorin dispose:)
				(ego posn: 110 266 loop: 8 cel: 4 show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance voBeestLeg of View
	(properties
		x 84
		y 199
		view 20308
	)
	
	(method (init)
		(super init: &rest)
		(if
			(and
				(not (ego has: ((ScriptID 64001 0) get: 25)))
				(not ((ScriptID 64017 0) test: 73))
			)
			(self setVisibleRange: 1)
		)
	)
	
	(method (doVerb)
		(ego setScript: soPickUpBeestLeg)
	)
)

(instance poTorin of Prop
	(properties)
)

(instance foFloorTileExit of CUExitFeature
	(properties)
	
	(method (doVerb)
		(theGame handsOff:)
		(curRoom arrowDown: oFloorTile)
		(curRoom setScript: soUnKneelAtTile (ScriptID 64020 0))
	)
)

(instance oFloorTile of Plane
	(properties
		picture 20302
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
		(foFloorTileExit init:)
	)
)

(instance soAttemptPickUpFloorTile of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(self setScript: soKneelAtTile self)
			)
			(1
				(curRoom initThumb: oFloorTile)
				(poTorinCUView
					view: 20309
					loop: 0
					posn: 348 205
					init:
					setCycle: CT 21 1 self
				)
			)
			(2
				(messager say: 5 1 10 1 self)
			)
			(3
				(messager say: 5 1 10 2 self)
			)
			(4
				(poTorinCUView setCycle: CT 30 1 self)
			)
			(5
				(messager sayRange: 5 1 10 3 4 self)
			)
			(6
				(poTorinCUView setCycle: End self)
				(messager sayRange: 5 1 10 5 6 self)
			)
			(7)
			(8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soPickUpFloorTile of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(self setScript: soKneelAtTile self)
			)
			(1
				(curRoom initThumb: oFloorTile)
				(poTorinCUView
					cel: 0
					loop: 0
					view: 20306
					posn: 348 205
					init:
				)
				(messager say: 5 1 9 0 self)
			)
			(2
				(poTorinCUView setCycle: End self)
			)
			(3
				(ego get: ((ScriptID 64001 0) get: 22))
				((ScriptID 64017 0) set: 50)
				(foFloorTile dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance foFloorTile of Feature
	(properties
		x 327
		y 296
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 324 297 348 290 384 300 356 312
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(if ((ScriptID 64017 0) test: 93)
			(ego setScript: soPickUpFloorTile)
		else
			(ego setScript: soAttemptPickUpFloorTile)
		)
	)
)

(instance foCeilingTile of Feature
	(properties
		sightAngle 40
		x 280
		y 100
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						192
						103
						224
						110
						230
						146
						272
						148
						284
						124
						300
						148
						314
						127
						330
						136
						340
						134
						340
						96
						370
						81
						302
						55
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(if (ego has: ((ScriptID 64001 0) get: 8))
			(messager say: 8 1 6 0)
		else
			(messager say: 8 1 12 0)
		)
	)
)

(instance soThroneCUQueenSpeech of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager sayRange: 1 1 7 1 10 self)
			)
			(1
				(messager say: 1 1 7 11 self)
				(poQueenCUView setCycle: CT 58 1 self)
			)
			(2
				(poTorinCUView setCycle: End self)
				(poQueenCUView setCycle: End self)
			)
			(3)
			(4
				(messager say: 1 1 7 12 self)
				(ego get: ((ScriptID 64001 0) get: 31))
				((ScriptID 64017 0) set: 59)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poTorinCUView of Prop
	(properties
		x 60
		y 315
		view 20305
		loop 1
	)
)

(instance poKingCUView of Prop
	(properties
		x 251
		y 248
		priority 10
		fixPriority 1
		view 20307
		loop 1
	)
)

(instance poQueenCUView of Prop
	(properties
		x 429
		y 225
		view 20305
	)
)

(instance poGirl1CUView of Prop
	(properties
		priority 10
		fixPriority 1
		view 20307
		loop 3
	)
)

(instance poGirl2CUView of Prop
	(properties
		x 631
		view 20307
		loop 4
	)
)

(instance foThroneCUExit of CUExitFeature
	(properties)
	
	(method (doVerb)
		(curRoom arrowDown: oThroneCUPlane)
	)
)

(instance oThroneCUPlane of Plane
	(properties
		picture 20301
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
		(poKingCUView init: self)
		(poQueenCUView init: self)
		(poTorinCUView
			init: self
			setPri: (- (poQueenCUView priority?) 1)
		)
		(poGirl1CUView init: self setCycle: Fwd)
		(poGirl2CUView init: self setCycle: Fwd)
		(foThroneCUExit init:)
		(poKingCUView setScript: soThroneCUQueenSpeech)
	)
)

(instance roThroneRoom of TPRoom
	(properties
		picture 20300
	)
	
	(method (init)
		(super init: &rest)
		(music1 pageSize: 20300)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 314 264 218 268 146 247 -20 259 -20 372 634 372 634 321
					yourself:
				)
		)
		(poKing init:)
		(poQueen init:)
		(poGirl1Fanning init:)
		(poGirl2Fanning init:)
		(foCeilingTile init:)
		(voBeestLeg init:)
		(if (not ((ScriptID 64017 0) test: 50))
			(foFloorTile init:)
		)
		(= local0 0)
		(foToCliffExit init:)
		(theGame handsOff:)
		(ego
			oPanner:
			posn: 28 419
			setScaler: Scaler 100 85 300 249
			init:
		)
		(if (not ((ScriptID 64017 0) test: 64))
			(ego setScript: soHumbleBoy)
		else
			(ego setMotion: MoveTo 278 311 (ScriptID 64020 0))
		)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 0) get: 8))
		((ScriptID 64017 0) set: 93)
	)
)
