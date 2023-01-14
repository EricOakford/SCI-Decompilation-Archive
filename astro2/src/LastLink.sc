;;; Sierra Script 1.0 - (do not remove this comment)
(script# 957)
(include sci.sh)

(public
	LastLink 0
)

(procedure (LastLink param1 param2 &tmp temp0)
	(return
		(if
			(and
				(param2 respondsTo: param1)
				(= temp0 (param2 param1?))
			)
			(LastLink param1 temp0)
		else
			param2
		)
	)
)
