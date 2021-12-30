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
		(voInsetFrame y: 0 init: modalPlaneNSpeed)
		(self setSize:)
		(= temp1 (Str newWith: 400 {Set Speed}))
		(self setTitle: (KString 8 (temp1 data?)))
		(temp1 dispose:)
		(poWalker init: modalPlaneNSpeed setPri: 100)
		(voClose init: (self nSpeed:))
		(voFastLeft init: (self nSpeed:))
		(voStepLeft init: (self nSpeed:))
		(voFastRight init: (self nSpeed:))
		(voStepRight init: (self nSpeed:))
		(foMove init: self)
		(foMove nUserRange: 28)
	)
)

(instance voClose of PushButton
	(properties
		x 6
		y 7
		view -5530
		loop 4
	)
	
	(method (oVerbs &tmp poWalkerCycleSpeed)
		(= poWalkerCycleSpeed (poWalker cycleSpeed?))
		(theGame oCantBeHereHandler: poWalkerCycleSpeed)
		(ego setSpeed: poWalkerCycleSpeed)
		(oInset dispose:)
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
		(poWalker cycleSpeed: 10)
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
		(poWalker
			cycleSpeed: (Min 40 (+ 1 (poWalker cycleSpeed?)))
		)
		(= local0 (+ gameTime 20))
	)
	
	(method (addSelfToCursorList)
		(if (> gameTime local0)
			(poWalker
				cycleSpeed: (Min 40 (+ 1 (poWalker cycleSpeed?)))
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
		(poWalker
			cycleSpeed: (Max (- (poWalker cycleSpeed?) 1) 0)
		)
		(= local0 (+ gameTime 20))
	)
	
	(method (addSelfToCursorList)
		(if (> gameTime local0)
			(poWalker
				cycleSpeed: (Max (- (poWalker cycleSpeed?) 1) 0)
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
		(poWalker cycleSpeed: 0)
	)
)
