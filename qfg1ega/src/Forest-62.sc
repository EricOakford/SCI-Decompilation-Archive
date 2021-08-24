;;; Sierra Script 1.0 - (do not remove this comment)
(script# 62)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm62 0
)

(instance rm62 of EncRoom
	(properties
		picture 702
		style DISSOLVE
		horizon 90
		north 45
		east 63
		south 70
		west 61
		encChance 15
	)
	
	(method (init)
		(if (and Night (== prevRoomNum 70))
			(Load SCRIPT 295)
		)
		(super init:)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(if (not monsterNum)
			(ego init:)
		)
		(switch prevRoomNum
			(45
				(ego posn: 130 92 setMotion: MoveTo 130 190)
			)
			(63
				(ego posn: 318 140 setMotion: MoveTo 0 140)
			)
			(61
				(ego posn: 1 140 setMotion: MoveTo 320 140)
			)
			(70
				(ego posn: 120 188 setMotion: MoveTo 120 175)
				(if (Btst fFaeryAttention)
					(Bclr fFaeryAttention)
					(User canControl: FALSE)
					(User canInput: FALSE)
					(self setScript: (ScriptID 295 0))
				)
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
		(Bset fBeenIn62)
		(super dispose:)
	)
)
