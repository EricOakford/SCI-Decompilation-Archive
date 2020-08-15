;;; Sierra Script 1.0 - (do not remove this comment)
(script# EGODEAD)
(include game.sh) (include "666.shm")
(use Main)
(use Print)
(use LoadMany)

(public
	EgoDead 0
)

(procedure (EgoDead deathMode retryState &tmp deathView temp1 temp2 oldCur [temp4 3])
	(switch deathMode
		(1
			(= deathView 921)
			(= temp2 4)
		)
		(2
			(= deathView 922)
			(= temp2 4)
		)
		(else 
			(Prints
				{You have used an illegal deathMode parameter.\nGo directly to JAIL, do not pass GO.}
			)
			(return)
		)
	)
	(= oldCur theCursor)
	(theGame setCursor: normalCursor TRUE 220 103)
	(repeat
		(switch
			(Print
				font: userFont
				width: 200
				addTitle: 2 NULL NULL deathMode EGODEAD
				addText: 3 NULL NULL deathMode 0 1 EGODEAD
				addButton: 2 1 NULL NULL 2 130 30 EGODEAD
				init:
			)
			(1
				(theGame restore:)
			)
			(2
				(theGame setCursor: oldCur)
				(if (and (> argc 1) retryState)
					(retryState cue:)
				)
				(break)				
				(LoadMany FALSE EGODEAD)
			)
		)
	)
)
