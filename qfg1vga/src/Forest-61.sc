;;; Sierra Script 1.0 - (do not remove this comment)
(script# 61)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use System)

(public
	rm61 0
)

(instance rm61 of EncRoom
	(properties
		picture 700
		style DISSOLVE
		horizon 60
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
	
	(method (dispose)
		(Bset fBeenIn61)
		(super dispose:)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)
