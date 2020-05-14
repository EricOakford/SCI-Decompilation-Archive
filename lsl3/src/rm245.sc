;;; Sierra Script 1.0 - (do not remove this comment)
(script# 245)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use System)

(public
	rm245 0
)

(local
	triedToEnterMaze
)
(instance rm245 of Room
	(properties
		picture 245
		west 240
	)
	
	(method (init)
		(super init:)
		(self setRegions: 41 setScript: RoomScript)
		(if (== prevRoomNum 500)
			(ego posn: 126 73 loop: 2)
		else
			(ego posn: 5 172 loop: 0)
		)
		(NormalEgo)
		(ego init:)
		(if currentStatus (ego observeControl: 4 8 16))
	)
	
	(method (newRoom newRoomNumber)
		(cls)
		(super newRoom: newRoomNumber)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(if (not playingAsPatti)
			(ego observeControl: cYELLOW)
			(if (& (ego onControl:) cMAGENTA)
				(if (not triedToEnterMaze) (= triedToEnterMaze TRUE) (Print 245 0))
			else
				(= triedToEnterMaze FALSE)
			)
		)
		(if (== currentStatus egoNORMAL)
			(cond 
				((& (ego onControl:) cBLUE) (curRoom newRoom: 500))
				((& (ego onControl:) cRED) (NotifyScript 41 9 300))
				((& (ego onControl:) cGREEN) (NotifyScript 41 0 300))
				((or (== 2 (ego edgeHit?)) (& (ego onControl:) cCYAN)) (ego x: (+ (ego x?) 8))
					(NotifyScript 41 8 300)
				)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(cond 
				((Said '/ball,boulder,boob') (Print 245 1))
				((Said '/bamboo') (Print 245 2))
				((Said '[/area]') (Print 245 3))
			)
		)
	)
)
