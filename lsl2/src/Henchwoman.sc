;;; Sierra Script 1.0 - (do not remove this comment)
(script# HENCHWOMAN)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Game)

(public
	rm8 0
)

(local
	local0
)
(instance rm8 of Region
	(properties)
	
	(method (init)
		(Load SOUND 105)
		(theSound init:)
		(super init:)
	)
	
	(method (handleEvent event)
		(if
			(or
				(!= (event type?) saidEvent)
				(== henchwomanIsHere FALSE)
				(event claimed?)
			)
			(return)
		)
		(if (Said 'call') (Print 8 0) (Print 8 1 #at -1 152))
		(if
			(Said
				'copulate,(nap<using),(enjoy<make),(bone<hop),(copulate<up)'
			)
			(Print 8 2)
		)
		(if (Said 'look>')
			(if (Said '/bimbo') (Print 8 3))
			(if (Said '/boob') (Print 8 4))
			(if (Said '/ass') (Print 8 5))
			(if (Said '/ear') (Print 8 6))
			(if (Said '/dress') (Print 8 7))
		)
	)
	
	(method (notify theLoop)
		(theSound loop: theLoop play:)
	)
)

(instance theSound of Sound
	(properties
		number 105
	)
)
