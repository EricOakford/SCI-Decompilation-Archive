;;; Sierra Script 1.0 - (do not remove this comment)
(script# 13)
(include game.sh)
(use Main)
(use Game)
(use System)

(public
	rm13 0
)

(instance rm13 of Room
	(properties
		picture 1500
		style FADEOUT
	)
	
	(method (init)
		(super init:)
		((ScriptID 0 4) number: 110 play:)
		(theGame setCursor: INVIS_CURSOR TRUE)
		(curRoom setScript: waitAWhile)
	)
)

(instance waitAWhile of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks 250)
			)
			(1
				(curRoom newRoom: 14)
			)
		)
	)
)
