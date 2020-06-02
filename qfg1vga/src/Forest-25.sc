;;; Sierra Script 1.0 - (do not remove this comment)
(script# 25)
(include game.sh) (include "25.shm")
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use Actor)
(use System)

(public
	rm25 0
)

(instance rm25 of EncRoom
	(properties
		picture 702
		style DISSOLVE
		horizon 55
		encChance 30
		entrances (| reNORTH reSOUTH reWEST)
		illBits (| cWHITE cLMAGENTA)
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init:)
		(Load VIEW 700)
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
		(Bset fBeenIn25)
		(super dispose:)
	)
)

(instance eastBush of View
	(properties
		x 228
		y 74
		noun N_EASTBUSH
		view 700
		loop 4
	)
)
