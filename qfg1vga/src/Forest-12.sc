;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12)
(include game.sh) (include "12.shm")
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use Actor)
(use System)

(public
	rm12 0
)

(instance rm12 of EncRoom
	(properties
		picture 700
		style DISSOLVE
		horizon 43
		encChance 30
		entrances (| reEAST reSOUTH)
		illBits (| cWHITE cLRED)
	)
	
	(method (init)
		(Load VIEW 700)
		(self setRegions: FOREST)
		(super init:)
		(StatusLine enable:)
		(NormalEgo)
		(southBush addToPic:)
		(southBush2 addToPic:)
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
		(Bset fBeenIn12)
		(super dispose:)
	)
)

(instance southBush of View
	(properties
		x 126
		y 165
		noun N_BUSH
		view 700
		loop 1
		priority 15
		signal fixPriOn
	)
)

(instance southBush2 of View
	(properties
		x 46
		y 167
		noun N_BUSH
		view 700
		loop 1
		priority 15
		signal fixPriOn
	)
)
