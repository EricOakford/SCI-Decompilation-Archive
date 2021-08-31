;;; Sierra Script 1.0 - (do not remove this comment)
(script# 16)
(include game.sh) (include "16.shm")
(use Main)
(use Procs)
(use Print)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Chase)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm16 0
)

(local
	flowersNotSpitting
	local1
	local2
	egoState
	seedTarget
	local5
	local6 =  99
	oldMoveSpeed
	oldCycleSpeed
	local9
	local10
	local11
	[flower 4]
	local16
	local17
	local18
	local19
	local20
	castingFetch
	triedThrow
	triedFetch
	local24
	local25
	local26
	local27
	egoFallCycle = [
		2 0
		138 83
		2 1
		150 91
		2 4
		160 100
		2 5
		169 107
		2 5
		172 123
		2 5
		173 139
		PATHEND
		]
)
(procedure (AlreadyKilledFlower)
	(messager say: N_ROOM NULL NULL 1)
)

(procedure (AlreadyGotSeed)
	(messager say: N_ROOM NULL NULL 2)
)

(procedure (GetSeed)
	(cond 
		((Btst fFlowersInactive)
			(messager say: N_ROOM NULL C_FLOWERS_NOT_SPITTING)
		)
		((== egoState 0)
			(ego setScript: getRock)
		)
		(else
			(messager say: N_ROOM NULL 14)
		)
	)
)

(procedure (localproc_017f)
	(if local2
		(return)
	else
		(= local2 1)
		(if (!= ([flower 0] loop?) 3)
			([flower 0] setLoop: 2 setCycle: EndLoop)
		)
		(if (!= ([flower 1] loop?) 3)
			([flower 1] setLoop: 2 setCycle: EndLoop)
		)
		(if (!= ([flower 2] loop?) 3)
			([flower 2] setLoop: 2 setCycle: EndLoop)
		)
		(if (!= ([flower 3] loop?) 3)
			([flower 3] setLoop: 2 setCycle: EndLoop)
		)
	)
)

(procedure (localproc_0210)
	([flower 0] stopUpd:)
	([flower 1] stopUpd:)
	([flower 2] stopUpd:)
	([flower 3] stopUpd:)
)

(class FlowerScript of Script
	(properties
		name "Script#"
		register2 -1
	)
)

(instance rm16 of Room
	(properties
		noun N_ROOM
		picture 16
		style DISSOLVE
		east 17
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 0
						319 0
						319 151
						278 151
						236 141
						186 139
						170 126
						140 139
						64 123
						42 157
						111 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						193 189
						237 171
						319 165
						319 189
					yourself:
				)
		)
		(Load SCRIPT JUMP)
		(LoadMany VIEW 16 518 517 510)
		(if (ego knows: FETCH)
			(Load VIEW 520)
		)
		(LoadMany SOUND
			(SoundFX 18)
			(SoundFX 27)
		)
		(super init:)
		(spitSound number: (SoundFX 18) init:)
		(gulpSound number: (SoundFX 27) init:)
		(if Night
			(= flowersNotSpitting TRUE)
			(if (Btst fGotSeed)
				(Bset fFlowersInactive)
			)
		)
		(= local19 2)
		(NormalEgo)
		(flyingRock
			setLoop: 4
			setStep: 70 30
			posn: 0 1000
			hide:
			ignoreActors: TRUE
			illegalBits: 0
			setCycle: Forward
			init:
		)
		(switch prevRoomNum
			(17
				(ego init: posn: 318 165 setMotion: MoveTo 275 165)
			)
			(else 
				(self setScript: sEnterFromSouth)
			)
		)
		(if (Btst fKilledFlower3)
			(flower0 setLoop: 3 cel: 4)
		)
		(if (Btst fKilledFlower2)
			(flower1 setLoop: 3 cel: 4)
		)
		(if (Btst fKilledFlower1)
			(flower3 setLoop: 3 cel: 4)
		)
		((= [flower 0] flower0) init: stopUpd:)
		((= [flower 1] flower1) init: stopUpd:)
		((= [flower 2] flower2) init: stopUpd:)
		((= [flower 3] flower3) init: stopUpd:)
		(leaf0 addToPic:)
		(leaf1 addToPic:)
		(leaf2 addToPic:)
		(leaf3 addToPic:)
		(if
			(and
				(not (Btst fGotSeed))
				(not (Btst fKilledFlower1))
				(not (Btst fKilledFlower2))
				(not (Btst fKilledFlower3))
			)
			(flyingSeed init: setScript: spitIt)
			(= local1 1)
		)
		(cliff init:)
		(grass init:)
		(trees init:)
		(theRoom init:)
		(yellowRock init:)
	)
	
	(method (doit)
		(if (and local16 (not flowersNotSpitting))
			(flyingSeed setScript: spitIt)
		)
		(super doit:)
		(cond 
			(script)
			((> (ego y?) 185)
				(curRoom setScript: sExitSouth)
			)
		)
	)
	
	(method (dispose)
		(Bset fBeenIn16)
		(Bclr fFlowersInactive)
		(DisposeScript MOVECYC)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(if (== egoState 1)
					(curRoom setScript: fallDown)
				)
			)
			(V_OPEN
				(cond 
					((Btst fGotSeed)
						(AlreadyGotSeed)
					)
					((!= (ego script?) 0)
						(messager say: N_ROOM V_OPEN C_OPEN_NOT_NOW)
					)
					((!= egoState 0)
						(messager say: N_ROOM V_OPEN C_OPEN_FALL)
						(ego setScript: fallDown)
					)
					((< [egoStats OPEN] 35)
						(messager say: N_ROOM V_OPEN C_OPEN_TOO_WEAK)
					)
					((CastSpell OPEN)
						(Bset fFlowersInactive)
						(curRoom setScript: openUp)
					)
				)
			)
			(V_FETCH
				(cond 
					(flowersNotSpitting
						(CantDo)
					)
					((Btst fGotSeed)
						(AlreadyGotSeed)
					)
					((Btst fFlowersInactive)
						(messager say: N_ROOM V_FETCH C_FETCH_TOO_LATE)
					)
					((== egoState 0)
						(= castingFetch TRUE)
						(curRoom setScript: throwIt)
					)
					(else
						(messager say: N_ROOM V_FETCH C_FETCH_FALL)
						(ego setScript: fallDown)
					)
				)
			)
			(V_FLAME
				(if (not (Btst fGotSeed))
					(messager say: N_ROOM V_FLAME C_DONT_DAMAGE_SEED)
				else
					(messager say: N_ROOM V_FLAME C_DONT_BE_DESTRUCTIVE)
				)
			)
			(V_DAZZLE
				(if (== egoState 1)
					(messager say: N_ROOM V_FETCH C_FETCH_FALL)
					(ego setScript: fallDown)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(if (not (Btst fFlowersInactive))
			(messager say: N_CLIFF V_LOOK C_FLOWERS_SPITTING)
		)
	)
)

(instance grass of Feature
	(properties
		x 171
		y 146
		noun N_GRASS
		nsTop 104
		nsLeft 24
		nsBottom 189
		nsRight 319
		onMeCheck cBLUE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(cond 
					((and (> egoState 1)
							(== local5 0))
						)
					((>= egoState 1)
						(ego setScript: climbDown)
					)
					(else
						(GetSeed)
					)
				)
			)
			(V_LOOK
				(messager say: N_GRASS V_LOOK NULL)
			)
			(V_FLAME
				(messager say: N_ROOM V_FLAME C_DONT_BE_DESTRUCTIVE)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance trees of Feature
	(properties
		x 159
		y 94
		noun N_TREES
		nsBottom 189
		nsRight 319
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(messager say: N_ROOM V_FLAME C_DONT_BE_DESTRUCTIVE)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cliff of Feature
	(properties
		x 159
		y 1
		noun N_CLIFF
		nsBottom 155
		nsRight 319
	)
	
	(method (doVerb theVerb)
		(if
			(and
				(OneOf theVerb
					34 42 44 46 16 38 21 36 39
					32 29 37 22 26 14 17 27 23
					31 30 40 43 45 53 11 28 20
					35 15 10 24 12 18 19 47 41
					33
				)
				(< (Abs (- mouseX (flyingSeed x?))) 10)
				(< (Abs (- mouseY (flyingSeed y?))) 10)
			)
			(flyingSeed doVerb: theVerb)
		else
			(switch theVerb
				(V_DO
					(cond 
						((not local10)
							(messager say: N_CLIFF V_DO NULL)
							(= local10 TRUE)
						)
						((>= egoState 1)
							(if
								(and
									(< (Abs (- mouseX (flyingSeed x?))) 10)
									(< (Abs (- mouseY (flyingSeed y?))) 10)
								)
								(flyingSeed doVerb: theVerb)
							else
								(ego setScript: climbDown)
							)
						)
						((Btst fGotSeed)
							(AlreadyGotSeed)
						)
						((TrySkill CLIMB 35 0)
							(ego setScript: goodClimb)
						)
						(else
							(curRoom setScript: badClimb)
						)
					)
				)
				(V_LOOK
					(messager say: N_CLIFF V_LOOK NULL 0 curRoom)
				)
				(V_FLAME
					(messager say: N_ROOM V_FLAME C_DONT_BE_DESTRUCTIVE)
				)
				(else
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance leaf0 of View
	(properties
		x 142
		y 93
		noun N_LEAF0
		view 16
	)
)

(instance leaf1 of View
	(properties
		x 35
		y 118
		noun N_LEAF1
		view 16
	)
)

(instance leaf2 of View
	(properties
		x 67
		y 58
		noun N_LEAF2
		view 16
		cel 1
	)
)

(instance leaf3 of View
	(properties
		x 230
		y 103
		noun N_LEAF3
		view 16
	)
)

(instance flower0 of Prop
	(properties
		x 112
		y 83
		noun N_FLOWERS
		view 16
		loop 1
		signal ignrAct
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== local6 99)
			(= local6 0)
		)
		(switch theVerb
			(V_OPEN
				(cond 
					((Btst fGotSeed)
						(AlreadyGotSeed)
					)
					((!= (ego script?) 0))
					((!= egoState 0)
						(messager say: N_FLOWERS V_OPEN C_LOSE_CONCENTRATION)
						(ego setScript: fallDown)
					)
					((< [egoStats OPEN] 35)
						(messager say: N_FLOWERS V_OPEN C_OPEN_TOO_WEAK)
					)
					((CastSpell OPEN)
						(Bset fFlowersInactive)
						(curRoom setScript: openUp)
					)
				)
			)
			(V_FLAME
				(if (not (Btst fGotSeed))
					(messager say: N_FLOWERS V_FLAME C_DONT_DAMAGE_SEED)
				else
					(messager say: N_FLOWERS V_FLAME C_DONT_BE_DESTRUCTIVE)
				)
			)
			(V_SWORD
				(switch egoState
					(1
						(messager say: N_FLOWERS V_SWORD C_SWORD_FALL)
						(ego setScript: fallDown)
					)
					(3)
					(4)
					(0
						(curRoom setScript: smashIt)
						(if (!= local6 2)
							(Bset fFlowersInactive)
						)
					)
				)
			)
			(V_DO
				(cond 
					((or (not local11) (Btst fGotSeed))
						(messager say: N_FLOWERS V_DO C_DONT_NEED_FLOWERS)
						(= local11 1)
					)
					((Btst 192)
						(messager say: N_FLOWERS V_DO C_FLOWERS_NOT_SPITTING)
					)
					((== egoState 1)
						(= egoState 2)
						(ego setScript: catchIt)
					)
					(else
						(messager say: N_FLOWERS V_DO C_DONT_NEED_FLOWERS)
					)
				)
			)
			(V_SEED
				(messager say: N_FLOWERS V_SEED C_KEEP_SEED)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance flower1 of Prop
	(properties
		x 21
		y 106
		view 16
		loop 1
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(= local6 1)
		(flower0 doVerb: theVerb)
	)
)

(instance flower2 of Prop
	(properties
		x 41
		y 61
		view 16
		loop 1
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(= local6 2)
		(flower0 doVerb: theVerb)
	)
)

(instance flower3 of Prop
	(properties
		x 215
		y 90
		view 16
		loop 1
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(= local6 3)
		(flower0 doVerb: theVerb)
	)
)

(instance flyingSeed of Actor
	(properties
		noun N_SEED
		view 16
		signal ignrAct
		illegalBits $0000
	)
	
	(method (init)
		(self
			ignoreActors: TRUE
			setLoop: 4
			setPri: 15
			posn: 0 1000
			hide:
			setCycle: Forward
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(cond 
					((Btst fFlowersInactive)
						(messager say: N_SEED V_DO C_FLOWERS_NOT_SPITTING)
					)
					((Btst fGotSeed)
						(AlreadyGotSeed)
					)
					((== egoState 1)
						(= egoState 2)
						(ego setScript: catchIt)
					)
					((or (== egoState 2) (== egoState 3))
						(messager say: N_SEED V_DO 10)
					)
					(else
						(messager say: N_SEED V_DO 13)
					)
				)
			)
			(V_DAGGER
				(messager say: N_SEED V_DAGGER NULL)
			)
			(V_ROCK
				(cond 
					((Btst fGotSeed)
						(AlreadyGotSeed)
					)
					((Btst fFlowersInactive)
						(messager say: N_SEED V_ROCK C_FLOWERS_NOT_SPITTING)
					)
					((== egoState 1)
						(curRoom setScript: fallDown)
					)
					((== egoState 0)
						(if (ego has: iRock)
							(curRoom setScript: throwIt)
						else
							(messager say: N_SEED V_ROCK C_NEED_ROCK)
						)
					)
					(else
						(messager say: N_SEED V_ROCK C_ROCK_NOT_NOW)
					)
				)
			)
			(V_LOOK
				(if (Btst fFlowersInactive)
					(messager say: N_SEED V_LOOK C_FLOWERS_NOT_SPITTING)
				else
					(messager say: N_SEED V_LOOK 14)
				)
			)
			(V_FLAME
				(messager say: N_ROOM V_FLAME C_DONT_BE_DESTRUCTIVE)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance magicLasso of Actor
	(properties
		view 520
	)
)

(instance flyingRock of Actor
	(properties
		view 510
	)
)

(instance lassoSeed of Script

	(method (doit)
		(if (== (lassoSeed state?) 3)
			(flyingSeed
				setPri: 7
				setStep: 6 4
				posn: (magicLasso x?) (magicLasso y?)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fFlowersInactive)
				(magicLasso
					ignoreActors: TRUE
					illegalBits: 0
					setMotion:
						MoveTo
						([flower local5] x?)
						(- ([flower local5] y?) 30)
						self
				)
			)
			(1
				(Bclr fFlowersInactive)
				(= local16 1)
				(= local25 1)
			)
			(2
				(flyingSeed
					setScript: 0
					setStep: 1 1
					setMotion: MoveTo (flyingSeed x?) (- (magicLasso y?) 2) self
				)
				(spitSound play:)
				([flower seedTarget] setCycle: BegLoop)
			)
			(3
				(magicLasso
					setPri: 7
					setStep: 6 4
					setMotion: MoveTo 234 117 self
				)
				(ego setCycle: EndLoop)
			)
			(4
				(= seconds 3)
			)
			(5
				(magicLasso hide:)
				(flyingSeed dispose:)
				(= local5 2)
				(= seedTarget 2)
				(ego setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(6
				(ego get: iSeed)
				(messager say: N_ROOM NULL C_FETCH_SEED 1 self)
				(Bset fGotSeed)
				(SolvePuzzle f16GetSeed 8)
				(NormalEgo)
				(ego x: (+ (ego x?) 10) loop: 1)
				(HandsOn)
			)
			(7
				(localproc_017f)
				(= seconds 3)
			)
			(8
				(localproc_0210)
				(magicLasso dispose:)
			)
		)
	)
)

(instance youMissed of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(flyingRock
					setStep: 30 20
					setMotion: MoveTo 0 (- (flyingRock y?) 100) self
				)
			)
			(1
				(flyingRock
					setMotion: JumpTo (Random 130 160) (Random 145 160) self
				)
			)
			(2
				(flyingRock hide:)
				(self dispose:)
			)
		)
	)
)

(instance sExitSouth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 60) self)
			)
			(2
				(curRoom newRoom: 24)
			)
		)
	)
)

(instance sEnterFromSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(ego init: posn: 170 240 setMotion: MoveTo 170 180 self)
			)
			(2
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance fallDown of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(= egoState 0)
				(= ticks 6)
			)
			(1
				(ego view: 517 setLoop: 2 setCel: 0 posn: 134 80)
				(= ticks 6)
			)
			(2
				(ego setCycle: MoveCycle @egoFallCycle self)
			)
			(3
				(ego
					view: 517
					setLoop: 6
					setCel: 0
					posn: 187 146
					setCycle: 0
				)
				(= cycles 2)
			)
			(4
				(ShakeScreen 2)
				(catchingFeat dispose:)
				(= seconds 2)
			)
			(5
				(if (not (TakeDamage 5))
					(EgoDead C_DIE_FALL C_DIE_FALL_TITLE 2 5 517)
				else
					(messager say: N_ROOM NULL C_HIT_GROUND 1 self)
				)
			)
			(6
				(messager say: N_ROOM NULL C_REGAIN_CONSCIOUSNESS 1 self)
				(NormalEgo)
				(ego loop: 2 posn: 164 136)
				(HandsOn)
				(ego setScript: 0)
			)
		)
	)
)

(instance badClimb of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(= register (ego cycleSpeed?))
				(if (not (Btst fClimbedSpireaLedge))
					(messager say: N_ROOM NULL C_FIRST_CLIMB)
				)
				(cond 
					((> mouseX 219) (ego setMotion: PolyPath 258 144 self))
					((> mouseX 131) (ego setMotion: PolyPath 168 125 self))
					(else (ego setMotion: PolyPath 73 122 self))
				)
			)
			(2
				(ego setHeading: 360 self)
			)
			(3
				(if (not (Btst fClimbedSpireaLedge))
					(Bset fClimbedSpireaLedge)
					(messager say: N_ROOM NULL NULL 3)
				)
				(ego
					view: 517
					setMotion: 0
					setLoop: 0
					x: (+ (ego x?) 6)
					y: (- (ego y?) 38)
					cel: 0
				)
				(= ticks 10)
			)
			(4
				(ego setCycle: Forward)
				(= seconds 3)
			)
			(5
				(messager say: N_ROOM NULL NULL 4)
				(ego
					view: 0
					setCycle: Walk
					setLoop: 2
					x: (- (ego x?) 6)
					y: (+ (ego y?) 38)
				)
				(= ticks 12)
			)
			(6
				(ego
					cycleSpeed: register
					setMotion: MoveTo (ego x?) (+ (ego y?) 5) self
				)
			)
			(7
				(NormalEgo)
				(= ticks 12)
			)
			(8
				(messager say: N_ROOM NULL NULL 5 self)
			)
			(9
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance goodClimb of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(catchingFeat init:)
				(= oldCycleSpeed (ego cycleSpeed?))
				(= oldMoveSpeed (ego moveSpeed?))
				(ego setMotion: PolyPath 143 134 self)
				(if (not (Btst fClimbedSpireaLedge))
					(messager say: N_ROOM NULL 0 6)
					(Bset fClimbedSpireaLedge)
				)
			)
			(1
				(ego setHeading: 0 self)
			)
			(2
				(ego
					view: 517
					setLoop: 0
					setCel: 0
					posn: (- (ego x?) 1) (- (ego y?) 38)
					ignoreActors: TRUE
					setCycle: EndLoop self
				)
			)
			(3
				(ego
					setLoop: 1
					setCel: 0
					posn: (- (ego x?) 2) (- (ego y?) 12)
					setCycle: EndLoop self
				)
			)
			(4
				(ego view: 0 setLoop: 2 setPri: 1 posn: 134 85)
				(= ticks 10)
			)
			(5
				(ego view: 4)
				(= egoState 1)
				(HandsOn)
				(User canControl: FALSE)
				(self dispose:)
			)
		)
	)
)

(instance getRock of Script
	
	(method (changeState newState &tmp [str 40])
		(switch (= state newState)
			(0
				(HandsOff)
				(= oldCycleSpeed (ego cycleSpeed?))
				(ego
					setMotion:
						MoveTo
						(ego x?)
						(if (> (ego y?) 175)
							(- (ego y?) 5)
						else
							(+ (ego y?) 5)
						)
				)
				(= ticks 30)
			)
			(1
				(= register (Random 0 1))
				(ego
					view: 510
					setLoop: register
					setCel: 0
					cycleSpeed: 6
					setCycle: EndLoop self
				)
			)
			(2
				(Message MsgGet 16 N_ROOM V_DO C_GET_ROCKS 1 @str)
				(Print addText: @str init: self)
			)
			(3
				(ego setCycle: BegLoop self)
			)
			(4
				(NormalEgo)
				(if register
					(ego cycleSpeed: oldCycleSpeed setHeading: 270 self)
				else
					(ego cycleSpeed: oldCycleSpeed setHeading: 90 self)
				)
			)
			(5
				(HandsOn)
				(ego get: iRock 10)
				(self dispose:)
			)
		)
	)
)

(instance climbDown of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== egoState 4) (flyingSeed dispose:))
				(ego
					view: 0
					setLoop: 0
					setCel: 0
					illegalBits: 0
					setPri: 7
					ignoreActors: 1
					posn: 142 83
					setMotion: 0
				)
				(catchingFeat dispose:)
				(= ticks 30)
			)
			(1
				(ego
					view: 517
					posn: 137 83
					setLoop: 1
					setCel: 9
					setCycle: BegLoop self
				)
			)
			(2
				(ego
					setLoop: 0
					setCel: 7
					posn: 137 83
					setCycle: BegLoop
					setMotion: MoveTo 137 101 self
				)
			)
			(3
				(= egoState 0)
				(NormalEgo)
				(ego
					posn: 136 134
					loop: 3
					setPri: -1
					cycleSpeed: oldCycleSpeed
					moveSpeed: oldMoveSpeed
					setHeading: 180 self
				)
			)
			(4
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance spitSound of Sound
	(properties
		number 18
		priority 3
	)
)

(instance gulpSound of Sound
	(properties
		number 27
		priority 3
	)
)

(instance catchIt of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(NormalEgo)
				(HandsOff)
				(ego illegalBits: 0 ignoreActors: TRUE setHeading: 270 self)
			)
			(1
				(= cycles 2)
			)
			(2
				(ego
					posn: (ego x?) (- (ego y?) 5)
					setHeading: 180 self
				)
			)
			(3
				(= cycles 2)
			)
			(4
				(if flowersNotSpitting
					(CantDo)
					(NormalEgo)
					(ego loop: 7)
					(HandsOn)
					(self dispose:)
					(= egoState 3)
				else
					(= egoState 3)
					(HandsOff)
					(ego
						view: 16
						setLoop: 5
						setCel: 0
						posn: 140 81
						setCycle: 0
					)
					(self dispose:)
				)
			)
		)
	)
)

(instance heCaughtIt of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				([flower seedTarget] setCycle: BegLoop)
				(ego setCel: 1)
				(flyingSeed posn: (- (ego x?) 12) (- (ego y?) 49))
				(= ticks 10)
			)
			(1
				(ego setCel: 2)
				(flyingSeed posn: (- (ego x?) 4) (- (ego y?) 44))
				(= ticks 10)
			)
			(2
				(ego setCel: 3)
				(flyingSeed posn: (+ (ego x?) 3) (- (ego y?) 30))
				(= ticks 10)
			)
			(3
				(ego setCel: 4)
				(flyingSeed hide:)
				(= ticks 20)
			)
			(4
				(ego setCel: 5)
				(= ticks 20)
			)
			(5
				(ego view: 4 setLoop: 2 setCel: 0)
				(= local5 2)
				(= seedTarget 2)
				(= cycles 2)
			)
			(6
				(messager say: N_ROOM NULL 20 0 self)
			)
			(7
				(localproc_017f)
				(= local6 99)
				(SolvePuzzle f16GetSeed 8)
				(Bset fGotSeed)
				(Bset fFlowersInactive)
				(ego get: 25 setScript: climbDown)
			)
		)
	)
)

(instance smashIt of Script
	
	(method (changeState newState &tmp temp0 [temp1 40])
		(switch (= state newState)
			(0
				(= local20 0)
				(switch local6
					(0
						(if (Btst fKilledFlower3)
							(AlreadyKilledFlower)
							(self dispose:)
						else
							(HandsOff)
							(= local19 0)
							(ego setMotion: PolyPath 117 130 self)
						)
					)
					(1
						(if (Btst fKilledFlower1)
							(AlreadyKilledFlower)
							(self dispose:)
						else
							(HandsOff)
							(= local19 1)
							(ego setMotion: PolyPath 53 142 self)
						)
					)
					(3
						(if (Btst fKilledFlower2)
							(AlreadyKilledFlower)
							(self dispose:)
						else
							(HandsOff)
							(= local19 3)
							(ego setMotion: PolyPath 220 140 self)
						)
					)
					(else 
						(messager say: N_ROOM NULL NULL 9)
						(self dispose:)
					)
				)
				(= local6 99)
			)
			(1
				(SolvePuzzle f10KillFlower -10)
				(ego
					view: 518
					setCel: 0
					setLoop: (if (== local19 1) 2 else 0)
				)
				(= ticks 6)
			)
			(2 (ego setCycle: EndLoop self))
			(3
				(++ local20)
				(ego
					setLoop: (if (== local19 1) 3 else 1)
					setCycle: EndLoop self
				)
			)
			(4
				(if (< local20 2)
					(switch local19
						(0 ([flower 0] setCel: 2))
						(1 ([flower 1] setCel: 2))
						(3 ([flower 3] setCel: 2))
					)
				)
				(switch local20
					(2
						(switch local19
							(0
								([flower 0]
									setLoop: 3
									cel: 0
									cycleSpeed: 6
									setCycle: EndLoop self
								)
								(= register 0)
								(Bset fKilledFlower3)
							)
							(1
								([flower 1]
									setLoop: 3
									cel: 0
									cycleSpeed: 6
									setCycle: EndLoop self
								)
								(= register 1)
								(Bset fKilledFlower1)
							)
							(3
								([flower 3]
									setLoop: 3
									cel: 0
									cycleSpeed: 6
									setCycle: EndLoop self
								)
								(= register 3)
								(Bset fKilledFlower2)
							)
							(else
								(= ticks 60)
							)
						)
					)
					(3
						(= local20 0)
						(= ticks 30)
					)
					(else 
						(= state (- state 2))
						(= ticks 12)
					)
				)
			)
			(5
				(if (< local20 2)
					(= temp0 (if local2 3 else 0))
					(switch local19
						(0
							([flower 0] setCel: temp0)
						)
						(1
							([flower 1] setCel: temp0)
						)
						(3
							([flower 3] setCel: temp0)
						)
					)
				)
				(ego setLoop: (if (== local19 1) 2 else 0) cel: 3)
				(= ticks 12)
			)
			(6 (ego setCycle: BegLoop self))
			(7
				(ego loop: (if (== local19 1) 1 else 3))
				(NormalEgo)
				(if
					(and
						(== local19 local5)
						(not local24)
						(not (Btst fGotSeed))
					)
					(self cue:)
				else
					(HandsOn)
					(self dispose:)
				)
				(= local19 2)
			)
			(8
				(flyingSeed
					init:
					show:
					posn: ([flower register] x?) (- ([flower register] y?) 8)
					setScript: 0
					setPri: 15
				)
				(= local16 0)
				(= local1 1)
				(= ticks 60)
			)
			(9
				(messager say: N_ROOM NULL NULL 10 self)
			)
			(10 (= ticks 10))
			(11
				(flyingSeed
					setPri: (- (ego priority?) 1)
					setMotion: MoveTo (ego x?) (- (ego y?) 30) self
				)
			)
			(12
				(flyingSeed dispose:)
				(= ticks 2)
			)
			(13
				(messager say: N_ROOM NULL NULL 11 self)
			)
			(14
				(SolvePuzzle f16GetSeed 8)
				(Bset fGotSeed)
				(localproc_017f)
				(ego
					get: iSeed
					setMotion:
						MoveTo
						(if (< (ego x?) 60) 90 else (ego x?))
						(if (< (ego x?) 60) (+ (ego y?) 25) else (ego y?))
						self
				)
			)
			(15
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance rockHitsIt of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(not local24)
				(== (flyingSeed y?) 150)
				(== (flyingRock y?) 160)
			)
			(= local24 1)
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 1)
			)
			(1
				(= oldCycleSpeed (ego cycleSpeed?))
				(= oldMoveSpeed (ego moveSpeed?))
				(flyingRock setMotion: Chase flyingSeed 5 self)
			)
			(2
				(Bset fFlowersInactive)
				(= local16 0)
				(flyingSeed setScript: 0 setMotion: 0)
				(flyingRock
					setMotion: MoveTo (+ (flyingSeed x?) 3) (flyingSeed y?) self
				)
				(if (!= ([flower local5] cel?) 0)
					([flower local5] setCel: 0)
				)
			)
			(3
				(flyingRock setMotion: JumpTo 150 160 self)
				(flyingSeed
					show:
					setStep: 3 15
					setMotion: MoveTo (flyingSeed x?) 150
				)
			)
			(4
				(= local5 2)
				(= seedTarget 2)
				(flyingSeed setMotion: 0 setCycle: 0 ignoreActors: 1)
				(flyingRock hide:)
				(= ticks 12)
			)
			(5
				(localproc_017f)
				(= seconds 3)
			)
			(6
				(localproc_0210)
				(flyingRock hide:)
				(= ticks 6)
			)
			(7
				(flyingSeed setCycle: 0 stopUpd:)
				(NormalEgo)
				(ego
					illegalBits: 0
					ignoreActors: TRUE
					setMotion:
						MoveTo
						(if (< (flyingSeed x?) (ego x?))
							(+ (flyingSeed x?) 16)
						else
							(- (flyingSeed x?) 16)
						)
						149
						self
				)
			)
			(8
				(ego
					view: 510
					setLoop: (if (< (flyingSeed x?) (ego x?)) 1 else 0)
					cel: 0
					cycleSpeed: 6
					setCycle: EndLoop self
				)
			)
			(9
				(flyingSeed dispose:)
				(ego setCycle: BegLoop self)
			)
			(10
				(SolvePuzzle f16GetSeed 8)
				(Bset fGotSeed)
				(ego
					get: 25
					cycleSpeed: oldCycleSpeed
					moveSpeed: oldMoveSpeed
				)
				(self dispose:)
			)
		)
	)
)

(instance spitIt of FlowerScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local16 0)
				(= seconds (Random 3 5))
			)
			(1
				(if (or Night (Btst fFlowersInactive))
					(self changeState: 8)
				else
					(= seedTarget local5)
					(if (== local26 (= local5 (Random 0 3)))
						(while (== local26 local5)
							(if (<= (++ local27) 3) (= local27 0) (break))
							(if (== local5 1) (= local5 2) else (= local5 1))
						)
					else
						(= local27 0)
					)
					(= local26 local5)
					([flower seedTarget]
						setLoop: 1
						startUpd:
						setCycle: EndLoop self
					)
					(if (and (!= local5 3) (!= seedTarget local5) local17)
						(throwIt cue:)
					)
				)
			)
			(2
				(flyingSeed
					show:
					yStep: 10
					posn:
						(switch seedTarget
							(0 110)
							(1 20)
							(2 40)
							(3 213)
						)
						(switch seedTarget
							(0 48)
							(1 69)
							(2 24)
							(3 54)
						)
				)
				(if local25 (lassoSeed cue:) else (self cue:))
			)
			(3
				(spitSound play:)
				(= local9 0)
				(flyingSeed
					setMotion:
						MoveTo
						(flyingSeed x?)
						(-
							(flyingSeed y?)
							(if (and (== seedTarget local5) (!= seedTarget 2))
								30
							else
								5
							)
						)
						self
				)
				(= ticks 7)
			)
			(4
				([flower seedTarget] setCel: 0)
			)
			(5
				(switch local5
					(0
						(= register 111)
						(= register2 62)
					)
					(1
						(= register 20)
						(= register2 85)
					)
					(2
						(= register 41)
						(= register2 40)
					)
					(3
						(= register 214)
						(= register2 69)
					)
				)
				(if (and (== egoState 3) (== local5 0))
					(self changeState: 20)
				else
					(flyingSeed
						yStep: 2
						moveSpeed: 3
						setMotion: JumpTo register register2 self
					)
				)
				(= ticks 5)
			)
			(6
				([flower local5] setLoop: 2 setCycle: EndLoop)
				(flyingSeed
					setPri: (+ ([flower local5] priority?) 1)
				)
			)
			(7
				(= local9 1)
				(gulpSound play:)
				(flyingSeed hide:)
				([flower local5] setCycle: BegLoop self)
			)
			(8
				([flower local5] stopUpd:)
				([flower seedTarget] stopUpd:)
				(if (or (not (Btst fFlowersInactive)) (not local18))
					(= local16 1)
				)
				(if Night
					(= flowersNotSpitting TRUE)
					(Bset fFlowersInactive)
				)
				(self dispose:)
			)
			(20
				(= egoState 4)
				(flyingSeed
					yStep: 6
					setMotion: JumpTo (- (ego x?) 12) (- (ego y?) 49) self
				)
			)
			(21
				(ego setScript: heCaughtIt)
				(self dispose:)
			)
		)
	)
)

(instance theRoom of Feature
	(properties
		x 159
		y 1
		z 94
		nsBottom 189
		nsRight 319
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_ROOM V_FLAME C_DONT_BE_DESTRUCTIVE)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance openUp of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(ego setMotion: MoveTo (ego x?) 145 self)
			)
			(2
				(ego view: 521 setLoop: 0 cycleSpeed: 6 setCycle: EndLoop)
				(= ticks 150)
			)
			(3
				(localproc_017f)
				(flyingSeed
					setPri: (+ ([flower local5] priority?) 1)
					posn: (- ([flower local5] x?) 1) (- ([flower local5] y?) 21)
					show:
				)
				(= seconds 3)
			)
			(4
				(localproc_0210)
				(flyingSeed setMotion: MoveTo (flyingSeed x?) 145 self)
			)
			(5
				(flyingSeed setCycle: 0 stopUpd:)
				(NormalEgo)
				(ego
					setMotion:
						MoveTo
						(if (< (flyingSeed x?) (ego x?))
							(+ (flyingSeed x?) 16)
						else
							(- (flyingSeed x?) 16)
						)
						144
						self
				)
			)
			(6
				(ego
					view: 510
					setLoop: (if (< (flyingSeed x?) (ego x?)) 1 else 0)
					cel: 0
					cycleSpeed: 6
					setCycle: EndLoop
				)
				(= ticks 48)
			)
			(7
				(messager say: N_ROOM NULL C_GET_SEED)
				(flyingSeed dispose:)
				(ego setCycle: BegLoop self)
			)
			(8
				(NormalEgo)
				(SolvePuzzle f16GetSeed 8)
				(ego get: iSeed setScript: 0)
				(Bset fGotSeed)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance throwIt of FlowerScript
	
	(method (doit)
		(super doit:)
		(if (!= theCursor waitCursor)
			(theGame setCursor: waitCursor)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= register (ego cycleSpeed?))
				(= register2 (ego moveSpeed?))
				(= ticks 10)
			)
			(1
				(cond 
					((and castingFetch (not triedFetch))
						(messager say: N_ROOM V_FETCH C_PREPARE_FETCH 0 self)
					)
					((and (not castingFetch) (not triedThrow))
						(messager say: N_SEED V_ROCK C_PREPARE_ROCK 0 self)
					)
					(else
						(= ticks 5)
					)
				)
			)
			(2
				(ego
					setMotion: MoveTo (Random 242 258) (Random 160 175) self
				)
			)
			(3
				(= ticks 30)
			)
			(4
				(ego
					view: (if castingFetch 520 else 510)
					setCycle: 0
					setLoop: 2
					cel: (if castingFetch 5 else 2)
					x: (if castingFetch (- (ego x?) 10) else (ego x?))
				)
				(= local17 1)
			)
			(5
				(= local17 0)
				(if castingFetch
					(ego cycleSpeed: 6 setCycle: BegLoop self)
				else
					(ego setCycle: CycleTo 4 1 self)
				)
			)
			(6
				(if castingFetch
					(magicLasso
						ignoreActors: 1
						posn: (- (ego x?) 10) (- (ego y?) 44)
						setLoop: 4
						setStep: 5 5
						setCycle: Forward
						init:
					)
					(if (> [egoStats 24] 20)
						(magicLasso setScript: lassoSeed)
					else
						(magicLasso setScript: lassoFailed)
					)
					(ego
						cycleSpeed: register
						moveSpeed: register2
						setLoop: 0
						setCel: 6
						setCycle: 0
					)
					(= triedFetch 1)
					(= castingFetch 0)
					(self dispose:)
				else
					(flyingRock
						posn: (- (ego x?) 13) (- (ego y?) 34)
						show:
					)
					(ego setCycle: EndLoop)
					(if (TrySkill THROW 0 -10)
						(flyingRock setScript: rockHitsIt self)
					else
						(flyingRock setScript: youMissed self)
					)
					(= triedThrow 1)
				)
			)
			(7
				(ego use: iRock 1)
				(= castingFetch 0)
				(NormalEgo)
				(ego loop: 1 cycleSpeed: register moveSpeed: register2)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance lassoFailed of Script
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fFlowersInactive)
				(= temp0 ([flower local5] x?))
				(= temp1 (- ([flower local5] y?) 30))
				(= ticks 10)
			)
			(1
				(magicLasso
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo temp0 temp1
				)
				(= ticks 100)
			)
			(2
				(magicLasso hide:)
				(= cycles 2)
			)
			(3
				(messager say: N_ROOM NULL C_FETCH_FAIL 0 self)
			)
			(4
				(NormalEgo)
				(ego x: (+ (ego x?) 10) loop: 7)
				(= ticks 10)
			)
			(5
				(HandsOn)
				(= local16 1)
				(Bclr fFlowersInactive)
				(client dispose:)
			)
		)
	)
)

(instance yellowRock of Feature
	(properties
		x 71
		y 161
		nsTop 134
		nsLeft 32
		nsBottom 189
		nsRight 111
		onMeCheck cYELLOW
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_ROOM V_FLAME C_DONT_BE_DESTRUCTIVE)
		else
			(cliff doVerb: theVerb &rest)
		)
	)
)

(instance catchingFeat of Feature
	(properties
		x 135
		y 164
		z 100
		nsTop 37
		nsLeft 119
		nsBottom 91
		nsRight 152
	)
	
	(method (init)
		(super init: &rest)
		(directionHandler add: self)
	)
	
	(method (dispose)
		(directionHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (== (event message?) dirStop))
				(== (theIconBar curIcon?) (theIconBar walkIconItem?))
				(& (event type?) direction)
			)
			(curRoom setScript: fallDown)
			(event claimed: TRUE)
			(return)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb V_WALK)
				(curRoom setScript: fallDown)
			else
				(return FALSE)
			)
		)
	)
)
