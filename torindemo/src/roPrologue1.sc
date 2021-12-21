;;; Sierra Script 1.0 - (do not remove this comment)
(script# 8000)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)

(public
	roPrologue1 0
)

(instance soPlayPrologue of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(PlayVMD 0 LOOKUP_ERROR)
				(PlayVMD 1 4 4 5)
				(PlayVMD 14 7)
				(PlayVMD 6)
				(= cycles 1)
			)
			(1 (curRoom newRoom: 9000))
		)
	)
)

(instance roPrologue1 of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: 0)
		(= global202 0)
		(curRoom setScript: LOOKUP_ERROR)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
