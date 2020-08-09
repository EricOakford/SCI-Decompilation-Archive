;;; Sierra Script 1.0 - (do not remove this comment)
;*************************************************************
;***
;*** BRIDGE
;***	 Demo version by Robert W. Lindsley
;***
;*************************************************************

(script#	BRIDGE)
(include game.sh)
(use Main)
(use InnRoom)
(use Print)
(use Motion)
(use Actor)
(use System)

(public
	bridge		0
)

(instance bridge of InnRoom
	(properties
		picture			pBRIDGE
		nextRoom			STRATEGO
		prevRoom			GOLF
	)
	(method (init)
		(otherCards init:)
		(myCard init:)
		(super init:	&rest)
		(self setScript:	sBridge)
	)
)

(instance sBridge of Script
	(method (changeState ns)
		(switchto (= state ns)
			(
				(= seconds 2)
			)
			(
				(Print
					width:		290,
					posn:			-1 180,
					addText:		{Play tournament bridge with partners and
									opponents ranging from amateurs
									to the highest caliber players in the country.},
					modeless:	TRUE,
					init:
				)
				(= seconds 8)
			)
			(
				(if modelessDialog (modelessDialog dispose:)) ;Cls
				(soundFx number:	100, play:)
				(myCard 
					illegalBits:	0,
					ignoreActors:	TRUE,
					setMotion:	MoveTo 163 106 self
				)
			)
			(
				(= seconds 2)
			)
			(
				(myCard dispose:)
				(soundFx number:	501, play:)
				(otherCards 
					setLoop:	1, 
					cel:	0, 
					setCycle:	Forward,
					setMotion:	MoveTo 0 96 self
			 	)
			)
			(
				(otherCards dispose:)
				(= seconds 4)
			)
			(
				(if modelessDialog (modelessDialog dispose:)) ;Cls
				(curRoom newRoom:	(curRoom nextRoom?))
			)
		)
	)
)

(instance otherCards of Actor
	(properties
		view		200
		loop		2
		x			163
		y			106
		moveSpeed	1
	)
)

(instance myCard of Actor
	(properties
		view		200
		loop		0
		x			147
		y			166
		moveSpeed	1
	)
)
