;;; Sierra Script 1.0 - (do not remove this comment)
(script# 33)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm33 0
)

(instance tromp of Sound
	(properties
		number 66
		priority 1
	)
)

(instance rm33 of EncRoom
	(properties
		picture 702
		style DISSOLVE
		horizon 90
		north 22
		east 34
		south 45
		encChance 35
		entrances (| reNORTH reEAST reSOUTH)
		illBits (| cWHITE cYELLOW)
	)
	
	(method (init)
		(Load VIEW vBushes)
		(if (== prevRoomNum 22)
			(Load SOUND (SoundFX 66))
		)
		(super init:)
		(mouseDownHandler add: self)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(if (not monsterNum) (ego init:))
		(switch prevRoomNum
			(22
				(if (Btst BABAYAGA_HUT_OPEN) (HandsOff))
				(ego posn: 130 91 setMotion: MoveTo 130 190)
			)
			(34
				(ego posn: 319 140 setMotion: MoveTo 0 140)
			)
			(45
				(ego posn: 120 189 setMotion: MoveTo 120 0)
			)
		)
		(westBush setPri: 7 ignoreActors: init: addToPic:)
		(if (and (Btst BABAYAGA_HUT_OPEN) (not (Btst BABAYAGA_FLY_AWAY)))
			(Bclr BABAYAGA_HUT_OPEN)
			(self setScript: hutUp)
		)
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
		(mouseDownHandler delete: self)
		(Bset VISITED_FOREST_33)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed ego event shiftDown)
				(HighPrint 33 0)
				;My feet are killing me.
				)
		)
	)
)

(instance westBush of View
	(properties
		y 176
		x 24
		view vBushes
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
				(HighPrint 33 1)
				;You feel the sound of dainty footsteps behind you.
				(self cue:)
			)
			(3 (= cycles 5))
			(4
				(tromp play:)
				(ShakeScreen 3)
				(Bclr BABAYAGA_HUT_OPEN)
				(HandsOn)
				(rm33 setScript: 0)
			)
		)
	)
)
