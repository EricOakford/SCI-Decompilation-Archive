;;; Sierra Script 1.0 - (do not remove this comment)
(script# 75)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use System)

(public
	rm75 0
)

(instance rm75 of EncRoom
	(properties
		picture 707
		style DISSOLVE
		horizon 50
		encChance 10
		entrances (| reNORTH reWEST)
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
		(Bset fBeenIn75)
		(super dispose:)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)
