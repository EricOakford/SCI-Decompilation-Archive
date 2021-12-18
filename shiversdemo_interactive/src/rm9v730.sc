;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9730)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm9v730 0
)

(instance rm9v730 of ShiversRoom
	(properties
		picture 9730
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 3)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(if (and (proc951_5 42) (< 9000 n) (< n 9300))
			(MonoOut {fade to 0})
			(sounds fade: 10908 0 20 3 1 0)
		)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9740
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9720
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 9080
	)
	
	(method (init)
		(self
			createPoly: 58 118 81 95 77 16 243 20 242 117 219 116
		)
		(super init: &rest)
	)
)
