;;; Sierra Script 1.0 - (do not remove this comment)
(script# 720)
(include sci.sh)
(use Main)
(use n074)
(use fileScr)
(use LarryRoom)
(use Plane)
(use Print)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm720 0
)

(local
	[local0 2]
	newPlane
)
(instance rm720 of LarryRoom
	(properties
		noun 1
		picture 720
		autoLoad 0
	)
	
	(method (init &tmp temp0)
		(super init: &rest)
		(Bclr 100)
		(narrator x: 10 y: 100 talkWidth: 295)
		((ScriptID 0 11) init: self)
		(hair
			setCycle: Fwd
			init:
			setPri: 100
			cycleSpeed: 12
			setScript: varySpeed
		)
		(face init:)
		(arms init:)
		(pants init:)
		(breasts init:)
		(body init:)
		(windSFX play:)
		(if global175
			(= temp0 1)
			(while (<= temp0 global175)
				(proc74_0 temp0)
				(++ temp0)
			)
		)
		(if (and (== prevRoomNum 730) global173)
			(self setScript: itemOnShamScr)
		else
			(theGame handsOn:)
		)
		(if (== global100 720)
			(= score 980)
			((ScriptID 88 0) update:)
			(self setScript: giveChampagneScr)
		)
	)
	
	(method (dispose)
		(narrator x: -1 y: -1 talkWidth: 0)
		((ScriptID 0 11) dispose:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if (!= global175 0) (== global179 0) (= global179 1))
				(theGame changeScore: 4 256)
				(messager
					say: noun theVerb
					(switch global179
						(0 2)
						(1 3)
						(2 4)
						(3 5)
						(4 6)
					)
				)
				(if (< global179 4) (++ global179))
			)
			(13
				(self setScript: giveOrchidScr)
			)
			(16
				(self setScript: giveBraceletScr)
			)
			(29
				(self setScript: givePearlScr)
			)
			(23
				(self setScript: giveDiamondScr)
			)
			(28
				(self setScript: giveSculptureScr)
			)
			(53
				(self setScript: giveWordsScr)
			)
			(41
				(self setScript: giveLampScr)
			)
			(20
				(if
					(and
						(Btst 258)
						(Btst 257)
						(Btst 259)
						(Btst 260)
						(Btst 261)
						(Btst 262)
						(Btst 263)
					)
					(self setScript: giveChampagneScr)
				else
					(messager say: noun theVerb)
				)
			)
			(19
				(if
					(and
						(Btst 258)
						(Btst 257)
						(Btst 259)
						(Btst 260)
						(Btst 261)
						(Btst 262)
						(Btst 263)
					)
					(messager say: noun theVerb 1)
				else
					(messager say: noun theVerb)
				)
			)
			(else 
				(return (super doVerb: theVerb))
			)
		)
		(return 1)
	)
	
	(method (cue)
		(self newRoom: 710)
	)
)

(instance varySpeed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks (Random 50 180)))
			(1
				(client cycleSpeed: (Random 8 15))
				(= state -1)
				(= cycles 2)
			)
		)
	)
)

(instance hair of Prop
	(properties
		noun 4
		x 152
		y 7
		view 720
		loop 3
		signal $5021
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(if (not (OneOf theVerb 4 1 2 5 6))
			(rm720 doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance body of View
	(properties
		x 155
		y 7
		view 720
		loop 5
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance endOfGameScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego put: 7)
			)
			(1
				(Prints {Start end of game cartoon})
				(= cycles 2)
			)
			(2 (theGame restart:))
		)
	)
)

(instance itemOnShamScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(-- state)
				(= ticks 120)
				(cond 
					(global173 (curRoom doVerb: global173) (= global173 0))
					(
						(and
							(or (talkers size:) (Print dialog?))
							(Print dialog?)
						)
					)
					(else
						(= gGButtonBarCurIcon (ScriptID 0 4))
						(theGame handsOn:)
						(self dispose:)
					)
				)
			)
		)
	)
)

(instance face of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					init: 179 11 190 24 185 34 177 38 170 37 166 24 168 13
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 4 1 2 5 6)
			(curRoom newRoom: 730)
		else
			(curRoom doVerb: theVerb)
		)
	)
)

(instance arms of Feature
	(properties
		noun 3
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					init:
						164
						68
						163
						71
						168
						74
						171
						67
						181
						60
						187
						60
						189
						50
						198
						44
						203
						47
						201
						58
						191
						79
						181
						91
						176
						89
						169
						84
						160
						90
						156
						86
						157
						67
						162
						62
						163
						50
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(if (not (OneOf theVerb 4 1 2 5 6))
			(rm720 doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance pants of Feature
	(properties
		noun 5
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					init:
						171
						95
						177
						100
						186
						98
						193
						90
						205
						88
						206
						117
						199
						124
						179
						133
						168
						139
						144
						133
						144
						126
						136
						132
						119
						126
						108
						85
						117
						76
						163
						95
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(if (not (OneOf theVerb 4 1 2 5 6))
			(rm720 doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance breasts of Feature
	(properties
		noun 2
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					init: 181 61 172 65 169 74 163 71 165 63 169 54
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(if (not (OneOf theVerb 4 1 2 5 6))
			(rm720 doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance giveBraceletScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego put: 5)
				(messager sayRange: 1 16 0 1 11 self)
			)
			(1
				(theGame changeScore: 20 257)
				(proc74_0 (++ global175))
				(= cycles 2)
			)
			(2
				(messager say: 1 16 0 12 self oneOnly: 0)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance giveOrchidScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego put: 28)
				(messager sayRange: 1 13 0 1 5 self)
			)
			(1
				(theGame changeScore: 20 258)
				(proc74_0 (++ global175))
				(= cycles 2)
			)
			(2
				(messager say: 1 13 0 6 self oneOnly: 0)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance givePearlScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego put: 29)
				(messager sayRange: 1 29 0 1 14 self)
			)
			(1
				(theGame changeScore: 20 259)
				(proc74_0 (++ global175))
				(= cycles 2)
			)
			(2
				(messager say: 1 29 0 15 self oneOnly: 0)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance giveDiamondScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego put: 10)
				(messager sayRange: 1 23 0 1 5 self)
			)
			(1
				(theGame changeScore: 20 260)
				(proc74_0 (++ global175))
				(= cycles 2)
			)
			(2
				(messager say: 1 23 0 6 self oneOnly: 0)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance giveSculptureScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego put: 33)
				(messager sayRange: 1 28 0 1 6 self)
			)
			(1
				(theGame changeScore: 20 261)
				(proc74_0 (++ global175))
				(= cycles 2)
			)
			(2
				(messager say: 1 28 0 7 self oneOnly: 0)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance giveWordsScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego put: 40)
				(messager sayRange: 1 53 0 1 4 self)
			)
			(1
				(theGame changeScore: 20 262)
				(proc74_0 (++ global175))
				(= cycles 2)
			)
			(2
				(messager say: 1 53 0 5 self oneOnly: 0)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance giveLampScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego put: 23)
				(messager sayRange: 1 41 0 1 3 self)
			)
			(1
				(theGame changeScore: 20 263)
				(proc74_0 (++ global175))
				(= cycles 2)
			)
			(2
				(messager say: 1 41 0 4 self oneOnly: 0)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance modifyPaletteScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks 10)
				(= register 100)
			)
			(1
				(if (> register 0) (-- register) (-- state))
				(Palette 2 80 254 register)
				(= ticks 2)
			)
			(2 (self dispose:))
		)
	)
)

(instance pointsCast of Cast
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(self eachElementDo: #motionCue)
	)
)

(instance giveChampagneScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 1 20 1 1 self)
			)
			(1
				(theGame changeScore: 20 264)
				(if (>= score 1000) (= score 999) (= register 1))
				(ego put: 7)
				(proc74_0 (++ global175))
				(= cycles 2)
			)
			(2
				(messager sayRange: 1 20 1 2 15 self)
			)
			(3
				(messager say: 1 20 1 16 self)
			)
			(4
				(messager sayRange: 1 20 1 17 24 self)
			)
			(5
				(if
					(or
						((ScriptID 88 0) running?)
						(!= ((ScriptID 88 0) currentValue?) score)
					)
					(-- state)
				)
				(= ticks 20)
			)
			(6
				(if register
					(Load rsVIEW 956)
					(Load rsSOUND 4)
					(theMusic2 number: 4 loop: 1 play:)
					((= newPlane (Plane new:))
						init: 0 0 320 50
						priority: (+ (GetHighPlanePri) 1)
						picture: -2
						addCast: pointsCast
					)
					(UpdatePlane newPlane)
					(pGuage init: pointsCast setCycle: End self)
					(theDoits add: pointsCast)
				else
					(++ state)
					(= cycles 2)
				)
			)
			(7
				(theDoits delete: pointsCast)
				(= cycles 2)
			)
			(8
				(messager say: 1 20 1 25 self)
			)
			(9 (= ticks 90))
			(10
				(theMusic fade:)
				(if (pointsCast size:)
					(pointsCast eachElementDo: #dispose dispose:)
					(newPlane dispose:)
				)
				(theGame hideControls:)
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 98 0)
				(= cycles 2)
			)
			(11
				(theMusic2 number: 710 loop: 1 play: self)
			)
			(12
				(theMusic2 number: 720 loop: 1 play: self)
				(Bset 100)
				((ScriptID 1816 14) bust: 0 eyes: 0 mouth: 0)
				(messager say: 1 20 1 26 self)
			)
			(13 0)
			(14
				(theMusic2 number: 720 loop: 1 play: self)
				(messager say: 1 20 1 27 self oneOnly: 0)
			)
			(15 0)
			(16
				(windSFX stop:)
				(theMusic stop:)
				(= cycles 2)
			)
			(17 (curRoom newRoom: 740))
		)
	)
)

(instance pGuage of Prop
	(properties
		x 174
		y 3
		view 956
		cycleSpeed 9
	)
)

(instance windSFX of Sound
	(properties
		flags $0001
		number 270
		loop -1
	)
)
