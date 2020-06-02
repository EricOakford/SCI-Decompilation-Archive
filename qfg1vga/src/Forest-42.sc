;;; Sierra Script 1.0 - (do not remove this comment)
(script# 42)
(include game.sh) (include "42.shm")
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use Actor)
(use System)

(public
	rm42 0
)

(instance rm42 of EncRoom
	(properties
		picture 703
		style DISSOLVE
		horizon 57
		encChance 20
		entrances (| reNORTH reEAST reSOUTH)
		illBits -24576
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init:)
		(Load VIEW 700)
		(StatusLine enable:)
		(NormalEgo)
		(westBush setPri: 6 ignoreActors: init: addToPic:)
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
		(Bset fBeenIn42)
		(super dispose:)
	)
)

(instance westBush of View
	(properties
		x 15
		y 75
		noun N_WESTBUSH
		view 700
		loop 4
	)
)
