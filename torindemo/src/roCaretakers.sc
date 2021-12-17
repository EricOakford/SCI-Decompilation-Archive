;;; Sierra Script 1.0 - (do not remove this comment)
(script# 42000)
(include sci.sh)
(use Main)
(use Inv)
(use TPRoom)
(use TPScript)
(use Array)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roCaretakers 0
)

(local
	local0
	local1
	local2
	local3 =  1
)
(procedure (localproc_0090 &tmp temp0 temp1 temp2)
	(if local2
		(= temp0 (+ 4 (proc64001_4)))
	else
		(= temp0 0)
	)
	(cond 
		((> (= temp1 global250) temp0) (= temp2 0))
		((== temp1 temp0) (= temp2 1))
		(else (= temp2 2))
	)
	(if (!= temp2 local3)
		(switch temp2
			(0
				(curRoom setScript: soSeeSawLeftDown)
			)
			(2
				(curRoom setScript: soSeeSawRightDown)
			)
			(1
				(switch local3
					(2
						(curRoom setScript: soSeeSawCenterFromRight)
					)
					(0
						(curRoom setScript: soSeeSawCenterFromLeft)
					)
				)
			)
		)
		(= local3 temp2)
		(foSeeSawLeft setRect:)
		(foSeeSawRight setRect:)
	)
)

(procedure (localproc_01aa)
	(= local0
		(IntArray
			newWith: 14 167 156 167 168 167 178 167 188 167 199 167 211 169 221
		)
	)
	(= local1
		(IntArray
			newWith: 14 455 243 455 233 455 221 455 210 455 200 455 190 455 178
		)
	)
)

(procedure (localproc_0263 param1 param2)
	(if (or (< argc 2) (not param1))
		(MonoOut {error in GetLocX -- 30400.sc DJM})
		(return 0)
	)
	(return (param1 at: (* param2 2)))
)

(procedure (localproc_02a7 param1 param2)
	(if (or (< argc 2) (not param1))
		(MonoOut {error in GetLocY -- 30400.sc DJM})
		(return 0)
	)
	(return (param1 at: (+ (* param2 2) 1)))
)

(instance foExitToIsland of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 4))
		(self setRect: 0 0 20 316)
	)
	
	(method (doVerb)
		(ego setScript: soExit)
	)
)

(instance soExit of TPScript
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
			(2 (curRoom newRoom: -25336))
		)
	)
)

(instance poSeeSaw of Prop
	(properties
		x 320
		y 213
		priority 237
		fixPriority 1
		view -23534
	)
	
	(method (doit &tmp temp0 temp1)
		(super doit: &rest)
		(= temp0 (localproc_0263 local0 cel))
		(= temp1 (localproc_02a7 local0 cel))
		(if
			(or
				(!= temp0 (voBallsInBasket x?))
				(!= temp1 (voBallsInBasket y?))
			)
			(voBallsInBasket posn: temp0 temp1)
			(if (voBallsInBasket scratch?) (voBallsInBasket doit:))
		)
		(= temp0 (localproc_0263 local1 cel))
		(= temp1 (localproc_02a7 local1 cel))
		(if
			(or
				(!= temp0 (voTorinInBasket x?))
				(!= temp1 (voTorinInBasket y?))
			)
			(voTorinInBasket posn: temp0 temp1)
			(if (voTorinInBasket scratch?) (voTorinInBasket doit:))
		)
	)
)

(instance poTorin of Prop
	(properties)
)

(instance voTorinInBasket of View
	(properties
		view -23535
		loop 4
	)
)

(instance voBallsInBasket of View
	(properties
		view -23534
		loop 1
	)
)

(instance foSeeSawLeft of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(if (< global250 6) (self setVisibleRange: 50))
		(if global250 (self setVisibleRange: 1))
		(self setRect:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(50
				(ego setScript: soPutBallInBasket)
			)
			(1
				(if (>= (proc64001_4) 5)
					(messager say: 1 1 2 0 0 -25336)
					(return)
				)
				(if (== global250 0)
					(MonoOut {tried to take ball from empty basket})
					(return)
				)
				(ego setScript: soTakeBallFromBasket)
			)
		)
	)
	
	(method (setRect)
		(switch local3
			(0
				(super setRect: 148 208 200 234)
			)
			(1
				(super setRect: 141 174 196 203)
			)
			(2
				(super setRect: 143 144 198 172)
			)
			(else 
				(MonoOut {prob in foSeeSawLeft setRect})
			)
		)
	)
)

(instance foSeeSawRight of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self setRect:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if local2
					(ego setScript: soJumpOutOfBasket)
				else
					(ego setScript: soJumpIntoBasket)
				)
			)
		)
	)
	
	(method (setRect)
		(switch local3
			(0
				(super setRect: 432 144 487 171)
			)
			(1
				(super setRect: 433 177 489 203)
			)
			(2
				(super setRect: 427 205 482 234)
			)
			(else 
				(MonoOut {prob in foSeeSawRight setRect})
			)
		)
	)
)

(instance soJumpIntoBasket of TPScript
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 381 256 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 0 self)
			)
			(2
				(ego hide:)
				(poTorin
					view: -23535
					loop: 0
					cel: 0
					posn: 381 256
					init:
					setCycle: End self
				)
			)
			(3
				(= local2 1)
				(poTorin dispose:)
				(voTorinInBasket init:)
				(foSeeSawLeft dispose:)
				(foExitToIsland dispose:)
				(localproc_0090)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soJumpOutOfBasket of TPScript
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local2 0)
				(voTorinInBasket dispose:)
				(foSeeSawLeft init:)
				(foExitToIsland init:)
				(switch local3
					(2 (= temp0 3))
					(1 (= temp0 2))
					(0 (= temp0 1))
				)
				(poTorin
					view: -23535
					loop: temp0
					cel: 0
					posn: 381 256
					init:
					setCycle: End self
				)
				(localproc_0090)
			)
			(1
				(poTorin dispose:)
				(ego oPanner: 1 -5436 0 show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soPutBallInBasket of TPScript
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 171 250 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 0 self)
			)
			(2
				(ego hide:)
				(poTorin
					view: -23536
					loop: 0
					cel: 0
					posn: 171 250
					init:
					setCycle: End self
				)
			)
			(3
				(if (== local3 1) (= temp0 1) else (= temp0 2))
				(poTorin loop: temp0 cel: 0 setCycle: End self)
			)
			(4
				(if (== global250 0) (voBallsInBasket init:))
				(++ global250)
				(voBallsInBasket cel: (- global250 1))
				(if (== global250 6) (foSeeSawLeft setTotalWidth: 50))
				(foSeeSawLeft setVisibleRange: 1)
				(ego put: gInventItem)
				(localproc_0090)
				(poTorin loop: 4 cel: 0 setCycle: End self)
			)
			(5
				(poTorin dispose:)
				(ego oPanner: 1 -5436 0 show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soTakeBallFromBasket of TPScript
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 171 250 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 0 self)
			)
			(2
				(ego hide:)
				(poTorin
					view: -23536
					loop: 2
					cel: (poTorin lastCel:)
					posn: 171 250
					init:
					setCycle: Beg self
				)
			)
			(3
				(if (== (-- global250) 0)
					(voBallsInBasket dispose:)
					(foSeeSawLeft setTotalWidth: 1)
				else
					(voBallsInBasket cel: (- global250 1))
				)
				(foSeeSawLeft setVisibleRange: 50)
				(ego get: (proc64001_2))
				(localproc_0090)
				(poTorin
					loop: 0
					cel: (poTorin lastCel:)
					setCycle: Beg self
				)
			)
			(4
				(poTorin dispose:)
				(ego oPanner: 1 -5436 3 show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soWalkIn of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: -30 290 setMotion: MoveTo 30 290 self)
				((ScriptID 64018 0) setScript: soBoogleWalkIn)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soBoogleWalkIn of TPScript
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

(instance soSeeSawLeftDown of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poSeeSaw setCycle: End self)
			)
			(1 (self dispose:))
		)
	)
)

(instance soSeeSawRightDown of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poSeeSaw setCycle: Beg self)
			)
			(1 (self dispose:))
		)
	)
)

(instance soSeeSawCenterFromRight of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poSeeSaw setCycle: CT 3 1 self)
			)
			(1 (self dispose:))
		)
	)
)

(instance soSeeSawCenterFromLeft of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poSeeSaw setCycle: CT 3 -1 self)
			)
			(1 (self dispose:))
		)
	)
)

(instance roCaretakers of TPRoom
	(properties
		picture -23536
	)
	
	(method (init)
		(super init: &rest)
		(music1 pageSize: -25336)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						2
						200
						2
						284
						35
						298
						128
						294
						190
						294
						296
						275
						360
						272
						353
						254
						506
						259
						577
						240
						602
						233
						609
						206
						550
						205
						506
						199
						414
						203
						333
						203
						277
						205
						213
						204
						118
						195
						138
						240
						163
						268
						230
						266
						295
						261
						292
						268
						214
						283
						157
						280
						131
						272
						64
						199
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 354 250 325 252 160 254 149 237 497 234 496 242 493 248
					yourself:
				)
		)
		(localproc_01aa)
		(poSeeSaw init:)
		(foSeeSawLeft init:)
		(foSeeSawRight init:)
		(if global250
			(= local3 0)
			(poSeeSaw cel: 6)
		else
			(= local3 1)
			(poSeeSaw cel: 3)
		)
		(foExitToIsland init:)
		(ego init: oPanner: setScaler: Scaler 73 35 255 205)
		((ScriptID 64018 0)
			posn: 30 300
			init:
			oPanner:
			setScaler: Scaler 73 35 255 205
		)
		(theGame handsOn:)
		(ego setScript: soWalkIn)
	)
	
	(method (dispose)
		(if local0 (local0 dispose:) (= local0 0))
		(if local1 (local1 dispose:) (= local1 0))
		(super dispose: &rest)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 0) get: 38))
		(ego get: ((ScriptID 64001 0) get: 39))
		(ego get: ((ScriptID 64001 0) get: 40))
		(ego get: ((ScriptID 64001 0) get: 41))
		(ego get: ((ScriptID 64001 0) get: 42))
		(ego get: ((ScriptID 64001 0) get: 43))
	)
)
