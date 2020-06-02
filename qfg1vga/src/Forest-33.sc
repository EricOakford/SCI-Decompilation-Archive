;;; Sierra Script 1.0 - (do not remove this comment)
(script# 33)
(include game.sh) (include "33.shm")
(use Main)
(use RandomEncounter)
(use Procs)
(use Sound)
(use Game)
(use Actor)
(use System)

(public
	rm33 0
)

(instance rm33 of EncRoom
	(properties
		picture 702
		style DISSOLVE
		horizon 56
		encChance 35
		entrances (| reNORTH reEAST reSOUTH)
		illBits -16384
	)
	
	(method (init)
		(Load VIEW 700)
		(if (== prevRoomNum 22) (Load SOUND (SoundFX 66)))
		(self setRegions: FOREST)
		(super init:)
		(mouseDownHandler add: self)
		(StatusLine enable:)
		(NormalEgo)
		(westBush setPri: 7 ignoreActors: init: addToPic:)
		(if (and (Btst fBabaHutOpen) (not (Btst fBabaFlyAway)))
			(Bclr fBabaHutOpen)
			(self setScript: hutUp)
		)
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
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(Bset fBeenIn33)
		(super dispose:)
	)
)

(instance westBush of View
	(properties
		x 11
		y 92
		noun N_WESTBUSH
		view 700
		loop 4
	)
)

(instance hutUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 6))
			(1
				(tromp number: (SoundFX 66) init: play:)
				(ShakeScreen 3)
				(= cycles 5)
			)
			(2
				(messager say: N_HUTUP NULL NULL 1)
				(self cue:)
			)
			(3 (= cycles 5))
			(4
				(tromp play:)
				(Bclr fBabaHutOpen)
				(HandsOn)
				(rm33 setScript: 0)
			)
		)
	)
)

(instance tromp of Sound
	(properties
		number 66
		priority 1
	)
)
