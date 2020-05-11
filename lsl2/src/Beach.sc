;;; Sierra Script 1.0 - (do not remove this comment)
(script# BEACH)
(include game.sh)
(use Main)
(use Intrface)
(use Game)

(public
	rm401 0
)

(local
	triedToEnterSea
)
(instance rm401 of Region
	(properties)
	
	(method (init)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $0100)
			(if (and (== triedToEnterSea FALSE) (!= currentStatus egoAtSea))
				(= triedToEnterSea TRUE)
				(Print BEACH 0)
			)
		else
			(= triedToEnterSea FALSE)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'call') (Print BEACH 1))
		(if (or (Said 'get/suntan') (Said 'lie<down'))
			(Print BEACH 2)
		)
		(if (Said 'dig')
			(Print BEACH 3)
			(Print BEACH 4 #at -1 152)
		)
		(if (Said '/umbrella') (Print BEACH 5))
		(if (Said '/boulder') (Print BEACH 6))
		(if (Said 'climb/palm') (Print BEACH 7))
		(if (Said 'look>')
			(if (Said '/up,overhead,cloud')
				(Print BEACH 8)
				(Print BEACH 9 #at -1 152)
			)
			(if (Said '/children,man,bimbo') (Print BEACH 10))
			(if (Said '/lagoon,fluid,lagoon') (Print BEACH 11))
			(if (Said '/beach,beach,carpet') (Print BEACH 12))
			(if (Said '/palm,bush,bush') (Print BEACH 13))
		)
	)
)
