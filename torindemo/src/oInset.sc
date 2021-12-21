;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64015)
(include sci.sh)
(use Main)
(use ModalPlane)
(use Button)
(use String)
(use Motion)
(use Actor)
(use System)

(public
	oInset 0
)

(local
	local0
)
(instance voInsetFrame of View
	(properties
		view -5530
		cel 1
	)
)

(instance poWalker of Prop
	(properties
		x 120
		y 216
		view -5436
	)
	
	(method (init)
		(super init: &rest)
		(= cycleSpeed (theGame oCantBeHereHandler?))
		(self setCycle: Fwd)
	)
)

(instance foMove of MoveFeature
	(properties)
)

(instance oInset of ModalPlane
	(properties
		left 180
		top 48
	)
	
	(method (init &tmp modalPlaneNSpeed temp1)
		(super init: &rest)
		(self moveTo: left top)
		(= modalPlaneNSpeed (self nSpeed:))
		(LOOKUP_ERROR y: 0 init: modalPlaneNSpeed)
		(self setSize:)
		(= temp1 (Str newWith: 400 LOOKUP_ERROR))
		(self setTitle: (KString 8 (temp1 data?)))
		(temp1 dispose:)
		(LOOKUP_ERROR init: modalPlaneNSpeed setPri: 100)
		(LOOKUP_ERROR init: (self nSpeed:))
		(LOOKUP_ERROR init: (self nSpeed:))
		(LOOKUP_ERROR init: (self nSpeed:))
		(LOOKUP_ERROR init: (self nSpeed:))
		(LOOKUP_ERROR init: (self nSpeed:))
		(LOOKUP_ERROR init: self)
		(LOOKUP_ERROR nUserRange: 28)
	)
)

(instance voClose of PushButton
	(properties
		x 6
		y 7
		view -5530
		loop 4
	)
	
	(method (oVerbs &tmp lOOKUP_ERRORCycleSpeed)
		(= lOOKUP_ERRORCycleSpeed (LOOKUP_ERROR cycleSpeed?))
		(theGame oCantBeHereHandler: lOOKUP_ERRORCycleSpeed)
		(ego setSpeed: lOOKUP_ERRORCycleSpeed)
		(LOOKUP_ERROR dispose:)
		(DisposeScript -1521)
	)
)

(instance voFastLeft of PushButton
	(properties
		x 42
		y 256
		view -5530
		loop 5
	)
	
	(method (oVerbs)
		(LOOKUP_ERROR cycleSpeed: 10)
	)
)

(instance voStepLeft of PushButton
	(properties
		x 76
		y 256
		view -5530
		loop 6
	)
	
	(method (oMyHandler)
		(LOOKUP_ERROR
			cycleSpeed: (Min 40 (+ 1 (LOOKUP_ERROR cycleSpeed?)))
		)
		(= local0 (+ gameTime 20))
	)
	
	(method (addSelfToCursorList)
		(if (> gameTime local0)
			(LOOKUP_ERROR
				cycleSpeed: (Min 40 (+ 1 (LOOKUP_ERROR cycleSpeed?)))
			)
			(= local0 (+ gameTime 5))
		)
	)
)

(instance voStepRight of PushButton
	(properties
		x 144
		y 256
		view -5530
		loop 8
	)
	
	(method (oMyHandler)
		(LOOKUP_ERROR
			cycleSpeed: (Max (- (LOOKUP_ERROR cycleSpeed?) 1) 0)
		)
		(= local0 (+ gameTime 20))
	)
	
	(method (addSelfToCursorList)
		(if (> gameTime local0)
			(LOOKUP_ERROR
				cycleSpeed: (Max (- (LOOKUP_ERROR cycleSpeed?) 1) 0)
			)
			(= local0 (+ gameTime 5))
		)
	)
)

(instance voFastRight of PushButton
	(properties
		x 178
		y 256
		view -5530
		loop 9
	)
	
	(method (oVerbs)
		(LOOKUP_ERROR cycleSpeed: 0)
	)
)
