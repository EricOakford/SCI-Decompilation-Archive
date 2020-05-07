;;; Sierra Script 1.0 - (do not remove this comment)
(script# HUT)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	thatchedHut 0
)

(instance thatchedHut of Room
	(properties
		picture 2
		horizon 99
		picAngle 70
	)
	
	(method (init)
		(LoadMany VIEW 2 602 702 502)
		(super init:)
		(addToPics add: chairPic init: doit:)
		(ego view: 2 loop: 5 cel: 1 posn: 227 152 init:)
		(hutDoor init:)
		(wave init: setCycle: Forward)
		(smallBikiniLady init:)
		(bikiniLady init:)
		(closeUpView init: hide:)
	)
)

(instance smallBikiniLady of Actor
	(properties
		y 85
		x 325
		yStep 1
		view 602
		signal (| ignrAct ignrHrz)
		illegalBits $0000
		xStep 2
	)
	
	(method (init)
		(super init:)
		(self setCycle: Walk setScript: smallBikiniScript)
	)
)

(instance smallBikiniScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client posn: 330 84 setMotion: MoveTo 226 84 self)
			)
			(1
				(client setMotion: MoveTo 145 79 self)
			)
			(2
				(client setLoop: 3 y: (+ (client y?) 1))
				(hutDoor startUpd: setCycle: EndLoop self)
			)
			(3
				(client xStep: 1 setMotion: MoveTo 152 76 self)
			)
			(4
				(client setPri: 1)
				(hutDoor setCycle: BegLoop self)
			)
			(5
				(hutDoor stopUpd:)
				(client dispose:)
			)
		)
	)
)

(instance hutDoor of Prop
	(properties
		y 80
		x 155
		view 2
		priority 3
		signal (| stopUpdOn fixPriOn)
	)
)

(instance chairPic of PicView
	(properties
		y 150
		x 233
		view 2
		loop 1
	)
)

(instance wave of Prop
	(properties
		y 174
		x 35
		view 502
		cycleSpeed 2
	)
)

(instance bikiniLady of Actor
	(properties
		y 140
		x -10
		view 702
	)
	
	(method (init)
		(super init:)
		(self setCycle: Walk setScript: walkOnBy)
	)
)

(instance closeUpView of View
	(properties
		y 85
		x 265
		view 702
		loop 4
		priority 15
		signal fixPriOn
	)
)

(instance walkOnBy of Script
	(properties)
	
	(method (doit)
		(cond 
			((< (client x?) 190) (ego setCel: 0))
			((< (client x?) 220) (ego setCel: 1) (closeUpView show:))
			((< (client x?) 235) (ego setCel: 2))
			((< (client x?) 260) (ego setCel: 3))
			((< (client x?) 290) (ego setCel: 4) (closeUpView hide:))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bikiniLady posn: 100 130 setMotion: MoveTo 340 190 self)
				(Print 2 0 #at 5 10 #width 185 #time 10 #dispose)
			)
			(1 (curRoom newRoom: 11))
		)
	)
)
