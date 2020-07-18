;;; Sierra Script 1.0 - (do not remove this comment)
(script# 31)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm31 0
)

(local
	egoCar
	[local1 19]
	frontDoor
	[local21 5]
)
(procedure (RoomPrint)
	(Print &rest #at -1 15)
)

(instance rm31 of Room
	(properties
		picture 31
		style HSHUTTER
	)
	
	(method (init)
		(super init:)
		(= perspective 70)
		(Load VIEW 54)
		(Load VIEW 51)
		(Load VIEW 270)
		((= frontDoor (View new:))
			view: 270
			posn: 145 105
			init:
			stopUpd:
		)
		((= egoCar (Actor new:))
			view: 54
			setStep: 3 3
			setLoop: 0
			setCel: 13
			setCycle: 0
			setMotion: 0
			posn: 30 157
			init:
			illegalBits: 0
		)
		(ego
			view: 6
			loop: 0
			cel: 0
			posn: 24 114
			priority: 7
			init:
		)
		(= keith (Actor new:))
		(keith
			view: 53
			loop: 1
			cel: 4
			posn: 208 116
			priority: 8
			setCycle: Walk
			setStep: 3 2
			init:
		)
		(self setScript: rm31Script)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance rm31Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(RoomPrint 31 0 #dispose)
				(ego setMotion: MoveTo 104 114)
				(keith setMotion: MoveTo 165 116)
				(= seconds 5)
			)
			(1
				(cls)
				(= seconds 5)
			)
			(2
				(curRoom newRoom: 60)
			)
		)
	)
)
