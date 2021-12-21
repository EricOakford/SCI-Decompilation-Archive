;;; Sierra Script 1.0 - (do not remove this comment)
(script# 61000)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use n64896)

(public
	roSierraLogo 0
)

(instance soSierraLogo of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 120))
			(1 (proc64896_1 1 10 self))
			(2 (curRoom newRoom: -4436))
		)
	)
)

(instance roSierraLogo of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		(= plane (ScriptID 0 2))
		(curRoom setScript: LOOKUP_ERROR)
	)
)
