;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64866)
(include sci.sh)
(use Main)
(use DialogPlane)
(use TPSelector)
(use TiledBitmap)
(use PushButton)
(use GenDialog)
(use String)
(use Array)
(use File)
(use System)

(public
	proc64866_0 0
	proc64866_1 1
	proc64866_2 2
)

(local
	theNewTextItem
	theNewTextItemGetText
	local2
	local3
)
(procedure (proc64866_0)
	(if (proc64866_2 1)
		(FrameOut)
		(theGame saveThis: local2 theNewTextItemGetText)
	)
	(if theNewTextItemGetText
		(proc64896_7 theNewTextItemGetText)
		(= theNewTextItemGetText 0)
	)
	(DisposeScript -670)
)

(procedure (proc64866_1)
	(if (proc64866_2 0)
		(FrameOut)
		(theGame restoreThis: local2 theNewTextItemGetText)
	)
	(if theNewTextItemGetText
		(proc64896_7 theNewTextItemGetText)
		(= theNewTextItemGetText 0)
	)
	(DisposeScript -670)
)

(procedure (proc64866_2 param1 &tmp temp0 temp1 temp2 oSelectorGetHeight oSelectorGetWidth newTiledView_2NWidth newTiledView_2NHeight newTiledView_2 newTiledView newDialogPlane newTextItem_2 temp11 temp12 newTiledView_2NOffsetX newTiledView_2NOffsetY [temp15 2] temp17 newTextItem temp19 temp20 temp21 temp22 temp23 temp24 temp25 temp26)
	((= newDialogPlane (DialogPlane new:)) init: 0 0 1 1)
	(if argc (= local3 param1) else (= local3 0))
	(= temp17 (* (GetTextWidth {M} global268 0) 36))
	(if (== local3 0)
		(= temp19 (MakeMessageText 26 0 0 1 -546))
	else
		(= temp19 (MakeMessageText 28 0 0 1 -546))
	)
	(= temp20 (MakeMessageText 22 0 0 1 -546))
	(= temp21 (MakeMessageText 24 0 0 1 -546))
	(= temp24
		(+
			(= temp24
				(Max
					(GetTextWidth temp19 global270 0)
					(GetTextWidth temp20 global270 0)
					(GetTextWidth temp21 global270 0)
				)
			)
			10
		)
	)
	(oBtnYes
		font: global270
		nLeading: global271
		fore: gFore
		back: gBack
		text: (Str with: temp19)
		bTileBorder: 1
		vTileOff: global276
		vTileOn: global277
		nMinWidth: temp24
		value: 1
		bDefault: 1
		init: newDialogPlane
	)
	(oBtnUp
		font: global270
		nLeading: global271
		fore: gFore
		back: gBack
		text: (MakeMessageText 39 0 0 1 -546)
		bTileBorder: 1
		vTileOff: global276
		vTileOn: global277
		init: newDialogPlane
	)
	(oBtnDown
		font: global270
		nLeading: global271
		fore: gFore
		back: gBack
		text: (MakeMessageText 40 0 0 1 -546)
		bTileBorder: 1
		vTileOff: global276
		vTileOn: global277
		init: newDialogPlane
	)
	(oBtnCancel
		font: global270
		nLeading: global271
		fore: gFore
		back: gBack
		text: (Str with: temp20)
		bTileBorder: 1
		vTileOff: global276
		vTileOn: global277
		nMinWidth: temp24
		value: 0
		init: newDialogPlane
	)
	(oBtnDelete
		font: global270
		nLeading: global271
		fore: gFore
		back: gBack
		text: (Str with: temp21)
		bTileBorder: 1
		vTileOff: global276
		vTileOn: global277
		nMinWidth: temp24
		init: newDialogPlane
	)
	(proc64896_7 temp19)
	(proc64896_7 temp20)
	(proc64896_7 temp21)
	(oSelector
		font: global268
		fore: gFore
		back: gBack
		length: 12
		width: 300
		init: (newDialogPlane getMainCast:)
	)
	(localproc_1174 oSelector)
	(= oSelectorGetHeight (oSelector getHeight:))
	(= oSelectorGetWidth (oSelector getWidth:))
	((= newTiledView (TiledView new:))
		view: global277
		init: oSelectorGetWidth oSelectorGetHeight 0 1 newDialogPlane
	)
	(= temp25
		(+
			(newTiledView nWidth?)
			10
			(oBtnYes nWidth?)
			(oBtnUp nWidth?)
		)
	)
	(if (== local3 0)
		(= newTextItem (TextItem new:))
		(newTextItem
			font: global268
			nLeading: global269
			fore: global274
			back: 255
			skip: 255
			mode: 0
			nMinWidth: temp25
			text: (Str with: {_})
			init: newDialogPlane
		)
	else
		(= newTextItem (EditItem new:))
		(newTextItem
			font: global268
			nLeading: global269
			fore: global274
			back: 255
			skip: 255
			mode: 0
			nMinWidth: (- temp25 12)
			border: 3
			nMaxChars: 36
			text: (Str with: {_})
			bTileBorder: 1
			vTile: global277
			init: newDialogPlane
		)
	)
	(= theNewTextItem newTextItem)
	(= temp2
		(+ (newTiledView nHeight?) 15 (newTextItem nHeight?))
	)
	(= temp1
		(Max
			(+ (newTextItem nWidth?) 10)
			(+
				(newTiledView nWidth?)
				20
				(oBtnYes nWidth?)
				(oBtnUp nWidth?)
			)
		)
	)
	((= newTiledView_2 (TiledView new:))
		view: -5517
		init: temp1 temp2 0 1 newDialogPlane
	)
	(= newTiledView_2NWidth (newTiledView_2 nWidth?))
	(= newTiledView_2NHeight (newTiledView_2 nHeight?))
	(if (== local3 0)
		(= temp22 (MakeMessageText 20 0 0 1 -546))
	else
		(= temp22 (MakeMessageText 21 0 0 1 -546))
	)
	(= newTextItem_2 (TextItem new:))
	(newTextItem_2
		font: global268
		nLeading: global269
		maxWidth: (- newTiledView_2NWidth 18)
		nMinWidth: (- newTiledView_2NWidth 12)
		fore: global274
		back: 255
		skip: 255
		text: temp22
		border: 3
		bTileBorder: 1
		vTile: -5516
		init: newDialogPlane
	)
	(= newTiledView_2NHeight
		(+ newTiledView_2NHeight (newTextItem_2 nHeight?))
	)
	(= temp11
		(+
			(thePlane left:)
			(/ (- (thePlane getWidth:) newTiledView_2NWidth) 2)
		)
	)
	(= temp12
		(+
			(thePlane top?)
			(/ (- (thePlane getHeight:) newTiledView_2NHeight) 2)
		)
	)
	(newDialogPlane
		setRect:
			temp11
			temp12
			(- (+ temp11 newTiledView_2NWidth) 1)
			(- (+ temp12 newTiledView_2NHeight) 1)
	)
	(UpdatePlane newDialogPlane)
	(= newTiledView_2NOffsetX (newTiledView_2 nOffsetX?))
	(= newTiledView_2NOffsetY (newTiledView_2 nOffsetY?))
	(newTiledView_2
		posn:
			(newTiledView_2 x?)
			(+ (newTiledView_2 y?) (newTextItem_2 nHeight?))
	)
	(UpdateScreenItem newTiledView_2)
	(= newTiledView_2NOffsetY
		(+ newTiledView_2NOffsetY (newTextItem_2 nHeight?))
	)
	(newTextItem
		posn: (+ newTiledView_2NOffsetX 5) (+ newTiledView_2NOffsetY 5)
	)
	(UpdateScreenItem newTextItem)
	(newTiledView
		posn:
			(+ newTiledView_2NOffsetX 5)
			(+ newTiledView_2NOffsetY 10 (newTextItem nHeight?))
	)
	(UpdateScreenItem newTiledView)
	(oSelector
		posn:
			(+ (newTiledView x?) (newTiledView nOffsetX?))
			(+ (newTiledView y?) (newTiledView nOffsetY?))
	)
	(oBtnUp
		posn: (+ (newTiledView x?) (newTiledView nWidth?) 5) (newTiledView y?)
	)
	(UpdateScreenItem oBtnUp)
	(oBtnDown
		posn:
			(oBtnUp x?)
			(-
				(+ (newTiledView y?) (newTiledView nHeight?))
				(oBtnDown nHeight?)
			)
	)
	(UpdateScreenItem oBtnDown)
	(oBtnYes
		posn: (+ (oBtnUp x?) (oBtnUp nWidth?) 5) (newTiledView y?)
	)
	(UpdateScreenItem oBtnYes)
	(oBtnCancel
		posn: (oBtnYes x?) (+ (oBtnYes y?) (oBtnYes nHeight?) 5)
	)
	(UpdateScreenItem oBtnCancel)
	(oBtnDelete
		posn: (oBtnCancel x?) (+ (oBtnCancel y?) (oBtnCancel nHeight?) 5)
	)
	(UpdateScreenItem oBtnDelete)
	(if (oSelector nItems?)
		(oBtnDelete enable:)
		(if gNewStr
			(if (!= (= temp26 (oSelector find: gNewStr)) -1)
				(oSelector doSelect: temp26)
			else
				(oSelector doSelect: (oSelector current?))
			)
		else
			(oSelector doSelect: (oSelector current?))
		)
	else
		(oBtnDelete disable:)
	)
	(localproc_0c77)
	(if
	(and (>= (oSelector nItems?) 20) (== local3 1))
		(proc64033_0
			(MakeMessageText 0 0 35 1 14)
			(Str with: global288)
		)
		(oBtnYes disable:)
	)
	(if
	(and (<= (oSelector nItems?) 0) (== local3 0))
		(oBtnYes disable:)
	)
	(FrameOut)
	(if (== local3 1) (newTextItem doVerb:))
	(newDialogPlane sitNSpin:)
)

(procedure (localproc_0c77)
	(if (oSelector canScrollUp:)
		(oBtnUp enable:)
	else
		(oBtnUp disable:)
	)
	(if (oSelector canScrollDown:)
		(oBtnDown enable:)
	else
		(oBtnDown disable:)
	)
)

(procedure (localproc_102c param1 &tmp temp0 temp1 temp2 temp3 temp4 newFile)
	(if (or (not argc) (not param1))
		(MonoOut {Bad call of FillFileSelector})
		(return)
	)
	(= temp4 (Str new: 100))
	(SaveGame 6 (temp4 data?) (theGame name?))
	((= newFile (File new:)) name: (temp4 data?) open: 2)
	(if (not newFile)
		(MonoOut {Can't create file directory in WriteCatalog})
		(return)
	)
	(= temp2 (param1 nItems?))
	(= temp1 0)
	(while (< temp1 temp2)
		(= temp3 (param1 getData: temp1))
		(if (= temp0 (param1 getText: temp1))
			(newFile writeWord: temp3)
			(newFile write: (temp0 data?) 36)
			(proc64896_7 temp0)
			(= temp0 0)
		)
		(++ temp1)
	)
	(newFile writeWord: -1 close: dispose:)
	(proc64896_7 temp4)
)

(procedure (localproc_1174 param1 &tmp temp0 temp1 temp2 temp3 temp4)
	(if (or (not argc) (not param1))
		(return (MonoOut {Bad call of FillFileSelector}))
	)
	(= temp1 (Str new: 720))
	(= temp2 (IntArray new: 21))
	(if
		(<=
			(= temp3
				(SaveGame
					5
					(theGame name?)
					(temp1 data?)
					(temp2 data?)
				)
			)
			0
		)
		(return 0)
	)
	(= temp4 0)
	(while (< temp4 temp3)
		(= temp0 (temp1 subStr: (* temp4 36) 36))
		(param1 addItem: temp0 (temp2 at: temp4))
		(temp0 dispose:)
		(++ temp4)
	)
	(temp1 dispose:)
	(return (temp2 dispose:))
)

(instance oSelector of TPSelector
	(properties)
	
	(method (delete)
		(super delete: &rest)
		(localproc_0c77)
	)
	
	(method (doSelect &tmp tPSelectorGetText)
		(super doSelect: &rest)
		(if theNewTextItem
			(if (= tPSelectorGetText (self getText:))
				(theNewTextItem setString: tPSelectorGetText)
				(proc64896_7 tPSelectorGetText)
			else
				(theNewTextItem setString: {_})
			)
		)
	)
	
	(method (doDouble)
		(oBtnYes doSelect:)
	)
)

(instance oBtnYes of DismissTextButton
	(properties)
	
	(method (doSelect &tmp oSelectorGetSelectNum)
		(= theNewTextItemGetText 0)
		(if theNewTextItem
			(= theNewTextItemGetText (theNewTextItem getText:))
		)
		(if
			(or
				(not theNewTextItemGetText)
				(<= (theNewTextItemGetText size:) 0)
				(theNewTextItemGetText compare: {_})
			)
			(proc64033_0
				(MakeMessageText 3 0 0 1 -546)
				(Str with: global288)
			)
			(return)
		)
		(if (== local3 1)
			(if
				(!=
					-1
					(= oSelectorGetSelectNum
						(oSelector find: theNewTextItemGetText)
					)
				)
				(= local2 (oSelector getData: oSelectorGetSelectNum))
				(MonoOut {name exists})
			else
				(= local2 0)
				(repeat
					(if (== -1 (oSelector findData: local2)) (break))
					(++ local2)
				)
			)
		else
			(= oSelectorGetSelectNum (oSelector getSelectNum:))
			(= local2 (oSelector getData: oSelectorGetSelectNum))
		)
		(if
			(and
				(== local3 1)
				(not (FileIO 17 2 (KArray 9 curSaveDir)))
			)
			(proc64033_0
				(MakeMessageText 30 0 0 1 -546)
				(Str with: global288)
			)
			(oBtnCancel doSelect:)
			(return)
		)
		(super doSelect: &rest)
	)
)

(instance oBtnCancel of DismissTextButton
	(properties)
)

(instance oBtnUp of TextButton
	(properties)
	
	(method (doSelect)
		(oSelector scrollUp:)
		(localproc_0c77)
	)
)

(instance oBtnDown of TextButton
	(properties)
	
	(method (doSelect)
		(oSelector scrollDown:)
		(localproc_0c77)
	)
)

(instance oBtnDelete of TextButton
	(properties)
	
	(method (doSelect &tmp oSelectorGetText temp1 oSelectorGetSelectNum temp3 oSelectorNItems)
		(if
			(not
				(proc64033_5
					(MakeMessageText 12 0 0 1 -546)
					(MakeMessageText 32 0 0 1 -546)
					(MakeMessageText 31 0 0 1 -546)
				)
			)
			(return)
		)
		(if (= oSelectorGetText (oSelector getText:))
			(= oSelectorGetSelectNum (oSelector getSelectNum:))
			(= temp1 (oSelector getData: oSelectorGetSelectNum))
			(oSelector delete: oSelectorGetSelectNum)
			(= temp3 (Str new: 100))
			(SaveGame 7 (temp3 data?) (theGame name?) temp1)
			(FileIO 4 (temp3 data?))
			(localproc_102c oSelector)
		)
		(if (not (= oSelectorNItems (oSelector nItems?)))
			(if (== local3 0) (oBtnYes disable:))
			(self disable:)
		)
		(if (and (== local3 1) (< oSelectorNItems 20))
			(oBtnYes enable:)
		)
	)
)
