;;; Sierra Script 1.0 - (do not remove this comment)
(script# 430)
(include sci.sh)
(use Main)
(use TellerIcon)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm430 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5 =  160
	local6 =  130
	local7
	local8
	local9
	local10
	local11
	local12
	local13
	[local14 12] = [0 50 -30 5 -53 -51 4 -31 -20 -15 -23 999]
	[local26 2]
	[local28 6] = [0 -61 -54 -12 -58 999]
	[local34 4] = [0 -3 -24 999]
	[local38 3] = [0 -62 999]
	[local41 3] = [0 2 999]
	[local44 4] = [0 -60 -7 999]
	[local48 3] = [0 6 999]
	[local51 3] = [0 -56 999]
	[local54 3] = [0 9 999]
	[local57 3] = [0 -16 999]
	[local60 3] = [0 -17 999]
	[local63 4] = [0 -59 -18 999]
	[local67 3] = [0 -21 999]
	[local70 3] = [0 19 999]
	[local73 3] = [0 22 999]
	[local76 3] = [0 -57 999]
	[local79 3] = [0 14 999]
	[local82 5] = [0 -26 29 -25 999]
	[local87 3] = [0 -27 999]
	[local90 3] = [0 28 999]
	[local93 20] = [0 -61 -54 -12 -58 -3 -24 -62 -60 -7 -56 -16 -17 -59 -18 -21 -57 -26 -27 999]
	[local113 20]
	[local133 7] = [0 -34 -3 -37 -38 -19 999]
	[local140 4] = [0 -35 -6 999]
	[local144 3] = [0 -36 999]
	[local147 4] = [0 -34 -6 999]
	[local151 4]
)
(procedure (localproc_1ad8 param1)
	(return (* 2 (/ (+ (* param1 800) (- local3 1)) local3)))
)

(procedure (localproc_1aec)
	(return
		(if
			(and
				(not (cast contains: (ScriptID 35 1)))
				(not local7)
			)
			(not local9)
		else
			0
		)
	)
)

(instance rm430 of Rm
	(properties
		noun 4
		picture 430
		vanishingY -300
	)
	
	(method (init)
		(HandsOff)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						263
						117
						219
						117
						172
						106
						175
						98
						141
						98
						131
						91
						64
						91
						64
						153
						101
						139
						116
						128
						168
						128
						199
						128
						199
						165
						183
						189
						263
						189
					yourself:
				)
		)
		(mat approachVerbs: 4 init:)
		(cSound setLoop: -1 changeTo: 430)
		(soundFx number: 913 setLoop: -1 play:)
		(fire setCycle: Fwd init:)
		(shield init:)
		(zebra init:)
		(leftbed init:)
		(rightbed init:)
		(wall init:)
		(fireplace init:)
		(basket init:)
		(log init:)
		(= [local26 0] @local14)
		(egoActions init: ego @local14 @local26)
		(if (not (== prevRoomNum 440))
			(walkHandler addToFront: curRoom)
		)
		(cond 
			((== prevRoomNum 440) (curRoom setScript: firstTime))
			((Btst 118) (self style: 8) (curRoom setScript: afterStory))
			(else (curRoom setScript: enterRoom))
		)
		(chestLid approachVerbs: 4 58 init: stopUpd:)
		(chest approachVerbs: 4 58 init:)
		(super init:)
	)
	
	(method (doit)
		(cond 
			((and (GameIsRestarting) (== (ego view?) 40)) (= local0 1))
			((curRoom script?) 0)
			((and (ego mover?) (== (ego view?) 40))
				(if (IsObject (ego looper?)) ((ego looper?) dispose:))
				(ego setMotion: 0 setScript: egoGetUp)
			)
			((and (> (ego y?) 155) (> (ego x?) 170)) (curRoom setScript: exitRoom))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(globalSound stop:)
		(soundFx stop:)
		(walkHandler delete: curRoom)
		(LoadMany 0 29 34 35)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(74
					(if (cast contains: (ScriptID 34 1))
						(self setScript: uhuraGoodNight 0 0)
					else
						(self setScript: goToBed)
					)
				)
				(3 (egoActions doVerb: 3))
				(84
					(if (localproc_1aec)
						(ego addHonor: -5)
						(if (ego castSpell: 28)
							((ScriptID 31 0) init: (ego x?) (+ (ego y?) 1) 20)
						)
					else
						(messager say: 9 6 45 0 0)
					)
				)
				(82
					(if (localproc_1aec)
						(ego addHonor: -5)
						(if (ego castSpell: 26)
							(self setScript: (ScriptID 37 0))
							(return 1)
						)
					else
						(messager say: 9 6 45 0 0)
					)
				)
				(81
					(if (localproc_1aec)
						(ego addHonor: -5)
						(if (ego castSpell: 25)
							(self setScript: (ScriptID 32 0) self 81)
						)
					else
						(messager say: 9 6 45 0 0)
					)
				)
				(83
					(if (localproc_1aec)
						(ego addHonor: -5)
						(if (ego castSpell: 27)
							(self setScript: (ScriptID 32 0) self 83)
						)
					else
						(messager say: 9 6 45 0 0)
					)
				)
				(75
					(if (localproc_1aec)
						(ego addHonor: -5)
						(if (ego castSpell: 19)
							(AutoTarget
								((User curEvent?) x?)
								((User curEvent?) y?)
							)
							(ego setScript: (ScriptID 13) 0 chest)
						)
					else
						(messager say: 9 6 45 0 0)
					)
				)
				(88
					(if (localproc_1aec)
						(ego addHonor: -5)
						(if (ego castSpell: 32)
							(self setScript: (ScriptID 32 0) self 88)
						)
					else
						(messager say: 9 6 45 0 0)
					)
				)
				(80
					(if (localproc_1aec)
						(ego addHonor: -5)
						(if (ego castSpell: 24)
							(ego setScript: (ScriptID 12 0) 0 80)
							(super doVerb: 80)
						)
					else
						(messager say: 9 6 45 0 0)
					)
				)
				(86
					(if (localproc_1aec)
						(ego addHonor: -5)
						(if (ego castSpell: 30)
							(ego setScript: (ScriptID 62 0))
						)
					else
						(messager say: 9 6 45 0 0)
					)
				)
				(78
					(if (localproc_1aec)
						(ego addHonor: -5)
						(if (ego castSpell: 22)
							(ego setScript: (ScriptID 12 0) 0 78)
						)
					else
						(messager say: 9 6 45 0 0)
					)
				)
				(76
					(if (localproc_1aec)
						(ego addHonor: -5)
						(if (ego castSpell: 20)
							(ego setScript: (ScriptID 12 0) 0 76)
						)
					else
						(messager say: 9 6 45 0 0)
					)
				)
				(77
					(if (localproc_1aec)
						(ego addHonor: -5)
						(if (ego castSpell: 21)
							(ego setScript: (ScriptID 12 0) 0 77)
						)
					else
						(messager say: 9 6 45 0 0)
					)
				)
				(85
					(if (localproc_1aec)
						(ego addHonor: -5)
						(if (ego castSpell: 29)
							(self setScript: (ScriptID 12 0) 0 85)
						)
					else
						(messager say: 9 6 45 0 0)
					)
				)
				(87
					(if (localproc_1aec)
						(ego addHonor: -5)
						(if (ego castSpell: 31)
							(ego setScript: (ScriptID 46 0))
						)
					else
						(messager say: 9 6 45 0 0)
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
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance egoActions of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				50
				(cast contains: (ScriptID 35 1))
				-30
				(cast contains: (ScriptID 35 1))
				5
				(cast contains: (ScriptID 35 1))
				-53
				(if (not local8) local7 else 0)
				-51
				(if (not local8) local7 else 0)
				4
				(if (and (not local12) local7) else local9)
				-31
				(if (and (not local12) local7) else local9)
				-20
				(localproc_1aec)
				-15
				(if Night (localproc_1aec) else 0)
				-23
				(localproc_1aec)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-53
					(= local8 1)
					(HandsOff)
					(messager say: 2 5 53 0)
					(curRoom setScript: walkUhuraIn)
					(return 0)
				)
				(-51
					(= local8 1)
					(= local12 1)
					(return 1)
				)
				(-31
					(= local8 1)
					(messager say: 2 5 31)
					(curRoom setScript: uhuraGoodNight 0 1)
					(return 0)
				)
				(-20 (= query 13) (return 1))
				(-15 (= query 13) (return 1))
				(-23 (= query 13) (return 1))
				(-30
					(messager say: 2 5 30)
					(curRoom doVerb: 74)
					(return 0)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(3
					(cond 
						((curRoom script?) 0)
						((== (ego view?) 40)
							(= local5 ((User curEvent?) x?))
							(= local6 ((User curEvent?) y?))
							(curRoom setScript: egoGetUp)
							(return 1)
						)
					)
				)
				(2 (super doVerb: theVerb))
				(else  (ego doVerb: theVerb))
			)
		)
	)
)

(instance rakeeshTell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog: -24 (== origHeroType 1) 2 (== origHeroType 1)
		)
	)
	
	(method (doChild)
		(return
			(if (== query -25)
				(curRoom setScript: checkForMagic)
				(return 0)
			else
				(super doChild: &rest)
			)
		)
	)
)

(instance uhuraTell of Teller
	(properties)
	
	(method (doChild)
		(switch query
			(-34
				(= local2 (| local2 $0001))
				(super doChild: query)
			)
			(-3
				(= local2 (| local2 $0002))
				(return 1)
			)
			(-37
				(= local2 (| local2 $0004))
				(return 1)
			)
			(-38
				(= local2 (| local2 $0008))
				(return 1)
			)
			(-19
				(= local2 (| local2 $0010))
				(return 1)
			)
			(-35
				(= local2 (| local2 $0020))
				(return 1)
			)
			(-36
				(= local2 (| local2 $0040))
				(return 1)
			)
			(-6
				(= local2 (| local2 $0080))
				(super doChild: query)
			)
		)
		(return (= local11 1))
	)
)

(instance checkForMagic of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 1 5 25 1 self)
			)
			(1
				(if [egoStats 12]
					(messager say: 1 5 25 2 self)
				else
					(= cycles 1)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance afterStory of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bclr 31)
				(ego
					view: 0
					setLoop: 0
					x: 102
					y: 106
					setScale:
					scaleX: 156
					scaleY: 156
					signal: (| (ego signal?) $1000)
					init:
				)
				(= cycles 3)
			)
			(1
				(self setScript: goToBed self)
			)
			(2 (Bclr 118) (self dispose:))
		)
	)
)

(instance goToBed of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(if local0
					(ego view: 35 loop: 0 setCel: 8 x: 102 y: 106)
					(self changeState: (= state (+ state 2)))
				else
					(ego setMotion: PolyPath 76 108 self)
				)
			)
			(1
				(ego
					view: 35
					loop: 0
					cel: 0
					x: 102
					y: 106
					setCycle: End self
				)
			)
			(2 (= seconds 3))
			(3
				(cast eachElementDo: #hide)
				(if (cast contains: (ScriptID 35 1))
					((ScriptID 35 1) dispose:)
				)
				(curRoom drawPic: 0 9)
				(= seconds 3)
			)
			(4
				(cast eachElementDo: #show)
				(curRoom drawPic: 430 9)
				(= cycles 3)
			)
			(5
				(UnLoad 129 0)
				((ScriptID 7 7) init: 5 40)
				(if (PalVary pvGET_CURRENT_STEP)
					(PalVary pvREVERSE 0)
					(Bclr 81)
				)
				(ego setCycle: Beg self)
			)
			(6
				(ego normalize: 6 cel: 6 x: 76 y: 108)
				(= cycles 3)
			)
			(7
				(if
				(and (== Day (+ gCurrentDay_6 1)) (not (Btst 75)))
					(Bset 75)
					(messager say: 5 6 55 0 self)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance checkTime of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= local4 (GetTime))
				(= cycles 1)
			)
			(1 (= cycles 5))
			(2
				(= local3 (- (= temp0 (GetTime)) local4))
				(self dispose:)
			)
		)
	)
)

(instance uhuraGoodNight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 34 1)
					setMotion:
						PolyPath
						((ScriptID 34 1) x?)
						(+ ((ScriptID 34 1) y?) 100)
						self
				)
			)
			(1
				(if register (HandsOn))
				(= local7 0)
				(= local12 1)
				(= local9 0)
				((ScriptID 34 1) dispose:)
				(self dispose:)
			)
		)
	)
)

(instance uhuraLeave of Script
	(properties)
	
	(method (doit)
		(if local11 (= local11 0) (self cycles: 200))
		(if (== local2 255) (self changeState: 1))
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 3 6 33)
				(= local10 1)
				(= cycles 200)
			)
			(1
				(HandsOff)
				(messager say: 3 6 39 0 self)
			)
			(2
				((ScriptID 34 1)
					setMotion:
						PolyPath
						((ScriptID 34 1) x?)
						(+ ((ScriptID 34 1) y?) 100)
						self
				)
			)
			(3
				(HandsOn)
				(= local7 0)
				(= local12 1)
				(= local9 0)
				((ScriptID 34 1) dispose:)
				(self dispose:)
			)
		)
	)
)

(instance stopEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local8 1)
				(ego setMotion: 0)
				(messager say: 2 3 52 0 self)
			)
			(1
				(ego setMotion: PolyPath 190 135 self)
			)
			(2
				(ego setHeading: 180)
				(= cycles 18)
			)
			(3
				(client setScript: walkUhuraIn)
			)
		)
	)
)

(instance walkUhuraIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local9 1)
				(= [local151 0] @local133)
				(= [local151 1] @local140)
				(= [local151 2] @local144)
				(uhuraTell
					init: (ScriptID 34 1) @local133 @local151 @local147
				)
				((ScriptID 34 1)
					x: 200
					y: 250
					noun: 3
					setScale:
					scaleX: 156
					scaleY: 156
					init:
					setCycle: Walk
					setMotion: MoveTo 200 155 self
				)
			)
			(1
				((ScriptID 34 1) setScript: uhuraLeave)
				(= cycles 1)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance exitRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(and
						(Btst 29)
						(not (Btst 38))
						(== timeODay 5)
						(not local0)
						(not local9)
						(not local12)
						(!= Day gCurrentDay_6)
					)
					(client setScript: stopEgo)
				)
				(HandsOff)
				(if (and local9 (cast contains: (ScriptID 34 1)))
					(ego setMotion: 0)
					(messager say: 2 3 54)
					((ScriptID 34 1)
						setMotion:
							PolyPath
							((ScriptID 34 1) x?)
							(+ ((ScriptID 34 1) y?) 50)
							self
					)
				else
					(= cycles 1)
				)
			)
			(1
				(ego setMotion: PolyPath (ego x?) (+ (ego y?) 30) self)
			)
			(2 (curRoom newRoom: 420))
		)
	)
)

(instance uhuraTryEnter of Script
	(properties)
	
	(method (doit)
		(cond 
			(local9 (self dispose:))
			(local8 (= local7 0) (self dispose:))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local7 1)
				(messager say: 3 6 52 0 self)
			)
			(1
				(= cycles (localproc_1ad8 10))
			)
			(2
				(messager say: 3 6 52)
				(= cycles (localproc_1ad8 10))
			)
			(3
				(= local7 0)
				(= local12 1)
				(self dispose:)
			)
		)
	)
)

(instance egoGetUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setCycle: Beg self))
			(1
				(ego view: 0 normalize: setMotion: PolyPath local5 local6)
				(= local0 0)
				(walkHandler delete: curRoom)
				(self dispose:)
			)
		)
	)
)

(instance firstTime of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= gCurrentDay_6 Day)
				(= [local113 0] @local28)
				(= [local113 1] @local34)
				(= [local113 2] @local57)
				(= [local113 3] @local76)
				(= [local113 4] @local82)
				(= [local113 5] @local38)
				(= [local113 6] @local41)
				(= [local113 7] @local44)
				(= [local113 8] @local48)
				(= [local113 9] @local51)
				(= [local113 10] @local54)
				(= [local113 11] @local60)
				(= [local113 12] @local63)
				(= [local113 13] @local67)
				(= [local113 14] @local70)
				(= [local113 15] @local73)
				(= [local113 16] @local79)
				(= [local113 17] @local87)
				(= [local113 18] @local90)
				(rakeeshTell
					init: (ScriptID 35 1) @local28 @local113 @local93
				)
				(= local0 1)
				(curRoom drawPic: 0)
				(Animate 0)
				((ScriptID 7 4) init: 20 0)
				(if (ego looper?)
					((ego looper?) dispose:)
					(ego looper: 0)
				)
				(ego
					view: 40
					setCycle: 0
					setLoop: -1
					setLoop: 0
					setMotion: 0
					cel: 5
					x: 103
					y: 105
					setScale:
					scaleX: 156
					scaleY: 156
					actions: egoActions
					noun: 2
					signal: (| (ego signal?) $1000)
					init:
				)
				((ScriptID 35 1)
					view: 432
					loop: 2
					cel: 0
					x: 232
					y: 105
					noun: 1
					signal: (| ((ScriptID 35 1) signal?) $1000)
					init:
				)
				(= seconds 1)
			)
			(1
				(if (PalVary pvGET_CURRENT_STEP)
					(PalVary pvCHANGE_TICKS 1)
				)
				(messager say: 1 6 1 0 self)
			)
			(2
				(UnLoad 129 0)
				(switch origHeroType
					(0
						(messager say: 1 6 43 0 self)
					)
					(1
						(Bset 133)
						(messager say: 1 6 6 0 self)
					)
					(2
						(messager say: 1 6 42 0 self)
					)
				)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance sitDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 103 105 self)
			)
			(1
				(if (ego looper?)
					((ego looper?) dispose:)
					(ego looper: 0)
				)
				(ego view: 40 setLoop: 0 setCycle: End self)
				(= local0 1)
			)
			(2
				(if
					(and
						(Btst 29)
						(not (Btst 38))
						(== timeODay 5)
						(not local7)
						(not local12)
						(!= Day gCurrentDay_6)
					)
					(ego setScript: uhuraTryEnter)
				)
				(= cycles 1)
			)
			(3
				(walkHandler addToFront: curRoom)
				(self dispose:)
			)
		)
	)
)

(instance enterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (not (Btst 75)) (== Day gCurrentDay_6))
					(= [local113 0] @local28)
					(= [local113 1] @local34)
					(= [local113 2] @local57)
					(= [local113 3] @local76)
					(= [local113 4] @local82)
					(= [local113 5] @local38)
					(= [local113 6] @local41)
					(= [local113 7] @local44)
					(= [local113 8] @local48)
					(= [local113 9] @local51)
					(= [local113 10] @local54)
					(= [local113 11] @local60)
					(= [local113 12] @local63)
					(= [local113 13] @local67)
					(= [local113 14] @local70)
					(= [local113 15] @local73)
					(= [local113 16] @local79)
					(= [local113 17] @local87)
					(= [local113 18] @local90)
					(rakeeshTell
						init: (ScriptID 35 1) @local28 @local113 @local93
					)
					((ScriptID 35 1)
						view: 432
						loop: 2
						cel: 0
						x: 232
						y: 105
						noun: 1
						signal: (| ((ScriptID 35 1) signal?) $1000)
						init:
					)
				)
				(ego
					normalize:
					x: 240
					y: 180
					setScale:
					scaleX: 156
					scaleY: 156
					actions: egoActions
					noun: 2
					setMotion: PolyPath 240 150 self
					signal: (| (ego signal?) $1000)
					init:
				)
			)
			(1
				(cond 
					(
					(and (== Day gCurrentDay_6) (== prevRoomNum 420)) (messager say: 1 6 41 0 self))
					(
					(and (== Day (+ gCurrentDay_6 1)) (not (Btst 75))) (Bset 75) (messager say: 5 6 46 0 self))
					((and (Btst 29) (== timeODay 5)) (self setScript: checkTime self))
					((and (!= Day gCurrentDay_7) (== timeODay 5)) (messager say: 5 6 63 0 self))
					(else (= ticks 1))
				)
			)
			(2
				(= gCurrentDay_7 Day)
				(HandsOn)
				(DisableIcons)
				(self dispose:)
			)
		)
	)
)

(instance walkToChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(self setScript: egoGetUp self)
			)
			(1
				(HandsOff)
				(ego setMotion: PolyPath 155 99 self)
			)
			(2
				(chest doVerb: register)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance useChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(chestLid setLoop: 1)
				(globalSound number: 311 setLoop: 1 play:)
				(ego view: 4 setLoop: 0 cel: 0 setCycle: CT 2 1 self)
			)
			(1 (= ticks 60))
			(2
				((ScriptID 29 0) init:)
				(= cycles 1)
			)
			(3 (ego setCycle: CT 0 -1 self))
			(4
				(ego normalize:)
				(chestLid setLoop: 2 stopUpd:)
				(globalSound number: 312 setLoop: 1 play:)
				(= cycles 1)
			)
			(5 (HandsOn) (self dispose:))
		)
	)
)

(instance useChestMag of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(chestLid setLoop: 1)
				(globalSound number: 311 setLoop: 1 play:)
				(= cycles 6)
			)
			(1
				((ScriptID 29 0) init:)
				(= cycles 1)
			)
			(2
				(chestLid setLoop: 2 stopUpd:)
				(globalSound number: 312 setLoop: 1 play:)
				(= cycles 1)
			)
			(3
				(chestLid stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance fillChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(chestLid loop: 1)
				(globalSound number: 311 setLoop: 1 play:)
				(ego view: 4 setLoop: 0 cel: 0 setCycle: CT 2 1 self)
			)
			(1 (= ticks 60))
			(2
				((ScriptID 29 1) init: local1)
				(= cycles 1)
			)
			(3 (ego setCycle: CT 0 -1 self))
			(4
				(ego normalize:)
				(chestLid loop: 2)
				(globalSound number: 312 setLoop: 1 play:)
				(= cycles 1)
			)
			(5 (HandsOn) (self dispose:))
		)
	)
)

(instance warmHands of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if local0
					(self setScript: egoGetUp self)
				else
					(= cycles 1)
				)
			)
			(1
				(HandsOff)
				(ego setMotion: PolyPath 149 130 self)
			)
			(2
				(ego setHeading: 180)
				(= cycles 18)
			)
			(3 (messager say: 7 4 0 0 self))
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance shield of Feature
	(properties
		x 86
		y 50
		noun 10
		nsTop 26
		nsLeft 72
		nsBottom 74
		nsRight 100
		sightAngle 180
	)
)

(instance zebra of Feature
	(properties
		x 233
		y 50
		noun 11
		nsTop 24
		nsLeft 204
		nsBottom 76
		nsRight 263
		sightAngle 180
	)
)

(instance leftbed of Feature
	(properties
		x 95
		y 97
		noun 12
		nsTop 87
		nsLeft 45
		nsBottom 107
		nsRight 146
		sightAngle 180
	)
)

(instance rightbed of Feature
	(properties
		x 241
		y 100
		noun 13
		nsTop 83
		nsLeft 198
		nsBottom 118
		nsRight 284
		sightAngle 180
	)
)

(instance wall of Feature
	(properties
		x 163
		y 26
		noun 14
		nsTop 24
		nsLeft 38
		nsBottom 82
		nsRight 288
		sightAngle 180
	)
)

(instance fireplace of Feature
	(properties
		x 145
		y 136
		noun 16
		nsTop 122
		nsLeft 108
		nsBottom 150
		nsRight 183
		sightAngle 180
	)
)

(instance basket of Feature
	(properties
		x 59
		y 146
		noun 17
		nsTop 136
		nsLeft 47
		nsBottom 157
		nsRight 71
		sightAngle 180
	)
)

(instance log of Feature
	(properties
		x 81
		y 145
		noun 18
		nsTop 140
		nsLeft 73
		nsBottom 151
		nsRight 89
		sightAngle 180
	)
)

(instance chest of Feature
	(properties
		x 165
		y 82
		noun 15
		nsTop 74
		nsLeft 144
		nsBottom 95
		nsRight 192
		sightAngle 40
		approachX 155
		approachY 99
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb -75) (chestLid setScript: useChestMag))
			((== theVerb 4)
				(if local0
					(curRoom setScript: walkToChest 0 theVerb)
				else
					(chestLid setScript: useChest)
				)
			)
			((and (< 9 theVerb) (< theVerb 62))
				(if (>= theVerb 39)
					(= local1 (- theVerb 11))
				else
					(= local1 (- theVerb 10))
				)
				(if local0
					(curRoom setScript: walkToChest 0 theVerb)
				else
					(chestLid setScript: fillChest)
				)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance mat of Feature
	(properties
		x 95
		y 99
		noun 6
		sightAngle 40
		onMeCheck $0004
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((ego script?) 0)
					((== (ego view?) 40) 0)
					(else (ego setScript: sitDown))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance chestLid of Prop
	(properties
		x 161
		y 78
		view 430
		loop 2
		signal $4000
	)
	
	(method (doVerb theVerb)
		(chest doVerb: theVerb &rest)
	)
)

(instance fire of Prop
	(properties
		x 145
		y 141
		noun 7
		view 423
		priority 14
		signal $0010
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(if (and (not (curRoom script?)) (== theVerb 4))
			(curRoom setScript: warmHands)
		else
			(super doVerb: theVerb &rest)
		)
	)
)
