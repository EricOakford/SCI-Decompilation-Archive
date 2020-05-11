;;; Sierra Script 1.0 - (do not remove this comment)
(script# AIRPORT)
(include game.sh)
(use Main)
(use Intrface)
(use Game)

(public
	rm500 0
)

(instance rm500 of Region
	(properties)
	
	(method (init)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== gamePhase phaseAIRPORT)
				(not gamePhaseTime)
				(!= curRoomNum 57)
				(> rgMinutes 5)
				(or (== currentStatus egoNormal) (== currentStatus egoInTerminal))
			)
			(= currentStatus egoDead)
			(= gamePhase phaseTIMEOVER)
			(Print 500 0)
			(Print 500 1)
			(Print 500 2)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if
			(and
				(ego has: iParachute)
				(Said '(conceal<on),apply,wear/parachute')
			)
			(Print 500 3)
		)
		(if
		(and (ego has: iBobbyPin) (Said 'wear,conceal,apply/bobbypin'))
			(Print 500 4)
		)
		(if
		(and (ego has: iPamphlet) (Said '(look<in),look/pamphlet'))
			(Print 500 5)
			(Print 500 6)
		)
		(if
		(and (ego has: iAirlineTicket) (Said 'look<in/ticket<airline'))
			(Print 500 7)
		)
		(if (Said 'look>')
			(if (Said '/up,overhead,ceiling') (Print 500 8))
			(if (Said '/art') (Print 500 9))
			(if (Said '/children,man,bimbo') (Print 500 10))
			(if (Said '/airline') (Print 500 11))
			(if (Said '/brick') (Print 500 12))
		)
	)
)
