;;; Sierra Script 1.0 - (do not remove this comment)
(script# 19040)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm19v040 0
)

(instance rm19v040 of ShiversRoom
	(properties
		picture 19040
	)
	
	(method (init)
		(super init: &rest)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 9)
		(cond 
			(
			(and (< 11000 prevRoomNum) (< prevRoomNum 19000))
				(sounds adjChainVol: 16)
				(sounds
					playChain: -1 -5 21901 -1 -1 -5 21901 -1 -1 -5 21904 -1
				)
				(sounds play: 21903 -1 48 0)
				(sounds play: 11903 0 82 0)
				(proc951_4 43)
			)
			(
			(and (proc951_11 8 19000) (== prevRoomNum 19010))
				(sounds fade: 21909 0 5 16 1 0)
				(sounds adjChainVol: 16)
				(sounds
					playChain: -1 -5 21901 -1 -1 -5 21901 -1 -1 -5 21904 -1
				)
				(sounds play: 21903 -1 48 0)
			)
		)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 19030
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 19010
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 19160
	)
	
	(method (init)
		(self
			createPoly: 132 41 99 51 103 114 133 111 154 102 179 113 192 113 191 56
		)
		(super init: &rest)
	)
)
