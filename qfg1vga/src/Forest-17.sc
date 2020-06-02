;;; Sierra Script 1.0 - (do not remove this comment)
(script# 17)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use System)

(public
	rm17 0
)

(instance rm17 of EncRoom
	(properties
		picture 703
		style DISSOLVE
		horizon 57
		encChance 30
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
	
	(method (dispose)
		(Bset fBeenIn17)
		(super dispose:)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)
