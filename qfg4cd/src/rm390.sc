;;; Sierra Script 1.0 - (do not remove this comment)
(script# 390)
(include sci.sh)
(use Main)
(use GloryRm)
(use EgoDead)
(use Scaler)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use StopWalk)
(use Motion)
(use Actor)
(use System)

(public
	rm390 0
)

(local
	gTheObj_2CycleSpeed
	local1
	local2
	local3
	local4
	theGGGTheObj_2CycleSpeed
	local6
	local7
)
(instance rm390 of GloryRm
	(properties
		picture 390
	)
	
	(method (init)
		(= local6
			(cond 
				((not (Btst 45)) 1)
				((Btst 45) 2)
				(else 0)
			)
		)
		(ego
			setPri: 152
			view: 7
			loop: 4
			cel: 7
			x: 234
			y: 60
			init:
			setScaler: Scaler 105 81 92 76
		)
		(theMusic number: 340 setLoop: -1 play:)
		(walkHandler addToFront: self)
		(lWin init: approachVerbs: 4)
		(rWin init: approachVerbs: 4)
		(if (Btst 70)
			(lWin setCel: (lWin lastCel:))
			(rWin setCel: (rWin lastCel:))
		)
		(deskLid init: approachVerbs: 4)
		(if (Btst 349) (deskLid setCel: (deskLid lastCel:)))
		(podium init: approachVerbs: 4)
		(if (Btst 352) (podium setCel: (podium lastCel:)))
		(mug init: approachVerbs: 4)
		(if (Btst 351) (mug setCel: (mug lastCel:)))
		(if (not (Btst 343)) (broom init: approachVerbs: 4))
		(if (and (ego has: 44) (Btst 401))
			(Palette palSET_FLAG 66 85 90)
			(Bset 373)
			(torchFx init:)
			(ego changeGait:)
		else
			(Palette palSET_FLAG 66 85 50)
		)
		(chestLid init: setPri: 86 approachVerbs: 4 63)
		(lWardrobe init: approachVerbs: 4)
		(rWardrobe init: approachVerbs: 4)
		(if (== local6 1)
			(oldMan init: setPri: 185 approachVerbs: 4 37)
		)
		(musicBox init: setPri: 152 approachVerbs: 4)
		(door init: approachVerbs: 4)
		(lamp init: approachVerbs: 4)
		(chest init: approachVerbs: 4 63)
		(bed init: approachVerbs: 4 37)
		(desk init: approachVerbs: 4)
		(frontDoor init: approachVerbs: 4)
		(couch init: approachVerbs: 4)
		(downChest init: approachVerbs: 4)
		(cabinet init: approachVerbs: 4)
		(tableChairs init: approachVerbs: 4)
		(stairs init: approachVerbs: 4)
		(frontSteps init: approachVerbs: 4)
		(pillar1 init: approachVerbs: 4)
		(pillar2 init: approachVerbs: 4)
		(railing1 init: approachVerbs: 4)
		(railing2 init: approachVerbs: 4)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						314
						126
						307
						126
						307
						130
						301
						130
						301
						139
						316
						144
						316
						161
						273
						183
						273
						157
						252
						141
						91
						141
						91
						149
						52
						161
						17
						161
						17
						156
						11
						156
						57
						78
						133
						78
						133
						87
						122
						87
						122
						91
						194
						91
						239
						76
						218
						76
						194
						88
						177
						88
						135
						85
						135
						75
						55
						75
						7
						156
						0
						156
						0
						189
						270
						189
						319
						165
						319
						130
						314
						130
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 87 153 176 153 152 182 81 182 61 167 61 162
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 228 174 216 186 161 186 173 174
					yourself:
				)
		)
		(super init: &rest)
		(RemapColors 1 253 112 175 62)
		(self setScript: sInWindow)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(return
			(if
				(and
					(== local6 1)
					(== ((theIconBar getCursor:) view?) 940)
					(< (ego y?) 115)
					(!= egoGait 2)
				)
				(theMusic2 number: 987 loop: 1 play:)
				(curRoom setScript: sEgoCreaks)
				(event claimed: 1)
				(return 1)
			else
				(super handleEvent: event &rest)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(91
				(if (== local6 2)
					(messager say: 20 6 44)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (newRoom n)
		(Palette palSET_FLAG 66 85 100)
		(Bclr 373)
		(ego changeGait: egoGait)
		(theMusic fade: 0)
		(super newRoom: n &rest)
	)
	
	(method (doTorch param1)
		(if param1
			(torchFx init:)
			(Bset 373)
			(ego changeGait:)
			(Palette palSET_FLAG 66 85 90)
		)
	)
)

(instance sInWindow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bclr 70)
				(= seconds 2)
			)
			(1
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego addHonor: -5 cycleSpeed: 10 setCycle: Beg self)
			)
			(2
				(lWin setCycle: Beg self)
				(rWin setCycle: Beg self)
				(ego
					setPri: -1
					x: 221
					y: 79
					normalize: 3
					setHeading: 180 self
				)
			)
			(3 0)
			(4 0)
			(5
				(ego cycleSpeed: gTheObj_2CycleSpeed)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOutWindow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 70)
				(theMusic2 number: 143 loop: 1 play:)
				(lWin setCycle: End self)
				(rWin setCycle: End self)
			)
			(1 0)
			(2
				(if
					(and
						(== local6 1)
						(!= (oldMan cel?) (oldMan lastCel:))
					)
					(oldMan setCycle: End)
				)
				(ego
					setPri: 152
					view: 7
					loop: 4
					cel: 0
					x: 234
					y: 60
					setCycle: CT 7 1 self
				)
			)
			(3
				(theGame handsOn:)
				(curRoom newRoom: 280)
			)
		)
	)
)

(instance sKillOldMan of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(oldMan setCycle: End self)
			)
			(1
				(messager say: 20 6 22 0 self)
			)
			(2 (oldMan setCycle: Beg self))
			(3
				(messager say: 18 6 23 0 self)
			)
			(4 (EgoDead 24 0 0 0 912))
		)
	)
)

(instance sGetWardrobeLoot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(client setCycle: End self)
			)
			(1
				(if
					(or
						(and register (not local3))
						(and (not register) (not local2))
					)
					(theMusic2 number: 143 loop: 1 play:)
					(if (== local6 1)
						(self setScript: sEgoCreaks self)
					else
						(= cycles 1)
					)
				else
					(= cycles 1)
				)
			)
			(2
				(cond 
					((and register (not (Btst 342))) (messager say: 20 6 19 0 self))
					((and (not register) (not (Btst 341))) (messager say: 20 6 20 0 self))
					(else (messager say: 20 6 39 0 self))
				)
			)
			(3
				(cond 
					(register
						(if (not (Btst 342))
							(if (not (Btst 389)) (Bset 389))
							(Bset 342)
							(ego get: 22 addHonor: -50)
						)
					)
					((not (Btst 341))
						(if (not (Btst 389)) (Bset 389))
						(Bset 341)
						(++ global154)
						((inventory at: 0)
							amount: (+ ((inventory at: 0) amount?) 20)
						)
					)
				)
				(client setCycle: Beg self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEgoCreaks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(= cycles 5)
			)
			(1
				1
				(++ local1)
				(if (or register (>= local1 3))
					(oldMan setCycle: End self)
				else
					(= state 5)
					(switch local1
						(1
							(messager say: 20 6 14 0 self)
						)
						(else 
							(messager say: 20 6 15 0 self)
						)
					)
				)
			)
			(2
				2
				(cond 
					(register (messager say: 21 6 21 0 self))
					((> (ego y?) 115) (= state 4) (messager say: 21 6 27 0 self))
					(else (messager say: 20 6 16 0 self))
				)
			)
			(3
				3
				(= theGGGTheObj_2CycleSpeed egoGait)
				(ego changeGait: 1 setMotion: PolyPath 223 81 self)
			)
			(4
				4
				(ego changeGait: theGGGTheObj_2CycleSpeed)
				(curRoom setScript: sOutWindow)
			)
			(5 (EgoDead 28 0 979 1 912))
			(6
				6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sUpStairs of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(
				(and
					(not (ego mover?))
					((ego cycler?) isKindOf: StopWalk)
					(== state 0)
				)
				(= register 1)
				(self changeState: state)
			)
			((and (== state 0) (>= (ego x?) 85)) (self cue:))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setScaler: Scaler 110 81 186 76)
				(torchFx setScaler: ego)
				(if register (ego setMotion: PolyPath 100 77 self))
			)
			(1
				(ego setScaler: Scaler 105 81 92 76)
				(torchFx setScaler: ego)
				(theGame handsOn:)
				(= register 0)
				(self dispose:)
			)
		)
	)
)

(instance sDownStairs of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(
				(and
					(not (ego mover?))
					((ego cycler?) isKindOf: StopWalk)
					(== state 0)
				)
				(= register 1)
				(self changeState: state)
			)
			((and (== state 0) (>= (ego y?) 159)) (self cue:))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setScaler: Scaler 110 81 186 76)
				(torchFx setScaler: ego)
				(if register (ego setMotion: PolyPath 11 167 self))
			)
			(1
				(ego setScaler: Scaler 110 85 186 132)
				(torchFx setScaler: ego)
				(theGame handsOn:)
				(= register 0)
				(self dispose:)
			)
		)
	)
)

(instance sGetBroom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 343)
				(messager say: 20 6 13 0 self)
			)
			(1
				(if (not (Btst 389)) (Bset 389))
				(broom hide:)
				(ego get: 39 addHonor: -50)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(chestLid setCycle: End self)
			)
			(1
				(cond 
					((Btst 348) (= [egoStats 14] 0) (messager say: 20 6 40 0 self))
					(register
						(ego use: 42 addHonor: 100)
						(Bset 348)
						(messager say: 20 6 26 0 self)
					)
					((Btst 345) (messager say: 20 6 39 0 self))
					(else
						(if (not (Btst 389)) (Bset 389))
						(Bset 345)
						(ego get: 42 addHonor: -50)
						((inventory at: 0)
							amount: (+ ((inventory at: 0) amount?) 18)
						)
						(messager say: 20 6 25 0 self)
					)
				)
			)
			(2
				(chestLid setCycle: Beg self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDoMusicBox of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(theMusic number: 391 setLoop: -1 play:)
				(musicBox setCycle: Fwd)
				(if (== local6 2)
					(= state 3)
					(= seconds 6)
				else
					(= seconds 4)
				)
			)
			(1
				1
				(oldMan setCycle: End self)
			)
			(2
				2
				(messager say: 21 6 30 0 self)
			)
			(3
				3
				(if (!= local1 2) (++ local1))
				(oldMan setCycle: Beg self)
			)
			(4
				4
				(musicBox setCel: 0)
				(theMusic fade: 0)
				(= seconds 1)
			)
			(5
				5
				(theMusic number: 340 setLoop: -1 play:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenDesk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 349)
				(deskLid setCycle: End self)
			)
			(1
				(if (not local4)
					(theMusic2 number: 143 loop: 1 play:)
					(if (== local6 1)
						(self setScript: sEgoCreaks self)
					else
						(= cycles 1)
					)
				else
					(= cycles 1)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCloseDesk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bclr 349)
				(deskLid setCycle: Beg self)
			)
			(1
				(if (not local4)
					(theMusic2 number: 143 loop: 1 play:)
					(if (== local6 1)
						(self setScript: sEgoCreaks self)
					else
						(= cycles 1)
					)
				else
					(= cycles 1)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDoMug of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(if (== (mug cel?) (mug lastCel:))
					(self changeState: 7)
				else
					(ego view: 31 loop: 0 cel: 0 setCycle: CT 2 1 self)
				)
			)
			(1
				1
				(messager say: 12 4 11 0 self)
			)
			(2
				2
				(mug hide:)
				(ego setCycle: Beg self)
			)
			(3
				3
				(ego normalize: 6 setMotion: PolyPath 250 155 self)
			)
			(4 4 (ego setHeading: 90 self))
			(5
				5
				(ego view: 4 loop: 0 cel: 0 setCycle: End self)
			)
			(6
				6
				(= state 12)
				(Bset 351)
				(mug cel: (mug lastCel:) show:)
				(ego setCycle: Beg self)
			)
			(7
				7
				(ego view: 4 loop: 0 cel: 0 setCycle: End self)
			)
			(8
				8
				(messager say: 12 4 12 0 self)
			)
			(9
				9
				(mug hide:)
				(ego setCycle: Beg self)
			)
			(10
				10
				(ego normalize: 6 setMotion: PolyPath 259 165 self)
			)
			(11
				11
				(ego view: 31 loop: 0 cel: 0 setCycle: CT 2 1 self)
			)
			(12
				12
				(Bclr 351)
				(mug cel: 0 show:)
				(ego setCycle: Beg self)
			)
			(13
				13
				(ego normalize: 6)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenDownChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(cond 
					((== (mug cel?) 0) (mug setCycle: End self))
					((Btst 352) (self changeState: 7))
					(else (self changeState: 5))
				)
			)
			(1
				1
				(theMusic2 number: 964 loop: 1 play:)
				(if (== local6 2)
					(= cycles 1)
				else
					(++ local1)
					(messager say: 20 6 33 0 self)
				)
			)
			(2
				2
				(if (>= local1 3)
					(oldMan setCycle: End self)
				else
					(self changeState: 8)
				)
			)
			(3
				3
				(messager say: 21 6 27 0 self)
			)
			(4 4 (EgoDead 28 0 979 1 912))
			(5
				5
				(Bset 352)
				(podium setCycle: End self)
			)
			(6
				6
				(= state 7)
				(messager say: 20 6 34 0 self)
			)
			(7
				(= state 7)
				(if (not (Btst 389)) (Bset 389))
				(Bset 346)
				(ego get: 5 addHonor: -50)
				(++ global154)
				((inventory at: 0)
					amount: (+ ((inventory at: 0) amount?) 11)
				)
				(messager say: 20 6 35 0 self)
			)
			(8
				8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance broom of View
	(properties
		sightAngle 180
		x 251
		y 139
		view 390
		loop 11
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sGetBroom)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance torchFx of Prop
	(properties
		view 775
	)
	
	(method (init)
		(super init:)
		(self setScaler: Scaler 105 81 92 76 setCycle: RandCycle)
		(if (ego has: 44) (self show:) else (self hide:))
	)
	
	(method (doit)
		(= x (ego x?))
		(= y (ego y?))
		(= z (- (ego z?) 2))
		(super doit: &rest)
	)
	
	(method (handleEvent)
		(return 0)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance chestLid of Prop
	(properties
		noun 5
		sightAngle 180
		approachX 127
		approachY 87
		x 114
		y 79
		view 390
		signal $4001
	)
	
	(method (doVerb theVerb)
		(chest doVerb: theVerb &rest)
	)
)

(instance lWardrobe of Prop
	(properties
		noun 1
		sightAngle 180
		x 139
		y 74
		view 390
		loop 1
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(self setScript: sGetWardrobeLoot 0 0)
			)
			(32
				(= local2 1)
				(super doVerb: theVerb &rest)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rWardrobe of Prop
	(properties
		noun 2
		sightAngle 180
		x 170
		y 77
		view 390
		loop 2
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(self setScript: sGetWardrobeLoot 0 1)
			)
			(32
				(= local3 1)
				(super doVerb: theVerb &rest)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lWin of Prop
	(properties
		noun 3
		sightAngle 180
		approachX 234
		approachY 85
		x 250
		y 61
		priority 59
		fixPriority 1
		view 390
		loop 3
		signal $4001
	)
	
	(method (doVerb theVerb)
		(rWin doVerb: theVerb &rest)
	)
)

(instance rWin of Prop
	(properties
		noun 3
		sightAngle 180
		approachX 234
		approachY 85
		x 249
		y 61
		priority 59
		fixPriority 1
		view 390
		loop 4
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (and (== local6 2) (not Night))
					(messager say: 20 6 42)
				else
					(curRoom setScript: sOutWindow)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance podium of Prop
	(properties
		noun 11
		sightAngle 180
		approachX 262
		approachY 153
		x 283
		y 134
		view 390
		loop 5
		signal $4001
	)
	
	(method (doVerb theVerb)
		(downChest doVerb: theVerb &rest)
	)
)

(instance deskLid of Prop
	(properties
		noun 7
		sightAngle 180
		x 233
		y 124
		view 390
		loop 6
		signal $4001
	)
	
	(method (doVerb theVerb)
		(desk doVerb: theVerb &rest)
	)
)

(instance oldMan of Prop
	(properties
		noun 21
		sightAngle 180
		approachX 89
		approachY 76
		x 61
		y 71
		view 390
		loop 7
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sEgoCreaks 0 1)
			)
			(37
				(curRoom setScript: sKillOldMan)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance musicBox of Prop
	(properties
		noun 8
		sightAngle 180
		approachX 67
		approachY 158
		x 46
		y 131
		view 390
		loop 8
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sDoMusicBox)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mug of Prop
	(properties
		noun 12
		sightAngle 180
		x 273
		y 154
		priority 135
		fixPriority 1
		view 390
		loop 9
		signal $5001
	)
	
	(method (handleEvent event)
		(if (== (mug cel?) (mug lastCel:))
			(= approachX 250)
			(= approachY 155)
		else
			(= approachX 259)
			(= approachY 165)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (Btst 352)
					(messager say: 12 4 43)
				else
					(curRoom setScript: sDoMug)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance door of Prop
	(properties
		noun 9
		sightAngle 180
		approachX 310
		approachY 126
		x 319
		y 126
		priority 120
		fixPriority 1
		view 390
		loop 10
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (messager say: 23 4 36))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lamp of Feature
	(properties
		noun 4
		nsTop 44
		nsRight 14
		nsBottom 62
		sightAngle 180
		approachX 100
		approachY 87
		x 7
		y 53
	)
)

(instance chest of Feature
	(properties
		noun 5
		nsLeft 97
		nsTop 67
		nsRight 129
		nsBottom 86
		sightAngle 180
		approachX 127
		approachY 87
		x 113
		y 76
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 345)
					(messager say: 5 1 4)
				else
					(messager say: 5 1 3)
				)
			)
			(4
				(if (Btst 345)
					(curRoom setScript: sOpenChest 0 0)
				else
					(messager say: 5 4 3)
				)
			)
			(42
				(cond 
					((Btst 345) (messager say: 5 42 4))
					((== (ego trySkill: 9 275) 1) (curRoom setScript: sOpenChest 0 0))
					(else (messager say: 5 42 2))
				)
			)
			(63
				(curRoom setScript: sOpenChest 0 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bed of Feature
	(properties
		noun 6
		nsLeft 14
		nsTop 55
		nsRight 118
		nsBottom 80
		sightAngle 180
		approachX 89
		approachY 76
		x 66
		y 67
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 87 73 87 82 67 82 67 73
						yourself:
					)
					7
					1
					5
					sDownStairs
				yourself:
			)
		)
	)
	
	(method (dispose)
		(heading dispose:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (== local6 1)
					(messager say: 6 1 5)
				else
					(messager say: 6 1 6)
				)
			)
			(4
				(if (== local6 1)
					(curRoom setScript: sEgoCreaks 0 1)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(37
				(if (== local6 1)
					(curRoom setScript: sKillOldMan)
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

(instance desk of Feature
	(properties
		noun 7
		nsLeft 214
		nsTop 108
		nsRight 254
		nsBottom 136
		sightAngle 180
		x 234
		y 122
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 349)
					(if (Btst 350)
						(messager say: 7 1 7)
					else
						(messager say: 7 1 32)
					)
				else
					(messager say: 7 1 8)
				)
			)
			(4
				(if (Btst 349)
					(cond 
						((and (not local7) (Btst 350)) (= local7 1) (messager say: 20 6 41))
						((not (Btst 350))
							(if (not (Btst 389)) (Bset 389))
							(Bset 350)
							(ego get: 3 addHonor: -50)
							(messager say: 20 6 32)
						)
						((Btst 349) (curRoom setScript: sCloseDesk))
					)
				else
					(curRoom setScript: sOpenDesk)
				)
			)
			(32
				(= local4 1)
				(super doVerb: theVerb &rest)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance frontDoor of Feature
	(properties
		noun 9
		nsLeft 292
		nsTop 72
		nsRight 319
		nsBottom 123
		sightAngle 180
		approachX 310
		approachY 126
		x 305
		y 97
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (messager say: 9 4 36))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance couch of Feature
	(properties
		noun 10
		nsLeft 106
		nsTop 111
		nsRight 175
		nsBottom 132
		sightAngle 180
		x 140
		y 121
	)
)

(instance downChest of Feature
	(properties
		noun 11
		nsLeft 267
		nsTop 119
		nsRight 289
		nsBottom 146
		sightAngle 180
		approachX 262
		approachY 153
		x 278
		y 132
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 352)
					(messager say: 20 6 34)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(4
				(if (and (Btst 352) (Btst 346))
					(messager say: 20 6 39)
				else
					(curRoom setScript: sOpenDownChest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cabinet of Feature
	(properties
		noun 13
		nsLeft 33
		nsTop 99
		nsRight 77
		nsBottom 150
		sightAngle 180
		x 55
		y 124
	)
)

(instance tableChairs of Feature
	(properties
		noun 14
		nsLeft 67
		nsTop 132
		nsRight 155
		nsBottom 178
		sightAngle 180
		x 111
		y 155
	)
)

(instance stairs of Feature
	(properties
		noun 15
		nsLeft 2
		nsTop 102
		nsRight 23
		nsBottom 156
		sightAngle 180
		approachX 12
		approachY 165
		x 12
		y 129
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 0 155 20 155 20 160 0 160
						yourself:
					)
					7
					3
					6
					sUpStairs
				yourself:
			)
		)
	)
	
	(method (dispose)
		(heading dispose:)
		(super dispose: &rest)
	)
)

(instance frontSteps of Feature
	(properties
		noun 16
		nsLeft 274
		nsTop 130
		nsRight 319
		nsBottom 187
		sightAngle 180
		approachX 269
		approachY 189
		x 296
		y 108
		z -50
	)
)

(instance pillar1 of Feature
	(properties
		noun 17
		nsLeft 176
		nsTop -1
		nsRight 212
		nsBottom 182
		sightAngle 180
		x 194
		y 90
	)
)

(instance pillar2 of Feature
	(properties
		noun 17
		nsLeft 257
		nsRight 279
		nsBottom 138
		sightAngle 180
		x 268
		y 69
	)
)

(instance railing1 of Feature
	(properties
		noun 19
		nsTop 72
		nsRight 177
		nsBottom 100
		sightAngle 180
		approachX 149
		approachY 87
		x 88
		y 66
		z -20
	)
)

(instance railing2 of Feature
	(properties
		noun 19
		nsLeft 207
		nsTop 57
		nsRight 264
		nsBottom 93
		sightAngle 180
		approachX 220
		approachY 81
		x 235
		y 75
	)
)
