;;; Sierra Script 1.0 - (do not remove this comment)
(script# 51400)
(include sci.sh)
(use Main)
(use TPRoom)
(use ExitFeature)
(use PolyPath)

(public
	roLycentiasLairEntr 0
)

(instance foHorrorsExit of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 4))
		(self setRect: 0 0 15 316)
	)
	
	(method (doVerb)
		(ego setMotion: PolyPath 0 300 self)
	)
	
	(method (cue)
		(curRoom newRoom: -14236)
	)
)

(instance roLycentiasLairEntr of TPRoom
	(properties
		picture -14136
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: -14136)
		(LOOKUP_ERROR init:)
		(ego init: oPanner:)
		(theGame handsOn:)
		(switch prevRoomNum
			(else  (ego posn: 30 300))
		)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
