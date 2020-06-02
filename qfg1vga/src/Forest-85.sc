;;; Sierra Script 1.0 - (do not remove this comment)
(script# 85)
(include game.sh) (include "85.shm")
(use Main)
(use RandomEncounter)
(use Procs)
(use LoadMany)
(use Wander)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm85 0
)

(local
	antwerpSplit
	oldEgoX
	oldEgoY
	oldCycleSpeed
)
(instance rm85 of EncRoom
	(properties
		picture 701
		style $0008
		encChance 30
		entrances (| reEAST reWEST)
		illBits -28672
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init:)
		(Load VIEW 700)
		(if (Btst fAntwerpInSky)
			(Load SCRIPT 970)
			(curRoom encChance: 0)
			(LoadMany VIEW 590 85)
			(LoadMany SOUND 9 10 11)
			(antFalls number: (SoundFX 9) loop: 1 init:)
			(antSplats number: (SoundFX 10) loop: 1 init:)
			(babyBoing number: (SoundFX 11) loop: 1 init:)
		)
		(NormalEgo)
		(= oldCycleSpeed (ego cycleSpeed?))
		(northBush addToPic:)
		(northBush2 addToPic:)
		(southBush addToPic:)
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
		(if (and (Btst fAntwerpInSky) (> (ego x?) 160))
			(Bclr fAntwerpInSky)
			(curRoom setScript: antwerped)
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn85)
		(super dispose:)
	)
)

(instance northBush of View
	(properties
		x 120
		y 51
		noun N_NORTHBUSH
		view 700
	)
)

(instance northBush2 of View
	(properties
		x 105
		y 73
		noun N_NORTHBUSH2
		view 700
		loop 1
	)
)

(instance southBush of View
	(properties
		x 58
		y 167
		noun N_SOUTHBUSH
		view 700
		loop 3
		priority 15
	)
)

(instance antwerp of Actor
	(properties
		noun N_ANTWERP
		yStep 6
		view 590
		xStep 4
	)
	
	(method (init)
		(= nightPalette 1590)
		(PalVary PALVARYTARGET 1590)
		(kernel_128 590)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(12
				(if (> (antwerped state?) 1)
					(HandsOff)
					(messager say: N_ANTWERP V_SWORD)
				else
					(= antwerpSplit TRUE)
					(curRoom setScript: splat)
				)
			)
			(16
				(if (> (antwerped state?) 1)
					(HandsOff)
					(messager say: N_ANTWERP V_SWORD)
				else
					(= antwerpSplit TRUE)
					(curRoom setScript: splat)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance a1 of Actor
	(properties
		noun N_BABY
		yStep 4
		view 590
		xStep 4
	)
	
	(method (doit)
		(if (== (self cel?) 0) (babyBoing loop: 1 play:))
		(super doit:)
	)
)

(instance a2 of Actor
	(properties
		noun N_BABY
		yStep 4
		view 590
		xStep 4
	)
)

(instance a3 of Actor
	(properties
		noun N_BABY
		yStep 4
		view 590
		xStep 4
	)
)

(instance a4 of Actor
	(properties
		noun N_BABY
		yStep 4
		view 590
		xStep 4
	)
)

(instance a5 of Actor
	(properties
		noun N_BABY
		yStep 4
		view 590
		xStep 4
	)
)

(instance antFalls of Sound
	(properties
		number 9
	)
)

(instance antSplats of Sound
	(properties
		number 10
	)
)

(instance babyBoing of Sound
	(properties
		number 11
	)
)

(instance egoActions of Actions
	
	(method (doVerb theVerb)
		(return
			(if (or (== theVerb V_DAGGER) (== theVerb V_SWORD))
				0
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance antwerped of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(antFalls play:)
				(User canControl: 0)
				(ego
					setMotion: 0
					view: 85
					setLoop: 1
					cycleSpeed: 12
					actions: egoActions
					setCycle: EndLoop self
				)
			)
			(1
				(User canControl: 0)
				(antwerp
					init:
					ignoreActors: 1
					ignoreHorizon: 1
					illegalBits: 0
					setLoop: 2
					cel: 0
					setCycle: 0
					posn: (ego x?) -30
					setPri: (+ (ego priority?) 1)
					setMotion: MoveTo (ego x?) (- (ego y?) 48) self
				)
			)
			(2
				(User canControl: 0)
				(antwerp
					setMotion: MoveTo (ego x?) (+ (ego y?) 4) self
				)
			)
			(3
				(HandsOff)
				(antFalls stop:)
				(antSplats play:)
				(ego view: 85 cel: 0 setLoop: 2 setCel: 0 setCycle: Forward)
				(antwerp setCycle: EndLoop self)
			)
			(4
				(antwerp setCycle: BegLoop setMotion: MoveTo 270 0)
				(= cycles 80)
			)
			(5 (EgoDead 5 6))
		)
	)
)

(instance splat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(antFalls stop:)
				(User canControl: FALSE canInput: FALSE)
				(ego
					view: 85
					setLoop: 0
					cycleSpeed: 0
					actions: 0
					setCycle: EndLoop self
				)
			)
			(1
				(antwerp
					ignoreActors: 1
					ignoreHorizon: 1
					illegalBits: 0
					setLoop: 2
					cel: 0
					setCycle: 0
					setPri: (+ (ego priority?) 1)
					setMotion: MoveTo (ego x?) (- (ego y?) 47) self
				)
			)
			(2
				(antSplats play:)
				(ego setCycle: BegLoop self)
				(Bset fAntwerpSplit)
				(antwerp setLoop: 5 setCycle: EndLoop)
			)
			(3
				(= oldEgoX (ego x?))
				(= oldEgoY (ego y?))
				(antwerp
					setLoop: 5
					cel: 0
					setStep: 4 4
					posn: oldEgoX oldEgoY
					setMotion: MoveTo oldEgoX (+ oldEgoY 16)
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(a1
					setLoop: 7
					posn: oldEgoX oldEgoY
					ignoreActors:
					init:
					setCycle: Forward
					setMotion: MoveTo (+ oldEgoX 16) (+ oldEgoY 22) self
				)
				(a2
					setLoop: 8
					posn: oldEgoX oldEgoY
					ignoreActors:
					init:
					setCycle: Forward
					setMotion: MoveTo (- oldEgoX 10) (- oldEgoY 10)
				)
				(a3
					setLoop: 7
					posn: oldEgoX oldEgoY
					ignoreActors:
					init:
					setCycle: Forward
					setMotion: MoveTo (+ oldEgoX 8) (+ oldEgoY 15)
				)
				(if (> howFast 0)
					(a4
						setLoop: 7
						posn: oldEgoX oldEgoY
						ignoreActors:
						init:
						setCycle: Forward
						setMotion: MoveTo (- oldEgoX 14) (- oldEgoY 5)
					)
					(a5
						setLoop: 8
						posn: oldEgoX oldEgoY
						ignoreActors:
						init:
						setCycle: Forward
						setMotion: MoveTo (+ oldEgoX 16) (- oldEgoY 10)
					)
				)
			)
			(4
				(a1 ignoreActors: 0 illegalBits: -2 setMotion: Wander)
				(a2 ignoreActors: 0 illegalBits: -2 setMotion: Wander)
				(a3 ignoreActors: 0 illegalBits: -2 setMotion: Wander)
				(if (> howFast slow)
					(a4 ignoreActors: 0 illegalBits: -2 setMotion: Wander)
					(a5 ignoreActors: 0 illegalBits: -2 setMotion: Wander)
				)
				(NormalEgo)
				(User canControl: TRUE canInput: TRUE)
				(ego cycleSpeed: oldCycleSpeed)
				(client setScript: 0)
			)
		)
	)
)
