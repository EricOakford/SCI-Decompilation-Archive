;;; Sierra Script 1.0 - (do not remove this comment)
(script# 69)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Procs)
(use PolyPath)
(use Game)
(use System)

(public
	rm69 0
)

(instance rm69 of EncRoom
	(properties
		picture 706
		style DISSOLVE
		horizon 68
		encChance 20
		entrances (| reNORTH reEAST)
		illBits -20480
	)
	
	(method (init)
		(self setRegions: FOREST)
		(Load VIEW 700)
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
		(if (and Night (== prevRoomNum 170) (Btst fFaeryAttention))
			(HandsOff)
			(ego setScript: fairyWalkIn)
			(Load SCRIPT 295)
			(Bclr fFaeryAttention)
			(self setScript: (ScriptID 295 0))
			(self encChance: 0)
		)
		(self setRegions: ENCOUNTER)
	)
	
	(method (dispose)
		(Bset fBeenIn69)
		(super dispose:)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)

(instance fairyWalkIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 90))
			(1
				(HandsOff)
				(ego setMotion: PolyPath 140 120 self)
			)
			(2
				(ego loop: 0)
				(self dispose:)
			)
		)
	)
)
