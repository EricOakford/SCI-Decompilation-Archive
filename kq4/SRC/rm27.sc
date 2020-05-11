;;; Sierra Script 1.0 - (do not remove this comment)
(script# 27)
(include game.sh)
(use Main)
(use Intrface)
(use Game)

(public
	Room27 0
)

(instance Room27 of Room
	(properties
		picture 27
	)
	
	(method (init)
		(= north 21)
		(= south 3)
		(= east 28)
		(= west 26)
		(= horizon 75)
		(= isIndoors FALSE)
		(super init:)
		(ego view: 2 xStep: 3 yStep: 2 init:)
		(if isNightTime (curRoom overlay: 127))
		(self setRegions: WOODS UNICORN)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if
				(and
					(== (event type?) saidEvent)
					(or
						(Said 'look/around')
						(Said 'look/room')
						(Said 'look[<around][/!*]')
					)
				)
				(Print
					(Format @str 27 0
						(if (not isNightTime)
							{You hear birds chirping from the many trees.}
						else
							{_}
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(super newRoom: newRoomNumber)
	)
)
