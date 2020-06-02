;;; Sierra Script 1.0 - (do not remove this comment)
(script# 56)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use System)

(public
	rm56 0
)

(instance rm56 of EncRoom
	(properties
		picture 701
		style DISSOLVE
		horizon 54
		encChance 15
		entrances (| reNORTH reEAST reSOUTH)
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
		(Bset fBeenIn56)
		(super dispose:)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)
