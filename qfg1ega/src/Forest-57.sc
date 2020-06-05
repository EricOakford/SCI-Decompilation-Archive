;;; Sierra Script 1.0 - (do not remove this comment)
(script# 57)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm57 0
)

(instance rm57 of EncRoom
	(properties
		picture 702
		style DISSOLVE
		horizon 90
		north 43
		east 58
		south 67
		west 56
		encChance 15
	)
	
	(method (init)
		(Load VIEW vSnow)
		(cSound stop:)
		(super init:)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(if (not monsterNum) (ego init:))
		(switch prevRoomNum
			(58
				(ego posn: 318 140 setMotion: MoveTo 0 140)
			)
			(56
				(ego posn: 1 140 setMotion: MoveTo 320 140)
			)
			(43
				(ego posn: 130 92 setMotion: MoveTo 130 190)
			)
			(67
				(ego posn: 120 188 setMotion: MoveTo 120 0)
			)
		)
		(addToPics
			add: snow1 snow2 snow5 snow6 snow7
			eachElementDo: #init
			doit:
		)
		(snow3 setPri: 15 ignoreActors: init: addToPic:)
		(snow4 setPri: 1 ignoreActors: init: addToPic:)
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
		(Bset VISITED_FOREST_57)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(if (and (Said 'look>') (Said '/ice'))
					(HighPrint 57 0)
					;You see snow and feel a cold wind blowing from the east.
				)
			)
		)
		(super handleEvent: event)
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
