;;; Sierra Script 1.0 - (do not remove this comment)
(script# ISLE)
(include game.sh)
(use Main)
(use Game)
(use System)

(public
	islePic 0
)

(instance islePic of Room
	(properties
		picture 1
		style IRISIN
	)
	
	(method (init)
		(super init:)
		(globalSound number: 5 loop: -1 play:)
		(self setScript: prettyAsAPicture)
	)
)

(instance prettyAsAPicture of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 5)
			)
			(1
				(curRoom newRoom: 2)
			)
		)
	)
)
