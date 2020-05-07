;;; Sierra Script 1.0 - (do not remove this comment)
(script# GATEPANEL) ;916
(include game.sh)
(use Main)
(use User)
(use Actor)
(use System)


(class GatePanel of View
	(properties
		offsetX 20
		offsetY 14
		maxCol 6
		maxRow 4
		cursorSpace 4
		numButtons 0
		strPointer 0
		strLen 0
		maxLen 0
		row 0
		column 0
		charCount 0
		failed 0
		value 0
	)
	
	(method (init theStrPointer theStrLen theMaxLen)
		(= strPointer theStrPointer)
		(= strLen theStrLen)
		(= maxLen theMaxLen)
		(theIconBar curIcon: (theIconBar at: ICON_DO))
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
		(theIconBar disable:)
		(SetPort 0)
		(super init:)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(directionHandler addToFront: self)
	)
	
	(method (dispose)
		(= failed (= charCount (= strPointer 0)))
		(super dispose:)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(directionHandler delete: self)
	)
	
	(method (handleEvent event &tmp eMod)
		(if (User canInput:)
			(event claimed: TRUE)
			(cond 
				((& (event type?) mouseDown)
					(cond 
						(
							(or
								(& (= eMod (event modifiers?)) (| mouseUp mouseDown))
								(& eMod ctrlDown)
								(& eMod altDown)
							)
							0
						)
						((self onMe: event) (self calcValue: event))
						(else (curRoom notify: 0) (self dispose:))
					)
				)
				((& (event type?) (| keyDown direction))
					(cond 
						((OneOf (event message?) V_TALK 4 6 V_DAGGER 9) 0)
						((OneOf (event message?) V_LOOK V_DO V_WALK V_DAGGER NULL)
							(self moveCursorPosn: event)
						)
						((== (event message?) ENTER)
							(if (self onMe: event)
								(self calcValue: event)
							else
								(curRoom notify: 0)
								(self dispose:)
							)
						)
						(27
							(curRoom notify: 0) (self dispose:)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
				(else
					(event claimed: FALSE)
				)
			)
		)
		(event claimed?)
	)
	
	(method (calcPosn param1)
		(= column
			(Max
				1
				(Min (+ (/ (- (param1 x?) x) offsetX) 1) maxCol)
			)
		)
		(= row
			(Max 0 (Min (/ (- (param1 y?) y) offsetY) maxRow))
		)
	)
	
	(method (calcValue param1 &tmp temp0)
		(= temp0 0)
		(= value 0)
		(if strPointer (= temp0 (WordAt strPointer charCount)))
		(self calcPosn: param1)
		(if
			(and
				(<= (= value (+ column (* row maxCol))) numButtons)
				(self buttonSetup:)
			)
			(self failCheck: temp0)
			(if (and (== (++ charCount) strLen) (not failed))
				(curRoom notify: (not failed))
				(self dispose:)
			)
			(if (== charCount maxLen)
				(curRoom notify: (not failed))
				(self dispose:)
			)
		)
	)
	
	(method (moveCursorPosn theCur &tmp theX theY)
		(self calcPosn: theCur)
		(= theX (- (+ x (* column offsetX)) (/ offsetX 2)))
		(= theY (- (+ y (* (+ row 1) offsetY)) cursorSpace))
		(switch (theCur message?)
			(V_LOOK
				(if (> row 0) (= theY (- theY offsetY)))
			)
			(V_DO
				(if (< row maxRow) (= theY (+ theY offsetY)))
			)
			(V_WALK
				(if (< column maxCol) (= theX (+ theX offsetX)))
			)
			(7
				(if (> column 1) (= theX (- theX offsetX)))
			)
		)
		(SetCursor theX theY)
	)
	
	(method (buttonSetup &tmp temp0 temp1 temp2 temp3)
		(if (OneOf value 26 27 29 30) (return 0))
		(= temp0 (+ (/ (- value 1) 16) 1))
		(= temp1 (mod (- value 1) 16))
		(= temp2 (+ x (* (- column 1) offsetX)))
		(= temp3 (+ y (* row offsetY)))
		(return (self drawButton: temp0 temp1 temp2 temp3))
	)
	
	(method (failCheck param1)
		(if (and param1 (!= param1 value)) (= failed 1))
	)
	
	(method (drawButton param1 param2 param3 param4 &tmp [temp0 20])
		(DrawCel view param1 param2 param3 param4 15)
		(return TRUE)
	)
)
