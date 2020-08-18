;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	COUNT.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Unknown
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	This procedure counts how many members of a list return TRUE from
;;;;	performing a piece of code.
;;;;
;;;;	Classes:
;;;;
;;;;  Procedures:
;;;;		Count


(script# COUNT)
(include game.sh)

(public
	Count	0
)

(procedure (Count theList theCode &tmp theCount theNode)
	(for
		(	(= theNode (List LFirstNode (theList elements?)))
			(= theCount 0)
		)
		theNode
		(	(= theNode (List LNextNode theNode))
		)
		
		(if (theCode doit: (List LNodeValue theNode) &rest)
			(++ theCount)
		)
	)
	(return theCount)
)
