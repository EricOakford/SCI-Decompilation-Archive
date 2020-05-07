;;; Sierra Script 1.0 - (do not remove this comment)
(script# 24)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use System)

(public
	rm24 0
)

(instance rm24 of EncRoom
	(properties
		picture 700
		style DISSOLVE
		horizon 90
		north 16
		east 25
		south 35
		west 23
		encChance 30
	)
	
	(method (init)
		(super init:)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(if (not monsterNum) (ego init:))
		(switch prevRoomNum
			(16
				(ego posn: 140 92 setMotion: MoveTo 140 190)
			)
			(23
				(ego posn: 1 140 setMotion: MoveTo 320 140)
			)
			(35
				(ego posn: 160 188 setMotion: MoveTo 160 0)
			)
			(25
				(ego posn: 318 140 setMotion: MoveTo 0 140)
			)
		)
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
		(Bset VISITED_FOREST_24)
		(super dispose:)
	)
)
