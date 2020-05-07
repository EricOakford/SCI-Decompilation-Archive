;;; Sierra Script 1.0 - (do not remove this comment)
(script# 74)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm74 0
)

(instance rm74 of EncRoom
	(properties
		picture 700
		style DISSOLVE
		horizon 90
		north 65
		east 75
		south 81
		west 73
		encChance 15
		ambushX 100
		ambushY 120
	)
	
	(method (init)
		(super init:)
		(Load VIEW vBushEyes)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(if (not monsterNum) (ego init:))
		(switch prevRoomNum
			(65
				(ego posn: 140 92 setMotion: MoveTo 140 190)
			)
			(73
				(ego posn: 1 140 setMotion: MoveTo 320 140)
			)
			(81
				(ego posn: 160 188 setMotion: MoveTo 160 0)
			)
			(75
				(ego posn: 318 140 setMotion: MoveTo 0 120)
			)
		)
		(eyes setPri: 5 init: stopUpd: setScript: peekABooScript)
		(if
			(not
				(OneOf prevRoomNum
					BEAR MINOTAUR SAURUS MANTRAY CHEETAUR GOBLIN TROLL OGRE SAURUSREX BRIGAND LEADER
				)
			)
			(= egoX (ego x?))
			(= egoY (ego y?))
		)
		(self setRegions: ENCOUNTER)
	)
	
	(method (dispose)
		(Bset VISITED_FOREST_74)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(if (Said 'look/eye')
					(HighPrint 74 0)
					;For a moment there, you thought you saw something.  But nothing is there now.
					)
			)
		)
		(super handleEvent: event)
	)
)

(instance peekABooScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 6 25)))
			(1 (eyes setCycle: EndLoop self))
			(2 (self changeState: 0))
		)
	)
)

(instance eyes of Prop
	(properties
		y 67
		x 305
		view vBushEyes
		cycleSpeed 1
	)
)
