;;; Sierra Script 1.0 - (do not remove this comment)
(script# 943)
(include game.sh)
(use Main)
(use Intrface)
(use PrintD)
(use Polygon)
(use Window)
(use File)
(use System)

;(define	PrgTitle				{Polygon Editor 1.11})
(define LineColor 0) ;(define	LineColor	  		vBLACK)
(define CtrlLineColor_O 4) ;(define	CtrlLineColor_O	vRED)
(define CtrlLineColor_A 3) ;(define	CtrlLineColor_A	vCYAN)
;(define	ShowScr 				Graph GShowBits 0 0 190 320 curMap)

(define	OBST_LIST			0)
(define	ALT_LIST				1)
(define	NUndoFlds			3)

(enum
	Adding
	Editing
	Dragging
	Inserting
)


(local
	curMap
	saveSystemWindow
	[fileName 40]
	rTop
	rLft
	rBot
	rRgt
	[editMenuItems 25] = [{EDITING} 0 0 {About} 0 0 {Map} 0 0 {Create} 0 0 {Type} 0 0 {Undo} 0 0 {Help} 0 0 {eXit} 120]
	[addMenuItems 22] = [{CREATING} 0 0 {About} 0 0 {Map} 0 0 {Done} 0 0 {Undo} 0 0 {Help} 0 0 {eXit} 120]
	[dragMenuItems 4] = [{DRAGGING}]
)
(procedure (localproc_17b8 theTheRLft &tmp temp0 theRLft theRTop)
	(= rRgt (= rBot 0))
	(= rLft (= rTop 32767))
	(= temp0 0)
	(while (< temp0 argc)
		(= theRLft [theTheRLft temp0])
		(if (< (= theRTop [theTheRLft (+ temp0 1)]) rTop)
			(= rTop theRTop)
		)
		(if (> theRTop rBot) (= rBot theRTop))
		(if (< theRLft rLft) (= rLft theRLft))
		(if (> theRLft rRgt) (= rRgt theRLft))
		(= temp0 (+ temp0 2))
	)
	(= rLft (- rLft 2))
	(= rTop (- rTop 2))
	(= rRgt (+ rRgt 2))
	(= rBot (+ rBot 2))
)

(procedure (localproc_1838 param1 param2 param3 param4)
	(return
		(+
			(* (+ (/ param1 2) 1) (+ (/ param3 2) 1))
			(* (+ (/ param2 2) 1) (+ (/ param4 2) 1))
		)
	)
)

(procedure (localproc_1863 param1 param2 param3 param4 param5 param6 &tmp temp0)
	(return
		(if
			(and
				(<=
					0
					(localproc_1838
						(- param3 param1)
						(- param4 param2)
						(- param5 param1)
						(- param6 param2)
					)
				)
				(<=
					0
					(localproc_1838
						(- param1 param3)
						(- param2 param4)
						(- param5 param3)
						(- param6 param4)
					)
				)
			)
			(return
				(if
				(= temp0 (GetDistance param1 param2 param3 param4))
					(/
						(Abs
							(localproc_1838
								(- param4 param2)
								(- param1 param3)
								(- param5 param1)
								(- param6 param2)
							)
						)
						temp0
					)
				else
					0
				)
			)
		else
			(return
				(Min
					(GetDistance param5 param6 param1 param2)
					(GetDistance param5 param6 param3 param4)
				)
			)
		)
	)
)

(procedure (DoPrint)
	(Print
		&rest
		#title
		{Polygon Editor 1.1}
		#font
		999
		#width
		240
	)
)

(class ClickMenu of Object
	(properties
		text 0
		array 0
	)
	
	(method (init theArray &tmp temp0 temp1 temp2 temp3 [temp4 4] [temp8 40] temp48 temp49)
		(= text (Memory 2 81))
		(Memory 6 text 0)
		(= temp1 (= array theArray))
		(= temp48 0)
		(= temp0 0)
		(while (= temp2 (Memory 5 temp1))
			(StrCpy @temp8 temp2)
			(if (not temp0) (StrCat @temp8 {:_}))
			(StrCat @temp8 {_})
			(StrCat text @temp8)
			(TextSize @temp4 @temp8 0 0)
			(= temp48 (+ temp48 [temp4 3]))
			(Memory 6 (+ temp1 4) temp48)
			(if (not (Memory 5 (+ temp1 2)))
				(if
					(and
						(<= 65 (= temp49 (StrAt temp2 0)))
						(<= temp49 90)
					)
					(= temp49 (+ temp49 32))
				)
				(Memory 6 (+ temp1 2) temp49)
			)
			(++ temp0)
			(= temp1 (+ temp1 6))
		)
		(DrawStatus text)
	)
	
	(method (dispose)
		(Memory 3 text)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp theArray temp1)
		(if (!= (event type?) mouseDown) (return 0))
		(if (>= (event y?) 10) (return 0))
		(= theArray array)
		(= temp1 0)
		(while (Memory 5 theArray)
			(if
			(and (< (event x?) (Memory 5 (+ theArray 4))) temp1)
				(event type: 4 message: (Memory 5 (+ theArray 2)))
				(return 0)
			)
			(++ temp1)
			(= theArray (+ theArray 6))
		)
		(return (event claimed: 1))
	)
)

(instance editMenu of ClickMenu
	(properties)
	
	(method (init)
		(super init: @editMenuItems)
	)
)

(instance addMenu of ClickMenu
	(properties)
	
	(method (init)
		(super init: @addMenuItems)
	)
)

(instance dragMenu of ClickMenu
	(properties)
	
	(method (init)
		(super init: @dragMenuItems)
	)
)

(class Class_943_4
	(properties
		x 0
		y 0
		underBits 0
	)
	
	(method (new)
		(Clone self)
	)
	
	(method (yourself)
		(return self)
	)
	
	(method (dispose)
		(if underBits (UnLoad 133 underBits) (= underBits 0))
		(DisposeClone self)
	)
	
	(method (draw param1 &tmp temp0 temp1)
		(if (== curMap 4)
			(= temp0 -1)
			(= temp1 4)
		else
			(= temp0 0)
			(= temp1 -1)
		)
		(Graph
			GDrawLine
			y
			x
			(param1 y?)
			(param1 x?)
			temp0
			-1
			temp1
		)
	)
	
	(method (save param1)
		(localproc_17b8 x y (param1 x?) (param1 y?))
		(if underBits (UnLoad 133 underBits))
		(= underBits
			(Graph GSaveBits rTop rLft rBot rRgt curMap)
		)
	)
	
	(method (restore)
		(if underBits
			(Graph GRestoreBits underBits)
			(= underBits 0)
		)
	)
)

(class _EditablePolygon of List
	(properties
		elements 0
		size 0
		curNode 0
		curPt 0
		closed 0
		type $0002
		closestPt 0
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
	)
	
	(method (add param1 param2 param3)
		(super
			add: (= curPt
				((Class_943_4 new:) x: param1 y: param2 yourself:)
			)
		)
		(self setCur: (FindKey elements curPt) param3)
	)
	
	(method (next param1 &tmp temp0)
		(= temp0 (super next: param1))
		(if (and closed (not temp0)) (return (super first:)))
		(return temp0)
	)
	
	(method (prev param1 &tmp temp0)
		(= temp0 (super prev: param1))
		(if (and closed (not temp0)) (return (super last:)))
		(return temp0)
	)
	
	(method (draw)
		(self eachLineDo: 83)
	)
	
	(method (advance &tmp temp0)
		(self setCur: (self next: curNode))
	)
	
	(method (retreat &tmp temp0)
		(self setCur: (self prev: curNode))
	)
	
	(method (setCur theCurNode param2)
		(if (= curNode theCurNode)
			(= curPt (NodeValue (= curNode theCurNode)))
			(if (or (< argc 2) param2)
				(SetCursor normalCursor 1 (curPt x?) (curPt y?))
			)
		)
	)
	
	(method (setCurClosest param1)
		(self setCur: (FindKey elements closestPt) param1)
	)
	
	(method (insertPt param1 param2 &tmp temp0)
		(= temp0
			((Class_943_4 new:) x: param1 y: param2 yourself:)
		)
		(self addAfter: closestPt temp0)
		(self setCur: (FindKey elements temp0))
	)
	
	(method (deletePt &tmp temp0)
		(if (== curNode (= temp0 (self prev: curNode)))
			(= temp0 0)
		)
		(self delete: curPt)
		(curPt dispose:)
		(self setCur: temp0)
	)
	
	(method (movePt param1 param2)
		(curPt x: param1 y: param2)
	)
	
	(method (undo param1 &tmp temp0 temp1 temp2 temp3)
		(self eachElementDo: #dispose release:)
		(= closed (Memory 5 param1))
		(= param1 (+ param1 2))
		(= temp1 (Memory 5 param1))
		(= param1 (+ param1 2))
		(= temp3 (Memory 5 param1))
		(= param1 (+ param1 2))
		(= temp2 0)
		(while (< temp2 temp1)
			(self
				add: (Memory 5 param1) (Memory 5 (= param1 (+ param1 2))) 0
			)
			(++ temp2)
			(= param1 (+ param1 2))
		)
		(self setCur: (FindKey elements (self at: temp3)) 0)
	)
	
	(method (saveForUndo &tmp temp0 temp1 _EditablePolygonFirst temp3)
		(= temp1 (= temp0 (Memory 2 (* 2 (+ (* 2 size) 3)))))
		(Memory 6 temp1 closed)
		(= temp1 (+ temp1 2))
		(Memory 6 temp1 size)
		(= temp1 (+ temp1 2))
		(Memory 6 temp1 (self indexOf: curPt))
		(= temp1 (+ temp1 2))
		(= _EditablePolygonFirst (self first:))
		(while _EditablePolygonFirst
			(= temp3 (NodeValue _EditablePolygonFirst))
			(Memory 6 temp1 (temp3 x?))
			(Memory 6 (= temp1 (+ temp1 2)) (temp3 y?))
			(= _EditablePolygonFirst
				(NextNode _EditablePolygonFirst)
			)
			(= temp1 (+ temp1 2))
		)
		(return temp0)
	)
	
	(method (getDistToLine param1 param2 &tmp _EditablePolygonLast _EditablePolygonFirst theClosestPt temp3 temp4 temp5 the_EditablePolygonFirst)
		(if (< size 2)
			(return (self getDistToPt: param1 param2 &rest))
		)
		(= temp4 32767)
		(= _EditablePolygonFirst (self first:))
		(= _EditablePolygonLast (self last:))
		(repeat
			(= theClosestPt (NodeValue _EditablePolygonFirst))
			(= the_EditablePolygonFirst
				(self next: _EditablePolygonFirst)
			)
			(= temp5 (NodeValue the_EditablePolygonFirst))
			(if
				(<
					(= temp3
						(localproc_1863
							(theClosestPt x?)
							(theClosestPt y?)
							(temp5 x?)
							(temp5 y?)
							param1
							param2
						)
					)
					temp4
				)
				(= temp4 temp3)
				(= closestPt theClosestPt)
			)
			(breakif (== _EditablePolygonFirst _EditablePolygonLast))
			(= _EditablePolygonFirst the_EditablePolygonFirst)
		)
		(return temp4)
	)
	
	(method (getDistToPt param1 param2 &tmp _EditablePolygonLast _EditablePolygonFirst theClosestPt temp3 temp4)
		(= temp4 32767)
		(= _EditablePolygonFirst (self first:))
		(= _EditablePolygonLast (self last:))
		(repeat
			(= theClosestPt (NodeValue _EditablePolygonFirst))
			(if
				(<
					(= temp3
						(GetDistance
							param1
							param2
							(theClosestPt x?)
							(theClosestPt y?)
						)
					)
					temp4
				)
				(= temp4 temp3)
				(= closestPt theClosestPt)
			)
			(breakif (== _EditablePolygonFirst _EditablePolygonLast))
			(= _EditablePolygonFirst
				(self next: _EditablePolygonFirst)
			)
		)
		(return temp4)
	)
	
	(method (startRedraw &tmp temp0 temp1 temp2 theCurPt theCurPt_2)
		(if (= temp0 (self next: curNode))
			(= theCurPt (NodeValue temp0))
		else
			(= theCurPt curPt)
		)
		(if (= temp1 (self prev: curNode))
			(= theCurPt_2 (NodeValue temp1))
		else
			(= theCurPt_2 curPt)
		)
		(localproc_17b8
			(theCurPt_2 x?)
			(theCurPt_2 y?)
			(curPt x?)
			(curPt y?)
			(theCurPt x?)
			(theCurPt y?)
		)
		(= lsTop rTop)
		(= lsLeft rLft)
		(= lsBottom rBot)
		(= lsRight rRgt)
	)
	
	(method (endRedraw)
		(localproc_17b8
			(curPt x?)
			(curPt y?)
			lsLeft
			lsTop
			lsRight
			lsBottom
		)
		(Graph GShowBits rTop rLft rBot rRgt curMap)
	)
	
	(method (restore)
		(self eachLineDo: 79)
	)
	
	(method (save)
		(self eachLineDo: 78)
	)
	
	(method (eachLineDo param1 &tmp _EditablePolygonFirst the_EditablePolygonFirst temp2 temp3 _EditablePolygonLast)
		(= _EditablePolygonFirst (self first:))
		(= _EditablePolygonLast (self last:))
		(while
			(or
				(!= _EditablePolygonFirst _EditablePolygonLast)
				closed
			)
			(= the_EditablePolygonFirst
				(self next: _EditablePolygonFirst)
			)
			(= temp2 (NodeValue _EditablePolygonFirst))
			(= temp3 (NodeValue the_EditablePolygonFirst))
			(temp2 param1: temp3 &rest)
			(breakif (== _EditablePolygonFirst _EditablePolygonLast))
			(= _EditablePolygonFirst the_EditablePolygonFirst)
		)
	)
	
	(method (writeObstacle &tmp temp0 temp1 temp2 temp3 temp4)
		(= temp1 (Memory 2 (* size 4)))
		(= temp2 (FirstNode elements))
		(= temp0 temp1)
		(while temp2
			(= temp3 (NodeValue temp2))
			(Memory 6 temp0 (temp3 x?))
			(Memory 6 (+ temp0 2) (temp3 y?))
			(= temp2 (NextNode temp2))
			(= temp0 (+ temp0 4))
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: type
					points: temp1
					size: size
					dynamic: 1
					yourself:
				)
		)
	)
	
	(method (writeFile param1 &tmp temp0 temp1 temp2 temp3 [temp4 10] temp14)
		(param1
			writeString:
				{\t\t\t((Polygon new:)\0D\n}
				{\t\t\t\ttype:_}
				(switch type
					(0 {PTotalAccess})
					(1 {PNearestAccess})
					(2 {PBarredAccess})
				)
				{,\0D\n}
		)
		(param1 writeString: {\t\t\t\tinit:\t})
		(= temp14 1)
		(= temp0 17)
		(= temp3 (FirstNode elements))
		(while temp3
			(= temp2 (NodeValue temp3))
			(Format @temp4 943 0 (temp2 x?) (temp2 y?))
			(if
				(>=
					(= temp0 (+ temp0 (= temp1 (+ (StrLen @temp4) 1))))
					80
				)
				(param1 writeString: {\0D\n\t\t\t\t\t\t})
				(= temp14 1)
				(= temp0 (+ 17 temp1))
			)
			(if (not temp14) (param1 writeString: {_}))
			(param1 writeString: @temp4)
			(= temp14 0)
			(= temp3 (NextNode temp3))
		)
		(param1 writeString: {,\0D\n})
		(param1
			writeString: {\t\t\t\tyourself\0D\n} {\t\t\t)\0D\n}
		)
	)
	
	(method (getAccessType &tmp temp0)
		(if
			(= temp0
				(PrintD
					80
					{Polygon access type}
					81
					{Total}
					1
					81
					{ Near_}
					2
					81
					{ Barred_}
					3
					117
					type
				)
			)
			(= type (- temp0 1))
		)
	)
	
	(method (check &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9 temp10 temp11 temp12 temp13 temp14 temp15 temp16)
		(asm
			pushi    #first
			pushi    0
			self     4
			sat      temp0
code_0464:
			lat      temp0
			bnt      code_04d6
			pushi    1
			push    
			callk    NodeValue,  2
			sat      temp2
			pushi    1
			lst      temp0
			callk    NextNode,  2
			sat      temp15
code_0478:
			lat      temp15
			bnt      code_04cb
			pushi    1
			push    
			callk    NodeValue,  2
			sat      temp16
			pushi    #x
			pushi    0
			lat      temp2
			send     4
			push    
			pushi    #x
			pushi    0
			lat      temp16
			send     4
			eq?     
			bnt      code_04c0
			pushi    #y
			pushi    0
			lat      temp2
			send     4
			push    
			pushi    #y
			pushi    0
			lat      temp16
			send     4
			eq?     
			bnt      code_04c0
			pushi    1
			lst      temp15
			callk    PrevNode,  2
			sat      temp15
			pushi    #delete
			pushi    1
			lst      temp16
			self     6
			pushi    #dispose
			pushi    0
			lat      temp16
			send     4
code_04c0:
			pushi    1
			lst      temp15
			callk    NextNode,  2
			sat      temp15
			jmp      code_0478
code_04cb:
			pushi    1
			lst      temp0
			callk    NextNode,  2
			sat      temp0
			jmp      code_0464
code_04d6:
			ldi      0
			sat      temp4
			ldi      0
			sat      temp9
			ldi      0
			sat      temp7
			ldi      1
			sat      temp8
			pushi    #first
			pushi    0
			self     4
			sat      temp6
			sat      temp0
code_04ef:
			ldi      1
			bnt      code_0587
			pushi    1
			lst      temp0
			callk    NodeValue,  2
			sat      temp2
			pushi    #next
			pushi    1
			lst      temp0
			self     6
			sat      temp1
			pushi    1
			push    
			callk    NodeValue,  2
			sat      temp3
			pushi    4
			dup     
			pushi    0
			lat      temp2
			send     4
			push    
			pushi    #y
			pushi    0
			lat      temp2
			send     4
			push    
			pushi    #x
			pushi    0
			lat      temp3
			send     4
			push    
			pushi    #y
			pushi    0
			lat      temp3
			send     4
			push    
			callk    ATan,  8
			sat      temp4
			lat      temp8
			not     
			bnt      code_056a
			lst      temp4
			lat      temp9
			sub     
			sat      temp5
			push    
			ldi      180
			gt?     
			bnt      code_0552
			lst      temp5
			ldi      360
			sub     
			sat      temp5
			jmp      code_0563
code_0552:
			lst      temp5
			ldi      65356
			lt?     
			bnt      code_0563
			lst      temp5
			ldi      360
			add     
			sat      temp5
code_0563:
			lst      temp7
			lat      temp5
			add     
			sat      temp7
code_056a:
			lat      temp4
			sat      temp9
			lst      temp0
			lat      temp6
			eq?     
			bnt      code_057c
			lat      temp8
			not     
			bnt      code_057c
code_057c:
			ldi      0
			sat      temp8
			lat      temp1
			sat      temp0
			jmp      code_04ef
code_0587:
			lst      temp7
			ldi      65176
			eq?     
			bnt      code_061a
			pushi    #first
			pushi    0
			self     4
			sat      temp0
			pushi    #last
			pushi    0
			self     4
			sat      temp10
code_059e:
			lst      temp0
			lat      temp10
			ne?     
			bnt      code_0631
			lst      temp0
			pushi    1
			lst      temp10
			callk    NextNode,  2
			ne?     
			bnt      code_0631
			pushi    1
			lst      temp0
			callk    NodeValue,  2
			sat      temp2
			pushi    1
			lst      temp10
			callk    NodeValue,  2
			sat      temp11
			pushi    #x
			pushi    0
			lat      temp2
			send     4
			sat      temp12
			pushi    #y
			pushi    0
			lat      temp2
			send     4
			sat      temp13
			pushi    #x
			pushi    1
			pushi    #x
			pushi    0
			lat      temp11
			send     4
			push    
			lat      temp2
			send     6
			pushi    #y
			pushi    1
			pushi    #y
			pushi    0
			lat      temp11
			send     4
			push    
			lat      temp2
			send     6
			pushi    #x
			pushi    1
			lst      temp12
			lat      temp11
			send     6
			pushi    #y
			pushi    1
			lst      temp13
			lat      temp11
			send     6
			pushi    1
			lst      temp0
			callk    NextNode,  2
			sat      temp0
			pushi    1
			lst      temp10
			callk    PrevNode,  2
			sat      temp10
			jmp      code_059e
			jmp      code_0631
code_061a:
			lst      temp7
			ldi      360
			ne?     
			bnt      code_0631
			pushi    4
			pushi    943
			pushi    1
			pTos     name
			lst      temp7
			calle    Printf,  8
code_0631:
			ret     
		)
	)
)

(instance readObstacle of Code
	(properties)
	
	(method (doit param1 &tmp temp0 temp1 polyEditAdd)
		(= polyEditAdd (PolyEdit add:))
		(= temp0 0)
		(= temp1 (param1 points?))
		(while (< temp0 (param1 size?))
			(polyEditAdd
				add: (Memory 5 temp1) (Memory 5 (+ temp1 2)) 0
				type: (param1 type?)
			)
			(++ temp0)
			(= temp1 (+ temp1 4))
		)
		(polyEditAdd closed: 1)
	)
)

(class PolyEdit of List
	(properties
		elements 0
		size 0
		curPolygon 0
		x 0
		y 0
		state $0000
		isMouseDown 0
		curMenu 0
		undoPrvPoly 0
		undoPoly 0
		undoPolyBuf 0
		undoX 0
		undoY 0
		undoState 0
	)
	
	(method (init)
		(DrawPic (curRoom curPic?) 100 TRUE currentPalette)
		(if (!= overlays -1)
			(DrawPic overlays 100 FALSE currentPalette)
		)
		(addToPics doit:)
		(cast eachElementDo: #stopUpd)
		(Animate (cast elements?) 0)
		(= saveSystemWindow systemWindow)
		(= systemWindow SysWindow)
		(= curMap 1)
		(self readObstacles:)
		(self changeState: (if size 1 else 0))
		(self draw:)
	)
	
	(method (doit &tmp newEvent [temp1 100])
		(self init:)
		(repeat
			(= newEvent (Event new:))
			(if
			(not (if curMenu (curMenu handleEvent: newEvent)))
				(GlobalToLocal newEvent)
				(breakif (self handleEvent: newEvent))
			)
			(newEvent dispose:)
		)
		(newEvent dispose:)
		(self dispose:)
	)
	
	(method (dispose)
		(self writeObstacles:)
		(if curMenu (curMenu dispose:) (= curMenu 0))
		(if undoPolyBuf
			(Memory 3 undoPolyBuf)
			(= undoPolyBuf 0)
		)
		(DrawStatus {_} 0 0)
		(DrawStatus 0)
		(cast eachElementDo: #startUpd)
		(Animate (cast elements?) 0)
		(self eachElementDo: #draw)
		(= systemWindow saveSystemWindow)
		(DisposeScript 940)
		(DisposeScript 993)
		(super dispose:)
		(DisposeScript 943)
	)
	
	(method (add)
		(super add: (= curPolygon (_EditablePolygon new:)))
		(return curPolygon)
	)
	
	(method (delete &tmp theCurPolygon)
		(= theCurPolygon curPolygon)
		(self advanceRetreat: 120 68)
		(if (== curPolygon theCurPolygon) (= curPolygon 0))
		(super delete: theCurPolygon &rest)
		(theCurPolygon dispose:)
	)
	
	(method (handleEvent event &tmp eventModifiers theX theY)
		(= theX x)
		(= theY y)
		(= x (event x?))
		(= y (event y?))
		(switch (event type?)
			(nullEvt
				(if curPolygon
					(if
						(and
							isMouseDown
							(not (OneOf state 0 2))
							(> (+ (Abs (- theX x)) (Abs (- theY y))) 1)
						)
						(if (!= state 3) (self saveForUndo:))
						(self changeState: 2)
					)
					(if
					(and (OneOf state 2 0) (or (!= theX x) (!= theY y)))
						(self movePt: x y)
					)
				)
			)
			(mouseDown
				(= eventModifiers (event modifiers?))
				(= isMouseDown 1)
				(cond 
					((& eventModifiers ctrlDown)
						(if (== state 0)
							(self finishAdding:)
							(= isMouseDown 0)
						else
							(self insertPt:)
						)
					)
					((& eventModifiers shiftDown) (if (!= state 0) (self deletePt:)) (= isMouseDown 0))
					((== state 0) (self addPt:))
					(else (self selectPt:))
				)
			)
			(mouseUp
				(= isMouseDown 0)
				(if (OneOf state 2 3) (self changeState: 1))
			)
			(keyDown
				(switch (event message?)
					(`?
						(event message: `h)
					)
					(`^s
						(event message: `x)
					)
					(`#2
						(event message: `@v)
					)
					(`#4
						(event message: `@c)
					)
				)
				(switch (event message?)
					(TAB
						(if (and (== state Editing) curPolygon)
							(self advanceRetreat: #next: #first:)
						)
					)
					(SHIFTTAB
						(if (and (== state Editing) curPolygon)
							(self advanceRetreat: #prev: #last:)
						)
					)
					(SPACEBAR
						(if (and (== state Editing) curPolygon)
							(curPolygon advance:)
						)
					)
					(BACKSPACE
						(if (and (== state Editing) curPolygon)
							(curPolygon retreat:)
						)
					)
					(`c
						(if (== state Editing)
							(self changeState: Adding)
							(= curPolygon NULL)
						)
					)
					(`t
						(if (and curPolygon (== state Editing))
							(curPolygon getAccessType:)
						)
					)
					(`d
						(if (== state Editing)
							(if curPolygon
								(self deletePt:)
							)
						else (if (== state Adding)
							(self finishAdding:)
						))
					)
					(`h
						(switch state
							(0 (DoPrint 943 2 30 1))
							(1 (DoPrint 943 3))
						)
					)
					(`u
						(self undo:)
					)
					(`m
						(self showMap: -1)
					)
					(`@v
						(self showMap: VMAP)
					)
					(`@c
						(self showMap: CMAP)
					)
					(`a
						(DoPrint 943 4 30 1)
					)
					(`r
						(if (== state Editing)
							(self draw:)
						)
					)
					(`x
						(return (self exit:))
					)
					(ESC
						(if (== state Adding)
							(self finishAdding:)
						)
					)
				)
			)
		)
		(return 0)
	)
	
	(method (changeState newState)
		(if curMenu (curMenu dispose:))
		(= curMenu
			(switch (= state newState)
				(0 addMenu)
				(1 editMenu)
				(2 dragMenu)
				(else  0)
			)
		)
		(if curMenu (curMenu init:))
	)
	
	(method (draw)
		(self eachElementDo: #restore)
		(self eachElementDo: #save)
		(self eachElementDo: #draw)
		(Graph GShowBits 0 0 190 320 curMap)
	)
	
	(method (select param1 param2 &tmp temp0 theCurPolygon temp2 theTheCurPolygon temp4)
		(= temp0 32767)
		(= theCurPolygon 0)
		(= temp4 (FirstNode elements))
		(while temp4
			(if
				(<
					(= temp2
						((= theTheCurPolygon (NodeValue temp4)) param1: x y)
					)
					temp0
				)
				(= temp0 temp2)
				(= theCurPolygon theTheCurPolygon)
			)
			(= temp4 (NextNode temp4))
		)
		((= curPolygon theCurPolygon) setCurClosest: param2)
	)
	
	(method (selectPt &tmp newEvent)
		(self select: 446 1)
		(= newEvent (Event new:))
		(GlobalToLocal newEvent)
		(= x (newEvent x?))
		(= y (newEvent y?))
		(newEvent dispose:)
	)
	
	(method (addPt)
		(self saveForUndo:)
		(if (not curPolygon)
			(self add:)
			(curPolygon add: x y 0)
		)
		(curPolygon add: x y)
	)
	
	(method (finishAdding &tmp polyEditFirst)
		(cond 
			(curPolygon
				(curPolygon closed: 1)
				(if (> (curPolygon size?) 1)
					(curPolygon deletePt: (curPolygon last:) advance:)
				)
				(self draw:)
				(curPolygon getAccessType:)
			)
			((not (= polyEditFirst (self first:))) (= curPolygon 0))
			(else (= curPolygon (NodeValue polyEditFirst)) (self draw:))
		)
		(if curPolygon (self changeState: 1))
	)
	
	(method (movePt param1 param2)
		(curPolygon startRedraw:)
		(self eachElementDo: #restore)
		(curPolygon movePt: param1 param2)
		(self eachElementDo: #save)
		(self eachElementDo: #draw)
		(curPolygon endRedraw:)
	)
	
	(method (insertPt)
		(self eachElementDo: #restore)
		(self select: 445 0)
		(self saveForUndo:)
		(curPolygon insertPt: x y)
		(self changeState: 3)
		(self eachElementDo: #save)
		(self eachElementDo: #draw)
		(Graph GShowBits 0 0 190 320 curMap)
	)
	
	(method (deletePt &tmp temp0)
		(self eachElementDo: #restore)
		(self select: 446 0)
		(self saveForUndo:)
		(curPolygon deletePt:)
		(if (not (curPolygon size?))
			(self delete: curPolygon)
			(if (not size) (self changeState: 0))
		)
		(self eachElementDo: #save)
		(self eachElementDo: #draw)
		(Graph GShowBits 0 0 190 320 curMap)
	)
	
	(method (undo &tmp temp0 theUndoPoly theUndoPrvPoly theUndoPolyBuf theUndoX theUndoY theUndoState)
		(if (not undoPoly) (Print 943 5) (return))
		(= theUndoPoly undoPoly)
		(= theUndoPrvPoly undoPrvPoly)
		(= theUndoPolyBuf undoPolyBuf)
		(= theUndoX undoX)
		(= theUndoY undoY)
		(= theUndoState undoState)
		(self saveForUndo: 0)
		(self eachElementDo: #restore)
		(if (= curPolygon theUndoPoly)
			(if (not (self contains: curPolygon))
				(= curPolygon (self add:))
				(if theUndoPrvPoly
					(self addAfter: theUndoPrvPoly curPolygon)
				else
					(self addToFront: curPolygon)
				)
			)
			(curPolygon undo: theUndoPolyBuf)
		else
			(= curPolygon (self add:))
		)
		(Memory 3 theUndoPolyBuf)
		(= x theUndoX)
		(= y theUndoY)
		(self changeState: theUndoState)
		(self eachElementDo: #save)
		(self eachElementDo: #draw)
		(Graph GShowBits 0 0 190 320 curMap)
		(SetCursor normalCursor 1 x y)
	)
	
	(method (saveForUndo param1)
		(if (= undoPoly curPolygon)
			(= undoPrvPoly (self prev: (= undoPoly curPolygon)))
			(if (and (or (not argc) param1) undoPolyBuf)
				(Memory 3 undoPolyBuf)
			)
			(= undoPolyBuf (curPolygon saveForUndo:))
		)
		(= undoX x)
		(= undoY y)
		(= undoState state)
	)
	
	(method (advanceRetreat param1 param2 &tmp temp0 temp1)
		(= temp1 (FindKey elements curPolygon))
		(if
			(and
				(not (= temp0 (self param1: temp1)))
				(not (= temp0 (self param2: temp1)))
			)
			(= temp0 temp1)
		)
		(= curPolygon (NodeValue temp0))
		(curPolygon setCur: (curPolygon curNode?))
	)
	
	(method (readObstacles)
		(if (curRoom obstacles?)
			((curRoom obstacles?)
				eachElementDo: #perform readObstacle
			)
		)
	)
	
	(method (writeObstacles)
		(if (curRoom obstacles?)
			((curRoom obstacles?) eachElementDo: #dispose release:)
		)
		(self eachElementDo: #writeObstacle)
	)
	
	(method (showMap theCurMap)
		(if (== theCurMap -1)
			(if (== curMap 1) (= theCurMap 4) else (= theCurMap 1))
		)
		(if (!= curMap theCurMap)
			(self eachElementDo: #restore)
			(= curMap theCurMap)
			(self eachElementDo: #save)
			(self eachElementDo: #draw)
			(Graph GShowBits 0 0 190 320 curMap)
		)
	)
	
	(method (exit &tmp [temp0 100] temp100 newFile temp102)
		(if (== state 0) (self finishAdding:))
		(self showMap: 1)
		(if (not curPolygon) (return 1))
		(self eachElementDo: #check)
		(if (not fileName)
			(Format @fileName 943 6 (curRoom curPic?))
		)
		(if
			(not
				(= temp100
					(PrintD
						80
						{Save Polygons}
						{File:_}
						41
						@fileName
						25
						102
						81
						{ Save_}
						1
						4
						1
						81
						{Abandon}
						2
						4
						5
						81
						{Cancel}
						0
						4
						5
					)
				)
			)
			(return 0)
		)
		(if (== temp100 2) (return 1))
		(if
			(and
				(FileIO 10 @fileName)
				(not
					(= temp100
						(PrintD
							(Format @temp0 943 7 @fileName)
							102
							81
							{Replace}
							1
							81
							{Append}
							2
							81
							{Cancel}
							0
						)
					)
				)
			)
			(return 0)
		)
		(= temp102 (if (== temp100 1) 2 else 0))
		(if
			(not
				((= newFile (File new:)) name: @fileName open: temp102)
			)
			(Printf 943 8 (newFile name?))
			(newFile dispose:)
			(return 0)
		)
		(newFile
			writeString: (Format @temp0 943 9 {Polygon Editor 1.1})
		)
		(newFile
			writeString:
				(Format
					@temp0
					943
					10
					{Dynamic Obstacles}
					(curRoom curPic?)
				)
		)
		(newFile writeString: {\t\t(curRoom addObstacle:\0D\n})
		(self eachElementDo: #writeFile newFile)
		(newFile writeString: {\t\t)\0D\n})
		(newFile dispose:)
		(return 1)
	)
)
