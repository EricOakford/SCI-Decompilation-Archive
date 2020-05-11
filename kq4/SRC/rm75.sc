;;; Sierra Script 1.0 - (do not remove this comment)
(script# 75)
(include game.sh)
(use Main)
(use Game)

(public
	Room75 0
)

(instance Room75 of Room
	(properties
		picture 75
		style $0010
		horizon 65
		north 72
	)
	
	(method (init)
		(self setRegions: TROLL_CAVE)
		(super init:)
		(= isIndoors TRUE)
		(= west 74)
		(= east 76)
		(ego view: 904 xStep: 4 yStep: 1)
		(switch prevRoomNum
			(72 (ego posn: 132 83 init:))
			(74 (ego posn: 10 167 init:))
			(else 
				(ego posn: 307 163 init:)
			)
		)
		(ego priority: (CoordPri (ego y?)))
		(NotifyScript TROLL_CAVE 1)
	)
)
