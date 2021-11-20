;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64990)
(include sci.sh)
(use Main)
(use DButton)
(use DSelector)
(use DEdit)
(use DText)
(use String)
(use Array)
(use Print)
(use Dialog)
(use File)
(use System)

(public
	GetDirectory 0
)

(local
	default
	i
	numGames
	selected
	theStatus
	butbuf1
	butbuf2
	butbuf3
	butbuf4
	SRbuf
	saveColGray
)
(procedure (GetDirectory param1 &tmp newPrintInit newStr newStr_2 newStr_3 temp4 newPrint newStr_4 temp7)
	(= newStr (Str new:))
	(= newStr_2 (Str new:))
	(= newStr_3 (Str new:))
	(repeat
		(= temp4
			(Min 45 (+ (Max 29 (+ (param1 size:) 6)) 11))
		)
		((= newPrint (Print new:))
			font: 999
			fore: 0
			back: 255
			skip: 255
			addEdit: (newStr copy: param1) temp4 0 20 param1
			classButton: SRDButton
			addButton: 1 27 0 0 1 0 34 -546
			addButton: 0 38 0 0 1 50 34 -546
		)
		(= newStr_4 (Str new:))
		(Message msgGET -546 1 0 0 1 (newStr_4 data?))
		((newPrint dialog?)
			add:
				((DText new:)
					text: (KString 8 (newStr_4 data?))
					font: 999
					fore: 0
					back: 255
					skip: 255
					setSize:
					moveTo: 19 14
					yourself:
				)
		)
		(= newPrintInit (newPrint init:))
		(newStr_4 dispose:)
		(if (not newPrintInit) (= temp7 0) (break))
		(if (not (newStr size:)) (GetCWD (newStr data?)))
		(if (ValidPath (newStr data?))
			(param1 copy: newStr)
			(= temp7 1)
			(break)
		)
		(Message msgGET -546 29 0 0 1 (newStr_3 data?))
		(newStr_2 format: (newStr_3 data?) newStr)
		((Print new:)
			font: 999
			fore: 0
			back: 255
			largeAlp: 0
			addText: (newStr_2 data?)
			init:
		)
	)
	(newStr dispose:)
	(newStr_2 dispose:)
	(newStr_3 dispose:)
	(return temp7)
)

(procedure (localproc_0c30)
	(return
		(cond 
			((== self Restore) 0)
			((localproc_0ec5) 1)
			(numGames 2)
			(else 3)
		)
	)
)

(procedure (localproc_0ec5)
	(if (< numGames 20)
		(CheckFreeSpace (KString 9 curSaveDir) 2)
	)
)

(procedure (localproc_0edd)
	((Print new:)
		font: 999
		fore: 0
		back: 255
		largeAlp: 0
		addText: 3 0 0 1 0 0 -546
		classButton: SRDButton
		addButton: 1 27 0 0 1 0 15 -546
		init:
	)
)

(class SRDialog of Dialog
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
		text 0
		font 0
		theItem 0
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		ticks 0
		caller 0
		plane 0
		eatTheMice 0
		lastTicks 0
		mouseHiliting 0
		margin 4
		modeless 0
		onScreen 0
	)
	
	(method (init param1 param2 param3)
		(self add:)
		(= butbuf1 (Str new:))
		(= butbuf2 (Str new:))
		(= butbuf3 (Str new:))
		(= butbuf4 (Str new:))
		((= plane (systemPlane new:)) picture: -2 back: 255)
		(if (not (self update: param1 param2 param3))
			(return 0)
		)
		(Message msgGET -546 24 0 0 1 (butbuf2 data?))
		(deleteI
			text: (KString 8 (butbuf2 data?))
			setSize:
			moveTo: i (+ (okI nsBottom?) 2)
		)
		(Message msgGET -546 23 0 0 1 (butbuf3 data?))
		(changeDirI
			text: (KString 8 (butbuf3 data?))
			setSize:
			moveTo: i (+ (deleteI nsBottom?) 2)
			state: (& (changeDirI state?) $fff7)
		)
		(Message msgGET -546 22 0 0 1 (butbuf4 data?))
		(cancelI
			text: (KString 8 (butbuf4 data?))
			setSize:
			moveTo: i (+ (changeDirI nsBottom?) 2)
			state: (& (cancelI state?) $fff7)
		)
		(self add: selectorI okI deleteI changeDirI cancelI textI)
		(super init:)
		(self setSize: center: eachElementDo: #updatePlane)
		(plane
			setRect:
				(- (plane left:) 15)
				(- (plane top?) 5)
				(+ (plane right:) 15)
				(+ (plane bottom?) 15)
				1
			setBitmap: 935 0 0 1
		)
		(return 1)
	)
	
	(method (doit param1 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7)
		(asm
			pushi    #new
			pushi    1
			pushi    720
			class    Str
			send     6
			sat      temp2
			pushi    #new
			pushi    0
			class    Str
			send     4
			sat      temp4
			pushi    #new
			pushi    0
			class    Str
			send     4
			sat      temp5
			pushi    #new
			pushi    1
			pushi    21
			class    IntArray
			send     6
			sat      temp3
			pushi    4
			pushi    3
			pushi    127
			dup     
			dup     
			callk    Palette,  8
			sal      saveColGray
			ldi      0
			sat      temp1
			pushSelf
			class    Restore
			eq?     
			bnt      code_0729
			lap      argc
			bnt      code_0729
			lap      param1
			bnt      code_0729
			pushi    #format
			pushi    2
			lofsa    {%ssg.cat}
			push    
			pushi    #name
			pushi    0
			lag      theGame
			send     4
			push    
			lat      temp4
			send     8
			pushi    2
			pushi    0
			pushi    #data
			pushi    0
			lat      temp4
			send     4
			push    
			callk    FileIO,  4
			sat      temp0
			push    
			ldi      65535
			eq?     
			bnt      code_0721
			ret     
code_0721:
			pushi    2
			pushi    1
			lst      temp0
			callk    FileIO,  4
code_0729:
			pushi    #init
			pushi    3
			lsp      param1
			lst      temp2
			lst      temp3
			self     10
			not     
			bnt      code_073f
			ldi      65534
			sat      temp1
code_073f:
			lat      temp1
			not     
			bnt      code_0bb3
code_0745:
			lsl      theStatus
			dup     
			ldi      0
			eq?     
			bnt      code_075b
			lal      numGames
			bnt      code_0756
			lofsa    okI
			jmp      code_0774
code_0756:
			lofsa    changeDirI
			jmp      code_0774
code_075b:
			dup     
			ldi      1
			eq?     
			bnt      code_0766
			lofsa    editI
			jmp      code_0774
code_0766:
			dup     
			ldi      2
			eq?     
			bnt      code_0771
			lofsa    okI
			jmp      code_0774
code_0771:
			lofsa    changeDirI
code_0774:
			toss    
			sal      default
			pushi    #doit
			pushi    1
			push    
			super    Dialog,  6
			sal      i
			pushi    #current
			pushi    0
			lofsa    selectorI
			send     4
			sal      selected
			lsl      i
			lofsa    changeDirI
			eq?     
			bnt      code_07ba
			pushi    1
			lsg      curSaveDir
			call     GetDirectory,  2
			bnt      code_0745
			pushi    #update
			pushi    3
			lsp      param1
			lst      temp2
			lst      temp3
			self     10
			not     
			bnt      code_0745
			ldi      65534
			sat      temp1
			jmp      code_0bb3
			jmp      code_0745
code_07ba:
			lsl      theStatus
			ldi      2
			eq?     
			bnt      code_08c7
			lsl      i
			lofsa    okI
			eq?     
			bnt      code_08c7
			pushi    #copy
			pushi    1
			pushi    #text
			pushi    0
			pushi    #at
			pushi    1
			pushi    #current
			pushi    0
			lofsa    selectorI
			send     4
			push    
			pushi    #textList
			pushi    0
			lofsa    selectorI
			send     4
			send     6
			send     4
			push    
			lap      param1
			send     6
			pushi    #font
			pushi    1
			pushi    999
			pushi    37
			pushi    1
			pushi    0
			pushi    38
			pushi    1
			pushi    255
			pushi    122
			pushi    1
			pushi    255
			pushi    695
			pushi    1
			pushi    0
			pushi    417
			pushi    7
			pushi    33
			pushi    0
			pushi    0
			pushi    1
			pushi    0
			pushi    0
			pushi    64990
			pushi    417
			pushi    7
			pushi    34
			pushi    0
			pushi    0
			pushi    1
			pushi    0
			pushi    30
			pushi    64990
			pushi    426
			pushi    1
			lofsa    oldName
			push    
			pushi    417
			pushi    3
			lsp      param1
			pushi    0
			pushi    15
			pushi    419
			pushi    5
			lst      temp4
			pushi    30
			pushi    0
			pushi    45
			lsp      param1
			pushi    423
			pushi    1
			class    SRDButton
			push    
			pushi    428
			pushi    8
			pushi    1
			pushi    33
			pushi    0
			pushi    0
			pushi    1
			pushi    0
			pushi    60
			pushi    64990
			pushi    428
			pushi    8
			pushi    0
			pushi    38
			pushi    0
			pushi    0
			pushi    1
			pushi    55
			pushi    60
			pushi    64990
			pushi    147
			pushi    0
			pushi    #new
			pushi    0
			class    Print
			send     4
			send     146
			bnt      code_0745
			pushi    #size
			pushi    0
			lat      temp4
			send     4
			not     
			bnt      code_08aa
			pushi    0
			call     localproc_0edd,  0
			jmp      code_0745
			jmp      code_0745
code_08aa:
			pushi    #copy
			pushi    1
			lst      temp4
			lap      param1
			send     6
			pushi    #at
			pushi    1
			lsl      selected
			lat      temp3
			send     6
			sat      temp1
			jmp      code_0bb3
			jmp      code_0745
code_08c7:
			lsl      theStatus
			ldi      1
			eq?     
			bnt      code_09c0
			pushi    3
			lsl      i
			lofsa    okI
			push    
			lofsa    editI
			push    
			calle    OneOf,  6
			bnt      code_09c0
			pushi    #copy
			pushi    1
			pushi    #text
			pushi    0
			lofsa    editI
			send     4
			push    
			lap      param1
			send     6
			pushi    #size
			pushi    0
			lap      param1
			send     4
			not     
			bnt      code_090c
			pushi    0
			call     localproc_0edd,  0
			jmp      code_0745
code_090c:
			ldi      65535
			sat      temp1
			ldi      0
			sal      i
code_0914:
			lsl      i
			lal      numGames
			lt?     
			bnt      code_095c
			pushi    #new
			pushi    1
			pushi    36
			class    Str
			send     6
			sat      temp7
			pushi    191
			pushi    #scaleX
			pushi    0
			lst      temp2
			lsl      i
			ldi      36
			mul     
			push    
			pushi    36
			lat      temp7
			send     12
			pushi    #compare
			pushi    1
			lst      temp7
			lap      param1
			send     6
			sat      temp1
			pushi    #dispose
			pushi    0
			lat      temp7
			send     4
			lat      temp1
			bt       code_095c
			+al      i
			jmp      code_0914
code_095c:
			lat      temp1
			bnt      code_096f
			pushi    #at
			pushi    1
			lsl      i
			lat      temp3
			send     6
			sat      temp1
			jmp      code_0bb3
code_096f:
			lsl      numGames
			ldi      20
			eq?     
			bnt      code_0985
			pushi    #at
			pushi    1
			lsl      selected
			lat      temp3
			send     6
			sat      temp1
			jmp      code_0bb3
code_0985:
			ldi      0
			sat      temp1
code_0989:
			ldi      1
			bnt      code_0bb3
			ldi      0
			sal      i
code_0992:
			lsl      i
			lal      numGames
			lt?     
			bnt      code_09ac
			lst      temp1
			pushi    #at
			pushi    1
			lsl      i
			lat      temp3
			send     6
			eq?     
			bt       code_09ac
			+al      i
			jmp      code_0992
code_09ac:
			lsl      i
			lal      numGames
			eq?     
			bnt      code_09b6
			jmp      code_0bb3
code_09b6:
			+at      temp1
			jmp      code_0989
			jmp      code_0bb3
			jmp      code_0745
code_09c0:
			lsl      i
			lofsa    deleteI
			eq?     
			bnt      code_0b7a
			pushi    #font
			pushi    1
			pushi    999
			pushi    37
			pushi    1
			pushi    0
			pushi    38
			pushi    1
			pushi    255
			pushi    122
			pushi    1
			pushi    255
			pushi    695
			pushi    1
			pushi    0
			pushi    417
			pushi    7
			pushi    12
			pushi    0
			pushi    0
			pushi    1
			pushi    0
			pushi    0
			pushi    64990
			pushi    423
			pushi    1
			class    SRDButton
			push    
			pushi    428
			pushi    8
			pushi    0
			pushi    31
			pushi    0
			pushi    0
			pushi    1
			pushi    0
			pushi    35
			pushi    64990
			pushi    428
			pushi    8
			pushi    1
			pushi    32
			pushi    0
			pushi    0
			pushi    1
			pushi    50
			pushi    35
			pushi    64990
			pushi    147
			pushi    0
			pushi    #new
			pushi    0
			class    Print
			send     4
			send     98
			not     
			bnt      code_0a36
			jmp      code_0745
code_0a36:
			pushi    2
			pushi    #data
			pushi    0
			lat      temp4
			send     4
			push    
			pushi    #name
			pushi    0
			lag      theGame
			send     4
			push    
			callk    MakeSaveCatName,  4
			pushi    #name
			pushi    1
			pushi    #data
			pushi    0
			lat      temp4
			send     4
			push    
			pushi    413
			pushi    1
			pushi    2
			pushi    #new
			pushi    0
			class    File
			send     4
			sat      temp0
			send     12
			ldi      0
			sal      i
code_0a70:
			lsl      i
			lal      numGames
			lt?     
			bnt      code_0b07
			lsl      i
			lal      selected
			ne?     
			bnt      code_0b02
			pushi    #at
			pushi    1
			lsl      i
			lat      temp3
			send     6
			sat      temp1
			pushi    75
			pushi    2
			pushi    0
			push    
			ldi      255
			and     
			push    
			pushi    75
			pushi    2
			pushi    1
			lst      temp1
			ldi      8
			shr     
			push    
			ldi      255
			and     
			push    
			pushi    75
			pushi    2
			pushi    2
			pushi    0
			lat      temp4
			send     24
			pushi    #write
			pushi    2
			pushi    #data
			pushi    0
			lat      temp4
			send     4
			push    
			pushi    2
			lat      temp0
			send     8
			pushi    #new
			pushi    1
			pushi    36
			class    Str
			send     6
			sat      temp7
			pushi    191
			pushi    #scaleX
			pushi    0
			lst      temp2
			lsl      i
			ldi      36
			mul     
			push    
			pushi    36
			lat      temp7
			send     12
			pushi    #write
			pushi    2
			pushi    #data
			pushi    0
			lat      temp7
			send     4
			push    
			pushi    36
			lat      temp0
			send     8
			pushi    #dispose
			pushi    0
			lat      temp7
			send     4
code_0b02:
			+al      i
			jmp      code_0a70
code_0b07:
			pushi    #at
			pushi    2
			pushi    0
			pushi    255
			pushi    75
			pushi    2
			pushi    1
			pushi    255
			lat      temp4
			send     16
			pushi    #write
			pushi    2
			pushi    #data
			pushi    0
			lat      temp4
			send     4
			push    
			pushi    2
			pushi    524
			pushi    0
			pushi    148
			pushi    0
			lat      temp0
			send     16
			pushi    3
			pushi    #data
			pushi    0
			lat      temp4
			send     4
			push    
			pushi    #name
			pushi    0
			lag      theGame
			send     4
			push    
			pushi    #at
			pushi    1
			lsl      selected
			lat      temp3
			send     6
			push    
			callk    MakeSaveFileName,  6
			pushi    2
			pushi    4
			pushi    #data
			pushi    0
			lat      temp4
			send     4
			push    
			callk    FileIO,  4
			pushi    #update
			pushi    3
			lsp      param1
			lst      temp2
			lst      temp3
			self     10
			jmp      code_0745
code_0b7a:
			lsl      i
			lofsa    okI
			eq?     
			bnt      code_0b93
			pushi    #at
			pushi    1
			lsl      selected
			lat      temp3
			send     6
			sat      temp1
			jmp      code_0bb3
			jmp      code_0745
code_0b93:
			lsl      i
			ldi      65535
			eq?     
			bt       code_0ba2
			lsl      i
			lofsa    cancelI
			eq?     
			bnt      code_0bab
code_0ba2:
			ldi      65535
			sat      temp1
			jmp      code_0bb3
			jmp      code_0745
code_0bab:
			lsl      theStatus
			ldi      1
			eq?     
			jmp      code_0745
code_0bb3:
			pushi    #dispose
			pushi    0
			lal      butbuf1
			send     4
			pushi    #dispose
			pushi    0
			lal      butbuf2
			send     4
			pushi    #dispose
			pushi    0
			lal      butbuf3
			send     4
			pushi    #dispose
			pushi    0
			lal      butbuf4
			send     4
			pushi    #dispose
			pushi    0
			lal      SRbuf
			send     4
			pushi    #dispose
			pushi    0
			lat      temp2
			send     4
			pushi    #dispose
			pushi    0
			lat      temp4
			send     4
			pushi    #dispose
			pushi    0
			lat      temp5
			send     4
			pushi    #dispose
			pushi    0
			lat      temp3
			send     4
			pushi    1
			pushi    64993
			callk    DisposeScript,  2
			pToa     plane
			sat      temp6
			pushi    #dispose
			pushi    0
			self     4
			pushi    #dispose
			pushi    0
			lat      temp6
			send     4
			pushi    0
			callk    FrameOut,  0
			pushi    2
			pushi    64990
			lst      temp1
			callk    DisposeScript,  4
			ret     
		)
	)
	
	(method (update param1 param2 param3 &tmp butbuf1Data newStr)
		(if
			(==
				(= numGames
					(GetSaveFiles
						(theGame name?)
						(param2 data?)
						(param3 data?)
					)
				)
				-1
			)
			(return 0)
		)
		(= theStatus (localproc_0c30))
		(= newStr (Str new:))
		(switch theStatus
			(0
				(Message msgGET -546 10 0 0 1 (newStr data?))
			)
			(1
				(Message msgGET -546 11 0 0 1 (newStr data?))
			)
			(else 
				(Message msgGET -546 30 0 0 1 (newStr data?))
			)
		)
		(textI
			text: (KString 8 (newStr data?))
			x: 13
			y: 12
			setSize: 240
			moveTo: 4 4
		)
		(newStr dispose:)
		(deleteI state: (if (not numGames) 0 else 3))
		(if (== theStatus 1)
			(editI
				x: 13
				y: 5
				back: 255
				skip: 255
				font: smallFont
				text: ((param1 copy: {}) data?)
				width: 26
				setSize:
				moveTo: 4 30
			)
			(if (not (self contains: editI))
				(self add: editI)
				(if (self contains: okI)
					(editI plane: (self plane?))
					(editI draw:)
					(AddScreenItem editI)
				)
			)
		else
			(editI text: 0)
			(if (self contains: editI)
				(self delete: editI)
				(DeleteScreenItem editI)
				(if (editI bitmap?)
					(Bitmap 1 (editI bitmap?))
					(editI bitmap: 0)
				)
			)
		)
		(if
			(and
				(selectorI textList?)
				((selectorI textList?) size:)
			)
			(selectorI setText: 0)
		)
		(selectorI
			current: 0
			font: smallFont
			width: 158
			setText: param2
			setSize:
			moveTo: 4 44
			state: 2
		)
		(= butbuf1Data (butbuf1 data?))
		(switch theStatus
			(0
				(Message msgGET -546 26 0 0 1 butbuf1Data)
			)
			(1
				(Message msgGET -546 28 0 0 1 butbuf1Data)
			)
			(else 
				(Message msgGET -546 25 0 0 1 butbuf1Data)
			)
		)
		(= i (+ (selectorI nsRight?) 55))
		(okI
			x: 0
			y: 0
			text: (KString 8 butbuf1Data)
			setSize:
			moveTo: i (selectorI nsTop?)
			state:
				(if
					(or
						(and (== theStatus 0) (not numGames))
						(== theStatus 3)
					)
					0
				else
					3
				)
		)
		(if (self contains: okI)
			(textI draw:)
			(okI draw:)
			(selectorI draw:)
			(deleteI draw:)
			(UpdateScreenItem okI)
			(UpdateScreenItem textI)
			(UpdateScreenItem selectorI)
			(UpdateScreenItem deleteI)
			(FrameOut)
		)
		(return 1)
	)
)

(class Restore of SRDialog
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
		text 0
		font 0
		theItem 0
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		ticks 0
		caller 0
		plane 0
		eatTheMice 0
		lastTicks 0
		mouseHiliting 0
		margin 4
		modeless 0
		onScreen 0
	)
	
	(method (init)
		(= SRbuf (Str new:))
		(Message msgGET -546 20 0 0 1 (SRbuf data?))
		(= text (SRbuf data?))
		(super init: &rest)
	)
)

(class Save of SRDialog
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
		text 0
		font 0
		theItem 0
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		ticks 0
		caller 0
		plane 0
		eatTheMice 0
		lastTicks 0
		mouseHiliting 0
		margin 4
		modeless 0
		onScreen 0
	)
	
	(method (init)
		(= SRbuf (Str new:))
		(Message msgGET -546 21 0 0 1 (SRbuf data?))
		(= text (SRbuf data?))
		(super init: &rest)
	)
)

(instance selectorI of DSelector
	(properties
		length 8
	)
	
	(method (draw &tmp temp0 newStr)
		(super draw: &rest)
		(if
		(and (editI text?) (textList size:) (editI bitmap?))
			(= temp0 ((= newStr (Str new:)) data?))
			(newStr data: (editI text?))
			(newStr format: {%s} ((textList at: current) text?))
			(editI text: (newStr data?) draw:)
			(newStr data: temp0 dispose:)
		)
	)
	
	(method (updatePlane &tmp temp0 temp1 temp2)
		(listPlane
			posn: (listPlane left:) (listPlane top?) plane
		)
		(UpdatePlane listPlane)
		(= temp0
			(-
				(+
					(/ (- (listPlane bottom?) (listPlane top?)) 2)
					(listPlane top?)
				)
				(plane top?)
			)
		)
		(= temp1 (+ (- (listPlane right:) (plane left:)) 19))
		(= temp2
			(CelHigh
				(upButton view?)
				(upButton loop?)
				(upButton cel?)
			)
		)
		(upButton moveTo: temp1 (- temp0 (+ temp2 4)))
		(UpdateScreenItem upButton)
		(downButton moveTo: temp1 (+ temp0 4))
		(UpdateScreenItem downButton)
	)
	
	(method (setText param1 &tmp temp0 newStr temp2 temp3)
		(if (and param1 (param1 size:))
			(= temp0 0)
			(while (param1 at: temp0)
				(super setText: (= newStr (param1 subStr: temp0 36)))
				(newStr dispose:)
				(= temp0 (+ temp0 36))
			)
		else
			(super setText: 0)
		)
		(if (and param1 (editI text?) (editI bitmap?))
			(= temp3 ((= newStr (Str new:)) data?))
			(newStr data: (editI text?))
			(if (not (textList size:))
				(newStr format: {})
			else
				(newStr format: {%s} ((textList at: current) text?))
			)
			(editI text: (newStr data?) draw:)
			(newStr data: temp3 dispose:)
		)
	)
)

(instance editI of DEdit
	(properties
		width 29
	)
)

(class SRDButton of DButton
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0003
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -546
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $0000
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		type $0002
		key 0
		value 0
		object 0
		selector 0
		textLeft 0
		textTop 0
		textRight 0
		textBottom 0
		text 0
		mode 1
		fore 0
		back 0
		skip 254
		font 999
		borderColor 0
		dimmed 0
		rects 0
	)
)

(instance okI of SRDButton
	(properties)
)

(instance cancelI of SRDButton
	(properties)
)

(instance deleteI of SRDButton
	(properties)
)

(instance changeDirI of SRDButton
	(properties
		loop 1
	)
)

(instance textI of DText
	(properties
		font 999
	)
)

(instance oldName of DText
	(properties
		borderColor 0
	)
)
