;;; Sierra Script 1.0 - (do not remove this comment)
(script# 26)
(include game.sh) (include "26.shm")
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use Actor)
(use System)

(public
	rm26 0
)

(instance rm26 of EncRoom
	(properties
		picture 700
		style DISSOLVE
		horizon 60
		encChance 20
		entrances 7
		illBits (| cWHITE cYELLOW)
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init:)
		(Load VIEW 700)
		(StatusLine enable:)
		(NormalEgo)
		(westBush setPri: 7 ignoreActors: init: addToPic:)
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
		(Bset fBeenIn26)
		(super dispose:)
	)
)

(instance westBush of View
	(properties
		y 59
		noun N_WESTBUSH
		view 700
		loop 4
	)
)
