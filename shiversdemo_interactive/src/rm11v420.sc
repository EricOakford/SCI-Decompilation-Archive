;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11420)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm11v420 0
)

(instance rm11v420 of ShiversRoom
	(properties
		picture 11420
	)
	
	(method (init)
		(proc951_16 27)
		(efBack init: 8)
		(super init: &rest)
	)
)

(instance efBack of ExitFeature
	(properties
		nextRoom 11090
	)
	
	(method (init)
		(if (== prevRoomNum 11080) (= nextRoom 11080))
		(if (== prevRoomNum 11090) (= nextRoom 11090))
		(self createPoly: 0 0 0 143 263 143 263 0 1 0)
		(super init: &rest)
	)
)
