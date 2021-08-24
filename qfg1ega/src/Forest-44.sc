;;; Sierra Script 1.0 - (do not remove this comment)
(script# 44)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use System)

(public
	rm44 0
)

(instance rm44 of EncRoom
	(properties
		picture 704
		style DISSOLVE
		east 45
		south 61
		encChance 20
		entrances (| reEAST reSOUTH)
	)
	
	(method (init)
		(super init:)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(if (not monsterNum)
			(ego init:)
		)
		(switch prevRoomNum
			(45
				(ego posn: 319 140 setMotion: MoveTo 0 140)
			)
			(61
				(ego posn: 275 189 setMotion: MoveTo 275 0)
			)
		)
		(if
			(not
				(OneOf prevRoomNum
					vBear vMinotaur vSaurus vMantray vCheetaur
					vGoblin vTroll vOgre vDragon vBrigand vBrigandLeader
				)
			)
			(= egoX (ego x?))
			(= egoY (ego y?))
		)
		(self setRegions: ENCOUNTER)
	)
	
	(method (dispose)
		(Bset fBeenIn44)
		(super dispose:)
	)
)
