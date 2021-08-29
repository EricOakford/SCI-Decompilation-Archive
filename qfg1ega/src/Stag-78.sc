;;; Sierra Script 1.0 - (do not remove this comment)
(script# 78)
(include game.sh)
(use Main)
(use CastDart)
(use ThrowKnife)
(use ThrowRock)
(use CastDazz)
(use Target)
(use LoadMany)
(use Wander)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm78 0
)

(local
	antwerpSplit
	[stagProperties 4]
	distToStag
	stagState
	antwerpX
	antwerpY
)
(instance rm78 of Room
	(properties
		picture 700
		style DISSOLVE
		horizon 90
		north 71
		east 79
		south 84
		west 77
	)
	
	(method (init)
		(super init:)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
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
		(ego init:)
		(if (Btst fAntwerpInSky)
			(Load SCRIPT WANDER)
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
		(if
			(and
				(not Night)
				(not (Btst fAntwerpInSky))
				(!= prevRoomNum 77)
				(Btst fStagHere)
			)
			(Load VIEW vStag)
			(= [stagProperties 0] (Random 90 116))
			(= [stagProperties 1] (Random 132 154))
			(= [stagProperties 2] (Random 0 1))
			(= [stagProperties 3] (Random 1 30))
			(stag
				view: vStag
				x: [stagProperties 0]
				y: [stagProperties 1]
				setScript: stagScript
				init:
			)
		else
			(Bclr fStagHere)
		)
		(switch prevRoomNum
			(71
				(ego posn: 140 92 setMotion: MoveTo 140 121)
			)
			(77
				(ego posn: 1 140 setMotion: MoveTo 40 140)
			)
			(84
				(ego posn: 160 188 setMotion: MoveTo 160 154)
			)
			(79
				(ego posn: 318 140 setMotion: MoveTo 234 140)
			)
		)
		(= stagState 0)
	)
	
	(method (doit)
		(super doit:)
		(if (and (< (ego y?) 140) (Btst fAntwerpInSky))
			(Bclr fAntwerpInSky)
			(curRoom setScript: antwerped)
		)
	)
	
	(method (dispose)
		(Bset fBeenIn78)
		(DisposeScript WANDER)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp spell targObj)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'cast>')
						(switch (= spell (SaidSpell event))
							(DETMAGIC
								(if (CastSpell spell)
									(HighPrint 78 0)
								)
							)
							(DAZZLE
								(if (CastSpell spell) (CastDazz)
									(HighPrint 78 1)
								)
							)
							(FLAMEDART
								(cond 
									((not (cast contains: stag))
										(event claimed: FALSE)
									)
									((CastSpell spell)
										(Bset fStagHurt)
										(Face ego stag)
										(RedrawCast)
										(CastDart stag)
									)
								)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					((Said 'throw/dagger,dagger')
						(= targObj (if (cast contains: stag) stag else 0))
						(if
							(and
								(ThrowKnife targObj)
								(cast contains: stag)
							)
							(Bset fStagHurt)
							(Face ego stag)
							(RedrawCast)
						)
					)
					((Said 'throw/boulder')
						(= targObj (if (cast contains: stag) stag else 0))
						(if (and (ThrowRock targObj) (cast contains: stag))
							(Bset fStagHurt)
							(Face ego stag)
							(RedrawCast)
						)
					)
					((Said 'climb,ride/buck')
						(if (Btst fStagHere)
							(HighPrint 78 2)
							;He's beyond your reach.
							else
							(HighPrint 78 3)
							;Huh?
						)
					)
					((Said 'look/buck,buck')
						(HighPrint 78 4)
						;You don't see a stag here.
					)
					((Said 'fight,kill,beat,chop/buck')
						(if (Btst fStagHere)
							(Bset fStagHurt)
							(HighPrint 78 5)
							;The stag seems to be magically protected.
						else
							(CantDo)
						)
					)
					((Said 'look/antwerp,baby')
						(if (> (cast size?) 2)
							(HighPrint 78 6)
							;You seem to have caused an Antwerp POPulation EXPLOSION!
						else
							(HighPrint 78 7)
							;You see no Antwerps here.
						)
					)
					((Said 'play,capture,kill,beat,get,attack,fight/antwerp,baby')
						(if (> (cast size?) 2)
							(HighPrint 78 8)
							;The bouncing baby Antwerps are all so cute, you can't bring yourself to interfere with their playing.
						else
							(HighPrint 78 7)
							;You see no Antwerps here.
						)
					)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance stagScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ChangeGait MOVE_WALK FALSE)
				(User canControl: FALSE)
				(if [stagProperties 3]
					(= stagState 0)
					(if [stagProperties 2]
						(stag loop: 6 cycleSpeed: 3 setCycle: Forward)
					else
						(stag loop: 7 cycleSpeed: 3 setCycle: Forward)
					)
					(= cycles [stagProperties 3])
				else
					(self cue:)
				)
			)
			(1
				(if [stagProperties 3]
					(stag setCycle: EndLoop self)
				else
					(self cue:)
				)
			)
			(2
				(if [stagProperties 3]
					(= stagState 2)
					(if [stagProperties 2]
						(stag loop: 4 cel: 7 cycleSpeed: 1 setCycle: BegLoop self)
					else
						(stag loop: 5 cel: 7 cycleSpeed: 1 setCycle: BegLoop self)
					)
				else
					(self cue:)
				)
			)
			(3
				(if [stagProperties 2]
					(= stagState 4)
					(stag
						loop: 2
						cel: 0
						cycleSpeed: 1
						xStep: 5
						moveSpeed: 1
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
					setMotion: MoveTo -40 (stag y?) self
				)
			)
			(5
				(User canControl: TRUE)
				(stag dispose:)
			)
		)
	)
)

(instance stagBolts of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE)
				(if (== stagState 0)
					(= stagState 1)
					(stag setCycle: EndLoop self)
				else
					(self cue:)
				)
			)
			(1
				(cond 
					((== stagState 1)
						(= stagState 3)
						(if [stagProperties 2]
							(stag loop: 4 cel: 7 cycleSpeed: 1 setCycle: BegLoop self)
						else
							(stag loop: 5 cel: 7 cycleSpeed: 1 setCycle: BegLoop self)
						)
					)
					((== stagState 2)
						(= stagState 3)
						(if [stagProperties 2]
							(stag setCycle: BegLoop self)
						else
							(stag setCycle: BegLoop self)
						)
					)
					(else (self cue:))
				)
			)
			(2
				(cond 
					([stagProperties 2]
						(cond 
							((== stagState 3)
								(= stagState 5)
								(stag
									loop: 2
									cel: 0
									cycleSpeed: 1
									xStep: 5
									moveSpeed: 1
									setCycle: EndLoop self
								)
							)
							((== stagState 4)
								(= stagState 5)
								(stag setCycle: EndLoop self)
							)
							(else (self cue:))
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
						view: vStagJump
						setLoop: 1
						illegalBits: 0
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
						setStep: 10 9
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
				)
			)
			(6
				(stag dispose:)
			)
		)
	)
)

(instance stag of TargActor
	(properties
		view vStag
	)
	
	(method (doit)
		(= distToStag (ego distanceTo: self))
		(if (!= script stagBolts)
			(cond 
				([stagProperties 2]
					(if (< distToStag 175)
						(self setScript: stagBolts)
					)
				)
				((< distToStag 120)
					(self setScript: stagBolts)
				)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if
			(or
				(MouseClaimed self event shiftDown)
				(Said 'look/buck,buck')
			)
			(event claimed: TRUE)
			(switch stagState
				(0
					(HighPrint 78 10)
					;The beautiful white stag is foraging for food.
					)
				(5
					(HighPrint 78 11)
					;The white stag takes a mighty leap.
					)
				(else
					(HighPrint 78 12)
					;You seem to have startled the white stag.
					)
			)
		)
	)
	
	(method (getHurt)
		(+= missedDaggers hitDaggers)
		(= hitDaggers 0)
		(HighPrint 78 9)
		;The stag looks more surprised than hurt.
		(if (!= script stagBolts)
			(self setScript: stagBolts)
		)
	)
)

(instance antwerp of Actor
	(properties
		yStep 16
		view vAntwerp
		xStep 2
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
				(EgoDead 78 17
					#title {Trounced by a bounce!}
					#icon vIcons 0 0)
					;You're obviously in no shape to continue the game.
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said '[<at,around][/!*,forest,greenery,clearing]')
						(if (> (cast size?) 2)
							(HighPrint 78 6)
							;You seem to have caused an Antwerp POPulation EXPLOSION!
						else
							(event claimed: FALSE)
						)
					)
					((Said 'look,search/')
						(CenterPrint 78 13)
						;Looking up, you can see a small, blue dot in the sky, getting bigger... and bigger... and BIGGER!
					)
					((Said 'use,lift,draw/blade,dagger,weapon')
						(if (self state?)
							(CenterPrint 78 14)
							;Too late!
						else
							(= antwerpSplit TRUE)
							(curRoom setScript: splat)
						)
					)
					((Said 'cast/')
						(CenterPrint 78 15)
						;There's no time for that!
					)
					((Said 'run,escape/')
						(CenterPrint 78 16)
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
					setMotion: MoveTo (+ antwerpX 16) (+ antwerpY 22) self
				)
				(a2
					setLoop: 7
					posn: antwerpX antwerpY
					ignoreActors:
					init:
					setCycle: Forward
					setMotion: MoveTo (- antwerpX 10) (- antwerpY 10)
				)
				(a3
					setLoop: 6
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
						setLoop: 6
						posn: antwerpX antwerpY
						ignoreActors:
						init:
						setCycle: Forward
						setMotion: MoveTo (+ antwerpX 16) (- antwerpY 10)
					)
				)
			)
			(4
				(a1 ignoreActors: 0 setMotion: Wander)
				(a2 ignoreActors: 0 setMotion: Wander)
				(a3 ignoreActors: 0 setMotion: Wander)
				(if (> howFast slow)
					(a4 ignoreActors: 0 setMotion: Wander)
					(a5 ignoreActors: 0 setMotion: Wander)
				)
				(NormalEgo)
				(User canControl: TRUE canInput: TRUE)
				(client setScript: 0)
			)
		)
	)
)
