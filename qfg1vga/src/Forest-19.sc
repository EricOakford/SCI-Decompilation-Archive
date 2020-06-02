;;; Sierra Script 1.0 - (do not remove this comment)
(script# 19)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use System)

(public
	rm19 0
)

(instance rm19 of EncRoom
	(properties
		picture 705
		style DISSOLVE
		encChance 30
		entrances (| reSOUTH reWEST)
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init:)
		(StatusLine enable:)
		(NormalEgo)
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
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(Bset fBeenIn19)
		(super dispose:)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)
