;;; Sierra Script 1.0 - (do not remove this comment)
;*************************************************************
;***
;*** STRATEGO
;***	 Demo version by Robert W. Lindsley
;***
;*************************************************************

(script#	STRATEGO)
(include game.sh)
(use Main)
(use InnRoom)
(use Print)
(use Motion)
(use Actor)
(use System)

(public
	stratego		0
)

(instance stratego of InnRoom
	(properties
		picture			pSTRATEGO
		nextRoom			WAITROOM
		prevRoom			BRIDGE
	)
	(method (init)
		(piece1 init:)
		(piece2 init:)
		(super init:	&rest)
		(self setScript:	sStratego)
	)
)

(instance sStratego of Script
	(method (changeState ns)
		(switchto (= state ns)
			(
				(= seconds 2)
			)
			(
				(Print
					width:		290,
					posn:			-1 180,
					addText:		{Or relive your past by playing some of your
									favorite childhood games.},
					modeless:	TRUE,
					init:
				)
				(= seconds 6)
			)
			(
				(piece1 
					moveSpeed:	3,
					setMotion:	MoveTo 200 55 self
				)
			)
			(
				(piece2 cel:	2, posn:	209 52)
				(= cycles 40)
			)
			(
				(soundFx number:	2261, play:)
				(piece1 setLoop:	1, setCycle:	EndLoop self)
			)
			(
				(piece1 setLoop:	2, setCycle:	EndLoop self)
			)
			(
				(piece1 dispose:)
				(= seconds 2)
			)
			(
				(if modelessDialog (modelessDialog dispose:)) ;Cls
				(= seconds 2)
			)
			(
				(curRoom newRoom:	(curRoom nextRoom?))
			)
		)
	)
)

(instance piece1 of Actor
	(properties
		view			220
		loop			0
		x				204
		y				115
	)
)

(instance piece2 of Actor
	(properties
		view			220
		loop			0
		cel			1
		x				200
		y				53
	)
)
