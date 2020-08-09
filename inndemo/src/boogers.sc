;;; Sierra Script 1.0 - (do not remove this comment)
;*************************************************************
;***
;***
;***	BOOGERS 
;***	  Demo version by Robert W. Lindsley
;***
;***
;*************************************************************

(script#	BOOGERS)
(include game.sh)
(use Main)
(use InnRoom)
(use Print)
(use Motion)
(use Actor)
(use System)

(public
	boogers			0
)

(instance boogers of InnRoom
	(properties
		picture			pBOOGERS
		style				FADEOUT
		nextRoom			RED_BARON
		prevRoom			MAP
	)
	(method (init)
		(booger init:)
		(super init:	&rest)
		(self setScript:	sBoogers)
	)
)

(instance sBoogers of Script
	(method (changeState ns)
		(switchto (= state ns)
			(
				(globalSound setVol: 100)
				(= cycles 15)
			)
			(
				(= seconds 3)
			)
			(
				(Print
					width:		290,
					posn:			-1 180,
					addText:		{You'll also get to play all kinds of games.
									And while you're playing, you'll 
									make new friends using the on-line live chat 
									feature available in most games, as well as in
									Waiting Rooms, Conference Rooms, and Chat
									Rooms.},
					modeless:	TRUE,
					init:
				)
				(= seconds 5)
			)
			(
				(soundFx number:	790, play:)
				(booger setCycle:	EndLoop self)
			)
			(
				(= seconds 4)
			)
			(
				(win init:)
				(= seconds 5)
			)
			(
				(win dispose:)
				(= seconds 2)
			)
			(
				(if modelessDialog (modelessDialog dispose:)) ;Cls
				(curRoom newRoom:	(curRoom nextRoom?))
			)
		)
	)
)

(instance booger of Actor
	(properties
		view			120
		loop			0
		x				141
		y				101
		cycleSpeed	6
	)
)

(instance win of Actor
	(properties
		view			121
		x				15
		y				5
	)
)
