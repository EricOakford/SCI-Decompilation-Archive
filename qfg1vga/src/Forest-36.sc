;;; Sierra Script 1.0 - (do not remove this comment)
(script# 36)
(include game.sh) (include "36.shm")
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use Actor)
(use System)

(public
	rm36 0
)

(instance rm36 of EncRoom
	(properties
		picture 701
		style DISSOLVE
		horizon 55
		encChance 20
		entrances (| reSOUTH reWEST reNORTH)
		illBits (| cWHITE cYELLOW)
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init:)
		(StatusLine enable:)
		(NormalEgo)
		(eastBush ignoreActors: setPri: 6 init: addToPic:)
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
		(Bset 26)
		(super dispose:)
	)
)

(instance eastBush of View
	(properties
		x 227
		y 75
		noun N_EASTBUSH
		view 700
		loop 4
	)
)
