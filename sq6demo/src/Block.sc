;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	BLOCK.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Unknown
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	A Block is a rectangle meant to exclude Actors.  'theActor' is
;;;;	required to stay out of a 'theBlock' by:
;;;;
;;;;		(theActor observeBlocks: theBlock ...)
;;;;
;;;;	Similarly, the ignoreBlocks method of Actor will allow him to be
;;;;	inside the block once more.
;;;;
;;;;	A Cage is a block meant to contain an Actor.
;;;;
;;;;	Classes:
;;;;		Block
;;;;		Cage


(script# BLOCK)
(include game.sh)
(use System)


(class Block kindof Object
	(properties
		name		"Blk"
		left		0
		top		0				;coordinates of block edges
		right		0
		bottom	0
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
	(method (doit theObj)
		(return
			(and
				(>= (theObj brLeft?) left)
				(>= (theObj brTop?) top)
				(<= (theObj brRight?) right)
				(<= (theObj brBottom?) bottom)
			)
		)
	)
)
