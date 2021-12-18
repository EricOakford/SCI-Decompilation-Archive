;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9460)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm9v46 0
)

(instance rm9v46 of ShiversRoom
	(properties
		picture 9460
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 9)
		(if
			(and
				(proc951_5 42)
				(or
					(== prevRoomNum 9850)
					(and (< 9000 prevRoomNum) (< prevRoomNum 9440))
				)
			)
			(MonoOut {fade to 8})
			(sounds fade: 10908 8 15 8 0 0)
		)
		(if (proc951_5 43)
			(sounds play: 10904 0 40 0)
			(proc951_4 43)
		)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(if (and (proc951_5 42) (> n 9470))
			(MonoOut {fade to 0})
			(sounds fade: 10908 0 20 3 1 0)
		)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9450
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9470
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 9760
	)
	
	(method (init)
		(self createPoly: 81 142 81 0 206 0 206 139)
		(super init: &rest)
	)
)
