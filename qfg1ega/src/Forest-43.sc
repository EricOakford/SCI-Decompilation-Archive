;;; Sierra Script 1.0 - (do not remove this comment)
(script# 43)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use System)

(public
	rm43 0
)

(instance rm43 of EncRoom
	(properties
		picture 705
		style DISSOLVE
		south 57
		west 42
		encChance 20
		entrances (| reSOUTH reWEST)
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
			(42
				(ego posn: 0 140 setMotion: MoveTo 320 140)
			)
			(57
				(ego posn: 45 189 setMotion: MoveTo 45 0)
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
		(Bset fBeenIn43)
		(super dispose:)
	)
)
