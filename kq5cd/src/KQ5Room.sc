;;; Sierra Script 1.0 - (do not remove this comment)
(script# 888)
(include game.sh)
(use Main)
(use Game)
(use User)


(class KQ5Room of Room
	
	(method (init)
		(super init: &rest)
		(if (and (User canControl:) (User canInput:))
			(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
		)
	)
	
	(method (newRoom)
		(theGame setCursor: waitCursor TRUE)
		(super newRoom: &rest)
	)
)
