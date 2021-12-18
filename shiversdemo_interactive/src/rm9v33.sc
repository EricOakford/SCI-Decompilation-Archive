;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9330)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm9v33 0
)

(instance rm9v33 of ShiversRoom
	(properties
		picture 9330
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 9)
		(efExitUp init: 4)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(if (and (proc951_5 42) (== n 9690))
			(sounds fade: 10908 0 20 3 1 0)
			(MonoOut {fade to 0})
		)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9320
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9300
	)
)

(instance efExitUp of ExitFeature
	(properties
		nextRoom 9331
	)
	
	(method (init)
		(self
			createPoly: 20 0 243 0 243 31 178 31 178 0 90 0 90 31 20 31
		)
		(super init: &rest)
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 9690
	)
	
	(method (init)
		(self
			createPoly: 90 0 90 31 97 54 73 82 187 81 167 57 178 31 178 0
		)
		(super init: &rest)
	)
)
