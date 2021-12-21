;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40400)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use Motion)
(use Actor)

(public
	roSpittinSandCu 0
)

(instance poTorinLands of Prop
	(properties
		x 303
		y 248
		view -25136
	)
)

(instance poBoogleLands of Prop
	(properties
		x 303
		y 248
		view -25136
		loop 1
	)
)

(instance poTorinLiftsHead of Prop
	(properties
		x 303
		y 248
		view -25136
		loop 2
	)
)

(instance poBoogleLaughs of Prop
	(properties
		x 526
		y 283
		view -25136
		loop 3
	)
)

(instance poBoogleSquished of Prop
	(properties
		x 526
		y 283
		view -25136
		loop 4
	)
)

(instance voBoogleSquished of Prop
	(properties
		x 526
		y 283
		view -25136
		loop 8
	)
)

(instance poTorinSavesBoogle of Prop
	(properties
		x 526
		y 283
		view -25136
		loop 5
	)
)

(instance voBall of View
	(properties
		x 563
		y 276
		view -25136
		loop 9
	)
)

(instance soSpittinSand of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: ((ScriptID 64001 0) get: 38))
				(ego put: ((ScriptID 64001 0) get: 39))
				(ego put: ((ScriptID 64001 0) get: 40))
				(ego put: ((ScriptID 64001 0) get: 41))
				(ego put: ((ScriptID 64001 0) get: 42))
				(ego put: ((ScriptID 64001 0) get: 43))
				(LOOKUP_ERROR init: setCycle: End self)
				(messager say: 0 0 1 1 self)
			)
			(1)
			(2
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init: setCycle: End self)
				(theSound lThumbLoop: -25134)
			)
			(3
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init: setCycle: End)
				(LOOKUP_ERROR init: setCycle: End self)
				(theSound lThumbLoop: -25132)
			)
			(4
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init: setCycle: End self)
				(theSound lThumbLoop: -25133)
			)
			(5
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR setCycle: End self)
			)
			(6
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init: setCycle: CT 12 1 self)
			)
			(7
				(LOOKUP_ERROR dispose:)
				(= ticks (LOOKUP_ERROR cycleSpeed?))
			)
			(8
				(LOOKUP_ERROR setCycle: CT 16 1 self)
			)
			(9
				(= ticks (LOOKUP_ERROR cycleSpeed?))
				(LOOKUP_ERROR init:)
			)
			(10
				(LOOKUP_ERROR setCycle: End self)
			)
			(11 (curRoom newRoom: -25236))
		)
	)
)

(instance roSpittinSandCu of TPRoom
	(properties
		picture -25136
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: -25136)
		(theGame handsOff:)
		(curRoom setScript: LOOKUP_ERROR)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
