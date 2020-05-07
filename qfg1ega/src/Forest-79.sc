;;; Sierra Script 1.0 - (do not remove this comment)
(script# 79)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm79 0
)

(instance rm79 of EncRoom
	(properties
		picture 702
		style DISSOLVE
		horizon 90
		north 72
		east 80
		west 78
		encChance 20
		entrances (| reNORTH reEAST reWEST)
		illBits (| cWHITE cLRED)
	)
	
	(method (init)
		(super init:)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(if (not monsterNum) (ego init:))
		(switch prevRoomNum
			(80
				(ego posn: 318 140 setMotion: MoveTo 0 140)
			)
			(78
				(ego posn: 1 140 setMotion: MoveTo 320 140)
			)
			(72
				(ego posn: 130 92 setMotion: MoveTo 130 190)
			)
		)
		(addToPics add: southBush eachElementDo: #init doit:)
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
		(Bset VISITED_FOREST_79)
		(super dispose:)
	)
)

(instance southBush of PicView
	(properties
		y 207
		x 158
		view vBushes
		loop 2
		cel 1
		priority 15
	)
)
