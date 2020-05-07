;;; Sierra Script 1.0 - (do not remove this comment)
(script# 34)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use System)

(public
	rm34 0
)

(instance rm34 of EncRoom
	(properties
		picture 700
		style DISSOLVE
		horizon 90
		north 23
		east 35
		south 51
		west 33
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
			(23
				(ego posn: 140 92 setMotion: MoveTo 140 190)
			)
			(33
				(ego posn: 1 140 setMotion: MoveTo 320 140)
			)
			(51
				(ego posn: 160 188 setMotion: MoveTo 160 0)
			)
			(35
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
		(mouseDownHandler delete: self)
		(Bset VISITED_FOREST_34)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed ego event shiftDown)
				(HighPrint 34 0)
				;Our hero looks really lost.
				)
		)
	)
)
