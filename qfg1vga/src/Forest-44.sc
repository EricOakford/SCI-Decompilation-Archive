;;; Sierra Script 1.0 - (do not remove this comment)
(script# 44)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use System)

(public
	rm44 0
)

(instance rm44 of EncRoom
	(properties
		picture 704
		style DISSOLVE
		encChance 20
		entrances (| reSOUTH reEAST)
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
		(Bset fBeenIn44)
		(super dispose:)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)
