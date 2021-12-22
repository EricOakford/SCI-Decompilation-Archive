;;; Sierra Script 1.0 - (do not remove this comment)
(script# 410)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use Polygon)
(use Feature)
(use System)

(public
	rm410 0
)

(local
	local0
	local1
	local2
)
(instance rm410 of LarryRoom
	(properties
		noun 1
		picture 410
		horizon 0
	)
	
	(method (init)
		(super init: &rest)
		(breasts init:)
		(towel init:)
		(neck init:)
		(leftArm init:)
		(rightArm init:)
		(herMouth init:)
		(nose init:)
		(herEyes init:)
		((ScriptID 0 11) init: curRoom)
		(theGame handsOn:)
		(cond 
			((not (Btst 64)) (Bset 64) (curRoom setScript: firstTimeChar))
			((Btst 39) (Bclr 39) (curRoom setScript: giveBatteries))
		)
	)
	
	(method (dispose)
		((ScriptID 0 11) dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(switch theVerb
				(1
					(switch local2
						(0 (= temp0 10))
						(1 (= temp0 8))
						(else  (= temp0 9))
					)
					(++ local2)
					(messager say: 1 1 temp0)
					(return 1)
				)
				(2
					(cond 
						((Btst 204) (messager say: 1 2 14) (return 1))
						((Btst 205) (= temp0 17))
						(else
							(switch (++ local0)
								(1 (= temp0 2))
								(2 (= temp0 3))
								(3 (= temp0 4))
								(4
									(curRoom setScript: getLost)
									(return 1)
								)
							)
						)
					)
					(messager say: 1 2 temp0)
					(return 1)
				)
				(25
					(self setScript: giveBatteries)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (cue)
		(curRoom newRoom: 400)
	)
)

(instance getLost of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 1 2 5 0 self)
			)
			(1
				(theGame changeScore: 2 205)
				(= ticks 320)
			)
			(2 (curRoom newRoom: 400))
		)
	)
)

(instance firstTimeChar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(messager say: 0 0 16 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance giveBatteries of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(ego put: 1)
				(messager sayRange: 1 25 0 1 4 self)
			)
			(2
				(theGame changeScore: 15 204)
				(if (Btst 203)
					(messager sayRange: 1 25 6 1 3 self)
				else
					(messager sayRange: 1 25 0 5 6 self)
				)
			)
			(3 (curRoom newRoom: 400))
		)
	)
)

(instance towel of Feature
	(properties
		noun 2
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 162 121 157 106 202 96 218 139 137 138
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
					init: 157 103 167 78 180 82 188 68 201 94 185 101 166 105
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(switch theVerb
				(4
					(switch local1
						(0 (= temp0 11))
						(1 (= temp0 12))
						(else  (= temp0 13))
					)
					(++ local1)
					(messager say: 3 4 temp0)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
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
					init: 215 126 187 57 193 50 207 53 217 65 234 139 219 139
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
					init: 131 120 150 87 162 91 153 107 155 112 137 139 112 139
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance neck of Feature
	(properties
		noun 6
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 174 65 183 51 187 68 179 81 166 78 160 63
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance herMouth of Feature
	(properties
		noun 7
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 176 52 176 55 164 57 161 54
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance nose of Feature
	(properties
		noun 8
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 164 41 169 41 174 49 163 50
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance herEyes of Feature
	(properties
		noun 9
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 181 32 182 38 151 43 148 37
					yourself:
				)
		)
		(super init: &rest)
	)
)
