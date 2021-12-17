;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64015)
(include sci.sh)
(use Main)
(use oMenuPopupPlane)
(use TiledBitmap)
(use ModalPlane)
(use PushButton)
(use GenDialog)
(use Motion)
(use Actor)
(use System)

(public
	oInset 0
)

(local
	local0
)
(instance poWalker of Prop
	(properties
		x 120
		y 216
		view -5436
	)
	
	(method (init)
		(super init: &rest)
		(= cycleSpeed (theGame nGameSpeed?))
		(self setCycle: Fwd)
	)
)

(instance foMove of MoveFeature
	(properties)
)

(instance oInset of ModalPlane
	(properties)
	
	(method (init &tmp [temp0 2] temp2 temp3 temp4 temp5 temp6 temp7 temp8 newTiledViewNWidth newTiledViewNHeight newTiledView newTextItem newTiledViewNOffsetX newTiledViewNOffsetY temp15)
		(super init: &rest)
		(poWalker init: self setPri: 100)
		(oClose init: self)
		(oAllLeft init: self)
		(oStepLeft init: self)
		(oAllRight init: self)
		(oStepRight init: self)
		(= temp2
			(CelHigh
				(poWalker view?)
				(poWalker loop?)
				(poWalker cel?)
			)
		)
		(= temp15
			(CelHigh
				(oAllLeft view?)
				(oAllLeft loop?)
				(oAllLeft cel?)
			)
		)
		(= temp3
			(CelWide
				(oAllLeft view?)
				(oAllLeft loop?)
				(oAllLeft cel?)
			)
		)
		(= temp4
			(CelWide
				(oStepLeft view?)
				(oStepLeft loop?)
				(oStepLeft cel?)
			)
		)
		(= temp5
			(CelWide
				(oAllRight view?)
				(oAllRight loop?)
				(oAllRight cel?)
			)
		)
		(= temp6
			(CelWide
				(oStepRight view?)
				(oStepRight loop?)
				(oStepRight cel?)
			)
		)
		(= temp7 (+ temp3 temp4 temp5 temp6 25))
		(= temp8 (+ temp15 temp2 15))
		((= newTiledView (TiledView new:))
			view: -5517
			init: temp7 temp8 0 1 self
		)
		(= newTiledViewNWidth (newTiledView nWidth?))
		(= newTiledViewNHeight (newTiledView nHeight?))
		(= newTextItem (TextItem new:))
		(newTextItem
			font: global268
			nLeading: global269
			maxWidth: (- newTiledViewNWidth 18)
			nMinWidth: (- newTiledViewNWidth 12)
			fore: global274
			back: 255
			skip: 255
			border: 5
			bTileBorder: 1
			vTile: -5516
			text: (MakeMessageText 0 0 8 1 14)
			setPri: 50
			init: self
		)
		(= newTiledViewNHeight
			(+ newTiledViewNHeight (newTextItem nHeight?))
		)
		(if (== gPlaneLeft -1)
			(= gPlaneLeft
				(+
					(thePlane left:)
					(/ (- (thePlane getWidth:) newTiledViewNWidth) 2)
				)
			)
			(= gPlaneTop
				(+
					(thePlane top?)
					(/ (- (thePlane getHeight:) newTiledViewNHeight) 2)
				)
			)
		)
		(self
			setRect:
				gPlaneLeft
				gPlaneTop
				(- (+ gPlaneLeft newTiledViewNWidth) 1)
				(- (+ gPlaneTop newTiledViewNHeight) 1)
		)
		(UpdatePlane self)
		(= newTiledViewNOffsetX (newTiledView nOffsetX?))
		(= newTiledViewNOffsetY (newTiledView nOffsetY?))
		(newTiledView
			posn: (newTiledView x?) (+ (newTiledView y?) (newTextItem nHeight?))
		)
		(= newTiledViewNOffsetY
			(+ newTiledViewNOffsetY (newTextItem nHeight?))
		)
		(poWalker
			posn:
				(+ newTiledViewNOffsetX (/ temp7 2))
				(+ newTiledViewNOffsetY -3 temp2)
		)
		(UpdateScreenItem poWalker)
		(oAllLeft
			posn: (+ newTiledViewNOffsetX 5) (+ newTiledViewNOffsetY 10 temp2)
		)
		(UpdateScreenItem oAllLeft)
		(oStepLeft
			posn: (+ (oAllLeft x?) temp3 5) (oAllLeft y?)
		)
		(UpdateScreenItem oStepLeft)
		(oStepRight
			posn: (+ (oStepLeft x?) temp4 5) (oAllLeft y?)
		)
		(UpdateScreenItem oStepRight)
		(oAllRight
			posn: (+ (oStepRight x?) temp6 5) (oAllLeft y?)
		)
		(UpdateScreenItem oAllRight)
		(UpdateScreenItem newTiledView)
		(foMove init: self)
		(foMove makeTopBorder: (newTextItem nHeight?))
	)
)

(instance oClose of DismissButton
	(properties
		x 10
		y 10
		priority 100
		fixPriority 1
		view -5519
		loop 4
		bDefault 1
	)
	
	(method (doSelect &tmp poWalkerCycleSpeed)
		(= poWalkerCycleSpeed (poWalker cycleSpeed?))
		(theGame nGameSpeed: poWalkerCycleSpeed)
		(ego setSpeed: poWalkerCycleSpeed)
		(= gPlaneLeft (plane left:))
		(= gPlaneTop (plane top?))
		(WritePrefs)
		(oInset dispose:)
		(DisposeScript -1521)
	)
)

(instance oAllLeft of PushButton
	(properties
		view -5519
		loop 2
	)
	
	(method (doSelect)
		(poWalker cycleSpeed: 10)
	)
)

(instance oStepLeft of PushButton
	(properties
		view -5519
	)
	
	(method (doClick)
		(poWalker
			cycleSpeed: (Min 10 (+ 1 (poWalker cycleSpeed?)))
		)
		(= local0 (+ gameTime 20))
	)
	
	(method (doHeld)
		(if (> gameTime local0)
			(poWalker
				cycleSpeed: (Min 10 (+ 1 (poWalker cycleSpeed?)))
			)
			(= local0 (+ gameTime 5))
		)
	)
)

(instance oStepRight of PushButton
	(properties
		view -5519
		loop 1
	)
	
	(method (doClick)
		(poWalker
			cycleSpeed: (Max (- (poWalker cycleSpeed?) 1) 1)
		)
		(= local0 (+ gameTime 20))
	)
	
	(method (doHeld)
		(if (> gameTime local0)
			(poWalker
				cycleSpeed: (Max (- (poWalker cycleSpeed?) 1) 1)
			)
			(= local0 (+ gameTime 5))
		)
	)
)

(instance oAllRight of PushButton
	(properties
		view -5519
		loop 3
	)
	
	(method (doSelect)
		(poWalker cycleSpeed: 1)
	)
)
