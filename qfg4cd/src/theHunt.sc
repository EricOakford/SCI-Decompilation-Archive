;;; Sierra Script 1.0 - (do not remove this comment)
(script# 51)
(include sci.sh)
(use Main)
(use Array)
(use PChase)
(use LoadMany)
(use Grooper)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	theHunt 0
	nec1 1
	nec2 2
	nec3 3
	sBlackOut 4
)

(local
	local0
	local1
)
(instance theHunt of Rgn
	(properties
		modNum 51
	)
	
	(method (init)
		(= local1 (IntArray with: 0 60 63 80))
		(= global366 0)
		(= global365 0)
		(LoadMany 130 -545)
		(super init: &rest)
	)
	
	(method (dispose)
		(local1 dispose:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(21
				(curRoom setScript: (ScriptID 32) curRoom 21)
			)
			(37
				(curRoom setScript: (ScriptID 32) curRoom 37)
			)
			(86
				(curRoom setScript: (ScriptID 32) curRoom 86)
			)
			(88
				(curRoom setScript: (ScriptID 32) curRoom 88)
			)
			(93
				(curRoom setScript: (ScriptID 32) curRoom 93)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sBlackOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (cast contains: nec1)
					(cond 
						((not (nec1 mover?))
							(if (> (ego distanceTo: nec1) 25)
								(nec1 setMotion: PChase ego 25 nec1)
							else
								(nec1 cue:)
							)
						)
						(((nec1 mover?) isMemberOf: PChase) ((nec1 mover?) caller: nec1))
					)
				else
					(++ state)
				)
				(if (cast contains: nec2)
					(cond 
						((not (nec2 mover?))
							(if (> (ego distanceTo: nec2) 25)
								(nec2 setMotion: PChase ego 25 nec2)
							else
								(nec2 cue:)
							)
						)
						(((nec2 mover?) isMemberOf: PChase) ((nec2 mover?) caller: nec2))
					)
				else
					(++ state)
				)
				(if (cast contains: nec3)
					(cond 
						((not (nec3 mover?))
							(if (> (ego distanceTo: nec3) 25)
								(nec3 setMotion: PChase ego 25 nec3)
							else
								(nec3 cue:)
							)
						)
						(((nec3 mover?) isMemberOf: PChase) ((nec3 mover?) caller: nec3))
					)
				else
					(++ state)
				)
				(ego
					setMotion: 0
					view: 43
					setLoop: -1
					setLoop: (if (< (ego heading?) 180) 0 else 1)
					setCel: 0
					setCycle: End
				)
				(self cue:)
			)
			(1 0)
			(2
				(if (not (cast contains: nec2)) (= ticks 1))
			)
			(3
				(Bset 36)
				(cast eachElementDo: #hide)
				(UpdatePlane
					((curRoom plane?) back: 0 picture: -1 yourself:)
				)
				((ScriptID 0 21) doit: 100)
				(theMusic fade:)
				(= cycles 300)
			)
			(4
				(ego changeGait: 0)
				(curRoom newRoom: 670)
			)
		)
	)
)

(instance nec1 of Actor
	(properties
		noun 3
		modNum 51
		view 870
		cel 3
		signal $6001
	)
	
	(method (init)
		(self
			setStep: 7 4
			setLooper: Grooper
			setCycle: Walk
			setScaler: ego
		)
		(super init: &rest)
	)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(if
		(and (not local0) (< (self distanceTo: ego) 30))
			(= local0 1)
			(theGame handsOff:)
			(ego setMotion: 0)
			(curRoom notify: 1)
		)
		(cond 
			(
				(and
					(<
						y
						(= temp0
							(local1 at: (/ (- (curRoom picture?) 400) 10))
						)
					)
					(< z 1000)
				)
				(= z (+ z 1000))
			)
			((and (> y temp0) (>= z 1000)) (= z (- z 1000)))
		)
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				36
				20
				21
				85
				83
				81
				87
				86
				88
				79
				102
				91
				89
				93
				80
				90
				94
				92
				82
				84
				95
				96
				97
				98
				11
			)
			(theHunt doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (cue)
		(if approachDist
			(sBlackOut cue:)
		else
			(self
				setCel: 0
				setCycle: CT 3 1
				approachDist: 1
				setMotion: JumpTo (ego x?) (ego y?) self
			)
		)
	)
)

(instance nec2 of Actor
	(properties
		noun 3
		modNum 51
		view 870
		cel 3
		signal $6001
	)
	
	(method (init)
		(self
			setStep: 7 4
			setLooper: Grooper
			setCycle: Walk
			setScaler: ego
		)
		(super init: &rest)
	)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(if
		(and (not local0) (< (self distanceTo: ego) 30))
			(= local0 1)
			(theGame handsOff:)
			(ego setMotion: 0)
			(curRoom notify: 1)
		)
		(cond 
			(
				(and
					(<
						y
						(= temp0
							(local1 at: (/ (- (curRoom picture?) 400) 10))
						)
					)
					(< z 1000)
				)
				(= z (+ z 1000))
			)
			((and (> y temp0) (>= z 1000)) (= z (- z 1000)))
		)
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				36
				20
				21
				85
				83
				81
				87
				86
				88
				79
				102
				91
				89
				93
				80
				90
				94
				92
				82
				84
				95
				96
				97
				98
				11
			)
			(theHunt doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (cue)
		(if approachDist
			(sBlackOut cue:)
		else
			(self
				setCel: 0
				setCycle: CT 3 1
				approachDist: 1
				setMotion: JumpTo (ego x?) (ego y?) self
			)
		)
	)
)

(instance nec3 of Actor
	(properties
		noun 3
		modNum 51
		view 870
		cel 3
		signal $6001
	)
	
	(method (init)
		(self
			setStep: 7 4
			setLooper: Grooper
			setCycle: Walk
			setScaler: ego
		)
		(super init: &rest)
	)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(if
		(and (not local0) (< (self distanceTo: ego) 30))
			(= local0 1)
			(theGame handsOff:)
			(ego setMotion: 0)
			(curRoom notify: 1)
		)
		(cond 
			(
				(and
					(<
						y
						(= temp0
							(local1 at: (/ (- (curRoom picture?) 400) 10))
						)
					)
					(< z 1000)
				)
				(= z (+ z 1000))
			)
			((and (> y temp0) (>= z 1000)) (= z (- z 1000)))
		)
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				36
				20
				21
				85
				83
				81
				87
				86
				88
				79
				102
				91
				89
				93
				80
				90
				94
				92
				82
				84
				95
				96
				97
				98
				11
			)
			(theHunt doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (cue)
		(if approachDist
			(sBlackOut cue:)
		else
			(self
				setCel: 0
				setCycle: CT 3 1
				approachDist: 1
				setMotion: JumpTo (ego x?) (ego y?) self
			)
		)
	)
)
