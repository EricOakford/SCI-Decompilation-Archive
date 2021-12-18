;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9430)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm9v43 0
)

(instance rm9v43 of ShiversRoom
	(properties
		picture 9430
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 9)
		(if (and (proc951_5 42) (== prevRoomNum 9850))
			(MonoOut {fade to 26})
			(sounds fade: 10908 26 15 8 0 0)
		)
		(if (proc951_5 43)
			(sounds play: 10930 0 82 0)
			(proc951_4 43)
		)
		(super init: &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9400
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9420
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 9460
	)
	
	(method (init)
		(self createPoly: 81 143 81 2 203 2 203 143)
		(super init: &rest)
	)
)
