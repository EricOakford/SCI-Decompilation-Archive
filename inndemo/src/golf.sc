;;; Sierra Script 1.0 - (do not remove this comment)
;*************************************************************
;***
;***
;***	GOLF 
;***	  Demo version by Robert W. Lindsley
;***
;***
;*************************************************************

(script#	GOLF)
(include game.sh)
(use Main)
(use InnRoom)
(use Print)
(use Motion)
(use Actor)
(use System)

(public
	golf			0
)

(instance golf of InnRoom
	(properties
		picture			pGOLF
		style				FADEOUT
		nextRoom			BRIDGE
		prevRoom			BJACK
	)
	(method (init)
		(char init:)
		(nextGuy init:)
		(person init:)
		(flag init:, setCycle:	Forward)
		(golfCursor init:, setPri:	15)
		(aim init:)
		(feet init:)
		(golfball init:)
		(super init:	&rest)
		(self setScript:	sGolf)
	)
)

(instance sGolf of Script
	(method (changeState ns)
		(switchto (= state ns)
			(
				(= seconds 3)
			)
			(
				(Print
					posn:			90 180,
					addText:		{Play a fun game of golf with your old 
									friend from school, or a new on-line
									friend.},
					modeless:	TRUE,
					init:
				)
				(= seconds 6)
			)
			(
				(if modelessDialog (modelessDialog dispose:)) ;Cls
				(golfCursor setCycle:	EndLoop self)
			)
			(
				(= cycles 10)
			)
			(
				(char setCycle:	EndLoop self)
			)
			(
				(aim cycleSpeed:	6, setCycle:	EndLoop self)
			)
			(
				(aim setLoop:	5, setCycle:	EndLoop self)
			)
			(
				(aim setLoop:	5, setCycle:	BegLoop self)
			)
			(
				(aim setLoop:	5, setCycle:	EndLoop self)
			)
			(
				(aim setLoop:	5, setCycle:	BegLoop self)
			)
			(
				(aim setLoop:	5, setCycle:	EndLoop self)
			)
			(
				(person setCycle:	EndLoop self)
			)
			(
				(theMusic number:	3004, play:)
				(person setLoop:	1, setCycle:	CycleTo 2 1 self)
			)
			(
				(soundFx number:	3005, play:)
				(person setCycle:	EndLoop self)
				(golfball setCycle:	EndLoop self)
			)
			(
			)
			(
				(person setLoop:	2, setCycle:	EndLoop self)
			)
			(
				(= seconds 2)
			)
			(
				(chat init:, setPri:	15)
				(= seconds 5)
			)
			(
				(chat dispose:)
				(= seconds 2)
			)
			(
				(if modelessDialog (modelessDialog dispose:)) ;Cls
				(curRoom newRoom:	(curRoom nextRoom?))
			)
		)
	)
)

(instance char of Actor
	(properties
		view			140
		loop			1
		cel			0
		x				9
		y				121
		priority		16
		signal		(| fixPriOn ignrAct)
	)
)

(instance nextGuy of Actor
	(properties
		view			140
		loop			0
		cel			0
		x				9
		y				121
		priority		9
		signal		(| fixPriOn ignrAct)
	)
)

(instance person of Actor
	(properties
		view			141
		loop			0
		cel			0
		x				127
		y				167
		cycleSpeed	2
	)
)

(instance flag of Actor
	(properties
		view			142
		loop			0
		cel			2
		x				282
		y				36
	)
)

(instance golfCursor of Actor
	(properties
		view			140
		loop			3
		cel			0
		x				181
		y				44
	)
)

(instance aim of Actor
	(properties
		view			140
		loop			4
		cel			14
		x				9
		y				121
		priority		15
		signal		fixPriOn
	)
)

(instance feet of Actor
	(properties
		view			140
		loop			2
		cel			0
		x				5
		y				172
		priority		1
		signal		fixPriOn
	)
)

(instance golfball of Actor
	(properties
		view			142
		loop			2
		x				164
		y				76
	)
)

(instance chat of Actor
	(properties
		view			143
		loop			0
		x				75
		y				163
	)
)
