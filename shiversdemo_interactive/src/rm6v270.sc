;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6270)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm6v270 0
)

(instance rm6v270 of ShiversRoom
	(properties
		picture 6270
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 3)
		(super init: &rest)
		(if (>= prevRoomNum 7000)
			(if (proc951_11 2 6000)
				(proc951_7 20603)
				(proc951_9 20603)
				(sounds play: 20603 -1 0 0)
				(sounds fade: 20603 32 15 8 0 0)
				(sounds fadeChain:)
			else
				(proc951_7 20601)
				(proc951_9 20601)
				(sounds play: 20601 -1 0 0)
				(sounds fade: 20601 10 15 5 0 0)
			)
			(sounds play: 10712 0 40 0)
			(proc951_4 43)
		)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 6500
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 6040
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 6030
	)
	
	(method (init)
		(self
			createPoly: 56 127 57 38 129 38 129 48 191 48 191 130
		)
		(super init: &rest)
	)
)
