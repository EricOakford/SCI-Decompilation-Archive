;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9800)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm9v800 0
)

(instance rm9v800 of ShiversRoom
	(properties
		picture 9810
	)
	
	(method (init)
		(proc951_16 23)
		(efBack init: 8)
		(super init: &rest)
	)
)

(instance efBack of ExitFeature
	(properties
		nextRoom 9740
	)
	
	(method (init)
		(if (== prevRoomNum 9710) (self nextRoom: 9710))
		(self createPoly: 0 0 0 143 263 143 263 0 1 0)
		(super init: &rest)
	)
)
