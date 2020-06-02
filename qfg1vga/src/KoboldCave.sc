;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15)
(include game.sh) (include "15.shm")
(use Main)
(use CastFlame)
(use Target)
(use Procs)
(use Print)
(use Polygon)
(use Feature)
(use LoadMany)
(use Window)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(enum 1
	cueToadstools
	cueTreasure
	cueKey
)

(public
	rm15 0
	KoboldFight 1
	chest 2
	kobKey 3
	kobDazzle 4
	chestBlows 5
	AwakenKobold 6
	KoboldHurtEgo 7
	egoFighting 8
)

(local
	[local0 3] = [299 306 317]
	[local3 3] = [71 92 125]
	local6
	local7
	theCycles
	theSwBlow
	local10
	theSwDodge
	theSwParry
	chestLockpickFail
	newSound
	[local15 5] = [-24 30 -21 27 3]
	[local20 5] = [-11 -11 -11 -11 -23]
	[local25 11] = [52 88 35 258 122 196 303 316 140 270 211]
	[local36 11] = [12 113 96 146 -2 87 63 92 160 5 36]
	theEgoKoboldBattleLoop_2
	local48
	gotKey
	local50
	local51
	local52
	local53
	local54
	local55
	local56
	takeItem
)
(procedure (KoboldFight param1)
	(if (and param1 (not (Btst 283))) (HandsOn))
	(if fightingKobold
		(User canControl: FALSE)
		(if (ego has: iSword)
			(ego view: 501 setLoop: egoKoboldBattleLoop)
		else
			(ego view: 512 setLoop: (* egoKoboldBattleLoop 5))
		)
		(ego setCel: 0 illegalBits: 0 cycleSpeed: 6)
	else
		(NormalEgo)
		(ego illegalBits: koboldIllBits)
	)
)

(procedure (AwakenKobold)
	(return
		(if
			(and
				(not (Btst fKoboldAwake))
				(not (Btst fKoboldDead))
				(!= (kobold script?) kobWakeUp)
			)
			(kobold setScript: kobWakeUp)
			(return 1)
		else
			(return 0)
		)
	)
)

(procedure (KoboldHurtEgo param1)
	(if (and (not (TakeDamage param1)) (not local56))
		(= local56 1)
		(= local50 0)
		(ego setScript: egoDies)
	)
)

(procedure (TakeTreasure)
	(Bset fKoboldChestSearched)
	(SolvePuzzle f15GetTreasure 5)
	(messager say: N_ROOM NULL C_GET_TREASURE)
	(if (IsObject treasure) (treasure dispose:))
	(= koboldIllBits (& koboldIllBits $dfff))
	(= takeItem cueTreasure)
	(ego illegalBits: koboldIllBits setScript: getItCued)
)

(procedure (localproc_02de &tmp temp0 temp1)
	(if
		(!=
			(= temp1
				(cond 
					(
						(<
							(= temp0
								(GetAngle (kobold x?) (kobold y?) (ego x?) (ego y?))
							)
							110
						)
						1
					)
					((< temp0 160) 3)
					((> temp0 250) 0)
					((> temp0 220) 2)
					(else 4)
				)
			)
			(kobold loop?)
		)
		(kobold setLoop: temp1)
	)
)

(procedure (localproc_034f param1 param2 param3)
	(if (CastSpell param1)
		(Bset fFightingKobold)
		(AwakenKobold)
		(ego setScript: (ScriptID param2 param3))
	)
)

(procedure (localproc_0377 param1 param2 param3)
	(cond 
		((Btst fKoboldAwake) (messager say: N_ROOM 0 18))
		((CastSpell param1) (ego setScript: (ScriptID param2 param3)))
	)
)

(class KScript of Script
	(properties
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
	)
	
	(method (cue)
		(if client (super cue:))
	)
)

(class ballScript of KScript
	(properties
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
	)
	
	(method (doit)
		(cond 
			((Btst fKoboldDead) (client dispose:))
			(
			(and (< state 2) (not (client inRect: 10 30 310 200))) (= register 0) (self changeState: 2))
			(else (super doit:))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ballSound number: 34 play:)
				(if
					(>=
						(-
							(- 100 (/ [egoStats AGIL] 2))
							(/ (ego distanceTo: kobold) 5)
						)
						(Rand100)
					)
					(client setMotion: MoveTo (ego x?) (ego y?) self)
				else
					(client
						setMotion:
							MoveTo
							[local25 register]
							[local36 (= register (Random 0 2))]
							self
					)
				)
			)
			(1
				(cond 
					((< (ego distanceTo: client) 20) (= register 1) (self cue:))
					(
					(>= (= register (+ register (Random 1 4))) 11) (= register 0) (self cue:))
					(else
						(client
							setMotion: MoveTo [local25 register] [local36 (= state 0)] self
						)
					)
				)
			)
			(2
				(ballSound number: 45 play:)
				(client
					setLoop: (if register 7 else 6)
					cel: 0
					setMotion: 0
					setCycle: EndLoop self
				)
				(if register
					(KoboldHurtEgo (if (ego has: 5) 15 else 20))
				)
			)
			(3 (client dispose:))
		)
	)
)

(instance rm15 of Room
	(properties
		noun N_ROOM
		picture 15
		style DISSOLVE
		west 14
	)
	
	(method (init &tmp [temp0 50])
		(= local55 0)
		(= disabledActions
			(| (= disabledActions (| disabledActions ACTION_REST)) ACTION_RUN)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						0
						57
						99
						56
						111
						62
						99
						96
						91
						102
						5
						102
						6
						162
						68
						162
						67
						147
						107
						148
						115
						148
						119
						152
						136
						165
						185
						169
						309
						169
						313
						89
						240
						89
						216
						77
						184
						77
						147
						68
						144
						63
						148
						57
						78
						39
						81
						32
						67
						29
						0
						29
						0
						56
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 261 98 279 114 260 136 208 136 189 118 190 107 204 98
					yourself:
				)
		)
		(Load RES_SCRIPT 111)
		(= fightingKobold (= fightingKoboldStart 0))
		(if [egoStats MAGIC]
			(Load RES_SOUND 33)
			(LoadMany RES_VIEW 520 521 522 532)
		)
		(= local50 0)
		(if (Btst fGotKoboldKey)
			(= gotKey TRUE)
		else
			(= gotKey FALSE)
		)
		(if (not (Btst fKoboldDead))
			(LoadMany SOUND 45 34)
			(LoadMany VIEW 513 175 176 178 502 510)
			(if (ego has: iSword)
				(Load VIEW 501)
			else
				(LoadMany VIEW 512 524)
			)
		)
		(if (not (Btst fKoboldChestUnlocked))
			(LoadMany SOUND 62)
		)
		(SolvePuzzle f15EnterCave 2)
		(StatusLine enable:)
		(super init:)
		(keyDownHandler add: self)
		(mouseDownHandler add: self)
		(ChangeGait MOVE_WALK FALSE)
		(NormalEgo)
		(ego posn: 15 46 init:)
		(drip init: setScript: dripper)
		(bigDrip init: stopUpd:)
		(if (not (Btst fTookToadstools))
			(toadstools init: setPri: 9)
			(toadstools1 init: setPri: 9)
			(toadstools2 init: setPri: 9)
		else
			(table init:)
		)
		(stalagmites init:)
		(sunLight init:)
		(if (not (Btst fKoboldChestUnlocked))
			(chest init: approachVerbs: V_LOCKPICK stopUpd:)
			(= koboldIllBits (| koboldIllBits cLMAGENTA))
		)
		(if (not (Btst fGotKoboldKey))
			(if egoKoboldBattleLoop
				(kobKey posn: 52 84)
			)
			(kobKey
				ignoreActors:
				setPri: 5
				init:
				approachVerbs: 4
				stopUpd:
			)
			(if (not (Btst fKoboldDead))
				(kobKey posn: 500 500)
			)
		)
		(if (not (Btst fKoboldDead))
			(kobold init:)
			(= theKobold kobold)
		)
		(ego illegalBits: koboldIllBits)
		(self setScript: kobEnter)
	)
	
	(method (doit)
		(if
		(and (< (ego x?) 35) (< (ego y?) 60) local55)
			(ego setMotion: 0)
			(curRoom newRoom: 14)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn15)
		(Bclr fFlag281)
		(Bclr fKoboldChestKnown)
		(= disabledActions 0)
		(DisposeScript 111)
		(super dispose:)
	)
	
	(method (doVerb theVerb param2 &tmp dartX temp1 evt)
		(return
			(switch theVerb
				(V_FLAME
					(= dartX ((= evt (Event new:)) x?))
					(= temp1 (+ (evt y?) 25))
					(evt dispose:)
					(CastFlame 0 0 dartX temp1)
					(Bset fFightingKobold)
				)
				(V_LOOK
					(= local53 1)
					(messager say: N_ROOM V_LOOK 0 1 curRoom)
				)
				(V_DETECT
					(localproc_0377 DETMAGIC 111 1)
					(return TRUE)
				)
				(V_OPEN
					(if (and (not (Btst fKoboldChestUnlocked)) (cast contains: chest))
						(localproc_0377 OPEN 111 2)
					else
						(messager say: N_ROOM V_OPEN 0)
					)
					(return TRUE)
				)
				(V_TRIGGER
					(if (and (not (Btst fKoboldChestUnlocked)) (cast contains: chest))
						(localproc_0377 TRIGGER 111 3)
					else
						(messager say: N_ROOM V_TRIGGER 0)
					)
					(return TRUE)
				)
				(V_FETCH
					(if
					(and (not (Btst fKoboldDead)) (cast contains: kobKey))
						(localproc_0377 FETCH 111 5)
					else
						(messager say: N_ROOM V_FETCH 0)
					)
					(return TRUE)
				)
				(V_DAZZLE
					(if (not (Btst fKoboldDead)) (localproc_034f DAZZLE 111 0))
					(return TRUE)
				)
				(V_CALM
					(localproc_034f CALM 111 4)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
	
	(method (cue)
		(cond 
			((== local53 0) (if (not (Btst fGotKoboldKey)) (messager say: N_KOBOLD V_LOOK C_KOBOLD_HAS_KEY)))
			((not (Btst fKoboldDead)) (messager say: N_ROOM V_LOOK 0 2))
		)
	)
)

(instance stalagmites of Feature
	(properties
		x 319
		y 189
		noun N_STALAGMITES
		nsBottom 189
		nsRight 319
		sightAngle 40
		onMeCheck $0004
	)
)

(instance sunLight of Feature
	(properties
		x 319
		y 189
		noun N_SUNLIGHT
		nsBottom 189
		nsRight 319
		sightAngle 40
		onMeCheck $0010
	)
)

(instance fungus of Feature
	(properties
		x 319
		y 189
		noun N_FUNGUS
		nsBottom 189
		nsRight 319
		sightAngle 40
		onMeCheck $0404
	)
)

(instance table of Feature
	(properties
		x 319
		y 189
		noun N_TABLE
		nsBottom 189
		nsRight 319
		sightAngle 40
		onMeCheck $0008
	)
)

(instance kobKey of Actor
	(properties
		x 229
		y 85
		noun N_KOBKEY
		view 178
		loop 6
		cel 12
		illegalBits $0000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(= gotKey TRUE)
				(Bset fGotKoboldKey)
				(SolvePuzzle f15GetKey 7)
				(messager say: N_KOBKEY NULL NULL 1 self)
			)
			(V_LOOK
				(messager say: N_KOBKEY V_LOOK)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(ego get: iBrassKey)
		(kobKey dispose:)
	)
)

(instance chest of Prop
	(properties
		x 132
		y 173
		noun N_CHEST
		approachX 132
		approachY 160
		view 15
		loop 1
	)
	
	(method (doit)
		(if (and (< (ego distanceTo: chest) 10) (not (Btst fKoboldChestKnown)))
			(messager say: N_CHEST NULL NULL)
			(chest setCel: 1)
			(Bset fKoboldChestKnown)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_SWORD
				(cond 
					((Btst fKoboldChestUnlocked)
						(messager say: N_CHEST V_LOCKPICK 1)
					)
					((not (< (ego distanceTo: chest) 80))
						(messager say: N_CHEST V_LOCKPICK 3)
					)
					(else
						(ego setScript: pickChest 0 1)
					)
				)
			)
			(V_DAGGER
				(cond 
					((Btst fKoboldChestUnlocked)
						(messager say: N_CHEST V_LOCKPICK 1)
					)
					((not (< (ego distanceTo: chest) 80))
						(messager say: N_CHEST V_LOCKPICK 3)
					)
					(else
						(ego setScript: pickChest 0 1)
					)
				)
			)
			(V_DO
				(cond 
					(
						(and
							(< (ego distanceTo: chest) 80)
							(cast contains: treasure)
						)
						(TakeTreasure treasure)
					)
					((Btst fKoboldChestSearched)
						(messager say: N_CHEST V_DO 2)
					)
					((not (< (ego distanceTo: chest) 80))
						(messager say: N_CHEST V_DO 3)
					)
					((not (Btst fKoboldChestUnlocked))
						(messager say: N_CHEST V_DO 1)
					)
					(else (TakeTreasure 0))
				)
			)
			(V_LOCKPICK
				(cond 
					((or (not (Btst fKoboldChestKnown)) (not (cast contains: chest)))
						(messager say: N_CHEST V_LOCKPICK 5)
					)
					((Btst fKoboldChestUnlocked)
						(messager say: N_CHEST V_LOCKPICK 1)
					)
					((not (< (ego distanceTo: chest) 80))
						(messager say: N_CHEST V_LOCKPICK 3)
					)
					((not [egoStats PICK])
						(messager say: N_CHEST V_LOCKPICK C_NO_PICK)
					)
					(else (ego setScript: pickChest 0 0))
				)
			)
			(V_BRASSKEY
				(if (and (Btst fKoboldChestKnown) (cast contains: chest))
					(messager say: N_CHEST V_BRASSKEY 0)
				)
			)
			(V_LOOK
				(cond 
					((cast contains: treasure) (messager say: N_CHEST V_LOOK 8))
					((not (cast contains: chest)) (messager say: N_CHEST V_LOOK 7))
					((not (Btst fKoboldChestKnown)) (messager say: N_CHEST V_LOOK 5))
					((Btst fKoboldChestSearched) (messager say: N_CHEST V_LOOK C_ALREADY_GOT_TREASURE))
					((Btst fKoboldChestUnlocked) (messager say: N_CHEST V_LOOK 1))
					(else (messager say: N_CHEST V_LOOK 6))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance chestBlows of KScript
	(properties)
	
	(method (dispose)
		(if (>= state 1) (newSound dispose:))
		(= chestLockpickFail 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client loop: 2 cel: 0 cycleSpeed: 12 setCycle: EndLoop self)
				((= newSound (Sound new:))
					number: 62
					priority: 15
					init:
					play:
				)
				(if register
					(= register 0)
					(ego setLoop: 2)
				else
					(ego setLoop: (if (mod (ego loop?) 2) 3 else 2))
				)
				(ego
					view: 513
					loop: (if (mod (ego loop?) 2) 3 else 2)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(Bset fKoboldChestUnlocked)
				(AwakenKobold)
				(if (< (ego distanceTo: chest) 80)
					(= chestLockpickFail 1)
					(KoboldHurtEgo 20)
				)
			)
			(2
				(= cycles 1)
				(if (< (ego distanceTo: chest) 80)
					(messager say: N_ROOM 0 3)
					(= cycles 5)
				)
			)
			(3
				(treasure init:)
				(KoboldFight 1)
				(Face ego treasure)
				(client dispose:)
			)
		)
	)
)

(instance treasure of View
	(properties
		x 132
		y 173
		view 15
		loop 2
		cel 6
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(if (< (ego distanceTo: chest) 80)
				(TakeTreasure treasure)
			else
				(messager say: N_CHEST V_DO 3)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance toadstools1 of View
	(properties
		x 233
		y 111
		view 15
		loop 3
	)
	
	(method (doVerb theVerb)
		(toadstools doVerb: theVerb)
	)
)

(instance toadstools2 of View
	(properties
		x 242
		y 111
		view 15
		loop 3
	)
	
	(method (doVerb theVerb)
		(toadstools doVerb: theVerb)
	)
)

(instance toadstools of View
	(properties
		x 224
		y 111
		noun N_TOADSTOOLS
		view 15
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_TOADSTOOLS V_LOOK))
			(V_DO
				(cond 
					(fightingKobold (messager say: N_TOADSTOOLS V_DO C_CANT_GET_TOADSTOOLS))
					((< (ego distanceTo: toadstools) 50)
						(messager say: N_TOADSTOOLS V_DO C_GET_TOADSTOOLS)
						(Bset fTookToadstools)
						(Bset fHaveToadstools)
						(toadstools dispose:)
						(toadstools1 dispose:)
						(toadstools2 dispose:)
						(table init:)
						(= takeItem cueToadstools)
						(ego setScript: getItCued)
					)
					(else (messager say: N_CHEST V_LOCKPICK 3 1))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance drip of Prop
	(properties
		view 15
	)
	
	(method (init)
		(= nightPalette 1175)
		(PalVary PALVARYTARGET 1175)
		(kernel_128 175)
		(super init:)
	)
)

(instance bigDrip of Prop
	(properties
		view 15
		loop 4
	)
)

(instance dripper of KScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register (Random 0 2))
				(if (< register 2)
					(bigDrip
						posn: [local0 register] [local3 register]
						setCycle: EndLoop self
					)
				else
					(drip
						posn: [local0 register] [local3 register]
						setCycle: EndLoop self
					)
				)
			)
			(1
				(if (< register 3)
					(bigDrip posn: 500 500 stopUpd:)
				else
					(drip posn: 500 500 stopUpd:)
				)
				(= state -1)
				(= cycles (Random 20 40))
			)
		)
	)
)

(instance kobWin of SysWindow
	(properties
		color 6
	)
)

(instance ballSound of Sound
	(properties
		number 34
		priority 2
	)
)

(instance kobold of TargActor
	(properties
		x 261
		y 61
		noun N_KOBOLD
		view 175
		loop 6
		cycleSpeed 24
		illegalBits $0000
		targDeltaY -15
	)
	
	(method (init)
		(Bclr fFlag280)
		(Bclr fFlag281)
		(ballSound init:)
		(= nightPalette 1175)
		(if (Btst fGotKoboldKey) (self setLoop: 5))
		(if (ego knows: FLAMEDART)
			(= damageToKoboldFlame (+ 5 (/ [egoStats FLAMEDART] 3)))
		)
		(= egoKoboldBattleLoop 0)
		(self ignoreActors: posn: 256 68)
		(super init:)
		(= monsterHealth koboldHealth)
		(if
			(or
				(not (Btst fBeenIn15))
				(and
					(not Night)
					(!= Day dayKoboldAwakened)
				)
			)
			(= koboldHealth 67)
		else
			(Bset fKoboldAwake)
			(self setScript: kobAwake)
		)
	)
	
	(method (doit &tmp [str 50])
		(if
			(and
				(not (Btst fKoboldAwake))
				(not (Btst fKoboldDead))
				(< (ego distanceTo: self) 100)
				(or (!= egoGait MOVE_SNEAK) (< [egoStats STEALTH] 20))
			)
			(Bset fKoboldAwake)
			(Message MsgGet 15 N_KOBOLD NULL NULL 1 @str)
			(Print addText: @str init:)
			(ChangeGait MOVE_WALK FALSE)
			(self setScript: kobWakeUp)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bclr fKoboldAwake)
		(ballSound dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(= local53 0)
				(messager say: N_KOBOLD V_LOOK 0 0 curRoom)
			)
			(V_TALK
				(cond 
					((not (Btst fKoboldAwake)) (AwakenKobold))
					((not (Btst fKoboldDead)) (messager say: N_KOBOLD V_ALTTALK))
				)
			)
			(V_DO
				(if (not gotKey)
					(cond 
						((> (ego distanceTo: kobold) 40)
							(messager say: N_KOBOLD V_DO C_CANT_REACH_KEY)
						)
						((Btst fKoboldDead)
							(messager say: N_KOBOLD V_DO C_KOBOLD_DEAD)
							(= gotKey TRUE)
							(= takeItem cueKey)
							(ego setScript: getItCued)
							(Bset fGotKoboldKey)
							(SolvePuzzle f15GetKey 7)
							(kobKey dispose:)
						)
						((Btst fKoboldAwake)
							(messager say: N_KOBOLD V_DO 15)
						)
						((TrySkill STEALTH 35 0)
							(messager say: N_KOBOLD V_DO 16)
							(= gotKey TRUE)
							(= takeItem cueKey)
							(self setLoop: 5)
							(ego setScript: getItCued)
							(Bset fGotKoboldKey)
							(SolvePuzzle f15GetKey 7)
							(kobKey dispose:)
						)
						(else (messager say: N_KOBOLD V_DO 13) (AwakenKobold))
					)
				)
			)
			(V_DAGGER
				(cond 
					(fightingKobold (messager say: N_KOBOLD V_DAGGER C_ALREADY_FIGHTING))
					((Btst fKoboldDead) (messager say: N_KOBOLD V_DAGGER C_KOBOLD_DEAD))
					(else
						(AwakenKobold)
						(if (not local56)
							(pointBox setPri: 15 setLoop: 2 init: stopUpd:)
							(curRoom setScript: startFight)
						)
					)
				)
			)
			(V_SWORD
				(cond 
					(fightingKobold (messager say: N_KOBOLD V_SWORD C_ALREADY_FIGHTING))
					((Btst fKoboldDead) (messager say: N_KOBOLD V_SWORD C_KOBOLD_DEAD))
					(else
						(AwakenKobold)
						(if (not local56)
							(pointBox setPri: 15 setLoop: 2 init: stopUpd:)
							(curRoom setScript: startFight)
						)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (getHurt param1)
		(= koboldHealth (- koboldHealth param1))
		(if
		(not (if (== script kobHurt) else (== script kobDies)))
			(self setScript: kobHurt)
		)
	)
)

(instance kobDazzle of KScript
	(properties)
	
	(method (dispose)
		(= local7 (* register 5))
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SkillUsed DAZZLE 5)
				(= register (/ (+ 5 [egoStats DAZZLE]) 10))
				(if (> register 6) (= register 6))
				(client
					view: 179
					setCel: 0
					cycleSpeed: 18
					setCycle: CycleTo 1 1 self
				)
			)
			(1
				(kobold setCycle: CycleTo 4 1 self)
			)
			(2 (= ticks 18))
			(3
				(if (> (-- register) 0)
					(kobold setCel: 1)
					(= state 0)
					(= ticks 18)
				else
					(kobold setCycle: EndLoop self)
				)
			)
			(4 (client setScript: kobAwake))
		)
	)
)

(instance kobWakeUp of KScript
	(properties)
	
	(method (dispose)
		(if egoKoboldBattleLoop
			(client posn: 62 80)
		else
			(client posn: 256 68)
		)
		(super dispose:)
	)
	
	(method (changeState newState &tmp [str 40])
		(switch (= state newState)
			(0
				(Bset fKoboldAwake)
				(client setLoop: 5 setCycle: EndLoop self)
			)
			(1
				(localproc_02de)
				(= ticks 1)
			)
			(2
				(Message MsgGet 15 N_ROOM NULL C_KOBOLD_WARNS 1 @str)
				(Print addText: @str init:)
				(client setScript: kobAwake)
			)
		)
	)
)

(instance kobAwake of KScript
	(properties)
	
	(method (doit)
		(localproc_02de)
		(if (and (Btst 280) (== state 0))
			(Bclr 280)
			(self changeState: 3)
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp clientLoop)
		(switch (= state newState)
			(0
				(= dayKoboldAwakened Day)
				(client view: 175 cel: 0 setCycle: 0)
				(localproc_02de)
				(if egoKoboldBattleLoop
					(client posn: 62 80)
				else
					(client posn: 256 68)
				)
				(cond 
					(local7
						(= cycles (Random 25 50))
						(= local7 0)
					)
					((and (Btst fFightingKobold) (not (Btst fFlag281)))
						(= cycles (Random 5 15))
					)
					(koboldCycles
						(= cycles koboldCycles)
						(= koboldCycles 0)
					)
					(theCycles
						(= cycles theCycles)
					)
					(else
						(= cycles (Random 25 50))
					)
				)
			)
			(1
				(client view: 175)
				(cond 
					((and (Btst fFightingKobold) (not (Btst fFlag281)))
						(client setScript: castRev)
					)
					(theCycles (= theCycles 0)
						(client setScript: castTele)
					)
					(else
						(client view: 177 cycleSpeed: 18 setCycle: CycleTo 3 1 self)
					)
				)
			)
			(2
				(= clientLoop (client loop?))
				((Actor new:)
					ignoreActors:
					illegalBits: 0
					view: 177
					setLoop: 5
					setStep: 24 16
					posn:
						(+ (client x?) [local15 clientLoop])
						(+ (client y?) [local20 clientLoop])
						20
					init:
					setCycle: Forward
					setScript: (ballScript new:)
				)
				(= state -1)
				(client view: 175 cycleSpeed: 24 setCycle: EndLoop self)
			)
			(3
				(client view: 178 setCel: 0)
				(= cycles 2)
			)
			(4
				(client setCel: 1)
				(= cycles 2)
			)
			(5
				(client view: 175 cel: 0 setCel: -1 forceUpd:)
				(= state 0)
				(= cycles 5)
			)
		)
	)
)

(instance castRev of KScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					view: 176
					setLoop: 1
					cel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(1
				(if (not (Btst fKoboldCastingReversal))
					(Bset fKoboldCastingReversal)
					(messager say: N_ROOM NULL C_KOBOLD_REVERSAL)
				)
				(Bset fFlag281)
				(client setScript: kobAwake)
			)
		)
	)
)

(instance castTele of KScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					view: 176
					setLoop: 0
					cel: 0
					cycleSpeed: 6
					setCycle: EndLoop self
				)
			)
			(1
				(= egoKoboldBattleLoop (- 1 egoKoboldBattleLoop))
				(if local50
					(NormalEgo)
					(ego illegalBits: 0)
					(curRoom setScript: startFight)
				)
				(if egoKoboldBattleLoop
					(client posn: 62 80)
				else
					(client posn: 256 68)
				)
				(client setCycle: BegLoop self)
			)
			(2 (client setScript: kobAwake))
		)
	)
)

(instance egoDies of KScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= [egoStats HEALTH] 0)
				(pointBox dispose:)
				(client
					view: 513
					setLoop: egoKoboldBattleLoop
					cel: 0
					illegalBits: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(1
				(cond 
					((Btst fKoboldChestExploded) (EgoDead 96 97))
					(chestLockpickFail (EgoDead 37 38))
					(else (EgoDead 132 133))
				)
			)
		)
	)
)

(instance kobHurt of KScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fKoboldAwake)
				(if (Btst fKoboldDead)
					(self dispose:)
				else
					(if local6
						(local6 dispose:)
						(= local6 0)
						(if egoKoboldBattleLoop
							(client posn: 62 80)
						else
							(client posn: 256 68)
						)
					)
					(client view: 178 setCycle: 0 setMotion: 0)
					(localproc_02de)
					(client setCel: 2)
					(= cycles 5)
				)
			)
			(1
				(client setCel: 3)
				(= cycles 3)
			)
			(2
				(client setCel: -1)
				(if (> koboldHealth 0)
					(= theCycles 3)
					(client setScript: kobAwake)
				else
					(client setScript: kobDies)
				)
			)
		)
	)
)

(instance kobDies of KScript
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(pointBox dispose:)
				(Bset fKoboldDead)
				(Bclr fKoboldAwake)
				(Bclr fFlag281)
				(ego setScript: 0)
				(curRoom setScript: 0)
				(= koboldIllBits (& koboldIllBits $bfff))
				(client
					view: 178
					setLoop: 5
					cel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
				(if (not (Btst fGotKoboldKey))
					(kobKey
						illegalBits: 0
						ignoreActors:
						view: 178
						posn: (kobold x?) (kobold y?)
						approachX: (kobold x?)
						approachY: (kobold y?)
						setLoop: 7
						cel: 0
						setPri: 5
						cycleSpeed: 12
						setCycle: CycleTo 4 1 self
					)
				)
			)
			(1
				(= monsterNum (= monsterHealth 0))
				(if (or (== heroType FIGHTER) (== heroType MAGIC_USER))
					(SolvePuzzle f15BeatKbold 10)
				)
				(cSound number: 20 loop: -1 play:)
				(if fightingKobold
					(curRoom setScript: stopFight)
				else
					(HandsOn)
					(curRoom setScript: 0)
				)
				(client dispose:)
			)
		)
	)
)

(instance kobEnter of KScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 107 55 self)
			)
			(1
				(if (not (Btst fBeenIn15))
					(messager say: N_ROOM NULL C_FIRST_ENTRY)
				)
				(HandsOn)
				(= local55 1)
				(self dispose:)
			)
		)
	)
)

(instance egoFighting of KScript
	(properties)
	
	(method (dispose)
		(= local50 0)
		(directionHandler delete: self)
		(ego setScript: 0 illegalBits: koboldIllBits)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local50 1)
				(if (ego has: 2)
					(= theSwBlow swBlow)
					(= local10 (= theSwDodge swDodge))
					(= theSwParry swParry)
				else
					(= theSwBlow knStab)
					(= local10 (= theSwDodge (= theSwParry knDodge)))
				)
				(= fightingKobold TRUE)
				(directionHandler addToFront: self)
				(self cue:)
			)
			(1 (KoboldFight 1) (= state 0))
		)
	)
	
	(method (handleEvent event &tmp temp0 eventX eventY)
		(if local50
			(cond 
				((== (event type?) mouseDown)
					(= eventX (event x?))
					(cond 
						((< (= eventY (event y?)) 144)
							(cond 
								((> eventX 285)
									(if (not local54)
										(ego setScript: theSwBlow self 0)
									else
										1
									)
								)
								((< eventX 265)
									(if (not local54)
										(ego setScript: theSwDodge self 2)
									else
										1
									)
								)
							)
						)
						((> eventY 159)
							(cond 
								((> eventX 285)
									(if (not local54)
										(ego setScript: local10 self 1)
									else
										1
									)
								)
								((< eventX 265)
									(if (not local54)
										(ego setScript: theSwParry self 3)
									else
										1
									)
								)
							)
						)
					)
					(event claimed: TRUE)
				)
				(
					(and
						(<= JOY_UP (event message?))
						(<= (event message?) JOY_UPLEFT)
						(not (ego script?))
					)
					(HandsOff)
					(switch (event message?)
						(JOY_UPRIGHT
							(ego setScript: theSwBlow self 0)
						)
						(JOY_DOWNRIGHT
							(ego setScript: local10 self 1)
						)
						(JOY_UPLEFT
							(ego setScript: theSwDodge self 2)
						)
						(JOY_DOWNLEFT
							(ego setScript: theSwParry self 3)
						)
						(else 
							(HandsOn)
							(User canControl: 0)
						)
					)
				)
			)
		)
	)
)

(instance pickChest of KScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fKoboldChestKnown)
				(ego
					view: 510
					setLoop: (if (< (+ (chest x?) 14) (ego x?)) 1 else 0)
					cel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(1
				(= local51 (= local52 1))
				(cond 
					((and register (TrySkill STR 40 0))
						(Bset fKoboldChestExploded)
						(messager say: N_ROOM NULL C_FORCE_CHEST 0 self)
					)
					(register
						(messager say: N_ROOM NULL C_CHEST_TOO_WEAK 0 self)
						(= local52 0)
					)
					((TrySkill PICK 70 lockPickBonus)
						(messager say: N_ROOM 0 C_LOCKPICK_SUCCESS)
						(= local51 (= local52 0))
						(chest cycleSpeed: 12 setCycle: CycleTo 3 1 self)
						(Bset fKoboldChestUnlocked)
					)
					(else (messager say: N_ROOM 0 C_LOCKPICK_FAIL 0 self))
				)
			)
			(2
				(if local51 (AwakenKobold))
				(if local52
					(chest setScript: chestBlows)
				else
					(KoboldFight 1)
				)
				(self dispose:)
			)
		)
	)
)

(instance searchCave of KScript
	(properties)
	
	(method (doit)
		(if (Btst fKoboldAwake) (self dispose:) else (super doit:))
	)
	
	(method (dispose)
		(ego setAvoider: 0 setMotion: 0)
		(KoboldFight 1)
		(super dispose:)
		(DisposeScript AVOIDER)
		(DisposeScript SIGHT)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setAvoider: Avoider)
				(if (< (ego x?) 160)
					(= register 0)
					(ego setMotion: MoveTo 37 128 self)
				else
					(= register 1)
					(ego setMotion: MoveTo 302 158 self)
				)
			)
			(1 (= seconds 3))
			(2
				(if register
					(ego setMotion: MoveTo 37 128 self)
				else
					(ego setMotion: MoveTo 302 158 self)
				)
			)
			(3 (= seconds 2))
			(4
				(ego setMotion: MoveTo 132 163 self)
			)
			(5 (= seconds 2))
			(6
				(KoboldFight 1)
				(self dispose:)
			)
		)
	)
)

(instance startFight of KScript
	(properties)
	
	(method (dispose)
		(= fightingKoboldStart 0)
		(super dispose:)
	)
	
	(method (changeState newState &tmp theEgoKoboldBattleLoop)
		(switch (= state newState)
			(0
				(= register 0)
				(HandsOff)
				(ChangeGait MOVE_WALK FALSE)
				(ego illegalBits: 0)
				(if fightingKobold
					(self cue:)
				else
					(= fightingKoboldStart TRUE)
					(= fightingKobold FALSE)
					(if
						(<
							(= koboldEvade
								(-
									(/
										(+
											(* [egoStats WEAPON] 8)
											(* [egoStats AGIL] 4)
											(* [egoStats STR] 2)
											[egoStats INT]
											[egoStats LUCK]
										)
										16
									)
									20
								)
							)
							5
						)
						(= koboldEvade 5)
					)
					(= damageToKobold (+ 9 (/ [egoStats STR] 10)))
					(if (ego has: 2) (= damageToKobold (+ damageToKobold 3)))
					(if (< (ego y?) 105)
						(self cue:)
					else
						(ego setMotion: MoveTo 160 (ego y?) self)
					)
				)
			)
			(1
				(if (and fightingKobold (ego has: iSword))
					((ego cycler?) vWalking: 501 vStopped: 501)
					(ego setLoop: (if egoKoboldBattleLoop 10 else 11))
				)
				(if egoKoboldBattleLoop
					(ego setMotion: MoveTo 130 81 self)
				else
					(ego setMotion: MoveTo 198 92 self)
				)
			)
			(2
				(= theEgoKoboldBattleLoop egoKoboldBattleLoop)
				(if (not (ego has: iSword))
					(= theEgoKoboldBattleLoop (+ theEgoKoboldBattleLoop 2))
				)
				(if fightingKobold
					(ego setCycle: 0)
					(= ticks 12)
				else
					(ego
						view: 502
						setLoop: theEgoKoboldBattleLoop
						cycleSpeed: 12
						setCycle: EndLoop self
					)
				)
			)
			(3
				(client setScript: egoFighting)
			)
		)
	)
)

(instance stopFight of KScript
	(properties)
	
	(method (changeState newState &tmp theEgoKoboldBattleLoop)
		(switch (= state newState)
			(0
				(HandsOff)
				(= fightingKobold FALSE)
				(= theEgoKoboldBattleLoop egoKoboldBattleLoop)
				(if (not (ego has: iSword))
					(= theEgoKoboldBattleLoop (+ theEgoKoboldBattleLoop 2))
				)
				(ego
					view: 502
					setLoop: theEgoKoboldBattleLoop
					cel: (ego lastCel:)
					setCycle: BegLoop self
				)
			)
			(1
				(NormalEgo)
				(ego loop: egoKoboldBattleLoop)
				(if (not (Btst fKoboldDead)) (ego illegalBits: koboldIllBits))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance swBlow of KScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= theEgoKoboldBattleLoop_2 egoKoboldBattleLoop)
				(ego
					cel: 0
					setLoop: (+ egoKoboldBattleLoop 2)
					setCycle: EndLoop self
				)
			)
			(1
				(if
					(and
						(== egoKoboldBattleLoop theEgoKoboldBattleLoop_2)
						(>= koboldEvade (Rand100))
					)
					(theKobold getHurt: damageToKobold)
				)
				(= ticks 12)
			)
			(2
				(ego cel: 0 setLoop: egoKoboldBattleLoop)
				(self dispose:)
			)
		)
	)
)

(instance swDodge of KScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setLoop: egoKoboldBattleLoop setCel: register)
				(= cycles 10)
			)
			(1
				(ego setCel: 0)
				(self dispose:)
			)
		)
	)
)

(instance swParry of KScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setLoop: (+ egoKoboldBattleLoop 8) setCel: 0)
				(= cycles 8)
			)
			(1
				(ego setLoop: egoKoboldBattleLoop)
				(self dispose:)
			)
		)
	)
)

(instance knStab of KScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= theEgoKoboldBattleLoop_2 egoKoboldBattleLoop)
				(ego setLoop: (if egoKoboldBattleLoop 5 else 0))
				(ego setCel: -1 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(1
				(if
					(and
						(== egoKoboldBattleLoop theEgoKoboldBattleLoop_2)
						(>= koboldEvade (Rand100))
					)
					(theKobold getHurt: damageToKobold)
				)
				(= cycles 4)
			)
			(2
				(ego setCycle: EndLoop self)
				(= cycles register)
			)
			(3
				(ego setCel: 0)
				(self dispose:)
			)
		)
	)
)

(instance knDodge of KScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setLoop: (if egoKoboldBattleLoop (+ register 5) else register)
					setCel: 0
				)
				(= cycles 10)
			)
			(1
				(ego setLoop: (if egoKoboldBattleLoop 5 else 0))
				(self dispose:)
			)
		)
	)
)

(class inputBox of View
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0101
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		oldX 0
		oldY 0
		relX 280
		relY 155
		first 1
		leftBor 260
		rightBor 303
		topBor 130
		botBor 175
		value 0
	)
	
	(method (init)
		(theIconBar disable:)
		(theGame setCursor: normalCursor 1 280 155)
		(self setPri: 14 ignoreActors:)
		(super init: &rest)
	)
	
	(method (dispose)
		(theGame setCursor: normalCursor 1)
		(theIconBar enable:)
		(super dispose:)
	)
)

(instance pointBox of inputBox
	(properties
		x 303
		y 175
		view 945
		loop 2
	)
)

(instance getItCued of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 60))
			(1
				(switch takeItem
					(cueToadstools (ego get: iMushroom 6))
					(cueTreasure (ego get: iGold 10 get: iSilver 60))
					(cueKey (ego get: iBrassKey))
				)
				(self cue:)
			)
			(2 (self dispose:))
		)
	)
)
