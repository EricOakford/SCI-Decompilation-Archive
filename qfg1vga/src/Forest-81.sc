;;; Sierra Script 1.0 - (do not remove this comment)
(script# 81)
(include game.sh) (include "81.shm")
(use Main)
(use RandomEncounter)
(use Procs)
(use Actor)
(use System)

(public
	rm81 0
)

(instance rm81 of EncRoom
	(properties
		noun N_ROOM
		picture 703
		style DISSOLVE
		horizon 61
		encChance 20
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init:)
		(NormalEgo)
		(hollowLog init: stopUpd:)
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
		(Bset fBeenIn81)
		(super dispose:)
	)
)

(instance hollowLog of View
	(properties
		x 228
		y 85
		noun N_HOLLOWLOG
		view 181
		priority 3
		signal fixPriOn
	)
)
