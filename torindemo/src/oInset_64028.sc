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
		(voInsetFrame y: 0 init: modalPlaneNSpeed)
		(self setSize:)
		(oScrollBar1
			oScrollPlane: 100
			init: (oInset nSpeed:)
			posn: 20 40
			solvedThrough: (- 100 gCurVolume)
		)
		(oScrollBar2
			oScrollPlane: 100
			init: (oInset nSpeed:)
			posn: 80 40
			solvedThrough: (- 100 gSaveThis)
		)
		(oScrollBar3
			oScrollPlane: 100
			init: (oInset nSpeed:)
			posn: 140 40
			solvedThrough: (- 100 global229)
		)
		(= temp1
			(Str newWith: 400 {Music, Sound FX, Dialog Volume})
		)
		(self setTitle: (KString 8 (temp1 data?)))
		(temp1 dispose:)
		(voClose init: (self nSpeed:))
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
	
	(method (oVerbs &tmp temp0)
		(oScrollBar1 dispose:)
		(oScrollBar2 dispose:)
		(oScrollBar3 dispose:)
		(oInset dispose:)
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
