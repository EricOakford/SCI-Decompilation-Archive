;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4)
(include game.sh)
(use System)


(class BassSetter of Code
	(properties
		radii 7
	)
	
	(method (doit param1)
		(param1 brBottom: (+ (param1 y?) 1))
		(param1 brTop: (- (param1 brBottom?) (param1 yStep?)))
		(param1 brLeft: (- (param1 x?) radii))
		(param1 brRight: (+ (param1 x?) radii))
	)
)
