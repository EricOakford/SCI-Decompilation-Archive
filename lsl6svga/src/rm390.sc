;;; Sierra Script 1.0 - (do not remove this comment)
(script# 390)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use Array)
(use Print)
(use Polygon)
(use Feature)
(use Timer)
(use Sound)
(use System)

(public
	rm390 0
)

(local
	local0
	local1
	local2
)
(instance rm390 of LarryRoom
	(properties
		noun 1
		picture 390
	)
	
	(method (init)
		(if global100 (= local2 2) else (= local2 4))
		(super init: &rest)
		(theMusic number: 0 stop:)
		(if
		(or (!= prevRoomNum 320) (not (theMusic2 handle?)))
			(theMusic2 number: 320 loop: -1 play: setVol: 90)
		)
		(roseEyes init:)
		(breasts init:)
		(hair init:)
		(nose init:)
		(roseMouth init:)
		(halterTop init:)
		(leftArm init:)
		(rightArm init:)
		(neck init:)
		(= gGButtonBarCurIcon (ScriptID 0 4))
		((ScriptID 0 11) init: rmCue)
		(fan init:)
		(lubricant init:)
		(pipe init:)
		(if (and (Btst 55) (not (Btst 108)))
			(stenchTimer setCycle: stenchTimer 3)
		else
			(theGame handsOn:)
		)
	)
	
	(method (dispose)
		((ScriptID 0 11) dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(66
					(curRoom setScript: giveFlowersScr)
				)
				(2
					(switch (++ local0)
						(1 (messager say: 1 2 1 0))
						(2 (messager say: 1 2 2 0))
						(3 (messager say: 1 2 3 0))
						(4 (messager say: 1 2 4 0))
						(5 (messager say: 1 2 5 0))
						(else  (messager say: 1 2 6 0))
					)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (cue)
		(theGame handsOn:)
	)
)

(instance roseEyes of Feature
	(properties
		noun 2
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 150 34 176 32 177 42 169 44 150 45
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance breasts of Feature
	(properties
		noun 3
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						130
						94
						142
						95
						143
						101
						157
						96
						175
						101
						175
						113
						166
						112
						156
						108
						134
						107
						117
						103
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance hair of Feature
	(properties
		noun 9
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						185
						78
						194
						75
						194
						66
						190
						60
						189
						52
						188
						44
						182
						41
						178
						27
						154
						24
						149
						32
						148
						44
						151
						59
						153
						69
						158
						78
						163
						83
						152
						84
						139
						72
						137
						53
						142
						46
						143
						26
						144
						14
						161
						7
						186
						9
						199
						27
						215
						63
						201
						85
						185
						84
						183
						77
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance nose of Feature
	(properties
		noun 7
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 154 41 162 39 166 48 158 52 153 47
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance roseMouth of Feature
	(properties
		noun 6
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 150 50 169 50 169 64 150 64
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance halterTop of Feature
	(properties
		noun 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						178
						114
						184
						123
						191
						126
						191
						133
						186
						133
						178
						139
						160
						139
						125
						133
						116
						128
						111
						116
						106
						108
						111
						102
						119
						103
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance leftArm of Feature
	(properties
		noun 5
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 188 83 204 83 247 139 221 139 189 109
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance rightArm of Feature
	(properties
		noun 4
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 138 55 142 81 135 82 109 60 93 38 94 30 111 23 147 16 142 29 118 41
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance neck of Feature
	(properties
		noun 8
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 161 67 182 61 185 87 158 86
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not local1)
					(= local1 1)
					(super doVerb: theVerb)
				else
					(messager say: 8 4 7)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fan of Feature
	(properties
		noun 11
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 0 0 63 0 69 10 65 20 78 30 75 53 61 55 54 49 20 72 0 58
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0 (ByteArray new: 40))
		(cond 
			((Message 0 390 noun theVerb 0 1 (temp0 data?)) (messager say: 11 theVerb))
			((Message 0 320 2 theVerb 0 1 (temp0 data?)) (messager say: 2 theVerb 0 0 0 320))
			(else (messager say: 11 0))
		)
		(temp0 dispose:)
	)
)

(instance lubricant of Feature
	(properties
		noun 7
		modNum 320
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 0 107 31 109 31 138 0 138
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb &tmp temp0 temp1)
		(if (and (== theVerb 4) (not (-- local2)))
			(ego get: 16)
			(Bset 99)
			(while (== (= temp0 (Random 1 61)) 9)
			)
			(DoAudio 2 611 1 4 temp0 1)
			(Print
				width: 160
				font: userFont
				addTitle: {Carlos, are you cheating again?}
				addText: 1 4 temp0 1 50 1 611
				fore: 67
				addText: {You now have the flowers for Rose.} 50 18
				fore: 0
				addIcon: 1592 1 0 0 0
				init:
			)
		else
			(= temp1 (ByteArray new: 40))
			(if (Message 0 320 noun theVerb 0 1 (temp1 data?))
				(messager say: noun theVerb 0 0 0 320)
			else
				(curRoom doVerb: theVerb)
			)
			(temp1 dispose:)
		)
	)
)

(instance pipe of Feature
	(properties
		noun 10
		modNum 320
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						308
						0
						304
						29
						302
						85
						312
						86
						312
						108
						302
						108
						302
						137
						246
						138
						232
						121
						238
						120
						239
						110
						230
						108
						228
						89
						237
						86
						225
						0
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(messager say: 10 0 0 0 0 320)
	)
)

(instance giveFlowersScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theGame changeScore: 5 198)
				(Bset 67)
				(= seconds 4)
			)
			(1
				(theMusic2 fade: 127 10 10 0)
				(curRoom newRoom: 320)
			)
		)
	)
)

(instance rmCue of Script
	(properties)
	
	(method (cue)
		(curRoom newRoom: 320)
	)
)

(instance stenchTimer of Timer
	(properties)
	
	(method (cue)
		(messager say: 0 0 10 0 curRoom)
		(Bset 108)
	)
)

(instance sfx of Sound
	(properties)
)
