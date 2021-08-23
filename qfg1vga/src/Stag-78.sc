;;; Sierra Script 1.0 - (do not remove this comment)
(script# 78)
(include game.sh) (include "78.shm")
(use Main)
(use CastDart)
(use ThrowKnife)
(use ThrowRock)
(use Target)
(use Procs)
(use PolyPath)
(use LoadMany)
(use Wander)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm78 0
)

(local
	[theSeconds 4]
	distToStag
	stagState
	antwerpX
	antwerpY
	saveSpeed
)
(instance rm78 of Room
	(properties
		noun N_ROOM
		picture 700
		style DISSOLVE
		horizon 63
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init:)
		(StatusLine enable:)
		(NormalEgo)
		(= saveSpeed (ego cycleSpeed?))
		(cond 
			((and (not (Btst fMetDryad)) (not monsterNum))
				(Bset fStagHere)
				(= monsterNum 0)
			)
			((and (Btst fAgreedToHelpDryad) (not monsterNum))
				(switch (Random 0 1)
					(0 (Bclr fStagHere))
					(1 (Bset fStagHere))
				)
			)
		)
		(if (Btst fAntwerpInSky)
			(Load RES_SCRIPT WANDER)
			(LoadMany RES_VIEW 590 85)
			(LoadMany RES_SOUND
				(SoundFX 9)
				(SoundFX 10)
				(SoundFX 11)
			)
			(antFalls number: (SoundFX 9) loop: 1 init:)
			(antSplats number: (SoundFX 10) loop: 1 init:)
			(babyBoing number: (SoundFX 11) loop: 1 init:)
		)
		(if
			(and
				(not Night)
				(not (Btst fAntwerpInSky))
				(!= prevRoomNum 77)
				(Btst fStagHere)
			)
			(Load RES_VIEW 78)
			(= [theSeconds 0] 89)
			(= [theSeconds 1] 123)
			(= [theSeconds 2] 0)
			(= [theSeconds 3] (Random 5 30))
			(stag view: 78 x: 166 y: 133 setScript: stagScript init:)
		else
			(Bclr fStagHere)
		)
		(= stagState 0)
	)
	
	(method (doit)
		(cond 
			(script)
			((and (Btst fAntwerpInSky) (< (ego y?) 140))
				(curRoom setScript: antwerped)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn78)
		(super dispose:)
	)
)

(instance stag of TargActor
	(properties
		noun N_STAG
		view 78
	)
	
	(method (init)
		(HandsOff)
		(ego setScript: stagWalkIn)
		(super init:)
	)
	
	(method (doit)
		(= distToStag (ego distanceTo: self))
		(if (!= script stagBolts)
			(cond 
				([theSeconds 2]
					(if (< distToStag 100)
						(self setScript: stagBolts)
					)
				)
				((< distToStag 75)
					(self setScript: stagBolts)
				)
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROCK
				(ThrowRock self)
			)
			(V_DAGGER
				(ThrowKnife self)
			)
			(V_FLAME
				(CastDart self)
			)
			(V_SWORD
				(self setScript: stagBolts)
			)
			(else 
				(switch stagState
					(0
						(messager say: N_STAG V_LOOK C_STAGFORAGING)
					)
					(5
						(messager say: N_STAG V_LOOK C_STAGLEAP)
					)
					(else
						(messager say: N_STAG V_LOOK)
					)
				)
			)
		)
	)
	
	(method (getHurt)
		(+= missedDaggers hitDaggers)
		(= hitDaggers 0)
		(Bset fStagHurt)
		(if (and (!= script stagBolts) (!= script stagHurt))
			(self setScript: stagHurt)
		)
	)
)

(instance stagHurt of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_STAG V_FLAME NULL 0 self)
			)
			(1
				(client setScript: stagBolts)
			)
		)
	)
)

(instance stagWalkIn of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 90)
			)
			(1
				(HandsOff)
				(switch prevRoomNum
					(71
						(ego setMotion: PolyPath 160 70 self)
					)
					(79
						(ego setMotion: PolyPath 260 155 self)
					)
					(84
						(ego setMotion: PolyPath 160 165 self)
					)
					(else 
						(ego setMotion: PolyPath 80 100 self)
					)
				)
			)
			(2
				(HandsOff)
				(switch prevRoomNum
					(71 (ego loop: 2))
					(84 (ego loop: 3))
					(77 (ego loop: 0))
					(else  (ego loop: 1))
				)
				(= ticks 30)
			)
			(3
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance stagScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ChangeGait MOVE_WALK FALSE)
				(User canControl: FALSE)
				(if [theSeconds 3]
					(= stagState 0)
					(if [theSeconds 2]
						(stag loop: 6 cycleSpeed: 12 moveSpeed: 12 setCycle: Forward)
					else
						(stag loop: 7 cycleSpeed: 12 moveSpeed: 12 setCycle: Forward)
					)
					(= seconds [theSeconds 3])
				else
					(self cue:)
				)
			)
			(1
				(if [theSeconds 3]
					(stag setCycle: EndLoop self)
				else
					(self cue:)
				)
			)
			(2
				(if [theSeconds 3]
					(= stagState 2)
					(if [theSeconds 2]
						(stag
							loop: 4
							cel: 7
							cycleSpeed: 6
							moveSpeed: 6
							setCycle: BegLoop self
						)
					else
						(stag
							loop: 5
							cel: 7
							cycleSpeed: 6
							moveSpeed: 6
							setCycle: BegLoop self
						)
					)
				else
					(self cue:)
				)
			)
			(3
				(if [theSeconds 2]
					(= stagState 4)
					(stag
						loop: 2
						cel: 0
						cycleSpeed: 6
						moveSpeed: 6
						xStep: 5
						yStep: 3
						setCycle: EndLoop self
					)
				else
					(self cue:)
				)
			)
			(4
				(User canControl: TRUE)
				(= stagState 6)
				(stag
					loop: 1
					cel: 0
					setCycle: Forward
					setMotion: PolyPath -40 (stag y?) self
				)
			)
			(5
				(stag posn: -250 (stag y?))
				(self cue:)
			)
			(6
				(User canControl: TRUE)
				(Bclr fStagHere)
				(stag dispose:)
			)
		)
	)
)

(instance stagBolts of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (== (ego script?) stagWalkIn))
					(HandsOff)
					(ego setCycle: 0 setMotion: 0)
				)
				(if (== stagState 0)
					(= stagState 1)
					(stag setCycle: EndLoop)
				else
					(self cue:)
				)
				(messager say: N_STAG V_LOOK NULL 0 self)
			)
			(1
				(stag cycleSpeed: 4 moveSpeed: 4)
				(cond 
					((== stagState 1)
						(= stagState 3)
						(if [theSeconds 2]
							(stag loop: 4 cel: 7 setCycle: BegLoop self)
						else
							(stag loop: 5 cel: 7 setCycle: BegLoop self)
						)
					)
					((== stagState 2)
						(= stagState 3)
						(stag setCycle: BegLoop self)
					)
					(else
						(self cue:)
					)
				)
			)
			(2
				(cond 
					([theSeconds 2]
						(cond 
							((== stagState 3)
								(= stagState 5)
								(stag loop: 2 cel: 0 xStep: 5 setCycle: EndLoop self)
							)
							((== stagState 4)
								(= stagState 5)
								(stag setCycle: EndLoop self)
							)
							(else
								(self cue:)
							)
						)
					)
					((== stagState 3)
						(= stagState 5)
						(self cue:)
					)
				)
			)
			(3
				(if (== stagState 5)
					(stag
						setLoop: 9
						cel: 0
						setStep: 2 2
						setCycle: CycleTo 2 1 self
					)
				else
					(self cue:)
				)
			)
			(4
				(User canControl: TRUE)
				(if (== stagState 5)
					(stag
						setStep: 12 9
						setCycle: Forward
						setMotion: MoveTo -50 (stag y?) self
					)
				else
					(self cue:)
				)
			)
			(5
				(if (< (stag x?) -30)
					(self cue:)
				else
					(stag
						setStep: 12 9
						setLoop: 9
						setCycle: Forward
						setMotion: MoveTo -50 (stag y?) self
					)
				)
			)
			(6
				(stag dispose:)
				(NormalEgo)
				(HandsOn)
				(if (== (ego script?) (ScriptID FOREST 1))
					((ScriptID FOREST 1) changeState: 4)
				else
					(ego setMotion: PolyPath 160 140 setScript: 0)
				)
				(self dispose:)
			)
		)
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

(instance antwerp of Actor
	(properties
		noun N_ANTWERP
		yStep 6
		view 590
		xStep 2
	)
	
	(method (init)
		(= nightPalette 1590)
		(PalVary PALVARYTARGET 1590)
		(AssertPalette 590)
		(super init:)
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_SWORD
				(if (> (antwerped state?) 1)
					(HandsOff)
					(messager say: N_ANTWERP V_SWORD)
				else
					(curRoom setScript: splat)
				)
			)
			(V_DAGGER
				(if (> (antwerped state?) 1)
					(HandsOff)
					(messager say: N_ANTWERP V_SWORD)
				else
					(curRoom setScript: splat)
				)
			)
			(else 
				(super doVerb: theVerb param2 &rest)
			)
		)
	)
)

(instance antwerped of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(antFalls play:)
				(User canControl: FALSE)
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
				(User canControl: FALSE)
				(antwerp
					init:
					ignoreActors: TRUE
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
				(User canControl: FALSE)
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
				(= seconds 3)
;				(= cycles 80)
			)
			(5
				(EgoDead C_DIE_ANTWERP C_DIE_ANTWERP_TITLE)
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
		priority 9
	)
)

(instance babyBoing of Sound
	(properties
		priority 12
	)
)

(instance a1 of Actor
	(properties
		noun 1
		yStep 4
		view 590
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

(instance splat of Script
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
					ignoreActors: TRUE
					ignoreHorizon: TRUE
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
					setLoop: 7
					posn: antwerpX antwerpY
					ignoreActors:
					init:
					setCycle: Forward
					setMotion: MoveTo (+ antwerpX 16) (+ antwerpY 22) self
				)
				(a2
					setLoop: 8
					posn: antwerpX antwerpY
					ignoreActors:
					init:
					setCycle: Forward
					setMotion: MoveTo (- antwerpX 10) (- antwerpY 10)
				)
				(a3
					setLoop: 7
					posn: antwerpX antwerpY
					ignoreActors:
					init:
					setCycle: Forward
					setMotion: MoveTo (+ antwerpX 8) (+ antwerpY 15)
				)
				(if (> howFast slow)
					(a4
						setLoop: 7
						posn: antwerpX antwerpY
						ignoreActors:
						init:
						setCycle: Forward
						setMotion: MoveTo (- antwerpX 14) (- antwerpY 5)
					)
					(a5
						setLoop: 8
						posn: antwerpX antwerpY
						ignoreActors:
						init:
						setCycle: Forward
						setMotion: MoveTo (+ antwerpX 16) (- antwerpY 10)
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
				(Bclr fAntwerpInSky)
				(ego cycleSpeed: saveSpeed)
				(client setScript: 0)
			)
		)
	)
)
