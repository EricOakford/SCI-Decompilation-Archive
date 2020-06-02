;;; Sierra Script 1.0 - (do not remove this comment)
(script# 79)
(include game.sh) (include "79.shm")
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use Actor)
(use System)

(public
	rm79 0
)

(instance rm79 of EncRoom
	(properties
		picture 702
		style DISSOLVE
		horizon 57
		encChance 20
		entrances (| reNORTH reEAST reWEST)
		illBits -28672
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init:)
		(StatusLine enable:)
		(NormalEgo)
		(southBush addToPic:)
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
		(Bset fBeenIn79)
		(super dispose:)
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
