;;; Sierra Script 1.0 - (do not remove this comment)
(script# CITY)
(include game.sh)
(use Main)
(use Intrface)
(use Game)

(public
	rm200 0
)

(instance rm200 of Region
	(properties)
	
	(method (init)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== gameState rgCITY)
				(not rgSeconds)
				(< curRoomNum 26)
				(> roomSeconds 5)
				(== currentStatus egoNORMAL)
			)
			(= currentStatus egoDEAD)
			(= gameState NULL)
			(Print CITY 0)
			(Print CITY 1)
		)
		(if
			(and
				(> gameMinutes 9)
				(== gameSeconds 1)
				(> roomSeconds 8)
				((inventory at: iDollarBill) ownedBy: 23)
			)
			(++ gameSeconds)
			(Print CITY 2)
			(Print CITY 3 #at -1 152)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'call/man,children,bimbo') (Print CITY 4))
		(if
		(and (ego has: iLotteryTicket) (Said 'scratch,mark,apply/ticket'))
			(Print CITY 5)
			(ego put: iLotteryTicket -1)
			(theGame changeScore: -2)
		)
		(if
			(and
				(ego has: iSunscreen)
				(Said 'wear,caress,conceal,apply/lotion,(lotion<suntan)')
			)
			(Print CITY 6)
		)
		(if (Said '/cab') (Print CITY 7))
		(if (Said 'look>')
			(if (Said '/up,overhead,cloud') (Print CITY 8))
			(if (Said '/auto,freeway,angeles') (Print CITY 9))
			(if (Said '/carpet')
				(if (> curRoomNum 30)
					(Print CITY 10)
				else
					(Print CITY 11)
				)
			)
			(if (Said '/palm,bush') (Print CITY 12))
		)
	)
)
