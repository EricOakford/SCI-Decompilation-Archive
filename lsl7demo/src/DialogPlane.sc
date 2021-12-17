;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64033)
(include sci.sh)
(use Main)
(use TiledBitmap)
(use ModalPlane)
(use PushButton)
(use GenDialog)
(use String)
(use Actor)
(use System)

(public
	proc64033_0 0
	proc64033_1 1
	proc64033_2 2
	proc64033_3 3
	proc64033_4 4
	proc64033_5 5
	proc64033_6 6
	proc64033_7 7
)

(local
	newEditItem
	newEditItemGetText
)
(procedure (proc64033_0 param1 param2 param3 param4 param5 param6 &tmp temp0 temp1 temp2 temp3 newTextItemNWidth newTextItemNHeight newDismissTextButtonNWidth newDismissTextButtonNHeight temp8 temp9 newDialogPlane newTiledView newDismissTextButton newTextItem_2 newTiledViewNWidth newTiledViewNHeight newTiledViewNOffsetX newTiledViewNOffsetY temp18 temp19 newTextItem temp21)
	(if (or (< argc 2) (not param1) (not param2))
		(if param1 (proc64896_7 param1))
		(if param2 (proc64896_7 param2))
		(PrintDebug {illegal call of TextDialog. gendialg.sc DJM})
		(return)
	)
	(if (< argc 3) (= temp0 0) else (= temp0 param3))
	(if (< argc 4) (= temp2 -2) else (= temp2 param4))
	(if (< argc 5) (= temp3 -2) else (= temp3 param5))
	(if (< argc 6) (= temp1 300) else (= temp1 param6))
	((= newDialogPlane (DialogPlane new:)) init: 0 0 1 1)
	(= newTextItem (TextItem new:))
	(newTextItem
		font: global268
		nLeading: global269
		maxWidth: temp1
		fore: global274
		back: 255
		skip: 255
		text: (Array 8 (Array 9 param1))
		border: 0
		mode: 0
		bTileBorder: 0
		init: newDialogPlane
	)
	(= newTextItemNWidth (newTextItem nWidth?))
	(= newTextItemNHeight (newTextItem nHeight?))
	(= newDismissTextButton (DismissTextButton new:))
	(newDismissTextButton
		font: global270
		nLeading: global271
		fore: gFore
		back: gBack
		text: (Array 8 (Array 9 param2))
		bTileBorder: 1
		vTileOff: global276
		vTileOn: global277
		bDefault: 1
		init: newDialogPlane
	)
	(= newDismissTextButtonNWidth
		(newDismissTextButton nWidth?)
	)
	(= newDismissTextButtonNHeight
		(newDismissTextButton nHeight?)
	)
	(= temp8
		(+ (Max newTextItemNWidth newDismissTextButtonNWidth) 10)
	)
	(= temp9
		(+ newTextItemNHeight newDismissTextButtonNHeight 15)
	)
	(if temp0 (= temp21 -5517) else (= temp21 global275))
	((= newTiledView (TiledView new:))
		view: temp21
		init: temp8 temp9 0 1 newDialogPlane
	)
	(= newTiledViewNWidth (newTiledView nWidth?))
	(= newTiledViewNHeight (newTiledView nHeight?))
	(if temp0
		(= newTextItem_2 (TextItem new:))
		(newTextItem_2
			font: global268
			nLeading: global269
			maxWidth: (- newTiledViewNWidth 18)
			nMinWidth: (- newTiledViewNWidth 12)
			fore: global274
			back: 255
			skip: 255
			text: (Array 8 (Array 9 temp0))
			border: 5
			bTileBorder: 1
			vTile: -5516
			init: newDialogPlane
		)
		(= newTiledViewNHeight
			(+ newTiledViewNHeight (newTextItem_2 nHeight?))
		)
	else
		(= newTextItem_2 0)
	)
	(switch temp2
		(-1
			(= temp2 (/ (- screenWidth newTiledViewNWidth) 2))
		)
		(-2
			(= temp2
				(+
					(thePlane left:)
					(/ (- (thePlane getWidth:) newTiledViewNWidth) 2)
				)
			)
		)
	)
	(switch temp3
		(-1
			(= temp3 (/ (- screenHeight newTiledViewNHeight) 2))
		)
		(-2
			(= temp3
				(+
					(thePlane top?)
					(/ (- (thePlane getHeight:) newTiledViewNHeight) 2)
				)
			)
		)
	)
	(newDialogPlane
		setRect:
			temp2
			temp3
			(- (+ temp2 newTiledViewNWidth) 1)
			(- (+ temp3 newTiledViewNHeight) 1)
	)
	(UpdatePlane newDialogPlane)
	(= newTiledViewNOffsetX (newTiledView nOffsetX?))
	(= newTiledViewNOffsetY (newTiledView nOffsetY?))
	(if newTextItem_2
		(newTiledView
			posn: (newTiledView x?) (+ (newTiledView y?) (newTextItem_2 nHeight?))
		)
		(= newTiledViewNOffsetY
			(+ newTiledViewNOffsetY (newTextItem_2 nHeight?))
		)
	)
	(newTextItem
		posn: (+ newTiledViewNOffsetX 5) (+ newTiledViewNOffsetY 5)
	)
	(= temp18
		(/ (- newTiledViewNWidth newDismissTextButtonNWidth) 2)
	)
	(= temp19 (+ newTiledViewNOffsetY 10 newTextItemNHeight))
	(newDismissTextButton posn: temp18 temp19)
	(if param1 (proc64896_7 param1))
	(if param2 (proc64896_7 param2))
	(if temp0 (proc64896_7 temp0))
	(newDialogPlane sitNSpin:)
)

(procedure (proc64033_1 param1 param2)
	(if (< argc 2)
		(PrintDebug {illegal set of dialog font. gendialg.sc DJM})
		(return)
	)
	(= global268 param1)
	(= global269 param2)
)

(procedure (proc64033_2 param1 param2)
	(if (< argc 2)
		(PrintDebug {illegal set of button font. gendialg.sc DJM})
		(return)
	)
	(= global270 param1)
	(= global271 param2)
)

(procedure (proc64033_3 param1 theGFore theGBack)
	(if (< argc 3)
		(PrintDebug
			{illegal set of dialog colors. gendialg.sc DJM}
		)
		(return)
	)
	(= global274 param1)
	(= gFore theGFore)
	(= gBack theGBack)
)

(procedure (proc64033_4 param1 param2 param3)
	(if (< argc 3)
		(PrintDebug
			{illegal set of dialog colors. gendialg.sc DJM}
		)
		(return)
	)
	(= global275 param1)
	(= global276 param2)
	(= global277 param3)
)

(procedure (proc64033_5 param1 param2 param3 param4 param5 param6 param7 &tmp temp0 temp1 temp2 temp3 newTextItemNWidth newTextItemNHeight newDismissTextButton_2NWidth newDismissTextButton_2NHeight temp8 temp9 newDialogPlane newTiledView newDismissTextButton newDismissTextButton_2 newTiledViewNWidth newTiledViewNHeight newTiledViewNOffsetX newTiledViewNOffsetY temp18 temp19 newTextItem newTextItem_2 temp22 temp23)
	(if
	(or (< argc 3) (not param1) (not param2) (not param3))
		(if param1 (proc64896_7 param1))
		(if param3 (proc64896_7 param2))
		(if param2 (proc64896_7 param3))
		(PrintDebug
			{illegal call of YesNoDialog. gendialg.sc DJM}
		)
		(return 0)
	)
	(if (< argc 4) (= temp0 0) else (= temp0 param4))
	(if (< argc 5) (= temp2 -2) else (= temp2 param5))
	(if (< argc 6) (= temp3 -2) else (= temp3 param6))
	(if (< argc 7) (= temp1 300) else (= temp1 param7))
	((= newDialogPlane (DialogPlane new:)) init: 0 0 1 1)
	(= newTextItem (TextItem new:))
	(newTextItem
		font: global268
		nLeading: global269
		maxWidth: temp1
		fore: global274
		back: 255
		skip: 255
		text: (Array 8 (Array 9 param1))
		mode: 0
		border: 0
		bTileBorder: 0
		init: newDialogPlane
	)
	(= newTextItemNWidth (newTextItem nWidth?))
	(= newTextItemNHeight (newTextItem nHeight?))
	(= temp22
		(+
			(= temp22
				(Max
					(GetTextWidth param2 global270 0)
					(GetTextWidth param3 global270 0)
				)
			)
			10
		)
	)
	(= newDismissTextButton (DismissTextButton new:))
	(newDismissTextButton
		font: global270
		nLeading: global271
		fore: gFore
		back: gBack
		text: (Array 8 (Array 9 param2))
		bTileBorder: 1
		vTileOff: global276
		vTileOn: global277
		nMinWidth: temp22
		value: 1
		bDefault: 1
		init: newDialogPlane
	)
	(= newDismissTextButton_2 (DismissTextButton new:))
	(newDismissTextButton_2
		font: global270
		nLeading: global271
		fore: gFore
		back: gBack
		text: (Array 8 (Array 9 param3))
		bTileBorder: 1
		vTileOff: global276
		vTileOn: global277
		nMinWidth: temp22
		value: 0
		init: newDialogPlane
	)
	(= newDismissTextButton_2NWidth
		(newDismissTextButton_2 nWidth?)
	)
	(= newDismissTextButton_2NHeight
		(newDismissTextButton_2 nHeight?)
	)
	(= temp8
		(+
			(Max
				newTextItemNWidth
				(+
					newDismissTextButton_2NWidth
					5
					newDismissTextButton_2NWidth
				)
			)
			10
		)
	)
	(= temp9
		(+ newTextItemNHeight newDismissTextButton_2NHeight 15)
	)
	(if temp0 (= temp23 -5517) else (= temp23 global275))
	((= newTiledView (TiledView new:))
		view: temp23
		init: temp8 temp9 0 1 newDialogPlane
	)
	(= newTiledViewNWidth (newTiledView nWidth?))
	(= newTiledViewNHeight (newTiledView nHeight?))
	(if temp0
		(= newTextItem_2 (TextItem new:))
		(newTextItem_2
			font: global268
			nLeading: global269
			maxWidth: (- newTiledViewNWidth 18)
			nMinWidth: (- newTiledViewNWidth 12)
			fore: global274
			back: 255
			skip: 255
			text: (Array 8 (Array 9 temp0))
			border: 5
			bTileBorder: 1
			vTile: -5516
			init: newDialogPlane
		)
		(= newTiledViewNHeight
			(+ newTiledViewNHeight (newTextItem_2 nHeight?))
		)
	else
		(= newTextItem_2 0)
	)
	(switch temp2
		(-1
			(= temp2 (/ (- screenWidth newTiledViewNWidth) 2))
		)
		(-2
			(= temp2
				(+
					(thePlane left:)
					(/ (- (thePlane getWidth:) newTiledViewNWidth) 2)
				)
			)
		)
	)
	(switch temp3
		(-1
			(= temp3 (/ (- screenHeight newTiledViewNHeight) 2))
		)
		(-2
			(= temp3
				(+
					(thePlane top?)
					(/ (- (thePlane getHeight:) newTiledViewNHeight) 2)
				)
			)
		)
	)
	(newDialogPlane
		setRect:
			temp2
			temp3
			(- (+ temp2 newTiledViewNWidth) 1)
			(- (+ temp3 newTiledViewNHeight) 1)
	)
	(UpdatePlane newDialogPlane)
	(= newTiledViewNOffsetX (newTiledView nOffsetX?))
	(= newTiledViewNOffsetY (newTiledView nOffsetY?))
	(if newTextItem_2
		(newTiledView
			posn: (newTiledView x?) (+ (newTiledView y?) (newTextItem_2 nHeight?))
		)
		(= newTiledViewNOffsetY
			(+ newTiledViewNOffsetY (newTextItem_2 nHeight?))
		)
	)
	(newTextItem
		posn: (+ newTiledViewNOffsetX 5) (+ newTiledViewNOffsetY 5)
	)
	(= temp18
		(/
			(-
				newTiledViewNWidth
				(+
					newDismissTextButton_2NWidth
					newDismissTextButton_2NWidth
					5
				)
			)
			2
		)
	)
	(= temp19 (+ newTiledViewNOffsetY 10 newTextItemNHeight))
	(newDismissTextButton posn: temp18 temp19)
	(newDismissTextButton_2
		posn: (+ temp18 newDismissTextButton_2NWidth 5) temp19
	)
	(if param1 (proc64896_7 param1))
	(if param2 (proc64896_7 param2))
	(if param3 (proc64896_7 param3))
	(if temp0 (proc64896_7 temp0))
	(return (newDialogPlane sitNSpin:))
)

(procedure (proc64033_6 param1 param2 param3 param4 param5 param6 param7 param8 &tmp temp0 temp1 temp2 temp3 newTextItemNWidth newTextItemNHeight newDismissTextButtonNWidth newDismissTextButtonNHeight temp8 temp9 newDialogPlane newTiledView newDismissTextButton newTiledViewNWidth newTiledViewNHeight newTiledViewNOffsetX newTiledViewNOffsetY temp17 temp18 newTextItem newTextItem_2 temp21 temp22 temp23 temp24 newEditItemNWidth newEditItemNHeight temp27 temp28 temp29 temp30)
	(if
		(or
			(< argc 4)
			(not param1)
			(not param2)
			(not param3)
			(not param4)
		)
		(if param1 (proc64896_7 param1))
		(if param3 (proc64896_7 param2))
		(if param2 (proc64896_7 param3))
		(PrintDebug
			{illegal call of YesNoDialog. gendialg.sc DJM}
		)
		(return 0)
	)
	(if (< argc 5) (= temp0 0) else (= temp0 param5))
	(if (< argc 6) (= temp2 -2) else (= temp2 param6))
	(if (< argc 7) (= temp3 -2) else (= temp3 param7))
	(if (< argc 8) (= temp1 300) else (= temp1 param8))
	((= newDialogPlane (DialogPlane new:)) init: 0 0 1 1)
	(= temp23 (* (GetTextWidth {M} global268 0) param4))
	(= newTextItem (TextItem new:))
	(newTextItem
		font: global268
		nLeading: global269
		maxWidth: temp1
		fore: global274
		back: 255
		skip: 255
		text: (Array 8 (Array 9 param1))
		mode: 0
		border: 0
		bTileBorder: 0
		init: newDialogPlane
	)
	(= newTextItemNWidth (newTextItem nWidth?))
	(= newTextItemNHeight (newTextItem nHeight?))
	(= temp21
		(+
			(= temp21
				(Max
					(GetTextWidth param2 global270 0)
					(GetTextWidth param3 global270 0)
				)
			)
			10
		)
	)
	(oBtnOK
		font: global270
		nLeading: global271
		fore: gFore
		back: gBack
		text: (Array 8 (Array 9 param2))
		bTileBorder: 1
		vTileOff: global276
		vTileOn: global277
		nMinWidth: temp21
		value: 1
		bDefault: 1
		init: newDialogPlane
	)
	(= newDismissTextButton (DismissTextButton new:))
	(newDismissTextButton
		font: global270
		nLeading: global271
		fore: gFore
		back: gBack
		text: (Array 8 (Array 9 param3))
		bTileBorder: 1
		vTileOff: global276
		vTileOn: global277
		nMinWidth: temp21
		value: 0
		init: newDialogPlane
	)
	(= newDismissTextButtonNWidth
		(newDismissTextButton nWidth?)
	)
	(= newDismissTextButtonNHeight
		(newDismissTextButton nHeight?)
	)
	(= temp24 (+ temp23 10))
	(= newEditItem (EditItem new:))
	(newEditItem
		font: global268
		nLeading: global269
		fore: global274
		back: 255
		skip: 255
		mode: 0
		nMinWidth: temp24
		border: 3
		nMaxChars: param4
		text: (Str with: {_})
		bTileBorder: 1
		vTile: global277
		init: newDialogPlane
	)
	(= newEditItemNWidth (newEditItem nWidth?))
	(= newEditItemNHeight (newEditItem nHeight?))
	(= temp8
		(+
			(Max
				newTextItemNWidth
				newEditItemNWidth
				(+
					newDismissTextButtonNWidth
					5
					newDismissTextButtonNWidth
				)
			)
			10
		)
	)
	(= temp9
		(+
			newTextItemNHeight
			newDismissTextButtonNHeight
			newEditItemNHeight
			20
		)
	)
	(if temp0 (= temp22 -5517) else (= temp22 global275))
	((= newTiledView (TiledView new:))
		view: temp22
		init: temp8 temp9 0 1 newDialogPlane
	)
	(= newTiledViewNWidth (newTiledView nWidth?))
	(= newTiledViewNHeight (newTiledView nHeight?))
	(if temp0
		(= newTextItem_2 (TextItem new:))
		(newTextItem_2
			font: global268
			nLeading: global269
			maxWidth: (- newTiledViewNWidth 18)
			nMinWidth: (- newTiledViewNWidth 12)
			fore: global274
			back: 255
			skip: 255
			text: (Array 8 (Array 9 temp0))
			border: 5
			bTileBorder: 1
			vTile: -5516
			init: newDialogPlane
		)
		(= newTiledViewNHeight
			(+ newTiledViewNHeight (newTextItem_2 nHeight?))
		)
	else
		(= newTextItem_2 0)
	)
	(switch temp2
		(-1
			(= temp2 (/ (- screenWidth newTiledViewNWidth) 2))
		)
		(-2
			(= temp2
				(+
					(thePlane left:)
					(/ (- (thePlane getWidth:) newTiledViewNWidth) 2)
				)
			)
		)
	)
	(switch temp3
		(-1
			(= temp3 (/ (- screenHeight newTiledViewNHeight) 2))
		)
		(-2
			(= temp3
				(+
					(thePlane top?)
					(/ (- (thePlane getHeight:) newTiledViewNHeight) 2)
				)
			)
		)
	)
	(newDialogPlane
		setRect:
			temp2
			temp3
			(- (+ temp2 newTiledViewNWidth) 1)
			(- (+ temp3 newTiledViewNHeight) 1)
	)
	(UpdatePlane newDialogPlane)
	(= newTiledViewNOffsetX (newTiledView nOffsetX?))
	(= newTiledViewNOffsetY (newTiledView nOffsetY?))
	(if newTextItem_2
		(newTiledView
			posn: (newTiledView x?) (+ (newTiledView y?) (newTextItem_2 nHeight?))
		)
		(= newTiledViewNOffsetY
			(+ newTiledViewNOffsetY (newTextItem_2 nHeight?))
		)
	)
	(= temp27
		(/ (- newTiledViewNWidth newTextItemNWidth) 2)
	)
	(= temp28 (+ newTiledViewNOffsetY 5))
	(newTextItem posn: temp27 temp28)
	(= temp29
		(/ (- newTiledViewNWidth newEditItemNWidth) 2)
	)
	(= temp30 (+ temp28 newTextItemNHeight 5))
	(newEditItem posn: temp29 temp30)
	(= temp17
		(/
			(-
				newTiledViewNWidth
				(+
					newDismissTextButtonNWidth
					newDismissTextButtonNWidth
					5
				)
			)
			2
		)
	)
	(= temp18 (+ temp30 newEditItemNHeight 5))
	(oBtnOK posn: temp17 temp18)
	(newDismissTextButton
		posn: (+ temp17 newDismissTextButtonNWidth 5) temp18
	)
	(UpdateScreenItem oBtnOK)
	(UpdateScreenItem newDismissTextButton)
	(UpdateScreenItem newEditItem)
	(UpdateScreenItem newTextItem)
	(UpdateScreenItem newTiledView)
	(FrameOut)
	(if param1 (proc64896_7 param1))
	(if param2 (proc64896_7 param2))
	(if param3 (proc64896_7 param3))
	(if temp0 (proc64896_7 temp0))
	(= newEditItemGetText 0)
	(newEditItem doVerb:)
	(newDialogPlane sitNSpin:)
	(return newEditItemGetText)
)

(procedure (proc64033_7 param1 param2 param3 param4 &tmp temp0 temp1 newTiledViewNWidth newTiledViewNHeight newDialogPlane newTiledView newDismissTextButton [theNewDismissTextButton 10] newDismissTextButtonNWidth newDismissTextButtonNHeight temp19 temp20 temp21 temp22 temp23)
	(if (or (< argc 4) (not [param4 0]))
		(PrintDebug
			{Illegal call of StackedButtonDialog. slidesho.sc MFM}
		)
		(return 0)
	)
	((= newDialogPlane (DialogPlane new:)) init: 0 0 1 1)
	(= temp23 0)
	(= newDismissTextButtonNWidth 0)
	(while (< temp23 param1)
		(= newDismissTextButton (DismissTextButton new:))
		(newDismissTextButton
			text: (Array 8 (Array 9 [param4 temp23]))
			fore: gFore
			back: gBack
			font: global270
			vTileOff: global276
			vTileOn: global277
			bTileBorder: 1
			nLeading: global271
			value: temp23
			bCancel: 0
			init: newDialogPlane
		)
		(= [theNewDismissTextButton temp23] newDismissTextButton)
		(if
			(<
				newDismissTextButtonNWidth
				(newDismissTextButton nWidth?)
			)
			(= newDismissTextButtonNWidth
				(newDismissTextButton nWidth?)
			)
		)
		(++ temp23)
	)
	(= newDismissTextButtonNHeight
		(newDismissTextButton nHeight?)
	)
	(= temp21 (+ newDismissTextButtonNWidth 10))
	(= temp22 (* (+ newDismissTextButtonNHeight 10) param1))
	((= newTiledView (TiledView new:))
		view: global275
		init: temp21 temp22 0 1 newDialogPlane
	)
	(= newTiledViewNWidth (newTiledView nWidth?))
	(= newTiledViewNHeight (newTiledView nHeight?))
	(if (== param2 0)
		(= temp0 (/ (- screenWidth newTiledViewNWidth) 2))
	else
		(= temp0 param2)
	)
	(if (== param3 0)
		(= temp1 (/ (- screenHeight newTiledViewNHeight) 2))
	else
		(= temp1 param3)
	)
	(newDialogPlane
		setRect:
			temp0
			temp1
			(- (+ temp0 newTiledViewNWidth) 1)
			(- (+ temp1 newTiledViewNHeight) 1)
	)
	(UpdatePlane newDialogPlane)
	(= temp23 0)
	(while (< temp23 param1)
		(= temp19
			(/
				(-
					newTiledViewNWidth
					(= newDismissTextButtonNWidth
						((= newDismissTextButton [theNewDismissTextButton temp23])
							nWidth?
						)
					)
				)
				2
			)
		)
		(= temp20
			(+ 15 (* temp23 (+ 10 newDismissTextButtonNHeight)))
		)
		(newDismissTextButton posn: temp19 temp20)
		(++ temp23)
	)
	(= temp23 0)
	(while (< temp23 param1)
		(if [param4 temp23] (proc64896_7 [param4 temp23]))
		(++ temp23)
	)
	(return (newDialogPlane sitNSpin:))
)

(instance poNull of Prop
	(properties)
)

(instance soDelayDispose of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(register dispose:)
				(= register 0)
				(self dispose:)
			)
		)
	)
)

(instance oBtnOK of DismissTextButton
	(properties)
	
	(method (doSelect &tmp temp0)
		(= newEditItemGetText 0)
		(if newEditItem
			(= newEditItemGetText (newEditItem getText:))
		)
		(super doSelect: &rest)
	)
)

(instance oBtnCancel of DismissTextButton
	(properties)
)

(class DialogPlane of ModalPlane
	(properties
		scratch 0
		resX -1
		resY -1
		left 0
		top 0
		right 0
		bottom 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		vanishingX 0
		vanishingY 0
		coordType 0
		picture -2
		style $0000
		priority 610
		back 0
		bitmap 0
		casts 0
		mirrored 0
		oMyFeatures 0
		bSpinning 0
		value 0
	)
	
	(method (dispose)
		(if bSpinning (= bSpinning 0) (return))
		(super dispose: &rest)
	)
	
	(method (sitNSpin &tmp temp0 theValue)
		(= bSpinning 1)
		(while bSpinning
			((self getMainCast:) doit:)
			(FrameOut)
			(= temp0 ((user curEvent?) new:))
			(gOEventHandler handleEvent: temp0)
			(temp0 dispose:)
		)
		(= theValue value)
		(self dispose:)
		(return theValue)
	)
)
