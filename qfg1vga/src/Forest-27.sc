;;; Sierra Script 1.0 - (do not remove this comment)
(script# 27)
(include game.sh) (include "27.shm")
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use Actor)
(use System)

(public
	rm27 0
)

(instance rm27 of EncRoom
	(properties
		picture 702
		style DISSOLVE
		horizon 90
		encChance 20
		entrances (| reEAST reWEST)
		illBits (| cWHITE cLRED)
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init:)
		(Load VIEW 700)
		(Load VIEW 702)
		(StatusLine enable:)
		(NormalEgo)
		(northBush addToPic:)
		(southBush addToPic:)
		(snow1 addToPic:)
		(snow2 addToPic:)
		(snow3 addToPic:)
		(snow4 addToPic:)
		(snow5 addToPic:)
		(snow6 addToPic:)
		(if
			(not
				(OneOf prevRoomNum
					vBear vMinotaur vSaurus vMantray vCheetaur
					vGoblin vOgre vTroll vDragon vBrigand vBrigandLeader
				)
			)
			(= egoX (ego x?))
			(= egoY (ego y?))
		)
		(self setRegions: ENCOUNTER)
	)
	
	(method (dispose)
		(Bset fBeenIn27)
		(super dispose:)
	)
)

(instance northBush of View
	(properties
		x 85
		y 35
		noun N_NORTHBUSH
		view 700
		loop 2
	)
)

(instance southBush of View
	(properties
		x 111
		y 167
		noun N_SOUTHBUSH
		view 700
		loop 3
		priority 15
	)
)

(instance snow1 of View
	(properties
		x 310
		y 8
		view 702
	)
)

(instance snow2 of View
	(properties
		x 310
		y 38
		view 702
		loop 1
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance snow3 of View
	(properties
		x 240
		y 44
		view 702
		loop 2
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance snow4 of View
	(properties
		x 239
		y 80
		view 702
		loop 3
		signal (| ignrAct fixPriOn)
	)
)

(instance snow5 of View
	(properties
		x 253
		y 126
		view 702
		loop 4
		signal (| ignrAct fixPriOn)
	)
)

(instance snow6 of View
	(properties
		x 313
		y 58
		view 702
		loop 4
		cel 1
		priority 15
		signal (| ignrAct fixPriOn)
	)
)
