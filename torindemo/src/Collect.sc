;;; Sierra Script 1.0 - (do not remove this comment)
(script# COLLECT)
(include game.sh)
(use System)


(class Collection kindof Object
	;;; The Collection class provides the ability to manipulate
	;;; collections of objects.  Objects which belong to a Collection
	;;; are said to be elements or members of it.  The Collection class
	;;; has no particular order defined for its elements, so it should
	;;; not be used for situations in which the objects should be
	;;; ordered -- use a List instead.
	
	(properties
		name 		"Collect"
		elements	0			;pointer to a Kernal List (KList) containing elements
		size 		0		 	;the number of elements in the list
		nextNode	0			;next node for use in eachElementDo, etc.
	)
;;;	(methods
;;;		doit
;;;		add					;add elements
;;;		delete				;delete elements
;;;		eachElementDo		;invoke a method in each element
;;;		firstTrue			;return first element satisfying a condition
;;;		allTrue				;return TRUE if all elements satisfy a condition
;;;		contains				;return TRUE if an object is in the Collection
;;;		isEmpty				;return TRUE if the list is empty (size == 0)
;;;		first					;return first KNode of the Collection
;;;		next					;return next KNode of the Collection
;;;		release				;delete elements from list to deallocate all nodes
;;;		isDuplicate			;return object ID if already in Collection, else FALSE
;;;		value					;return the value of a node
;;;	)

	(method (new)
		(return
			((super new:)
				elements: (KList LNew),
				yourself:
			)
		)
	)

	(method (doit)
		(self eachElementDo: #doit: &rest)
	)

	(method (add item &tmp obj n node)
		;If the Collection does not have a KList, create one for it.
		(if (not elements)
			(= elements (KList LNew))
		)
		
		;Loop over the arguments, adding each to the Collection.
		(for	((= n 0))
				(< n argc)
				((++ n))
			(if (not (self isDuplicate: [item n]))
				(KList LAddToEnd elements (KList LNewNode [item n]) [item n])
				(++ size)
			)
		)
		(return self)
	)

	(method (delete item &tmp n)
		;Loop over arguments, deleting each from the Collection.
		(for	((= n 0))
				(< n argc)
				((++ n))
			; If we're going to delete our nextNode, make sure we get
			;	another nextNode
			(if (and nextNode
						(== [item n] (KList LNodeValue nextNode))
				)
				(= nextNode (KList LNextNode (KList LFindKey elements [item n])))
			)
			(if (KList LDeleteKey elements [item n])
				(-- size)
			)
		)
		(return self)
	)

	(method (dispose)
		(if elements
			(self eachElementDo: #dispose:)
			(KList LDispose elements)
		)
		(= elements NULL)
		(= size 0)
		(super dispose:)
	)

	(method (first)
		(return (KList LFirstNode elements))
	)

	(method (next node)
		(return (KList LNextNode node))
	)

	(method (isEmpty)
		(return (or (== elements NULL) (KList LEmpty elements)))
	)

	(method (contains anObject)
		(return (KList LFindKey elements anObject))
	)

	(method (eachElementDo action &tmp node obj)
		(KList LEachElementDo elements action &rest)
;		(for	((= node (KList LFirstNode elements)))
;				node
;				((= node nextNode))
;			(= nextNode (KList LNextNode node))
;			(= obj (KList LNodeValue node))
;			(obj action &rest)
;		)
	)

	(method (firstTrue action &tmp node obj)
		(KList LFirstTrue elements action &rest)
;		(for	((= node (KList LFirstNode elements)))
;				node
;				((= node nextNode))
;			(= nextNode (KList LNextNode node))
;			(= obj (KList LNodeValue node))
;			(if (obj action &rest)
;				(return obj)
;			)
;		)
;		(return NULL)
	)

	(method (allTrue action &tmp node obj)
		(KList LAllTrue elements action &rest)
;		(for	((= node (KList LFirstNode elements)))	;(for	((= node (self first:)))
;				node
;				((= node nextNode))
;			(= nextNode (KList LNextNode node))			;(= nextNode (self next:node))
;			(= obj (KList LNodeValue node))
;			(if (not (obj action &rest))
;				(return FALSE)
;			)
;		)
;		(return TRUE)
	)

	(method (release &tmp node)
		;
		; Delete all elements from collection to deallocate list nodes

		(for	((= node (KList LFirstNode elements)))
				node
				((= node nextNode))
			(= nextNode (KList LNextNode node))
			(self delete: (KList LNodeValue node))
		)
	)

	(method (isDuplicate obj)
		;
		; Return FALSE (i.e. obj not already in list), since for class Collection
		;	and List we don't care about duplicates.  For class Set, this method
		;	will be over-ridden to determine if obj is already in the Set.

		(return FALSE)
	)

	(method (value n)
		(return (KList LNodeValue n) )
	)
)