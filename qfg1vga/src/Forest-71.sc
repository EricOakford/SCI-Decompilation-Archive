;;; Sierra Script 1.0 - (do not remove this comment)
(script# 71)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Procs)
(use PolyPath)
(use Game)
(use System)

(public
	rm71 0
)

(instance rm71 of EncRoom
	(properties
		picture 703
		style DISSOLVE
		horizon 59
		encChance 15
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
		(Bset fBeenIn71)
		(super dispose:)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)

(instance fairyWalkIn of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 90)
			)
			(1
				(HandsOff)
				(ego setMotion: PolyPath 160 100 self)
			)
			(2
				(ego loop: 1)
				(self dispose:)
			)
		)
	)
)
