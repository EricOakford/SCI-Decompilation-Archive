;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64921)
(include sci.sh)
(use Main)
(use DButton)
(use DIcon)
(use DSelector)
(use DEdit)
(use DText)
(use Plane)
(use String)
(use Dialog)
(use FileSel)
(use Actor)
(use System)

(public
	Prints 0
	Printf 1
	GetInput 2
	FindFormatLen 3
)

(procedure (Prints &tmp newPrint)
	(= newPrint (Print new:))
	(newPrint addText: &rest init:)
)

(procedure (Printf &tmp newPrint)
	(= newPrint (Print new:))
	(newPrint addTextF: &rest init:)
)

(procedure (GetInput param1 param2 param3 param4)
	(if
		((Print new:)
			font: (= inputFont (if (> argc 3) param4 else 300))
			addText: (if (and (> argc 2) param3) param3 else {})
			addEdit: param1 param2 0 12 param1
			init:
		)
		(param1 size:)
	)
)

(procedure (FindFormatLen param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5)
	((= temp4
		(Str newWith: (KString 10 (KString 9 param1)) {})
	)
		copy: param1
	)
	(= temp0 (= temp1 (temp4 size:)))
	(= temp2 0)
	(= temp3 0)
	(while (< temp3 temp1)
		(if (== (temp4 at: temp3) 37)
			(switch (temp4 at: (++ temp3))
				(100 (= temp0 (+ temp0 5)))
				(120 (= temp0 (+ temp0 4)))
				(115
					(= temp5 (Str with: [param2 temp2]))
					(= temp0 (+ temp0 (temp5 size:)))
					(temp5 dispose:)
				)
			)
			(++ temp2)
		)
		(++ temp3)
	)
	(temp4 dispose:)
	(return (++ temp0))
)

(class Print of Obj
	(properties
		scratch 0
		dialog 0
		plane 0
		title 0
		mode 0
		font 300
		width 0
		fore 0
		back 255
		skip 255
		x -1
		y -1
		margin 4
		ticks 0
		caller 0
		retValue 0
		modeless 0
		first 0
		saveCursor 0
		bitmap 0
		onScreen 1
		classButton 0
		classEdit 0
		classIcon 0
		classText 0
		largeAlp 1
		xtheX 0
		xtheY 0
	)
	
	(method (init theCaller)
		(= caller 0)
		(if argc (= caller theCaller))
		(if (> argc 1) (self addText: &rest))
		(self showSelf:)
	)
	
	(method (doit)
		(dialog doit:)
	)
	
	(method (dispose)
		(if (prints contains: self) (prints delete: self))
		(plane dispose:)
		(= width
			(= mode
				(= title
					(= first (= saveCursor (= plane (= bitmap 0))))
				)
			)
		)
		(= x (= y -1))
		(= modeless 0)
		(= largeAlp 1)
		(sounds pause: 0)
		(= font 300)
		(super dispose:)
	)
	
	(method (addBitmap param1 param2 param3)
		(if (not dialog) (= dialog (Dialog new:)))
		(= bitmap (DIcon new:))
		(dialog
			add:
				(bitmap
					view: param1
					loop: param2
					cel: param3
					setSize:
					moveTo: 0 0
					yourself:
				)
		)
		(= width (- (CelWide param1 param2 param3) 44))
		(return bitmap)
	)
	
	(method (addButton param1 theTheCurRoomNum &tmp theTheTheCurRoomNum theTheTheCurRoomNum_2 theTheTheCurRoomNum_3 temp3 theTheTheCurRoomNum_4 theTheTheCurRoomNum_5 theCurRoomNum newStr temp8 temp9)
		(if (not dialog) (= dialog (Dialog new:)))
		(= temp9 (if classButton else DButton))
		(if (> argc 4)
			(= theTheTheCurRoomNum [theTheCurRoomNum 0])
			(= theTheTheCurRoomNum_2 [theTheCurRoomNum 1])
			(= theTheTheCurRoomNum_3 [theTheCurRoomNum 2])
			(= temp3
				(if [theTheCurRoomNum 3] [theTheCurRoomNum 3] else 1)
			)
			(= theTheTheCurRoomNum_4 0)
			(= theTheTheCurRoomNum_5 0)
			(= theCurRoomNum curRoomNum)
			(if (> argc 5)
				(= theTheTheCurRoomNum_4 [theTheCurRoomNum 4])
				(if (> argc 6)
					(= theTheTheCurRoomNum_5 [theTheCurRoomNum 5])
					(if (> argc 7) (= theCurRoomNum [theTheCurRoomNum 6]))
				)
			)
			(= newStr (Str new:))
			(if
				(or
					(not
						(Message
							msgSIZE
							theCurRoomNum
							theTheTheCurRoomNum
							theTheTheCurRoomNum_2
							theTheTheCurRoomNum_3
							temp3
						)
					)
					(not
						(Message
							msgGET
							theCurRoomNum
							theTheTheCurRoomNum
							theTheTheCurRoomNum_2
							theTheTheCurRoomNum_3
							temp3
							(newStr data?)
						)
					)
				)
				(newStr dispose:)
				(return 0)
			)
			(dialog
				add:
					(= temp8
						((temp9 new:)
							value: param1
							font: font
							fore: fore
							back: (if (== back -1) (temp9 back?) else back)
							skip: (if (== skip -1) (temp9 skip?) else skip)
							text: (newStr data?)
							setSize:
							moveTo: (+ 22 theTheTheCurRoomNum_4) (+ 22 theTheTheCurRoomNum_5)
							yourself:
						)
					)
			)
			(newStr data: 0 dispose:)
		else
			(= theTheTheCurRoomNum_4 0)
			(= theTheTheCurRoomNum_5 0)
			(if (> argc 2)
				(= theTheTheCurRoomNum_4 [theTheCurRoomNum 1])
				(if (> argc 3)
					(= theTheTheCurRoomNum_5 [theTheCurRoomNum 2])
				)
			)
			(= newStr (KString 8 (KString 9 [theTheCurRoomNum 0])))
			(dialog
				add:
					(= temp8
						((temp9 new:)
							value: param1
							font: font
							fore: fore
							back: (if (== back -1) (temp9 back?) else back)
							skip: (if (== skip -1) (temp9 skip?) else skip)
							text: newStr
							setSize:
							moveTo: (+ 22 theTheTheCurRoomNum_4) (+ 22 theTheTheCurRoomNum_5)
							yourself:
						)
					)
			)
		)
		(return temp8)
	)
	
	(method (addButtonBM param1 param2 param3 &tmp temp0)
		(= temp0 (self addButton: &rest))
		(if (!= param1 -1)
			(temp0 view: param1 loop: param2 cel: param3 setSize:)
		else
			(temp0 view: -546 loop: 0 cel: 0 setSize:)
		)
	)
	
	(method (addEdit param1 param2 param3 param4 param5 &tmp temp0 temp1 temp2 temp3)
		(if (not dialog) (= dialog (Dialog new:)))
		(= temp3 (if classEdit else DEdit))
		(if (> argc 4) (param1 copy: param5))
		(if (> argc 2)
			(= temp0 param3)
			(if (> argc 3) (= temp1 param4))
		)
		(dialog
			add:
				(= temp2
					((temp3 new:)
						text: (param1 data?)
						width: param2
						fore: fore
						font: font
						back: (if (== back -1) (temp3 back?) else back)
						skip: (if (== skip -1) (temp3 skip?) else skip)
						setSize:
						moveTo: (+ temp0 22) (+ temp1 22)
						yourself:
					)
				)
		)
		(return temp2)
	)
	
	(method (addFSelector param1 param2 param3 param4 &tmp temp0 newFileSelector)
		(if (not dialog) (= dialog (Dialog new:)))
		((= newFileSelector (FileSelector new:)) font: font)
		(dialog
			add:
				(= temp0
					(newFileSelector
						setText:
						mask:
							(if (> argc 3)
								(KString 8 (KString 9 param4))
							else
								(KString 8 {*.*})
							)
						sort: 0
						length: (if (!= param3 -1) param3 else 0)
						setSize:
						moveTo: (+ param1 22) (+ param2 22)
						yourself:
					)
				)
		)
		(return temp0)
	)
	
	(method (addIcon param1 param2 param3 param4 param5 &tmp temp0 temp1 temp2 temp3)
		(if (not dialog) (= dialog (Dialog new:)))
		(if (> argc 3)
			(= temp0 param4)
			(= temp1 param5)
		else
			(= temp0 (= temp1 0))
		)
		(= temp3 (if classIcon else DIcon))
		(if (== param2 -1)
			(dialog
				add:
					(= temp2
						(param1
							setSize:
							moveTo: (+ temp0 22) (+ temp1 22)
							yourself:
						)
					)
			)
		else
			(dialog
				add:
					(= temp2
						((temp3 new:)
							view: param1
							loop: param2
							cel: param3
							setSize:
							moveTo: (+ temp0 22) (+ temp1 22)
							yourself:
						)
					)
			)
		)
		(return temp2)
	)
	
	(method (addSelector param1 param2 param3 param4 &tmp temp0 newDSelector)
		(if (not dialog) (= dialog (Dialog new:)))
		((= newDSelector (DSelector new:)) font: font)
		(dialog
			add:
				(= temp0
					(newDSelector
						setText: param4 &rest
						length: (if (!= param3 -1) param3 else 0)
						setSize:
						moveTo: (+ param1 22) (+ param2 22)
						yourself:
					)
				)
		)
		(return temp0)
	)
	
	(method (addSlider)
		(return 0)
	)
	
	(method (addText theTheCurRoomNum &tmp theTheTheCurRoomNum theTheTheCurRoomNum_2 theTheTheCurRoomNum_3 temp3 theTheTheCurRoomNum_4 theTheTheCurRoomNum_5 theCurRoomNum newStr temp8 temp9 temp10 temp11)
		(if (not dialog) (= dialog (Dialog new:)))
		(= temp11 (if classText else DText))
		(if (> argc 3)
			(= theTheTheCurRoomNum [theTheCurRoomNum 0])
			(= theTheTheCurRoomNum_2 [theTheCurRoomNum 1])
			(= theTheTheCurRoomNum_3 [theTheCurRoomNum 2])
			(= temp3
				(if [theTheCurRoomNum 3] [theTheCurRoomNum 3] else 1)
			)
			(= theTheTheCurRoomNum_4 0)
			(= theTheTheCurRoomNum_5 0)
			(= theCurRoomNum curRoomNum)
			(if (>= argc 5)
				(= theTheTheCurRoomNum_4 [theTheCurRoomNum 4])
				(if (>= argc 6)
					(= theTheTheCurRoomNum_5 [theTheCurRoomNum 5])
					(if (>= argc 7) (= theCurRoomNum [theTheCurRoomNum 6]))
				)
			)
			(= newStr (Str new:))
			(if
				(or
					(not
						(Message
							msgSIZE
							theCurRoomNum
							theTheTheCurRoomNum
							theTheTheCurRoomNum_2
							theTheTheCurRoomNum_3
							temp3
						)
					)
					(not
						(Message
							msgGET
							theCurRoomNum
							theTheTheCurRoomNum
							theTheTheCurRoomNum_2
							theTheTheCurRoomNum_3
							temp3
							(newStr data?)
						)
					)
				)
				(return 0)
			)
			(if largeAlp
				(if
					(and
						(<= 65 (= largeAlp (newStr at: 0)))
						(<= (= largeAlp (newStr at: 0)) 90)
					)
					(newStr at: 0 9)
				else
					(= largeAlp 0)
				)
			)
			(dialog
				add:
					((temp11 new:)
						text: (KString 8 (KString 9 (newStr data?)))
						font: font
						mode: mode
						fore: 92
						setPri: 10
						back: 255
						skip: (if (== skip -1) (temp11 skip?) else skip)
						setSize: width
						moveTo: (+ 23 theTheTheCurRoomNum_4) (+ 23 theTheTheCurRoomNum_5)
						yourself:
					)
					(= temp9
						((temp11 new:)
							text: (newStr data?)
							font: font
							mode: mode
							fore: fore
							back: 255
							skip: (if (== skip -1) (temp11 skip?) else skip)
							setSize: width
							moveTo:
								(= xtheX (+ 22 theTheTheCurRoomNum_4))
								(= xtheY (+ 22 theTheTheCurRoomNum_5))
							yourself:
						)
					)
			)
		else
			(= theTheTheCurRoomNum_4 0)
			(= theTheTheCurRoomNum_5 0)
			(if (>= argc 2)
				(= theTheTheCurRoomNum_4 [theTheCurRoomNum 1])
				(if (>= argc 3)
					(= theTheTheCurRoomNum_5 [theTheCurRoomNum 2])
				)
			)
			(= newStr (Str new:))
			(newStr
				data: (KString 8 (KString 9 [theTheCurRoomNum 0]))
			)
			(if largeAlp
				(if
					(and
						(<= 65 (= largeAlp (newStr at: 0)))
						(<= (= largeAlp (newStr at: 0)) 90)
					)
					(newStr at: 0 9)
				else
					(= largeAlp 0)
				)
			)
			(dialog
				add:
					((temp11 new:)
						text: (KString 8 (KString 9 (newStr data?)))
						font: font
						mode: mode
						fore: 92
						setPri: 10
						back: 255
						skip: (if (== skip -1) (temp11 skip?) else skip)
						setSize: width
						moveTo: (+ 23 theTheTheCurRoomNum_4) (+ 23 theTheTheCurRoomNum_5)
						yourself:
					)
					(= temp9
						((temp11 new:)
							text: (newStr data?)
							font: font
							mode: mode
							fore: fore
							back: 255
							skip: (if (== skip -1) (temp11 skip?) else skip)
							setSize: width
							moveTo:
								(= xtheX (+ 22 theTheTheCurRoomNum_4))
								(= xtheY (+ 22 theTheTheCurRoomNum_5))
							yourself:
						)
					)
			)
		)
		(newStr data: 0 dispose:)
		(return temp9)
	)
	
	(method (addTextBM param1 param2 param3 &tmp temp0)
		(= temp0 (self addText: &rest))
		(if (!= param1 -1)
			(temp0 view: param1 loop: param2 cel: param3 setSize:)
		else
			(temp0 view: 1234 loop: 2 cel: 0 setSize:)
		)
	)
	
	(method (addTextF &tmp temp0 temp1)
		(= temp0 (FindFormatLen &rest))
		(= temp1 (Str newWith: temp0 {}))
		(temp1 format: &rest)
		(self addText: (temp1 data?))
		(temp1 dispose:)
	)
	
	(method (addTitle param1 &tmp temp0 temp1 temp2 temp3 temp4 newStr)
		(return
			(if (> argc 1)
				(= temp0 [param1 0])
				(= temp1 [param1 1])
				(= temp2 [param1 2])
				(= temp3 [param1 3])
				(= temp4 [param1 4])
				(= newStr (Str new:))
				(if
					(or
						(not (Message msgSIZE temp4 temp0 temp1 temp2 temp3))
						(not
							(Message
								msgGET
								temp4
								temp0
								temp1
								temp2
								temp3
								(newStr data?)
							)
						)
					)
					(newStr dispose:)
					(return 0)
				)
				(= title (KString 8 (newStr data?)))
				(newStr data: 0 dispose:)
			else
				(= title (KString 8 (KString 9 [param1 0])))
			)
		)
	)
	
	(method (posn theX theY)
		(= x theX)
		(= y theY)
	)
	
	(method (handleEvent event)
		(return
			(if (dialog handleEvent: event)
				(dialog dispose:)
				(return 1)
			else
				0
			)
		)
	)
	
	(method (cue &tmp theCaller)
		(= theCaller caller)
		(= dialog 0)
		(self dispose:)
		(if theCaller (theCaller cue:))
	)
	
	(method (showSelf &tmp theFirst temp1 temp2 temp3 temp4 theRetValue dialogPlane newCast temp8)
		(if saveCursor (theGame setCursor: normalCursor))
		(FrameOut)
		(if (not dialog) (= dialog (Dialog new:)))
		((= plane (Plane new:)) picture: -2 back: back)
		(if largeAlp (= newCast (Cast new:)))
		(dialog
			plane: plane
			name: {PODialog}
			caller: self
			text: title
			ticks: ticks
			margin: 22
			modeless: modeless
			onScreen: onScreen
			init:
		)
		(if bitmap
			(dialog
				nsLeft: (bitmap nsLeft?)
				nsTop: (bitmap nsTop?)
				nsRight: (bitmap nsRight?)
				nsBottom: (bitmap nsBottom?)
			)
		else
			(dialog setSize:)
		)
		(if title
			((dialog plane?) setTitle: title)
			(dialog
				nsLeft: ((dialog plane?) left:)
				nsTop: ((dialog plane?) top?)
				nsRight: ((dialog plane?) right:)
				nsBottom: ((dialog plane?) bottom?)
			)
		)
		(dialog center:)
		(= temp2 (if (== x -1) (dialog nsLeft?) else x))
		(= temp3 (if (== y -1) (dialog nsTop?) else y))
		(dialog moveTo: temp2 temp3)
		(= dialogPlane (dialog plane?))
		(dialogPlane
			setRect:
				(- (dialogPlane left:) 2)
				(dialogPlane top?)
				(+ (dialogPlane right:) 2)
				(dialogPlane bottom?)
				(- (dialogPlane left:) 2)
				(dialogPlane top?)
				(+ (dialogPlane right:) 2)
				(dialogPlane bottom?)
			setBitmap: 935 0 0 1
		)
		(if largeAlp
			(dialogPlane addCast: newCast)
			((View new:)
				view: 935
				loop: (if (> (= temp8 (- largeAlp 65)) 15) 4 else 3)
				cel: (mod temp8 16)
				posn: (- xtheX 3) (- xtheY 7)
				init: newCast
			)
		)
		(dialog eachElementDo: #updatePlane)
		(FrameOut)
		(return
			(if (== modeless 0)
				(sounds pause: 1)
				(if (not (= theFirst first))
					(if
						(and
							(= theFirst (dialog firstTrue: #checkState 1))
							(not (dialog firstTrue: #checkState 2))
						)
						(theFirst state: (| (theFirst state?) $0002))
					)
				else
					(= theFirst (dialog at: theFirst))
				)
				(= retValue (dialog doit: theFirst))
				(switch retValue
					(-1 (= retValue 0))
					(-2 (= retValue 1))
					(0 (= retValue 1))
					(else 
						(if (retValue object?)
							(theGame
								panelObj: (retValue object?)
								panelSelector: (retValue selector?)
							)
						)
						(cond 
							((retValue isKindOf: DEdit) (= retValue (retValue text?)))
							((retValue isKindOf: DSelector) (= retValue (retValue getText:)))
							(else (= retValue (retValue value:)))
						)
					)
				)
				(if saveCursor
					(theGame setCursor: (theIconBar getCursor:))
				)
				(= theRetValue retValue)
				(dialog dispose:)
				(return theRetValue)
			else
				(prints addToFront: self)
			)
		)
	)
	
	(method (isModeless param1)
		(return (if argc (== modeless param1) else modeless))
	)
)
