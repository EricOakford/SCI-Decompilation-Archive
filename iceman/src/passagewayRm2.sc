;;; Sierra Script 1.0 - (do not remove this comment)
(script# 36)
(include sci.sh)
(use Main)
(use Intrface)
(use Game)

(public
	passagewayRm2 0
)

(instance passagewayRm2 of Rm
	(properties
		picture 36
		east 34
		west 37
	)
	
	(method (init)
		(super init:)
		(self setRegions: 314)
		(switch prevRoomNum
			(east
				(ego x: 283 y: 72 loop: 1)
			)
			(35 (ego x: 311 y: 159 loop: 1))
			(else 
				(globalSound
					number: 11
					loop: -1
					owner: theGame
					priority: 1
					play:
				)
				(ego x: 31 y: 72 loop: 0)
			)
		)
		(ego init:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room,scene]') (Print 36 0) (Print 36 1))
			((Said 'look/reactor') (Print 36 2))
			((Said 'get/reactor') (Print 36 3))
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (> (ego y?) 80) (= newRoomNumber 35))
		(super newRoom: newRoomNumber)
	)
)
