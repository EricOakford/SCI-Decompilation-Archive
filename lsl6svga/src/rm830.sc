;;; Sierra Script 1.0 - (do not remove this comment)
(script# 830)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm830 0
)

(instance rm830 of LarryRoom
	(properties
		noun 1
		picture 830
		horizon 0
	)
	
	(method (init)
		(super init: &rest)
		((ScriptID 0 11) init: rm830)
		(battery init:)
		(motor init:)
		(transformer init:)
		(pulley init:)
		(wire init:)
		(theGame handsOn:)
	)
	
	(method (dispose)
		((ScriptID 0 11) dispose:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 64)
			(if (Btst 266)
				(messager say: 2 64 1)
			else
				(curRoom setScript: wrenchOnMotor)
			)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (cue)
		(curRoom newRoom: 820)
	)
)

(instance wrenchOnMotor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff: changeScore: 15 266)
				(Bset 23)
				(wire setCycle: End self)
			)
			(1
				(messager say: 3 64 0 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance wire of Prop
	(properties
		noun 4
		x 163
		y 69
		view 830
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 64)
				(if (Btst 23)
					(messager say: 2 64 1)
				else
					(curRoom setScript: wrenchOnMotor)
				)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance battery of Feature
	(properties
		noun 2
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 148 85 142 66 176 63 171 86
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 64)
				(if (Btst 23)
					(messager say: 2 64 1)
				else
					(curRoom setScript: wrenchOnMotor)
				)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance motor of Feature
	(properties
		noun 3
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 116 81 115 57 137 45 143 99 123 97
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 64)
				(if (Btst 23)
					(messager say: 2 64 1)
				else
					(curRoom setScript: wrenchOnMotor)
				)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance transformer of Feature
	(properties
		noun 6
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 152 89 176 91 182 100 165 101 143 101
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance pulley of Feature
	(properties
		noun 5
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 127 50 131 43 185 41 190 49 186 55 133 55
					yourself:
				)
		)
		(super init: &rest)
	)
)
