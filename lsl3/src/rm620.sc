;;; Sierra Script 1.0 - (do not remove this comment)
(script# 620)
(include game.sh)
(use Main)
(use Intrface)
(use Follow)
(use Motion)
(use Game)
(use Actor)

(public
	rm620 0
)

(instance rm620 of Room
	(properties
		picture 620
		east 630
	)
	
	(method (init)
		(super init:)
		(addToPics
			add: atpKQsign
			add: atpNumbers
			add: atpSalesman1
			add: atpSalesman2
			add: atpSalesman3
			add: atpSalesman4
			add: atpTarget1
			add: atpTarget2
			add: atpTarget3
			add: atpTarget4
			add: atpTaxi
			add: atpTombstone
			add: atpCauldron
			add: atpClothes
			add: atpMonolithBurger
			doit:
		)
		(NormalEgo)
		(ego posn: 12 188 init:)
		(NormalActor aLarry 0)
		(aLarry
			view: 720
			posn: 12 184
			setMotion: Follow ego 28
			setCycle: Walk
			init:
			loop: 0
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'get/flat')
				(Print 620 0)
			)
			((Said 'get')
				(Print 620 1)
			)
			((Said 'look>')
				(cond 
					((Said '/prop')
						(Print 620 2)
					)
					((Said '/flat')
						(Print 620 3)
					)
					((Said '/couple,man')
						(Print 620 4)
					)
					((Said '/cauldron')
						(Print 620 5)
					)
					((Said '/number')
						(Print 620 6)
					)
					((Said '/cloth')
						(Print 620 7)
					)
					((Said '/gravestone')
						(Print 620 8
							#mode teJustCenter
							#at 10 -1
							#width 290
						)
					)
					((Said '/awning,column')
						(Print 620 9)
					)
					((Said '/cab,auto')
						(Print 620 10)
						(Print 620 11
							#at -1 144
						))
					((Said '[/backdrop,backstage,area]')
						(Print 620 12)
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(music fade:)
	)
)

(instance atpKQsign of PicView
	(properties
		y 107
		x 122
		view 620
		priority 7
		signal ignrAct
	)
)

(instance atpNumbers of PicView
	(properties
		y 164
		x 177
		view 620
		cel 1
		priority 12
	)
)

(instance atpSalesman1 of PicView
	(properties
		y 134
		x 7
		view 620
		cel 2
		priority 9
	)
)

(instance atpSalesman2 of PicView
	(properties
		y 142
		x 16
		view 620
		cel 2
		priority 10
	)
)

(instance atpSalesman3 of PicView
	(properties
		y 151
		x 10
		view 620
		cel 2
		priority 11
	)
)

(instance atpSalesman4 of PicView
	(properties
		y 156
		x 26
		view 620
		cel 2
		priority 11
	)
)

(instance atpTarget1 of PicView
	(properties
		y 158
		x 238
		view 620
		loop 1
		priority 11
	)
)

(instance atpTarget2 of PicView
	(properties
		y 161
		x 241
		view 620
		loop 1
		priority 12
	)
)

(instance atpTarget3 of PicView
	(properties
		y 164
		x 245
		view 620
		loop 1
		priority 12
	)
)

(instance atpTarget4 of PicView
	(properties
		y 116
		x 278
		view 620
		loop 1
		priority 8
	)
)

(instance atpTaxi of PicView
	(properties
		y 189
		x 106
		view 620
		loop 1
		cel 1
		priority 14
	)
)

(instance atpTombstone of PicView
	(properties
		y 97
		x 23
		view 620
		loop 1
		cel 2
		priority 6
	)
)

(instance atpCauldron of PicView
	(properties
		y 128
		x 58
		view 620
		loop 1
		cel 3
		priority 9
	)
)

(instance atpClothes of PicView
	(properties
		y 137
		x 185
		view 620
		loop 1
		cel 4
		priority 9
	)
)

(instance atpMonolithBurger of PicView
	(properties
		y 156
		x 297
		view 620
		loop 2
		priority 11
	)
)

(instance aLarry of Actor)
