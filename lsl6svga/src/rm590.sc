;;; Sierra Script 1.0 - (do not remove this comment)
(script# 590)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use Polygon)
(use Feature)
(use System)

(public
	rm590 0
)

(local
	local0
)
(instance rm590 of LarryRoom
	(properties
		noun 9
		picture 590
	)
	
	(method (init)
		(super init: &rest)
		(narrator x: 10 y: 10 talkWidth: 150)
		(shabArm init:)
		(shabChest init:)
		(shabEyes init:)
		(shabHair init:)
		(shabNose init:)
		(shabMouth init:)
		((ScriptID 0 11) init: self)
		(if (== global173 18)
			(self doVerb: 18)
		else
			(theGame handsOn:)
		)
	)
	
	(method (dispose)
		(narrator x: -1 y: -1 talkWidth: 0)
		((ScriptID 0 11) dispose:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(2
					(if (!= ((inventory at: 17) owner?) curRoomNum)
						(if (Btst 38) (= local0 -1))
						(messager
							say:
								noun
								theVerb
								(switch (++ local0)
									(1 4)
									(2 3)
									(3 7)
									(4 8)
									(5
										(theGame changeScore: 4 225)
										(Bset 38)
										9
									)
									(else  2)
								)
						)
					else
						(messager say: noun theVerb 1)
					)
					(return 1)
				)
				(18
					(curRoom setScript: giveGownScr)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (cue)
		(curRoom newRoom: 580)
	)
)

(instance showCondomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 9 65 1 0 self)
			)
			(1 (curRoom newRoom: 860))
		)
	)
)

(instance giveGownScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(theGame changeScore: 14 226)
				(ego put: 17 curRoomNum)
				(messager sayRange: 9 18 0 1 4 self)
			)
			(2
				(if (ego has: 8)
					(messager sayRange: 9 18 0 5 6 self oneOnly: 0)
					0
					self
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(3 (= cycles 2))
			(4
				(theGame hideControls:)
				(thePlane drawPic: -1 10)
				(= ticks 180)
			)
			(5
				(narrator x: -1 y: -1 talkWidth: 0)
				(messager say: 9 18 0 7 self)
			)
			(6 (= ticks 120))
			(7
				(Bset 24)
				(curRoom newRoom: 860)
			)
		)
	)
)

(instance shabArm of Feature
	(properties
		noun 2
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					init: 212 107 228 99 244 107 250 139 212 139 209 123
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(messager say: 2 4 0 1)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance shabChest of Feature
	(properties
		noun 18
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					init: 175 75 221 97 201 124 151 97
					yourself:
				)
		)
	)
)

(instance shabEyes of Feature
	(properties
		noun 15
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					init: 183 40 212 37 212 49 195 48 184 53
					yourself:
				)
		)
	)
)

(instance shabHair of Feature
	(properties
		noun 3
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						176
						31
						184
						8
						206
						0
						231
						12
						242
						34
						236
						57
						225
						57
						225
						34
						218
						38
						207
						29
						188
						29
						181
						41
					yourself:
				)
		)
	)
)

(instance shabNose of Feature
	(properties
		noun 19
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 203 48 202 57 191 58 191 50
					yourself:
				)
		)
	)
)

(instance shabMouth of Feature
	(properties
		noun 14
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon: ((Polygon new:)
				init: 190 60 204 58 205 67 194 68
				yourself:
			)
		)
	)
)
