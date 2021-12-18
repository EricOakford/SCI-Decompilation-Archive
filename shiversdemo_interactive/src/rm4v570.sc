;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4570)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm4v570 0
)

(instance rm4v570 of ShiversRoom
	(properties
		picture 4040
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(if (== prevRoomNum 4600)
			(proc951_7 20402)
			(proc951_9 20402)
			(sounds play: 20402 -1 0 0)
			(sounds fade: 20402 98 15 15 0 0)
		)
		(super init: &rest)
		(if (proc951_5 6)
			(proc951_4 6)
			(sounds play: 10405 0 30 0)
		)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 4560
	)
	
	(method (init)
		(self createPoly: 0 0 50 0 50 142 0 142 0 0)
		(super init: &rest)
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 4580
	)
	
	(method (init)
		(self createPoly: 210 0 262 0 262 142 210 142 210 0)
		(super init: &rest)
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 4540
	)
	
	(method (init)
		(self
			createPoly: 176 112 86 112 117 77 117 61 145 61 145 78
		)
		(super init: &rest)
	)
)
