;;; Sierra Script 1.0 - (do not remove this comment)
(script# 400)
(include sci.sh)
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
	local1 =  1
	local2
	local3
	newActor
	newActor_2
	local6
	local7
	local8
	newProp
	local10
	local11
	local12
	local13
	local14
	local15
	local16
	local17
	local18
	local19 =  340
	local20
	local21
	local22
	local23
	local24
	theGGOwnerX_3
	theGGOwnerY_3
	local27
	local28
	[local29 2] = [400 405]
	local31
	[local32 4] = [160 -45 160 355]
	[local36 4] = [35 160 300 160]
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
	(self)
	(super doit: &rest)
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
		style: (if local43 16393 else 9)
	)
	(if local43
		(DrawPic (curRoom picture?) 16393 TRUE)
	else
		(DrawPic (curRoom picture?) dpOPEN_PIXELATION TRUE)
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
	(Message msgGET curRoomNum param1 param2 param3 temp0)
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
	(properties)
	
	(method (init theClient theX theY theCaller &tmp [temp0 3] clientCycler)
		(if (>= argc 1)
			(= client theClient)
			(if (>= argc 2)
				(= x theX)
				(if (>= argc 3)
					(= y theY)
					(if (>= argc 4) (= caller theCaller))
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
			(if
			(>= (Abs (- gameTime b-moveCnt)) (client moveSpeed?))
				(= b-moveCnt gameTime)
				(client y: (+ (client y?) (client yStep?)))
			)
			(return)
		)
	)
)

(instance controls of Controls
	(properties)
)

(instance rm400 of Rm
	(properties
		noun 17
		picture 400
		vanishingY 49
	)
	
	(method (init &tmp [temp0 2])
		(= number curRoomNum)
		(= controls controls)
		(= perspective picAngle)
		(if (== prevRoomNum 550)
			(= local2 1)
			(= local24 global417)
			(= theGGOwnerX_3 gGOwnerX_3)
			(= theGGOwnerY_3 gGOwnerY_3)
			(= prevRoomNum gGGClientModNum_2)
			(= local27 (& global418 $0001))
			(localproc_32f4)
			(ego
				setScale: Scaler 127 30 189 70
				x: 160
				y: 146
				normalize:
				init:
				noun: 3
			)
			(switch global155
				(0 (self setScript: egoIsDead))
				(1
					(= local15 1)
					(cSound setLoop: -1 changeTo: 400)
					(it x: 190 y: 158 init:)
					(it setScript: monsterIsDead)
				)
				(2
					(ego changeGait: 1)
					(= local47 1)
					(HandsOn)
					(self setScript: encounterScript)
				)
			)
		else
			(= local47 0)
			(= local1 0)
			(cond 
				((== monsterNum 3) (cSound setLoop: -1 changeTo: 407))
				((== monsterNum 11) (cSound setLoop: -1 changeTo: 408))
				(else (cSound setLoop: -1 changeTo: 400))
			)
			(= monsterHealth 0)
			(= global426 0)
			(= local24 (Random 64 256))
			(= theGGOwnerX_3 (= theGGOwnerY_3 (Random 5 127)))
			(switch monsterNum
				(2
					(= local24 90)
					(if (Btst 99)
						(= theGGOwnerX_3 52)
					else
						(= theGGOwnerX_3 50)
					)
					(= global418 (& global418 $fff7))
					(= theGGOwnerY_3 59)
					(localproc_32f4)
					(ego
						setScale: Scaler 127 30 189 70
						x: (if (Btst 99) 60 else 160)
						y: 156
						normalize:
						init:
						noun: 3
					)
					(if (Btst 99)
						(self setScript: beeTree)
					else
						(self setScript: encounterScript)
					)
					(HandsOn)
				)
				(74
					(= local15 1)
					((ScriptID 705 0) init: 9 6 48)
					(switch controlRet
						(1 (= local22 1))
						(2 0)
					)
					(= theGGOwnerX_3 (= theGGOwnerY_3 128))
					(= local24 192)
					(= local23 0)
					(localproc_32f4)
					(ego
						setScale: Scaler 127 30 189 70
						x: 240
						y: 156
						normalize:
						init:
						noun: 3
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
						noun: 3
					)
					(HandsOn)
					(= local23 0)
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
					(= local15 1)
					(= local21 (localproc_3479 6 1 29))
					(= theGGOwnerX_3 (= theGGOwnerY_3 128))
					(= local24 192)
					(localproc_32f4)
					(ego
						x: 160
						y: 156
						setScale: Scaler 127 30 189 70
						normalize:
						init:
						noun: 3
					)
					(= local23 0)
					(self setScript: encounterScript)
					(HandsOn)
				)
				(5
					(= local15 1)
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
						noun: 3
					)
					(= local23 0)
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
						noun: 3
					)
					(if (== monsterNum 999)
						(= local15 1)
						(= local23 0)
					else
						(self setScript: encounterScript)
					)
					(HandsOn)
				)
			)
		)
		(Animate (cast elements?) 1)
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
				(if (or (not local23) (== monsterNum 2))
					(= theGGOwnerX_3 -20)
					(= theGGOwnerY_3 (ego y?))
					(self setScript: sExit)
				else
					(self setScript: showNewRoom self 4)
				)
			)
			((> (ego y?) 183)
				(if (or (not local23) (== monsterNum 2))
					(= theGGOwnerX_3 (ego x?))
					(= theGGOwnerY_3 250)
					(self setScript: sExit)
				else
					(self setScript: showNewRoom self 3)
				)
			)
			((> (ego x?) 313)
				(if (not local23)
					(= theGGOwnerX_3 330)
					(= theGGOwnerY_3 (ego y?))
					(self setScript: sExit)
				else
					(self setScript: showNewRoom self 2)
				)
			)
			((< (ego y?) 80)
				(if (or (not local23) (== monsterNum 2))
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
		(if timer (timer dispose: delete:))
		(if (== monsterNum 2) (globalSound stop:))
		(= global461 0)
		(= global462 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(65
					(if local23
						(messager say: 0 0 47)
					else
						((ScriptID 7 5) init: global455)
					)
				)
				(74
					(if
					(or (and local15 (not local23)) (== monsterNum 74))
						(= monsterNum 74)
						(self setScript: sleepScript 0 0)
					else
						(messager say: 9 6 37)
					)
				)
				(84
					(if (ego castSpell: 28)
						((ScriptID 31 0) init: (ego x?) (+ (ego y?) 1) 80)
					)
				)
				(82
					(if (ego castSpell: 26)
						(ego setScript: (ScriptID 37 0))
						(return 1)
					)
				)
				(40
					(if (and (== monsterNum 2) (== theGGOwnerX_3 52))
						(if (& global418 $0002)
							(if (& global418 $0004)
								(super doVerb: theVerb &rest)
							else
								(messager say: 0 40 34)
							)
						else
							(self setScript: pourHoney)
						)
					else
						(super doVerb: theVerb &rest)
					)
				)
				(20
					(++ global426)
					(if (== monsterNum 2) (ego setScale:))
					(self setScript: (ScriptID 32 0) self 20)
				)
				(75
					(if (ego castSpell: 19)
						(AutoTarget
							((User curEvent?) x?)
							((User curEvent?) y?)
						)
						(self setScript: (ScriptID 13))
					)
				)
				(81
					(if (ego castSpell: 25)
						(if (== monsterNum 2) (ego setScale:))
						(self setScript: (ScriptID 32 0) self 81)
					)
				)
				(83
					(if (ego castSpell: 27)
						(if (== monsterNum 2) (ego setScale:))
						(self setScript: (ScriptID 32 0) self 83)
					)
				)
				(88
					(if (ego castSpell: 32)
						(if (== monsterNum 2) (ego setScale:))
						(self setScript: (ScriptID 32 0) self 88)
					)
				)
				(80
					(if (ego castSpell: 24)
						((Timer new:) setReal: self (/ [egoStats 24] 10))
						(self setScript: (ScriptID 12 0) 0 80)
						(if local13
							(newProp setScript: 0 dispose:)
							(= newProp 0)
							(= local13 0)
						)
						(= local3 1)
					)
				)
				(-77
					(if (== monsterNum 5)
						(if local6
							(messager say: 0 0 2 1 0 12)
						else
							(genericProp doVerb: 4)
						)
						(return 1)
					else
						(messager say: 0 0 2 1 0 12)
					)
				)
				(-76
					(messager say: 0 0 1 1 0 12)
				)
				(-80
					(messager say: 0 0 4 1 0 12)
				)
				(86
					(if (ego castSpell: 30)
						(if local23
							(messager say: 9 6 57)
						else
							(self setScript: (ScriptID 62 0))
						)
					)
				)
				(78
					(if (ego castSpell: 22)
						((Timer new:) setReal: self (/ [egoStats 22] 10))
						(self setScript: (ScriptID 12 0) 0 78)
					)
				)
				(77
					(if (ego castSpell: 21)
						(if (== monsterNum 5)
							(self setScript: (ScriptID 12 0) 0 77)
						else
							(self setScript: (ScriptID 12 0) 0 77)
						)
					)
				)
				(33
					(if (== monsterNum 2) (ego setScale:))
					(self setScript: (ScriptID 32 0) 0 33)
				)
				(87
					(if (ego castSpell: 31)
						(self setScript: (ScriptID 46 0))
					)
				)
				(85
					(if (ego castSpell: 29)
						(sFx number: 943 play:)
						(self setScript: (ScriptID 12 0) self 85)
					)
				)
				(4
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
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 43
					setLoop: (Random 0 3)
					cel: 0
					cycleSpeed: 10
					setCycle: End self
				)
			)
			(1
				(switch monsterNum
					(565
						(EgoDead 41 400 707 End 152)
					)
					(560
						(EgoDead 42 400 633 End 158)
					)
					(585
						(EgoDead 43 400 455 End 155)
					)
				)
			)
		)
	)
)

(instance monsterIsDead of Script
	(properties)
	
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
	
	(method (changeState newState &tmp [temp0 23] temp23 temp24)
		(switch (= state newState)
			(0
				(HandsOff)
				(it
					approachVerbs: 4
					approachX: (/ (+ (it nsLeft?) (it nsRight?)) 2)
					approachY: (+ (/ (+ (it nsTop?) (it nsBottom?)) 2) 5)
				)
				(= local23 0)
				(= local15 1)
				(= monsterHealth 0)
				(= local11 (if (Random 0 1) -10 else 10))
				(Face it (it x?) (+ (it y?) local11) self)
				(switch monsterNum
					(565 (ego solvePuzzle: 293 2 9))
					(585 (ego solvePuzzle: 292 2 9))
					(560 (ego solvePuzzle: 294 2 9))
				)
			)
			(1
				(= temp24
					(cond 
						((== monsterNum 565) 153)
						((== monsterNum 585) 156)
						((== monsterNum 560) 159)
					)
				)
				(cSound setLoop: 1 changeTo: temp24 self)
				(globalSound number: 931 setLoop: 1 play:)
				(it
					loop: (if (< local11 0) 1 else 0)
					cel: 0
					setMotion: 0
					init:
					setCycle: End
				)
			)
			(2
				(= itX (it x?))
				(= itY (it y?))
				(it
					approachVerbs: 4
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
									type: 2
									dynamic: 1
									yourself:
								)
						)
					else
						((curRoom obstacles?)
							add:
								((Polygon new:)
									points: @temp0
									size: 4
									type: 2
									dynamic: 1
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
								type: 2
								dynamic: 1
								yourself:
							)
					)
				)
				(= local12 1)
				(HandsOn)
				(cSound number: 400 setLoop: -1 play:)
				(= seconds 20)
			)
			(3 (self dispose:))
		)
	)
)

(instance getRocks of Script
	(properties)
	
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
					setCycle: End self
				)
				(= register (Narrator y?))
			)
			(1
				(Narrator y: 20)
				(messager say: 9 6 45 0 self)
				(ego get: 23 (Random 2 5))
			)
			(2
				(Narrator y: register)
				(ego setCycle: Beg self)
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
	(properties)
	
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
				(messager say: 9 6 44)
				(= cycles 2)
			)
			(2
				(if (< monsterHealth 2) (= monsterHealth 2))
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance searchMonster of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 50])
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
				(ego view: 4 setCycle: End self)
			)
			(2 (= cycles 10))
			(3
				(if register
					(if (== register -1)
						(ego get: 22 solvePuzzle: 261 3 9)
						(messager say: 9 6 38 0 self)
					else
						(ego get: 0 register)
						(Message msgGET 400 9 6 39 1 @temp0)
						(messager sayFormat: 99 @temp0 register)
						(= cycles 1)
					)
				else
					(messager say: 9 6 40 0 self)
				)
			)
			(4 (ego setCycle: Beg self))
			(5
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance beeTree of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(if (not (& global418 $0004))
					(if (not (& global418 $0010)) (it init:))
					(if (& global418 $0002) (genericProp init:))
				)
				(beeHandler init:)
				(globalSound number: 405 setLoop: -1 play:)
				((= newActor (Actor new:))
					view: 402
					x: 202
					y: 13
					priority: 1
					signal: 16400
					setLoop: 2
					noun: 54
					setCycle: RandCycle
					init:
				)
				((= newActor_2 (Actor new:))
					view: 402
					x: 213
					y: 29
					priority: 11
					signal: 16400
					setLoop: 2
					noun: 54
					setCycle: RandCycle
					init:
				)
				(self dispose:)
			)
		)
	)
)

(instance sleepScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 theClock temp2 temp3)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 2)
				(= local1 0)
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
					setCycle: End self
				)
				(cSound setLoop: -1 fade: 127 1 30 0 changeTo: 927)
			)
			(3
				(if (= temp2 (PalVary pvGET_CURRENT_STEP))
					(if (< temp2 64) (PalVary pvCHANGE_TICKS 3))
				else
					(PalVary pvINIT 400 3)
				)
				(= seconds 5)
			)
			(4
				(if (not local12)
					(if (and local13 (Btst 32))
						(= temp3 (Random 0 3))
					else
						(= temp3 0)
					)
					(if (not temp3)
						(cond 
							((and (Random 0 1) (not (Btst 32))) (Bset 32) (= monsterNum 1))
							((and (<= 4 timeODay) (<= timeODay 6)) (if (< (Random 1 10) 5) (= monsterNum 585)))
							((not (Random 0 2)) (= monsterNum 585))
						)
						(if (and (!= monsterNum 74) (!= timeODay 7))
							(= theClock Clock)
							((ScriptID 7 4) init: 3)
							(= temp0
								(/ (mod (- (+ Clock 3600) theClock) 3600) 150)
							)
							(ego useStamina: (- (* temp0 2)))
							(ego takeDamage: (- (* temp0 2)))
							(ego useMana: (- (* temp0 2)))
						)
					)
				)
				(self cue:)
			)
			(5
				(switch monsterNum
					(74 (= seconds 5))
					(1
						(cSound number: 409 setLoop: -1 play:)
						(self setScript: (ScriptID 402 0) self)
					)
					(else 
						(= local22 0)
						(= local13 0)
						(if (Btst 150)
							(= local1 1)
							(self setScript: paladinHearsMonster self)
						else
							(= seconds 2)
						)
					)
				)
			)
			(6
				(if (!= monsterNum 1) (self cue:))
			)
			(7
				(cond 
					((== monsterNum 1) ((ego actions?) dispose:) (ego actions: 0) (self cue:))
					((== monsterNum 74) (PalVary pvREVERSE 3) (Bclr 81) (= seconds 4))
					(local1 (= seconds 3))
					(else (ego setCycle: Beg self))
				)
			)
			(8
				(if (!= monsterNum 74)
					(cond 
						((== monsterNum 1) (HandsOn) (self dispose:))
						(local1
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
				((ScriptID 7 7) init: 5 40)
				(= cycles 10)
			)
			(10 (ego setCycle: Beg self))
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and global426 (not local17))
					(messager say: 0 20 36 0 self)
					(ego get: 10 global426)
				else
					(self cue:)
				)
			)
			(1
				(ego setMotion: PolyPath theGGOwnerX_3 theGGOwnerY_3 self)
			)
			(2
				(if local13
					(Bset 140)
					(EgoDead 48 400 415 End)
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
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 170 160 self)
			)
			(1 (ego setHeading: 90 self))
			(2
				(ego view: 4 loop: 0 setCycle: End self)
			)
			(3
				(= global418 (| global418 $0008))
				(genericProp init:)
				(messager say: 0 40 35 0 self)
			)
			(4 (ego setCycle: Beg self))
			(5
				(ego normalize:)
				(ego drop: 29 1)
				(= global418 (| global418 $0002))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance getFeather of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 90 self)
			)
			(1
				(ego view: 4 loop: 0 setCycle: End self)
			)
			(2
				(genericProp setCycle: End self noun: 8)
				(Bclr 99)
				(ego get: 30 solvePuzzle: 260 8)
				(= global418 (| global418 $0004))
			)
			(3 (ego setCycle: Beg self))
			(4
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance paladinHearsMonster of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setLoop: 3 cel: 0 setCycle: End self)
				(= register (Narrator y?))
			)
			(1
				(Narrator y: 20)
				(messager say: 9 6 51)
				(ego setLoop: 5 cel: 3 setCycle: End self)
			)
			(2
				(Narrator y: register)
				(ego x: (+ (ego x?) 25) normalize:)
				(= cycles 10)
			)
			(3 (self dispose:))
		)
	)
)

(instance castFire of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((User curEvent?) x: 146 y: 160)
				(self setScript: (ScriptID 32 0) self 81)
			)
			(1
				(HandsOn)
				(ego normalize:)
				(= local13 1)
				((= newProp (Prop new:))
					signal: 20496
					view: 400
					loop: 6
					cel: 0
					x: 144
					y: 149
					priority: 13
					setScript: loopSound
					noun: 20
					init:
					setCycle: Fwd
				)
				(self dispose:)
			)
		)
	)
)

(instance loopSound of Script
	(properties)
	
	(method (doit)
		(if
		(and (!= (globalSound number?) 913) (== state 1))
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
			(0 (= ticks 120))
			(1
				(globalSound number: 913 setLoop: -1 play: self)
			)
			(2 (self init:))
		)
	)
)

(instance kindleFire of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 4 loop: 1 cel: 0 setCycle: End self)
			)
			(1
				(if local13 (self cue:) else (= seconds 5))
			)
			(2
				(if local13
					(= local13 0)
					(newProp setScript: 0 dispose:)
					(messager say: 20 48 0)
					(= newProp 0)
				else
					(= local13 1)
					((= newProp (Prop new:))
						view: 400
						loop: 6
						cel: 0
						x: 144
						y: 149
						priority: 13
						signal: 20496
						noun: 20
						setScript: loopSound
						init:
						setCycle: Fwd
					)
				)
				(ego setCycle: Beg self)
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
		signal $7000
	)
	
	(method (init)
		(if (not (cast contains: self)) (super init:))
		(if local15 (self approachVerbs: 4))
		(switch monsterNum
			(2
				(= noun 21)
				(self approachVerbs: 4)
				(cond 
					(
						(and
							(Btst 99)
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
							(Btst 99)
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
			(560
				(if local15
					(self
						view: 560
						setScale: 160
						cycleSpeed: (+ (ego cycleSpeed?) 5)
						noun: 22
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
						noun: 23
						setMotion: MChase ego local45 self
					)
				)
			)
			(565
				(if local15
					(self
						view: 563
						setScale: 160
						cycleSpeed: (+ (ego cycleSpeed?) 4)
						noun: 24
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
						noun: 25
						setMotion: MChase ego local45 self
					)
				)
			)
			(585
				(if local15
					(self
						view: 582
						setScale: 160
						cycleSpeed: (+ (ego cycleSpeed?) 4)
						noun: 26
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
						noun: 27
						setMotion: MChase ego local45 self
					)
				)
			)
		)
	)
	
	(method (doit &tmp temp0 theBrLeft theBrRight temp3 temp4 temp5 temp6)
		(if script (script doit:))
		(if
			(and
				local3
				local2
				(curRoom timer?)
				(not (ego script?))
			)
			(= local3 0)
			((curRoom timer?) dispose:)
			(curRoom timer: 0)
			(messager say: 9 6 56)
		)
		(return
			(if (not local8)
				(= signal (| signal $4000))
				(cond 
					((and mover (curRoom timer?) (!= monsterNum 2))
						(self setMotion: 0)
						(= local14
							((Polygon new:)
								type: 2
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
							(not local15)
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
								(if (!= loop 1) (self setMotion: BookTo 160 300 charge))
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
							(EgoDead 24 0 410)
						)
						(if (> y (ego y?)) (= local46 1))
					)
					((== monsterNum 2)
						(cond 
							(
								(and
									(Btst 99)
									(& global418 $0002)
									(== x 188)
									(> (ego x?) 160)
								)
								(self setCycle: Walk setLoop: 0 setMotion: MoveTo 340 100)
								(= global418 (| global418 $0010))
								(genericProp noun: 19)
							)
							((and (> (ego x?) 200) (== x 214)) (self setCycle: Walk setLoop: 0 setMotion: MoveTo 340 y))
						)
					)
					(else 0)
				)
				(if (& signal $8000) (return (& signal $8000)))
				(if (and (& signal $0004) (not (& signal $0002)))
					(return (not (& signal $0002)))
				)
				(if scaler (scaler doit:))
				(if (> scaleX theScaleX) (= theScaleX scaleX))
				(if (< scaleX 0) (= scaleY (= scaleX theScaleX)))
				(if (& scaleSignal $0001)
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
					(avoider (avoider doit:))
					(mover (mover doit:))
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
						(565
							(sFx setLoop: 1 number: 904 play:)
						)
						(585
							(sFx setLoop: 1 number: 907 play:)
						)
						(560
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
				(4
					(cond 
						((== monsterNum 2) (return 1))
						(local23 (super doVerb: theVerb &rest))
						(else
							(switch monsterNum
								(565
									(ego setScript: searchMonster 0 0)
								)
								(585
									(ego
										setScript: searchMonster 0 (if local16 0 else (Random 2 5))
									)
								)
								(560
									(ego setScript: searchMonster 0 (if local16 0 else -1))
								)
								(else 
									(super doVerb: theVerb &rest)
								)
							)
							(= local16 1)
						)
					)
				)
				(1
					(if (== monsterNum 2)
						(if (not mover)
							(if loop
								(messager say: 21 1 59)
							else
								(messager say: 21 1 58)
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
			((== monsterNum 2) (self loop: 4 cel: 0 y: 176 z: 150))
			((< (ego z?) local45)
				(self setMotion: 0)
				(ego setMotion: 0)
				(= local23 0)
				(if (< global461 global462)
					(= global417 local24)
					(= gGOwnerX_3 theGGOwnerX_3)
					(= gGOwnerY_3 theGGOwnerY_3)
					(= gGGClientModNum_2 prevRoomNum)
					(if local27
						(= global418 (& global418 $0001))
					else
						(= global418 (^ global418 $0001))
					)
					(if (not local10)
						(= local10 1)
						(= local8 10000)
						(self setScript: doBattle)
					)
				else
					(= monsterHealth 0)
					(= local13 0)
					(messager say: 9 6 46)
					(= local15 1)
					(= local17 1)
					(curRoom setScript: 0)
					(cSound setLoop: -1 changeTo: 400)
					(self dispose:)
					(HandsOn)
				)
			)
			((IsObject cycler) (cycler dispose:) (= cycler 0))
		)
	)
	
	(method (getHurt param1 param2)
		(= local2 1)
		(if (curRoom timer?)
			((curRoom timer?) dispose: delete:)
		)
		(if (not script)
			(if (!= monsterNum 2)
				(if (not local15)
					(= local8 10)
					(switch monsterNum
						(565
							(= monsterHealth (- monsterHealth param2))
						)
						(585
							(= monsterHealth (- monsterHealth param2))
						)
						(560
							(= monsterHealth (- monsterHealth param2))
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
	(properties)
	
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
				(= local20 (Random 1 local21))
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
				(self approachVerbs: 4)
				(if (& global418 $0010) (= noun 19) else (= noun 8))
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 173 149 203 149 203 164 173 164
							yourself:
						)
				)
			)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((== noun 5)
						(if (or (== loop 0) (== loop 1) (== loop 3))
							(if (== cel 0)
								(= local6 1)
								(self setCycle: End self)
								(globalSound setLoop: 1 number: 401 play:)
								(ego addHonor: 10)
							else
								(messager say: 5 6 31)
							)
						else
							(super doVerb: theVerb &rest)
						)
					)
					((== noun 6) (messager say: 6 4 30))
					(
						(and
							(not (& global418 $0008))
							(not (& global418 $0004))
						)
						(self setScript: getFeather)
					)
					(else (super doVerb: theVerb &rest))
				)
			)
			(1
				(cond 
					((== noun 5)
						(if (or (== loop 0) (== loop 1) (== loop 3))
							(if (== cel 0)
								(messager say: 5 6 32)
							else
								(messager say: 5 1 31)
							)
						else
							(super doVerb: theVerb &rest)
						)
					)
					((== noun 6) (messager say: 6 1 29 local20))
					(else (super doVerb: theVerb &rest))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(if (== monsterNum 5)
			(if (or (not local6) local7)
				(self doVerb: 4)
			else
				(= local7 1)
				(messager say: 5 6 33)
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
		noun 7
		view 417
		signal $5000
	)
	
	(method (init)
		(= origStep (ego origStep?))
		(super init:)
		(self
			setCycle: Walk
			setLoop: Grooper
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
		(if (== theVerb 4)
			(= local23 0)
			(if (Btst 3) (Bclr 3) else (Bclr 2))
			(self dispose:)
		)
		(super doVerb: theVerb &rest)
	)
)

(instance fireWood of View
	(properties
		x 146
		y 160
		noun 10
		view 400
		loop 8
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 129 155 163 155 163 171 129 171
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(81
				(if (not local13)
					(= noun 20)
					(curRoom setScript: castFire)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(19
				(if (not local13)
					(= noun 20)
					(curRoom setScript: kindleFire)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(4
				(if local13
					(= noun 10)
					(curRoom setScript: kindleFire)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(48
				(if local13
					(= noun 10)
					(ego drop: 37 1)
					(ego get: 15 1)
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
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= ticks 20))
			(1
				(ego normalize:)
				(switch monsterNum
					(565
						(= local45 35)
						(= global462 600)
						(if (not monsterHealth) (= monsterHealth 150))
					)
					(585
						(= local45 30)
						(= global462 700)
						(if (not monsterHealth) (= monsterHealth 200))
					)
					(560
						(= local45 40)
						(if (not monsterHealth) (= monsterHealth 320))
						(= global462 1000)
					)
					(0
						(= global462 1000)
						(= local23 0)
						(= monsterHealth 1000)
						(client setScript: charge)
						(self dispose:)
					)
					(3
						(client setScript: laurelAndHardy)
						(self dispose:)
					)
					(4
						(= local23 0)
						(genericProp init:)
						(HandsOn)
						(self dispose:)
					)
					(5
						(genericProp approachVerbs: 4 init:)
						(= local23 0)
						(HandsOn)
						(self dispose:)
					)
					(11
						(eggo init: approachVerbs: 4)
						(= local23 1)
						(HandsOn)
						(self dispose:)
					)
				)
				(HandsOn)
				(self cue:)
			)
			(2
				(= local15 0)
				(cond 
					((== monsterNum 2) (it init:))
					(local47
						(it x: 270 y: 140 init:)
						(ego setMotion: PolyPath -10 (ego y?))
					)
					(else
						(if
							(and
								(!= monsterNum 11)
								(Btst 150)
								(not local1)
								(not local47)
							)
							(= local1 1)
							(messager say: 9 6 51)
						)
						(= temp0 (Random 0 3))
						(it x: [local32 temp0] y: [local36 temp0] init:)
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
				(= local23 1)
				(if
				(and (!= (cSound number?) 700) (!= monsterNum 2))
					(cSound setLoop: -1 number: 700 play:)
				)
				(self dispose:)
			)
		)
	)
)

(instance showNewRoom of Script
	(properties)
	
	(method (changeState newState &tmp egoX egoY temp2)
		(switch (= state newState)
			(0 (HandsOff) (self cue:))
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
									(Bset 99)
									(Bclr 83)
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
						(= local23 0)
						(globalSound number: 405 setLoop: -1 play:)
						(beeHandler init:)
						((= newActor (Actor new:))
							view: 402
							x: 202
							y: 13
							priority: 1
							signal: 16400
							setLoop: 2
							noun: 54
							setCycle: Fwd
							init:
						)
						((= newActor_2 (Actor new:))
							view: 402
							x: 213
							y: 29
							priority: 11
							signal: 16400
							setLoop: 2
							noun: 54
							setCycle: Fwd
							init:
						)
					)
				)
				(= cycles 15)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance charge of Script
	(properties)
	
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
				(theIconBar curIcon: (theIconBar at: 1))
				(if (not (HaveMouse))
					(theGame setCursor: theCursor 1 310 155)
				else
					(theGame setCursor: theCursor 1)
				)
				(Face ego it self)
			)
			(1 (= cycles 2))
			(2
				(messager say: 9 6 24 0 self)
			)
			(3 (it setCycle: End self))
			(4
				(messager say: 9 6 49 0 self)
			)
			(5 (= seconds 2))
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
				(messager say: 9 6 50 0 self)
			)
			(8
				(= local23 0)
				(it dispose:)
				(self dispose:)
			)
		)
	)
)

(instance laurelAndHardy of Script
	(properties)
	
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(= cycles 20)
			)
			(1
				(globalSound number: 406 setLoop: -1 play:)
				(messager say: 54 6 55 0 self)
			)
			(2
				(newActor
					cycleSpeed: 0
					moveSpeed: 0
					priority: 14
					setMotion: MoveTo (- (ego x?) 10) (- (ego y?) 25) self
				)
				(newActor_2
					cycleSpeed: 0
					moveSpeed: 0
					priority: 14
					setMotion: MoveTo (- (ego x?) 7) (- (ego y?) 40)
				)
			)
			(3
				(ego view: 11 loop: 1 cycleSpeed: 0 setCycle: Fwd)
				(= seconds 4)
			)
			(4
				(globalSound stop:)
				(EgoDead 54)
			)
		)
	)
)

(instance beeHandler of TargFeature
	(properties
		x 210
		y 60
		noun 54
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
		(newActor setScript: killerBees)
	)
)

(instance sFx of Sound
	(properties)
)
