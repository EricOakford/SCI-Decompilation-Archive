;;; Sierra Script 1.0 - (do not remove this comment)
(script# 61)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use System)

(public
	rm61 0
)

(instance rm61 of EncRoom
	(properties
		picture 700
		style DISSOLVE
		horizon 90
		north 44
		east 62
		south 69
		west 60
		encChance 15
	)
	
	(method (init)
		(super init:)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(if (not monsterNum) (ego init:))
		(switch prevRoomNum
			(44
				(ego posn: 140 92 setMotion: MoveTo 140 190)
			)
			(60
				(ego posn: 1 140 setMotion: MoveTo 320 140)
			)
			(69
				(ego posn: 160 188 setMotion: MoveTo 160 0)
			)
			(62
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
		(Bset VISITED_FOREST_61)
		(super dispose:)
	)
)
