;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9620)
(include sci.sh)
(use Main)
(use Procs)
(use System)

(public
	rm9v62 0
)

(instance rm9v62 of ShiversRoom
	(properties
		picture 9620
	)
	
	(method (init)
		(efExitLeft init: 7)
		(efExitRight init: 1)
		(efExitForward init: 9)
		(super init: &rest)
		(cond 
			((== prevRoomNum 15260) (curRoom setScript: sEnter) (proc951_4 43))
			((proc951_5 43) (sounds play: 11503 0 82 0) (proc951_4 43))
		)
	)
	
	(method (newRoom n)
		(proc951_1 6)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9630
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9870
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 9170
	)
	
	(method (init)
		(self createPoly: 33 141 53 82 141 82 167 142)
		(super init: &rest)
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sounds fade: 21501 0 1 30 1 self)
				(sounds play: 11503 0 98 0)
			)
			(1
				(proc951_10 21501)
				(= cycles 1)
			)
			(2
				(proc951_7 20903)
				(proc951_9 20903)
				(sounds play: 20903 -1 0 0)
				(sounds fade: 20903 42 15 5 0 0)
				(theGame handsOn:)
				(= cycles 1)
			)
			(3 (self dispose:))
		)
	)
)
