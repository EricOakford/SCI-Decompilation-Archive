;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20900)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use CueMe)
(use ExitFeature)
(use Talker)
(use PolyPath)
(use Polygon)
(use Motion)
(use Actor)
(use System)

(public
	roVedarCu 0
)

(instance foExit of ExitFeature
	(properties
		nsLeft 550
		nsTop 218
		nsRight 624
		nsBottom 312
		approachX 626
		approachY 280
		x 628
		y 283
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 7))
	)
	
	(method (doVerb)
		(ego oPlane: self self)
	)
	
	(method (cue)
		(curRoom newRoom: 20100)
	)
)

(instance poMan of Prop
	(properties
		noun 1
		x 334
		y 184
		view 20103
		loop 1
	)
	
	(method (init)
		(if ((ScriptID 64017 0) test: 49)
			(= loop 4)
			(= cel (self lastCel:))
			(= gToVederPillow LOOKUP_ERROR)
		else
			(= loop 1)
			(= cel 0)
			(= gToVederPillow LOOKUP_ERROR)
		)
		(super init: &rest)
		(if ((ScriptID 64017 0) test: 49)
			(self
				setVisibleRange: 33 1 36 40 37 34 39 27 32 24 30 31 29 25 48 28 26
			)
		else
			(self setCycle: End LOOKUP_ERROR)
		)
	)
	
	(method (dispose)
		(= gToVederPillow 0)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 27 32 24 30 31 29 25 48 28 26)
			(if ((ScriptID 64017 0) test: 49)
				(messager say: 1 31 0 0)
			else
				(messager say: 1 28 0 0)
			)
			(return)
		)
		(switch theVerb
			(1
				(ego setScript: LOOKUP_ERROR)
			)
			(37
				(ego setScript: LOOKUP_ERROR)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance coAddHotspots of CueMe
	(properties)
	
	(method (cue)
		(LOOKUP_ERROR
			setVisibleRange: 33 1 36 40 37 34 39 27 32 24 30 31 29 25 48 28 26
		)
	)
)

(instance soTalkToVeder of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego oCuee: LOOKUP_ERROR self)
			)
			(1
				((ScriptID 64017 0) set: 80)
				(switch global263
					(0 (= global263 1))
					(1 (= global263 2))
					(2 (= global263 3))
					(3 (= global263 4))
					(4 (= global263 5))
					(5 (= global263 6))
				)
				(messager say: 1 1 global263 0 self)
			)
			(2 (self dispose:))
		)
	)
)

(instance poTorinGivesPillow of Prop
	(properties
		x 416
		y 226
		view 20103
		loop 3
	)
)

(instance poManTakesPillow of Prop
	(properties
		x 334
		y 184
		view 20103
		loop 4
	)
)

(instance soTorinGivesPillow of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 416 226 self)
			)
			(1 (ego setHeading: 315 self))
			(2
				(theGame handsOff:)
				(ego hide:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
			)
			(3)
			(4
				(ego put: ((ScriptID 64001 0) get: 27))
				(ego get: ((ScriptID 64001 0) get: 21))
				((ScriptID 64017 0) set: 49)
				(= global263 7)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init:)
				(ego posn: 461 215 setLoop: 5 scrollTo: show:)
				(theGame handsOn:)
			)
		)
	)
)

(instance voVederBody of View
	(properties
		view 20103
		loop 5
	)
	
	(method (init)
		(self posn: (LOOKUP_ERROR x?) (LOOKUP_ERROR y?))
		(super init: &rest)
		(LOOKUP_ERROR hide:)
	)
	
	(method (dispose)
		(LOOKUP_ERROR show:)
		(super dispose: &rest)
	)
)

(instance toVeder of Talker
	(properties
		view 20103
		loop 6
	)
	
	(method (init)
		(= x (LOOKUP_ERROR x?))
		(= y (LOOKUP_ERROR y?))
		(= priority (LOOKUP_ERROR priority?))
		(= frame LOOKUP_ERROR)
		(super init: &rest)
	)
)

(instance voVederPillowBody of View
	(properties
		view 20103
		loop 7
	)
	
	(method (init)
		(self posn: (LOOKUP_ERROR x?) (LOOKUP_ERROR y?))
		(super init: &rest)
		(LOOKUP_ERROR hide:)
	)
	
	(method (dispose)
		(LOOKUP_ERROR show:)
		(super dispose: &rest)
	)
)

(instance toVederPillow of Talker
	(properties
		view 20103
		loop 8
	)
	
	(method (init)
		(= x (LOOKUP_ERROR x?))
		(= y (LOOKUP_ERROR y?))
		(= priority (LOOKUP_ERROR priority?))
		(= frame LOOKUP_ERROR)
		(super init: &rest)
	)
)

(instance roVedarCu of TPRoom
	(properties
		picture 20900
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: 20900)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						412
						211
						353
						226
						371
						233
						401
						226
						464
						244
						486
						237
						555
						258
						538
						290
						586
						324
						628
						312
						633
						264
						611
						248
					yourself:
				)
		)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(ego
			posn: 626 280
			init:
			oPanner: 0 -5436 7
			setMotion: MoveTo 543 246 (ScriptID 64020 0)
		)
		(theGame handsOff:)
	)
	
	(method (setWander)
		(return LOOKUP_ERROR)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 0) get: 27))
	)
)
