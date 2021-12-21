;;; Sierra Script 1.0 - (do not remove this comment)
(script# 51000)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPSound)
(use Script)
(use n64896)
(use Feature)
(use Motion)
(use Actor)

(public
	roOnStage 0
)

(local
	local0
)
(instance coNextRoom of CueObj
	(properties)
	
	(method (cue)
		(curRoom newRoom: -10536)
	)
)

(instance sawSound of TPSound
	(properties)
)

(instance soEnterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 174 312 self)
			)
			(1
				(LOOKUP_ERROR posn: (ego x?) (ego y?) init:)
				(ego dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soPlaySaw of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR cel: 0 setCycle: End self)
			)
			(1
				(messager say: 0 0 0 1 self)
				(gInventItem moveTo: -3)
			)
			(2
				(LOOKUP_ERROR hide:)
				(LOOKUP_ERROR vThumbView: -14535)
				(LOOKUP_ERROR
					posn: (LOOKUP_ERROR x?) (LOOKUP_ERROR y?)
					init:
					cycleSpeed: 7
					setCycle: Fwd
				)
				(LOOKUP_ERROR
					posn: (LOOKUP_ERROR x?) (LOOKUP_ERROR y?)
					init:
					cycleSpeed: 5
					setCycle: Fwd
				)
				(LOOKUP_ERROR init:)
				(= ticks 180)
			)
			(3
				(LOOKUP_ERROR stop:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR show:)
				(LOOKUP_ERROR cel: 0 setCycle: End self)
			)
			(4
				(LOOKUP_ERROR init: cel: 0 setCycle: End self)
			)
			(5
				(messager sayRange: 0 0 0 2 6 self)
			)
			(6
				(LOOKUP_ERROR loop: 1 cel: 0 init: setCycle: End self)
			)
			(7
				(LOOKUP_ERROR loop: 2 cel: 0 init: setCycle: End self)
			)
			(8
				(proc64896_1 1 10 LOOKUP_ERROR)
				(self dispose:)
			)
		)
	)
)

(instance poBendSaw of Prop
	(properties
		view -14535
		loop 1
	)
)

(instance poBowSaw of Prop
	(properties
		view -14535
		loop 2
	)
)

(instance poDirector of Prop
	(properties
		x 43
		y 303
		view -14534
	)
)

(instance poCop of Prop
	(properties
		x 43
		y 303
		view -14534
		loop 1
	)
)

(instance fakeEgo of Prop
	(properties
		view -14535
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 67)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 67)
			(LOOKUP_ERROR setScript: LOOKUP_ERROR)
		)
	)
)

(instance roOnStage of TPRoom
	(properties
		picture -14536
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: -14536)
		(theGame handsOff:)
		(ego oPanner: posn: -20 440 init:)
		(curRoom setScript: LOOKUP_ERROR)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 0) get: 55))
		(ego get: ((ScriptID 64001 0) get: 56))
		(ego get: ((ScriptID 64001 0) get: 57))
	)
)
