;;; Sierra Script 1.0 - (do not remove this comment)
;*********************************************************************
;***
;***	BJACK
;***	  Demo version by Robert W. Lindsley
;***
;***
;*********************************************************************

(script#	BJACK)
(include game.sh)
(use Main)
(use InnRoom)
(use Print)
(use Motion)
(use Actor)
(use System)

(public
	bJack			0
)

(instance bJack of InnRoom
	(properties
		picture		pBJACK
		nextRoom		GOLF
		prevRoom		CHESS
	)
	(method (init)
		(head init:)
 		(theScore init:)
		(myCard 
			init:, 
			ignoreHorizon:	TRUE,
			illegalBits:	0,
			ignoreActors:	TRUE
		)
		(super init:	&rest)
		(self setScript:	sBjack)
	)
)

(instance sBjack of Script
	(method (changeState ns)
		(switchto (= state ns)
			(
				(= seconds 3)
			)
			(
				(Print
					width:		290,
					posn:			-1 180,
					addText:		{Or, you can play blackjack with
									a table of people spread over multiple 
									states, and all you have to lose or gain
									are CasinoBucks.},
					modeless:	TRUE,
					init:
				)
				(= seconds 10)
			)
			(
				(if modelessDialog (modelessDialog dispose:)) ;Cls
				(hit ignoreActors:	TRUE, illegalBits:	0, init:)
				(= cycles 30)
			)
			(
				(hit dispose:)
				(= cycles 10)
			)
			(
				(myCard setMotion:	MoveTo 22 69 self)				
			)
			(
				(= cycles 30)
			)
			(
				(myCard cel:	1)
				(= cycles 20)
			)
			(
				(theScore cel:	3)
				(= cycles 30)
			)
			(
				(bustPlate init:)
				(= seconds 2)
			)					 
			(
				(curRoom newRoom:	(curRoom nextRoom?))
			)
		)
	)
)

(instance head of Actor
	(properties
		view			180
		x				9
		y				150
	)
)

(instance bustPlate of Prop
	(properties
		view			180
		cel			1
		x				9
		y				150
	)
)

(instance myCard of Actor
	(properties
		view		180
		loop		1
		cel		0
		x			156
		y			0
		moveSpeed	1
	)
)

(instance hit of Actor
	(properties
		view		180
		loop		2
		cel		1
		x			82
		y			29
	)
)

(instance theScore of Actor
	(properties
		view		180
		loop		1
		cel		2
		x			34
		y			49
	)
)
