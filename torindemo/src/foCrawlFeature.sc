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
	(if (== param1 (aoTorinCrawls x?)) (return))
	(if (> param1 (aoTorinCrawls x?))
		(oTorinCycle cycleDir: -1)
	else
		(oTorinCycle cycleDir: 1)
	)
	(aoTorinCrawls setMotion: MoveTo param1 local0 temp0)
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
		(curRoom setScript: soCrawlOutL1)
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
		(curRoom setScript: soCrawlOutL2)
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
		(curRoom setScript: soCrawlOutR2)
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
		(curRoom setScript: soCrawlOutL3)
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
		(curRoom setScript: soCrawlOutR3)
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
		(self setCycle: oTorinCycle)
		(self setPri: 20)
		((ScriptID 64018 0)
			posn: (+ (aoTorinCrawls x?) local4) local0
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
				(== (curRoom plane?) oDuctPlane3)
				(<= (- x local3) local2)
				(not local1)
			)
			(= local1 1)
			(self setMotion: 0)
			(self setCel: 2)
			(self setScript: soMenuBonk)
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
				(== (curRoom plane?) oDuctPlane2)
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
				(sound1 lThumbLoop: -15335 self)
			)
			(1
				((ScriptID 64000 2) hilite:)
				(sound1 lThumbLoop: -15334 self)
			)
			(2
				(sound1 lThumbLoop: -15333)
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
				(aoTorinCrawls posn: 650 local0 init:)
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
				(aoTorinCrawls dispose:)
				((ScriptID 64018 0) dispose:)
				(curRoom initThumb: oDuctPlane2)
				(aoTorinCrawls
					posn: 660 local0
					init:
					setCycle: oTorinCycle
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
				(aoTorinCrawls dispose:)
				((ScriptID 64018 0) dispose:)
				(curRoom initThumb: oDuctPlane3)
				(aoTorinCrawls
					posn: 660 local0
					init:
					setCycle: oTorinCycle
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
				(aoTorinCrawls dispose:)
				((ScriptID 64018 0) dispose:)
				(curRoom arrowDown: oDuctPlane2)
				(= local0 292)
				(aoTorinCrawls
					posn: -40 local0
					init:
					setCycle: oTorinCycle
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
				(aoTorinCrawls dispose:)
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
				(aoTorinCrawls dispose:)
				(curRoom arrowDown: oDuctPlane3)
				(= local0 166)
				(aoTorinCrawls
					posn: -40 local0
					init:
					setCycle: oTorinCycle
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
		(foCrawlFeature2 init:)
		(foExit2L init:)
		(foExit2R init:)
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
		(foCrawlFeature3 init:)
		(foExit3L init:)
		(foExit3R init:)
	)
)

(instance roAirDuct1 of TPRoom
	(properties
		picture -15336
	)
	
	(method (init)
		(super init: &rest)
		(= local2 ((ScriptID 64000 0) findData:))
		(aoTorinCrawls posn: 300 200 cel: 2 init:)
		(SetNowSeen aoTorinCrawls)
		(= local3
			(- (aoTorinCrawls x?) (aoTorinCrawls nsLeft?))
		)
		(aoTorinCrawls cel: 0 dispose:)
		(music1 pageSize: -15436)
		(foExit1L init:)
		(foCrawlFeature1 init:)
		(theGame handsOn:)
		(= local0 292)
		(curRoom setScript: soCrawlInR1)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
		(= local1 0)
		(= local5 0)
		(= local6 0)
	)
)
