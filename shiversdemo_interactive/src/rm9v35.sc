;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9350)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm9v35 0
)

(instance rm9v35 of ShiversRoom
	(properties
		picture 9350
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 9)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(if (and (proc951_5 42) (== n 9730))
			(MonoOut {fade to 8})
			(sounds fade: 10908 8 15 8 0 0)
		)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9340
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9360
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 9730
	)
	
	(method (init)
		(self
			createPoly: 81 143 125 91 125 50 199 50 199 92 237 142
		)
		(super init: &rest)
	)
)
