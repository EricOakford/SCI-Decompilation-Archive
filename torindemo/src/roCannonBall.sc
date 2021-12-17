;;; Sierra Script 1.0 - (do not remove this comment)
(script# 42200)
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
	roCannonBall 0
)

(local
	local0
)
(instance foExitToIsland of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 3))
		(self setRect: 610 0 632 316)
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
				(ego setMotion: PolyPath 662 300 self)
			)
			(1
				(theGame handsOff:)
				((ScriptID 64018 0) setMotion: PolyPath 662 300)
				(ego setMotion: MoveTo 662 300 self)
			)
			(2 (curRoom newRoom: -25336))
		)
	)
)

(instance foCoinBox of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 404 252 432 241 446 248 446 281 420 292 403 280
					yourself:
				)
		)
	)
	
	(method (doVerb)
		(if (>= (proc64001_4) 5)
			(messager say: 1 1 2 0 0 -25336)
		else
			(curRoom setScript: soSendCannonballDown)
		)
	)
)

(instance poCannonball of Prop
	(properties
		view -23336
	)
)

(instance poTorin of Prop
	(properties
		x 553
		y 306
		view -23336
		loop 7
	)
)

(instance soSendCannonballDown of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 432 300 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 315 self)
			)
			(2
				(foCoinBox setTotalWidth: 1)
				(theGame handsOn:)
				(poCannonball
					loop: 0
					cel: 0
					posn: 376 90
					init:
					setCycle: End self
				)
			)
			(3 (= ticks 30))
			(4
				(poCannonball
					loop: 1
					cel: 0
					posn: 381 166
					setCycle: End self
				)
			)
			(5 (= ticks 60))
			(6
				(poCannonball
					loop: 2
					cel: 0
					posn: 166 216
					setCycle: End self
				)
			)
			(7 (= ticks 60))
			(8
				(poCannonball
					loop: 3
					cel: 0
					posn: 87 183
					setCycle: End self
				)
			)
			(9 (= ticks 60))
			(10
				(poCannonball
					loop: 4
					cel: 0
					posn: 186 92
					setCycle: End self
				)
			)
			(11 (= ticks 60))
			(12
				(poCannonball
					loop: 5
					cel: 0
					posn: 412 92
					setCycle: End self
				)
			)
			(13
				(if (> (ego x?) 460)
					(curRoom setScript: soNabBall)
				else
					(self cue:)
				)
			)
			(14 (= ticks 60))
			(15
				(poCannonball
					loop: 6
					cel: 0
					posn: 449 233
					setCycle: End self
				)
			)
			(16
				(poCannonball dispose:)
				(foCoinBox setVisibleRange: 1)
				(self dispose:)
			)
		)
	)
)

(instance soNabBall of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local0 0)
				(ego setScript: soGetInPlace)
				(= ticks 60)
			)
			(1 (= ticks 10))
			(2
				(if (not local0) (self changeState: (- state 1)))
				(poCannonball
					loop: 6
					cel: 0
					posn: 449 233
					setCycle: CT 21 1 self
				)
			)
			(3
				(poCannonball dispose:)
				(ego hide:)
				(poTorin cel: 0 init: setCycle: End self)
			)
			(4
				(poTorin dispose:)
				(if (not (proc64001_2))
					(MonoOut {no cannonballs left})
				else
					(ego get: (proc64001_2))
				)
				(ego posn: 556 310 oPanner: 1 -5436 4 show:)
				(foCoinBox setVisibleRange: 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soGetInPlace of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 484 312 self)
			)
			(1 (ego setHeading: 0 self))
			(2
				(= local0 1)
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
				(ego posn: 662 300 setMotion: MoveTo 600 300 self)
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
					posn: 672 300
					setMotion: MoveTo 610 300 self
				)
			)
			(1
				((ScriptID 64018 0) bSwing: 1)
				(self dispose:)
			)
		)
	)
)

(instance roCannonBall of TPRoom
	(properties
		picture -23336
	)
	
	(method (init)
		(super init: &rest)
		(music1 pageSize: -23336)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 409 313 629 314 628 295 544 294
					yourself:
				)
		)
		(foCoinBox init:)
		(foExitToIsland init:)
		(ego init: oPanner: nCurPosY: 75)
		((ScriptID 64018 0)
			posn: 610 300
			init:
			oPanner:
			nCurPosY: 75
		)
		(theGame handsOn:)
		(ego setScript: soWalkIn)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
