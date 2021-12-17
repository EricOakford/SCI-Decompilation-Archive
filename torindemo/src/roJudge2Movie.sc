;;; Sierra Script 1.0 - (do not remove this comment)
(script# 55000)
(include sci.sh)
(use Main)
(use TPRoom)

(public
	roJudge2Movie 0
)

(instance roJudge2Movie of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		((ScriptID 64017 0) set: 142)
		(curRoom newRoom: -15036)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
