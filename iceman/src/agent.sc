;;; Sierra Script 1.0 - (do not remove this comment)
(script# 309)
(include game.sh)
(use Main)
(use Intrface)
(use n361)
(use Actor)

(public
	agent 0
)

(instance agent of Actor
	(properties
		view 212
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/babe,stacy][/!*]')
				(if (and (HaveMem 1616) (< (ego distanceTo: self) 70))
					(AddExtras
						111
						(ScriptID 360 0)
						(ScriptID 360 1)
						(ScriptID 360 2)
					)
				else
					(Print 309 0)
				)
			)
			((> (ego distanceTo: self) 60))
			((Said 'get,ask/name')
				(Print 309 1)
			)
		)
	)
)
