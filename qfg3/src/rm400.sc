;;; Sierra Script 1.0 - (do not remove this comment)
(script# 400)
(include game.sh) (include "400.shm")
(use Main)
(use Target)
(use EgoDead)
(use MChase)
(use Dialog)
(use PAvoid)
(use Talker)
(use PChase)
(use Scaler)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Timer)
(use Grooper)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm400 0
	sleepScript 1
	proc400_2 2
)

(local
	theScaleX =  128
	sensedDanger =  1
	monsterHurt
	local3
	bees1
	bees2
	local6
	trapSprung
	local8
	campFire
	local10
	local11
	local12
	fireLit
	local14
	monsterDead
	searchedMonster
	local17
	local18
	local19 =  340
	randSign
	endSigns
	local22
	monsterHere
	local24
	theGGOwnerX_3
	theGGOwnerY_3
	local27
	local28
	local29 = [400 405]
	local31
	local32 = [160 -45 160 355]
	local36 = [35 160 300 160]
	local40
	itX
	itY
	local43
	theCel
	local45
	local46
	local47
)
(procedure (proc400_2)
;;;	(self)
;;;	(super doit: &rest)
)

(procedure (localproc_32f4 &tmp [temp0 2] temp2 temp3)
	(= local31
		(+
			(<<
				(^
					(= local28
						(+
							local24
							(* (+ theGGOwnerX_3 15) 3)
							(* (+ theGGOwnerY_3 30) 2)
						)
					)
					local24
				)
				$0001
			)
			(& $0001 (+ theGGOwnerX_3 theGGOwnerY_3))
		)
	)
	(= local27
		(mod (+ local24 theGGOwnerX_3 theGGOwnerY_3) 2)
	)
	(curRoom picture: [local29 local27])
	(switch (mod (+ local24 theGGOwnerX_3 theGGOwnerY_3) 4)
		(0 (= local43 1))
		(1 (= local43 0))
		(2 (= local43 1))
		(3 (= local43 0))
	)
	(if cast
		(cast
			eachElementDo: #dispose
			eachElementDo: #delete
			release:
		)
	)
	(if addToPics
		(addToPics
			eachElementDo: #dispose
			eachElementDo: #delete
			release:
		)
	)
	(if (curRoom obstacles?)
		((curRoom obstacles?) eachElementDo: #dispose release:)
	)
	(curRoom
		curPic: (curRoom picture?)
		style: (if local43 16393 else PIXELDISSOLVE)
	)
	(if local43
		(DrawPic (curRoom picture?) 16393 TRUE)
	else
		(DrawPic (curRoom picture?) PIXELDISSOLVE TRUE)
	)
	(= temp2 0)
	(= temp3 1)
	(while (< temp2 8)
		(if (& local31 temp3)
			((ScriptID 403 0)
				init: temp2 local28 local43 theGGOwnerX_3 theGGOwnerY_3 local27
			)
		)
		(++ temp2)
		(= temp3 (<< temp3 $0001))
	)
	(DisposeScript 403)
	(if local22
		(fireWood
			approachVerbs: 19 4
			approachX: 176
			approachY: 170
			init:
		)
	)
)

(procedure (localproc_3479 param1 param2 param3 &tmp temp0)
	(= temp0 1)
	(while
	(Message MsgGet curRoomNum param1 param2 param3 temp0)
		(++ temp0)
	)
	(return (-- temp0))
)

(procedure (localproc_3499 param1 &tmp temp0 [temp1 2] temp3 temp4)
	(= temp3 -100)
	(= temp0 0)
	(while (!= temp3 30583)
		(= temp3 (WordAt param1 (* 2 temp0)))
		(++ temp0)
	)
	(return (-- temp0))
)

(instance BookTo of Motion
	
	(method (init actor theX theY whoCares &tmp [temp0 3] clientCycler)
		(if (>= argc 1)
			(= client actor)
			(if (>= argc 2)
				(= x theX)
				(if (>= argc 3)
					(= y theY)
					(if (>= argc 4) (= caller whoCares))
				)
			)
		)
		(= yLast (= xLast (= completed 0)))
		(= b-moveCnt (+ 1 (client moveSpeed?) gameTime))
		(if (= clientCycler (client cycler?))
			(clientCycler cycleCnt: b-moveCnt)
		)
	)
	
	(method (doit &tmp [temp0 6])
		(if (>= (client y?) y)
			(self moveDone:)
		else
			(if (>= (Abs (- gameTime b-moveCnt)) (client moveSpeed?))
				(= b-moveCnt gameTime)
				(client y: (+ (client y?) (client yStep?)))
			)
			(return)
		)
	)
)

(instance roomControls of Controls
	(properties
		name {controls}
	)
)

(instance rm400 of Room
	(properties
		noun N_ROOM
		picture 400
		vanishingY 49
	)
	
	(method (init &tmp [temp0 2])
		(= number curRoomNum)
		(= controls roomControls)
		(= perspective picAngle)
		(if (== prevRoomNum 550)
			(= monsterHurt 1)
			(= local24 global417)
			(= theGGOwnerX_3 gGOwnerX_3)
			(= theGGOwnerY_3 gGOwnerY_3)
			(= prevRoomNum savannaPanoNum)
			(= local27 (& global418 $0001))
			(localproc_32f4)
			(ego
				setScale: Scaler 127 30 189 70
				x: 160
				y: 146
				normalize:
				init:
				noun: N_EGO_TELL
			)
			(switch battleResult
				(battleEGOLOST
					(self setScript: egoIsDead)
				)
				(battleEGOWON
					(= monsterDead TRUE)
					(cSound setLoop: -1 changeTo: 400)
					(it x: 190 y: 158 init:)
					(it setScript: monsterIsDead)
				)
				(battleEGORUNS
					(ego changeGait: MOVE_RUN)
					(= local47 1)
					(HandsOn)
					(self setScript: encounterScript)
				)
			)
		else
			(= local47 0)
			(= sensedDanger 0)
			(cond 
				((== monsterNum 3)
					(cSound setLoop: -1 changeTo: 407)
				)
				((== monsterNum 11)
					(cSound setLoop: -1 changeTo: 408)
				)
				(else
					(cSound setLoop: -1 changeTo: 400)
				)
			)
			(= monsterHealth 0)
			(= thrownDaggers 0)
			(= local24 (Random 64 256))
			(= theGGOwnerX_3 (= theGGOwnerY_3 (Random 5 127)))
			(switch monsterNum
				(2
					(= local24 90)
					(if (Btst fFoundHoneyBird)
						(= theGGOwnerX_3 52)
					else
						(= theGGOwnerX_3 50)
					)
					(&= global418 $fff7)
					(= theGGOwnerY_3 59)
					(localproc_32f4)
					(ego
						setScale: Scaler 127 30 189 70
						x: (if (Btst fFoundHoneyBird) 60 else 160)
						y: 156
						normalize:
						init:
						noun: N_EGO_TELL
					)
					(if (Btst fFoundHoneyBird)
						(self setScript: beeTree)
					else
						(self setScript: encounterScript)
					)
					(HandsOn)
				)
				(74
					(= monsterDead TRUE)
					((ScriptID 705 0) init: 9 6 48)
					(switch controlRet
						(1 (= local22 1))
						(2 0)
					)
					(= theGGOwnerX_3 (= theGGOwnerY_3 128))
					(= local24 192)
					(= monsterHere 0)
					(localproc_32f4)
					(ego
						setScale: Scaler 127 30 189 70
						x: 240
						y: 156
						normalize:
						init:
						noun: N_EGO_TELL
					)
					(HandsOn)
				)
				(0
					(= theGGOwnerX_3 (= theGGOwnerY_3 128))
					(= local24 192)
					(localproc_32f4)
					(ego
						x: 160
						y: 156
						setScale: Scaler 127 30 189 70
						normalize:
						init:
						noun: N_EGO_TELL
					)
					(HandsOn)
					(= monsterHere 0)
					(self setScript: encounterScript)
					(theGame save: 1)
				)
				(3
					(HandsOff)
					(DrawPic 400)
					(ego
						view: 5
						loop: 6
						cel: 0
						x: 125
						y: 140
						setScale:
						scaleX: 155
						scaleY: 155
						setMotion: 0
						init:
					)
					(self setScript: encounterScript)
				)
				(4
					(= monsterDead 1)
					(= endSigns (localproc_3479 6 1 29))
					(= theGGOwnerX_3 (= theGGOwnerY_3 128))
					(= local24 192)
					(localproc_32f4)
					(ego
						x: 160
						y: 156
						setScale: Scaler 127 30 189 70
						normalize:
						init:
						noun: N_EGO_TELL
					)
					(= monsterHere 0)
					(self setScript: encounterScript)
					(HandsOn)
				)
				(5
					(= monsterDead 1)
					(= theGGOwnerX_3 (= theGGOwnerY_3 128))
					(= local24 192)
					(localproc_32f4)
					(ego
						x: 160
						y: 156
						setScale: Scaler 127 30 189 70
						setAvoider: PAvoider
						normalize:
						init:
						noun: N_EGO_TELL
					)
					(= monsterHere 0)
					(self setScript: encounterScript)
					(HandsOn)
				)
				(else 
					(localproc_32f4)
					(ego
						setScale: Scaler 127 30 189 70
						x: 160
						y: 156
						normalize:
						init:
						noun: N_EGO_TELL
					)
					(if (== monsterNum 999)
						(= monsterDead 1)
						(= monsterHere 0)
					else
						(self setScript: encounterScript)
					)
					(HandsOn)
				)
			)
		)
		(Animate (cast elements?) TRUE)
	)
	
	(method (doit)
		(cond 
			(script 0)
			(local40
				(if
					(and
						(< 8 (ego x?))
						(< (ego x?) 315)
						(< 80 (ego y?))
						(< (ego y?) 184)
					)
					(= local40 0)
					(HandsOn)
				)
			)
			((< (ego x?) 6)
				(if (or (not monsterHere) (== monsterNum 2))
					(= theGGOwnerX_3 -20)
					(= theGGOwnerY_3 (ego y?))
					(self setScript: sExit)
				else
					(self setScript: showNewRoom self 4)
				)
			)
			((> (ego y?) 183)
				(if (or (not monsterHere) (== monsterNum 2))
					(= theGGOwnerX_3 (ego x?))
					(= theGGOwnerY_3 250)
					(self setScript: sExit)
				else
					(self setScript: showNewRoom self 3)
				)
			)
			((> (ego x?) 313)
				(if (not monsterHere)
					(= theGGOwnerX_3 330)
					(= theGGOwnerY_3 (ego y?))
					(self setScript: sExit)
				else
					(self setScript: showNewRoom self 2)
				)
			)
			((< (ego y?) 80)
				(if (or (not monsterHere) (== monsterNum 2))
					(= theGGOwnerX_3 (ego x?))
					(= theGGOwnerY_3 (- (ego y?) 10))
					(self setScript: sExit)
				else
					(self setScript: showNewRoom self 1)
				)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(if timer
			(timer dispose: delete:)
		)
		(if (== monsterNum 2)
			(globalSound stop:)
		)
		(= global461 0)
		(= global462 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_REST
					(if monsterHere
						(messager say: NULL NULL C_CANT_REST)
					else
						((ScriptID TIME 5) init: restTime)
					)
				)
				(V_SLEEP
					(if (or (and monsterDead (not monsterHere)) (== monsterNum 74))
						(= monsterNum 74)
						(self setScript: sleepScript 0 0)
					else
						(messager say: N_CUE V_DOIT C_CANT_SLEEP)
					)
				)
				(V_LEVITATE
					(if (ego castSpell: LEVITATE)
						((ScriptID LEVITATION 0) init: (ego x?) (+ (ego y?) 1) 80)
					)
				)
				(V_FETCH
					(if (ego castSpell: FETCH)
						(ego setScript: (ScriptID CASTFETCH 0))
						(return TRUE)
					)
				)
				(V_HONEY
					(if (and (== monsterNum 2) (== theGGOwnerX_3 52))
						(if (& global418 $0002)
							(if (& global418 $0004)
								(super doVerb: theVerb &rest)
							else
								;EO: There is no such message in the MSG file
								(messager say: NULL V_HONEY C_CANT_POUR_HONEY)
							)
						else
							(self setScript: pourHoney)
						)
					else
						(super doVerb: theVerb &rest)
					)
				)
				(V_DAGGER
					(++ thrownDaggers)
					(if (== monsterNum 2)
						(ego setScale:)
					)
					(self setScript: (ScriptID PROJECTILE 0) self V_DAGGER)
				)
				(V_OPEN
					(if (ego castSpell: OPEN)
						(FindTarget
							((User curEvent?) x?)
							((User curEvent?) y?)
						)
						(self setScript: (ScriptID CASTOPEN))
					)
				)
				(V_FLAME
					(if (ego castSpell: FLAMEDART)
						(if (== monsterNum 2)
							(ego setScale:)
						)
						(self setScript: (ScriptID PROJECTILE 0) self V_FLAME)
					)
				)
				(V_FORCEBOLT
					(if (ego castSpell: FORCEBOLT)
						(if (== monsterNum 2)
							(ego setScale:)
						)
						(self setScript: (ScriptID PROJECTILE 0) self V_FORCEBOLT)
					)
				)
				(V_LIGHTNING
					(if (ego castSpell: LIGHTNING)
						(if (== monsterNum 2)
							(ego setScale:)
						)
						(self setScript: (ScriptID PROJECTILE 0) self V_LIGHTNING)
					)
				)
				(V_CALM
					(if (ego castSpell: CALM)
						((Timer new:) setReal: self (/ [egoStats CALM] 10))
						(self setScript: (ScriptID CASTAREA 0) 0 V_CALM)
						(if fireLit
							(campFire setScript: 0 dispose:)
							(= campFire 0)
							(= fireLit 0)
						)
						(= local3 1)
					)
				)
				;not sure what these three were meant for
				(-77
					(if (== monsterNum 5)
						(if local6
							(messager say: NULL NULL 2 1 0 12)
						else
							(genericProp doVerb: V_DO)
						)
						(return TRUE)
					else
						(messager say: NULL NULL 2 1 0 12)
					)
				)
				(-76
					(messager say: NULL NULL 1 1 0 12)
				)
				(-80
					(messager say: NULL NULL 4 1 0 12)
				)
				(V_JUGGLE
					(if (ego castSpell: JUGGLE)
						(if monsterHere
							(messager say: N_CUE V_DOIT C_JUGGLE_LIGHTS)
						else
							(self setScript: (ScriptID CASTJUGGLE 0))
						)
					)
				)
				(V_DAZZLE
					(if (ego castSpell: DAZZLE)
						((Timer new:) setReal: self (/ [egoStats DAZZLE] 10))
						(self setScript: (ScriptID CASTAREA 0) 0 V_DAZZLE)
					)
				)
				(V_TRIGGER
					(if (ego castSpell: TRIGGER)
						(if (== monsterNum 5)
							(self setScript: (ScriptID CASTAREA 0) 0 V_TRIGGER)
						else
							(self setScript: (ScriptID CASTAREA 0) 0 V_TRIGGER)
						)
					)
				)
				(V_ROCK
					(if (== monsterNum 2)
						(ego setScale:)
					)
					(self setScript: (ScriptID PROJECTILE 0) 0 V_ROCK)
				)
				(V_STAFF
					(if (ego castSpell: STAFF)
						(self setScript: (ScriptID STAFF_SCRIPT 0))
					)
				)
				(V_REVERSAL
					(if (ego castSpell: REVERSAL)
						(sFx number: 943 play:)
						(self setScript: (ScriptID CASTAREA 0) self V_REVERSAL)
					)
				)
				(V_DO
					(if (> ((User curEvent?) y?) 100)
						(self setScript: getRocks)
					else
						(super doVerb: theVerb &rest)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
	
	(method (cue)
		(if (== monsterNum 2)
			(ego setScale: Scaler 127 30 189 70)
		else
			(super cue:)
		)
	)
)

(instance egoIsDead of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 43
					setLoop: (Random 0 3)
					cel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(1
				(switch monsterNum
					(vAnt
						(EgoDead C_DEATH_ANT 400 707 EndLoop 152)
					)
					(vDinosaur
						(EgoDead C_DEATH_DINOSAUR 400 633 EndLoop 158)
					)
					(vCroc
						(EgoDead C_DEATH_CROC 400 455 EndLoop 155)
					)
				)
			)
		)
	)
)

(instance monsterIsDead of Script

	(method (doit &tmp egoX egoY)
		(= egoY (ego y?))
		(= egoX (ego x?))
		(if
			(and
				(or (< egoY 70) (> egoY 182) (< egoX 10) (> egoX 310))
				(== state 2)
			)
			(self dispose:)
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp [temp0 23] temp23 sndNum)
		(switch (= state newState)
			(0
				(HandsOff)
				(it
					approachVerbs: 4
					approachX: (/ (+ (it nsLeft?) (it nsRight?)) 2)
					approachY: (+ (/ (+ (it nsTop?) (it nsBottom?)) 2) 5)
				)
				(= monsterHere FALSE)
				(= monsterDead TRUE)
				(= monsterHealth 0)
				(= local11 (if (Random 0 1) -10 else 10))
				(Face it (it x?) (+ (it y?) local11) self)
				(switch monsterNum
					(vAnt
						(ego solvePuzzle: fBeatAnt 2 (| puzzleFIGHTER puzzlePALADIN))
					)
					(vCroc
						(ego solvePuzzle: fBeatCroc 2 (| puzzleFIGHTER puzzlePALADIN))
					)
					(vDinosaur
						(ego solvePuzzle: fBeatDinosaur 2 (| puzzleFIGHTER puzzlePALADIN))
					)
				)
			)
			(1
				(= sndNum
					(cond 
						((== monsterNum vAnt) 153)
						((== monsterNum vCroc) 156)
						((== monsterNum vDinosaur) 159)
					)
				)
				(cSound setLoop: 1 changeTo: sndNum self)
				(globalSound number: 931 setLoop: 1 play:)
				(it
					loop: (if (< local11 0) 1 else 0)
					cel: 0
					setMotion: 0
					init:
					setCycle: EndLoop
				)
			)
			(2
				(= itX (it x?))
				(= itY (it y?))
				(it
					approachVerbs: V_DO
					approachX: (/ (+ (it nsLeft?) (it nsRight?)) 2)
					approachY: (+ (/ (+ (it nsTop?) (it nsBottom?)) 2) 5)
					stopUpd:
				)
				(= [temp0 0] (- (it nsLeft?) 3))
				(= [temp0 1]
					(- (/ (+ (it nsTop?) (it nsBottom?)) 2) 4)
				)
				(= [temp0 2] (+ 3 (it nsRight?)))
				(= [temp0 3]
					(- (/ (+ (it nsTop?) (it nsBottom?)) 2) 4)
				)
				(= [temp0 4] (+ 3 (it nsRight?)))
				(= [temp0 5] (+ 4 (it nsBottom?)))
				(= [temp0 6] (- (it nsLeft?) 3))
				(= [temp0 7] (+ 4 (it nsBottom?)))
				(= [temp0 8] 30583)
				(= [temp0 9] 0)
				(if (curRoom obstacles?)
					(if
						(= temp23
							(MergePoly
								@temp0
								((curRoom obstacles?) elements?)
								((curRoom obstacles?) size?)
							)
						)
						((curRoom obstacles?)
							add:
								((Polygon new:)
									points: temp23
									size: (localproc_3499 temp23)
									type: PBarredAccess
									dynamic: TRUE
									yourself:
								)
						)
					else
						((curRoom obstacles?)
							add:
								((Polygon new:)
									points: @temp0
									size: 4
									type: PBarredAccess
									dynamic: TRUE
									yourself:
								)
						)
					)
				else
					(curRoom
						addObstacle:
							((Polygon new:)
								points: @temp0
								size: 4
								type: PBarredAccess
								dynamic: TRUE
								yourself:
							)
					)
				)
				(= local12 1)
				(HandsOn)
				(cSound number: 400 setLoop: -1 play:)
				(= seconds 20)
			)
			(3
				(self dispose:)
			)
		)
	)
)

(instance getRocks of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (IsObject (ego looper?))
					((ego looper?) dispose:)
					(ego looper: 0)
				)
				(ego
					setMotion: 0
					view: 4
					loop: (mod (ego loop?) 2)
					setCycle: EndLoop self
				)
				(= register (Narrator y?))
			)
			(1
				(Narrator y: 20)
				(messager say: N_CUE V_DOIT C_GET_ROCKS 0 self)
				(ego get: iRocks (Random 2 5))
			)
			(2
				(Narrator y: register)
				(ego setCycle: BegLoop self)
			)
			(3
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance doBattle of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (IsObject (ego looper?)) ((ego looper?) dispose:))
				(ego setMotion: 0)
				(= seconds 2)
				(globalSound client: 0)
			)
			(1
				(messager say: N_CUE V_DOIT C_DO_BATTLE)
				(= cycles 2)
			)
			(2
				(if (< monsterHealth 2)
					(= monsterHealth 2)
				)
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance searchMonster of Script
	
	(method (changeState newState &tmp [str 50])
		(switch (= state newState)
			(0
				(HandsOff)
				(if (Random 0 1)
					(Face ego (+ (ego x?) 10) (ego y?) self)
				else
					(Face ego (- (ego x?) 10) (ego y?) self)
				)
			)
			(1
				(ego view: 4 setCycle: EndLoop self)
			)
			(2 (= cycles 10))
			(3
				(if register
					(if (== register -1)
						(ego get: iHorn solvePuzzle: fGetHorn 3 (| puzzleFIGHTER puzzlePALADIN))
						(messager say: N_CUE V_DOIT C_GET_HORN 0 self)
					else
						(ego get: iRoyals register)
						(Message MsgGet 400 N_CUE V_DOIT C_GET_MONEY 1 @str)
						(messager sayFormat: NARRATOR @str register)
						(= cycles 1)
					)
				else
					(messager say: N_CUE V_DOIT C_GET_NOTHING 0 self)
				)
			)
			(4
				(ego setCycle: BegLoop self)
			)
			(5
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance beeTree of Script

	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(if (not (& global418 $0004))
					(if (not (& global418 $0010))
						(it init:)
					)
					(if (& global418 $0002)
						(genericProp init:)
					)
				)
				(beeHandler init:)
				(globalSound number: 405 setLoop: -1 play:)
				((= bees1 (Actor new:))
					view: 402
					x: 202
					y: 13
					priority: 1
					signal: ignrAct
					setLoop: 2
					noun: N_BEES
					setCycle: RandCycle
					init:
				)
				((= bees2 (Actor new:))
					view: 402
					x: 213
					y: 29
					priority: 11
					signal: ignrAct
					setLoop: 2
					noun: N_BEES
					setCycle: RandCycle
					init:
				)
				(self dispose:)
			)
		)
	)
)

(instance sleepScript of Script
	
	(method (changeState newState &tmp sleptHours saveTime palInfo rand)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 2)
				(= sensedDanger 0)
			)
			(1
				(ego setMotion: PolyPath 165 135 self)
			)
			(2
				(ego
					x: (- (ego x?) 25)
					view: 35
					loop: 1
					cel: 0
					setCycle: EndLoop self
				)
				(cSound setLoop: -1 fade: 127 1 30 0 changeTo: 927)
			)
			(3
				(if (= palInfo (PalVary PALVARYINFO))
					(if (< palInfo 64) (PalVary PALVARYNEWTIME 3))
				else
					(PalVary PALVARYSTART 400 3)
				)
				(= seconds 5)
			)
			(4
				(if (not local12)
					(if (and fireLit (Btst fMetAardvark))
						(= rand (Random 0 3))
					else
						(= rand 0)
					)
					(if (not rand)
						(cond 
							((and (Random 0 1) (not (Btst fMetAardvark)))
								(Bset fMetAardvark)
								(= monsterNum 1)
							)
							((and (<= TIME_SUNSET timeODay) (<= timeODay TIME_MIDNIGHT))
								(if (< (Random 1 10) 5)
									(= monsterNum 585)
								)
							)
							((not (Random 0 2))
								(= monsterNum 585)
							)
						)
						(if (and (!= monsterNum 74) (!= timeODay TIME_NOTYETDAWN))
							(= saveTime Clock)
							((ScriptID TIME 4) init: 3)
							(= sleptHours
								(/ (mod (- (+ Clock 3600) saveTime) 3600) 150)
							)
							(ego useStamina: (- (* sleptHours 2)))
							(ego takeDamage: (- (* sleptHours 2)))
							(ego useMana: (- (* sleptHours 2)))
						)
					)
				)
				(self cue:)
			)
			(5
				(switch monsterNum
					(74
						(= seconds 5)
					)
					(1
						(cSound number: 409 setLoop: -1 play:)
						(self setScript: (ScriptID 402 0) self)
					)
					(else 
						(= local22 0)
						(= fireLit FALSE)
						(if (Btst fSenseDanger)
							(= sensedDanger TRUE)
							(self setScript: paladinHearsMonster self)
						else
							(= seconds 2)
						)
					)
				)
			)
			(6
				(if (!= monsterNum 1)
					(self cue:)
				)
			)
			(7
				(cond 
					((== monsterNum 1)
						((ego actions?) dispose:)
						(ego actions: 0)
						(self cue:)
					)
					((== monsterNum 74)
						(PalVary PALVARYREVERSE 3)
						(Bclr fEgoIsAsleep)
						(= seconds 4)
					)
					(sensedDanger
						(= seconds 3)
					)
					(else
						(ego setCycle: BegLoop self)
					)
				)
			)
			(8
				(if (!= monsterNum 74)
					(cond 
						((== monsterNum 1)
							(HandsOn)
							(self dispose:)
						)
						(sensedDanger
							(= local12 1)
							(client setScript: encounterScript)
							(self dispose:)
						)
						(else
							(= local12 1)
							(ego x: (+ (ego x?) 25))
							(client setScript: encounterScript)
							(self dispose:)
						)
					)
				else
					(self cue:)
				)
			)
			(9
				((ScriptID TIME 7) init: 5 40)
				(= cycles 10)
			)
			(10
				(ego setCycle: BegLoop self)
			)
			(11
				(ego x: (+ (ego x?) 25) normalize:)
				(cSound setLoop: -1 number: 400 play:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sExit of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and thrownDaggers (not local17))
					(messager say: NULL V_DAGGER C_GET_DAGGERS 0 self)
					(ego get: iDaggers thrownDaggers)
				else
					(self cue:)
				)
			)
			(1
				(ego setMotion: PolyPath theGGOwnerX_3 theGGOwnerY_3 self)
			)
			(2
				(if fireLit
					(Bset fFakeDeath)
					(EgoDead C_DEATH_SMOKY 400 415 EndLoop)
					(= cycles 10)
				else
					(self cue:)
				)
			)
			(3
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance pourHoney of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 170 160 self)
			)
			(1
				(ego setHeading: 90 self)
			)
			(2
				(ego view: 4 loop: 0 setCycle: EndLoop self)
			)
			(3
				(|= global418 $0008)
				(genericProp init:)
				(messager say: NULL V_HONEY C_POUR_HONEY 0 self)
			)
			(4
				(ego setCycle: BegLoop self)
			)
			(5
				(ego normalize:)
				(ego drop: iHoney 1)
				(|= global418 $0002)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance getFeather of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 90 self)
			)
			(1
				(ego view: 4 loop: 0 setCycle: EndLoop self)
			)
			(2
				(genericProp setCycle: EndLoop self noun: 8)
				(Bclr fFoundHoneyBird)
				(ego get: iFeather solvePuzzle: fGetFeather 8)
				(|= global418 $0004)
			)
			(3
				(ego setCycle: BegLoop self)
			)
			(4
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance paladinHearsMonster of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setLoop: 3 cel: 0 setCycle: EndLoop self)
				(= register (Narrator y?))
			)
			(1
				(Narrator y: 20)
				(messager say: N_CUE V_DOIT C_SENSE_DANGER)
				(ego setLoop: 5 cel: 3 setCycle: EndLoop self)
			)
			(2
				(Narrator y: register)
				(ego x: (+ (ego x?) 25) normalize:)
				(= cycles 10)
			)
			(3
				(self dispose:)
			)
		)
	)
)

(instance castFire of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((User curEvent?) x: 146 y: 160)
				(self setScript: (ScriptID PROJECTILE 0) self 81)
			)
			(1
				(HandsOn)
				(ego normalize:)
				(= fireLit TRUE)
				((= campFire (Prop new:))
					signal: (| ignrAct skipCheck fixPriOn)
					view: 400
					loop: 6
					cel: 0
					x: 144
					y: 149
					priority: 13
					setScript: loopSound
					noun: N_FIRE
					init:
					setCycle: Forward
				)
				(self dispose:)
			)
		)
	)
)

(instance loopSound of Script
	
	(method (doit)
		(if (and (!= (globalSound number?) 913) (== state 1))
			(self cue:)
		else
			(super doit:)
		)
	)
	
	(method (dispose)
		(globalSound client: 0 stop:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks 120)
			)
			(1
				(globalSound number: 913 setLoop: -1 play: self)
			)
			(2
				(self init:)
			)
		)
	)
)

(instance kindleFire of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 4 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(1
				(if fireLit
					(self cue:)
				else
					(= seconds 5)
				)
			)
			(2
				(if fireLit
					(= fireLit FALSE)
					(campFire setScript: 0 dispose:)
					(messager say: N_FIRE V_PEACEWATER 0)
					(= campFire FALSE)
				else
					(= fireLit TRUE)
					((= campFire (Prop new:))
						view: 400
						loop: 6
						cel: 0
						x: 144
						y: 149
						priority: 13
						signal: (| ignrAct skipCheck fixPriOn)
						noun: 20
						setScript: loopSound
						init:
						setCycle: Forward
					)
				)
				(ego setCycle: BegLoop self)
			)
			(3
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance it of TargActor
	(properties
		view 0
		signal (| ignrAct ignrHrz skipCheck)
	)
	
	(method (init)
		(if (not (cast contains: self))
			(super init:)
		)
		(if monsterDead
			(self approachVerbs: V_DO)
		)
		(switch monsterNum
			(2
				(= noun N_HONEY_BIRD)
				(self approachVerbs: V_DO)
				(cond 
					(
						(and
							(Btst fFoundHoneyBird)
							(> (ego x?) 10)
							(not (& global418 $0002))
						)
						(self
							view: 402
							setLoop: 4
							cel: 0
							y: 176
							z: 150
							x: 214
							cycleSpeed: 3
							signal: (| signal fixPriOn)
							moveSpeed: 0
							priority: 10
						)
					)
					(
						(and
							(Btst fFoundHoneyBird)
							(& global418 $0002)
							(not (& global418 $0004))
						)
						(self
							view: 402
							setLoop: 0
							x: 188
							y: 180
							z: 20
							cycleSpeed: 3
							moveSpeed: 2
							setCycle: RandCycle
						)
					)
					(else
						(self
							view: 402
							setLoop: 0
							z: 0
							y: 26
							x: 50
							cycleSpeed: 3
							moveSpeed: 2
							setCycle: Walk
							signal: (| signal fixPriOn)
							setMotion: MoveTo local19 26 self
							priority: 10
						)
					)
				)
				(= approachX x)
				(= approachY y)
			)
			(vDinosaur
				(if monsterDead
					(self
						view: vDinosaur
						setScale: 160
						cycleSpeed: (+ (ego cycleSpeed?) 5)
						noun: N_DINOSAUR_DEAD
						setMotion: 0
					)
				else
					(self
						view: 558
						origStep: 4622
						setScale: 160
						moveSpeed: (+ (ego moveSpeed?) 8)
						cycleSpeed: (+ (ego cycleSpeed?) 10)
						setCycle: Walk
						noun: N_DINOSAUR_ALIVE
						setMotion: MChase ego local45 self
					)
				)
			)
			(vAnt
				(if monsterDead
					(self
						view: 563
						setScale: 160
						cycleSpeed: (+ (ego cycleSpeed?) 4)
						noun: N_ANT_DEAD
						setMotion: 0
					)
				else
					(self
						origStep: 3598
						view: 561
						setScale: 160
						moveSpeed: (+ (ego moveSpeed?) 7)
						cycleSpeed: (+ (ego cycleSpeed?) 8)
						setCycle: Walk
						noun: N_ANT_ALIVE
						setMotion: MChase ego local45 self
					)
				)
			)
			(vCroc
				(if monsterDead
					(self
						view: 582
						setScale: 160
						cycleSpeed: (+ (ego cycleSpeed?) 4)
						noun: N_CROC_DEAD
						setMotion: 0
					)
				else
					(self
						origStep: 2570
						view: 580
						setScale: 160
						moveSpeed: (+ (ego moveSpeed?) 5)
						cycleSpeed: (+ (ego cycleSpeed?) 6)
						setCycle: Walk
						noun: N_CROC_ALIVE
						setMotion: MChase ego local45 self
					)
				)
			)
		)
	)
	
	(method (doit &tmp temp0 theBrLeft theBrRight temp3 temp4 temp5 temp6)
		(if script
			(script doit:)
		)
		(if
			(and
				local3
				monsterHurt
				(curRoom timer?)
				(not (ego script?))
			)
			(= local3 0)
			((curRoom timer?) dispose:)
			(curRoom timer: 0)
			(messager say: N_CUE V_DOIT C_CANT_CALM)
		)
		(return
			(if (not local8)
				(|= signal ignrAct)
				(cond 
					((and mover (curRoom timer?) (!= monsterNum 2))
						(self setMotion: 0)
						(= local14
							((Polygon new:)
								type: PBarredAccess
								init:
									(- nsLeft 6)
									(- nsBottom 5)
									(+ 6 nsRight)
									(- nsBottom 5)
									(+ 6 nsRight)
									(+ 3 nsBottom)
									(- nsLeft 6)
									(+ 3 nsBottom)
								yourself:
							)
						)
						(curRoom addObstacle: local14)
					)
					(
						(and
							(not monsterDead)
							(not mover)
							(not (curRoom timer?))
							(not local40)
							(!= monsterNum 74)
							(!= monsterNum 2)
						)
						(if (== monsterNum 0)
							(if (and (== (charge state?) 6) (<= y 300))
								(if (IsObject local14)
									((curRoom obstacles?) delete: local14)
									(local14 dispose:)
								)
								(if (!= loop 1)
									(self setMotion: BookTo 160 300 charge)
								)
							)
						else
							(if (IsObject local14)
								((curRoom obstacles?) delete: local14)
								(local14 dispose:)
							)
							(self setMotion: MChase ego local45 self)
						)
					)
				)
				(cond 
					((== monsterNum 560)
						(if (!= cel theCel)
							(= theCel cel)
							(if (or (== cel 1) (== cel 5))
								(ShakeScreen 1)
								(sFx setLoop: 1 number: 404 play:)
							)
						)
					)
					((== monsterNum 0)
						(if (!= cel theCel)
							(= theCel cel)
							(if (or (== cel 0) (== cel 2))
								(ShakeScreen 1)
								(sFx setLoop: 1 number: 404 play:)
							)
						)
						(if
							(and
								(<= nsLeft (ego x?))
								(<= (ego x?) nsRight)
								(< (ego y?) y)
								(not local46)
							)
							(EgoDead C_RHINO 0 410)
						)
						(if (> y (ego y?))
							(= local46 1)
						)
					)
					((== monsterNum 2)
						(cond 
							(
								(and
									(Btst fFoundHoneyBird)
									(& global418 $0002)
									(== x 188)
									(> (ego x?) 160)
								)
								(self setCycle: Walk setLoop: 0 setMotion: MoveTo 340 100)
								(|= global418 $0010)
								(genericProp noun: N_FEATHER)
							)
							((and (> (ego x?) 200) (== x 214))
								(self setCycle: Walk setLoop: 0 setMotion: MoveTo 340 y)
							)
						)
					)
					(else 0)
				)
				(if (& signal delObj)
					(return (& signal delObj))
				)
				(if (and (& signal notUpd) (not (& signal startUpdOn)))
					(return (not (& signal startUpdOn)))
				)
				(if scaler
					(scaler doit:)
				)
				(if (> scaleX theScaleX)
					(= theScaleX scaleX)
				)
				(if (< scaleX 0)
					(= scaleY (= scaleX theScaleX))
				)
				(if (& scaleSignal scalable)
					(= temp5 (>> origStep $0008))
					(= temp6 (& origStep $00ff))
					(if (or (< y 0) (< x -40) (> y 260) (> x 340))
						(= temp3 temp5)
						(= temp4 temp6)
					else
						(if (< y (curRoom vanishingY?))
							(= temp3 (/ (- (curRoom vanishingY?) y) temp5))
							(= temp4 (/ (- (curRoom vanishingY?) y) temp6))
						else
							(= temp3 (/ (* temp5 scaleX) 128))
							(= temp4 (/ (* temp6 scaleY) 128))
						)
						(if temp3
							(if (or (> temp3 temp5) (< temp3 0)) (= temp3 temp5))
						else
							(= temp3 1)
						)
						(if temp4
							(if (or (> temp4 temp6) (< temp4 0)) (= temp4 temp6))
						else
							(= temp4 1)
						)
					)
					(if (or (!= temp3 xStep) (!= temp4 yStep))
						(self setStep: temp3 temp4 1)
					)
				)
				(cond 
					(avoider
						(avoider doit:)
					)
					(mover
						(mover doit:)
					)
				)
				(if cycler
					(= theBrLeft brLeft)
					(= theBrRight brRight)
					(cycler doit:)
					(if baseSetter
						(baseSetter doit: self)
					else
						(BaseSetter self)
					)
					(if
						(and
							(or (!= theBrLeft brLeft) (!= theBrRight brRight))
							(self cantBeHere:)
						)
						(self findPosn:)
					)
				)
				(= xLast x)
				(= yLast y)
			else
				(if (== local8 1)
					(switch monsterNum
						(vAnt
							(sFx setLoop: 1 number: 904 play:)
						)
						(vCroc
							(sFx setLoop: 1 number: 907 play:)
						)
						(vDinosaur
							(sFx setLoop: 1 number: 903 play:)
						)
					)
				)
				(-- local8)
			)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_DO
					(cond 
						((== monsterNum 2)
							(return TRUE)
						)
						(monsterHere
							(super doVerb: theVerb &rest)
						)
						(else
							(switch monsterNum
								(vAnt
									(ego setScript: searchMonster 0 0)
								)
								(vCroc
									(ego
										setScript: searchMonster 0 (if searchedMonster 0 else (Random 2 5))
									)
								)
								(vDinosaur
									(ego setScript: searchMonster 0 (if searchedMonster 0 else -1))
								)
								(else 
									(super doVerb: theVerb &rest)
								)
							)
							(= searchedMonster TRUE)
						)
					)
				)
				(V_LOOK
					(if (== monsterNum 2)
						(if (not mover)
							(if loop
								(messager say: N_HONEY_BIRD V_LOOK C_BIRD_IN_TREE)
							else
								(messager say: N_HONEY_BIRD V_LOOK C_BIRD_IN_HONEY)
							)
						else
							(super doVerb: theVerb &rest)
						)
					else
						(super doVerb: theVerb &rest)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
	
	(method (delete)
		(= signal (& signal $ffdf))
		(super delete:)
	)
	
	(method (cue &tmp temp0)
		(cond 
			((== monsterNum 2)
				(self loop: 4 cel: 0 y: 176 z: 150)
			)
			((< (ego z?) local45)
				(self setMotion: 0)
				(ego setMotion: 0)
				(= monsterHere 0)
				(if (< global461 global462)
					(= global417 local24)
					(= gGOwnerX_3 theGGOwnerX_3)
					(= gGOwnerY_3 theGGOwnerY_3)
					(= savannaPanoNum prevRoomNum)
					(if local27
						(&= global418 $0001)
					else
						(^= global418 $0001)
					)
					(if (not local10)
						(= local10 1)
						(= local8 10000)
						(self setScript: doBattle)
					)
				else
					(= monsterHealth FALSE)
					(= fireLit FALSE)
					(messager say: N_CUE V_DOIT C_GOT_AWAY)
					(= monsterDead TRUE)
					(= local17 1)
					(curRoom setScript: 0)
					(cSound setLoop: -1 changeTo: 400)
					(self dispose:)
					(HandsOn)
				)
			)
			((IsObject cycler)
				(cycler dispose:)
				(= cycler 0)
			)
		)
	)
	
	(method (getHurt amount whatHurt)
		(= monsterHurt TRUE)
		(if (curRoom timer?)
			((curRoom timer?) dispose: delete:)
		)
		(if (not script)
			(if (!= monsterNum 2)
				(if (not monsterDead)
					(= local8 10)
					(switch monsterNum
						(vAnt
							(-= monsterHealth whatHurt)
						)
						(vCroc
							(-= monsterHealth whatHurt)
						)
						(vDinosaur
							(-= monsterHealth whatHurt)
						)
					)
					(if (< monsterHealth 1)
						(= local10 0)
						(self setScript: monsterIsDead)
					)
				)
			else
				(self
					moveSpeed: 0
					cycleSpeed: 0
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 340 y
				)
			)
		)
	)
)

(instance genericProp of Prop

	(method (init)
		(cond 
			((== monsterNum 5)
				(= view 401)
				(= loop (Random 0 3))
				(= x 148)
				(= y 137)
				(= noun 5)
				(= approachX x)
				(= approachY 147)
			)
			((== monsterNum 4)
				(= view 400)
				(= loop 7)
				(= cel (Random 0 3))
				(= x 106)
				(= y 114)
				(= noun 6)
				(= randSign (Random 1 endSigns))
			)
			(else
				(= x 188)
				(= y 160)
				(= view 402)
				(= loop 3)
				(= cel (if (& global418 $0002) 0 else 1))
				(= priority 0)
				(= signal 20496)
				(= approachX 170)
				(= approachY 160)
				(self approachVerbs: V_DO)
				(if (& global418 $0010)
					(= noun N_FEATHER)
				else
					(= noun N_HONEY)
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								173 149
								203 149
								203 164
								173 164
							yourself:
						)
				)
			)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(cond 
					((== noun N_TRAP)
						(if (or (== loop 0) (== loop 1) (== loop 3))
							(if (== cel 0)
								(= local6 1)
								(self setCycle: EndLoop self)
								(globalSound setLoop: 1 number: 401 play:)
								(ego addHonor: 10)
							else
								(messager say: N_TRAP V_DOIT C_TRAP_DISARMED)
							)
						else
							(super doVerb: theVerb &rest)
						)
					)
					((== noun N_SIGN)
						(messager say: N_SIGN V_DO C_DONT_TAKE_SIGN)
					)
					(
						(and
							(not (& global418 $0008))
							(not (& global418 $0004))
						)
						(self setScript: getFeather)
					)
					(else
						(super doVerb: theVerb &rest)
					)
				)
			)
			(V_LOOK
				(cond 
					((== noun N_TRAP)
						(if (or (== loop 0) (== loop 1) (== loop 3))
							(if (== cel 0)
								(messager say: N_TRAP V_DOIT C_TRAP_ARMED)
							else
								(messager say: N_TRAP V_LOOK C_TRAP_DISARMED)
							)
						else
							(super doVerb: theVerb &rest)
						)
					)
					((== noun N_SIGN)
						(messager say: N_SIGN V_LOOK C_READ_SIGN randSign)
					)
					(else
						(super doVerb: theVerb &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(if (== monsterNum 5)
			(if (or (not local6) trapSprung)
				(self doVerb: V_DO)
			else
				(= trapSprung TRUE)
				(messager say: N_TRAP V_DOIT C_SPRING_TRAP)
			)
		else
			(super cue:)
		)
	)
)

(instance eggo of Actor
	(properties
		x -10
		y 160
		noun N_EGGO
		view 417
		signal (| ignrAct skipCheck)
	)
	
	(method (init)
		(= origStep (ego origStep?))
		(super init:)
		(self
			setCycle: Walk
			setLoop: GradualLooper
			cycleSpeed: (ego cycleSpeed?)
			moveSpeed: (ego moveSpeed?)
			setScale: 140
			setMotion: PFollow ego 40
		)
	)
	
	(method (doit)
		(= approachX x)
		(= approachY (+ y 5))
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(= monsterHere 0)
			(if (Btst fStarving)
				(Bclr fStarving)
			else
				(Bclr fHungry)
			)
			(self dispose:)
		)
		(super doVerb: theVerb &rest)
	)
)

(instance fireWood of View
	(properties
		x 146
		y 160
		noun N_FIREWOOD
		view 400
		loop 8
		signal ignrAct
	)
	
	(method (init)
		(super init: &rest)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						129 155
						163 155
						163 171
						129 171
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(if (not fireLit)
					(= noun N_FIRE)
					(curRoom setScript: castFire)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(V_TINDERBOX
				(if (not fireLit)
					(= noun N_FIRE)
					(curRoom setScript: kindleFire)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(V_DO
				(if fireLit
					(= noun N_FIREWOOD)
					(curRoom setScript: kindleFire)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(V_PEACEWATER
				(if fireLit
					(= noun N_FIREWOOD)
					(ego drop: iPeaceWater 1)
					(ego get: iWaterskin 1)
					(curRoom setScript: kindleFire)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance encounterScript of Script

	(method (changeState newState &tmp i)
		(switch (= state newState)
			(0
				(= ticks 20)
			)
			(1
				(ego normalize:)
				(switch monsterNum
					(vAnt
						(= local45 35)
						(= global462 600)
						(if (not monsterHealth)
							(= monsterHealth 150)
						)
					)
					(vCroc
						(= local45 30)
						(= global462 700)
						(if (not monsterHealth)
							(= monsterHealth 200)
						)
					)
					(vDinosaur
						(= local45 40)
						(if (not monsterHealth)
							(= monsterHealth 320)
						)
						(= global462 1000)
					)
					(0
						(= global462 1000)
						(= monsterHere 0)
						(= monsterHealth 1000)
						(client setScript: charge)
						(self dispose:)
					)
					(3
						(client setScript: laurelAndHardy)
						(self dispose:)
					)
					(4
						(= monsterHere FALSE)
						(genericProp init:)
						(HandsOn)
						(self dispose:)
					)
					(5
						(genericProp approachVerbs: V_DO init:)
						(= monsterHere FALSE)
						(HandsOn)
						(self dispose:)
					)
					(11
						(eggo init: approachVerbs: V_DO)
						(= monsterHere TRUE)
						(HandsOn)
						(self dispose:)
					)
				)
				(HandsOn)
				(self cue:)
			)
			(2
				(= monsterDead FALSE)
				(cond 
					((== monsterNum 2)
						(it init:)
					)
					(local47
						(it x: 270 y: 140 init:)
						(ego setMotion: PolyPath -10 (ego y?))
					)
					(else
						(if
							(and
								(!= monsterNum 11)
								(Btst fSenseDanger)
								(not sensedDanger)
								(not local47)
							)
							(= sensedDanger TRUE)
							(messager say: N_CUE V_DOIT C_SENSE_DANGER)
						)
						(= i (Random 0 3))
						(it x: [local32 i] y: [local36 i] init:)
					)
				)
				(if local47
					(if (== machineSpeed 0)
						(it signal: (& (it signal?) $bfff))
						(= local8 200)
					)
				else
					(= local8 50)
				)
				(= monsterHere TRUE)
				(if (and (!= (cSound number?) 700) (!= monsterNum 2))
					(cSound setLoop: -1 number: 700 play:)
				)
				(self dispose:)
			)
		)
	)
)

(instance showNewRoom of Script
	
	(method (changeState newState &tmp egoX egoY temp2)
		(switch (= state newState)
			(0
				(HandsOff)
				(self cue:)
			)
			(1
				(switch register
					(4
						(= egoX -15)
						(= egoY (ego y?))
						(ego setMotion: PolyPath egoX egoY self)
					)
					(3
						(= egoY 220)
						(= egoX (ego x?))
						(ego setMotion: PolyPath egoX egoY self)
					)
					(2
						(= egoX 330)
						(= egoY (ego y?))
						(ego setMotion: PolyPath egoX egoY self)
					)
					(1 (self cue:))
				)
			)
			(2
				(= local40 1)
				(it setMotion: 0)
				(ego setMotion: 0)
				(= cycles 2)
			)
			(3
				(switch register
					(4
						(-- theGGOwnerX_3)
						(= egoX 0)
						(if (== monsterNum 11)
							(eggo x: 330)
						else
							(it x: (+ 325 (- (it x?) (ego x?))))
						)
						(ego x: 325)
						(= egoY (ego y?))
					)
					(3
						(++ theGGOwnerY_3)
						(= egoY 190)
						(if (== monsterNum 11)
							(eggo y: 80)
						else
							(it y: (- (it y?) (ego y?)))
						)
						(ego y: 80)
						(= egoX (ego x?))
					)
					(2
						(++ theGGOwnerX_3)
						(= egoX 320)
						(= egoY (ego y?))
						(cond 
							((== monsterNum 11) (eggo x: -10))
							((== monsterNum 2)
								(if (== theGGOwnerX_3 52)
									(= local19 214)
									(Bset fFoundHoneyBird)
									(Bclr fHoneyBirdHinted)
								)
							)
							(else (it x: (+ -5 (- (it x?) (ego x?)))))
						)
						(ego x: -5)
					)
					(1
						(-- theGGOwnerY_3)
						(= egoX (ego x?))
						(= egoY 79)
						(if (== monsterNum 11)
							(eggo y: 225)
						else
							(it y: (+ 265 (- (it y?) (ego y?))))
						)
						(ego y: 195)
					)
				)
				(localproc_32f4)
				(ego
					normalize:
					init:
					setScale: Scaler 127 30 189 70
					setMotion: PolyPath egoX egoY
				)
				(if (== monsterNum 11)
					(eggo init:)
				else
					(it init:)
					(if (and (== monsterNum 2) (== theGGOwnerX_3 52))
						(= monsterHere FALSE)
						(globalSound number: 405 setLoop: -1 play:)
						(beeHandler init:)
						((= bees1 (Actor new:))
							view: 402
							x: 202
							y: 13
							priority: 1
							signal: (| ignrAct fixPriOn)
							setLoop: 2
							noun: 54
							setCycle: Forward
							init:
						)
						((= bees2 (Actor new:))
							view: 402
							x: 213
							y: 29
							priority: 11
							signal: (| ignrAct fixPriOn)
							setLoop: 2
							noun: 54
							setCycle: Forward
							init:
						)
					)
				)
				(= cycles 15)
			)
			(4
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance charge of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(it
					view: 497
					init:
					loop: 1
					setMotion: 0
					cel: 0
					x: 150
					y: 59
				)
				(theIconBar curIcon: (theIconBar at: ICON_WALK))
				(if (not (HaveMouse))
					(theGame setCursor: theCursor TRUE 310 155)
				else
					(theGame setCursor: theCursor TRUE)
				)
				(Face ego it self)
			)
			(1
				(= cycles 2)
			)
			(2
				(messager say: N_CUE V_DOIT C_RHINO 0 self)
			)
			(3
				(it setCycle: EndLoop self)
			)
			(4
				(messager say: N_CUE V_DOIT C_RHINO_SEES 0 self)
			)
			(5
				(= seconds 2)
			)
			(6
				(it
					setLoop: 0
					origStep: 3341
					maxScale: 3200
					setScale: Scaler 150 7 180 50
					moveSpeed: 6
					cycleSpeed: 5
					setCycle: Walk
					setMotion: BookTo 160 300 self
				)
			)
			(7
				(messager say: N_CUE V_DOIT C_RHINO_PASSES 0 self)
			)
			(8
				(= monsterHere FALSE)
				(it dispose:)
				(self dispose:)
			)
		)
	)
)

(instance laurelAndHardy of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(self setScript: (ScriptID 401 0) self)
			)
			(1
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance killerBees of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(= cycles 20)
			)
			(1
				(globalSound number: 406 setLoop: -1 play:)
				(messager say: N_BEES V_DOIT C_KILLER_BEES 0 self)
			)
			(2
				(bees1
					cycleSpeed: 0
					moveSpeed: 0
					priority: 14
					setMotion: MoveTo (- (ego x?) 10) (- (ego y?) 25) self
				)
				(bees2
					cycleSpeed: 0
					moveSpeed: 0
					priority: 14
					setMotion: MoveTo (- (ego x?) 7) (- (ego y?) 40)
				)
			)
			(3
				(ego view: 11 loop: 1 cycleSpeed: 0 setCycle: Forward)
				(= seconds 4)
			)
			(4
				(globalSound stop:)
				(EgoDead C_DEATH_BEES)
			)
		)
	)
)

(instance beeHandler of TargFeature
	(properties
		x 210
		y 60
		noun N_BEES
		nsTop 10
		nsLeft 193
		nsBottom 52
		nsRight 238
	)
	
	(method (init)
		(theDoits add: self)
		(super init:)
	)
	
	(method (doit)
		(if
			(and
				(!= (globalSound number?) 405)
				(== (globalSound prevSignal?) -1)
			)
			(globalSound number: 405 setLoop: -1 play:)
		)
		(super doit:)
	)
	
	(method (getHurt)
		(bees1 setScript: killerBees)
	)
)

(instance sFx of Sound)
