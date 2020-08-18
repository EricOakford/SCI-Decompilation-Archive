;;; Sierra Script 1.0 - (do not remove this comment)
(script# 18)
(include game.sh)
(use Main)
(use PQRoom)
(use System)

(public
	swatTitle 0
)

(instance swatTitle of PQRoom
	(properties
		picture 18
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
				(if (theMusic handle?)
					(-- state)
				)
				(= cycles 1)
			)
			(2
				(curRoom newRoom: 8)
			)
		)
	)
)
