;;; Sierra Script 1.0 - (do not remove this comment)
(script# 692)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room692 0
)

(local
	lolotte
	edgar
	heart
	minister
)
(instance weddingMarch of Sound
	(properties
		number 69
		loop 2
	)
)

(instance escort of Prop
	(properties)
	
	(method (cue)
		(self addToPic:)
	)
)

(instance Room692 of Room
	(properties
		picture 92
		style DISSOLVE
	)
	
	(method (init)
		(super init:)
		((View new:)
			view: 806
			loop: 0
			cel: 0
			posn: 118 104
			setPri: 6
			init:
			addToPic:
		)
		((View new:)
			view: 806
			loop: 0
			cel: 0
			posn: 111 111
			setPri: 7
			init:
			addToPic:
		)
		((View new:)
			view: 806
			loop: 0
			cel: 0
			posn: 103 119
			setPri: 8
			init:
			addToPic:
		)
		((Prop new:)
			view: 806
			loop: 1
			cel: 5
			posn: 118 48
			setPri: 1
			setCycle: Forward
			ignoreActors:
			init:
		)
		((Prop new:)
			view: 806
			loop: 1
			cel: 3
			posn: 111 55
			setPri: 2
			setCycle: Forward
			ignoreActors:
			init:
		)
		((Prop new:)
			view: 806
			loop: 1
			cel: 0
			posn: 103 63
			setPri: 3
			setCycle: Forward
			ignoreActors:
			init:
		)
		((View new:)
			view: 806
			loop: 0
			cel: 1
			posn: 205 104
			setPri: 6
			init:
			addToPic:
		)
		((View new:)
			view: 806
			loop: 0
			cel: 1
			posn: 212 110
			setPri: 7
			init:
			addToPic:
		)
		((View new:)
			view: 806
			loop: 0
			cel: 1
			posn: 221 119
			setPri: 8
			init:
			addToPic:
		)
		((Prop new:)
			view: 806
			loop: 1
			cel: 3
			posn: 205 48
			setPri: 1
			setCycle: Forward
			ignoreActors:
			init:
		)
		((Prop new:)
			view: 806
			loop: 1
			cel: 2
			posn: 212 54
			setPri: 2
			setCycle: Forward
			ignoreActors:
			init:
		)
		((Prop new:)
			view: 806
			loop: 1
			cel: 0
			posn: 221 63
			setPri: 3
			setCycle: Forward
			ignoreActors:
			init:
		)
		((View new:)
			view: 804
			loop: 4
			cel: 0
			posn: 59 141
			setPri: 10
			init:
			addToPic:
		)
		((View new:)
			view: 804
			loop: 4
			cel: 0
			posn: 56 149
			setPri: 11
			init:
			addToPic:
		)
		((View new:)
			view: 804
			loop: 4
			cel: 0
			posn: 49 158
			setPri: 12
			init:
			addToPic:
		)
		((View new:)
			view: 804
			loop: 4
			cel: 1
			posn: 267 143
			setPri: 10
			init:
			addToPic:
		)
		((View new:)
			view: 804
			loop: 4
			cel: 1
			posn: 271 151
			setPri: 11
			init:
			addToPic:
		)
		((View new:)
			view: 804
			loop: 4
			cel: 1
			posn: 276 159
			setPri: 12
			init:
			addToPic:
		)
		(User canControl: 0 canInput: 0)
		((= lolotte (Prop new:))
			view: 807
			loop: 3
			cel: 0
			posn: 162 93
			setPri: 5
			init:
		)
		(ego
			view: 808
			loop: 0
			cel: 0
			illegalBits: 0
			ignoreActors:
			posn: 10 131
			setScript: egoActions
			init:
		)
		((= edgar (Actor new:))
			view: 810
			loop: 0
			cel: 0
			posn: 175 131
			ignoreActors:
			init:
			stopUpd:
		)
		((= minister (Prop new:))
			view: 804
			loop: 0
			cel: 0
			ignoreActors:
			posn: 184 111
			init:
			stopUpd:
		)
		(weddingMarch play:)
	)
)

(instance egoActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE canInput: FALSE)
				(ego
					xStep: 2
					setMotion: MoveTo 105 132 self
					moveSpeed: 1
					cycleSpeed: 1
				)
			)
			(1
				(ego setLoop: 2 setMotion: MoveTo 152 132 self)
				(escort
					view: 808
					setLoop: 1
					ignoreActors:
					posn: 117 128
					setCycle: EndLoop escort
					init:
				)
			)
			(2
				(ego setLoop: 3 cel: 0 cycleSpeed: 0 setCycle: EndLoop self)
				(edgar setCycle: EndLoop)
			)
			(3
				(lolotte cel: 0 setCycle: EndLoop self)
			)
			(4
				(lolotte cel: 0 setCycle: EndLoop self)
			)
			(5
				(minister cycleSpeed: 2 setCycle: EndLoop self)
			)
			(6
				(minister stopUpd:)
				(= timedMessage (Print 692 0 #at -1 10 #dispose))
				(= seconds 12)
			)
			(7
				(cls)
				(edgar hide:)
				(ego view: 810 setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(8
				(= heart (Prop new:))
				(heart
					view: 810
					setLoop: 2
					ignoreActors:
					cel: 0
					setPri: 15
					posn: 175 85
					setCycle: EndLoop self
					init:
				)
			)
			(9
				(heart dispose:)
				(ego setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(10 (= seconds 3))
			(11
				(ego setLoop: 5)
				(edgar
					setLoop: 4
					posn: 181 126
					show:
					cel: 0
					setPri: 8
					setCycle: EndLoop self
				)
			)
			(12 (= seconds 5))
			(13
				(Print 692 1 #at -1 10 #time 15)
				(cls)
				(= seconds 4)
			)
			(14
				(Print 692 2 #at -1 10 #time 15)
				(cls)
				(User canControl: TRUE canInput: TRUE)
				(= dead TRUE)
			)
		)
	)
)
