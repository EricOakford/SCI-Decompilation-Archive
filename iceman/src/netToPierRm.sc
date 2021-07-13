;;; Sierra Script 1.0 - (do not remove this comment)
(script# 46)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)

(public
	netToPierRm 0
)

(instance netToPierRm of Room
	(properties
		picture 54
		north 71
		east 47
		south 47
		west 45
	)
	
	(method (init)
		(super init:)
		(self setRegions: rgScuba)
		(ego init:)
		(switch prevRoomNum
			(45
				(ego
					illegalBits: cWHITE
					loop: 0
					posn: 10 (ego y?)
					setMotion: MoveTo 325 (ego y?)
				)
			)
			(else 
				(ego
					illegalBits: cWHITE
					loop: 1
					posn: 285 100
					setMotion: MoveTo 150 100
				)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'bind,conceal,adjust,drop,park/vehicle,diver')
				(if (ego has: iDiver)
					(Print 46 0)
				else
					(Print 46 1)
				)
			)
			((Said 'look')
				(Print 46 2)
				(Print 46 3)
			)
		)
	)
)
