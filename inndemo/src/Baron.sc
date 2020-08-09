;;; Sierra Script 1.0 - (do not remove this comment)
;*************************************************************
;***
;***
;***	RED_BARON 
;***	  Demo version by Robert W. Lindsley
;***
;***
;*************************************************************

(script#	RED_BARON)
(include game.sh)
(use Main)
(use InnRoom)
(use Print)
(use Motion)
(use Actor)
(use System)

(public
	redBaron			0
)

(local
	howManyShots = 0
)

(instance redBaron of InnRoom
	(properties
		picture			pRED_BARON
		style				FADEOUT
		nextRoom			TRIVIA
		prevRoom			BOOGERS
	)
	(method (init)
		(dial init:)
		(movingPeg init:, cycleSpeed:	20, setCycle:	Forward)
		(comp init:, cycleSpeed:	30, setCycle:	Forward)
		(super init:	&rest)
		(self setScript:	sRedBaron)
	)
)

(instance sRedBaron of Script
	(method (changeState ns)
		(switchto (= state ns)
			(
				(= seconds 3)
			)
			(
				(Print
					width:		290,
					posn:			-1 180,
					addText:		{Imagine sitting in your family
									room, engaging in an aerial dogfight or
									a chess game with someone 2,000 miles away.},
					modeless:	TRUE,
					init:
				)
				(= seconds 9)
			)
			(
				(if modelessDialog (modelessDialog dispose:)) ;Cls
				(gun1 init:, cel:	(gun1 lastCel:))
				(gun2 init:, cel:	(gun2 lastCel:))
				(dial cycleSpeed:	35, setCycle:	Forward)
				(theMusic number:	3001, play:)
				(plane2 init:, cycleSpeed:	7, setCycle: EndLoop self)
				(self setScript:	fireScript)
			)
			(
				(plane2 setLoop:	1, cel:	0, setCycle:	EndLoop self)
			)
			(
				(plane2 dispose:)
				(theMusic number:	3001, play:)
				(plane1 init:, cycleSpeed:	5, setCycle:	EndLoop self)
			)
			(
				(plane1 dispose:)
				(theMusic number:	3001, play:)
				(plane3 init:, cycleSpeed:	5, setCycle:	EndLoop self)
			)
			(
				(plane3 dispose:)
				(script dispose:)
				(gun1 dispose:)
				(gun2 dispose:)
				(= seconds 3)
			)
			(
				(= seconds 2)
			)
			(
				(curRoom newRoom:	(curRoom nextRoom?))
			)
		)
	)
)

(instance fireScript of Script
	(method (changeState ns)
		(switchto (= state ns)
			(
				(soundFx number:	3002, play:)
				(gun1 cel:	0, setCycle:	EndLoop self)
			)
			(
				(soundFx number:	3002, play:)
				(gun2 cel:	0, setCycle:	EndLoop self)
			)
			(
				(if (== (++ howManyShots) 8)
					(self dispose:)
				else
					(self init:)
				)
			)
		)
	)
)

(instance movingPeg of Actor
	(properties
		view			502
		loop			0
		cel			1
		x				159
		y				189
		cycleSpeed	6
	)
)

(instance comp of Actor
	(properties
		view			502
		loop			1
		cel			3
		x				262
		y				184
		cycleSpeed	7
	)
)

(instance dial of Actor
	(properties
		view			502
		loop			2
		cel			0
		x				161
		y				146
		cycleSpeed	8
	)
)

(instance gun1 of Actor
	(properties
		view			503
		loop			0
		x				98
		y				100
	)
)

(instance gun2 of Actor
	(properties
		view			503
		loop			1
		x				193
		y				101
	)
)

(instance plane1 of Actor
	(properties
		view			501
		loop			2
		x				216
		y				88
	)
)

(instance plane2 of Actor
	(properties
		view			501
		loop			0
		x				141
		y				38
	)
)

(instance plane3 of Actor
	(properties
		view			501
		loop			3
		x				49
		y				89
	)
)
