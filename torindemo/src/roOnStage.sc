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
				(fakeEgo posn: (ego x?) (ego y?) init:)
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
				(fakeEgo cel: 0 setCycle: End self)
			)
			(1
				(messager say: 0 0 0 1 self)
				(gInventItem moveTo: -3)
			)
			(2
				(fakeEgo hide:)
				(sawSound vThumbView: -14535)
				(poBendSaw
					posn: (fakeEgo x?) (fakeEgo y?)
					init:
					cycleSpeed: 7
					setCycle: Fwd
				)
				(poBowSaw
					posn: (fakeEgo x?) (fakeEgo y?)
					init:
					cycleSpeed: 5
					setCycle: Fwd
				)
				(poDirector init:)
				(= ticks 180)
			)
			(3
				(sawSound stop:)
				(poBowSaw dispose:)
				(poBendSaw dispose:)
				(fakeEgo show:)
				(poDirector cel: 0 setCycle: End self)
			)
			(4
				(poCop init: cel: 0 setCycle: End self)
			)
			(5
				(messager sayRange: 0 0 0 2 6 self)
			)
			(6
				(poCop loop: 1 cel: 0 init: setCycle: End self)
			)
			(7
				(poCop loop: 2 cel: 0 init: setCycle: End self)
			)
			(8
				(proc64896_1 1 10 coNextRoom)
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
		(if (== theVerb 67) (fakeEgo setScript: soPlaySaw))
	)
)

(instance roOnStage of TPRoom
	(properties
		picture -14536
	)
	
	(method (init)
		(super init: &rest)
		(music1 pageSize: -14536)
		(theGame handsOff:)
		(ego oPanner: posn: -20 440 init:)
		(curRoom setScript: soEnterRoom)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 0) get: 55))
		(ego get: ((ScriptID 64001 0) get: 56))
		(ego get: ((ScriptID 64001 0) get: 57))
	)
)
