;;; Sierra Script 1.0 - (do not remove this comment)
(script# 30100)
(include sci.sh)
(use Main)
(use ScrollExit)
(use TPRoom)
(use TPScript)
(use TPSound)
(use CueMe)
(use n64896)
(use ExitFeature)
(use Plane)
(use Talker)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	roPergolaOpening 0
)

(local
	local0 =  64
	local1 =  1
	local2 =  1
	local3 =  128
	local4
)
(instance voBackground of View
	(properties
		y 316
		view 30105
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 0)
	)
)

(instance oZoomer of Code
	(properties)
	
	(method (doit)
		(if (!= local0 local3)
			(= local0 (+ local0 (* local1 local2)))
			(LOOKUP_ERROR scaleX: local0 scaleY: local0)
		else
			(LOOKUP_ERROR cue:)
		)
	)
)

(instance foLowerShard of ExitFeature
	(properties
		noun 2
		nsTop 191
		nsRight 80
		nsBottom 312
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 13)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: noun 0 0 0))
			(13
				(messager say: noun theVerb 0 0 self)
			)
		)
	)
	
	(method (cue)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance foUpperShard of Feature
	(properties
		noun 1
		nsLeft 56
		nsRight 132
		nsBottom 75
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(messager say: noun 0 0 0)
	)
)

(instance foThunderstorm of Feature
	(properties
		noun 3
		nsLeft 356
		nsRight 630
		nsBottom 132
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(messager say: noun 0 0 0)
	)
)

(instance oAmbient of TPSound
	(properties)
)

(instance oTorinFaceDown of Narrator
	(properties)
)

(instance soEntrance of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR vThumbView: 30199)
				(= seconds 3)
			)
			(1 (theDoits add: LOOKUP_ERROR))
			(2
				(theDoits delete: LOOKUP_ERROR)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init: setCycle: End self)
				(theSound lThumbLoop: 30101)
			)
			(3
				(= gToTorinPullsOutMeat LOOKUP_ERROR)
				(messager say: 0 0 0 1 self)
			)
			(4
				(= gToTorinPullsOutMeat 0)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init: setCycle: End self)
			)
			(5
				(LOOKUP_ERROR dispose:)
				(ego posn: 148 252 init: oPanner: lCheck: LOOKUP_ERROR)
				(messager sayRange: 0 0 0 2 3 self)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poTorinEntrance of Prop
	(properties
		x 148
		y 252
		priority 100
		view 30100
	)
)

(instance poTorinLooksAround of Prop
	(properties
		x 148
		y 252
		priority 100
		view 30100
		loop 1
	)
)

(instance poTorinFallsThrough of Prop
	(properties
		x 148
		y 252
		priority 100
		view 30100
		loop 2
	)
)

(instance poLeavesFlyUp of Prop
	(properties
		x 148
		y 252
		priority 100
		view 30100
		loop 3
	)
)

(instance oTreetopPlane of Plane
	(properties
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
	)
)

(instance oTreebasePlane of Plane
	(properties
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
		(LOOKUP_ERROR init: self setScale:)
		(LOOKUP_ERROR
			setCel: 0
			init: self
			setCycle: End LOOKUP_ERROR
			setScale:
		)
		(LOOKUP_ERROR setScale:)
		(= local0 128)
		(= local3 (MulDiv 128 133 100))
		(theDoits add: LOOKUP_ERROR)
	)
)

(instance voTorinUnconscious of View
	(properties
		x 445
		y 180
		maxScale 256
		view 30104
	)
)

(instance poPergolians of Prop
	(properties
		x 445
		y 180
		maxScale 256
		view 30104
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(= local4 1)
		(theGame handsOff:)
	)
)

(instance poPergoliansKillTorin of Prop
	(properties
		x 445
		y 180
		maxScale 256
		view 30104
		loop 2
	)
)

(instance voSkeleton of Prop
	(properties
		x 445
		y 180
		maxScale 256
		priority 170
		view 30104
		loop 3
	)
)

(instance coTestForClick of CueMe
	(properties)
	
	(method (cue)
		(if local4
			(proc64896_1 1 10 LOOKUP_ERROR)
		else
			(curRoom setScript: LOOKUP_ERROR)
		)
	)
)

(instance coNextRoom of CueMe
	(properties)
	
	(method (cue)
		(curRoom newRoom: 30200)
	)
)

(instance soSkeleton of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR setCel: 0 init: setCycle: CT 12 1 self)
			)
			(1
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR setCycle: End self)
			)
			(2
				((ScriptID 64019 0) show: 0 42 1 30200)
				(LOOKUP_ERROR dispose: init:)
				(self dispose:)
			)
		)
	)
)

(instance oBaseZoomer of Code
	(properties)
	
	(method (doit)
		(if (!= local0 local3)
			(= local0 (+ local0 (* local1 local2)))
			(LOOKUP_ERROR scaleX: local0 scaleY: local0)
			(LOOKUP_ERROR scaleX: local0 scaleY: local0)
			(LOOKUP_ERROR scaleX: local0 scaleY: local0)
		else
			(theDoits delete: self)
			(self dispose:)
		)
	)
)

(instance roPergolaOpening of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		(= plane (LOOKUP_ERROR init: yourself:))
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR setScale: scaleX: 64 scaleY: 64)
		(curRoom setScript: LOOKUP_ERROR)
		(theMusic pageSize: 30100)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)

(instance oTreeScrollPlane of TorScrollPlane
	(properties
		priority 20
	)
	
	(method (nSeq)
		(AddPicAt self 30100 0 0)
		(AddPicAt self 30101 0 316)
		(AddPicAt self 30102 0 632)
		(AddPicAt self 30103 0 948)
		(AddPicAt self 30104 0 1264)
	)
)

(instance oTreeWalkHandler of Code
	(properties)
	
	(method (doit)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance oFallingMusic of TPSound
	(properties)
)

(instance soTorinFallsThrough of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego hide:)
				(LOOKUP_ERROR init: setCycle: End self)
				(theMusic pageSize: 0)
				(LOOKUP_ERROR lThumbLoop: 30198)
				(theSound lThumbLoop: 30102 LOOKUP_ERROR)
				(messager say: 0 0 0 4)
			)
			(1
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init: setCycle: End self)
			)
			(2
				(LOOKUP_ERROR dispose:)
				(curRoom
					plane:
						(LOOKUP_ERROR
							oBoogleFeatures: 0
							oExtraPlanes: 0
							init: (thePlane findData:) 1580
							yourself:
						)
				)
				(LOOKUP_ERROR sitNSpin: 0 1264 self 3)
			)
			(3
				(LOOKUP_ERROR dispose:)
				(curRoom plane: (LOOKUP_ERROR init: yourself:))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance coMoreFallingSFX of CueMe
	(properties)
	
	(method (cue)
		(theSound lThumbLoop: 30103 LOOKUP_ERROR)
	)
)

(instance coUgh of CueMe
	(properties)
	
	(method (cue)
		(messager say: 0 0 0 5 LOOKUP_ERROR)
	)
)

(instance coCaptured of CueMe
	(properties)
	
	(method (cue)
		(messager say: 0 0 1 0 0 30200)
	)
)
