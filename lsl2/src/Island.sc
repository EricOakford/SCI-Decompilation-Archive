;;; Sierra Script 1.0 - (do not remove this comment)
(script# ISLAND)
(include game.sh)
(use Main)
(use Intrface)
(use Game)

(public
	rm700 0
)

(instance rm700 of Region
	(properties)
	
	(method (init)
		(super init:)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (and (ego has: iStoutStick) (Said 'apply,throw/stick'))
			(Print ISLAND 0)
			(ego put: iStoutStick -1)
		)
		(if (ego has: iVine)
			(if (Said '</landscape')
				(Ok)
				(Print ISLAND 1)
				(Print ISLAND 2 #at -1 152)
				(ego put: iVine -1)
			)
			(if (Said 'throw,apply/landscape') (Print ISLAND 3))
		)
		(if
			(and
				(ego has: iAshes)
				(Said 'smell,eat,throw,conceal,look/>')
			)
			(Print ISLAND 4)
		)
		(if
		(and (ego has: iAshes) (Said 'throw,throw,conceal,apply/>'))
			(Print ISLAND 5)
			(ego put: iAshes -1)
		)
		(if (Said 'look>')
			(if (Said '/up,overhead,cloud') (Print ISLAND 6))
			(if (Said '/chimpanzee') (Print ISLAND 7))
			(if (Said '/boulder,head,art') (Print ISLAND 8))
			(if (Said '/palm') (Print ISLAND 9))
		)
	)
)
