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
	local0
	aDoor
	[local2 2]
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
		((View new:)
			view: 829
			ignoreActors:
			loop: 8
			posn: 35 173
			setPri: 11
			addToPic:
		)
		((View new:)
			view: 829
			loop: 5
			cel: 1
			posn: 65 151
			setPri: 13
			addToPic:
		)
		((View new:)
			view: 829
			loop: 7
			posn: 33 68
			setPri: 1
			addToPic:
		)
		((View new:)
			view: 829
			loop: 7
			posn: 58 68
			setPri: 1
			addToPic:
		)
		((View new:)
			view: 829
			loop: 9
			setPri: 5
			posn: 214 105
			addToPic:
		)
		((= aDoor (Actor new:))
			view: 829
			ignoreActors:
			illegalBits: 0
			setLoop: 6
			setStep: 1 1
			setPri: 14
			posn: 9 137
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
	(properties)
	
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
