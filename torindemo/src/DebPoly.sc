;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64013)
(include sci.sh)
(use Main)
(use Set)
(use Event)
(use DText)
(use String)
(use Array)
(use Print)
(use Polygon)
(use System)


(local
	thePEditor
	local1
	dTextSel_141
	local3
	eventSel_141Sel_1
	eventSel_141Sel_2
)
(class EditablePolygon of Polygon
	(properties
		scratch 0
		size 0
		points 0
		type $0001
		dynamic 0
		rightSide 0
		destX 0
		destY 0
		destHeading 0
	)
	
	(method (dispose)
		(if (and (not local3) destHeading) (self hide:))
		(super dispose:)
	)
	
	(method (show param1 param2 &tmp temp0 theRightSide theDestX theRightSide_2 theDestX_2 temp5)
		(if destHeading (self hide:))
		(= destHeading (Set new:))
		(self bWander: (= temp5 0))
		(= theRightSide_2 (= theRightSide rightSide))
		(= theDestX_2 (= theDestX destX))
		(while (< (++ temp5) size)
			(self bWander: temp5)
			(destHeading
				add:
					(AddLine
						(thePEditor plane?)
						theRightSide_2
						theDestX_2
						rightSide
						destX
						1000
						(+ 10 (thePEditor oTextSaver?))
						0
						0
						1
					)
			)
			(= theRightSide_2 rightSide)
			(= theDestX_2 destX)
		)
		(if (or (< argc 2) (not param2))
			(destHeading
				add:
					(AddLine
						(thePEditor plane?)
						theRightSide
						theDestX
						theRightSide_2
						theDestX_2
						1000
						(+ 10 (thePEditor oTextSaver?))
						0
						0
						1
					)
			)
		)
		(if (or (not argc) (not param1)) (FrameOut))
	)
	
	(method (morphScript param1 param2 &tmp temp0 theDestY temp2)
		(if (not size) (return (not size)))
		(= destY 32767)
		(= temp0 0)
		(while (< temp0 size)
			(self bWander: temp0)
			(if
				(<
					(= theDestY (GetDistance rightSide destX param1 param2))
					destY
				)
				(if 0 (Printf LOOKUP_ERROR param1 param2 theDestY))
				(= temp2 temp0)
				(= destY theDestY)
			)
			(++ temp0)
		)
		(if 0 (Printf LOOKUP_ERROR temp2))
		(return temp2)
	)
	
	(method (nDoits param1 param2 param3 param4 &tmp temp0)
		(= temp0 (IntArray copy: points))
		(= param1 (* param1 2))
		(temp0
			copyToFrom: (+ param1 2) points param1 (- (points size:) param1)
		)
		(temp0 at: param1 param2 param3)
		(points copy: temp0)
		(++ size)
		(temp0 dispose:)
		(if (and destHeading (> argc 3) param4)
			(self hide: 1 show: param4 &rest)
		)
	)
	
	(method (learn param1 param2 &tmp newIntArray)
		(= newIntArray (IntArray new:))
		(= param1 (* param1 2))
		(newIntArray
			copyToFrom: 0 points 0 (Max 0 param1)
			copyToFrom: param1 points (+ param1 2) (- (points size:) (+ param1 2))
		)
		(points copy: newIntArray)
		(-- size)
		(newIntArray dispose:)
		(if (and destHeading (> argc 1) param2)
			(self hide: 1)
			(self show: param2 &rest)
		)
	)
	
	(method (bWander param1 &tmp temp0)
		(= rightSide (points at: (= temp0 (* param1 2))))
		(= destX (points at: (+ temp0 1)))
		(if 0
			(Printf {Polygon access type} param1 rightSide destX)
		)
	)
	
	(method (distanceTo anObj param2 param3)
		(self bWander: anObj)
		(GetDistance rightSide destX param2 param3)
	)
	
	(method (selectBoogleMover param1 param2 &tmp temp0 temp1)
		(= temp0 -1)
		(= temp1 0)
		(while (< temp1 size)
			(self bWander: temp1)
			(if (and (== rightSide param1) (== destX param2))
				(= temp0 temp1)
			)
			(++ temp1)
		)
		(return temp0)
	)
	
	(method (removeObstacle param1)
		(if (not destHeading) (= destHeading (Set new:)))
		(destHeading add: param1)
	)
	
	(method (hide param1 &tmp temp0 temp1)
		(if (not destHeading) (return))
		(while (destHeading size:)
			(DeleteLine
				(= temp1 (destHeading at: 0))
				(thePEditor plane?)
			)
			(destHeading delete: temp1)
		)
		(if (or (not argc) (not param1)) (FrameOut))
	)
	
	(method (stopIdler)
		(self nDoits: size &rest)
	)
	
	(method (doSquash param1 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9 temp10 temp11 temp12 temp13)
		(asm
			_line_   201
			_file_   {filename}
			_line_   202
			lsp      param1
			ldi      0
			eq?     
			bnt      code_06b2
			_line_   203
			pushi    #size
			pushi    0
			pToa     destHeading
			send     4
			push    
			ldi      1
			sub     
			sat      temp7
			_line_   204
			pushi    #at
			pushi    1
			push    
			pToa     destHeading
			send     6
			sat      temp1
			_line_   205
			pushi    #bWander
			pushi    1
			lst      temp7
			self     6
			_line_   206
			pToa     rightSide
			sat      temp3
			_line_   207
			pToa     destX
			sat      temp4
			jmp      code_06e7
code_06b2:
			_line_   208
			_line_   209
			lsp      param1
			ldi      1
			sub     
			sat      temp7
			_line_   210
			pushi    #at
			pushi    1
			push    
			pToa     destHeading
			send     6
			sat      temp1
			_line_   211
			pushi    #bWander
			pushi    1
			lst      temp7
			self     6
			_line_   212
			pToa     rightSide
			sat      temp3
			_line_   213
			pToa     destX
			sat      temp4
code_06e7:
			_line_   215
			pushi    #at
			pushi    1
			lsp      param1
			pToa     destHeading
			send     6
			sat      temp2
			_line_   216
			lsp      param1
			pushi    #size
			pushi    0
			pToa     destHeading
			send     4
			push    
			ldi      1
			sub     
			eq?     
			bnt      code_0713
			_line_   217
			ldi      0
			sat      temp7
			jmp      code_0720
code_0713:
			_line_   218
			_line_   219
			lsp      param1
			ldi      1
			add     
			sat      temp7
code_0720:
			_line_   221
			pushi    #bWander
			pushi    1
			lst      temp7
			self     6
			_line_   222
			pToa     rightSide
			sat      temp5
			_line_   223
			pToa     destX
			sat      temp6
			_line_   225
			ldi      0
			sat      temp8
			_line_   226
			ldi      0
			sat      temp9
			_line_   228
			pushi    #plane
			pushi    0
			lal      thePEditor
			send     4
			sat      temp10
			_line_   229
			pushi    #oTextSaver
			pushi    0
			lal      thePEditor
			send     4
			sat      temp13
			_line_   231
code_0765:
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			pushi    #curEvent
			pushi    0
			lag      user
			send     4
			send     4
			sat      temp0
			send     4
			push    
			ldi      2
			and     
			not     
			bnt      code_080c
			_line_   232
			pushi    #localize
			pushi    1
			lst      temp10
			lat      temp0
			send     6
			_line_   233
			pushi    #x
			pushi    0
			lat      temp0
			send     4
			sat      temp11
			pushi    #y
			pushi    0
			lat      temp0
			send     4
			sat      temp12
			_line_   234
			lst      temp8
			lat      temp11
			ne?     
			bt       code_07b9
			lst      temp9
			lat      temp12
			ne?     
			bnt      code_0765
code_07b9:
			_line_   235
			pushi    11
			lst      temp1
			lst      temp10
			lst      temp3
			lst      temp4
			lst      temp11
			lst      temp12
			pushi    1000
			lst      temp13
			pushi    0
			pushi    0
			pushi    1
			callk    UpdateLine,  22
			_line_   236
			pushi    11
			lst      temp2
			lst      temp10
			lst      temp5
			lst      temp6
			lst      temp11
			lst      temp12
			pushi    1000
			lst      temp13
			pushi    0
			pushi    0
			pushi    1
			callk    UpdateLine,  22
			_line_   237
			lat      temp11
			sat      temp8
			_line_   238
			lat      temp12
			sat      temp9
			_line_   239
			pushi    0
			callk    FrameOut,  0
			jmp      code_0765
code_080c:
			_line_   242
			pushi    #localize
			pushi    1
			lst      temp10
			lat      temp0
			send     6
			_line_   243
			pushi    11
			lst      temp1
			lst      temp10
			lst      temp3
			lst      temp4
			pushi    #x
			pushi    0
			lat      temp0
			send     4
			push    
			pushi    #y
			pushi    0
			lat      temp0
			send     4
			push    
			pushi    1000
			lst      temp13
			pushi    0
			pushi    0
			pushi    1
			callk    UpdateLine,  22
			_line_   244
			pushi    11
			lst      temp2
			lst      temp10
			lst      temp5
			lst      temp6
			pushi    #x
			pushi    0
			lat      temp0
			send     4
			push    
			pushi    #y
			pushi    0
			lat      temp0
			send     4
			push    
			pushi    1000
			lst      temp13
			pushi    0
			pushi    0
			pushi    1
			callk    UpdateLine,  22
			_line_   245
			pushi    #unSquash
			pushi    3
			lsp      param1
			pushi    #x
			pushi    0
			lat      temp0
			send     4
			push    
			pushi    #y
			pushi    0
			lat      temp0
			send     4
			push    
			self     10
			_line_   246
			pushi    0
			callk    FrameOut,  0
			_line_   247
			ret     
		)
	)
	
	(method (unSquash param1 param2 param3 &tmp temp0)
		(points at: (= temp0 (* param1 2)) param2)
		(points at: (+ temp0 1) param3)
	)
	
	(method (saveMessages param1 param2 &tmp temp0 temp1 temp2)
		(if (and (> argc 1) param2)
			(param1
				writeString: LOOKUP_ERROR
				writeString: (self name?)
				writeString: LOOKUP_ERROR
			)
		)
		(param1 writeString: LOOKUP_ERROR)
		(= temp0
			(Str
				with:
					(switch type
						(0 'LOOKUP_ERROR')
						(1 'LOOKUP_ERROR')
						(3 'LOOKUP_ERROR')
						(2 'LOOKUP_ERROR')
					)
			)
		)
		(temp0 cat: LOOKUP_ERROR)
		(param1 writeString: temp0)
		(temp0 with: LOOKUP_ERROR)
		(= temp2 0)
		(= temp1 0)
		(while (< temp1 size)
			(self bWander: temp1)
			(temp0 format: LOOKUP_ERROR rightSide destX)
			(= temp2 (+ temp2 (temp0 size:)))
			(param1 writeString: temp0)
			(cond 
				((> temp2 49) (= temp2 0) (param1 writeString: LOOKUP_ERROR))
				((< (+ temp1 1) size) (param1 writeString: LOOKUP_ERROR))
			)
			(++ temp1)
		)
		(param1 writeString: LOOKUP_ERROR)
		(temp0 dispose:)
	)
	
	(method (showSelf &tmp temp0 newStr newStr_2)
		(= newStr (Str new:))
		(= newStr_2 (Str new:))
		(= temp0 0)
		(while (< temp0 size)
			(self bWander: temp0)
			(newStr_2 format: LOOKUP_ERROR rightSide)
			(newStr cat: newStr_2)
			(newStr_2 format: LOOKUP_ERROR destX)
			(newStr cat: newStr_2)
			(++ temp0)
		)
		(Printf LOOKUP_ERROR (newStr data?))
		(newStr dispose:)
		(newStr_2 dispose:)
	)
)

(class PEditor of Set
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
		soMyScript 0
		oTextWindowThingy 0
		displayWidth 0
		whoWidth 0
		rightSide 0
		destX 0
		speakerView 0
		oTextSaver 0
		mode 0
		speakerLoop 0
		plane 0
	)
	
	(method (init param1 thePlane_2 &tmp newEvent)
		(= oTextSaver 210)
		(= local3 1)
		(= plane thePlane_2)
		((= newEvent (Event new:)) x: 4)
		(newEvent y: 4)
		(newEvent localize: thePlane_2)
		(= eventSel_141Sel_1 (newEvent x?))
		(= eventSel_141Sel_2 (newEvent y?))
		(newEvent dispose:)
		(if (and argc param1)
			(= mode 0)
			(= local1 (Str format: LOOKUP_ERROR))
		else
			(= mode 1)
			(= local1 (Str format: LOOKUP_ERROR))
		)
		((= dTextSel_141 (DText new:))
			text: (KString 8 (local1 data?))
			fore: 235
			back: 227
			skip: 0
			posn: eventSel_141Sel_1 eventSel_141Sel_2
			setSize: 240
			setPri: 1000
			init: (plane nSpeed:)
		)
		(super init: (plane nSpeed:))
		(= thePEditor self)
		(= speakerLoop 0)
	)
	
	(method (doit &tmp temp0 theTheCursor)
		(= theTheCursor theCursor)
		(theGame setCursor: normalCursor)
		(while (not speakerLoop)
			(= temp0 ((user curEvent?) new:))
			(self handleEvent: temp0)
		)
		(theGame setCursor: theTheCursor)
	)
	
	(method (dispose &tmp theTheCursor)
		(dTextSel_141 dispose:)
		(local1 dispose:)
		(= theTheCursor theCursor)
		(theGame setCursor: waitCursor)
		(if (not local3) (self hide: 1))
		(FrameOut)
		(theGame setCursor: theTheCursor)
		(super dispose:)
	)
	
	(method (show)
		(if (self size:) (self eachElementDo: #show &rest))
		(FrameOut)
	)
	
	(method (speakerCel param1)
		(cond 
			((> argc 1)
				(self
					add: ((EditablePolygon new:) init: param1 &rest yourself:)
				)
			)
			((param1 isKindOf: EditablePolygon) (self add: param1))
			(else
				(self
					add:
						((EditablePolygon new:)
							size: (param1 size:)
							type: (param1 type?)
							points: (IntArray copy: (param1 points?))
							dynamic: 1
							yourself:
						)
				)
			)
		)
	)
	
	(method (oWho param1 &tmp temp0 theTheCursor)
		(= theTheCursor theCursor)
		(theGame setCursor: waitCursor)
		(= temp0 0)
		(while (< temp0 (param1 size:))
			(self speakerCel: (param1 at: temp0))
			(++ temp0)
		)
		(self show: 1)
		(FrameOut)
		(theGame setCursor: theTheCursor)
	)
	
	(method (hide)
		(if (self size:) (self eachElementDo: #hide &rest))
	)
	
	(method (morphScript param1 param2 &tmp theWhoWidth theDisplayWidth theDisplayWidthDestY theTheDisplayWidthDestY theNextNode)
		(= displayWidth (= whoWidth 0))
		(if (not (self size:)) (return))
		(= theTheDisplayWidthDestY 32767)
		(= theNextNode (KList 3 elements))
		(while theNextNode
			(= nextNode (KList 6 theNextNode))
			(= theWhoWidth
				((= theDisplayWidth (KList 8 theNextNode))
					morphScript: param1 param2
				)
			)
			(if
				(<
					(= theDisplayWidthDestY (theDisplayWidth destY?))
					theTheDisplayWidthDestY
				)
				(= displayWidth theDisplayWidth)
				(= whoWidth theWhoWidth)
				(= theTheDisplayWidthDestY theDisplayWidthDestY)
			)
			(= theNextNode nextNode)
		)
	)
	
	(method (handleEvent event &tmp temp0 temp1 temp2 temp3 temp4)
		(asm
			_line_   597
			_file_   {filename}
			_line_   598
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      4
			and     
			bnt      code_13e5
			_line_   599
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			dup     
			_line_   600
			ldi      32
			eq?     
			bnt      code_1350
			_line_   601
			ldi      0
			sal      local3
			_line_   602
			pTos     mode
			ldi      0
			eq?     
			bnt      code_1332
			pushi    #size
			pushi    0
			self     4
			bnt      code_1332
			_line_   603
			pushi    #format
			pushi    1
			lofsa    LOOKUP_ERROR
			push    
			class    Str
			send     6
			sal      local1
			_line_   604
			ldi      1
			aTop     mode
			jmp      code_1360
code_1332:
			_line_   605
			_line_   606
			pushi    #format
			pushi    1
			lofsa    LOOKUP_ERROR
			push    
			class    Str
			send     6
			sal      local1
			_line_   607
			ldi      0
			aTop     mode
			jmp      code_1360
code_1350:
			dup     
			_line_   610
			ldi      27
			eq?     
			bnt      code_1360
			_line_   611
			ldi      1
			aTop     speakerLoop
code_1360:
			toss    
			_line_   614
			pushi    #dispose
			pushi    0
			lal      dTextSel_141
			send     4
			_line_   615
			_line_   616
			pushi    #text
			pushi    1
			pushi    2
			pushi    8
			pushi    #data
			pushi    0
			lal      local1
			send     4
			push    
			callk    KString,  4
			push    
			_line_   617
			pushi    37
			pushi    1
			pushi    235
			_line_   618
			pushi    38
			pushi    1
			pushi    227
			_line_   619
			pushi    114
			pushi    1
			pushi    0
			_line_   620
			pushi    331
			pushi    2
			lsl      eventSel_141Sel_1
			lsl      eventSel_141Sel_2
			_line_   621
			pushi    372
			pushi    1
			pushi    240
			_line_   622
			pushi    74
			pushi    1
			pushi    1000
			_line_   623
			pushi    142
			pushi    1
			pushi    #nSpeed
			pushi    0
			pToa     plane
			send     4
			push    
			pushi    #new
			pushi    0
			class    DText
			send     4
			sal      dTextSel_141
			send     50
			_line_   625
			pushi    0
			callk    FrameOut,  0
code_13e5:
			_line_   628
			pTos     mode
			dup     
			_line_   629
			ldi      0
			eq?     
			bnt      code_143d
			_line_   630
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      1
			and     
			bnt      code_1629
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			not     
			bnt      code_1629
			_line_   631
			pushi    #localize
			pushi    1
			pTos     plane
			lap      event
			send     6
			_line_   632
			pushi    #aCase
			pushi    2
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			self     8
			jmp      code_1629
code_143d:
			dup     
			_line_   635
			ldi      1
			eq?     
			bnt      code_1629
			_line_   636
			_line_   637
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      1
			and     
			bnt      code_1629
			pushi    #size
			pushi    0
			self     4
			bnt      code_1629
			_line_   640
			ldi      0
			sal      local3
			_line_   642
			_line_   643
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			not     
			bnt      code_14b7
			_line_   644
			pushi    #localize
			pushi    1
			pTos     plane
			lap      event
			send     6
			_line_   645
			pushi    #morphScript
			pushi    2
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			self     8
			_line_   646
			pushi    #doSquash
			pushi    1
			pTos     whoWidth
			pToa     displayWidth
			send     6
			jmp      code_1629
code_14b7:
			_line_   648
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      3
			and     
			bnt      code_152c
			_line_   649
			pushi    #localize
			pushi    1
			pTos     plane
			lap      event
			send     6
			_line_   650
			pushi    #morphScript
			pushi    2
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			self     8
			_line_   651
			pushi    #learn
			pushi    2
			pTos     whoWidth
			pushi    1
			pToa     displayWidth
			send     8
			_line_   652
			pushi    #size
			pushi    0
			pToa     displayWidth
			send     4
			push    
			ldi      2
			le?     
			bnt      code_1629
			_line_   653
			pushi    #delete
			pushi    1
			pTos     displayWidth
			self     6
			_line_   654
			pushi    #dispose
			pushi    0
			pToa     displayWidth
			send     4
			jmp      code_1629
code_152c:
			_line_   657
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      4
			and     
			bnt      code_1629
			_line_   658
			pushi    #localize
			pushi    1
			pTos     plane
			lap      event
			send     6
			_line_   659
			pushi    #morphScript
			pushi    2
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			self     8
			_line_   660
			pToa     whoWidth
			sat      temp0
			_line_   661
			pTos     whoWidth
			ldi      1
			sub     
			sat      temp1
			push    
			ldi      0
			lt?     
			bnt      code_158e
			_line_   662
			pushi    #size
			pushi    0
			pToa     displayWidth
			send     4
			push    
			ldi      1
			sub     
			sat      temp1
code_158e:
			_line_   664
			pTos     whoWidth
			ldi      1
			add     
			sat      temp2
			push    
			pushi    #size
			pushi    0
			pToa     displayWidth
			send     4
			ge?     
			bnt      code_15ab
			_line_   665
			ldi      0
			sat      temp2
code_15ab:
			_line_   667
			pushi    #distanceTo
			pushi    3
			lst      temp1
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			pToa     displayWidth
			send     10
			sat      temp3
			_line_   668
			pushi    #distanceTo
			pushi    3
			lst      temp2
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			pToa     displayWidth
			send     10
			sat      temp4
			_line_   669
			pushi    1165
			pushi    4
			_line_   670
			lst      temp3
			gt?     
			bnt      code_1607
			_line_   671
			lat      temp1
			jmp      code_160f
code_1607:
			_line_   672
			_line_   673
			pToa     whoWidth
code_160f:
			push    
			_line_   675
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			pushi    1
			pToa     displayWidth
			send     12
code_1629:
			toss    
			_line_   683
			ret     
		)
	)
	
	(method (aCase param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8)
		(asm
			_line_   491
			_file_   {filename}
			_line_   493
			_line_   494
			ldi      0
			sat      temp0
			_line_   495
			lap      param1
			aTop     rightSide
			_line_   496
			lap      param2
			aTop     destX
			_line_   497
			ldi      0
			aTop     speakerView
			_line_   499
			pushi    #init
			pushi    0
			pushi    148
			pushi    0
			pushi    #new
			pushi    0
			class    EditablePolygon
			send     4
			send     8
			aTop     displayWidth
			_line_   500
			pToa     scratch
			bnt      code_1048
			_line_   502
			pushi    #new
			pushi    1
			pushi    13
			class    Str
			send     6
			sat      temp6
			_line_   503
			pushi    #newWith
			pushi    2
			pushi    13
			lofsa    LOOKUP_ERROR
			push    
			class    Str
			send     8
			sat      temp7
			_line_   504
			_line_   505
			pushi    #addTitle
			pushi    1
			lofsa    LOOKUP_ERROR
			push    
			_line_   506
			pushi    416
			pushi    5
			lst      temp6
			pushi    12
			pushi    0
			pushi    0
			lst      temp7
			_line_   507
			pushi    142
			pushi    0
			class    Print
			send     24
			_line_   509
			pushi    0
			callk    FrameOut,  0
			_line_   510
			pushi    #name
			pushi    1
			pushi    #data
			pushi    0
			lat      temp6
			send     4
			push    
			pushi    43
			pushi    1
			pushi    0
			pToa     displayWidth
			send     12
			_line_   511
			pushi    #dispose
			pushi    0
			lat      temp7
			send     4
code_1048:
			_line_   514
			pushi    #stopIdler
			pushi    2
			lsp      param1
			lsp      param2
			pToa     displayWidth
			send     8
			_line_   515
code_105b:
			pToa     speakerView
			not     
			bnt      code_1128
			_line_   516
			pToa     rightSide
			sat      temp2
			_line_   517
			pToa     destX
			sat      temp3
			_line_   518
			pushi    #aSeq
			pushi    2
			pTos     rightSide
			pTos     destX
			self     8
			sat      temp4
			bnt      code_109c
			_line_   519
			_line_   520
			pushi    #stopIdler
			pushi    2
			pTos     rightSide
			pTos     destX
			_line_   521
			pushi    1169
			pushi    1
			push    
			pToa     displayWidth
			send     14
code_109c:
			_line_   525
			_line_   526
			_line_   527
			pTos     speakerView
			ldi      3
			eq?     
			bnt      code_105b
			_line_   528
			pushi    #size
			pushi    0
			pToa     displayWidth
			send     4
			push    
			ldi      0
			gt?     
			bnt      code_105b
			_line_   529
			_line_   530
			pushi    #size
			pushi    0
			pToa     displayWidth
			send     4
			push    
			ldi      1
			sub     
			sat      temp8
			_line_   533
			pushi    #learn
			pushi    3
			push    
			pushi    1
			pushi    1
			pToa     displayWidth
			send     10
			_line_   537
			pushi    1167
			pushi    #x
			lst      temp8
			ldi      1
			sub     
			push    
			pToa     displayWidth
			send     6
			_line_   538
			pushi    #rightSide
			pushi    0
			pToa     displayWidth
			send     4
			aTop     rightSide
			_line_   539
			pushi    #destX
			pushi    0
			pToa     displayWidth
			send     4
			aTop     destX
			_line_   541
			pToa     rightSide
			sat      temp2
			_line_   542
			pToa     destX
			sat      temp3
			_line_   544
			ldi      0
			aTop     speakerView
			jmp      code_105b
code_1128:
			_line_   547
			pushi    #size
			pushi    0
			pToa     displayWidth
			send     4
			push    
			ldi      2
			gt?     
			bnt      code_1241
			_line_   548
			pushi    #removeObstacle
			pushi    1
			pushi    10
			pTos     plane
			lsp      param1
			lsp      param2
			lst      temp2
			lst      temp3
			pushi    1000
			pTos     oTextSaver
			pushi    0
			pushi    0
			pushi    1
			callk    AddLine,  20
			push    
			pToa     displayWidth
			send     6
			_line_   549
			pushi    #add
			pushi    1
			pTos     displayWidth
			self     6
			_line_   550
			pushi    0
			callk    FrameOut,  0
			_line_   554
			pToa     scratch
			not     
			bnt      code_1257
			_line_   555
			_line_   556
			_line_   557
			pushi    #addTitle
			pushi    1
			lofsa    LOOKUP_ERROR
			push    
			_line_   558
			pushi    425
			pushi    4
			pushi    1
			lofsa    LOOKUP_ERROR
			push    
			pushi    0
			pushi    0
			_line_   559
			pushi    425
			pushi    4
			pushi    2
			lofsa    LOOKUP_ERROR
			push    
			pushi    60
			pushi    0
			_line_   560
			pushi    425
			pushi    4
			pushi    3
			lofsa    LOOKUP_ERROR
			push    
			pushi    120
			pushi    0
			_line_   561
			pushi    425
			pushi    4
			dup     
			lofsa    LOOKUP_ERROR
			push    
			pushi    195
			pushi    0
			_line_   562
			pushi    142
			pushi    0
			class    Print
			send     58
			sat      temp5
			bnt      code_1257
			_line_   564
			_line_   565
			pushi    43
			pushi    #x
			push    
			ldi      1
			sub     
			push    
			pToa     displayWidth
			send     6
			_line_   566
			lst      temp5
			dup     
			_line_   567
			ldi      4
			eq?     
			bnt      code_1221
			_line_   568
			pushi    65535
			pushi    1
			pTos     displayWidth
			callk    TestPoly,  2
			ne?     
			bnt      code_123e
			_line_   569
			pushi    1
			lofsa    LOOKUP_ERROR
			push    
			callk    MonoOut,  2
			jmp      code_123e
code_1221:
			_line_   572
			_line_   573
			pushi    1
			pushi    1
			pTos     displayWidth
			callk    TestPoly,  2
			ne?     
			bnt      code_123e
			_line_   574
			pushi    1
			lofsa    LOOKUP_ERROR
			push    
			callk    MonoOut,  2
code_123e:
			toss    
			jmp      code_1257
code_1241:
			_line_   580
			_line_   581
			pushi    #dispose
			pushi    0
			pToa     displayWidth
			send     4
			_line_   582
			ldi      0
			aTop     displayWidth
code_1257:
			_line_   584
			pushi    0
			callk    FrameOut,  0
			_line_   585
			ret     
		)
	)
	
	(method (aSeq theRightSide theDestX &tmp temp0 temp1)
		(= temp1
			(AddLine
				plane
				theRightSide
				theDestX
				theRightSide
				theDestX
				1000
				oTextSaver
				0
				0
				1
			)
		)
		(FrameOut)
		(= rightSide theRightSide)
		(= destX theDestX)
		(while
		(!= ((= temp0 ((user curEvent?) new:)) type?) 1)
			(temp0 localize: plane)
			(if
			(or (!= (temp0 x?) rightSide) (!= (temp0 y?) destX))
				(UpdateLine
					temp1
					plane
					theRightSide
					theDestX
					(temp0 x?)
					(temp0 y?)
					1000
					oTextSaver
					0
					0
					1
				)
			)
			(= rightSide (temp0 x?))
			(= destX (temp0 y?))
			(FrameOut)
		)
		(if (= speakerView (temp0 modifiers?))
			(DeleteLine temp1 plane)
			(= temp1 0)
		else
			(temp0 localize: plane)
			(UpdateLine
				temp1
				plane
				theRightSide
				theDestX
				rightSide
				destX
				1000
				oTextSaver
				0
				0
				1
			)
		)
		(return temp1)
	)
)
