;;; Sierra Script 1.0 - (do not remove this comment)
(script# 440)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use rm740)
(use Array)
(use Print)
(use Inset)
(use Polygon)
(use Feature)
(use LoadMany)
(use DCIcon)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm440 0
)

(local
	local0
	local1
	local2
	local3
	[newGirl 13]
	[local17 11] = [{KGirl} {DGirl} {HGirl} {CGirl} {IGirl} {BGirl} {EGirl} {AGirl} {FGirl} {GGirl} {JGirl}]
	[local28 11] = [242 239 160 199 200 222 217 156 114 143 218]
	[local39 11] = [83 61 97 56 86 45 71 47 80 81 100]
	[local50 11] = [229 229 153 192 192 210 206 144 104 133 209]
	[local61 11] = [80 58 90 55 82 44 67 46 78 79 98]
	local72
	local73
	newGirlView
	local75
	local76
	local77 =  5
	local78
	local79
	local80
	local81
	local82
	local83
	local84
	local85
	local86
)
(procedure (localproc_0ae7 param1 param2 &tmp temp0)
	(if (not [newGirl 0]) (return))
	(= temp0 0)
	(while (<= temp0 10)
		([newGirl temp0] cel: param1)
		(if (> argc 1) ([newGirl temp0] view: param2))
		(++ temp0)
	)
	(if
	(and newGirlView (!= newGirlView ([newGirl 0] view?)))
		(UnLoad 128 newGirlView)
		(= newGirlView ([newGirl 0] view?))
	)
)

(procedure (localproc_0e65 param1 &tmp temp0)
	(= local76 1)
	(= temp0 0)
	(while (<= temp0 10)
		((= [newGirl temp0] (Girl new:))
			name: [local17 temp0]
			view: 445
			setLoop: temp0
			cel: 0
			x: [local50 temp0]
			y: [local61 temp0]
			ignoreActors: 1
			priority: 108
			fixPriority: 1
			signal: 26657
			init:
			setCycle: 0
			approachX: [local28 temp0]
			approachY: [local39 temp0]
			noun: 14
			approachVerbs: 4 1 2 5 6
		)
		(++ temp0)
	)
	(if argc (param1 cue:))
)

(procedure (localproc_23b6 param1 param2 param3)
	(theGame handsOff:)
	(if (> argc 2)
		(messager say: param1 param2 param3 0 retCtrlCue 460)
	else
		(messager say: param1 param2 0 0 retCtrlCue 460)
	)
)

(procedure (localproc_2c38 param1 param2 &tmp temp0 theTheCursor temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9 temp10)
	(theMusic number: 313 play: setLoop: 1)
	(Load rsVIEW 453)
	(= theTheCursor theCursor)
	(theGame setCursor: normalCursor)
	(= temp4 80)
	(= temp6 90)
	(= temp5 180)
	(= temp7 90)
	(= temp8 10)
	(= temp10 57)
	(= temp3 0)
	(SetCursor 255 100)
	(while (not temp3)
		(Print
			font: userFont
			width: 110
			back: 46
			addTitle: 2 0 1 2 82
			addText: 2 0 1 1 72 1 82
			addIcon: frameIcon -1 -1 2 4
			addIcon: (deathIcon view: 453 cel: 0 loop: 0 yourself:) -1 -1 0 1
		)
		((Print dialog?) setSize:)
		(= temp3
			(Print
				addButton: 1 2 0 3 1 73 57 82
				addButton: 0 2 0 2 1 140 57 82
				init:
			)
		)
		(DoAudio 3 82 2 0 1 1)
		(switch temp3
			(0
				(Print back: 7)
				(theGame setCursor: theTheCursor)
				(sfx number: 0 dispose:)
				(theMusic number: 0 stop:)
				(sounds eachElementDo: #pause 0)
				(if param2 (param2 cue:))
				(= temp3 -1)
			)
			(1
				(= local3 0)
				(theGame restore:)
				(FrameOut)
				(= temp3 0)
			)
		)
	)
	(LoadMany 0 -569)
)

(instance rm440 of LarryRoom
	(properties
		noun 1
		picture 440
		horizon 0
		autoLoad 0
	)
	
	(method (init)
		(super init:)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init: 109 51 120 51 120 63 109 63
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 96 72 107 72 107 85 96 85
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 125 74 137 74 137 88 125 88
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 145 83 158 83 158 97 145 97
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 185 50 197 50 197 63 185 63
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 220 54 232 54 232 67 220 67
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 198 64 210 64 210 77 198 77
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 183 75 195 75 195 90 183 90
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 221 74 234 74 234 88 221 88
					yourself:
				)
				((Polygon new:)
					type: 3
					init:
						199
						105
						199
						94
						214
						94
						214
						106
						254
						106
						254
						92
						239
						81
						239
						63
						244
						54
						260
						46
						258
						39
						216
						43
						216
						53
						202
						53
						202
						44
						149
						44
						149
						54
						136
						54
						136
						45
						74
						45
						74
						105
					yourself:
				)
		)
		(recordPlayer
			init:
			approachVerbs: 4 1 2 5 6
			ignoreActors: 1
		)
		(rightDoor init: approachVerbs: 4 ignoreActors: 1)
		(leftDoor init: approachVerbs: 4 ignoreActors: 1)
		(switch prevRoomNum
			(455
				(ego init: normalize: 441 0 posn: 241 69 setPri: 107)
			)
			(230
				(ego init: normalize: 441 1 x: 283 y: 98 setPri: 107)
				(rightDoor cel: (rightDoor lastCel:))
				(curRoom setScript: enterThruDoorScr 0 2)
			)
			(460
				(ego init: normalize: 441 0 x: 239 y: 69 setPri: 107)
				(cav
					init:
					approachVerbs: 4 1 2 5 6
					setLoop: 5 1
					ignoreActors: 1
				)
				(self setScript: talkToCavScr 0 1)
			)
			(else 
				(ego init: normalize: 441 3 x: 120 y: 119 setPri: 107)
				(leftDoor cel: (leftDoor lastCel:))
				(curRoom setScript: enterThruDoorScr 0 1)
			)
		)
		(UnLoad 128 900)
		(theMusic2 number: 0 stop:)
		(cond 
			((or (Btst 210) (Btst 66)))
			(
			(and (Btst 9) (not (OneOf prevRoomNum 460 455)))
				(theMusic2 number: 440 loop: -1 play: setVol: 127)
				(localproc_0e65)
				(recordPlayer setCycle: Fwd)
				(upperSpeaker init: approachVerbs: 4 5 ignoreActors: 1)
				(lowerSpeaker init: approachVerbs: 4 5 ignoreActors: 1)
				(cav init: approachVerbs: 4 1 2 5 6 setScript: cavScr)
			)
			(else
				(cav
					init:
					view: 442
					setLoop: 0
					cel: 0
					approachVerbs: 4 1 2 5 6
				)
			)
		)
		(larryStep init: approachVerbs: 4)
		(stage init: approachVerbs: 4)
		(waterPipes init:)
		(rightLight init:)
		(upperLight init:)
		(upperLight2 init:)
		(lowerLight init:)
		(lowerLight2 init:)
		(Load 143 440)
		(Load rsSCRIPT 1802)
		(= local82 1)
		(cond 
			((ResCheck 140 346) (= local84 140))
			((ResCheck 141 346) (= local84 141))
			(else (= local84 132))
		)
		(proc79_11 814 355 346 347 445 443 444)
	)
	
	(method (doit)
		(if (and (not local76) (== (ego view?) 441))
			(switch (ego cel?)
				(0
					(if (== local82 2)
						(= local82 1)
						(sfx number: 346 loop: 1 play:)
					)
				)
				(3
					(if (== local82 1)
						(= local82 2)
						(sfx number: 347 loop: 1 play:)
					)
				)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(sfx number: 0 dispose:)
		(proc79_12 814 355 346 347 445 443 444)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1
					(cond 
						(local76 (messager say: 1 1 1) (return 1))
						(local75 (messager say: 1 1 3) (return 1))
						(else (return (super doVerb: theVerb)))
					)
				)
				(6
					(if local76
						(messager say: 1 6)
					else
						(messager say: 1 6 4)
					)
					(return 1)
				)
				(2
					(cond 
						(local76 (messager say: 1 2 1) (return 1))
						((cast contains: cav) (messager say: 1 2 3) (return 1))
						(else (return (super doVerb: theVerb)))
					)
				)
				(else 
					(return (super doVerb: theVerb))
				)
			)
		)
	)
	
	(method (cue)
		(closeUpInset dispose:)
	)
	
	(method (newRoom n)
		(titTimer dispose: delete:)
		(punchTimer dispose: delete:)
		(super newRoom: n)
		(if (not (OneOf n 420 230)) (ego normalize: 900))
	)
)

(class cObj of Obj
	(properties
		scratch 0
	)
)

(instance enterThruDoorScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 20))
			(1
				(if (== register 2)
					(= state 9)
					(= cycles 2)
				else
					(ego setMotion: MoveTo 124 97 self)
				)
			)
			(2
				(leftDoor setCycle: Beg self)
			)
			(3
				(sfx number: 33 loop: 1 play:)
				(theGame handsOn:)
				(self dispose:)
			)
			(10
				(ego setMotion: MoveTo 250 98 self)
			)
			(11
				(rightDoor setCycle: Beg self)
			)
			(12
				(sfx number: 33 loop: 1 play:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance cav of Actor
	(properties
		noun 2
		approachX 239
		approachY 69
		x 260
		y 66
		view 442
		loop 5
		signal $6821
	)
	
	(method (init)
		(= local75 1)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if [newGirl 0]
					(messager say: 2 2 1 0)
				else
					(curRoom setScript: talkToCavScr)
				)
			)
			(1
				(if [newGirl 0]
					(messager say: 2 2 1 0)
				else
					(curRoom setScript: talkToCavScr)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(super cue:)
		(if (Btst 6) (cav view: 442 setLoop: 0 cel: 0))
	)
)

(instance larryStep of Feature
	(properties
		noun 15
		approachX 107
		approachY 56
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 107 48 122 48 122 66 107 66
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(cond 
					((Btst 209) (messager say: 15 1))
					(local76 (messager say: 15 1 1))
				)
			)
			(4
				(if local76
					(curRoom setScript: larryScr 0 0)
				else
					(curRoom setScript: larryScr 0 -2)
				)
			)
			(5
				(if local76
					(messager say: 15 5 1)
				else
					(messager say: 15 5)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rightDoor of Prop
	(properties
		noun 16
		approachX 255
		approachY 97
		x 265
		y 95
		priority 170
		fixPriority 1
		view 440
		loop 1
		signal $6821
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 258 89 282 90 279 109 258 107
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: exitThruDoorScr 0 2)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance leftDoor of Prop
	(properties
		noun 4
		approachX 121
		approachY 105
		x 127
		y 107
		priority 170
		fixPriority 1
		view 440
		signal $6821
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: exitThruDoorScr 0 1)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance stage of Feature
	(properties
		noun 17
		approachX 239
		approachY 71
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 262 87 252 83 243 75 242 64 248 55 260 49 269 49
					yourself:
				)
		)
		(super init:)
	)
)

(instance waterPipes of Feature
	(properties
		noun 18
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						0
						65
						18
						62
						21
						65
						166
						65
						166
						0
						173
						0
						173
						63
						175
						66
						177
						64
						177
						61
						183
						62
						186
						66
						183
						71
						178
						72
						172
						71
						172
						138
						166
						138
						166
						70
						162
						70
						29
						70
						17
						72
						0
						72
					yourself:
				)
		)
		(super init:)
	)
)

(instance rightLight of Feature
	(properties
		noun 19
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						282
						52
						291
						62
						301
						65
						309
						60
						315
						62
						313
						75
						306
						76
						300
						71
						291
						72
						276
						83
					yourself:
				)
		)
		(super init:)
	)
)

(instance exitThruDoorScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== register 2)
					(= state 3)
					(= cycles 2)
				else
					(sfx number: 32 loop: 1 play:)
					(leftDoor setCycle: End self)
				)
			)
			(1
				(ego setMotion: MoveTo 121 119 self)
				(theMusic2 fade: 0 10 10 1)
			)
			(2
				(if global205 (proc79_7))
				(= ticks 120)
			)
			(3
				(theMusic2 number: 0 stop:)
				(curRoom newRoom: 420)
			)
			(4
				(sfx number: 32 loop: 1 play:)
				(rightDoor setCycle: End self)
			)
			(5
				(ego setMotion: MoveTo (+ (ego x?) 20) (ego y?) self)
				(theMusic2 fade: 0 10 10 1)
			)
			(6
				(ego hide:)
				(if global205 (proc79_7))
				(= ticks 120)
			)
			(7
				(theMusic2 number: 0 stop:)
				(curRoom newRoom: 230)
			)
		)
	)
)

(instance Girl of Prop
	(properties)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(2
				(= temp0 (Random 1 6))
				(messager say: 14 2 0 temp0)
			)
			(4
				(= temp0 (Random 1 5))
				(messager say: 14 4 0 temp0)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance upperSpeaker of Prop
	(properties
		noun 3
		sightAngle 10
		approachX 258
		approachY 41
		x 270
		y 41
		priority 41
		fixPriority 1
		view 440
		loop 2
		signal $6821
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 277 49 265 49 262 34 275 34
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doit)
		(if (== (theMusic2 prevSignal?) 10)
			(= cel 1)
			(theMusic2 prevSignal: 0)
		else
			(= cel 0)
		)
		(super doit:)
	)
)

(instance lowerSpeaker of View
	(properties
		noun 3
		sightAngle 10
		approachX 254
		approachY 106
		x 258
		y 106
		priority 106
		fixPriority 1
		view 440
		loop 2
		signal $6821
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 266 112 253 114 250 99 261 97
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(= cel (upperSpeaker cel?))
	)
)

(instance tits of View
	(properties
		x 116
		y 73
		priority 84
		fixPriority 1
		view 460
		loop 4
		signal $6821
	)
)

(instance sfx of Sound
	(properties
		flags $0005
	)
)

(instance titCue of cObj
	(properties)
	
	(method (cue)
		(= local80 0)
	)
)

(instance fastForwardCue of cObj
	(properties)
	
	(method (cue)
		(if (talkers size:) (messager cue: 1))
		(= gNewStr_4 (ByteArray new: 40))
		(= gNewStr_3 (ByteArray new: 40))
		(Message 0 440 0 0 23 1 (gNewStr_4 data?))
		(Message 0 440 0 0 24 1 (gNewStr_3 data?))
		(= gGButtonBarGetCursor theCursor)
		(theGame setCursor: normalCursor)
		(SetCursor 220 105)
		(if
			(Print
				width: 200
				font: userFont
				addTitle: (gNewStr_3 data?)
				addText: (gNewStr_4 data?) 50 1
				addIcon: 1911 0 0 0 0
				addButton: 0 0 0 27 1 50 33 440
				addButton: 1 0 0 28 1 120 33 440
				init:
			)
			(recordPlayer setCycle: 0 cel: 0)
			(larryScr register: 5)
			(= local73 2)
			(theMusic2 number: 0 stop:)
		else
			(= gLarryRoom self)
			(theIconBar enableIcon: (ScriptID 0 8) show:)
		)
		(gNewStr_4 dispose:)
		(= gNewStr_4 0)
		(gNewStr_3 dispose:)
		(= gNewStr_3 0)
		(theGame setCursor: gGButtonBarGetCursor)
	)
)

(instance rightBoob of Feature
	(properties
		noun 4
		modNum 460
		y 1
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 122 115 131 106 150 108 158 122 150 133 139 138 130 137 121 123
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 4 1)
			(localproc_23b6 4 theVerb)
		else
			(closeUpInset doVerb: theVerb)
		)
	)
)

(instance leftBoob of Feature
	(properties
		noun 1
		modNum 460
		y 1
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 200 120 198 128 189 137 175 137 168 131 168 115 173 107 188 108
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 4 1)
			(localproc_23b6 1 theVerb)
		else
			(closeUpInset doVerb: theVerb)
		)
	)
)

(instance shirt of Feature
	(properties
		noun 6
		modNum 460
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						127
						76
						136
						71
						142
						88
						155
						95
						159
						96
						163
						92
						171
						92
						179
						80
						179
						68
						188
						70
						188
						104
						199
						117
						198
						132
						188
						131
						189
						133
						184
						133
						180
						128
						179
						130
						173
						129
						169
						131
						162
						127
						156
						128
						149
						131
						141
						131
						138
						135
						132
						131
						131
						132
						128
						131
						126
						135
						120
						128
						121
						115
						128
						105
						130
						90
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((not (ego has: 0)) (localproc_23b6 6 4))
					(local80 ((curRoom script?) setScript: takeOffShirtScr))
					(else
						(= local80 1)
						(titTimer setReal: titCue 5)
						(localproc_23b6 6 4 6)
					)
				)
			)
			(1 (localproc_23b6 6 theVerb))
			(5 (localproc_23b6 6 theVerb))
			(else 
				(closeUpInset doVerb: theVerb)
			)
		)
	)
)

(instance face of Feature
	(properties
		noun 8
		modNum 460
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 150 64 138 43 140 18 147 15 168 15 175 33 173 53 163 64
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (localproc_23b6 8 theVerb))
			(1
				(if local79
					(localproc_23b6 8 theVerb 4)
				else
					(= local79 1)
					(localproc_23b6 8 theVerb)
				)
			)
			(else 
				(closeUpInset doVerb: theVerb)
			)
		)
	)
)

(instance earring of Feature
	(properties
		noun 9
		modNum 460
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 136 44 143 44 143 65 136 69
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 4 1 5)
			(localproc_23b6 9 theVerb)
		else
			(closeUpInset doVerb: theVerb)
		)
	)
)

(instance rightArm of Feature
	(properties
		noun 10
		modNum 460
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						186
						67
						198
						68
						209
						81
						215
						138
						196
						138
						193
						133
						199
						122
						198
						110
						189
						104
						188
						78
						183
						67
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 4 1)
			(localproc_23b6 10 theVerb)
		else
			(closeUpInset doVerb: theVerb)
		)
	)
)

(instance leftArm of Feature
	(properties
		noun 10
		modNum 460
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 132 138 114 138 113 84 116 75 130 72 129 101 121 113 121 126
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 4 1)
			(localproc_23b6 10 theVerb)
		else
			(closeUpInset doVerb: theVerb)
		)
	)
)

(instance chest of Feature
	(properties
		noun 10
		modNum 460
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						160
						65
						171
						57
						175
						63
						181
						68
						181
						75
						172
						91
						157
						95
						145
						88
						136
						71
						145
						65
						147
						58
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 4 1)
			(localproc_23b6 10 theVerb)
		else
			(closeUpInset doVerb: theVerb)
		)
	)
)

(instance titTimer of Timer
	(properties)
	
	(method (cue)
		(localproc_23b6 0 0 7)
	)
)

(instance punchTimer of Timer
	(properties)
	
	(method (cue)
		(closeUpInset dispose:)
	)
)

(instance upperLight of Feature
	(properties
		noun 21
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						173
						88
						293
						88
						293
						85
						302
						82
						307
						82
						319
						89
						318
						104
						299
						110
						292
						106
						292
						104
						173
						104
					yourself:
				)
		)
		(super init:)
	)
)

(instance lowerLight of Feature
	(properties
		noun 21
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						174
						32
						296
						32
						296
						29
						307
						26
						319
						34
						319
						45
						302
						55
						295
						51
						295
						49
						174
						49
					yourself:
				)
		)
		(super init:)
	)
)

(instance upperLight2 of Feature
	(properties
		noun 21
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 0 32 16 23 26 28 26 32 165 32 165 49 26 49 19 53 0 48
					yourself:
				)
		)
		(super init:)
	)
)

(instance lowerLight2 of Feature
	(properties
		noun 21
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 0 90 21 80 27 88 165 88 165 104 27 104 27 107 21 110 0 100
					yourself:
				)
		)
		(super init:)
	)
)

(instance talkToCavScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(cav view: 452 setMotion: MoveTo 250 68 self)
			)
			(2
				(cav view: 442 setLoop: 0 cel: 0)
				(Load 143 460)
				(Load rsPIC 460)
				(Load rsVIEW 460)
				(if (not (Btst 62))
					(messager say: 2 1 0 1 self 440)
				else
					(messager say: 2 1 2 1 self 440)
				)
			)
			(3
				(Bclr 9)
				(theMusic2 number: 442 loop: -1 play:)
				(if global205 (proc79_7))
				(= cycles 2)
			)
			(4
				(curRoom setInset: closeUpInset self)
			)
			(5
				(if (Btst 6)
					(theGame handsOff:)
					(ego view: 98)
					(cav view: 450 setLoop: 0 posn: 239 69 cel: 0)
					(UnLoad 128 442)
					(UnLoad 128 443)
					(UnLoad 128 449)
					(UnLoad 128 445)
					(UnLoad 128 446)
					(UnLoad 128 460)
					(UnLoad 129 460)
					(UnLoad 143 440)
					(UnLoad 143 460)
					(Load rsVIEW 1906)
					(Load rsVIEW 451)
					(Load rsVIEW 441)
					(theMusic2 number: 0 stop:)
					(= ticks 120)
				else
					(self dispose:)
				)
			)
			(6 (cav setCycle: CT 5 1 self))
			(7
				(UpdateScreenItem ((ScriptID 92 3) view: 1906))
				(sfx number: 443 loop: 1 play:)
				(leftDoor setPri: 106)
				(ShakeScreen 1 1)
				(= ticks 4)
			)
			(8
				(cav cel: 6)
				(ego
					view: 451
					setLoop: 0
					cel: 0
					x: 239
					y: 69
					scaleSignal: 1
					scaleX: 12
					scaleY: 12
					setCycle: 0
				)
				(= ticks 4)
			)
			(9
				(ego x: 229 y: 68 scaleX: 21 scaleY: 21)
				(= ticks 4)
			)
			(10
				(ego x: 204 y: 69 scaleX: 46 scaleY: 46)
				(= ticks 4)
			)
			(11
				(ego x: 163 y: 69 scaleX: 80 scaleY: 80)
				(cav
					view: 452
					setLoop: 0
					setMotion: MoveTo (+ (cav x?) 10) (cav y?) cav
				)
				(= ticks 4)
			)
			(12
				(ego x: 104 y: 68 setScale: 0)
				(= ticks 4)
			)
			(13
				(sfx number: 444 loop: 1 play:)
				(ego setLoop: 1 setMotion: 0)
				(ShakeScreen 2 1)
				(= ticks 120)
			)
			(14 (localproc_2c38 1 self))
			(15
				(UpdateScreenItem ((ScriptID 92 3) view: 1900 loop: 1))
				(Bclr 6)
				(ego normalize: 441 0 posn: 239 69 setPri: 107)
				(leftDoor setPri: 170)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance theBadge of View
	(properties
		noun 7
		modNum 460
		x 155
		y 130
		priority 130
		fixPriority 1
		view 460
		loop 5
		signal $6821
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 147 139 158 125 166 125 174 135 169 139
					yourself:
				)
		)
		(super init:)
		(badgePoly init:)
	)
	
	(method (dispose)
		(badgePoly dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (localproc_23b6 7 theVerb))
			(1
				(if (not local83)
					(= local83 1)
					(localproc_23b6 7 1)
				else
					(localproc_23b6 7 1 5)
				)
			)
			(2 (localproc_23b6 7 2))
			(5
				(switch (++ local81)
					(1 (localproc_23b6 7 5 8))
					(2 (localproc_23b6 7 5 9))
					(else 
						((curRoom script?) setScript: takeBadgeScr)
					)
				)
			)
			(else 
				(closeUpInset doVerb: theVerb)
			)
		)
	)
)

(instance takeOffShirtScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 6 4 1 1 self 460)
			)
			(1
				(tits show:)
				(theBadge x: 156 y: 123)
				(= ticks 120)
			)
			(2
				(sfx number: 312 loop: 1 play:)
				(= ticks 30)
			)
			(3
				(messager say: 6 4 1 2 self 460)
			)
			(4 (= ticks 30))
			(5
				(messager say: 6 4 1 3 self 460)
			)
			(6 (= ticks 30))
			(7
				(messager say: 6 4 1 4 self 460)
			)
			(8
				(Bset 6)
				(punchTimer setCycle: punchTimer 2)
				(self dispose:)
			)
		)
	)
)

(instance recordPlayer of Prop
	(properties
		noun 6
		approachX 249
		approachY 51
		x 259
		y 55
		priority 55
		fixPriority 1
		view 440
		loop 3
		signal $6821
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (cast contains: cav)
					(messager say: 6 4 3)
				else
					(curRoom setScript: playARecordScr)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance larryScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 115 57 self)
			)
			(1
				(if (== register -2)
					(messager say: 15 4 0 0 self)
				else
					(= state 3)
					(messager say: 15 4 1 1 self)
				)
			)
			(2
				(ego
					setMotion: MoveTo (larryStep approachX?) (larryStep approachY?) self
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
			(4
				(= register 1)
				(= gLarryRoom fastForwardCue)
				(theIconBar enableIcon: (ScriptID 0 8) show:)
				(= cycles 2)
			)
			(5
				(if (OneOf (cav loop?) 4 5 6)
					(if (> howFast 3)
						(ego cycleSpeed: 6)
					else
						(ego cycleSpeed: 4)
					)
					(ego view: 443 setLoop: (- (cav loop?) 4) setCycle: Fwd)
				else
					(ego view: 441 setLoop: 8 cel: 0 setCycle: 0)
				)
				(cond 
					((and (!= (cav loop?) 8) (not (-- local77)))
						(messager say: 15 4 1 (++ register) self)
						(= local77 (Random 4 6))
					)
					((== register 3) (= local77 (= cycles 1)))
					(else (= ticks 90))
				)
				(if (< register 5) (-- state))
			)
			(6
				(if (not local73) (= local73 1))
				(if gLarryRoom
					(= gLarryRoom 0)
					(theIconBar disableIcon: (ScriptID 0 8) show:)
				)
				(ego normalize: 441 setLoop: 0 setCycle: 0 setPri: 107)
			)
			(7
				(= gGButtonBarCurIcon (ScriptID 0 3))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance takeBadgeScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(messager sayRange: 7 5 10 1 3 self 460)
			)
			(2
				(theBadge dispose:)
				(theGame changeScore: 15 210)
				(ego get: 0)
				(= seconds 4)
			)
			(3
				(messager sayRange: 7 5 10 4 7 self 460)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance badgePoly of Feature
	(properties)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init: 146 156 170 157 170 147 157 141 153 147 147 148
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(theBadge doVerb: theVerb)
	)
)

(instance playARecordScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0 [temp1 2])
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not local85)
					(= local85 1)
					(messager say: 6 0 0 0 self)
				else
					(= cycles 2)
				)
			)
			(1
				(theGame setCursor: normalCursor 1)
				(= gNewStr_3 (ByteArray new: 30))
				(Message 0 440 5 0 5 1 (gNewStr_3 data?))
				(Print
					width: 140
					font: 999
					addTitle: (gNewStr_3 data?)
					x: -1
					y: 18
					addButton: 1000 5 0 6 1 0 0 440
					addButton: 230 5 0 7 1 115 0 440
					addButton: 380 5 0 9 1 0 15 440
					addButton: 440 5 0 10 1 115 15 440
				)
				(gNewStr_3 dispose:)
				(= gNewStr_3 0)
				(if
					(= temp0
						(Print
							addButton: 442 5 0 11 1 0 30 440
							addButton: 511 5 0 12 1 115 30 440
							addButton: 1001 5 0 13 1 0 45 440
							addButton: 560 5 0 14 1 115 45 440
							addButton: 860 5 0 15 1 0 60 440
							addButton: 320 5 0 16 1 115 60 440
							addButton: 420 5 0 18 1 0 75 440
							addButton: 580 5 0 20 1 115 75 440
							addButton: 430 5 0 19 1 0 90 440
							addButton: 840 5 0 21 1 115 90 440
							addButton: 310 5 0 8 1 0 105 440
							addButton: 337 5 0 22 1 115 105 440
							init:
						)
					)
					(theMusic2 number: temp0 loop: -1 play:)
					(recordPlayer setCycle: Fwd)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance retCtrlCue of cObj
	(properties)
	
	(method (cue)
		(theGame handsOn:)
	)
)

(instance closeUpInset of Inset
	(properties
		picture 460
		noun 2
	)
	
	(method (init)
		(curRoom curPic: 460)
		(self picture: 460 plane: 0)
		(super init: &rest)
		(gGraphMenuBar state: (| (gGraphMenuBar state?) $0004))
		(if (not (Btst 62))
			(Bset 62)
			(titTimer setCycle: titTimer 4)
		else
			(theGame handsOn:)
		)
		((ScriptID 0 11) init: curRoom)
		(theIconBar disableIcon: (ScriptID 0 11) show:)
		(rightArm init:)
		(leftArm init:)
		(chest init:)
		(face init:)
		(if (not (Btst 210))
			(theBadge init: y: 130 ignoreActors: 1)
		)
		(shirt init:)
		(rightBoob init:)
		(leftBoob init:)
		(tits init: hide: ignoreActors: 1)
		(earring init:)
		(plane setRect: 0 15 319 154)
		(UpdatePlane plane)
		(FrameOut)
	)
	
	(method (dispose)
		(theMusic2 fade:)
		((ScriptID 0 11) dispose:)
		(gGraphMenuBar state: (& (gGraphMenuBar state?) $fffb))
		(curRoom curPic: 440)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0 (ByteArray new: 40))
		(cond 
			((== theVerb 2)
				(if (ego has: 0)
					(localproc_23b6 2 2 6)
				else
					(switch (++ local78)
						(1 (localproc_23b6 2 2))
						(2 (localproc_23b6 2 2 2))
						(else  (localproc_23b6 2 2 3))
					)
				)
			)
			(
			(not (Message 0 460 2 theVerb 0 1 (temp0 data?))) (localproc_23b6 2 15))
			(else (localproc_23b6 2 theVerb))
		)
		(temp0 dispose:)
	)
)

(instance cavScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= ticks 30)
				(= register 0)
				(= local72 (Random 4 6))
				(cav cel: 0 setLoop: local72)
				(localproc_0ae7 register (+ local72 440))
			)
			(1
				(if (== (++ register) (+ (cav lastCel:) 1))
					(= register 0)
					(if (not (Random 0 3)) (= local72 (Random 4 8)))
				)
				(cav loop: local72 cel: register)
				(localproc_0ae7 register (+ local72 440))
				(if (not local73) (-- state))
				(if
					(or
						(and (== local72 4) (OneOf register 0 4))
						(and (== local72 5) (OneOf register 2 5))
						(and (== local72 6) (OneOf register 2 6))
					)
					(= ticks 40)
				else
					(= ticks 1)
				)
			)
			(2
				(theGame handsOff:)
				(if gLarryRoom
					(= gLarryRoom 0)
					(theIconBar disableIcon: (ScriptID 0 8) show:)
				)
				(cav setLoop: 0 cel: 0)
				(= temp0 1)
				(while (<= temp0 10)
					([newGirl temp0] dispose:)
					(= [newGirl temp0] 0)
					(++ temp0)
				)
				(if (== local73 2)
					(Load 143 440)
					([newGirl 0] dispose:)
					(= [newGirl 0] 0)
					(= state 16)
				else
					(if (> howFast 3)
						([newGirl 0] cycleSpeed: 4)
					else
						([newGirl 0] cycleSpeed: 2)
					)
					([newGirl 0] view: 449 setLoop: 0 x: 165 y: 64 cel: 0)
				)
				(= ticks 60)
			)
			(3
				(cav
					view: 452
					setLoop: 3
					setCycle: Fwd
					setMotion: MoveTo 260 60 self
				)
				(Load rsSOUND 441)
			)
			(4
				(cav view: 442 setLoop: 9 cel: 0 setCycle: 0)
				(theMusic2 stop:)
				(sfx number: 441 loop: 1 play:)
				(= ticks 60)
			)
			(5
				(upperSpeaker cel: 0)
				(lowerSpeaker cel: 0)
				(recordPlayer setCycle: 0 cel: 0)
				(cav cel: 0)
				(= ticks 30)
			)
			(6 (cav setCycle: Beg self))
			(7
				(cav
					view: 452
					setLoop: 2
					setCycle: Fwd
					setMotion: MoveTo 260 66 self
				)
			)
			(8 (self setScript: clapScr))
			(9 (self cue:))
			(10
				(messager say: 15 4 1 6 self)
			)
			(11 (self setScript: clapScr))
			(12
				(if (> howFast 3)
					([newGirl 0] cycleSpeed: 4)
				else
					([newGirl 0] cycleSpeed: 2)
				)
				([newGirl 0]
					cel: 0
					setPri: ([newGirl 0] priority?)
					y: (- ([newGirl 0] y?) 4)
					setCycle: CT 9 1 self
				)
			)
			(13
				(leftDoor setCycle: End self)
			)
			(14
				([newGirl 0] setCycle: End self)
			)
			(15
				(cav setLoop: 1 cel: 0)
				(= ticks 10)
			)
			(16
				(if (> howFast 3)
					([newGirl 0] cycleSpeed: 6)
				else
					([newGirl 0] cycleSpeed: 3)
				)
				([newGirl 0] setLoop: 1 cel: 0 setCycle: End self)
			)
			(17
				(messager say: 0 0 17 0 self)
				(theGame changeScore: 7 209)
				(if [newGirl 0]
					([newGirl 0] dispose:)
					(= [newGirl 0] 0)
				)
				(if (!= local73 2)
					(leftDoor setCycle: Beg self)
				else
					(= cycles 2)
				)
			)
			(18)
			(19
				(ego
					normalize: 441
					setPri: 107
					setLoop: 0
					setMotion: MoveTo (- (ego x?) 20) (ego y?) self
				)
			)
			(20
				(ego setLoop: 0)
				(= local76 0)
				((curRoom script?) cue:)
				(Bclr 9)
				(self dispose:)
			)
		)
	)
)

(instance clapScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= register 3)
				(cav
					view: 442
					cel: 0
					setLoop: 0 1
					cycleSpeed: 6
					setCycle: 0
					setMotion: 0
				)
				(= ticks 10)
			)
			(1 (cav cel: 1) (= ticks 10))
			(2 (cav cel: 2) (= ticks 10))
			(3
				(cav cel: 3)
				(sfx number: 445 loop: 1 play:)
				(= ticks 10)
			)
			(4 (cav cel: 2) (= ticks 10))
			(5
				(if (-- register) (= state (- state 2)))
				(cav cel: 3)
				(sfx number: 445 loop: 1 play:)
				(= ticks 10)
			)
			(6
				(cav cycleSpeed: 10 setCycle: Beg self)
			)
			(7
				(self dispose:)
				((cav script?) cue:)
			)
		)
	)
)

(instance deathIcon of DCIcon
	(properties)
	
	(method (init)
		(if (> howFast 3)
			(= cycleSpeed 10)
		else
			(= cycleSpeed 8)
		)
		((= cycler (End new:)) init: self 0)
		(super init: &rest)
	)
	
	(method (cycle)
		(if cuees (cuees doit:))
		(switch cel
			(3
				(if (not local0)
					(= local0 1)
					(sfx number: 355 loop: 1 play:)
				)
			)
			(7
				(if (not local1)
					(= local1 1)
					(sfx number: 355 loop: 1 play:)
				)
			)
			(12
				(if (not local2)
					(= local2 1)
					(sfx number: 814 loop: 1 play:)
				)
			)
			(14
				(if (not local3)
					(= local3 1)
					(= local0 0)
					(= local1 0)
					(= local2 0)
					(sfx number: 0 stop:)
					(DoAudio 2 82 2 0 1 1)
				)
			)
		)
		(super cycle:)
	)
)

(instance frameIcon of DCIcon
	(properties
		view 1850
	)
	
	(method (init)
		(super init: &rest)
		(cycler dispose:)
		(= cycler 0)
	)
)
