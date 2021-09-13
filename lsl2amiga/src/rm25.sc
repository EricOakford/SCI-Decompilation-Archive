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
		(aView1
			loop: 1
			cel: 1
			setPri: 12
			ignoreActors:
			addToPic:
		)
		(aView2
			loop: 1
			cel: 0
			setPri: 12
			ignoreActors:
			addToPic:
		)
		(aWave
			setLoop: 2
			setCel: 0
			setPri: 0
			setCycle: Forward
			cycleSpeed: 3
			isExtra: 1
			init:
		)
		(aPole
			setPri: 12
			setCycle: Forward
			cycleSpeed: 1
			isExtra: 1
			init:
		)
		(NormalEgo)
		(aDoor
			setLoop: 0
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

(instance aView1 of View
	(properties
		y 83
		x 266
		view 231
	)
)

(instance aView2 of View
	(properties
		y 106
		x 248
		view 231
	)
)

(instance aWave of Prop
	(properties
		y 95
		x 319
		view 231
	)
)

(instance aPole of Prop
	(properties
		y 138
		x 176
		view 230
	)
)

(instance aDoor of AutoDoor
	(properties
		y 84
		x 185
		view 231
		msgLook {A small, discreetly lettered sign on the door says: "No hair loss too great."}
		msgLookLock 1110
		msgLocked 1130
		msgExcept 1151
		msgFunny 1174
		msgCloser 1194
	)
)
