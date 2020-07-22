;;; Sierra Script 1.0 - (do not remove this comment)
(script# 360)
(include game.sh)
(use Main)
(use Procs)
(use Intrface)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm360 0
)

(local
	local0
	local1
	newRubberBand
	local3
	[local4 80] = [0 0 36 0 70 0 115 0 156 0 188 0 231 0 272 0 0 31 25 36 68 28 117 26 159 33 187 32 226 34 281 26 0 64 33 63 68 76 112 68 148 77 189 66 240 59 276 69 0 103 24 117 -1 -1 -1 -1 159 114 200 111 240 112 276 112 0 141 34 143 -1 -1 -1 -1 157 148 192 146 234 150 271 152]
	[local84 8] = [64 114 119 114 64 152 119 152]
)
(procedure (localproc_1828 param1 param2 param3 param4 param5 &tmp temp0 temp1 temp2 temp3)
	(= temp0 (param1 x?))
	(= temp1 (param1 y?))
	(= temp2 (+ (param1 x?) (param1 width:)))
	(return
		(not
			(if
				(or
					(<= (= temp3 (+ (param1 y?) (param1 height:))) param3)
					(>= temp1 param5)
					(<= temp2 param2)
				)
			else
				(>= temp0 param4)
			)
		)
	)
)

(procedure (localproc_187f param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5)
	(= temp0 (+ (param1 x?) (/ (param1 width:) 2)))
	(= temp1 (+ (param1 y?) (/ (param1 height:) 2)))
	(= temp2 (param2 x?))
	(= temp3 (param2 y?))
	(= temp4 (+ temp2 (param2 width:)))
	(= temp5 (+ temp3 (param2 height:)))
	(return
		(if
			(and
				(<= temp2 temp0)
				(<= temp0 temp4)
				(<= temp3 temp1)
			)
			(<= temp1 temp5)
		else
			0
		)
	)
)

(instance rm360 of Room
	(properties
		lookStr {You are in a room formed out of a giant jigsaw puzzle.__It looks incomplete.}
		picture 361
		style $0007
		south 320
	)
	
	(method (init)
		(super init: &rest)
		(LoadMany 128 360 370 371 372 373)
		(puzzle init:)
		(self setScript: demo6)
	)
	
	(method (dispose)
		(puzzle dispose:)
		(super dispose: &rest)
	)
)

(instance demo6 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(jigsawPiece1 init: setMotion: MoveTo 68 29)
				(= ticks 1)
			)
			(1 (= ticks 1))
			(2
				(if (== (jigsawPiece1 x?) 68)
					(self changeState: 3)
				else
					(self changeState: 1)
				)
			)
			(3
				(jigsawPiece2 init: setMotion: MoveTo 159 33)
				(= ticks 120)
			)
			(4 (= ticks 1))
			(5
				(if (== (jigsawPiece2 x?) 159)
					(self changeState: 6)
				else
					(self changeState: 4)
				)
			)
			(6
				(jigsawPiece3 init: setMotion: MoveTo 240 112)
				(= ticks 120)
			)
			(7 (= ticks 1))
			(8
				(if (== (jigsawPiece3 x?) 240)
					(self changeState: 9)
				else
					(self changeState: 7)
				)
			)
			(9
				(= local0
					(DoDisplay
						{I think I'm starting to get the PICTURE.}
						67
						40
						145
					)
				)
				(= seconds 6)
			)
			(10
				(DoDisplay local0)
				(= ticks 1)
			)
			(11 (curRoom newRoom: 240 8))
		)
	)
)

(class RubberBand of Object
	(properties
		placedList 0
		startX 0
		underLine1 0
		underLine2 0
		underLine3 0
		underLine4 0
		startY 0
		oldEndX 0
	)
	
	(method (init theStartY theOldEndX)
		(super init: &rest)
		(= placedList (= startY theStartY))
		(= startX (= oldEndX theOldEndX))
	)
	
	(method (doit theStartY theOldEndX)
		(self oldEndY:)
		(if (<= theStartY placedList)
			(= theStartY (+ placedList 2))
		)
		(if (<= theOldEndX startX) (= theOldEndX (+ startX 2)))
		(self drawBox: startX placedList theOldEndX theStartY)
		(= startY theStartY)
		(= oldEndX theOldEndX)
	)
	
	(method (dispose)
		(self oldEndY:)
		(super dispose:)
	)
	
	(method (drawBox param1 param2 param3 param4)
		(= underLine1
			(Graph
				GSaveBits
				(- param1 1)
				(- param2 1)
				(+ param1 2)
				(+ param4 2)
				1
			)
		)
		(= underLine2
			(Graph
				GSaveBits
				(- param1 1)
				(- param4 1)
				(+ param3 2)
				(+ param4 2)
				1
			)
		)
		(= underLine3
			(Graph
				GSaveBits
				(- param3 1)
				(- param2 1)
				(+ param3 1)
				(+ param4 1)
				1
			)
		)
		(= underLine4
			(Graph
				GSaveBits
				(- param1 1)
				(- param2 1)
				(+ param3 1)
				(+ param2 1)
				1
			)
		)
		(Graph GDrawLine param1 param2 param1 param4 0 -1 -1)
		(Graph GDrawLine param1 param4 param3 param4 0 -1 -1)
		(Graph GDrawLine param3 param2 param3 param4 0 -1 -1)
		(Graph GDrawLine param1 param2 param3 param2 0 -1 -1)
		(Graph
			GShowBits
			(- param1 1)
			(- param2 1)
			(+ param3 1)
			(+ param4 1)
			1
		)
	)
	
	(method (oldEndY)
		(if underLine1
			(Graph GRestoreBits underLine4)
			(Graph GRestoreBits underLine3)
			(Graph GRestoreBits underLine2)
			(Graph GRestoreBits underLine1)
			(Graph
				GShowBits
				startX
				placedList
				(+ oldEndX 1)
				(+ startY 1)
				1
			)
			(= underLine1 0)
		)
	)
)

(class VObject of Object
	(properties
		view 0
		loop 0
		cel 0
		x 0
		y 0
	)
	
	(method (posn theX theY)
		(= x theX)
		(= y theY)
	)
	
	(method (show)
		(DrawCel view loop cel x y 15)
	)
	
	(method (hide &tmp vObjectWidth vObjectHeight)
		(= vObjectWidth (self width:))
		(= vObjectHeight (self height:))
		(Graph
			GFillRect
			y
			x
			(+ y vObjectHeight)
			(+ x vObjectWidth)
			1
			198
		)
		(Graph
			GShowBits
			y
			x
			(+ y vObjectHeight)
			(+ x vObjectWidth)
		)
	)
	
	(method (onMe theObjOrX &tmp theObjOrXX theObjOrXY theX temp3 theY_2 temp5)
		(= theObjOrXX (theObjOrX x?))
		(= theObjOrXY (theObjOrX y?))
		(= theX x)
		(= theY_2 y)
		(= temp3 (+ theX (self width:)))
		(= temp5 (+ theY_2 (self height:)))
		(return
			(if
				(and
					(<= theX theObjOrXX)
					(<= theObjOrXX temp3)
					(<= theY_2 theObjOrXY)
				)
				(<= theObjOrXY temp5)
			else
				0
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(return
			(cond 
				((event claimed?)
					(return TRUE)
				)
				(
					(and
						(== (event type?) userEvent)
						(== (event message?) verbDo)
						(self onMe: event)
					)
					(event claimed: TRUE)
				)
			)
		)
	)
	
	(method (width)
		(CelWide view loop cel)
	)
	
	(method (height)
		(CelHigh view loop cel)
	)
	
	(method (restoreLines param1 &tmp theX theY temp2 temp3 temp4 temp5 temp6 temp7)
		(= theX x)
		(= theY y)
		(= temp2 (+ x (self width:)))
		(= temp3 (+ y (self height:)))
		(= temp4 (param1 x?))
		(= temp5 (param1 y?))
		(= temp6 (+ (param1 x?) (param1 width:)))
		(= temp7 (+ (param1 y?) (param1 height:)))
		(return
			(not
				(if
				(or (<= temp3 temp5) (>= theY temp7) (<= temp2 temp4))
				else
					(>= theX temp6)
				)
			)
		)
	)
)

(class PuzPiece of VObject
	(properties
		view 0
		loop 0
		cel 0
		x 0
		y 0
		intersect 0
		trueXPos 0
		trueYPos 0
		xAnchorOff 0
		value 0
	)
	
	(method (doit param1 param2 &tmp temp0 puzzleAddToIntSet)
		(if (not ((puzzle pView?) size?))
			(= param1 (- param1 (/ (self width:) 2)))
			(= param2 (- param2 (/ (self height:) 2)))
		)
		(if
		(== self (= puzzleAddToIntSet (puzzle addToIntSet?)))
			(self posn: param1 param2)
		else
			(self posn: (+ param1 trueYPos) (+ param2 xAnchorOff))
		)
	)
	
	(method (posn param1 param2)
		(super posn: param1 param2)
		(if (== self (puzzle addToIntSet?))
			((puzzle curPiece?) posn: x y)
		)
	)
	
	(method (hide)
		(super hide:)
		(puzzle getDragList: self)
	)
	
	(method (handleEvent event &tmp temp0 newEvent eventX eventY newRubberBandPlacedList newRubberBandStartX newRubberBandStartY newRubberBandOldEndX)
		(return
			(cond 
				((Btst 7) 0)
				((event claimed?) (return 1))
				((puzzle addToIntSet?) (return 0))
				((or (!= (event type?) userEvent) (not (self onMe: event))))
				((== (event message?) verbDo)
					((= newRubberBand (RubberBand new:))
						init: (event x?) (event y?)
					)
					(= eventX (event x?))
					(= eventY (event y?))
					(while (!= ((= newEvent (Event new:)) type?) 2)
						(newEvent localize:)
						(if
							(or
								(!= eventX (newEvent x?))
								(!= eventY (newEvent y?))
							)
							(= eventX (newEvent x?))
							(= eventY (newEvent y?))
							(newRubberBand doit: eventX eventY)
						)
						(newEvent dispose:)
					)
					(newEvent dispose:)
					(self hide:)
					(= newRubberBandPlacedList (newRubberBand placedList?))
					(= newRubberBandStartX (newRubberBand startX?))
					(= newRubberBandStartY (newRubberBand startY?))
					(= newRubberBandOldEndX (newRubberBand oldEndX?))
					(puzzle dragList: self)
					(puzzle
						pickPiece:
							newRubberBandPlacedList
							newRubberBandStartX
							newRubberBandStartY
							newRubberBandOldEndX
					)
					(event claimed: TRUE)
				)
				((== (event message?) verbLook)
					(HighPrint 360 0)
				)
			)
		)
	)
	
	(method (yAnchorOff param1 param2)
		(if (self restoreLines: param2) (param1 add: self))
	)
)

(class RPuzzle of Object
	(properties
		addToIntSet 0
		drawIntPieces 0
		rotate 0
		pView 0
		curPiece 0
		notPlacedList 0
		oldX 0
		oldY 0
		dragBits 0
		xExtentOff 0
	)
	
	(method (numPieces &tmp drawIntPiecesSize)
		(if (= drawIntPiecesSize (drawIntPieces size?))
			(theIconBar disable:)
			(if drawIntPiecesSize
				(= addToIntSet
					(drawIntPieces at: (Random 0 (- drawIntPiecesSize 1)))
				)
				(addToIntSet posn: (boxObj x?) (boxObj y?))
				(self depositPiece:)
			)
		else
			(HighPrint 360 1)
		)
	)
	
	(method (getNewPiece param1 param2 &tmp temp0 puzzleRotate)
		(theIconBar enable:)
		(= puzzleRotate (puzzle rotate?))
		(if (pView contains: addToIntSet)
			(addToIntSet
				posn:
					(+ param1 (addToIntSet trueYPos?))
					(+ param2 (addToIntSet xAnchorOff?))
			)
			(if
				(or
					(localproc_187f addToIntSet (puzzleRotate at: 0))
					(localproc_187f addToIntSet (puzzleRotate at: 1))
					(localproc_187f addToIntSet (puzzleRotate at: 2))
					(localproc_187f addToIntSet (puzzleRotate at: 3))
				)
				(self placePiece:)
				(return)
			)
		)
		(if
			(<=
				(= temp0
					(GetDistance
						(addToIntSet x?)
						(addToIntSet y?)
						(addToIntSet intersect?)
						(addToIntSet trueXPos?)
					)
				)
				8
			)
			(addToIntSet
				posn: (addToIntSet intersect?) (addToIntSet trueXPos?)
			)
		)
		(self assignToView:)
		(addToIntSet show:)
		(drawIntPieces delete: addToIntSet)
		(rotate add: addToIntSet)
		(= addToIntSet 0)
		(if (self placeDragList:)
			(Bset 7)
			(Print 360 2)
			(backDoor init:)
		)
	)
	
	(method (placePiece)
		(theIconBar enable:)
		(addToIntSet x: -100 y: -100)
		(self assignToView:)
		(= addToIntSet 0)
	)
	
	(method (dragList theAddToIntSet)
		(theIconBar disable:)
		(= addToIntSet theAddToIntSet)
		(self depositPiece:)
		(addToIntSet hide:)
		(rotate delete: addToIntSet)
		(drawIntPieces add: addToIntSet)
	)
	
	(method (depositPiece)
		(curPiece
			view: (addToIntSet view?)
			loop: (addToIntSet loop?)
			cel: (addToIntSet cel?)
			x: (addToIntSet x?)
			y: (addToIntSet y?)
			setPri: 15
		)
	)
	
	(method (assignToView)
		(curPiece x: -100 y: -100)
		(Animate (cast elements?) 0)
	)
	
	(method (getDragList param1 &tmp newSet temp1 pViewSize)
		((= newSet (Set new:)) add:)
		(rotate eachElementDo: #yAnchorOff newSet param1)
		(= temp1 0)
		(= pViewSize (pView size?))
		(while (< temp1 pViewSize)
			(rotate
				eachElementDo: #yAnchorOff newSet (pView at: temp1)
			)
			(++ temp1)
		)
		(newSet
			delete: param1
			eachElementDo: #show
			release:
			dispose:
		)
	)
	
	(method (pickPiece param1 param2 param3 param4 &tmp temp0 temp1 addToIntSetX addToIntSetY temp4 temp5 theAddToIntSetX theAddToIntSetY temp8 temp9 temp10)
		(if
			(u>=
				(= temp10 (* (- param3 param1) (- param4 param2)))
				16000
			)
			(newRubberBand dispose:)
			(addToIntSet hide:)
			(HighPrint 360 3)
			(return)
		)
		(= addToIntSetX (addToIntSet x?))
		(= temp4
			(+
				(= addToIntSetY (addToIntSet y?))
				(addToIntSet height:)
			)
		)
		(= temp5 (+ addToIntSetX (addToIntSet width:)))
		(if
			(not
				(if (< (- param3 param1) 4)
				else
					(< (- param4 param2) 4)
				)
			)
			(= temp0 0)
			(while (< temp0 ((puzzle rotate?) size?))
				(if
					(and
						(!= addToIntSet (= temp1 ((puzzle rotate?) at: temp0)))
						(!= (temp1 view?) 360)
						(localproc_1828 temp1 param1 param2 param3 param4)
					)
					(temp1
						trueYPos: (- (temp1 x?) (addToIntSet x?))
						xAnchorOff: (- (temp1 y?) (addToIntSet y?))
					)
					(rotate delete: temp1)
					(-- temp0)
					(drawIntPieces add: temp1)
					(pView add: temp1)
					(= theAddToIntSetX (temp1 x?))
					(= theAddToIntSetY (temp1 y?))
					(= temp8 (+ theAddToIntSetX (temp1 width:)))
					(= temp9 (+ theAddToIntSetY (temp1 height:)))
					(if (< theAddToIntSetX addToIntSetX)
						(= addToIntSetX theAddToIntSetX)
					)
					(if (< theAddToIntSetY addToIntSetY)
						(= addToIntSetY theAddToIntSetY)
					)
					(if (> temp9 temp4) (= temp4 temp9))
					(if (> temp8 temp5) (= temp5 temp8))
				)
				(++ temp0)
			)
			(= dragBits (- (addToIntSet x?) addToIntSetX))
			(= xExtentOff (- (addToIntSet y?) addToIntSetY))
			(newRubberBand oldEndY:)
			(addToIntSet hide:)
			(if (pView size?)
				(theGame
					setCursor:
						theCursor
						1
						(= notPlacedList
							(+ addToIntSetX (/ (- temp5 addToIntSetX) 2))
						)
						(= oldX (+ addToIntSetY (/ (- temp4 addToIntSetY) 2)))
				)
				(pView eachElementDo: #hide)
				(theView hide:)
				(= oldY
					(Graph GSaveBits addToIntSetY addToIntSetX temp4 temp5 1)
				)
				(pView eachElementDo: #show)
				(addToIntSet show:)
				(curPiece show:)
				(newRubberBand
					placedList: addToIntSetX
					startX: addToIntSetY
					startY: temp5
					oldEndX: temp4
				)
			else
				(newRubberBand dispose:)
			)
		else
			(newRubberBand dispose:)
		)
		(addToIntSet hide:)
	)
	
	(method (clearView &tmp temp0 addToIntSetX addToIntSetY)
		(= addToIntSetX (addToIntSet x?))
		(= addToIntSetY (addToIntSet y?))
		(Graph GRestoreBits (puzzle oldY?))
		(Graph
			GShowBits
			(newRubberBand startX?)
			(newRubberBand placedList?)
			(newRubberBand oldEndX?)
			(newRubberBand startY?)
			1
		)
		(newRubberBand dispose:)
		(self getNewPiece: 0 0)
		(= temp0 0)
		(while (< temp0 (pView size?))
			(= addToIntSet (pView at: temp0))
			(self getNewPiece: addToIntSetX addToIntSetY)
			(++ temp0)
		)
		(pView release:)
	)
	
	(method (placeDragList &tmp temp0 temp1)
		(return
			(if (== (rotate size?) 40)
				(= temp0 0)
				(while (< temp0 40)
					(if
						(and
							(!= ((= temp1 (rotate at: temp0)) view?) 360)
							(or
								(!= (temp1 x?) (temp1 intersect?))
								(!= (temp1 y?) (temp1 trueXPos?))
							)
						)
						(return 0)
					)
					(++ temp0)
				)
				(return 1)
			else
				0
			)
		)
	)
	
	(method (testForDone &tmp temp0 rotateSize temp2 temp3 temp4 temp5 temp6)
		(= rotateSize (rotate size?))
		(= temp6 0)
		(if (> rotateSize 0) (Bset 8))
		(= temp0 0)
		(while (< temp0 rotateSize)
			(= temp3 ((= temp2 (rotate at: temp0)) x?))
			(= temp4 (temp2 y?))
			(if (== (temp2 value?) 0)
				(= temp6 1)
				(= [global210 0] -2)
			else
				(= [global210 (temp2 value?)] (+ (* temp3 200) temp4))
			)
			(++ temp0)
		)
		(if (not temp6) (= [global210 0] 0))
	)
)

(instance theView of Prop
	(properties)
	
	(method (doit &tmp temp0 temp1 temp2 temp3 puzzleAddToIntSet puzzleNotPlacedList puzzleOldX temp7 temp8)
		(super doit: &rest)
		(if (= puzzleAddToIntSet (puzzle addToIntSet?))
			((= temp1 (Event new: -1)) localize:)
			(= temp2 (temp1 x?))
			(= temp3 (temp1 y?))
			(temp1 dispose:)
			(= puzzleNotPlacedList (puzzle notPlacedList?))
			(= puzzleOldX (puzzle oldX?))
			(if
				(or
					(!= puzzleNotPlacedList temp2)
					(!= puzzleOldX temp3)
				)
				(= temp7 (- temp2 puzzleNotPlacedList))
				(= temp8 (- temp3 puzzleOldX))
				(if (IsObject newRubberBand)
					(if
						(or
							(< (+ temp7 (newRubberBand placedList?)) 1)
							(< (+ temp8 (newRubberBand startX?)) 2)
							(> (+ temp7 (newRubberBand startY?)) 318)
							(> (+ temp8 (newRubberBand oldEndX?)) 198)
						)
						(= temp7 0)
						(= temp8 0)
					)
					(Graph GRestoreBits (puzzle oldY?))
					(Graph
						GShowBits
						(newRubberBand startX?)
						(newRubberBand placedList?)
						(newRubberBand oldEndX?)
						(newRubberBand startY?)
						1
					)
					(newRubberBand
						placedList: (+ temp7 (newRubberBand placedList?))
					)
					(newRubberBand
						startX: (+ temp8 (newRubberBand startX?))
					)
					(newRubberBand
						startY: (+ temp7 (newRubberBand startY?))
					)
					(newRubberBand
						oldEndX: (+ temp8 (newRubberBand oldEndX?))
					)
					(puzzle
						oldY:
							(Graph
								GSaveBits
								(newRubberBand startX?)
								(newRubberBand placedList?)
								(newRubberBand oldEndX?)
								(newRubberBand startY?)
								1
							)
					)
					(puzzleAddToIntSet
						doit:
							(+ (puzzle dragBits?) (newRubberBand placedList?))
							(+ (puzzle xExtentOff?) (newRubberBand startX?))
					)
					((puzzle pView?)
						eachElementDo:
							#doit
							(+ (puzzle dragBits?) (newRubberBand placedList?))
							(+ (puzzle xExtentOff?) (newRubberBand startX?))
					)
					((puzzle pView?) eachElementDo: #show)
					((puzzle addToIntSet?) show:)
				else
					(puzzleAddToIntSet doit: temp2 temp3)
				)
				(puzzle notPlacedList: temp2)
				(puzzle oldX: temp3)
			)
		)
	)
	
	(method (handleEvent event &tmp [temp0 3] puzzleRotate)
		(= puzzleRotate (puzzle rotate?))
		(if
			(and
				(not (event claimed?))
				(== (event type?) userEvent)
				(== (event message?) verbDo)
				(puzzle addToIntSet?)
			)
			(if
				(not
					(if
						(or
							((puzzleRotate at: 0) handleEvent: event)
							((puzzleRotate at: 1) handleEvent: event)
							((puzzleRotate at: 2) handleEvent: event)
						)
					else
						((puzzleRotate at: 3) handleEvent: event)
					)
				)
				(if ((puzzle pView?) size?)
					(puzzle clearView:)
				else
					(puzzle getNewPiece:)
				)
				(event claimed: 1)
			)
		)
	)
)

(instance puzzle of RPuzzle
	(properties)
	
	(method (init &tmp temp0 temp1 temp2 temp3 newPuzPiece temp5 temp6 [temp7 103] temp110 temp111 temp112 temp113 temp114)
		(super init: &rest)
		((= drawIntPieces (List new:)) add:)
		((= pView (List new:)) add:)
		((= rotate (EventHandler new:)) add:)
		((= curPiece theView) view: 370 posn: -100 -100 init:)
		(= temp110 (= temp0 (= temp6 0)))
		(= temp1 0)
		(while (< temp1 5)
			(= temp2 0)
			(while (< temp2 8)
				(if
				(!= [local4 (* (= temp3 (+ (* 8 temp1) temp2)) 2)] -1)
					(= temp5 (+ temp3 temp3))
					(if
						(or
							(and (== temp1 1) (== temp2 2))
							(and (== temp1 3) (== temp2 6))
							(and (== temp1 1) (== temp2 4))
						)
						((= newPuzPiece (PuzPiece new:))
							x: -100
							y: -100
							view: (+ temp0 (if (mod temp1 2) 372 else 370))
							loop: (/ temp3 16)
							cel: (/ temp2 2)
							intersect: [local4 temp5]
							trueXPos: [local4 (+ temp5 1)]
							init:
							show:
						)
					else
						((= newPuzPiece (PuzPiece new:))
							x: [local4 temp5]
							y: [local4 (+ temp5 1)]
							view: (+ temp0 (if (mod temp1 2) 372 else 370))
							loop: (/ temp3 16)
							cel: (/ temp2 2)
							intersect: [local4 temp5]
							trueXPos: [local4 (+ temp5 1)]
							init:
							show:
						)
					)
					(if (= temp111 [global210 temp110])
						(if (== temp111 -2)
							(= temp112 0)
							(= temp113 0)
						else
							(= temp114 (& temp111 $0001))
							(= temp112 (/ (>> temp111 $0001) 100))
							(= temp113
								(+
									(* 2 (mod (>> temp111 $0001) 100))
									(& temp111 $0001)
								)
							)
						)
						(newPuzPiece posn: temp112 temp113 show:)
						(rotate add: newPuzPiece)
					else
						(drawIntPieces add: newPuzPiece)
					)
				else
					(= temp5 (+ temp6 temp6))
					((= newPuzPiece (boxObj new:))
						x: [local84 temp5]
						y: [local84 (+ temp5 1)]
						view: 360
						loop: 0
						cel: temp6
						init:
					)
					(rotate addToFront: newPuzPiece)
					(newPuzPiece show:)
					(++ temp6)
				)
				(= temp0 (- 1 temp0))
				(newPuzPiece value: temp110)
				(++ temp110)
				(++ temp2)
			)
			(++ temp1)
		)
	)
	
	(method (dispose)
		(self testForDone:)
		(drawIntPieces eachElementDo: #dispose release: dispose:)
		(rotate eachElementDo: #dispose release: dispose:)
		(pView eachElementDo: #dispose release: dispose:)
		(super dispose: &rest)
	)
)

(instance backDoor of Prop
	(properties
		x 94
		y 48
		description {back door}
		sightAngle 180
		lookStr {This door is labelled "Doce Omor".}
		view 360
		loop 1
	)
	
	(method (handleEvent event &tmp [temp0 3] puzzleRotate)
		(= puzzleRotate (puzzle rotate?))
		(if
			(and
				(not (event claimed?))
				(self onMe: event)
				(== (event type?) userEvent)
				(== (event message?) verbDo)
			)
			(if
				(not
					(if
						(or
							((puzzleRotate at: 0) handleEvent: event)
							((puzzleRotate at: 1) handleEvent: event)
							((puzzleRotate at: 2) handleEvent: event)
						)
					else
						((puzzleRotate at: 3) handleEvent: event)
					)
				)
				(HighPrint 360 4)
				(event claimed: 1)
			)
		)
	)
)

(instance boxObj of PuzPiece
	(properties
		view 360
		x 67
		y 114
	)
	
	(method (handleEvent event &tmp temp0 puzzleRotate)
		(= temp0 1)
		(cond 
			((event claimed?))
			((not (self onMe: event)) (= temp0 0))
			((!= (event type?) userEvent))
			((== (event message?) verbLook)
				(++ local3)
				(cond 
					((== ((puzzle drawIntPieces?) size?) 0) (HighPrint 360 1))
					((== local3 1) (HighPrint 360 5))
					((== local3 2) (HighPrint 360 6))
					(else (HighPrint 360 7))
				)
			)
			((!= (event message?) verbDo)
				(HighPrint 360 8)
			)
			((puzzle addToIntSet?)
				(cond 
					(((puzzle pView?) size?) (Print 360 9))
					(
						(or
							(localproc_187f
								(puzzle addToIntSet?)
								((= puzzleRotate (puzzle rotate?)) at: 0)
							)
							(localproc_187f
								(puzzle addToIntSet?)
								(puzzleRotate at: 1)
							)
							(localproc_187f
								(puzzle addToIntSet?)
								(puzzleRotate at: 2)
							)
							(localproc_187f
								(puzzle addToIntSet?)
								(puzzleRotate at: 3)
							)
						)
						(puzzle placePiece:)
					)
				)
			)
			(else (puzzle numPieces:))
		)
		(if temp0 (event claimed: 1))
		(event claimed?)
	)
)

(instance jigsawPiece1 of Actor
	(properties
		x 134
		y 130
		yStep 5
		view 372
		cel 1
		priority 15
		signal $1810
		xStep 5
	)
)

(instance jigsawPiece2 of Actor
	(properties
		x 134
		y 130
		yStep 5
		view 372
		cel 2
		priority 15
		signal $1810
		xStep 5
	)
)

(instance jigsawPiece3 of Actor
	(properties
		x 134
		y 130
		yStep 5
		view 372
		loop 1
		cel 3
		priority 15
		signal $1810
		xStep 5
	)
)
