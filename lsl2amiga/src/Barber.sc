;;; Sierra Script 1.0 - (do not remove this comment)
(script# 7)
(include game.sh)
(use Main)
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
		(addToPics
			add: aSeat1 aSeat2 aTable1 aTable2 aCoatRack aPainting aCalendar aCounter
			doit:
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

(instance aSeat1 of PicView
	(properties
		y 146
		x 102
		view 232
		priority 8
		signal ignrAct
	)
)

(instance aSeat2 of PicView
	(properties
		y 146
		x 220
		view 232
		cel 1
		priority 8
		signal ignrAct
	)
)

(instance aTable1 of PicView
	(properties
		y 123
		x 119
		view 232
		loop 1
		cel 2
		priority 8
		signal ignrAct
	)
)

(instance aTable2 of PicView
	(properties
		y 123
		x 203
		view 232
		loop 1
		cel 3
		priority 8
		signal ignrAct
	)
)

(instance aCoatRack of PicView
	(properties
		y 111
		x 127
		view 232
		loop 1
		cel 1
		priority 7
		signal ignrAct
	)
)

(instance aPainting of PicView
	(properties
		y 101
		x 100
		view 232
		cel 2
		priority 1
		signal ignrAct
	)
)

(instance aCalendar of PicView
	(properties
		y 105
		x 208
		view 232
		cel 3
		priority 1
		signal ignrAct
	)
)

(instance aCounter of PicView
	(properties
		y 92
		x 162
		view 232
		loop 1
		cel 4
		priority 1
		signal ignrAct
	)
)
