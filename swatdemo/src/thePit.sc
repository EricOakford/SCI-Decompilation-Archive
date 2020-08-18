;;; Sierra Script 1.0 - (do not remove this comment)
(script# 16)
(include game.sh)
(use Main)
(use PQRoom)
(use Motion)
(use Actor)
(use System)

(public
	thePit 0
)

(instance thePit of PQRoom
	(properties
		picture 16
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: demoScript)
	)
)

(instance demoScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(theInterface newInvLevel: -1 1)
				(swatPup init: setCycle: EndLoop self)
			)
			(2
				(curRoom newRoom: 15)
			)
		)
	)
)

(instance swatPup of Prop
	(properties
		x 205
		y 78
		view 160
	)
)
