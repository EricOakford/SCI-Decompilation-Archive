;;; Sierra Script 1.0 - (do not remove this comment)
;********************************************************************
;***
;***	MAP
;***	 Demo version by Robert W. Lindsley
;***
;********************************************************************

(script#	MAP)
(include game.sh)
(use Main)
(use InnRoom)
(use Print)
(use Motion)
(use Actor)
(use System)

(public
	map			0
)

(instance map of InnRoom
	(properties
		picture		pMAP
		nextRoom		BOOGERS
		prevRoom		TITLE
	)
	(method (init)
		(super init:&rest)
		(self setScript:	sMap)
	)
)

(instance sMap of Script
	(method (changeState ns)
		(switchto (= state ns)
			(
				(casinoSign init:)
				(lights1	init:)
				(lights2	init:)
				(lights3	init:)
				(water init:)
				(windmill init:)
				(smoke init:)
				(plane init:)
				(postOfficeFlag init:)
				(= cycles 2)
			)
			(
				(= seconds 3)
			)
			(
				(Print
					width:		290,
					posn:			-1 180,
					addText:
						{Welcome to ImagiNation, the premier, on-line
						interactive entertainment network.
						ImagiNation is the place to escape
						for fun, fantasy, and friendship.},
					modeless:	TRUE,
					init:
				)
				(= seconds 10)
			)
			(
				(if modelessDialog (modelessDialog dispose:)) ;Cls
				(Print
					width:		290,
					posn:			-1 180,
					addText:		{The demonstration you are about to see will 
									show you why you should join INN today!  Just
									think, your hard-working PC that runs word
									processors and spreadsheets can also open up
									a whole new world of excitement.  The world
									of ImagiNation!},
					modeless:	TRUE,
					init:
				)
				(= seconds 15)
			)
			(
				(if modelessDialog (modelessDialog dispose:)) ;Cls
				(curRoom newRoom:		(curRoom nextRoom?))
			)
		)
	)
)

(instance water of Prop
	(properties
		view vMAP
		loop lWater
		x 135
		y 104
	)
	(method (init)
		(super init:)
		(self cycleSpeed: (Random 10 12), setCycle: Forward)
	)
)
(instance casinoSign of Prop
	(properties
		view vMAP
		loop lCasinoSign
		x 128
		y 10
	)
	(method (init)
		(super init:)
		(self cycleSpeed: (Random 12 15), setCycle: Forward)
	)
)
(instance smoke of Prop
	(properties
		view vMAP
		loop lSmoke
		x 236
		y 12
	)
	(method (init)
		(super init:)
		(self cycleSpeed: (Random 13 15), setCycle: Forward)
	)
)
(instance windmill of Prop
	(properties
		view vMAP
		loop lWindmill
		x 291
		y 70
	)
	(method (init)
		(super init:)
		(self cycleSpeed: (Random 10 12), setCycle: Forward)
	)
)
(instance lights1 of Prop
	(properties
		view vMAP
		loop lLights1
		x 85
		y 7
	)
	(method (init)
		(super init:)
		(self cycleSpeed: (Random 10 12), setCycle: Forward)
	)
)
(instance lights2 of Prop
	(properties
		view vMAP
		loop lLights2
		x 50
		y 31
	)
	(method (init)
		(super init:)
		(self cycleSpeed: (Random 10 12), setCycle: Forward)
	)
)
(instance lights3 of Prop
	(properties
		view vMAP
		loop lLights3
		x 102
		y 12
	)
	(method (init)
		(super init:)
		(self cycleSpeed: (Random 10 12), setCycle: Forward)
	)
)
(instance plane of Prop
	(properties
		view vMAP
		loop lPlane
		x 177
		y 46
	)
)
(instance postOfficeFlag of Prop
	(properties
		view vMAP
		loop lPostOfficeFlag
		x 79
		y 69
	)
	(method (init)
		(super init:)
		(self cycleSpeed: (Random 14 16), setCycle: Forward)
	)
)
