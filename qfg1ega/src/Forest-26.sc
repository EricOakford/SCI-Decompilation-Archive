;;; Sierra Script 1.0 - (do not remove this comment)
(script# 26)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm26 0
)

(instance rm26 of EncRoom
	(properties
		picture 700
		style DISSOLVE
		horizon 90
		north 19
		east 27
		south 42
		encChance 20
		entrances (| reNORTH reEAST reSOUTH)
		illBits (| cWHITE cYELLOW)
	)
	
	(method (init)
		(super init:)
		(Load VIEW vBushes)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(if (not monsterNum) (ego init:))
		(switch prevRoomNum
			(19
				(ego posn: 140 92 setMotion: MoveTo 140 190)
			)
			(42
				(ego posn: 160 188 setMotion: MoveTo 160 0)
			)
			(27
				(ego posn: 318 140 setMotion: MoveTo 0 140)
			)
		)
		(westBush setPri: 7 ignoreActors: init: addToPic:)
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
		(Bset VISITED_FOREST_26)
		(super dispose:)
	)
)

(instance westBush of View
	(properties
		y 167
		x 32
		view vBushes
	)
)
