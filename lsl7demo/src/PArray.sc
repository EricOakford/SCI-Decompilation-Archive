;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64898)
(include game.sh)
(use Array)


(class PArray of IDArray
	(method (add obj &tmp i)
		(for ((= i 0)) (< i argc) ((+= i 2))
			(self at: [obj i] [obj (+ i 1)])
		)
	)
)
