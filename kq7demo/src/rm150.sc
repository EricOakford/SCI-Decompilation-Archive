;;; Sierra Script 1.0 - (do not remove this comment)
(script# 150)
(include game.sh)
(use Main)
(use KQRoom)
(use Motion)
(use Actor)
(use System)

(public
	rm150 0
)

(instance rm150 of KQRoom
	(properties
		picture 999
	)
	
	(method (init)
		(super init:)
		(valenice init:)
		(rosella init:)
		(self setScript: roomScript)
	)
)

(instance roomScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(valenice init:)
				(rosella init: setCycle: Forward)
				(= seconds 3)
			)
			(1
				(messager say: ALL ALL 2 0 self 100)
			)
			(2 (= seconds 2))
			(3 (curRoom newRoom: 1250))
		)
	)
)

(instance valenice of Actor
	(properties
		x 250
		y 80
		view 0
	)
)

(instance rosella of Actor
	(properties
		x 50
		y 80
		view 1
		loop 1
	)
)
