;;; Sierra Script 1.0 - (do not remove this comment)
(script# 19)
(include sci.sh)
(use Main)
(use String)
(use Print)
(use System)

(public
	aboutCode 0
)

(local
	theIconBarGetCursor
)
(procedure (localproc_012c param1 param2 &tmp temp0 temp1 temp2)
	(if
	(= temp2 (Message msgSIZE 19 param1 0 0 param2))
		(= temp1 (Str new: temp2))
		(Message msgGET 19 param1 0 0 param2 (temp1 data?))
		(Print
			font: 0
			largeAlp: 0
			mode: 1
			addText: (temp1 data?)
			init:
		)
		(temp1 dispose:)
	)
)

(instance aboutCode of Code
	(properties)
	
	(method (doit &tmp temp0 [temp1 2])
		((= temp0 (= theIconBarGetCursor (theIconBar getCursor:)))
			view: 999
		)
		(theGame setCursor: temp0)
		(localproc_012c 2 1)
		(localproc_012c 2 2)
		(localproc_012c 2 3)
		(localproc_012c 2 4)
		(localproc_012c 2 5)
		(localproc_012c 2 6)
		(localproc_012c 2 7)
		(localproc_012c 2 8)
		(localproc_012c 2 9)
		(localproc_012c 2 10)
		(localproc_012c 2 11)
		(localproc_012c 2 12)
		(localproc_012c 2 13)
		(localproc_012c 2 14)
		(localproc_012c 1 1)
		(localproc_012c 1 2)
		(localproc_012c 1 3)
		(localproc_012c 1 4)
		(localproc_012c 1 5)
		(localproc_012c 1 6)
		(localproc_012c 1 7)
		(localproc_012c 1 8)
		(localproc_012c 1 9)
		(localproc_012c 1 10)
		(localproc_012c 1 11)
		(localproc_012c 3 1)
		(self dispose:)
	)
	
	(method (dispose)
		(theGame setCursor: theIconBarGetCursor)
		(DisposeScript 19)
	)
)
