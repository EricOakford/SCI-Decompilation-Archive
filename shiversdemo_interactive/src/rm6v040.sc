;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6040)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm6v040 0
)

(instance rm6v040 of ShiversRoom
	(properties
		picture 6040
	)
	
	(method (init)
		(super init: &rest)
		(if (proc951_5 43)
			(proc951_4 43)
			(if (== prevRoomNum 6030)
				(sounds play: 10614 0 40 0)
			else
				(sounds play: 10712 0 40 0)
			)
		)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 3)
		(if (proc951_11 2 6000)
			(proc951_7 20608)
			(proc951_9 20608)
			(sounds play: 20608 -1 32 0)
		)
	)
	
	(method (newRoom n)
		(if (proc951_11 2 6000)
			(if (== n 6280)
				(sounds stop: 20601)
			else
				(sounds fade: 20608 0 5 5 1 0)
			)
		)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 6270
	)
	
	(method (init)
		(if
		(or (== prevRoomNum 6030) (== prevRoomNum 6020))
			(= nextRoom 6030)
		)
		(super init: &rest)
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 6020
	)
	
	(method (init)
		(if
		(or (== prevRoomNum 6270) (== prevRoomNum 6260))
			(= nextRoom 6260)
		)
		(super init: &rest)
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 6280
	)
	
	(method (init)
		(self
			createPoly: 97 142 93 110 126 100 147 100 178 112 178 142
		)
		(super init: &rest)
	)
)
