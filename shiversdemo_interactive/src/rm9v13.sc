;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9130)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm9v13 0
)

(instance rm9v13 of ShiversRoom
	(properties
		picture 9130
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(proc951_1 3)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9120
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9140
	)
)
