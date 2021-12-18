;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11050)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm11v050 0
)

(instance rm11v050 of ShiversRoom
	(properties
		picture 11050
	)
	
	(method (init)
		(super init: &rest)
		(if (and (== prevRoomNum 11040) (proc951_5 43))
			(proc951_4 43)
			(sounds play: 11105 0 32 0)
		)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 9)
		(cond 
			((proc951_11 9 11000)
				(proc951_7 21103)
				(proc951_9 21103)
				(sounds play: 21103 -1 50 0)
				(sounds fade: 21101 0 15 8 1 0)
				(sounds fadeChain:)
			)
			((proc951_11 3 11000)
				(sounds fade: 21103 0 5 16 1 0)
				(proc951_7 21101)
				(proc951_9 21101)
				(sounds play: 21101 -1 48 0)
				(sounds playChain: -1 -5 21102 -1)
				(sounds adjChainVol: 32)
			)
		)
	)
	
	(method (newRoom n)
		(if (and (<= 11000 n) (<= n 11040))
			(if (proc951_11 3 11000)
				(sounds stop: 21101)
				(sounds stopChain:)
			)
			(sounds fade: 21103 0 5 16 1 0)
		)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 11020
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 11040
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 11060
	)
	
	(method (init)
		(self
			createPoly:
				203
				135
				177
				115
				178
				98
				185
				98
				185
				28
				120
				27
				120
				104
				120
				79
				107
				79
				106
				85
				100
				107
				62
				111
				52
				125
				62
				135
		)
		(super init: &rest)
	)
)
