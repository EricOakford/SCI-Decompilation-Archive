;;; Sierra Script 1.0 - (do not remove this comment)
(script# CPATH)
(include game.sh)
(use Intrface)
(use Motion)


(class CPath of MoveTo
	(properties
		index -1
		dir 1
		endOfPath 0
	)
	
	(method (init actor toCall)
		(= client actor)
		(= caller (if (>= argc 2) toCall else 0))
		(= dir (if (>= dir 0) 1 else -1))
		(= x (client x?))
		(= y (client y?))
		(if (== endOfPath 0)
			(= endOfPath (self findEnd:))
		)
		(if (self atEnd:)
			(self moveDone:)
		else
			(self next:)
			(super init: client x y)
		)
	)
	
	(method (moveDone)
		(cond 
			((not (self atEnd:))
				(self next:)
				(super init: client x y)
			)
			(caller
				(super moveDone:)
			)
			(else
				(= index -1)
				(self init: client)
			)
		)
	)
	
	(method (at)
		(Printf CPATH 0 name)
		(return FALSE)
	)
	
	(method (next)
		(if (== dir 1)
			(= x (self at: (++ index)))
			(= y (self at: (++ index)))
		else
			(if (<= index 0) (= index (self findEnd:)))
			(= y (self at: (-- index)))
			(= x (self at: (-- index)))
		)
	)
	
	(method (atEnd)
		(return
			(if (== index endOfPath)
			else
				(== (+ index 1) endOfPath)
			)
		)
	)
	
	(method (findEnd &tmp i)
		(= i 0)
		(while (!= (self at: i) PATHEND)
			(++ i)
		)
		(return i)
	)
)
