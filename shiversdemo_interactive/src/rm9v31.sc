;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9310)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm9v31 0
)

(instance rm9v31 of ShiversRoom
	(properties
		picture 9310
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(if (and (== prevRoomNum 9680) (proc951_5 42))
			(proc951_7 10908)
			(proc951_9 10908)
			(sounds play: 10908 -1 8 0)
		)
		(super init: &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9300
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9320
	)
)
