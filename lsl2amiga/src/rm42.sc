;;; Sierra Script 1.0 - (do not remove this comment)
(script# 42)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm42 0
)

(instance rm42 of Room
	(properties
		picture 42
		horizon 5
		east 47
		south 40
		west 41
	)
	
	(method (init)
		(Load VIEW 407)
		(super init:)
		(addToPics add: aUmbrella1 aUmbrella2 aUmbrella3 doit:)
		(self setRegions: 401 setScript: rm42Script)
		(if (or (== prevRoomNum 138) (== prevRoomNum 10))
			(Load VIEW 144)
			(Load VIEW 408)
			(Load VIEW 409)
			(ego
				view: 144
				setLoop: 0
				illegalBits: 0
				ignoreActors:
				cycleSpeed: 1
				moveSpeed: 1
				setStep: 1 1
				posn: -8 54
				setCycle: Forward
				init:
				;lose any no-longer-useful items
				put: iSwimsuit -1
				put: iSunscreen -1
				put: iWig -1
				put: iSewingKit -1
				put: iFruit -1
			)
			(aCreep
				illegalBits: 0
				setCycle: Walk
				init:
			)
			(HandsOff)
			(rm42Script changeState: 1)
			(= currentStatus egoATSEA)
			(curRoom west: 0)
		else
			(aSport
				illegalBits: 0
				setCycle: Forward
				hide:
				setScript: sportsScript
				init:
			)
			(if (or (== prevRoomNum 0) (== prevRoomNum 40))
				(ego posn: 155 185)
			)
			(NormalEgo)
			(ego init:)
		)
	)
)

(instance rm42Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(ego setMotion: MoveTo 3 61 self)
			)
			(2
				(Print 42 2)
				(Print 42 3)
				(= seconds 3)
			)
			(3
				(ego setMotion: MoveTo 70 95 self)
			)
			(4
				(ego
					cycleSpeed: 2
					setLoop: 1
					cel: 0
					posn: 72 96
					setStep: 3 2
					setCycle: EndLoop self
				)
			)
			(5
				(ego setLoop: 2 posn: 84 111 cel: 0 setCycle: EndLoop self)
			)
			(6
				(ego
					cycleSpeed: 1
					setLoop: 3
					posn: 92 124
					cel: 0
					setCycle: EndLoop self
				)
			)
			(7
				(= seconds 5)
			)
			(8
				(aCreep setMotion: MoveTo 84 150 self)
				(= cycles 15)
			)
			(9
				(Print 42 4)
			)
			(10
				(aCreep view: 409 cel: 0 setCycle: Forward)
				(= cycles 18)
			)
			(11
				(aCreep
					view: 408
					setCycle: Walk
					setMotion: MoveTo 333 120 self
				)
				(= cycles 25)
			)
			(12
				(if (> filthLevel 10)
					(Print 42 5 #at -1 130)
				)
			)
			(13
				(aCreep hide:)
				(Print 42 6 #at -1 15 #width 280 #draw)
				(ego
					cycleSpeed: 1
					setLoop: 4
					cel: 0
					posn: 101 150
					setCycle: EndLoop self
				)
			)
			(14
				(Print 42 7 #at -1 20 #draw)
				(Print 42 8 #at -1 130)
				(= seconds 3)
			)
			(15
				(ego setLoop: 5 cel: 0 setCycle: EndLoop self)
			)
			(16
				(= seconds 3)
			)
			(17
				(Print 42 9 #at -1 130)
				(= seconds 3)
			)
			(18
				(Print 42 10)
				(Print 42 11)
				(NormalEgo loopS)
				(rm42 west: 41)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/umbrella')
				(Print 42 0)
			)
			(if (Said '[/airport,palm,bush]')
				(Print 42 1)
			)
		)
	)
)

(instance sportsScript of Script
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(if (!= prevRoomNum 138)
					(= seconds (Random 3 5))
				)
			)
			(1
				(aSport
					setLoop: 0
					cel: 0
					posn: -30 49
					setStep: 5 2
					cycleSpeed: 3
					show:
					setMotion: MoveTo 360 50 self
				)
			)
			(2
				(aSport hide:)
				(= seconds (Random 2 5))
			)
			(3
				(aSport
					setLoop: 1
					posn: -24 107
					setStep: 3 1
					cycleSpeed: 4
					show:
					setMotion: MoveTo 343 64 self
				)
			)
			(4
				(aSport hide:)
				(= seconds (Random 2 5))
			)
			(5
				(aSport
					setLoop: 2
					posn: -29 57
					setStep: 1 1
					cycleSpeed: 0
					moveSpeed: 1
					show:
					setMotion: MoveTo 337 58 self
				)
			)
			(6
				(aSport hide:)
				(= seconds (Random 2 5))
			)
			(7
				(aSport
					setLoop: 3
					posn: -10 47
					setStep: 3 1
					cycleSpeed: 4
					moveSpeed: 0
					show:
					setMotion: MoveTo 333 48 self
				)
			)
			(8
				(aSport hide:)
				(= seconds (Random 2 5))
			)
			(9
				(aSport
					setLoop: 4
					posn: -35 79
					setStep: 2 1
					cycleSpeed: 1
					show:
					setMotion: MoveTo 334 55 self
				)
			)
			(10
				(aSport hide:)
				(= seconds (Random 2 5))
			)
			(11
				(aSport
					setLoop: 5
					posn: 358 76
					setStep: 3 1
					cycleSpeed: 4
					show:
					setMotion: MoveTo -30 113 self
				)
			)
			(12
				(aSport hide:)
				(= seconds (Random 2 5))
			)
			(13
				(aSport
					setLoop: 6
					posn: -23 73
					setStep: 5 2
					cycleSpeed: 3
					show:
					setMotion: MoveTo 377 48 self
				)
			)
			(14
				(aSport hide:)
				(self changeState: 0)
			)
		)
	)
)

(instance aUmbrella1 of PicView
	(properties
		y 172
		x 76
		view 407
		loop 7
		cel 1
		priority 13
		signal ignrAct
	)
)

(instance aUmbrella2 of PicView
	(properties
		y 159
		x 206
		view 407
		loop 7
		priority 12
		signal ignrAct
	)
)

(instance aUmbrella3 of PicView
	(properties
		y 174
		x 294
		view 407
		loop 7
		cel 2
		priority 13
		signal ignrAct
	)
)

(instance aCreep of Actor
	(properties
		y 149
		x -12
		view 408
		signal ignrAct
	)
)

(instance aSport of Actor
	(properties
		y 79
		x -40
		view 407
		priority 1
		signal ignrAct
	)
)
