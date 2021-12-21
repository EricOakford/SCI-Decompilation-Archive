;;; Sierra Script 1.0 - (do not remove this comment)
(script# 14000)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use ExitFeature)
(use Print)
(use Talker)
(use Actor)

(public
	roSnails 0
	toSlim 1
	toSlime 2
)

(instance foExit of CUExitFeature
	(properties
		unregisterDefaultHandler 10100
	)
)

(instance poSlimNormalEyes of Prop
	(properties
		x 240
		y 220
		priority 300
		fixPriority 1
		view 10116
		loop 4
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Blink 200)
	)
)

(instance poSlimBlink of Prop
	(properties
		x 240
		y 220
		priority 300
		fixPriority 1
		view 10116
		loop 4
	)
)

(instance voSlimNormalMouth of View
	(properties
		x 240
		y 220
		priority 300
		fixPriority 1
		view 10116
		loop 1
		cel 2
	)
)

(instance toSlim of Talker
	(properties
		x 240
		y 220
		view 10116
		loop 1
		priority 300
	)
	
	(method (init)
		(LOOKUP_ERROR hide:)
		(LOOKUP_ERROR hide:)
		(= eyes LOOKUP_ERROR)
		(super init: &rest)
	)
	
	(method (dispose)
		(LOOKUP_ERROR show:)
		(LOOKUP_ERROR show:)
		(super dispose: &rest)
	)
)

(instance poSlim of Prop
	(properties
		x 240
		y 220
		view 10116
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 2 8 18 21 4 9)
		(if (> global225 4) (self setVisibleRange: 23))
		(= signal (| signal $1000))
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
	)
	
	(method (dispose)
		(LOOKUP_ERROR dispose:)
		(LOOKUP_ERROR dispose:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(23
				(ego setScript: LOOKUP_ERROR)
			)
			(1
				(switch global225
					(0)
					(1
						(++ global225)
						(theGame handsOff:)
						(messager say: noun theVerb 1 0 (ScriptID 64020 0))
					)
					(2
						(++ global225)
						(theGame handsOff:)
						(messager say: noun theVerb 2 0 (ScriptID 64020 0))
					)
					(3
						(theGame handsOff:)
						(if ((ScriptID 64017 0) test: 27)
							(++ global225)
							(messager say: noun theVerb 3 0 (ScriptID 64020 0))
						else
							(messager say: noun theVerb 8 0 (ScriptID 64020 0))
						)
					)
					(4
						(++ global225)
						(theGame handsOff:)
						(self setVisibleRange: 23)
						(messager say: noun theVerb 4 0 (ScriptID 64020 0))
						((ScriptID 64017 0) set: 24)
					)
					(5
						(theGame handsOff:)
						(messager say: noun theVerb 5 0 (ScriptID 64020 0))
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poSlimeNormalEyes of Prop
	(properties
		x 369
		y 218
		priority 300
		fixPriority 1
		view 10116
		loop 10
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Blink 200)
	)
)

(instance poSlimeBlink of Prop
	(properties
		x 369
		y 218
		priority 300
		fixPriority 1
		view 10116
		loop 10
	)
)

(instance poSlimeNormalMouth of Prop
	(properties
		x 369
		y 218
		priority 300
		fixPriority 1
		view 10116
		loop 8
		cel 2
	)
)

(instance toSlime of Talker
	(properties
		x 369
		y 218
		view 10116
		loop 8
		priority 300
	)
	
	(method (init)
		(LOOKUP_ERROR hide:)
		(LOOKUP_ERROR hide:)
		(= eyes LOOKUP_ERROR)
		(super init: &rest)
	)
	
	(method (dispose)
		(LOOKUP_ERROR show:)
		(LOOKUP_ERROR show:)
		(super dispose: &rest)
	)
)

(instance poSlime of Prop
	(properties
		x 369
		y 218
		view 10116
		loop 7
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 2 8 21 18 4 9)
		(if (> global225 4) (self setVisibleRange: 23))
		(= signal (| signal $1000))
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
	)
	
	(method (dispose)
		(LOOKUP_ERROR dispose:)
		(LOOKUP_ERROR dispose:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(LOOKUP_ERROR doVerb: theVerb)
	)
)

(instance soPickUpSnails of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 0 23 9 0 self)
			)
			(1
				(messager say: 0 23 11 0 self)
			)
			(2
				(Prints LOOKUP_ERROR)
				(ego put: ((ScriptID 64001 0) get: 13))
				(ego get: ((ScriptID 64001 0) get: 6))
				((ScriptID 64017 0) set: 6)
				((ScriptID 64017 0) set: 24)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance roSnails of TPRoom
	(properties
		picture 14000
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: 14000)
		(theGame handsOn:)
		(LOOKUP_ERROR init:)
		(if (not ((ScriptID 64017 0) test: 6))
			(LOOKUP_ERROR init:)
			(LOOKUP_ERROR init:)
		)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 0) get: 13))
		(ego get: ((ScriptID 64001 0) get: 3))
		((ScriptID 64017 0) set: 27)
	)
)
