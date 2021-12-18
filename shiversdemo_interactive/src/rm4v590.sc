;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4590)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm4v590 0
)

(instance rm4v590 of ShiversRoom
	(properties
		picture 4200
	)
	
	(method (init)
		(if (proc951_5 6) (= picture 4270))
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 3)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(if (== n 4620) (sounds fade: 20402 0 15 8 1 0))
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 4580
	)
)

(instance efExitRight of ExitFeature
	(properties
		nsLeft 220
		nextRoom 4560
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 4620
	)
	
	(method (init)
		(self
			createPoly: 88 116 116 90 117 60 146 60 146 89 176 115
		)
		(super init: &rest)
	)
)
