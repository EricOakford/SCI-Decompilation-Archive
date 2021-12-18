;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4600)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm4v600 0
)

(instance rm4v600 of ShiversRoom
	(properties
		picture 4010
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 6)
		(efExitForward init: 3)
		(super init: &rest)
		(if (>= prevRoomNum 5000) (sounds play: 10505 0 30 0))
	)
	
	(method (newRoom n)
		(proc951_1 2)
		(if (== n 4570)
			(sounds stop: 20402)
			(sounds fade: 10403 0 15 20 1 0)
			(proc951_10 10403)
		)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 4630
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 4620
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 4570
	)
	
	(method (init)
		(self
			createPoly: 201 134 57 134 83 107 83 20 166 20 166 100
		)
		(super init: &rest)
	)
)
