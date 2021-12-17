;;; Sierra Script 1.0 - (do not remove this comment)
(script# LIST)
(include game.sh)
(use Collect)


(class List kindof Collection
	;;; A List is a Collection which has a specified order to its
	;;; elements.  You will probably use the Set class (below) more
	;;; than List, as Set automatically weeds out duplicates.
	
;;;	(methods
;;;		at					;return the object at a given position in the List
;;;		last				;return the last KNode of the List
;;;		prev				;return previous KNode
;;;		addToFront		;add object to front of List
;;;		addToEnd			;add object to end of List
;;;		addAfter			;add object after an element of List
;;;		indexOf			;return index of an object in a List
;;;		sort				;sort list by property or id
;;;	)

	(method (at n &tmp node)
		(return (KList LAt elements n))
;		(for	((= node (KList LFirstNode elements)))		;(for	((= node (self first:)))
;				(and n node)
;				((-- n) (= node (KList LNextNode node)))	;((-- n) (= node (self next: node)))
;		)
;		(return
;			(if node
;				(KList LNodeValue node)
;			else
;				0
;			)
;		)
	)

	(method (last)
		(return (KList LLastNode elements))
	)

	(method (prev node)
		(return (KList LPrevNode node))
	)
	
	(method (addToFront obj &tmp n)
		(if (not elements)
			(= elements (KList LNew))
		)
		
		(for	((= n (- argc 1)))
				(<= 0 n)
				((-- n))
			(if (not (self isDuplicate: [obj n]))
				(KList LAddToFront elements (KList LNewNode [obj n]) [obj n])
				(++ size)
			)
		)
		(return self)
	)

	(method (addToEnd obj &tmp n)
		(if (not elements)
			(= elements (KList LNew))
		)
		
		(for	((= n 0))
				(< n argc)
				((++ n))
			(if (not (self isDuplicate: [obj n]))
				(KList LAddToEnd elements (KList LNewNode [obj n]) [obj n])
				(++ size)
			)
		)
		(return self)
	)

	(method (addAfter el obj &tmp n num insertNode)
		(if (= insertNode (KList LFindKey elements el))
			(-- argc)
			(for	((= n 0))
					(< n argc)
					((++ n))
				(if (not (self isDuplicate: [obj n]))
					(= insertNode
						(KList LAddAfter elements insertNode (KList LNewNode [obj n]) [obj n])
					)
					(++ size)
				)
			)
		)
		(return self)
	)

	(method (indexOf obj &tmp n node)
		(return (KList LIndexOf elements obj))
;		(for	((= n 0) (= node (KList LFirstNode elements)))
;				node
;				((++ n) (= node (KList LNextNode node)))
;			(if (== obj (KList LNodeValue node))
;				(return n)
;			)
;		)
;		(return -1)
	)

	(method (sort theProp doDescending &tmp v des)
		(= v (if argc theProp else -1))
		(= des (and (> argc 1) doDescending))
		(KList LSort elements v des)
	)
)