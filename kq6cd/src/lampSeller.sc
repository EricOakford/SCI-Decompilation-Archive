;;; Sierra Script 1.0 - (do not remove this comment)
(script# 241)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	lampSeller 0
	lampSellerScr 1
)

(instance lampSeller of Actor
	(properties
		x 19
		y 128
		noun 4
		approachX 58
		approachY 128
		_approachVerbs -32766
		view 2431
		priority 7
		signal $5010
	)
	
	(method (init)
		(super init: &rest)
		(polePoly init:)
		(self setScript: lampSellerScr)
	)
	
	(method (dispose)
		(super dispose:)
		(polePoly dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(43
				(self setScript: (ScriptID 11 2))
			)
			(2
				(cond 
					(((ScriptID 10 0) isSet: 16) (self setScript: (ScriptID 11 3) 0 24))
					((not ((ScriptID 10 0) isSet: 8))
						((ScriptID 10 0) setIt: 8)
						(self setScript: (ScriptID 11 3) 0 22)
					)
					(((ScriptID 10 0) isSet: 8) (self setScript: (ScriptID 11 3) 0 23))
				)
			)
			(else 
				(if (OneOf theVerb 1 5)
					(messager say: noun theVerb 0 0 0 240)
				else
					(self setScript: (ScriptID 11 3) 0 -1)
				)
			)
		)
	)
)

(instance lampSellerScr of Script
	(properties)
	
	(method (dispose)
		(Bclr 59)
		(= register 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 241 0) loop: 0 cel: 0 setCycle: 0 setSpeed: 6)
				(if register
					(= register 0)
				else
					(= register 1)
					(= state
						(switch (Random 0 2)
							(0 4)
							(1 5)
							(2 6)
						))
					(-- state)
				)
				(= seconds (Random 5 8))
			)
			(1
				(if (not (curRoom script?))
					((ScriptID 241 0) loop: 2 cel: 0 setCycle: EndLoop)
					(if (& msgType $0002)
						(if (<= (ego y?) 130)
							(= cycles 1)
						else
							(messager say: 1 0 19 1 self 240)
						)
					else
						(= cycles 6)
					)
				else
					(= cycles (= state 2))
				)
			)
			(2
				(Bset 59)
				(if (not (& msgType $0002))
					(messager say: 1 0 19 1 self 240)
				else
					(= ticks 6)
				)
			)
			(3
				(Bclr 59)
				(= state -1)
				(self cue:)
			)
			(4
				((ScriptID 241 0)
					loop: 0
					cel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
				(= state -1)
			)
			(5
				((ScriptID 241 0) loop: 3 cel: 0)
				(= seconds (Random 2 4))
				(= state -1)
			)
			(6
				((ScriptID 241 0) loop: 4 cel: 0)
				(= ticks 45)
			)
			(7
				((ScriptID 241 0) loop: 5 cel: 0 setCycle: EndLoop self)
				(= state -1)
			)
		)
	)
)

(instance polePoly of Feature
	(properties
		x 19
		y 129
		noun 15
	)
	
	(method (init)
		(= onMeCheck
			((Polygon new:)
				type: 3
				init: 21 134 36 123 39 117 44 124 30 137
				yourself:
			)
		)
		(super init: &rest)
	)
)
