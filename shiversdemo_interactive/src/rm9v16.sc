;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9160)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use Actor)

(public
	rm9v16 0
)

(instance rm9v16 of ShiversRoom
	(properties
		picture 9160
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 9)
		(if (proc951_5 42)
			((Prop new:)
				view: 9160
				setPri: 5 1
				loop: 1
				cycleSpeed: 12
				setCycle: Fwd
				init:
			)
		else
			((Prop new:) view: 9160 setPri: 5 1 loop: 0 init:)
		)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(proc951_1 3)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9190
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9170
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 9120
	)
	
	(method (init)
		(self
			createPoly: 50 150 71 92 72 47 164 48 165 71 159 73 156 110 209 145
		)
		(super init: &rest)
	)
)
