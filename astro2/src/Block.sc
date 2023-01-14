;;; Sierra Script 1.0 - (do not remove this comment)
(script# BLOCK)
(include game.sh)
(use System)


(class Block kindof Object
;;; A Block is a rectangle meant to exclude Actors.  'theActor' is required
;;; to stay out of a 'theBlock' by
;;;	(theActor observeBlocks: theBlock  ...)
;;; Similarly,
;;;	(theActor ignoreBlocks: theBlock ...)
;;; will allow the Actor to be inside the block once more.
	
	(properties
		name "Blk"
		top 0				;coordinates of block edges
		left 0
		bottom 0
		right 0
	)
	
	
	(method (doit theObj)
		(return
			(or
				(<= (theObj brBottom?) top)
				(> (theObj brTop?) bottom)
				(< (theObj brRight?) left)
				(>= (theObj brLeft?) right)
			)
		)
	)
)




(class Cage kindof Block
;;; A Cage is a Block which requires that an Actor be inside the rectangle
;;; rather than outside.  It is set and cleared in the same way as Blocks,
;;; using observeBlocks: and ignoreBlocks:.
	
	(method (doit theObj)
		(return
			(and
				(>= (theObj brTop?) top)
				(>= (theObj brLeft?) left)
				(<= (theObj brBottom?) bottom)
				(<= (theObj brRight?) right)
			)
		)
	)
)
