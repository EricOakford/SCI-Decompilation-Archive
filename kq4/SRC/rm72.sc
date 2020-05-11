;;; Sierra Script 1.0 - (do not remove this comment)
(script# 72)
(include game.sh)
(use Main)
(use Game)

(public
	Room72 0
)

(instance Room72 of Room
	(properties
		picture 72
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(self setRegions: TROLL_CAVE)
		(super init:)
		(= isIndoors TRUE)
		(= south 75)
		(ego view: 904 xStep: 4 yStep: 1)
		(if (== prevRoomNum 75)
			(ego posn: 160 172 init:)
		else
			(ego posn: 85 94 init:)
		)
		(ego priority: (CoordPri (ego y?)))
		(NotifyScript TROLL_CAVE 1)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: 0) $0040) (= newRoomNum 71))
	)
	
	(method (dispose)
		(super dispose:)
	)
)
