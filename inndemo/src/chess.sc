;;; Sierra Script 1.0 - (do not remove this comment)
;************************************************************************
;***
;*** CHESS
;***	 Demo version by Robert W. Lindsley
;***
;************************************************************************

(script#	CHESS)
(include game.sh)
(use Main)
(use InnRoom)
(use Print)
(use Motion)
(use Actor)
(use System)

(public
	chess			0
)

(instance chess of InnRoom
	(properties
		picture		pCHESS
		nextRoom		BJACK
		prevRoom		TRIVIA
	)
	(method (init)
		(bishop init:)
		(theClock init:)
		(super init:	&rest)
		(self setScript:	sChess)
	)
)

(instance sChess of Script
	(method (changeState ns)
		(switchto (= state ns)
			(
				(= seconds 3)
			)
			(
				(Print
					width:		290,
					posn:			-1 180,
					addText:		{You can also play with people of different
									experience levels.  Imagine learning from
									real-live people, or maybe even teaching
									somebody less experienced than yourself.},
					modeless:	TRUE,
					init:
				)
				(= seconds 6)
			)
			(
				(soundFx number:	102, play:)
				(bishop 
					moveSpeed:	2,
					setMotion:	MoveTo 172 117 self
				)
			)
			(
				(= seconds 4)
			)
			(
				(if modelessDialog (modelessDialog dispose:)) ;Cls
				(curRoom newRoom:	(curRoom nextRoom?))
			)
		)
	)
)

(instance bishop of Actor
	(properties
		view		210
		x			72
		y			37
	)
)

(instance theClock of Actor
	(properties
		view		210
		loop		1
		x			300
		y			83
	)
	(method (init)
		(super init:	&rest)
		(self setPri:	15, setScript:	clockScript)
	)
)

(instance clockScript of Script
	(method (changeState ns)
		(switchto (= state ns)
			(
				(= seconds 1)
			)
			(
				(if (== (client cel?) (client lastCel:))
					(self dispose:)
				else
					(client cel:	(+ (client cel?) 1))
					(self init:)
				)
			)
		)
	)
)
