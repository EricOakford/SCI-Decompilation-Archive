;;; Sierra Script 1.0 - (do not remove this comment)
(script# WHERETO) ;29
(include game.sh) (include "10.shm")
(use Main)
(use FPRoom)
(use Print)

(public
	whereTo 0
)

(instance whereTo of FPRoom
	(properties
		picture 780
	)
	
	(method (init &tmp [str 10] nextRoom temp11)
		(super init:)
		(= normalCursor ((theIconBar curIcon?) cursor?))
		(theGame setCursor: ARROW_CURSOR TRUE 201 126)
		(= str 0)
		(= nextRoom 0)
		(= nextRoom
			(Print
				addText: N_WHERETO NULL C_WHERETO 1 0 0 DEBUG
				addEdit: @str 5 115 0
				init:
			)
		)
		(= nextRoom 100)
		(if str
			(= nextRoom (ReadNumber @str))
		)
		(theGame setCursor: normalCursor)
		(self newRoom: nextRoom)
	)
)
