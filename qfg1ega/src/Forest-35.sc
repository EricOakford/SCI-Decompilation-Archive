;;; Sierra Script 1.0 - (do not remove this comment)
(script# 35)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use System)

(public
	rm35 0
)

(instance rm35 of EncRoom
	(properties
		picture 703
		style DISSOLVE
		horizon 90
		north 24
		east 36
		south 52
		west 34
		encChance 20
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler add: self)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(if (not monsterNum) (ego init:))
		(switch prevRoomNum
			(24
				(ego posn: 190 92 setMotion: MoveTo 190 190)
			)
			(36
				(ego posn: 318 140 setMotion: MoveTo 0 140)
			)
			(34
				(ego posn: 1 140 setMotion: MoveTo 320 140)
			)
			(52
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
		(mouseDownHandler delete: self)
		(Bset VISITED_FOREST_35)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed ego event shiftDown)
				(HighPrint 35 0)
				;Our hero looks a little lost.
				)
		)
	)
)
