;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64030)
(include sci.sh)
(use Script)
(use Print)


(class TPScript of Script
	(properties
		scratch 0
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
		oMessageList 0
		nTextWidth 0
		createDisplay 1
	)
	
	(method (init)
		(self posnList:)
		(super init: &rest)
	)
	
	(method (dispose)
		(if oMessageList
			((ScriptID 64002 6) disable:)
			((ScriptID 64002 6) setScript: 0)
		)
		(super dispose: &rest)
	)
	
	(method (rememberMessage)
		(Prints LOOKUP_ERROR)
	)
	
	(method (sayMessage)
		(Prints LOOKUP_ERROR)
	)
	
	(method (posnList)
		(cond 
			(oMessageList
				((ScriptID 64002 6) enable:)
				((ScriptID 64002 6) setScript: self)
			)
			(createDisplay ((ScriptID 64002 6) disable:))
		)
		(cond 
			(nTextWidth
				((ScriptID 64002 4) enable:)
				((ScriptID 64002 4) setScript: self)
			)
			(createDisplay ((ScriptID 64002 4) disable:))
		)
	)
)
