;;; Sierra Script 1.0 - (do not remove this comment)
(script# 59)
(include sci.sh)
(use Main)
(use Intrface)
(use Game)

(public
	inCave1Rm 0
)

(instance inCave1Rm of Rm
	(properties
		picture 59
		south 56
	)
	
	(method (init)
		(super init:)
		(ego init:)
		(self setRegions: 304)
		(if (== prevRoomNum south) (ego posn: 143 133 loop: 3))
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room]')
				(if ((ScriptID 304 0) notify: 0)
					(Print 59 0)
					(Print 59 1)
				else
					(Print 59 2)
				)
			)
		)
	)
)
