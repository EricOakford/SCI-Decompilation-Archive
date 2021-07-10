;;; Sierra Script 1.0 - (do not remove this comment)
(script# 817)
(include game.sh)
(use Eval)
(use System)


(class TimedControler of Code
	(properties
		theObj 0
		selector 0
		cycles 10
		cyclCnt 0
		incPerTime 0
	)
	
	(method (init theTheObj theSelector)
		(= theObj theTheObj)
		(= selector theSelector)
	)
	
	(method (doit &tmp temp0)
		(if (>= (++ cyclCnt) cycles)
			(= cyclCnt 0)
			(self drive:)
		)
	)
	
	(method (drive)
		(Eval theObj selector
			(+ (Eval theObj selector) incPerTime)
		)
	)
)
