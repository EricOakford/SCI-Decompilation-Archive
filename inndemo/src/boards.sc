;;; Sierra Script 1.0 - (do not remove this comment)
;*************************************************************
;***
;*** BULLETIN BOARD
;***	 Demo version by Robert W. Lindsley
;***
;*************************************************************

(script#	BOARDS)
(include game.sh)
(use Main)
(use InnRoom)
(use Print)
(use System)

(public
	boards		0
)

(instance boards of InnRoom
	(properties
		picture			pBOARDS
		nextRoom			TITLE2
		prevRoom			WAITROOM
	)
	(method (init)
		(super init:	&rest)
		(self setScript:	sBoards)
	)
)

(instance sBoards of Script
	(method (changeState ns)
		(switchto (= state ns)
			(
				(= seconds 2)
			)
			(
				(Print
					width:		290,
					posn:			-1 180,
					addText:		{Or find out what hundreds of your INN
									neighbors thought of last night's TV lineup.},
					modeless:	TRUE,
					init:
				)
				(= seconds 7)
			)
			(
				(if modelessDialog (modelessDialog dispose:)) ;Cls
				(curRoom newRoom:	(curRoom nextRoom?))
			)
		)
	)
)
