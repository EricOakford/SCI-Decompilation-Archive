;;; Sierra Script 1.0 - (do not remove this comment)
(script# ENCOUNTER)
(include game.sh) (include "210.shm")
(use Main)
(use ThrowKnife)
(use ThrowRock)
(use CastCalm)
(use CastDazzle)
(use Target)
(use Procs)
(use GloryObstacles)
(use Print)
(use PChase)
(use PolyPath)
(use Motion)
(use Game)
(use User)
(use System)

(public
	Encounter 0
	smallMonster 1
)

(local
	searchedMonster
	egoDirection
	egoEscaped
	local3
	monster
	bodyView
	local6
	haveBody
	local8
	local9
	searchX
	searchY
	local12
	gotBeard
	[monsterPts 30]
	monsterDist = [
		500
		40
		40
		40
		40
		50
		30
		35
		40
		50
		30
		40
		]
	monsterHP = [
		10000			;aardvark
		133				;bear
		186				;minotaur
		53				;saurus
		86				;mantray
		113				;cheetaur
		60				;goblin
		140				;troll
		93				;ogre
		186				;saurus rex
		60				;brigand
		100				;brigand leader
		]
	monsterChasesEgo
	monsterHurt
	monsterStopped
)
(procedure (GetMonsterIndex monster &tmp temp0)
	(return
		(if (and (<= vBear monster) (<= monster vBrigandLeader))
			(return (+ 1 (/ (- monster vBear) 5)))
		else
			(return FALSE)
		)
	)
)

(procedure (SetEgoDirection)
	(cond 
		((> egoX 310)
			(= egoDirection EAST)
		)
		((< egoX 10)
			(= egoDirection WEST)
		)
		((< egoY 100)
			(= egoDirection NORTH)
		)
		(else
			(= egoDirection SOUTH)
		)
	)
)

(procedure (MonsterDies afterBattle)
	(Bclr fMantrayDies)
	(= haveBody TRUE)
	(= local8 1)
	(if
		(or
			(not monster)
			(not (cast contains: monster))
		)
		((= monster smallMonster) init:)
	)
	(= bodyView (+ monsterNum 1))
	(SkillUsed LUCK (/ [monsterHP (GetMonsterIndex monsterNum)] 12))
	(if afterBattle
		(monster setLoop: 1)
		(cond 
			((OneOf curRoomNum 19 43 75 92)
				(ego posn: 164 108)
				(if (!= monsterNum vMantray)
					(monster posn: 88 112)
				else
					(monster posn: 150 100)
				)
			)
			((== curRoomNum 79)
				(ego posn: 120 120)
				(monster posn: 220 120)
			)
			((and (OneOf curRoomNum 19 43 86) (== monsterNum vBrigand))
				(ego posn: 140 120)
				(monster posn: 60 120)
			)
			((== monsterNum vMantray)
				(ego posn: 150 120)
				(monster posn: 150 100)
			)
			((== monsterNum vDragon)
				(ego posn: 120 120)
				(monster posn: 293 128)
			)
			((== monsterNum vCheetaur)
				(ego posn: 150 120)
				(monster posn: 235 134)
			)
			(else
				(ego posn: 150 122)
				(monster posn: 220 137)
			)
		)
	)
	(ego
		loop: loopW
		illegalBits: (curRoom illBits?)
		ignoreControl: cWHITE
		ignoreActors:
		init:
	)
	(NormalEgo)
	(ChangeGait MOVE_WALK FALSE)
	(monster setScript: killTheMonster)
)

(procedure (SetMonsterChase obj)
	(obj
		illegalBits: 0
		setMotion: PChase ego [monsterDist (GetMonsterIndex monsterNum)] Encounter
	)
	(= monsterChasesEgo 1)
	(obj setScript: 0)
)

(procedure (SetMonsterStats defaultMonster &tmp tmpMonsterNum retMonster)
	(if (and argc defaultMonster)
		(= retMonster defaultMonster)
	else
		(cond 
			((< [egoStats EXPER] 1000) (= tmpMonsterNum (Random 0 3)))
			((< [egoStats EXPER] 2000) (= tmpMonsterNum (Random 0 6)))
			(else (= tmpMonsterNum (Random 2 6)))
		)
		(if Night
			(+= tmpMonsterNum 2)
		)
		(= retMonster
			(switch tmpMonsterNum
				(0 vGoblin)
				(1 vSaurus)
				(2 vGoblin)
				(3 vBrigand)
				(4 vMantray)
				(5 vCheetaur)
				(6 vDragon)
				(else  vTroll)
			)
		)
	)
	(if (OneOf curRoomNum 85 86 92)
		(if (or (<= tmpMonsterNum 4) (not Night))
			(= retMonster 465)
		else
			(= retMonster 450)
		)
	)
	(= bucks 0)
	(cond 
		((== retMonster vGoblin)
			(= bucks (Random 1 10))
		)
		((== retMonster vBrigand)
			(= bucks (Random 5 25))
		)
		((== retMonster vTroll)
			(= bucks (Random 20 50))
		)
		((== retMonster vMinotaur)
			(= bucks 50)
		)
	)
	(|= disabledActions ACTION_REST)
	(return retMonster)
)

(procedure (localproc_0e23 vMonster actMonster &tmp curRoomAmbushX curRoomAmbushY temp2 temp3 temp4 [temp5 40])
	(= temp2 (& (curRoom entrances?) $000a))
	(= temp4 (& (curRoom entrances?) $0008))
	(= temp3 (& (curRoom entrances?) $0002))
	(actMonster view: vMonster)
	(if (!= vMonster 435)
		(actMonster xStep: 6 yStep: 4 cel: 0)
		(switch vMonster
			(430
				(actMonster xStep: 5 yStep: 3 setCycle: Forward)
			)
			(450
				(actMonster xStep: 5 yStep: 3 setCycle: Walk)
			)
			(440
				(actMonster xStep: 6 yStep: 3 setCycle: Forward)
			)
			(445
				(actMonster xStep: 4 yStep: 2 setCycle: Walk)
			)
			(465
				(actMonster xStep: 3 yStep: 2 setCycle: Walk)
			)
			(460
				(actMonster xStep: 8 yStep: 5 setCycle: Forward)
			)
		)
		(if fastEgo
			(actMonster
				xStep: (* (actMonster xStep?) 2)
				yStep: (* (actMonster yStep?) 2)
			)
		)
	)
	(cond 
		(egoEscaped
			(SetCurIcon 1)
			(if (== vMonster 435)
				(monster
					setCycle: Walk
					z: 25
					xStep: (Random 4 8)
					yStep: (Random 3 5)
				)
			)
			(= monsterChasesEgo 1)
			(switch egoDirection
				(1
					(if
						(or
							(== vMonster 445)
							(== vMonster 450)
							(== vMonster 465)
							(== vMonster 455)
						)
						(ego setMotion: MoveTo egoX (- egoY 2))
						(monster
							posn:
								(+ (ego x?) (Random 20 40))
								(+
									(ego y?)
									[monsterDist (GetMonsterIndex vMonster)]
									(Random 20 30)
								)
							setCel: -1
							setLoop: 3
						)
						(monster setScript: runDelay)
					else
						(ego setMotion: MoveTo 320 egoY)
						(monster
							posn:
								(-
									(ego x?)
									(+ [monsterDist (GetMonsterIndex vMonster)] (Random 25 40))
								)
								(ego y?)
							setLoop: 0
							setCel: -1
						)
						(monster setScript: runDelay)
					)
				)
				(3
					(if
						(or
							(== vMonster 445)
							(== vMonster 450)
							(== vMonster 465)
							(== vMonster 455)
						)
						(ego setMotion: MoveTo egoX (+ egoY 2))
						(switch (curRoom picture?)
							(704
								(monster posn: 209 87 setLoop: 2)
							)
							(705
								(monster posn: 61 78 setLoop: 2)
							)
							(else 
								(monster
									setLoop: 2
									posn:
										(- (ego x?) 10)
										(-
											(ego y?)
											(+ [monsterDist (GetMonsterIndex vMonster)] (Random 20 30))
										)
								)
							)
						)
						(monster setCel: -1)
						(monster setScript: runDelay)
					else
						(ego setMotion: MoveTo 0 egoY)
						(monster
							posn:
								(+
									(ego x?)
									[monsterDist (GetMonsterIndex vMonster)]
									(Random 25 40)
								)
								(ego y?)
							setLoop: 1
							setCel: -1
						)
						(monster setScript: runDelay)
					)
				)
				(2
					(ego setMotion: MoveTo 320 egoY)
					(monster
						posn:
							(-
								(ego x?)
								(+ [monsterDist (GetMonsterIndex vMonster)] (Random 25 40))
							)
							(ego y?)
						setLoop: 0
						setCel: -1
					)
					(monster setScript: runDelay)
				)
				(4
					(ego setMotion: MoveTo 0 egoY)
					(monster
						posn:
							(+
								(ego x?)
								[monsterDist (GetMonsterIndex vMonster)]
								(Random 25 40)
							)
							(ego y?)
						setLoop: 1
						setCel: -1
					)
					(monster setScript: runDelay)
				)
			)
			(User canControl: 1)
		)
		(local3
			(if (== vMonster 435)
				(monster
					setCycle: Walk
					z: 25
					xStep: (Random 4 8)
					yStep: (Random 3 5)
				)
			)
			(= monsterChasesEgo 1)
			(switch egoDirection
				(1
					(= monsterChasesEgo 0)
					(ego setMotion: MoveTo egoX 190)
					(if
						(or
							(== vMonster 445)
							(== vMonster 450)
							(== vMonster 465)
							(== vMonster 455)
						)
						(monster setScript: northDelay)
					else
						(= vMonster (= monsterHealth 0))
						(monster dispose:)
						(= monsterNum 0)
						(= monsterChasesEgo 0)
					)
				)
				(3
					(ego setMotion: MoveTo egoX 0)
					(if
						(or
							(== vMonster 445)
							(== vMonster 450)
							(== vMonster 465)
							(== vMonster 455)
						)
						(monster setScript: southDelay)
					else
						(= monsterNum (= monsterHealth 0))
						(monster dispose:)
						(= monsterNum 0)
						(= monsterChasesEgo 0)
					)
				)
				(2
					(ego setMotion: MoveTo 0 egoY)
					(monster
						posn: (- egoX monsterDistX) (+ egoY monsterDistY)
						loop: 1
						setCel: -1
						setLoop: -1
						setMotion: PChase ego [monsterDist (GetMonsterIndex vMonster)] Encounter
					)
				)
				(4
					(ego setMotion: MoveTo 320 egoY)
					(monster
						posn: (- egoX monsterDistX) (+ egoY monsterDistY)
						loop: 0
						setCel: -1
						setLoop: -1
						setMotion: PChase ego [monsterDist (GetMonsterIndex vMonster)] Encounter
					)
				)
			)
		)
		((== vMonster 435) (monster setScript: (ScriptID 436 2)))
		((== temp2 10)
			(switch (= temp2 (if (< (Random 0 1000) 500) 8 else 2))
				(2 (actMonster setScript: inEast))
				(8 (actMonster setScript: inWest))
			)
		)
		(temp3 (actMonster setScript: inEast))
		(temp4 (actMonster setScript: inWest))
		(else
			(= curRoomAmbushX (curRoom ambushX?))
			(= curRoomAmbushY (curRoom ambushY?))
			(actMonster
				posn: curRoomAmbushX curRoomAmbushY
				setMotion: PChase ego [monsterDist (GetMonsterIndex vMonster)] Encounter
			)
		)
	)
)

(procedure (SearchMonster &tmp [buffer 120] [str 70])
	(switch bucks
		(0
			(messager say: N_MONSTER NULL C_GETNOTHING 0 0 210)
		)
		(1
			(messager say: N_MONSTER NULL C_GET1COIN 0 0 210)
			;now uses the actual message
;;;			(Prints
;;;				{You find a single silver coin, carefully polish it, and place it in your pouch.__What a way to make a living!}
;;;			)
			(ego get: iSilver 1)
		)
		(else
			;now uses the message resource
			(Message MsgGet ENCOUNTER N_MONSTER NULL C_GETCOINS 1 @str)
			(Print
				font: userFont
				mode: teJustCenter
				addTextF: @buffer @str bucks
				init:
			)
			;(Printf @str {You find %d silver coins, and carefully place them in your pouch.} bucks)
			(ego get: iSilver bucks)
		)
	)
	(= bucks 0)
)

(class EncRoom of Room
	(properties
		encChance 0
		entrances (| reNORTH reEAST reSOUTH reWEST)
		ambushX 160
		ambushY 100
		illBits cWHITE
	)
)

(instance smallMonster of TargActor
	(properties
		noun N_LOOKIT
		modNum ENCOUNTER
		view vMantray
		signal (| ignrAct ignrHrz)
		illegalBits $0000
	)
	
	(method (init)
		(if (!= monsterNum vBrigand)
			(= nightPalette (+ monsterNum 1000))
			(PalVary PALVARYTARGET (+ monsterNum 1000))
		)
		(cond 
			((== monsterNum vMantray))
			(
				(or
					(== monsterNum vOgre)
					(== monsterNum vTroll)
					(== monsterNum vBrigand)
					(== monsterNum vGoblin)
				)
				(self looper: aLooper4)
			)
			(else
				(self looper: aLooper2)
			)
		)
		(switch monsterNum
			(vMinotaur
				(self targDeltaY: -35)
			)
			(vSaurus
				(self targDeltaX: 5)
				(self targDeltaY: -32)
			)
			(vMantray
				(self targDeltaX: -40)
			)
			(vCheetaur
				(self targDeltaX: 7)
				(self targDeltaY: -22)
			)
			(vGoblin
				(self targDeltaX: -3)
				(self targDeltaY: -19)
			)
			(vTroll
				(self targDeltaX: -5)
				(self targDeltaY: -32)
			)
			(vOgre
				(self targDeltaY: -30)
			)
			(vDragon
				(self targDeltaX: 7)
				(self targDeltaY: -28)
			)
			(vBrigand
				(self targDeltaY: -27)
			)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_SWORD
					(if haveBody
						(AlreadyDone)
					else
						(curRoom newRoom: monsterNum)
					)
				)
				(V_ROCK
					(if haveBody
						(AlreadyDone)
					else
						(ThrowRock smallMonster)
					)
				)
				(V_DAGGER
					(if haveBody
						(AlreadyDone)
					else
						(ThrowKnife smallMonster)
					)
				)
				(V_LOOK
					(if haveBody
						(messager say: N_LOOKIT V_LOOK C_DEAD 0 0 ENCOUNTER)
					else
						(switch monsterNum
							(vGoblin
								(messager say: N_LOOKIT V_LOOK C_GOBLIN 0 0 ENCOUNTER)
							)
							(vBrigand
								(messager say: N_LOOKIT V_LOOK C_BRIGAND 0 0 ENCOUNTER)
							)
							(vSaurus
								(messager say: N_LOOKIT V_LOOK C_SAURUS 0 0 ENCOUNTER)
							)
							(vDragon
								(messager say: N_LOOKIT V_LOOK C_SAURUSREX 0 0 ENCOUNTER)
							)
							(vCheetaur
								;(messager say: N_LOOKIT V_LOOK C_SAURUSREX 0 0 ENCOUNTER)
								(messager say: N_LOOKIT V_LOOK C_CHEETAUR 0 0 ENCOUNTER)	;bug fix
							)
							(vOgre
								(messager say: N_LOOKIT V_LOOK C_OGRE 0 0 ENCOUNTER)
							)
							(vTroll
								(messager say: N_LOOKIT V_LOOK C_TROLL 0 0 ENCOUNTER)
							)
							(vMantray
								(messager say: N_LOOKIT V_LOOK C_MANTRAY 0 0 ENCOUNTER)
							)
							(else 
								(messager say: N_LOOKIT V_LOOK C_MANTRAY 0 0 ENCOUNTER)
							)
						)
					)
				)
				(V_SMELL
					(messager say: N_ROOM V_SMELL NULL 0 0 ENCOUNTER)
				)
				(V_DO
					(if haveBody
						(if searchedMonster
							(AlreadyDone)
						else
							(ego setScript: searchIt 0 monster)
						)
					else
						(curRoom newRoom: monsterNum)
					)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
	
	(method (cue)
		(if gotBeard
			(= gotBeard FALSE)
			(SearchMonster)
		)
	)
	
	(method (getHurt damage)
		(= monsterHurt TRUE)
		(= monsterStopped 0)
		(-= monsterHealth damage)
	)
)

(instance Encounter of Region
	(properties
		modNum ENCOUNTER
		noun N_ROOM
	)
	
	(method (init &tmp curRoomEncChance temp1)
		(= local12 1)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(HandsOn)
		(ScriptID PCHASE)
		(Load SCRIPT GLORY_OBSTACLES)
		(super init: &rest)
		(= local3 (= egoEscaped (= monsterHurt 0)))
		(= monster 0)
		(cond 
			(
				(not
					(OneOf prevRoomNum
						vBear vMinotaur vSaurus vMantray vCheetaur
						vGoblin vOgre vTroll vDragon vBrigand vBrigandLeader
					)
				)
				(if monsterNum
					(if (and (== monsterNum vTroll) (not Night))
						(ego illegalBits: (curRoom illBits?) init:)
						(= monsterNum (= monsterHealth 0))
						(Animate (cast elements?) FALSE)
						(messager say: N_ROOM NULL C_TROLLFLEES 1 0 ENCOUNTER)
					else
						(= local3 1)
						(ego illegalBits: (curRoom illBits?) init:)
						(= local6 (= haveBody 0))
						(SetMonsterStats monsterNum)
					)
				else
					(= curRoomEncChance (curRoom encChance?))
					(if Night
						(= curRoomEncChance (* curRoomEncChance 2))
					)
					(if (Btst fBeenIn97)
						(= curRoomEncChance (* curRoomEncChance 2))
					)
					(if (== prevRoomNum 45)
						(= curRoomEncChance 0)
					)
					(cond 
						((> (Rand100) curRoomEncChance))
						(
							(and
								(== egoGait MOVE_SNEAK)
								(TrySkill STEALTH curRoomEncChance)
								(TrySkill LUCK 0 0)
							)
							(Animate (cast elements?) FALSE)
							(messager say: N_ROOM NULL C_SNEAKSUCCESS 2 0 ENCOUNTER)
						)
						(else
							(= monsterNum (SetMonsterStats 0))
							(= monsterHealth
								[monsterHP (GetMonsterIndex monsterNum)]
							)
							(= local6 (= haveBody 0))
						)
					)
					(ego illegalBits: (curRoom illBits?))
				)
			)
			((<= monsterHealth 0)
				(MonsterDies 1)
			)
			(else
				(= local6 (= haveBody 0))
				(ChangeGait MOVE_RUN FALSE)
				(= egoEscaped 1)
				(NormalEgo)
				(ego illegalBits: (curRoom illBits?) posn: 160 140 init:)
				(SetMonsterStats monsterNum)
			)
		)
		(SetEgoDirection)
	)
	
	(method (doit)
		(cond 
			(
				(and
					(not local6)
					(not haveBody)
					monsterNum
					(or local3 (ego inRect: 40 40 260 160))
				)
				(= local6 1)
				((= monster smallMonster) posn: 0 1000 init:)
				(localproc_0e23 monsterNum monster)
			)
			((and (== monsterNum vMantray) (Btst fMantrayDies))
				(MonsterDies 0)
			)
			(
				(and
					local6
					monsterNum
					(not local8)
					(<= monsterHealth 0)
					(not (Btst fMantrayDies))
				)
				(MonsterDies 0)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(= disabledActions 0)
		(DisposeScript PCHASE)
		(DisposeScript GLORY_OBSTACLES)
		(DisposeScript 436)
		(super dispose: &rest)
		(DisposeScript ENCOUNTER)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_CALM
					(if (or monsterChasesEgo (== monsterNum vMantray))
						(monster setScript: spellDelay 0 22)
						(return TRUE)
					else
						(messager say: N_ROOM V_MAGIC NULL 1 0 ENCOUNTER)
						(return TRUE)
					)
				)
				(V_OPEN
					(messager say: N_ROOM V_MAGIC NULL 2 0 ENCOUNTER)
					(return TRUE)
				)
				(V_DETECT
					(messager say: N_ROOM V_MAGIC NULL 3 0 ENCOUNTER)
					(return TRUE)
				)
				(V_DAZZLE
					(if (or monsterChasesEgo (== monsterNum vMantray))
						(monster setScript: spellDelay 0 20)
					else
						(messager say: N_ROOM V_MAGIC NULL 4 0 ENCOUNTER)
					)
					(return TRUE)
				)
				(V_ZAP
					(= zapPower (+ 5 (/ [egoStats ZAP] 10)))
					(if (or (ego has: iDagger) (ego has: iSword))
						(messager say: N_ROOM V_MAGIC NULL 5 0 ENCOUNTER)
					else
						(messager say: N_ROOM V_MAGIC NULL 6 0 ENCOUNTER)
					)
					(return TRUE)
				)
				(V_FETCH
					(if local6
						(messager say: N_ROOM V_MAGIC NULL 7 0 ENCOUNTER)
					else
						(messager say: N_ROOM V_MAGIC NULL 8 0 ENCOUNTER)
					)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
	
	(method (cue)
		(if (and local12 monsterNum (> monsterHealth 0))
			(= local12 0)
			(ChangeGait MOVE_WALK FALSE)
			(if (and local12 (or egoEscaped local3))
				(self setScript: checkProject 0 0)
			else
				(self setScript: checkProject 0 1)
			)
		)
	)
	
	(method (newRoom n)
		(HandsOff)
		(if local6
			(= monsterDistX (- (ego x?) (monster x?)))
			(= monsterDistY (- (ego y?) (monster y?)))
		)
		(if
			(or
				haveBody
				(> monsterDistX 250)
				(> monsterDistY 180)
				(and
					(not
						(OneOf n
							vBear vMinotaur vSaurus vMantray vCheetaur
							vGoblin vOgre vTroll vDragon vBrigand vBrigandLeader
						)
					)
					(not
						(OneOf n
							11 12 17 18 19 23 24 25 26 27 33 34 35 36 42
							43 44 51 52 56 57 61 62 63 69 71 72 74 75 79
							80 81 85 86 92
						)
					)
				)
			)
			(= monsterNum (= monsterHealth 0))
			(= brigandHead 0)
		)
		(Bclr fMantrayDies)
		(super newRoom: n &rest)
	)
)

(instance checkProject of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and monsterNum (> monsterHealth 0))
					(HandsOff)
					(if (not register)
						(ego setMotion: 0)
						(smallMonster setMotion: 0)
						(messager say: N_ROOM NULL C_NOESCAPE 3 self ENCOUNTER)
					else
						(ego setMotion: 0)
						(smallMonster setMotion: 0)
						(messager say: N_ROOM NULL C_GETREADY 4 self ENCOUNTER)
					)
				else
					(= ticks 1)
				)
			)
			(1
				(HandsOff)
				(= ticks 20)
			)
			(2
				(cond 
					((IsObject projObj)
						(-- state)
						(projObj dispose:)
						(= cycles 2)
					)
					((and monsterNum (> monsterHealth 0))
						(if (IsObject narrator)
							(narrator dispose:)
						)
						(if (IsObject fastCast)
							(fastCast dispose:)
						)
						(curRoom newRoom: monsterNum)
					)
					(else
						(HandsOn)
						(self dispose:)
					)
				)
			)
		)
	)
)

(instance inWest of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setLoop: 0
					posn: -100 106
					setMotion: MoveTo 0 106 self
				)
			)
			(1
				(SetMonsterChase client)
			)
		)
	)
)

(instance inEast of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setLoop: 1
					posn: 400 106
					setMotion: MoveTo 310 106 self
				)
			)
			(1
				(SetMonsterChase client)
			)
		)
	)
)

(instance northDelay of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 90))
			(1
				(monster
					posn:
						egoX
						(cond 
							((> (curRoom horizon?) (- egoY monsterDistY)) (- (curRoom horizon?) 20))
							((== monsterNum vMantray) -10)
							(else (- egoY monsterDistY))
						)
					setCel: -1
					setLoop: -1
					setMotion: PChase ego [monsterDist (GetMonsterIndex monsterNum)] Encounter
				)
				(= monsterChasesEgo 1)
				(client setScript: 0)
			)
		)
	)
)

(instance southDelay of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 90))
			(1
				(monster
					posn: egoX (if (== monsterNum 435) 235 else (- egoY monsterDistY))
					setCel: -1
					setLoop: -1
					setMotion: PChase ego [monsterDist (GetMonsterIndex monsterNum)] Encounter
				)
				(= monsterChasesEgo 1)
				(client setScript: 0)
			)
		)
	)
)

(instance runDelay of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: 0)
				(= ticks 90)
			)
			(1
				(client
					setCycle: Forward
					setMotion: PChase ego [monsterDist (GetMonsterIndex monsterNum)] Encounter
				)
				(client setScript: 0)
			)
		)
	)
)

(instance spellDelay of Script
	(properties)
	
	(method (doit)
		(if (and seconds monsterHurt (not monsterStopped))
			(= seconds 0)
			(= cycles 1)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= monsterChasesEgo 0)
				(monster setCycle: 0 setMotion: 0 ignoreActors: 0)
				(if (== register CALM)
					(if (not (CastCalm self self))
						(self changeState: 4)
					)
				else
					(self changeState: 2)
				)
			)
			(1
				(= state 3)
				(cond 
					((== monsterNum vMantray)
						;these now use the actual messages
						(messager say: N_MONSTER NULL C_MANTRAYIMMUNE 1 self ENCOUNTER)
						;(Prints {Unfortunately, the Mantray appears not to have been affected by your spell.})
						;(= ticks 1)
					)
					(monsterHurt
						(messager say: N_MONSTER NULL C_CANTCALM 1 self ENCOUNTER)
						;(Prints {The monster doesn't seem very calm.__Maybe it didn't like you hurting it.})
						;(= ticks 1)
					)
					(else
						(= seconds (+ 5 (/ [egoStats CALM] 10)))
					)
				)
			)
			(2
				(= monsterStopped TRUE)
				(if (not (CastDazz self self))
					(self changeState: 4)
				)
			)
			(3
				(if (== monsterNum vMantray)
					;this now uses the actual message
					(messager say: N_MONSTER NULL C_MANTRAYIMMUNE 1 self ENCOUNTER)
					;(Prints {Unfortunately, the Mantray appears not to have been affected by your spell.})
					;(= ticks 1)
				else
					;this used CALM by mistake
					(= seconds (+ 3 (/ [egoStats DAZZLE] 10)))
				)
			)
			(4
				(if (not local8)
					(if
						(or
							(== monsterNum vGoblin)
							(== monsterNum vTroll)
							(== monsterNum vBrigand)
							(== monsterNum vOgre)
							(and
								(< -15 (- (ego x?) (monster x?)))
								(< (- (ego x?) (monster x?)) 15)
							)
						)
						(monster setLoop: -1 setCycle: Walk)
					else
						(monster setCycle: Forward)
					)
					(monster
						ignoreActors:
						setMotion: PChase ego [monsterDist (GetMonsterIndex monsterNum)] Encounter
					)
					(= monsterChasesEgo TRUE)
				)
				(= monsterStopped FALSE)
				(self dispose:)
			)
		)
	)
)

(instance searchIt of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				((= monster register) ignoreActors:)
				(= searchX
					(switch monsterNum
						(vSaurus -1)
						(vCheetaur 1)
						(vDragon 71)
						(vGoblin -26)
						(vMantray 33)
						(vBrigand 19)
						(vMinotaur 38)
						(vOgre -34)
						(vTroll 11)
						(else  13)
					)
				)
				(= searchY
					(switch monsterNum
						(vSaurus -7)
						(vCheetaur -17)
						(vDragon -20)
						(vGoblin -15)
						(vMantray -7)
						(vBrigand -10)
						(vMinotaur -16)
						(vOgre -17)
						(vTroll -17)
						(else  -14)
					)
				)
				(if (and (== monsterNum vDragon) (monster loop?))
					(= searchX -58)
				)
				(HandsOff)
				(ego
					ignoreActors:
					setMotion:
						PolyPath
						(+ (monster x?) searchX)
						(+ (monster y?) searchY)
						self
				)
			)
			(1
				(ego
					view: 510
					setLoop:
						(cond 
							((== monsterNum vGoblin) 0)
							((== monsterNum vBrigand) 1)
							(else (mod (ego loop?) 2))
						)
					setCycle: EndLoop self
				)
			)
			(2 (ego setCycle: BegLoop self))
			(3
				(messager say: N_MONSTER NULL NULL 1 self ENCOUNTER)
			)
			(4
				(cond 
					((== monsterNum vTroll)
						(if (Btst fBeardKnown)
							(ego get: iTrollBeard)
							(= gotBeard TRUE)
							(messager say: N_MONSTER NULL C_GETBEARD 2 smallMonster ENCOUNTER)
						else
							(SearchMonster)
						)
					)
					((== monsterNum vCheetaur)
						(if (Btst fClawsKnown)
							(ego get: iCheetaurClaw (Random 4 10))
							(messager say: N_MONSTER 60 C_GETCLAWS2 1 0 ENCOUNTER)
						else
							(SearchMonster)
						)
					)
					(else
						(SearchMonster)
					)
				)
				(ego
					view: 0
					setLoop: -1
					setCycle: Walk
					setMotion:
						PolyPath
						185
						(if (< (ego x?) (monster x?)) 140 else 160)
						self
				)
			)
			(5
				(if (and (== monsterNum vTroll) (not (Btst fBeardKnown)))
					(messager say: N_MONSTER 60 C_TROLL 1 0 ENCOUNTER)
				)
				(= searchedMonster TRUE)
				(NormalEgo 2 4)
				(ego illegalBits: (curRoom illBits?))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance killTheMonster of Script
	
	(method (changeState newState &tmp theX theY)
		(switch (= state newState)
			(0
				(HandsOff)
				(= disabledActions 0)
				(if (!= monsterNum vMantray)
					(monster
						view: bodyView
						setLoop:
							(if (> (monster loop?) 1)
								0
							else
								(monster loop?)
							)
						cel: 0
						ignoreActors:
						setMotion: 0
						cycleSpeed: 8
					)
					(if (== monster vTroll)
						(monster setLoop: 0)
					)
					(if (!= monsterNum vGoblin)
						(monster setCycle: EndLoop self)
					else
						(= ticks 1)
					)
				else
					(self setScript: (ScriptID 436 3) self monster)
				)
			)
			(1
				(= theX (monster x?))
				(= theY (monster y?))
				(monster stopUpd:)
				(ego ignoreActors:)
				(HandsOn)
				(switch monsterNum
					(vGoblin
						(if (not (monster loop?))
							(= [monsterPts 0] (+ theX 31))
							(= [monsterPts 1] (- theY 17))
							(= [monsterPts 2] (+ theX 11))
							(= [monsterPts 3] (+ theY 4))
							(= [monsterPts 4] (- theX 20))
							(= [monsterPts 5] (+ theY 5))
							(= [monsterPts 6] (- theX 26))
							(= [monsterPts 7] (- theY 15))
							(= [monsterPts 8] theX)
							(= [monsterPts 9] (- theY 30))
							(= [monsterPts 10] 30583)
							(= [monsterPts 11] 0)
							(AddMovingObstacle @monsterPts 2)
						else
							(= [monsterPts 0] (+ theX 4))
							(= [monsterPts 1] (- theY 20))
							(= [monsterPts 2] (+ theX 31))
							(= [monsterPts 3] (- theY 14))
							(= [monsterPts 4] (+ theX 27))
							(= [monsterPts 5] (- theY 5))
							(= [monsterPts 6] (- theX 17))
							(= [monsterPts 7] (+ theY 5))
							(= [monsterPts 8] (- theX 27))
							(= [monsterPts 9] (- theY 16))
							(= [monsterPts 10] (- theX 13))
							(= [monsterPts 11] (- theY 28))
							(= [monsterPts 12] 30583)
							(= [monsterPts 13] 0)
							(AddMovingObstacle @monsterPts 2)
						)
					)
					(vMantray
						(if (== (monster loop?) 6)
							(= [monsterPts 0] (- theX 8))
							(= [monsterPts 1] (- theY 8))
							(= [monsterPts 2] (- theX 16))
							(= [monsterPts 3] (+ theY 12))
							(= [monsterPts 4] (- theX 26))
							(= [monsterPts 5] (+ theY 25))
							(= [monsterPts 6] (- theX 48))
							(= [monsterPts 7] (+ theY 20))
							(= [monsterPts 8] (- theX 56))
							(= [monsterPts 9] (- theY 5))
							(= [monsterPts 10] 30583)
							(= [monsterPts 11] 0)
							(AddMovingObstacle @monsterPts 2)
						else
							(= [monsterPts 0] (+ theX 8))
							(= [monsterPts 1] (- theY 8))
							(= [monsterPts 2] (+ theX 66))
							(= [monsterPts 3] (- theY 5))
							(= [monsterPts 4] (+ theX 49))
							(= [monsterPts 5] (+ theY 19))
							(= [monsterPts 6] (+ theX 27))
							(= [monsterPts 7] (+ theY 25))
							(= [monsterPts 8] (+ theX 17))
							(= [monsterPts 9] (+ theY 13))
							(= [monsterPts 10] 30583)
							(= [monsterPts 11] 0)
							(AddMovingObstacle @monsterPts 2)
						)
					)
					(vSaurus
						(if (not (monster loop?))
							(= [monsterPts 0] (- theX 28))
							(= [monsterPts 1] (- theY 11))
							(= [monsterPts 2] (+ theX 24))
							(= [monsterPts 3] (- theY 11))
							(= [monsterPts 4] (+ theX 38))
							(= [monsterPts 5] (- theY 4))
							(= [monsterPts 6] (+ theX 33))
							(= [monsterPts 7] (+ theY 8))
							(= [monsterPts 8] (+ theX 4))
							(= [monsterPts 9] (+ theY 8))
							(= [monsterPts 10] (+ theX 4))
							(= [monsterPts 11] (+ theY 5))
							(= [monsterPts 12] (- theX 15))
							(= [monsterPts 13] (+ theY 5))
							(= [monsterPts 14] (- theX 15))
							(= [monsterPts 15] (+ theY 11))
							(= [monsterPts 16] (- theX 34))
							(= [monsterPts 17] (+ theY 11))
							(= [monsterPts 18] (- theX 34))
							(= [monsterPts 19] (+ theY 4))
							(= [monsterPts 20] (- theX 58))
							(= [monsterPts 21] (+ theY 6))
							(= [monsterPts 22] (- theX 60))
							(= [monsterPts 23] theY)
							(= [monsterPts 24] 30583)
							(= [monsterPts 25] 0)
							(AddMovingObstacle @monsterPts 2)
						else
							(= [monsterPts 0] (- theX 25))
							(= [monsterPts 1] (- theY 11))
							(= [monsterPts 2] (+ theX 30))
							(= [monsterPts 3] (- theY 11))
							(= [monsterPts 4] (+ theX 61))
							(= [monsterPts 5] (+ theY 1))
							(= [monsterPts 6] (+ theX 59))
							(= [monsterPts 7] (+ theY 5))
							(= [monsterPts 8] (+ theX 35))
							(= [monsterPts 9] (+ theY 4))
							(= [monsterPts 10] (+ theX 34))
							(= [monsterPts 11] (+ theY 10))
							(= [monsterPts 12] (+ theX 16))
							(= [monsterPts 13] (+ theY 10))
							(= [monsterPts 14] (+ theX 15))
							(= [monsterPts 15] (+ theY 5))
							(= [monsterPts 16] (- theX 4))
							(= [monsterPts 17] (+ theY 5))
							(= [monsterPts 18] (- theX 3))
							(= [monsterPts 19] (+ theY 8))
							(= [monsterPts 20] (- theX 32))
							(= [monsterPts 21] (+ theY 8))
							(= [monsterPts 22] (- theX 37))
							(= [monsterPts 23] (- theY 4))
							(= [monsterPts 24] 30583)
							(= [monsterPts 25] 0)
							(AddMovingObstacle @monsterPts 2)
						)
					)
					(vCheetaur
						(if (not (monster loop?))
							(= [monsterPts 0] (+ theX 32))
							(= [monsterPts 1] (- theY 17))
							(= [monsterPts 2] (+ theX 48))
							(= [monsterPts 3] (- theY 12))
							(= [monsterPts 4] (+ theX 28))
							(= [monsterPts 5] (+ theY 4))
							(= [monsterPts 6] (- theX 6))
							(= [monsterPts 7] (+ theY 4))
							(= [monsterPts 8] (- theX 12))
							(= [monsterPts 9] (+ theY 1))
							(= [monsterPts 10] (- theX 31))
							(= [monsterPts 11] (+ theY 1))
							(= [monsterPts 12] (- theX 31))
							(= [monsterPts 13] (- theY 3))
							(= [monsterPts 14] (- theX 21))
							(= [monsterPts 15] (- theY 15))
							(= [monsterPts 16] 30583)
							(= [monsterPts 17] 0)
							(AddMovingObstacle @monsterPts 2)
						else
							(= [monsterPts 0] (- theX 24))
							(= [monsterPts 1] (- theY 19))
							(= [monsterPts 2] (+ theX 20))
							(= [monsterPts 3] (- theY 15))
							(= [monsterPts 4] (+ theX 31))
							(= [monsterPts 5] (+ theY 1))
							(= [monsterPts 6] (+ theX 11))
							(= [monsterPts 7] (+ theY 1))
							(= [monsterPts 8] (+ theX 7))
							(= [monsterPts 9] (+ theY 4))
							(= [monsterPts 10] (- theX 28))
							(= [monsterPts 11] (+ theY 3))
							(= [monsterPts 12] (- theX 47))
							(= [monsterPts 13] (- theY 12))
							(= [monsterPts 14] 30583)
							(= [monsterPts 15] 0)
							(AddMovingObstacle @monsterPts 2)
						)
					)
					(vBrigand
						(if (not (monster loop?))
							(= [monsterPts 0] (- theX 22))
							(= [monsterPts 1] (- theY 18))
							(= [monsterPts 2] (- theX 7))
							(= [monsterPts 3] (- theY 23))
							(= [monsterPts 4] (+ theX 15))
							(= [monsterPts 5] (- theY 10))
							(= [monsterPts 6] (+ theX 43))
							(= [monsterPts 7] (- theY 16))
							(= [monsterPts 8] (+ theX 54))
							(= [monsterPts 9] (- theY 9))
							(= [monsterPts 10] (+ theX 57))
							(= [monsterPts 11] (- theY 2))
							(= [monsterPts 12] (+ theX 33))
							(= [monsterPts 13] (+ theY 5))
							(= [monsterPts 14] (- theX 1))
							(= [monsterPts 15] (+ theY 9))
							(= [monsterPts 16] (- theX 26))
							(= [monsterPts 17] (- theY 9))
							(= [monsterPts 18] 30583)
							(= [monsterPts 19] 0)
							(AddMovingObstacle @monsterPts 2)
						else
							(= [monsterPts 0] (- theX 42))
							(= [monsterPts 1] (- theY 16))
							(= [monsterPts 2] (- theX 15))
							(= [monsterPts 3] (- theY 10))
							(= [monsterPts 4] (+ theX 8))
							(= [monsterPts 5] (- theY 22))
							(= [monsterPts 6] (+ theX 22))
							(= [monsterPts 7] (- theY 18))
							(= [monsterPts 8] (+ theX 27))
							(= [monsterPts 9] (- theY 9))
							(= [monsterPts 10] (+ theX 3))
							(= [monsterPts 11] (+ theY 8))
							(= [monsterPts 12] (- theX 56))
							(= [monsterPts 13] (- theY 2))
							(= [monsterPts 14] 30583)
							(= [monsterPts 15] 0)
							(AddMovingObstacle @monsterPts 2)
						)
					)
					(vDragon
						(if (not (monster loop?))
							(= [monsterPts 0] (- theX 7))
							(= [monsterPts 1] (- theY 13))
							(= [monsterPts 2] (+ theX 25))
							(= [monsterPts 3] (- theY 21))
							(= [monsterPts 4] (+ theX 124))
							(= [monsterPts 5] (- theY 21))
							(= [monsterPts 6] (+ theX 142))
							(= [monsterPts 7] (- theY 2))
							(= [monsterPts 8] (+ theX 132))
							(= [monsterPts 9] (+ theY 8))
							(= [monsterPts 10] (+ theX 35))
							(= [monsterPts 11] (+ theY 7))
							(= [monsterPts 12] (+ theX 30))
							(= [monsterPts 13] (+ theY 1))
							(= [monsterPts 14] (- theX 7))
							(= [monsterPts 15] (+ theY 1))
							(= [monsterPts 16] 30583)
							(= [monsterPts 17] 0)
							(AddMovingObstacle @monsterPts 2)
						else
							(= [monsterPts 0] (- theX 123))
							(= [monsterPts 1] (- theY 20))
							(= [monsterPts 2] (- theX 24))
							(= [monsterPts 3] (- theY 20))
							(= [monsterPts 4] (+ theX 8))
							(= [monsterPts 5] (- theY 14))
							(= [monsterPts 6] (+ theX 8))
							(= [monsterPts 7] (+ theY 1))
							(= [monsterPts 8] (- theX 30))
							(= [monsterPts 9] (+ theY 1))
							(= [monsterPts 10] (- theX 35))
							(= [monsterPts 11] (+ theY 7))
							(= [monsterPts 12] (- theX 131))
							(= [monsterPts 13] (+ theY 7))
							(= [monsterPts 14] (- theX 141))
							(= [monsterPts 15] (- theY 2))
							(= [monsterPts 16] 30583)
							(= [monsterPts 17] 0)
							(AddMovingObstacle @monsterPts 2)
						)
					)
					(vTroll
						(= [monsterPts 0] (- theX 8))
						(= [monsterPts 1] (- theY 30))
						(= [monsterPts 2] (+ theX 25))
						(= [monsterPts 3] (- theY 13))
						(= [monsterPts 4] (+ theX 26))
						(= [monsterPts 5] (- theY 3))
						(= [monsterPts 6] (+ theX 1))
						(= [monsterPts 7] (+ theY 6))
						(= [monsterPts 8] (- theX 16))
						(= [monsterPts 9] (- theY 9))
						(= [monsterPts 10] (- theX 22))
						(= [monsterPts 11] (- theY 7))
						(= [monsterPts 12] (- theX 32))
						(= [monsterPts 13] (- theY 14))
						(= [monsterPts 14] (- theX 32))
						(= [monsterPts 15] (- theY 26))
						(= [monsterPts 16] 30583)
						(= [monsterPts 17] 0)
						(AddMovingObstacle @monsterPts 2)
					)
				)
			)
		)
	)
)

(instance aLooper4 of Code
	
	(method (doit param1 param2)
		(param1
			setLoop:
				(cond 
					((and (<= 30 param2) (<= param2 150)) 0)
					((and (<= 151 param2) (<= param2 210)) 2)
					((and (<= 211 param2) (<= param2 330)) 1)
					(else 3)
				)
		)
	)
)

(instance aLooper2 of Code
	(method (doit param1 param2)
		(param1
			setLoop: (if (and (<= 0 param2) (<= param2 180)) 0 else 1)
		)
	)
)
