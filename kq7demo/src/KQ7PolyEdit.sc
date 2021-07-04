;;; Sierra Script 1.0 - (do not remove this comment)
(script# 202)
(include game.sh)
(use Main)
(use String)
(use Array)
(use Print)
(use Polygon)
(use User)
(use System)


(local
	thePEditor
)
(class EditablePolygon of Polygon
	(properties
		currentX 0
		currentY 0
		shortestDistance 0
		lines 0
	)
	
	(method (dispose)
		(if lines (self hide:))
		(super dispose:)
	)
	
	(method (show param1 &tmp temp0 theCurrentX theCurrentY theCurrentX_2 theCurrentY_2 temp5)
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
						200
						(thePEditor lineColor?)
						0
						0
						1
					)
			)
			(= theCurrentX_2 currentX)
			(= theCurrentY_2 currentY)
		)
		(lines
			add:
				(AddLine
					(thePEditor plane?)
					theCurrentX
					theCurrentY
					theCurrentX_2
					theCurrentY_2
					200
					(thePEditor lineColor?)
					0
					0
					1
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
		(if (and lines (> argc 3) param4) (self hide: 1 show:))
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
		(if (and lines (> argc 1) param2) (self hide: 1 show:))
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
			lsp      param1
			ldi      0
			eq?     
			bnt      code_04a8
			pushi    #size
			pushi    0
			pToa     lines
			send     4
			push    
			ldi      1
			sub     
			sat      temp7
			pushi    #at
			pushi    1
			push    
			pToa     lines
			send     6
			sat      temp1
			pushi    #indexToCoor
			pushi    1
			lst      temp7
			self     6
			pToa     currentX
			sat      temp3
			pToa     currentY
			sat      temp4
			jmp      code_04cb
code_04a8:
			lsp      param1
			ldi      1
			sub     
			sat      temp7
			pushi    #at
			pushi    1
			push    
			pToa     lines
			send     6
			sat      temp1
			pushi    #indexToCoor
			pushi    1
			lst      temp7
			self     6
			pToa     currentX
			sat      temp3
			pToa     currentY
			sat      temp4
code_04cb:
			pushi    #at
			pushi    1
			lsp      param1
			pToa     lines
			send     6
			sat      temp2
			lsp      param1
			pushi    #size
			pushi    0
			pToa     lines
			send     4
			push    
			ldi      1
			sub     
			eq?     
			bnt      code_04ee
			ldi      0
			sat      temp7
			jmp      code_04f5
code_04ee:
			lsp      param1
			ldi      1
			add     
			sat      temp7
code_04f5:
			pushi    #indexToCoor
			pushi    1
			lst      temp7
			self     6
			pToa     currentX
			sat      temp5
			pToa     currentY
			sat      temp6
			ldi      0
			sat      temp8
			ldi      0
			sat      temp9
			pushi    #plane
			pushi    0
			lal      thePEditor
			send     4
			sat      temp10
			pushi    #editColor
			pushi    0
			lal      thePEditor
			send     4
			sat      temp13
code_0522:
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			pushi    #curEvent
			pushi    0
			class    User
			send     4
			send     4
			sat      temp0
			send     4
			push    
			ldi      2
			and     
			not     
			bnt      code_05b1
			pushi    #localize
			pushi    1
			lst      temp10
			lat      temp0
			send     6
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
			lst      temp8
			lat      temp11
			ne?     
			bt       code_056d
			lst      temp9
			lat      temp12
			ne?     
			bnt      code_0522
code_056d:
			pushi    11
			lst      temp1
			lst      temp10
			lst      temp3
			lst      temp4
			lst      temp11
			lst      temp12
			pushi    200
			lst      temp13
			pushi    0
			pushi    0
			pushi    1
			callk    UpdateLine,  22
			pushi    11
			lst      temp2
			lst      temp10
			lst      temp5
			lst      temp6
			lst      temp11
			lst      temp12
			pushi    200
			lst      temp13
			pushi    0
			pushi    0
			pushi    1
			callk    UpdateLine,  22
			lat      temp11
			sat      temp8
			lat      temp12
			sat      temp9
			pushi    0
			callk    FrameOut,  0
			jmp      code_0522
code_05b1:
			pushi    #localize
			pushi    1
			lst      temp10
			lat      temp0
			send     6
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
			pushi    200
			pushi    #lineColor
			pushi    0
			lal      thePEditor
			send     4
			push    
			pushi    0
			pushi    0
			pushi    1
			callk    UpdateLine,  22
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
			pushi    200
			pushi    #lineColor
			pushi    0
			lal      thePEditor
			send     4
			push    
			pushi    0
			pushi    0
			pushi    1
			callk    UpdateLine,  22
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
			pushi    0
			callk    FrameOut,  0
			ret     
		)
	)
	
	(method (setPoint param1 param2 param3 &tmp temp0)
		(points at: (= temp0 (* param1 2)) param2)
		(points at: (+ temp0 1) param3)
	)
	
	(method (writeToFile param1 param2 &tmp temp0 temp1 temp2)
		(if (and (> argc 1) param2)
			(param1
				writeString: {\0D\n\t\t\t; ***_}
				writeString: (self name?)
				writeString: { ***\0D\n}
			)
		)
		(param1
			writeString: {\t\t\t((Polygon new:)\0D\n\t\t\t\ttype:_}
		)
		(= temp0
			(String
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
			(temp0 format: {%d %d_} currentX currentY)
			(= temp2 (+ temp2 (temp0 size:)))
			(param1 writeString: temp0)
			(if (> temp2 48)
				(= temp2 0)
				(param1 writeString: {\0D\n\t\t\t\t\t})
			)
			(++ temp1)
		)
		(param1
			writeString: {,\0D\n\t\t\t\tyourself:\0D\n\t\t\t)\0D\n}
		)
		(temp0 dispose:)
	)
	
	(method (showSelf &tmp temp0 newStr newStr_2)
		(= newStr (String new:))
		(= newStr_2 (String new:))
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
		editColor 50
		lineColor 255
		mode 0
		finished 0
		plane 0
	)
	
	(method (init)
		(super init: &rest)
		(= plane (cast plane?))
		(= thePEditor self)
		(= finished 0)
		(= mode 1)
	)
	
	(method (doit &tmp temp0 theTheCursor)
		(= theTheCursor theCursor)
		(theGame setCursor: normalCursor)
		(while (not finished)
			(= temp0 ((User curEvent?) new:))
			(self handleEvent: temp0)
		)
		(theGame setCursor: theTheCursor)
	)
	
	(method (dispose &tmp theTheCursor)
		(= theTheCursor theCursor)
		(theGame setCursor: waitCursor)
		(self hide: 1)
		(FrameOut)
		(theGame setCursor: theTheCursor)
		(super dispose:)
	)
	
	(method (show)
		(if (self size:) (self eachElementDo: #show &rest))
	)
	
	(method (addPolygon param1)
		(cond 
			((> argc 1)
				(self
					add: ((EditablePolygon new:) init: param1 yourself:) ;&rest yourself:)
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
		(= theNextNode (FirstNode elements))
		(while theNextNode
			(= nextNode (NextNode theNextNode))
			(= theCurrentIndex
				((= theCurrentPolygon (NodeValue theNextNode))
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
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      4
			and     
			bnt      code_0b28
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			dup     
			ldi      32
			eq?     
			bnt      code_0b1d
			pTos     mode
			ldi      0
			eq?     
			bnt      code_0b17
			pushi    #size
			pushi    0
			self     4
			bnt      code_0b17
			ldi      1
			aTop     mode
			jmp      code_0b27
code_0b17:
			ldi      0
			aTop     mode
			jmp      code_0b27
code_0b1d:
			dup     
			ldi      27
			eq?     
			bnt      code_0b27
			ldi      1
			aTop     finished
code_0b27:
			toss    
code_0b28:
			pTos     mode
			dup     
			ldi      0
			eq?     
			bnt      code_0b71
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      1
			and     
			bnt      code_0cf8
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			not     
			bnt      code_0cf8
			pushi    #localize
			pushi    1
			pTos     plane
			lap      event
			send     6
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
			jmp      code_0cf8
code_0b71:
			dup     
			ldi      1
			eq?     
			bnt      code_0cf8
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      1
			and     
			bnt      code_0cf8
			pushi    #size
			pushi    0
			self     4
			bnt      code_0cf8
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			not     
			bnt      code_0bcb
			pushi    #localize
			pushi    1
			pTos     plane
			lap      event
			send     6
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
			pushi    #dragPoint
			pushi    1
			pTos     currentIndex
			pToa     currentPolygon
			send     6
			jmp      code_0cf8
code_0bcb:
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      3
			and     
			bnt      code_0c2b
			pushi    #localize
			pushi    1
			pTos     plane
			lap      event
			send     6
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
			pushi    #deletePoint
			pushi    2
			pTos     currentIndex
			pushi    1
			pToa     currentPolygon
			send     8
			pushi    #size
			pushi    0
			pToa     currentPolygon
			send     4
			push    
			ldi      2
			le?     
			bnt      code_0cf8
			pushi    #delete
			pushi    1
			pTos     currentPolygon
			self     6
			pushi    #dispose
			pushi    0
			pToa     currentPolygon
			send     4
			jmp      code_0cf8
code_0c2b:
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      4
			and     
			bnt      code_0cf8
			pushi    #localize
			pushi    1
			pTos     plane
			lap      event
			send     6
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
			pToa     currentIndex
			sat      temp0
			pTos     currentIndex
			ldi      1
			sub     
			sat      temp1
			push    
			ldi      0
			lt?     
			bnt      code_0c7b
			pushi    #size
			pushi    0
			pToa     currentPolygon
			send     4
			push    
			ldi      1
			sub     
			sat      temp1
code_0c7b:
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
			bnt      code_0c92
			ldi      0
			sat      temp2
code_0c92:
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
			pushi    727
			pushi    4
			lst      temp3
			gt?     
			bnt      code_0cdf
			lat      temp1
			jmp      code_0ce1
code_0cdf:
			pToa     currentIndex
code_0ce1:
			push    
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
code_0cf8:
			toss    
			ret     
		)
	)
	
	(method (constructPolygon param1 param2 &tmp temp0 temp1 temp2 temp3 temp4)
		(asm
			ldi      0
			sat      temp0
			lap      param1
			aTop     currentX
			lap      param2
			aTop     currentY
			ldi      0
			aTop     depressType
			pushi    #init
			pushi    0
			pushi    153
			pushi    0
			pushi    #new
			pushi    0
			class    EditablePolygon
			send     4
			send     8
			aTop     currentPolygon
			pushi    #addPoint
			pushi    2
			lsp      param1
			lsp      param2
			pToa     currentPolygon
			send     8
code_0a27:
			pToa     depressType
			not     
			bnt      code_0a57
			pToa     currentX
			sat      temp2
			pToa     currentY
			sat      temp3
			pushi    #dropAnchor
			pushi    2
			pTos     currentX
			pTos     currentY
			self     8
			sat      temp4
			bnt      code_0a27
			pushi    #addPoint
			pushi    2
			pTos     currentX
			pTos     currentY
			pushi    731
			pushi    1
			push    
			pToa     currentPolygon
			send     14
			jmp      code_0a27
code_0a57:
			pushi    #size
			pushi    0
			pToa     currentPolygon
			send     4
			push    
			ldi      2
			gt?     
			bnt      code_0a92
			pushi    #addLine
			pushi    1
			pushi    10
			pTos     plane
			lsp      param1
			lsp      param2
			lst      temp2
			lst      temp3
			pushi    200
			pTos     lineColor
			pushi    0
			pushi    0
			pushi    1
			callk    AddLine,  20
			push    
			pToa     currentPolygon
			send     6
			pushi    #add
			pushi    1
			pTos     currentPolygon
			self     6
			jmp      code_0a9f
code_0a92:
			pushi    #dispose
			pushi    0
			pToa     currentPolygon
			send     4
			ldi      0
			aTop     currentPolygon
code_0a9f:
			pushi    0
			callk    FrameOut,  0
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
				200
				editColor
				0
				0
				1
			)
		)
		(FrameOut)
		(= currentX theCurrentX)
		(= currentY theCurrentY)
		(while
		(!= ((= temp0 ((User curEvent?) new:)) type?) 1)
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
					200
					editColor
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
				200
				lineColor
				0
				0
				1
			)
		)
		(return temp1)
	)
)
