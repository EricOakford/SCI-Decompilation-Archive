;;; Sierra Script 1.0 - (do not remove this comment)
(script# PENTAGON)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use Actor)
(use System)

(public
	pentagonFront 0
)

(instance pentagonFront of Room
	(properties
		picture 18
		style DISSOLVE
	)
	
	(method (init)
		(super init:)
		(Load VIEW 18)
		(Load SOUND (SoundFX 67))
		(addToPics
			add: cars1Pic cars2Pic jimsCarPic
			eachElementDo: #init
			doit:
		)
		(self setScript: watchTheBuilding)
	)
)

(instance cars1Pic of PicView
	(properties
		y 136
		x 107
		view 18
		loop 4
		priority 9
	)
)

(instance cars2Pic of PicView
	(properties
		y 135
		x 263
		view 18
		loop 4
		cel 1
		priority 9
	)
)

(instance jimsCarPic of PicView
	(properties
		y 169
		x 189
		view 18
		loop 3
		priority 13
	)
)

(instance watchTheBuilding of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(globalSound number: (SoundFX 67) loop: -1 play:)
				(= cycles 10)
			)
			(1
				(Print 18 0 #at -1 15 #dispose)
				(= seconds 8)
			)
			(2 (curRoom newRoom: 23))
		)
	)
)
