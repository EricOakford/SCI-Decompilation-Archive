;;; Sierra Script 1.0 - (do not remove this comment)
(script# 77)
(include game.sh)
(use Main)
(use ThrowFlameDart)
(use ThrowDagger1)
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
	[theCycles 4]
	local4
	local5
	local6
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
		(if
		(and (not Night) (!= prevRoomNum 76) (Btst STAG_PRESENT))
			(Load VIEW vStag)
		)
		(super init:)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(NormalEgo)
		(cond 
			((and (not (Btst MET_DRYAD)) (not monsterNum)) (Bset STAG_PRESENT) (= monsterNum FALSE))
			(
			(and (Btst DRYAD_AGREED_HELP) (not monsterNum) (not (Btst STAG_PRESENT)))
			(switch (Random 0 1)
				(0 (Bclr STAG_PRESENT))
				(1 (Bset STAG_PRESENT))
			))
		)
		(ego init:)
		(if
		(and (not Night) (!= prevRoomNum 76) (Btst STAG_PRESENT))
			(= [theCycles 0] (Random 90 116))
			(= [theCycles 1] (Random 132 154))
			(= [theCycles 2] (Random 0 1))
			(= [theCycles 3] (Random 1 30))
			(stag
				view: vStag
				x: [theCycles 0]
				y: [theCycles 1]
				setScript: stagScript
				init:
			)
		else
			(Bclr STAG_PRESENT)
		)
		(switch prevRoomNum
			(70
				(ego posn: 180 92 setMotion: MoveTo 180 110)
				(if (Btst GOT_FAIRIES_ATTENTION)
					(User canControl: FALSE)
					(User canInput: FALSE)
					(Bclr GOT_FAIRIES_ATTENTION)
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
		(= local6 0)
		(= local5 (Btst STAG_PRESENT))
		(addToPics add: southBush eachElementDo: #init doit:)
	)
	
	(method (doit)
		(switch (ego edgeHit?)
			(EAST
				(Bclr STAG_PRESENT)
				(curRoom newRoom: 78)
			)
			(NORTH
				(Bclr STAG_PRESENT)
				(curRoom newRoom: 70)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset VISITED_STAG_77)
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
										(Bset STAG_HURT)
										(Face ego stag)
										(RedrawCast)
										(FlameCast stag)
									)
								)
							)
							(else  (event claimed: FALSE))
						)
					)
					((Said 'throw/dagger,dagger')
						(= temp0 (if (cast contains: stag) stag else 0))
						(if (KnifeCast temp0)
							(if (Btst STAG_PRESENT) (Bset STAG_HURT))
							(if (cast contains: stag)
								(Face ego stag)
								(RedrawCast)
							)
						)
					)
					((Said 'throw/boulder')
						(= temp0 (if (cast contains: stag) stag else 0))
						(if (RockCast temp0)
							(if (Btst STAG_PRESENT) (Bset STAG_HURT))
							(if (cast contains: stag)
								(Face ego stag)
								(RedrawCast)
							)
						)
					)
					((Said 'climb,ride/buck,buck') (if (Btst STAG_PRESENT)
							(HighPrint 77 2)
							;He's beyond your reach.
							else (PrintCantDoThat)))
					((Said 'fight,kill,beat,chop/buck,buck')
						(if (Btst STAG_PRESENT)
							(Bset STAG_HURT)
							(HighPrint 77 3)
							;The stag seems to be magically protected.
						else
							(PrintCantDoThat)
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ChangeGait MOVE_WALK 0)
				(User canControl: FALSE)
				(if [theCycles 3]
					(= local6 0)
					(if [theCycles 2]
						(stag loop: 6 cycleSpeed: 3 setCycle: Forward)
					else
						(stag loop: 7 cycleSpeed: 3 setCycle: Forward)
					)
					(= cycles [theCycles 3])
				else
					(self cue:)
				)
			)
			(1
				(if [theCycles 3]
					(stag setCycle: EndLoop self)
				else
					(self cue:)
				)
			)
			(2
				(if [theCycles 3]
					(= local6 2)
					(if [theCycles 2]
						(stag loop: 4 cel: 7 cycleSpeed: 1 setCycle: BegLoop self)
					else
						(stag loop: 5 cel: 7 cycleSpeed: 1 setCycle: BegLoop self)
					)
				else
					(self cue:)
				)
			)
			(3
				(if [theCycles 2]
					(= local6 4)
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
				(= local6 6)
				(stag
					loop: 1
					cel: 0
					setCycle: Forward
					setMotion: MoveTo -30 (stag y?) self
				)
			)
			(5
				(User canControl: TRUE)
				(= local5 0)
				(stag dispose:)
			)
		)
	)
)

(instance stagBolts of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE)
				(if (== local6 0)
					(= local6 1)
					(stag setCycle: EndLoop self)
				else
					(self cue:)
				)
			)
			(1
				(cond 
					((== local6 1)
						(= local6 3)
						(if [theCycles 2]
							(stag loop: 4 cel: 7 cycleSpeed: 1 setCycle: BegLoop self)
						else
							(stag loop: 5 cel: 7 cycleSpeed: 1 setCycle: BegLoop self)
						)
					)
					((== local6 2)
						(= local6 3)
						(if [theCycles 2]
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
					([theCycles 2]
						(cond 
							((== local6 3)
								(= local6 5)
								(stag
									loop: 2
									cel: 0
									cycleSpeed: 1
									xStep: 5
									moveSpeed: 1
									setCycle: EndLoop self
								)
							)
							((== local6 4) (= local6 5) (stag setCycle: EndLoop self))
							(else (self cue:))
						)
					)
					((== local6 3) (= local6 5) (self cue:))
				)
			)
			(3
				(if (== local6 5)
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
				(if (== local6 5)
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
		(= local4 (ego distanceTo: self))
		(if (!= script stagBolts)
			(cond 
				([theCycles 2] (if (< local4 175) (self setScript: stagBolts)))
				((< local4 120) (self setScript: stagBolts))
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
			(switch local6
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
		(= missedDaggers (+ missedDaggers hitDaggers))
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
