;;; Sierra Script 1.0 - (do not remove this comment)
(script# 19250)
(include sci.sh)
(use Main)
(use n925)
(use Procs)
(use System)

(public
	rm19v250 0
)

(instance rm19v250 of ShiversRoom
	(properties
		picture 19250
	)
	
	(method (init)
		(super init: &rest)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(if (proc951_11 8 19000) (self setScript: sAttack))
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 19280
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 19260
	)
)

(instance sAttack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (proc925_1 -7346 self))
			(1
				(efExitLeft dispose:)
				(efExitRight dispose:)
				(proc925_0 self)
			)
			(2
				(efExitLeft init: 2)
				(efExitRight init: 1)
				(if (proc951_11 8 19000)
					(proc951_9 21909)
					(sounds play: 21909 -1 50 0)
					(proc951_9 21910)
					(sounds play: 21910 -1 50 0)
					(proc925_1 -7346 self 0 1)
					(= state 0)
				else
					(sounds
						playChain: -1 -5 21901 -1 -1 -5 21901 -1 -1 -5 21904 -1
					)
					(sounds play: 21903 -1 48 0)
					(sounds adjChainVol: 16)
					(self dispose:)
				)
			)
		)
	)
)
