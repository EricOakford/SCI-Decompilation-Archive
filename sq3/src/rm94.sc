;;; Sierra Script 1.0 - (do not remove this comment)
(script# 94)
(include game.sh)
(use Main)
(use Intrface)
(use Follow)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm94 0
)

(local
	local0
	local1
)
(instance rm94 of Room
	(properties
		picture 94
		style HSHUTTER
	)
	
	(method (init)
		(User canInput: TRUE canControl: TRUE)
		(Load VIEW 68)
		(Load VIEW 102)
		(Load VIEW 195)
		(Load VIEW 196)
		(super init:)
		(ship init: stopUpd:)
		(ramp init: stopUpd:)
		(switch prevRoomNum
			(93
				(addToPics add: ship1 ship2 ship3 ship4 ship5)
				(addToPics doit:)
				(ego
					view: 68
					posn: 67 121
					init:
					setMotion: MoveTo 300 121 self
				)
			)
			(120
				(User canInput: FALSE canControl: FALSE)
				(= inCartoon TRUE)
				(Load SOUND 38)
				(Load SOUND 40)
				(addToPics add: ship1 ship2 ship3 ship4 ship5 robo)
				(addToPics doit:)
				(ego
					view: 68
					setLoop: -1
					setCel: -1
					setStep: 6 3
					setPri: 14
					posn: 52 176
					init:
				)
				(Scott init: setScript: ScottScript)
				(Mark init: setMotion: Follow Scott 10)
				(self setScript: RunAway)
			)
			(else 
				(User canInput: FALSE canControl: FALSE)
				(= inCartoon TRUE)
				(Load SOUND 38)
				(Load SOUND 40)
				(addToPics add: ship1 ship2 ship3 ship4 ship5 robo)
				(addToPics doit:)
				(ego
					view: 68
					setLoop: -1
					setCel: -1
					setStep: 6 3
					setPri: 14
					posn: 52 176
					init:
				)
				(Scott init: setScript: ScottScript)
				(Mark init: setMotion: Follow Scott 10)
				(self setScript: RunAway)
			)
		)
	)
	
	(method (doit)
		(if (== (ego onControl:) (| cBLACK cBLUE))
			(curRoom newRoom: 93)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							((Said '[/area]')
								(Print 94 0)
							)
							((Said '/craft,aluminum')
								(Print 94 1)
							)
							((Said '/bow')
								(Print 94 2)
							)
							((Said '/air')
								(Print 94 3)
							)
						)
					)
					((Said 'climb[/down,up]')
						(Print 94 4)
					)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance RunAway of Script
	(method (doit)
		(super doit:)
		(if (and (== local0 1) (== (liftOff prevSignal?) -1))
			(= local0 0)
			(self changeState: 9)
		)
		(if (and (== local0 2) (== (warpOut prevSignal?) -1))
			(curRoom newRoom: 14)
		)
	)
	
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 200 182 self)
			)
			(1
				(balloon init: stopUpd:)
				(ego setMotion: MoveTo 261 182 self)
			)
			(2
				(ego setMotion: MoveTo 269 174 self)
			)
			(3
				(ego dispose:)
			)
			(4
				(liftOff play:)
				(ramp dispose:)
				(Scott dispose:)
				(Mark dispose:)
				(ship setCel: 1)
				(= seconds 5)
			)
			(5
				(ship setMotion: MoveTo 228 115 self)
			)
			(6
				(ship setCel: 2 setMotion: MoveTo 228 45 self)
			)
			(7
				(ship setCel: 3 setMotion: MoveTo 228 -11 self)
			)
			(8
				(= local0 1)
				(balloon dispose:)
			)
			(9
				(warpOut play:)
				(ship
					setLoop: 5
					setCel: 0
					posn: 279 -14
					setStep: 4 6
					setMotion: MoveTo 243 15 self
				)
			)
			(10
				(ship setCycle: EndLoop setMotion: MoveTo 215 43 self)
			)
			(11
				(ship setMotion: MoveTo 215 -5 self)
			)
			(12 (= local0 2))
		)
	)
)

(instance ScottScript of Script
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(= cycles 10)
			)
			(1
				(Scott setMotion: MoveTo 261 150 self)
			)
			(2
				(Scott setMotion: MoveTo 269 142 self)
			)
			(3
				(RunAway changeState: 4)
			)
		)
	)
)

(instance Scott of Actor
	(method (init)
		(super init:)
		(self
			view: 195
			loop: 0
			posn: 52 144
			setCycle: Walk
			setStep: 6 3
			setPri: 14
			illegalBits: 0
			ignoreActors: TRUE
		)
	)
)

(instance Mark of Actor
	(method (init)
		(super init:)
		(self
			view: 196
			loop: 0
			posn: 52 144
			setCycle: Walk
			setStep: 6 3
			setPri: 14
			illegalBits: 0
			ignoreActors: TRUE
		)
	)
)

(instance ship of Actor
	(method (init)
		(super init:)
		(self
			view: 102
			setLoop: 0
			setCel: 0
			posn: 228 165
			setStep: 6 6
			setPri: 15
			illegalBits: 0
			ignoreHorizon: TRUE
			ignoreActors: TRUE
		)
	)
)

(instance ramp of Prop
	(method (init)
		(super init:)
		(self
			view: 102
			setLoop: 1
			setCel: 0
			posn: 266 182
			setPri: 13
			setCycle: 0
			ignoreActors: TRUE
		)
	)
)

(instance balloon of Prop
	(method (init)
		(super init:)
		(self
			view: 102
			setLoop: 4
			setCel: 1
			posn: 79 162
			setPri: 15
			setCycle: 0
			ignoreActors: TRUE
		)
	)
)

(instance ship1 of PicView
	(properties
		y 176
		x 146
		view 102
		loop 2
		signal ignrAct
	)
)

(instance ship2 of PicView
	(properties
		y 157
		x 176
		view 102
		loop 2
		cel 1
		signal ignrAct
	)
)

(instance ship3 of PicView
	(properties
		y 140
		x 204
		view 102
		loop 2
		cel 2
		signal ignrAct
	)
)

(instance ship4 of PicView
	(properties
		y 139
		x 263
		view 102
		loop 3
		signal ignrAct
	)
)

(instance ship5 of PicView
	(properties
		y 139
		x 312
		view 102
		loop 3
		signal ignrAct
	)
)

(instance robo of PicView
	(properties
		y 179
		x 41
		view 102
		loop 4
		priority 15
		signal ignrAct
	)
)

(instance warpOut of Sound
	(properties
		number 38
		priority 1
	)
)

(instance liftOff of Sound
	(properties
		number 40
		priority 1
	)
)
