;;; Sierra Script 1.0 - (do not remove this comment)
(script# 355)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm355 0
)

(instance rm355 of Room
	(properties
		picture 355
	)
	
	(method (init)
		(Load VIEW 352)
		(Load VIEW 356)
		(super init:)
		(addToPics
			add: atpComputer
			add: atpPaper
			add: atpPhone
			add: atpSign
			doit:
		)
		(if (> machineSpeed 16)
			(aFountain setCycle: Forward isExtra: 1 init:)
		)
		(aKen init:)
		(ego illegalBits: 0 posn: 290 141 init:)
		(self setScript: RoomScript)
	)
)

(instance RoomScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 214 146 self)
				(= cycles 22)
			)
			(1
				(aKen setCycle: Forward)
			)
			(2
				(Print 355 0)
				(aKen setCel: 0)
				(ego setMotion: MoveTo 269 165 self)
				(= cycles 22)
			)
			(3
				(aKen setCycle: Forward)
			)
			(4
				(Print 355 1)
				(aKen setCel: 0)
				(ego view: 356 loop: 0 cel: 0 cycleSpeed: 1 setCycle: EndLoop)
				(= cycles 33)
			)
			(5
				(ego setCycle: BegLoop)
				(= cycles 11)
			)
			(6
				(ego loop: 1 cel: 0 setCycle: EndLoop)
				(= cycles 33)
			)
			(7
				(ego setCycle: BegLoop)
				(= cycles 11)
			)
			(8
				(ego
					view: currentEgoView
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 161 165 self
				)
			)
			(9
				(ego setMotion: MoveTo 165 149 self)
			)
			(10
				(ego view: 356 loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(11
				(ego
					cycleSpeed: 1
					setLoop: 3
					cel: 0
					setCycle: EndLoop
					setMotion: MoveTo 146 154 self
				)
			)
			(12
				(Print 355 2)
				(= seconds 2)
			)
			(13
				(aKen setCycle: Forward)
				(= seconds 3)
			)
			(14
				(Print 355 3)
				(aKen viewer: tiradeCycler loop: 1)
				(= seconds 3)
			)
			(15
				(Print 355 4 #at -1 144)
				(= seconds 2)
			)
			(16
				(Print 355 5)
				(aKen loop: 2)
				(= seconds 3)
			)
			(17
				(Print 355 6)
				(AddViewToPic ego)
				(ego
					view: 355
					loop: 3
					posn: (aKen x?) (aKen y?)
					setPri: 5
					stopUpd:
				)
				(NormalActor aKen 3 352)
				(aKen
					illegalBits: 0
					viewer: 0
					posn: 30 140
					setPri: 3
					setMotion: MoveTo 30 132 self
				)
			)
			(18
				(aKen setMotion: MoveTo 40 128 self)
			)
			(19
				(Print 355 7 #at -1 144)
				(aKen setMotion: MoveTo 109 128 self)
			)
			(20
				(Print 355 8)
				(curRoom newRoom: 350)
			)
		)
	)
)

(instance aFountain of Prop
	(properties
		y 104
		x 157
		view 350
		loop 3
	)
)

(instance aKen of Actor
	(properties
		y 139
		x 33
		view 355
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setPri: 10 stopUpd:)
	)
)

(instance tiradeCycler of Code
	(method (doit)
		(if (not (Random 0 4))
			(aKen cycleSpeed: (Random 0 2))
		)
	)
)

(instance atpComputer of PicView
	(properties
		y 149
		x 33
		view 355
		loop 4
		priority 11
		signal ignrAct
	)
)

(instance atpPaper of PicView
	(properties
		y 128
		x 67
		view 355
		loop 4
		cel 1
		priority 12
		signal ignrAct
	)
)

(instance atpPhone of PicView
	(properties
		y 124
		x 57
		view 355
		loop 4
		cel 2
		priority 12
		signal ignrAct
	)
)

(instance atpSign of PicView
	(properties
		y 142
		x 63
		view 355
		loop 4
		cel 3
		priority 12
		signal ignrAct
	)
)
