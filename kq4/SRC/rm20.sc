;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include game.sh)
(use Main)
(use Intrface)
(use Game)

(public
	Room20 0
)

(instance Room20 of Room
	(properties
		picture 20
	)
	
	(method (init)
		(= north 14)
		(= south 26)
		(= east 21)
		(= west 19)
		(= horizon 68)
		(= isIndoors FALSE)
		(super init:)
		(ego view: 2 init:)
		(if isNightTime (curRoom overlay: 120))
		(self setRegions: MEADOW UNICORN)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if
				(and
					(== (event type?) saidEvent)
					(or (Said 'look/room') (Said 'look[<around][/!*]'))
				)
				(Print 20 0)
			else
				FALSE
			)
		)
	)
)
