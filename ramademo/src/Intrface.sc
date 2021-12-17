;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	INTRFACE.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Bob Heitman
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	Classes and procedures that make up the user interface for SCI.
;;;;
;;;;	Procedures:
;;;;		StillDown
;;;;		GetNumber
;;;;		MousedOn


(script# INTRFACE)
(include game.sh)
(use String)
(use Print)
(use System)

;;;(procedure
;;;	GetNumber
;;;	StillDown
;;;	MousedOn
;;;)

(public
	StillDown	0
	GetNumber	1
	MousedOn		2
)


(procedure (StillDown &tmp event ret)
	;;; Return true if there is no mouse up in queue.

	(= event (Event new:))
	(= ret (!= (event type?) mouseUp))
	(event dispose:)
	(return ret)
)


(procedure (GetNumber string default &tmp theLine theVal)
	(= theLine (String newWith: 80 {}))
	(if (> argc 1)
		(theLine format: {%d} default)
	)
	(= theVal
		(if (GetInput theLine 5 string)
			(theLine asInteger:)
		else
			-1
		)
	)
	(theLine dispose:)
	(return theVal)
)

(procedure (MousedOn obj event)
	(return
		(and
			(< (obj nsLeft?) (event x?) (obj nsRight?))
			(< (obj nsTop?)  (event y?) (obj nsBottom?))
		)
	)
)
