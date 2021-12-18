;;; Sierra Script 1.0 - (do not remove this comment)
(script# 19010)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm19v010 0
)

(instance rm19v010 of ShiversRoom
	(properties
		picture 19010
	)
	
	(method (init)
		(super init: &rest)
		(if (proc951_5 43)
			(sounds play: 11903 0 82 0)
			(proc951_4 43)
		)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 9)
		(if (proc951_11 8 19000)
			(sounds fadeChain:)
			(sounds fade: 21903 0 15 8 1 0)
			(sounds play: 21909 -1 58 0)
		)
	)
	
	(method (newRoom n)
		(if (proc951_11 8 19000)
			(sounds stop: 21903)
			(sounds interruptChain:)
		)
		(proc951_1 3)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 19040
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 19020
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 19050
	)
	
	(method (init)
		(self
			createPoly:
				208
				142
				202
				129
				217
				128
				219
				98
				189
				77
				127
				77
				117
				66
				95
				95
				21
				95
				21
				144
		)
		(super init: &rest)
	)
)
