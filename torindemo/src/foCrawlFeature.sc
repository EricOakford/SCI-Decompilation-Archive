;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50200)
(include sci.sh)
(use Main)
(use TPRoom)
(use Script)
(use ExitFeature)
(use Plane)
(use Feature)
(use Motion)
(use Actor)

(public
	roAirDuct1 0
)

(local
	local0
	local1
	local2
	local3
	local4 =  100
	local5
	local6
)
(procedure (localproc_00e0 param1 param2 &tmp temp0)
	(if (> argc 1) (= temp0 param2) else (= temp0 0))
	(if (== param1 (LOOKUP_ERROR x?)) (return))
	(if (> param1 (LOOKUP_ERROR x?))
		(LOOKUP_ERROR cycleDir: -1)
	else
		(LOOKUP_ERROR cycleDir: 1)
	)
	(LOOKUP_ERROR setMotion: MoveTo param1 local0 temp0)
	((ScriptID 64018 0)
		setMotion: MoveTo (+ param1 local4) local0
	)
)

(instance foExit1L of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 4))
		(self setRect: 0 230 40 287)
	)
	
	(method (doVerb)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance foExit2L of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 4))
		(self setRect: 0 97 40 165)
	)
	
	(method (doVerb)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance foExit2R of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 3))
		(self setRect: 591 97 631 165)
	)
	
	(method (doVerb)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance foExit3L of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 4))
		(self setRect: 0 0 40 71)
	)
	
	(method (doVerb)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance foExit3R of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 3))
		(self setRect: 591 0 631 71)
	)
	
	(method (doVerb)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance oTorinCycle of Walk
	(properties)
)

(class foCrawlFeature of Feature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
	)
	
	(method (init)
		(super init: &rest)
		(self setRect: 0 0 632 316)
		(self nScrollMaxX: -1)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) evMOUSEBUTTON)
				(self onMe: event)
			)
			(localproc_00e0 (event x?))
			(event claimed: 1)
			(return 1)
		)
		(return 0)
	)
)

(instance foCrawlFeature1 of foCrawlFeature
	(properties)
)

(instance foCrawlFeature2 of foCrawlFeature
	(properties)
)

(instance foCrawlFeature3 of foCrawlFeature
	(properties)
)

(instance aoTorinCrawls of Actor
	(properties
		view -15335
		xStep 10
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: LOOKUP_ERROR)
		(self setPri: 20)
		((ScriptID 64018 0)
			posn: (+ (LOOKUP_ERROR x?) local4) local0
			init:
			oPanner:
			setPri: 20
			signal: (| ((ScriptID 64018 0) signal?) $4000)
			bSwing: 0
		)
	)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(= cycleSpeed (ego cycleSpeed?))
		(= moveSpeed (ego moveSpeed?))
		((ScriptID 64018 0) cycleSpeed: cycleSpeed)
		((ScriptID 64018 0) moveSpeed: moveSpeed)
		((ScriptID 64018 0) xStep: xStep)
		(if
			(and
				(== (curRoom plane?) LOOKUP_ERROR)
				(<= (- x local3) local2)
				(not local1)
			)
			(= local1 1)
			(self setMotion: 0)
			(self setCel: 2)
			(self setScript: LOOKUP_ERROR)
		)
		(if
			(and
				(<= x 322)
				(== (curRoom plane?) thePlane)
				(not local5)
			)
			(= local5 1)
			(= local4 -100)
			((ScriptID 64018 0) posn: (+ x local4) local0)
			(if mover (localproc_00e0 (mover x?) (mover caller?)))
		)
		(if
			(and
				(<= x 127)
				(== (curRoom plane?) LOOKUP_ERROR)
				(not local6)
			)
			(= local6 1)
			(= local4 100)
			((ScriptID 64018 0) posn: (+ x local4) local0)
			(if mover (localproc_00e0 (mover x?) (mover caller?)))
		)
	)
	
	(method (cantBeHere)
		(return 0)
	)
	
	(method (setHeading h)
		(= heading h)
	)
)

(instance soMenuBonk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 64018 0) setMotion: 0)
				((ScriptID 64000 0) show:)
				(theSound lThumbLoop: -15335 self)
			)
			(1
				((ScriptID 64000 2) hilite:)
				(theSound lThumbLoop: -15334 self)
			)
			(2
				(theSound lThumbLoop: -15333)
				((ScriptID 64000 0) hide:)
				(theGame handsOn:)
			)
		)
	)
)

(instance soCrawlInR1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR posn: 650 local0 init:)
				(localproc_00e0 540 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soCrawlOutL1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(localproc_00e0 -40 self)
			)
			(1
				(LOOKUP_ERROR dispose:)
				((ScriptID 64018 0) dispose:)
				(curRoom initThumb: LOOKUP_ERROR)
				(LOOKUP_ERROR
					posn: 660 local0
					init:
					setCycle: LOOKUP_ERROR
				)
				(localproc_00e0 600 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soCrawlOutL2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(localproc_00e0 -40 self)
			)
			(1
				(LOOKUP_ERROR dispose:)
				((ScriptID 64018 0) dispose:)
				(curRoom initThumb: LOOKUP_ERROR)
				(LOOKUP_ERROR
					posn: 660 local0
					init:
					setCycle: LOOKUP_ERROR
				)
				(localproc_00e0 600 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soCrawlOutR2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(localproc_00e0 660 self)
			)
			(1
				(LOOKUP_ERROR dispose:)
				((ScriptID 64018 0) dispose:)
				(curRoom arrowDown: LOOKUP_ERROR)
				(= local0 292)
				(LOOKUP_ERROR
					posn: -40 local0
					init:
					setCycle: LOOKUP_ERROR
				)
				(localproc_00e0 20 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soCrawlOutL3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(localproc_00e0 -40 self)
			)
			(1
				(LOOKUP_ERROR dispose:)
				((ScriptID 64018 0) dispose:)
				(curRoom newRoom: -15236)
			)
		)
	)
)

(instance soCrawlOutR3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(localproc_00e0 660 self)
			)
			(1
				(LOOKUP_ERROR dispose:)
				(curRoom arrowDown: LOOKUP_ERROR)
				(= local0 166)
				(LOOKUP_ERROR
					posn: -40 local0
					init:
					setCycle: LOOKUP_ERROR
				)
				(localproc_00e0 20 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance oDuctPlane2 of Plane
	(properties
		picture -15335
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
		(= local0 166)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
	)
)

(instance oDuctPlane3 of Plane
	(properties
		picture -15334
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
		(= local0 70)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
	)
)

(instance roAirDuct1 of TPRoom
	(properties
		picture -15336
	)
	
	(method (init)
		(super init: &rest)
		(= local2 ((ScriptID 64000 0) findData:))
		(LOOKUP_ERROR posn: 300 200 cel: 2 init:)
		(SetNowSeen LOOKUP_ERROR)
		(= local3 (- (LOOKUP_ERROR x?) (LOOKUP_ERROR nsLeft?)))
		(LOOKUP_ERROR cel: 0 dispose:)
		(theMusic pageSize: -15436)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(theGame handsOn:)
		(= local0 292)
		(curRoom setScript: LOOKUP_ERROR)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
		(= local1 0)
		(= local5 0)
		(= local6 0)
	)
)
