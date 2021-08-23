;;; Sierra Script 1.0 - (do not remove this comment)
(script# 76)
(include game.sh) (include "76.shm")
(use Main)
(use CastDart)
(use ThrowKnife)
(use ThrowRock)
(use CastDazzle)
(use Target)
(use Procs)
(use Print)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm76 0
	dryadTalker 1
)

(enum 1 ;Dryad states
	dryadAvailable
	dryadHere
	dryadOut
)

;Yes or No
(enum
	sayYes
	sayNo
)

(local
	dryadState
	magicAcornOnGround
	eatIt
	dryadHostile
	attackedStag
	spitCount
	usedFlameDart
)
(procedure (PickUpAcorn)
	(HandsOff)
	(if (and magicAcornOnGround (< (ego distanceTo: acorn) 80))
		(messager say: N_ROOM NULL C_GETMAGICACORN)
	else
		(messager say: N_ROOM NULL C_GETREGULARACORN 1)
	)
	(ego setScript: pickEmUp)
)

(procedure (EatAcorn)
	(messager say: N_ROOM NULL C_EATACORN)
)

(procedure (SayNoToDryad)
	(HandsOff)
	(if (Btst fAgreedToHelpDryad)
		(messager say: N_ROOM NULL C_DONTGIVESEED)
	else
		(messager say: N_ROOM NULL C_NOTFORESTFRIEND)
	)
	(dryad setScript: intoTree)
)

(procedure (SayYesToDryad)
	(HandsOff)
	(if (Btst fAgreedToHelpDryad)
		(if (ego has: iSeed)
			(dryad setScript: hasSeed)
		else
			(messager say: N_ROOM NULL C_DONTHAVEIT 0)
			(dryad setScript: intoTree)
		)
	else
		(dryad setScript: aidMeScript)
	)
)

(instance rm76 of Room
	(properties
		noun N_ROOM
		picture 76
		style DISSOLVE
	)
	
	(method (init)
		(if (and (Btst fStagHere) (not Night))
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							319 131
							181 131
							169 136
							139 131
							129 144
							156 149
							175 160
							271 160
							319 148
							319 189
							0 189
							0 0
							319 0
						yourself:
					)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							319 131
							181 131
							169 136
							52 136
							15 149
							156 149
							175 160
							271 160
							319 148
							319 189
							0 189
							0 0
							319 0
						yourself:
					)
			)
		)
		(if (not Night)
			(LoadMany RES_VIEW 79)
			(if (or (Btst fKilledFlower1) (Btst fKilledFlower2) (Btst fKilledFlower3))
				(LoadMany RES_SOUND (SoundFX 18) (SoundFX 27))
			)
		else
			(Load RES_SCRIPT 7)
		)
		(LoadMany RES_VIEW 77 72 510)
		(LoadMany RES_SOUND 97 98)
		(Load RES_SCRIPT JUMP)
		(if (Btst fStagHere)
			(Load RES_VIEW 78)
		)
		(super init:)
		(self
			setFeatures:
				mountain
				bigGreenTree
				brokenTree
				frontRock
				backRock
				bushes
				acorns
		)
		;UPGRADE
;;;		(mountain init:)
;;;		(bigGreenTree init:)
;;;		(brokenTree init:)
;;;		(frontRock init:)
;;;		(backRock init:)
;;;		(bushes init:)
;;;		(acorns init:)
		
		(NormalEgo)
		(ChangeGait MOVE_WALK FALSE)
		(= yesNoTimer 0)
		(if (not Night)
			(spitSound number: (SoundFX 18) init:)
			(gulpSound number: (SoundFX 27) init:)
			(seed
				illegalBits: 0
				setLoop: 4
				init:
				ignoreActors:
				setCycle: Forward
				stopUpd:
				hide:
			)
		)
		(if (and (not Night) (Btst fStagHere))
			(stag
				view: 78
				x: 318
				y: 145
				setScript: stagEntrance
				init:
				illegalBits: 0
			)
		else
			(ego
				posn: 318 130
				init:
				actions: egoActions
				setMotion: MoveTo 290 130
			)
			(Bclr fStagHere)
			(= dryadState dryadAvailable)
		)
		(miscSound init: play:)
	)
	
	(method (doit)
		(cond 
			((and attackedStag (not (ego script?)) (< (stag x?) 50))
				(= attackedStag 0)
				(Bset fStagHurt)
				(HandsOff)
				(ego setScript: priorTo)
			)
			(
				(and
					(not (ego script?))
					(not (stag script?))
					(not (dryad script?))
					(not (Btst fLearnedDispel))
					(== dryadState dryadHere)
					(or dryadHostile (Btst fStagHurt))
				)
				(HandsOff)
				(= dryadHostile FALSE)
				(dryad setScript: egoToStag)
			)
		)
		(cond 
			(
				(and
					(== (ego edgeHit?) EAST)
					(>= dryadState dryadAvailable)
					(not (== (ego script?) goTo77))
				)
				(Bclr fStagHere)
				(ego setScript: goTo77)
			)
			(
				(and
					(< (ego x?) 280)
					(== dryadState dryadAvailable)
					(not Night)
					(not (Btst fLearnedDispel))
					(or
						usedFlameDart
						(not (Btst fLearnedDispel))
						(Btst fStagHurt)
						(Btst fKilledFlower1)
						(Btst fKilledFlower2)
						(Btst fKilledFlower3)
					)
				)
				(if (ego script?)
					(ego setScript: 0)
				)
				(= dryadState dryadHere)
				(dryad init: setScript: outOfTree)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn76)
		(DisposeScript JUMP)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp targObj)
		(switch theVerb
			(V_LOOK
				(messager say: N_ROOM V_LOOK NULL 0)
			)
			(V_DO
				(messager say: N_ROOM V_DO)
			)
			(V_DETECT
				(if (== dryadState dryadHere)
					(messager say: N_ROOM V_DETECT C_DETECTDRYAD)
				else
					(messager say: N_ROOM V_DETECT)
				)
			)
			(V_DAZZLE
				(if (and (Btst fStagHere) (< (stag x?) 50))
					(Bset fStagHurt)
					(= attackedStag TRUE)
				)
				(if (== dryadState dryadHere)
					(= dryadHostile TRUE)
				)
				(CastDazz)
			)
			(V_FLAME
				(ego setScript: castADart)
			)
			(V_SLEEP
				(if (not Night)
					(messager say: N_ROOM V_SLEEP C_DAY)
					(DisposeScript 7)
				else
					(ego setScript: goToSleep)
				)
			)
			(V_DAGGER
				(= targObj (if (cast contains: stag) stag else 0))
				(if (ThrowKnife targObj)
					(if (== dryadState dryadHere)
						(= dryadHostile TRUE)
					)
					(if (Btst fStagHere)
						(= attackedStag TRUE)
						(Bset fStagHurt)
					)
					(if (cast contains: stag)
						(Face ego stag)
						(RedrawCast)
					)
				)
			)
			(V_OPEN
				(messager say: N_ROOM V_OPEN)
			)
			(V_ROCK
				(= targObj (if (cast contains: stag) stag else 0))
				(if (ThrowRock targObj)
					(if (== dryadState dryadHere)
						(= dryadHostile TRUE)
					)
					(if (Btst fStagHere) (= attackedStag TRUE)
						(Bset fStagHurt)
					)
					(if (cast contains: stag)
						(Face ego stag)
						(RedrawCast)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mountain of Feature
	(properties
		x 271
		y 129
		z 103
		noun N_MOUNTAIN
		nsTop 7
		nsLeft 224
		nsBottom 45
		nsRight 318
		sightAngle 40
	)
)

(instance bigGreenTree of Feature
	(properties
		x 131
		y 125
		noun N_DRYADTREE
		sightAngle 40
		onMeCheck cRED
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_DRYADTREE V_LOOK)
			)
			(V_DO
				(messager say: N_DRYADTREE V_DO)
			)
			(V_TALK
				(messager say: N_DRYADTREE V_TALK)
			)
			(V_DAGGER
				(if (== dryadState dryadHere)
					(messager say: N_DRYADTREE V_DAGGER C_DRYADHERE)
					(dryad setScript: egoToStag)
				else
					(messager say: N_DRYADTREE V_DAGGER)
				)
			)
			(V_SWORD
				(if (== dryadState dryadHere)
					(messager say: N_DRYADTREE V_DAGGER)
					(dryad setScript: egoToStag)
				else
					(messager say: N_DRYADTREE V_DAGGER)
				)
			)
			(V_CANDELABRA
				(if (== dryadState dryadHere)
					(messager say: N_DRYADTREE V_CANDELABRA C_DRYADHERE)
					(dryad setScript: egoToStag)
				)
			)
			(V_ACORN
				(messager say: N_DRYADTREE V_ACORN)
			)
			(56
				(if (== dryadState dryadHere)
					(messager say: N_DRYADTREE V_CANDELABRA C_DRYADHERE)
					(dryad setScript: egoToStag)
				else
					(messager say: N_DRYADTREE V_CANDELABRA)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance brokenTree of Feature
	(properties
		x 131
		y 184
		noun N_BROKENTREE
		sightAngle 40
		onMeCheck cBLUE
	)
)

(instance bushes of Feature
	(properties
		x 131
		y 134
		noun N_BUSHES
		sightAngle 40
		onMeCheck cMAGENTA
	)
)

(instance frontRock of Feature
	(properties
		x 131
		y 180
		noun N_ROCK
		sightAngle 40
		onMeCheck cGREEN
	)
)

(instance backRock of Feature
	(properties
		x 131
		y 44
		noun N_ROCK
		sightAngle 40
		onMeCheck cCYAN
	)
)

(instance acorns of Feature
	(properties
		x 195
		y 130
		noun N_ACORN
		nsTop 124
		nsLeft 163
		nsBottom 140
		nsRight 227
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if magicAcornOnGround
					(messager say: N_ACORN V_LOOK C_ACORNONGROUND)
				else
					(messager say: N_ACORN V_LOOK)
				)
			)
			(V_DO
				(PickUpAcorn)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theAcorn of Feature
	(properties
		x 271
		y 129
		z 103
		noun N_ACORN
		nsTop 127
		nsLeft 125
		nsBottom 134
		nsRight 136
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (and (== theVerb V_DO) (cast contains: acorn))
			(PickUpAcorn)
		else
			(acorns doVerb: theVerb &rest)
		)
	)
)

(instance acorn of Prop
	(properties
		x 130
		y 132
		noun N_ACORN
		view 77
		loop 1
	)
	
	(method (init)
		(= nightPalette 177)
		(PalVary PALVARYTARGET 177)
		(AssertPalette 77)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(PickUpAcorn)
		else
			(acorns doVerb: theVerb &rest)
		)
	)
)

(instance stag of TargActor
	(properties
		noun N_STAG
		view 78
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_STAG V_LOOK)
			)
			(V_DO
				(messager say: N_STAG V_DO)
			)
			(V_DAZZLE
				(if (and (Btst fStagHere) (< (stag x?) 50))
					(Bset fStagHurt)
					(= attackedStag TRUE)
				)
				(if (== dryadState dryadHere)
					(= dryadHostile TRUE)
				)
				(CastDazz)
			)
			(V_FLAME
				(= attackedStag TRUE)
				(Bset fStagHurt)
				(ego setScript: castADart)
			)
			(V_SWORD
				(= attackedStag TRUE)
				(if (== dryadState dryadHere)
					(messager say: N_DRYADTREE V_DAGGER C_DRYADHERE)
					(dryad setScript: egoToStag)
				else
					(Bset fStagHurt)
				)
			)
			(V_ROCK
				(Bset fStagHurt)
				(= attackedStag TRUE)
				(if (== dryadState dryadHere)
					(messager say: N_DRYADTREE V_DAGGER C_DRYADHERE)
					(dryad setScript: egoToStag)
				else
					(Bset fStagHurt)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (getHurt)
		(HandsOff)
		(+= missedDaggers hitDaggers)
		(= hitDaggers 0)
		(messager say: N_STAG)
	)
)

(instance seed of Actor
	(properties
		view 76
		loop 4
	)
	
	(method (init)
		(= nightPalette 176)
		(PalVary PALVARYTARGET 176)
		(AssertPalette 76)
		(super init:)
	)
)

(instance dryad of Actor
	(properties
		x 148
		y 128
		noun N_DRYAD
		view 76
		illegalBits $0000
	)
	
	(method (init)
		(= nightPalette 176)
		(PalVary PALVARYTARGET 176)
		(AssertPalette 76)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (== dryadState dryadHere)
					(messager say: N_DRYAD V_LOOK C_DRYADHERE)
				else
					(messager say: N_DRYAD V_LOOK NULL 0)
				)
			)
			(V_DO
				(if (Btst fStagHere)
					(messager say: N_DRYAD V_DO C_CANTREACHSTAG)
					(if (== dryadState dryadHere)
						(messager say: N_DRYAD V_DO C_DRYADHERE)
					)
				else
					(messager say: N_DRYAD V_DO C_GETREGULARACORN)
				)
			)
			(V_DAGGER
				(if (== dryadState dryadHere)
					(messager say: N_DRYADTREE V_DAGGER C_DRYADHERE)
					(dryad setScript: egoToStag)
				else
					(messager say: N_DRYAD V_DAGGER C_GETREGULARACORN)
				)
			)
			(V_SEED
				(dryad setScript: hasSeed)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance stagEntrance of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(stag
					setLoop: 1
					cel: 0
					xStep: 5
					posn: 319 150
					setCycle: Forward
					setMotion: MoveTo 200 140 self
				)
			)
			(1
				(stag setLoop: 5 cel: 0 cycleSpeed: 12 setCycle: EndLoop self)
			)
			(2
				(stag setLoop: 7 setCycle: Forward)
				(= cycles 30)
			)
			(3 (stag setCycle: EndLoop self))
			(4
				(stag setLoop: 5 cel: 7 setCycle: BegLoop self)
			)
			(5
				(stag
					setLoop: 1
					cel: 0
					cycleSpeed: 6
					setCycle: Forward
					setMotion: MoveTo 190 140 self
				)
				(ego posn: 319 130 init: setMotion: MoveTo 290 130)
			)
			(6
				(stag
					setLoop: 9
					cel: 0
					xStep: 9
					setCycle: EndLoop
					setMotion: JumpTo 70 140 self
				)
			)
			(7
				(stag
					setLoop: 1
					cel: 0
					xStep: 5
					setCycle: Walk
					setMotion: MoveTo 30 140 self
				)
			)
			(8
				(if (not (Btst fBeenIn76))
					(messager say: N_ROOM NULL C_FIRST_ENTER 1 self)
				else
					(messager say: N_ROOM NULL C_ENTER_AGAIN 1 self)
				)
			)
			(9
				(if (not (Btst fBeenIn76))
					(messager say: N_ROOM NULL C_FIRST_STAG 1)
				else
					(messager say: N_ROOM NULL C_STAG_AGAIN 1)
				)
				(stag setLoop: 3 setCycle: EndLoop self)
				(= dryadState dryadAvailable)
			)
			(10
				(HandsOn)
				(self cue:)
			)
			(11
				(stag setLoop: 4 cel: 0 cycleSpeed: 12 setCycle: EndLoop self)
			)
			(12
				(stag loop: 6 setCycle: Forward)
				(= cycles (Random 20 90))
			)
			(13
				(stag setCycle: EndLoop self)
			)
			(14
				(stag loop: 4 cel: 7 setCycle: BegLoop self)
			)
			(15
				(= cycles (Random 5 35))
			)
			(16
				(self changeState: 11)
			)
		)
	)
)

(instance outOfTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(miscSound stop: number: 98 play:)
				(dryad setLoop: 0 setCel: 0 setCycle: EndLoop)
				(ego setMotion: 0)
				(= cycles 25)
			)
			(1
				(ego setMotion: PolyPath 204 133 self)
			)
			(2
				(cond 
					((or usedFlameDart (Btst fStagHurt)) (dryad setLoop: 1 cel: 6 forceUpd: setScript: egoToStag))
					((Btst fAgreedToHelpDryad)
						(switch
							(Print
								addText: N_ROOM NULL C_ASK_BROUGHT_SEED 1 0 0 76
								addButton: 1 N_ROOM NULL C_YESORNO 1 0 30 76
								addButton: 2 N_ROOM NULL C_YESORNO 2 0 48 76
								init:
							)
							(1 (SayYesToDryad))
							(2 (SayNoToDryad))
						)
					)
					((Btst fMetDryad)
						(switch
							(Print
								addText: N_ROOM NULL C_ASK_AGAIN 1 0 0 76
								addButton: 1 N_ROOM NULL C_YESORNO 1 0 34 76
								addButton: 2 N_ROOM NULL C_YESORNO 2 0 52 76
								init:
							)
							(1 (SayYesToDryad))
							(2 (SayNoToDryad))
						)
					)
					(else
						(switch
							(Print
								addText: N_ROOM NULL C_ASK_FIRST 1 0 0 76
								addButton: 1 N_ROOM NULL C_YESORNO 1 0 36 76
								addButton: 2 N_ROOM NULL C_YESORNO 2 0 54 76
								init:
							)
							(1 (SayYesToDryad))
							(2 (SayNoToDryad))
						)
					)
				)
				(Bset fMetDryad)
			)
		)
	)
)

(instance intoTree of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(self cue:)
			)
			(1
				(dryad
					posn: (+ (dryad x?) 1) (+ (dryad y?) 1)
					setLoop: 0
					setCel: 14
					setCycle: BegLoop self
				)
				(= dryadState dryadOut)
			)
			(2
				(if (Btst fLearnedDispel)
					(acorn init: setCycle: EndLoop)
					(theAcorn init:)
					(= magicAcornOnGround TRUE)
					(messager say: N_ROOM NULL C_ACORNFALL 1 self)
				else
					(self cue:)
				)
			)
			(3
				(HandsOn)
				(dryad setScript: 0)
			)
		)
	)
)

(instance hasSeed of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: setMotion: MoveTo 154 135 self)
			)
			(1
				(ego setLoop: 3 setCycle: 0)
				(= cycles 2)
			)
			(2
				(ego view: 72 setLoop: 0 cel: 1 setCycle: 0 forceUpd:)
				(= cycles 15)
			)
			(3
				(ego setCycle: CycleTo 2 1)
				(dryad
					x: (+ (dryad x?) 2)
					setLoop: 3
					setCycle: EndLoop self
				)
			)
			(4
				(HandsOff)
				(messager say: N_ROOM NULL C_GIVESEED)
				(SolvePuzzle f76GiveSeed 7)
				(ego setCycle: EndLoop self)
			)
			(5
				(HandsOff)
				(ego use: iSeed)
				(dryad setLoop: 0 setCel: 14)
				(ego
					view: 0
					setCycle: Walk
					setMotion: MoveTo 210 (ego y?) self
				)
			)
			(6
				(ego setLoop: 1 setMotion: PolyPath 190 (ego y?) self)
			)
			(7
				(NormalEgo)
				(theGame setCursor: waitCursor)
				(if (or (Btst fKilledFlower1) (Btst fKilledFlower2) (Btst fKilledFlower3))
					(messager say: N_ROOM NULL C_BLABBERMOUTH)
					(dryad setScript: oMyGod)
				else
					(self cue:)
				)
			)
			(8
				(messager say: N_ROOM NULL C_SHEGOTIT 1 self)
			)
			(9
				(dryadTalker keepWindow: TRUE)
				(= ticks 60)
			)
			(10
				(messager say: N_ROOM NULL C_DRYADGIVESRECIPE 1 self)
			)
			(11
				(messager say: N_ROOM NULL C_DRYADGIVESRECIPE 2 self)
			)
			(12
				(messager say: N_ROOM NULL C_DRYADGIVESRECIPE 3 self)
			)
			(13
				(messager say: N_ROOM NULL C_DRYADGIVESRECIPE 4 self)
			)
			(14
				(messager say: N_ROOM NULL C_DRYADGIVESRECIPE 5 self)
			)
			(15
				(messager say: N_ROOM NULL C_DRYADGIVESRECIPE 6 self)
			)
			(16
				(messager say: N_ROOM NULL C_DRYADGIVESRECIPE 7 self)
			)
			(17
				(messager say: N_ROOM NULL C_DRYADGIVESRECIPE 8 self)
			)
			(18
				(messager say: N_ROOM NULL C_DRYADGIVESRECIPE 9 self)
			)
			(19
				(messager say: N_ROOM NULL C_DRYADGIVESRECIPE 10 self)
				(Bset fLearnedDispel)
				(Bclr fAgreedToHelpDryad)
				(dryadTalker keepWindow: 0)
				(dryad setScript: intoTree)
			)
		)
	)
)

(instance pickEmUp of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and magicAcornOnGround (< (ego distanceTo: acorn) 80))
					(ego get: iAcorn setMotion: MoveTo 147 135 self)
				else
					(self cue:)
				)
			)
			(1
				(ego view: 510 setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(2
				(cond 
					((and magicAcornOnGround (< (ego distanceTo: acorn) 80))
						(= magicAcornOnGround FALSE)
						(SolvePuzzle f76GetAcorn 1)
						(acorn dispose:)
						(theAcorn dispose:)
					)
					(eatIt
						(messager say: N_ROOM NULL C_EATACORN))
					(else
						(messager say: N_ROOM NULL C_PUT_REGULAR_ACORN)
					)
				)
				(ego setCycle: BegLoop self)
			)
			(3
				(if eatIt
					(= eatIt 0)
					(EatAcorn)
				)
				(NormalEgo)
				(ego setScript: 0)
				(HandsOn)
			)
		)
	)
)

(instance stagBolts of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(self cue:)
			)
			(1
				(stag setLoop: 4 cel: 7 setCycle: BegLoop self)
			)
			(2
				(stag setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(3
				(stag
					setLoop: 9
					cel: 0
					xStep: 9
					setCycle: EndLoop
					setMotion: MoveTo 10 140 self
				)
			)
			(4
				(Bclr fStagHere)
				(stag dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance oMyGod of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath (+ (ego x?) 40) (ego y?) self)
			)
			(1
				(Face ego dryad)
				(dryad
					setLoop: 1
					cel: 0
					posn: (- (dryad x?) 1) (- (dryad y?) 3)
					forceUpd:
				)
				(self cue:)
			)
			(2 (messager say: N_DRYAD NULL C_OHMYGOD 1 self))
			(3
				(dryad setCycle: CycleTo 3 1)
				(= seconds 3)
			)
			(4
				(dryad setCel: 3 setCycle: CycleTo 6 1 self)
			)
			(5
				(dryad setScript: egoToPlant)
			)
		)
	)
)

(instance egoToPlant of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(stag setScript: stagBolts)
				(messager say: N_ROOM NULL C_KILLEDFLOWER 0 self)
			)
			(1
				(dryad
					setLoop: 3
					cel: 0
					posn: (+ (dryad x?) 2) (+ (dryad y?) 2)
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(ego view: 79 setLoop: 0 cel: 0 setCycle: EndLoop self)
				(dryad setCel: 2 setCycle: CycleTo 5 1)
			)
			(3
				(ego setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(4
				(ego
					view: 79
					setLoop: 3
					cel: 0
					posn: (+ (ego x?) 6) (+ (ego y?) 5)
					forceUpd:
				)
				(= seconds 2)
			)
			(5
				(++ spitCount)
				(ego setLoop: 3 setCycle: EndLoop self)
			)
			(6
				(seed
					posn: (ego x?) (- (ego y?) 29)
					yStep: 10
					startUpd:
					show:
				)
				(self cue:)
			)
			(7
				(spitSound play:)
				(ego setCycle: BegLoop)
				(seed
					setMotion: MoveTo (seed x?) (- (seed y?) 30) self
				)
			)
			(8
				(ego setLoop: 3 setCycle: EndLoop)
				(seed
					yStep: 10
					setMotion: MoveTo (seed x?) (+ (seed y?) 17) self
				)
			)
			(9
				(gulpSound play:)
				(seed hide:)
				(ego setCycle: BegLoop self)
			)
			(10
				(if (< spitCount 4)
					(self changeState: 5)
				else
					(self cue:)
				)
			)
			(11 (EgoDead 100 101 2 6 79))
		)
	)
)

(instance egoToStag of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(stag setScript: stagBolts)
				(dryad
					setLoop: 1
					cel: 0
					posn: (- (dryad x?) 1) (- (dryad y?) 3)
					forceUpd:
					setCycle: EndLoop self
				)
			)
			(1
				(cond 
					((Btst fStagHurt)
						(messager say: N_ROOM NULL C_HURTSTAG 0 self)
					)
					(usedFlameDart
						(messager say: N_ROOM NULL C_USEDFLAMEDART 0 self)
					)
					(else
						(messager say: N_ROOM NULL C_GETREGULARACORN 2 self)
					)
				)
				(self cue:)
			)
			(2
				(dryad
					setLoop: 3
					cel: 0
					posn: (+ (dryad x?) 2) (+ (dryad y?) 2)
					setCycle: CycleTo 2 1 self
				)
			)
			(3
				(ego view: 79 setLoop: 0 setCel: 0 setCycle: EndLoop self)
				(dryad setCel: 2 setCycle: CycleTo 5 1)
			)
			(4
				(ego setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(5
				(= seconds 5)
			)
			(6 (EgoDead 102 103 13 1 79))
		)
	)
)

(instance goToSleep of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo (ego x?) 147 self)
			)
			(1
				(= currentPalette 1)
				(curRoom drawPic: 76 IRISIN)
				(= seconds 3)
			)
			(2
				(messager say: N_ROOM NULL C_GOTOSLEEP 1)
				(= seconds 4)
			)
			(3
				(self setScript: (ScriptID 88 0) self)
			)
			(4
				(NormalEgo)
				(HandsOff)
				(self dispose:)
			)
		)
	)
)

(instance castADart of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (cast contains: stag) (not (ego script?)))
					(Face ego stag)
					(CastDart stag self)
				else
					(CastDart 0 self)
				)
			)
			(1
				(if (== dryadState dryadHere)
					(= dryadHostile TRUE)
				)
				(if (cast contains: stag)
					(= attackedStag TRUE)
					(Bset fStagHurt)
				)
				(= usedFlameDart TRUE)
				(= ticks 6)
			)
			(2 (self dispose:))
		)
	)
)

(instance aidMeScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SolvePuzzle f76AgreeToHelp 1)
				(messager say: N_ROOM NULL C_AIDME 0 self)
				(Bset fAgreedToHelpDryad)
			)
			(1
				(if (ego has: iSeed)
					(switch
						(Print
							addText: N_ROOM NULL C_ASK_FOR_SEED 1 0 0 76
							addButton: sayYes N_ROOM NULL C_YESORNO 1 0 42 76
							addButton: sayNo N_ROOM NULL C_YESORNO 2 0 60 76
							init:
						)
						(sayYes (SayYesToDryad))
						(sayNo (SayNoToDryad))
					)
				else
					(dryad setScript: intoTree)
				)
			)
		)
	)
)

(instance priorTo of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 30)
			)
			(1
				(messager say: N_ROOM NULL C_STARTLEDSTAG 1 self)
			)
			(2
				(stag setScript: stagBolts)
				(ego setScript: 0)
			)
		)
	)
)

(instance goTo77 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (+ (ego x?) 30) (ego y?) self)
			)
			(1
				(curRoom newRoom: 77)
			)
		)
	)
)

(instance miscSound of Sound
	(properties
		number 97
		priority 1
		loop 2
	)
)

(instance spitSound of Sound
	(properties
		number 18
		priority 1
	)
)

(instance gulpSound of Sound
	(properties
		number 27
		priority 1
	)
)

(instance dryadTalker of Talker
	(properties
		x 10
		y 10
		view 1076
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2076)
		(PalVary PALVARYTARGET 2076)
		(AssertPalette 1076)
		(= font userFont)
		(super init: dryadBust dryadEye dryadMouth &rest)
	)
)

(instance dryadBust of Prop
	(properties
		view 1076
	)
)

(instance dryadMouth of Prop
	(properties
		nsTop 33
		nsLeft 53
		view 1076
		loop 1
	)
)

(instance dryadEye of Prop
	(properties
		nsTop 19
		nsLeft 55
		view 1076
		loop 2
	)
)

(instance egoActions of Actions
	(method (doVerb theVerb)
		(return
			(if (== theVerb V_ACORN)
				(SolvePuzzle fEatAcorn -5)
				(ego use: iAcorn)
				(= eatIt TRUE)
				(EatAcorn)
			else
				(return FALSE)
			)
		)
	)
)
