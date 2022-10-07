;;; Sierra Script 1.0 - (do not remove this comment)
(script# 560)
(include sci.sh)
(use Main)
(use KQ6Room)
(use Kq6Procs)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm560 0
)

(local
	local0
)
(instance rm560 of KQ6Room
	(properties
		noun 3
		picture 560
		horizon 0
		east 580
		south 550
		vanishingY -200
	)
	
	(method (init)
		(super init: &rest)
		(theGame handsOff:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						164
						152
						137
						160
						119
						162
						83
						158
						75
						151
						74
						148
						68
						139
						78
						134
						94
						128
						138
						128
						158
						147
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						319
						0
						319
						132
						232
						148
						201
						147
						196
						141
						221
						137
						197
						134
						182
						128
						169
						132
						159
						125
						147
						118
						168
						117
						191
						94
						161
						94
						149
						98
						140
						108
						91
						96
						70
						113
						43
						104
						15
						97
						13
						99
						38
						105
						74
						122
						62
						128
						62
						143
						56
						145
						33
						148
						26
						166
						19
						184
						78
						184
						88
						171
						110
						171
						130
						177
						136
						189
						0
						189
						0
						0
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 189 244 189 248 186 319 186
					yourself:
				)
		)
		(stairs init:)
		(rocks init:)
		(bear init:)
		(skull init:)
		(pit init:)
		(doors init: approachVerbs: 1 5 2)
		(trees init: approachVerbs: 2)
		(roomPath init:)
		(if (== ((inventory at: 42) owner?) 560)
			(scythe init: stopUpd: setPri: 13)
		)
		(curRoom setScript: egoEnters)
	)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(= temp0 (ego onControl: 1))
		(cond 
			(script 0)
			((& temp0 $0800) (theGame handsOff:) (self setScript: backUpScript))
			(
			(and (& temp0 $0080) (not (== (ego priority?) 14))) (ego setPri: 14) (= local0 1))
			((and (not (& temp0 $0080)) local0) (ego setPri: -1) (= local0 0))
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance trees of Feature
	(properties
		noun 9
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(cond 
			((!= theVerb 2) (super doVerb: theVerb &rest))
			((Btst 25) (messager say: noun theVerb 4))
			(else (messager say: noun theVerb 3))
		)
	)
	
	(method (onMe event)
		(return
			(if (super onMe: event)
				(if (<= mouseX 180)
					(self approachX: 92 approachY: 107 x: 53 y: 70)
				else
					(self approachX: 197 approachY: 143 x: 249 y: 105)
				)
				(return 1)
			else
				0
			)
		)
	)
)

(instance roomPath of Feature
	(properties
		noun 10
		onMeCheck $0100
	)
)

(instance rocks of Feature
	(properties
		noun 2
		onMeCheck $0008
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 1) (messager say: noun theVerb 10 0 0 0))
			((OneOf theVerb 2 5) (messager say: noun theVerb 0 0 0 0))
			(else (messager say: noun 0 0 0 0 0))
		)
	)
)

(instance bear of Feature
	(properties
		noun 12
		onMeCheck $0010
	)
)

(instance stairs of Feature
	(properties
		noun 13
		onMeCheck $0080
	)
)

(instance doors of Feature
	(properties
		noun 7
		onMeCheck $0004
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 8 16 35) (messager say: noun theVerb 0))
			((OneOf theVerb 2 5)
				(if (Btst 25)
					(messager say: noun theVerb 4)
				else
					(messager say: noun theVerb 3)
				)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
	
	(method (onMe event)
		(return
			(if (super onMe: event)
				(if (<= mouseX 180)
					(self approachX: 92 approachY: 107 x: 53 y: 70)
				else
					(self approachX: 197 approachY: 143 x: 249 y: 105)
				)
				(return 1)
			else
				0
			)
		)
	)
)

(instance skull of Feature
	(properties
		noun 8
		onMeCheck $0040
	)
)

(instance pit of Feature
	(properties
		noun 5
		onMeCheck $0020
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 1) (curRoom setScript: lookFireKludgeScript))
			((!= theVerb 5) (super doVerb: theVerb &rest))
			((!= ((inventory at: 6) owner?) 560) (messager say: noun theVerb 6))
			(else (curRoom setScript: getCoal))
		)
	)
)

(instance scythe of View
	(properties
		x 15
		y 45
		noun 4
		view 560
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(theGame handsOff:)
			(curRoom setScript: getScythe)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance lookFireKludgeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 5 1 0 1 self))
			(1
				(if (not (Btst 14))
					(messager say: 5 1 8 1 self)
				else
					(= cycles 1)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance backUpScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 1 0 7 0 self))
			(1
				(ego setMotion: PolyPath (ego x?) (+ (ego y?) 10) self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(soundFx number: 580 loop: 1 flags: 1 play:)
				(if (== prevRoomNum 550)
					(ego
						init:
						posn: 150 188
						setScale: Scaler 100 75 190 84
						setMotion: MoveTo 150 170 self
					)
				else
					(ego
						init:
						posn: 318 162
						setScale: Scaler 100 75 190 84
						setMotion: MoveTo 250 162 self
					)
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getCoal of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 152 142 self)
			)
			(1
				(ego
					view: 561
					setPri: 14
					normal: 0
					setPri: 12
					setLoop: 1
					cel: 0
					cycleSpeed: 14
					setCycle: CycleTo 3 1 self
				)
			)
			(2 (ego setCycle: EndLoop self))
			(3
				(theGame givePoints: 1)
				(ego get: 6 reset:)
				(messager say: 5 5 5 1 self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getScythe of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					ignoreActors: 1
					illegalBits: 0
					setMotion: PolyPath 34 103 self
				)
			)
			(1
				(scythe dispose:)
				(ego
					normal: 0
					view: 561
					cel: 0
					setLoop: 0
					setSpeed: 8
					setCycle: EndLoop self
				)
			)
			(2
				(ego get: 42)
				(theGame givePoints: 1)
				(messager say: 4 5 0 1 self)
			)
			(3
				(theGame handsOn:)
				(ego reset:)
				(ego setPri: 14)
				(self dispose:)
			)
		)
	)
)
