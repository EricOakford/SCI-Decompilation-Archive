;;; Sierra Script 1.0 - (do not remove this comment)
(script# 826)
(include game.sh)
(use Main)
(use Submarine_806)
(use Eval)
(use System)


(class Monitor of Code
	(properties
		active 0
		theSelector 0
		value 0
		cycles 0
		variance 0
		count 0
	)
	
	(method (init theTheSelector theValue theVariance)
		(if argc
			(= theSelector theTheSelector)
			(if (>= argc 2)
				(= value theValue)
				(if (>= argc 3) (= variance theVariance))
			)
		)
		(= count 0)
		(= active 1)
	)
	
	(method (doit)
		(if
			(and
				active
				(not (umod (++ cycles) 50))
				(>
					(Abs (- value (Eval Submarine theSelector)))
					variance
				)
			)
			(if (<= (++ count) 3)
				(if modelessDialog (-- count) else (self warn:))
			else
				(self die:)
			)
		)
	)
	
	(method (warn)
	)
	
	(method (die)
	)
)
