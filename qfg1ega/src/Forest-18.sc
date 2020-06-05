;;; Sierra Script 1.0 - (do not remove this comment)
(script# 18)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm18 0
)

(instance rm18 of EncRoom
	(properties
		picture 700
		style DISSOLVE
		horizon 90
		east 19
		west 17
		encChance 30
		entrances (| reEAST reWEST)
		illBits (| cWHITE cLRED)
	)
	
	(method (init)
		(super init:)
		(Load VIEW vBushes)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(ego illegalBits: (| cWHITE cLRED))
		(if (not monsterNum) (ego init:))
		(switch prevRoomNum
			(17
				(ego posn: 1 140 setMotion: MoveTo 320 140)
			)
			(19
				(ego posn: 318 140 setMotion: MoveTo 0 140)
			)
		)
		(addToPics
			add: northBush southBush
			eachElementDo: #init
			doit:
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
		(Bset VISITED_FOREST_18)
		(super dispose:)
	)
)

(instance northBush of PicView
	(properties
		y 86
		x 133
		view vBushes
		loop 2
	)
)

(instance southBush of PicView
	(properties
		y 207
		x 162
		view vBushes
		loop 2
		cel 1
		priority 15
	)
)
