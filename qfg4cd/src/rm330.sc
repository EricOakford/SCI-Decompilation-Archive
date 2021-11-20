;;; Sierra Script 1.0 - (do not remove this comment)
(script# 330)
(include sci.sh)
(use Main)
(use GloryRm)
(use Scaler)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm330 0
)

(local
	local0
	[local1 2]
	gTheObj_2CycleSpeed
	local4
	local5
	local6
	local7
	local8
	theTimeZone
	local10
)
(instance rm330 of GloryRm
	(properties
		picture 330
	)
	
	(method (init)
		(= local0
			(cond 
				((== prevRoomNum 270) 8)
				((== global427 4) 5)
				((and (Btst 39) (< global427 4)) 4)
				(
					(and
						gCurrentDay_7
						(Btst 138)
						(not (Btst 154))
						(not (Btst 139))
					)
					11
				)
				(
					(and
						gCurrentDay_7
						(>= Day 12)
						(not (== Day gCurrentDay_7))
						(not (Btst 130))
						(not (Btst 155))
					)
					12
				)
				(
					(and
						gCurrentDay_7
						(>= Day 16)
						(not (== Day gCurrentDay_7))
						(Btst 139)
						(not (Btst 115))
						(not (Btst 156))
					)
					13
				)
				((and (>= Day 5) (Btst 37) (not (Btst 48))) 3)
				((== global428 2) 1)
				((== global428 4) 2)
				((and (Btst 110) (not (Btst 153))) 10)
				((== global427 6) 75)
				((== global427 5) (not (Btst 115)) 7)
				(else 0)
			)
		)
		(ego init: normalize:)
		(switch prevRoomNum
			(320
				(ego
					posn: 115 240
					setScaler: Scaler 120 103 161 126
					setPri: 170
				)
			)
			(260
				(Palette palSET_FLAG 0 255 0)
				(theMusic number: 320 setLoop: -1 play:)
				(ego
					view: 7
					loop: 4
					cel: 7
					posn: 68 101
					setScaler: Scaler 121 113 102 91
				)
			)
			(270
				(Palette palSET_FLAG 0 85 0)
				(Palette palSET_FLAG 112 255 0)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					setScaler: Scaler 120 103 161 126
					view: 57
					setLoop: 1 1
					setCel: 0
					posn: 202 132
				)
			)
			(else 
				(ego posn: 160 160 setScaler: Scaler 120 103 161 126)
			)
		)
		(walkHandler addToFront: self)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						319
						0
						319
						189
						208
						189
						208
						163
						261
						145
						232
						133
						156
						151
						92
						129
						52
						133
						52
						189
						0
						189
						0
						0
					yourself:
				)
		)
		(if Night (winPatch init: approachVerbs: 4))
		(garlic1 init: approachVerbs: 4)
		(garlic2 init: approachVerbs: 4)
		(rug init: approachVerbs: 4)
		(candle init: approachVerbs: 4 39)
		(lamp init: approachVerbs: 4 39)
		(theWindow init: approachVerbs: 4 33)
		(bedPost init: approachVerbs: 4)
		(bed init: approachVerbs: 4)
		(bedHead init: approachVerbs: 4)
		(chest init: approachVerbs: 4)
		(chestLid init: setPri: 120)
		(if (Btst 152)
			(if (and (Btst 82) (< global427 9))
				(Bclr 152)
			else
				(note init: approachVerbs: 4)
			)
		)
		(super init: &rest)
		(switch prevRoomNum
			(270
				(curRoom setScript: sGoToBed 0 3)
			)
			(260
				(curRoom setScript: sEnterThruWin)
			)
			(else 
				(curRoom setScript: sEnterScr)
			)
		)
	)
	
	(method (doit)
		(if (and (not script) (!= timeODay theTimeZone))
			(= theTimeZone timeODay)
			(cond 
				((>= timeODay 4)
					(if (not (cast contains: winPatch))
						(winPatch init: approachVerbs: 4)
					)
				)
				((cast contains: winPatch) (winPatch dispose:))
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== ((theIconBar getCursor:) view?) 940)
				(>= (event y?) 163)
			)
			(curRoom setScript: sExitScript)
		else
			(super handleEvent: event &rest)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(104
				(curRoom setScript: sGoToBed 0 1)
			)
			(103
				(curRoom setScript: sGoToBed 0 2)
			)
			(89
				(if Night
					(curRoom setScript: sExitThruWin 0 1)
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

(instance sEnterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 115 162 self)
			)
			(1
				(ego setPri: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 115 162 self)
			)
			(1
				(ego setPri: 170 setMotion: MoveTo 115 240 self)
			)
			(2
				(theGame handsOn:)
				(curRoom newRoom: 320)
			)
		)
	)
)

(instance sEnterThruWin of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (< local8 100)
			(Palette palSET_FLAG 0 255 (= local8 (+ local8 5)))
		)
	)
	
	(method (changeState newState &tmp temp0)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_06bf
			pushi    #handsOff
			pushi    0
			lag      theGame
			send     4
			pushi    1
			pushi    70
			callb    Bclr,  2
			pushi    #cycleSpeed
			pushi    0
			lag      ego
			send     4
			sal      gTheObj_2CycleSpeed
			ldi      2
			aTop     cycles
			jmp      code_0753
code_06bf:
			dup     
			ldi      1
			eq?     
			bnt      code_06db
			pushi    #cycleSpeed
			pushi    1
			pushi    12
			pushi    236
			pushi    2
			class    Beg
			push    
			pushSelf
			lag      ego
			send     14
			jmp      code_0753
code_06db:
			dup     
			ldi      2
			eq?     
			bnt      code_071f
			pushi    #posn
			pushi    2
			pushi    68
			pushi    141
			pushi    342
			pushi    5
			class    Scaler
			push    
			pushi    120
			pushi    103
			pushi    161
			pushi    126
			pushi    265
			pushi    1
			lsl      gTheObj_2CycleSpeed
			pushi    790
			pushi    1
			pushi    3
			pushi    322
			pushi    4
			class    PolyPath
			push    
			pushi    68
			pushi    151
			pushSelf
			lag      ego
			send     46
			jmp      code_0753
code_071f:
			dup     
			ldi      3
			eq?     
			bnt      code_0753
code_0725:
			lsl      local8
			ldi      100
			lt?     
			bnt      code_0743
			pushi    #doit
			pushi    0
			self     4
			ldi      0
			sat      temp0
code_0736:
			lst      temp0
			ldi      70
			lt?     
			bnt      code_0725
			+at      temp0
			jmp      code_0736
			jmp      code_0725
code_0743:
			pushi    #handsOn
			pushi    0
			lag      theGame
			send     4
			pushi    #dispose
			pushi    0
			self     4
code_0753:
			toss    
			ret     
		)
	)
)

(instance sExitThruWin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(Bset 70)
					(ego setMotion: PolyPath 68 141 self)
				else
					(= cycles 1)
				)
			)
			(1
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					setScaler: Scaler 121 113 102 91
					view: 7
					loop: 4
					cel: 0
					posn: 68 101
					cycleSpeed: 12
					setCycle: CT 7 1 self
				)
			)
			(2
				(if register
					(messager say: 0 89 29 0 self)
				else
					(= cycles 1)
				)
			)
			(3
				(ego cycleSpeed: gTheObj_2CycleSpeed)
				(theGame handsOn:)
				(curRoom newRoom: 260)
			)
		)
	)
)

(instance sUseChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 123 140 self)
			)
			(1 (Face ego 319 150 self))
			(2
				(ego view: 4 loop: 0 cel: 0 setCycle: CT 2 1 self)
			)
			(3
				(chestLid setCycle: End self)
			)
			(4 (= seconds 1))
			(5
				(if register
					((ScriptID 29 1) init: register)
				else
					((ScriptID 29 0) init:)
				)
				(= cycles 1)
			)
			(6
				(theGame handsOff:)
				(chestLid setCycle: Beg self)
			)
			(7
				(ego view: 4 loop: 0 cel: 2 setCycle: Beg self)
			)
			(8
				(ego normalize: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGoToBed of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(mouseDownHandler addToFront: self)
				(keyDownHandler addToFront: self)
				(if (and (== register 3) (== local0 8))
					(= register 0)
					(= gTheObj_2CycleSpeed (ego cycleSpeed?))
					(ego
						setScaler: Scaler 120 103 161 126
						view: 57
						setLoop: 1 1
						setLooper: 0
						setCel: 0
					)
					(self changeState: 4)
				else
					(ego setMotion: PolyPath 202 132 self)
				)
			)
			(1
				1
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 57
					setCel: 0
					setLoop: 0 1
					posn: 202 132
					cycleSpeed: 9
					setCycle: End self
				)
			)
			(2
				2
				(if register
					(if (== register 2)
						(self changeState: 9)
					else
						(= cycles 1)
					)
				else
					((ScriptID 7 2) init:)
					(DisposeScript 7)
					(DisposeScript 34)
				)
			)
			(3
				3
				(theGame handsOff:)
				(= temp0 100)
				(while (>= temp0 0)
					(Palette palSET_FLAG 0 85 temp0)
					(Palette palSET_FLAG 112 255 temp0)
					(FrameOut)
					(= temp0 (- temp0 5))
				)
				(if local7 (= local7 0) (candleLight dispose:))
				(cast eachElementDo: #hide)
				(= cycles 3)
			)
			(4
				(cond 
					(
						(and
							(OneOf local0 11 12 13)
							(<= timeODay 6)
							(not local10)
						)
						(= local10 1)
						(= local4 1)
						(= cycles 1)
					)
					((and (== local0 5) Night (!= global427 5)) (= local5 1) (messager say: 10 6 15 0 self))
					(else
						(if (cast contains: winPatch) (winPatch dispose:))
						(if
							(or
								(and
									(Btst 79)
									(not (Btst 82))
									(not (Btst 152))
									(>= Day (+ gCurrentDay_5 3))
									(not (Btst 115))
								)
								(and (== global427 8) (Btst 161))
							)
							(if (not (cast contains: note))
								(if (and (== global427 8) (Btst 161)) (= global427 9))
								(Bset 152)
								(note hide: init:)
							)
							(= cycles 1)
						else
							(switch local0
								(1
									(messager say: 10 6 12 0 self)
								)
								(2
									(messager say: 10 6 13 0 self)
								)
								(3
									(messager say: 10 6 14 0 self)
								)
								(4
									(= global427 4)
									(= cycles 1)
								)
								(7
									(= global427 6)
									(= cycles 1)
								)
								(75
									(= global427 7)
									(messager say: 10 6 17 0 self)
								)
								(8
									(= global427 8)
									(= cycles 1)
								)
								(10
									(Bset 153)
									(messager say: 10 6 20 0 self)
								)
								(else  (= cycles 1))
							)
						)
					)
				)
			)
			(5
				5
				(cast eachElementDo: #show)
				(if local4 (domovoi init: setPri: 238))
				(= temp0 0)
				(while (< temp0 100)
					(Palette palSET_FLAG 0 85 temp0)
					(Palette palSET_FLAG 112 255 temp0)
					(FrameOut)
					(= temp0 (+ temp0 5))
				)
				(= cycles 2)
			)
			(6
				6
				(if (and (not local4) (not local5))
					(++ global428)
					(= [egoStats 19] (ego maxMana:))
					(= [egoStats 18] (ego maxStamina:))
					(= [egoStats 17] (ego maxHealth:))
					((ScriptID 7 7) init: 7)
				)
				(= cycles 1)
			)
			(7
				7
				(cond 
					(local4
						(switch local0
							(11
								(messager say: 10 6 21 0 self)
							)
							(12
								(messager say: 10 6 23 0 self)
							)
							(13
								(messager say: 10 6 23 0 self)
							)
							(else  (= cycles 1))
						)
					)
					(local5
						(Bset 136)
						(if (>= timeODay 4)
							(= timeODay 6)
							((ScriptID 7 7) init: 1 0 1)
						)
						(= global427 5)
						(messager say: 10 6 16 0 self)
					)
					(else (= cycles 1))
				)
			)
			(8
				8
				(if local4
					(= gCurrentDay_7 Day)
					(switch local0
						(11
							(Bset 154)
							(messager say: 12 6 22 0 self)
						)
						(12
							(Bset 155)
							(messager say: 12 6 28 0 self)
						)
						(13
							(Bset 156)
							(Bset 144)
							(messager say: 12 6 24 0 self)
						)
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(9
				9
				(if
				(and (not (cast contains: winPatch)) (>= timeODay 4))
					(winPatch init: approachVerbs: 4)
				)
				(if (== register 2) ((ScriptID 7 5) init: restTime 1))
				(ego
					cycleSpeed: 8
					setLoop: 0 1
					setCel: 11
					setCycle: Beg self
				)
			)
			(10
				10
				(ego
					normalize: 1
					cycleSpeed: gTheObj_2CycleSpeed
					setMotion: MoveTo 201 146 self
				)
			)
			(11
				11
				(if local4 (= local4 0) (domovoi dispose:))
				(= local5 0)
				(mouseDownHandler delete: self)
				(keyDownHandler delete: self)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if (and (== state 2) (not register))
			(switch (event message?)
				(KEY_h
					(self cue:)
					(event claimed: 1)
					(return)
				)
				(KEY_g
					(= register 2)
					(self changeState: 9)
					(event claimed: 1)
					(return)
				)
				(else 
					(self changeState: 9)
					(event claimed: 1)
					(return)
				)
			)
		else
			(super handleEvent: event &rest)
		)
	)
)

(instance chestLid of Prop
	(properties
		noun 4
		sightAngle 40
		approachX 93
		approachY 128
		x 130
		y 125
		priority 34
		fixPriority 1
		view 330
		loop 1
		signal $4001
	)
	
	(method (doVerb theVerb)
		(chest doVerb: theVerb &rest)
	)
)

(instance domovoi of Prop
	(properties
		noun 12
		sightAngle 180
		x 143
		y 115
		view 330
		loop 2
		signal $4001
	)
)

(instance note of View
	(properties
		noun 11
		x 137
		y 110
		priority 121
		fixPriority 1
		view 330
		loop 4
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(Bclr 152)
				(Bset 399)
				(self hide:)
				(if (< global427 9)
					(messager say: 11 4 5)
				else
					(Bset 34)
					(messager say: 11 4 6)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance chest of View
	(properties
		heading 180
		noun 4
		x 130
		y 125
		priority 17
		fixPriority 1
		view 330
		cel 2
		signal $4000
	)
	
	(method (doVerb theVerb)
		(cond 
			(
			(and (cast contains: note) (note isNotHidden:)) (note doVerb: theVerb &rest))
			((OneOf theVerb 4 -80) (chestLid setScript: sUseChest 0 0))
			(
				(OneOf
					theVerb
					1
					2
					85
					83
					81
					87
					86
					88
					79
					102
					91
					89
					93
					80
					90
					94
					92
					82
					84
					95
					96
					97
					98
					11
					10
				)
				(super doVerb: theVerb &rest)
			)
			(else (chestLid setScript: sUseChest 0 theVerb))
		)
	)
)

(instance garlic1 of View
	(properties
		noun 1
		x 37
		y 19
		view 330
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (ego has: 22)
					(messager say: 1 4 27)
				else
					(ego get: 22)
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance garlic2 of View
	(properties
		noun 1
		x 154
		y 19
		view 330
		cel 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(garlic1 doVerb: theVerb &rest)
	)
)

(instance rug of View
	(properties
		noun 2
		approachX 145
		approachY 152
		x 79
		y 133
		priority 17
		fixPriority 1
		view 330
		cel 3
		signal $4000
	)
)

(instance winPatch of View
	(properties
		noun 9
		sightAngle 180
		approachX 68
		approachY 141
		x 41
		y 52
		view 330
		loop 5
		signal $4000
	)
	
	(method (doVerb theVerb)
		(theWindow doVerb: theVerb)
	)
)

(instance candleLight of Prop
	(properties
		noun 6
		x 263
		y 89
		view 330
		loop 6
		cycleSpeed 8
	)
	
	(method (init)
		(self setCycle: RandCycle)
		(super init: &rest)
	)
	
	(method (dispose)
		(self setCycle: 0)
		(super dispose:)
	)
)

(instance candle of Feature
	(properties
		noun 6
		nsLeft 256
		nsTop 91
		nsRight 270
		nsBottom 106
		sightAngle 180
		approachX 251
		approachY 141
		x 263
		y 98
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 4 39)
			(if local7
				(= local7 0)
				(candleLight dispose:)
				(messager say: 6 4 26)
			else
				(= local7 1)
				(candleLight init:)
				(messager say: 6 4 25)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance lamp of Feature
	(properties
		noun 7
		nsLeft 80
		nsTop 43
		nsRight 103
		nsBottom 64
		sightAngle 180
		approachX 94
		approachY 131
		x 91
		y 100
		z 47
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 4 39)
			(if local6
				(= local6 0)
				(messager say: 7 4 26)
			else
				(= local6 1)
				(messager say: 7 4 25)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance theWindow of Feature
	(properties
		noun 9
		nsLeft 37
		nsTop 48
		nsRight 104
		nsBottom 98
		sightAngle 180
		approachX 68
		approachY 141
		x 70
		y 73
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if Night
					(if (== (ego trySkill: 11 200) 1)
						(curRoom setScript: sExitThruWin)
					else
						(messager say: 10 6 1 1)
					)
				else
					(messager say: 10 6 2 1)
				)
			)
			(33
				(if Night
					(curRoom setScript: sExitThruWin)
				else
					(messager say: 10 6 2 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bedPost of Feature
	(properties
		noun 8
		nsLeft 170
		nsTop 104
		nsRight 181
		nsBottom 140
		sightAngle 180
		x 175
		y 122
	)
)

(instance bed of Feature
	(properties
		noun 5
		nsLeft 116
		nsTop 94
		nsRight 245
		nsBottom 128
		sightAngle 180
		approachX 201
		approachY 146
		x 180
		y 109
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom setScript: sGoToBed 0 0)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance bedHead of Feature
	(properties
		noun 5
		nsLeft 183
		nsTop 73
		nsRight 254
		nsBottom 99
		sightAngle 180
		x 218
		y 86
	)
)
