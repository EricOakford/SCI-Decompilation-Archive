;;; Sierra Script 1.0 - (do not remove this comment)
(script# 77)
(include game.sh)
(use Main)
(use CastDart)
(use ThrowKnife)
(use ThrowRock)
(use CastDazz)
(use Target)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm77 0
)

(local
	[stagProperties 4]
	distToStag
	stagIsHere
	stagState
	spellCast
)
(instance rm77 of Room
	(properties
		picture 701
		style DISSOLVE
		horizon 90
		west 76
	)
	
	(method (init)
		(Load VIEW vBushes)
		(if (and Night (== prevRoomNum 70))
			(Load SCRIPT 295)
		)
		(if (and (not Night) (!= prevRoomNum 76) (Btst fStagHere))
			(Load VIEW vStag)
		)
		(super init:)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(cond 
			((and (not (Btst fMetDryad)) (not monsterNum))
				(Bset fStagHere)
				(= monsterNum FALSE)
			)
			((and (Btst fAgreedToHelpDryad) (not monsterNum) (not (Btst fStagHere)))
			(switch (Random 0 1)
				(0 (Bclr fStagHere))
				(1 (Bset fStagHere))
			))
		)
		(ego init:)
		(if (and (not Night) (!= prevRoomNum 76) (Btst fStagHere))
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
			(70
				(ego posn: 180 92 setMotion: MoveTo 180 110)
				(if (Btst fFaeryAttention)
					(User canControl: FALSE)
					(User canInput: FALSE)
					(Bclr fFaeryAttention)
					(self setScript: (ScriptID 295 0))
				)
			)
			(76
				(ego posn: 1 140 setMotion: MoveTo 40 140)
			)
			(78
				(ego posn: 318 140 setMotion: MoveTo 253 140)
			)
		)
		(= stagState 0)
		(= stagIsHere (Btst fStagHere))
		(addToPics add: southBush eachElementDo: #init doit:)
	)
	
	(method (doit)
		(switch (ego edgeHit?)
			(EAST
				(Bclr fStagHere)
				(curRoom newRoom: 78)
			)
			(NORTH
				(Bclr fStagHere)
				(curRoom newRoom: 70)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset fBeenIn77)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'cast>')
						(switch (= spellCast (SaidSpell event))
							(DETMAGIC
								(if (CastSpell spellCast)
									(HighPrint 77 0)
									;You detect a strange, magical aura in this place.
								)
							)
							(DAZZLE
								(if (CastSpell spellCast)
									(CastDazz)
									(HighPrint 77 1)
									;Your spell has no effect.
								)
							)
							(FLAMEDART
								(cond 
									((not (cast contains: stag)) (event claimed: FALSE))
									((CastSpell spellCast)
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
						(= temp0 (if (cast contains: stag) stag else 0))
						(if (ThrowKnife temp0)
							(if (Btst fStagHere)
								(Bset fStagHurt)
							)
							(if (cast contains: stag)
								(Face ego stag)
								(RedrawCast)
							)
						)
					)
					((Said 'throw/boulder')
						(= temp0 (if (cast contains: stag) stag else 0))
						(if (ThrowRock temp0)
							(if (Btst fStagHere)
								(Bset fStagHurt)
							)
							(if (cast contains: stag)
								(Face ego stag)
								(RedrawCast)
							)
						)
					)
					((Said 'climb,ride/buck,buck')
						(if (Btst fStagHere)
							(HighPrint 77 2)
							;He's beyond your reach.
						else
							(CantDo)
						)
					)
					((Said 'fight,kill,beat,chop/buck,buck')
						(if (Btst fStagHere)
							(Bset fStagHurt)
							(HighPrint 77 3)
							;The stag seems to be magically protected.
						else
							(CantDo)
						)
					)
					((Said 'look/buck,buck')
						(HighPrint 77 4)
						;You don't see a stag here.
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
					setMotion: MoveTo -30 (stag y?) self
				)
			)
			(5
				(User canControl: TRUE)
				(= stagIsHere FALSE)
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
						setMotion: MoveTo -200 (stag y?) self
					)
				else
					(self cue:)
				)
			)
			(5
				(if (< (stag x?) -30) (self cue:))
			)
			(6 (stag dispose:))
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
					(HighPrint 77 6)
					;The beautiful white stag is foraging for food.
				)
				(5
					(HighPrint 77 7)
					;The white stag takes a mighty leap.
				)
				(else
					(HighPrint 77 8)
					;You seem to have startled the white stag.
				)
			)
		)
	)
	
	(method (getHurt)
		(+= missedDaggers hitDaggers)
		(= hitDaggers 0)
		(HighPrint 77 5)
		;The stag looks more surprised than hurt.
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
