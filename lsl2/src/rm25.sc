;;; Sierra Script 1.0 - (do not remove this comment)
(script# 25)
(include game.sh)
(use Main)
(use Door)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm25 0
)

(local
	aDoor
	aPole
	aWave
)
(instance rm25 of Room
	(properties
		picture 25
		horizon 1
		east 26
		west 24
	)
	
	(method (init)
		(Load VIEW 231)
		(Load VIEW 230)
		(super init:)
		((View new:)
			view: 231
			loop: 1
			cel: 1
			posn: 266 83
			setPri: 12
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 231
			loop: 1
			cel: 0
			posn: 248 106
			setPri: 12
			ignoreActors:
			addToPic:
		)
		((= aWave (Prop new:))
			view: 231
			setLoop: 2
			setCel: 0
			setPri: 0
			posn: 319 95
			setCycle: Forward
			cycleSpeed: 3
			isExtra: TRUE
			init:
		)
		((= aPole (Prop new:))
			view: 230
			setPri: 12
			posn: 176 138
			setCycle: Forward
			cycleSpeed: 1
			isExtra: TRUE
			init:
		)
		(NormalEgo)
		((= aDoor (AutoDoor new:))
			view: 231
			setLoop: 0
			posn: 185 84
			setPri: 4
			entranceTo: 125
			msgLook:
				{A small, discreetly lettered sign on the door says: "No hair loss too great."}
			msgFunny: {"Come in. It's open!"}
			msgCloser: {Just walk closer.}
			init:
		)
		(cond 
			((== prevRoomNum 24)
				(ego posn: 2 168)
			)
			((== prevRoomNum 26)
				(ego posn: 318 134)
			)
			((== prevRoomNum 125)
				(ego posn: 186 124)
			)
			(else
				(ego posn: 245 120)
			)
		)
		(ego init:)
		(self setRegions: CITY setScript: rm25Script)
	)
)

(instance rm25Script of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cCYAN)
			(curRoom newRoom: 21)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aWave cel: 0 setCycle: EndLoop self)
			)
			(1
				(= cycles (Random 6 10))
			)
			(2
				(self changeState: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/lagoon,beach,fluid,lagoon')
				(Print 25 0)
			)
			(if (Said '[/building,(building<man),building,airport]')
				(Print 25 1)
				(Print 25 2)
				(Print 25 3)
			)
		)
	)
)
