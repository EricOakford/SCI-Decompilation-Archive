;;; Sierra Script 1.0 - (do not remove this comment)
(script# 27)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm27 0
)

(instance rm27 of EncRoom
	(properties
		picture 702
		style DISSOLVE
		horizon 90
		east 28
		west 26
		encChance 20
		entrances (| reEAST reWEST)
		illBits (| cWHITE cLRED)
	)
	
	(method (init)
		(super init:)
		(Load VIEW vBushes)
		(Load VIEW vSnow)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(if (not monsterNum) (ego init:))
		(switch prevRoomNum
			(28
				(ego posn: 318 140 setMotion: MoveTo 0 140)
			)
			(26
				(ego posn: 1 140 setMotion: MoveTo 320 140)
			)
		)
		(addToPics
			add: northBush southBush snow1 snow2 snow5 snow6 snow7
			eachElementDo: #init
			doit:
		)
		(snow3 setPri: 15 ignoreActors: init: addToPic:)
		(snow4 setPri: 1 ignoreActors: init: addToPic:)
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
		(Bset VISITED_FOREST_27)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(if (and (Said 'look>') (Said '/ice'))
					(HighPrint 27 0)
					;You see snow and feel a cold wind blowing from the east.
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance northBush of PicView
	(properties
		y 71
		x 136
		view vBushes
		loop 2
	)
)

(instance southBush of PicView
	(properties
		y 207
		x 153
		view vBushes
		loop 2
		cel 1
		priority 15
	)
)

(instance snow1 of PicView
	(properties
		y 40
		x 263
		view vSnow
		priority 15
	)
)

(instance snow2 of PicView
	(properties
		y 76
		x 256
		view vSnow
		loop 1
		priority 15
	)
)

(instance snow3 of View
	(properties
		y 134
		x 268
		view vSnow
		loop 2
	)
)

(instance snow4 of View
	(properties
		y 125
		x 284
		view vSnow
		loop 3
	)
)

(instance snow5 of PicView
	(properties
		y 26
		x 185
		view vSnow
		loop 4
		priority 15
	)
)

(instance snow6 of PicView
	(properties
		y 44
		x 179
		view vSnow
		loop 4
		cel 1
		priority 15
	)
)

(instance snow7 of PicView
	(properties
		y 83
		x 194
		view vSnow
		loop 5
	)
)
