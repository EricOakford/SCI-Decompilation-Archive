;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9550)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm9v55 0
)

(instance rm9v55 of ShiversRoom
	(properties
		picture 9550
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 9)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(if (and (== n 9570) (proc951_11 6 9000))
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
		nextRoom 9540
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9521
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 9570
	)
	
	(method (init)
		(self
			createPoly: 78 142 77 92 151 79 153 0 231 0 231 88 212 99 207 141
		)
		(super init: &rest)
	)
)
