;;; Sierra Script 1.0 - (do not remove this comment)
(script# 74)
(include game.sh)
(use Main)
(use RandomEncounter)
(use Procs)
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
		horizon 65
		encChance 15
		ambushX 100
		ambushY 120
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init:)
		(Load VIEW 67)
		(StatusLine enable:)
		(NormalEgo)
		(theEyes
			setPri: 5
			init:
			stopUpd:
			setScript: peekABooScript
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
	
	(method (dispose)
		(Bset fBeenIn74)
		(super dispose:)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)

(instance peekABooScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 6 25))
			)
			(1
				(theEyes setCycle: EndLoop self)
			)
			(2
				(self changeState: 0)
			)
		)
	)
)

(instance theEyes of Prop
	(properties
		x 305
		y 67
		view 67
		loop 6
		cycleSpeed 3
	)
)
