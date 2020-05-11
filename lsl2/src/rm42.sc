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

(local
	sports
	guy
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
		((View new:)
			view: 407
			loop: 7
			cel: 1
			posn: 76 172
			setPri: 13
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 407
			loop: 7
			cel: 0
			posn: 206 159
			setPri: 12
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 407
			loop: 7
			cel: 2
			posn: 294 174
			setPri: 13
			ignoreActors:
			addToPic:
		)
		(self setRegions: BEACH setScript: rm42Script)
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
				put: 5 -1
				put: 9 -1
				put: 14 -1
				put: 12 -1
				put: 11 -1
			)
			((= guy (Actor new:))
				view: 408
				ignoreActors:
				illegalBits: 0
				setCycle: Walk
				posn: -12 149
				init:
			)
			(HandsOff)
			(rm42Script changeState: 1)
			(= currentStatus egoAtSea)
			(curRoom west: 0)
		else
			((= sports (Actor new:))
				view: 407
				setCycle: Forward
				setPri: 1
				posn: -40 79
				illegalBits: 0
				ignoreActors:
				init:
				hide:
				setScript: sportsScript
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
	(properties)
	
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
			(7 (= seconds 5))
			(8
				(guy setMotion: MoveTo 84 150 self)
				(= cycles 15)
			)
			(9 (Print 42 4))
			(10
				(guy view: 409 cel: 0 setCycle: Forward)
				(= cycles 18)
			)
			(11
				(guy
					view: 408
					setCycle: Walk
					setMotion: MoveTo 333 120 self
				)
				(= cycles 25)
			)
			(12
				(if (> filthLevel 10) (Print 42 5 #at -1 152))
			)
			(13
				(guy dispose:)
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
				(Print 42 8 #at -1 152)
				(= seconds 3)
			)
			(15
				(ego setLoop: 5 cel: 0 setCycle: EndLoop self)
			)
			(16 (= seconds 3))
			(17
				(Print 42 9 #at -1 152)
				(= seconds 3)
			)
			(18
				(Print 42 10)
				(Print 42 11)
				(NormalEgo 2)
				(rm42 west: 41)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/umbrella') (Print 42 0))
			(if (Said '[/airport,palm,bush]') (Print 42 1))
		)
	)
)

(instance sportsScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(if (!= prevRoomNum 138) (= seconds (Random 3 5)))
			)
			(1
				(sports
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
				(sports hide:)
				(= seconds (Random 2 5))
			)
			(3
				(sports
					setLoop: 1
					posn: -24 107
					setStep: 3 1
					cycleSpeed: 4
					show:
					setMotion: MoveTo 343 64 self
				)
			)
			(4
				(sports hide:)
				(= seconds (Random 2 5))
			)
			(5
				(sports
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
				(sports hide:)
				(= seconds (Random 2 5))
			)
			(7
				(sports
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
				(sports hide:)
				(= seconds (Random 2 5))
			)
			(9
				(sports
					setLoop: 4
					posn: -35 79
					setStep: 2 1
					cycleSpeed: 1
					show:
					setMotion: MoveTo 334 55 self
				)
			)
			(10
				(sports hide:)
				(= seconds (Random 2 5))
			)
			(11
				(sports
					setLoop: 5
					posn: 358 76
					setStep: 3 1
					cycleSpeed: 4
					show:
					setMotion: MoveTo -30 113 self
				)
			)
			(12
				(sports hide:)
				(= seconds (Random 2 5))
			)
			(13
				(sports
					setLoop: 6
					posn: -23 73
					setStep: 5 2
					cycleSpeed: 3
					show:
					setMotion: MoveTo 377 48 self
				)
			)
			(14
				(sports hide:)
				(self changeState: 0)
			)
		)
	)
)
