;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64028)
(include sci.sh)
(use Main)
(use oMenuPopupPlane)
(use ScrollBar)
(use TPSound)
(use TiledBitmap)
(use CueMe)
(use ModalPlane)
(use PushButton)
(use GenDialog)
(use Actor)

(public
	oInset 0
)

(local
	theGOFileReadWord_4 =  -1
	theGOFileReadWord_3 =  -1
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
	(properties)
	
	(method (init &tmp modalPlaneGetMainCast newTiledViewNOffsetX newTiledViewNOffsetY temp3 temp4 temp5 temp6 newTiledView newTextItem newTiledViewNHeight newTiledViewNWidth temp11 temp12 newTextItem_4 newTextItem_3 newTextItem_2)
		(super init: &rest)
		(= modalPlaneGetMainCast (self getMainCast:))
		(= temp5 (CelWide -5520 0 0))
		(= temp6 (CelHigh -5520 1 0))
		(= temp3 (+ temp5 10))
		(oClose init: modalPlaneGetMainCast)
		(oReset init: modalPlaneGetMainCast)
		(= temp4 (+ (* 3 temp6) (oReset nHeight?) 25))
		((= newTiledView (TiledView new:))
			view: -5517
			init: temp3 temp4 0 1 self
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
			text: (MakeMessageText 0 0 6 1 14)
			setPri: 50
			init: self
		)
		(= newTiledViewNHeight
			(+ newTiledViewNHeight (newTextItem nHeight?))
		)
		(if (== gLeft -1)
			(= gLeft
				(+
					(thePlane left:)
					(/ (- (thePlane getWidth:) newTiledViewNWidth) 2)
				)
			)
			(= gTop
				(+
					(thePlane top?)
					(/ (- (thePlane getHeight:) newTiledViewNHeight) 2)
				)
			)
		)
		(self
			setRect:
				gLeft
				gTop
				(- (+ gLeft newTiledViewNWidth) 1)
				(- (+ gTop newTiledViewNHeight) 1)
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
		(= newTextItem_2 (TextItem new:))
		(newTextItem_2
			font: global268
			nLeading: global269
			maxWidth: temp5
			nMinWidth: temp5
			fore: global274
			back: 255
			skip: 255
			text: (MakeMessageText 0 0 6 3 14)
			border: 0
			bTileBorder: 0
			setPri: 50
			init: self
			posn: (+ newTiledViewNOffsetX 5) (+ newTiledViewNOffsetY 5)
		)
		(oMusicScroll
			maxPosn: 100
			init: modalPlaneGetMainCast
			posn: (+ newTiledViewNOffsetX 5) (+ newTiledViewNOffsetY 25)
			setPos: gOFileReadWord_3
		)
		(= newTextItem_3 (TextItem new:))
		(newTextItem_3
			font: global268
			nLeading: global269
			maxWidth: temp5
			nMinWidth: temp5
			fore: global274
			back: 255
			skip: 255
			text: (MakeMessageText 0 0 6 4 14)
			border: 0
			bTileBorder: 0
			setPri: 50
			init: self
			posn: (+ newTiledViewNOffsetX 5) (+ newTiledViewNOffsetY temp6 10)
		)
		(oSFXScroll
			maxPosn: 100
			init: modalPlaneGetMainCast
			posn: (+ newTiledViewNOffsetX 5) (+ newTiledViewNOffsetY temp6 30)
			setPos: gOFileReadWord_4
		)
		(= newTextItem_4 (TextItem new:))
		(newTextItem_4
			font: global268
			nLeading: global269
			maxWidth: temp5
			nMinWidth: temp5
			fore: global274
			back: 255
			skip: 255
			text: (MakeMessageText 0 0 6 2 14)
			border: 0
			bTileBorder: 0
			setPri: 50
			init: self
			posn: (+ newTiledViewNOffsetX 5) (+ newTiledViewNOffsetY 15 temp6 temp6)
		)
		(oAudioScroll
			maxPosn: 100
			init: modalPlaneGetMainCast
			posn: (+ newTiledViewNOffsetX 5) (+ newTiledViewNOffsetY 35 temp6 temp6)
			setPos: gOFileReadWord_5
		)
		(= temp11
			(/ (- newTiledViewNWidth (oReset nWidth?)) 2)
		)
		(= temp12 (+ newTiledViewNOffsetY 20 (* 3 temp6)))
		(oReset posn: temp11 temp12)
		(UpdateScreenItem newTiledView)
		(UpdateScreenItem newTextItem_4)
		(UpdateScreenItem newTextItem_3)
		(UpdateScreenItem newTextItem_2)
		(UpdateScreenItem oAudioScroll)
		(UpdateScreenItem oSFXScroll)
		(UpdateScreenItem oMusicScroll)
		(UpdateScreenItem (oAudioScroll oThumb?))
		(UpdateScreenItem (oSFXScroll oThumb?))
		(UpdateScreenItem (oMusicScroll oThumb?))
		(UpdateScreenItem oReset)
		(foMove init: self)
		(foMove makeTopBorder: (newTextItem nHeight?))
	)
	
	(method (dispose)
		(oVolumeSound dispose:)
		(if (!= theGOFileReadWord_3 -1)
			(proc64031_1 theGOFileReadWord_4)
			(proc64031_0 theGOFileReadWord_3)
			(= theGOFileReadWord_3 -1)
			(= theGOFileReadWord_4 -1)
		)
		(= gLeft left)
		(= gTop top)
		(WritePrefs)
		(super dispose: &rest)
		(DisposeScript -1508)
	)
)

(instance oReset of MessageButton
	(properties
		case 5
		font 2510
		vTileOff -5523
		vTileOn -5522
		nLeading 3
		module 14
	)
	
	(method (init)
		(= fore gFore)
		(= back gBack)
		(super init: &rest)
	)
	
	(method (doSelect &tmp temp0)
		(oMusicScroll setPos: 65 scrolled:)
		(oSFXScroll setPos: 60 scrolled:)
		(oAudioScroll setPos: 100 scrolled:)
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
)

(instance oMusicScroll of ScrollBar
	(properties
		priority 100
		fixPriority 1
		view -5520
		type $0001
		vThumbView -5520
		lThumbLoop 2
	)
	
	(method (scrolled)
		(proc64031_0 (self getPos:))
	)
)

(instance oSFXScroll of ScrollBar
	(properties
		priority 100
		fixPriority 1
		view -5520
		type $0001
		vThumbView -5520
		lThumbLoop 3
	)
	
	(method (scrolled)
		(proc64031_1 (self getPos:))
	)
	
	(method (stopThumb)
	)
)

(instance oAudioScroll of ScrollBar
	(properties
		priority 100
		fixPriority 1
		view -5520
		type $0001
		vThumbView -5520
		lThumbLoop 1
	)
	
	(method (scrolled)
		(proc64031_2 (self getPos:))
	)
	
	(method (stopThumb)
		(if (== theGOFileReadWord_4 -1)
			(= theGOFileReadWord_4 gOFileReadWord_4)
			(= theGOFileReadWord_3 gOFileReadWord_3)
			(proc64031_0 (MulDiv 35 gOFileReadWord_3 100))
			(proc64031_1 (MulDiv 35 gOFileReadWord_4 100))
		)
	)
)

(instance coResetVolumes of CueMe
	(properties)
	
	(method (cue)
		(if (!= theGOFileReadWord_3 -1)
			(proc64031_1 theGOFileReadWord_4)
			(proc64031_0 theGOFileReadWord_3)
			(= theGOFileReadWord_3 -1)
			(= theGOFileReadWord_4 -1)
		)
	)
)

(instance oVolumeSound of TPSound
	(properties)
)
