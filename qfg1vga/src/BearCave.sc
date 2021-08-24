;;; Sierra Script 1.0 - (do not remove this comment)
(script# 14)
(include game.sh) (include "14.shm")
(use Main)
(use CastDart)
(use ThrowKnife)
(use ThrowRock)
(use Target)
(use Procs)
(use Polygon)
(use Feature)
(use LoadMany)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm14 0
)
(enum 1
	cueRation
	cueFlower
	cueFruit
	cueVegetable
)

(local
	bearCue
	bearState
	local2
	local3
	bearKillsEgo
	distFromBear
	burnedBear
	hurtBear
	dazzledBear
	calmedBear
	bearState0
	bearState1
	bearState2
	dartX
	dartY
	bearState5
	bearState6
	bearState7 = [253 295 149 226 284 319 93 152 223 29 65]
	local28 = [101 64 19 164 152 103 91 61 58 130 54]
	dripX = [28 63 83 102 235 295 195 39 251]
	dripY = [93 85 79 79 86 99 98 103 90 146 124 319 189]
)
(procedure (withBearPoly)
	(curRoom
		addObstacle:
			((Polygon new:)
				type: PBarredAccess
				init:
					0 0
					319 0
					319 88
					293 94
					244 94
					94 121
					41 107
					43 119
					81 124
					55 133
					19 130
					0 135
				yourself:
			)
			((Polygon new:)
				type: PBarredAccess
				init:
					110 189
					184 157
					177 178
					221 181
					250 166
					231 162
					209 174
					198 161
					319 109
					319 189
				yourself:
			)
			((Polygon new:)
				type: PBarredAccess
				init:
					231 103
					249 115
					214 134
					190 138
					167 127
					167 113
				yourself:
			)
	)
)

(procedure (noBearPoly)
	(curRoom
		addObstacle:
			((Polygon new:)
				type: PBarredAccess
				init:
					0 0
					319 0
					319 88
					293 94
					244 94
					94 121
					41 107
					43 119
					81 124
					55 133
					19 130
					0 135
				yourself:
			)
			((Polygon new:)
				type: PBarredAccess
				init:
					110 189
					184 157
					177 178
					221 181
					250 166
					231 162
					209 174
					198 161
					319 109
					319 189
				yourself:
			)
	)
)

(procedure (KilledByBear)
	(EgoDead C_DIE_BEAR C_DIE_BEAR_TITLE 0 0 800)
)

(procedure (localproc_049f)
	(= local2 (if (< (ego x?) (bear x?)) 110 else 20))
	(= local3 (if (< (ego x?) (bear x?)) 40 else 12))
)

(procedure (localproc_0cb2)
	(= bearState1 (- (bear x?) (ego x?)))
	(= bearState2 (- (bear y?) (ego y?)))
	(= dartX (+ (ego x?) (* bearState1 30)))
	(= dartY (+ (ego y?) (* bearState2 30)))
)

(instance rm14 of Room
	(properties
		noun N_ROOM
		picture 14
		style DISSOLVE
		horizon 92
	)
	
	(method (init)
		(if (not (Btst fBearGone))
			(withBearPoly)
			(LoadMany VIEW vBear 516 14 510)
			(= monsterHealth MAX_HP_BEAR)
			(= monsterNum vBear)
		else
			(noBearPoly)
		)
		(Load SOUND 20)
		(super init:)
		(if (== egoGait MOVE_RUN)
			(ChangeGait MOVE_WALK FALSE)
		)
		(if (or (!= prevRoomNum 15) (== (cSound prevSignal?) -1))
			(cSound priority: 1 number: 20 loop: -1 play:)
		)
		(if (not (Btst fBearGone))
			(bear init:)
			(if (ego knows: FLAMEDART)
				(egoShoots number: 33 init:)
				(magicHit number: 45 init:)
				(dart init:)
				(puff init:)
			)
		)
		(drip init: setScript: dripScript)
		(stalactites init:)
		(stalagmites init:)
		(caveGlow init:)
		(NormalEgo)
		(ego setScript: cmonIn)
	)
	
	(method (doit)
		(if (and (ego edgeHit?) (not (ego script?)))
			(ego setScript: movinOn)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset fBeenIn14)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_ROOM V_LOOK NULL)
			)
			(V_CALM
				(VerbSpell V_CALM)
				(= calmedBear TRUE)
				(dazCalmTimer setReal: dazCalmTimer 30)
			)
			(V_DAZZLE
				(VerbSpell V_DAZZLE)
				(= dazzledBear TRUE)
				(dazCalmTimer setReal: dazCalmTimer 5)
			)
			(V_DETECT
				(if (Btst fBearGone)
					(messager say: N_ROOM V_DETECT 9)
				else
					(messager say: N_ROOM V_DETECT 10)
				)
			)
			(V_FLAME
				(dart setScript: flameDart)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (newRoom n)
		(dazCalmTimer dispose: delete:)
		(bearTimer dispose: delete:)
		(super newRoom: n)
	)
)

(instance stalactites of Feature
	(properties
		y 1
		noun N_STALACTITES
		nsBottom 189
		nsRight 319
		sightAngle 40
		onMeCheck cBLUE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_STALACTITES V_LOOK NULL)
			)
			(V_DO
				(messager say: N_STALACTITES V_DO NULL)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance stalagmites of Feature
	(properties
		y 1
		noun N_STALAGMITES
		nsBottom 189
		nsRight 319
		sightAngle 40
		onMeCheck cGREEN
	)
)

(instance caveGlow of Feature
	(properties
		y 1
		noun N_CAVEGLOW
		nsBottom 189
		nsRight 319
		sightAngle 40
		onMeCheck cCYAN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(if (not (Btst fBearGone))
				(messager say: N_CAVEGLOW V_LOOK C_BEAR_HERE)
			else
				(messager say: N_CAVEGLOW V_LOOK C_BEAR_GONE)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance puff of Prop
	(properties
		z 25
		view 522
		loop 3
	)
)

(instance drip of Prop
	(properties
		view 14
	)
)

(instance dart of Actor
	(properties
		z 25
		view 522
	)
)

(instance bear of TargActor
	(properties
		x 243
		y 116
		noun N_BEAR
		view vBear
		signal ignrAct
		targDeltaX -28
		targDeltaY 20
	)
	
	(method (doit)
		(= distFromBear (ego distanceTo: self))
		(cond 
			((or bearKillsEgo (OneOf bearState 4 5)) 0)
			(dazzledBear
				(if (not (OneOf bearState 1 3))
					(self setScript: bearUp)
					(bearTimer setReal: bearTimer 30)
				)
			)
			((or (Btst fBearFriendly) calmedBear)
				(localproc_049f)
				(if (OneOf bearState 3 1) (self setScript: bearDrop))
			)
			((> distFromBear local2)
				(if (and (not hurtBear) (OneOf bearState 1 3))
					(self setScript: bearDrop)
				)
			)
			((and (>= local2 distFromBear) (>= distFromBear local3))
				(if (and (not hurtBear) (== bearState 0))
					(self setScript: bearUp)
				)
			)
			((> (+ (ego y?) 15) (bear y?))
				(cond 
					((< (ego x?) (- (bear x?) 15))
						(if (not bearKillsEgo)
							(++ bearKillsEgo)
							(self setScript: bearKills)
						)
					)
					((not bearKillsEgo)
						(++ bearKillsEgo)
						(self setScript: bearKillsRm15)
					)
				)
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(cond 
					((or calmedBear (Btst fBearFriendly))
						(messager say: N_BEAR V_LOOK C_BEAR_DOCILE)
					)
					(dazzledBear
						(messager say: N_BEAR V_LOOK C_BEAR_STUNNED)
					)
					(burnedBear
						(messager say: N_BEAR V_LOOK C_BEAR_SCORCHED)
					)
					((OneOf bearState 0 2)
						(messager say: N_BEAR V_LOOK C_BEAR_NEUTRAL)
					)
					(else
						(messager say: N_BEAR V_LOOK C_BEAR_HOSTILE)
					)
				)
			)
			(V_TALK
				(if (Btst fTalkedToBear)
					(messager say: N_BEAR V_ALTTALK C_TALKBEAR2)
				else
					(Bset fTalkedToBear)
					(messager say: N_BEAR V_ALTTALK C_TALKBEAR1)
				)
			)
			(V_DO
				(messager say: N_BEAR V_DO NULL)
			)
			(V_RATIONS
				(= calmedBear TRUE)
				(Bset fBearFriendly)
				(messager say: N_BEAR V_RATIONS C_BEAR_FED)
				(SolvePuzzle f14CalmBear 5)
				(dazCalmTimer setReal: dazCalmTimer 30)
				(= bearCue cueRation)
				(ego setScript: cueItScript)
			)
			(V_FLOWERS
				(messager say: N_BEAR V_FLOWERS)
				(= bearCue cueFlower)
				(ego setScript: cueItScript)
			)
			(V_FRUIT
				(= calmedBear TRUE)
				(Bset fBearFriendly)
				(messager say: N_BEAR V_RATIONS C_BEAR_FED)
				(SolvePuzzle f14CalmBear 5)
				(dazCalmTimer setReal: dazCalmTimer 30)
				(= bearCue cueFruit)
				(ego setScript: cueItScript)
			)
			(V_VEGETABLES
				(= calmedBear TRUE)
				(Bset fBearFriendly)
				(messager say: N_BEAR V_RATIONS C_BEAR_FED)
				(SolvePuzzle f14CalmBear 5)
				(dazCalmTimer setReal: dazCalmTimer 30)
				(= bearCue cueVegetable)
				(ego setScript: cueItScript)
			)
			(V_THIEFKIT
				(messager say: N_BEAR V_LOCKPICK)
			)
			(V_LOCKPICK
				(messager say: N_BEAR V_LOCKPICK)
			)
			(V_BRASSKEY
				(if (or dazzledBear calmedBear (Btst fBearFriendly))
					(SolvePuzzle f14FreeBear 25)
					(self setScript: useKey)
				else
					(messager say: N_BEAR V_BRASSKEY)
				)
			)
			(V_SWORD
				(curRoom newRoom: vBear)
			)
			(V_FLAME
				(CastDart self)
			)
			(V_DAGGER
				(ThrowKnife self)
			)
			(V_ROCK
				(ThrowRock self)
			)
		)
	)
	
	(method (getHurt damage)
		(cond 
			((<= (-= monsterHealth damage) 0)
				(Bset fBearDying)
				(curRoom newRoom: 171)
			)
			((OneOf bearState 0 2)
				(self setScript: bearUp)
			)
			(else
				(self setLoop: 1 cel: 0 setCycle: EndLoop)
			)
		)
	)
)

(instance movinOn of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(switch (ego edgeHit?)
					(WEST
						(ego
							setMotion: MoveTo (- (ego x?) 35) (+ (ego y?) 20) self
						)
					)
					(SOUTH
						(ego
							setMotion: MoveTo (- (ego x?) 75) (+ (ego y?) 50) self
						)
					)
					(else 
						(ego setMotion: MoveTo 340 95 self)
					)
				)
			)
			(1
				(curRoom
					newRoom: (if (OneOf (ego edgeHit?) WEST SOUTH) 13 else 15)
				)
			)
		)
	)
)

(instance cmonIn of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register
					(if (not (Btst fBearGone)) (not (Btst fBearCaveMessage)) else 0)
				)
				(HandsOff)
				(if (== prevRoomNum 15)
					(= local2 20)
					(= local3 12)
					(ego
						heading: 225
						loop: 5
						init:
						posn: 300 100
						setMotion: MoveTo 280 110 self
					)
				else
					(= local2 110)
					(= local3 40)
					(ego init: posn: -15 225 setMotion: MoveTo 30 180 self)
				)
			)
			(1
				(bear cel: 1)
				(= ticks (if register 90 else 12))
			)
			(2
				(if register
					(messager say: N_ROOM NULL C_FIRST_ENTRY)
					(Bset fBearCaveMessage)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance bearUp of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(= bearState 1)
				(bear setCycle: CycleTo 4 1)
				(= seconds 4)
			)
			(1
				(bear setCycle: EndLoop self)
			)
			(2
				(if
					(and
						(not hurtBear)
						(not (Btst fMetBear))
						(not dazzledBear)
					)
					(messager say: N_ROOM NULL C_BEAR_HOSTILE)
					(Bset fMetBear)
				)
				(= bearState 3)
			)
		)
	)
)

(instance bearDrop of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== bearState 1)
					(= bearState 2)
					(bear setCycle: BegLoop self)
				else
					(= bearState 2)
					(bear setLoop: 0 cel: 5 setCycle: BegLoop self)
				)
			)
			(1 (= bearState 0))
		)
	)
)

(instance flameDart of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(++ hurtBear)
				(TrySkill MAGIC 0 5)
				(TrySkill FLAMEDART 0 5)
				(ego
					view: 522
					setLoop: (if (< (ego x?) (bear x?)) 0 else 1)
					cel: 0
					setCycle: CycleTo 5 1 self
				)
			)
			(1
				(egoShoots play:)
				(ego setCycle: EndLoop)
				(= bearState0 (Random 0 300))
				(if
					(or
						(< bearState0 (= distFromBear (ego distanceTo: bear)))
						(OneOf bearState 1 2)
					)
					(dart setScript: bouncer)
					(++ state)
					(self cue:)
				else
					(dart
						setLoop: 2
						setStep: 18 12
						setPri: 12
						ignoreActors: TRUE
						posn: (ego x?) (ego y?)
						show:
						setCycle: Forward
					)
					(++ burnedBear)
					(dart
						setMotion:
							MoveTo
							(+ (bear x?) (- (Random 0 10) 10))
							(+ (bear y?) (- (Random 0 17) 19))
							self
					)
					(bear getHurt: (+ 5 (/ [egoStats STR] 10)))
				)
			)
			(2
				(magicHit play:)
				(dart setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(3
				(NormalEgo)
				(Face ego bear self)
			)
			(4
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance bouncer of Script
	(method (doit)
		(if (and bearState5 (not (dart inRect: 10 35 310 205)))
			(= bearState5 0)
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= bearState6 (Random 3 5))
				(= bearState5 1)
				(localproc_0cb2)
				(dart
					posn: (ego x?) (ego y?)
					show:
					setLoop: 2
					setStep: 18 12
					setPri: 12
					ignoreActors:
					setCycle: Forward
					startUpd:
					setMotion: MoveTo dartX dartY
				)
			)
			(1
				(puff
					ignoreActors:
					cel: 0
					setPri: 12
					posn: (dart x?) (dart y?)
					setCycle: EndLoop
				)
				(+= bearState6 (Random 1 3))
				(dart
					setMotion: MoveTo [bearState7 bearState6] [local28 bearState6] self
				)
			)
			(2
				(if (< bearState6 10)
					(-= state 2))
				(self cue:)
			)
			(3
				(dart setLoop: 3 cel: 0 setMotion: 0 setCycle: EndLoop self)
			)
			(4 (self dispose:))
		)
	)
)

(instance bearKills of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 18)
			)
			(1
				(bear loop: 2 cel: 0 setCycle: EndLoop)
				(= ticks 6)
			)
			(2
				(bear loop: 3 cel: 0 setCycle: EndLoop)
				(ego
					view: 516
					loop: 2
					cel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(3
				(= ticks 72)
			)
			(4
				(KilledByBear)
			)
		)
	)
)

(instance bearKillsRm15 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: 1 setMotion: MoveTo 235 (ego y?) self)
			)
			(1
				(bear loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(2
				(bear loop: 3 cel: 0 setCycle: EndLoop)
				(ego
					view: 516
					loop: 2
					cel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(3
				(= ticks 90)
			)
			(4
				(KilledByBear)
			)
		)
	)
)

(instance useKey of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego use: iBrassKey)
				(HandsOff)
				(= bearState 5)
				(bear setCycle: EndLoop self)
			)
			(1
				(ego ignoreActors: setMotion: MoveTo 209 149 self)
			)
			(2
				(bear stopUpd:)
				(ego
					setMotion: MoveTo (- (bear x?) 22) (+ (bear y?) 10) self
				)
			)
			(3
				(ego view: 510 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(4
				(messager say: N_ROOM NULL C_UNLOCK_CHAIN 1 self)
				(cSound stop:)
				(NormalEgo)
				(Bset fSavedBarnard)
			)
			(5
				(curRoom newRoom: 171)
			)
		)
	)
)

(instance dripScript of Script
	(method (changeState newState &tmp dripIndex)
		(switch (= state newState)
			(0 (= ticks (Random 1 200)))
			(1
				(= dripIndex (Random 0 8))
				(client
					cel: 0
					posn: [dripX dripIndex] [dripY dripIndex]
					init:
					setCycle: EndLoop self
				)
			)
			(2
				(dripDrip play:)
				(self changeState: 0)
			)
		)
	)
)

(instance cueItScript of Script
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= ticks 60))
			(1
				(switch bearCue
					(cueRation (ego use: iRations))
					(cueFlower (ego use: iFlowers))
					(cueFruit (ego use: iFruit))
					(cueVegetable (ego use: iVegetables))
				)
				(self cue:)
			)
			(2 (self dispose:))
		)
	)
)

(instance dazCalmTimer of Timer
	(method (cue)
		(= calmedBear (= dazzledBear FALSE))
	)
)

(instance bearTimer of Timer
	(method (cue)
		(if (== bearState 3)
			(bear setScript: 0)
		)
	)
)

(instance dripDrip of Sound
	(properties
		number 116
	)
)

(instance magicHit of Sound
	(properties
		number 45
		priority 1
	)
)

(instance egoShoots of Sound
	(properties
		number 33
		priority 2
	)
)
