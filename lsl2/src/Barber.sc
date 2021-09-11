;;; Sierra Script 1.0 - (do not remove this comment)
(script# BARBER)
(include game.sh)
(use Intrface)
(use Sound)
(use Game)
(use Actor)

(public
	rm7 0
)

(instance rm7 of Region
	(method (init)
		(super init:)
		((View new:)
			view: 232
			loop: 0
			cel: 0
			posn: 102 146
			setPri: 8
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 232
			loop: 0
			cel: 1
			posn: 220 146
			setPri: 8
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 232
			loop: 1
			cel: 2
			posn: 119 123
			setPri: 8
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 232
			loop: 1
			cel: 3
			posn: 203 123
			setPri: 8
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 232
			loop: 1
			cel: 1
			posn: 127 111
			setPri: 7
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 232
			loop: 0
			cel: 2
			posn: 100 101
			setPri: 1
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 232
			loop: 0
			cel: 3
			posn: 208 105
			setPri: 1
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 232
			loop: 1
			cel: 4
			posn: 162 92
			setPri: 1
			ignoreActors:
			addToPic:
		)
		(Load SOUND 107)
		(theSound play:)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/man,man,bimbo,children')
				(Print 7 0)
			)
			(if (Said '/barstool')
				(Print 7 1)
			)
			(if (Said '/buffet')
				(Print 7 2)
			)
			(if (Said '/cosmo')
				(Print 7 3)
			)
			(if (Said '/carpet')
				(Print 7 4)
			)
			(if (Said '/art,bimbo,art,brick')
				(Print 7 5)
			)
			(if (Said '/mirror')
				(Print 7 6)
			)
			(if (Said '[/building,building,buffet,carpet,ceiling,airport]')
				(Print 7 7)
			)
		)
	)
)

(instance theSound of Sound
	(properties
		number 107
	)
)
