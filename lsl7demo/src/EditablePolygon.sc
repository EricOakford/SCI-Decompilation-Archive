;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64013)
(include sci.sh)
(use Main)
(use DText)
(use String)
(use Array)
(use Print)
(use Polygon)
(use System)


(local
	thePEditor
	local1
	newDText
	local3
	newEventX
	newEventY
)
(class EditablePolygon of Polygon
	(properties
		scratch 0
		size 0
		points 0
		type $0001
		dynamic 0
		currentX 0
		currentY 0
		shortestDistance 0
		lines 0
	)
	
	(method (dispose)
		(if (and (not local3) lines) (self hide:))
		(super dispose:)
	)
	
	(method (show param1 param2 &tmp temp0 theCurrentX theCurrentY theCurrentX_2 theCurrentY_2 temp5)
		(if lines (self hide:))
		(= lines (Set new:))
		(self indexToCoor: (= temp5 0))
		(= theCurrentX_2 (= theCurrentX currentX))
		(= theCurrentY_2 (= theCurrentY currentY))
		(while (< (++ temp5) size)
			(self indexToCoor: temp5)
			(lines
				add:
					(AddLine
						(thePEditor plane?)
						theCurrentX_2
						theCurrentY_2
						currentX
						currentY
						1000
						(+ 10 (thePEditor lineColor?))
						0
						0
						1
					)
			)
			(= theCurrentX_2 currentX)
			(= theCurrentY_2 currentY)
		)
		(if (or (< argc 2) (not param2))
			(lines
				add:
					(AddLine
						(thePEditor plane?)
						theCurrentX
						theCurrentY
						theCurrentX_2
						theCurrentY_2
						1000
						(+ 10 (thePEditor lineColor?))
						0
						0
						1
					)
			)
		)
		(if (or (not argc) (not param1)) (FrameOut))
	)
	
	(method (findClosestPoint param1 param2 &tmp temp0 theShortestDistance temp2)
		(if (not size) (return (not size)))
		(= shortestDistance 32767)
		(= temp0 0)
		(while (< temp0 size)
			(self indexToCoor: temp0)
			(if
				(<
					(= theShortestDistance
						(GetDistance currentX currentY param1 param2)
					)
					shortestDistance
				)
				(if 0
					(Printf
						{Finding closest point for %d,%d. New shortest : %d}
						param1
						param2
						theShortestDistance
					)
				)
				(= temp2 temp0)
				(= shortestDistance theShortestDistance)
			)
			(++ temp0)
		)
		(if 0 (Printf {Returning nearest index : %d} temp2))
		(return temp2)
	)
	
	(method (insertPoint param1 param2 param3 param4 &tmp temp0)
		(= temp0 (IntArray copy: points))
		(= param1 (* param1 2))
		(temp0
			copyToFrom: (+ param1 2) points param1 (- (points size:) param1)
		)
		(temp0 at: param1 param2 param3)
		(points copy: temp0)
		(++ size)
		(temp0 dispose:)
		(if (and lines (> argc 3) param4)
			(self hide: 1 show: param4 &rest)
		)
	)
	
	(method (deletePoint param1 param2 &tmp newIntArray)
		(= newIntArray (IntArray new:))
		(= param1 (* param1 2))
		(newIntArray
			copyToFrom: 0 points 0 (Max 0 param1)
			copyToFrom: param1 points (+ param1 2) (- (points size:) (+ param1 2))
		)
		(points copy: newIntArray)
		(-- size)
		(newIntArray dispose:)
		(if (and lines (> argc 1) param2)
			(self hide: 1)
			(self show: param2 &rest)
		)
	)
	
	(method (indexToCoor param1 &tmp temp0)
		(= currentX (points at: (= temp0 (* param1 2))))
		(= currentY (points at: (+ temp0 1)))
		(if 0
			(Printf
				{indexToCoor - index : %d x : %d y: %d}
				param1
				currentX
				currentY
			)
		)
	)
	
	(method (distanceTo anObj param2 param3)
		(self indexToCoor: anObj)
		(GetDistance currentX currentY param2 param3)
	)
	
	(method (findPoint param1 param2 &tmp temp0 temp1)
		(= temp0 -1)
		(= temp1 0)
		(while (< temp1 size)
			(self indexToCoor: temp1)
			(if
			(and (== currentX param1) (== currentY param2))
				(= temp0 temp1)
			)
			(++ temp1)
		)
		(return temp0)
	)
	
	(method (addLine param1)
		(if (not lines) (= lines (Set new:)))
		(lines add: param1)
	)
	
	(method (hide param1 &tmp temp0 temp1)
		(if (not lines) (return))
		(while (lines size:)
			(DeleteLine
				(= temp1 (lines at: 0))
				(thePEditor plane?)
			)
			(lines delete: temp1)
		)
		(if (or (not argc) (not param1)) (FrameOut))
	)
	
	(method (addPoint)
		(self insertPoint: size &rest)
	)
	
	(method (dragPoint param1 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9 temp10 temp11 temp12 temp13)
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
			pToa     lines
			send     4
			push    
			ldi      1
			sub     
			sat      temp7
			_line_   204
			pushi    #at
			pushi    1
			push    
			pToa     lines
			send     6
			sat      temp1
			_line_   205
			pushi    #indexToCoor
			pushi    1
			lst      temp7
			self     6
			_line_   206
			pToa     currentX
			sat      temp3
			_line_   207
			pToa     currentY
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
			pToa     lines
			send     6
			sat      temp1
			_line_   211
			pushi    #indexToCoor
			pushi    1
			lst      temp7
			self     6
			_line_   212
			pToa     currentX
			sat      temp3
			_line_   213
			pToa     currentY
			sat      temp4
code_06e7:
			_line_   215
			pushi    #at
			pushi    1
			lsp      param1
			pToa     lines
			send     6
			sat      temp2
			_line_   216
			lsp      param1
			pushi    #size
			pushi    0
			pToa     lines
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
			pushi    #indexToCoor
			pushi    1
			lst      temp7
			self     6
			_line_   222
			pToa     currentX
			sat      temp5
			_line_   223
			pToa     currentY
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
			pushi    #lineColor
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
			pushi    #setPoint
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
	
	(method (setPoint param1 param2 param3 &tmp temp0)
		(points at: (= temp0 (* param1 2)) param2)
		(points at: (+ temp0 1) param3)
	)
	
	(method (writeToFile param1 &tmp temp0 temp1 temp2)
		(param1
			writeString: {\0D\n\t\t\t; ***_}
			writeString: (self name?)
			writeString: { ***\0D\n}
		)
		(param1
			writeString: {\t\t\t((Polygon new:)\0D\n\t\t\t\ttype:_}
		)
		(= temp0
			(Str
				with:
					(switch type
						(0 {PTotal})
						(1 {PNearest})
						(3 {PContained})
						(2 {PBarred})
					)
			)
		)
		(temp0 cat: {Access,\0D\n\t\t\t\tinit:_})
		(param1 writeString: temp0)
		(temp0 with: {})
		(= temp2 0)
		(= temp1 0)
		(while (< temp1 size)
			(self indexToCoor: temp1)
			(temp0 format: {%d %d} currentX currentY)
			(= temp2 (+ temp2 (temp0 size:)))
			(param1 writeString: temp0)
			(cond 
				((> temp2 49) (= temp2 0) (param1 writeString: {\0D\n\t\t\t\t\t\t}))
				((< (+ temp1 1) size) (param1 writeString: {_}))
			)
			(++ temp1)
		)
		(param1
			writeString: {,\0D\n\t\t\t\tyourself:\0D\n\t\t\t)\0D\n}
		)
		(temp0 dispose:)
	)
	
	(method (showSelf &tmp temp0 newStr newStr_2)
		(= newStr (Str new:))
		(= newStr_2 (Str new:))
		(= temp0 0)
		(while (< temp0 size)
			(self indexToCoor: temp0)
			(newStr_2 format: {%d_} currentX)
			(newStr cat: newStr_2)
			(newStr_2 format: {%d_} currentY)
			(newStr cat: newStr_2)
			(++ temp0)
		)
		(Printf {%s} (newStr data?))
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
		polygons 0
		focus 0
		currentPolygon 0
		currentIndex 0
		currentX 0
		currentY 0
		depressType 0
		lineColor 0
		mode 0
		finished 0
		plane 0
	)
	
	(method (init param1 thePlane_2 &tmp newEvent)
		(= lineColor 210)
		(= local3 1)
		(= plane thePlane_2)
		((= newEvent (Event new:)) x: 4)
		(newEvent y: 4)
		(newEvent localize: thePlane_2)
		(= newEventX (newEvent x?))
		(= newEventY (newEvent y?))
		(newEvent dispose:)
		(if (and argc param1)
			(= mode 0)
			(= local1 (Str format: {Create Mode}))
		else
			(= mode 1)
			(= local1 (Str format: {Edit Mode}))
		)
		((= newDText (DText new:))
			text: (Array 8 (local1 data?))
			fore: 87
			back: 0
			skip: 255
			posn: newEventX newEventY
			setSize: 240
			setPri: 1000
			init: (plane getMainCast:)
		)
		(super init: (plane getMainCast:))
		(= thePEditor self)
		(= finished 0)
	)
	
	(method (doit &tmp temp0 theTheCursor)
		(= theTheCursor theCursor)
		(theGame setCursor: normalCursor)
		(while (not finished)
			(= temp0 ((user curEvent?) new:))
			(self handleEvent: temp0)
		)
		(theGame setCursor: theTheCursor)
	)
	
	(method (dispose &tmp theTheCursor)
		(newDText dispose:)
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
	
	(method (addPolygon param1)
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
	
	(method (readPolygonsFromList param1 &tmp temp0 theTheCursor)
		(= theTheCursor theCursor)
		(theGame setCursor: waitCursor)
		(= temp0 0)
		(while (< temp0 (param1 size:))
			(self addPolygon: (param1 at: temp0))
			(++ temp0)
		)
		(self show: 1)
		(FrameOut)
		(theGame setCursor: theTheCursor)
	)
	
	(method (hide)
		(if (self size:) (self eachElementDo: #hide &rest))
	)
	
	(method (findClosestPoint param1 param2 &tmp theCurrentIndex theCurrentPolygon theCurrentPolygonShortestDistance theTheCurrentPolygonShortestDistance theNextNode)
		(= currentPolygon (= currentIndex 0))
		(if (not (self size:)) (return))
		(= theTheCurrentPolygonShortestDistance 32767)
		(= theNextNode (List 3 elements))
		(while theNextNode
			(= nextNode (List 6 theNextNode))
			(= theCurrentIndex
				((= theCurrentPolygon (List 8 theNextNode))
					findClosestPoint: param1 param2
				)
			)
			(if
				(<
					(= theCurrentPolygonShortestDistance
						(theCurrentPolygon shortestDistance?)
					)
					theTheCurrentPolygonShortestDistance
				)
				(= currentPolygon theCurrentPolygon)
				(= currentIndex theCurrentIndex)
				(= theTheCurrentPolygonShortestDistance
					theCurrentPolygonShortestDistance
				)
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
			bnt      code_13cd
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
			bnt      code_1339
			_line_   601
			ldi      0
			sal      local3
			_line_   602
			pTos     mode
			ldi      0
			eq?     
			bnt      code_131b
			pushi    #size
			pushi    0
			self     4
			bnt      code_131b
			_line_   603
			pushi    #format
			pushi    1
			lofsa    {Edit Mode}
			push    
			class    Str
			send     6
			sal      local1
			_line_   604
			ldi      1
			aTop     mode
			jmp      code_1349
code_131b:
			_line_   605
			_line_   606
			pushi    #format
			pushi    1
			lofsa    {Create Mode}
			push    
			class    Str
			send     6
			sal      local1
			_line_   607
			ldi      0
			aTop     mode
			jmp      code_1349
code_1339:
			dup     
			_line_   610
			ldi      27
			eq?     
			bnt      code_1349
			_line_   611
			ldi      1
			aTop     finished
code_1349:
			toss    
			_line_   614
			pushi    #dispose
			pushi    0
			lal      newDText
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
			callk    Array,  4
			push    
			_line_   617
			pushi    37
			pushi    1
			pushi    87
			_line_   618
			pushi    38
			pushi    1
			pushi    0
			_line_   619
			pushi    114
			pushi    1
			pushi    255
			_line_   620
			pushi    332
			pushi    2
			lsl      newEventX
			lsl      newEventY
			_line_   621
			pushi    376
			pushi    1
			pushi    240
			_line_   622
			pushi    74
			pushi    1
			pushi    1000
			_line_   623
			pushi    142
			pushi    1
			pushi    #getMainCast
			pushi    0
			pToa     plane
			send     4
			push    
			pushi    #new
			pushi    0
			class    DText
			send     4
			sal      newDText
			send     50
			_line_   625
			pushi    0
			callk    FrameOut,  0
code_13cd:
			_line_   628
			pTos     mode
			dup     
			_line_   629
			ldi      0
			eq?     
			bnt      code_1425
			_line_   630
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      1
			and     
			bnt      code_1610
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			not     
			bnt      code_1610
			_line_   631
			pushi    #localize
			pushi    1
			pTos     plane
			lap      event
			send     6
			_line_   632
			pushi    #constructPolygon
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
			jmp      code_1610
code_1425:
			dup     
			_line_   635
			ldi      1
			eq?     
			bnt      code_1610
			_line_   636
			_line_   637
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      1
			and     
			bnt      code_1610
			pushi    #size
			pushi    0
			self     4
			bnt      code_1610
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
			bnt      code_149e
			_line_   644
			pushi    #localize
			pushi    1
			pTos     plane
			lap      event
			send     6
			_line_   645
			pushi    #findClosestPoint
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
			pushi    #dragPoint
			pushi    1
			pTos     currentIndex
			pToa     currentPolygon
			send     6
			jmp      code_1610
code_149e:
			_line_   648
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      3
			and     
			bnt      code_1513
			_line_   649
			pushi    #localize
			pushi    1
			pTos     plane
			lap      event
			send     6
			_line_   650
			pushi    #findClosestPoint
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
			pushi    #deletePoint
			pushi    2
			pTos     currentIndex
			pushi    1
			pToa     currentPolygon
			send     8
			_line_   652
			pushi    #size
			pushi    0
			pToa     currentPolygon
			send     4
			push    
			ldi      2
			le?     
			bnt      code_1610
			_line_   653
			pushi    #delete
			pushi    1
			pTos     currentPolygon
			self     6
			_line_   654
			pushi    #dispose
			pushi    0
			pToa     currentPolygon
			send     4
			jmp      code_1610
code_1513:
			_line_   657
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      4
			and     
			bnt      code_1610
			_line_   658
			pushi    #localize
			pushi    1
			pTos     plane
			lap      event
			send     6
			_line_   659
			pushi    #findClosestPoint
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
			pToa     currentIndex
			sat      temp0
			_line_   661
			pTos     currentIndex
			ldi      1
			sub     
			sat      temp1
			push    
			ldi      0
			lt?     
			bnt      code_1575
			_line_   662
			pushi    #size
			pushi    0
			pToa     currentPolygon
			send     4
			push    
			ldi      1
			sub     
			sat      temp1
code_1575:
			_line_   664
			pTos     currentIndex
			ldi      1
			add     
			sat      temp2
			push    
			pushi    #size
			pushi    0
			pToa     currentPolygon
			send     4
			ge?     
			bnt      code_1592
			_line_   665
			ldi      0
			sat      temp2
code_1592:
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
			pToa     currentPolygon
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
			pToa     currentPolygon
			send     10
			sat      temp4
			_line_   669
			pushi    1120
			pushi    4
			_line_   670
			lst      temp3
			gt?     
			bnt      code_15ee
			_line_   671
			lat      temp1
			jmp      code_15f6
code_15ee:
			_line_   672
			_line_   673
			pToa     currentIndex
code_15f6:
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
			pToa     currentPolygon
			send     12
code_1610:
			toss    
			_line_   683
			ret     
		)
	)
	
	(method (constructPolygon param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8)
		(asm
			_line_   491
			_file_   {filename}
			_line_   493
			_line_   494
			ldi      0
			sat      temp0
			_line_   495
			lap      param1
			aTop     currentX
			_line_   496
			lap      param2
			aTop     currentY
			_line_   497
			ldi      0
			aTop     depressType
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
			aTop     currentPolygon
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
			lofsa    {default}
			push    
			class    Str
			send     8
			sat      temp7
			_line_   504
			_line_   505
			pushi    #addTitle
			pushi    1
			lofsa    {Feature Name:}
			push    
			_line_   506
			pushi    419
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
			pToa     currentPolygon
			send     12
			_line_   511
			pushi    #dispose
			pushi    0
			lat      temp7
			send     4
			_line_   514
			pushi    #addPoint
			pushi    2
			lsp      param1
			lsp      param2
			pToa     currentPolygon
			send     8
			_line_   515
code_1044:
			pToa     depressType
			not     
			bnt      code_1111
			_line_   516
			pToa     currentX
			sat      temp2
			_line_   517
			pToa     currentY
			sat      temp3
			_line_   518
			pushi    #dropAnchor
			pushi    2
			pTos     currentX
			pTos     currentY
			self     8
			sat      temp4
			bnt      code_1085
			_line_   519
			_line_   520
			pushi    #addPoint
			pushi    2
			pTos     currentX
			pTos     currentY
			_line_   521
			pushi    1124
			pushi    1
			push    
			pToa     currentPolygon
			send     14
code_1085:
			_line_   525
			_line_   526
			_line_   527
			pTos     depressType
			ldi      3
			eq?     
			bnt      code_1044
			_line_   528
			pushi    #size
			pushi    0
			pToa     currentPolygon
			send     4
			push    
			ldi      0
			gt?     
			bnt      code_1044
			_line_   529
			_line_   530
			pushi    #size
			pushi    0
			pToa     currentPolygon
			send     4
			push    
			ldi      1
			sub     
			sat      temp8
			_line_   533
			pushi    #deletePoint
			pushi    3
			push    
			pushi    1
			pushi    1
			pToa     currentPolygon
			send     10
			_line_   537
			pushi    1122
			pushi    #x
			lst      temp8
			ldi      1
			sub     
			push    
			pToa     currentPolygon
			send     6
			_line_   538
			pushi    #currentX
			pushi    0
			pToa     currentPolygon
			send     4
			aTop     currentX
			_line_   539
			pushi    #currentY
			pushi    0
			pToa     currentPolygon
			send     4
			aTop     currentY
			_line_   541
			pToa     currentX
			sat      temp2
			_line_   542
			pToa     currentY
			sat      temp3
			_line_   544
			ldi      0
			aTop     depressType
			jmp      code_1044
code_1111:
			_line_   547
			pushi    #size
			pushi    0
			pToa     currentPolygon
			send     4
			push    
			ldi      2
			gt?     
			bnt      code_122a
			_line_   548
			pushi    #addLine
			pushi    1
			pushi    10
			pTos     plane
			lsp      param1
			lsp      param2
			lst      temp2
			lst      temp3
			pushi    1000
			pTos     lineColor
			pushi    0
			pushi    0
			pushi    1
			callk    AddLine,  20
			push    
			pToa     currentPolygon
			send     6
			_line_   549
			pushi    #add
			pushi    1
			pTos     currentPolygon
			self     6
			_line_   550
			pushi    0
			callk    FrameOut,  0
			_line_   554
			pToa     scratch
			not     
			bnt      code_1240
			_line_   555
			_line_   556
			_line_   557
			pushi    #addTitle
			pushi    1
			lofsa    {Polygon access type}
			push    
			_line_   558
			pushi    428
			pushi    4
			pushi    1
			lofsa    {Total}
			push    
			pushi    0
			pushi    0
			_line_   559
			pushi    428
			pushi    4
			pushi    2
			lofsa    { Near_}
			push    
			pushi    60
			pushi    0
			_line_   560
			pushi    428
			pushi    4
			pushi    3
			lofsa    { Barred_}
			push    
			pushi    120
			pushi    0
			_line_   561
			pushi    428
			pushi    4
			dup     
			lofsa    { Container_}
			push    
			pushi    195
			pushi    0
			_line_   562
			pushi    142
			pushi    0
			class    Print
			send     58
			sat      temp5
			bnt      code_1240
			_line_   564
			_line_   565
			pushi    43
			pushi    #x
			push    
			ldi      1
			sub     
			push    
			pToa     currentPolygon
			send     6
			_line_   566
			lst      temp5
			dup     
			_line_   567
			ldi      4
			eq?     
			bnt      code_120a
			_line_   568
			pushi    65535
			pushi    1
			pTos     currentPolygon
			callk    Dummy,  2
			ne?     
			bnt      code_1227
			_line_   569
			pushi    1
			lofsa    {Container polygon must be CCW}
			push    
			callk    PrintDebug,  2
			jmp      code_1227
code_120a:
			_line_   572
			_line_   573
			pushi    1
			pushi    1
			pTos     currentPolygon
			callk    Dummy,  2
			ne?     
			bnt      code_1227
			_line_   574
			pushi    1
			lofsa    {Non container polygon must be CW}
			push    
			callk    PrintDebug,  2
code_1227:
			toss    
			jmp      code_1240
code_122a:
			_line_   580
			_line_   581
			pushi    #dispose
			pushi    0
			pToa     currentPolygon
			send     4
			_line_   582
			ldi      0
			aTop     currentPolygon
code_1240:
			_line_   584
			pushi    0
			callk    FrameOut,  0
			_line_   585
			ret     
		)
	)
	
	(method (dropAnchor theCurrentX theCurrentY &tmp temp0 temp1)
		(= temp1
			(AddLine
				plane
				theCurrentX
				theCurrentY
				theCurrentX
				theCurrentY
				1000
				lineColor
				0
				0
				1
			)
		)
		(FrameOut)
		(= currentX theCurrentX)
		(= currentY theCurrentY)
		(while
		(!= ((= temp0 ((user curEvent?) new:)) type?) 1)
			(temp0 localize: plane)
			(if
			(or (!= (temp0 x?) currentX) (!= (temp0 y?) currentY))
				(UpdateLine
					temp1
					plane
					theCurrentX
					theCurrentY
					(temp0 x?)
					(temp0 y?)
					1000
					lineColor
					0
					0
					1
				)
			)
			(= currentX (temp0 x?))
			(= currentY (temp0 y?))
			(FrameOut)
		)
		(if (= depressType (temp0 modifiers?))
			(DeleteLine temp1 plane)
			(= temp1 0)
		else
			(temp0 localize: plane)
			(UpdateLine
				temp1
				plane
				theCurrentX
				theCurrentY
				currentX
				currentY
				1000
				lineColor
				0
				0
				1
			)
		)
		(return temp1)
	)
)
