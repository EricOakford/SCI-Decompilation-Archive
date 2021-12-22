;;; Sierra Script 1.0 - (do not remove this comment)
(script# 630)
(include sci.sh)
(use Main)
(use n078)
(use fileScr)
(use EgoDead)
(use LarryRoom)
(use rm740)
(use Print)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm630 0
)

(local
	local0
	local1 =  3
	local2
	local3
	local4
	local5
	local6 =  -1
	[newActor 7]
	[local14 7] = [0 2 4 6 8 10 12]
	[local21 7] = [132 120 100 112 97 86 31]
	[local28 28] = [88 85 74 82 78 71 63 1 4 2 8 8 4 -1 1 2 3 2 2 1 -1 -1 52 48 106 56 52 40]
	local56 =  -1
	local57
	local58
	local59
	local60
	local61
	local62
	local63
	local64
	local65 =  -1
	local66
	local67 =  5
)
(class cObj of Obj
	(properties
		scratch 0
	)
)

(instance rm630 of LarryRoom
	(properties
		noun 7
		picture 630
		horizon 0
	)
	
	(method (init &tmp [temp0 2])
		(Bset 77)
		(if global100 (= local0 2) else (= local0 4))
		(if
			(and
				(== prevRoomNum 620)
				(Btst 52)
				(or
					(and (Btst 34) (not (Btst 12)))
					(and (Btst 80) (Btst 79))
				)
			)
			(markus init: approachVerbs: 4 2 5 6)
			(if (Btst 79)
				(markus
					view: 639
					setLoop: 0 1
					setCycle: Fwd
					x: 202
					y: 93
					setPri: 150
					setScript: workOnToiletScr
				)
			else
				(markus setScript: workOnSinkScr)
			)
			(= local57 1)
			(Bset 54)
			(Bset 101)
			(self
				addObstacle:
					((Polygon new:)
						type: 3
						init: 183 71 183 85 236 85 223 71
						yourself:
					)
			)
		else
			(self
				addObstacle:
					((Polygon new:)
						type: 3
						init: 183 71 167 101 243 101 223 71
						yourself:
					)
			)
		)
		(if (== prevRoomNum 860)
			(= style 9)
			(theDoor
				init:
				cel: 0
				init:
				approachVerbs: 4
				ignoreActors: 1
			)
			(ego
				x: (sink approachX?)
				y: (sink approachY?)
				init:
				view: 635
				setLoop: 7 1
				cycleSpeed: 6
				cel: 3
				actions: egoActions
			)
			(Bset 50)
			(Bclr 52)
			(self setScript: afterShabScr)
		else
			(ego
				x: 205
				y: 58
				init:
				normalize: 636 2 1
				setPri: 70
				actions: egoActions
			)
			(theDoor init: approachVerbs: 4 ignoreActors: 1)
			(self setScript: enterDoorScr)
		)
		(super init:)
		(if (== prevRoomNum 860) (theGame drawControls:))
		(theMusic2 number: 0 stop:)
		(if
			(or
				(not (== (theMusic number?) 620))
				(not (theMusic handle?))
			)
			(theMusic number: 620 loop: -1 setVol: 100 play:)
		else
			(theMusic fade: 100 10 10 0)
		)
		(flowers init:)
		(toiletCovers init:)
		(upperToilet init: approachVerbs: 4 61 6)
		(lowerToilet init: approachVerbs: 4 61 6)
		(lowerToilet3 init: approachVerbs: 4 61 6)
		(shower init:)
		(sink init: approachVerbs: 4 57 35)
		(rWindow init:)
		(lWindow init:)
		(towelHandle init:)
		(mirror1 init:)
		(mirror init:)
		(mirror2 init:)
		(fWindow init:)
		(fWindow2 init:)
		(Load rsSCRIPT 1800)
		(Load rsVIEW 635)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				local66
				(== (ego view?) 635)
				(== (ego loop?) 5)
				(== (ego cel?) 2)
			)
			(jerkSound play:)
			(if (not (-- local67))
				(ego cycleSpeed: (Random 1 3))
				(= local67 (Random 5 10))
			)
		)
	)
	
	(method (newRoom n)
		(if
			(and
				local57
				(not (Btst 12))
				(Btst 34)
				(== (markus script?) workOnToiletScr)
			)
			(Bclr 54)
			(Bclr 79)
		)
		(yuckTimer dispose: delete:)
		(fartTimer dispose: delete:)
		(if (!= n 620) (theMusic fade: 0 10 10 1))
		(ego setScaler: 0)
		(super newRoom: n)
	)
)

(instance bedView of View
	(properties
		x 179
		y 5
		priority 40
		fixPriority 1
		view 637
		signal $6821
	)
)

(instance gasket of View
	(properties
		x 186
		y 94
		priority 106
		fixPriority 1
		view 630
		loop 5
		signal $6821
	)
	
	(method (doVerb theVerb)
		(egoActions doVerb: theVerb)
	)
)

(instance egoHead of View
	(properties
		signal $6821
	)
	
	(method (doVerb theVerb)
		(egoActions doVerb: theVerb)
	)
)

(instance theDoor of Prop
	(properties
		noun 14
		approachX 207
		approachY 65
		x 204
		y 63
		priority 65
		fixPriority 1
		view 630
		cel 5
		signal $6821
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not (curRoom script?))
					(curRoom setScript: exitDoorScr)
				else
					(messager say: 14 4 2)
				)
			)
			(1
				(if (and (== (-- local1) 1) local4)
					(sfx number: 1004 loop: 1 play:)
					(yuckTimer setReal: yuckTimer 3)
				else
					(messager say: 14 1)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sinkWater of Prop
	(properties
		x 156
		y 63
		priority 63
		fixPriority 1
		view 630
		loop -1
		signal $6821
	)
	
	(method (init)
		(if (Btst 12) (= loop 2) else (= loop 1))
		(runningWaterFx number: 634 loop: -1 play:)
		(self setCycle: Fwd)
		(super init:)
	)
	
	(method (dispose)
		(runningWaterFx stop:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(sink doVerb: theVerb)
	)
)

(instance lWindow of Feature
	(properties
		noun 3
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 0 0 105 0 150 95 90 95 87 99 112 127 112 139 0 139
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance larry of Feature
	(properties)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init:
						190
						98
						188
						91
						198
						75
						189
						67
						189
						60
						193
						55
						202
						53
						211
						55
						216
						61
						216
						67
						209
						77
						214
						81
						212
						85
						216
						80
						215
						95
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(egoActions doVerb: theVerb)
	)
)

(instance rWindow of Feature
	(properties
		noun 8
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 262 0 319 0 319 60 303 96 268 96
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance fWindow of Feature
	(properties
		noun 1
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						163
						127
						166
						119
						224
						110
						224
						117
						222
						120
						221
						128
						218
						132
						161
						132
						153
						139
						112
						139
						112
						127
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance fWindow2 of Feature
	(properties
		noun 1
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						247
						139
						243
						132
						237
						133
						236
						129
						239
						121
						243
						121
						243
						124
						247
						127
						251
						126
						252
						133
						256
						129
						255
						122
						260
						126
						263
						118
						270
						117
						273
						112
						268
						107
						267
						107
						269
						103
						269
						97
						295
						97
						277
						139
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance shower of Feature
	(properties
		noun 9
		sightAngle 10
		x 250
		y 41
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 255 0 255 88 228 62 228 58 230 55 231 49 226 45 225 41 231 39 234 0
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance toiletCovers of Feature
	(properties
		noun 11
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 91 96 152 96 164 121 163 126 113 127 90 100
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance flowers of Feature
	(properties
		noun 6
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						214
						87
						222
						88
						223
						84
						230
						84
						232
						89
						237
						83
						243
						87
						247
						87
						247
						95
						266
						96
						266
						107
						272
						114
						269
						117
						264
						116
						263
						123
						259
						126
						254
						121
						256
						130
						251
						131
						250
						126
						245
						126
						242
						122
						239
						121
						237
						128
						238
						135
						236
						139
						217
						139
						217
						133
						221
						127
						222
						120
						224
						118
						225
						109
						221
						107
						219
						104
						215
						104
						214
						101
						215
						99
						216
						93
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance mirror of Feature
	(properties
		noun 5
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 126 22 152 48 142 57
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance mirror1 of Feature
	(properties
		noun 5
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 106 0 116 0 168 107 187 104 187 115 163 119
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance mirror2 of Feature
	(properties
		noun 5
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 254 0 262 0 267 96 259 96 248 94 246 90 255 89
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance towelHandle of Feature
	(properties
		noun 13
		sightAngle 10
		x 148
		y 35
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 139 31 149 25 158 37 145 42
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(4
				(cond 
					(local4 (messager say: 13 4 17))
					((not (-- local0))
						(ego get: 38 36 37 19 6 39)
						(while (== (= temp0 (Random 1 61)) 9)
						)
						(DoAudio 2 611 1 4 temp0 1)
						(Print
							width: 160
							font: userFont
							addTitle: {Carlos, are you cheating again?}
							addText: 1 4 temp0 1 50 1 611
							fore: 67
							addText:
								{You now have the towel, toilet cover, toilet paper, hand creme, brochure and the wash cloth.}
								50
								17
							fore: 0
							addIcon: 1592 1 0 0 0
							init:
						)
					)
					(else (super doVerb: theVerb))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance enterDoorScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setScaler: Scaler 100 72 69 53)
				(if (and (Btst 240) (Btst 68)) (bedView init:))
				(= cycles 2)
			)
			(1
				(if (and (not local57) (Btst 50))
					(sinkWater init: ignoreActors: 1)
				)
				(ego setMotion: MoveTo (ego x?) 72 self)
			)
			(2
				(ego setPri: 70)
				(ego setHeading: 360 self)
			)
			(3 (theDoor setCycle: Beg self))
			(4
				(doorFx number: 33 loop: 1 play:)
				(= ticks 90)
			)
			(5
				(if local57
					(ego setMotion: MoveTo 205 79 self)
				else
					(ego setHeading: 225 self)
				)
			)
			(6
				(if local57
					(theGame handsOn:)
					(self dispose:)
				else
					(ego setMotion: MoveTo 191 87 self)
				)
			)
			(7
				(ego setScaler: 0 setHeading: 270 self)
			)
			(8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance runningWaterFx of Sound
	(properties
		flags $0005
	)
)

(instance doorFx of Sound
	(properties
		flags $0005
	)
)

(instance upperToilet of Feature
	(properties
		noun 12
		sightAngle 10
		approachX 200
		approachY 100
		x 200
		y 104
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						187
						115
						187
						100
						191
						94
						200
						92
						209
						93
						217
						95
						214
						97
						215
						101
						219
						103
						219
						107
						223
						108
						223
						111
						220
						115
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(if local4
			(egoActions doVerb: theVerb)
		else
			(switch theVerb
				(4
					(cond 
						(local57 (messager say: 12 4 7))
						(
						(and (> local60 1) (not local61) (not (Btst 79)))
							(sfx number: 633 loop: 1 play:)
							(= local61 1)
							(Bset 79)
							(messager say: 12 4 24)
						)
						((Btst 79) (sfx number: 633 loop: 1 play: toiletCue))
						(else
							(= local61 1)
							(= local60 0)
							(sfx number: 633 loop: 1 play:)
							(messager say: 12 4)
						)
					)
				)
				(61
					(cond 
						((Btst 79) (messager say: 12 61 24))
						(local57 (messager say: 12 61 7))
						(local4 (messager say: 12 61 17))
						(else (curRoom setScript: useToiletScr))
					)
				)
				(62
					(cond 
						((Btst 79) (messager say: 12 62 24))
						(local57 (messager say: 12 62 7))
						(else (curRoom setScript: plugToiletScr))
					)
				)
				(1
					(cond 
						((Btst 79) (messager say: 12 1 24))
						((ego has: 36) (messager say: 12 1 15))
						(else (messager say: 12 1))
					)
				)
				(6
					(cond 
						((Btst 79) (messager say: 12 6 24))
						(local57 (messager say: 12 6 7))
						(else (curRoom setScript: pissScr))
					)
				)
				(35
					(theGame changeScore: 6 250)
					(= global185 4)
					((inventory at: 39) cue:)
					(messager say: 2 35 0 0 0 85)
				)
				(37
					(messager say: 2 37 0 0 0 85)
				)
				(36
					(messager say: 2 36 0 0 0 85)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance lowerToilet of Feature
	(properties
		noun 12
		approachX 201
		approachY 100
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 237 133 244 133 248 140 236 140
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(upperToilet doVerb: theVerb)
	)
)

(instance lowerToilet3 of Feature
	(properties
		noun 12
		approachX 201
		approachY 100
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 154 139 160 132 218 132 216 135 218 139
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(upperToilet doVerb: theVerb)
	)
)

(instance yuckTimer of Timer
	(properties)
	
	(method (cue)
		(if
			(and
				(not local66)
				local4
				(== (curRoom script?) useToiletScr)
			)
			(cond 
				((fartTimer seconds?)
					(fartTimer seconds: 0 dispose: delete:)
					(self setReal: self 3)
				)
				(
					(and
						(or (talkers size:) (Print dialog?))
						(Print dialog?)
					)
					(self setReal: self 3)
				)
				(else ((curRoom script?) setScript: yuckScr))
			)
		)
	)
)

(instance fartTimer of Timer
	(properties)
	
	(method (cue)
		(if
			(and
				local4
				(== (curRoom script?) useToiletScr)
				(not local66)
				(!= (ego view?) 636)
			)
			(if
				(and
					(or (talkers size:) (Print dialog?))
					(Print dialog?)
				)
				(self setReal: self 3)
			else
				((curRoom script?) setScript: fartScr)
			)
		)
	)
)

(instance crowdScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== register local56)
					(= ticks 10)
				else
					(= cycles 2)
				)
			)
			(1
				(if (== register local56)
					(client
						setMotion: MoveTo [local21 register] [local28 register] self
					)
				else
					(client setMotion: MoveTo 66 65 self)
				)
			)
			(2
				(if (== register local56)
					(client cel: 0 setCycle: 0)
					(= ticks 30)
				else
					(client
						setMotion: MoveTo [local21 register] [local28 register] self
					)
				)
			)
			(3
				(if (== register local56)
					(= local56 -1)
					(= local6 -1)
					((ego script?) cue:)
					(self dispose:)
				else
					(client
						setLoop: (+ (client loop?) 1) 1
						cel: 0
						setCycle: 0
					)
					(= cycles 2)
				)
			)
			(4 (self dispose:))
		)
	)
)

(instance wetClothScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 635 setLoop: 1 1 cycleSpeed: 8 setCycle: Fwd)
				(= ticks 120)
			)
			(1
				(if (not (Btst 12))
					(= global185 8)
				else
					(= global185 0)
				)
				(theGame changeScore: 6 250)
				(messager say: 2 35 0 0 self 85)
			)
			(2
				((inventory at: 39) cue:)
				(ego normalize: 636 1 1 setPri: 70)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance talkToMarkScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not local62)
					(= local62 1)
					(if (== (markus script?) workOnSinkScr)
						(++ state)
						(messager sayRange: 4 2 26 1 8 self)
					else
						(messager say: 4 2 27 0 self)
					)
				else
					(messager say: 4 2 23 0 self)
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
			(2 (= seconds 3))
			(3
				(messager say: 4 2 26 9 self)
			)
			(4 (= seconds 3))
			(5
				(messager say: 4 2 26 10 self)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance plugToiletScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 79)
				(messager say: 12 62 16 1 self)
			)
			(1 (= seconds 2))
			(2
				(messager say: 12 62 16 2 self)
			)
			(3
				(sfx number: 814 loop: 1 play: self)
				(ego put: 37)
			)
			(4
				(messager say: 12 62 16 3 self)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance jerkOffScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 635 setLoop: 5 1 cycleSpeed: 4 cel: 0)
				(messager say: 2 54 2 1 self)
			)
			(1
				(= local66 1)
				(ego setCycle: Fwd)
				(= ticks 180)
			)
			(2
				(if (< (++ local6) 6)
					(-- state)
				else
					(= local56 local6)
				)
				((= [newActor local6] (Actor new:))
					view: 638
					setLoop: [local14 local6] 1
					x: 0
					y: 62
					setStep: 1 1
					cycleSpeed: 4
					moveSpeed: 4
					ignoreActors: 1
					init:
					setCycle: Fwd
					setScript: (crowdScr new:) 0 local6
				)
				(if (== local6 5) (= ticks 300) else (= ticks 180))
			)
			(3)
			(4
				(= local66 0)
				(sfx number: 141 loop: 1 play:)
				(UpdateScreenItem ((ScriptID 92 3) view: 1905))
				(messager say: 2 54 2 2 self)
			)
			(5
				(EgoDead 18 self)
				(= temp1 0)
				(while (< temp1 7)
					([newActor temp1] dispose:)
					(= [newActor temp1] 0)
					(++ temp1)
				)
			)
			(6
				(UpdateScreenItem ((ScriptID 92 3) view: 1900 loop: 1))
				((curRoom script?) dispose:)
				(egoHead dispose:)
				(gasket dispose:)
				(ego normalize: 636 2 1 setPri: 70)
				(= local4 0)
				(= local58 0)
				((ScriptID 0 11) dispose:)
				(theDoor approachVerbs: 4)
				(sink approachVerbs: 4 35 57)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance markus of Prop
	(properties
		noun 4
		approachX 200
		approachY 85
		x 195
		y 100
		priority 100
		view 632
		signal $6821
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(cond 
					(
					(and (not (ego has: 41)) (not (ego has: 12)))
						(ego get: 41)
						(theGame changeScore: 8 248)
						(messager say: 4 5 5)
					)
					((not (ego has: 12))
						(ego get: 12)
						(theGame changeScore: 8 249)
						(messager say: 4 5 6)
					)
					(else (messager say: 4 5 3))
				)
			)
			(2
				(curRoom setScript: talkToMarkScr)
			)
			(9
				(ego put: 3)
				(messager say: 4 9)
			)
			(1
				(if (== (markus script?) workOnSinkScr)
					(messager say: 4 1 26)
				else
					(messager say: 4 1 27)
				)
			)
			(6
				(curRoom setScript: zipperOnMarkScr)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance egoActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(cond 
				((OneOf theVerb 56 48) (messager say: 2 theVerb) (return 1))
				(local4
					(switch theVerb
						(54
							(if (and local4 (not local58))
								(ego view: 635 setLoop: 5 1 cel: 0)
								(messager say: 2 54 20)
								(= local58 1)
								(return 1)
							else
								(super doVerb: theVerb)
							)
						)
						(59
							(cond 
								((and local4 (not local58)) (messager say: 2 59 19) (return 1))
								((and local4 local58) (ego setScript: jerkOffScr) (return 1))
								(else (super doVerb: theVerb))
							)
						)
						(62
							(if local4
								(ego setScript: wipeAssScr)
								(return 1)
							else
								(super doVerb: theVerb)
							)
						)
						(else  (ego doVerb: theVerb))
					)
				)
				(else (super doVerb: theVerb))
			)
		)
	)
)

(instance toiletCue of cObj
	(properties)
	
	(method (cue)
		(messager say: 12 4 24)
	)
)

(instance sink of Feature
	(properties
		noun 10
		sightAngle 0
		approachX 182
		approachY 83
		x 151
		y 74
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						149
						59
						157
						59
						158
						58
						171
						58
						174
						67
						168
						76
						173
						84
						170
						89
						160
						86
						154
						80
						149
						68
						152
						65
						148
						63
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(cond 
					(
					(and (not (cast contains: sinkWater)) (Btst 12)) (messager say: 10 1 13))
					((and (cast contains: sinkWater) (Btst 12)) (messager say: 10 1 10))
					(
					(and (cast contains: sinkWater) (not (Btst 12))) (messager say: 10 1 14))
					((not local2) (= local2 1) (messager say: 10 1 11))
					(else (messager say: 10 1 12))
				)
			)
			(4
				(cond 
					(local57
						(if (== (markus script?) workOnSinkScr)
							(messager say: 10 4 7)
						else
							(messager say: 10 4 27)
						)
					)
					(local4 (messager say: 10 4 2))
					((cast contains: sinkWater) (curRoom setScript: turnOffWaterScr))
					(else (curRoom setScript: turnOnWaterScr))
				)
			)
			(35
				(cond 
					((not (cast contains: sinkWater)) (messager say: 10 35 8))
					(local4 (messager say: 10 35 14))
					(else (curRoom setScript: washHandsScr 0 1))
				)
			)
			(37
				(messager say: 2 37 0 0 0 85)
			)
			(36
				(messager say: 2 36 0 0 0 85)
			)
			(6
				(if local57
					(messager say: 10 6 7)
				else
					(super doVerb: theVerb)
				)
			)
			(57
				(cond 
					((not (cast contains: sinkWater)) (messager say: 10 57 8))
					((not (Btst 12)) (messager say: 10 57 14))
					(else (curRoom setScript: washHandsScr 0 0))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance washHandsScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if local5 (theGame changeScore: 2 247))
				(= ticks 30)
			)
			(1
				(if (!= (ego heading?) 270)
					(ego setHeading: 270 self)
				else
					(= cycles 2)
				)
			)
			(2 (= cycles 2))
			(3
				(ego view: 635 setLoop: 1 1 setCycle: Fwd cycleSpeed: 6)
				(= ticks 300)
			)
			(4
				(ego setLoop: 2 1 cel: 0 setCycle: 0)
				(= ticks 30)
			)
			(5 (ego setCycle: End self))
			(6
				(if register
					(ego setLoop: 3 1 setCycle: Fwd)
					(= ticks 300)
				else
					(self cue:)
				)
			)
			(7
				(ego setLoop: 2 1 setCel: 255)
				(= ticks 30)
			)
			(8
				(if (not register)
					(ego put: 34)
					(= state (+ state 2))
				)
				(ego setCycle: Beg self)
			)
			(9
				(if (not (Btst 12))
					(= global185 8)
				else
					(= global185 0)
				)
				(messager say: 2 35 0 0 self 85)
			)
			(10
				(theGame changeScore: 6 250)
				((inventory at: 39) cue:)
				(= cycles 2)
			)
			(11
				(ego normalize: 636 1 1 setPri: 70)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance fartScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 30)
			)
			(1
				(sfx number: 631 loop: 1 play:)
				(= ticks 90)
			)
			(2
				(messager say: 0 0 28 0 self)
			)
			(3
				(theGame handsOn:)
				(yuckTimer setReal: yuckTimer 10)
				(self dispose:)
			)
		)
	)
)

(instance wipeAssScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff: changeScore: 1 251)
				(= local4 0)
				(= register 2)
				(messager say: 2 62 2 0 self)
			)
			(1
				(ego
					view: 631
					setLoop: 0 1
					cel: 0
					cycleSpeed: 10
					setCycle: CT 2 1 self
				)
			)
			(2 (= ticks (Random 30 60)))
			(3 (ego setCycle: End self))
			(4 (= ticks (Random 30 60)))
			(5
				(ego view: 631 setLoop: 1 1 cel: 0 setCycle: End self)
			)
			(6
				(sfx number: 637 loop: 1 play:)
				(ego cel: 0 setCycle: End self)
			)
			(7
				(sfx number: 637 loop: 1 play:)
				(cond 
					((-- register) (= state 0) (ego setCycle: Beg self))
					((not (Random 0 2)) (messager say: 6 4 0 2 self 620))
					(else (self cue:))
				)
			)
			(8
				(ego setCycle: Beg useToiletScr)
				(Bclr 55)
				(self dispose:)
			)
		)
	)
)

(instance afterShabScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(UpdateScreenItem ((ScriptID 92 3) view: 1906))
				(= register (= cycles 2))
			)
			(1
				(sinkWater init: ignoreActors: 1)
				(curRoom showControls: 1)
				(theGame controlsVisible: 0 drawControls:)
				(gargleSound number: 635 loop: -1 play:)
				(= cycles 2)
			)
			(2 (ego setCycle: CT 8 1 self))
			(3 (ego setCycle: CT 3 -1 self))
			(4 (ego setCycle: CT 12 1 self))
			(5
				(ego setCycle: End self)
				(gargleSound number: 0 stop:)
				(sfx number: 636 loop: 1 play:)
				(switch (-- register)
					(1 (messager say: 0 0 1 1 self))
					(0 (messager say: 0 0 1 2 self))
				)
			)
			(6)
			(7
				(ego cel: 0)
				(if (not register)
					(UpdateScreenItem ((ScriptID 92 3) view: 1900 loop: 1))
					(= next turnOffWaterScr)
					(self dispose:)
				else
					(= ticks 90)
				)
			)
			(8 (ego setCycle: CT 3 1 self))
			(9
				(gargleSound number: 635 loop: -1 play:)
				(= state 1)
				(self cue:)
			)
		)
	)
)

(instance useToiletScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff: changeScore: 2 245)
				(++ local60)
				(if (> (++ local5) 3)
					(messager say: 0 0 18 0 self)
				else
					(= local61 0)
					(messager say: 12 61 16 0 self)
				)
			)
			(1
				(if (> local5 3)
					(theGame handsOn:)
					(self dispose:)
				else
					(proc79_11 814)
					(Bset 55)
					(Bclr 106)
					(Bclr 107)
					(Bclr 108)
					(Bclr 109)
					(= local4 1)
					(theDoor approachVerbs: 0)
					(sink approachVerbs: 0)
					(ego setHeading: 360 self)
				)
			)
			(2 (= ticks 30))
			(3
				(gasket init:)
				(ego
					view: 635
					setLoop: 0 1
					cel: 0
					setCycle: 0
					cycleSpeed: 8
					x: 202
					y: 101
					setCycle: CT 2 1 self
				)
			)
			(4
				(ego setPri: 150 setCycle: CT 4 1 self)
			)
			(5
				(ego cel: 5)
				(sfx number: 814 loop: 1 play: self)
				(egoHead
					view: 635
					setLoop: 4 1
					cel: 1
					x: 198
					y: 140
					ignoreActors: 1
					init:
				)
			)
			(6
				(sfx number: 0 dispose:)
				(proc79_12 814)
				(larry init:)
				(egoHead cel: 2)
				(theGame handsOn:)
				(fartTimer setReal: fartTimer 12)
				((ScriptID 0 11) init: self)
			)
			(7
				((ScriptID 0 11) dispose:)
				(theGame handsOff:)
				(ego view: 635 setLoop: 0 1 setCycle: 0 cel: 4)
				(egoHead cel: 1)
				(= cycles 2)
			)
			(8
				(gasket dispose:)
				(egoHead cel: 0)
				(ego setCycle: Beg self)
			)
			(9
				(ego normalize: 636 3 1 setPri: 70)
				(= ticks 30)
			)
			(10 (ego setHeading: 180 self))
			(11
				(if (Btst 55)
					(theGame changeScore: -2 246)
					(messager say: 2 3 2 0 self)
				else
					(= cycles 2)
				)
			)
			(12
				(theGame handsOn:)
				(= local4 0)
				(= local58 0)
				(theDoor approachVerbs: 4)
				(sink approachVerbs: 4 35 57)
				(egoHead dispose:)
				(larry dispose:)
				(self dispose:)
			)
		)
	)
)

(instance turnOffWaterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local59 1)
				(if
					(and
						(!= (ego x?) (sink approachX?))
						(!= (ego y?) (sink approachY?))
					)
					(ego
						setMotion: PolyPath (sink approachX?) (sink approachY?) self
					)
				else
					(= cycles 2)
				)
			)
			(1
				(if (!= (ego heading?) 270)
					(ego setHeading: 270 self)
				else
					(= cycles 2)
				)
			)
			(2 (= cycles 2))
			(3
				(ego
					view: 635
					setCycle: 0
					setLoop: 6 1
					cel: 0
					cycleSpeed: 8
					setCycle: CT 3 1 self
				)
			)
			(4 (ego cel: 5) (= ticks 20))
			(5
				(ego cel: 4)
				(sfx number: 355 loop: 1 play:)
				(= ticks 20)
			)
			(6 (ego cel: 5) (= ticks 20))
			(7
				(ego cel: 4)
				(sfx number: 355 loop: 1 play: self)
			)
			(8
				(runningWaterFx stop:)
				(Bclr 50)
				(if (cast contains: sinkWater) (sinkWater dispose:))
				(ego setCycle: End self)
			)
			(9
				(ego normalize: 636 1 1 setPri: 70)
				(= local59 0)
				(= ticks 60)
			)
			(10
				(= cycles 2)
				(if (and (not local63) (== prevRoomNum 860))
					(= local63 1)
					(proc78_0)
				)
			)
			(11
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance turnOnWaterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local59 1)
				(ego
					setMotion: PolyPath (sink approachX?) (sink approachY?) self
				)
			)
			(1
				(if (!= (ego heading?) 270)
					(ego setHeading: 270 self)
				else
					(= cycles 2)
				)
			)
			(2 (= cycles 2))
			(3
				(ego
					view: 635
					setCycle: 0
					setLoop: 6 1
					cel: 0
					cycleSpeed: 6
					setCycle: CT 3 1 self
				)
			)
			(4 (ego cel: 4) (= ticks 30))
			(5
				(ego cel: 5)
				(sfx number: 355 loop: 1 play:)
				(= ticks 20)
			)
			(6
				(Bset 50)
				(sinkWater init:)
				(= ticks 20)
			)
			(7 (ego cel: 4) (= ticks 30))
			(8
				(ego cel: 5)
				(sfx number: 355 loop: 1 play:)
				(= ticks 30)
			)
			(9
				(if (not (Btst 12))
					(messager say: 10 1 14 0 self)
				else
					(= cycles 2)
				)
			)
			(10 (ego setCycle: End self))
			(11
				(ego normalize: 636 1 1 setPri: 70)
				(Bset 19)
				(= local59 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance gargleSound of Sound
	(properties)
)

(instance sfx of Sound
	(properties)
)

(instance sfx2 of Sound
	(properties)
)

(instance jerkSound of Sound
	(properties
		number 632
	)
)

(instance exitDoorScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 30)
			)
			(1 (ego setHeading: 360 self))
			(2
				(if (and (Btst 240) (Btst 68)) (bedView init:))
				(= cycles 2)
			)
			(3
				(doorFx number: 32 loop: 1 play:)
				(theDoor setCycle: End self)
			)
			(4
				(theMusic fade: 127 10 10 0)
				(ego
					setScaler: Scaler 100 72 69 53
					setMotion: MoveTo 205 58 self
				)
			)
			(5 (curRoom newRoom: 620))
		)
	)
)

(instance pissScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(++ local64)
				(if (!= (ego heading?) 180)
					(ego setHeading: 180 self)
				else
					(self cue:)
				)
			)
			(1 (= cycles 2))
			(2
				(if (== local64 1)
					(messager say: 12 6 0 0 self)
				else
					(self cue:)
				)
			)
			(3
				(ego
					view: 634
					setLoop: 0 1
					cycleSpeed: 8
					cel: 0
					setCycle: CT 2 1 self
				)
			)
			(4
				(sfx number: 35 loop: 1 play: self)
				(ego setCycle: End self)
			)
			(5)
			(6
				(switch local64
					(1
						(sfx2 number: 30 loop: -1 play:)
						(= ticks 200)
						(ego setPri: 110 setLoop: 1 1 cycleSpeed: 4 setCycle: Fwd)
						(= register 3)
					)
					(2 (= ticks 30) (= register 2))
					(else 
						(= state 8)
						(= ticks 90)
					)
				)
			)
			(7
				(sfx2 number: 0 stop:)
				(ego setLoop: 2 1 cel: 0 setCycle: End self)
			)
			(8
				(if (-- register) (= state (- state 2)))
				(sfx number: 351 loop: 1 play: self)
			)
			(9
				(if (>= local64 3)
					(messager say: 12 6 25 0 self)
				else
					(self cue:)
				)
			)
			(10
				(ego
					setLoop: 0 1
					setCycle: 0
					cel: 10
					cycleSpeed: 8
					setCycle: CT 3 -1 self
				)
			)
			(11
				(ego setCycle: Beg self)
				(sfx number: 35 loop: 1 play:)
			)
			(12
				(theGame changeScore: 2 252 handsOn:)
				(ego normalize: 636 2 1 setPri: 70)
				(self dispose:)
			)
		)
	)
)

(instance yuckScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc79_11 631 30)
				(= ticks 30)
			)
			(1
				(sfx number: 631 loop: 1 play:)
				(= ticks 60)
			)
			(2
				(sfx2 number: 30 loop: -1 play:)
				(= ticks 120)
			)
			(3
				(sfx2 number: 0 stop:)
				(messager say: 0 0 28 0 self)
			)
			(4
				(sfx number: 0 dispose:)
				(sfx2 number: 0 dispose:)
				(proc79_12 631 30)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance workOnToiletScr of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== (markus loop?) 0)
				(== (markus cel?) 1)
				(not (sfx handle?))
			)
			(sfx number: 328 loop: 1 play:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 10)))
			(1
				(markus setLoop: 1 1 setCycle: 0 cel: 0)
				(= ticks 30)
			)
			(2 (markus setCycle: End self))
			(3
				(markus setLoop: 2 1 cel: 0 setCycle: End self)
			)
			(4
				(sfx number: 814 loop: 1 play:)
				(= ticks 180)
			)
			(5
				(switch (Random 0 5)
					(1
						(sfx number: 631 loop: 1 play:)
						(= ticks 60)
					)
					(2
						(sfx number: 516 loop: 1 play:)
						(= ticks 60)
					)
					(else  (self cue:))
				)
			)
			(6
				(markus setLoop: 3 1 cel: 0 setCycle: End self)
			)
			(7
				(markus setLoop: 0 1 setCycle: Fwd)
				(self init:)
			)
		)
	)
)

(instance zipperOnMarkScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 198 85 self)
			)
			(1 (ego setHeading: 180 self))
			(2 (= cycles 2))
			(3
				(ego
					view: 634
					setLoop: 3 1
					cel: 0
					cycleSpeed: 8
					setCycle: CT 2 1 self
				)
			)
			(4
				(sfx2 number: 35 loop: 1 play:)
				(ego setCycle: End self)
			)
			(5 (messager say: 4 6 0 0 self))
			(6 (ego setCycle: CT 2 -1 self))
			(7
				(sfx2 number: 35 loop: 1 play:)
				(ego setCycle: Beg self)
			)
			(8
				(ego normalize: 636 2 1 setPri: 70)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance workOnSinkScr of Script
	(properties)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_30e6
			pushi    #setLoop
			pushi    2
			pushi    0
			pushi    1
			pushi    16
			pushi    1
			pushi    0
			pushi    236
			pushi    2
			class    End
			push    
			pushSelf
			lofsa    markus
			send     22
			jmp      code_3619
code_30e6:
			dup     
			ldi      1
			eq?     
			bnt      code_3135
			pushi    #contains
			pushi    1
			lofsa    sinkWater
			push    
			lag      cast
			send     6
			bnt      code_3119
			pushi    #setLoop
			pushi    2
			pushi    3
			pushi    1
			pushi    16
			pushi    1
			pushi    0
			pushi    236
			pushi    2
			class    End
			push    
			pushSelf
			lofsa    markus
			send     22
			jmp      code_3128
code_3119:
			ldi      5
			aTop     state
			pushi    2
			pushi    30
			pushi    120
			callk    Random,  4
			aTop     ticks
code_3128:
			pushi    2
			pushi    2
			pushi    5
			callk    Random,  4
			aTop     register
			jmp      code_3619
code_3135:
			dup     
			ldi      2
			eq?     
			bnt      code_315d
			pushi    #contains
			pushi    1
			lofsa    sinkWater
			push    
			lag      cast
			send     6
			bnt      code_3156
			pushi    #dispose
			pushi    0
			lofsa    sinkWater
			send     4
code_3156:
			ldi      2
			aTop     cycles
			jmp      code_3619
code_315d:
			dup     
			ldi      3
			eq?     
			bnt      code_31bc
			pushi    #setLoop
			pushi    2
			pushi    4
			pushi    1
			pushi    266
			pushi    1
			pushi    6
			pushi    16
			pushi    1
			pushi    0
			pushi    236
			pushi    2
			class    End
			push    
			pushSelf
			lofsa    markus
			send     28
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_3199
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_3199
code_3199:
			not     
			bnt      code_31b0
			pushi    #number
			pushi    1
			pushi    355
			pushi    15
			pushi    1
			pushi    1
			pushi    51
			pushi    0
			lofsa    sfx
			send     16
code_31b0:
			dpToa    register
			bnt      code_3619
			ldi      1
			aTop     state
			jmp      code_3619
code_31bc:
			dup     
			ldi      4
			eq?     
			bnt      code_31df
			pushi    #setLoop
			pushi    2
			pushi    5
			pushi    1
			pushi    16
			pushi    1
			pushi    0
			pushi    236
			pushi    2
			class    End
			push    
			pushSelf
			lofsa    markus
			send     22
			jmp      code_3619
code_31df:
			dup     
			ldi      5
			eq?     
			bnt      code_31f4
			pushi    2
			pushi    30
			pushi    120
			callk    Random,  4
			aTop     ticks
			jmp      code_3619
code_31f4:
			dup     
			ldi      6
			eq?     
			bnt      code_3216
			pushi    #setLoop
			pushi    2
			pushi    1
			pushi    1
			pushi    16
			pushi    1
			pushi    0
			pushi    236
			pushi    2
			class    End
			push    
			pushSelf
			lofsa    markus
			send     22
			jmp      code_3619
code_3216:
			dup     
			ldi      7
			eq?     
			bnt      code_3234
			pushi    #setLoop
			pushi    2
			pushi    2
			pushi    1
			pushi    16
			pushi    1
			pushi    0
			lofsa    markus
			send     14
			ldi      2
			aTop     cycles
			jmp      code_3619
code_3234:
			dup     
			ldi      8
			eq?     
			bnt      code_3254
			pushi    2
			pushi    3
			pushi    5
			callk    Random,  4
			aTop     register
			pushi    2
			pushi    30
			pushi    120
			callk    Random,  4
			aTop     ticks
			jmp      code_3619
code_3254:
			dup     
			ldi      9
			eq?     
			bnt      code_3269
			pushi    2
			pushi    10
			pushi    30
			callk    Random,  4
			aTop     ticks
			jmp      code_3619
code_3269:
			dup     
			ldi      10
			eq?     
			bnt      code_32d2
			pushi    #setLoop
			pushi    2
			pushi    2
			pushi    1
			pushi    16
			pushi    1
			pushi    0
			pushi    266
			pushi    1
			pushi    2
			pushi    6
			pushi    10
			callk    Random,  4
			push    
			pushi    236
			pushi    2
			class    End
			push    
			pushSelf
			lofsa    markus
			send     28
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_32ac
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_32ac
code_32ac:
			not     
			bnt      code_32c3
			pushi    #number
			pushi    1
			pushi    404
			pushi    15
			pushi    1
			pushi    1
			pushi    51
			pushi    0
			lofsa    sfx
			send     16
code_32c3:
			dpToa    register
			bnt      code_3619
			pTos     state
			ldi      2
			sub     
			aTop     state
			jmp      code_3619
code_32d2:
			dup     
			ldi      11
			eq?     
			bnt      code_32ee
			pushi    #cel
			pushi    1
			pushi    1
			pushi    236
			pushi    2
			class    Beg
			push    
			pushSelf
			lofsa    markus
			send     14
			jmp      code_3619
code_32ee:
			dup     
			ldi      12
			eq?     
			bnt      code_3302
			pushi    2
			pushi    2
			pushi    4
			callk    Random,  4
			aTop     seconds
			jmp      code_3619
code_3302:
			dup     
			ldi      13
			eq?     
			bnt      code_3325
			pushi    #setLoop
			pushi    2
			pushi    3
			pushi    1
			pushi    16
			pushi    1
			pushi    0
			pushi    236
			pushi    2
			class    End
			push    
			pushSelf
			lofsa    markus
			send     22
			jmp      code_3619
code_3325:
			dup     
			ldi      14
			eq?     
			bnt      code_336c
			pushi    #contains
			pushi    1
			lofsa    sinkWater
			push    
			lag      cast
			send     6
			not     
			bnt      code_335b
			pushi    2
			pushi    1
			pushi    100
			callk    Random,  4
			push    
			ldi      30
			gt?     
			bnt      code_335b
			pushi    #setLoop
			pushi    1
			pushi    1
			pushi    147
			pushi    0
			lofsa    sinkWater
			send     10
code_335b:
			ldi      60
			aTop     ticks
			pushi    2
			pushi    2
			pushi    5
			callk    Random,  4
			aTop     register
			jmp      code_3619
code_336c:
			dup     
			ldi      15
			eq?     
			bnt      code_337a
			ldi      2
			aTop     cycles
			jmp      code_3619
code_337a:
			dup     
			ldi      16
			eq?     
			bnt      code_33d0
			pushi    #setLoop
			pushi    2
			pushi    4
			pushi    1
			pushi    266
			pushi    1
			pushi    6
			pushi    16
			pushi    1
			pushi    0
			pushi    236
			pushi    2
			class    End
			push    
			pushSelf
			lofsa    markus
			send     28
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_33b6
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_33b6
code_33b6:
			not     
			bnt      code_3619
			pushi    #number
			pushi    1
			pushi    355
			pushi    15
			pushi    1
			pushi    1
			pushi    51
			pushi    0
			lofsa    sfx
			send     16
			jmp      code_3619
code_33d0:
			dup     
			ldi      17
			eq?     
			bnt      code_3428
			dpToa    register
			bnt      code_33e0
			ldi      14
			aTop     state
code_33e0:
			pushi    #contains
			pushi    1
			lofsa    sinkWater
			push    
			lag      cast
			send     6
			bnt      code_341a
			pushi    2
			pushi    1
			pushi    100
			callk    Random,  4
			push    
			ldi      50
			gt?     
			bnt      code_340e
			pushi    #setLoop
			pushi    2
			pushi    2
			pushi    1
			lofsa    sinkWater
			send     8
			jmp      code_341a
code_340e:
			pushi    #setLoop
			pushi    2
			pushi    1
			pushi    1
			lofsa    sinkWater
			send     8
code_341a:
			pushi    2
			pushi    10
			pushi    30
			callk    Random,  4
			aTop     ticks
			jmp      code_3619
code_3428:
			dup     
			ldi      18
			eq?     
			bnt      code_344b
			pushi    #setLoop
			pushi    2
			pushi    5
			pushi    1
			pushi    16
			pushi    1
			pushi    0
			pushi    236
			pushi    2
			class    End
			push    
			pushSelf
			lofsa    markus
			send     22
			jmp      code_3619
code_344b:
			dup     
			ldi      19
			eq?     
			bnt      code_3464
			pushi    2
			pushi    30
			pushi    90
			callk    Random,  4
			aTop     ticks
			ldi      0
			aTop     register
			jmp      code_3619
code_3464:
			dup     
			ldi      20
			eq?     
			bnt      code_34d5
			pushi    2
			pushi    1
			pushi    100
			callk    Random,  4
			push    
			ldi      60
			gt?     
			bnt      code_349f
			pushi    #setLoop
			pushi    2
			pushi    6
			pushi    1
			pushi    16
			pushi    1
			pushi    0
			pushi    236
			pushi    4
			class    CT
			push    
			pushi    4
			pushi    1
			pushSelf
			lofsa    markus
			send     26
			ldi      1
			aTop     register
			jmp      code_34a7
code_349f:
			ldi      23
			aTop     state
			ldi      2
			aTop     cycles
code_34a7:
			pushi    #contains
			pushi    1
			lofsa    sinkWater
			push    
			lag      cast
			send     6
			bnt      code_3619
			pushi    #loop
			pushi    0
			lofsa    sinkWater
			send     4
			push    
			ldi      2
			eq?     
			bnt      code_3619
			pushi    #setLoop
			pushi    1
			pushi    1
			lofsa    sinkWater
			send     6
			jmp      code_3619
code_34d5:
			dup     
			ldi      21
			eq?     
			bnt      code_3500
			pushi    #number
			pushi    1
			pushi    637
			pushi    15
			pushi    1
			pushi    1
			pushi    51
			pushi    0
			lofsa    sfx
			send     16
			pushi    #setCycle
			pushi    2
			class    End
			push    
			pushSelf
			lofsa    markus
			send     8
			jmp      code_3619
code_3500:
			dup     
			ldi      22
			eq?     
			bnt      code_3531
			pushi    #number
			pushi    1
			pushi    637
			pushi    15
			pushi    1
			pushi    1
			pushi    51
			pushi    0
			lofsa    sfx
			send     16
			pushi    #setCycle
			pushi    4
			class    CT
			push    
			pushi    3
			pushi    65535
			pushSelf
			lofsa    markus
			send     12
			jmp      code_3619
code_3531:
			dup     
			ldi      23
			eq?     
			bnt      code_355c
			pushi    #number
			pushi    1
			pushi    637
			pushi    15
			pushi    1
			pushi    1
			pushi    51
			pushi    0
			lofsa    sfx
			send     16
			pushi    #setCycle
			pushi    2
			class    Beg
			push    
			pushSelf
			lofsa    markus
			send     8
			jmp      code_3619
code_355c:
			dup     
			ldi      24
			eq?     
			bnt      code_356a
			ldi      60
			aTop     ticks
			jmp      code_3619
code_356a:
			dup     
			ldi      25
			eq?     
			bnt      code_360b
			pushi    2
			pushi    0
			pushi    4
			callk    Random,  4
			sal      local65
			pToa     register
			not     
			bnt      code_35be
			lsl      local65
			ldi      1
			eq?     
			bnt      code_35be
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_359f
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_359f
code_359f:
			not     
			bnt      code_35be
			pushi    #number
			pushi    1
			pushi    631
			pushi    15
			pushi    1
			pushi    1
			pushi    51
			pushi    0
			lofsa    sfx
			send     16
			ldi      180
			aTop     ticks
			jmp      code_3619
code_35be:
			pToa     register
			not     
			bnt      code_3601
			lsl      local65
			ldi      2
			eq?     
			bnt      code_3601
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_35e2
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_35e2
code_35e2:
			not     
			bnt      code_3601
			pushi    #number
			pushi    1
			pushi    516
			pushi    15
			pushi    1
			pushi    1
			pushi    51
			pushi    0
			lofsa    sfx
			send     16
			ldi      180
			aTop     ticks
			jmp      code_3619
code_3601:
			pushi    #init
			pushi    0
			self     4
			jmp      code_3619
code_360b:
			dup     
			ldi      26
			eq?     
			bnt      code_3619
			pushi    #init
			pushi    0
			self     4
code_3619:
			toss    
			ret     
		)
	)
)
