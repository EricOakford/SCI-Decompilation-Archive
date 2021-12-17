;;; Sierra Script 1.0 - (do not remove this comment)
(script# SET)
(include game.sh)
(use List)


(class Set kindof List
	;;; A Set is a kind of List which does not contain duplicate
	;;; elements: adding an object to a Set which already contains the
	;;; object does not change the Set.  This eliminates the
	;;; possibility of a whole class of errors based on multiple
	;;; occurances of an object in a collection.  Most system
	;;; collections (the cast, etc.) are Sets.
	
	(method (isDuplicate obj)
		(return (self contains: obj))
	)
)