;;; Sierra Script 1.0 - (do not remove this comment)
(script# 535)
(include game.sh)
(use Main)
(use Intrface)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm535 0
)

(local
	[msgBuf 77]
	[titleBuf 22]
)
(instance rm535 of Room
	(properties
		picture 535
		horizon 1
	)
	
	(method (init)
		(Load SOUND 4)
		(Load SOUND 536)
		(music number: 535 loop: -1 play:)
		(HandsOff)
		(if (Btst fFlag15)
			(= currentEgoView 803)
		)
		(super init:)
		(aBird1 init:)
		(aBird2 init:)
		(aBird3 init:)
		(self setScript: RoomScript)
		(ego
			view: 536
			loop: 0
			cel: 0
			ignoreActors:
			setStep: 1 1
			moveSpeed: 1
			cycleSpeed: 1
			posn: 49 26
			init:
		)
	)
)

(instance RoomScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 2)
			)
			(1
				(if (== currentEgoView 803)
					(Print 535 0 #at 10 -1 #width 290)
					(Print 535 1 #at -1 144)
				else
					(Print 535 2 #at 10 -1 #width 290)
				)
				(ego setMotion: MoveTo 280 26)
				(= cycles (Random 55 111))
			)
			(2
				(if (!= currentEgoView 803)
					(Print 535 3)
				)
				(= cycles (Random 55 111))
			)
			(3
				(if (!= currentEgoView 803)
					(if (>= filthLevel 3)
						(Print 535 4)
						(Print 535 5 #at -1 144)
					else
						(Print 535 6)
					)
				else
					(Print 535 7)
				)
				(= cycles (Random 55 111))
			)
			(4
				(if (== currentEgoView 803)
					(ego setMotion: MoveTo 280 26 self)
				else
					(self changeState: 7)
				)
			)
			(5
				(music number: 536 loop: 1 play:)
				(Print 535 8)
				(Print 535 9)
				(= cycles 33)
			)
			(6
				(= currentStatus egoNORMAL)
				(curRoom newRoom: 530)
			)
			(7
				(music number: 4 loop: 1 play:)
				(ego
					moveSpeed: 0
					cycleSpeed: 0
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(8
				(Print 535 10 #at 10 5 #dispose)
				(ego setMotion: theJump)
			)
			(9
				(theGame setScript: (ScriptID DYING))
				((ScriptID DYING)
					caller: 814
					register: (Format @msgBuf 535 11)
					next: (Format @titleBuf 535 12)
				)
			)
		)
	)
)

(instance aBird1 of Actor
	(properties
		y 19
		x 13
		view 535
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setScript: Bird1Script setCycle: Walk)
	)
)

(instance Bird1Script of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Random 0 2))
					(= seconds (Random 3 7))
				)
			)
			(1
				(aBird1 setMotion: MoveTo 333 19 self)
			)
		)
	)
)

(instance aBird2 of Actor
	(properties
		y 138
		x 274
		view 535
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setScript: Bird2Script setCycle: Walk)
	)
)

(instance Bird2Script of Script	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Random 0 3))
					(= seconds (Random 1 3))
				)
			)
			(1
				(aBird2 setMotion: MoveTo -14 138 self)
			)
		)
	)
)

(instance aBird3 of Actor
	(properties
		view 535
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			posn: (Random 70 234) 23
			setScript: Bird3Script
			setCycle: Walk
		)
	)
)

(instance Bird3Script of Script
	(method (doit)
		(super doit:)
		(if (and (== state 0) (> 11 (client distanceTo: ego)))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(aBird3 setMotion: MoveTo 335 155 self)
			)
		)
	)
)

(instance theJump of Jump
	(method (init)
		(super init: ego RoomScript)
		(self yStep: 5 y: 300)
	)
)
