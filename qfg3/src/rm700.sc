;;; Sierra Script 1.0 - (do not remove this comment)
(script# 700)
(include sci.sh)
(use Main)
(use Target)
(use EgoDead)
(use MChase)
(use Dialog)
(use PAvoid)
(use Talker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm700 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	newProp
	local8
	local9
	local10
	local11
	local12
	local13
	newProp_2
	local15
	local16
	local17
	[local18 2]
	newActor
	local21
	local22
	local23
	local24
	local25
	local26
	local27
	local28 =  1
	local29
	local30
	local31
	local32
	local33
	local34 =  340
	local35
	local36
	local37
	local38
	[local39 2] = [8]
	local41
	theGGOwnerX_4
	theGGOwnerY_4
	local44
	local45
	[local46 2] = [700 705]
	local48
	[local49 4] = [-20 170 160 340]
	[local53 4] = [165 -10 260 165]
	local57
	[local58 2]
	local60
	local61
	local62
	local63
	[local64 2]
	[local66 4] = [20 -20 10 -10]
	[local70 48] = [-23 -23 -38 -38 10 -10 -20 20 -34 -34 -22 22 0 1 2 3 0 1 0 1 4 5 4 4 4 5 4 5]
)
(procedure (localproc_3a24 &tmp [temp0 2] temp2 temp3)
	(= local48
		(+
			(<<
				(^
					(= local45
						(+
							local41
							(* (+ theGGOwnerX_4 15) 3)
							(* (+ theGGOwnerY_4 30) 2)
						)
					)
					local41
				)
				$0001
			)
			(& $0001 (+ theGGOwnerX_4 theGGOwnerY_4))
		)
	)
	(= local44
		(mod (+ local41 theGGOwnerX_4 theGGOwnerY_4) 2)
	)
	(switch (mod (+ local41 (* theGGOwnerX_4 theGGOwnerY_4)) 4)
		(0 (= local60 1))
		(1 (= local60 0))
		(2 (= local60 1))
		(3 (= local60 0))
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
	(Animate (cast elements?) 0)
	(if (curRoom obstacles?)
		((curRoom obstacles?) eachElementDo: #dispose release:)
	)
	(if local44
		(curRoom picture: 700 curPic: 700)
		(if local60
			(curRoom
				style: 16393
				addObstacle:
					((Polygon new:)
						type: 2
						init: -15 220 -15 151 121 151 151 167 151 220
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 190 220 190 167 218 152 330 152 330 220
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 330 115 243 114 164 79 154 43 154 -15 330 -15
						yourself:
					)
					((Polygon new:)
						type: 2
						init: -15 -15 122 -15 122 43 116 74 59 120 -15 117
						yourself:
					)
			)
		else
			(curRoom
				style: 9
				addObstacle:
					((Polygon new:)
						type: 2
						init: -15 -15 168 -15 168 43 159 78 66 119 -15 119
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 190 -15 330 -15 330 119 255 119 200 76 190 41
						yourself:
					)
					((Polygon new:)
						type: 2
						init: -15 220 -15 158 121 158 128 220
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 172 220 176 155 330 154 330 220
						yourself:
					)
			)
		)
	else
		(curRoom picture: 705 curPic: 705)
		(if local60
			(curRoom
				style: 16393
				addObstacle:
					((Polygon new:)
						type: 2
						init: -15 220 -15 169 136 170 155 220
						yourself:
					)
					((Polygon new:)
						type: 2
						init: -15 -15 188 -15 188 27 178 93 136 139 -15 138
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 330 158 223 147 207 27 207 -15 330 -15
						yourself:
					)
			)
		else
			(curRoom
				style: 9
				addObstacle:
					((Polygon new:)
						type: 2
						init: -15 -15 113 -15 113 26 91 146 -15 156
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 330 142 182 142 146 98 129 28 129 -15 330 -15
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 161 220 188 172 330 172 330 220
						yourself:
					)
			)
		)
	)
	(DrawPic (curRoom picture?) (curRoom style?) TRUE)
	(= temp2 0)
	(= temp3 1)
	(while (< temp2 6)
		(if (& local48 temp3)
			((ScriptID 703 0)
				init: temp2 local45 local60 theGGOwnerX_4 theGGOwnerY_4 local44
			)
		)
		(++ temp2)
		(= temp3 (<< temp3 $0001))
	)
	(DisposeScript 703)
	(if local37
		(fireWood
			view: 700
			approachVerbs: 19 4
			approachX: 176
			approachY: 170
			loop: 7
			cel: 0
			x: 146
			y: 160
			init:
		)
	)
)

(procedure (localproc_3e94 param1 &tmp temp0 [temp1 2] temp3 temp4)
	(= temp3 -100)
	(= temp0 0)
	(while (!= temp3 30583)
		(= temp3 (WordAt param1 (* 2 temp0)))
		(++ temp0)
	)
	(return (-- temp0))
)

(procedure (localproc_3ebe param1)
	(return
		(if
			(or
				(< (param1 x?) -80)
				(> (param1 x?) 399)
				(< (param1 y?) -80)
			)
		else
			(> (param1 y?) 269)
		)
	)
)

(procedure (localproc_3ef2 param1 param2 param3 &tmp temp0)
	(= temp0 1)
	(while
	(Message msgGET curRoomNum param1 param2 param3 temp0)
		(++ temp0)
	)
	(return (-- temp0))
)

(instance controls of Controls
	(properties)
)

(instance sFx of Sound
	(properties)
)

(instance leopardProject of Actor
	(properties
		signal $1000
	)
	
	(method (init &tmp temp0 temp1)
		(switch (= temp0 (Random 0 2))
			(0
				(globalSound number: 13 setLoop: 1 play:)
				(= temp1 2)
			)
			(1
				(globalSound number: 943 setLoop: 1 play:)
				(= temp1 13)
			)
			(2
				(globalSound number: 11 setLoop: 1 play:)
				(= temp1 4)
			)
		)
		(self
			view: 21
			loop: temp1
			x: (+
				(it x?)
				(/ (* (it scaleY?) [local66 (it loop?)]) 128)
			)
			y: (+
				(it y?)
				(/ (* (it scaleY?) [local70 (it loop?)]) 128)
			)
			setStep: 8 5
			moveSpeed: 0
			cycleSpeed: 0
			z: 15
			signal: 6160
			priority: (Max (it priority?) (ego priority?))
			ignoreActors: 1
			ignoreHorizon: 1
			illegalBits: 0
			setScale: -1 it
			setCycle: Fwd
			setMotion:
				MoveTo
				(/ (+ (ego nsLeft?) (ego nsRight?)) 2)
				(+ (ego nsTop?) 25)
				self
		)
		(switch temp0
			(0
				(= global432 (- global432 global373))
			)
			(1
				(= global432 (- global432 global375))
			)
			(2
				(= global432 (- global432 global380))
			)
		)
		(super init:)
		(SetNowSeen self)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((== heroType 0)
				(if
				(== (theIconBar curInvIcon?) (inventory at: 5))
					(= local11 1)
					(= local10 0)
				else
					(= local11 0)
				)
			)
			((== heroType 3)
				(if
					(and
						(== (theIconBar curInvIcon?) (inventory at: 1))
						((inventory at: 1) state?)
					)
					(= local10 1)
					(= local11 0)
				else
					(= local10 0)
				)
			)
		)
		(cond 
			(
				(and
					(ego onMe: (/ (+ nsRight nsLeft) 2) (+ nsTop 4))
					(not noun)
				)
				(= noun 1)
				(self cue: 0)
			)
			(
				(and
					noun
					(it onMe: (/ (+ nsRight nsLeft) 2) (+ nsTop 4))
				)
				(self cue: 0)
			)
		)
	)
	
	(method (dispose)
		(if (IsObject script)
			(DisposeClone script)
			(= script 0)
		)
		(super dispose:)
	)
	
	(method (setScript)
		(if (IsObject script)
			(DisposeClone script)
			(= script 0)
		)
		(super setScript: &rest)
	)
	
	(method (cue param1 &tmp temp0)
		(cond 
			((not script)
				(= temp0 0)
				(cond 
					(
						(and
							(ego onMe: (/ (+ nsRight nsLeft) 2) (+ nsTop 4))
							(not reversalTimer)
							(not local11)
						)
						(cond 
							((not local10)
								(= local28 0)
								(globalSound number: 901 setLoop: 1 play:)
								(if (not (ego takeDamage: 20))
									(curRoom setScript: egoIsDead)
								)
							)
							((not local9) (= local9 1) (messager say: 4 6 91))
						)
						(= temp0 1)
					)
					(
						(and
							(ego onMe: (/ (+ nsRight nsLeft) 2) (+ nsTop 4))
							(or reversalTimer local11)
						)
						(self setMotion: MoveTo (it x?) (it y?) self)
						(sFx number: 10 setLoop: 1 play:)
						(if (and local11 (not local8))
							(= local8 1)
							(messager say: 4 6 92)
						)
					)
					(
					(it onMe: (/ (+ nsRight nsLeft) 2) (+ nsTop 4)) (it getHurt: -1 0) (= temp0 1))
					(else (= temp0 1))
				)
				(if (and (not cycleSpeed) temp0)
					(self
						cycleSpeed: 1
						setMotion: 0
						setScript: (explode new:) self
					)
				)
			)
			((and argc param1) (DisposeClone script) (= script 0) (self dispose:))
		)
	)
)

(instance explode of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((== (client loop?) 2) (client loop: 9 cel: 0 setCycle: End self))
					((== (client loop?) 4) (client loop: 10 cel: 0 setCycle: End self))
					(else (client loop: 14 cel: 0 setCycle: End self))
				)
				(globalSound number: 930 setLoop: 1 play:)
			)
			(1 (client cue: 1))
		)
	)
)

(instance rm700 of Rm
	(properties
		noun 79
		picture 700
		horizon 41
		vanishingY 37
	)
	
	(method (init &tmp temp0)
		(= number curRoomNum)
		(= controls controls)
		(= perspective picAngle)
		(= curPic picture)
		(= overlays -1)
		(if (== prevRoomNum 550)
			(= local1 1)
			(= local41 global430)
			(= theGGOwnerX_4 gGOwnerX_4)
			(= theGGOwnerY_4 gGOwnerY_4)
			(= prevRoomNum gGGClientModNum_2_2)
			(= local44 (& global431 $0001))
			(= local28 (& global431 $0002))
			(localproc_3a24)
			(ego
				init:
				normalize:
				setScale: Scaler 120 59 189 47
				posn: 150 156
				signal: 2
				noun: 2
			)
			(switch global155
				(0 (self setScript: egoIsDead))
				(1
					(cSound setLoop: -1 changeTo: 400)
					(= local30 1)
					(it x: 210 y: 150 init:)
					(if (== monsterNum 590) (it loop: 1 cel: 7))
					(switch monsterNum
						(590 (ego solvePuzzle: 295 2 9))
						(595 (ego solvePuzzle: 290 2 9))
						(580 (ego solvePuzzle: 291 2 9))
						(575 (ego solvePuzzle: 296 2 9))
						(565 (ego solvePuzzle: 293 2 9))
						(585 (ego solvePuzzle: 292 2 9))
					)
					(self setScript: monsterIsDead)
				)
				(2
					(= local3 1)
					(ego changeGait: 1)
					(self setScript: encounterScript)
					(cSound setLoop: -1 changeTo: 700)
				)
			)
		else
			(cSound setLoop: -1 changeTo: 400)
			(= global432 230)
			(= monsterHealth 0)
			(= global426 0)
			(= local41 (Random 64 256))
			(= theGGOwnerX_4 (= theGGOwnerY_4 (Random 5 127)))
			(switch monsterNum
				(74
					((ScriptID 705 0) init: 4 6 95)
					(switch controlRet
						(1 (= local37 1))
						(2 0)
					)
					(= theGGOwnerX_4 (= theGGOwnerY_4 128))
					(= local41 192)
					(= local38 0)
					(= local30 1)
					(localproc_3a24)
					(ego
						x: (if local44 245 else 182)
						y: (if local44 80 else 125)
						init:
						signal: 2
						normalize:
						setScale: Scaler 120 59 189 47
						posn: 240 156
						noun: 2
					)
				)
				(590
					(localproc_3a24)
					(ego
						init:
						normalize:
						signal: 2
						setScale: Scaler 120 59 189 47
						posn: 160 156
						noun: 2
					)
					(if (Btst 96)
						(self setScript: monkeysWithEgo)
						(theGame save: 1)
					else
						(self setScript: encounterScript)
					)
				)
				(6
					(= local38 1)
					(localproc_3a24)
					(HandsOn)
					(ego
						init:
						normalize:
						signal: 2
						setScale: Scaler 120 59 189 47
						posn: 160 156
						noun: 2
					)
					(Bclr 105)
					(cond 
						((& global431 $4000)
							(= global431
								(| (= global431 (& global431 $bfff)) $2000)
							)
						)
						((and (Btst 102) (& global431 $8000))
							(= global431
								(| (= global431 (& global431 $7fff)) $4000)
							)
						)
						(
						(and (not (Btst 102)) (not (& global431 $8000))) (= global431 (| global431 $8000)))
					)
					(self setScript: (ScriptID 702 1))
					((ScriptID 702 0) init:)
				)
				(8
					(= local41 88)
					(= theGGOwnerX_4 (= theGGOwnerY_4 77))
					(localproc_3a24)
					(ego
						init:
						normalize:
						setScale: Scaler 120 59 189 47
						signal: 2
						posn: 160 156
						noun: 2
					)
					(self setScript: (ScriptID 701 2))
				)
				(9
					(Bset 105)
					(= local41 88)
					(= theGGOwnerX_4 (= theGGOwnerY_4 77))
					(localproc_3a24)
					(ego
						init:
						normalize:
						signal: 2
						setScale: Scaler 120 59 189 47
						posn: 160 156
						noun: 2
					)
					(self setScript: (ScriptID 701 5))
				)
				(7
					(= local41 88)
					(= theGGOwnerX_4 (= theGGOwnerY_4 77))
					(localproc_3a24)
					(ego
						init:
						normalize:
						signal: 2
						setScale: Scaler 120 59 189 47
						posn: 160 156
						noun: 2
					)
					(= local38 0)
					(self setScript: (ScriptID 704 0))
					(HandsOn)
				)
				(10
					(= local41 88)
					(= theGGOwnerX_4 (= theGGOwnerY_4 77))
					(localproc_3a24)
					(ego
						init:
						normalize:
						signal: 2
						setScale: Scaler 120 59 189 47
						posn: 160 156
						noun: 2
					)
					(if (not (Btst 116))
						((= newActor (Actor new:))
							view: 700
							loop: 5
							cel: 0
							x: 77
							y: 12
							priority: 15
							signal: 20496
							actions: genericProp
							noun: 81
							init:
						)
					)
					((View new:)
						view: 700
						loop: 4
						cel: 0
						x: 90
						y: 12
						priority: 15
						signal: 20496
						actions: genericProp
						noun: 81
						init:
					)
					((View new:)
						view: 700
						loop: 4
						cel: 1
						x: 85
						y: 18
						priority: 15
						signal: 20496
						actions: genericProp
						noun: 81
						init:
					)
					((View new:)
						view: 700
						loop: 5
						cel: 1
						x: 83
						y: 17
						priority: 15
						signal: 20496
						actions: genericProp
						noun: 81
						init:
					)
					(genericProp
						nsLeft: 63
						nsTop: 5
						nsRight: 100
						nsBottom: 22
						noun: 81
					)
					(HandsOn)
				)
				(4
					(= local30 1)
					(= local36 (localproc_3ef2 6 1 80))
					(= local38 0)
					(= theGGOwnerX_4 (= theGGOwnerY_4 128))
					(= local41 192)
					(localproc_3a24)
					(ego
						x: 160
						y: 156
						init:
						setAvoider: PAvoider
						normalize:
						signal: 2
						setScale: Scaler 120 59 189 47
						noun: 2
					)
					(genericProp init:)
					(HandsOn)
				)
				(5
					(= local30 1)
					(= local38 0)
					(= theGGOwnerX_4 (= theGGOwnerY_4 128))
					(= local41 192)
					(localproc_3a24)
					(ego
						x: 160
						y: 156
						init:
						normalize:
						signal: 2
						setAvoider: PAvoider
						setScale: Scaler 120 59 189 47
						noun: 2
					)
					(genericProp approachVerbs: 4 init:)
					(HandsOn)
				)
				(else 
					(localproc_3a24)
					(ego
						init:
						normalize:
						signal: 2
						setScale: Scaler 120 59 189 47
						posn: 160 156
						noun: 2
					)
					(if (!= monsterNum 999)
						(self setScript: encounterScript)
						(= local38 1)
					)
				)
			)
		)
		(Animate (cast elements?) 1)
	)
	
	(method (doit)
		(if (and local38 (Btst 102) (== monsterNum 6))
			(= local38 0)
		)
		(cond 
			(script
				(if (== monsterNum 10)
					(if (> (ego z?) 70)
						(theIconBar enable: 3)
					else
						(theIconBar disable: 3)
					)
				)
			)
			(local57
				(if
					(and
						(<= 4 (ego x?))
						(<= (ego x?) 316)
						(<= 48 (ego y?))
						(<= (ego y?) 185)
					)
					(= local57 0)
					(HandsOn)
				)
			)
			((<= (ego x?) 3)
				(cond 
					(
					(and (== monsterNum 6) (Btst 102) (not (Btst 105)))
						(= local57 1)
						(messager say: 3 6 20)
						(ego setMotion: PolyPath 20 (ego y?))
					)
					((and (== monsterNum 9) (Btst 105))
						(= local57 1)
						(messager say: 5 6 101)
						(ego setMotion: PolyPath 20 (ego y?))
					)
					(else
						(= local24 1)
						(if (not local38)
							(= theGGOwnerX_4 -20)
							(= theGGOwnerY_4 (ego y?))
							(self setScript: sExit)
						else
							(self setScript: showNewRoom self 4)
						)
					)
				)
			)
			((>= (ego y?) 187)
				(cond 
					(
					(and (== monsterNum 6) (Btst 102) (not (Btst 105)))
						(= local57 1)
						(messager say: 3 6 20)
						(ego setMotion: PolyPath (ego x?) 170)
					)
					((and (== monsterNum 9) (Btst 105))
						(= local57 1)
						(messager say: 5 6 101)
						(ego setMotion: PolyPath (ego x?) 170)
					)
					(else
						(= local24 1)
						(if (not local38)
							(= theGGOwnerX_4 (ego x?))
							(= theGGOwnerY_4 250)
							(self setScript: sExit)
						else
							(self setScript: showNewRoom self 3)
						)
					)
				)
			)
			((>= (ego x?) 317)
				(cond 
					(
					(and (== monsterNum 6) (Btst 102) (not (Btst 105)))
						(= local57 1)
						(messager say: 3 6 20)
						(ego setMotion: PolyPath 300 (ego y?))
					)
					((and (== monsterNum 9) (Btst 105))
						(= local57 1)
						(messager say: 5 6 101)
						(ego setMotion: PolyPath 300 (ego y?))
					)
					(else
						(= local24 1)
						(if (not local38)
							(= theGGOwnerX_4 330)
							(= theGGOwnerY_4 (ego y?))
							(self setScript: sExit)
						else
							(self setScript: showNewRoom self 2)
						)
					)
				)
			)
			((<= (ego y?) 45)
				(cond 
					(
					(and (== monsterNum 6) (Btst 102) (not (Btst 105)))
						(= local57 1)
						(messager say: 3 6 20)
						(ego setMotion: PolyPath (ego x?) (+ (ego y?) 10))
					)
					((and (== monsterNum 9) (Btst 105))
						(= local57 1)
						(messager say: 5 6 101)
						(ego setMotion: PolyPath (ego x?) (+ (ego y?) 10))
					)
					(else
						(= local24 1)
						(if (not local38)
							(= theGGOwnerX_4 (ego x?))
							(= theGGOwnerY_4 vanishingY)
							(self setScript: sExit)
						else
							(self setScript: showNewRoom self 1)
						)
					)
				)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(if timer (timer dispose: delete:))
		(= global461 0)
		(= global462 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(65
					(if local38
						(messager say: 0 0 93)
					else
						((ScriptID 7 5) init: global455)
					)
				)
				(74
					(if (and local30 (not local38))
						(= monsterNum 74)
						(= local17 1)
						(self setScript: sleepScript 0 0)
					else
						(messager say: 4 6 16)
					)
				)
				(20
					(++ global426)
					(if (OneOf monsterNum 580 8 9) (ego setScale:))
					(ego setScript: (ScriptID 32 0) self 20)
				)
				(81
					(if (ego castSpell: 25)
						(if (OneOf monsterNum 580 8 9) (ego setScale:))
						(ego setScript: (ScriptID 32 0) self 81)
					)
				)
				(-77
					(messager say: 0 0 2 1 0 12)
				)
				(-76
					(messager say: 0 0 1 1 0 12)
				)
				(-80
					(messager say: 0 0 4 1 0 12)
				)
				(83
					(if (ego castSpell: 27)
						(if (OneOf monsterNum 580 8 9) (ego setScale:))
						(ego setScript: (ScriptID 32 0) self 83)
					)
				)
				(88
					(if (ego castSpell: 32)
						(if (OneOf monsterNum 580 8 9) (ego setScale:))
						(ego setScript: (ScriptID 32 0) self 88)
					)
				)
				(80
					(if (ego castSpell: 24)
						(= local2 1)
						((Timer new:) setReal: self (/ [egoStats 24] 10))
						(ego setScript: (ScriptID 12 0) self 80)
					)
				)
				(75
					(if (ego castSpell: 19)
						(AutoTarget
							((User curEvent?) x?)
							((User curEvent?) y?)
						)
						(if (and (== monsterNum 8) (not (Btst 109)))
							(ego setScript: (ScriptID 13) 0 (ScriptID 701 6))
						else
							(ego setScript: (ScriptID 13))
						)
					)
				)
				(77
					(if (ego castSpell: 21)
						(if (== (genericProp noun?) 7)
							(ego setScript: (ScriptID 12 0) genericProp)
						else
							(super doVerb: theVerb &rest)
						)
					)
				)
				(84
					(if (ego castSpell: 28)
						((ScriptID 31 0) init: (ego x?) (+ (ego y?) 1) 80)
					)
				)
				(82
					(if (ego castSpell: 26)
						(cond 
							((== monsterNum 10)
								(if (not script)
									(self setScript: (ScriptID 37 0) 0 genericProp)
								)
							)
							((not script) (self setScript: (ScriptID 37 0)))
						)
					)
					(return 1)
				)
				(86
					(cond 
						(local38 (messager say: 4 6 99))
						((ego castSpell: 30) (ego setScript: (ScriptID 62 0)))
					)
				)
				(87
					(if (ego castSpell: 31)
						(ego setScript: (ScriptID 46 0))
					)
				)
				(78
					(if (ego castSpell: 22)
						((Timer new:) setReal: self (/ [egoStats 22] 10))
						(ego setScript: (ScriptID 12 0) self 78)
					)
				)
				(33
					(if (OneOf monsterNum 580 8 9) (ego setScale:))
					(ego setScript: (ScriptID 32 0) self 33)
				)
				(85
					(if (ego castSpell: 29)
						(= reversalTimer [egoStats 29])
						(sFx number: 943 setLoop: 1 play:)
						(ego setScript: (ScriptID 12 0) self 85)
					)
				)
				(4
					(if (> ((User curEvent?) y?) 100)
						(ego setScript: getRocks)
					else
						(super doVerb: theVerb)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (cue &tmp temp0)
		(if (== monsterNum 580)
			(ego setScale: Scaler 120 59 189 47)
		else
			(super cue:)
		)
	)
)

(instance monkeysWithEgo of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(self setScript: (ScriptID 701 0) self)
			)
			(1
				(curRoom setScript: encounterScript)
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
				(messager say: 4 6 96)
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

(instance doBattle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 2)
				(if (IsObject (ego looper?)) ((ego looper?) dispose:))
				(ego looper: 0 setMotion: 0)
			)
			(1
				(messager say: 4 6 51)
				(= cycles 2)
			)
			(2
				(if (< monsterHealth 2) (= monsterHealth 2))
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance getRocks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
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
				(messager say: 4 6 89 0 self)
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

(instance encounterScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(ego normalize:)
				(HandsOn)
				(switch monsterNum
					(595
						(= local63 100)
						(if (not monsterHealth) (= monsterHealth 180))
						(= global462 800)
					)
					(565
						(= local63 40)
						(if (not monsterHealth) (= monsterHealth 150))
						(= global462 600)
					)
					(585
						(= local63 30)
						(if (not monsterHealth) (= monsterHealth 200))
						(= global462 700)
					)
					(580
						(= local63 30)
						(if (not monsterHealth) (= monsterHealth 150))
						(= global462 700)
					)
					(575
						(= local63 30)
						(if (not monsterHealth) (= monsterHealth 180))
						(= global462 700)
					)
					(590
						(= local63 50)
						(if (not monsterHealth) (= monsterHealth 200))
						(= global462 700)
					)
				)
				(self cue:)
			)
			(2
				(= local30 0)
				(if local3
					(if (== machineSpeed 0)
						(it signal: (& (it signal?) $bfff))
						(= local6 200)
					)
					(it x: 300 y: 150 init:)
					(ego setMotion: PolyPath -10 (ego y?))
				else
					(= local6 50)
					(if (and (Btst 150) (not local0))
						(messager say: 4 6 96)
					)
					(= temp0 (Random 0 3))
					(it x: [local49 temp0] y: [local53 temp0] init:)
				)
				(= local38 1)
				(cSound setLoop: -1 number: 700 play:)
				(self dispose:)
			)
		)
	)
)

(instance genericProp of Prop
	(properties)
	
	(method (init)
		(= signal (| signal $1000))
		(if (== monsterNum 5)
			(= view 700)
			(= cel 0)
			(= loop (Random 9 10))
			(= x 180)
			(= y 147)
			(= noun 7)
			(= approachX x)
			(= approachY 155)
		else
			(= view 700)
			(= loop 8)
			(= cel (Random 0 3))
			(= x 181)
			(= y 166)
			(= noun 6)
			(= local35 (Random 1 local36))
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((== monsterNum 10)
						(if
						(and (>= (ego z?) 75) (ego inRect: 70 170 110 190))
							(if (IsObject newActor)
								(newActor dispose:)
								(Bclr 132)
								(ego get: 39 solvePuzzle: 317 3 2)
								(messager say: 4 6 12)
								(Bset 116)
							else
								(messager say: 4 6 13)
							)
						else
							(messager say: 4 6 14)
						)
					)
					((== noun 6) (messager say: 6 4 79))
					((== cel 0)
						(self setCycle: End self)
						(= local4 1)
						(globalSound number: 401 setLoop: 1 play:)
						(ego addHonor: 10)
					)
					(else (messager say: 7 6 81))
				)
			)
			(1
				(cond 
					((== monsterNum 10) (super doVerb: theVerb &rest))
					((== monsterNum 4) (messager say: 6 1 80 local35))
					((== cel 0) (messager say: 7 6 82))
					(else (messager say: 7 1 81))
				)
			)
			(-82
				(if (== monsterNum 10)
					(if (IsObject newActor)
						(newActor dispose:)
						(ego solvePuzzle: 317 3 get: 39)
						(messager say: 4 6 15)
						(Bset 116)
					else
						(messager say: 4 6 13)
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
	
	(method (cue)
		(if (== monsterNum 5)
			(if (or (not local4) local5)
				(self doVerb: 4)
			else
				(= local5 1)
				(messager say: 7 6 83)
			)
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
				(ego
					view: 43
					loop: (Random 0 3)
					cel: 0
					cycleSpeed: 10
					setMotion: 0
					setCycle: End self
				)
			)
			(1
				(switch monsterNum
					(595
						(EgoDead 1 700 633 End 158)
					)
					(590
						(if (Btst 96)
							(EgoDead 2 700 707 End 158)
						else
							(EgoDead 84 700 707 End 158)
						)
					)
					(565
						(EgoDead 3 700 707 End 152)
					)
					(580
						(EgoDead 5 700 937 End 152)
					)
					(585
						(EgoDead 6 700 455 End 155)
					)
					(575
						(EgoDead 4 700 937 End 152)
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance monsterIsDead of Script
	(properties)
	
	(method (doit)
		(if (it z?) (it z: (- (it z?) 1)))
		(super doit:)
	)
	
	(method (changeState newState &tmp temp0 [temp1 13] temp14)
		(switch (= state newState)
			(0
				(HandsOff)
				(it
					approachVerbs: 4
					approachX: (/ (+ (it nsLeft?) (it nsRight?)) 2)
					approachY: (+ (/ (+ (it nsTop?) (it nsBottom?)) 2) 5)
				)
				(= local30 1)
				(it setMotion: 0 init:)
				(= local38 0)
				(if (== monsterNum 590)
					(= cycles 5)
				else
					(= temp0 (if (Random 0 1) -10 else 10))
					(Face it (it x?) (+ (it y?) temp0) self)
				)
			)
			(1
				(if (!= monsterNum 590)
					(it
						loop: (if (< temp0 0) 1 else 0)
						cel: 0
						setMotion: 0
						init:
						setCycle: End
					)
				else
					(it setCycle: Beg)
				)
				(globalSound setLoop: 1 number: 931 play:)
				(switch monsterNum
					(565
						(cSound setLoop: 1 changeTo: 153 self)
					)
					(580
						(cSound setLoop: 1 changeTo: 153 self)
					)
					(575
						(cSound setLoop: 1 changeTo: 153 self)
					)
					(590
						(cSound setLoop: 1 changeTo: 156 self)
					)
					(595
						(cSound setLoop: 1 changeTo: 159 self)
					)
					(585
						(cSound setLoop: 1 changeTo: 156 self)
					)
				)
			)
			(2
				(cond 
					((Btst 96) (Bclr 96) (client setScript: (ScriptID 701 1) 0 0 1))
					((== monsterNum 590) (HandsOn) (it dispose:))
				)
				(= cycles 6)
			)
			(3
				(it
					approachVerbs: 4
					approachX: (/ (+ (it nsLeft?) (it nsRight?)) 2)
					approachY: (+ (/ (+ (it nsTop?) (it nsBottom?)) 2) 5)
				)
				(= [temp1 0] (- (it nsLeft?) 3))
				(= [temp1 1] (/ (+ (it nsTop?) (it nsBottom?)) 2))
				(= [temp1 2] (+ 3 (it nsRight?)))
				(= [temp1 3] (/ (+ (it nsTop?) (it nsBottom?)) 2))
				(= [temp1 4] (+ 3 (it nsRight?)))
				(= [temp1 5] (+ 3 (it nsBottom?)))
				(= [temp1 6] (- (it nsLeft?) 3))
				(= [temp1 7] (+ 3 (it nsBottom?)))
				(= [temp1 8] 30583)
				(= [temp1 9] 0)
				(if
					(= temp14
						(MergePoly
							@temp1
							((curRoom obstacles?) elements?)
							((curRoom obstacles?) size?)
						)
					)
					((curRoom obstacles?)
						add:
							((Polygon new:)
								points: temp14
								size: (localproc_3e94 temp14)
								type: 2
								dynamic: 1
								yourself:
							)
					)
				else
					((curRoom obstacles?)
						add:
							((Polygon new:)
								points: @temp1
								size: 4
								type: 2
								dynamic: 1
								yourself:
							)
					)
				)
				(= local13 1)
				(HandsOn)
				(cSound number: 400 setLoop: -1 play:)
				(self dispose:)
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
				(if register (ego get: 0 register))
			)
			(1
				(ego view: 4 cel: 0 setCycle: End self)
			)
			(2 (= cycles 10))
			(3
				(if register
					(Message msgGET 700 4 6 18 1 @temp0)
					(messager sayFormat: 99 @temp0 register)
					(= cycles 1)
				else
					(messager say: 4 6 19 0 self)
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

(instance sleepScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 theClock temp2 temp3)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 2)
				(= local0 0)
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
					(PalVary pvINIT 700 3)
				)
				(= seconds 5)
			)
			(4
				(if (not local13)
					(if local15 (= temp3 (Random 0 3)) else (= temp3 0))
					(if (not temp3)
						(cond 
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
					(74 (= seconds 10))
					(else 
						(= local15 0)
						(= local37 0)
						(if (Btst 150)
							(= local0 1)
							(self setScript: paladinHearsMonster self)
						else
							(= seconds 2)
						)
					)
				)
			)
			(6
				(cond 
					((== monsterNum 74) (PalVary pvREVERSE 3) (= seconds 4))
					(local0 (= seconds 3))
					(else (ego setCycle: Beg self))
				)
			)
			(7
				(if (!= monsterNum 74)
					(if local0
						(= local13 1)
						(client setScript: encounterScript)
						(self dispose:)
					else
						(= local13 1)
						(ego x: 165 y: 135)
						(client setScript: encounterScript)
						(self dispose:)
					)
				else
					(self cue:)
				)
			)
			(8
				((ScriptID 7 7) init: 5 40)
				(= cycles 10)
			)
			(9 (ego setCycle: Beg self))
			(10
				(ego x: 165 y: 135 normalize:)
				(= cycles 10)
			)
			(11
				(cSound setLoop: -1 number: 400 play:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance castFire of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((User curEvent?) x: 146 y: 160)
				(self setScript: (ScriptID 32 0) self 81)
			)
			(1
				(HandsOn)
				(ego normalize:)
				(= local15 1)
				((= newProp (Prop new:))
					signal: 20496
					view: 700
					loop: 6
					cel: 0
					x: 144
					y: 149
					priority: 13
					setScript: loopSound
					init:
					setCycle: Fwd
				)
				(self dispose:)
			)
		)
	)
)

(instance justLayDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 165 135 self)
			)
			(1
				(ego
					x: 140
					y: 135
					view: 35
					loop: 1
					cel: 0
					setCycle: End self
				)
			)
			(2 (self dispose:))
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
				(globalSound number: 913 setLoop: 10000 play: self)
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
				(if local15 (self cue:) else (= seconds 5))
			)
			(2
				(if local15
					(= local15 0)
					(newProp setScript: 0 dispose:)
					(messager say: 93 48 0)
					(= newProp 0)
				else
					(= local15 1)
					((= newProp (Prop new:))
						view: 700
						loop: 6
						cel: 0
						x: 144
						y: 149
						priority: 13
						signal: 20496
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

(instance throwSpell of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(cond 
					(
						(and
							(< global432 global373)
							(< global432 global380)
							(< global432 global375)
						)
						(= local27 1)
						(= local63 15)
						(it setMotion: MChase ego local63 it)
						(self dispose:)
					)
					((and local28 (< global432 115))
						(it setMotion: PolyPath -50 -50 it setAvoider: PAvoider)
						(= local38 0)
						(self dispose:)
					)
					(else (it setMotion: 0) (Face it ego self))
				)
			)
			(1
				(= register (it loop?))
				(it
					view: 706
					cel: 0
					loop:
						(cond 
							((< (ego x?) (it x?)) (if (< (ego y?) (it y?)) 3 else 1))
							((< (ego y?) (it y?)) 2)
							(else 0)
						)
					setCycle: End self
				)
			)
			(2
				((leopardProject new:) init:)
				(= cycles 5)
			)
			(3 (it setCycle: Beg self))
			(4
				(it view: 590 loop: register setCycle: Walk)
				(= temp0
					(switch arcadeDifficulty
						(1 240)
						(2 120)
						(3 70)
					)
				)
				(= cycles (Random 20 temp0))
			)
			(5
				(it setMotion: MChase ego local63 it)
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
				(ego signal: 8194)
				(if (and (== monsterNum 8) (not (Btst 109)))
					(if (Btst 110)
						(if (Btst 111)
							(EgoDead 8)
						else
							(Bset 111)
							(ego addHonor: -100)
						)
					else
						(Bset 110)
						(ego addHonor: -100)
					)
				)
				(self cue:)
			)
			(1
				(if (and global426 (not local33))
					(messager say: 0 20 86 0 self)
					(ego get: 10 global426)
				else
					(self cue:)
				)
			)
			(2
				(if local15 (messager say: 4 6 98))
				(ego setMotion: PolyPath theGGOwnerX_4 theGGOwnerY_4 self)
			)
			(3
				(curRoom newRoom: prevRoomNum)
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
		(if local30 (self approachVerbs: 4))
		(switch monsterNum
			(595
				(if local30
					(self view: 592 setScale: -1 ego cycleSpeed: 6 noun: 82)
				else
					(self
						origStep: 1542
						view: 590
						setScale: -1 ego
						moveSpeed: (+ (ego moveSpeed?) 4)
						cycleSpeed: (+ (ego cycleSpeed?) 4)
						setCycle: Walk
						noun: 83
						setMotion: MChase ego local63 self
					)
				)
			)
			(590
				(if local30
					(self
						view: 585
						setScale: -1 ego
						cycleSpeed: (+ (ego cycleSpeed?) 4)
					)
				else
					(self
						origStep: 1540
						view: 585
						setScale: -1 ego
						moveSpeed: (+ (ego moveSpeed?) 3)
						cycleSpeed: (+ (ego cycleSpeed?) 5)
						setCycle: Fwd
						setCel: 0
						setLoop: 2
						noun: 84
						setMotion: MChase ego local63 self
					)
				)
			)
			(565
				(if local30
					(self view: 563 setScale: -1 ego cycleSpeed: 6 noun: 85)
				else
					(self
						origStep: 1542
						view: 561
						setScale: -1 ego
						moveSpeed: (+ (ego moveSpeed?) 6)
						cycleSpeed: (+ (ego cycleSpeed?) 7)
						setCycle: Walk
						noun: 86
						setMotion: MChase ego local63 self
					)
				)
			)
			(580
				(if local30
					(self
						view: 577
						z: 20
						setScale: -1 ego
						cycleSpeed: 6
						noun: 87
					)
				else
					(self
						origStep: 1542
						view: 575
						setScale: -1 ego
						moveSpeed: (+ (ego moveSpeed?) 3)
						cycleSpeed: (ego cycleSpeed?)
						setCycle: Walk
						z: 20
						noun: 88
						setMotion: MChase ego local63 self
					)
				)
			)
			(585
				(if local30
					(self view: 582 setScale: -1 ego cycleSpeed: 6 noun: 89)
				else
					(self
						origStep: 1542
						view: 580
						setScale: -1 ego
						moveSpeed: (+ (ego moveSpeed?) 4)
						cycleSpeed: (+ (ego cycleSpeed?) 5)
						setCycle: Walk
						noun: 90
						setMotion: MChase ego local63 self
					)
				)
			)
			(575
				(if local30
					(self view: 572 setScale: -1 ego cycleSpeed: 6 noun: 91)
				else
					(self
						origStep: 1542
						view: 570
						setScale: -1 ego
						moveSpeed: (+ (ego moveSpeed?) 4)
						cycleSpeed: (+ (ego cycleSpeed?) 5)
						setCycle: Walk
						noun: 92
						setMotion: MChase ego local63 self
					)
				)
			)
			(else  (self dispose:))
		)
	)
	
	(method (doit &tmp temp0 theBrLeft theBrRight temp3 temp4 temp5 temp6)
		(if
			(and
				local2
				local1
				(curRoom timer?)
				(not (ego script?))
			)
			(= local2 0)
			((curRoom timer?) dispose:)
			(curRoom timer: 0)
			(messager say: 0 6 87)
		)
		(if script (script doit:))
		(return
			(if (not local6)
				(= signal (| signal $4000))
				(if (== monsterNum 590)
					(cond 
						(mover
							(if (not (& signal $0008))
								(if (== local62 7)
									(= local62 0)
									(= signal (| signal $0030))
									(= priority 0)
								else
									(++ local62)
									(= signal (& signal $ffdf))
								)
							)
							(if (not (Random 0 50)) (ShakeScreen 1))
						)
						((and (or (== loop 1) (== loop 0)) cycler) (= signal (& signal $ffcf)) (ShakeScreen 1))
					)
				)
				(if (not (mod Clock 10))
					(if
						(and
							(localproc_3ebe self)
							(not local25)
							(== egoGait 2)
						)
						(= local25 1)
						(if (>= (Random 0 [egoStats 8]) 100)
							(= global461 10000)
							(self cue:)
						)
					)
				else
					(= local25 0)
				)
				(cond 
					((and mover (curRoom timer?))
						(if (== monsterNum 590)
							((curRoom timer?) dispose: delete:)
						else
							(self setMotion: 0)
							(= local31
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
							(curRoom addObstacle: local31)
						)
					)
					(
					(and (== monsterNum 590) cycler (curRoom timer?))
						(self setCycle: 0)
						(if (IsObject newProp_2) (newProp_2 setCycle: 0))
						(= local31
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
						(curRoom addObstacle: local31)
					)
					(
						(and
							(== monsterNum 590)
							(not local30)
							(not mover)
							(not (curRoom timer?))
							(not cycler)
						)
						(if (and (not local12) (not local26))
							(cond 
								(
								(<= (GetDistance x y (ego x?) (ego y?)) local63)
									(self setCycle: End self)
									(if (IsObject newProp_2) (newProp_2 setCycle: End))
								)
								((!= cel 0)
									(self setCycle: Beg self)
									(if (IsObject newProp_2) (newProp_2 setCycle: Beg))
								)
							)
						)
					)
					(
					(and (not local30) (not mover) (not (curRoom timer?)))
						(if ((curRoom obstacles?) contains: local31)
							((curRoom obstacles?) delete: local31)
							(local31 dispose:)
						)
						(if (and (not script) (!= monsterNum 590))
							(self setMotion: MChase ego local63 self)
						)
					)
				)
				(if
					(and
						(not local30)
						(== monsterNum 595)
						(not (curRoom timer?))
						(not local26)
						(<= (GetDistance x y (ego x?) (ego y?)) 15)
					)
					(= global461 0)
					(self cue:)
				)
				(if (<= y 45)
					(= signal (| signal $0008))
				else
					(= signal (& signal $fff7))
				)
				(if (& signal $8000) (return (& signal $8000)))
				(if (and (& signal $0004) (not (& signal $0002)))
					(return (not (& signal $0002)))
				)
				(if scaler (scaler doit:))
				(if (& scaleSignal $0001)
					(= temp5 (>> origStep $0008))
					(= temp6 (& origStep $00ff))
					(if (< y (curRoom vanishingY?))
						(= temp3 (/ (- (curRoom vanishingY?) y) temp5))
						(= temp4 (/ (- (curRoom vanishingY?) y) temp6))
					else
						(= temp3 (/ (* temp5 scaleX) 128))
						(= temp4 (/ (* temp6 scaleY) 128))
					)
					(cond 
						((> temp3 temp5) (= temp3 temp5))
						((< temp3 1) (= temp3 1))
					)
					(cond 
						((> temp4 temp6) (= temp4 temp6))
						((< temp4 1) (= temp4 1))
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
				)
				(= brTop (- y 1))
				(= brLeft (- x 1))
				(= brRight (+ x 1))
				(= brBottom (+ y 1))
				(= xLast x)
				(= yLast y)
			else
				(if (== local6 1)
					(switch monsterNum
						(565
							(sFx number: 904 setLoop: 1 play:)
						)
						(585
							(sFx number: 907 setLoop: 1 play:)
						)
						(595
							(sFx number: 909 setLoop: 1 play:)
						)
						(580
							(sFx number: 909 setLoop: 1 play:)
						)
					)
				)
				(-- local6)
			)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if local38
						(super doVerb: theVerb &rest)
					else
						(switch monsterNum
							(595
								(self
									setScript: searchMonster 0 (if local32 0 else (Random 2 10))
								)
							)
							(565
								(self setScript: searchMonster 0 0)
							)
							(580
								(self setScript: searchMonster 0 0)
							)
							(585
								(self
									setScript: searchMonster 0 (if local32 0 else (Random 2 10))
								)
							)
							(575
								(self
									setScript: searchMonster 0 (if local32 0 else (Random 2 10))
								)
							)
						)
						(= local32 1)
						(return 1)
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
		(if (< global461 global462)
			(cond 
				(
					(and
						(>= global461 15)
						(== monsterNum 595)
						(not local27)
					)
					(if (not (curRoom timer?))
						(self setScript: throwSpell)
					)
				)
				(
				(and (== monsterNum 590) (== loop 2) (not local12))
					(self
						setCel:
						setLoop: (if (> x (ego x?)) 0 else 1)
						setCycle: End self
						setMotion: 0
					)
					((= newProp_2 (Prop new:))
						view: 585
						loop: 3
						cel: 0
						setScale: -1 self
						x: x
						y: y
						signal: 16400
						priority: priority
						cycleSpeed: cycleSpeed
						setCycle: End
						init:
					)
				)
				(
				(and (== monsterNum 590) (== cel 0) (not local12))
					(self
						setCel: 0
						setLoop: 2
						setCycle: Fwd
						setMotion: MChase ego local63 self
					)
					(if (IsObject newProp_2)
						(newProp_2 dispose:)
						(= newProp_2 0)
					)
				)
				(
					(and
						(== monsterNum 590)
						(> (GetDistance x y (ego x?) (ego y?)) local63)
						(not local12)
					)
					(self setCycle: Beg self)
					(if (IsObject newProp_2) (newProp_2 setCycle: Beg))
				)
				((<= (ego z?) local63)
					(= local38 0)
					(ego setMotion: 0)
					(self setMotion: 0)
					(= global430 local41)
					(= gGOwnerX_4 theGGOwnerX_4)
					(= gGOwnerY_4 theGGOwnerY_4)
					(= gGGClientModNum_2_2 prevRoomNum)
					(if local44
						(= global431 (& global431 $fffe))
					else
						(= global431 (^ global431 $0001))
					)
					(if local28
						(= global431 (& global431 $fffd))
					else
						(= global431 (^ global431 $0002))
					)
					(if (not local26)
						(= local26 1)
						(= local6 10000)
						(self setScript: doBattle)
					)
				)
				((not local12)
					(= local12 1)
					(self setCycle: 0 setMotion: MChase ego local63 self)
				)
			)
		else
			(self setMotion: 0)
			(= local15 0)
			(messager say: 4 6 90)
			(ego setMotion: 0)
			(= local38 0)
			(= local30 1)
			(= local13 1)
			(= local33 1)
			(if (Btst 96)
				(Bclr 96)
				(curRoom setScript: (ScriptID 701 1) 0 0 0)
			else
				(curRoom setScript: 0)
				(HandsOn)
			)
			(cSound setLoop: -1 changeTo: 400)
			(self dispose:)
		)
	)
	
	(method (cantBeHere)
		(return 0)
	)
	
	(method (getHurt param1 param2)
		(= local1 1)
		(if (curRoom timer?)
			((curRoom timer?) dispose: delete:)
		)
		(if (not local30)
			(switch monsterNum
				(595
					(if (== param1 -1)
						(if local29 0 else (= local29 1) (messager say: 4 6 94))
					else
						(= local6 10)
						(= monsterHealth (- monsterHealth param2))
					)
				)
				(590
					(if (!= cel 0)
						(= local6 10)
						(= monsterHealth (- monsterHealth param2))
					)
				)
				(565
					(= local6 10)
					(= monsterHealth (- monsterHealth param2))
				)
				(580
					(= local6 10)
					(= monsterHealth (- monsterHealth param2))
				)
				(585
					(= local6 10)
					(= monsterHealth (- monsterHealth param2))
				)
				(575
					(= local6 10)
					(= monsterHealth (- monsterHealth param2))
				)
			)
			(if (and (< monsterHealth 1) (!= script doBattle))
				(= local26 0)
				(self setMotion: 0)
				(self setScript: monsterIsDead)
			)
		)
	)
)

(instance fireWood of View
	(properties
		x 146
		y 160
		noun 80
		view 700
		loop 7
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
				(if (not local15)
					(= noun 93)
					(curRoom setScript: castFire)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(19
				(if (not local15)
					(= noun 93)
					(curRoom setScript: kindleFire)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(48
				(if local15
					(= noun 80)
					(ego drop: 37 1)
					(ego get: 15 1)
					(curRoom setScript: kindleFire)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(4
				(if local15
					(= noun 80)
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

(instance showNewRoom of Script
	(properties)
	
	(method (changeState newState &tmp egoX egoY)
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
						(= egoY 230)
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
				(= local57 1)
				(it setMotion: 0)
				(ego setMotion: 0)
				(= cycles 2)
			)
			(3
				(HandsOff)
				(switch register
					(4
						(-- theGGOwnerX_4)
						(= egoX 0)
						(it x: (+ 330 (- (it x?) (ego x?))))
						(ego x: 325 y: 155)
						(= egoY (ego y?))
					)
					(1
						(-- theGGOwnerY_4)
						(= egoX (ego x?))
						(= egoY 0)
						(it y: (+ 230 (- (it y?) (ego y?))))
						(ego y: 205 x: 160)
					)
					(3
						(++ theGGOwnerY_4)
						(= egoY 190)
						(it y: (+ 45 (- (it y?) (ego y?))))
						(ego y: 45 x: 160)
						(= egoX (ego x?))
					)
					(2
						(++ theGGOwnerX_4)
						(= egoX 320)
						(= egoY (ego y?))
						(it x: (+ -15 (- (it x?) (ego x?))))
						(ego x: -5 y: 155)
					)
				)
				(localproc_3a24)
				(ego
					init:
					normalize:
					signal: 2
					setScale: Scaler 120 59 189 47
					setMotion: PolyPath egoX egoY
				)
				(= cycles 2)
			)
			(4
				(if (!= monsterNum 6) (it init:))
				(HandsOn)
				(= cycles 2)
			)
			(5
				(if (== monsterNum 6)
					((ScriptID 702 0) init:)
					(++ local22)
					(= seconds (+ (ego moveSpeed?) 1))
				else
					(= local24 0)
					(self dispose:)
				)
			)
			(6
				(switch local22
					(1
						(if (& global431 $4000)
							(messager say: 4 6 58)
						else
							(messager say: 4 6 62)
						)
					)
					(2
						(if (& global431 $4000)
							(messager say: 4 6 61)
							(= local38 0)
						else
							(messager say: 4 6 57)
						)
					)
					(3
						(messager say: 4 6 59)
						(= local38 0)
					)
				)
				(self dispose:)
			)
		)
	)
)
