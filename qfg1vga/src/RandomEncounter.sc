;;; Sierra Script 1.0 - (do not remove this comment)
(script# ENCOUNTER)
(include game.sh) (include "210.shm")
(use Main)
(use CastDagger)
(use CastRock)
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
	local2
	local3
	theSmallMonster
	viewDeadMonster
	local6
	monsterDead
	local8
	local9
	SearchMonsterX
	SearchMonsterY
	local12
	gotBeard
	[monsterPts 30]
	[local44 12] = [500 40 40 40 60 50 30 35 40 50 30 40]
	[monsterHP 12] = [10000 133 186 53 86 113 60 140 93 186 60 100]
	local68
	monsterHurt
	local70
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

(procedure (localproc_0117 param1)
	(Bclr fFlag351)
	(= monsterDead TRUE)
	(= local8 1)
	(if
		(or
			(not theSmallMonster)
			(not (cast contains: theSmallMonster))
		)
		((= theSmallMonster smallMonster) init:)
	)
	(= viewDeadMonster (+ monsterNum 1))
	(SkillUsed LUCK (/ [monsterHP (GetMonsterIndex monsterNum)] 12))
	(if param1
		(theSmallMonster setLoop: 1)
		(cond 
			((OneOf curRoomNum 19 43 75 92)
				(ego posn: 164 108)
				(if (!= monsterNum vMantray)
					(theSmallMonster posn: 88 112)
				else
					(theSmallMonster posn: 150 100)
				)
			)
			((== curRoomNum 79)
				(ego posn: 120 120)
				(theSmallMonster posn: 220 120)
			)
			((and (OneOf curRoomNum 19 43 86) (== monsterNum vBrigand))
				(ego posn: 140 120)
				(theSmallMonster posn: 60 120)
			)
			((== monsterNum vMantray)
				(ego posn: 150 120)
				(theSmallMonster posn: 150 100)
			)
			((== monsterNum vDragon)
				(ego posn: 120 120)
				(theSmallMonster posn: 293 128)
			)
			((== monsterNum vCheetaur)
				(ego posn: 150 120)
				(theSmallMonster posn: 235 134)
			)
			(else
				(ego posn: 150 122)
				(theSmallMonster posn: 220 137)
			)
		)
	)
	(ego
		loop: 1
		illegalBits: (curRoom illBits?)
		ignoreControl: cWHITE
		ignoreActors:
		init:
	)
	(NormalEgo)
	(ChangeGait MOVE_WALK FALSE)
	(theSmallMonster setScript: killTheMonster)
)

(procedure (SetMonsterChase param1)
	(param1
		illegalBits: 0
		setMotion: PChase ego [local44 (GetMonsterIndex monsterNum)] Encounter
	)
	(= local68 1)
	(param1 setScript: 0)
)

(procedure (SetMonsterStats defaultMonster &tmp temp0 retMonster)
	(if (and argc defaultMonster)
		(= retMonster defaultMonster)
	else
		(cond 
			((< [egoStats EXPER] 1000) (= temp0 (Random 0 3)))
			((< [egoStats EXPER] 2000) (= temp0 (Random 0 6)))
			(else (= temp0 (Random 2 6)))
		)
		(if Night (= temp0 (+ temp0 2)))
		(= retMonster
			(switch temp0
				(0 445)
				(1 430)
				(2 445)
				(3 465)
				(4 435)
				(5 440)
				(6 460)
				(else  450)
			)
		)
	)
	(if (OneOf curRoomNum 85 86 92)
		(if (or (<= temp0 4) (not Night))
			(= retMonster 465)
		else
			(= retMonster 450)
		)
	)
	(= bucks 0)
	(cond 
		((== retMonster 445) (= bucks (Random 1 10)))
		((== retMonster 465) (= bucks (Random 5 25)))
		((== retMonster 450) (= bucks (Random 20 50)))
		((== retMonster 425) (= bucks 50))
	)
	(= disabledActions (| disabledActions $0008))
	(return retMonster)
)

(procedure (localproc_0e23 param1 param2 &tmp curRoomAmbushX curRoomAmbushY temp2 temp3 temp4 [temp5 40])
	(= temp2 (& (curRoom entrances?) $000a))
	(= temp4 (& (curRoom entrances?) $0008))
	(= temp3 (& (curRoom entrances?) $0002))
	(param2 view: param1)
	(if (!= param1 435)
		(param2 xStep: 6 yStep: 4 cel: 0)
		(switch param1
			(430
				(param2 xStep: 5 yStep: 3 setCycle: Forward)
			)
			(450
				(param2 xStep: 5 yStep: 3 setCycle: Walk)
			)
			(440
				(param2 xStep: 6 yStep: 3 setCycle: Forward)
			)
			(445
				(param2 xStep: 4 yStep: 2 setCycle: Walk)
			)
			(465
				(param2 xStep: 3 yStep: 2 setCycle: Walk)
			)
			(460
				(param2 xStep: 8 yStep: 5 setCycle: Forward)
			)
		)
		(if fastEgo
			(param2
				xStep: (* (param2 xStep?) 2)
				yStep: (* (param2 yStep?) 2)
			)
		)
	)
	(cond 
		(local2
			(ChangeTheCursor 1)
			(if (== param1 435)
				(theSmallMonster
					setCycle: Walk
					z: 25
					xStep: (Random 4 8)
					yStep: (Random 3 5)
				)
			)
			(= local68 1)
			(switch egoDirection
				(1
					(if
						(or
							(== param1 445)
							(== param1 450)
							(== param1 465)
							(== param1 455)
						)
						(ego setMotion: MoveTo egoX (- egoY 2))
						(theSmallMonster
							posn:
								(+ (ego x?) (Random 20 40))
								(+
									(ego y?)
									[local44 (GetMonsterIndex param1)]
									(Random 20 30)
								)
							setCel: -1
							setLoop: 3
						)
						(theSmallMonster setScript: runDelay)
					else
						(ego setMotion: MoveTo 320 egoY)
						(theSmallMonster
							posn:
								(-
									(ego x?)
									(+ [local44 (GetMonsterIndex param1)] (Random 25 40))
								)
								(ego y?)
							setLoop: 0
							setCel: -1
						)
						(theSmallMonster setScript: runDelay)
					)
				)
				(3
					(if
						(or
							(== param1 445)
							(== param1 450)
							(== param1 465)
							(== param1 455)
						)
						(ego setMotion: MoveTo egoX (+ egoY 2))
						(switch (curRoom picture?)
							(704
								(theSmallMonster posn: 209 87 setLoop: 2)
							)
							(705
								(theSmallMonster posn: 61 78 setLoop: 2)
							)
							(else 
								(theSmallMonster
									setLoop: 2
									posn:
										(- (ego x?) 10)
										(-
											(ego y?)
											(+ [local44 (GetMonsterIndex param1)] (Random 20 30))
										)
								)
							)
						)
						(theSmallMonster setCel: -1)
						(theSmallMonster setScript: runDelay)
					else
						(ego setMotion: MoveTo 0 egoY)
						(theSmallMonster
							posn:
								(+
									(ego x?)
									[local44 (GetMonsterIndex param1)]
									(Random 25 40)
								)
								(ego y?)
							setLoop: 1
							setCel: -1
						)
						(theSmallMonster setScript: runDelay)
					)
				)
				(2
					(ego setMotion: MoveTo 320 egoY)
					(theSmallMonster
						posn:
							(-
								(ego x?)
								(+ [local44 (GetMonsterIndex param1)] (Random 25 40))
							)
							(ego y?)
						setLoop: 0
						setCel: -1
					)
					(theSmallMonster setScript: runDelay)
				)
				(4
					(ego setMotion: MoveTo 0 egoY)
					(theSmallMonster
						posn:
							(+
								(ego x?)
								[local44 (GetMonsterIndex param1)]
								(Random 25 40)
							)
							(ego y?)
						setLoop: 1
						setCel: -1
					)
					(theSmallMonster setScript: runDelay)
				)
			)
			(User canControl: 1)
		)
		(local3
			(if (== param1 435)
				(theSmallMonster
					setCycle: Walk
					z: 25
					xStep: (Random 4 8)
					yStep: (Random 3 5)
				)
			)
			(= local68 1)
			(switch egoDirection
				(1
					(= local68 0)
					(ego setMotion: MoveTo egoX 190)
					(if
						(or
							(== param1 445)
							(== param1 450)
							(== param1 465)
							(== param1 455)
						)
						(theSmallMonster setScript: northDelay)
					else
						(= param1 (= monsterHealth 0))
						(theSmallMonster dispose:)
						(= monsterNum 0)
						(= local68 0)
					)
				)
				(3
					(ego setMotion: MoveTo egoX 0)
					(if
						(or
							(== param1 445)
							(== param1 450)
							(== param1 465)
							(== param1 455)
						)
						(theSmallMonster setScript: southDelay)
					else
						(= monsterNum (= monsterHealth 0))
						(theSmallMonster dispose:)
						(= monsterNum 0)
						(= local68 0)
					)
				)
				(2
					(ego setMotion: MoveTo 0 egoY)
					(theSmallMonster
						posn: (- egoX monsterDistX) (+ egoY monsterDistY)
						loop: 1
						setCel: -1
						setLoop: -1
						setMotion: PChase ego [local44 (GetMonsterIndex param1)] Encounter
					)
				)
				(4
					(ego setMotion: MoveTo 320 egoY)
					(theSmallMonster
						posn: (- egoX monsterDistX) (+ egoY monsterDistY)
						loop: 0
						setCel: -1
						setLoop: -1
						setMotion: PChase ego [local44 (GetMonsterIndex param1)] Encounter
					)
				)
			)
		)
		((== param1 435) (theSmallMonster setScript: (ScriptID 436 2)))
		((== temp2 10)
			(switch (= temp2 (if (< (Random 0 1000) 500) 8 else 2))
				(2 (param2 setScript: inEast))
				(8 (param2 setScript: inWest))
			)
		)
		(temp3 (param2 setScript: inEast))
		(temp4 (param2 setScript: inWest))
		(else
			(= curRoomAmbushX (curRoom ambushX?))
			(= curRoomAmbushY (curRoom ambushY?))
			(param2
				posn: curRoomAmbushX curRoomAmbushY
				setMotion: PChase ego [local44 (GetMonsterIndex param1)] Encounter
			)
		)
	)
)

(procedure (SearchMonster &tmp [temp0 120] [str 70])
	(switch bucks
		(0
			(messager say: 2 0 13 0 0 210)
		)
		(1
			(Prints
				{You find a single silver coin, carefully polish it, and place it in your pouch.__What a way to make a living!}
			)
			(ego get: iSilver 1)
		)
		(else 
			(Printf
				@str
				{You find %d silver coins, and carefully place them in your pouch.}
				bucks
			)
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
					(if monsterDead
						(AlreadyDone)
					else
						(curRoom newRoom: monsterNum)
					)
				)
				(V_ROCK
					(if monsterDead
						(AlreadyDone)
					else
						(CastRock smallMonster)
					)
				)
				(V_DAGGER
					(if monsterDead
						(AlreadyDone)
					else
						(CastDagger smallMonster)
					)
				)
				(V_LOOK
					(if monsterDead
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
					(if monsterDead
						(if searchedMonster
							(AlreadyDone)
						else
							(ego setScript: searchIt 0 theSmallMonster)
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
		(= local70 0)
		(= monsterHealth (- monsterHealth damage))
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
		(= local3 (= local2 (= monsterHurt 0)))
		(= theSmallMonster 0)
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
						(= local6 (= monsterDead 0))
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
							(= local6 (= monsterDead 0))
						)
					)
					(ego illegalBits: (curRoom illBits?))
				)
			)
			((<= monsterHealth 0)
				(localproc_0117 1)
			)
			(else
				(= local6 (= monsterDead 0))
				(ChangeGait MOVE_RUN FALSE)
				(= local2 1)
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
					(not monsterDead)
					monsterNum
					(or local3 (ego inRect: 40 40 260 160))
				)
				(= local6 1)
				((= theSmallMonster smallMonster) posn: 0 1000 init:)
				(localproc_0e23 monsterNum theSmallMonster)
			)
			((and (== monsterNum vMantray) (Btst fFlag351))
				(localproc_0117 0)
			)
			(
				(and
					local6
					monsterNum
					(not local8)
					(<= monsterHealth 0)
					(not (Btst fFlag351))
				)
				(localproc_0117 0)
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
					(if (or local68 (== monsterNum vMantray))
						(theSmallMonster setScript: spellDelay 0 22)
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
					(if (or local68 (== monsterNum vMantray))
						(theSmallMonster setScript: spellDelay 0 20)
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
			(if (and local12 (or local2 local3))
				(self setScript: checkProject 0 0)
			else
				(self setScript: checkProject 0 1)
			)
		)
	)
	
	(method (newRoom n)
		(HandsOff)
		(if local6
			(= monsterDistX (- (ego x?) (theSmallMonster x?)))
			(= monsterDistY (- (ego y?) (theSmallMonster y?)))
		)
		(if
			(or
				monsterDead
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
		(Bclr fFlag351)
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
					((IsObject gClient)
						(-- state)
						(gClient dispose:)
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
				(theSmallMonster
					posn:
						egoX
						(cond 
							((> (curRoom horizon?) (- egoY monsterDistY)) (- (curRoom horizon?) 20))
							((== monsterNum vMantray) -10)
							(else (- egoY monsterDistY))
						)
					setCel: -1
					setLoop: -1
					setMotion: PChase ego [local44 (GetMonsterIndex monsterNum)] Encounter
				)
				(= local68 1)
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
				(theSmallMonster
					posn: egoX (if (== monsterNum 435) 235 else (- egoY monsterDistY))
					setCel: -1
					setLoop: -1
					setMotion: PChase ego [local44 (GetMonsterIndex monsterNum)] Encounter
				)
				(= local68 1)
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
					setMotion: PChase ego [local44 (GetMonsterIndex monsterNum)] Encounter
				)
				(client setScript: 0)
			)
		)
	)
)

(instance spellDelay of Script
	(properties)
	
	(method (doit)
		(if (and seconds monsterHurt (not local70))
			(= seconds 0)
			(= cycles 1)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local68 0)
				(theSmallMonster setCycle: 0 setMotion: 0 ignoreActors: 0)
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
						(Prints
							{Unfortunately, the Mantray appears not to have been affected by your spell.}
						)
						(= ticks 1)
					)
					(monsterHurt
						(Prints
							{The monster doesn't seem very calm.__Maybe it didn't like you hurting it.}
						)
						(= ticks 1)
					)
					(else (= seconds (+ 5 (/ [egoStats CALM] 10))))
				)
			)
			(2
				(= local70 1)
				(if (not (CastDazzle self self))
					(self changeState: 4)
				)
			)
			(3
				(if (== monsterNum vMantray)
					(Prints
						{Unfortunately, the Mantray appears not to have been affected by your spell.}
					)
					(= ticks 1)
				else
					(= seconds (+ 3 (/ [egoStats CALM] 10)))
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
								(< -15 (- (ego x?) (theSmallMonster x?)))
								(< (- (ego x?) (theSmallMonster x?)) 15)
							)
						)
						(theSmallMonster setLoop: -1 setCycle: Walk)
					else
						(theSmallMonster setCycle: Forward)
					)
					(theSmallMonster
						ignoreActors:
						setMotion: PChase ego [local44 (GetMonsterIndex monsterNum)] Encounter
					)
					(= local68 1)
				)
				(= local70 0)
				(self dispose:)
			)
		)
	)
)

(instance searchIt of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((= theSmallMonster register) ignoreActors:)
				(= SearchMonsterX
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
				(= SearchMonsterY
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
				(if (and (== monsterNum vDragon) (theSmallMonster loop?))
					(= SearchMonsterX -58)
				)
				(HandsOff)
				(ego
					ignoreActors:
					setMotion:
						PolyPath
						(+ (theSmallMonster x?) SearchMonsterX)
						(+ (theSmallMonster y?) SearchMonsterY)
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
				(messager say: N_SEARCHIT NULL NULL 1 self ENCOUNTER)
			)
			(4
				(cond 
					((== monsterNum vTroll)
						(if (Btst fBeardKnown)
							(ego get: iTrollBeard)
							(= gotBeard TRUE)
							(messager say: N_SEARCHIT NULL C_GETBEARD 2 smallMonster ENCOUNTER)
						else
							(SearchMonster)
						)
					)
					((== monsterNum vCheetaur)
						(if (Btst fClawsKnown)
							(ego get: iCheetaurClaw (Random 4 10))
							(messager say: N_SEARCHIT 60 C_GETCLAWS2 1 0 ENCOUNTER)
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
						(if (< (ego x?) (theSmallMonster x?)) 140 else 160)
						self
				)
			)
			(5
				(if (and (== monsterNum vTroll) (not (Btst fBeardKnown)))
					(messager say: N_SEARCHIT 60 C_TROLL 1 0 ENCOUNTER)
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
					(theSmallMonster
						view: viewDeadMonster
						setLoop:
							(if (> (theSmallMonster loop?) 1)
								0
							else
								(theSmallMonster loop?)
							)
						cel: 0
						ignoreActors:
						setMotion: 0
						cycleSpeed: 8
					)
					(if (== theSmallMonster vTroll)
						(theSmallMonster setLoop: 0)
					)
					(if (!= monsterNum vGoblin)
						(theSmallMonster setCycle: EndLoop self)
					else
						(= ticks 1)
					)
				else
					(self setScript: (ScriptID 436 3) self theSmallMonster)
				)
			)
			(1
				(= theX (theSmallMonster x?))
				(= theY (theSmallMonster y?))
				(theSmallMonster stopUpd:)
				(ego ignoreActors:)
				(HandsOn)
				(switch monsterNum
					(vGoblin
						(if (not (theSmallMonster loop?))
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
						(if (== (theSmallMonster loop?) 6)
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
						(if (not (theSmallMonster loop?))
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
						(if (not (theSmallMonster loop?))
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
						(if (not (theSmallMonster loop?))
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
						(if (not (theSmallMonster loop?))
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
