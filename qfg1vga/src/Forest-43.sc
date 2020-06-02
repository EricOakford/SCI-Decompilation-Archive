;;; Sierra Script 1.0 - (do not remove this comment)
(script# 43)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Procs)
(use Game)
(use System)

(public
	rm43 0
)

(instance rm43 of EncRoom
	(properties
		picture 705
		style DISSOLVE
		encChance 20
		entrances (| reSOUTH reWEST)
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
		(Bset fBeenIn43)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return FALSE)
		;((ScriptID 804 0) doVerb: theVerb &rest)
	)
)
