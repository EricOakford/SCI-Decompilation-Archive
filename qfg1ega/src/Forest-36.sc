;;; Sierra Script 1.0 - (do not remove this comment)
(script# 36)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm36 0
)

(instance rm36 of EncRoom
	(properties
		picture 701
		style DISSOLVE
		horizon 90
		north 25
		south 53
		west 35
		encChance 20
		entrances (| reNORTH reSOUTH reWEST)
		illBits (| cWHITE cYELLOW)
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler add: self)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(if (not monsterNum) (ego init:))
		(switch prevRoomNum
			(25
				(ego posn: 180 91 setMotion: MoveTo 180 190)
			)
			(35
				(ego posn: 0 140 setMotion: MoveTo 320 140)
			)
			(53
				(ego posn: 160 189 setMotion: MoveTo 160 0)
			)
		)
		(eastBush ignoreActors: setPri: 6 init: addToPic:)
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
		(Bset VISITED_FOREST_36)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed ego event shiftDown)
				(HighPrint 36 0)
				;Have I been here before?
				)
		)
	)
)

(instance eastBush of View
	(properties
		y 160
		x 285
		view vBushes
		loop 1
	)
)
