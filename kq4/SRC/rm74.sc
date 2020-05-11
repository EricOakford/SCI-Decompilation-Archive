;;; Sierra Script 1.0 - (do not remove this comment)
(script# 74)
(include game.sh)
(use Main)
(use Game)

(public
	Room74 0
)

(instance Room74 of Room
	(properties
		picture 74
		style (| BLACKOUT IRISOUT)
		horizon 65
		north 71
	)
	
	(method (init)
		(super init:)
		(self setRegions: TROLL_CAVE)
		(= isIndoors TRUE)
		(= east 75)
		(ego view: 904 xStep: 4 yStep: 1)
		(switch prevRoomNum
			(75 (ego posn: 295 167 init:))
			(else  (ego posn: 109 84 init:))
		)
		(ego priority: (CoordPri (ego y?)))
		(NotifyScript TROLL_CAVE 1)
	)
)
