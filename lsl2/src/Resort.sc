;;; Sierra Script 1.0 - (do not remove this comment)
(script# RESORT)
(include game.sh)
(use Main)
(use Intrface)
(use Game)

(public
	rm400 0
)

(instance rm400 of Region
	(properties)
	
	(method (init)
		(super init:)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if
			(and
				(ego has: iSoap)
				(or (Said 'clean/me')	;EO: This originally was '&,&', which seems &to be a decompiler error.
					(Said 'apply/soap')
				)
			)
			(Print RESORT 0)
			(Print RESORT 1 #at -1 152)
		)
		(if (and (ego has: iFlower) (Said 'smell/bush,flower'))
			(Print RESORT 2)
		)
		(if (Said 'look>')
			(if (Said '/up,overhead,cloud') (Print RESORT 3))
			(if (Said '/children,man,bimbo') (Print RESORT 4))
			(if (Said '/palm,bush,bush')
				(Print RESORT 5)
				(Print RESORT 6)
			)
		)
	)
)
