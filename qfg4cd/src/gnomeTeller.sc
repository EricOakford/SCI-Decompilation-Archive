;;; Sierra Script 1.0 - (do not remove this comment)
(script# 322)
(include sci.sh)
(use Main)
(use TellerIcon)
(use Feature)
(use System)

(public
	gnomeTeller 0
	peasantTeller 1
)

(instance gnomeTeller of Teller
	(properties
		title 1
	)
	
	(method (init param1 param2 param3)
		(= talker (ScriptID 67 0))
		(super
			init: param2 param3 10 149
			(switch param1
				(4 2)
				(5 1)
			)
		)
	)
)

(instance peasantTeller of Teller
	(properties
		title 1
	)
	
	(method (init param1 param2 param3)
		(= talker (ScriptID 77 0))
		(super
			init:
				param2
				param3
				10
				147
				(switch param1
					(1 3)
					(2 4)
					(4 2)
					(5 1)
					(6 6)
					(7 7)
					(10 5)
					(11 9)
					(12 8)
				)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(2
					(if (Btst 162)
						(if
							(and
								(Btst 127)
								(>= Day 3)
								(OneOf timeODay 4 5)
								(not (Btst 129))
								(not (Btst 130))
							)
							(messager say: 11 6 15 0 0 322)
							(return 1)
						else
							(messager say: 11 6 16 0 0 322)
							(return 1)
						)
					else
						(switch ((CueObj client?) loop?)
							(0 (= talker (ScriptID 77 0)))
							(6 (= talker (ScriptID 76 0)))
							(else 
								(= talker (ScriptID 78 0))
							)
						)
						(super doVerb: theVerb &rest)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)
