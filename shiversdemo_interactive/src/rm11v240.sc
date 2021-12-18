;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11240)
(include sci.sh)
(use Main)
(use Procs)
(use System)

(public
	rm11v240 0
)

(instance rm11v240 of ShiversRoom
	(properties
		picture 11240
	)
	
	(method (init)
		(super init: &rest)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 9)
		(cond 
			((== prevRoomNum 19020) (curRoom setScript: sEnter))
			((proc951_5 43) (sounds play: 11903 0 82 0))
		)
		(proc951_4 43)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 11250
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 11500
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 11230
	)
	
	(method (init)
		(self
			createPoly:
				202
				141
				184
				127
				132
				122
				114
				95
				88
				105
				76
				81
				86
				78
				93
				67
				68
				53
				26
				54
				27
				142
		)
		(super init: &rest)
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sounds fadeChain:)
				(sounds fade: 21903 0 5 16 1 0)
				(sounds play: 11903 0 82 self)
			)
			(1
				(sounds interruptChain:)
				(= cycles 1)
			)
			(2
				(if (proc951_11 3 11000)
					(proc951_7 21103)
					(proc951_9 21103)
					(sounds play: 21103 -1 50 0)
				else
					(proc951_7 21101)
					(proc951_9 21101)
					(sounds adjChainVol: 32)
					(sounds play: 21101 -1 48 0)
					(sounds playChain: -1 -5 21102 -1)
				)
				(theGame handsOn:)
			)
		)
	)
)
