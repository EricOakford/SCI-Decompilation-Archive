;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64885)
(include sci.sh)
(use Main)
(use System)


(class delayDisposeScript of Obj
	(properties
		scratch 0
		nMaxMessages 0
	)
	
	(method (init theNMaxMessages)
		(super init:)
		(= nMaxMessages theNMaxMessages)
		(theDoits add: self)
	)
	
	(method (doit)
		(DisposeScript nMaxMessages)
		(theDoits delete: self)
		(self dispose:)
	)
)
