;;; Sierra Script 1.0 - (do not remove this comment)
(script# AIRPLANE)
(include game.sh)
(use Main)
(use Intrface)
(use Game)

(public
	rm600 0
)

(instance rm600 of Region
	(properties)
	
	(method (init)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== gamePhase phaseAIRPLANE)
				(not gamePhaseTime)
				(> rgMinutes 5)
				(== currentStatus egoNormal)
			)
			(= currentStatus egoDead)
			(= gamePhase phaseTIMEOVER)
			(ego hide:)
			(Print AIRPLANE 0 #draw)
			(Print AIRPLANE 1)
		)
		(if (and (== gamePhase phaseAIRPLANE) (== gamePhaseTime 300))
			(-- gamePhaseTime)
			(Print AIRPLANE 2)
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
			(Print AIRPLANE 3)
		)
		(if
		(and (ego has: iBobbyPin) (Said 'wear,conceal,apply/bobbypin'))
			(Print AIRPLANE 4)
		)
		(if
		(and (ego has: iPamphlet) (Said '(look<in),look/pamphlet'))
			(Print AIRPLANE 5)
			(Print AIRPLANE 6)
		)
		(if
			(and
				(ego has: iAirsickBag)
				(or (Said 'barf') (Said 'apply/bag'))
			)
			(PrintOk)
			(Print AIRPLANE 7)
			(ego put: iAirsickBag -1)
			(theGame changeScore: -2)
		)
		(if (Said 'look>')
			(if (Said '/up,overhead,ceiling') (Print AIRPLANE 8))
			(if (Said '/bimbo') (Print AIRPLANE 9))
			(if (Said '/barstool')
				(if (== currentStatus egoSitting)
					(Print AIRPLANE 10)
				else
					(Print AIRPLANE 11)
				)
			)
			(if (Said '[/airport]') (Print AIRPLANE 12))
		)
		(if (Said 'call,call/bimbo') (Print AIRPLANE 13))
		(if (Said 'carry,caress,caress/bimbo') (Print AIRPLANE 14))
		(if (Said 'call/children,man')
			(if (Random 0 1)
				(Print (Format @str AIRPLANE 15 introductoryPhrase))
				(Print AIRPLANE 16)
			else
				(Print AIRPLANE 17)
			)
		)
	)
)
