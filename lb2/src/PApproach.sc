;;; Sierra Script 1.0 - (do not remove this comment)
(script# 919)
(include game.sh)
(use PolyPath)


(class PApproach of PolyPath
	(properties
		distance 20
		targetX 0
		targetY 0
	)
	
	(method (init actor whom)
		(if (>= argc 1)
			(= client actor)
			(if (>= argc 2)
				(if (IsObject [whom 0])
					(= targetX ([whom 0] x?))
					(= targetY ([whom 0] y?))
					(if (>= argc 3)
						(= distance [whom 1])
						(if (>= argc 4)
							(= caller [whom 2])
						)
					)
				else
					(= targetX [whom 0])
					(= targetY [whom 1])
					(if (>= argc 4)
						(= distance [whom 2])
						(if (>= argc 5)
							(= caller [whom 3])
						)
					)
				)
			)
			(super init: client targetX targetY caller)
		else
			(super init:)
		)
	)
	
	(method (onTarget)
		(return
			(<=
				(GetDistance (client x?) (client y?) targetX targetY)
				distance
			)
		)
	)
)
