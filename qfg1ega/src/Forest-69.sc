;;; Sierra Script 1.0 - (do not remove this comment)
(script# 69)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm69 0
)

(instance rm69 of EncRoom
	(properties
		picture 701
		style DISSOLVE
		horizon 90
		north 61
		east 70
		encChance 20
		entrances (| reNORTH reEAST)
		illBits (| cWHITE cLRED cLMAGENTA)
	)
	
	(method (init)
		(Load VIEW vBushes)
		(if (and Night (== prevRoomNum 70))
			(Load SCRIPT 295)
		)
		(super init:)
		(self setLocales: FOREST)
		(StatusLine enable:)
		(NormalEgo)
		(if (not monsterNum) (ego illegalBits: (| cWHITE cLMAGENTA) init:))
		(switch prevRoomNum
			(70
				(ego posn: 318 140 setMotion: MoveTo 265 140)
				(if (Btst GOT_FAIRIES_ATTENTION)
					(User canControl: FALSE)
					(User canInput: FALSE)
					(Bclr GOT_FAIRIES_ATTENTION)
					(self setScript: (ScriptID 295 0))
				)
			)
			(61
				(ego posn: 180 92 setMotion: MoveTo 180 190)
			)
		)
		(southBush init:)
		(addToPics add: southBush doit:)
		(westBush ignoreActors: setPri: 7 init: addToPic:)
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
		(Bset VISITED_FOREST_69)
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

(instance westBush of View
	(properties
		y 173
		x 30
		view vBushes
	)
)
