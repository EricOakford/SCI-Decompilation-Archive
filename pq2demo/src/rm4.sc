;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm4 0
)

(procedure (CaptainSays)
	(Print &rest #at -1 24)
)

(instance aTimer of Timer)

(instance phone of Prop)

(instance captain of Prop)

(instance blab of Prop)

(instance rambo of View)

(instance carKey of View)

(instance rm4 of Room
	(properties
		picture 4
		style IRISIN
	)
	
	(method (init)
		(super init:)
		(Load VIEW 1)
		(Load VIEW 65)
		(Load VIEW 62)
		(NormalEgo)
		(self setScript: rm4Script)
		(ego view: 1)
		(ego posn: 111 144 loop: 1 init:)
	)
	
	(method (dispose)
		(rm4Script dispose:)
		(aTimer dispose: delete:)
		(super dispose:)
	)
)

(instance rm4Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(phone
					view: 3
					setLoop: 1
					setCel: 0
					posn: 172 116
					setPri: 9
					init:
					stopUpd:
				)
				(captain
					view: 65
					posn: 53 134
					loop: 3
					cel: 0
					setPri: 9
					init:
				)
				(blab
					view: 65
					loop: 1
					posn: 48 112
					setPri: 12
					setCycle: Forward
					cycleSpeed: 2
					init:
				)
				((= keith (View new:))
					view: 62
					posn: 213 134
					loop: 0
					cel: 0
					init:
					stopUpd:
				)
				(rambo
					view: 65
					posn: 111 110
					loop: 5
					cel: 0
					setPri: 7
					init:
					addToPic:
				)
				(carKey
					view: 65
					posn: 141 110
					loop: 5
					cel: 2
					init:
					stopUpd:
				)
				((View new:)
					view: 65
					posn: 183 135
					loop: 6
					cel: 1
					init:
					ignoreActors:
					addToPic:
				)
				(self cue:)
			)
			(1
				(aTimer setReal: self 3)
			)
			(2
				(ego loop: 1)
				(HandsOff)
				(captain setCycle: EndLoop self)
			)
			(3
				(CaptainSays 4 0 #dispose)
				(= seconds 8)
			)
			(4
				(cls)
				(CaptainSays 4 1 #dispose)
				(captain setCycle: BegLoop)
				(= seconds 8)
			)
			(5
				(cls)
				(ego loop: 1)
				(captain setCycle: EndLoop)
				(= seconds 2)
			)
			(6
				(CaptainSays 4 2 #dispose)
				(captain setCycle: BegLoop)
				(= seconds 7)
			)
			(7
				(cls)
				(curRoom newRoom: 31)
			)
		)
	)
)
