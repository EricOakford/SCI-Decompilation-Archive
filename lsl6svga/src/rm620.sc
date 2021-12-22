;;; Sierra Script 1.0 - (do not remove this comment)
(script# 620)
(include sci.sh)
(use Main)
(use n078)
(use fileScr)
(use LarryRoom)
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
	rm620 0
	phone 1
	proc620_2 2
	bed 3
	upperDoor 4
	sfxL 5
	theCondom 6
)

(local
	local0
	local1
	local2 =  1
	local3
	local4
	local5
	local6
	local7
	local8
	local9
	local10
	local11
	local12
	local13
	local14
	local15
	local16
	local17
	local18
)
(procedure (proc620_2)
	(if (OneOf prevRoomNum 380 280 650 270 260)
		(= PROPERTY-ACCESS-IN-NON-METHOD 14)
	)
	(super init: &rest)
	(if global100
		(= local5 2)
		(= local6 2)
		(= local16 2)
	else
		(= local5 4)
		(= local6 4)
		(= local16 4)
	)
	(UpdateScreenItem
		((ScriptID 92 3) view: 1900 loop: 1 cel: 0)
	)
	(Bclr 4)
	(self
		addObstacle:
			((Polygon new:)
				type: 3
				init:
					297
					170
					296
					117
					291
					117
					266
					98
					267
					91
					255
					87
					149
					87
					80
					119
					53
					119
					53
					133
					51
					133
					50
					170
				yourself:
			)
			((Polygon new:)
				type: 2
				init:
					171
					116
					173
					114
					206
					99
					233
					115
					233
					134
					204
					131
					181
					138
					161
					129
					153
					134
					123
					134
					125
					116
				yourself:
			)
	)
	(closetDoor
		init:
		approachVerbs: 4 2 5 6
		setLoop: 6
		ignoreActors: 1
	)
	(upperDoor
		init:
		ignoreActors: 1
		setLoop: 0
		approachVerbs: 4 2 5 6
	)
	(bed
		init:
		approachVerbs: 4 2 5 6
		setLoop: 0
		ignoreActors: 1
	)
	(cond 
		((== prevRoomNum 630)
			(upperDoor view: 624 setLoop: 1 cel: 10)
			(ego init: normalize: 900 posn: 281 86 setLoop: 8 cel: 5)
			(self setScript: enterFromBathroomScr)
		)
		((OneOf prevRoomNum 380 280 650 270 260)
			(ego
				init:
				normalize: 622
				setCycle: 0
				setLoop: 5 1
				cel: 0
				x: 103
				y: 106
				setPri: -1
				cycleSpeed: 8
			)
			(Bclr 75)
			(Bclr 74)
			(= local8 1)
			(self setScript: fromIrisScr)
		)
		(else
			(ego init: normalize: 900 7 x: 301 y: 189 setLoop: 7)
			(self setScript: enterFrom600Scr)
		)
	)
	(if
		(or
			(!= (theMusic number?) 620)
			(not (theMusic handle?))
		)
		(theMusic number: 620 loop: -1 play:)
	)
	(if (Btst 75)
		(bedFx number: 621 loop: -1 play: hold: 1)
		(bed setCycle: Fwd)
	)
	(if (and (Btst 240) (Btst 68))
		(pillow init: approachVerbs: 4 2 5 6 noun: 21)
		(bed loop: 1 noun: 21)
		(if (not (Btst 241))
			(theCondom
				init:
				approachVerbs: 4 2 5 6
				setLoop: 3
				ignoreActors: 1
			)
		)
	else
		(pillow init: approachVerbs: 4 2 5 6)
	)
	(onOffButton
		init:
		approachVerbs: 4 2 5 6
		ignoreActors: 1
		setLoop: 5
	)
	(phone
		init:
		approachVerbs: 4 2 5 6
		ignoreActors: 1
		setLoop: 1
	)
	(theFlowers
		init:
		approachVerbs: 4 2 5 6
		setLoop: 4
		ignoreActors: 1
	)
	(if (Btst 239)
		(theFlowers noun: 24 cel: 1)
	else
		(theFlowers cel: 0)
	)
	(overHang init:)
	(theWindow init: approachVerbs: 4)
	(ladder init: approachVerbs: 4 2 5 6)
	(pool init: approachVerbs: 4 2 5 6)
	(purpleCard init: approachVerbs: 4 1 2 5 6 7)
	(redCard init: approachVerbs: 4 1 2 5 6 7)
	(blueCard init: approachVerbs: 4 1 2 5 6 7)
	(chair init: approachVerbs: 4 2 5 6)
	(table init: approachVerbs: 4 1 2 5 6 35)
	(carpet init:)
	(bungeeTimer setReal: bungeeTimer 60)
	(disposalTimer setReal: disposalTimer 100)
	(dumbWaiterTimer setReal: dumbWaiterTimer 200)
	(iceMachineTimer setReal: iceMachineTimer 300)
	(stuff
		init:
		approachVerbs: 4 2 5 6
		ignoreActors: 1
		setLoop: 2
	)
	(plant1 init:)
	(plant2 init:)
	(plant3 init:)
	(vase init: approachVerbs: 4 2 5 6)
)

(class cObj of Obj
	(properties
		scratch 0
	)
)

(instance rm620 of LarryRoom
	(properties
		noun 7
		picture 620
		horizon 0
		autoLoad 0
	)
	
	(method (init)
		(if (OneOf prevRoomNum 380 280 650 270 260)
			(= style 14)
		)
		(super init: &rest)
		(if global100
			(= local5 2)
			(= local6 2)
			(= local16 2)
		else
			(= local5 4)
			(= local6 4)
			(= local16 4)
		)
		(UpdateScreenItem
			((ScriptID 92 3) view: 1900 loop: 1 cel: 0)
		)
		(Bclr 4)
		(self
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						297
						170
						296
						117
						291
						117
						266
						98
						267
						91
						255
						87
						149
						87
						80
						119
						53
						119
						53
						133
						51
						133
						50
						170
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						171
						116
						173
						114
						206
						99
						233
						115
						233
						134
						204
						131
						181
						138
						161
						129
						153
						134
						123
						134
						125
						116
					yourself:
				)
		)
		(closetDoor
			init:
			approachVerbs: 4 2 5 6
			setLoop: 6
			ignoreActors: 1
		)
		(upperDoor
			init:
			ignoreActors: 1
			setLoop: 0
			approachVerbs: 4 2 5 6
		)
		(bed
			init:
			approachVerbs: 4 2 5 6
			setLoop: 0
			ignoreActors: 1
		)
		(cond 
			((== prevRoomNum 630)
				(upperDoor view: 624 setLoop: 1 cel: 10)
				(ego init: normalize: 900 posn: 281 86 setLoop: 8 cel: 5)
				(self setScript: enterFromBathroomScr)
			)
			((OneOf prevRoomNum 380 280 650 270 260)
				(ego
					init:
					normalize: 622
					setCycle: 0
					setLoop: 5 1
					cel: 0
					x: 103
					y: 106
					setPri: -1
					cycleSpeed: 8
				)
				(Bclr 75)
				(Bclr 74)
				(= local8 1)
				(self setScript: fromIrisScr)
			)
			(else
				(ego init: normalize: 900 7 x: 301 y: 189 setLoop: 7)
				(self setScript: enterFrom600Scr)
			)
		)
		(if
			(or
				(!= (theMusic number?) 620)
				(not (theMusic handle?))
			)
			(theMusic number: 620 loop: -1 play:)
		)
		(if (Btst 75)
			(bedFx number: 621 loop: -1 play: hold: 1)
			(bed setCycle: Fwd)
		)
		(if (and (Btst 240) (Btst 68))
			(pillow init: approachVerbs: 4 2 5 6 noun: 21)
			(bed loop: 1 noun: 21)
			(if (not (Btst 241))
				(theCondom
					init:
					approachVerbs: 4 2 5 6
					setLoop: 3
					ignoreActors: 1
				)
			)
		else
			(pillow init: approachVerbs: 4 2 5 6)
		)
		(onOffButton
			init:
			approachVerbs: 4 2 5 6
			ignoreActors: 1
			setLoop: 5
		)
		(phone
			init:
			approachVerbs: 4 2 5 6
			ignoreActors: 1
			setLoop: 1
		)
		(theFlowers
			init:
			approachVerbs: 4 2 5 6
			setLoop: 4
			ignoreActors: 1
		)
		(if (Btst 239)
			(theFlowers noun: 24 cel: 1)
		else
			(theFlowers cel: 0)
		)
		(overHang init:)
		(theWindow init: approachVerbs: 4)
		(ladder init: approachVerbs: 4 2 5 6)
		(pool init: approachVerbs: 4 2 5 6)
		(purpleCard init: approachVerbs: 4 1 2 5 6 7)
		(redCard init: approachVerbs: 4 1 2 5 6 7)
		(blueCard init: approachVerbs: 4 1 2 5 6 7)
		(chair init: approachVerbs: 4 2 5 6)
		(table init: approachVerbs: 4 1 2 5 6 35)
		(carpet init:)
		(bungeeTimer setReal: bungeeTimer 60)
		(disposalTimer setReal: disposalTimer 100)
		(dumbWaiterTimer setReal: dumbWaiterTimer 200)
		(iceMachineTimer setReal: iceMachineTimer 300)
		(stuff
			init:
			approachVerbs: 4 2 5 6
			ignoreActors: 1
			setLoop: 2
		)
		(plant1 init:)
		(plant2 init:)
		(plant3 init:)
		(vase init: approachVerbs: 4 2 5 6)
	)
	
	(method (doit)
		(if
			(or
				(and local8 (Btst 75))
				(and (== local1 1) (Btst 75))
			)
			(cond 
				((and (== local2 2) (== (bed cel?) 1)) (ego y: (+ (ego y?) 1)) (= local2 1))
				((and (== local2 1) (== (bed cel?) 0)) (ego y: (- (ego y?) 1)) (= local2 2))
			)
		)
		(cond 
			(script)
			(
				(and
					(Btst 54)
					(or (< (ego x?) 247) (> (ego y?) 109))
				)
				(Bclr 101)
				(self setScript: (ScriptID 624 1))
			)
			((and (> (ego x?) 70) (> (ego y?) 139)) (self setScript: exitSouthScr))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(DisposeScript 610)
		(bungeeTimer dispose: delete:)
		(disposalTimer dispose: delete:)
		(iceMachineTimer dispose: delete:)
		(dumbWaiterTimer dispose: delete:)
		(shakeTimer dispose: delete:)
		(super dispose:)
	)
	
	(method (cue)
		(theMusic
			number: 620
			loop: -1
			play:
			setVol: 127
			setPri: 50
		)
	)
	
	(method (notify param1)
		(cond 
			(
				(and
					((curRoom script?) script?)
					(or (Btst 57) (Btst 97))
				)
				(((curRoom script?) script?) register: param1)
			)
			((curRoom script?) ((curRoom script?) register: param1))
		)
	)
)

(instance getFlowersScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 188 104 self)
			)
			(1 (ego setHeading: 180 self))
			(2 (= cycles 2))
			(3
				(theFlowers noun: 24 cel: 1)
				(ego get: 16)
				(theGame handsOn: changeScore: 4 239)
				(self dispose:)
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
				(messager say: 6 register 0 0 self)
			)
			(6 (ego setCycle: Beg self))
			(7
				(ego normalize: 900)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFromBathroomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 30))
			(1
				(ego setLoop: 5 setMotion: MoveTo 264 95 self)
			)
			(2
				(ego setLoop: -1 setMotion: MoveTo 259 100 self)
			)
			(3 (= ticks 30))
			(4 (ego setHeading: 45 self))
			(5 (= cycles 2))
			(6
				(upperDoor setCycle: Beg self)
			)
			(7
				(sfx number: 33 loop: 1 play:)
				(ego setHeading: 180 self)
			)
			(8 (= cycles 2))
			(9
				(upperDoor view: 620 setLoop: 0 cel: 0)
				(theGame handsOn:)
				(sfx number: 0 dispose:)
				(self dispose:)
			)
		)
	)
)

(instance bungeeJumper of Prop
	(properties
		noun 19
		sightAngle 10
		approachX 122
		approachY 101
		x 77
		y 50
		z 50
		priority 30
		fixPriority 1
		view 625
		signal $6021
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 6)
			(curRoom setScript: flashScr)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance upperDoor of Prop
	(properties
		noun 11
		sightAngle 45
		approachX 263
		approachY 99
		x 265
		y 88
		priority 88
		fixPriority 1
		view 620
		signal $4021
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 267 34 286 37 277 96 265 85
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: enterBathroomScr)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theFlowers of View
	(properties
		noun 12
		sightAngle 10
		approachX 203
		approachY 100
		x 203
		y 110
		priority 130
		fixPriority 1
		view 620
		loop 4
		signal $5021
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						194
						96
						198
						91
						207
						92
						197
						84
						206
						80
						211
						89
						215
						73
						221
						76
						222
						82
						215
						93
						226
						90
						233
						101
						228
						103
						219
						98
						217
						103
						215
						118
						206
						117
						208
						99
						202
						98
						197
						101
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(cond 
			((and (OneOf theVerb 15 4 1 5) (Btst 239)) (messager say: 24 theVerb))
			((== theVerb 5) (curRoom setScript: getFlowersScr))
			(else (super doVerb: theVerb))
		)
	)
)

(instance closetDoor of Prop
	(properties
		noun 9
		sightAngle 45
		approachX 302
		approachY 135
		x 292
		y 113
		priority 113
		fixPriority 1
		view 620
		loop 6
		signal $4021
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 295 59 310 62 309 79 314 106 307 121 295 112
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: openClosetScr)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theCondom of View
	(properties
		noun 10
		sightAngle 10
		approachX 140
		approachY 94
		x 121
		y 76
		z 2
		priority 81
		fixPriority 1
		view 620
		loop 3
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((and (== y 76) (Btst 75)) (= y 75))
			((and (== y 75) (Btst 75)) (= y 76))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: handOnCondomScr)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance phone of Prop
	(properties
		noun 15
		sightAngle 10
		approachX 148
		approachY 134
		x 170
		y 115
		priority 130
		fixPriority 1
		view 620
		loop 1
		signal $4021
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 4 5)
			(curRoom setScript: (ScriptID 622 0))
		else
			(super doVerb: theVerb)
		)
	)
)

(instance carpet of Feature
	(properties
		noun 6
		approachX 105
		approachY 124
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						67
						106
						83
						108
						92
						105
						97
						100
						121
						90
						139
						84
						140
						82
						258
						79
						263
						85
						265
						83
						277
						94
						277
						100
						286
						112
						294
						112
						307
						122
						301
						139
						57
						139
						57
						133
						63
						127
						64
						116
						63
						114
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 4 5)
			(curRoom setScript: carpetScr 0 theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance vase of Feature
	(properties
		noun 24
		approachX 203
		approachY 100
		y 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 212 94 210 113 199 111 198 93
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(if (not (Btst 239))
			(theFlowers doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance overHang of Feature
	(properties
		sightAngle 10
		x 245
		y 27
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 274 12 310 24 308 33 297 40 274 32 221 45 210 20 251 7
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (and (== theVerb 4) (not (-- local5)))
			(= local5 1)
			(= gGButtonBarGetCursor theCursor)
			(theGame setCursor: normalCursor)
			(SetCursor 190 90)
			(while (== (= temp0 (Random 1 61)) 9)
			)
			(DoAudio 2 611 1 4 temp0 1)
			(switch
				(Print
					width: 160
					font: userFont
					addTitle: {Carlos, are you cheating again?}
					addText: 1 4 temp0 1 50 1 611
					fore: 67
					addText: {Mark Hood, the plumber should work on what?} 50 17
					fore: 0
					addButton: 1 {sink} 50 30
					addButton: 2 {toilet} 90 30
					addIcon: 1592 1 0 0 0
					init:
				)
				(1
					(Bset 79)
					(Bset 12)
					(Bset 52)
					(Bset 80)
					(Bset 244)
				)
				(2
					(Bclr 12)
					(Bclr 79)
					(Bset 34)
					(Bset 19)
					(Bset 244)
					(Bset 52)
				)
			)
			(theGame setCursor: gGButtonBarGetCursor)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance table of Feature
	(properties
		noun 20
		sightAngle 10
		approachX 207
		approachY 132
		y 1
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 165 114 204 98 220 105 228 126 201 124 180 135
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance chair of Feature
	(properties
		noun 8
		sightAngle 45
		approachX 148
		approachY 134
		x 144
		y 133
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						131
						101
						146
						94
						154
						95
						153
						112
						166
						118
						166
						122
						160
						125
						160
						130
						151
						135
						142
						129
						139
						129
						136
						123
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sitChairScr)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pool of Feature
	(properties
		noun 17
		sightAngle 45
		approachX 69
		approachY 119
		x 54
		y 77
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 43 66 60 68 68 72 69 79 52 85 49 83 52 80 46 80 45 72 44 70 46 68
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance theWindow of Feature
	(properties
		noun 23
		sightAngle 10
		approachX 169
		approachY 86
		x 94
		y 35
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						24
						0
						235
						0
						239
						10
						207
						21
						221
						44
						250
						38
						257
						58
						257
						78
						139
						81
						138
						76
						129
						71
						117
						70
						110
						73
						106
						76
						90
						79
						69
						86
						59
						94
						56
						91
						59
						91
						57
						89
						64
						86
						61
						83
						53
						86
						51
						82
						52
						80
						46
						79
						44
						71
						46
						68
						36
						60
						45
						56
						31
						54
						30
						52
						38
						37
						31
						25
						24
						23
						24
						14
						27
						14
						27
						6
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance pillow of Feature
	(properties
		noun 16
		sightAngle 10
		approachX 145
		approachY 91
		x 123
		y 175
		z 100
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 111 75 112 72 129 69 136 77 124 81 111 79
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(if (cast contains: theCondom)
			(theCondom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance ladder of Feature
	(properties
		noun 13
		sightAngle 10
		approachX 59
		approachY 115
		x 19
		y 21
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 14 15 23 12 25 25 16 28
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance bedFx of Sound
	(properties
		flags $0005
		loop -1
	)
)

(instance sfxL of Sound
	(properties
		flags $0005
		loop -1
	)
)

(instance sfx of Sound
	(properties
		flags $0005
	)
)

(instance onOffButton of View
	(properties
		noun 2
		sightAngle 10
		approachX 160
		approachY 89
		x 139
		y 75
		priority 75
		fixPriority 1
		view 620
		loop 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: pressButtonScr)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance stuff of View
	(properties
		sightAngle 10
		approachX 194
		approachY 135
		x 189
		y 8
		z -100
		priority 130
		fixPriority 1
		view 620
		loop 2
	)
)

(instance purpleCard of Feature
	(properties
		noun 4
		sightAngle 10
		approachX 192
		approachY 105
		x 192
		y 108
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 199 99 201 107 192 112 186 106
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 6) (messager say: 4 6))
			((OneOf theVerb 4 1 2 5 6)
				(= global207 theVerb)
				(curRoom setScript: (ScriptID 624 0) 0 1)
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance redCard of Feature
	(properties
		noun 3
		sightAngle 10
		approachX 208
		approachY 132
		y 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 184 107 190 107 194 110 194 117 185 117
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 6) (messager say: 3 6))
			((OneOf theVerb 4 1 2 5 6)
				(= global207 theVerb)
				(curRoom setScript: (ScriptID 624 0) 0 3)
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance blueCard of Feature
	(properties
		noun 5
		sightAngle 10
		approachX 219
		approachY 132
		y 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 194 110 205 110 208 117 192 117
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 6) (messager say: 5 6))
			((OneOf theVerb 4 1 2 5 6)
				(= global207 theVerb)
				(curRoom setScript: (ScriptID 624 0) 0 2)
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance plant1 of Feature
	(properties
		noun 22
		sightAngle 10
		x 8
		y 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						0
						0
						23
						0
						28
						4
						29
						6
						24
						9
						27
						11
						27
						14
						21
						12
						23
						15
						20
						18
						15
						16
						15
						22
						11
						19
						8
						25
						3
						20
						0
						25
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance plant2 of Feature
	(properties
		noun 22
		sightAngle 45
		x 7
		y 140
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						0
						62
						11
						52
						29
						52
						45
						70
						45
						81
						52
						82
						50
						84
						53
						87
						60
						86
						55
						92
						66
						98
						62
						101
						68
						110
						63
						115
						64
						118
						65
						121
						61
						126
						61
						129
						56
						138
						0
						138
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(if
				(and
					(== theVerb 2)
					(not (-- local16))
					(DoSound sndSET_SOUND)
					(not local15)
					(< (ego x?) 52)
				)
				(= local16 1)
				(curRoom setScript: (ScriptID 623 0) neighborCue 0)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance plant3 of Feature
	(properties
		noun 22
		sightAngle 10
		x 307
		y 15
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						319
						0
						319
						76
						313
						65
						299
						68
						303
						59
						297
						59
						294
						63
						289
						60
						289
						46
						309
						33
						304
						24
						293
						30
						293
						22
						286
						24
						277
						13
						258
						13
						249
						7
						240
						10
						236
						0
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (and (== theVerb 4) (not (-- local6)))
			(= local6 1)
			(while (== (= temp0 (Random 1 61)) 9)
			)
			(DoAudio 2 611 1 4 temp0 1)
			(= gGButtonBarGetCursor theCursor)
			(theGame setCursor: normalCursor)
			(SetCursor 190 110)
			(switch
				(Print
					width: 160
					font: userFont
					addTitle: {Carlos, are you cheating again?}
					addText: 1 4 temp0 1 50 1 611
					addIcon: 1592 1 0 0 0
					fore: 67
					addText: {Yeah, watta ya want?} 50 17
					fore: 0
					addButton: 1 {bungee jumper} 50 25
					addButton: 2 {disposal machine} 50 40
					addButton: 3 {dumb waiter} 110 25
					addButton: 4 {ice machine} 110 40
					init:
				)
				(1
					(if (> (bungeeTimer seconds?) 3)
						(bungeeTimer seconds: 3)
					)
				)
				(2
					(if (> (disposalTimer seconds?) 3)
						(disposalTimer seconds: 3)
					)
				)
				(3
					(if (> (dumbWaiterTimer seconds?) 3)
						(dumbWaiterTimer seconds: 3)
					)
				)
				(4
					(if (> (iceMachineTimer seconds?) 3)
						(iceMachineTimer seconds: 3)
					)
				)
			)
			(theGame setCursor: gGButtonBarGetCursor)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance exitSouthScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(ego
					setLoop: 4
					setMotion: MoveTo (+ (ego x?) 30) 210 self
				)
				(theMusic fade: 0 10 10 1)
			)
			(2 (= ticks 120))
			(3 (curRoom newRoom: 600))
		)
	)
)

(instance neighborCue of cObj
	(properties)
	
	(method (cue)
		(= local15 0)
	)
)

(instance disposalTimer of Timer
	(properties)
	
	(method (cue)
		(if (not (proc79_14))
			(curRoom setScript: disposalScr)
		else
			(self setReal: self 60)
		)
	)
)

(instance dumbWaiterTimer of Timer
	(properties)
	
	(method (cue)
		(if (not (proc79_14))
			(curRoom setScript: dumbWaiterScr)
		else
			(self setReal: self 60)
		)
	)
)

(instance iceMachineTimer of Timer
	(properties)
	
	(method (cue)
		(if (not (proc79_14))
			(curRoom setScript: iceMachineScr)
		else
			(self setReal: self 60)
		)
	)
)

(instance bed of Prop
	(properties
		noun 1
		sightAngle 10
		approachX 108
		approachY 107
		approachDist 10
		x 58
		y 59
		priority 59
		fixPriority 1
		view 626
		signal $4021
		cycleSpeed 4
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						60
						95
						67
						88
						103
						77
						110
						76
						112
						72
						119
						69
						131
						71
						138
						77
						137
						85
						126
						88
						100
						97
						90
						105
						83
						108
						69
						107
						62
						100
						68
						99
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sitBedScr)
			)
			(1
				(if cycler
					(messager say: 1 1 3)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance enterFrom600Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 249 131 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance pressButtonScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local8 0)
				(= local1 0)
				(ego setHeading: 270 self)
			)
			(1 (= cycles 2))
			(2
				(ego
					view: 901
					setLoop: 1
					cycleSpeed: 6
					cel: 0
					setCycle: CT 3 1 self
				)
			)
			(3 (= ticks 30))
			(4
				(if (bed cycler?)
					(bed setCycle: 0)
					(bedFx stop:)
					(Bclr 75)
					(onOffButton cel: 0)
				else
					(onOffButton cel: 1)
					(bedFx number: 621 loop: -1 play: hold: 1)
					(bed setCycle: Fwd)
					(Bset 75)
				)
				(= cycles 2)
			)
			(5
				(sfx number: 572 loop: 1 play:)
				(= ticks 60)
			)
			(6
				(ego cycleSpeed: 6 setCycle: End self)
			)
			(7
				(ego normalize: 900 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sitChairScr of Script
	(properties)
	
	(method (dispose &tmp temp0)
		(if (keyDownHandler size:)
			(keyDownHandler delete: self)
			(mouseDownHandler delete: self)
		)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(OnMeAndLowY theObj: 0)
				(= register 0)
				(Load rsVIEW 621)
				(ego loop: 8 cel: 4 heading: 125)
				(= cycles 2)
			)
			(1
				(ego
					view: 621
					setLoop: 0
					cel: 0
					x: 148
					y: 134
					cycleSpeed: 8
					setCycle: 0
				)
				(= ticks 30)
			)
			(2 (ego setCycle: End self))
			(3
				(Bset 76)
				(keyDownHandler add: self)
				(mouseDownHandler add: self)
				(= gGButtonBarCurIcon (ScriptID 0 5))
				(theGame handsOn:)
				(user canControl: 0)
				(theIconBar
					disableIcon:
						(ScriptID 0 6)
						(ScriptID 0 4)
						(ScriptID 0 7)
						(ScriptID 0 9)
					show:
				)
			)
			(4
				(theGame handsOff:)
				(keyDownHandler delete: self)
				(mouseDownHandler delete: self)
				(if (== register phone)
					(= local1 0)
					(ego view: 621 setLoop: 1 cel: 0 setCycle: CT 3 1 self)
					(phone hide:)
				else
					(= state 6)
					(= cycles 2)
				)
			)
			(5
				(sfx number: 622 loop: 1 play:)
				(ego setCycle: End self)
			)
			(6
				(= next (ScriptID 622 0))
				(self dispose:)
			)
			(7
				(Bclr 76)
				(ego setCycle: Beg self)
			)
			(8
				(ego normalize: 900 4 x: 148 y: 134)
				(= ticks 10)
			)
			(9
				(UnLoad 128 621)
				(if (not register)
					(ego setMotion: PolyPath local3 local4 self)
				else
					(ego
						setMotion: PolyPath (register approachX?) (register approachY?) self
					)
				)
			)
			(10
				(= gGButtonBarCurIcon (ScriptID 0 3))
				(theGame handsOn:)
				(UnLoad 128 621)
				(self dispose:)
				(if register
					(= gGButtonBarCurIcon (ScriptID 0 5))
					(register doVerb: 4)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(and
					(not (event modifiers?))
					(& (event type?) evMOUSEBUTTON)
				)
				(and
					(& (event type?) evKEYBOARD)
					(== (event message?) KEY_RETURN)
				)
				(& (event type?) $4000)
			)
			(cond 
				(
					(and
						(> mouseY 13)
						(== (theIconBar curIcon?) (ScriptID 0 5))
					)
					(OnMeAndLowY init:)
					(cast eachElementDo: #perform OnMeAndLowY event)
					(features eachElementDo: #perform OnMeAndLowY event)
					(if (= register (OnMeAndLowY theObj?))
						(= local3 (event x?))
						(= local4 (event y?))
						(= cycles 2)
						(event claimed: 1)
						(return)
					)
				)
				(
					(and
						(> mouseY 13)
						(== (theIconBar curIcon?) (ScriptID 0 3))
					)
					(= register 0)
					(= local3 (event x?))
					(= local4 (event y?))
					(= cycles 2)
					(event claimed: 1)
					(return)
				)
				(else (theIconBar handleEvent: (user curEvent?)))
			)
		)
	)
)

(instance shakeTimer of Timer
	(properties)
	
	(method (cue)
		(if (and (not local1) (or local8 local10))
			(= local1 1)
		else
			(= local1 0)
		)
	)
)

(instance fromIrisScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 3)
			)
			(1
				(switch prevRoomNum
					(380
						(messager say: 0 0 18 0 self)
					)
					(650
						(messager say: 0 0 12 1 self)
					)
					(else 
						(ego put: 2 35)
						(messager say: 0 0 9 0 self)
					)
				)
			)
			(2 (= ticks 30))
			(3
				(ego setLoop: 4 cel: 3)
				(= ticks 60)
			)
			(4 (ego setCycle: Beg self))
			(5 (= ticks 60))
			(6
				(if (== prevRoomNum 650)
					(messager sayRange: 0 0 12 2 3 self)
				else
					(= cycles 2)
				)
			)
			(7
				(if (== prevRoomNum 650)
					(messager say: 0 0 12 4 self)
				else
					(= cycles 2)
				)
			)
			(8 (= cycles 2))
			(9 (= cycles 2) (proc78_0))
			(10
				(= next sitBedScr)
				(self dispose:)
			)
		)
	)
)

(instance openClosetScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 300 138 self)
			)
			(1 (ego setHeading: 0 self))
			(2 (= cycles 2))
			(3
				(ego view: 901 setLoop: 7 cel: 0 setCycle: CT 3 1 self)
			)
			(4
				(sfx number: 32 loop: 1 play:)
				(closetDoor
					view: 624
					setLoop: 0
					cel: 0
					cycleSpeed: 4
					setCycle: End self
				)
			)
			(5
				(ego
					normalize: 900
					setLoop: 3 1
					setMotion: MoveTo 298 127 self
				)
			)
			(6 (ego setHeading: 45 self))
			(7 (= cycles 2))
			(8
				(ego view: 901 setLoop: 2 cel: 0 setCycle: 0)
				(= cycles 2)
			)
			(9 (ego setCycle: CT 3 1 self))
			(10
				(messager say: 9 4 8 0 self)
			)
			(11 (= ticks 30))
			(12 (ego setCycle: Beg self))
			(13
				(ego normalize: 900 setHeading: 180 self)
			)
			(14 (= cycles 2))
			(15
				(ego setMotion: MoveTo 300 138 self)
			)
			(16 (= cycles 2))
			(17 (ego setHeading: 360 self))
			(18 (= cycles 2))
			(19
				(closetDoor setCycle: Beg self)
			)
			(20
				(sfx number: 33 loop: 1 play:)
				(= ticks 60)
			)
			(21
				(ego setMotion: MoveTo 284 129 self)
			)
			(22 (ego setHeading: 180 self))
			(23 (= cycles 2))
			(24
				(closetDoor view: 620 setLoop: 6 cel: 0)
				(ego normalize: 900)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterBathroomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 45 self)
			)
			(1
				(sfx number: 32 loop: 1 play:)
				(upperDoor
					view: 624
					setLoop: 1
					cycleSpeed: 6
					cel: 0
					setCycle: End self
				)
			)
			(2
				(ego setLoop: 6 setMotion: MoveTo 282 87 self)
			)
			(3 (ego hide:) (= cycles 2))
			(4 (curRoom newRoom: 630))
		)
	)
)

(instance disposalScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sfx number: 624 setLoop: 1 setVol: 127 play:)
				(if (not local13)
					(= local13 1)
					(messager say: 18 0 4 0 self)
				else
					(= ticks 300)
				)
			)
			(1
				(theGame handsOn:)
				(sfx number: 0 stop:)
				(self dispose:)
				(disposalTimer setReal: disposalTimer (Random 100 300))
			)
		)
	)
)

(instance flashScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego loop: 8 cel: 7 heading: 310)
				(= cycles 2)
			)
			(1
				(ego
					view: 908
					setLoop: 1
					cel: 0
					cycleSpeed: 8
					setCycle: CT 3 1 self
				)
			)
			(2
				(sfx number: 35 loop: 1 play:)
				(ego setCycle: CT 6 1 self)
			)
			(3
				(if (> (bungeeTimer seconds?) 2)
					(bungeeTimer seconds: 99)
				)
				(messager say: 19 6 0 1 self)
			)
			(4
				(= local9 1)
				(bungeeTimer cue:)
			)
			(5 (= ticks 30))
			(6
				(= local9 0)
				(bungeeTimer cue:)
				(messager say: 19 6 0 2 self)
			)
			(7
				(ego normalize: 900 7)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance iceMachineScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sfxL number: 605 loop: -1 play:)
				(if (not local11)
					(= local11 1)
					(messager say: 18 0 5 0 self)
				else
					(= seconds 4)
				)
			)
			(1
				(sfxL number: 0 stop:)
				(sfx number: 604 loop: 1 play: self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
				(iceMachineTimer
					setReal: iceMachineTimer (Random 100 300)
				)
			)
		)
	)
)

(instance bungeeTimer of Timer
	(properties)
	
	(method (cue)
		(asm
			+al      local7
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_2755
			lal      local9
			not     
			bnt      code_2714
			lsg      global178
			ldi      10
			lt?     
			bt       code_2703
			pushi    0
			calle    proc79_14,  0
			bnt      code_2714
code_2703:
			ldi      0
			sal      local7
			pushi    #setReal
			pushi    2
			pushSelf
			pushi    10
			self     8
			jmp      code_295e
code_2714:
			ldi      999
			sag      global178
			pushi    #setLoop
			pushi    1
			pushi    0
			pushi    328
			pushi    1
			pushi    1
			pushi    311
			pushi    4
			dup     
			pushi    2
			pushi    5
			pushi    6
			pushi    16
			pushi    1
			pushi    0
			pushi    74
			pushi    1
			pushi    40
			pushi    147
			pushi    0
			pushi    127
			pushi    0
			pushi    236
			pushi    4
			class    CT
			push    
			pushi    4
			pushi    1
			pushSelf
			lofsa    bungeeJumper
			send     56
			jmp      code_295e
code_2755:
			dup     
			ldi      2
			eq?     
			bnt      code_279f
			lal      local9
			bnt      code_2795
			pushi    #setCycle
			pushi    1
			pushi    0
			pushi    254
			pushi    1
			pushi    1
			pushi    16
			pushi    1
			pushi    0
			lofsa    bungeeJumper
			send     18
			pushi    #number
			pushi    1
			pushi    312
			pushi    15
			pushi    1
			pushi    1
			pushi    51
			pushi    1
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			push    
			lofsa    sfx
			send     18
			jmp      code_295e
code_2795:
			pushi    #cue
			pushi    0
			self     4
			jmp      code_295e
code_279f:
			dup     
			ldi      3
			eq?     
			bnt      code_27ff
			pushi    #contains
			pushi    1
			lofsa    bungeeJumper
			push    
			lag      cast
			send     6
			bnt      code_27f5
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			push    
			lofsa    flashScr
			ne?     
			bnt      code_27da
			pushi    #number
			pushi    1
			pushi    235
			pushi    15
			pushi    1
			pushi    1
			pushi    51
			pushi    0
			lofsa    sfx
			send     16
code_27da:
			pushi    #setLoop
			pushi    1
			pushi    0
			pushi    16
			pushi    1
			pushi    4
			pushi    236
			pushi    2
			class    End
			push    
			pushSelf
			lofsa    bungeeJumper
			send     20
			jmp      code_295e
code_27f5:
			pushi    #cue
			pushi    0
			self     4
			jmp      code_295e
code_27ff:
			dup     
			ldi      4
			eq?     
			bnt      code_292d
			+al      local18
			pushi    #contains
			pushi    1
			lofsa    bungeeJumper
			push    
			lag      cast
			send     6
			bnt      code_2822
			pushi    #dispose
			pushi    0
			lofsa    bungeeJumper
			send     4
code_2822:
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_2838
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_2838
code_2838:
			not     
			bnt      code_2885
			pushi    0
			calle    proc79_14,  0
			not     
			bnt      code_2885
			lal      local17
			not     
			bnt      code_2885
			pushi    #handsOff
			pushi    0
			lag      theGame
			send     4
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			push    
			ldi      310
			ne?     
			bnt      code_2871
			pushi    #setHeading
			pushi    1
			pushi    310
			lag      ego
			send     6
code_2871:
			pushi    #say
			pushi    5
			pushi    19
			pushi    0
			pushi    0
			pushi    0
			pushSelf
			lag      messager
			send     14
			jmp      code_2926
code_2885:
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_289b
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_289b
code_289b:
			not     
			bnt      code_28c5
			pushi    0
			calle    proc79_14,  0
			not     
			bnt      code_28c5
			lsl      local18
			ldi      3
			eq?     
			bnt      code_28c5
			pushi    #setScript
			pushi    3
			lofsa    talk2BungeeJumper
			push    
			pushi    0
			pushi    0
			lag      curRoom
			send     10
			jmp      code_2926
code_28c5:
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_28db
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_28db
code_28db:
			not     
			bnt      code_2905
			pushi    0
			calle    proc79_14,  0
			not     
			bnt      code_2905
			lsl      local18
			ldi      5
			eq?     
			bnt      code_2905
			pushi    #setScript
			pushi    3
			lofsa    talk2BungeeJumper
			push    
			pushi    0
			pushi    1
			lag      curRoom
			send     10
			jmp      code_2926
code_2905:
			ldi      0
			sal      local7
			pushi    2
			pushi    30
			pushi    120
			callk    Random,  4
			sag      global178
			pushi    #setReal
			pushi    2
			pushSelf
			pushi    2
			pushi    60
			pushi    120
			callk    Random,  4
			push    
			self     8
code_2926:
			ldi      1
			sal      local17
			jmp      code_295e
code_292d:
			dup     
			ldi      5
			eq?     
			bnt      code_295e
			ldi      0
			sal      local7
			pushi    2
			pushi    30
			pushi    120
			callk    Random,  4
			sag      global178
			pushi    #setReal
			pushi    2
			pushSelf
			pushi    2
			pushi    30
			pushi    90
			callk    Random,  4
			push    
			self     8
			pushi    #handsOn
			pushi    0
			lag      theGame
			send     4
code_295e:
			toss    
			ret     
		)
	)
)

(instance talk2BungeeJumper of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (or (!= (ego x?) 122) (!= (ego y?) 101))
					(ego setMotion: PolyPath 122 101 self)
				else
					(self cue:)
				)
			)
			(1
				(if (!= (ego heading?) 310)
					(ego setHeading: 310 self)
				else
					(self cue:)
				)
			)
			(2 (= cycles 2))
			(3
				(if (not register)
					(messager say: 19 2 0 1 self)
				else
					(messager say: 19 2 0 2 self)
				)
			)
			(4
				(= local7 0)
				(bungeeTimer setReal: bungeeTimer (Random 30 90))
				(ego normalize: 900 7)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance dumbWaiterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= register 60)
				(sfxL number: 571 loop: -1 play: setVol: register)
				(= ticks 60)
			)
			(1
				(if (not local12)
					(= local12 1)
					(messager say: 18 0 13 0)
				)
				(= ticks 30)
			)
			(2
				(if (<= (= register (+ register 2)) 127)
					(-- state)
					(sfxL setVol: register)
				)
				(= cycles 2)
			)
			(3 (= ticks 60))
			(4
				(if (>= (= register (- register 2)) 60)
					(-- state)
					(sfxL setVol: register)
				)
				(= cycles 2)
			)
			(5
				(theGame handsOn:)
				(sfxL number: 0 stop:)
				(dumbWaiterTimer
					setReal: dumbWaiterTimer (Random 100 300)
				)
				(self dispose:)
			)
		)
	)
)

(instance sitBedScr of Script
	(properties)
	
	(method (dispose &tmp temp0)
		(if (keyDownHandler size:)
			(keyDownHandler delete: self)
			(mouseDownHandler delete: self)
		)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
					(and
						(not local14)
						(OneOf prevRoomNum 270 260 280 650 380)
					)
					(= local14 1)
					(= state 5)
					(= cycles 2)
				else
					(ego setMotion: MoveTo 103 106 self)
				)
			)
			(1 (ego setHeading: 180 self))
			(2 (= cycles 2))
			(3
				(ego
					view: 622
					setLoop: 2
					cel: 0
					setCycle: 0
					x: 103
					y: 106
				)
				(= ticks 30)
			)
			(4
				(ego cycleSpeed: 6 setCycle: End self)
			)
			(5
				(= local8 1)
				(if (bed cycler?) (messager say: 1 4 3))
				(shakeTimer setReal: shakeTimer 5)
				(= cycles 2)
			)
			(6
				(= register 0)
				(OnMeAndLowY theObj: 0)
				(keyDownHandler add: self)
				(mouseDownHandler add: self)
				(if (OneOf prevRoomNum 380 650)
					(= gGButtonBarCurIcon (ScriptID 0 3))
				else
					(= gGButtonBarCurIcon (ScriptID 0 5))
				)
				(theGame handsOn:)
				(user canControl: 0)
				(theIconBar
					disableIcon:
						(ScriptID 0 6)
						(ScriptID 0 4)
						(ScriptID 0 7)
						(ScriptID 0 9)
					show:
				)
			)
			(7
				(keyDownHandler delete: self)
				(mouseDownHandler delete: self)
				(theGame handsOff:)
				(cond 
					((and local0 local10)
						(ego
							view: 622
							setLoop: 4
							cel: 3
							x: 103
							y: 106
							setCycle: Beg self
						)
						(= local0 0)
						(= local10 0)
						(= local8 1)
					)
					((and local10 (== register bed))
						(= state 5)
						(ego
							view: 622
							setLoop: 4
							cel: 3
							x: 103
							y: 106
							setCycle: Beg self
						)
						(= local10 0)
						(= local8 1)
					)
					((and (not local10) (== register bed))
						(ego
							view: 622
							setLoop: 4
							cel: 0
							cycleSpeed: 8
							x: 103
							y: 106
							setCycle: CT 3 1 self
						)
						(= local10 1)
						(= local8 0)
					)
					(else (= cycles 2) (= local8 0))
				)
			)
			(8
				(cond 
					((and local10 (Btst 75))
						(ego
							view: 622
							setLoop: 6
							setCycle: Fwd
							x: 89
							y: 95
							cycleSpeed: 4
						)
						(= state 5)
						(= cycles 2)
					)
					((not local10) (= cycles 2))
					(else (= state 5) (= cycles 2))
				)
			)
			(9
				(ego
					view: 622
					setLoop: 2
					cel: 9
					cycleSpeed: 6
					x: 103
					y: 106
					setCycle: Beg self
				)
			)
			(10
				(shakeTimer setReal: shakeTimer 6)
				(ego
					normalize: 900 4
					setMotion: MoveTo (bed approachX?) (bed approachY?) self
				)
			)
			(11
				(UnLoad 128 622)
				(= local8 0)
				(if (not register)
					(ego setMotion: PolyPath local3 local4 self)
				else
					(ego
						setMotion: PolyPath (register approachX?) (register approachY?) self
					)
				)
			)
			(12
				(= gGButtonBarCurIcon (ScriptID 0 3))
				(theGame handsOn:)
				(UnLoad 128 622)
				(self dispose:)
				(cond 
					((== register phone)
						(= local1 0)
						(= gGButtonBarCurIcon (ScriptID 0 5))
						(register doVerb: 4)
					)
					(register
						(= gGButtonBarCurIcon (ScriptID 0 5))
						(register doVerb: 4)
					)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(and
					(not (event modifiers?))
					(& (event type?) evMOUSEBUTTON)
				)
				(and
					(& (event type?) evKEYBOARD)
					(== (event message?) KEY_RETURN)
				)
				(& (event type?) $4000)
			)
			(cond 
				(
					(and
						(> mouseY 13)
						(== (theIconBar curIcon?) (ScriptID 0 5))
					)
					(OnMeAndLowY init:)
					(cast eachElementDo: #perform OnMeAndLowY event)
					(features eachElementDo: #perform OnMeAndLowY event)
					(= register (OnMeAndLowY theObj?))
					(if (and register (!= register bed))
						(= local3 100)
						(= local4 126)
						(= local0 1)
						(= cycles 2)
					)
					(event claimed: 1)
					(return)
				)
				(
					(and
						(> mouseY 13)
						(== (theIconBar curIcon?) (ScriptID 0 3))
					)
					(= local3 (event x?))
					(= local4 (event y?))
					(= local0 1)
					(= cycles 2)
					(event claimed: 1)
					(return)
				)
				(else (theIconBar handleEvent: (user curEvent?)))
			)
		)
	)
)

(instance handOnCondomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (!= (ego heading?) 270)
					(ego setHeading: 270 self)
				else
					(= cycles 2)
				)
			)
			(1 (= cycles 2))
			(2
				(ego setMotion: MoveTo 140 94 self)
			)
			(3
				(ego view: 901 setLoop: 1 cel: 0 setCycle: CT 3 1 self)
			)
			(4
				(ego get: 8)
				(theGame changeScore: 4 241)
				(theCondom dispose:)
				(= ticks 60)
			)
			(5 (ego setCycle: End self))
			(6
				(ego normalize: 900 1)
				(= cycles 2)
			)
			(7
				(if
					(and
						(not (Btst 24))
						(== ((inventory at: 17) owner?) 590)
					)
					(= register 1)
					(messager say: 10 5 6 1 self)
				else
					(= register 0)
					(messager say: 10 5 0 0 self)
				)
			)
			(8
				(if (not register)
					(theGame handsOn:)
					(self dispose:)
				else
					(theGame hideControls: setCursor: waitCursor 1)
					(curRoom drawPic: -1)
					(cast eachElementDo: #hide)
					(= ticks 30)
				)
			)
			(9
				(Print
					font: userFont
					addText: 10 5 6 2 5 1 620
					modeless: 2
					init:
				)
				(= ticks 300)
			)
			(10
				(if (Print dialog?) ((Print dialog?) dispose:))
				(Print modeless: 0)
				(Bset 24)
				(= cycles 2)
			)
			(11 (curRoom newRoom: 860))
		)
	)
)
