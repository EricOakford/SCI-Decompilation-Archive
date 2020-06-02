;;; Sierra Script 1.0 - (do not remove this comment)
(script# 92)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Procs)
(use System)

(public
	rm92 0
)

(instance rm92 of EncRoom
	(properties
		picture 707
		style DISSOLVE
		encChance 50
		entrances (| reNORTH reWEST)
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init:)
		(NormalEgo)
		(Bclr fBrigsUnaware)
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
		(Bset fBeenIn92)
		(super dispose:)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)
