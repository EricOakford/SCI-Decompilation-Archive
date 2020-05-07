;;; Sierra Script 1.0 - (do not remove this comment)
(script# ENDICE)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	endIce 0
)

(instance endIce of Room
	(properties
		picture 100
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(water init: setCycle: Forward)
		(globalSound
			number:
			(switch numVoices
				(1 401)
				(3 201)
				(else  1)
			)
			play:
		)
		(self setScript: doItAgain)
	)
)

(instance water of Prop
	(properties
		y 122
		x 114
		view 500
	)
)

(instance doItAgain of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(Print 101 0 #at -1 130 #dispose)
				(= seconds 7)
			)
			(2
				(Print 101 1 #at -1 150 #dispose)
				(= seconds 5)
			)
			(3
				(Print 101 2 #at -1 170 #dispose)
				(= seconds 5)
			)
			(4
				(globalSound fade:)
				(theGame restart:)
			)
		)
	)
)
