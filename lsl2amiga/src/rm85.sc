;;; Sierra Script 1.0 - (do not remove this comment)
(script# 85)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm85 0
)

(local
	[local0 3]
)
(instance rm85 of Room
	(properties
		picture 95
		horizon 1
	)
	
	(method (init)
		(Load VIEW 829)
		(Load VIEW 197)
		(super init:)
		(addToPics add: aView1 aView2 aView3 aView4 aView5 doit:)
		(aDoor
			ignoreActors:
			illegalBits: 0
			setLoop: 6
			setStep: 1 1
			setPri: 14
			stopUpd:
			init:
		)
		(= currentStatus egoWONGAME)
		(ego
			view: 197
			setLoop: 0
			posn: 48 -2
			illegalBits: 0
			setStep: 5 5
			ignoreActors:
			setCycle: Forward
			init:
		)
		(HandsOff)
		(self setScript: rm85Script)
	)
)

(instance rm85Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setPri: -1 setMotion: MoveTo 149 144 self)
			)
			(1
				(ego setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(2
				(ego view: 100 loop: 2 setLoop: -1)
				(= cycles 12)
			)
			(3
				(Print 85 0 #draw)
				(Print 85 1)
				(ego
					setCycle: Walk
					setStep: 3 2
					setMotion: MoveTo 144 183 self
				)
			)
			(4
				(Print 85 2)
				(ego setMotion: MoveTo 35 183 self)
			)
			(5
				(ego setMotion: MoveTo 32 153 self)
			)
			(6
				(aDoor setMotion: MoveTo 35 137 self)
			)
			(7
				(Print 85 3)
				(= endGameState 102)
				(curRoom newRoom: 92)
			)
		)
	)
)

(instance aView1 of PicView
	(properties
		y 173
		x 35
		view 829
		loop 8
		priority 11
		signal ignrAct
	)
)

(instance aView2 of PicView
	(properties
		y 151
		x 65
		view 829
		loop 5
		cel 1
		priority 13
	)
)

(instance aView3 of PicView
	(properties
		y 68
		x 33
		view 829
		loop 7
		priority 1
	)
)

(instance aView4 of PicView
	(properties
		y 68
		x 58
		view 829
		loop 7
		priority 1
	)
)

(instance aView5 of PicView
	(properties
		y 214
		x 105
		view 829
		loop 9
		priority 5
	)
)

(instance aDoor of Actor
	(properties
		y 137
		x 9
		view 829
	)
)
