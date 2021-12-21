;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64898)
(include sci.sh)
(use Array)


(class PArray of IDArray
	(properties
		scratch 0
		data 0
		type $0001
	)
	
	(method (add param1 &tmp temp0)
		(= temp0 0)
		(while (< temp0 argc)
			(self at: [param1 temp0] [param1 (+ temp0 1)])
			(= temp0 (+ temp0 2))
		)
	)
)
