;;; Sierra Script 1.0 - (do not remove this comment)
(script# 49)
(include sci.sh)
(use Main)
(use Grooper)
(use System)

(public
	doorMat 0
)

(class doorMat of Obj
	(properties
		scratch 0
		thePolyList 0
		loopAllow1 0
		loopAllow2 0
		loopAllow3 0
		theScript 0
	)
	
	(method (init theThePolyList theLoopAllow1 theLoopAllow2 theLoopAllow3 theTheScript)
		(super init: &rest)
		(= thePolyList theThePolyList)
		(= loopAllow1 theLoopAllow1)
		(= loopAllow2 theLoopAllow2)
		(= loopAllow3 theLoopAllow3)
		(= theScript theTheScript)
		(theDoits add: self)
	)
	
	(method (doit &tmp egoLooper)
		(if
			(and
				(thePolyList onMe: (ego x?) (ego y?))
				(not (curRoom script?))
				(OneOf (ego loop?) loopAllow1 loopAllow2 loopAllow3)
				(= egoLooper (ego looper?))
				(not (egoLooper isKindOf: Grycler))
			)
			(curRoom setScript: theScript)
		)
	)
	
	(method (dispose)
		(thePolyList dispose:)
		(theDoits delete: self)
		(super dispose: &rest)
	)
)
