;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64893)
(include sci.sh)
(use Main)
(use TiledBitmap)
(use GenDialog)
(use Plane)
(use String)
(use Actor)
(use System)


(class MenuItem of View
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
		state $0000
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
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $5021
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
		oNotify 0
		nSelectMethod 755
		chShortcut 0
		nModifiers 4
		userData 0
		nModule -1
		nNoun 0
		nVerb 0
		nCase 0
		nSeq 1
		oText 0
		bHilited 0
		bChecked 0
		nWidth 0
		nHeight 0
		oChildren 0
		oParent 0
		oHandler 0
		nLevel 0
		voCheck 0
		nMinTextWidth 0
		bmpOff 0
		bmpOn 0
		oRightString 0
		oLeftString 0
		nTextHeight 0
		nRightWidth 0
		nLeftWidth 0
		nGapWidth 0
	)
	
	(method (init theOParent theOHandler theNLevel)
		(= oParent theOParent)
		(= oHandler theOHandler)
		(= oChildren (List new:))
		(= nLevel theNLevel)
		(= bChecked (= bHilited 0))
		(self addItems:)
		(self setup: oHandler)
		(super init: &rest)
		(self forceCursor: (ScriptID 64006 0))
		(oChildren
			eachElementDo: #init self oHandler (+ nLevel 1) &rest
		)
	)
	
	(method (dispose)
		(if oChildren
			(oChildren release:)
			(oChildren dispose:)
			(= oChildren 0)
		)
		(if oText (proc64896_7 oText) (= oText 0))
		(if oLeftString
			(proc64896_7 oLeftString)
			(= oLeftString 0)
		)
		(if oRightString
			(proc64896_7 oRightString)
			(= oRightString 0)
		)
		(if bitmap
			(if bmpOff
				(= bitmap 0)
				(Bitmap 1 bmpOff)
				(= bmpOff 0)
				(Bitmap 1 bmpOn)
				(= bmpOn 0)
			else
				(Bitmap 1 bitmap)
				(= bitmap 0)
			)
		)
		(super dispose: &rest)
	)
	
	(method (show)
		(if (and (!= (oHandler nStyle?) 0) (not bitmap))
			(self setup:)
		)
		(self checkStatus:)
		(super show: &rest)
		(if voCheck (voCheck show:))
	)
	
	(method (handleEvent event)
		(if (== (event type?) evKEYBOARD)
			(if
				(and
					(== (event message?) chShortcut)
					(== (event modifiers?) nModifiers)
				)
				(oHandler hide:)
				(event claimed: 1)
				(self doSelect:)
			else
				(oChildren eachElementDo: #handleEvent event)
			)
			(return (event claimed?))
		)
		(return
			(if (self onMe: event)
				(if (== (event type?) evMOUSEBUTTON)
					(gOEventHandler registerEventHog: oHandler)
					(oHandler bMouseDown: 1)
				)
				(cond 
					((or (oHandler bPopup?) (> nLevel 1)) (self hilite:))
					((oHandler bMouseDown?) (self hilite:))
				)
				(if
					(and
						(== (event type?) evMOUSERELEASE)
						(== (oChildren size:) 0)
					)
					(self unhilite:)
					(oHandler hide:)
					(oHandler nReturn: userData)
					(self doSelect:)
				)
				(event claimed: 1)
				(return 1)
			else
				(if (== (oChildren size:) 0) (self unhilite:))
				(if bHilited
					(oChildren eachElementDo: #handleEvent event)
				)
				(return (event claimed?))
			)
		)
	)
	
	(method (posn param1 param2)
		(super posn: param1 param2 &rest)
		(if voCheck
			(voCheck posn: param1 param2)
			(if (voCheck isNotHidden:) (UpdateScreenItem voCheck))
			(SetNowSeen voCheck)
		)
		(if (self isNotHidden:)
			(UpdateScreenItem self)
			(SetNowSeen self)
		)
	)
	
	(method (setCel &tmp theCel)
		(cond 
			((== (oHandler nStyle?) 0)
				(= theCel 0)
				(if bHilited (= theCel (+ theCel 1)))
				(if (!= cel theCel) (= cel theCel))
			)
			(bHilited (= bitmap bmpOn))
			(else (= bitmap bmpOff))
		)
		(cond 
			(bChecked
				(if (not voCheck)
					(= voCheck (oHandler getCheckView: self))
				)
			)
			(voCheck (voCheck dispose:) (= voCheck 0))
		)
		(if (self isNotHidden:) (UpdateScreenItem self))
	)
	
	(method (hide &tmp temp0)
		(if (not (self isNotHidden:)) (return))
		(self unhilite:)
		(oChildren eachElementDo: #hide)
		(super hide: &rest)
		(if voCheck (voCheck hide:))
		(if (and (!= (oHandler nStyle?) 0) bitmap)
			(if bmpOff
				(= bitmap 0)
				(Bitmap 1 bmpOff)
				(= bmpOff 0)
				(Bitmap 1 bmpOn)
				(= bmpOn 0)
			else
				(Bitmap 1 bitmap)
				(= bitmap 0)
			)
		)
	)
	
	(method (addItems)
	)
	
	(method (doSelect)
		(if oNotify (Eval oNotify nSelectMethod userData))
	)
	
	(method (checkStatus)
	)
	
	(method (hilite)
		(= bHilited 1)
		(self setCel:)
		(oChildren eachElementDo: #show)
		(oChildren eachElementDo: #unhilite)
		(oParent doAllButMe: self 977)
	)
	
	(method (unhilite)
		(= bHilited 0)
		(self setCel:)
		(oChildren eachElementDo: #hide)
	)
	
	(method (check)
		(= bChecked 1)
		(self setCel:)
	)
	
	(method (uncheck)
		(= bChecked 0)
		(self setCel:)
	)
	
	(method (addChild param1)
		(oChildren addToEnd: param1)
		(self setChildWidths:)
		(param1
			init: self oHandler (+ nLevel 1) (oHandler plane?) &rest
		)
		(if (not (self isNotHidden:)) (param1 hide:))
		(oHandler setChildPos:)
	)
	
	(method (doAllButMe param1 param2 &tmp oChildrenFirst temp1)
		(= oChildrenFirst (oChildren first:))
		(while oChildrenFirst
			(if (!= (= temp1 (KList 8 oChildrenFirst)) param1)
				(temp1 param2: &rest)
			)
			(= oChildrenFirst (oChildren next: oChildrenFirst))
		)
	)
	
	(method (setChildPos param1 param2 &tmp oChildrenFirst temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9 oChildrenSize)
		(if (or (not oChildren) (not (oChildren size:)))
			(return)
		)
		(= temp6 0)
		(= temp7 ((oChildren at: 0) nWidth?))
		(= oChildrenSize (oChildren size:))
		(= temp9 0)
		(while (< temp9 oChildrenSize)
			(= temp6 (+ temp6 ((oChildren at: temp9) nHeight?)))
			(++ temp9)
		)
		(= temp4 (+ param1 temp7))
		(= temp8 (+ param2 temp6))
		(if (< param1 0) (= param1 0))
		(if (< param2 0) (= param2 0))
		(if (> temp4 lastScreenX)
			(= param1 (- param1 (- temp4 lastScreenX)))
		)
		(if (> temp8 lastScreenY)
			(= param2 (- param2 (- temp8 lastScreenY)))
		)
		(= oChildrenFirst (oChildren first:))
		(while oChildrenFirst
			((= temp1 (KList 8 oChildrenFirst)) posn: param1 param2)
			(= temp2 (temp1 oChildren?))
			(= temp5 1)
			(if (and temp2 (temp2 size:))
				(= temp3 ((temp2 at: 0) nWidth?))
				(if
					(>
						(= temp4 (+ param1 (temp1 nWidth?) temp3))
						lastScreenX
					)
					(= temp5 0)
				)
			)
			(if temp5
				(temp1 setChildPos: (+ param1 (temp1 nWidth?)) param2)
			else
				(temp1 setChildPos: (- param1 temp3) param2)
			)
			(= param2 (+ param2 (temp1 nHeight?)))
			(= oChildrenFirst (oChildren next: oChildrenFirst))
		)
	)
	
	(method (setChildWidths &tmp temp0 temp1 oChildrenFirst temp3)
		(= temp1 0)
		(= oChildrenFirst (oChildren first:))
		(while oChildrenFirst
			(if
				(>
					(= temp0
						((= temp3 (KList 8 oChildrenFirst))
							calcTextWidths: oHandler
						)
					)
					temp1
				)
				(= temp1 temp0)
			)
			(= oChildrenFirst (oChildren next: oChildrenFirst))
		)
		(oChildren eachElementDo: #setMinTextWidth temp1)
	)
	
	(method (setMinTextWidth theNMinTextWidth)
		(= nMinTextWidth theNMinTextWidth)
		(= nWidth
			(+
				(oHandler nHBorder?)
				(self getCheckWidth:)
				nMinTextWidth
				(oHandler nHBorder?)
			)
		)
		(= nHeight
			(-
				(+
					(oHandler nVBorder?)
					nTextHeight
					(oHandler nVBorder?)
				)
				(oHandler nLeading?)
			)
		)
	)
	
	(method (calcTextWidths theOHandler &tmp theOText temp1 oTextSize)
		(if argc (= oHandler theOHandler))
		(if (not oText)
			(= oText
				(MakeMessageText nNoun nVerb nCase nSeq nModule)
			)
			(if (not oText) (= oText (Str with: {default})))
		)
		(if (not (IsStringObject oText))
			(= theOText oText)
			(= oText (Str with: theOText))
			(proc64896_7 theOText)
		)
		(if (not oLeftString)
			(= temp1 (oText indexOf: 124))
			(= oTextSize (oText size:))
			(if (== temp1 -1)
				(= oLeftString (Str with: oText))
				(= oRightString 0)
			else
				(= oLeftString (oText subStr: 0 temp1))
				(if (== (++ temp1) oTextSize)
					(= oRightString 0)
				else
					(= oRightString
						(oText subStr: temp1 (- (oText size:) temp1))
					)
				)
			)
			(= nTextHeight
				(GetTextWidth_2 oLeftString (oHandler nFont?) 0)
			)
			(= nLeftWidth
				(GetTextWidth oLeftString (oHandler nFont?) 0)
			)
			(if oRightString
				(= nRightWidth
					(GetTextWidth oRightString (oHandler nFont?) 0)
				)
				(= nGapWidth (GetTextWidth {W} (oHandler nFont?) 0))
			else
				(= nGapWidth (= nRightWidth 0))
			)
		)
		(return (+ nLeftWidth nGapWidth nRightWidth))
	)
	
	(method (setup &tmp [temp0 2] temp2 temp3 temp4 temp5 temp6 temp7)
		(asm
			_line_   177
			_file_   {filename}
			_line_   179
			pushi    #nStyle
			pushi    0
			pToa     oHandler
			send     4
			push    
			ldi      0
			eq?     
			bnt      code_042c
			_line_   180
			pushi    3
			pTos     view
			pTos     loop
			pushi    0
			callk    CelWide,  6
			aTop     nWidth
			_line_   181
			pushi    3
			pTos     view
			pTos     loop
			pushi    0
			callk    CelHigh,  6
			aTop     nHeight
			_line_   182
			ret     
code_042c:
			_line_   185
			pToa     nLeftWidth
			not     
			bnt      code_043e
			_line_   186
			pushi    #calcTextWidths
			pushi    0
			self     4
code_043e:
			_line_   189
			pushi    #setChildWidths
			pushi    0
			self     4
			_line_   191
			pTos     nMinTextWidth
			ldi      0
			eq?     
			bnt      code_0460
			_line_   192
			pTos     nLeftWidth
			pToa     nGapWidth
			add     
			push    
			pToa     nRightWidth
			add     
			aTop     nMinTextWidth
code_0460:
			_line_   195
			pushi    #nHBorder
			pushi    0
			pToa     oHandler
			send     4
			push    
			pushi    #getCheckWidth
			pushi    0
			self     4
			add     
			push    
			pToa     nMinTextWidth
			add     
			push    
			pushi    #nHBorder
			pushi    0
			pToa     oHandler
			send     4
			add     
			aTop     nWidth
			_line_   196
			pushi    #nVBorder
			pushi    0
			pToa     oHandler
			send     4
			push    
			pToa     nTextHeight
			add     
			push    
			pushi    #nVBorder
			pushi    0
			pToa     oHandler
			send     4
			add     
			push    
			pushi    #nLeading
			pushi    0
			pToa     oHandler
			send     4
			sub     
			aTop     nHeight
			_line_   197
			pushi    #nHBorder
			pushi    0
			pToa     oHandler
			send     4
			push    
			pushi    #getCheckWidth
			pushi    0
			self     4
			add     
			sat      temp4
			_line_   198
			pushi    #nVBorder
			pushi    0
			pToa     oHandler
			send     4
			sat      temp5
			_line_   202
			pToa     bitmap
			bnt      code_0523
			_line_   203
			pToa     bmpOff
			bnt      code_050e
			_line_   205
			ldi      0
			aTop     bitmap
			_line_   206
			pushi    2
			pushi    1
			pTos     bmpOff
			callk    Bitmap,  4
			_line_   207
			ldi      0
			aTop     bmpOff
			_line_   208
			pushi    2
			pushi    1
			pTos     bmpOn
			callk    Bitmap,  4
			_line_   209
			ldi      0
			aTop     bmpOn
			jmp      code_0523
code_050e:
			_line_   210
			_line_   212
			pushi    2
			pushi    1
			pTos     bitmap
			callk    Bitmap,  4
			_line_   213
			ldi      0
			aTop     bitmap
code_0523:
			_line_   217
			pushi    #nStyle
			pushi    0
			pToa     oHandler
			send     4
			push    
			dup     
			_line_   218
			ldi      1
			eq?     
			bnt      code_05f9
			_line_   221
			pushi    8
			pushi    0
			_line_   222
			pTos     nWidth
			pTos     nHeight
			_line_   223
			pushi    255
			pushi    #nUnhilitedBack
			pushi    0
			pToa     oHandler
			send     4
			push    
			_line_   224
			pushi    640
			pushi    480
			_line_   225
			pushi    1
			callk    Bitmap,  16
			aTop     bmpOff
			_line_   229
			pushi    7
			pushi    5
			pTos     bmpOff
			_line_   230
			pushi    0
			pushi    0
			_line_   231
			pTos     nWidth
			ldi      1
			sub     
			push    
			pTos     nHeight
			ldi      1
			sub     
			push    
			_line_   232
			pushi    #nUnhilitedBack
			pushi    0
			pToa     oHandler
			send     4
			push    
			callk    Bitmap,  14
			_line_   236
			pushi    8
			pushi    0
			_line_   237
			pTos     nWidth
			pTos     nHeight
			_line_   238
			pushi    255
			pushi    #nHilitedBack
			pushi    0
			pToa     oHandler
			send     4
			push    
			_line_   239
			pushi    640
			pushi    480
			_line_   240
			pushi    1
			callk    Bitmap,  16
			aTop     bmpOn
			_line_   244
			pushi    7
			pushi    5
			pTos     bmpOn
			_line_   245
			pushi    0
			pushi    0
			_line_   246
			pTos     nWidth
			ldi      1
			sub     
			push    
			pTos     nHeight
			ldi      1
			sub     
			push    
			_line_   247
			pushi    #nHilitedBack
			pushi    0
			pToa     oHandler
			send     4
			push    
			callk    Bitmap,  14
			jmp      code_06e0
code_05f9:
			dup     
			_line_   251
			ldi      2
			eq?     
			bnt      code_06e0
			_line_   252
			pushi    #init
			pushi    5
			pushi    #vUnhilited
			pushi    0
			pToa     oHandler
			send     4
			push    
			pTos     nWidth
			pTos     nHeight
			pushi    0
			pushi    #bBorder
			pushi    0
			pToa     oHandler
			send     4
			push    
			class    TiledBitmap
			send     14
			_line_   253
			pushi    #getBitmap
			pushi    1
			pushi    1
			class    TiledBitmap
			send     6
			aTop     bmpOff
			_line_   254
			pushi    #nWidth
			pushi    0
			class    TiledBitmap
			send     4
			sat      temp2
			_line_   255
			pushi    #nHeight
			pushi    0
			class    TiledBitmap
			send     4
			sat      temp3
			_line_   257
			pushi    #init
			pushi    5
			pushi    #vHilited
			pushi    0
			pToa     oHandler
			send     4
			push    
			pTos     nWidth
			pTos     nHeight
			pushi    0
			pushi    #bBorder
			pushi    0
			pToa     oHandler
			send     4
			push    
			class    TiledBitmap
			send     14
			_line_   258
			pushi    #getBitmap
			pushi    1
			pushi    1
			class    TiledBitmap
			send     6
			aTop     bmpOn
			_line_   259
			lst      temp4
			pushi    #nOffsetX
			pushi    0
			class    TiledBitmap
			send     4
			add     
			sat      temp4
			_line_   260
			lst      temp5
			pushi    #nOffsetY
			pushi    0
			class    TiledBitmap
			send     4
			add     
			sat      temp5
			_line_   261
			pushi    #nWidth
			pushi    0
			class    TiledBitmap
			send     4
			aTop     nWidth
			_line_   262
			pushi    #nHeight
			pushi    0
			class    TiledBitmap
			send     4
			aTop     nHeight
			_line_   263
			_line_   264
			pTos     nWidth
			lat      temp2
			ne?     
			bt       code_06e0
			_line_   265
			pTos     nHeight
			lat      temp3
			ne?     
			bnt      code_06e0
code_06e0:
			toss    
			_line_   273
			pushi    14
			pushi    4
			pTos     bmpOff
			_line_   274
			pushi    #data
			pushi    0
			pToa     oLeftString
			send     4
			push    
			_line_   275
			lst      temp4
			lst      temp5
			_line_   276
			lst      temp4
			pToa     nLeftWidth
			add     
			push    
			ldi      1
			sub     
			push    
			lst      temp5
			pToa     nTextHeight
			add     
			push    
			ldi      1
			sub     
			push    
			_line_   277
			pushi    #nUnhilitedFore
			pushi    0
			pToa     oHandler
			send     4
			push    
			pushi    255
			dup     
			_line_   278
			pushi    #nFont
			pushi    0
			pToa     oHandler
			send     4
			push    
			pushi    0
			_line_   279
			pushi    255
			_line_   280
			pushi    0
			callk    Bitmap,  28
			_line_   284
			pushi    14
			pushi    4
			pTos     bmpOn
			_line_   285
			pushi    #data
			pushi    0
			pToa     oLeftString
			send     4
			push    
			_line_   286
			lst      temp4
			lst      temp5
			_line_   287
			lst      temp4
			pToa     nLeftWidth
			add     
			push    
			ldi      1
			sub     
			push    
			lst      temp5
			pToa     nTextHeight
			add     
			push    
			ldi      1
			sub     
			push    
			_line_   288
			pushi    #nHilitedFore
			pushi    0
			pToa     oHandler
			send     4
			push    
			pushi    255
			dup     
			_line_   289
			pushi    #nFont
			pushi    0
			pToa     oHandler
			send     4
			push    
			pushi    0
			_line_   290
			pushi    255
			_line_   291
			pushi    0
			callk    Bitmap,  28
			_line_   294
			pToa     oRightString
			bnt      code_0877
			_line_   295
			lst      temp4
			pToa     nMinTextWidth
			add     
			push    
			pToa     nRightWidth
			sub     
			sat      temp6
			_line_   297
			pushi    14
			pushi    4
			pTos     bmpOff
			_line_   298
			pushi    #data
			pushi    0
			pToa     oRightString
			send     4
			push    
			_line_   299
			lst      temp6
			lst      temp5
			_line_   300
			lst      temp6
			pToa     nRightWidth
			add     
			push    
			ldi      1
			sub     
			push    
			lst      temp5
			pToa     nTextHeight
			add     
			push    
			ldi      1
			sub     
			push    
			_line_   301
			pushi    #nUnhilitedFore
			pushi    0
			pToa     oHandler
			send     4
			push    
			pushi    255
			dup     
			_line_   302
			pushi    #nFont
			pushi    0
			pToa     oHandler
			send     4
			push    
			pushi    0
			_line_   303
			pushi    255
			_line_   304
			pushi    0
			callk    Bitmap,  28
			_line_   308
			pushi    14
			pushi    4
			pTos     bmpOn
			_line_   309
			pushi    #data
			pushi    0
			pToa     oRightString
			send     4
			push    
			_line_   310
			lst      temp6
			lst      temp5
			_line_   311
			lst      temp6
			pToa     nRightWidth
			add     
			push    
			ldi      1
			sub     
			push    
			lst      temp5
			pToa     nTextHeight
			add     
			push    
			ldi      1
			sub     
			push    
			_line_   312
			pushi    #nHilitedFore
			pushi    0
			pToa     oHandler
			send     4
			push    
			pushi    255
			dup     
			_line_   313
			pushi    #nFont
			pushi    0
			pToa     oHandler
			send     4
			push    
			pushi    0
			_line_   314
			pushi    255
			_line_   315
			pushi    0
			callk    Bitmap,  28
code_0877:
			_line_   320
			pToa     bmpOff
			aTop     bitmap
			_line_   321
			ret     
		)
	)
	
	(method (getCheckWidth)
		(return
			(if (and (not (oHandler bPopup?)) (== nLevel 1))
				(return 0)
			else
				(return (oHandler getCheckWidth:))
			)
		)
	)
)

(class MenuHeader of MenuItem
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
		state $0000
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
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $5021
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
		oNotify 0
		nSelectMethod 755
		chShortcut 0
		nModifiers 4
		userData 0
		nModule -1
		nNoun 0
		nVerb 0
		nCase 0
		nSeq 1
		oText 0
		bHilited 0
		bChecked 0
		nWidth 0
		nHeight 0
		oChildren 0
		oParent 0
		oHandler 0
		nLevel 0
		voCheck 0
		nMinTextWidth 0
		bmpOff 0
		bmpOn 0
		oRightString 0
		oLeftString 0
		nTextHeight 0
		nRightWidth 0
		nLeftWidth 0
		nGapWidth 0
	)
	
	(method (handleEvent event)
		(if (self onMe: event) (event claimed: 1))
		(event claimed?)
	)
	
	(method (setCel)
	)
	
	(method (setup &tmp [temp0 4] temp4 oHandlerNVBorder [temp6 2] temp8)
		(if (== (oHandler nStyle?) 0)
			(= nWidth (CelWide view loop 0))
			(= nHeight (CelHigh view loop 0))
			(return)
		)
		(if (not nLeftWidth) (self calcTextWidths:))
		(self setChildWidths:)
		(if (== nMinTextWidth 0)
			(= nMinTextWidth (+ nLeftWidth nGapWidth nRightWidth))
		)
		(= nWidth
			(+
				(oHandler nHBorder?)
				(self getCheckWidth:)
				nMinTextWidth
				(oHandler nHBorder?)
			)
		)
		(= nHeight
			(-
				(+
					(oHandler nVBorder?)
					nTextHeight
					(oHandler nVBorder?)
				)
				(oHandler nLeading?)
			)
		)
		(= temp4
			(+ (oHandler nHBorder?) (self getCheckWidth:))
		)
		(= oHandlerNVBorder (oHandler nVBorder?))
		(if bitmap
			(if bmpOff
				(= bitmap 0)
				(Bitmap 1 bmpOff)
				(= bmpOff 0)
				(Bitmap 1 bmpOn)
				(= bmpOn 0)
			else
				(Bitmap 1 bitmap)
				(= bitmap 0)
			)
		)
		(switch (oHandler nStyle?)
			(1
				(= bitmap
					(Bitmap
						0
						nWidth
						nHeight
						255
						(oHandler nHeaderBack?)
						640
						480
						1
					)
				)
				(Bitmap
					5
					bitmap
					0
					0
					(- nWidth 1)
					(- nHeight 1)
					(oHandler nHeaderBack?)
				)
			)
			(2
				(TiledBitmap
					init: (oHandler vUnhilited?) nWidth nHeight 0 (oHandler bBorder?)
				)
				(= bitmap (TiledBitmap getBitmap: 1))
				(= temp4 (+ temp4 (TiledBitmap nOffsetX?)))
				(= oHandlerNVBorder
					(+ oHandlerNVBorder (TiledBitmap nOffsetY?))
				)
				(= nWidth (TiledBitmap nWidth?))
				(= nHeight (TiledBitmap nHeight?))
			)
		)
		(= temp8 (/ (- nMinTextWidth nLeftWidth) 2))
		(Bitmap
			4
			bitmap
			(oLeftString data?)
			(+ temp4 temp8)
			oHandlerNVBorder
			(- (+ temp4 temp8 nLeftWidth) 1)
			(- (+ oHandlerNVBorder nTextHeight) 1)
			(oHandler nHeaderFore?)
			255
			255
			(oHandler nFont?)
			0
			255
			0
		)
	)
)

(class MenuHandler of Obj
	(properties
		scratch 0
		nStyle 0
		bPopup 0
		nUnhilitedBack 0
		nHilitedBack 1
		vHilited -1
		vUnhilited -1
		bBorder 0
		nHilitedFore 1
		nUnhilitedFore 0
		nFont 0
		nLeading 2
		nVBorder 3
		nHBorder 5
		nHeaderBack 0
		nHeaderFore 1
		plane 0
		oChildren 0
		oCast 0
		bMouseDown 0
		vCheck -1
		lCheck 0
		cCheck 0
		nDisableCount 0
		nReturn 0
	)
	
	(method (init theOCast)
		(super init: &rest)
		(if (or (== argc 0) (not theOCast))
			(= oCast (Cast new:))
		else
			(= oCast theOCast)
		)
		(= plane (oCast plane?))
		(if (not plane)
			(= plane ((Plane new:) init: 0 0 640 480 yourself:))
		)
		(plane addCast: oCast)
		(= bMouseDown 0)
		(gOEventHandler registerKeyHandler: self)
		(plane setPri: -1)
		(plane picture: -2)
		(UpdatePlane plane)
		(= oChildren (List new:))
		(self addItems:)
		(oChildren eachElementDo: #init self self 1 oCast)
		(self setChildPos:)
		(self hide:)
	)
	
	(method (dispose)
		(gOEventHandler unregisterEventHog: self)
		(gOEventHandler unregisterKeyHandler: self)
		(if plane (plane dispose:))
		(= plane (= oCast 0))
		(super dispose: &rest)
	)
	
	(method (show)
		(if nDisableCount (return))
		(plane setPri: 500)
		(UpdatePlane plane)
		(= bMouseDown 0)
		(oChildren eachElementDo: #unhilite)
		(oChildren eachElementDo: #show)
	)
	
	(method (addItems)
	)
	
	(method (hide)
		(if gOEventHandler
			(gOEventHandler unregisterEventHog: self)
		)
		(plane setPri: -1)
		(UpdatePlane plane)
		(oChildren eachElementDo: #hide)
	)
	
	(method (enable param1)
		(if plane
			(if (and argc param1)
				(= nDisableCount 0)
			else
				(= nDisableCount (Max (- nDisableCount 1) 0))
			)
		)
	)
	
	(method (disable)
		(if plane (++ nDisableCount) (self hide:))
	)
	
	(method (addChild param1)
		(oChildren addToEnd: param1)
		(self setChildWidths:)
		(param1 init: self self 1 plane &rest)
		(if (== (plane priority?) -1) (param1 hide:))
		(self setChildPos:)
	)
	
	(method (showPopup &tmp newEvent theNReturn)
		(if nDisableCount (return nDisableCount))
		(plane setPri: 500)
		(UpdatePlane plane)
		(= bMouseDown 1)
		(oChildren eachElementDo: #unhilite)
		(oChildren eachElementDo: #show)
		(plane doit:)
		(FrameOut)
		(gOEventHandler registerEventHog: self)
		(while (== (plane priority?) 500)
			(= newEvent (Event new:))
			(self handleEvent: newEvent)
			(newEvent dispose:)
			(plane doit:)
			(FrameOut)
		)
		(= theNReturn nReturn)
		(self dispose:)
		(return theNReturn)
	)
	
	(method (posn param1 param2)
		(if (or (not oChildren) (not (oChildren size:)))
			(MonoOut {Trying to position empty menu. DJM menus.sc})
			(return)
		)
		(self setChildPos: param1 param2)
	)
	
	(method (posnAt param1 theTheMouseX theTheMouseY &tmp theMouseX theMouseY temp2 temp3 oChildrenSize temp5 temp6 temp7 temp8 temp9 [temp10 2])
		(if (not argc)
			(MonoOut {illegal call of posnOver.})
			(return)
		)
		(if (not bPopup)
			(MonoOut {Attempt to use posnOver on non-popup menu})
			(return)
		)
		(if (or (not oChildren) (not (oChildren size:)))
			(return)
		)
		(if (< argc 3)
			(= theMouseX mouseX)
			(= theMouseY mouseY)
		else
			(= theMouseX theTheMouseX)
			(= theMouseY theTheMouseY)
		)
		(= temp5 ((oChildren at: 0) nWidth?))
		(= temp6 0)
		(= oChildrenSize (oChildren size:))
		(= temp7 0)
		(while (< temp7 oChildrenSize)
			(= temp6 (+ temp6 ((oChildren at: temp7) nHeight?)))
			(++ temp7)
		)
		(= temp2 (/ temp5 2))
		(= temp3 0)
		(if (> param1 (- oChildrenSize 1))
			(= param1 (- oChildrenSize 1))
		)
		(= temp7 0)
		(while (< temp7 param1)
			(= temp3 (+ temp3 ((oChildren at: temp7) nHeight?)))
			(++ temp7)
		)
		(= temp3
			(+ temp3 (/ ((oChildren at: param1) nHeight?) 2))
		)
		(if
		(> (= temp9 (- (+ theMouseY temp6) temp3)) lastScreenY)
			(= temp3
				(-
					temp6
					(/ ((oChildren at: (- oChildrenSize 1)) nHeight?) 2)
				)
			)
		)
		(self posn: (- theMouseX temp2) (- theMouseY temp3))
	)
	
	(method (isHilited)
		(if (not oChildren) (return 0))
		(if (oChildren firstTrue: #bHilited) (return 1))
		(return 0)
	)
	
	(method (isVisible)
		(return (== (plane priority?) 500))
	)
	
	(method (getWidth &tmp oChildrenFirst temp1 temp2)
		(return
			(if oChildren
				(= temp2 0)
				(= oChildrenFirst (oChildren first:))
				(while oChildrenFirst
					(= temp1 (KList 8 oChildrenFirst))
					(= temp2 (+ temp2 (temp1 nWidth?)))
					(= oChildrenFirst (oChildren next: oChildrenFirst))
				)
				(return temp2)
			else
				(return 0)
			)
		)
	)
	
	(method (getHeight &tmp temp0)
		(return
			(if oChildren
				(return ((= temp0 (oChildren at: 0)) nHeight?))
			else
				(return 0)
			)
		)
	)
	
	(method (doAllButMe param1 param2 &tmp oChildrenFirst temp1)
		(= oChildrenFirst (oChildren first:))
		(while oChildrenFirst
			(if (!= (= temp1 (KList 8 oChildrenFirst)) param1)
				(temp1 param2: &rest)
			)
			(= oChildrenFirst (oChildren next: oChildrenFirst))
		)
	)
	
	(method (handleEvent event)
		(if (or nDisableCount (not (user canControl:)))
			(return 0)
		)
		(if (== (event type?) evMOUSEBUTTON) (= bMouseDown 1))
		(if (== (event type?) evMOUSERELEASE) (= bMouseDown 0))
		(oChildren eachElementDo: #handleEvent event &rest)
		(cond 
			(
				(and
					(not (event claimed?))
					(or
						(== (event type?) evMOUSEBUTTON)
						(and (== (event type?) evMOUSERELEASE) bPopup)
					)
				)
				(oChildren eachElementDo: #unhilite)
				(gOEventHandler unregisterEventHog: self)
				(if bPopup (= nReturn -1) (self hide:))
			)
			((== curRoomNum 110) (curRoom notify: 3))
		)
		(if (!= (event type?) evKEYBOARD) (event claimed: 1))
		(return (event claimed?))
	)
	
	(method (stopHogging)
		(self hide:)
	)
	
	(method (setChildPos param1 param2 &tmp oChildrenFirst temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9 temp10 temp11 oChildrenSize)
		(if (not (= oChildrenFirst (oChildren first:)))
			(return)
		)
		(= temp1 (KList 8 oChildrenFirst))
		(if (< argc 2)
			(= temp2 (temp1 x?))
			(= temp3 (temp1 y?))
		else
			(= temp2 param1)
			(= temp3 param2)
		)
		(if bPopup
			(= temp8 0)
			(= temp9 ((oChildren at: 0) nWidth?))
			(= oChildrenSize (oChildren size:))
			(= temp11 0)
			(while (< temp11 oChildrenSize)
				(= temp8 (+ temp8 ((oChildren at: temp11) nHeight?)))
				(++ temp11)
			)
			(= temp6 (+ temp2 temp9))
			(= temp10 (+ temp3 temp8))
			(if (< temp2 0) (= temp2 0))
			(if (< temp3 0) (= temp3 0))
			(if (> temp6 lastScreenX)
				(= temp2 (- temp2 (- temp6 lastScreenX)))
			)
			(if (> temp10 lastScreenY)
				(= temp3 (- temp3 (- temp10 lastScreenY)))
			)
		)
		(while oChildrenFirst
			((= temp1 (KList 8 oChildrenFirst)) posn: temp2 temp3)
			(if bPopup
				(= temp4 (temp1 oChildren?))
				(= temp7 1)
				(if (and temp4 (temp4 size:))
					(= temp5 ((temp4 at: 0) nWidth?))
					(if
						(>
							(= temp6 (+ temp2 (temp1 nWidth?) temp5))
							lastScreenX
						)
						(= temp7 0)
					)
				)
				(if temp7
					(temp1 setChildPos: (+ temp2 (temp1 nWidth?)) temp3)
				else
					(temp1 setChildPos: (- temp2 temp5) temp3)
				)
				(= temp3 (+ temp3 (temp1 nHeight?)))
			else
				(temp1 setChildPos: temp2 (+ temp3 (temp1 nHeight?)))
				(= temp2 (+ temp2 (temp1 nWidth?)))
			)
			(= oChildrenFirst (oChildren next: oChildrenFirst))
		)
	)
	
	(method (getCheckView param1 &tmp temp0)
		(if (== vCheck -1) (return 0))
		(= temp0
			((View new:)
				view: vCheck
				loop: lCheck
				cel: cCheck
				setPri: 1000
				x: (param1 x?)
				y: (param1 y?)
				init: (param1 plane?)
				yourself:
			)
		)
		(if (not (param1 isNotHidden:)) (temp0 hide:))
		(return temp0)
	)
	
	(method (getCheckWidth)
		(return
			(if (== vCheck -1)
				(return 0)
			else
				(return (CelWide vCheck lCheck cCheck))
			)
		)
	)
	
	(method (setChildWidths &tmp temp0 temp1 oChildrenFirst temp3)
		(if (not bPopup) (return))
		(= temp1 0)
		(= oChildrenFirst (oChildren first:))
		(while oChildrenFirst
			(if
				(>
					(= temp0
						((= temp3 (KList 8 oChildrenFirst))
							calcTextWidths: self
						)
					)
					temp1
				)
				(= temp1 temp0)
			)
			(= oChildrenFirst (oChildren next: oChildrenFirst))
		)
		(oChildren eachElementDo: #setMinTextWidth temp1)
	)
)
