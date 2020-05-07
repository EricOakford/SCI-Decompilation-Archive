;;; Sierra Script 1.0 - (do not remove this comment)
(script# NEW_WALK)
(include game.sh)
(use Motion)


(class NewWalk of Forward
	(properties
		xLast 0
		yLast 0
	)
	
	(method (doit &tmp theMover)
		(if
			(and
				(IsObject (= theMover (client mover?)))
				(or (!= (client x?) xLast) (!= (client y?) yLast))
			)
			(= xLast (client x?))
			(= yLast (client y?))
			(super doit:)
		)
	)
	
	(method (nextCel)
		(= cycleCnt (client cycleSpeed?))
		(super nextCel:)
	)
)
