;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9850)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm9v850 0
)

(instance rm9v850 of ShiversRoom
	(properties
		picture 9850
	)
	
	(method (init)
		(if (proc951_5 43)
			(sounds play: 10920 0 42 0)
			(proc951_4 43)
		)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efDoor init: 3)
		(efSecret init: 3)
		(efMasks init: 3)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(if
			(and
				(!= n 9390)
				(!= n 9380)
				(proc951_5 42)
				(proc951_11 0 9000)
			)
			(sounds fade: 20908 0 10 16 1 0)
			(sounds stop: 20903)
			(proc951_9 20903)
			(sounds play: 20903 -1 0 0)
			(sounds fade: 20903 42 10 16 0 0)
		)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9380
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9390
	)
)

(instance efDoor of ExitFeature
	(properties
		nextRoom 9470
	)
	
	(method (init)
		(self createPoly: 55 75 54 13 83 3 82 78)
		(super init: &rest)
	)
)

(instance efSecret of ExitFeature
	(properties
		nextRoom 9420
	)
	
	(method (init)
		(self createPoly: 103 82 100 47 138 46 137 86)
		(super init: &rest)
	)
)

(instance efMasks of ExitFeature
	(properties
		nextRoom 9460
	)
	
	(method (init)
		(self createPoly: 39 33 21 33 21 76 52 74 50 31)
		(super init: &rest)
	)
)
