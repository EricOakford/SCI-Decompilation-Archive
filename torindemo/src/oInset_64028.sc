;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64028)
(include sci.sh)
(use Main)
(use ScrollBar)
(use TPSound)
(use ModalPlane)
(use Button)
(use String)
(use Actor)

(public
	oInset 0
)

(instance voInsetFrame of View
	(properties
		fixPriority 1
		view -5530
		cel 1
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
		(LOOKUP_ERROR
			oScrollPlane: 100
			init: (LOOKUP_ERROR nSpeed:)
			posn: 20 40
			solvedThrough: (- 100 gCurVolume)
		)
		(LOOKUP_ERROR
			oScrollPlane: 100
			init: (LOOKUP_ERROR nSpeed:)
			posn: 80 40
			solvedThrough: (- 100 gSaveThis)
		)
		(LOOKUP_ERROR
			oScrollPlane: 100
			init: (LOOKUP_ERROR nSpeed:)
			posn: 140 40
			solvedThrough: (- 100 global229)
		)
		(= temp1 (Str newWith: 400 LOOKUP_ERROR))
		(self setTitle: (KString 8 (temp1 data?)))
		(temp1 dispose:)
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
	
	(method (oVerbs &tmp temp0)
		(LOOKUP_ERROR dispose:)
		(LOOKUP_ERROR dispose:)
		(LOOKUP_ERROR dispose:)
		(LOOKUP_ERROR dispose:)
		(DisposeScript -1508)
	)
)

(instance oScrollBar1 of ScrollBar
	(properties
		priority 16
		fixPriority 1
		view -5532
		loop 11
		oEScrollExit -5532
		oWScrollExit 14
		oVertHandle -5532
		oMyScrollPlane 13
		nHighBound -5532
		bXAxis 12
	)
	
	(method (setPenalty)
		(proc64031_0 (- 100 (self getPenalty:)))
	)
)

(instance oScrollBar2 of ScrollBar
	(properties
		priority 16
		fixPriority 1
		view -5532
		loop 11
		oEScrollExit -5532
		oWScrollExit 14
		oVertHandle -5532
		oMyScrollPlane 13
		nHighBound -5532
		bXAxis 12
	)
	
	(method (setPenalty)
		(proc64031_1 (- 100 (self getPenalty:)))
	)
)

(instance oScrollBar3 of ScrollBar
	(properties
		priority 16
		fixPriority 1
		view -5532
		loop 11
		oEScrollExit -5532
		oWScrollExit 14
		oVertHandle -5532
		oMyScrollPlane 13
		nHighBound -5532
		bXAxis 12
	)
	
	(method (setPenalty)
		(proc64031_2 (- 100 (self getPenalty:)))
	)
)
