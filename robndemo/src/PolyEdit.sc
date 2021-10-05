;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	POLYEDIT.SC
;;;;	(c) Sierra On-Line, Inc, 1990
;;;;
;;;;	Author: Mark Wilden (original version by Chad Bye)
;;;;
;;;;	Edit and create Polygons
;;;;
;;;;	Invoked by (PolygonEditor doit:)
;;;;	Writes polygon definitions to a file on exit
;;;;
(script# POLYEDIT)
(include game.sh)
(use Main)
(use Intrface)
(use PrintD)
(use Polygon)
(use Window)
(use File)
(use System)


;;;(procedure
;;;	DoPrint
;;;	FindBoundingRect
;;;	LineDist
;;;	Dot
;;;)

;(define PrgTitle			{Polygon Editor 1.11})
(define LineColor			0) ;vBLACK
(define CtrlLineColor	4) ;vRED
;(define ShowScr 			Graph GShowBits 0 0 190 320 curMap)

(define NUndoFlds	3)

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
	rTop		; set by FindBoundingRect (multiple return values)
	rLft		;		"				"
	rBot		;		"				"
	rRgt		;		"				"
	
	editMenuItems = [
		{EDITING}	0		0
		{About}		0		0
		{Map}			0		0
		{Create}		0		0
		{Type}		0		0
		{Undo}		0		0
		{Help}		0		0
		{eXit}		`x		0
		0
	]

	addMenuItems = [
		{CREATING}	0		0
		{About}		0		0
		{Map}			0		0
		{Done}		0		0
		{Undo}		0		0
		{Help}		0		0
		{eXit}		`x		0
		0
	]
)
(define MaxLen			80)
(define NArrayFlds	3)

(class ClickMenu of Object
	(properties
		name		"ClickMenu"
		text		0
		array		0
	)
;;;	(methods
;;;		handleEvent
;;;	)

	(method (init theArray
					  &tmp itemNum base theText theWidth [r 4] [str 40] totWidth chr)
		(= text (Memory MNewPtr (+ MaxLen 1)))

		(Memory MWriteWord text 0)
		(= base (= array theArray))
		(= totWidth 0)
		(for ((= itemNum 0))
			  (= theText (Memory MReadWord base))
			  ((++ itemNum) (+= base (* 2 NArrayFlds)))
			(StrCpy @str theText)
			(if (not itemNum)
				(StrCat @str {: })
			)
			(StrCat @str {_})
			(StrCat text @str)
			(TextSize @r @str SYSFONT 0)
			(+= totWidth [r 3])
			(Memory MWriteWord (+ base 4) totWidth)
			(if (not (Memory MReadWord (+ base 2)))
				(= chr (StrAt theText 0))
				; convert to lower case
				(if (<= `A chr `Z)
					(+= chr (- `a `A))
				)
				(Memory MWriteWord (+ base 2) chr)
			)
		)
		(DrawStatus text)
	)
	
	(method (handleEvent event &tmp base itemNum)
		(if (!= (event type?) mouseDown)
			(return 0)
		)
		(if (>= (event y?) 10)
			(return 0)
		)
		(= base array)
		(for ((= itemNum 0))
			  (Memory MReadWord base)
			  ((++ itemNum) (+= base (* 2 NArrayFlds)))
			(if (and
					(< (event x?) (Memory MReadWord (+ base 4)))
					itemNum
				 )
				(event
					type:		keyDown,
					message:	(Memory MReadWord (+ base 2))
				)
				; don't claim it: pass its keystroke to client
				(return 0)
			)
		)
		(return (event claimed: TRUE))
	)
	
	(method (dispose)
		(Memory MDisposePtr text)
		(super dispose:)
	)
)

(instance editMenu of ClickMenu
	(method (init)
		(super init: @editMenuItems)
	)
)

(instance addMenu of ClickMenu
	(method (init)
		(super init: @addMenuItems)
	)
)

(class _EditablePoint ;kindof RootObj
	(properties
		x				0
		y				0
		underBits	0
	)
;;;	(methods
;;;		new
;;;		yourself
;;;		dispose
;;;		draw
;;;		save
;;;		restore
;;;	)
	
	(method (new)
		(return (Clone self))
	)

	(method (yourself)
		(return self)
	)

	(method (draw pt &tmp visualColor ctrlColor)
		(if (== curMap CMAP)
			(= visualColor -1)
			(= ctrlColor CtrlLineColor)
		else
			(= visualColor LineColor)
			(= ctrlColor -1)
		)
		(Graph GDrawLine y x (pt y?) (pt x?) visualColor -1 ctrlColor)
	)

	(method (save pt)
		(FindBoundingRect x y (pt x?) (pt y?))
		(if underBits
			(UnLoad MEMORY underBits)
		)
		(= underBits (Graph GSaveBits rTop rLft rBot rRgt curMap))
	)

	(method (restore pt)
		(if underBits
			(Graph GRestoreBits underBits)
			(= underBits 0)
		)
	)

	(method (dispose)
		(if underBits
			(UnLoad MEMORY underBits)
			(= underBits 0)
		)
		(DisposeClone self)
	)
)

(class _EditablePolygon kindof List
	(properties
		curNode		NULL
		curPt			NULL
		closed		FALSE
		type			PBarredAccess

		closestPt	NULL
		lsTop			0
		lsLeft		0
		lsBottom		0
		lsRight		0
	)

;;;	(methods
;;;		draw
;;;		advance
;;;		retreat
;;;		setCur
;;;		setCurClosest
;;;		insertPt
;;;		deletePt
;;;		movePt
;;;		undo
;;;		saveForUndo
;;;		getDistToLine
;;;		getDistToPt
;;;		startRedraw
;;;		endRedraw
;;;		restore
;;;		save
;;;		eachLineDo
;;;		writeObstacle
;;;		writeFile
;;;		getAccessType
;;;		check
;;;	)

	(method (getAccessType &tmp rc)
		(if (= rc (PrintD
					#title: {Polygon access type}
					#button:	{Total} 1
					#button:	{_Near_} 2
					#button:	{_Barred_} 3
					#button:	{_Container_} 4
					#first:	type
			 ))
			(= type (- rc 1))
		)
	)

	(method (writeFile file &tmp lineLen len pt node [str 10] frst)
		(file writeString:
			{\09\09\09((Polygon new:)\r}
			{\09\09\09\09type: }
				(switch type
					(PTotalAccess {PTotalAccess})
					(PNearestAccess {PNearestAccess})
					(PBarredAccess {PBarredAccess})
					(PContainedAccess {PContainedAccess})
				)
				{,\r}
		)

		(file writeString: {\09\09\09\09init:\09})
		(= frst TRUE)
		(= lineLen 17)
		(for ((= node (FirstNode elements)))
			  node
			  ((= node (NextNode node)))
			(= pt (NodeValue node))
			(Format @str POLYEDIT 0 (pt x?) (pt y?))
			(if (>= (+= lineLen (= len (+ (StrLen @str) 1))) 80)
				(file writeString: {\r\09\09\09\09\09\09})
				(= frst TRUE)
				(= lineLen (+ 17 len))
			)
			(if (not frst)
				(file writeString: { })
			)
			(file writeString: @str)
			(= frst FALSE)
		)
		(file writeString: {,\r})

		(file writeString:
			{\09\09\09\09yourself\r}
			{\09\09\09)\r}
		)
	)
	
	(method (check &tmp
						node nextNode point nextPoint
						angle delta firstNode totAngle firstTime lastAngle 
						backNode backPoint savX savY
						lastNode cmpNode cmpPoint
			  )
		; delete duplicate points
		(for ((= node (self first:)))
			  node
			  ((= node (NextNode node)))
			(= point (NodeValue node))
			(for ((= cmpNode (NextNode node)))
				  cmpNode
				  ((= cmpNode (NextNode cmpNode)))
				(= cmpPoint (NodeValue cmpNode))
				(if (and
						(== (point x?) (cmpPoint x?))
						(== (point y?) (cmpPoint y?))
					 )
					(= cmpNode (PrevNode cmpNode))
					(self delete: cmpPoint)
					(cmpPoint dispose:)
				)
			)
		)

		; check that polygon is defined clockwise
		(= angle 0)
		(= lastAngle 0)
		(= totAngle 0)
		(= firstTime TRUE)
		(for ((= node (= firstNode (self first:))))
			  TRUE
			  ((= node nextNode))

			(= point (NodeValue node))
			(= nextNode (self next: node))
			(= nextPoint (NodeValue nextNode))

			(= angle (ATan (point x?) (point y?) (nextPoint x?) (nextPoint y?)))
			
			(if (not firstTime)
				(= delta (- angle lastAngle))
				(if (> delta 180)
					(-= delta 360)
				else (if (< delta -180)
					(+= delta 360)
				))
				(+= totAngle delta)
			)

			(= lastAngle angle)

			(breakif (and (== node firstNode) (not firstTime)))
			(= firstTime FALSE)
		)

		;reverse direction of container polygons.
		(if (== type PContainedAccess)
			(= totAngle (- totAngle))
		)
		
		(if (== totAngle -360)
			; it's counter-clockwise so invert it
			(for ((= node (self first:)) (= backNode (self last:)))
				  (and (!= node backNode) (!= node (NextNode backNode)))
				  ((= node (NextNode node)) (= backNode (PrevNode backNode)))
				(= point (NodeValue node))
				(= backPoint (NodeValue backNode))
				(= savX (point x?))
				(= savY (point y?))
				(point x: (backPoint x?))
				(point y: (backPoint y?))
				(backPoint x: savX)
				(backPoint y: savY)
			)
		else (if (!= totAngle 360)
			(Printf POLYEDIT 1 name totAngle)
		))
	)
	
	(method (writeObstacle &tmp cp buf node pt newPoly)
		(= buf (Memory MNewPtr (* size 4)))
		(for ((= node (FirstNode elements)) (= cp buf))
			  node
			  ((= node (NextNode node)) (+= cp 4))
			(= pt (NodeValue node))
			(Memory MWriteWord cp (pt x?))
			(Memory MWriteWord (+ cp 2) (pt y?))
		)

		(curRoom addObstacle:
			((Polygon new:)
				type:		type,
				points:	buf,
				size:		size,
				dynamic:	TRUE,
				yourself:
			)
		)
	)

	(method (movePt aX aY)
		(curPt x: aX, y: aY)
	)
	
	(method (restore)
		(self eachLineDo: #restore:)
	)
	
	(method (save)
		(self eachLineDo: #save:)
	)
	
	(method (draw)
		(self eachLineDo: #draw:)
	)

	(method (add aX aY displayCursor)
		(super add:
			(= curPt
				((_EditablePoint new:)
					x:	aX,
					y:	aY,
					yourself:
				)
			)
		)
		(self setCur: (FindKey elements curPt) displayCursor)
	)
	
	(method (advance &tmp node)
		(self setCur: (self next: curNode))
	)
	
	(method (retreat &tmp node)
		(self setCur: (self prev: curNode))
	)
	
	(method (setCur node displayCursor)
		(if (= curNode node)
			(= curPt (NodeValue node))
			(if (or (< argc 2) displayCursor)
				(SetCursor normalCursor TRUE (curPt x?) (curPt y?))
			)
		)
	)
	
	(method (setCurClosest showCsr)
		(self setCur: (FindKey elements closestPt) showCsr)
	)

	(method (startRedraw &tmp nextNode prevNode lastNode nextPt prevPt)
		(if (= nextNode (self next: curNode))
			(= nextPt (NodeValue nextNode))
		else
			(= nextPt curPt)
		)
		(if (= prevNode (self prev: curNode))
			(= prevPt (NodeValue prevNode))
		else
			(= prevPt curPt)
		)
		(FindBoundingRect
			(prevPt x?) (prevPt y?)
			(curPt x?) (curPt y?)
			(nextPt x?) (nextPt y?)
		)
		(= lsTop rTop)
		(= lsLeft rLft)
		(= lsBottom rBot)
		(= lsRight rRgt)
	)

	(method (endRedraw)
		(FindBoundingRect
			(curPt x?) (curPt y?)
			lsLeft lsTop
			lsRight lsBottom
		)
		(Graph GShowBits rTop rLft rBot rRgt curMap)
	)

	(method (getDistToPt aX aY &tmp lastNode node pt dist closestDist)
		(= closestDist INFINITY)
		(for ((= node (self first:)) (= lastNode (self last:)))
			  TRUE
			  ((= node (self next: node)))
			(= pt (NodeValue node))
			(if (< (= dist (GetDistance aX aY (pt x?) (pt y?))) closestDist)
				(= closestDist dist)
				(= closestPt pt)
			)
			(breakif (== node lastNode))
		)
		(return closestDist)
	)

	(method (getDistToLine aX aY &tmp lastNode node pt dist closestDist
												 nextPt nextNode)
		(if (< size 2)
			(return (self getDistToPt: aX aY &rest))
		)

		(= closestDist INFINITY)
		(for ((= node (self first:)) (= lastNode (self last:)))
			  TRUE
			  ((= node nextNode))
			(= pt (NodeValue node))
			(= nextNode (self next: node))
			(= nextPt (NodeValue nextNode))
			(if (<
					(= dist (LineDist (pt x?) (pt y?) (nextPt x?) (nextPt y?) aX aY))
					closestDist
				 )
				(= closestDist dist)
				(= closestPt pt)
			)
			(breakif (== node lastNode))
		)
		(return closestDist)
	)
	
	(method (insertPt aX aY &tmp newPt)
		(= newPt
			((_EditablePoint new:)
				x:	aX,
				y:	aY,
				yourself:
			)
		)

		(self addAfter: closestPt newPt)
		(self setCur: (FindKey elements newPt))
	)

	(method (eachLineDo doWhat &tmp node nextNode obj nextObj lastNode)
		(for ((= node (self first:)) (= lastNode (self last:)))
			  (or (!= node lastNode) closed)
			  ((= node nextNode))

			(= nextNode (self next: node))
			
			; set variables because of problem with &rest
			(= obj (NodeValue node))
			(= nextObj (NodeValue nextNode))
			(obj doWhat: nextObj &rest)
			
			(breakif (== node lastNode))
		)
	)
	
	(method (deletePt &tmp newCur)
		(if (== curNode (= newCur (self prev: curNode)))
			(= newCur NULL)
		)
		(self delete: curPt)
		(curPt dispose:)
		(self setCur: newCur)
	)
	
	(method (next node &tmp retNode)
		(= retNode (super next: node))
		(if (and closed (not retNode))
			(return (super first:))
		)
		(return retNode)
	)

	(method (prev node &tmp retNode)
		(= retNode (super prev: node))
		(if (and closed (not retNode))
			(return (super last:))
		)
		(return retNode)
	)

	(method (saveForUndo &tmp undoBuffer cp node pt)
		(= cp (= undoBuffer (Memory MNewPtr (* 2 (+ (* 2 size) NUndoFlds)))))
		(Memory MWriteWord cp closed)
		(+= cp 2)
		(Memory MWriteWord cp size)
		(+= cp 2)
		(Memory MWriteWord cp (self indexOf: curPt))
		(+= cp 2)
		(for ((= node (self first:)))
			  node
			  ((= node (NextNode node)) (+= cp 2))
			(= pt (NodeValue node))
			(Memory MWriteWord cp (pt x?))
			(Memory MWriteWord (+= cp 2) (pt y?))
		)
		(return undoBuffer)
	)
	
	(method (undo undoBuffer &tmp lastUndoBuffer aSize i curPtNum)
		(self
			eachElementDo: #dispose:,
			release:
		)
		(= closed (Memory MReadWord undoBuffer))
		(+= undoBuffer 2)
		(= aSize (Memory MReadWord undoBuffer))
		(+= undoBuffer 2)
		(= curPtNum (Memory MReadWord undoBuffer))
		(+= undoBuffer 2)
		(for ((= i 0))
			  (< i aSize)
			  ((++ i) (+= undoBuffer 2))
			(self add:
				(Memory MReadWord undoBuffer)
				(Memory MReadWord (+= undoBuffer 2))
				FALSE
			)
		)
		(self setCur: (FindKey elements (self at: curPtNum)) FALSE)
	)
)

(instance readObstacle of Code
	(method (doit obstacle &tmp i base curPoly)
		(= curPoly (PolygonEditor add:))
		(for ((= i 0) (= base (obstacle points?)))
			  (< i (obstacle size?))
			  ((++ i) (+= base 4))
			(curPoly
				add:
					(Memory MReadWord base)
					(Memory MReadWord (+ base 2))
					FALSE,
				type:	(obstacle type?)
			)
		)
		(curPoly closed: TRUE)
	)
)

(class PolygonEditor of List
;;;	(enum
;;;		Adding
;;;		Editing
;;;		Dragging
;;;		Inserting
;;;	)
	(properties
		name			"PolyEdit"
		curPolygon	NULL
		x				0
		y				0
		state			Adding
		isMouseDown	0
		curMenu		0
		
		undoPrvPoly	0
		undoPoly		0
		undoPolyBuf	0	
		undoX			0	
		undoY			0	
		undoState	0	
	)

;;;	(methods
;;;		init
;;;		doit
;;;		handleEvent
;;;		changeState
;;;		dispose
;;;
;;;		draw
;;;		select
;;;		selectPt
;;;		addPt
;;;		finishAdding
;;;		movePt
;;;		insertPt
;;;		deletePt
;;;		undo
;;;		saveForUndo
;;;		advanceRetreat
;;;		readObstacles
;;;		writeObstacles
;;;		showMap
;;;		exit
;;;	)

	(method (handleEvent event &tmp mods lastX lastY [str 20])
		(= lastX x)
		(= lastY y)
		(= x (event x?))
		(= y (event y?))

		(switch (event type?)
			(nullEvt
				(if curPolygon
					(if (and
							isMouseDown
							(not (OneOf state Adding Dragging))
							(> (+ (Abs (- lastX x)) (Abs (- lastY y))) 1)
						 )
						(if (!= state Inserting)
							; else insertPt: has already saved state
							(self saveForUndo:)
						)
						(self changeState: Dragging)
					)
					(if (and
							(OneOf state Dragging Adding)
							(or (!= lastX x) (!= lastY y))
						 )
						(self movePt: x y)
					)

					(if (== state Dragging)
						(DrawStatus (Format @str POLYEDIT 2 x y))
					)
				)
			)

			(mouseDown
				(= mods (event modifiers?))
				(= isMouseDown TRUE)
				(cond
					((& mods ctrlDown)
						(if (== state Adding)
							(self finishAdding:)
							(= isMouseDown FALSE)
						else
							(self insertPt:)
						)
					)
					((& mods shiftDown)
						(if (!= state Adding)
							(self deletePt:)
						)
						; don't allow dragging after deleting
						(= isMouseDown FALSE)
					)
					(else
						(if (== state Adding)
							(self addPt:)
						else
							(self selectPt:)
						)
					)
				)
			)

			(mouseUp
				(= isMouseDown FALSE)
				(if (OneOf state Dragging Inserting)
					(self changeState: Editing)
				)
			)

			(keyDown
				; map some synonyms
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
							(Adding
								(DoPrint POLYEDIT 3
								#mode:	teJustCenter
								 )
							)

							(Editing
								(DoPrint POLYEDIT 4
								)
							)
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
						(DoPrint POLYEDIT 5
							#mode: teJustCenter
						)
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
		(if curMenu
			(curMenu dispose:)
		)
		(= curMenu
			(switch (= state newState)
				(Adding		addMenu)
				(Editing		editMenu)
				(Dragging	0)
				(else			0)
			)
		)
		(if curMenu
			(curMenu init:)
		)
	)
	
	(method (readObstacles)
		(if (curRoom obstacles?)
			((curRoom obstacles?) eachElementDo: #perform: readObstacle)
		)
	)
	
	(method (writeObstacles)
		(if (curRoom obstacles?)
			((curRoom obstacles?)
				eachElementDo: #dispose:,
				release:
			)
		)
		(self eachElementDo: #writeObstacle:)
	)
	
	(method (add) 
		(super add: (= curPolygon (_EditablePolygon new:)))
		(return curPolygon)
	)

	(method (draw)
		(self eachElementDo: #restore:)
		(self eachElementDo: #save:)
		(self eachElementDo: #draw:)
		(Graph GShowBits 0 0 190 320 curMap)
	)

	(method (selectPt &tmp event)
		(self select: #getDistToPt: TRUE)
		(= event (Event new:))
		(GlobalToLocal event)
		(= x (event x?))
		(= y (event y?))
		(event dispose:)
	)

	(method (addPt)
		(self saveForUndo:) 
		(if (not curPolygon)
			(self add:)
			(curPolygon add: x y FALSE)
		)
		(curPolygon add: x y)
	)

	(method (finishAdding &tmp node)
		(if curPolygon
			(curPolygon closed: TRUE)
			(if (> (curPolygon size?) 1)
				(curPolygon
					deletePt: (curPolygon last:),
					advance:
				)
			)
			(self draw:)
			(curPolygon getAccessType:)
		else
			(if (not (= node (self first:)))
				(= curPolygon NULL)
			else
				(= curPolygon (NodeValue node))
				(self draw:)
			)
		)
		(if curPolygon
			(self changeState: Editing)
		)
	)

	(method (movePt aX aY)
		(curPolygon startRedraw:)
		(self eachElementDo: #restore:)
		(curPolygon movePt: aX aY)
		(self eachElementDo: #save:)
		(self eachElementDo: #draw:)
		(curPolygon endRedraw:)
	)

	(method (insertPt)
		(self eachElementDo: #restore:)

		(self select: #getDistToLine FALSE)
		(self saveForUndo:)
		(curPolygon insertPt: x y)
		(self changeState: Inserting)

		(self eachElementDo: #save:)
		(self eachElementDo: #draw:)
		(Graph GShowBits 0 0 190 320 curMap)
	)
	
	(method (delete &tmp saveCur)
		; make sure curPolygon is always legit or NULL
		(= saveCur curPolygon)
		(self advanceRetreat: #prev: #next:)
		(if (== curPolygon saveCur)
			(= curPolygon NULL)
		)
		(super delete: saveCur &rest)
		(saveCur dispose:)
	)

	(method (deletePt &tmp nextPoly)
		(self eachElementDo: #restore:)

		(self select: #getDistToPt FALSE)
		(self saveForUndo:)
		(curPolygon deletePt:)
		(if (not (curPolygon size?))
			(self delete: curPolygon)
			(if (not size)
				(self changeState: Adding)
			)
		)

		(self eachElementDo: #save:)
		(self eachElementDo: #draw:)
		(Graph GShowBits 0 0 190 320 curMap)
	)

	(method (select distCalc showCsr &tmp closestDist closestPoly dist poly node)
		; find polygon w/line or pt closest to point
		(= closestDist INFINITY)
		(= closestPoly NULL)
		(for ((= node (FirstNode elements)))
			  node
			  ((= node (NextNode node)))
			(= poly (NodeValue node))
			(if (< (= dist (poly distCalc: x y)) closestDist)
				(= closestDist dist)
				(= closestPoly poly)
			)
		)
		((= curPolygon closestPoly) setCurClosest: showCsr)
	)
	
	(method (advanceRetreat dir1 dir2 &tmp newNode curPolyNode)
		(= curPolyNode (FindKey elements curPolygon))
		(if (not (= newNode (self dir1: curPolyNode)))
			(if (not (= newNode (self dir2: curPolyNode)))
				(= newNode curPolyNode)
			)
		)

		(= curPolygon (NodeValue newNode))
		(curPolygon setCur: (curPolygon curNode?))
	)

	(method (saveForUndo throwAwayOld)
		(if (= undoPoly curPolygon)
			(= undoPrvPoly (self prev: curPolygon))
			(if (and
					(or (not argc) throwAwayOld)
					undoPolyBuf
				 )
				(Memory MDisposePtr undoPolyBuf)
			)
			(= undoPolyBuf (curPolygon saveForUndo:))
		)
		(= undoX x)
		(= undoY y)
		(= undoState state)
	)

	(method (undo &tmp event
						saveUndoPoly saveUndoPrvPoly
						saveUndoPolyBuf saveUndoX saveUndoY
						saveUndoState)
		(if (not undoPoly)
			(Print POLYEDIT 6)
			(return)
		)
		; save undo state locally, so we can save the current state
		(= saveUndoPoly undoPoly)
		(= saveUndoPrvPoly undoPrvPoly)
		(= saveUndoPolyBuf undoPolyBuf)
		(= saveUndoX undoX)
		(= saveUndoY undoY)
		(= saveUndoState undoState)

		(self saveForUndo: FALSE)

		(self eachElementDo: #restore:)

		(if (= curPolygon saveUndoPoly)
			; was curPolygon deleted?
			(if (not (self contains: curPolygon))
				(= curPolygon (self add:))
				(if saveUndoPrvPoly
					(self addAfter: saveUndoPrvPoly curPolygon)
				else
					(self addToFront: curPolygon)
				)
			)
			(curPolygon undo: saveUndoPolyBuf)
		else
			(= curPolygon (self add:))
		)
		(Memory MDisposePtr saveUndoPolyBuf)
		(= x saveUndoX)
		(= y saveUndoY)
		(self changeState: saveUndoState)

		(self eachElementDo: #save:)
		(self eachElementDo: #draw:)
		(Graph GShowBits 0 0 190 320 curMap)
		(SetCursor normalCursor TRUE x y)
	)

	(method (showMap whichMap)
		(if (== whichMap -1)
			(if (== curMap VMAP)
				(= whichMap CMAP)
			else
				(= whichMap VMAP)
			)
		)

		(if (!= curMap whichMap)
			(self eachElementDo: #restore:)
			(= curMap whichMap)
			(self eachElementDo: #save:)
			(self eachElementDo: #draw:)
			(Graph GShowBits 0 0 190 320 curMap)
		)
	)
	
	(method (init)
		; start with a clean slate (may be lines left over from previous
		; editing session)
		(DrawPic (curRoom curPic?) PLAIN TRUE currentPalette)
		(if (!= overlays -1)
			(DrawPic overlays PLAIN FALSE currentPalette)
		)
		(addToPics doit:)

		(cast eachElementDo: #stopUpd:)
		(Animate (cast elements?) FALSE)

		(= saveSystemWindow systemWindow)
		(= systemWindow SysWindow)
		(= curMap VMAP)
		(self readObstacles:)
		(self changeState: (if size Editing else Adding))
		(self draw:)
	)

	(method (doit &tmp event [str 100])
		(self init:)
		(repeat
			(= event (Event new:))
			(if (not (and curMenu (curMenu handleEvent: event)))
				(GlobalToLocal event)
				(breakif (self handleEvent: event))
			)
			(event dispose:)
		)
		(event dispose:)
		(self dispose:)
	)

	(method (exit &tmp [str 100] rc file fileMode)
		(if (== state Adding)
			(self finishAdding:)
		)

		(self showMap: VMAP)
		
		(if (not curPolygon)
			(return 1)
		)

		(self eachElementDo: #check:)

		(if (not fileName)
			(Format @fileName POLYEDIT 7 (curRoom curPic?))
		)
		(= rc (PrintD
			#title:	{Save Polygons}
			{File: } #edit: @fileName 25
			#new:
			#button: {_Save_} 1 #x: 1
			#button: {Abandon} 2 #x: 5
			#button: {Cancel} 0 #x: 5
		))
		
		(if (not rc)
			(return 0)
		)
		(if (== rc 2)
			(return 1)
		)

		(if (FileIO fileExists @fileName)
			(= rc (PrintD
				(Format @str POLYEDIT 8 @fileName)
				#new:
				#button: {Replace} 1
				#button: {Append} 2
				#button: {Cancel} 0
			))
			(if (not rc)
				(return 0)
			)
		)
		(= fileMode (if (== rc 1) fTrunc else fAppend))
		(if (not ((= file (File new:))
				name:	@fileName,
				open:	fileMode
			 ))
			(Printf POLYEDIT 9 (file name?))
			(file dispose:)
			(return 0)
		)
		(file writeString: (Format @str POLYEDIT 10 {Polygon Editor 1.11}))
		(file writeString: (Format @str POLYEDIT 11
			{Dynamic Obstacles} (curRoom curPic?))
		)
		(file writeString: {\09\09(curRoom addObstacle:\r})
		(self eachElementDo: #writeFile: file)
		(file writeString: {\09\09)\r})

		(file dispose:)
		(return 1)
	)

	(method (dispose)
		(self writeObstacles:)
		(if curMenu
			(curMenu dispose:)
			(= curMenu 0)
		)
		(if undoPolyBuf
			(Memory MDisposePtr undoPolyBuf)
			(= undoPolyBuf 0)
		)
		(DrawStatus { } 0 0)
		(DrawStatus 0)

		(cast eachElementDo: #startUpd:)
		(Animate (cast elements?) FALSE)
		(self eachElementDo: #draw:)

		(= systemWindow saveSystemWindow)
		(DisposeScript PRINTD)
		(DisposeScript FILE)
		(super dispose:)
		(DisposeScript POLYEDIT)
	)
)

(procedure (FindBoundingRect args &tmp i x y)
	(= rRgt (= rBot 0))
	(= rLft (= rTop INFINITY))
	
	(for ((= i 0)) (< i argc) ((+= i 2))
		(= x [args i])
		(= y [args (+ i 1)])
		(if (< y rTop)
			(= rTop y)
		)
		(if (> y rBot)
			(= rBot y)
		)
		(if (< x rLft)
			(= rLft x)
		)
		(if (> x rRgt)
			(= rRgt x)
		)
	)
	(-= rLft 2)
	(-= rTop 2)
	(+= rRgt 2)
	(+= rBot 2)
)

(procedure (Dot x1 y1 x2 y2)											; Larry Scott
; returns dot product between vectors (x1,y1) and (x2,y2)
;
	(return (+
		(*
			(+ (/ x1 2) 1)
			(+ (/ x2 2) 1)
		)
		(*
			(+ (/ y1 2) 1)
			(+ (/ y2 2) 1)
		)
	))
)

(procedure (LineDist X1 Y1 X2 Y2 X3 Y3 &tmp xyDist)			; Larry Scott
;outputs distance that pt is from line 
;
; input
;      Pt1 = (X1,Y1) of start of line segment
;      Pt2 = (X2,Y2) of end of line segment
;      Pt3 = (X3,Y3) of point to find distance to line segment Pt1-Pt2
;

	(if (and
			(<= 0 (Dot (- X2 X1) (- Y2 Y1) (- X3 X1) (- Y3 Y1))) 
			(<= 0 (Dot (- X1 X2) (- Y1 Y2) (- X3 X2) (- Y3 Y2))) 
		 )
		 	
		(return
			(if (= xyDist (GetDistance X1 Y1 X2 Y2))
				(/ 
					(Abs (Dot (- Y2 Y1) (- X1 X2) (- X3 X1) (- Y3 Y1)))
					xyDist
				)
			else
				0
			)
		)
	else
		(return 
			(Min 
				(GetDistance X3 Y3 X1 Y1)
				(GetDistance X3 Y3 X2 Y2)
			)
		)
	)
)

(procedure (DoPrint)
	(Print
		&rest
		#title:	{Polygon Editor 1.11}
		#font:	999
		#width:	240
	)
)
