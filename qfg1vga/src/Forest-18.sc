;;; Sierra Script 1.0 - (do not remove this comment)
(script# 18)
(include game.sh) (include "18.shm")
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use Actor)
(use System)

(public
	rm18 0
)

(instance rm18 of EncRoom
	(properties
		picture 702
		style DISSOLVE
		encChance 30
		entrances (| reEAST reWEST)
		illBits (| cWHITE cLRED)
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init:)
		(Load VIEW 700)
		(StatusLine enable:)
		(NormalEgo)
		(ego illegalBits: (| cWHITE cLRED))
		(northBush addToPic:)
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
		(Bset fBeenIn18)
		(super dispose:)
	)
)

(instance northBush of View
	(properties
		x 85
		y 35
		noun N_NORTH_BUSH
		view 700
		loop 2
	)
)

(instance southBush of View
	(properties
		x 111
		y 167
		noun N_SOUTH_BUSH
		view 700
		loop 3
		priority 15
	)
)
