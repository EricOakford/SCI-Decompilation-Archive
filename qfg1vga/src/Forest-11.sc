;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use System)

(public
	rm11 0
)

(instance rm11 of EncRoom
	(properties
		picture 704
		style DISSOLVE
		encChance 30
		entrances (| reEAST reSOUTH)
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
		(Bset fBeenIn11)
		(super dispose:)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)
