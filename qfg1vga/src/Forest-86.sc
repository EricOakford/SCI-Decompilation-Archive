;;; Sierra Script 1.0 - (do not remove this comment)
(script# 86)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use System)

(public
	rm86 0
)

(instance rm86 of EncRoom
	(properties
		picture 705
		style DISSOLVE
		encChance 100
		entrances (| reSOUTH reWEST)
		illBits -24576
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init:)
		(StatusLine enable:)
		(NormalEgo)
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
		(Bset fBeenIn86)
		(super dispose:)
	)
)
