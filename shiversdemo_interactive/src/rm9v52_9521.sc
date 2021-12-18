;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9521)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm9v52 0
)

(instance rm9v52 of ShiversRoom
	(properties
		picture 9521
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitDown init: 5)
		(efExitUp init: 3)
		(if (proc951_11 6 9000)
			(proc951_9 20910)
			(sounds play: 20910 -1 34 0)
		)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(super newRoom: n &rest)
		(if
		(and (!= n 9520) (!= n 9770) (proc951_11 6 9000))
			(sounds fade: 20910 0 10 16 1 0)
		)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9550
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9530
	)
)

(instance efExitUp of ExitFeature
	(properties
		nextRoom 9770
	)
	
	(method (init)
		(self createPoly: 100 0 106 36 156 36 167 0)
		(super init: &rest)
	)
)

(instance efExitDown of ExitFeature
	(properties
		nextRoom 9520
	)
	
	(method (init)
		(self createPoly: 65 143 55 67 183 67 191 137)
		(super init: &rest)
	)
)
