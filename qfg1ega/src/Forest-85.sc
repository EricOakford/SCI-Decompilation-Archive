;;; Sierra Script 1.0 - (do not remove this comment)
(script# 85)
(include game.sh)
(use Main)
(use RandomEncounter)
(use LoadMany)
(use Wander)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm85 0
)

(local
	antwerpSplit
	antwerpX
	antwerpY
)
(instance rm85 of EncRoom
	(properties
		picture 701
		style DISSOLVE
		horizon 90
		east 86
		west 84
		encChance 30
		entrances (| reEAST reWEST)
		illBits (| cWHITE cLRED)
	)
	
	(method (init)
		(super init:)
		(Load VIEW vBushes)
		(if (Btst fAntwerpInSky)
			(Load SCRIPT WANDER)
			(curRoom encChance: 0)
			(LoadMany VIEW vAntwerp vEgoKillAntwerp)
			(LoadMany SOUND
				(SoundFX 9)
				(SoundFX 10)
				(SoundFX 11)
			)
			(antFalls number: (SoundFX 9) loop: 1 init:)
			(antSplats number: (SoundFX 10) loop: 1 init:)
			(babyBoing number: (SoundFX 11) loop: 1 init:)
		)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(if (not monsterNum)
			(ego init:)
		)
		(switch prevRoomNum
			(84
				(ego posn: 1 140 setMotion: MoveTo 32 140)
			)
			(86
				(ego posn: 318 140 setMotion: MoveTo 0 140)
			)
		)
		(addToPics
			add: northBush southBush
			eachElementDo: #init
			doit:
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
	
	(method (doit)
		(super doit:)
		(if (and (> (ego x?) 140) (Btst fAntwerpInSky))
			(Bclr fAntwerpInSky)
			(curRoom setScript: antwerped)
		)
	)
	
	(method (dispose)
		(Bset fBeenIn85)
		(DisposeScript WANDER)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'look[/!*,forest,greenery,clearing]')
						(if (> (cast size?) 2)
							(HighPrint 85 0)
							;You seem to have caused an Antwerp POPulation EXPLOSION!
						else
							(event claimed: FALSE)
						)
					)
					((Said 'look/antwerp,baby')
						(if (> (cast size?) 2)
							(HighPrint 85 0)
							;You seem to have caused an Antwerp POPulation EXPLOSION!
						else
							(HighPrint 85 1)
							;You see no Antwerps here.
						)
					)
					((Said 'capture,kill,beat,get,attack,fight,play/antwerp,baby')
						(if (> (cast size?) 2)
							(HighPrint 85 2)
							;The bouncing baby Antwerps are all so cute, you can't bring yourself to interfere with their playing.
						else
							(HighPrint 85 1)
							;You see no Antwerps here.
						)
					)
				)
			)
		)
	)
)

(instance northBush of PicView
	(properties
		y 82
		x 194
		view vBushes
		loop 2
	)
)

(instance southBush of PicView
	(properties
		y 207
		x 158
		view vBushes
		loop 2
		cel 1
		priority 15
	)
)

(instance antwerp of Actor
	(properties
		yStep 20
		view vAntwerp
		xStep 8
	)
)

(instance antwerped of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr fAntwerpInSky)
				(antFalls play:)
				(User canControl: FALSE)
				(ego
					setMotion: 0
					view: vEgoKillAntwerp
					setLoop: 1
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(User canControl: FALSE)
				(antwerp
					init:
					ignoreActors: TRUE
					ignoreHorizon: TRUE
					illegalBits: 0
					setLoop: 2
					cel: 0
					setCycle: 0
					posn: 90 -30
					setPri: (+ (ego priority?) 1)
					setMotion: MoveTo (ego x?) (+ (ego y?) 4) self
				)
			)
			(2
				(antFalls stop:)
				(antSplats play:)
				(ego
					view: vEgoKillAntwerp
					cel: 0
					setLoop: 2
					setCel: -1
					cycleSpeed: 1
					setCycle: Forward
				)
				(antwerp setCycle: EndLoop self)
			)
			(3
				(antwerp setCycle: BegLoop setMotion: MoveTo 270 0)
				(= cycles 80)
			)
			(4
				(EgoDead 85 7
					#title {Trounced by a bounce!}
					#icon vIcons 0 0
					;You're obviously in no shape to continue the game.
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'look,search/')
						(CenterPrint 85 3)
						;Looking up, you can see a small, blue dot in the sky, getting bigger... and bigger... and BIGGER!
					)
					((Said 'use,lift,draw/blade,dagger,weapon')
						(if (self state?)
							(CenterPrint 85 4)
							;Too late!
						else
							(= antwerpSplit TRUE)
							(curRoom setScript: splat)
						)
					)
					((Said 'cast/')
						(CenterPrint 85 5)
						;There's no time for that!
					)
					((Said 'run,escape/')
						(CenterPrint 85 6)
						;Where to? There is no escaping the hurtling blue blob.
					)
				)
			)
		)
	)
)

(instance antFalls of Sound
	(properties
		priority 8
	)
)

(instance antSplats of Sound
	(properties
		priority 10
	)
)

(instance babyBoing of Sound
	(properties
		priority 12
	)
)

(instance a1 of Actor
	(properties
		yStep 4
		view vAntwerp
		xStep 4
	)
	
	(method (doit)
		(if (== (self cel?) 0)
			(babyBoing loop: 1 play:)
		)
		(super doit:)
	)
)

(instance a2 of Actor
	(properties
		yStep 4
		view vAntwerp
		xStep 4
	)
)

(instance a3 of Actor
	(properties
		yStep 4
		view vAntwerp
		xStep 4
	)
)

(instance a4 of Actor
	(properties
		yStep 4
		view vAntwerp
		xStep 4
	)
)

(instance a5 of Actor
	(properties
		yStep 4
		view vAntwerp
		xStep 4
	)
)

(instance splat of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE canInput: FALSE)
				(ego view: vEgoKillAntwerp setLoop: 0 cycleSpeed: 0 setCycle: EndLoop self)
			)
			(1
				(antwerp
					init:
					ignoreActors: TRUE
					ignoreHorizon: TRUE
					illegalBits: 0
					setLoop: 2
					cel: 0
					setCycle: 0
					posn: 90 -30
					setPri: (+ (ego priority?) 1)
					setMotion: MoveTo (ego x?) (- (ego y?) 12) self
				)
			)
			(2
				(antSplats play:)
				(ego setCycle: BegLoop self)
				(Bset fAntwerpSplit)
				(antwerp setLoop: 5 setCycle: EndLoop)
			)
			(3
				(= antwerpX (ego x?))
				(= antwerpY (ego y?))
				(antwerp
					setLoop: 5
					cel: 0
					setStep: 4 4
					posn: antwerpX antwerpY
					setMotion: MoveTo antwerpX (+ antwerpY 16)
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(a1
					setLoop: 6
					posn: antwerpX antwerpY
					ignoreActors:
					init:
					setCycle: Forward
					illegalBits: 0
					setMotion: MoveTo (+ antwerpX 16) (+ antwerpY 22) self
				)
				(a2
					setLoop: 7
					posn: antwerpX antwerpY
					ignoreActors:
					init:
					setCycle: Forward
					illegalBits: 0
					setMotion: MoveTo (- antwerpX 10) (- antwerpY 10)
				)
				(a3
					setLoop: 6
					posn: antwerpX antwerpY
					ignoreActors:
					init:
					setCycle: Forward
					illegalBits: 0
					setMotion: MoveTo (+ antwerpX 8) (+ antwerpY 15)
				)
				(if (> howFast slow)
					(a4
						setLoop: 7
						posn: antwerpX antwerpY
						ignoreActors:
						init:
						setCycle: Forward
						illegalBits: 0
						setMotion: MoveTo (- antwerpX 14) (- antwerpY 5)
					)
					(a5
						setLoop: 6
						posn: antwerpX antwerpY
						ignoreActors:
						init:
						setCycle: Forward
						illegalBits: 0
						setMotion: MoveTo (+ antwerpX 16) (- antwerpY 10)
					)
				)
			)
			(4
				(a1 ignoreActors: 0 illegalBits: cWHITE setMotion: Wander)
				(a2 ignoreActors: 0 illegalBits: cWHITE setMotion: Wander)
				(a3 ignoreActors: 0 illegalBits: cWHITE setMotion: Wander)
				(if (> howFast slow)
					(a4 ignoreActors: 0 illegalBits: cWHITE setMotion: Wander)
					(a5 ignoreActors: 0 illegalBits: cWHITE setMotion: Wander)
				)
				(NormalEgo)
				(User canControl: TRUE canInput: TRUE)
				(client setScript: 0)
			)
		)
	)
)
