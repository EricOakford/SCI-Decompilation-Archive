;;; Sierra Script 1.0 - (do not remove this comment)
(script# 73)
(include game.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Game)

(public
	cliffsRm 0
	cliffFeat 1
)

(instance cliffsRm of Room
	(properties
		picture 73
		west 74
	)
	
	(method (init)
		(super init:)
		(self setRegions: 310 311 setFeatures: cliffFeat)
		(ego init:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room]')
				(Print 73 0)
			)
		)
	)
)

(instance cliffFeat of RFeature
	(properties
		y 126
		x -13
		nsTop 125
		nsLeft -15
		nsBottom 127
		nsRight -10
	)
)
