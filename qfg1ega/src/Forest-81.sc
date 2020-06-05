;;; Sierra Script 1.0 - (do not remove this comment)
(script# 81)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm81 0
)

(instance rm81 of EncRoom
	(properties
		picture 703
		style DISSOLVE
		horizon 80
		north 74
		east 82
		south 87
		west 80
		encChance 20
	)
	
	(method (init)
		(super init:)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(if (not monsterNum) (ego init:))
		(hollowLog init: stopUpd:)
		(switch prevRoomNum
			(74
				(ego posn: 190 92 setMotion: MoveTo 190 190)
			)
			(82
				(ego posn: 318 140 setMotion: MoveTo 0 140)
			)
			(80
				(ego posn: 1 140 setMotion: MoveTo 320 140)
			)
			(87
				(ego posn: 200 188 setMotion: MoveTo 200 0)
			)
		)
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
		(Bset VISITED_FOREST_81)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look[<around,at][/forest,area]')
						(HighPrint 81 0)
						;There is a roaring sound off to the east.
						)
					(
						(or
							(MouseClaimed hollowLog event shiftDown)
							(Said 'look/log')
						)
						(HighPrint 81 1)
						;The hollow log looks somberly back with it's single giant eye
						;and you know at once that nothing is to be gained by investigating this gaunt relic of a more vertical past.
					)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance hollowLog of View
	(properties
		y 91
		x 281
		view vHollowLog
	)
)
