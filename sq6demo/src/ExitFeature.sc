;;; Sierra Script 1.0 - (do not remove this comment)
(script# 31)
(include game.sh)
(use Main)
(use Feature)
(use System)

(public
	ExitFeature 0
)

(class ExitFeature of Feature
	(properties
		sightAngle 360
		nextRoom 0
		exitCursor 0
		lastCursor 0
		message 11
	)
	
	(method (init theExitCursor)
		(super init: &rest)
		(theDoits add: self)
		(= exitCursor theExitCursor)
		(= lastCursor theCursor)
	)
	
	(method (doit)
		(cond 
			((self onMe: mouseX mouseY)
				(if (== theCursor theWalkCursor)
					(= lastCursor theCursor)
					((theIconBar curIcon?) type: 16384 message: message)
					(theGame setCursor: exitCursor)
				)
			)
			((== theCursor exitCursor)
				((theIconBar curIcon?) type: 20480 message: V_WALK)
				(theGame setCursor: lastCursor)
			)
		)
	)
	
	(method (dispose)
		(theDoits delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(= approachX (event x?))
		(= approachY (event y?))
		(super handleEvent: event)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 11) 0)
			((OneOf theVerb 7 8 9 10)
				(= theCursor theWalkCursor)
				((theIconBar walkIconItem?) type: 20480 message: V_WALK)
				(theGame handsOff:)
				(curRoom newRoom: nextRoom)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)
