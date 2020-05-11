;;; Sierra Script 1.0 - (do not remove this comment)
(script# AIRPLANE)
(include sci.sh)
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
				(== gamePhase 5)
				(not gamePhaseTime)
				(> rgMinutes 5)
				(== egoState 0)
			)
			(= egoState 1001)
			(= gamePhase 0)
			(ego hide:)
			(Print 600 0 #draw)
			(Print 600 1)
		)
		(if (and (== gamePhase 5) (== gamePhaseTime 300))
			(-- gamePhaseTime)
			(Print 600 2)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if
			(and
				(ego has: 24)
				(Said '(conceal<on),apply,wear/parachute')
			)
			(Print 600 3)
		)
		(if
		(and (ego has: 25) (Said 'wear,conceal,apply/bobbypin'))
			(Print 600 4)
		)
		(if
		(and (ego has: 26) (Said '(look<in),look/pamphlet'))
			(Print 600 5)
			(Print 600 6)
		)
		(if
			(and
				(ego has: 27)
				(or (Said 'barf') (Said 'apply/bag'))
			)
			(PrintOk)
			(Print 600 7)
			(ego put: 27 -1)
			(theGame changeScore: -2)
		)
		(if (Said 'look>')
			(if (Said '/up,overhead,ceiling') (Print 600 8))
			(if (Said '/bimbo') (Print 600 9))
			(if (Said '/barstool')
				(if (== egoState 1009)
					(Print 600 10)
				else
					(Print 600 11)
				)
			)
			(if (Said '[/airport]') (Print 600 12))
		)
		(if (Said 'call,call/bimbo') (Print 600 13))
		(if (Said 'carry,caress,caress/bimbo') (Print 600 14))
		(if (Said 'call/children,man')
			(if (Random 0 1)
				(Print (Format @str 600 15 introductoryPhrase))
				(Print 600 16)
			else
				(Print 600 17)
			)
		)
	)
)
