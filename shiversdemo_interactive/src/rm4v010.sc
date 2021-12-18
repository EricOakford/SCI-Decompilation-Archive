;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4010)
(include sci.sh)
(use Main)
(use Procs)
(use System)

(public
	rm4v010 0
)

(instance rm4v010 of ShiversRoom
	(properties
		picture 4260
	)
	
	(method (init)
		(efExitLeft init: 7)
		(efExitRight init: 6)
		(efExitForward init: 3)
		(super init: &rest)
		(if (<= prevRoomNum 4000) (curRoom setScript: sEnter))
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 4000
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 4000
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 4030
	)
	
	(method (init)
		(self
			createPoly:
				67
				128
				103
				95
				104
				46
				124
				48
				124
				52
				133
				54
				134
				48
				151
				48
				152
				89
				181
				127
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
				(sounds fade: 10318 0 1 30 1 0)
				(= cycles 1)
			)
			(1
				(proc951_9 20402)
				(sounds play: 20402 -1 0 0)
				(sounds fade: 20402 98 1 40 0 0)
				(theGame handsOn:)
				(= cycles 1)
			)
			(2 (self dispose:))
		)
	)
)
