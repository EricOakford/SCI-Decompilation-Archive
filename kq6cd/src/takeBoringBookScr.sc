;;; Sierra Script 1.0 - (do not remove this comment)
(script# 273)
(include sci.sh)
(use Main)
(use Motion)
(use Actor)
(use System)

(public
	boringBook 0
	takeBoringBookScr 1
)

(instance takeBoringBookScr of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 273)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= register
					(if (< ((ScriptID 270 2) y?) 140) 1 else 0)
				)
				(ego
					setSpeed: 6
					view: 278
					loop: 0
					cel: 0
					normal: 0
					setCycle: CycleTo 2 1 self
				)
			)
			(1
				(boringBook dispose:)
				(= cycles 2)
			)
			(2 (ego setCycle: EndLoop self))
			(3 (= cycles 2))
			(4 (messager say: 2 5 4 1 self))
			(5
				(if register
					((ScriptID 270 2) view: 276 cel: 0 setCycle: CycleTo 4 1 self)
				else
					(++ state)
					(self cue:)
				)
			)
			(6 (= cycles 2))
			(7
				(messager say: 2 5 4 2 self oneOnly: 0)
			)
			(8
				(if register
					((ScriptID 270 2) setCycle: BegLoop self)
				else
					(++ state)
					(= cycles 1)
				)
			)
			(9
				((ScriptID 270 2) view: 276 loop: 0 cel: 0)
				(= cycles 1)
			)
			(10
				(ego reset: get: 1)
				(theGame givePoints: 1)
				(= cycles 2)
			)
			(11
				(UnLoad 128 278)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance boringBook of View
	(properties
		x 112
		y 135
		z 14
		noun 2
		sightAngle 40
		approachX 108
		approachY 137
		view 270
		loop 2
		priority 8
		signal $0011
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5 1)
	)
	
	(method (doVerb theVerb &tmp theScript)
		(= theScript (ScriptID 270 1))
		(theScript doVerb: theVerb &rest)
	)
	
	(method (onMe event &tmp temp0)
		(if (= temp0 (super onMe: event &rest))
			(if (== (event message?) JOY_UP)
				(= approachX 134)
				(= approachY 129)
			else
				(= approachX 112)
				(= approachY 137)
			)
		)
		(return temp0)
	)
)
