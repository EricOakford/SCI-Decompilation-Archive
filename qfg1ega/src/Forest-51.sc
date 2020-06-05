;;; Sierra Script 1.0 - (do not remove this comment)
(script# 51)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use System)

(public
	rm51 0
)

(instance rm51 of EncRoom
	(properties
		picture 703
		style DISSOLVE
		horizon 90
		north 34
		east 52
		south 63
		west 45
		encChance 15
	)
	
	(method (init)
		(super init:)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(if (not monsterNum) (ego init:))
		(switch prevRoomNum
			(34
				(ego posn: 190 92 setMotion: MoveTo 190 190)
			)
			(52
				(ego posn: 318 140 setMotion: MoveTo 0 140)
			)
			(45
				(ego posn: 1 140 setMotion: MoveTo 320 140)
			)
			(63
				(ego posn: 200 188 setMotion: MoveTo 200 0)
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
		(Bset VISITED_FOREST_51)
		(super dispose:)
	)
)
