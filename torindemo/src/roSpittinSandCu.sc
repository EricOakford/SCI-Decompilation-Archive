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
				(poTorinLands init: setCycle: End self)
				(messager say: 0 0 1 1 self)
			)
			(1)
			(2
				(poTorinLands dispose:)
				(poBoogleLands init: setCycle: End self)
				(sound1 lThumbLoop: -25134)
			)
			(3
				(poBoogleLands dispose:)
				(poTorinLiftsHead init: setCycle: End)
				(poBoogleLaughs init: setCycle: End self)
				(sound1 lThumbLoop: -25132)
			)
			(4
				(poBoogleLaughs dispose:)
				(poBoogleSquished init: setCycle: End self)
				(sound1 lThumbLoop: -25133)
			)
			(5
				(poBoogleSquished dispose:)
				(voBoogleSquished init:)
				(poTorinLiftsHead setCycle: End self)
			)
			(6
				(poTorinLiftsHead dispose:)
				(poTorinSavesBoogle init: setCycle: CT 12 1 self)
			)
			(7
				(voBoogleSquished dispose:)
				(= ticks (poTorinSavesBoogle cycleSpeed?))
			)
			(8
				(poTorinSavesBoogle setCycle: CT 16 1 self)
			)
			(9
				(= ticks (poTorinSavesBoogle cycleSpeed?))
				(voBall init:)
			)
			(10
				(poTorinSavesBoogle setCycle: End self)
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
		(music1 pageSize: -25136)
		(theGame handsOff:)
		(curRoom setScript: soSpittinSand)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
