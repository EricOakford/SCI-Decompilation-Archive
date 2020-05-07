;;; Sierra Script 1.0 - (do not remove this comment)
(script# 25)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm25 0
)

(instance rm25 of EncRoom
	(properties
		picture 702
		style DISSOLVE
		horizon 90
		north 17
		south 36
		west 24
		encChance 30
		entrances (| reNORTH reSOUTH reWEST)
		illBits (| cWHITE cLMAGENTA)
	)
	
	(method (init)
		(super init:)
		(Load VIEW vBushes)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(if (not monsterNum) (ego init:))
		(switch prevRoomNum
			(24
				(ego posn: 1 140 setMotion: MoveTo 320 140)
			)
			(17
				(ego posn: 130 92 setMotion: MoveTo 130 190)
			)
			(36
				(ego posn: 120 188 setMotion: MoveTo 120 0)
			)
		)
		(eastBush ignoreActors: setPri: 6 init: addToPic:)
		(if
			(not
				(OneOf
					prevRoomNum
					BEAR MINOTAUR SAURUS MANTRAY CHEETAUR GOBLIN TROLL OGRE SAURUSREX BRIGAND LEADER
				)
			)
			(= egoX (ego x?))
			(= egoY (ego y?))
		)
		(self setRegions: ENCOUNTER)
	)
	
	(method (dispose)
		(Bset VISITED_FOREST_25)
		(super dispose:)
	)
)

(instance eastBush of View
	(properties
		y 147
		x 278
		view vBushes
		loop 1
	)
)
