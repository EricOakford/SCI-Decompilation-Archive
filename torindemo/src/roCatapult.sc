;;; Sierra Script 1.0 - (do not remove this comment)
(script# 42100)
(include sci.sh)
(use Main)
(use Inv)
(use TPRoom)
(use TPScript)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roCatapult 0
)

(local
	local0
)
(instance foExitToIslandEast of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 4))
		(self setRect: 0 0 20 316)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance foExitToIslandNorth of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 2))
		(self setRect: 0 296 632 316)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance foExitToIslandWest of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 3))
		(self setRect: 612 0 632 316)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance soExitEast of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath -30 290 self)
			)
			(1
				(theGame handsOff:)
				((ScriptID 64018 0) setMotion: PolyPath -30 290)
				(ego setMotion: MoveTo -30 290 self)
			)
			(2
				(= global248 1)
				(curRoom newRoom: -25336)
			)
		)
	)
)

(instance soExitNorth of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 316 386 self)
			)
			(1
				(theGame handsOff:)
				((ScriptID 64018 0) setMotion: PolyPath 316 386)
				(ego setMotion: MoveTo 316 386 self)
			)
			(2
				(= global248 1)
				(curRoom newRoom: -25336)
			)
		)
	)
)

(instance soExitWest of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 662 290 self)
			)
			(1
				(theGame handsOff:)
				((ScriptID 64018 0) setMotion: PolyPath 662 290)
				(ego setMotion: MoveTo 662 290 self)
			)
			(2
				(= global248 0)
				(curRoom newRoom: -25336)
			)
		)
	)
)

(instance voLever of View
	(properties
		x 489
		y 313
		priority 200
		fixPriority 1
		view -23435
		loop 3
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= signal (| signal $1000))
	)
	
	(method (doVerb)
		(if local0 (ego setScript: LOOKUP_ERROR) (return))
		(if ((ScriptID 64017 0) test: 102)
			(ego setScript: LOOKUP_ERROR)
		else
			(ego setScript: LOOKUP_ERROR)
		)
	)
)

(instance poTorin of Prop
	(properties
		x 489
		y 313
		priority 210
		fixPriority 1
	)
)

(instance poCatapult of Prop
	(properties
		x 489
		y 313
		priority 200
		fixPriority 1
		view -23435
		loop 1
	)
)

(instance poBallsLand of Prop
	(properties
		priority 100
		fixPriority 1
		view -23434
	)
)

(instance poTorinLands of Prop
	(properties
		priority 100
		fixPriority 1
		view -23432
	)
)

(instance foLava of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 50)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 4 72 630 71 632 147 614 165 499 171 498 96 305 90 302 169 4 205
					yourself:
				)
		)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance foRope of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 51)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 486 174 479 200 483 228 501 230
					yourself:
				)
		)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance foCatapultBowl of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 50)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 503 166 543 158 586 171 584 210 552 242 525 240 497 219 494 184
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if local0
					(ego setScript: LOOKUP_ERROR)
				else
					(ego setScript: LOOKUP_ERROR)
				)
			)
			(50
				(ego setScript: LOOKUP_ERROR)
			)
		)
	)
)

(instance soTryForLever of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR
					view: -23432
					loop: 0
					cel: 0
					setCycle: End self
				)
				(messager say: 2 1 3 0 self -25336)
			)
			(1)
			(2
				(LOOKUP_ERROR view: -23433)
				(LOOKUP_ERROR loop: 0 cel: (LOOKUP_ERROR lastCel:))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soJumpInBowl of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 316 280 self)
			)
			(1
				(theGame handsOff:)
				(self setScript: (ScriptID 64018 1) self)
			)
			(2
				(ego setMotion: PolyPath 489 313 self)
			)
			(3 (ego setHeading: 315 self))
			(4
				(ego hide:)
				(LOOKUP_ERROR
					view: -23433
					loop: 0
					cel: 0
					init:
					setCycle: End self
				)
			)
			(5
				(= local0 1)
				(LOOKUP_ERROR setTotalWidth: 50)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				({oBoogleWalkInWest} dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soCutRope of TPScript
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(((ScriptID 64001 0) get: 1) moveTo: -3)
				(LOOKUP_ERROR hide:)
				(LOOKUP_ERROR
					view: -23432
					loop: 1
					cel: 0
					setCycle: End self
				)
			)
			(1
				(cond 
					((> (= temp0 (+ 4 (proc64001_4) global249)) 5) (LOOKUP_ERROR loop: 2 posn: 255 177))
					((== temp0 5) (LOOKUP_ERROR loop: 3 posn: 166 147))
					(else (LOOKUP_ERROR loop: 4 posn: 86 96))
				)
				(LOOKUP_ERROR cel: 0 init: setCycle: End self)
			)
			(2
				(LOOKUP_ERROR dispose:)
				(cond 
					((> (= temp0 (+ 4 (proc64001_4) global249)) 5) ((ScriptID 64019 0) show: 0 42 5 -25336))
					((== temp0 5) (curRoom newRoom: -25136) (return))
					(else ((ScriptID 64019 0) show: 0 42 4 -25336))
				)
				(LOOKUP_ERROR show:)
				(LOOKUP_ERROR view: -23433)
				(LOOKUP_ERROR loop: 0 cel: (LOOKUP_ERROR lastCel:))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soGetOutOfBowl of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR
					view: -23433
					loop: 1
					cel: 0
					setCycle: End self
				)
			)
			(1
				(LOOKUP_ERROR dispose:)
				(ego posn: 489 313 oPanner: 1 -5436 7 show:)
				(ego setMotion: PolyPath 316 280 self)
			)
			(2
				(self setScript: (ScriptID 64018 2) self)
			)
			(3
				(= local0 0)
				(LOOKUP_ERROR setVisibleRange: 50)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soThrowBall of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 489 313 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 315 self)
			)
			(2
				(ego hide:)
				(LOOKUP_ERROR
					view: -23433
					loop: 2
					cel: 0
					init:
					setCycle: End self
				)
			)
			(3
				(LOOKUP_ERROR dispose:)
				(ego show:)
				(ego put: gInventItem)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soCockCatapult of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 489 313 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 315 self)
			)
			(2
				(ego hide:)
				(LOOKUP_ERROR
					view: -23435
					loop: 0
					cel: 0
					init:
					setCycle: End self
				)
			)
			(3
				(LOOKUP_ERROR view: -23435 loop: 2 cel: 0)
				((ScriptID 64017 0) set: 102)
				(ego show:)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soTriggerCatapult of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 489 313 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 315 self)
			)
			(2
				(ego hide:)
				(LOOKUP_ERROR
					view: -23434
					loop: 0
					cel: 0
					init:
					setCycle: End self
				)
			)
			(3
				(LOOKUP_ERROR
					view: -23434
					loop: 1
					cel: 0
					setCycle: End self
				)
			)
			(4
				(self setScript: LOOKUP_ERROR self)
			)
			(5
				(LOOKUP_ERROR view: -23435 loop: 1 cel: 0)
				((ScriptID 64017 0) clear: 102)
				(ego show:)
				(LOOKUP_ERROR dispose:)
				(= global249 0)
				(LOOKUP_ERROR dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soBallsLand of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global249) (self dispose:) (return))
					((> global249 5) (LOOKUP_ERROR loop: 2 posn: 254 174))
					((== global249 5) (LOOKUP_ERROR loop: 3 posn: 154 135))
					(else (LOOKUP_ERROR loop: 4 posn: 82 97))
				)
				(LOOKUP_ERROR cel: 0 init: setCycle: End self)
			)
			(1
				(LOOKUP_ERROR dispose:)
				(self dispose:)
			)
		)
	)
)

(instance soPutBallInBowl of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 489 313 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 315 self)
			)
			(2
				(ego hide:)
				(LOOKUP_ERROR
					view: -23436
					loop: 0
					cel: 0
					init:
					setCycle: End self
				)
			)
			(3
				(ego put: gInventItem)
				(++ global249)
				(LOOKUP_ERROR dispose:)
				(ego oPanner: 1 -5436 6 show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soWalkInEast of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: -30 290 setMotion: MoveTo 30 290 self)
				((ScriptID 64018 0) setScript: LOOKUP_ERROR)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soBoogleWalkInEast of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 64018 0)
					bSwing: 0
					posn: -40 290
					setMotion: MoveTo 20 290 self
				)
			)
			(1
				((ScriptID 64018 0) bSwing: 1)
				(self dispose:)
			)
		)
	)
)

(instance soWalkInWest of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: 662 290 setMotion: MoveTo 600 290 self)
				((ScriptID 64018 0) setScript: LOOKUP_ERROR)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soBoogleWalkInWest of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 64018 0)
					bSwing: 0
					posn: 672 290
					setMotion: MoveTo 610 290 self
				)
			)
			(1
				((ScriptID 64018 0) bSwing: 1)
				(self dispose:)
			)
		)
	)
)

(instance roCatapult of TPRoom
	(properties
		picture -23436
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: -25336)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 0 212 0 312 631 314 630 250 610 269 459 305 244 191 93 218
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 84 282 53 260 96 250 202 257 244 274 205 286
					yourself:
				)
		)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(if ((ScriptID 64017 0) test: 102)
			(LOOKUP_ERROR init:)
			(LOOKUP_ERROR view: -23435 loop: 2)
		else
			(LOOKUP_ERROR view: -23435 loop: 1)
		)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(ego init: oPanner:)
		((ScriptID 64018 0) posn: 30 300 init: oPanner:)
		(switch prevRoomNum
			(-25336
				(if global248
					(ego setScript: LOOKUP_ERROR)
				else
					(ego setScript: LOOKUP_ERROR)
				)
			)
			(else 
				(ego posn: 20 300)
				(theGame handsOn:)
			)
		)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
		(if (proc64001_2) (ego get: (proc64001_2)))
	)
)
