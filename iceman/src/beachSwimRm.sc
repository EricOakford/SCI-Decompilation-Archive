;;; Sierra Script 1.0 - (do not remove this comment)
(script# 16)
(include game.sh)
(use Main)
(use Intrface)
(use InitAllFeatures)
(use LoadMany)
(use RFeature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	beachSwimRm 0
)

(instance beachSwimRm of Room
	(properties
		picture 16
		horizon 120
		east 15
		south 15
		west 8
	)
	
	(method (init)
		(self setRegions: 301 300 308)
		(LoadMany VIEW 217 1 16)
		(ego view: 217 init:)
		(super init:)
		(addToPics
			add:
				hut
				hut2
				hut3
				palmTree
				palmTree2
				palmTree3
				palmTree4
				palmTree5
				palmTree6
				palmTree7
				palmTree8
				palmTree9
				palmTree10
				palmTree11
			eachElementDo: #priority 0
			eachElementDo: #init
			doit:
		)
		(pixelPerson init:)
		(ego
			x:
				(switch prevRoomNum
					(2 (/ (ego x?) 8))
					(12
						(+ (/ (* (ego x?) 6) 32) 40)
					)
					(13
						(+ (/ (* (ego x?) 7) 32) 104)
					)
					(24 (+ (/ (ego x?) 2) 174))
					(else  1)
				)
		)
		(ego y: (+ 5 horizon) setLoop: -1)
		(pixelPerson init:)
		(InitAllFeatures)
	)
	
	(method (doit)
		(if (< (ego y?) horizon)
			(self
				newRoom:
					(cond 
						((<= (ego x?) 40) 2)
						((<= (ego x?) 104) 12)
						((<= (ego x?) 174) 13)
						(else 24)
					)
			)
		else
			(super doit:)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room,water]')
				(Print 16 0)
			)
		)
	)
	
	(method (newRoom nRoom)
		(super newRoom: nRoom)
		(switch nRoom
			(2 (ego x: (* (ego x?) 2)))
			(12
				(ego x: (+ (/ (* (- (ego x?) 40) 26) 6) 60))
			)
			(13
				(ego x: (* (/ (* (- (ego x?) 104) 8) 7) 4))
			)
			(24
				(ego x: (* (- (ego x?) 174) 2))
			)
		)
	)
)

(instance hut of RPicView
	(properties
		y 94
		x 76
		view 16
		priority 5
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]/building')
				(Print 16 1)
			)
		)
	)
)

(instance hut2 of RPicView
	(properties
		y 93
		x 125
		view 16
		cel 1
		priority 5
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]/building')
				(Print 16 1)
			)
		)
	)
)

(instance hut3 of RPicView
	(properties
		y 94
		x 169
		view 16
		cel 1
		priority 5
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]/building')
				(Print 16 1)
			)
		)
	)
)

(instance palmTree of PicView
	(properties
		y 95
		x 151
		view 16
		loop 1
		cel 2
		priority 5
	)
)

(instance palmTree2 of PicView
	(properties
		y 95
		x 103
		view 16
		loop 1
		priority 5
	)
)

(instance palmTree3 of PicView
	(properties
		y 89
		x 9
		view 16
		loop 1
		cel 2
		priority 5
	)
)

(instance palmTree4 of PicView
	(properties
		y 95
		x 28
		view 16
		loop 1
		priority 5
	)
)

(instance palmTree5 of PicView
	(properties
		y 93
		x 45
		view 16
		loop 1
		priority 5
	)
)

(instance palmTree6 of PicView
	(properties
		y 96
		x 142
		view 16
		loop 1
		priority 5
	)
)

(instance palmTree7 of PicView
	(properties
		y 92
		x 198
		view 16
		loop 1
		priority 5
	)
)

(instance palmTree8 of PicView
	(properties
		y 90
		x 210
		view 16
		loop 1
		cel 2
		priority 5
	)
)

(instance palmTree9 of PicView
	(properties
		y 92
		x 230
		view 16
		loop 1
		priority 5
	)
)

(instance palmTree10 of PicView
	(properties
		y 90
		x 252
		view 16
		loop 1
		priority 5
	)
)

(instance palmTree11 of PicView
	(properties
		y 91
		x 270
		view 16
		loop 1
		priority 5
	)
)

(instance pixelPerson of Actor
	(properties
		view 1
		loop 5
	)
	
	(method (init)
		(super init:)
		(self
			hide:
			setPri: 1
			setStep: 1 1
			setLoop:
			illegalBits: 0
			ignoreHorizon:
			moveSpeed: 1
			setScript: paceScript
		)
	)
)

(instance paceScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 10)))
			(1
				(client posn: -10 100 show: setMotion: MoveTo 295 90 self)
			)
			(2
				(client hide:)
				(= seconds (Random 2 10))
			)
			(3
				(client show: setMotion: MoveTo -10 100 self)
			)
			(4 (client hide:) (self init:))
		)
	)
)
