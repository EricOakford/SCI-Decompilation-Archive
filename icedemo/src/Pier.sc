;;; Sierra Script 1.0 - (do not remove this comment)
(script# PIER)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Wander)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	pierRm 0
)

(instance pierRm of Room
	(properties
		picture 23
		style WIPERIGHT
	)
	
	(method (init)
		(super init:)
		(LoadMany VIEW 123 23 423 23 523)
		(ego
			view: 123
			loop: 0
			ignoreControl: cYELLOW
			ignoreControl: cRED
			observeControl: cWHITE
			posn: 20 145
			init:
			setScript: walkThePlank
		)
		(flag init:)
		(wave1 init:)
		(wave2 init:)
		(bird1 init:)
		(bird2 init:)
		(addToPics add: hatch officer init: doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript WANDER)
	)
)

(instance officer of PicView
	(properties
		y 115
		x 156
		view 523
		priority 7
	)
)

(instance walkThePlank of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(globalSound number: 68 play:)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 43 140 self
				)
			)
			(1
				(Print 23 0 #at -1 148 #dispose)
				(ego setMotion: MoveTo 161 116 self)
			)
			(2
				(ego setMotion: MoveTo 180 116 self)
			)
			(3
				(client setScript: presentArms)
			)
		)
	)
)

(instance presentArms of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 23
					setLoop: 1
					setCel: 0
					cycleSpeed: 0
					setCycle: Forward
				)
				(= seconds 4)
			)
			(1 (ego setCycle: BegLoop self))
			(2
				(wave1 setCycle: 0)
				(wave2 setCycle: 0)
				(bird1 setCycle: 0 setMotion: 0)
				(bird2 setCycle: 0 setMotion: 0)
				(curRoom newRoom: 25)
			)
		)
	)
)

(instance wave1 of Prop
	(properties
		y 189
		x 61
		view 423
		priority 14
		cycleSpeed 2
	)
	
	(method (init)
		(super init:)
		(if (== howFast slow)
			(self addToPic:)
		else
			(self setCycle: Forward)
		)
	)
)

(instance wave2 of Prop
	(properties
		y 189
		x 181
		view 423
		loop 1
		priority 14
		cycleSpeed 2
	)
	
	(method (init)
		(super init:)
		(if (== howFast slow)
			(self addToPic:)
		else
			(self setCycle: Forward)
		)
	)
)

(instance flag of Prop
	(properties
		y 42
		x 267
		view 23
		cel 2
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(if (== howFast slow)
			(self addToPic:)
		else
			(self setCycle: Forward)
		)
	)
	
	(method (addToPic)
		(self cel: 8)
		(super addToPic:)
	)
)

(instance hatch of PicView
	(properties
		y 127
		x 238
		view 23
		loop 3
		priority 9
	)
)

(instance bird1 of Actor
	(properties
		y 20
		x 100
		view 23
	)
	
	(method (init)
		(super init:)
		(self
			setMotion: Wander 100
			setCycle: Forward
			x: (Random 50 270)
			setLoop: 5
			setPri: 0
		)
	)
	
	(method (canBeHere)
		(return
			(if (and (< -20 y) (< y 50) (< -50 x))
				(< x 370)
			else
				0
			)
		)
	)
)

(instance bird2 of Actor
	(properties
		y 20
		x 100
		view 23
	)
	
	(method (init)
		(super init:)
		(self
			setMotion: Wander 100
			setCycle: Forward
			x: (Random 50 270)
			setLoop: 5
			setPri: 0
		)
	)
	
	(method (canBeHere)
		(return
			(if (and (< -20 y) (< y 50) (< -50 x))
				(< x 370)
			else
				0
			)
		)
	)
)
