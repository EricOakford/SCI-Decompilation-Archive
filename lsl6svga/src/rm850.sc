;;; Sierra Script 1.0 - (do not remove this comment)
(script# 850)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use PolyFeature)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm850 0
)

(instance rm850 of LarryRoom
	(properties
		noun 1
		picture 850
	)
	
	(method (init)
		(super init: &rest)
		(Bclr 84)
		(bang init:)
		(gun init:)
		(pouch init:)
		(chair init:)
		(panels init:)
		(aWindow init:)
		(monitors init:)
		(monitor1 init: setCycle: Fwd)
		(monitor2 init: setCycle: Fwd)
		(monitor3 init: setCycle: Fwd)
		(monitor4 init: setCycle: Fwd)
		(fuzz init: setCycle: Fwd)
		(if (!= ((inventory at: 18) owner?) curRoomNum)
			(minusHandcuffs init:)
		else
			(handcuffs
				init:
				setPolygon:
					((Polygon new:)
						type: 0
						init: 161 67 157 96 145 93 148 67
						yourself:
					)
			)
		)
		(if (== global100 850) (Bset 18))
		(if (Btst 18)
			(theMusic number: 360 loop: -1 play:)
			(shower init: setCycle: Fwd)
		else
			(theMusic number: 850 loop: -1 play:)
			(theMusic2 number: 851 loop: -1 play:)
			(eyeMonitor init:)
		)
		((ScriptID 0 11) init: self)
		(proc79_11 383)
		(theGame handsOn:)
	)
	
	(method (dispose)
		(sfx number: 0 dispose:)
		(proc79_12 383)
		(Bclr 95)
		(theMusic fade:)
		((ScriptID 0 11) dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(self setScript: stopLarryScr 0 1)
					(return 1)
				)
				(2
					(messager
						say:
							noun
							theVerb
							(cond 
								((Btst 18) 1)
								((< (+ global191 1) 5)
								(switch (++ global191)
									(1 2)
									(2 3)
									(3 6)
									(4 4)
								))
								(else 5)
							)
					)
				)
				(else 
					(return (super doVerb: theVerb))
				)
			)
		)
	)
	
	(method (cue)
		(curRoom newRoom: 800)
	)
)

(instance stopLarryScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(blockLarry init: setCycle: End self)
				(Bset 95)
			)
			(1 (= cycles 2))
			(2
				(if register
					(messager say: 1 4 0 0 self)
				else
					(messager
						say: 5 4 (if (not (Bset 91)) 0 else 7) 0 self
					)
				)
			)
			(3
				(blockLarry setCycle: Beg self)
			)
			(4
				(Bclr 95)
				(blockLarry dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance missleDeathScr of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(== state 3)
				(== (DoAudio 6 850 3 5 0 0) -1)
				(not (-- register))
			)
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 3 5 0 0 self)
				(= ticks 120)
			)
			(1
				(triggerFinger init: cel: 0 setCycle: CT 2 1 self)
			)
			(2 (= ticks 120))
			(3
				(= register (* howFast 75))
				(triggerFinger cel: 3)
			)
			(4
				(triggerFinger cel: 7 setCycle: End self)
				(theMusic fade: 0 20 10 1)
			)
			(5
				(sfx number: 383 loop: 1 play:)
				(= ticks 60)
			)
			(6
				(Bset 2)
				(curRoom newRoom: 800)
			)
		)
	)
)

(instance takeHandcuffsScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(minusHandcuffs init:)
				(handcuffs dispose:)
				(ego get: 18)
				(= cycles 2)
			)
			(1
				(theGame changeScore: 15 273)
				(messager say: 3 5 1 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance bang of Feature
	(properties
		noun 5
		y 1
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						143
						27
						143
						4
						176
						3
						174
						33
						182
						42
						189
						68
						225
						61
						230
						67
						191
						85
						208
						102
						201
						139
						179
						139
						184
						113
						171
						109
						155
						108
						155
						139
						138
						139
						134
						109
						126
						97
						118
						74
						125
						36
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(cond 
			((and (== theVerb 1) (Btst 18)) (messager say: noun theVerb 1))
			((== theVerb 2) (rm850 doVerb: theVerb))
			((== theVerb 4) (curRoom setScript: stopLarryScr 0 0))
			(else (super doVerb: theVerb))
		)
	)
)

(instance handcuffs of Feature
	(properties
		noun 3
		y 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(if
			(and (not (ego has: 18)) (not (Btst 208)) (Btst 18))
				(curRoom setScript: takeHandcuffsScr)
			else
				(curRoom setScript: missleDeathScr)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance pouch of Feature
	(properties
		noun 2
		x 122
		y 2
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 120 66 136 70 127 88 112 86
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(Bset 84)
			(curRoom setScript: missleDeathScr)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance gun of Feature
	(properties
		noun 4
		y 2
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 177 79 166 100 159 97 166 71
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(Bset 84)
			(curRoom setScript: missleDeathScr)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance chair of Feature
	(properties
		noun 9
		y 2
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						163
						107
						175
						122
						175
						139
						166
						139
						169
						126
						162
						115
						157
						113
						156
						138
						149
						138
						151
						111
						140
						111
						138
						139
						130
						140
						126
						134
						129
						116
						137
						107
					yourself:
				)
		)
	)
)

(instance panels of PolyFeature
	(properties
		noun 8
	)
	
	(method (init)
		(super init: &rest)
		(self
			addPolygon:
				((Polygon new:)
					type: 0
					init: 0 67 28 67 103 50 122 52 116 71 108 71 37 92 0 113
					yourself:
				)
				((Polygon new:)
					type: 0
					init: 319 57 319 89 207 76 232 66 226 61 188 67 186 55
					yourself:
				)
		)
	)
)

(instance aWindow of Feature
	(properties
		noun 7
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon: ((Polygon new:)
				type: 0
				init: 0 0 60 0 63 47 0 62
				yourself:
			)
		)
	)
)

(instance monitors of PolyFeature
	(properties
		noun 6
	)
	
	(method (init)
		(super init: &rest)
		(self
			addPolygon:
				((Polygon new:)
					type: 0
					init: 104 29 127 30 125 46 105 47
					yourself:
				)
				((Polygon new:)
					type: 0
					init: 319 4 319 47 308 49 308 2
					yourself:
				)
		)
	)
)

(instance eyeMonitor of Feature
	(properties
		noun 6
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 231 6 231 41 189 41 188 6
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance monitor1 of Prop
	(properties
		noun 6
		x 244
		y 9
		view 853
		cel 5
	)
)

(instance monitor2 of Prop
	(properties
		noun 6
		x 276
		y 8
		view 853
		loop 1
		cel 2
	)
)

(instance monitor3 of Prop
	(properties
		noun 6
		x 276
		y 33
		view 853
		loop 3
		cel 13
	)
)

(instance monitor4 of Prop
	(properties
		noun 6
		x 245
		y 33
		view 853
		loop 2
		cel 3
		cycleSpeed 7
	)
)

(instance fuzz of Prop
	(properties
		noun 6
		x 105
		y 6
		priority 6
		fixPriority 1
		view 853
		loop 4
		cel 2
	)
)

(instance shower of Prop
	(properties
		noun 10
		x 193
		y 14
		priority 26
		fixPriority 1
		view 853
		loop 5
		cel 2
		cycleSpeed 7
	)
)

(instance minusHandcuffs of View
	(properties
		x 143
		y 60
		view 850
		loop 1
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance triggerFinger of Prop
	(properties
		x 201
		y 55
		priority 3
		fixPriority 1
		view 850
		cel 8
		cycleSpeed 10
	)
)

(instance blockLarry of Actor
	(properties
		x 124
		y 8
		priority 3
		fixPriority 1
		view 850
		loop 2
	)
)

(instance sfx of Sound
	(properties
		flags $0005
	)
)
