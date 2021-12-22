;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use CycleBet)
(use rm740)
(use Print)
(use PolyPath)
(use Polygon)
(use Feature)
(use Timer)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm200 0
	rideTram 1
)

(local
	[local0 3] = [0 0 -1]
	gEgoCycleSpeed
	local4
	local5
	local6
	local7
	local8
	local9
	local10
	local11
)
(class cObj of Obj
	(properties
		scratch 0
	)
)

(instance rm200 of LarryRoom
	(properties
		noun 12
		picture 200
		horizon 0
		showControls 1
	)
	
	(method (init)
		(super init: &rest)
		(Palette 2 0 255 100)
		(if global100
			(= local6 2)
			(= local7 2)
			(= local8 2)
			(= local9 2)
			(= local10 2)
		else
			(= local6 4)
			(= local7 4)
			(= local8 4)
			(= local9 4)
			(= local10 4)
		)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						0
						319
						0
						319
						116
						267
						116
						253
						106
						256
						99
						246
						104
						214
						96
						198
						96
						198
						92
						121
						92
						121
						96
						84
						96
						67
						96
						72
						112
						0
						112
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 0 117 23 117 39 186 283 186 292 122 319 122 319 189 0 189
					yourself:
				)
		)
		(theGame handsOff:)
		(if (not (Btst 35))
			(ego normalize: 900 init: ignoreActors: 0)
		)
		(if (Btst 187) (= global171 10) (Bset 20) (Bset 183))
		(if (OneOf global171 1 2 9)
			(gammie
				init:
				setScript: gammieScr
				approachVerbs: 4 1 2 5 6 7
			)
		)
		(switch prevRoomNum
			(600
				(self setScript: downStairsScr)
			)
			(300
				(if (not (Btst 35))
					(self setScript: enterThruLeftDoor)
				)
			)
			(500
				(if (not (Btst 35))
					(self setScript: enterThruRightDoor)
				)
			)
			(210
				(ego
					x: (gammie approachX?)
					y: (gammie approachY?)
					heading: 315
					cel: 7
				)
				(if (== global171 2)
					(gammie setScript: gammieLeavesScr)
				else
					(theGame handsOn:)
				)
			)
			(800
				(if (not gGEgoX) (= gGEgoX 157))
				(ego x: gGEgoX y: 190 loop: 8 cel: 3 heading: 180)
				(self setScript: enterFrom800Scr)
			)
			(else 
				(ego normalize: 900 3 loop: 8 cel: 3 init: x: 157 y: 119)
				(theGame handsOn:)
			)
		)
		(if (Btst 35)
			(if (Btst 36)
				((ScriptID 825 1) z: 0 view: 90 posn: 0 126 init:)
			else
				((ScriptID 825 1) z: 0 view: 90 posn: 319 126 init:)
			)
			((ScriptID 825 2) play: setLoop: -1)
			(self setScript: rideTram)
		)
		(fKeyBox init: approachVerbs: 4 2 5 6)
		(fCounterTop init: approachVerbs: 4 2 5 6)
		(if
			(or
				(not (theMusic2 handle?))
				(not (OneOf prevRoomNum 210 300 500 600))
			)
			(theMusic2 number: 200 loop: -1 play:)
			(theMusic number: 0 stop:)
		)
		(ashTray init: approachVerbs: 4 2 5 6 7)
		(leftChand init:)
		(rightChand init:)
		(theSign init:)
		(leftPlant init: approachVerbs: 4 5)
		(rightPlant init: approachVerbs: 4 5)
		(stairs init:)
		(carpet init:)
		(leftDoorway init:)
		(rightDoorway init:)
		(gammiesDoor init: approachVerbs: 4 2 5 6)
	)
	
	(method (doit &tmp [temp0 21])
		(cond 
			(script)
			((ego inRect: 249 93 268 106) (self setScript: upStairsScr))
			((ego inRect: 0 99 5 122) (self setScript: exitThruLeftDoorScr))
			((ego inRect: 309 95 319 138) (self setScript: exitThruRightDoorScr))
			((== (ego edgeHit?) 3) (self setScript: exitSouthScr))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(stenchTimer dispose: delete:)
		(super dispose:)
	)
	
	(method (newRoom n)
		(if (and (== global171 2) (!= n 300)) (= global171 7))
		(super newRoom: n)
	)
)

(instance gammie of Actor
	(properties
		noun 3
		approachX 184
		approachY 96
		x 162
		y 68
		priority 67
		fixPriority 1
		view 202
		loop 1
		signal $6821
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(cond 
					((Btst 35) (messager say: 3 2 11))
					((!= script gammieLeavesScr) (= global207 2) (curRoom setScript: talkToGammieScr))
					(else (proc79_3 ego gammie) (messager say: 3 2 2))
				)
			)
			(6
				(curRoom setScript: zipperScr)
			)
			(1
				(cond 
					((and (not (ego has: 22)) (Btst 35)) (messager say: 3 1 11))
					((Btst 35) (messager say: 3 1 10))
					((!= script gammieLeavesScr) (= global207 1) (curRoom setScript: talkToGammieScr))
					(else (proc79_3 ego gammie) (messager say: 3 1 2))
				)
			)
			(7
				(if (== global171 9)
					(messager say: 4 7 0 0 0 210)
				else
					(Bset 56)
					(theMusic2 fade: 80 10 10 0)
					(ego cel: 7 heading: 315)
					(curRoom newRoom: 210)
				)
			)
			(51
				(messager say: 4 51 0 0 0 210)
			)
			(5
				(messager say: 4 5 0 0 0 210)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ashTray of Feature
	(properties
		noun 16
		sightAngle 10
		approachX 217
		approachY 100
		x 230
		y 87
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 223 78 236 78 236 97 223 97
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(cond 
			((== theVerb 6) (curRoom setScript: pissInAshTray))
			((and (not (-- local6)) (== theVerb 4))
				(while (== (= temp0 (Random 1 61)) 9)
				)
				(DoAudio 2 611 1 4 temp0 1)
				(Print
					width: 160
					font: userFont
					addTitle: {Carlos, are you cheating again?}
					addText: 1 4 temp0 1 50 1 611
					fore: 67
					addText: {ThunderBird's cartoon ready.} 50 18
					fore: 0
					addIcon: 1592 1 0 0 0
					init:
				)
				(curRoom newRoom: 650)
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance fKeyBox of Feature
	(properties
		noun 4
		sightAngle 10
		approachX 85
		approachY 92
		x 91
		y 79
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 83 73 97 73 97 92 83 92
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 4 5 6)
			(curRoom setScript: keyScript 0 theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fCounterTop of Feature
	(properties
		noun 14
		sightAngle 10
		approachX 139
		approachY 92
		x 118
		y 69
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 216 67 223 69 224 73 217 77 104 77 100 75 98 70 104 67
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (and (== theVerb 4) (cast contains: gammie))
			(messager say: 14 4 12)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance leftChand of Feature
	(properties
		noun 5
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 103 0 111 3 114 10 106 20 90 21 80 12 80 3 86 0
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (and (not (-- local8)) (== theVerb 4))
			(Bset 93)
			(Bset 220)
			(Bset 21)
			(Bset 179)
			(= global189 1)
			(ego get: 39 27 26 24 4 41)
			(= global185 0)
			((inventory at: 39) noun: 52 message: 37)
			((inventory at: 13) owner: 330 cue:)
			(ego get: 13)
			(while (== (= temp0 (Random 1 61)) 9)
			)
			(DoAudio 2 611 1 4 temp0 1)
			(Print
				width: 160
				font: userFont
				addTitle: {Carlos, are you cheating again?}
				addText: 1 4 temp0 1 50 1 611
				fore: 67
				addText: {Giving you stuff to fix cellulite machine.} 50 18
				fore: 0
				addIcon: 1592 1 0 0 0
				init:
			)
			(ego
				x: (gammie approachX?)
				y: (gammie approachY?)
				loop: 8
				cel: 7
				heading: 310
			)
			(while (!= (DoAudio 6 611 1 4 temp0 1) -1)
			)
			(gammie doVerb: 2)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance rightChand of Feature
	(properties
		noun 11
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 235 0 245 1 243 11 233 19 219 20 210 13 210 4 220 0
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(cond 
			((and (not (-- local9)) (== theVerb 4))
				(= global189 1)
				(while (== (= temp0 (Random 1 61)) 9)
				)
				(DoAudio 2 611 1 4 temp0 1)
				(Print
					width: 160
					font: userFont
					addTitle: {Carlos, are you cheating again?}
					addText: 1 4 temp0 1 50 1 611
					fore: 67
					addText: {The maid's cart is now upstairs.} 50 18
					fore: 0
					addIcon: 1592 1 0 0 0
					init:
				)
				(theGame handsOff:)
				(ego setMotion: PolyPath 257 96)
			)
			((and (cast contains: gammie) (== theVerb 2)) (messager say: 11 2 12))
			(else (super doVerb: theVerb))
		)
	)
)

(instance gammiesDoor of Prop
	(properties
		noun 2
		approachX 68
		approachY 97
		x 76
		y 92
		priority 92
		fixPriority 1
		view 200
		signal $4821
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (and (not (-- local7)) (== theVerb 4))
			(Bset 204)
			(Bclr 88)
			(while (== (= temp0 (Random 1 61)) 9)
			)
			(DoAudio 2 611 1 4 temp0 1)
			(Print
				width: 160
				font: userFont
				addTitle: {Carlos, are you cheating again?}
				addText: 1 4 temp0 1 50 1 611
				fore: 67
				addText: {Char's cartoon ready...} 50 18
				fore: 0
				addIcon: 1592 1 0 0 0
				init:
			)
			(curRoom newRoom: 380)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance leftDoorway of Feature
	(properties
		noun 6
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						16
						89
						18
						61
						26
						47
						39
						42
						46
						44
						49
						51
						45
						74
						46
						101
						39
						102
						39
						94
						34
						92
						28
						94
						27
						100
						23
						94
						25
						91
						21
						88
					yourself:
				)
		)
		(super init:)
	)
)

(instance rightDoorway of Feature
	(properties
		noun 7
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						272
						52
						275
						43
						292
						45
						304
						63
						304
						87
						297
						89
						294
						98
						289
						91
						284
						91
						281
						95
						282
						102
						282
						105
						272
						105
						273
						102
					yourself:
				)
		)
		(super init:)
	)
)

(instance carpet of Feature
	(properties
		noun 1
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						23
						138
						39
						121
						37
						111
						40
						105
						54
						104
						60
						97
						54
						94
						54
						89
						75
						83
						103
						85
						113
						88
						117
						82
						202
						84
						203
						89
						217
						90
						220
						87
						244
						93
						273
						106
						283
						105
						283
						112
						278
						118
						282
						129
						284
						136
						294
						138
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 4 5)
			(curRoom setScript: carpetScr 0 theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance theSign of Feature
	(properties
		noun 15
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 146 24 178 24 236 45 235 52 230 57 173 42 149 41 97 54 91 54 91 44
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (and (== theVerb 4) (not (-- local10)))
			(proc79_11 1004)
			(Bset 111)
			(if
				(and
					(DoSound sndSET_SOUND)
					(or (ResCheck 140 1004) (ResCheck 141))
				)
				(sfx number: 1004 loop: 1 play:)
			)
			(Printf
				{Current free memory: %u kBytes\nMax available: %u kBytes\nmachineSpeed: %d}
				(MemoryInfo 0)
				global260
				howFast
			)
			(sfx number: 0 stop:)
			(proc79_12 1004)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance rightPlant of Feature
	(properties
		noun 13
		sightAngle 10
		approachX 293
		approachY 136
		x 314
		y 135
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						319
						139
						296
						139
						291
						135
						287
						137
						283
						136
						285
						124
						280
						128
						279
						118
						282
						113
						281
						100
						280
						97
						286
						92
						291
						93
						293
						99
						296
						98
						296
						93
						300
						88
						319
						88
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (and (== theVerb 5) (cast contains: gammie))
			(curRoom setScript: takePlantScr)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance stairs of Feature
	(properties
		noun 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 267 88 266 104 242 92 240 77 259 64
					yourself:
				)
		)
		(super init:)
	)
)

(instance leftPlant of Feature
	(properties
		noun 9
		sightAngle 10
		approachX 25
		approachY 134
		x 3
		y 135
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						0
						86
						4
						91
						9
						88
						18
						89
						24
						89
						24
						93
						25
						98
						27
						102
						27
						96
						29
						93
						35
						92
						39
						96
						38
						105
						40
						126
						33
						122
						37
						136
						33
						136
						26
						130
						26
						137
						23
						137
						22
						139
						4
						138
						1
						134
						5
						129
						2
						126
						0
						130
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (and (== theVerb 5) (cast contains: gammie))
			(messager say: 9 5 12)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance rideTram of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar
					disableIcon:
						(ScriptID 0 3)
						(ScriptID 0 5)
						(ScriptID 0 6)
						(ScriptID 0 9)
					enableIcon: (ScriptID 0 7) (ScriptID 0 4)
					show:
				)
				(if
				(OneOf gGButtonBarCurIcon (ScriptID 0 4) (ScriptID 0 7))
					(theIconBar curIcon: gGButtonBarCurIcon)
				else
					(theIconBar curIcon: (ScriptID 0 7))
				)
				(theGame setCursor: ((theIconBar curIcon?) getCursor:))
				(User canInput: 1 canControl: 1)
				(if (Btst 36)
					((ScriptID 825 1)
						setCycle: Walk
						setMotion: MoveTo 319 126 self
					)
				else
					((ScriptID 825 1)
						setCycle: Walk
						setMotion: MoveTo 0 126 self
					)
				)
			)
			(1
				(DisposeScript 826)
				(if (Btst 36)
					(curRoom newRoom: 500)
				else
					(curRoom newRoom: 300)
				)
			)
		)
	)
)

(instance carpetScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (< (ego heading?) 181)
					(ego setHeading: 45 self)
				else
					(ego setHeading: 270 self)
				)
			)
			(1 (= cycles 2))
			(2
				(ego
					view: 901
					setLoop: (if (== (ego heading?) 45) 4 else 5)
					cel: 0
					setCycle: 0
				)
				(= cycles 2)
			)
			(3 (ego setCycle: CT 3 1 self))
			(4 (= ticks 30))
			(5
				(messager say: 1 register 0 0 self)
			)
			(6 (ego setCycle: Beg self))
			(7
				(ego normalize: 900 ignoreActors: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance exitSouthScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego ignoreActors: 1 setMotion: MoveTo (ego x?) 190 self)
				(theMusic2 fade: 30 10 20 1)
			)
			(1 (= ticks 120))
			(2
				(= gGEgoX (ego x?))
				(theMusic number: 0 stop:)
				(theMusic2 number: 0 stop:)
				(curRoom newRoom: 800)
			)
		)
	)
)

(instance enterFrom800Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(ego setMotion: MoveTo gGEgoX 119 self)
			)
			(2
				(if
					(and
						(not (Btst 106))
						(Btst 55)
						(cast contains: gammie)
					)
					(stenchTimer setReal: stenchTimer 5)
				)
				(theGame handsOn:)
				(= gGEgoX 0)
				(self dispose:)
			)
		)
	)
)

(instance exitThruLeftDoorScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 0 (ego y?) self)
			)
			(1 (curRoom newRoom: 300))
		)
	)
)

(instance exitThruRightDoorScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 320 108 self)
			)
			(1 (curRoom newRoom: 500))
		)
	)
)

(instance enterThruLeftDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: 5 112 setMotion: MoveTo 50 112 self)
			)
			(1
				(ego normalize: 900 ignoreActors: 0)
				(if
					(and
						(not (Btst 106))
						(Btst 55)
						(cast contains: gammie)
					)
					(stenchTimer setReal: stenchTimer 5)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterThruRightDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 315 115 setMotion: MoveTo 275 115 self)
			)
			(1
				(theGame handsOn:)
				(if
					(and
						(not (Btst 106))
						(Btst 55)
						(cast contains: gammie)
					)
					(stenchTimer setReal: stenchTimer 5)
				)
				(self dispose:)
			)
		)
	)
)

(instance upStairsScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(ego
					setLoop: 6
					setCycle: 0
					setCycle: Fwd
					setPri: 90
					setMotion: MoveTo 278 79 self
				)
				(theMusic2 fade: 80 10 10 0)
			)
			(2 (= ticks 30))
			(3 (curRoom newRoom: 600))
		)
	)
)

(instance talkToGammieScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego cel: 7 heading: 315)
				(= ticks 30)
			)
			(1
				(if (not (Btst 93))
					(Bset 93)
					(messager say: 3 2 1 0 self)
				else
					(self cue:)
				)
			)
			(2
				(theMusic2 fade: 80 10 10 0 self)
			)
			(3 (curRoom newRoom: 210))
		)
	)
)

(instance zipperScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 310 self)
			)
			(1 (= cycles 2))
			(2
				(ego
					view: 908
					setLoop: 1
					cel: 0
					cycleSpeed: 8
					setCycle: CT 3 1 self
				)
			)
			(3
				(sfx number: 35 loop: 1 play:)
				(ego setCycle: CT 6 1 self)
			)
			(4
				(if (not (ego has: 22))
					(messager say: 3 6 7 0 self)
				else
					(messager say: 3 6 0 0 self)
				)
			)
			(5
				(ego normalize: 900 7)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance stenchTimer of Timer
	(properties)
	
	(method (cue)
		(if (not (proc79_14))
			(if (not (Random 0 1))
				(messager say: 0 0 3 0 0 200)
			else
				(messager say: 0 0 14 0 0 210)
			)
			(Bset 106)
		else
			(self setReal: self 3)
		)
	)
)

(instance downStairsScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 278 79)
				(= ticks 90)
			)
			(1
				(theMusic2 fade: 127 10 10 0)
				(ego setLoop: 5 setPri: 90 setMotion: MoveTo 243 106 self)
			)
			(2
				(ego normalize: 900 ignoreActors: 0)
				(if
					(and
						(not (Btst 106))
						(Btst 55)
						(cast contains: gammie)
					)
					(stenchTimer setReal: stenchTimer 5)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance keyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(ego loop: 8 cel: 6 heading: 45)
				(if (!= register 6) (= state 7))
				(= cycles 2)
			)
			(1
				(ego
					view: 908
					setLoop: 0
					cel: 0
					cycleSpeed: 8
					setCycle: CT 3 1 self
				)
				(sfx setLoop: 1)
			)
			(2
				(sfx number: 35 loop: 1 play:)
				(ego setCycle: CT 6 1 self)
				(= local5 3)
			)
			(3
				(if local4
					(sfx number: 351 loop: 1 play:)
					(if (-- local5) (-- state))
					(= ticks 60)
				else
					(pissingSfx number: 30 loop: -1 play:)
					(= seconds 4)
				)
			)
			(4
				(pissingSfx number: 0 stop:)
				(if local4
					(messager say: 12 6 25 1 self 630)
				else
					(messager say: 4 6 0 0 self)
				)
			)
			(5
				(= local4 1)
				(ego setCycle: CT 3 -1 self)
			)
			(6
				(sfx number: 35 loop: 1 play:)
				(ego setCycle: Beg self)
			)
			(7
				(ego normalize: 900 6 ignoreActors: 0)
				(theGame handsOn:)
				(self dispose:)
			)
			(8
				(ego view: 201 cel: 0 setLoop: 0 setCycle: CT 4 1 self)
			)
			(9 (= ticks 30))
			(10
				(cond 
					((!= register 5) (messager say: 4 register 0 0 self))
					((not (Btst 161))
						(ego get: 21)
						(theGame changeScore: 2 161)
						(messager say: 4 5 8 0 self)
					)
					(else (messager say: 4 5 9 0 self))
				)
			)
			(11 (ego setCycle: End self))
			(12
				(ego normalize: 900 3 ignoreActors: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sfx of Sound
	(properties
		flags $0005
	)
)

(instance pissingSfx of Sound
	(properties
		flags $0001
		loop -1
	)
)

(instance takePlantScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 13 5 12 1 self)
			)
			(1
				(messager say: 13 5 12 3 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance pissInAshTray of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego loop: 8 cel: 6 heading: 45)
				(= ticks 30)
			)
			(1
				(ego
					view: 908
					setLoop: 0
					cel: 0
					cycleSpeed: 8
					setCycle: CT 3 1 self
				)
				(sfx setLoop: 1)
			)
			(2
				(sfx number: 35 loop: 1 play:)
				(ego setCycle: CT 6 1 self)
			)
			(3
				(messager say: 16 6 0 0 self)
			)
			(4
				(ego normalize: 900 6)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance gammieWalkingCue of cObj
	(properties)
	
	(method (cue)
		(switch (++ local11)
			(1
				(gammie setLoop: 1 setMotion: MoveTo -2 115 self)
			)
			(2
				(gammieLeavesScr register: 1)
			)
		)
	)
)

(instance gammieScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Random 0 1))
					(gammie setLoop: 1 cel: 0)
					(= state 19)
				else
					(gammie setLoop: 0 cel: 0)
				)
				(= seconds (Random 3 10))
			)
			(1
				(gammie setCycle: CT 12 1 self)
			)
			(2 (= seconds 2))
			(3 (gammie setCycle: End self))
			(4 (self init:))
			(20
				(gammie setCycle: CT 2 1 self)
			)
			(21
				(gammie setCycle: CycleBet 2 7 (Random 2 4) self)
			)
			(22 (gammie setCycle: End self))
			(23 (self init:))
		)
	)
)

(instance gammieLeavesScr of Script
	(properties)
	
	(method (dispose)
		(if global205 (proc79_7))
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(gammie
					view: 203
					x: 159
					y: 98
					setPri: 30
					setCycle: Fwd
					setLoop: 1
					setStep: 2 1
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo 93 98 self
				)
				(if (not global205) (proc79_8 2))
				(= global171 2)
			)
			(1
				(gammie
					approachVerbs: 0
					x: 78
					y: 86
					setLoop: 4
					cel: 2
					setCycle: 0
				)
				(sfx number: 32 loop: 1 play:)
				(gammiesDoor setCycle: End self)
				(ego setMotion: MoveTo 133 103)
			)
			(2
				(gammie
					setLoop: 1
					setCycle: Fwd
					setMotion: MoveTo 66 85 self
				)
			)
			(3
				(gammie setLoop: 4 setCycle: 0 cel: 2)
				(= ticks 10)
			)
			(4
				(gammie
					setLoop: 2
					setCycle: Fwd
					setMotion: MoveTo 65 98 self
				)
			)
			(5
				(gammie setPri: -1 setLoop: 4 cel: 0 setCycle: 0)
				(= ticks 10)
			)
			(6
				(gammie setLoop: 4 cel: 3)
				(= ticks 10)
			)
			(7
				(gammiesDoor setCycle: Beg self)
			)
			(8
				(sfx number: 33 loop: 1 play:)
				(gammie setLoop: 4 cel: 0)
				(= ticks 10)
			)
			(9
				(ego ignoreActors: 0)
				(gammie
					setLoop: 0
					setCycle: Fwd
					setMotion: MoveTo 80 97 self
					ignoreActors: 0
				)
			)
			(10
				(gammie
					setLoop: 2
					setMotion: MoveTo 68 115 gammieWalkingCue
				)
				(= ticks 90)
			)
			(11
				(messager say: 3 2 2 1 self)
			)
			(12
				(messager say: 3 2 2 2 self)
			)
			(13
				(messager say: 3 2 2 3 self)
			)
			(14
				(if (not register) (-- state))
				(= ticks 30)
			)
			(15
				(theGame handsOn:)
				(if global205 (proc79_7))
				(gammie hide: ignoreActors: 1)
				(= global171 3)
				(= seconds 15)
			)
			(16
				(= global171 7)
				(client dispose:)
			)
		)
	)
)
