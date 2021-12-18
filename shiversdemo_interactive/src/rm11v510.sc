;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11510)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm11v510 0
)

(instance rm11v510 of ShiversRoom
	(properties
		picture 11510
	)
	
	(method (init)
		(efBack init: 8)
		(proc951_16 101)
		(super init: &rest)
	)
)

(instance efBack of ExitFeature
	(properties
		nextRoom 11020
	)
	
	(method (init)
		(self
			createPoly:
				81
				53
				81
				0
				0
				0
				0
				143
				264
				143
				264
				0
				81
				0
				81
				53
				74
				59
				54
				60
				51
				71
				52
				90
				55
				96
				73
				95
				81
				99
				159
				97
				182
				100
				208
				98
				217
				94
				209
				46
				195
				42
				173
				42
				161
				53
		)
		(cond 
			((== prevRoomNum 11190) (= nextRoom 11190))
			((== prevRoomNum 11110) (= nextRoom 11110))
			(else (= nextRoom 11020))
		)
		(super init: &rest)
	)
)
