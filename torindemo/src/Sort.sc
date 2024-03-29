;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	SORT.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1989
;;;;
;;;;	Author: 	Pablo Ghenis
;;;;	Updated:
;;;;
;;;;	A Transfer sort.
;;;;
;;;;	Procedures:
;;;;		Sort


(script# SORT)
(include game.sh)
(use List)
(use System)

;;;(procedure
;;;	Sort
;;;)

(public
	Sort	0
)

(procedure (Sort theList theLTCode &tmp temp node minValue tempList)

	;;Transfer sort (yeech!), isnt really bad since our lists are 
	;;only about 10 elements long. Conserving heap memory is a higher
	;;priority nowadays...
	;;
	;;	--Pablo Ghenis 6/9/89

	(= tempList (List new:))

	(while	(= node (KList LFirstNode (theList elements?)))
		
		(for
			((= minValue (KList LNodeValue node)))
			node
			((= node (KList LNextNode node)))
			
			(if (theLTCode doit: (= temp (KList LNodeValue node)) minValue &rest)
				(= minValue temp)
			)
			
		)
	
		;transfer by appending to destination, deleting from source
		
		(transferElement doit: minValue theList tempList)
		
	);while
	
	(tempList
		eachElementDo: #perform transferElement tempList theList,
		dispose:
	)
	
);Sort


(instance transferElement of Code

	(properties name "TE")
	
	(method (doit e sourceList destinationList)
		(destinationList	addToEnd: e)
		(sourceList			delete: e)
	)
)