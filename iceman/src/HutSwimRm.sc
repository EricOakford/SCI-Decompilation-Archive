;;; Sierra Script 1.0 - (do not remove this comment)
(script# 8)
(include game.sh)
(use Main)
(use Intrface)
(use InitAllFeatures)
(use RFeature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	HutSwimRm 0
)

(local
	local0
)
(instance HutSwimRm of Room
	(properties
		picture 8
		horizon 120
		east 16
		south 15
		west 15
	)
	
	(method (init)
		(super init:)
		(switch prevRoomNum
			(3
				(ego x: (+ (/ (ego x?) 8) 260))
			)
			(4
				(if (== (ego loop?) 1)
					(ego
						x:
							(cond 
								((< (ego y?) 104) 154)
								((< (ego y?) 146) 207)
								((< (ego y?) 200) 252)
							)
						loop: 2
					)
				else
					(ego x: 261 loop: 0)
				)
			)
			(5
				(if (== (ego loop?) 1)
					(ego
						x:
							(cond 
								((< (ego y?) 104) (+ (/ (ego y?) 2) 8))
								((< (ego y?) 146) (- (ego y?) 30))
								((< (ego y?) 200) 144)
							)
						loop: 2
					)
				else
					(ego x: 147 loop: 0)
				)
			)
			(6 (ego x: 122 loop: 2))
			(7 (ego x: 14))
		)
		(ego init: view: 217 setLoop: -1 y: (+ 5 horizon))
		(self setRegions: 301 300 308)
		(addToPics
			add:
				hut1
				hut2
				hut3
				hut4
				hotelHut
				tree
				((Clone tree) x: 220 y: 89 yourself:)
				((Clone tree) x: 114 y: 87 yourself:)
				((Clone tree) x: 301 y: 94 yourself:)
			eachElementDo: #init
			eachElementDo: #priority 1
			doit:
		)
		(InitAllFeatures)
		(pixelPerson init:)
	)
	
	(method (doit)
		(if (< (ego y?) horizon)
			(self
				newRoom:
					(cond 
						((<= (ego x?) 55) 7)
						((<= (ego x?) 117) 5)
						((<= (ego x?) 127) 6)
						((<= (ego x?) 250) 4)
						(else 3)
					)
			)
		else
			(super doit:)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]>')
				(cond 
					((Said '/building')
						(Print 8 0)
					)
					((Said '/hotel')
						(Print 8 1)
					)
					((Said '/palm')
						(Print 8 2)
					)
					((Said '/bush')
						(Print 8 3)
					)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(super newRoom: newRoomNumber)
		(switch newRoomNumber
			(7
				(ego y: (+ (ego x?) 125) loop: 0)
			)
			(5
				(cond 
					((<= (ego x?) 76) (ego posn: 84 103 view: 200 loop: 0))
					((<= (ego x?) 127) (ego posn: 72 137 view: 200 loop: 0))
					((<= (ego x?) 152) (ego posn: 10 185 loop: 0))
				)
			)
			(4
				(cond 
					((<= (ego x?) 181) (ego posn: 84 103 view: 200 loop: 0))
					((<= (ego x?) 233) (ego posn: 72 137 view: 200 loop: 0))
					((<= (ego x?) 250) (ego posn: 10 185 loop: 0))
				)
			)
			(3
				(ego x: (/ (* (- (ego x?) 250) 32) 7))
			)
		)
	)
)

(instance hut1 of RPicView
	(properties
		y 97
		x 125
		view 8
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]/building')
				(Print 8 4)
			)
		)
	)
)

(instance hut2 of RPicView
	(properties
		y 95
		x 74
		view 8
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]/building')
				(Print 8 5)
			)
		)
	)
)

(instance hut3 of RPicView
	(properties
		y 98
		x 177
		view 8
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]/building')
				(Print 8 6)
			)
		)
	)
)

(instance hut4 of RPicView
	(properties
		y 102
		x 231
		view 8
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]/building')
				(Print 8 7)
			)
		)
	)
)

(instance hotelHut of RPicView
	(properties
		y 96
		x 310
		view 8
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]/building,hotel')
				(Print 8 8)
			)
		)
	)
)

(instance tree of RPicView
	(properties
		y 88
		x 56
		view 8
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]/palm')
				(Print 8 9)
			)
		)
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
			setPri: 0
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
				(client posn: 23 90 show: setMotion: MoveTo 330 100 self)
			)
			(2
				(client hide:)
				(= seconds (Random 2 10))
			)
			(3
				(client show: setMotion: MoveTo 23 90 self)
			)
			(4 (client hide:) (self init:))
		)
	)
)
