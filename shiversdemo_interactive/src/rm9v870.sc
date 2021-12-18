;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9870)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm9v870 0
)

(instance rm9v870 of ShiversRoom
	(properties
		picture 9870
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 9)
		(super init: &rest)
		(if (and (== prevRoomNum 9630) (proc951_5 43))
			(sounds play: 11503 0 82 0)
			(proc951_4 43)
		)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9620
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9630
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 9910
	)
	
	(method (init)
		(self createPoly: 89 93 87 55 97 9 186 9 187 103 108 104)
		(super init: &rest)
	)
)
