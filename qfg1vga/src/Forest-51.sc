;;; Sierra Script 1.0 - (do not remove this comment)
(script# 51)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use System)

(public
	rm51 0
)

(instance rm51 of EncRoom
	(properties
		picture 703
		style DISSOLVE
		horizon 55
		encChance 15
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
		(Bset fBeenIn51)
		(super dispose:)
	)
	
	(method (doVerb)
		(return 0)
	)
)
