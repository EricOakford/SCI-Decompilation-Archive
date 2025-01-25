;;; Sierra Script 1.0 - (do not remove this comment)
(script# 321)
(include game.sh) (include "321.shm")
(use Main)
(use TellerIcon)
(use System)

(public
	innKeepTeller 0
)

(instance innKeepTeller of Teller
	(properties
		title 1
	)
	
	(method (init eventNum obj msgFile)
		(= talker (ScriptID 98 0))
		(super
			init: obj msgFile
				N_TELL V_INNKEEPER_TELL
				(switch eventNum
					(1 N_ASK_FIRST)
					(2 4)
					(4 2)
					(5 1)
					(6 7)
					(7 8)
					(8 10)
					(9 5)
					(10 6)
					(11 10)
					(12 9)
				)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(if (Btst 162)
				(if
					(and
						(Btst 127)
						(>= Day 3)
						(OneOf timeODay 4 5)
						(not (Btst 129))
						(not (Btst 130))
					)
					(messager say: 12 6 14 0 0 321)
					(return 1)
				else
					(messager say: 12 6 15 0 0 321)
					(return 1)
				)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
)
