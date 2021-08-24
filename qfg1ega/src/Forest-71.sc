;;; Sierra Script 1.0 - (do not remove this comment)
(script# 71)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm71 0
)

(instance rm71 of EncRoom
	(properties
		picture 703
		style DISSOLVE
		horizon 90
		north 63
		east 72
		south 78
		west 70
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
			(63
				(ego posn: 190 92 setMotion: MoveTo 190 190)
			)
			(72
				(ego posn: 318 140 setMotion: MoveTo 0 140)
			)
			(70
				(ego posn: 1 140 setMotion: MoveTo 50 140)
				(if (Btst fFaeryAttention)
					(User canControl: FALSE)
					(User canInput: FALSE)
					(Bclr fFaeryAttention)
					(self setScript: (ScriptID 295 0))
				)
			)
			(78
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
		(Bset fBeenIn71)
		(super dispose:)
	)
)
