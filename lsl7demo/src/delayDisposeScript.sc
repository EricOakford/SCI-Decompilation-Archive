;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64885)
(include sci.sh)
(use Main)
(use System)


(class delayDisposeScript of Obj
	(properties
		scratch 0
		scriptId 0
	)
	
	(method (init theScriptId)
		(super init:)
		(= scriptId theScriptId)
		(theDoits add: self)
	)
	
	(method (doit)
		(DisposeScript scriptId)
		(theDoits delete: self)
		(self dispose:)
	)
)
