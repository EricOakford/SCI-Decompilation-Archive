;;; Sierra Script 1.0 - (do not remove this comment)
(script# 121)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm121 0
)

(instance rm121 of Room
	(properties
		picture 121
	)
	
	(method (init &tmp [temp0 50])
		(HandsOff)
		(= inCartoon TRUE)
		(Load VIEW 194)
		(Load VIEW 68)
		(Load VIEW 210)
		(Load PICTURE 120)
		(Load PICTURE 122)
		(Load SOUND 30)
		(super init:)
		(ego
			view: 68
			loop: 2
			posn: 152 92
			setCycle: Walk
			setStep: 2 1
			setPri: 15
			ignoreActors: TRUE
		)
		(self setScript: OpenDoor)
	)
	
	(method (doit &tmp [temp0 50])
		(super doit:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
	)
)

(instance OpenDoor of Script
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(Print 121 0)
				(door init: setCycle: EndLoop self)
			)
			(1
				(ego init: setMotion: MoveTo 152 116 self)
				(guard1 init: setMotion: MoveTo 149 116)
				(guard2 init: setMotion: MoveTo 155 116)
			)
			(2
				(= seconds 2)
			)
			(3
				(door setCycle: BegLoop self)
				(ego hide:)
				(guard1 hide:)
				(guard2 hide:)
			)
			(4
				(curRoom drawPic: 120)
				(addToPics add: robo1 robo2)
				(addToPics doit:)
				(ego show:)
				(guard1 show:)
				(guard2 show:)
				(theMusic number: 30 loop: -1 priority: 1 play:)
				(= cycles 2)
			)
			(5
				(= seconds 1)
				(door dispose:)
			)
			(6
				(= cycles 2)
			)
			(7
				(ego setMotion: MoveTo 145 116 self)
			)
			(8
				(ego setMotion: MoveTo 98 116 self)
				(guard1 setLoop: 3 setMotion: MoveTo 98 116)
				(guard2 setLoop: 3 setMotion: MoveTo 98 116)
			)
			(9
				(curRoom drawPic: 122)
				(elevator init:)
				(ego
					view: 0
					setStep: 3 2
					posn: 277 171
					ignoreActors: TRUE
					setPri: -1
					setMotion: MoveTo 145 171 self
				)
				(guard1
					setLoop: 0
					cel: 0
					setStep: 3 2
					posn: 296 168
					setPri: -1
					setMotion: MoveTo 162 168
				)
				(guard2
					setLoop: 0
					cel: 0
					setStep: 3 2
					posn: 291 174
					setPri: -1
					setMotion: MoveTo 166 174
				)
			)
			(10
				(ego hide:)
				(elevator
					illegalBits: 0
					setCycle: Walk
					setLoop: 0
					setMotion: MoveTo 145 122 self
				)
			)
			(11
				(guard1
					setLoop: 1
					cel: 0
					setMotion: MoveTo 330 (guard1 y?)
				)
				(guard2
					setLoop: 1
					cel: 0
					setMotion: MoveTo 330 (guard2 y?)
				)
				(elevator setLoop: 1 setMotion: MoveTo 145 67 self)
			)
			(12
				(elevator setLoop: 2 setMotion: MoveTo 145 41 self)
			)
			(13
				(ego cycleSpeed: 1 posn: 145 41 show:)
				(elevator setLoop: 3)
				(ego setMotion: MoveTo 137 40 self)
			)
			(14
				(ego view: 199 setLoop: 6 cel: 0 setCycle: EndLoop self)
			)
			(15
				(elevator setMotion: MoveTo 145 67 self)
			)
			(16
				(elevator setLoop: 4 setMotion: MoveTo 145 122 self)
			)
			(17
				(elevator setLoop: 5 setMotion: MoveTo 145 171 self)
			)
			(18
				(ego cycleSpeed: 0)
				(curRoom newRoom: 96)
			)
		)
	)
)

(instance door of Prop
	(method (init)
		(super init:)
		(self
			view: 194
			setLoop: 0
			setCel: 0
			posn: 153 91
			setPri: 5
			ignoreActors: TRUE
		)
	)
)

(instance guard1 of Actor
	(method (init)
		(super init:)
		(self
			view: 210
			setLoop: 2
			setCycle: Walk
			setStep: 2 1
			posn: 149 90
			setPri: 5
			ignoreActors: TRUE
		)
	)
)

(instance guard2 of Actor
	(method (init)
		(super init:)
		(self
			view: 210
			setLoop: 2
			setCycle: Walk
			setStep: 2 1
			posn: 155 90
			setPri: 5
			ignoreActors: TRUE
		)
	)
)

(instance elevator of Actor
	(method (init)
		(super init:)
		(self
			view: 199
			setLoop: 5
			setCel: 0
			yStep: 4
			posn: 145 171
			setPri: 4
			ignoreActors: TRUE
		)
	)
)

(instance robo1 of PicView
	(properties
		y 88
		x 53
		view 194
		loop 2
		priority 7
		signal ignrAct
	)
)

(instance robo2 of PicView
	(properties
		y 88
		x 265
		view 194
		loop 2
		cel 1
		priority 7
		signal ignrAct
	)
)
