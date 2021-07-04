;;; Sierra Script 1.0 - (do not remove this comment)
(script# HOTSPOT)
(include game.sh)
(use Main)
(use Print)
(use System)

(public
	HotSpot 0
)

(class HotSpot of Object
	(properties
		_respondVerbs 0
		theVerb 0
	)
	
	(method (doit)
		(cond 
			(curInvItem
				(if (user useHotspots?)
					(theCursor
						view: (curInvItem view?)
						loop: (curInvItem loop?)
						setCel: (+ (curInvItem cel?) 1)
					)
				)
				(user message: (curInvItem verb?))
			)
			((not (theExitFeature respondsTo: #exitDir))
				(if (user useHotspots?)
					(theCursor view: ARROW_CURSOR loop: 0 setCel: 1)
				)
				(user message: theVerb)
			)
			(else
				(if (user useHotspots?)
					(theCursor
						view: 989
						loop: (theExitFeature exitDir?)
						setCel: 0
					)
				)
				(user message: theVerb)
			)
		)
	)
	
	(method (setProps args)
		(= theVerb [args 0])
		(if (> argc 1)
			(self respondVerbs: &rest)
		)
	)
	
	(method (respondVerbs args &tmp i temp1)
		(if (!= [args 0] 9999)
			(= _respondVerbs 0)
		else
			(Printf {arg0: %d, arg1: %d} [args 0] [args 1])
		)
		(if (and argc respondCode [args 0])
			(for ((= i 0)) (< i argc) ((++ i))
				(if (== [args i] 9999)
					(SetDebug)
				else
					((ScriptID 0 4) addToEnd: [args i])
					(= temp1 (respondCode doit: [args i]))
					(self _respondVerbs: (| (self _respondVerbs?) temp1))
				)
			)
		)
	)
	
	(method (addRespondVerb args &tmp i obj)
		(if (and argc respondCode [args 0])
			(for ((= i 0)) (< i argc) ((++ i))
				((ScriptID 0 4) addToEnd: [args i])
				(= obj (respondCode doit: [args i]))
				(self _respondVerbs: (| (self _respondVerbs?) obj))
			)
		)
	)
)
