;;; Sierra Script 1.0 - (do not remove this comment)
(script# 57)
(include game.sh) (include "57.shm")
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use Actor)
(use System)

(public
	rm57 0
)

(instance rm57 of EncRoom
	(properties
		picture 703
		style DISSOLVE
		horizon 54
		encChance 15
	)
	
	(method (init)
		(Load VIEW 702)
		(cSound stop:)
		(self setRegions: FOREST)
		(super init:)
		(StatusLine enable:)
		(NormalEgo)
		(addToPics
			add: snow2 snow5 snow6
			eachElementDo: #init
			doit:
		)
		(snow3 setPri: 15 ignoreActors: init: addToPic:)
		(snow4 setPri: 1 ignoreActors: init: addToPic:)
		(if
			(not
				(OneOf prevRoomNum
					vBear vMinotaur vSaurus vMantray vCheetaur
					vGoblin vOgre vTroll vDragon vBrigand vBrigandLeader
				)
			)
			(= egoX (ego x?))
			(= egoY (ego y?))
		)
		(self setRegions: ENCOUNTER)
	)
	
	(method (dispose)
		(Bset fBeenIn57)
		(super dispose:)
	)
)

(instance snow2 of View
	(properties
		x 292
		y 37
		noun 1
		view 702
		loop 1
		priority 15
		signal fixPriOn
	)
)

(instance snow3 of View
	(properties
		x 240
		y 44
		noun N_SNOW
		view 702
		loop 2
		priority 15
		signal fixPriOn
	)
)

(instance snow4 of View
	(properties
		x 235
		y 95
		noun N_SNOW
		view 702
		loop 3
		signal fixPriOn
	)
)

(instance snow5 of View
	(properties
		x 259
		y 128
		noun N_SNOW
		view 702
		loop 4
		priority 1
		signal (| ignrAct fixPriOn)
	)
)

(instance snow6 of View
	(properties
		x 315
		y 94
		noun N_SNOW
		view 702
		loop 5
		priority 1
		signal fixPriOn
	)
)
